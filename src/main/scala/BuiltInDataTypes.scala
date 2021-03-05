package CSVValidation
import scala.collection.mutable.HashMap

object BuiltInDataTypes {
  val types = HashMap(
    "number" -> "http://www.w3.org/2001/XMLSchema#double",
    "binary" -> "http://www.w3.org/2001/XMLSchema#base64Binary",
    "datetime" -> "http://www.w3.org/2001/XMLSchema#dateTime",
    "any" -> "http://www.w3.org/2001/XMLSchema#anyAtomicType",
    "xml" -> "http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral",
    "html" -> "http://www.w3.org/1999/02/22-rdf-syntax-ns#HTML",
    "json" -> "http://www.w3.org/ns/csvw#JSON",
    "anyAtomicType" -> "http://www.w3.org/2001/XMLSchema#anyAtomicType",
    "anyURI" -> "http://www.w3.org/2001/XMLSchema#anyURI",
    "base64Binary" -> "http://www.w3.org/2001/XMLSchema#base64Binary",
    "boolean" -> "http://www.w3.org/2001/XMLSchema#boolean",
    "date" -> "http://www.w3.org/2001/XMLSchema#date",
    "dateTime" -> "http://www.w3.org/2001/XMLSchema#dateTime",
    "dateTimeStamp" -> "http://www.w3.org/2001/XMLSchema#dateTimeStamp",
    "decimal" -> "http://www.w3.org/2001/XMLSchema#decimal",
    "integer" -> "http://www.w3.org/2001/XMLSchema#integer",
    "long" -> "http://www.w3.org/2001/XMLSchema#long",
    "int" -> "http://www.w3.org/2001/XMLSchema#int",
    "short" -> "http://www.w3.org/2001/XMLSchema#short",
    "byte" -> "http://www.w3.org/2001/XMLSchema#byte",
    "nonNegativeInteger" -> "http://www.w3.org/2001/XMLSchema#nonNegativeInteger",
    "positiveInteger" -> "http://www.w3.org/2001/XMLSchema#positiveInteger",
    "unsignedLong" -> "http://www.w3.org/2001/XMLSchema#unsignedLong",
    "unsignedInt" -> "http://www.w3.org/2001/XMLSchema#unsignedInt",
    "unsignedShort" -> "http://www.w3.org/2001/XMLSchema#unsignedShort",
    "unsignedByte" -> "http://www.w3.org/2001/XMLSchema#unsignedByte",
    "nonPositiveInteger" -> "http://www.w3.org/2001/XMLSchema#nonPositiveInteger",
    "negativeInteger" -> "http://www.w3.org/2001/XMLSchema#negativeInteger",
    "double" -> "http://www.w3.org/2001/XMLSchema#double",
    "duration" -> "http://www.w3.org/2001/XMLSchema#duration",
    "dayTimeDuration" -> "http://www.w3.org/2001/XMLSchema#dayTimeDuration",
    "yearMonthDuration" -> "http://www.w3.org/2001/XMLSchema#yearMonthDuration",
    "float" -> "http://www.w3.org/2001/XMLSchema#float",
    "gDay" -> "http://www.w3.org/2001/XMLSchema#gDay",
    "gMonth" -> "http://www.w3.org/2001/XMLSchema#gMonth",
    "gMonthDay" -> "http://www.w3.org/2001/XMLSchema#gMonthDay",
    "gYear" -> "http://www.w3.org/2001/XMLSchema#gYear",
    "gYearMonth" -> "http://www.w3.org/2001/XMLSchema#gYearMonth",
    "hexBinary" -> "http://www.w3.org/2001/XMLSchema#hexBinary",
    "QName" -> "http://www.w3.org/2001/XMLSchema#QName",
    "string" -> "http://www.w3.org/2001/XMLSchema#string",
    "normalizedString" -> "http://www.w3.org/2001/XMLSchema#normalizedString",
    "token" -> "http://www.w3.org/2001/XMLSchema#token",
    "language" -> "http://www.w3.org/2001/XMLSchema#language",
    "Name" -> "http://www.w3.org/2001/XMLSchema#Name",
    "NMTOKEN" -> "http://www.w3.org/2001/XMLSchema#NMTOKEN",
    "time" -> "http://www.w3.org/2001/XMLSchema#time"
  )

}
