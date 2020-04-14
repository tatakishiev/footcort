package configuration.application

import com.typesafe.config.ConfigFactory
import io.ktor.config.HoconApplicationConfig
import io.ktor.server.engine.*
import io.ktor.server.netty.Netty
import io.ktor.util.KtorExperimentalAPI

@KtorExperimentalAPI
@EngineAPI
fun configureServer(): BaseApplicationEngine {

    val port: Int = HoconApplicationConfig(ConfigFactory.load()).property("ktor.deployment.port").getString().toInt()
    val host: String = HoconApplicationConfig(ConfigFactory.load()).property("ktor.deployment.host").getString()

    val env = applicationEngineEnvironment {
        connector {
            this.host = host
            this.port = port
        }
        module {
            jackson()
            statusPages()
            security()
            routingModule()
            session()
        }
    }

    return embeddedServer(
        factory = Netty,
        environment = env
    )
}