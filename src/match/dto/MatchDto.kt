package match.dto

import java.time.LocalDateTime

data class MatchDto(
    val id: Long,
    val start: LocalDateTime,
    val end: LocalDateTime
)