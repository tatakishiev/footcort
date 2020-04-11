package configuration.application

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.sessions.SessionStorageMemory
import io.ktor.sessions.Sessions
import io.ktor.sessions.cookie

fun Application.session() {
    install(Sessions) {
        cookie<UserSession>(
            Cookies.AUTH_COOKIE,
            storage = SessionStorageMemory()
        ) {
            cookie.path = "/"
            cookie.extensions["SameSite"] = "lax"
        }
    }
}

object Cookies {
    const val AUTH_COOKIE = "auth"
}
