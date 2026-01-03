package com.example.quizappandroid.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizappandroid.R
import com.example.quizappandroid.models.Score

class HistoryAdapter(
    private val scores: List<Score>,
    private val onItemClick: (Score) -> Unit
) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvLevel: TextView = itemView.findViewById(R.id.tv_level)
        val tvScore: TextView = itemView.findViewById(R.id.tv_score)
        val tvDate: TextView = itemView.findViewById(R.id.tv_date)
        val cardItem: View = itemView.findViewById(R.id.card_item)

        fun bind(score: Score) {
            // Set level with color
            val levelText = when (score.level) {
                "easy" -> "Easy"
                "medium" -> "Medium"
                "hard" -> "Hard"
                else -> score.level
            }
            tvLevel.text = levelText

            // Set level color
            val levelColor = when (score.level) {
                "easy" -> R.color.easy_color
                "medium" -> R.color.medium_color
                "hard" -> R.color.hard_color
                else -> R.color.colorPrimary
            }
            tvLevel.setTextColor(itemView.context.getColor(levelColor))

            // Set score
            tvScore.text = "${String.format("%.0f", score.percentage)}%"

            // Set score color
            val scoreColor = if (score.percentage >= 60) R.color.correct_color else R.color.wrong_color
            tvScore.setTextColor(itemView.context.getColor(scoreColor))

            // Set date (simplified - just show date part)
            val dateText = if (score.date.isNotEmpty()) {
                score.date.substring(0, minOf(10, score.date.length))
            } else {
                "N/A"
            }
            tvDate.text = dateText

            // Set click listener
            cardItem.setOnClickListener {
                onItemClick(score)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_history, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(scores[position])
    }

    override fun getItemCount(): Int = scores.size
}
