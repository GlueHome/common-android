package com.gluehome.common.data.log

import org.amshove.kluent.shouldBe
import org.junit.After
import org.junit.Test

class LoggerExtraInfoTest {

    val instance = LoggerExtraInfo.instance

    @Test
    fun `Test adding items with a map`() {
        instance.add(mapOf("password" to 12345))
        instance.getAll().size shouldBe 1
    }

    @Test
    fun `Test adding items key pair`() {
        instance.add("password", 12345)
        instance.getAll().size shouldBe 1
    }

    @Test
    fun `CLearing test`() {
        instance.add(mapOf("password" to 12345))
        instance.clear()
        instance.getAll().size shouldBe 0
    }

    @After
    fun tearDown() {
        instance.clear()
    }
}
