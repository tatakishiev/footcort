package court.repository

import court.entity.Court
import court.entity.Courts
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

interface CourtRepository {
    fun findAll(): List<Court>
    fun findById(id: Long): Court?
}

class CourtRepositoryImpl : CourtRepository {
    override fun findAll(): List<Court> {
        return Courts.selectAll().map { it.toCourt() }
    }

    override fun findById(id: Long): Court? {
        return Courts.select { Courts.id eq id }.firstOrNull()?.toCourt()
    }
}

internal fun ResultRow.toCourt(): Court = Court(
    id = this[Courts.id].value,
    name = this[Courts.name],
    isHall = this[Courts.isHall]
)

