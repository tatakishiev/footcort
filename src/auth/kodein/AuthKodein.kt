package auth.kodein

import auth.endpoint.AuthEndpointImpl
import auth.mapper.TokenMapperImpl
import auth.mapper.UserSessionMapper
import auth.mapper.UserSessionMapperImpl
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

object AuthKodein {
    val module = Kodein.Module("REGISTRATION") {
        bind() from singleton { TokenMapperImpl() }
        bind() from singleton { AuthEndpointImpl(instance(), instance(), instance()) }
        bind() from singleton { UserSessionMapperImpl() }
    }
}