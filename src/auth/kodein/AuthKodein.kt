package auth.kodein

import auth.endpoint.RegistrationEndpointImpl
import auth.mapper.TokenMapperImpl
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

object AuthKodein {
    val module = Kodein.Module("REGISTRATION") {
        bind() from singleton { TokenMapperImpl() }
        bind() from singleton {
            RegistrationEndpointImpl(
                instance(),
                instance(),
                instance()
            )
        }
    }
}