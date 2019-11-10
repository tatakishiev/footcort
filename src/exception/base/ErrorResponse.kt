package exception.base

internal data class ErrorResponse(val errors: Map<String, List<String?>>)