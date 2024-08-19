package io.github.alexoooo.arc.model


data class ArcTask(
    val train: List<ArcExample>,
    val test: List<ArcExample>
) {
    fun toJson(): String {
        val trainJson = train.joinToString(", \n    ") { i -> i.toJson() }
        val testJson = test.joinToString(", \n    ") { i -> i.toJson() }

        return (
"""{
  "train": [
    $trainJson
  ],
  "test": [
    $testJson
  ]
}""")
    }
}