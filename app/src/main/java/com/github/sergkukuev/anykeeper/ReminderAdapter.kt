package com.github.sergkukuev.anykeeper

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.sergkukuev.anykeeper.databinding.ReminderItemBinding

class ReminderAdapter : RecyclerView.Adapter<ReminderAdapter.ViewHolder>() {
    private val reminders = ArrayList<Reminder>()

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ReminderItemBinding.bind(item)
        fun bind(note: Reminder) = with(binding) {
            tvTime.text = note.expire.toString()
            tvTitle.text = note.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.reminder_item, parent, false))
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