import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class EncoderTest {
    private val encoder = Encoder()

    @Test
    fun encoder_EncodeEasyNumber_GetCorrectString() {
        val number = "327"
        val result = encoder.encode(number)
        assertEquals("CCCXXVII", result)
    }

    @Test
    fun encoder_EncodeFours_GetCorrectString() {
        val number = "444"
        val result = encoder.encode(number)
        assertEquals("CDXLIV", result)
    }

    @Test
    fun encoder_EncodeSixes_GetCorrectString() {
        val number = "666"
        val result = encoder.encode(number)
        assertEquals("DCLXVI", result)
    }

    @Test
    fun encoder_EncodeNines_GetCorrectString() {
        val number = "999"
        val result = encoder.encode(number)
        assertEquals("CMXCIX", result)
    }

    @Test
    fun encoder_EncodeHardNumber_GetCorrectString() {
        val number = "3888"
        val result = encoder.encode(number)
        assertEquals("MMMDCCCLXXXVIII", result)
    }

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