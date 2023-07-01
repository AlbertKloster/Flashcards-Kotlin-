package flashcards

import java.util.*

class FlashcardsService {
    private val flashcards = Flashcards()
    private val fileHandler = FileHandler()

    private fun getTerm(): String {
        val term = Logger.read()
        if (getAllFlashcardsByTerm(term).isEmpty()) return term
        throw RuntimeException("The card \"$term\" already exists.")
    }

    private fun getDefinition(): String {
        val definition = Logger.read()
        if (getAllFlashcardsByDefinition(definition).isEmpty()) return definition
        throw RuntimeException("The definition \"$definition\" already exists.")
    }

    private fun getAllFlashcardsByTerm(term: String) = flashcards.getAllFlashcards().filter { it.term == term }

    private fun getAllFlashcardsByDefinition(definition: String) =
        flashcards.getAllFlashcards().filter { it.definition == definition }

    fun add() {
        Logger.print("The card:")
        val term = getTerm()
        Logger.print("The definition of the card:")
        val definition = getDefinition()
        flashcards.addFlashcard(Flashcard(term, definition))
        Logger.print("The pair (\"$term\":\"$definition\") has been added.")
    }

    fun remove() {
        Logger.print("Which card?")
        val term = Logger.read()
        val allFlashcardsByTerm = getAllFlashcardsByTerm(term)
        if (allFlashcardsByTerm.isEmpty()) throw RuntimeException("Can't remove \"$term\": there is no such card.")
        flashcards.removeFlashcard(allFlashcardsByTerm.first())
        Logger.print("The card has been removed.")
    }

    fun export() {
        Logger.print("File name:")
        val filename = Logger.read()
        fileHandler.saveFlashcards(flashcards.getAllFlashcards(), filename)
        Logger.print("${flashcards.getAllFlashcards().size} cards have been saved.")
    }

    fun import() {
        Logger.print("File name:")
        val filename = Logger.read()
        val flashcardList = fileHandler.readFlashcards(filename)
        addAll(flashcardList)
        Logger.print("${flashcardList.size} cards have been loaded.")
    }

    private fun addAll(flashcardList: List<Flashcard>) {
        flashcardList.forEach { flashcard ->
            val allFlashcardsByTerm = getAllFlashcardsByTerm(flashcard.term)
            if (allFlashcardsByTerm.isNotEmpty()) {
                flashcards.removeFlashcard(allFlashcardsByTerm.first())
            }
            flashcards.addFlashcard(flashcard)
        }
    }

    fun ask() {
        Logger.print("How many times to ask?")
        val numberOfQuestions = Logger.read().toInt()
        val random = Random()
        for (questionNumber in 1..numberOfQuestions) {
            val flashcard = flashcards.getAllFlashcards()[random.nextInt(0, flashcards.getAllFlashcards().size)]
            Logger.print("Print the definition of \"${flashcard.term}\":")
            val answer = Logger.read()
            val allFlashcardsByDefinition = getAllFlashcardsByDefinition(answer)
            if (allFlashcardsByDefinition.isEmpty()) {
                Logger.print("Wrong. The right answer is \"${flashcard.definition}\".")
                flashcard.errors++
            } else if (allFlashcardsByDefinition.first() == flashcard) {
                Logger.print("Correct!")
            } else if (allFlashcardsByDefinition.first() != flashcard) {
                Logger.print("Wrong. The right answer is \"${flashcard.definition}\", but your definition is correct for \"${allFlashcardsByDefinition.first().term}\".")
                flashcard.errors++
            }
        }
    }

    fun log() {
        Logger.print("File name:")
        fileHandler.saveLogBook(Logger.read())
        println("The log has been saved.")
    }

    fun hardestCard() {
        val allFlashcards = flashcards.getAllFlashcards()
        if (allFlashcards.isEmpty()) {
            Logger.print("There are no cards with errors.")
            return
        }
        val maxError = allFlashcards.maxOf { it.errors }
        if (maxError == 0) {
            Logger.print("There are no cards with errors.")
            return
        }
        val maxErrorFlashcards = allFlashcards.filter { it.errors == maxError }
        if (maxErrorFlashcards.isEmpty()) Logger.print("There are no cards with errors.")
        if (maxErrorFlashcards.size == 1) Logger.print("The hardest card is \"${maxErrorFlashcards.first().term}\". You have $maxError errors answering it.")
        if (maxErrorFlashcards.size > 1) Logger.print("The hardest cards are \"${maxErrorFlashcards.joinToString("\", \"") { it.term }}\". You have $maxError errors answering them.")
    }

    fun resetStats() {
        flashcards.getAllFlashcards().forEach { it.errors = 0 }
        Logger.print("Card statistics have been reset.")
    }

}