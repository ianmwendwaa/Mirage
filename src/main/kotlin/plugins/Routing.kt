// MIRAGE: Coined on 13/11/2026 by Mwendwa while washing utensils.
// Mwendzi's Integrated Advanced General-Purpose Engine

package com.mirage.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// Mock data to be sent
@Serializable
data class MockMemberData(
    @SerialName("member_name") val name: String,
    @SerialName("member_phone_number") val phoneNumber: String,
    @SerialName("member_number") val number: String
)

// Mock data payload to be received
@Serializable
data class ReceivedMemberData(
    val mockId: Int = 0,
    @SerialName("member_name") val memberName: String,
    @SerialName("member_phone_number") val memberPhoneNumber: String,
    @SerialName ("member_number") val memberNumber: String
)

class MemberService {
    val mockMembersList = listOf<MockMemberData>(
        MockMemberData("User 1", "0700000000", "ELDR-USEO-0000"),
        MockMemberData("User 2", "0700000001", "ELDR-USET-0001"),
        MockMemberData("User 3", "0700000002", "ELDR-USET-0002"),
        MockMemberData("User 4", "0700000003", "ELDR-USEF-0003"),
        MockMemberData("User 5", "0700000004", "ELDR-USEF-0004"),
    )
    fun addMember(receivedMemberData: ReceivedMemberData): Int {
        return receivedMemberData.mockId
    }

}
fun Application.configureRouting() {
    val memberService = MemberService()
    routing {
        get("/") {
            call.respondText("Hello Ian! Seeing this means that Mirage is up and ready to get her hands dirty.")
        }
        post("/create_member"){
            try {
                val newMembersPayload = call.receive<ReceivedMemberData>()

                val insertedId = memberService.addMember(newMembersPayload)
                call.respond(HttpStatusCode.OK, "Received member: ${newMembersPayload.memberName} with telephone" +
                        " number: ${newMembersPayload.memberPhoneNumber}")
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "Error adding member: ${e.message}")
            }
        }
        get("/all_members"){
            try {
                val payload = MemberService().mockMembersList
                call.respond(HttpStatusCode.OK, MemberService().mockMembersList)
            }catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "Error: ${e.message}")
            }
        }
    }
}