package endpoint.court

import dto.court.CourtDto
import mapper.court.CourtMapper
import service.court.CourtService

interface CourtEndpoint {
    fun getAll(): List<CourtDto>
}

class CourtEndpointImpl(
    private val courtService: CourtService,
    private val courtMapper: CourtMapper
) : CourtEndpoint {
    override fun getAll(): List<CourtDto> {
        return courtService.getAll().map { courtMapper.toCourtDto(it) }
    }
}