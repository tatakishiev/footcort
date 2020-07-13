package court.kodein

import court.endpoint.CourtEndpointImpl
import court.mapper.CourtMapperImpl
import court.repository.CourtRepositoryImpl
import court.service.CourtServiceImpl
import court.validation.CourtValidator
import court.validation.CourtValidatorImpl
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

object CourtKodein {
    val module = Kodein.Module("COURT") {
        bind() from singleton { CourtEndpointImpl(instance(), instance(), instance()) }
        bind() from singleton { CourtServiceImpl(instance()) }
        bind() from singleton { CourtMapperImpl() }
        bind() from singleton { CourtRepositoryImpl() }
        bind() from singleton { CourtValidatorImpl() }
    }
}