package court.exception

import court.entity.Court
import exception.EntityNotFoundException

class CourtNotFoundException(id: Long) : EntityNotFoundException(id = id, entity = Court::class)