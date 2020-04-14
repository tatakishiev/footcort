import configuration.application.configureServer
import configuration.database.DatabaseFactory
import io.ktor.server.engine.EngineAPI
import io.ktor.util.KtorExperimentalAPI

@KtorExperimentalAPI
@EngineAPI
fun main() {
    DatabaseFactory.init()
    configureServer().start(wait = true)
}