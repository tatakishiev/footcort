package court.endpoint

import court.service.CourtService
import io.ktor.application.ApplicationCall
import io.ktor.response.respond
import court.mapper.CourtMapper

interface CourtEndpoint {
    suspend fun findAll(ctx: ApplicationCall)
}

class CourtEndpointImpl(
    private val courtService: CourtService,
    private val courtMapper: CourtMapper
) : CourtEndpoint {

    override suspend fun findAll(ctx: ApplicationCall) {
        return ctx.respond(courtService.findAll().map { courtMapper.toCourtDto(it) })
    }
}