package company.kodein

import company.endpoint.CompanyEndpointImpl
import company.mapper.CompanyMapperImpl
import company.repository.CompanyRepositoryImpl
import company.service.CompanyServiceImpl
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

object CompanyKodein {
    val module = Kodein.Module("COMPANY") {
        bind() from singleton { CompanyRepositoryImpl() }
        bind() from singleton { CompanyMapperImpl() }
        bind() from singleton { CompanyServiceImpl(instance()) }
        bind() from singleton { CompanyEndpointImpl(instance(), instance()) }
    }
}