package com.test.mozarktest.model
import androidx.annotation.Keep

import com.google.gson.annotations.SerializedName


@Keep
data class UserListResponse(
    @SerializedName("info")
    var info: Info = Info(),
    @SerializedName("results")
    var results: List<UserResult> = listOf()
)

@Keep
data class Info(
    @SerializedName("page")
    var page: Int = 0,
    @SerializedName("results")
    var results: Int = 0,
    @SerializedName("seed")
    var seed: String = "",
    @SerializedName("version")
    var version: String = ""
)

@Keep
data class UserResult(
    @SerializedName("cell")
    var cell: String = "",
    @SerializedName("dob")
    var dob: Dob = Dob(),
    @SerializedName("email")
    var email: String = "",
    @SerializedName("gender")
    var gender: String = "",
    @SerializedName("id")
    var id: Id = Id(),
    @SerializedName("location")
    var location: Location = Location(),
    @SerializedName("login")
    var login: Login = Login(),
    @SerializedName("name")
    var name: Name = Name(),
    @SerializedName("nat")
    var nat: String = "",
    @SerializedName("phone")
    var phone: String = "",
    @SerializedName("picture")
    var picture: Picture = Picture(),
    @SerializedName("registered")
    var registered: Registered = Registered()
)

@Keep
data class Dob(
    @SerializedName("age")
    var age: Int = 0,
    @SerializedName("date")
    var date: String = ""
)

@Keep
data class Id(
    @SerializedName("name")
    var name: String = "",
    @SerializedName("value")
    var value: Any? = Any()
)

@Keep
data class Location(
    @SerializedName("city")
    var city: String = "",
    @SerializedName("coordinates")
    var coordinates: Coordinates = Coordinates(),
    @SerializedName("country")
    var country: String = "",
    @SerializedName("postcode")
    var postcode: Any? = Any(),
    @SerializedName("state")
    var state: String = "",
    @SerializedName("street")
    var street: Street = Street(),
    @SerializedName("timezone")
    var timezone: Timezone = Timezone()
)

@Keep
data class Login(
    @SerializedName("md5")
    var md5: String = "",
    @SerializedName("password")
    var password: String = "",
    @SerializedName("salt")
    var salt: String = "",
    @SerializedName("sha1")
    var sha1: String = "",
    @SerializedName("sha256")
    var sha256: String = "",
    @SerializedName("username")
    var username: String = "",
    @SerializedName("uuid")
    var uuid: String = ""
)

@Keep
data class Name(
    @SerializedName("first")
    var first: String = "",
    @SerializedName("last")
    var last: String = "",
    @SerializedName("title")
    var title: String = ""
)

@Keep
data class Picture(
    @SerializedName("large")
    var large: String = "",
    @SerializedName("medium")
    var medium: String = "",
    @SerializedName("thumbnail")
    var thumbnail: String = ""
)

@Keep
data class Registered(
    @SerializedName("age")
    var age: Int = 0,
    @SerializedName("date")
    var date: String = ""
)

@Keep
data class Coordinates(
    @SerializedName("latitude")
    var latitude: String = "",
    @SerializedName("longitude")
    var longitude: String = ""
)

@Keep
data class Street(
    @SerializedName("name")
    var name: String = "",
    @SerializedName("number")
    var number: Int = 0
)

@Keep
data class Timezone(
    @SerializedName("description")
    var description: String = "",
    @SerializedName("offset")
    var offset: String = ""
)