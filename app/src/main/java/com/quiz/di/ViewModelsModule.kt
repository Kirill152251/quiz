package com.quiz.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.quiz.presentation.view_models.QuestionScreenViewModel
import com.quiz.presentation.view_models.QuizConfigurationViewModel
import com.quiz.presentation.view_models.ResultScreenViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass

@Module
interface ViewModelsModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(QuizConfigurationViewModel::class)
    @Singleton
    fun bindQuizConfigViewModel(viewModel: QuizConfigurationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(QuestionScreenViewModel::class)
    @Singleton
    fun bindQuestionViewModel(viewModel: QuestionScreenViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ResultScreenViewModel::class)
    @Singleton
    fun bindResultViewModel(viewModel: ResultScreenViewModel): ViewModel
}

@Singleton
class ViewModelFactory @Inject constructor(
    private val creators: @JvmSuppressWildcards Map<Class<out ViewModel>,
            Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var creator: Provider<out ViewModel>? = creators[modelClass]
        if (creator == null) {
            for ((key, value) in creators) {
                if (modelClass.isAssignableFrom(key)) {
                    creator = value
                    break
                }
            }
        }
        if (creator == null) {
            throw IllegalArgumentException("Unknown model class: $modelClass")
        }
        try {
            @Suppress("UNCHECKED_CAST")
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)