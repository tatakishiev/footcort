package configuration.application

import com.typesafe.config.ConfigFactory
import configuration.database.DatabaseFactory
import configuration.kodein.KodeinModule
import controller.court.court
import endpoint.court.CourtEndpointImpl
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.config.HoconApplicationConfig
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.jackson.jackson
import io.ktor.routing.Routing
import io.ktor.server.engine.*
import io.ktor.server.netty.Netty
import org.kodein.di.generic.instance

@EngineAPI
fun setup(): BaseApplicationEngine {
    DatabaseFactory.init()
    return configureServer()
}

@EngineAPI
fun configureServer(): BaseApplicationEngine {

    val port: Int = HoconApplicationConfig(ConfigFactory.load()).property("ktor.deployment.port").getString().toInt()
    val host: String = HoconApplicationConfig(ConfigFactory.load()).property("ktor.deployment.host").getString()

    val env = applicationEngineEnvironment {
        connector {
            this.host = host
            this.port = port
        }
        module {
            routingModule()
            mainDependencies()
        }
    }

    return embeddedServer(
        factory = Netty,
        environment = env
    )
}

fun Application.routingModule() {
    val courtEndpoint by KodeinModule.kodein.instance<CourtEndpointImpl>()
    install(Routing) {
        court(courtEndpoint)
    }
}


fun Application.mainDependencies() {
    install(ContentNegotiation) {
        jackson { }
    }
    install(StatusPages)
}
