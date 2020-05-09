package court.endpoint

import court.dto.CourtDto
import court.mapper.CourtMapper
import court.service.CourtService

interface CourtEndpoint {
    suspend fun findAll(): List<CourtDto>
}

class CourtEndpointImpl(
    private val courtService: CourtService,
    private val courtMapper: CourtMapper
) : CourtEndpoint {

    override suspend fun findAll(): List<CourtDto> {
        return courtService.findAll().map { courtMapper.toCourtDto(it) }
    }
}