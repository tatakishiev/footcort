package dto.user

data class CreateUserDto(
    val phoneNumber: String,
    val firstName: String?,
    val lastName: String?,
    val password: String
)