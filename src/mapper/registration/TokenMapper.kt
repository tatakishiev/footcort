package mapper.registration

import dto.registration.TokenDto
import dto.user.UserDto

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