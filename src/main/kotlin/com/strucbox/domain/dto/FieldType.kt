package com.strucbox.domain.dto


abstract class FieldType(
        val preprocessor: (String) -> String = { it },
        val postprocessor: (String) -> String = { it }
)
