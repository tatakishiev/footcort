package user.entity

import io.ktor.auth.Principal
import org.jetbrains.exposed.dao.id.LongIdTable

object Users : LongIdTable("users") {
    val phoneNumber = varchar("phone_number", 20).uniqueIndex()
    val firstName = varchar("first_name", 25).nullable()
    val lastName = varchar("last_name", 30).nullable()
    val password = varchar("password", 150)
    val role = enumerationByName("role", 20,  Roles::class)
}

class User(
    val id: Long,
    val phoneNumber: String,
    val firstName: String?,
    val lastName: String?,
    val password: String,
    val role: Roles
) : Principal

enum class Roles {
//    COMPANY_OWNER,
    USER
}