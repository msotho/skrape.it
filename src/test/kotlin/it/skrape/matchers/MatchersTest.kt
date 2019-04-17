package it.skrape.matchers

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.opentest4j.AssertionFailedError


internal class MatchersTest {

    private val anInt = 200
    private val aString = "test"
    private val aNullableString: String? = null

    @Test
    internal fun `toBe is working type safe for Int`() {
        anInt toBe 200
        anInt `to be` 200
    }

    @Test
    internal fun `toBe is working type safe for String`() {
        aString toBe "test"
        aString `to be` "test"
    }

    @Test
    internal fun `toBe can handle expected is null`() {
        Assertions.assertThrows(AssertionFailedError::class.java) {
            "null" toBe null
            "null" `to be` null
        }
    }

    @Test
    internal fun `toBe can handle actual is null`() {
        Assertions.assertThrows(AssertionFailedError::class.java) {
            aNullableString toBe "foo"
            aNullableString `to be` "foo"
        }
    }

    @Test
    internal fun `toBe is working type safe for null String`() {
        aNullableString toBe null
        aNullableString `to be` null
    }

    @Test
    internal fun `toNotBe is working type safe for null String`() {
        aString toBeNot null
        aString `to be not` null
    }

    @Test
    internal fun `toContain is working type safe for String`() {
        aString toContain "es"
        aString `to contain` "es"
    }

    @Test
    internal fun `toContain is throwing exception on assertion failure`() {
        Assertions.assertThrows(AssertionError::class.java) {
            aString toContain "foo"
            aString `to contain` "foo"
        }
    }

    @Test
    internal fun `toNotContain is working type safe for String`() {
        aString toNotContain "foo"
        aString `to not contain` "foo"
    }

    @Test
    internal fun `toNotContain is throwing exception on assertion failure`() {
        Assertions.assertThrows(AssertionError::class.java) {
            aString toNotContain "es"
            aString `to not contain` "es"
        }
    }

    @Test
    internal fun `toContain is working with lists`() {
        listOf("foo", "bar") toContain "bar"
        listOf("foo", "bar") `to contain` "bar"
    }

    @Test
    internal fun `toContain on lists is throwing exception on assertion failure`() {
        Assertions.assertThrows(AssertionError::class.java) {
            listOf("foo", "bar") toContain "schnitzel"
            listOf("foo", "bar") `to contain` "schnitzel"
        }
    }

    @Test
    internal fun `isPresent can handle multiple presents of matching ELEMENTS`() {
        val elements = mock<Elements> { on { size } doReturn 2 }
        elements.toBePresent()
    }

    @Test
    internal fun `isPresent can handle single occurrence of matching ELEMENTS`() {
        val elements = mock<Elements> { on { size } doReturn 1 }
        elements.toBePresent()
    }

    @Test
    internal fun `isPresent is throwing exception if no ELEMENTS matches`() {
        val elements = mock<Elements> { on { size } doReturn 0 }
        Assertions.assertThrows(AssertionFailedError::class.java) {
            elements.toBePresent()
        }
    }

    @Test
    internal fun `isPresent can handle multiple occurrence of an ELEMENT`() {
        val elements = mock<Elements> { on { size } doReturn 2 }
        val element = mock<Element> { on { allElements } doReturn elements }
        element.toBePresent()
    }

    @Test
    internal fun `isPresent can handle single occurrence of an ELEMENT`() {
        val elements = mock<Elements> { on { size } doReturn 1 }
        val element = mock<Element> { on { allElements } doReturn elements }
        element.toBePresent()
    }

    @Test
    internal fun `isPresent is throwing exception if no ELEMENT matches`() {
        val elements = mock<Elements> { on { size } doReturn 0 }
        val element = mock<Element> { on { allElements } doReturn elements }
        Assertions.assertThrows(AssertionFailedError::class.java) {
            element.toBePresent()
        }
    }
}
