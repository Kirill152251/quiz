package com.usecases

import com.models.Quiz

interface DeleteQuizUseCase {
    suspend operator fun invoke(quiz: Quiz)
}