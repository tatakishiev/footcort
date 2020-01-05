package configuration.application

import configuration.kodein.KodeinModule
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.jwt.jwt
import org.kodein.di.generic.instance
import user.service.UserServiceImpl
import utils.JwtProvider


fun Application.security() {
    val userService by KodeinModule.kodein.instance<UserServiceImpl>()
    install(Authentication) {
        jwt {
            verifier(JwtProvider.verifier)
            validate { credentials ->
                if (credentials.payload.audience.contains(JwtProvider.audience)) {
                    credentials.payload.claims["phoneNumber"]?.asString()?.let { userService.findByPhoneNumber(it) }
                } else null
            }
        }
    }
}
