package configuration.application

import auth.controller.registration
import auth.endpoint.RegistrationEndpointImpl
import configuration.kodein.KodeinModule
import court.controller.court
import court.endpoint.CourtEndpointImpl
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.routing.Routing
import org.kodein.di.generic.instance
import user.controller.user
import user.endpoint.UserEndpointImpl

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