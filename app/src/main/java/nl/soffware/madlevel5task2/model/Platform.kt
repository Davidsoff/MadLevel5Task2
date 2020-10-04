package nl.soffware.madlevel5task2.model

enum class Platform(val title: String) {
    PC("PC"),
    PS4("Playstation 4"),
    PS5("Playstation 5"),
    SWITCH("Nintendo Switch"),
    WII("Nintendo Wii"),
    XBOX_X("Xbox Series X");

    override fun toString(): String {
        return title
    }
}
