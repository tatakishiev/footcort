package auth.dto

data class RegistrationDto(
    val phoneNumber: String,
    val firstName: String?,
    val lastName: String?,
    val password: String
)