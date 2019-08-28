package com.strucbox.domain.v1.dsl

import com.strucbox.domain.v1.dto.Cardinality
import com.strucbox.domain.v1.dto.RelationDto


data class RelationConfig(val name: String, val edges: List<EdgeConfig>, val cardinality: Cardinality, val annotations: List<FieldConfig>, val inverseName: String?) {

    @ScopedStructureSpecBuilder
    class Builder {
        lateinit var name: String
        var edges: MutableList<EdgeConfig> = mutableListOf()
        var cardinality: Cardinality = Cardinality.ONE_TO_ONE
        var annotations: MutableList<FieldConfig> = mutableListOf()
        var inverseName: String? = null

        fun edge(init: EdgeConfig.Builder.() -> Unit) {
            val builder = EdgeConfig.Builder()
            builder.init()
            edges.add(builder.build())
        }

        fun annotation(init: FieldConfig.Builder.() -> Unit) {
            val builder = FieldConfig.Builder()
            builder.init()
            annotations.add(builder.build())
        }

        fun build(): RelationConfig {
            return RelationConfig(name, edges, cardinality, annotations, inverseName)
        }
    }
}

object RelationConfigConverter {
    fun toDto(config: RelationConfig): RelationDto =
            RelationDto(
                    name = config.name,
                    edges = config.edges.map { EdgeConfigConverter.toDto(it) },
                    cardinality = config.cardinality,
                    annotations = config.annotations.map { FieldConfigConverter.toDto(it) },
                    inverseName = config.inverseName
            )
}
