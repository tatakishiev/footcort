package dto.registration

data class LoginRequestDto(
    val phoneNumber: String,
    val password: String
)