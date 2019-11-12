package controller.user

import endpoint.user.UserEndpoint
import filterrequest.base.PageRequest
import filterrequest.user.UserSearchRequest
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.routing.Routing
import io.ktor.routing.route


@Location("/search")
data class UserSearchLocation(
    val searchRequest: UserSearchRequest,
    val pageRequest: PageRequest
)

fun Routing.user(userEndpoint: UserEndpoint) {
    route("users") {
        get<UserSearchLocation> { usrSearchLocation ->
            userEndpoint.search(this.context, usrSearchLocation)
        }
    }
}
