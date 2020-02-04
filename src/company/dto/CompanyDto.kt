package company.dto

import kotlin.reflect.KProperty

data class CompanyDto(
    val id: Long,
    val name: String,
    val address: String
)

data class CompanyCreateDto(
    val name: String,
    val address: String
)

data class CompanyUpdateDto(
    val name: String,
    val address: String
)


class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        if (value.isBlank()) throw Exception("Hey")
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}