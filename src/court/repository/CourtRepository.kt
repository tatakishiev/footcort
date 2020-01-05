package court.repository

import court.entity.Court
import court.entity.Courts
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

interface CourtRepository {
    fun findAll(): List<Court>
}

class CourtRepositoryImpl : CourtRepository {
    override fun findAll(): List<Court> {
        return Courts.selectAll().map { it.toCourt() }
    }
}

internal fun ResultRow.toCourt(): Court = Court(
    id = this[Courts.id].value,
    name = this[Courts.name],
    isHall = this[Courts.isHall]
)

