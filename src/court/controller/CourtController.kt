package court.controller

import court.endpoint.CourtEndpoint
import io.ktor.auth.authenticate
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.route

fun Routing.court(courtEndpoint: CourtEndpoint) {
    authenticate {
        route("/api/courts") {
            get { courtEndpoint.findAll(this.context) }
        }
    }
}