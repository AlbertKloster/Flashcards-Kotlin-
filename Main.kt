package flashcards

val flashcardsService = FlashcardsService()

fun main() {

    var exit = false
    while (!exit) {
        println("Input the action (add, remove, import, export, ask, exit):")
        try {
            when (Actions.getAction(readln())) {
                Actions.ADD -> add()
                Actions.REMOVE -> remove()
                Actions.IMPORT -> import()
                Actions.EXPORT -> export()
                Actions.ASK -> ask()
                Actions.EXIT -> exit = true
            }
        } catch (e: RuntimeException) {
            println(e.message)
        }
    }
    println("Bye bye!")
}

private fun add() {
    flashcardsService.add()
}

private fun remove() {
    flashcardsService.remove()
}

private fun import() {
    flashcardsService.import()
}

private fun export() {
    flashcardsService.export()
}

private fun ask() {
    flashcardsService.ask()
}