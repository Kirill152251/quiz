package com.usecases

import com.models.Quiz

interface GetLikedQuizzesUseCase {
    suspend operator fun invoke(): List<Quiz>
}