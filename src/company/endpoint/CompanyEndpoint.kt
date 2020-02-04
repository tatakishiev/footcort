package company.endpoint

import company.dto.CompanyCreateDto
import company.dto.CompanyDto
import company.dto.CompanyUpdateDto
import company.entity.Company
import company.filterrequest.CompanyFilterRequest
import company.mapper.CompanyMapper
import company.request.CompanyCreateRequest
import company.request.CompanyUpdateRequest
import company.service.CompanyService
import exception.company.CompanyByIdNotFoundException
import utils.pagination.PageResponse
import utils.toPageResponse

interface CompanyEndpoint {
    fun save(dto: CompanyCreateDto): CompanyDto
    fun update(dto: CompanyUpdateDto, id: Long): CompanyDto
    fun search(dto: CompanyFilterRequest): PageResponse<CompanyDto>
}

class CompanyEndpointImpl(private val companyService: CompanyService,
                          private val companyMapper: CompanyMapper) : CompanyEndpoint {

    override fun save(dto: CompanyCreateDto): CompanyDto {
        val companyCreateRequest: CompanyCreateRequest = companyMapper.toCompanyCreateRequest(dto)
        val company: Company = companyService.save(companyCreateRequest)
        return companyMapper.toCompanyDto(company)
    }

    override fun update(dto: CompanyUpdateDto, id: Long): CompanyDto {
        val updatingCompany: Company = companyService.findById(id) ?: throw CompanyByIdNotFoundException(id)
        val companyUpdateRequest: CompanyUpdateRequest = companyMapper.toCompanyUpdateRequest(dto)
        val updatedCompany: Company = companyService.update(companyUpdateRequest, updatingCompany)
        return companyMapper.toCompanyDto(updatedCompany)
    }

    override fun search(dto: CompanyFilterRequest): PageResponse<CompanyDto> {
        val all: List<Company> = companyService.search(dto)
        return all.map { companyMapper.toCompanyDto(it) }.toPageResponse()
    }
}