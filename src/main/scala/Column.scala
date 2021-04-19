package CSVValidation

import CSVValidation.traits.ObjectNodeExtentions.IteratorHasGetKeysAndValues
import com.fasterxml.jackson.databind.{JsonNode, ObjectMapper}
import com.fasterxml.jackson.databind.node.{
  ArrayNode,
  JsonNodeFactory,
  NullNode,
  ObjectNode,
  TextNode
}
import errors.MetadataError

import scala.jdk.CollectionConverters.IteratorHasAsScala

object Column {
  val datatypeDefaultValue: ObjectNode = JsonNodeFactory.instance
    .objectNode()
    .set("@id", new TextNode("http://www.w3.org/2001/XMLSchema#string"))

  def getOrdered(inheritedProperties: ObjectNode): Boolean = {
    if (!inheritedProperties.path("ordered").isMissingNode) {
      inheritedProperties.path("ordered").asBoolean
    } else {
      false
    }
  }

  def getTextDirection(inheritedProperties: ObjectNode): String = {
    if (!inheritedProperties.path("textDirection").isMissingNode) {
      inheritedProperties.path("textDirection").asText()
    } else {
      "inherit"
    }
  }

  def getSuppressOutput(columnProperties: ObjectNode): Boolean = {
    if (!columnProperties.path("suppressOutput").isMissingNode) {
      columnProperties.path("suppressOutput").asBoolean
    } else {
      false
    }
  }

  def getVirtual(columnProperties: ObjectNode): Boolean = {
    if (!columnProperties.path("virtual").isMissingNode) {
      columnProperties.path("virtual").asBoolean
    } else {
      false
    }
  }

  def getRequired(inheritedProperties: ObjectNode): Boolean = {
    if (!inheritedProperties.path("required").isMissingNode) {
      inheritedProperties.path("required").asBoolean()
    } else {
      false
    }
  }

  def getDefault(inheritedProperties: ObjectNode): String = {
    if (!inheritedProperties.path("default").isMissingNode) {
      inheritedProperties.get("default").asText()
    } else {
      ""
    }
  }

  def getId(columnProperties: ObjectNode): Option[String] = {
    val idNode = columnProperties.path("@id")
    if (idNode.isMissingNode) None else Some(idNode.asText())
  }

  def getName(columnProperties: ObjectNode, lang: String): Option[String] = {
    if (!columnProperties.path("name").isMissingNode) {
      Some(columnProperties.get("name").asText())
    } else if (
      (!columnProperties.path("titles").isMissingNode) && (!columnProperties
        .path("titles")
        .path(lang)
        .isMissingNode)
    ) {
      val langArray = Array.from(
        columnProperties.path("titles").path(lang).elements().asScala
      )
      Some(langArray(0).asText())
    } else {
      // Not sure what to return here. Hope it does not reach here
      None
    }
  }

  def getNullParam(inheritedProperties: ObjectNode): Array[String] = {
    if (!inheritedProperties.path("null").isMissingNode) {
      inheritedProperties.get("null") match {
        case a: ArrayNode => {
          var nullParamsToReturn = Array[String]()
          val nullParams = Array.from(a.elements.asScala)
          for (np <- nullParams)
            nullParamsToReturn = nullParamsToReturn :+ np.asText()
          nullParamsToReturn
        }
        case s: TextNode => Array[String](s.asText())
      }
    } else {
      Array[String]("")
    }
  }

  def getAboutUrl(inheritedProperties: ObjectNode): Option[String] = {
    val aboutUrlNode = inheritedProperties.path("aboutUrl")
    if (aboutUrlNode.isMissingNode) None else Some(aboutUrlNode.asText())
  }

  def getPropertyUrl(inheritedProperties: ObjectNode): Option[String] = {
    val propertyUrlNode = inheritedProperties.path("propertyUrl")
    if (propertyUrlNode.isMissingNode) None else Some(propertyUrlNode.asText)
  }

