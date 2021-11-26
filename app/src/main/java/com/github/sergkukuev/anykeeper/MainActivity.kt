package com.github.sergkukuev.anykeeper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.sergkukuev.anykeeper.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val adapter = ReminderAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureRecyclerView()
        configureButtons()
    }

    private fun configureRecyclerView() = with(binding) {
        rvReminders.apply {
            adapter = this@MainActivity.adapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private var id = 1  // TODO: remove after real values
    private fun configureButtons() = with(binding) {
        btnNewReminder.setOnClickListener {
            val text = "Напоминание №${id++}"
            val timestamp = System.currentTimeMillis() / 1000L
            val reminder = Reminder(text, timestamp)
            adapter.addReminder(reminder)
        }
    }
}