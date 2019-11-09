package controller.court

import endpoint.court.CourtEndpoint
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.route

fun Routing.court(courtEndpoint: CourtEndpoint) {
    route("courts") {
        get { call.respond(courtEndpoint.getAll()) }
    }
}