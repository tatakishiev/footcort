package match.endpoint

import court.entity.Court
import court.service.CourtService
import match.dto.MatchDto
import match.exception.CourtNotFoundException
import match.filterrequest.MatchFilterRequest
import match.mapper.MatchMapper
import match.service.MatchService

interface MatchEndpoint {
    fun findByCourtId(courtId: Long, filterRequest: MatchFilterRequest): List<MatchDto>
}

class MatchEndpointImpl(
    private val matchService: MatchService,
    private val matchMapper: MatchMapper,
    private val courtService: CourtService
) : MatchEndpoint {

    override fun findByCourtId(courtId: Long, filterRequest: MatchFilterRequest): List<MatchDto> {
        val court: Court = courtService.findById(courtId) ?: throw CourtNotFoundException(courtId)
        return matchService.findByCourt(court, filterRequest).map { matchMapper.toMatchDto(it) }
    }
}