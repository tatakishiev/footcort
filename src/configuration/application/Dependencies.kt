package configuration.application

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.locations.Locations

fun Application.mainDependencies() {
    install(ContentNegotiation) {
        jackson { }
    }
    install(Locations)
}