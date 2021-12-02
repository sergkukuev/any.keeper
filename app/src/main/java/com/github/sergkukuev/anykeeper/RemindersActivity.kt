package com.github.sergkukuev.anykeeper

import android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.sergkukuev.anykeeper.databinding.RemindersActivityBinding
import java.time.LocalDateTime
import kotlin.random.Random

class RemindersActivity : AppCompatActivity() {
    lateinit var binding: RemindersActivityBinding
    private val adapter = ReminderAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RemindersActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureRecyclerView()
        configureButtons()
        configureSwipes()
    }

    private fun configureRecyclerView() = with(binding) {
        rvReminders.apply {
            adapter = this@RemindersActivity.adapter
            layoutManager = LinearLayoutManager(this@RemindersActivity)
            addItemDecoration(
                DividerItemDecoration(
                    this@RemindersActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private var id = 1  // TODO: remove after real values
    private fun configureButtons() = with(binding) {
        btnNewReminder.setOnClickListener {
            val text = "Напоминание №${id++}"
            val timestamp = LocalDateTime.now().toEpochMilli()
            val lifetime = Random.nextLong(864000000) // 10 days
            val reminder = Reminder(text, lifetime, timestamp)
            adapter.addReminder(reminder)
        }
    }

    private fun configureSwipes() = with(binding) {
        val swipeGesture = object : SwipeGesture() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        adapter.removeReminder(viewHolder.adapterPosition)
                    }
                }
            }
        }

        swipeGesture.addSwipeLeftBackgroundColor(R.color.white)
        swipeGesture.addSwipeLeftActionIcon(R.drawable.ic_menu_delete)

        val touchHelper = ItemTouchHelper(swipeGesture)
        touchHelper.attachToRecyclerView(rvReminders)
    }
}