package company.entity

import org.jetbrains.exposed.dao.id.LongIdTable

object Companies : LongIdTable("companies") {
    val name = varchar("name", 255)
    val address = varchar("address", 255)
}

data class Company(
    val id: Long,
    val name: String,
    val address: String
)

object CompaniesPhoneNumbers : LongIdTable("companies_phone_numbers") {
    val phoneNumber = varchar("phone_number", 255)
    val companyId = long("company_id").references(Companies.id)
}