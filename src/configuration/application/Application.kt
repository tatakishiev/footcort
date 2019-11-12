package configuration.application

import com.typesafe.config.ConfigFactory
import configuration.database.DatabaseFactory
import configuration.kodein.KodeinModule
import controller.court.court
import controller.registration.registration
import controller.user.UserSearchLocation
import controller.user.user
import endpoint.court.CourtEndpointImpl
import endpoint.registration.RegistrationEndpointImpl
import endpoint.user.UserEndpointImpl
import exception.base.ErrorResponse
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.jwt.jwt
import io.ktor.config.HoconApplicationConfig
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.http.HttpStatusCode
import io.ktor.jackson.jackson
import io.ktor.locations.Locations
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.*
import io.ktor.server.netty.Netty
import org.kodein.di.generic.instance
import service.user.UserServiceImpl
import utils.JwtProvider

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
            statusPages()
            security()
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
    val userEndpoint by KodeinModule.kodein.instance<UserEndpointImpl>()
    val registrationEndpoint by KodeinModule.kodein.instance<RegistrationEndpointImpl>()
    install(Routing) {
        court(courtEndpoint)
        user(userEndpoint)
        registration(registrationEndpoint)
    }
}

fun Application.mainDependencies() {
    install(ContentNegotiation) {
        jackson { }
    }
    install(Locations)
}

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

fun Application.security() {
    val userService by KodeinModule.kodein.instance<UserServiceImpl>()
    install(Authentication) {
        jwt {
            verifier(JwtProvider.verifier)
            validate { credentials ->
                if (credentials.payload.audience.contains(JwtProvider.audience)) {
                    credentials.payload.claims["phoneNumber"]?.asString()?.let { userService.findByPhoneNumber(it) }
                } else null
            }
        }
    }
}
