package user.repository

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import user.entity.User
import user.entity.Users
import user.request.CreateUserRequest

interface UserRepository {
    fun findAll(): List<User>
    fun findByPhoneNumber(phoneNumber: String): User?
    fun existsByPhoneNumber(phoneNumber: String): Boolean
    fun create(createUserRequest: CreateUserRequest): User
}

class UserRepositoryImpl : UserRepository {

    override fun create(createUserRequest: CreateUserRequest): User {
        return Users.insert {
            it[firstName] = createUserRequest.firstName
            it[lastName] = createUserRequest.lastName
            it[phoneNumber] = createUserRequest.phoneNumber
            it[password] = createUserRequest.password
        }.resultedValues!!.first().toUser()
    }

    override fun existsByPhoneNumber(phoneNumber: String): Boolean {
        return findByPhoneNumber(phoneNumber) != null
        //ToDo::check exists for exposed
    }

    override fun findByPhoneNumber(phoneNumber: String): User? {
        return Users.select { Users.phoneNumber eq phoneNumber }
            .firstOrNull()?.toUser()
    }

    override fun findAll(): List<User> {
        return Users.selectAll().map { it.toUser() }
    }
}

internal fun ResultRow.toUser(): User = User(
    id = this[Users.id],
    phoneNumber = this[Users.phoneNumber],
    firstName = this[Users.firstName],
    lastName = this[Users.lastName],
    password = this[Users.password]
)