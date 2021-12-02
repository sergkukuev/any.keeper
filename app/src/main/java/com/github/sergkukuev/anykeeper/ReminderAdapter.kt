package com.github.sergkukuev.anykeeper

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.sergkukuev.anykeeper.databinding.ReminderItemBinding
import java.time.LocalDateTime

class ReminderAdapter : RecyclerView.Adapter<ReminderAdapter.ViewHolder>() {
    private val reminders = ArrayList<Reminder>()

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ReminderItemBinding.bind(item)
        fun bind(note: Reminder) = with(binding) {
            val expiration = note.timestamp + note.lifetime
            expirationView.text = asExpirationDate(expiration)
            creationView.text = asCreationDate(note.timestamp)
            titleView.text = note.title
        }

        private fun asCreationDate(posix: Long): String {
            return posix.toLocalDateTime().asString("dd.MM.yy")
        }

        private fun asExpirationDate(posix: Long): String {
            val days = posix.toLocalDateTime().diffOfDays(LocalDateTime.now())
            val result = when {
                days > 366 -> "> year"
                days > 31 -> "> month"
                days > 0 -> "$days days"
                days.compareTo(0) == 0 -> "< day"
                else -> "expired"
            }
            return result
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.reminder_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(reminders[position])
    }

    override fun getItemCount(): Int {
        return reminders.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addReminder(reminder: Reminder) {
        reminders.add(reminder)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun removeReminder(index: Int) {
        reminders.removeAt(index)
        notifyDataSetChanged()
    }
}