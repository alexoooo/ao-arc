package io.github.alexoooo.arc.model


data class ArcExample(
    val input: ArcGrid,
    val output: ArcGrid
) {
    //-----------------------------------------------------------------------------------------------------------------
    companion object {
        private const val inputKey = "\"input\":"
        private const val outputKey = "\"output\":"

        fun ofJsonList(listOfExampleJson: String): List<ArcExample> {
            val withoutWhitespace = listOfExampleJson
                .replace(Regex("\\s+"), "")

            require(withoutWhitespace.startsWith("[") && withoutWhitespace.endsWith("]")) {
                "Expected JSON list: $listOfExampleJson"
            }

            val builder = mutableListOf<ArcExample>()

            var remainder = withoutWhitespace
                .substring(1 ..< withoutWhitespace.length - 1)

            while (remainder.isNotEmpty()) {
                if (builder.isNotEmpty()) {
                    require(remainder.startsWith(",")) {
                        "Expected comma in list: $remainder"
                    }
                    remainder = remainder.substring(1)
                }

                require(remainder.startsWith("{")) {
                    "Expected JSON map: $remainder"
                }
                val endOfExample = remainder.indexOf("}")
                val exampleJson = remainder.substring(0 .. endOfExample)
                val example = ofJson(exampleJson)
                builder.add(example)

                remainder = remainder.substring(endOfExample + 1)
            }

            return builder
        }


        fun ofJson(mapOfInputAndOutput: String): ArcExample {
            val withoutWhitespace = mapOfInputAndOutput
                .replace(Regex("\\s+"), "")

            require(withoutWhitespace.startsWith("{") && withoutWhitespace.endsWith("}")) {
                "Expected JSON map: $withoutWhitespace"
            }

            val startOfInput = withoutWhitespace.indexOf(inputKey)
            require(startOfInput >= 0) {
                "\"input\" key expected in JSON map: $withoutWhitespace"
            }

            val startOfOutput = withoutWhitespace.indexOf(outputKey)
            require(startOfOutput >= 0) {
                "\"output\" key expected in JSON map: $withoutWhitespace"
            }

            if (startOfOutput < startOfInput) {
                TODO("\"input\" key after \"output\" key not supported (yet)")
            }

            val inputJson = withoutWhitespace
                .substring((startOfInput + inputKey.length) ..< startOfOutput - 1)
            val input = ArcGrid.ofJson(inputJson)

            val outputJson = withoutWhitespace
                .substring((startOfOutput + outputKey.length) ..< withoutWhitespace.length - 1)
            val output = ArcGrid.ofJson(outputJson)

            return ArcExample(input, output)
        }
    }

    //-----------------------------------------------------------------------------------------------------------------
    fun toJson(): String {
        return """
            {"input": ${input.toJson()}, "output": ${output.toJson()}}
        """.trimIndent()
    }
}