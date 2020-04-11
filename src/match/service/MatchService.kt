package match.service

import court.entity.Court
import match.entity.Match
import match.filterrequest.MatchFilterRequest
import match.repository.MatchRepository
import org.jetbrains.exposed.sql.transactions.transaction

interface MatchService {
    fun findByCourt(court: Court, filterRequest: MatchFilterRequest): List<Match>
}

class MatchServiceImpl(private val repository: MatchRepository) : MatchService {

    override fun findByCourt(court: Court, filterRequest: MatchFilterRequest): List<Match> {
        return transaction {
            repository.findByCourt(court)
        }
    }
}