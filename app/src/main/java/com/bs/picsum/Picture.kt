package com.bs.picsum

import com.google.gson.annotations.SerializedName

class Picture {
    @SerializedName("id")
    var id: String? = null
    @SerializedName("author")
    var author: String? = null
    @SerializedName("width")
    var width: String? = null
    @SerializedName("url")
    var url: String? = null
    @SerializedName("download_url")
    var download_url: String? = null
}
