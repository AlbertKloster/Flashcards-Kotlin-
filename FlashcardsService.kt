package flashcards

import java.util.*

class FlashcardsService {
    private val flashcards = Flashcards()
    private val fileHandler = FileHandler()

    private fun getTerm(): String {
        val term = readln()
        if (getAllFlashcardsByTerm(term).isEmpty()) return term
        throw RuntimeException("The card \"$term\" already exists.")
    }

    private fun getDefinition(): String {
        val definition = readln()
        if (getAllFlashcardsByDefinition(definition).isEmpty()) return definition
        throw RuntimeException("The definition \"$definition\" already exists.")
    }

    private fun getAllFlashcardsByTerm(term: String) = flashcards.getAllFlashcards().filter { it.term == term }

    private fun getAllFlashcardsByDefinition(definition: String) =
        flashcards.getAllFlashcards().filter { it.definition == definition }

    fun add() {
        println("The card:")
        val term = getTerm()
        println("The definition of the card:")
        val definition = getDefinition()
        flashcards.addFlashcard(Flashcard(term, definition))
        println("The pair (\"$term\":\"$definition\") has been added.")
    }

    fun remove() {
        println("Which card?")
        val term = readln()
        val allFlashcardsByTerm = getAllFlashcardsByTerm(term)
        if (allFlashcardsByTerm.isEmpty()) throw RuntimeException("Can't remove \"$term\": there is no such card.")
        flashcards.removeFlashcard(allFlashcardsByTerm.first())
        println("The card has been removed.")
    }

    fun export() {
        println("File name:")
        val filename = readln()
        fileHandler.write(flashcards.getAllFlashcards(), filename)
        println("${flashcards.getAllFlashcards().size} cards have been saved.")
    }

    fun import() {
        println("File name:")
        val filename = readln()
        val flashcardList = fileHandler.read(filename)
        addAll(flashcardList)
        println("${flashcardList.size} cards have been loaded.")
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
        println("How many times to ask?")
        val numberOfQuestions = readln().toInt()
        val random = Random()
        for (questionNumber in 1..numberOfQuestions) {
            val flashcard = flashcards.getAllFlashcards()[random.nextInt(0, flashcards.getAllFlashcards().size)]
            println("Print the definition of \"${flashcard.term}\":")
            val answer = readln()
            val allFlashcardsByDefinition = getAllFlashcardsByDefinition(answer)
            if (allFlashcardsByDefinition.isEmpty()) println("Wrong. The right answer is \"${flashcard.definition}\".")
            else if (allFlashcardsByDefinition.first() == flashcard) println("Correct!")
            else if (allFlashcardsByDefinition.first() != flashcard) println("Wrong. The right answer is \"${flashcard.definition}\", but your definition is correct for \"${allFlashcardsByDefinition.first().term}\".")
        }
    }


}