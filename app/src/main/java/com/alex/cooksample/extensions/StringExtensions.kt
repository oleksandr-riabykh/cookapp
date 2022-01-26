package com.alex.cooksample.extensions

import java.text.SimpleDateFormat
import java.util.*

//todo handle error if wrong format or empty
fun String?.dateFormat(formatIn: String, formatOut: String): String {
    val input = SimpleDateFormat(formatIn, Locale.getDefault())
    val output = SimpleDateFormat(formatOut, Locale.getDefault())
    val parse = this?.let { input.parse(it) } ?: ""
    return output.format(parse)
}