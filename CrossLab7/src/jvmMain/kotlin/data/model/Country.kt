package data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Country(
    @SerialName("name")
    val name: Name,
    @SerialName("tld")
    val tld: List<String>? = null,
    @SerialName("cca2")
    val cca2: String,
    @SerialName("ccn3")
    val ccn3: String? = null,
    @SerialName("cca3")
    val cca3: String,
    @SerialName("cioc")
    val cioc: String? = null,
    @SerialName("independent")
    val independent: Boolean? = null,
    @SerialName("status")
    val status: String,
    @SerialName("unMember")
    val unMember: Boolean,
    @SerialName("currencies")
    val currencies: Map<String, Currency>,
    @SerialName("idd")
    val idd: Idd? = null,
    @SerialName("capital")
    val capital: List<String>,
    @SerialName("altSpellings")
    val altSpellings: List<String>,
    @SerialName("region")
    val region: String,
    @SerialName("subregion")
    val subregion: String,
    @SerialName("languages")
    val languages: Map<String, String>,
    @SerialName("translations")
    val translations: Map<String, Translation>,
    @SerialName("latlng")
    val latlng: List<Double>,
    @SerialName("landlocked")
    val landlocked: Boolean,
    @SerialName("borders")
    val borders: List<String>? = null,
    @SerialName("area")
    val area: Double,
    @SerialName("demonyms")
    val demonyms: Map<String, Demonym>,
    @SerialName("flag")
    val flag: String,
    @SerialName("maps")
    val maps: Map<String, String>,
    @SerialName("population")
    val population: Int,
    @SerialName("gini")
    val gini: Map<String, Double>? = null,
    @SerialName("fifa")
    val fifa: String? = null,
    @SerialName("car")
    val car: Car,
    @SerialName("timezones")
    val timezones: List<String>,
    @SerialName("continents")
    val continents: List<String>,
    @SerialName("flags")
    val flags: Flags,
    @SerialName("coatOfArms")
    val coatOfArms: Map<String, String>,
    @SerialName("startOfWeek")
    val startOfWeek: String,
    @SerialName("capitalInfo")
    val capitalInfo: CapitalInfo,
    @SerialName("postalCode")
    val postalCode: PostalCode = PostalCode("", "")
)

@Serializable
data class Name(
    @SerialName("common")
    val common: String,
    @SerialName("official")
    val official: String,
    @SerialName("nativeName")
    val nativeName: Map<String, Map<String, String>>
)
@Serializable
data class Flags(
    @SerialName("png")
    val png: String,
    @SerialName("svg")
    val svg: String,
    @SerialName("alt")
    val alt: String?=null
)
@Serializable
data class Currency(
    @SerialName("name")
    val name: String,
    @SerialName("symbol")
    val symbol: String? = null
)

@Serializable
data class Idd(
    @SerialName("root")
    val root: String?=null,
    @SerialName("suffixes")
    val suffixes: List<String>?=null
)

@Serializable
data class Translation(
    @SerialName("official")
    val official: String,
    @SerialName("common")
    val common: String
)

@Serializable
data class Demonym(
    @SerialName("f")
    val f: String,
    @SerialName("m")
    val m: String
)

@Serializable
data class Car(
    @SerialName("signs")
    val signs: List<String>,
    @SerialName("side")
    val side: String
)

@Serializable
data class CapitalInfo(
    @SerialName("latlng")
    val latlng: List<Double>
)

@Serializable
data class PostalCode(
    @SerialName("format")
    val format: String,
    @SerialName("regex")
    val regex: String
)

