package match.controller

import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.post
import io.ktor.routing.route
import match.endpoint.MatchEndpoint
import match.filterrequest.MatchFilterRequest

fun Routing.match(matchEndpoint: MatchEndpoint) {
    route("/api/courts/{id}") {
        post {
            val id: Long = call.parameters["id"]!!.toLong()
            val filterRequest = this.context.receive<MatchFilterRequest>()
            this.context.respond(matchEndpoint.findByCourtId(id, filterRequest))
        }
    }
}