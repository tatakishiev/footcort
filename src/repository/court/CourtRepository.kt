package repository.court

import domain.entity.court.Court
import domain.entity.court.Courts
import domainrequest.court.CreateCourtRequest
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

interface CourtRepository {
    fun getAll(): List<Court>
    fun create(createCourtRequest: CreateCourtRequest): Court
}

class CourtRepositoryImpl : CourtRepository {

    override fun create(createCourtRequest: CreateCourtRequest): Court {
        return Courts.insert {
            it[name] = createCourtRequest.name
            it[isHall] = createCourtRequest.isHall
        }.resultedValues!!.first().toCourt()
    }

    override fun getAll(): List<Court> {
        return Courts.selectAll().map { it.toCourt() }
    }
}

internal fun ResultRow.toCourt(): Court = Court(
    id = this[Courts.id].value,
    name = this[Courts.name],
    isHall = this[Courts.isHall]
)