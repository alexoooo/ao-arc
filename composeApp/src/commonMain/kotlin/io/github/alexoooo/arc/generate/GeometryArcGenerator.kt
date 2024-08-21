package io.github.alexoooo.arc.generate

import io.github.alexoooo.arc.model.*
import kotlin.random.Random
import kotlin.random.nextInt


class GeometryArcGenerator(
    private val random: Random
): ArcGenerator {
    override fun generate(): ArcTask {
        val offsetRows = random.nextInt(1 .. 5)
        val offsetColumns = random.nextInt(1 .. 5)

        val uniqueExamples = mutableSetOf<ArcExample>()
        while (uniqueExamples.size < 4) {
            val randomExample = generateExample(offsetRows, offsetColumns)
            uniqueExamples.add(randomExample)
        }

        val iterator = uniqueExamples.iterator()
        return ArcTask(
            listOf(iterator.next(), iterator.next(), iterator.next()),
            listOf(iterator.next()))
    }


    fun generateExample(
        offsetRows: Int,
        offsetColumns: Int
    ): ArcExample {
        val builder = ArcGridBuilder()

        val objectRows = random.nextInt(1, 4)
        val objectColumns = random.nextInt(1, 4)

        for (row in 1 .. objectRows) {
            for (col in 1 .. objectColumns) {
                builder.set(ArcCoordinate(row), ArcCoordinate(col), ArcColor.ofIndex(1))
            }
        }

        val input = builder.build(dimensions = ArcDimensions(9, 9))

        builder.shiftRight(offsetColumns)
        builder.shiftDown(offsetRows)

        val output = builder.build(dimensions = input.dimensions)

        return ArcExample(input, output)
    }
}