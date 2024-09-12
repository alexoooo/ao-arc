package io.github.alexoooo.arc.generate

import io.github.alexoooo.arc.model.*
import kotlin.random.Random
import kotlin.random.nextInt


class GeometryArcGenerator(
    private val random: Random
): ArcGenerator {
    //-----------------------------------------------------------------------------------------------------------------
    @Suppress("ConstPropertyName")
    companion object {
        private const val exampleAttemptLimit = 100
        private val dimensions = ArcDimensions(9, 9)
    }


    //-----------------------------------------------------------------------------------------------------------------
    override fun generate(): ArcTask {
        while (true) {
            val arcTask = tryGenerate()
            if (arcTask != null) {
                return arcTask
            }
        }
    }


    private fun tryGenerate(): ArcTask? {
        val offsetRows = random.nextInt(-5 .. 5)
        val offsetColumns = random.nextInt(-5 .. 5)

        if (offsetRows == 0 && offsetColumns == 0) {
            return null
        }

        var attemptCount = 0
        val uniqueExamples = mutableSetOf<ArcExample>()
        while (uniqueExamples.size < 4 && attemptCount < exampleAttemptLimit) {
            val randomExample = tryGenerateExample(offsetRows, offsetColumns)
            if (randomExample != null) {
                uniqueExamples.add(randomExample)
            }
            attemptCount++
        }

        if (uniqueExamples.size < 4) {
            return null
        }

        val iterator = uniqueExamples.iterator()
        return ArcTask(
            listOf(iterator.next(), iterator.next(), iterator.next()),
            listOf(iterator.next()))
    }


    private fun tryGenerateExample(
        offsetRows: Int,
        offsetColumns: Int
    ): ArcExample? {
        val builder = ArcGridBuilder()

        val objectTopLeftRow = ArcCoordinate(random.nextInt(0, dimensions.rows))
        val objectTopLeftColumn = ArcCoordinate(random.nextInt(0, dimensions.columns))

        val objectWidth = random.nextInt(1, dimensions.rows / 2)
        val objectHeight = random.nextInt(1, dimensions.columns / 2)

        val color = ArcColor.ofIndex(random.nextInt(1, ArcColor.count))

        val filled = builder.fill(
            ArcCell(objectTopLeftRow, objectTopLeftColumn),
            objectWidth,
            objectHeight,
            color)
        if (! filled) {
            return null
        }

        val input = builder.build(dimensions = dimensions)
        val inputColouredCount = input.cells.sumOf { row -> row.count { it == color } }
        if (inputColouredCount != (objectWidth * objectHeight)) {
            return null
        }

        if (offsetColumns > 0) {
            builder.shiftRight(offsetColumns)
        }
        else if (offsetColumns < 0) {
            builder.shiftLeft(-offsetColumns)
        }

        if (offsetRows > 0) {
            builder.shiftDown(offsetRows)
        }
        else if (offsetRows < 0) {
            builder.shiftUp(-offsetRows)
        }

        val output = builder.build(dimensions = input.dimensions)
        val outputColouredCount = output.cells.sumOf { row -> row.count { it == color } }
        if (outputColouredCount != inputColouredCount) {
            return null
        }

        return ArcExample(input, output)
    }


    private fun ArcGridBuilder.fill(
        topLeft: ArcCell,
        rowCount: Int,
        columnCount: Int,
        color: ArcColor
    ): Boolean {
        val cells = mutableListOf<ArcCell>()

        for (row in 0 ..< rowCount) {
            for (col in 0 ..< columnCount) {
                val rowIndex = topLeft.row.index + row
                val columnIndex = topLeft.column.index + col

                if (! ArcCoordinate.inRange(rowIndex) || ! ArcCoordinate.inRange(columnIndex)) {
                    return false
                }

                cells.add(ArcCell(ArcCoordinate(rowIndex), ArcCoordinate(columnIndex)))
            }
        }

        for (cell in cells) {
            set(cell, color)
        }
        return true
    }
}