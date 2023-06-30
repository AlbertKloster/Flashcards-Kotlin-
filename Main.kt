package flashcards

fun main() {
    val flashcard = Flashcard(readln(), readln())
    println("Your answer is  ${if (readln() == flashcard.definition) "right!" else "wrong..."}")
}
