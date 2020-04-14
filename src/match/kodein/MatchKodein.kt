package match.kodein

import match.endpoint.MatchEndpointImpl
import match.mapper.MatchMapperImpl
import match.repository.MatchRepositoryImpl
import match.service.MatchServiceImpl
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

object MatchKodein {
    val module = Kodein.Module("MATCH") {
        bind() from singleton { MatchRepositoryImpl() }
        bind() from singleton { MatchMapperImpl() }
        bind() from singleton { MatchServiceImpl(instance()) }
        bind() from singleton { MatchEndpointImpl(instance(), instance(), instance(), instance()) }
    }
}