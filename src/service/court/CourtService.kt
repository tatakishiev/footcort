package service.court

import domain.entity.court.Court
import domain.entity.court.Courts
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

interface CourtService {
    fun getAll(): List<Court>
}

class CourtServiceImpl : CourtService {

    override fun getAll(): List<Court> {
        return transaction {
            Courts.selectAll().map { Court(id = it[Courts.id], name = it[Courts.name]) }
        }
    }
}