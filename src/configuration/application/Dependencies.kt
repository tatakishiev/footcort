package configuration.application

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson

fun Application.mainDependencies() {
    install(ContentNegotiation) {
        jackson { }
    }
}