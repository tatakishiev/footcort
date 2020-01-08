package company.controller

import company.dto.CompanyCreateDto
import company.dto.CompanyUpdateDto
import company.endpoint.CompanyEndpoint
import company.filterrequest.CompanyFilterRequest
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.post
import io.ktor.routing.put
import io.ktor.routing.route

fun Routing.company(companyEndpoint: CompanyEndpoint) {
    route("api/companies") {
        post {
            val dto = this.context.receive<CompanyFilterRequest>()
            this.context.respond(companyEndpoint.search(dto))
        }
        post("save") {
            val dto = this.context.receive<CompanyCreateDto>()
            this.context.respond(companyEndpoint.save(dto))
        }
        put("{id}") {
            val id: Long = call.parameters["id"]!!.toLong()
            val dto = this.context.receive<CompanyUpdateDto>()
            this.context.respond(companyEndpoint.update(dto, id))
        }
    }
}