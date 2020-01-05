import configuration.application.setup
import io.ktor.server.engine.EngineAPI
import io.ktor.util.KtorExperimentalAPI

@KtorExperimentalAPI
@EngineAPI
fun main() {
    setup().start(wait = true)
}