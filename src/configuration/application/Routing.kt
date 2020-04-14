package configuration.application

import auth.controller.auth
import auth.endpoint.AuthEndpointImpl
import company.controller.company
import company.endpoint.CompanyEndpointImpl
import configuration.kodein.KodeinModule
import court.controller.court
import court.endpoint.CourtEndpointImpl
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.auth.authenticate
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
        auth(registrationEndpoint)
//        authenticate(Auth.SESSION) {
        this@install.court(courtEndpoint)
        this@install.user(userEndpoint)
        this@install.company(companyEndpoint)
        this@install.match(matchEndpoint)
//        }
    }
}