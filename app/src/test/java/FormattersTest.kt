
import com.r4s.igt.utils.formatCurrency
import com.r4s.igt.utils.formatDate
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.util.*

/** Created by sebastian with ♥ on 18.10.2018 **/
@RunWith(RobolectricTestRunner::class)
class FormattersTest {

    @Test
    fun formatDate() {
        Locale.setDefault(Locale("gb", "GB"))
        var date = "04/05/2016T16:45"

        var priceformated = date.formatDate("dd/MM/yyyy'T'HH:mm")
        val expected = "May 04, 2016 16:45:00"
        assertEquals("Conversion of date", expected, priceformated)
    }

    @Test
    fun testConvertPrices() {
        Locale.setDefault(Locale ("pl", "PL"))
        var price = 500.toLong()
        var priceformated = price.formatCurrency("GBP")
        val expected = "500 GBP"
        assertEquals("Conversion of price", expected, priceformated)
    }
    @Test
    fun testConvertBigPrices() {
        Locale.setDefault(Locale ("pl", "PL"))
        var price = 500000.toLong()
        var priceformated = price.formatCurrency("GBP")
        val expected = "500 000 GBP"
        assertEquals("Conversion of price", expected, priceformated)
    }

    @Test
    fun testConvertGbPrices() {
        Locale.setDefault(Locale ("gb", "GB"))
        var price = 500.toLong()
        var priceformated = price.formatCurrency("GBP")
        val expected = "GBP 500.00"
        assertEquals("Conversion of price", expected, priceformated)
    }

    @Test
    fun testConvertBigGbPrices() {
        Locale.setDefault(Locale ("gb", "GB"))
        var price = 500000.toLong()
        var priceformated = price.formatCurrency("GBP")
        val expected = "GBP 500,000.00"
        assertEquals("Conversion of price", expected, priceformated)
    }



}