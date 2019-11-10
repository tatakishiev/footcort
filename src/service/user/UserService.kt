package service.user

import domain.entity.user.User
import domainrequest.user.CreateUserRequest
import exception.user.UserByPhoneNumberNotFoundException
import repository.user.UserRepository

interface UserService {
    fun findByPhoneNumber(phoneNumber: String): User
    fun existsByPhoneNumber(phoneNumber: String): Boolean
    fun create(createUserRequest: CreateUserRequest): User
}

class UserServiceImpl(private val userRepository: UserRepository) : UserService {

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