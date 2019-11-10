package domain.entity.user

import io.ktor.auth.Principal
import org.jetbrains.exposed.dao.LongIdTable
import org.jetbrains.exposed.sql.Column

object Users : LongIdTable() {
    val phoneNumber: Column<String> = varchar("phone_number", 20).uniqueIndex()
    val firstName: Column<String?> = varchar("first_name", 25).nullable()
    val lastName: Column<String?> = varchar("last_name", 30).nullable()
    val password: Column<String> = varchar("password", 150)
}

class User(
    val id: Long,
    val phoneNumber: String,
    val firstName: String?,
    val lastName: String?
) : Principal