package user.mapper

import auth.dto.RegistrationDto
import org.mindrot.jbcrypt.BCrypt
import user.dto.UserDto
import user.entity.User
import user.request.CreateUserRequest

interface UserMapper {
    fun toDto(user: User): UserDto
    fun toCreateUseRequest(registrationDto: RegistrationDto): CreateUserRequest
}

class UserMapperImpl : UserMapper {

    override fun toCreateUseRequest(registrationDto: RegistrationDto): CreateUserRequest {
        return CreateUserRequest(
            firstName = registrationDto.firstName,
            lastName = registrationDto.lastName,
            phoneNumber = registrationDto.phoneNumber,
            password = BCrypt.hashpw(registrationDto.password, BCrypt.gensalt())
        )
    }

    override fun toDto(user: User): UserDto {
        return UserDto(
            id = user.id,
            firstName = user.firstName,
            lastName = user.lastName,
            phoneNumber = user.phoneNumber
        )
    }
}