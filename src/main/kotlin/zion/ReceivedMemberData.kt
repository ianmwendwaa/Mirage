package com.mirage.zion

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReceivedMemberData(
    @SerialName("member_name") val receivedMemberName: String,
    @SerialName("member_phone_number") val receivedPhoneNumber: String,
    @SerialName("member_number") val receivedMemberNumber: String
)
