package com.quiz.domain.models

import com.quiz.data.cache.QuizEntity

data class SavedQuiz(
    val saveTime: String,
    val questions: List<Question>,
    val numberOfQuestions: Int,
    val difficulty: Difficulty,
    val categories: List<Category>
) {
    fun toEntity(): QuizEntity {
        val questionsIds = mutableListOf<String>()
        this.questions.forEach {
            questionsIds.add(it.id)
        }
        val quizId = questionsIds.joinToString("")
        return QuizEntity(
            id = quizId,
            saveTime = this.saveTime,
            questions = this.questions,
            numberOfQuestions = this.numberOfQuestions,
            difficulty = this.difficulty.convertToUIString(),
            category = this.categories.map {
                it.convertToUIString()
            }
        )
    }

    fun toDomain(): Quiz {
        return Quiz(
            questions = this.questions,
            categories = this.categories,
            numberOfQuestions = this.numberOfQuestions,
            difficulty = this.difficulty
        )
    }
}
