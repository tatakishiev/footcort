package company.mapper

import company.dto.CompanyCreateDto
import company.dto.CompanyDto
import company.dto.CompanyUpdateDto
import company.entity.Company
import company.request.CompanyCreateRequest
import company.request.CompanyUpdateRequest

interface CompanyMapper {
    fun toCompanyDto(company: Company): CompanyDto
    fun toCompanyCreateRequest(dto: CompanyCreateDto): CompanyCreateRequest
    fun toCompanyUpdateRequest(dto: CompanyUpdateDto): CompanyUpdateRequest
}

class CompanyMapperImpl : CompanyMapper {

    override fun toCompanyDto(company: Company): CompanyDto = CompanyDto(
        id = company.id,
        address = company.address,
        name = company.name
    )

    override fun toCompanyCreateRequest(dto: CompanyCreateDto): CompanyCreateRequest = CompanyCreateRequest(
        name = dto.name,
        address = dto.address
    )

    override fun toCompanyUpdateRequest(dto: CompanyUpdateDto): CompanyUpdateRequest = CompanyUpdateRequest(
        name = dto.name,
        address = dto.address
    )
}