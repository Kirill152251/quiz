package com.usecases

import com.models.QuestionsMetadata

interface FetchMetadataUseCase {
    suspend operator fun invoke(): QuestionsMetadata
}