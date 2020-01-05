package auth.dto

data class LoginRequestDto(
    val phoneNumber: String,
    val password: String
)