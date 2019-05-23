package com.gluehome.common.domain.utils

import java.util.Random
import java.util.concurrent.ThreadLocalRandom

fun generateRandom(between: LongRange): Long {
    return between.shuffled().last()
}

fun generateRandom(between: IntRange): Int {
    return between.shuffled().last()
}

fun ClosedRange<Int>.random() =
    Random().nextInt((endInclusive + 1) - start) + start

fun ClosedRange<Long>.random() = ThreadLocalRandom.current().nextLong(start, endInclusive)

fun <E> List<E>.getRandomElement() = this[Random().nextInt(this.size)]
