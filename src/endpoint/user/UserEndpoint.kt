package endpoint.user

import controller.user.UserSearchLocation
import domain.entity.user.User
import dto.user.UserDto
import filterrequest.user.UserFilterRequest
import io.ktor.application.ApplicationCall
import io.ktor.http.Parameters
import io.ktor.locations.locations
import io.ktor.response.respond
import mapper.user.UserMapper
import service.user.UserService

interface UserEndpoint {
    suspend fun search(ctx: ApplicationCall, userSearchLocation: UserSearchLocation)
}

class UserEndpointImpl(
    private val userService: UserService,
    private val userMapper: UserMapper
) : UserEndpoint {

    override suspend fun search(ctx: ApplicationCall, userSearchLocation: UserSearchLocation) {
        val users: List<UserDto> = userService.search(userSearchLocation).map { userMapper.toDto(it) }
        ctx.respond(users)
    }
}