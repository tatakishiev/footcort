package match.controller

import configuration.application.Cookies
import configuration.application.FailMessage
import configuration.application.UserSession
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import match.dto.MatchCreateDto
import match.endpoint.MatchEndpoint
import match.filterrequest.MatchFilterRequest

fun Routing.match(matchEndpoint: MatchEndpoint) {
    route("/api/courts/{id}/matches") {
        post {
            val id: Long = call.parameters["id"]!!.toLong()
            val filterRequest = this.context.receive<MatchFilterRequest>()
            this.context.respond(matchEndpoint.findByCourtId(id, filterRequest))
        }
        post("/save") {
            val courtId: Long = call.parameters["id"]!!.toLong()
            val matchCreateDto = this.context.receive<MatchCreateDto>()
            val userSession: UserSession = call.sessions.get<UserSession>()!!
            this.context.respond(matchEndpoint.save(matchCreateDto, courtId, userSession))
        }
    }
}