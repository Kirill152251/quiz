package com.quiz.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.quiz.R
import com.quiz.databinding.FragmentQuizConfigurationBinding

class QuizConfigurationScreen : Fragment() {

    private var _binding: FragmentQuizConfigurationBinding? = null
    private val binding get() = _binding!!

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