package com.gluehome.common.domain

import com.gluehome.common.domain.framework.functional.Either
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class EitherTest {

    @Test fun `Either Right should return correct type`() {
        val result = Either.Right("Sarah Connor")

        assertTrue { result.isRight }
        assertFalse { result.isLeft }
        result.either({},
                { right ->
                    assertTrue { right == "Sarah Connor" }
                })
    }

    @Test fun `Either Left should return correct type`() {
        val result = Either.Left("Sarah Connor")

        assertTrue { result.isLeft }
        assertFalse { result.isRight }
        result.either({ left ->
            assertTrue { left == "Sarah Connor" }
        }, {})
    }
}
