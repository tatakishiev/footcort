package repository.user

import domain.entity.user.User
import domain.entity.user.Users
import filterrequest.base.PageRequest
import filterrequest.user.UserFilterRequest
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

interface UserRepository {
    fun search(userFilterRequest: UserFilterRequest): List<User>
    fun getAll(pageRequest: PageRequest): List<User>
}

class UserRepositoryImpl : UserRepository {

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
            lastName = row[Users.lastName]
        )
    }
}