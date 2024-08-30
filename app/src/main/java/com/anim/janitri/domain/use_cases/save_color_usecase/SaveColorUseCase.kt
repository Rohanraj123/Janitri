package com.anim.janitri.domain.use_cases.save_color_usecase

import com.anim.janitri.common.Resource
import com.anim.janitri.domain.model.ColorSchemeDto
import com.anim.janitri.domain.repositories.ColorSchemeRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveColorUseCase @Inject constructor(
    private val colorSchemeRepo: ColorSchemeRepo
) {
    operator fun invoke(colorSchemeDto: ColorSchemeDto): Flow<Resource<String>> = flow {
        try {
            emit(Resource.Loading())
            colorSchemeRepo.insertColor(colorSchemeDto)
            emit(Resource.Success("Colors are saved successfully"))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "Unexpected error occurred"))
        }
    }
}