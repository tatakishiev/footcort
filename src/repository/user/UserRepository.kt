package repository.user

import controller.user.UserSearchLocation
import domain.entity.user.User
import domain.entity.user.Users
import domainrequest.user.CreateUserRequest
import filterrequest.base.PageRequest
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

interface UserRepository {
    fun search(userSearchLocation: UserSearchLocation): List<User>
    fun getAll(pageRequest: PageRequest): List<User>
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

    override fun search(userSearchLocation: UserSearchLocation): List<User> {
        return transaction {
            userSearchLocation.searchRequest?.searchString?.let {
                Users.select {
                    Users.firstName like it
                }
            }?.limit(userSearchLocation.pageRequest.limit, offset = userSearchLocation.pageRequest.offset)
                ?.map { toDomain(it) } ?: getAll(userSearchLocation.pageRequest)
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

internal fun ResultRow.toUser(): User = User(
    id = this[Users.id].value,
    phoneNumber = this[Users.phoneNumber],
    firstName = this[Users.firstName],
    lastName = this[Users.lastName],
    password = this[Users.password]
)