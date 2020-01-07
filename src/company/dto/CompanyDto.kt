package company.dto

data class CompanyDto(
    val id: Long,
    val name: String,
    val address: String
)

data class CompanyCreateDto(
    val name: String,
    val address: String
)

data class CompanyUpdateDto(
    val name: String,
    val address: String
)