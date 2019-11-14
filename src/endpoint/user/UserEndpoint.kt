package endpoint.user

import controller.user.UserSearchLocation
import dto.user.UserDto
import io.ktor.application.ApplicationCall
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