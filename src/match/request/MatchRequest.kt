package match.request

import java.time.LocalDateTime

data class MatchCreateRequest(
    val start: LocalDateTime,
    val end: LocalDateTime,
    val creatorId: Long,
    val description: String,
    val courtId: Long
)