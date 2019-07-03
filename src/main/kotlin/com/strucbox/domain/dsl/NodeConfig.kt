package com.strucbox.domain.dsl

import com.strucbox.domain.dto.NodeDto

data class NodeConfig(
        val name: String,
        val fields: List<FieldConfig>,
        val actions: List<String>?,
        val childNodes: List<NodeConfig>?
) {

    @ScopedStructureSpecBuilder
    class Builder {
        lateinit var name: String
        val fields: MutableList<FieldConfig> = mutableListOf()
        var actions: List<String>? = null
        val childNodes: MutableList<NodeConfig> = mutableListOf()

        fun field(init: FieldConfig.Builder.() -> Unit) {
            val builder = FieldConfig.Builder()
            builder.init()
            fields.add(builder.build())
        }

        fun node(init: Builder.() -> Unit) {
            val builder = Builder()
            builder.init()
            childNodes.add(builder.build())
        }

        fun build(): NodeConfig {
            return NodeConfig(name, fields, actions, childNodes)
        }
    }
}

object NodeConfigConverter {
    fun toDto(config: NodeConfig): NodeDto =
            NodeDto(
                    name = config.name,
                    fields = config.fields.map { FieldConfigConverter.toDto(it) },
                    actions = config.actions ?: listOf(),
                    childNodes = if (config.childNodes?.size!! > 0) config.childNodes.map { toDto(it) } else null
            )
}