package com.anim.janitri.domain.use_cases.sync_colors_usecase

import com.anim.janitri.domain.model.ColorSchemeDto
import com.anim.janitri.domain.repositories.ColorSchemeRepo
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SyncColorsUseCase @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val colorSchemeRepo: ColorSchemeRepo
) {
    suspend fun invoke(colors: List<ColorSchemeDto>) {
        withContext(Dispatchers.IO) {
            syncToFireStore(colors)
        }
    }

    private suspend fun syncToFireStore(colors: List<ColorSchemeDto>) {
        val batch = firestore.batch()
        val ids: List<Long> = colors.map { color -> color.id }

        colors.forEach { colorSchemeDto ->
            val colorData = hashMapOf(
                "id" to colorSchemeDto.id,
                "colorHex" to colorSchemeDto.colorHex,
                "date" to colorSchemeDto.date,
                "sync" to colorSchemeDto.sync
            )

            val docRef = firestore.collection("colors").document(colorSchemeDto.id.toString())
            batch.set(docRef, colorData)
        }

        try {
            batch.commit().await()
            colorSchemeRepo.updateSyncStatus(ids)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}