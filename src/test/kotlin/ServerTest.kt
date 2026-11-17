package com.mirage

import com.mirage.plugins.configureHttp
import com.mirage.plugins.configureRouting
import com.mirage.plugins.configureSerialization
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import kotlin.test.Test

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

    }
}
