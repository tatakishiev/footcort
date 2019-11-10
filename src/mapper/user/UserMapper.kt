package mapper.user

import domain.entity.user.User
import domainrequest.user.CreateUserRequest
import dto.user.CreateUserDto
import dto.user.UserDto
import org.mindrot.jbcrypt.BCrypt

interface UserMapper {
    fun toDto(user: User): UserDto
    fun toCreateUseRequest(createUserDto: CreateUserDto): CreateUserRequest
}

class UserMapperImpl : UserMapper {

    override fun toCreateUseRequest(createUserDto: CreateUserDto): CreateUserRequest {
        return CreateUserRequest(
            firstName = createUserDto.firstName,
            lastName = createUserDto.lastName,
            phoneNumber = createUserDto.phoneNumber,
            password = BCrypt.hashpw(createUserDto.password, BCrypt.gensalt())
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