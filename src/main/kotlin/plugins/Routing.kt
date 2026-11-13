// MIRAGE: Coined on 13/11/2026 by Mwendwa while washing utensils.
// Mwendzi's Integrated Advanced General-Purpose Engine

package com.mirage.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.http.content.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello, World!")
        }
        staticResources("/static", "static")
        get("/json/kotlinx-serialization") {
            call.respond(mapOf("hello" to "world"))
        }
    }
}