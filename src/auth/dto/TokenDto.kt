package auth.dto

import user.dto.UserDto

data class TokenDto(
    val token: String,
    val user: UserDto
)