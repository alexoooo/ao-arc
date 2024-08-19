package io.github.alexoooo.arc.generate

import io.github.alexoooo.arc.model.*
import kotlin.random.Random


class RandomArcGenerator(
    seed: Long
): ArcGenerator {
    private val random = Random(seed)


    override fun generate(): ArcTask {
        val trainCount = random.nextInt(3, 6)
        val train = (1 .. trainCount).map { randomExample() }
        val test = listOf(randomExample())
        return ArcTask(train, test)
    }


    private fun randomExample(): ArcExample {
        return ArcExample(randomGrid(), randomGrid())
    }


    private fun randomGrid(): ArcGrid {
        val dimensions = ArcDimensions(
            randomDimension(), randomDimension())

        val rows =
            (1 .. dimensions.rows).map {
                (1 .. dimensions.columns).map {
                    randomColor()
                }
            }

        return ArcGrid(dimensions, rows)
    }


    private fun randomDimension(): Int {
        return 1 + random.nextInt(ArcDimensions.max)
    }


    private fun randomColor(): ArcColor {
        val randomColorIndex = random.nextInt(ArcColor.count)
        return ArcColor.ofIndex(randomColorIndex)
    }
}