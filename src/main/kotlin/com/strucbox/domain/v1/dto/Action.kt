package com.strucbox.domain.v1.dto

abstract class Action(
        val httpMethod: String,
        val processor: (Unit) -> Unit
)
