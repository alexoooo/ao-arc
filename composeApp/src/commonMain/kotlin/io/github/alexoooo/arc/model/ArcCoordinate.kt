package io.github.alexoooo.arc.model


data class ArcCoordinate(
    val index: Int
) {
    //-----------------------------------------------------------------------------------------------------------------
    init {
        require(index in 0..<ArcDimensions.max) {
            "Must be in range [0 ..< ${ArcDimensions.max}]: $index"
        }
    }
}
