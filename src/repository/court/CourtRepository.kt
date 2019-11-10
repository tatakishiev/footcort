package repository.court

import domain.entity.court.Court
import domain.entity.court.Courts
import domainrequest.court.CreateCourtRequest
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

interface CourtRepository {
    fun getAll(): List<Court>
    fun create(createCourtRequest: CreateCourtRequest): Court
}

class CourtRepositoryImpl : CourtRepository {

    override fun create(createCourtRequest: CreateCourtRequest): Court {
        val responseId = transaction {
            Courts.insertAndGetId {
                it[name] = createCourtRequest.name
            }
        }

        return transaction {
            Courts.select { Courts.id eq responseId }.map { toDomain(it) }.first()
        }
    }

    override fun getAll(): List<Court> {
        return transaction {
            Courts.selectAll().map { toDomain(it) }
        }
    }

    private fun toDomain(row: ResultRow): Court {
        return Court(
            id = row[Courts.id].value,
            name = row[Courts.name]
        )
    }
}