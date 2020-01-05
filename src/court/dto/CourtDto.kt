package court.dto

data class CourtDto(
    val id: Long,
    val name: String,
    val isHall: Boolean
)

data class CreateCourtDto(
    val name: String,
    val isHall: Boolean
)