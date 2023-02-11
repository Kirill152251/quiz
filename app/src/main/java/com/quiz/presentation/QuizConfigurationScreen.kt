package com.quiz.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.quiz.R
import com.quiz.appComponent
import com.quiz.databinding.FragmentQuizConfigurationBinding
import com.quiz.presentation.view_models.QuizConfigurationViewModel
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
        binding.btnStartQuiz.setOnClickListener {
            findNavController().navigate(R.id.action_quizConfiguration_to_questionScreen)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}