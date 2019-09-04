package com.strucbox.domain.v1.dto

enum class HttpMethod { GET, PUT, DELETE, POST, PATCH, HEAD }

abstract class Action<T, U>(
        val httpMethod: HttpMethod
) {
    abstract fun processor(input: T): U
}
