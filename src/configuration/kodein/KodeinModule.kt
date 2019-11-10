package configuration.kodein

import endpoint.court.CourtEndpointImpl
import endpoint.registration.RegistrationEndpointImpl
import endpoint.user.UserEndpointImpl
import mapper.court.CourtMapperImpl
import mapper.registration.TokenMapperImpl
import mapper.user.UserMapperImpl
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import repository.court.CourtRepositoryImpl
import repository.user.UserRepositoryImpl
import service.court.CourtServiceImpl
import service.user.UserServiceImpl

object KodeinModule {
    private val courtModule = Kodein.Module("COURT") {
        bind() from singleton { CourtEndpointImpl(instance(), instance()) }
        bind() from singleton { CourtServiceImpl(instance()) }
        bind() from singleton { CourtMapperImpl() }
        bind() from singleton { CourtRepositoryImpl() }
    }

    private val userModule = Kodein.Module("USER") {
        bind() from singleton { UserRepositoryImpl() }
        bind() from singleton { UserServiceImpl(instance()) }
        bind() from singleton { UserMapperImpl() }
        bind() from singleton { UserEndpointImpl(instance(), instance()) }
    }

    private val registrationModule = Kodein.Module("REGISTRATION") {
        bind() from singleton { TokenMapperImpl() }
        bind() from singleton {
            RegistrationEndpointImpl(
                instance(),
                instance(),
                instance()
            )
        }
    }

    internal val kodein = Kodein {
        import(courtModule)
        import(userModule)
        import(registrationModule)
    }
}