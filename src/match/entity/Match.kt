package match.entity

import court.entity.Courts
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.`java-time`.datetime
import java.time.LocalDateTime

object Matches : LongIdTable("matches") {
    val start = datetime("start")
    val end = datetime("end")
    val courtId = reference("court_id", Courts)
    val description = varchar("description", 200)
}

data class Match(
    val id: Long,
    val start: LocalDateTime,
    val end: LocalDateTime
)