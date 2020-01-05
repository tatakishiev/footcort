package user.dto

data class CreateUserDto(
    val phoneNumber: String,
    val firstName: String?,
    val lastName: String?,
    val password: String
)

data class UserDto(
    val id: Long,
    val phoneNumber: String,
    val firstName: String?,
    val lastName: String?
)