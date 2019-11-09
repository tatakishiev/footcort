package domain.entity.court

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object Courts : Table() {
    val id: Column<Long> = long("id").autoIncrement().primaryKey()
    val name: Column<String> = varchar("name", 200).uniqueIndex()
}

class Court(
    val id: Long,
    val name: String
)