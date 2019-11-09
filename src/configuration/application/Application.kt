package configuration.application

import configuration.kodein.KodeinModule
import controller.court.court
import configuration.database.DatabaseFactory
import endpoint.court.CourtEndpointImpl
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.jackson.jackson
import io.ktor.locations.get
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.BaseApplicationEngine
import io.ktor.server.engine.EngineAPI
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.kodein.di.generic.instance


@EngineAPI
fun setup(): BaseApplicationEngine {
    DatabaseFactory.init()
    return configureServer()
}

@EngineAPI
fun configureServer(
): BaseApplicationEngine {
    return embeddedServer(
        Netty,
        port = 8080,
        module = Application::mainModule,
        watchPaths = listOf("mainModule")
    )
}

fun Application.mainModule() {
    val courtEndpoint by KodeinModule.kodein.instance<CourtEndpointImpl>()
    install(ContentNegotiation) {
        jackson { }
    }

    install(StatusPages)
    install(Routing) {
        court(courtEndpoint)
    }
}

