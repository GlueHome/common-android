package com.gluehome.common.domain.utils


class RandomUtils {
    companion object {
        fun randomNumberBetween(x: Int, y: Int): Int = (x..y).random()
        fun randomNumberBetween(x: Long, y: Long): Long = (x..y).random()
    }
}
