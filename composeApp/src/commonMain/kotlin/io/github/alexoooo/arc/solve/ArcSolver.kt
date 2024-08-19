package io.github.alexoooo.arc.solve

import io.github.alexoooo.arc.model.ArcExample
import io.github.alexoooo.arc.model.ArcGrid


interface ArcSolver {
    fun solve(
        train: List<ArcExample>,
        testInput: ArcGrid
    ): List<ArcGrid>
}