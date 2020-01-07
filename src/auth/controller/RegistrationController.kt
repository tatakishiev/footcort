package auth.controller

import auth.endpoint.RegistrationEndpoint
import io.ktor.routing.Routing
import io.ktor.routing.post
import io.ktor.routing.route

fun Routing.registration(registrationEndpoint: RegistrationEndpoint) {
    route("/api/registration") {
        post { registrationEndpoint.register(this.context) }
        route("login") {
            post { registrationEndpoint.login(this.context) }
        }
    }
}