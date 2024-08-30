package com.anim.janitri.presentation.color_list_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anim.janitri.common.Resource
import com.anim.janitri.domain.model.ColorSchemeDto
import com.anim.janitri.domain.use_cases.delete_color_usecase.DeleteColorUseCase
import com.anim.janitri.domain.use_cases.get_color_list_usecase.GetColorUseCase
import com.anim.janitri.domain.use_cases.get_unsynced_color_list_usecase.GetUnSyncedColorListUseCase
import com.anim.janitri.domain.use_cases.save_color_usecase.SaveColorUseCase
import com.anim.janitri.domain.use_cases.sync_colors_usecase.SyncColorsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.security.PrivateKey
import javax.inject.Inject

@HiltViewModel
class ColorListScreenViewModel @Inject constructor(
    private val saveColorUseCase: SaveColorUseCase,
    private val getColorUseCase: GetColorUseCase,
    private val getUnSyncedColorListUseCase: GetUnSyncedColorListUseCase,
    private val deleteColorUseCase: DeleteColorUseCase,
    private val syncColorsUseCase: SyncColorsUseCase
): ViewModel() {

    /** To store sync status.*/
    private val _syncStatus = MutableStateFlow<Resource<Unit>>(Resource.Loading())
    val syncStatus: StateFlow<Resource<Unit>> = _syncStatus

    /** To store save status.*/
    private val _saveColorStatus = MutableStateFlow(SaveColorState())
    val saveColorStatus: StateFlow<SaveColorState> = _saveColorStatus // For purpose of showing saving status not needed in our case.

    /** To store delete status.*/
    private val _deleteColorStatus = MutableStateFlow(DeleteColorStatus())
    val deleteColorStatus: StateFlow<DeleteColorStatus> = _deleteColorStatus

    /** To store the colors as a collector.*/
    private val _getColors = MutableStateFlow(GetColorsState())
    val getColors: StateFlow<GetColorsState> = _getColors

    /** To store unSynced colors as a collector.*/
    private val _getUnSyncedColors = MutableStateFlow(GetUnSyncedColorsState())
    val getUnSyncedColors: StateFlow<GetUnSyncedColorsState> = _getUnSyncedColors

    init {
        getColors()
        getUnSyncedColors()
    }

    fun insertColor(colorSchemeDto: ColorSchemeDto) {
        saveColorUseCase.invoke(colorSchemeDto).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _saveColorStatus.value = SaveColorState(success = result.data!!)
                }
                is Resource.Error -> {
                    _saveColorStatus.value = SaveColorState(error = result.message!!)
                }
                else -> {
                    _saveColorStatus.value = SaveColorState()
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getColors() {
        getColorUseCase.invoke().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _getColors.value = GetColorsState(success = result.data!!)
                }
                is Resource.Error -> {
                    _getColors.value = GetColorsState(error = result.message!!)
                }
                else -> {
                    _getColors.value = GetColorsState()
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getUnSyncedColors() {
        getUnSyncedColorListUseCase.invoke().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _getUnSyncedColors.value = GetUnSyncedColorsState(success = result.data!!)
                }
                is Resource.Error -> {
                    _getUnSyncedColors.value = GetUnSyncedColorsState(error = result.message!!)
                }
                else -> {
                    _getUnSyncedColors.value = GetUnSyncedColorsState()
                }
            }
        }.launchIn(viewModelScope)
    }

    fun syncColors(colors: List<ColorSchemeDto>) {
        viewModelScope.launch {
            _syncStatus.value = Resource.Loading()

            try {
                syncColorsUseCase.invoke(colors)
                _syncStatus.value = Resource.Success(Unit)
            } catch (e: Exception) {
                _syncStatus.value =
                    Resource.Error(message = e.localizedMessage ?: "Unexpected error occurred")
            }
        }
    }

    fun deleteColor(id: Long) {
        deleteColorUseCase.invoke(id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _deleteColorStatus.value = DeleteColorStatus(success = result.data!!)
                }
                is Resource.Error -> {
                    _deleteColorStatus.value = DeleteColorStatus(error = result.message!!)
                }
                else -> _deleteColorStatus.value = DeleteColorStatus()
            }
        }.launchIn(viewModelScope)
    }
}

data class SaveColorState(
    val isLoading: Boolean = false,
    val success: String = "",
    val error: String = ""
)
data class DeleteColorStatus(
    val isLoading: Boolean = false,
    val success: String = "",
    val error: String = ""
)

data class GetColorsState(
    val isLoading: Boolean = false,
    val success: List<ColorSchemeDto> = emptyList(),
    val error: String = ""
)

data class GetUnSyncedColorsState(
    val isLoading: Boolean = false,
    val success: List<ColorSchemeDto> = emptyList(),
    val error: String = ""
)