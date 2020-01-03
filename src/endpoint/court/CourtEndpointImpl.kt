package endpoint.court

import domain.entity.court.Court
import domainrequest.court.CreateCourtRequest
import dto.court.CourtDto
import dto.court.CreateCourtDto
import io.ktor.application.ApplicationCall
import io.ktor.request.receive
import io.ktor.response.respond
import mapper.court.CourtMapper
import service.court.CourtService

interface CourtEndpoint {
    suspend fun getAll(ctx: ApplicationCall)
    suspend fun create(ctx: ApplicationCall): CourtDto
}

class CourtEndpointImpl(
    private val courtService: CourtService,
    private val courtMapper: CourtMapper
) : CourtEndpoint {

    override suspend fun create(ctx: ApplicationCall): CourtDto {
        val createCourtDto: CreateCourtDto = ctx.receive()
        val createCourtRequest: CreateCourtRequest = courtMapper.toCreateCourtRequest(createCourtDto)
        val createdCourt: Court = courtService.create(createCourtRequest)
        return courtMapper.toCourtDto(createdCourt)
    }

    override suspend fun getAll(ctx: ApplicationCall) {
        return ctx.respond(courtService.getAll().map { courtMapper.toCourtDto(it) })
    }
}