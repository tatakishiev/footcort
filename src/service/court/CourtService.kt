package service.court

import domain.entity.court.Court
import domain.entity.court.Courts
import domain.entity.court.Courts.id
import domain.entity.court.Courts.name
import domainrequest.court.CreateCourtRequest
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import repository.court.CourtRepository

interface CourtService {
    fun getAll(): List<Court>
    fun create(createCourtRequest: CreateCourtRequest): Court
}

class CourtServiceImpl(private val courtRepository: CourtRepository) : CourtService {

    override fun create(createCourtRequest: CreateCourtRequest): Court {
        return courtRepository.create(createCourtRequest)
    }

    override fun getAll(): List<Court> {
        return courtRepository.getAll()
    }
}