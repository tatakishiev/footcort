package controller.court

import endpoint.court.CourtEndpoint
import io.ktor.auth.authenticate
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route

fun Routing.court(courtEndpoint: CourtEndpoint) {
    authenticate {
        route("courts") {
            get { courtEndpoint.getAll(this.context) }
            post { courtEndpoint.create(this.context) }
        }
    }
}