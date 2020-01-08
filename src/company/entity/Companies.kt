package company.entity

import org.jetbrains.exposed.sql.Table

object Companies : Table("companies") {
    val id = long("id").autoIncrement()
    val name = varchar("name", 255)
    val address = varchar("address", 255)
    override val primaryKey = PrimaryKey(id)
}

data class Company(
    val id: Long,
    val name: String,
    val address: String
)

object CompaniesPhoneNumbers : Table("companies_phone_numbers") {
    val id = long("id").autoIncrement()
    val phoneNumber = varchar("phone_number", 255)
    val companyId = long("company_id").references(Companies.id)
    override val primaryKey = PrimaryKey(id)
}