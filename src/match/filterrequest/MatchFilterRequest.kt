package match.filterrequest

import company.filterrequest.PageRequest
import match.entity.Matches
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.SortOrder
import java.time.LocalDateTime

data class MatchFilterRequest(
    val pageRequest: PageRequest = PageRequest(),
    val searchRequest: MatchSearchRequest?,
    val sortRequest: MatchSortRequest?
)

data class MatchSearchRequest(
    val start: LocalDateTime,
    val end: LocalDateTime
)

enum class MatchSortParam(val value: Column<*>) {
    ID(Matches.id)
}

data class MatchSortRequest(
    val param: MatchSortParam,
    val direction: SortOrder = SortOrder.ASC
)