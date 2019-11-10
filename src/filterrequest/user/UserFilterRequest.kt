package filterrequest.user

import filterrequest.base.PageRequest

data class UserFilterRequest(
    val searchRequest: UserSearchRequest,
    val pageRequest: PageRequest
)

data class UserSearchRequest(
    val searchString: String?
)

