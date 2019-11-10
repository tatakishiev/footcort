package controller.user

import endpoint.user.UserEndpoint
import io.ktor.routing.Routing
import io.ktor.routing.post
import io.ktor.routing.route

fun Routing.user(userEndpoint: UserEndpoint) {
    route("users") {
        post { userEndpoint.create(this.context) }
    }
}