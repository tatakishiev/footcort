package domain.entity.user

import io.ktor.auth.Principal
import org.jetbrains.exposed.sql.Table

object Users : Table("users") {
    val id = long("id").entityId().autoIncrement()
    val phoneNumber = varchar("phone_number", 20).uniqueIndex()
    val firstName = varchar("first_name", 25).nullable()
    val lastName = varchar("last_name", 30).nullable()
    val password = varchar("password", 150)
}

class User(
    val id: Long,
    val phoneNumber: String,
    val firstName: String?,
    val lastName: String?,
    val password: String
) : Principal