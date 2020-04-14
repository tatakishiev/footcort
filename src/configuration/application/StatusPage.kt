package configuration.application

import exception.EntityNotFoundException
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.StatusPages
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import user.exception.UserByPhoneNumberNotFoundException

fun Application.statusPages() {
    install(StatusPages) {
        exception<EntityNotFoundException> { cause ->
            cause.message?.let { call.respond(HttpStatusCode.NotFound, mapOf("message" to it)) }
        }
        exception<UserByPhoneNumberNotFoundException> { cause ->
            cause.message?.let { call.respond(HttpStatusCode.NotFound, mapOf("message" to it)) }
        }
    }
}
