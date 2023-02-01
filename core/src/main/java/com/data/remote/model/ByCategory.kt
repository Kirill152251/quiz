package com.data.remote.model

import com.google.gson.annotations.SerializedName

data class ByCategory(
    @SerializedName("Arts & Literature")
    val artAndLit: Int,
    @SerializedName("Film & TV")
    val films: Int,
    @SerializedName("Food & Drink")
    val foodAndDrink: Int,
    @SerializedName("General Knowledge")
    val general: Int,
    @SerializedName("Geography")
    val geography: Int,
    @SerializedName("History")
    val history: Int,
    @SerializedName("Music")
    val music: Int,
    @SerializedName("Science")
    val science: Int,
    @SerializedName("Society & Culture")
    val societyAndCulture: Int,
    @SerializedName("Sport & Leisure")
    val sportAndLeisure: Int
)