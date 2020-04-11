import configuration.application.configureServer
import configuration.application.setup
import configuration.database.DatabaseFactory
import io.ktor.server.engine.EngineAPI
import io.ktor.util.KtorExperimentalAPI

@KtorExperimentalAPI
@EngineAPI
fun main() {
    DatabaseFactory.init()
    configureServer()
    setup().start(wait = true)
}