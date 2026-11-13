// MIRAGE: Coined on 13/11/2026 by Mwendwa while washing utensils.
// Mwendzi's Integrated Advanced General-Purpose Engine

package com.mirage.plugins

import com.mirage.zion.ReceivedMemberData
import com.mirage.zion.transactions.MemberService
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.request.receive

fun Application.configureRouting() {
    val memberService = MemberService()
    routing {
        get("/") {
            call.respondText("Hello, World!")
        }
        post("/create_member"){
            try {
                val newMembersPayload = call.receive<ReceivedMemberData>()

                val insertedId = memberService.addMember(newMembersPayload)
                call.respond(HttpStatusCode.Created, "Member: ${newMembersPayload.receivedMemberName} created successfully!")
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "Error adding member: ${e.message}")
            }
        }
    }
}