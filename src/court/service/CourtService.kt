package court.service

import court.entity.Court
import court.repository.CourtRepository
import court.request.CreateCourtRequest
import org.jetbrains.exposed.sql.transactions.transaction

interface CourtService {
    fun findAll(): List<Court>
    fun findById(id: Long): Court?
    fun create(createCourtRequest: CreateCourtRequest): Court
}

class CourtServiceImpl(private val repository: CourtRepository) : CourtService {

    override fun findAll(): List<Court> {
        return transaction { repository.findAll() }
    }

    override fun findById(id: Long): Court? {
        return transaction { repository.findById(id) }
    }

    override fun create(createCourtRequest: CreateCourtRequest): Court {
        return transaction { repository.create(createCourtRequest) }
    }
}