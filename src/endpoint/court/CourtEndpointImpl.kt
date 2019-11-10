package endpoint.court

import domainrequest.court.CreateCourtRequest
import dto.court.CreateCourtDto
import io.ktor.application.ApplicationCall
import io.ktor.request.receive
import io.ktor.response.respond
import mapper.court.CourtMapper
import service.court.CourtService

interface CourtEndpoint {
    suspend fun getAll(ctx: ApplicationCall)
    suspend fun create(ctx: ApplicationCall)
}

class CourtEndpointImpl(
    private val courtService: CourtService,
    private val courtMapper: CourtMapper
) : CourtEndpoint {

    override suspend fun create(ctx: ApplicationCall) {
        val createCourtDto: CreateCourtDto = ctx.receive()
        val createCourtRequest: CreateCourtRequest = courtMapper.toCreateCourtRequest(createCourtDto)
        ctx.respond(courtService.create(createCourtRequest))
    }

    override suspend fun getAll(ctx: ApplicationCall) {
        ctx.respond(courtService.getAll().map { courtMapper.toCourtDto(it) })
    }
}