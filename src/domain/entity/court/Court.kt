package domain.entity.court

import org.jetbrains.exposed.dao.LongIdTable
import org.jetbrains.exposed.sql.Column

object Courts : LongIdTable() {
    val name: Column<String> = varchar("name", 200).uniqueIndex()
}

class Court(
    val id: Long,
    val name: String
)