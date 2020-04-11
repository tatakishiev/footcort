package configuration.application

import auth.controller.jwt
import auth.endpoint.AuthEndpointImpl
import company.controller.company
import company.endpoint.CompanyEndpointImpl
import configuration.kodein.KodeinModule
import court.controller.court
import court.endpoint.CourtEndpointImpl
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.routing.Routing
import match.controller.match
import match.endpoint.MatchEndpointImpl
import org.kodein.di.generic.instance
import user.controller.user
import user.endpoint.UserEndpointImpl

fun Application.routingModule() {
    val courtEndpoint by KodeinModule.kodein.instance<CourtEndpointImpl>()
    val userEndpoint by KodeinModule.kodein.instance<UserEndpointImpl>()
    val registrationEndpoint by KodeinModule.kodein.instance<AuthEndpointImpl>()
    val companyEndpoint by KodeinModule.kodein.instance<CompanyEndpointImpl>()
    val matchEndpoint by KodeinModule.kodein.instance<MatchEndpointImpl>()

    install(Routing) {
        court(courtEndpoint)
        user(userEndpoint)
        jwt(registrationEndpoint)
        company(companyEndpoint)
        match(matchEndpoint)
    }
}