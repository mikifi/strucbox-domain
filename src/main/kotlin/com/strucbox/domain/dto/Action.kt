package com.strucbox.domain.dto

abstract class Action(
        val httpMethod: String,
        val processor: (Unit) -> Unit
)
