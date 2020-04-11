package company.filterrequest

import company.entity.Companies
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.SortOrder

data class CompanyFilterRequest(
    val searchRequest: CompanySearchRequest?,
    val sortRequest: List<CompanySortRequest>?,
    val pageRequest: PageRequest = PageRequest()
)

data class PageRequest(
    val limit: Int = 100,
    val offset: Int = 0
)

data class CompanySearchRequest(
    val searchString: String?
)

enum class CompanySortParam(val value: Column<*>) {
    ID(Companies.id)
}

data class CompanySortRequest(
    val param: CompanySortParam,
    val direction: SortOrder = SortOrder.ASC
)