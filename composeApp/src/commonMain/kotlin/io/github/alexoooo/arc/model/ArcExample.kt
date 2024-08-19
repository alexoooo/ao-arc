package io.github.alexoooo.arc.model


data class ArcExample(
    val input: ArcGrid,
    val output: ArcGrid
) {
    fun toJson(): String {
        return """
            {"input": ${input.toJson()}, "output": ${output.toJson()}}
        """.trimIndent()
    }
}