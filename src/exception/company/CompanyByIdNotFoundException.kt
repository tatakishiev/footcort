package exception.company

class CompanyByIdNotFoundException(id: Long) : Exception("Company with id $id not found")