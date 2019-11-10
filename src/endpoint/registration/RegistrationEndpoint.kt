package endpoint.registration

import domain.entity.user.User
import domainrequest.user.CreateUserRequest
import dto.registration.LoginRequestDto
import dto.registration.RegistrationDto
import dto.registration.TokenDto
import dto.user.UserDto
import io.ktor.application.ApplicationCall
import io.ktor.request.receive
import io.ktor.response.respond
import mapper.registration.TokenMapper
import mapper.user.UserMapper
import service.user.UserService
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