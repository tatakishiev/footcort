package unit.service.company

import company.entity.Company
import company.repository.CompanyRepository
import company.service.CompanyServiceImpl
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import kotlin.test.assertEquals

//class CompanyServiceTests {
//
//    private val repository: CompanyRepository = mockk()
//    private val service = CompanyServiceImpl(repository)
//
//    @Test
//    fun findById_shouldReturnCompany_whenCompanyExistsInDataBase() {
//        val company = Company(id = 1, name = "futbolistan", address = "Tynystanova")
//        every { repository.findById(1) } returns company
//
//
//        val actual: Company? = service.findById(1)
//
//        assertEquals(actual, company)
//    }
//}
//
