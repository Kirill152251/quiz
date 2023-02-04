package com.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.di.ConfigurationScreenComponentViewModel
import com.quiz_configuration.R
import com.quiz_configuration.databinding.FragmentQuizConfigurationBinding

class QuizConfigurationScreen : Fragment() {

    private var _binding: FragmentQuizConfigurationBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<ConfigurationScreenComponentViewModel>()
            .configurationComponent.inject(this)
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
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}