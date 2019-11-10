package endpoint.user

import domainrequest.user.CreateUserRequest
import dto.user.CreateUserDto
import dto.user.UserDto
import io.ktor.application.ApplicationCall
import io.ktor.request.receive
import io.ktor.response.respond
import mapper.user.UserMapper
import service.user.UserService

interface UserEndpoint {
    suspend fun create(ctx: ApplicationCall)
}

class UserEndpointImpl(private val userService: UserService,
                       private val userMapper: UserMapper) : UserEndpoint {

    override suspend fun create(ctx: ApplicationCall) {
        val createUserDto: CreateUserDto = ctx.receive()
        val createUserRequest: CreateUserRequest = userMapper.toCreateUseRequest(createUserDto)
        ctx.respond(userService.create(createUserRequest))
    }
}