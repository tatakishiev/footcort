package dto.registration

import dto.user.UserDto

data class TokenDto(
    val token: String,
    val user: UserDto
)