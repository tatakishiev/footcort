package court.validation

import court.dto.CreateCourtDto
import org.valiktor.functions.isNotBlank
import org.valiktor.functions.isNotNull
import org.valiktor.validate

interface CourtValidator {
    fun createCourtValidation(createCourtDto: CreateCourtDto)
}

class CourtValidatorImpl : CourtValidator {

    override fun createCourtValidation(createCourtDto: CreateCourtDto) {
        validate(createCourtDto) {
            validate(CreateCourtDto::isHall).isNotNull()
            validate(CreateCourtDto::name).isNotNull().isNotBlank()
        }
    }
}