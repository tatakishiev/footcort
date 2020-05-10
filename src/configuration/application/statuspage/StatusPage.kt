package configuration.application.statuspage

import exception.EntityNotFoundException
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.StatusPages
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import org.valiktor.ConstraintViolation
import org.valiktor.ConstraintViolationException
import user.exception.UserByPhoneNumberNotFoundException

fun Application.statusPages() {
    install(StatusPages) {
        exception<EntityNotFoundException> { exception ->
            exception.message?.let { call.respond(HttpStatusCode.NotFound, mapOf("message" to it)) }
        }
        exception<UserByPhoneNumberNotFoundException> { exception ->
            exception.message?.let { call.respond(HttpStatusCode.NotFound, mapOf("message" to it)) }
        }
        exception<ConstraintViolationException> { exception ->
            val constraintViolations: Set<ConstraintViolation> = exception.constraintViolations
            val constraints: List<ConstraintValidationDto> = constraintViolations.map {
                ConstraintValidationDto(
                    property = it.property,
                    cause = it.constraint.name
                )
            }
            call.respond(HttpStatusCode.ExpectationFailed, mapOf("exceptions" to constraints))
        }
    }
}
