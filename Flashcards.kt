package flashcards

class Flashcards {
    private val flashcards = mutableListOf<Flashcard>()

    fun addFlashcard(flashcard: Flashcard) {
        flashcards.add(flashcard)
    }

    fun removeFlashcard(flashcard: Flashcard) {
        flashcards.remove(flashcard)
    }

    fun getAllFlashcards() = flashcards

}