package dto.user

data class UserDto(
    val id: Long,
    val phoneNumber: String,
    val firstName: String?,
    val lastName: String?
)