package com.strucbox.domain.dsl

import com.strucbox.domain.dto.StructureDto


/**
 * Annotation for implicit DSL receivers (builders) that tells Kotlin to fail
 * compilation if a call on a receiver tries to access outer receiver scopes.
 * More details: https://goo.gl/cdsgXV
 * */
@DslMarker
annotation class ScopedStructureSpecBuilder

data class StructureConfig internal constructor(
        val name: String,
        val owner: String,
        val nodes: List<NodeConfig>,
        val public: Boolean,
        val layout: LayoutConfig?,
        val app: AppConfig?
) {

    @ScopedStructureSpecBuilder
    class Builder {
        lateinit var name: String
        lateinit var owner: String
        val nodes: MutableList<NodeConfig> = mutableListOf()
        var public: Boolean = true
        var layout: LayoutConfig? = null
        var app: AppConfig? = null

        fun node(init: NodeConfig.Builder.() -> Unit) {
            val builder = NodeConfig.Builder()
            builder.init()
            nodes.add(builder.build())
        }

        fun layout(init: LayoutConfig.Builder.() -> Unit) {
            val builder = LayoutConfig.Builder()
            builder.init()
            layout = builder.build()
        }

        fun app(init: AppConfig.Builder.() -> Unit) {
            val builder = AppConfig.Builder()
            builder.init()
            app = builder.build()
        }

        fun build(): StructureConfig {
            return StructureConfig(
                    name, owner, nodes, public, layout, app
            )
        }
    }

    fun toStructureDto(): StructureDto =
            StructureConfigConverter.toDto(this)
}


fun structure(init: StructureConfig.Builder.() -> Unit): StructureConfig {
    val builder = StructureConfig.Builder()
    builder.init()
    return builder.build()
}


object StructureConfigConverter {
    fun toDto(config: StructureConfig): StructureDto =
            StructureDto(
                    name = config.name,
                    owner = config.owner,
                    nodes = config.nodes.map { NodeConfigConverter.toDto(it) },
                    layout = if (config.layout != null) LayoutConfigConverter.toDto(config.layout) else null,
                    app = if (config.app != null) AppConfigConverter.toDto(config.app) else null
            )
}