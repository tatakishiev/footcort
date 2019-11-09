package controller.court

import endpoint.court.CourtEndpoint
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.route

fun Routing.court(courtEndpoint: CourtEndpoint) {
    route("/api/courts") {
        get { courtEndpoint.getAll() }
    }
}