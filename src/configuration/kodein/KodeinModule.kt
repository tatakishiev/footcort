package configuration.kodein

import endpoint.court.CourtEndpointImpl
import mapper.court.CourtMapperImpl
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import repository.court.CourtRepositoryImpl
import service.court.CourtServiceImpl

object KodeinModule {
    private val courtModule = Kodein.Module("COURT") {
        bind() from singleton { CourtEndpointImpl(instance(), instance()) }
        bind() from singleton { CourtServiceImpl(instance()) }
        bind() from singleton { CourtMapperImpl() }
        bind() from singleton { CourtRepositoryImpl() }
    }

    internal val kodein = Kodein {
        import(courtModule)
    }
}