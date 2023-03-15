package com.quiz.data.cache

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.quiz.domain.models.Question
import com.quiz.domain.models.SavedQuiz
import com.quiz.utils.QUIZ_DATABASE
import com.quiz.utils.toEnumCategory
import com.quiz.utils.toEnumDifficulty

@Entity(tableName = QUIZ_DATABASE)
data class QuizEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val saveTime: String,
    val category: List<String>,
    val numberOfQuestions: Int,
    val difficulty: String,
    val questions: List<Question>
) {
    fun toDomain(): SavedQuiz {
        return SavedQuiz(
            saveTime = this.saveTime,
            numberOfQuestions = this.numberOfQuestions,
            questions = this.questions,
            categories = this.category.map {
                it.toEnumCategory()
            },
            difficulty = this.difficulty.toEnumDifficulty()
        )
    }
}