  def getValueUrl(inheritedProperties: ObjectNode): Option[String] = {
    val valueUrlNode = inheritedProperties.path("valueUrl")
    if (valueUrlNode.isMissingNode) None else Some(valueUrlNode.asText())
  }

  def getSeparator(inheritedProperties: ObjectNode): Option[String] = {
    val separatorNode = inheritedProperties.path("separator")
    if (separatorNode.isMissingNode) None else Some(separatorNode.asText())
  }

  def fromJson(
      number: Int,
      columnDesc: ObjectNode,
      baseUrl: String,
      lang: String,
      inheritedProperties: ObjectNode
  ): Column = {
    var annotations = Map[String, JsonNode]()
    var warnings = Array[ErrorMessage]()
//    val mapper = new ObjectMapper()
    val columnProperties = JsonNodeFactory.instance.objectNode()
//    val columnProperties = mapper.createObjectNode()
    val inheritedPropertiesCopy = inheritedProperties.deepCopy()

    for ((property, value) <- columnDesc.getKeysAndValues) {
      (property, value) match {
        case ("@type", v: TextNode) if v.asText != "Column" => {
          throw new MetadataError(
            s"columns[$number].@type, @type of column is not 'Column'"
          )
        }
        case _ => {
          val (v, w, csvwPropertyType) =
            PropertyChecker.checkProperty(property, value, baseUrl, lang)
          if (w.nonEmpty) {
            warnings :+ warnings.concat(
              w.map(warningString =>
                ErrorMessage(
                  warningString,
                  "metadata",
                  "",
                  "",
                  s"$property: ${value.asText}",
                  ""
                )
              )
            )
          }
          csvwPropertyType match {
            case PropertyType.Annotation                   => annotations += (property -> v)
            case PropertyType.Common | PropertyType.Column =>
//              columnProperties.set(property, v)
            case PropertyType.Inherited =>
              inheritedPropertiesCopy.set(property, v)
            case _ =>
              warnings :+= ErrorMessage(
                s"invalid_property",
                "metadata",
                "",
                "",
                s"column: ${property}",
                ""
              )
          }
        }
      }
    }
    val datatype =
      if (!inheritedPropertiesCopy.path("datatype").isMissingNode) {
        inheritedPropertiesCopy.get("datatype")
      } else {
        datatypeDefaultValue
      }

    val newLang = if (!inheritedPropertiesCopy.path("lang").isMissingNode) {
      inheritedPropertiesCopy.get("lang").asText()
    } else {
      "und"
    }

    new Column(
      number = number,
      name = getName(columnProperties, lang),
      id = getId(columnProperties),
      datatype = datatype,
      lang = newLang,
      nullParam = getNullParam(inheritedPropertiesCopy),
      default = getDefault(inheritedPropertiesCopy),
      required = getRequired(inheritedPropertiesCopy),
      aboutUrl = getAboutUrl(inheritedPropertiesCopy),
      propertyUrl = getPropertyUrl(inheritedPropertiesCopy),
      valueUrl = getValueUrl(inheritedPropertiesCopy),
      separator = getSeparator(inheritedPropertiesCopy),
      ordered = getOrdered(inheritedPropertiesCopy),
      titles =
        columnProperties.get("titles"), // Keeping it as jsonNode, revisit
      suppressOutput = getSuppressOutput(columnProperties),
      virtual = getVirtual(columnProperties),
      textDirection = getTextDirection(inheritedPropertiesCopy),
      annotations = annotations,
      warnings = warnings
    )
  }
}

case class Column private (
    number: Int,
    name: Option[String],
    id: Option[String],
    aboutUrl: Option[String],
    datatype: JsonNode,
    default: String,
    lang: String,
    nullParam: Array[String],
    ordered: Boolean,
    propertyUrl: Option[String],
    required: Boolean,
    separator: Option[String],
    suppressOutput: Boolean,
    textDirection: String,
//  defaultName: String, // Not used, this logic is included in name param
    titles: JsonNode,
    valueUrl: Option[String],
    virtual: Boolean,
    annotations: Map[String, JsonNode],
    warnings: Array[ErrorMessage]
) {}
