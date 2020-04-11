package match.exception

import java.lang.Exception

class CourtNotFoundException(id: Long) : Exception("There is no Court with id $id")