package com.quiz.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import com.quiz.R
import com.quiz.databinding.AnswerItemBinding
import com.quiz.databinding.ItemBtnBinding
import com.quiz.domain.models.AnsweredQuestion

class AnswersAdapter(private val onButtonClick: () -> Unit) :
    ListAdapter<AnsweredQuestion, AnswersAdapter.ResultViewHolder>(ItemCallback) {

    sealed class ResultViewHolder(binding: ViewBinding) : ViewHolder(binding.root) {
        class AnswerViewHolder(private val binding: AnswerItemBinding, val context: Context) :
            ResultViewHolder(binding) {
            fun bind(item: AnsweredQuestion) {
                binding.apply {
                    val indexOfChosenAnswer = item.allAnswers.indexOf(item.chosenAnswer)
                    val indexOfCorrectAnswer = item.allAnswers.indexOf(item.correctAnswer)
                    imageIndicator1.isVisible = false
                    imageIndicator2.isVisible = false
                    imageIndicator3.isVisible = false
                    imageIndicator4.isVisible = false
                    if (item.isAnsweredCorrect()) {
                        setCorrectnessIcon(this, indexOfChosenAnswer, R.drawable.ic_correct)
                    } else {
                        setCorrectnessIcon(this, indexOfCorrectAnswer, R.drawable.ic_correct)
                        setCorrectnessIcon(this, indexOfChosenAnswer, R.drawable.ic_wrong)
                    }
                    textQuestion.text = item.questionText
                    textFirstAnswer.text = context.getString(R.string.first, item.allAnswers[0])
                    textSecondAnswer.text = context.getString(R.string.second, item.allAnswers[1])
                    textThirdAnswer.text = context.getString(R.string.third, item.allAnswers[2])
                    textForthAnswer.text = context.getString(R.string.forth, item.allAnswers[3])
                }
            }
        }

        class ButtonViewHolder(
            private val binding: ItemBtnBinding,
            private val onButtonClick: () -> Unit
        ) : ResultViewHolder(binding) {
            fun bind() {
                binding.btnStartNewQuiz.setOnClickListener {
                    onButtonClick()
                }
            }
        }

        fun setCorrectnessIcon(
            binding: AnswerItemBinding,
            index: Int,
            @DrawableRes icon: Int
        ) {
            binding.apply {
                when (index) {
                    0 -> {
                        imageIndicator1.setBackgroundResource(icon)
                        imageIndicator1.isVisible = true
                    }
                    1 -> {
                        imageIndicator2.setBackgroundResource(icon)
                        imageIndicator2.isVisible = true
                    }
                    2 -> {
                        imageIndicator3.setBackgroundResource(icon)
                        imageIndicator3.isVisible = true
                    }
                    else -> {
                        imageIndicator4.setBackgroundResource(icon)
                        imageIndicator4.isVisible = true
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == TYPE_ANSWER) {
            val binding = AnswerItemBinding.inflate(inflater, parent, false)
            ResultViewHolder.AnswerViewHolder(binding, parent.context)
        } else {
            val binding = ItemBtnBinding.inflate(inflater, parent, false)
            ResultViewHolder.ButtonViewHolder(binding, onButtonClick)
        }
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        when (holder) {
            is ResultViewHolder.AnswerViewHolder -> holder.bind(getItem(position))
            is ResultViewHolder.ButtonViewHolder -> holder.bind()
        }
    }

    companion object {
        private const val TYPE_ANSWER = 0
        private const val TYPE_BUTTON = 1
    }

    override fun getItemCount(): Int {
        return currentList.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == currentList.size) {
            TYPE_BUTTON
        } else {
            TYPE_ANSWER
        }

    }

    object ItemCallback : DiffUtil.ItemCallback<AnsweredQuestion>() {

        override fun areItemsTheSame(
            oldItem: AnsweredQuestion,
            newItem: AnsweredQuestion
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: AnsweredQuestion,
            newItem: AnsweredQuestion
        ): Boolean {
            return oldItem.questionText == newItem.questionText
        }
    }
}