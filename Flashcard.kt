package flashcards

data class Flashcard(val term: String, val definition: String, var errors: Int = 0)