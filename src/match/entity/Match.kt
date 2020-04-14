package match.entity

import court.entity.Courts
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.`java-time`.datetime
import user.entity.User
import user.entity.Users
import java.time.LocalDateTime

object Matches : LongIdTable("matches") {
    val start = datetime("start")
    val end = datetime("end")
    val courtId = long("court_id").references(Courts.id)
    val description = varchar("description", 200)
    val createdBy = long("creator_id").references(Users.id)
}

object MatchesParticipants : LongIdTable("matches_participants") {
    val userId = reference("user_id", Users)
    val matchId = reference("match_id", Matches)
}

data class Match(
    val id: Long,
    val start: LocalDateTime,
    val end: LocalDateTime,
    val creator: User
)