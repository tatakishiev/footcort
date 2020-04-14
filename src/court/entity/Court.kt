package court.entity

import company.entity.Companies
import org.jetbrains.exposed.dao.id.LongIdTable

object Courts : LongIdTable("courts") {
    val name = varchar("name", 200).uniqueIndex()
    val isHall = bool("is_hall")
    val companyId = long("company_id").references(Companies.id)
}

data class Court(
    val id: Long,
    val name: String,
    val isHall: Boolean
)