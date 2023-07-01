package flashcards

import java.io.File
import java.io.IOException

class FileHandler {
    fun readFlashcards(filename: String): List<Flashcard> {
        val flashcards = mutableListOf<Flashcard>()
        try {
            val flashcardTextLines = File(filename).readText().trim().split("\r\n")
            for (line in flashcardTextLines.indices step 3) {
                val term = flashcardTextLines[line]
                val definition = flashcardTextLines[line + 1]
                val errors = flashcardTextLines[line + 2].toInt()
                flashcards.add(Flashcard(term, definition, errors))
            }
            return flashcards
        } catch (e: IOException) {
            throw RuntimeException("File not found.")
        }
    }

    fun saveFlashcards(flashcards: List<Flashcard>, filename: String) {
        val builder = StringBuilder()
        flashcards.forEach {
            builder.append(it.term)
            builder.append("\r\n")
            builder.append(it.definition)
            builder.append("\r\n")
            builder.append(it.errors)
            builder.append("\r\n")
        }
        File(filename).writeText(builder.toString())
    }

    fun saveLogBook(filename: String) {
        File(filename).writeText(Logger.getLogBook().joinToString("\r\n"))
    }
}