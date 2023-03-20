package com.quiz.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.quiz.R
import com.quiz.appComponent
import com.quiz.databinding.FragmentQuestionScreenBinding
import com.quiz.presentation.view_models.QuestionScreenViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class QuestionScreen : Fragment() {

    private var _binding: FragmentQuestionScreenBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<QuestionScreenViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuestionScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setCurrentQuestion()
        binding.textNumberOfAllQuestions.text = viewModel.getQuizSize().toString()
        bindQuestion()
        bindQuestionNumber()
        binding.btnSubmit.setOnClickListener {
            binding.apply {
                if (textNumberOfCurrentQuestion.text == viewModel.getQuizSize().toString()) {
                    findNavController().navigate(R.id.action_questionScreen_to_resultScreen)
                }
                when (binding.radioGroupNumber.checkedRadioButtonId) {
                    firstAnswer.id -> viewModel.setNextQuestion(firstAnswer.text.toString())
                    secondAnswer.id -> viewModel.setNextQuestion(secondAnswer.text.toString())
                    thirdAnswer.id -> viewModel.setNextQuestion(thirdAnswer.text.toString())
                    else -> viewModel.setNextQuestion(forthAnswer.text.toString())
                }
            }
        }
    }

    private fun bindQuestion() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.currentQuestion.collect { question ->
                    binding.apply {
                        textQuestion.text = question.question
                        firstAnswer.text = question.shuffledAnswers[0]
                        secondAnswer.text = question.shuffledAnswers[1]
                        thirdAnswer.text = question.shuffledAnswers[2]
                        forthAnswer.text = question.shuffledAnswers[3]
                    }
                }
            }
        }
    }

    private fun bindQuestionNumber() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.numberOfCurrentQuestion.collect { number ->
                    binding.textNumberOfCurrentQuestion.text = number.toString()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}