package user.controller

import io.ktor.auth.authenticate
import io.ktor.routing.Routing
import io.ktor.routing.post
import io.ktor.routing.route
import user.endpoint.UserEndpoint

fun Routing.user(userEndpoint: UserEndpoint) {
    authenticate {
        route("/api/users") {
            post { userEndpoint.findAll(this.context) }
        }
    }
}