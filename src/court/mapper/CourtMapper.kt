package court.mapper

import court.dto.CourtDto
import court.dto.CreateCourtDto
import court.entity.Court
import court.request.CreateCourtRequest

interface CourtMapper {
    fun toCourtDto(court: Court): CourtDto
    fun toCreateCourtRequest(createCourtDto: CreateCourtDto): CreateCourtRequest
}

class CourtMapperImpl : CourtMapper {

    override fun toCreateCourtRequest(createCourtDto: CreateCourtDto) =
        CreateCourtRequest(
            name = createCourtDto.name,
            isHall = createCourtDto.isHall
        )

    override fun toCourtDto(court: Court) = CourtDto(
        id = court.id,
        name = court.name,
        isHall = court.isHall
    )
}