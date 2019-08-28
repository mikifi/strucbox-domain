package com.strucbox.domain.v1.dsl

import com.strucbox.domain.v1.dto.FieldDto


data class FieldConfig(val name: String, val type: String, val values: List<String>?) {

    @ScopedStructureSpecBuilder
    class Builder {
        lateinit var name: String
        lateinit var type: String
        var values: List<String>? = null

        fun build(): FieldConfig {
            return FieldConfig(name, type, values)
        }
    }
}

object FieldConfigConverter {
    fun toDto(config: FieldConfig): FieldDto =
            FieldDto(
                    name = config.name,
                    type = config.type,
                    values = config.values
            )
}