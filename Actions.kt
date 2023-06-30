package flashcards

enum class Actions(val string: String) {
    ADD("add"), REMOVE("remove"), IMPORT("import"), EXPORT("export"), ASK("ask"), EXIT("exit");

    companion object {
        fun getAction(input: String): Actions {
            for (action in Actions.values()) {
                if (action.string == input.lowercase()) return action
            }
            throw RuntimeException("Wrong action $input")
        }
    }
}