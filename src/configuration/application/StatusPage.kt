package configuration.application

import exception.base.ErrorResponse
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.StatusPages
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond

fun Application.statusPages() {
    install(StatusPages) {
        exception(Exception::class.java) {
            val errorResponse = ErrorResponse(mapOf("error" to listOf("detail", it.localizedMessage)))
            context.respond(
                HttpStatusCode.InternalServerError, errorResponse
            )
        }
    }
}