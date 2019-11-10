package filterrequest.base

data class PageRequest(
    val limit: Int = 50,
    val offset: Int = 1
)