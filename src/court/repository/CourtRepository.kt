package court.repository

import court.entity.Court
import court.entity.Courts
import court.request.CreateCourtRequest
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

interface CourtRepository {
    fun findAll(): List<Court>
    fun findById(id: Long): Court?
    fun create(createCourtRequest: CreateCourtRequest): Court
}

class CourtRepositoryImpl : CourtRepository {
    override fun findAll(): List<Court> {
        return Courts.selectAll().map { it.toCourt() }
    }

    override fun findById(id: Long): Court? {
        return Courts.select { Courts.id eq id }.firstOrNull()?.toCourt()
    }

    override fun create(createCourtRequest: CreateCourtRequest): Court {
        return Courts.insert {
            it[name] = createCourtRequest.name
            it[isHall] = createCourtRequest.isHall
        }.resultedValues!!.first().toCourt()
    }
}

internal fun ResultRow.toCourt(): Court = Court(
    id = this[Courts.id].value,
    name = this[Courts.name],
    isHall = this[Courts.isHall]
)