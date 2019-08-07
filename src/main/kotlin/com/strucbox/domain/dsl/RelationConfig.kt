package com.strucbox.domain.dsl

import com.strucbox.domain.dto.Cardinality
import com.strucbox.domain.dto.EdgeDto
import com.strucbox.domain.dto.RelationDto


data class RelationConfig(val name: String, val edges: List<EdgeConfig>, val cardinality: Cardinality, val inverseName: String?, val annotation: String?) {

    @ScopedStructureSpecBuilder
    class Builder {
        lateinit var name: String
        var edges: MutableList<EdgeConfig> = mutableListOf()
        var cardinality: Cardinality = Cardinality.ONE_TO_ONE
        var inverseName: String? = null
        var annotation: String? = null

        fun edge(init: EdgeConfig.Builder.() -> Unit) {
            val builder = EdgeConfig.Builder()
            builder.init()
            edges.add(builder.build())
        }

        fun build(): RelationConfig {
            return RelationConfig(name, edges, cardinality, inverseName, annotation)
        }
    }
}

object RelationConfigConverter {
    fun toDto(config: RelationConfig): RelationDto =
            RelationDto(
                    name = config.name,
                    edges = config.edges.map { EdgeConfigConverter.toDto(it) },
                    cardinality = config.cardinality,
                    inverseName = config.inverseName,
                    annotation = config.annotation
            )
}
