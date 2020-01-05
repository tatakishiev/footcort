package court.entity

import org.jetbrains.exposed.sql.Table

object Courts : Table("courts") {
    val id = long("id").entityId().autoIncrement()
    val name = varchar("name", 200).uniqueIndex()
    val isHall = bool("is_hall")
}

data class Court(
    val id: Long,
    val name: String,
    val isHall: Boolean
)