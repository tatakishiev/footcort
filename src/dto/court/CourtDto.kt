package dto.court

data class CourtDto(
    val id: Long,
    val name: String
)

data class CreateCourtDto(
    val name: String
)