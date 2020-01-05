package user.kodein

import user.endpoint.UserEndpointImpl
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import user.mapper.UserMapperImpl
import user.repository.UserRepositoryImpl
import user.service.UserServiceImpl

object UserKodein {
    val module = Kodein.Module("USER") {
        bind() from singleton { UserRepositoryImpl() }
        bind() from singleton { UserServiceImpl(instance()) }
        bind() from singleton { UserMapperImpl() }
        bind() from singleton { UserEndpointImpl(instance(), instance()) }
    }
}