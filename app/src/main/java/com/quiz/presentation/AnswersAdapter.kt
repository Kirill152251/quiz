package com.quiz.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.quiz.databinding.AnswerItemBinding
import com.quiz.domain.models.AnsweredQuestion

class AnswersAdapter(
    @ColorInt private val correctColor: Int,
    @ColorInt private val inCorrectColor: Int,
    @DrawableRes private val correctIcon: Int,
    @DrawableRes private val inCorrectIcon: Int
) :
    ListAdapter<AnsweredQuestion, AnswersAdapter.AnswerViewHolder>(ItemCallback) {

    class AnswerViewHolder(val binding: AnswerItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AnswerItemBinding.inflate(inflater)
        return AnswerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.apply {
            val indexOfChosenAnswer = item.allAnswers.indexOf(item.chosenAnswer)
            val indexOfCorrectAnswer = item.allAnswers.indexOf(item.correctAnswer)
            if (item.isAnsweredCorrect()) {
                itemAnswer.setCardBackgroundColor(correctColor)
                setCorrectnessIcon(this, indexOfChosenAnswer, correctIcon)
            } else {
                setCorrectnessIcon(this, indexOfCorrectAnswer, correctIcon)
                setCorrectnessIcon(this, indexOfChosenAnswer, inCorrectIcon)
                itemAnswer.setCardBackgroundColor(inCorrectColor)
            }

            textQuestion.text = item.questionText

            textFirstAnswer.text = item.allAnswers[0]
            textSecondAnswer.text = item.allAnswers[1]
            textThirdAnswer.text = item.allAnswers[2]
            textForthAnswer.text = item.allAnswers[3]
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