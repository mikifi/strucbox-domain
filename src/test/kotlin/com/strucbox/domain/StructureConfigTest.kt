package com.strucbox.domain

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.strucbox.domain.dsl.structure
import com.strucbox.domain.dto.StructureDto
import org.junit.Test

class StructureConfigTest {
    companion object {
        val objectMapper = ObjectMapper().registerKotlinModule()

        val yamlMapper = ObjectMapper(YAMLFactory())

        fun serialize(any: Any): String {
            return objectMapper.writeValueAsString(any)
        }

        fun serializeToYaml(any: Any): String {
            return yamlMapper.writeValueAsString(any)
        }

        fun deserialize(string: String): StructureDto {
            return objectMapper.readValue(string)
        }

        fun deserializeFromYaml(string: String): StructureDto {
            return yamlMapper.readValue(string)
        }
    }


    @Test
    fun structure_minimal_dtoAsExpected() {
        val testStructure = structure {
            name = "abc"
            owner = "test"
            public = true
            node {
                name = "node1"
            }

        }
        val structureDto: StructureDto = testStructure.toStructureDto()
        System.out.println(serialize(structureDto))
        System.out.println(serializeToYaml(structureDto))
    }

    @Test
    fun structure_jsonMap_dtoAsExpected() {
        val map = mapOf(Pair("helloe", listOf(mapOf(Pair("there", "highground")))), Pair("general", "kenobi"))
        val string = serialize(map)
        val deserilized = objectMapper.readValue<Map<String, Any>>(string)

        System.out.println(string)
        System.out.println(deserilized)

    }

    @Test
    fun structure_access_dtoAsExpected() {
        val accessStructure = structure {
            name = "acl"
            owner = "strucbox.com"

            public = false
        }
    }


}