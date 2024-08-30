package com.anim.janitri.domain.use_cases.get_unsynced_color_list_usecase

import com.anim.janitri.common.Resource
import com.anim.janitri.domain.model.ColorSchemeDto
import com.anim.janitri.domain.repositories.ColorSchemeRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUnSyncedColorListUseCase @Inject constructor(
    private val colorSchemeRepo: ColorSchemeRepo
) {
    operator fun invoke(): Flow<Resource<List<ColorSchemeDto>>> = flow {
        try {
            emit(Resource.Loading())
            colorSchemeRepo.getUnSyncedColors().collect { result ->
                emit(Resource.Success(result))
            }
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "Unexpected error occurred"))
        }
    }
}