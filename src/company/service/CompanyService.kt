package company.service

import company.entity.Company
import company.repository.CompanyRepository
import company.request.CompanyCreateRequest
import company.request.CompanyUpdateRequest
import org.jetbrains.exposed.sql.transactions.transaction

interface CompanyService {
    fun save(request: CompanyCreateRequest): Company
    fun update(request: CompanyUpdateRequest, updatingCompany: Company): Company
    fun findById(id: Long): Company?
}

class CompanyServiceImpl(private val repository: CompanyRepository) : CompanyService {

    override fun save(request: CompanyCreateRequest): Company {
        return transaction { repository.save(request) }
    }

    override fun update(request: CompanyUpdateRequest, updatingCompany: Company): Company {
        return transaction {
            repository.update(request, updatingCompany)
            repository.findById(updatingCompany.id)!!
        }
    }

    override fun findById(id: Long): Company? {
        return transaction { repository.findById(id) }
    }
}