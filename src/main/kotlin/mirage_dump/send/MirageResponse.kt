package com.mirage.mirage_dump.send

import kotlinx.serialization.Serializable

@Serializable
data class MirageResponse(
    val status: Int,
    val message: String
)
