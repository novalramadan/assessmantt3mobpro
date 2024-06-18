package org.d3if3103.mobpro1.model

import com.squareup.moshi.Json

data class Laptop(
    val id: String,
    val nama_barang: String,
    val spesifikasi: String,
    @Json(name = "image") val imageId: String
//    val mine: Int
)
