package com.strucbox.domain.v1.dsl

import com.strucbox.domain.v1.dto.AppDto

data class AppConfig(val androidApp: String, val iosApp: String) {

    @ScopedStructureSpecBuilder
    class Builder {
        lateinit var androidApp: String
        lateinit var iosApp: String

        fun build(): AppConfig {
            return AppConfig(androidApp, iosApp)
        }
    }
}

object AppConfigConverter {
    fun toDto(config: AppConfig): AppDto =
            AppDto(
                    androidApp = config.androidApp,
                    iosApp = config.iosApp
            )
}