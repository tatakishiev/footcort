package mapper.court

import dto.court.CourtDto
import domain.entity.court.Court
import domainrequest.court.CreateCourtRequest
import dto.court.CreateCourtDto

interface CourtMapper {
    fun toCourtDto(court: Court): CourtDto
    fun toCreateCourtRequest(createCourtDto: CreateCourtDto): CreateCourtRequest
}

class CourtMapperImpl: CourtMapper {

    override fun toCreateCourtRequest(createCourtDto: CreateCourtDto) = CreateCourtRequest(
        name = createCourtDto.name
    )

    override fun toCourtDto(court: Court) = CourtDto(
        id = court.id,
        name = court.name
    )
}