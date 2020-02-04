package company.repository

import company.entity.Companies
import company.entity.Company
import company.filterrequest.CompanyFilterRequest
import company.request.CompanyCreateRequest
import company.request.CompanyUpdateRequest
import org.jetbrains.exposed.sql.*

interface CompanyRepository {
    fun save(request: CompanyCreateRequest): Company
    fun update(request: CompanyUpdateRequest, updatingCompany: Company): Int
    fun findById(id: Long): Company?
    fun search(dto: CompanyFilterRequest): List<Company>
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

    override fun search(dto: CompanyFilterRequest): List<Company> {
        val total: Query = Companies.selectAll().limit(dto.pageRequest.limit, offset = dto.pageRequest.offset)

        dto.sortRequest?.let {
            it.forEach { sortRequest ->
                total.orderBy(sortRequest.param.value to sortRequest.direction)
            }
        }

        dto.searchRequest?.searchString?.let {
            total.andWhere {
                Companies.name like "%$it%"
            }
        }
        return total.map { it.toCompany() }
    }
}

internal fun ResultRow.toCompany(): Company = Company(
    id = this[Companies.id],
    name = this[Companies.name],
    address = this[Companies.address]
)