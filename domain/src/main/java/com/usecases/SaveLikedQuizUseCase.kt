package com.usecases

import com.models.Quiz

interface SaveLikedQuizUseCase {
    suspend operator fun invoke(quiz: Quiz)
}