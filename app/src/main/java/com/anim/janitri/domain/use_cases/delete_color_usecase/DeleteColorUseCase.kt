package com.anim.janitri.domain.use_cases.delete_color_usecase

import com.anim.janitri.common.Resource
import com.anim.janitri.domain.repositories.ColorSchemeRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteColorUseCase @Inject constructor(
    private val colorSchemeRepo: ColorSchemeRepo
) {

    operator fun invoke(id: Long): Flow<Resource<String>> = flow {
        try {
            emit(Resource.Loading())
            colorSchemeRepo.deleteColor(id)
            emit(Resource.Success(data = "Successfully deleted the color"))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message ?: "Unexpected error occurred"))
        }
    }
}