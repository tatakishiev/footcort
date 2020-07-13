package court.endpoint

import court.dto.CourtDto
import court.dto.CreateCourtDto
import court.entity.Court
import court.mapper.CourtMapper
import court.request.CreateCourtRequest
import court.service.CourtService

interface CourtEndpoint {
    suspend fun findAll(): List<CourtDto>
    suspend fun create(createCourtDto: CreateCourtDto): CourtDto
}

class CourtEndpointImpl(
    private val courtService: CourtService,
    private val courtMapper: CourtMapper
) : CourtEndpoint {

    override suspend fun findAll(): List<CourtDto> {
        return courtService.findAll().map { courtMapper.toCourtDto(it) }
    }

    override suspend fun create(createCourtDto: CreateCourtDto): CourtDto {
        val createCourtRequest: CreateCourtRequest = courtMapper.toCreateCourtRequest(createCourtDto)
        val court: Court = courtService.create(createCourtRequest)
        return courtMapper.toCourtDto(court)
    }
}