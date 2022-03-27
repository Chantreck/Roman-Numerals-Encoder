import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class EncoderTest {
    private val encoder = Encoder()

    // Позитивные тесты
    
    @TestFactory
    fun encoder_EncodeNumber_GetCorrectString() = listOf(
        DynamicTest.dynamicTest("encoder_EncodeEasyNumber_GetCorrectString") {
            assertEquals("CCCXXVII", encoder.encode("327"))
        },
        DynamicTest.dynamicTest("encoder_EncodeFours_GetCorrectString") {
            assertEquals("CDXLIV", encoder.encode("444"))
        },
        DynamicTest.dynamicTest("encoder_EncodeSixes_GetCorrectString") {
            assertEquals("DCLXVI", encoder.encode("666"))
        },
        DynamicTest.dynamicTest("encoder_EncodeNines_GetCorrectString") {
            assertEquals("CMXCIX", encoder.encode("999"))
        },
        DynamicTest.dynamicTest("encoder_EncodeHardNumber_GetCorrectString") {
            assertEquals("MMMDCCCLXXXVIII", encoder.encode("3888"))
        },
        DynamicTest.dynamicTest("encoder_EncodeLittleNumber_GetCorrectString") {
            assertEquals("I", encoder.encode("1"))
        },
        DynamicTest.dynamicTest("encoder_EncodeBigNumber_GetCorrectString") {
            assertEquals("MMMCMXCIX", encoder.encode("3999"))
        }
    )

    // Негативные тесты

    @Test
    fun encoder_EncodeString_ThrowException() {
        val number = "abc"
        val exception = assertThrows<NumberFormatException> {
            encoder.encode(number)
        }
        assertEquals("Provided not a number", exception.message)
    }

    @Test
    fun encoder_EncodeEmptyString_ThrowException() {
        val number = ""
        val exception = assertThrows<NumberFormatException> {
            encoder.encode(number)
        }
        assertEquals("Provided not a number", exception.message)
    }

    @Test
    fun encoder_EncodeTooLittleNumber_ThrowException() {
        val number = "0"
        val exception = assertThrows<IllegalArgumentException> {
            encoder.encode(number)
        }
        assertEquals("Number must be greater than 0", exception.message)
    }

    @Test
    fun encoder_EncodeTooBigNumber_ThrowException() {
        val number = "4000"
        val exception = assertThrows<IllegalArgumentException> {
            encoder.encode(number)
        }
        assertEquals("Number must be less than 4000", exception.message)
    }
}