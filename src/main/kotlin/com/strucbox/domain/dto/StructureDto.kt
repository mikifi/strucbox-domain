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
        @get:Schema(required = true, description = "Name of the storage.")
        val name: String,

        @Tag(11)
        @NotBlank
        @get:Size(max = 100)
        @get:Schema(required = true, description = "Owner (username) of the storage.")
        val owner: String,

        @Tag(12)
        @NotEmpty @Valid
        @get:Schema(required = true, description = "Root nodes of this storage.")
        val nodes: List<NodeDto>,

        @Tag(13)
        @NotNull
        @get:Schema(required = true, description = "Name of the storage.")
        val public: Boolean = true,

        @Tag(14)
        @Valid
        @get:Schema(required = false, description = "Visual layout for the storage.")
        val layout: LayoutDto? = null,

        @Tag(15)
        @Valid
        @get:Schema(required = false, description = "Information about mobile apps connected to the storage.")
        val app: AppDto? = null
)


@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(
        description = "Schema for defining a type of node in the storage. "
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
        val actions: List<String> = listOf(),

        @Tag(13)
        @Valid
        @get:Schema(required = false, description = "Child node types of this node")
        val childNodes: List<NodeDto>? = null
)


@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(
        description = "A field has a name and a type defining how the field is displayed."
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
        description = "Information about mobile app for a given storage."
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
        description = "Layout for web application and mobile apps."
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
