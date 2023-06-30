package flashcards

class FlashcardsService {
    private val flashcards = Flashcards()

    fun setCards() {
        println("Input the number of cards:")
        val numberOfCards = readln().toInt()
        for (n in 1..numberOfCards) {
            println("Card #$n:")
            val term = getTerm()
            println("The definition for card #$n:")
            val definition = getDefinition()
            flashcards.addFlashcard(Flashcard(term, definition))
        }
    }

    fun askAllFlashcards() {
        flashcards.getAllFlashcards().forEach {
            println("Print the definition of \"${it.term}\":")
            val answer = readln()
            val allFlashcardsByDefinition = getAllFlashcardsByDefinition(answer)
            if (allFlashcardsByDefinition.isEmpty()) println("Wrong. The right answer is \"${it.definition}\".")
            else if (allFlashcardsByDefinition.first() == it) println("Correct!")
            else if (allFlashcardsByDefinition.first() != it) println("Wrong. The right answer is \"${it.definition}\", but your definition is correct for \"${allFlashcardsByDefinition.first().term}\".")
        }
    }

    private fun getTerm(): String {
        while (true) {
            val term = readln()
            if (getAllFlashcardsByTerm(term).isEmpty()) return term
            println("The term \"$term\" already exists. Try again:")
        }
    }

    private fun getDefinition(): String {
        while (true) {
            val definition = readln()
            if (getAllFlashcardsByDefinition(definition).isEmpty()) return definition
            println("The definition \"$definition\" already exists. Try again:")
        }
    }

    private fun getAllFlashcardsByTerm(term: String) = flashcards.getAllFlashcards().filter { it.term == term }

    private fun getAllFlashcardsByDefinition(definition: String) = flashcards.getAllFlashcards().filter { it.definition == definition }

}