package court.request

data class CreateCourtRequest(
    val name: String,
    val isHall: Boolean
)