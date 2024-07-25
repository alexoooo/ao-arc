package io.github.alexoooo.arc

import kotlin.test.Test
import kotlin.test.assertEquals


class ArcGridTest {
    @Test
    fun twoByTwo() {
        val json = "[[0,1],[1,0]]"
        val grid = ArcGrid.ofJson(json)

        assertEquals(
            ArcGrid(
                ArcDimensions(2, 2),
                listOf(
                    listOf(ArcColor.ofIndex(0), ArcColor.ofIndex(1)),
                    listOf(ArcColor.ofIndex(1), ArcColor.ofIndex(0)))
            ),
            grid
        )
    }


    @Test
    fun twoByThree() {
        val json = "[[0,1,2],[1,0,2]]"
        val grid = ArcGrid.ofJson(json)

        assertEquals(
            ArcGrid(
                ArcDimensions(2, 3),
                listOf(
                    listOf(ArcColor.ofIndex(0), ArcColor.ofIndex(1), ArcColor.ofIndex(2)),
                    listOf(ArcColor.ofIndex(1), ArcColor.ofIndex(0), ArcColor.ofIndex(2)))
            ),
            grid
        )
    }


    @Test
    fun threeByThree() {
        val json = "[[0,1,2],[1,0,2],[2,0,1]]"
        val grid = ArcGrid.ofJson(json)

        assertEquals(
            ArcGrid(
                ArcDimensions(3, 3),
                listOf(
                    listOf(ArcColor.ofIndex(0), ArcColor.ofIndex(1), ArcColor.ofIndex(2)),
                    listOf(ArcColor.ofIndex(1), ArcColor.ofIndex(0), ArcColor.ofIndex(2)),
                    listOf(ArcColor.ofIndex(2), ArcColor.ofIndex(0), ArcColor.ofIndex(1)))
            ),
            grid
        )
    }
}