package com.gluehome.common.domain.extension

fun Boolean?.isNullOrFalse(): Boolean {
    return this == null || this == false
}

fun Any?.isNull(): Boolean {
    return this == null
}

fun Any?.isNullOrEmpty(): Boolean {
    return this == null || (this is String && this.isEmpty())
}

fun Map<String, String>.filterEmptyValues() = this.filter { it.value.isNotEmpty() }
fun HashMap<String, Any>.filterEmptyValues() = this.filter { !it.value.isNullOrEmpty() }
