package repository.user

import domain.entity.user.User
import domain.entity.user.Users
import domainrequest.user.CreateUserRequest
import filterrequest.base.PageRequest
import filterrequest.user.UserFilterRequest
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

interface UserRepository {
    fun search(userFilterRequest: UserFilterRequest): List<User>
    fun getAll(pageRequest: PageRequest): List<User>
    fun findByPhoneNumber(phoneNumber: String): User?
    fun existsByPhoneNumber(phoneNumber: String): Boolean
    fun create(createUserRequest: CreateUserRequest): User
}

class UserRepositoryImpl : UserRepository {

    override fun create(createUserRequest: CreateUserRequest): User {
        return transaction {
            val userId = Users.insertAndGetId {
                it[firstName] = createUserRequest.firstName
                it[lastName] = createUserRequest.lastName
                it[phoneNumber] = createUserRequest.phoneNumber
                it[password] = createUserRequest.password
            }.value

            Users.select { Users.id eq userId }.map {
                toDomain(it)
            }.first()
        }
    }

    override fun existsByPhoneNumber(phoneNumber: String): Boolean {
        return transaction {
            findByPhoneNumber(phoneNumber) != null
            //ToDo::check exists for exposed
        }
    }

    override fun findByPhoneNumber(phoneNumber: String): User? {
        return transaction {
            Users.select { Users.phoneNumber eq phoneNumber }
                .firstOrNull()
                ?.let { toDomain(it) }
        }
    }

    override fun getAll(pageRequest: PageRequest): List<User> {
        return transaction {
            Users.selectAll().map { toDomain(it) }
        }
    }

    override fun search(userFilterRequest: UserFilterRequest): List<User> {
        return transaction {
            userFilterRequest.searchRequest.searchString?.let {
                Users.select {
                    Users.firstName like it
                }
            }?.limit(userFilterRequest.pageRequest.limit, offset = userFilterRequest.pageRequest.offset)
                ?.map { toDomain(it) } ?: getAll(userFilterRequest.pageRequest)
        }
    }

    private fun toDomain(row: ResultRow): User {
        return User(
            id = row[Users.id].value,
            phoneNumber = row[Users.phoneNumber],
            firstName = row[Users.firstName],
            lastName = row[Users.lastName],
            password = row[Users.password]
        )
    }
}