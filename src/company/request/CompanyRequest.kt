package company.request

data class CompanyCreateRequest(
    val name: String,
    val address: String
)

data class CompanyUpdateRequest(
    val name: String,
    val address: String
)