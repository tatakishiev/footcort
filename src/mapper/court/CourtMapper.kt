package mapper.court

import dto.court.CourtDto
import domain.entity.court.Court

interface CourtMapper {
    fun toCourtDto(court: Court): CourtDto
}

class CourtMapperImpl: CourtMapper {
    override fun toCourtDto(court: Court) = CourtDto(
        id = court.id,
        name = court.name
    )
}