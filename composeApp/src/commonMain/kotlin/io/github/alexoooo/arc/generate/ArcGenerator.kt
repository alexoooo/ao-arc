package io.github.alexoooo.arc.generate

import io.github.alexoooo.arc.model.ArcTask


interface ArcGenerator {
    fun generate(): ArcTask
}