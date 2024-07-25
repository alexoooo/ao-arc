package io.github.alexoooo.arc


data class ArcDimensions(
    val rows: Int,
    val columns: Int
) {
    //-----------------------------------------------------------------------------------------------------------------
    companion object {
        @Suppress("ConstPropertyName")
        const val max = 30
    }


    //-----------------------------------------------------------------------------------------------------------------
    init {
        require(rows in 1..max) {
            "Rows must be in [1, 30]: $rows"
        }

        require(columns in 1..max) {
            "Columns must be in [1, 30]: $columns"
        }
    }
}
