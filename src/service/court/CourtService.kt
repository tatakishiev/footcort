package service.court

import domain.entity.court.Court
import domain.entity.court.Courts
import domain.entity.court.Courts.id
import domain.entity.court.Courts.name
import domainrequest.court.CreateCourtRequest
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

interface CourtService {
    fun getAll(): List<Court>
    fun create(createCourtRequest: CreateCourtRequest): Court
}

class CourtServiceImpl : CourtService {

    override fun create(createCourtRequest: CreateCourtRequest): Court {
        val responseId = transaction {
            Courts.insertAndGetId {
                it[name] = createCourtRequest.name
            }
        }

        return transaction {
            Courts.select { id eq responseId }.map {
                Court(
                    id = it[id].value,
                    name = it[name]
                )
            }.first()
        }


    }

    override fun getAll(): List<Court> {
        return transaction {
            Courts.selectAll().map {
                Court(
                    id = it[id].value,
                    name = it[name]
                )
            }
        }
    }
}