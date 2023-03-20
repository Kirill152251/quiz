package com.quiz.domain.models

data class Quiz(
    val questions: List<Question>,
    val numberOfQuestions: Int,
    val difficulty: Difficulty,
    val categories: List<Category>
) {
    fun toSavedQuiz(saveTime: String): SavedQuiz {
        return SavedQuiz(
            saveTime = saveTime,
            questions = this.questions,
            numberOfQuestions = this.numberOfQuestions,
            difficulty = this.difficulty,
            categories = this.categories
        )
    }

    companion object {
        val emptyQuiz = Quiz(listOf(), 0, Difficulty.HARD, listOf())
    }
}
