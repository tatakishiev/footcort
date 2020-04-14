package match.endpoint

import configuration.application.UserSession
import court.entity.Court
import court.service.CourtService
import match.dto.MatchCreateDto
import match.dto.MatchDto
import match.entity.Match
import match.exception.CourtNotFoundException
import match.filterrequest.MatchFilterRequest
import match.mapper.MatchMapper
import match.request.MatchCreateRequest
import match.service.MatchService
import user.entity.User
import user.service.UserService
import java.lang.Exception

interface MatchEndpoint {
    fun findByCourtId(courtId: Long, filterRequest: MatchFilterRequest): List<MatchDto>
    fun save(matchCreateDto: MatchCreateDto, courtId: Long, userSession: UserSession): MatchDto
}

class MatchEndpointImpl(
    private val matchService: MatchService,
    private val matchMapper: MatchMapper,
    private val courtService: CourtService,
    private val userService: UserService
) : MatchEndpoint {

    override fun findByCourtId(courtId: Long, filterRequest: MatchFilterRequest): List<MatchDto> {
        val court: Court = courtService.findById(courtId) ?: throw CourtNotFoundException(courtId)
        return matchService.findByCourt(court, filterRequest).map { matchMapper.toMatchDto(it) }
    }

    override fun save(matchCreateDto: MatchCreateDto, courtId: Long, userSession: UserSession): MatchDto {
        val court: Court = courtService.findById(courtId) ?: throw CourtNotFoundException(courtId)
        val currentUser: User = userService.findByPhoneNumber(userSession.phoneNumber)
        val matchCreateRequest: MatchCreateRequest =
            matchMapper.toMatchCreateRequest(matchCreateDto, court, currentUser)
        val match: Match = matchService.save(matchCreateRequest)
        return matchMapper.toMatchDto(match)
    }
}