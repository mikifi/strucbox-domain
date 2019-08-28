package com.strucbox.domain.v1.dto

import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.Charset
import java.time.Instant

val ALPHANUMERIC = Regex("[^A-Za-z0-9_ ]")
val UTF_8: Charset = Charset.forName("UTF-8")

abstract class FieldType<T>(
        val parse: (String) -> T,
        val toString: (T) -> String = { it.toString() }
)

class Identifier : FieldType<String>(
        parse = { URLDecoder.decode(ALPHANUMERIC.replace(it, ""), UTF_8) },
        toString = { URLEncoder.encode(it, UTF_8) }
)

class StringValue : FieldType<String>(
        parse = { it }
)

class DateTimeValue : FieldType<Instant>(
        parse = { Instant.parse(it) }
)

class BooleanValue : FieldType<Boolean>(
        parse = { it.toBoolean() }
)

