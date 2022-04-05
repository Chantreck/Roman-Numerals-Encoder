package com.tsu.roman_encoder

class Encoder {
    private val romanNumbers = mapOf(
        1000 to "M", 900 to "CM", 500 to "D", 400 to "CD",
        100 to "C", 90 to "XC", 50 to "L", 40 to "XL",
        10 to "X", 9 to "IX", 5 to "V", 4 to "IV", 1 to "I"
    )
    private var counter = romanNumbers.mapValues { 0 }.toMutableMap()

    fun encode(num: String): String {
        try {
            num.toInt()
        } catch (e: NumberFormatException) {
            throw NumberFormatException("Provided not a number")
        }

        var number = num.toInt()

        if (number < 1) {
            throw IllegalArgumentException("Number must be greater than 0")
        } else if (number >= 4000) {
            throw IllegalArgumentException("Number must be less than 4000")
        }

        for (key in counter.keys) {
            val div = number / key
            counter[key] = div
            number -= key * div
        }

        var result = ""
        for (entry in counter.entries) {
            result += romanNumbers[entry.key]?.repeat(entry.value)
        }

        return result
    }
}