package auth.endpoint

import auth.dto.LoginRequestDto
import auth.dto.RegistrationDto
import auth.dto.TokenDto
import auth.mapper.TokenMapper
import io.ktor.application.ApplicationCall
import io.ktor.request.receive
import io.ktor.response.respond
import user.dto.UserDto
import user.entity.User
import user.mapper.UserMapper
import user.request.CreateUserRequest
import user.service.UserService
import utils.JwtProvider

interface RegistrationEndpoint {
    suspend fun login(ctx: ApplicationCall)
    suspend fun register(ctx: ApplicationCall)
}

class RegistrationEndpointImpl(
    private val userService: UserService,
    private val userMapper: UserMapper,
    private val tokenMapper: TokenMapper
) : RegistrationEndpoint {

    override suspend fun register(ctx: ApplicationCall) {
        val registrationDto: RegistrationDto = ctx.receive()
        val createUserRequest: CreateUserRequest = userMapper.toCreateUseRequest(registrationDto)
        val user: User = userService.create(createUserRequest)
        val token: String = JwtProvider.createJWT(user)
        val userDto: UserDto = userMapper.toDto(user)
        val tokenDto: TokenDto = tokenMapper.toTokenDto(token, userDto)
        ctx.respond(tokenDto)
    }

    override suspend fun login(ctx: ApplicationCall) {
        val loginRequestDto: LoginRequestDto = ctx.receive()
        val user: User = userService.findUserByCredentials(loginRequestDto)
        val token: String = JwtProvider.createJWT(user)
        val userDto: UserDto = userMapper.toDto(user)
        val tokenDto: TokenDto = tokenMapper.toTokenDto(token, userDto)
        ctx.respond(tokenDto)
    }
}