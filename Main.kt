package flashcards

val flashcardsService = FlashcardsService()

fun main() {

    var exit = false
    while (!exit) {
        Logger.print("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):")
        try {
            when (Actions.getAction(Logger.read())) {
                Actions.ADD -> add()
                Actions.REMOVE -> remove()
                Actions.IMPORT -> import()
                Actions.EXPORT -> export()
                Actions.ASK -> ask()
                Actions.EXIT -> exit = true
                Actions.LOG -> log()
                Actions.HARDEST_CARD -> hardestCard()
                Actions.RESET_STATS -> resetStats()
            }
        } catch (e: RuntimeException) {
            Logger.print(e.message ?: "")
        }
    }
    Logger.print("Bye bye!")
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

private fun log() {
    flashcardsService.log()
}

private fun hardestCard() {
    flashcardsService.hardestCard()
}

private fun resetStats() {
    flashcardsService.resetStats()
}