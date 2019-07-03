package com.strucbox.domain.dsl

import com.strucbox.domain.dto.LayoutDto


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