package com.gluehome.common.domain

import com.gluehome.common.domain.framework.DateMapper
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test

class DateMapperTest {

    lateinit var cut: DateMapper

    @Before
    fun setUp() {
        cut = DateMapper()
    }

    @Test
    fun `when timestamp is given a format, it should return the right string conversion`() {
        // given
        val dateStr = "22/08/2018"

        // when
        val stringDate = cut.formatDate(cut.toDayMonthYearTimestamp(dateStr), "EEEE, MMMM d")

        // then
        "Wednesday, August 22" shouldBeEqualTo stringDate
    }

    @Test
    fun `when timestamp for 22nd is given, it should return a friendly string conversion`() {
        // given
        val dateStr = "22/08/2018"

        // when
        val stringDate = cut.dateToCardHeaderTitle(cut.toDayMonthYearTimestamp(dateStr))

        // then
        "Wednesday, August 22nd" shouldBeEqualTo stringDate
    }

    @Test
    fun `when timestamp for 21st is given, it should return a friendly string conversion`() {
        // given
        val dateStr = "21/08/2018"

        // when
        val stringDate = cut.dateToCardHeaderTitle(cut.toDayMonthYearTimestamp(dateStr))

        // then
        "Tuesday, August 21st" shouldBeEqualTo stringDate
    }

    @Test
    fun `when timestamp for 3rd is given, it should return a friendly string conversion`() {
        // given
        val dateStr = "03/08/2018"

        // when
        val stringDate = cut.dateToCardHeaderTitle(cut.toDayMonthYearTimestamp(dateStr))

        // then
        "Friday, August 3rd" shouldBeEqualTo stringDate
    }

    @Test
    fun `when timestamp for 31st is given, it should return a friendly string conversion`() {
        // given
        val dateStr = "31/08/2018"

        // when
        val stringDate = cut.dateToCardHeaderTitle(cut.toDayMonthYearTimestamp(dateStr))

        // then
        "Friday, August 31st" shouldBeEqualTo stringDate
    }

    @Test
    fun `when timestamp for 11th is given, it should return a friendly string conversion`() {
        // given
        val dateStr = "11/08/2018"

        // when
        val stringDate = cut.dateToCardHeaderTitle(cut.toDayMonthYearTimestamp(dateStr))

        // then
        "Saturday, August 11th" shouldBeEqualTo stringDate
    }

    @Test
    fun `when timestamp for 12th is given, it should return a friendly string conversion`() {
        // given
        val dateStr = "12/08/2018"

        // when
        val stringDate = cut.dateToCardHeaderTitle(cut.toDayMonthYearTimestamp(dateStr))

        // then
        "Sunday, August 12th" shouldBeEqualTo stringDate
    }

    @Test
    fun `when timestamp for 13th is given, it should return a friendly string conversion`() {
        // given
        val dateStr = "13/08/2018"

        // when
        val stringDate = cut.dateToCardHeaderTitle(cut.toDayMonthYearTimestamp(dateStr))

        // then
        "Monday, August 13th" shouldBeEqualTo stringDate
    }

    @Test
    fun `when timestamp for 23rd is given, it should return a friendly string conversion`() {
        // given
        val dateStr = "23/08/2018"

        // when
        val stringDate = cut.dateToCardHeaderTitle(cut.toDayMonthYearTimestamp(dateStr))

        // then
        "Thursday, August 23rd" shouldBeEqualTo stringDate
    }
}
