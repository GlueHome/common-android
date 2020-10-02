package gluehome.common.data.network

class HttpLogFilter {

    private val includeList = listOf(
        "glue-correlation-id: ",
        "<-- ",
        "{",
        "[",
        "--> GET",
        "--> POST",
        "--> DELETE",
        "--> PUT",
        "WWW-Authenticate",
        "--> PATCH"
    )

    fun shouldBeRemoved(word: String) = ! includeList.any { word.startsWith(it, ignoreCase = true) }
}