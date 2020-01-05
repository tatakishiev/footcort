package user.endpoint

import user.dto.UserDto
import io.ktor.application.ApplicationCall
import io.ktor.response.respond
import user.mapper.UserMapper
import user.service.UserService

interface UserEndpoint {
    suspend fun findAll(ctx: ApplicationCall)
}

class UserEndpointImpl(
    private val userService: UserService,
    private val userMapper: UserMapper
) : UserEndpoint {

    override suspend fun findAll(ctx: ApplicationCall) {
        val users: List<UserDto> = userService.findAll().map { userMapper.toDto(it) }
        ctx.respond(users)
    }
}