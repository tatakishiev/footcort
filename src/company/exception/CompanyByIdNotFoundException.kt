package company.exception

import company.entity.Company
import exception.EntityNotFoundException

class CompanyByIdNotFoundException(id: Long) : EntityNotFoundException(id = id, entity = Company::class)