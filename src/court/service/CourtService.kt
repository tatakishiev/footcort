package court.service

import court.entity.Court
import court.repository.CourtRepository
import org.jetbrains.exposed.sql.transactions.transaction

interface CourtService {
    fun findAll(): List<Court>
}

class CourtServiceImpl(private val courtRepository: CourtRepository) : CourtService {

    override fun findAll(): List<Court> {
        return transaction { courtRepository.findAll() }
    }
}