package flashcards

class FlashcardsService {
    val flashcards = Flashcards()

    fun setCards() {
        println("Input the number of cards:")
        val numberOfCards = readln().toInt()
        for (n in 1..numberOfCards) {
            println("Card #$n:")
            val term = readln()
            println("The definition for card #$n:")
            val definition = readln()
            flashcards.addFlashcard(Flashcard(term, definition))
        }
    }

    fun askAllFlashcards() {
        flashcards.getAllFlashcards().forEach {
            println("Print the definition of \"${it.term}\":")
            val answer = readln()
            println(if (answer == it.definition) "Correct!" else "Wrong. The right answer is \"${it.definition}\".")
        }
    }

}