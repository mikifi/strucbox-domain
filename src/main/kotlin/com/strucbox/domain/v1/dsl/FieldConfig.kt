package com.strucbox.domain.v1.dsl

import com.strucbox.domain.v1.dto.FieldDto
import com.strucbox.domain.v1.dto.Limitation


data class FieldConfig(val name: String, val type: String, val values: List<String>?, val limitation: Limitation?) {

    @ScopedStructureSpecBuilder
    class Builder {
        lateinit var name: String
        lateinit var type: String
        var values: List<String>? = null
        var limitation: Limitation? = null

        fun build(): FieldConfig {
            return FieldConfig(name, type, values, limitation)
        }
    }
}

object FieldConfigConverter {
    fun toDto(config: FieldConfig): FieldDto =
            FieldDto(
                    name = config.name,
                    type = config.type,
                    values = config.values,
                    limitation = config.limitation
            )
}