package com.anim.janitri.domain.use_cases.update_synced_status_usecase

import com.anim.janitri.common.Resource
import com.anim.janitri.domain.repositories.ColorSchemeRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateSyncedStatusUseCase @Inject constructor(
    private val colorSchemeRepo: ColorSchemeRepo
) {

    operator fun invoke(ids: List<Long>): Flow<Resource<String>> = flow {
        try {
            emit(Resource.Loading())
            colorSchemeRepo.updateSyncStatus(ids)
            emit(Resource.Success(data = "Sync status updated successfully"))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "Unexpected error occurred"))
        }
    }
}