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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.quiz.R
import com.quiz.appComponent
import com.quiz.databinding.FragmentResultScreenBinding
import com.quiz.presentation.view_models.ResultScreenViewModel
import javax.inject.Inject

class ResultScreen : Fragment() {

    private var _binding: FragmentResultScreenBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<ResultScreenViewModel> { viewModelFactory }

    private val adapter = AnswersAdapter()

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            rvAnswers.adapter = adapter
            rvAnswers.layoutManager = LinearLayoutManager(requireContext())
            rvAnswers.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    LinearLayoutManager.VERTICAL
                )
            )
            textResult.text = getString(
                R.string.quiz_result,
                viewModel.getNumberOfCorrectAnswers(),
                viewModel.getNumberOfQuestions()
            )
            btnStartNewQuiz.setOnClickListener {
                findNavController().navigate(R.id.action_resultScreen_to_quizConfiguration)
            }
        }
        adapter.submitList(viewModel.getAnsweredQuestions())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}