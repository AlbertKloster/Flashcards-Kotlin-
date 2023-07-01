package flashcards

val flashcardsService = FlashcardRepositoryService()

fun main(args: Array<String>) {

    val importFilename = getImportFilename(args)
    if (importFilename != null) {
        import(importFilename)
    }

    val exportFilename = getExportFilename(args)
    if (exportFilename != null) {
        export(exportFilename)
    }

    var exit = false
    while (!exit) {
        Logger.print("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):")
        try {
            when (Actions.getAction(Logger.read())) {
                Actions.ADD -> add()
                Actions.REMOVE -> remove()
                Actions.IMPORT -> import(null)
                Actions.EXPORT -> export(null)
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
    if (exportFilename != null) export(exportFilename)
}

fun getImportFilename(args: Array<String>): String? {
    val indexOfImport = args.indexOf("-import")
    if (indexOfImport < 0) return null
    if (args.size > indexOfImport + 1) return args[indexOfImport + 1]
    return null
}

fun getExportFilename(args: Array<String>): String? {
    val indexOfExport = args.indexOf("-export")
    if (indexOfExport < 0) return null
    if (args.size > indexOfExport + 1) return args[indexOfExport + 1]
    return null
}

private fun add() {
    flashcardsService.add()
}

private fun remove() {
    flashcardsService.remove()
}

private fun import(importFilename: String?) {
    flashcardsService.import(importFilename)
}

private fun export(exportFilename: String?) {
    flashcardsService.export(exportFilename)
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