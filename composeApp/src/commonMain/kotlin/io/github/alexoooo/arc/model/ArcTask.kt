package io.github.alexoooo.arc.model


data class ArcTask(
    val train: List<ArcExample>,
    val test: List<ArcExample>
) {
    //-----------------------------------------------------------------------------------------------------------------
    @Suppress("ConstPropertyName")
    companion object {
        private const val trainKey = "\"train\":"
        private const val testKey = "\"test\":"


        fun ofJson(mapOfTrainAndTest: String): ArcTask {
            val withoutWhitespace = mapOfTrainAndTest
                .replace(Regex("\\s+"), "")

            require(withoutWhitespace.startsWith("{") && withoutWhitespace.endsWith("}")) {
                "Must start with '{' and end with '}': $withoutWhitespace"
            }

            val startOfTrain = withoutWhitespace.indexOf(trainKey)
            require(startOfTrain >= 0) {
                "Must contain \"train\" key: $withoutWhitespace"
            }

            val startOfTest = withoutWhitespace.indexOf(testKey)
            require(startOfTest >= 0) {
                "Must contain \"test\" key: $withoutWhitespace"
            }

            if (startOfTest < startOfTrain) {
                TODO("\"train\" key after \"test\" key not supported (yet)")
            }

            val trainListJson = withoutWhitespace
                .substring((startOfTrain + trainKey.length) ..< startOfTest - 1)
            val train = ArcExample.ofJsonList(trainListJson)

            val testListJson = withoutWhitespace
                .substring((startOfTest + testKey.length)..< withoutWhitespace.length - 1)
            val test = ArcExample.ofJsonList(testListJson)

            return ArcTask(train, test)
        }
    }

    //-----------------------------------------------------------------------------------------------------------------
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