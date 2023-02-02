package com.di

import androidx.lifecycle.ViewModel
import com.data.remote.QuizService
import com.presentation.QuizConfiguration
import dagger.Component
import javax.inject.Scope
import kotlin.properties.Delegates.notNull

@Component(dependencies = [ConfigurationScreenDeps::class])
@ConfigurationScreenScope
internal interface QuizConfigurationComponent {

    fun inject(fragment: QuizConfiguration)

    @Component.Builder
    interface Builder {
        fun deps(deps: ConfigurationScreenDeps): Builder
        fun build(): QuizConfigurationComponent
    }
}


internal class ConfigurationScreenComponentViewModel : ViewModel() {
    val configurationComponent =
        DaggerQuizConfigurationComponent.builder().deps(ConfigurationScreenDepsProvider.deps)
            .build()
}

interface ConfigurationScreenDeps {
    val quizService: QuizService
}

interface ConfigurationScreenDepsProvider {

    val deps: ConfigurationScreenDeps

    companion object : ConfigurationScreenDepsProvider by ConfigurationScreenDepsStore
}

object ConfigurationScreenDepsStore : ConfigurationScreenDepsProvider {
    override var deps: ConfigurationScreenDeps by notNull()
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ConfigurationScreenScope