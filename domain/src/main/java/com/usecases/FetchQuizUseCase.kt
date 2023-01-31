package com.usecases

import com.models.Category
import com.models.Difficulty
import com.models.Quiz

interface FetchQuizUseCase {
    suspend operator fun invoke(
        difficulty: Difficulty,
        category: List<Category>,
        limit: Number
    ): Quiz
}