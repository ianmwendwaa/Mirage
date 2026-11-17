package com.mirage.mirage_dump.receive

import kotlinx.serialization.Serializable

@Serializable
data class ReceivePayload<T>(
    val expectedPayload: T
)