package com.quiz.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.quiz.R
import com.quiz.appComponent
import com.quiz.databinding.FragmentQuizConfigurationBinding
import com.quiz.domain.models.Category
import com.quiz.domain.models.Category.*
import com.quiz.domain.models.Difficulty
import com.quiz.domain.models.Difficulty.*
import com.quiz.domain.models.Quiz
import com.quiz.presentation.viewmodel.QuizConfigurationViewModel
import com.quiz.utils.ApiResult
import kotlinx.coroutines.launch
import javax.inject.Inject

class QuizConfigurationScreen : Fragment() {

    private var _binding: FragmentQuizConfigurationBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<QuizConfigurationViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizConfigurationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (viewModel.currentQuiz() != Quiz.emptyQuiz) {
            findNavController().navigate(R.id.action_quizConfiguration_to_questionScreen)
        }
        binding.btnStartQuiz.setOnClickListener {
            fetchQuiz()
        }
    }

    private fun fetchQuiz() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.fetchQuizFlow(getDifficulty(), getNumberOfQuestions(), getCategory())
                    .collect { result ->
                        when (result) {
                            is ApiResult.Error -> {
                                binding.progressHorizontal.isVisible = false
                                Snackbar.make(
                                    requireView(),
                                    getString(R.string.error_message),
                                    3500
                                ).show()
                            }
                            ApiResult.Loading -> {
                                binding.progressHorizontal.isVisible = true
                            }
                            is ApiResult.Success -> {
                                binding.progressHorizontal.isVisible = false
                                viewModel.cacheCurrentQuiz(result.result)
                                findNavController().navigate(R.id.action_quizConfiguration_to_questionScreen)
                            }
                        }
                    }
            }
        }
    }

    private fun getCategory(): List<Category> {
        val list = mutableListOf<Category>()
        binding.apply {
            if (checkboxArts.isChecked) list.add(ART_AND_LITERATURE)
            if (checkboxFilms.isChecked) list.add(FILM_AND_TV)
            if (checkboxFood.isChecked) list.add(FOOD_AND_DRINKS)
            if (checkboxGeneral.isChecked) list.add(GENERAL_KNOWLEDGE)
            if (checkboxGeography.isChecked) list.add(GEOGRAPHY)
            if (checkboxScience.isChecked) list.add(SCIENCE)
            if (checkboxSociety.isChecked) list.add(SOCIETY_AND_CULTURE)
            if (checkboxSport.isChecked) list.add(SPORT_AND_LEISURE)
            if (checkboxMusic.isChecked) list.add(MUSIC)
            if (checkboxHistory.isChecked) list.add(HISTORY)
        }
        return list
    }

    private fun getNumberOfQuestions(): Int {
        return when (binding.radioGroupNumber.checkedRadioButtonId) {
            R.id.radio_button_five -> 5
            R.id.radio_button_ten -> 10
            R.id.radio_button_fifteen -> 15
            else -> 20
        }
    }

    private fun getDifficulty(): Difficulty {
        return when (binding.radioGroupDifficulty.checkedRadioButtonId) {
            R.id.radio_button_easy -> EASY
            R.id.radio_button_medium -> MEDIUM
            else -> HARD
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}