package configuration.database

import com.typesafe.config.ConfigFactory
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.config.HoconApplicationConfig
import org.jetbrains.exposed.sql.Database
import org.flywaydb.core.Flyway

object DatabaseFactory {

    private val appConfig = HoconApplicationConfig(ConfigFactory.load())
    private val dbUrl = appConfig.property("db.jdbcUrl").getString()
    private val dbUser = appConfig.property("db.dbUser").getString()
    private val dbPassword = appConfig.property("db.dbPassword").getString()

    fun init() {
        Database.connect(hikari())
//        val flyway = Flyway.configure().dataSource(
//            dbUrl,
//            dbUser,
//            dbPassword
//        ).load()
//        flyway.migrate()
    }



    private fun hikari(): HikariDataSource {
        val config = HikariConfig()
        config.driverClassName = "com.p6spy.engine.spy.P6SpyDriver"
        config.jdbcUrl = dbUrl
        config.username = dbUser
        config.password = dbPassword
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()
        return HikariDataSource(config)
    }
}