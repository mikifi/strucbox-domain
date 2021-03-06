package com.strucbox.domain.v1.dsl

import com.strucbox.domain.v1.dto.LayoutDto


data class LayoutConfig(val icon: String, val css: String) {

    @ScopedStructureSpecBuilder
    class Builder {
        lateinit var icon: String
        lateinit var css: String

        fun build(): LayoutConfig {
            return LayoutConfig(icon, css)
        }
    }
}

object LayoutConfigConverter {
    fun toDto(config: LayoutConfig): LayoutDto =
            LayoutDto(
                    icon = config.icon,
                    css = config.css
            )
}