package com.strucbox.domain.dsl

import com.strucbox.domain.dto.EdgeDto

data class EdgeConfig(val source: String, val target: String) {

    @ScopedStructureSpecBuilder
    class Builder {
        lateinit var source: String
        lateinit var target: String

        fun build(): EdgeConfig {
            return EdgeConfig(source, target)
        }
    }
}

object EdgeConfigConverter {
    fun toDto(config: EdgeConfig): EdgeDto =
            EdgeDto(
                    source = config.source,
                    target = config.target
            )
}