package io.github.alexoooo.arc


data class ArcGrid(
    val dimensions: ArcDimensions,
    val cells: List<List<ArcColor>>
) {
    //-----------------------------------------------------------------------------------------------------------------
    companion object {
        fun ofJson(arrayOfArraysOfPixelIndexes: String): ArcGrid {
            val withoutWhitespace = arrayOfArraysOfPixelIndexes
                .replace(Regex("\\s+"), "")

            require(withoutWhitespace.startsWith("[[") && withoutWhitespace.endsWith("]]")) {
                "Two-dimensional array expected (list of list of int): $arrayOfArraysOfPixelIndexes"
            }

            val insideOuterArray = withoutWhitespace.substring(1, withoutWhitespace.length - 1)
            require(insideOuterArray.matches(Regex("""\[\d+(,\d+)+](,\[\d+(,\d+)+])+"""))) {
                "Arrays of int expected inside outer array: $insideOuterArray"
            }

            val withoutOpeningFirstAndClosingLast = insideOuterArray.substring(1, insideOuterArray.length - 1)

            val innerArrayJson = withoutOpeningFirstAndClosingLast.split("],[")

            val cellsJson: List<List<String>> = innerArrayJson
                .map { it.split(",") }

            val cellsColor = cellsJson.map { row -> row.map { it.toInt() } }
            require(cellsColor.isNotEmpty())
            require(cellsColor[0].isNotEmpty()) {
                "At least one column required (top): $arrayOfArraysOfPixelIndexes"
            }

            val cells = cellsColor
                .map { row -> row.map { ArcColor.ofIndex(it) } }

            val dimensions = ArcDimensions(
                cells.size,
                cells.maxOf { it.size }
            )

            return ArcGrid(dimensions, cells)
        }
    }


    //-----------------------------------------------------------------------------------------------------------------
    init {
        require(cells.size == dimensions.rows) {
            "Unexpected number of rows: ${dimensions.rows} expected, but ${cells.size} provided"
        }
        require(cells[0].size == dimensions.columns) {
            "Unexpected number of columns: ${dimensions.columns} expected, but ${cells[0].size} provided"
        }
        require(cells.all { it.size == dimensions.columns }) {
            "Uneven number of columns: ${cells.map { it.size }}"
        }
    }


    //-----------------------------------------------------------------------------------------------------------------
    fun get(rowIndex: ArcCoordinate, columnIndex: ArcCoordinate): ArcColor {
        require(rowIndex.index < dimensions.rows) {
            "Row index '$rowIndex' must be in: [0 .. ${dimensions.rows - 1}]"
        }
        require(columnIndex.index < dimensions.columns) {
            "Column index '$rowIndex' must be in: [0 .. ${dimensions.columns - 1}]"
        }
        return cells[rowIndex.index][columnIndex.index]
    }
}
