package utils.pagination

data class PageResponse<T>(
    val list: List<T>,
    val total: Int
)