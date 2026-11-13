package com.mirage.mirage_dump.send

import kotlinx.serialization.Serializable

@Serializable
data class PackagePayload<T>(
    val payloadData: T
)

data class DummyPayload(
    val name: String,
    val email: String,
    val age: Int
)
fun dataclassTemplate(){
    val payloadSrc = PackagePayload<DummyPayload>(
        DummyPayload("Ian", "ian@email.com", 34)
    )
    println(payloadSrc.payloadData.name)
}