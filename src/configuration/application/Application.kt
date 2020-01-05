package configuration.application

import configuration.database.DatabaseFactory
import io.ktor.server.engine.BaseApplicationEngine
import io.ktor.server.engine.EngineAPI
import io.ktor.util.KtorExperimentalAPI

@KtorExperimentalAPI
@EngineAPI
fun setup(): BaseApplicationEngine {
    DatabaseFactory.init()
    return configureServer()
}