package io.github.alexoooo.arc


enum class ArcColor(
    index: Int
) {
    //-----------------------------------------------------------------------------------------------------------------
    Black(0),
    Blue(1),
    Red(2),
    Green(3),
    Yellow(4),
    Gray(5),
    Magenta(6),
    Orange(7),
    Cyan(8),
    Brown(9);


    //-----------------------------------------------------------------------------------------------------------------
    companion object {
        fun ofIndex(index: Int): ArcColor {
            return entries[index]
        }
    }
}