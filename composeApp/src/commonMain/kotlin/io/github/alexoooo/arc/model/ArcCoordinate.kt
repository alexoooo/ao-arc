package io.github.alexoooo.arc.model

import kotlin.jvm.JvmInline


@JvmInline
value class ArcCoordinate(
    val index: Int
) {
    //-----------------------------------------------------------------------------------------------------------------
    companion object {
        fun inRange(index: Int): Boolean {
            return index in 0..< ArcDimensions.max
        }
    }


    //-----------------------------------------------------------------------------------------------------------------
    init {
        require(inRange(index)) {
            "Must be in range [0 ..< ${ArcDimensions.max}]: $index"
        }
    }
}
