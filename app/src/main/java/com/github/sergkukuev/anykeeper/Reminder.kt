package com.github.sergkukuev.anykeeper

data class Reminder(
    val title: String,
    val lifetime: Long,
    val timestamp: Long
)

class ReminderComparator {
    companion object : Comparator<Reminder> {
        override fun compare(o1: Reminder, o2: Reminder): Int {
            val t1 = o1.timestamp + o1.lifetime
            val t2 = o2.timestamp + o2.lifetime
            return (t1 - t2).toInt()
        }
    }
}
