package configuration.application

import configuration.kodein.KodeinModule
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.session
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import org.kodein.di.generic.instance
import user.entity.Roles
import user.service.UserServiceImpl


fun Application.security() {
    install(Authentication) {
        configureAuthSession()
//        configureAuthJwt()
    }
}

fun Authentication.Configuration.configureAuthSession() {
    val userService by KodeinModule.kodein.instance<UserServiceImpl>()
    session<UserSession>(Auth.SESSION) {
        challenge {
            call.respond(HttpStatusCode.Forbidden, FailMessage())
        }
        validate { session: UserSession ->
            userService.findByPhoneNumber(session.phoneNumber)
        }
    }
}

//fun Authentication.Configuration.configureAuthJwt() {
//    val userService by KodeinModule.kodein.instance<UserServiceImpl>()
//    jwt(AuthName.JWT) {
//        verifier(JwtProvider.verifier)
//        validate { credentials: JWTCredential ->
//            if (credentials.payload.audience.contains(JwtProvider.audience)) {
//                credentials.payload.claims["phoneNumber"]?.asString()?.let { userService.findByPhoneNumber(it) }
//            } else null
//        }
//    }
//}

object Auth {
    const val SESSION = "session"
    const val JWT = "jwt"
}

data class UserSession(
    val id: Long,
    val phoneNumber: String,
    val role: Roles
)

data class FailMessage(
    val statusCode: HttpStatusCode = HttpStatusCode.Forbidden,
    val message: String = "User is not Authenticated"
)