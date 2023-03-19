package com.quiz.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.quiz.R
import com.quiz.databinding.QuizItemBinding
import com.quiz.domain.models.SavedQuiz

class QuizAdapter(private val onDelete: (quiz: SavedQuiz) -> Unit) :
    ListAdapter<SavedQuiz, QuizAdapter.QuizViewHolder>(ItemCallback) {

    class QuizViewHolder(val binding: QuizItemBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = QuizItemBinding.inflate(inflater, parent, false)
        return QuizViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.apply {
            textSaveDate.text = holder.context.getString(R.string.save_date, item.saveTime)
            textDifficulty.text = holder.context.getString(R.string.difficulty, item.difficulty)
            textNumberOfQuestions.text =
                holder.context.getString(
                    R.string.number_of_questions,
                    item.numberOfQuestions.toString()
                )
            textCategory.text =
                holder.context.getString(R.string.category, item.categories.joinToString(", ") {
                    it.convertToUIString()
                })
            btnDelete.setOnClickListener {
                onDelete(item)
            }
        }
    }

    object ItemCallback : DiffUtil.ItemCallback<SavedQuiz>() {
        override fun areItemsTheSame(oldItem: SavedQuiz, newItem: SavedQuiz): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: SavedQuiz, newItem: SavedQuiz): Boolean {
            return oldItem.saveTime == newItem.saveTime
        }

    }
}