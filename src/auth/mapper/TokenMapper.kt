package auth.mapper

import auth.dto.TokenDto
import user.dto.UserDto

interface TokenMapper {
    fun toTokenDto(token: String, userDto: UserDto): TokenDto
}

class TokenMapperImpl : TokenMapper {
    override fun toTokenDto(token: String, userDto: UserDto): TokenDto {
        return TokenDto(
            token = token,
            user = userDto
        )
    }
}