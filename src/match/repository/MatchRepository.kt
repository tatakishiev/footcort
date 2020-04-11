package match.repository

import court.entity.Court
import match.entity.Match
import match.entity.Matches
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select

interface MatchRepository {
    fun findByCourt(court: Court): List<Match>
}

class MatchRepositoryImpl : MatchRepository {
    override fun findByCourt(court: Court): List<Match> {
        return Matches.select { Matches.courtId eq court.id }.map { it.toMatch() }
    }
}

internal fun ResultRow.toMatch() = Match(
    id = this[Matches.id].value,
    start = this[Matches.start],
    end = this[Matches.end]
)