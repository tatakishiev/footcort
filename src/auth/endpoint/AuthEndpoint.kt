package auth.endpoint

import auth.dto.LoginRequestDto
import auth.dto.RegistrationDto
import auth.dto.TokenDto
import auth.mapper.TokenMapper
import auth.mapper.UserSessionMapper
import auth.validation.AuthValidation
import configuration.application.UserSession
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.sessions.sessions
import io.ktor.sessions.set
import org.valiktor.functions.isNotBlank
import org.valiktor.validate
import user.dto.UserDto
import user.entity.User
import user.mapper.UserMapper
import user.request.CreateUserRequest
import user.service.UserService
import utils.JwtProvider

interface AuthEndpoint {
    suspend fun login(context: ApplicationCall)
    suspend fun register(context: ApplicationCall)
}

class AuthEndpointImpl(
    private val userService: UserService,
    private val userMapper: UserMapper,
    private val userSessionMapper: UserSessionMapper,
    private val authValidation: AuthValidation
) : AuthEndpoint {

    override suspend fun register(context: ApplicationCall) {
        val registrationDto: RegistrationDto = context.receive()
        authValidation.registrationValidation(registrationDto)
        val createUserRequest: CreateUserRequest = userMapper.toCreateUseRequest(registrationDto)
        val user: User = userService.create(createUserRequest)
        val userSession: UserSession = userSessionMapper.toUserSession(user)
        val userDto: UserDto = userMapper.toDto(user)
        context.sessions.set(userSession)
        context.respond(userDto)
    }

    override suspend fun login(context: ApplicationCall) {
        val loginRequestDto: LoginRequestDto = context.receive()
        val user: User = userService.findUserByCredentials(loginRequestDto)
        val userSession: UserSession = userSessionMapper.toUserSession(user)
        val userDto: UserDto = userMapper.toDto(user)
        context.sessions.set(userSession)
        context.respond(userDto)
    }

//JWT Login and registration impl
//    override suspend fun register(ctx: ApplicationCall) {
//        val registrationDto: RegistrationDto = ctx.receive()
//        val createUserRequest: CreateUserRequest = userMapper.toCreateUseRequest(registrationDto)
//        val user: User = userService.create(createUserRequest)
//        val token: String = JwtProvider.createJWT(user)
//        val userDto: UserDto = userMapper.toDto(user)
//        val tokenDto: TokenDto = tokenMapper.toTokenDto(token, userDto)
//        ctx.respond(tokenDto)
//    }
//
//    override suspend fun login(ctx: ApplicationCall) {
//        val loginRequestDto: LoginRequestDto = ctx.receive()
//        val user: User = userService.findUserByCredentials(loginRequestDto)
//        val token: String = JwtProvider.createJWT(user)
//        val userDto: UserDto = userMapper.toDto(user)
//        val tokenDto: TokenDto = tokenMapper.toTokenDto(token, userDto)
//        ctx.respond(tokenDto)
//    }
}