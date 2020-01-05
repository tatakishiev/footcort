package user.controller

import user.endpoint.UserEndpoint
import io.ktor.routing.Routing
import io.ktor.routing.post
import io.ktor.routing.route

fun Routing.user(userEndpoint: UserEndpoint) {
    route("/api/users") {
        post { userEndpoint.findAll(this.context) }
    }
}