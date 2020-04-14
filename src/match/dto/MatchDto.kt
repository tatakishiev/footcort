package match.dto

import java.time.LocalDateTime

data class MatchDto(
    val id: Long,
    val start: LocalDateTime,
    val end: LocalDateTime
)

data class MatchCreateDto(
    val start: LocalDateTime = LocalDateTime.now(),
    val end: LocalDateTime = LocalDateTime.now(),
    val description: String
)