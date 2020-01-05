package user.service

import auth.dto.LoginRequestDto
import exception.user.CredentialsNotMatchingException
import exception.user.UserByPhoneNumberNotFoundException
import org.jetbrains.exposed.sql.transactions.transaction
import org.mindrot.jbcrypt.BCrypt
import user.entity.User
import user.repository.UserRepository
import user.request.CreateUserRequest

interface UserService {
    fun findByPhoneNumber(phoneNumber: String): User
    fun existsByPhoneNumber(phoneNumber: String): Boolean
    fun create(createUserRequest: CreateUserRequest): User
    fun findUserByCredentials(loginRequestDto: LoginRequestDto): User
    fun search(): List<User>
    fun findAll(): List<User>
}

class UserServiceImpl(private val userRepository: UserRepository) : UserService {

    override fun search(): List<User> {
        TODO()
    }

    override fun findAll(): List<User> = transaction { userRepository.findAll() }

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