package controller.user

import endpoint.user.UserEndpoint
import filterrequest.base.PageRequest
import filterrequest.user.UserSearchRequest
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.routing.Routing

@KtorExperimentalLocationsAPI
fun Routing.user(userEndpoint: UserEndpoint) {
    get<UserSearchLocation> { usrSearchLocation ->
        userEndpoint.search(this.context, usrSearchLocation)
    }
}

@Location("/search")
data class UserSearchLocation(
    val searchRequest: UserSearchRequest?,
    val pageRequest: PageRequest = PageRequest()
)