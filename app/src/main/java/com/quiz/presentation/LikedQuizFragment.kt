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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.quiz.R
import com.quiz.appComponent
import com.quiz.databinding.FragmentFavoriteQuizzesScreenBinding
import com.quiz.domain.models.SavedQuiz
import com.quiz.presentation.view_models.LikedQuizScreenEvent
import com.quiz.presentation.view_models.LikedQuizScreenState
import com.quiz.presentation.view_models.LikedQuizScreenViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class LikedQuizFragment : Fragment() {

    private var _binding: FragmentFavoriteQuizzesScreenBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<LikedQuizScreenViewModel> { viewModelFactory }

    private val adapter = QuizAdapter(object : QuizAdapter.Listener {
        override fun deleteItem(item: SavedQuiz) {
            viewModel.setEvent(LikedQuizScreenEvent.DeleteItem(item))
        }

        override fun clickItem(item: SavedQuiz) {
            viewModel.setEvent(LikedQuizScreenEvent.SetupChosenQuiz(item))
            findNavController()
                .navigate(R.id.action_favoriteQuizzesScreen_to_quiz_nav)
        }
    })

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteQuizzesScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvQuiz.adapter = adapter
        binding.rvQuiz.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state) {
                        LikedQuizScreenState.EmptyList -> {
                            binding.apply {
                                rvQuiz.isVisible = false
                                textEmptyList.isVisible = true
                            }
                        }
                        LikedQuizScreenState.Idle -> {}
                        is LikedQuizScreenState.LikedQuizList -> {
                            binding.apply {
                                rvQuiz.isVisible = true
                                textEmptyList.isVisible = false
                            }
                            adapter.submitList(state.quizList)
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}