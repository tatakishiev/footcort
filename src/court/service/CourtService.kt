package court.service

import court.entity.Court
import court.repository.CourtRepository
import org.jetbrains.exposed.sql.transactions.transaction

interface CourtService {
    fun findAll(): List<Court>
    fun findById(id: Long): Court?
}

class CourtServiceImpl(private val repository: CourtRepository) : CourtService {

    override fun findAll(): List<Court> {
        return transaction { repository.findAll() }
    }

    override fun findById(id: Long): Court? {
        return transaction { repository.findById(id) }
    }
}