package match.repository

import court.entity.Court
import match.entity.Match
import match.entity.Matches
import match.request.MatchCreateRequest
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import user.entity.Users
import user.repository.toUser

interface MatchRepository {
    fun findByCourt(court: Court): List<Match>
    fun save(matchCreateRequest: MatchCreateRequest): Match
}

class MatchRepositoryImpl : MatchRepository {
    override fun findByCourt(court: Court): List<Match> {
        return Matches.innerJoin(Users)
            .select { Matches.courtId eq court.id }.map { it.toMatch() }
    }

    override fun save(matchCreateRequest: MatchCreateRequest): Match {
        val id = Matches.insertAndGetId { match ->
            match[start] = matchCreateRequest.start
            match[end] = matchCreateRequest.end
            match[courtId] = matchCreateRequest.courtId
            match[createdBy] = matchCreateRequest.creatorId
            match[description] = matchCreateRequest.description
        }

        return Matches.innerJoin(Users)
            .select { Matches.id eq id }.firstOrNull()!!.toMatch()
    }
}

internal fun ResultRow.toMatch() = Match(
    id = this[Matches.id].value,
    start = this[Matches.start],
    end = this[Matches.end],
    creator = this.toUser()
)