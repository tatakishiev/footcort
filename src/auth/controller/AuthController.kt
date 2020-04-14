package auth.controller

import auth.endpoint.AuthEndpoint
import io.ktor.routing.Routing
import io.ktor.routing.post
import io.ktor.routing.route

fun Routing.auth(authEndpoint: AuthEndpoint) {
    route("/api/auth") {
        post("/registration") { authEndpoint.register(this.context) }
        post("/login") { authEndpoint.login(this.context) }
    }
}