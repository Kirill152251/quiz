package com.quiz.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.quiz.R
import com.quiz.databinding.AnswerItemBinding
import com.quiz.domain.models.AnsweredQuestion

class AnswersAdapter :
    ListAdapter<AnsweredQuestion, AnswersAdapter.AnswerViewHolder>(ItemCallback) {

    class AnswerViewHolder(val binding: AnswerItemBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AnswerItemBinding.inflate(inflater)
        return AnswerViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.apply {
            val indexOfChosenAnswer = item.allAnswers.indexOf(item.chosenAnswer)
            val indexOfCorrectAnswer = item.allAnswers.indexOf(item.correctAnswer)
            if (item.isAnsweredCorrect()) {
                setCorrectnessIcon(this, indexOfChosenAnswer, R.drawable.ic_correct)
            } else {
                setCorrectnessIcon(this, indexOfCorrectAnswer, R.drawable.ic_correct)
                setCorrectnessIcon(this, indexOfChosenAnswer, R.drawable.ic_wrong)
            }

            textQuestion.text = item.questionText

            textFirstAnswer.text = holder.context.getString(R.string.first, item.allAnswers[0])
            textSecondAnswer.text = holder.context.getString(R.string.second, item.allAnswers[1])
            textThirdAnswer.text = holder.context.getString(R.string.third, item.allAnswers[2])
            textForthAnswer.text = holder.context.getString(R.string.forth, item.allAnswers[3])
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

    private fun setCorrectnessIcon(
        binding: AnswerItemBinding,
        index: Int,
        @DrawableRes icon: Int
    ) {
        binding.apply {
            when (index) {
                0 -> imageIndicator1.setBackgroundResource(icon)
                1 -> imageIndicator2.setBackgroundResource(icon)
                2 -> imageIndicator3.setBackgroundResource(icon)
                else -> imageIndicator4.setBackgroundResource(icon)
            }
        }
    }
}