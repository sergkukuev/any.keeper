package com.github.sergkukuev.anykeeper

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

fun LocalDateTime.diffOfDays(date: LocalDateTime = LocalDateTime.now()): Long {
    return ChronoUnit.DAYS.between(date, this)
}

fun LocalDateTime.asString(pattern: String): String {
    return this.format(DateTimeFormatter.ofPattern(pattern))
}

fun LocalDateTime.toEpochMilli(zone: ZoneId = ZoneId.systemDefault()): Long {
    return this.atZone(zone).toInstant().toEpochMilli()
}

fun Long.toLocalDateTime(zone: ZoneId = ZoneId.systemDefault()): LocalDateTime {
    return Instant.ofEpochMilli(this).atZone(zone).toLocalDateTime()
}
