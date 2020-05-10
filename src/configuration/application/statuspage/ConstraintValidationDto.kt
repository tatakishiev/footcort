package configuration.application.statuspage

data class ConstraintValidationDto(
    val property: String,
    val cause: String
)