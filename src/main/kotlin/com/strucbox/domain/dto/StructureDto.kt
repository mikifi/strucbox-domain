package com.strucbox.domain.dto

import com.fasterxml.jackson.annotation.JsonInclude
import io.protostuff.Tag
import io.swagger.v3.oas.annotations.media.Schema

import javax.validation.Valid
import javax.validation.constraints.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(
        description = "Domain model for storage. This is the main entry for defining Strucbox structures"
)
data class StructureDto(
        @Tag(10)
        @NotBlank @Pattern(regexp = "[a-zA-Z0-9_-]+")
        @get:Size(max = 100)
        @get:Schema(required = true, description = "Name of the structure")
        val name: String,

        @Tag(11)
        @NotBlank
        @get:Size(max = 100)
        @get:Schema(required = true, description = "Owner (username) of the structure")
        val owner: String,

        @Tag(12)
        @NotEmpty @Valid
        @get:Schema(required = true, description = "Root nodes of this structure")
        val nodes: List<NodeDto>,

        @Tag(13)
        @NotEmpty @Valid
        @get:Schema(required = true, description = "Relations between nodes of this structure")
        val relations: List<RelationDto>,

        @Tag(14)
        @NotNull
        @get:Schema(required = true, description = "Wheather structure is public")
        val public: Boolean = true,

        @Tag(15)
        @Valid
        @get:Schema(required = false, description = "Visual layout for the structure")
        val layout: LayoutDto? = null,

        @Tag(16)
        @Valid
        @get:Schema(required = false, description = "Information about mobile apps connected to the structure")
        val app: AppDto? = null
)

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(
        description = "Enum with valid cardinality for relations"
)
enum class Cardinality {
    ONE_TO_ONE, ONE_TO_MANY, MANY_TO_MANY, MANY_TO_ONE
}

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(
        description = "Schema for defining a relations between nodes"
)
data class RelationDto(
        @Tag(10)
        @NotBlank @Pattern(regexp = "[a-zA-Z0-9_-]+")
        @get:Size(max = 100)
        @get:Schema(required = true, description = "Name of the relation")
        val name: String,

        @Tag(11)
        @NotNull @Valid
        @get:Schema(required = false, description = "Target and sources variants of this relation")
        val edges: List<EdgeDto> = listOf(),

        @Tag(12)
        @get:Schema(required = false, description = "Cardinality of this relation")
        val cardinality: Cardinality = Cardinality.ONE_TO_ONE,

        @Tag(13)
        @Valid @NotNull
        @get:Schema(required = true, description = "List of annotations on relation")
        val annotations: List<FieldDto> = listOf(),

        @Tag(14)
        @get:Schema(required = false, description = "Reverse name of relation. If provided, the relation is bidirectional")
        val inverseName: String? = null
)

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(
        description = "Schema for defining a edge for a relation between nodes"
)
data class EdgeDto(
        @Tag(10)
        @NotBlank @Pattern(regexp = "[a-zA-Z0-9_-]+")
        @get:Size(max = 100)
        @get:Schema(required = true, description = "Name of the node that is the source of the relation")
        val source: String,

        @Tag(11)
        @NotNull @Valid
        @get:Schema(required = true, description = "Name of the node that is the target of the relation")
        val target: String
)

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(
        description = "Schema for defining a type of node in the structure"
)
data class NodeDto(
        @Tag(10)
        @get:Size(max = 100)
        @get:Schema(required = true, description = "Name of the node")
        val name: String,

        @Tag(11)
        @Valid @NotNull
        @get:Schema(required = true, description = "List of fields with values for a node")
        val fields: List<FieldDto> = listOf(),

        @Tag(12)
        @Valid @NotNull
        @get:Schema(required = true, description = "List of actions allowed to perform on nodes of this type. The action references a class name implementing the Action abstract class")
        val actions: List<String> = listOf()
)


@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(
        description = "A field has a name and a type defining how the field is displayed"
)
data class FieldDto(
        @Tag(10)
        @get:Size(max = 100)
        @get:Schema(required = true, description = "Name of the field")
        val name: String,

        @Tag(11)
        @get:Size(max = 100)
        @get:Schema(required = true, description = "Type of the field. The type references a class name implementing the FieldType abstract class")
        val type: String,

        @Tag(12)
        @get:Schema(required = false, description = "List of values that are allowed for this type")
        val values: List<String>? = null,

        @Tag(13)
        @get:Schema(required = false, description = "Set to true if this field identifies the node resource")
        val identifier: Boolean = false
)


@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(
        description = "Information about mobile app for a given structure"
)
data class AppDto(
        @Tag(10)
        @get:Size(max = 100)
        @get:Schema(required = false, description = "URI to Android app. Null if not applicable")
        val androidApp: String? = null,

        @Tag(11)
        @get:Size(max = 100)
        @get:Schema(required = false, description = "URI to iOS app. Null if not applicable")
        val iosApp: String? = null
)


@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(
        description = "Layout for web application and mobile apps"
)
data class LayoutDto(
        @Tag(10)
        @get:Size(max = 100)
        @get:Schema(required = false, description = "URI to icon")
        val icon: String? = null,

        @Tag(11)
        @get:Size(max = 100)
        @get:Schema(required = false, description = "URI to CSS")
        val css: String? = null
)
