package flashcards

object Logger {
    private val logBook = mutableListOf<String>()

    fun print(log: String) {
        logBook.add(log)
        println(log)
    }

    fun read(): String {
        val input = readln()
        logBook.add(input)
        return input
    }

    fun getLogBook() = logBook
}