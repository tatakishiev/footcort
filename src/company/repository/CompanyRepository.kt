package company.repository

import company.entity.Companies
import company.entity.Company
import company.request.CompanyCreateRequest
import company.request.CompanyUpdateRequest
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.update

interface CompanyRepository {
    fun save(request: CompanyCreateRequest): Company
    fun update(request: CompanyUpdateRequest, updatingCompany: Company): Int
    fun findById(id: Long): Company?
}

class CompanyRepositoryImpl : CompanyRepository {
    override fun save(request: CompanyCreateRequest): Company {
        return Companies.insert {
            it[name] = request.name
            it[address] = request.address
        }.resultedValues!!.first().toCompany()
    }

    override fun update(request: CompanyUpdateRequest, updatingCompany: Company): Int {
        return Companies.update({ Companies.id eq updatingCompany.id }) {
            it[address] = request.address
            it[name] = request.name
        }
    }

    override fun findById(id: Long): Company? {
        return Companies.select { Companies.id eq id }.firstOrNull()?.toCompany()
    }
}

internal fun ResultRow.toCompany(): Company = Company(
    id = this[Companies.id],
    name = this[Companies.name],
    address = this[Companies.address]
)