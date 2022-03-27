import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class EncoderTest {
    private val encoder = Encoder()

    // Позитивные тесты

    @TestFactory
    fun encoder_Number_GetCorrectString() = listOf(
        DynamicTest.dynamicTest("encoder_EasyNumber_GetCorrectString") {
            assertEquals("CCCXXVII", encoder.encode("327"))
        },
        DynamicTest.dynamicTest("encoder_NumberOfFours_GetCorrectString") {
            assertEquals("CDXLIV", encoder.encode("444"))
        },
        DynamicTest.dynamicTest("encoder_NumberOfSixes_GetCorrectString") {
            assertEquals("DCLXVI", encoder.encode("666"))
        },
        DynamicTest.dynamicTest("encoder_NumberOfNines_GetCorrectString") {
            assertEquals("CMXCIX", encoder.encode("999"))
        },
        DynamicTest.dynamicTest("encoder_HardNumber_GetCorrectString") {
            assertEquals("MMMDCCCLXXXVIII", encoder.encode("3888"))
        },
        DynamicTest.dynamicTest("encoder_GreaterThanLowerBoundNumber_GetCorrectString") {
            assertEquals("C", encoder.encode("100"))
        },
        DynamicTest.dynamicTest("encoder_LessThanHigherBoundNumber_GetCorrectString") {
            assertEquals("MMMCMXC", encoder.encode("3990"))
        }
    )

    // Негативные тесты

    @Test
    fun encoder_NotEmptyString_ThrowException() {
        val number = "abc"
        val exception = assertThrows<NumberFormatException> {
            encoder.encode(number)
        }
        assertEquals("Provided not a number", exception.message)
    }

    @Test
    fun encoder_EmptyString_ThrowException() {
        val number = ""
        val exception = assertThrows<NumberFormatException> {
            encoder.encode(number)
        }
        assertEquals("Provided not a number", exception.message)
    }

    @Test
    fun encoder_LowerBoundNumber_ThrowException() {
        val number = "0"
        val exception = assertThrows<IllegalArgumentException> {
            encoder.encode(number)
        }
        assertEquals("Number must be greater than 0", exception.message)
    }

    @Test
    fun encoder_LessThanLowerBoundNumber_ThrowException() {
        val number = "-100"
        val exception = assertThrows<IllegalArgumentException> {
            encoder.encode(number)
        }
        assertEquals("Number must be greater than 0", exception.message)
    }

    @Test
    fun encoder_HigherBoundNumber_ThrowException() {
        val number = "4000"
        val exception = assertThrows<IllegalArgumentException> {
            encoder.encode(number)
        }
        assertEquals("Number must be less than 4000", exception.message)
    }

    @Test
    fun encoder_GreaterThanHigherBoundNumber_ThrowException() {
        val number = "10000"
        val exception = assertThrows<IllegalArgumentException> {
            encoder.encode(number)
        }
        assertEquals("Number must be less than 4000", exception.message)
    }
}