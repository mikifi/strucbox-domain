package com.strucbox.domain.dsl

import com.strucbox.domain.dto.NodeDto

data class NodeConfig(
        val name: String,
        val fields: List<FieldConfig>,
        val actions: List<String>?
) {

    @ScopedStructureSpecBuilder
    class Builder() {
        lateinit var name: String
        val fields: MutableList<FieldConfig> = mutableListOf()
        var actions: List<String>? = null

        fun field(init: FieldConfig.Builder.() -> Unit) {
            val builder = FieldConfig.Builder()
            builder.init()
            fields.add(builder.build())
        }

        fun build(): NodeConfig {
            return NodeConfig(name, fields, actions)
        }
    }
}

object NodeConfigConverter {
    fun toDto(config: NodeConfig): NodeDto =
            NodeDto(
                    name = config.name,
                    fields = config.fields.map { FieldConfigConverter.toDto(it) },
                    actions = config.actions ?: listOf()
            )
}