package domainrequest.user

data class CreateUserRequest(
    val firstName: String?,
    val lastName: String?,
    val phoneNumber: String,
    val password: String
)