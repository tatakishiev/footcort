package domainrequest.court

data class CreateCourtRequest(
    val name: String,
    val isHall: Boolean
)