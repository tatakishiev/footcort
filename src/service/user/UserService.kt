package service.user

import controller.user.UserSearchLocation
import domain.entity.user.User
import domainrequest.user.CreateUserRequest
import dto.registration.LoginRequestDto
import exception.user.UserByPhoneNumberNotFoundException
import exception.user.CredentialsNotMatchingException
import filterrequest.user.UserFilterRequest
import org.mindrot.jbcrypt.BCrypt
import repository.user.UserRepository

interface UserService {
    fun findByPhoneNumber(phoneNumber: String): User
    fun existsByPhoneNumber(phoneNumber: String): Boolean
    fun create(createUserRequest: CreateUserRequest): User
    fun findUserByCredentials(loginRequestDto: LoginRequestDto): User
    fun search(userSearchLocation: UserSearchLocation): List<User>
}

class UserServiceImpl(private val userRepository: UserRepository) : UserService {

    override fun search(userSearchLocation: UserSearchLocation): List<User> {
        return userRepository.search(userSearchLocation)
    }

    override fun findUserByCredentials(loginRequestDto: LoginRequestDto): User {
        val user: User = userRepository.findByPhoneNumber(loginRequestDto.phoneNumber)
            ?: throw UserByPhoneNumberNotFoundException(loginRequestDto.phoneNumber)

        if (BCrypt.checkpw(loginRequestDto.password, user.password)) {
            return user
        }
        throw CredentialsNotMatchingException()
    }

    override fun create(createUserRequest: CreateUserRequest): User {
        return userRepository.create(createUserRequest)
    }

    override fun existsByPhoneNumber(phoneNumber: String): Boolean {
        return userRepository.existsByPhoneNumber(phoneNumber)
    }

    override fun findByPhoneNumber(phoneNumber: String): User {
        return userRepository.findByPhoneNumber(phoneNumber) ?: throw UserByPhoneNumberNotFoundException(phoneNumber)
    }
}