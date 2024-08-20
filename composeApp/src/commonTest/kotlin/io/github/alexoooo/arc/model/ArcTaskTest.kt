package io.github.alexoooo.arc.model

import kotlin.test.Test
import kotlin.test.assertEquals


class ArcTaskTest {
    @Test
    fun parseJson() {
        val json = """{
  "train": [
    {"input": [[1, 0], [0, 0]], "output": [[1, 1], [1, 1]]},
    {"input": [[0, 0], [4, 0]], "output": [[4, 4], [4, 4]]},
    {"input": [[0, 0], [6, 0]], "output": [[6, 6], [6, 6]]}
  ],
  "test": [
    {"input": [[0, 0], [0, 8]], "output": [[8, 8], [8, 8]]}
  ]
}"""
        val task = ArcTask.ofJson(json)

        assertEquals(3, task.train.size)
        assertEquals(1, task.test.size)
    }
}