package com.quiz.data.cache

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class QuizDatabaseTest {

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: QuizDatabase
    private lateinit var dao: QuizDao

    private val entity = QuizEntity(
        id = "1",
        difficulty = "hard",
        numberOfQuestions = 1,
        category = listOf("category"),
        questions = listOf(),
        saveTime = "00"
    )

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            QuizDatabase::class.java
        ).addTypeConverter(Converter()).build()
        dao = db.quizDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun add_item_should_return_this_item_inFlow() = runTest {
        dao.insert(entity)
        dao.getData().test {
            val data = awaitItem()
            assert(data.contains(entity))
            assert(data[0].category.first() == "category")
            cancel()
        }
    }

    @Test
    fun add_two_same_entities_should_save_only_one() = runTest {
        dao.insert(entity)
        val id = dao.insert(entity)
        dao.getData().test {
            val list = awaitItem()
            assert(id == -1L)
            assert(list.size == 1)
        }
    }

    @Test
    fun add_item_and_delete_item_list_should_be_empty() = runTest {
        dao.insert(entity)
        dao.delete(entity)
        dao.getData().test {
            val list = awaitItem()
            assert(list.isEmpty())
        }
    }
}
