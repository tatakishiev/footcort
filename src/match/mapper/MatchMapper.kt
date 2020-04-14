package match.mapper

import court.entity.Court
import match.dto.MatchCreateDto
import match.dto.MatchDto
import match.entity.Match
import match.request.MatchCreateRequest
import user.entity.User

interface MatchMapper {
    fun toMatchDto(match: Match): MatchDto
    fun toMatchCreateRequest(
        matchCreateDto: MatchCreateDto,
        court: Court,
        currentUser: User
    ): MatchCreateRequest
}

class MatchMapperImpl : MatchMapper {
    override fun toMatchDto(match: Match): MatchDto {
        return MatchDto(
            id = match.id,
            start = match.start,
            end = match.end
        )
    }

    override fun toMatchCreateRequest(
        matchCreateDto: MatchCreateDto,
        court: Court,
        currentUser: User
    ): MatchCreateRequest {
        return MatchCreateRequest(
            start = matchCreateDto.start,
            end = matchCreateDto.end,
            description = matchCreateDto.description,
            courtId = court.id,
            creatorId = currentUser.id
        )
    }
}