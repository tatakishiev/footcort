package match.mapper

import match.dto.MatchDto
import match.entity.Match

interface MatchMapper {
    fun toMatchDto(match: Match): MatchDto
}

class MatchMapperImpl : MatchMapper {
    override fun toMatchDto(match: Match): MatchDto {
        return MatchDto(
            id = match.id,
            start = match.start,
            end = match.end
        )
    }
}