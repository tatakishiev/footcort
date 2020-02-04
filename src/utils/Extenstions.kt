package utils

import utils.pagination.PageResponse

fun <T> Collection<T>.toPageResponse(): PageResponse<T> {
    return PageResponse(
        total = this.size,
        list = this as List<T>
    )
}