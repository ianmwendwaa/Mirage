package com.mirage

import com.mirage.plugins.configureHttp
import com.mirage.plugins.configureRouting
import com.mirage.plugins.configureSerialization
import com.mirage.zion.ReceivedMemberData
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ServerTest {

    @Test
    fun test_server_response() = testApplication {
        application {
            // Install content negotiation
            configureSerialization()
            module()
            configureRouting()
            configureHttp()
        }

        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }
        val dummyPayload = ReceivedMemberData(
            "Ian Mwendwa",
            "0791930433",
            "ELDR-IANM-0433"
        )
        val response = client.post("/create_member"){
            contentType(ContentType.Application.Json)
            setBody(dummyPayload)
        }
        assertEquals(HttpStatusCode.Created, response.status)
    }
}
