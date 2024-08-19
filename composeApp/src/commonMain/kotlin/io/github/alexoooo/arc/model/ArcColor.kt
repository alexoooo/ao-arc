package io.github.alexoooo.arc.model


enum class ArcColor(
    val index: Int,
    val argbColorCode: Long
) {
    //-----------------------------------------------------------------------------------------------------------------
    Black(0, 0xFF000000),
    Blue(1, 0xFF1E93FF),
    Red(2, 0xFFF93C31),
    Green(3, 0xFF4FCC30),
    Yellow(4, 0xFFFFDC00),
    Gray(5, 0xFF999999),
    Magenta(6, 0xFFE53AA3),
    Orange(7, 0xFFFF851B),
    Cyan(8, 0xFF87D8F1),
    Maroon(9, 0xFF921231);


    //-----------------------------------------------------------------------------------------------------------------
    companion object {
        val count = ArcColor.entries.size

        fun ofIndex(index: Int): ArcColor {
            return entries[index]
        }
    }
}