package configuration.kodein

import endpoint.court.CourtEndpointImpl
import mapper.court.CourtMapperImpl
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import service.court.CourtServiceImpl

object KodeinModule {
    private val courtModule = Kodein.Module("COURT") {
        bind() from singleton { CourtEndpointImpl(instance(), instance()) }
        bind() from singleton { CourtServiceImpl() }
        bind() from singleton { CourtMapperImpl() }
    }

    internal val kodein = Kodein {
        import(courtModule)
    }
}