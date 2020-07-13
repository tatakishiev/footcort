package court.controller

import configuration.application.Auth
import court.dto.CourtDto
import court.dto.CreateCourtDto
import court.endpoint.CourtEndpoint
import io.ktor.auth.authenticate
import io.ktor.request.*
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route

fun Routing.court(courtEndpoint: CourtEndpoint) {
    authenticate(Auth.SESSION) {
        route("/api/courts") {
            get {
                val courts: List<CourtDto> = courtEndpoint.findAll()
                this.context.respond(courts)
            }
            post {
                val createCourtDto: CreateCourtDto = this.context.receive()
                val courtDto: CourtDto = courtEndpoint.create(createCourtDto)
                this.context.respond(courtDto)
            }
        }
    }
}