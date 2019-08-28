package com.strucbox.domain.v1

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.strucbox.domain.v1.dsl.structure
import com.strucbox.domain.v1.dto.EdgeDto
import com.strucbox.domain.v1.dto.StructureDto
import org.junit.Test

class StructureConfigTest {
    companion object {
        val objectMapper = ObjectMapper().registerKotlinModule()

        val yamlMapper = ObjectMapper(YAMLFactory()).registerKotlinModule()

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

            node {
                name = "node2"
                field {
                    name = "fieldA"
                    type = "some.class.String"
                }
                field {
                    name = "fieldB"
                    type = "some.class.Boolean"
                }
            }

            node {
                name = "node3"
            }

            relation {
                name = "subNode"
                inverseName = "subNodeOf"

                annotation {
                    name = "annotationA"
                    type = "some.class.String"
                }

                annotation {
                    name = "annotationB"
                    type = "some.class.Boolean"
                }

                edge {
                    source = "node1"
                    target = "node2"
                }

                edge {
                    source = "node1"
                    target = "node3"
                }
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

    @Test
    fun structure_parseJson_dtoAsExpected() {
        val json = "{\"name\":\"abc\",\"owner\":\"test\",\"nodes\":[{\"name\":\"node1\",\"fields\":[],\"actions\":[]},{\"name\":\"node2\",\"fields\":[],\"actions\":[]},{\"name\":\"node3\",\"fields\":[],\"actions\":[]}],\"relations\":[{\"name\":\"subNode\",\"edges\":[{\"source\":\"node1\",\"target\":\"node2\"},{\"source\":\"node1\",\"target\":\"node2\"}],\"cardinality\":\"ONE_TO_ONE\",\"inverseName\":\"subNodeOf\"}],\"public\":true}\n"
        val structureDto = deserialize(json)
        println(structureDto)
    }

    @Test
    fun structure_parseYml_dtoAsExpected() {
        val yaml = "name: \"abc\"\n" +
                "owner: \"test\"\n" +
                "nodes:\n" +
                "- name: \"node1\"\n" +
                "  fields: []\n" +
                "  actions: []\n" +
                "- name: \"node2\"\n" +
                "  fields: []\n" +
                "  actions: []\n" +
                "- name: \"node3\"\n" +
                "  fields: []\n" +
                "  actions: []\n" +
                "relations:\n" +
                "- name: \"subNode\"\n" +
                "  edges:\n" +
                "  - source: \"node1\"\n" +
                "    target: \"node2\"\n" +
                "  - source: \"node1\"\n" +
                "    target: \"node2\"\n" +
                "  cardinality: \"ONE_TO_ONE\"\n" +
                "  inverseName: \"subNodeOf\"\n" +
                "public: true"
        val structureDto = deserializeFromYaml(yaml)
        println(structureDto)
    }
}