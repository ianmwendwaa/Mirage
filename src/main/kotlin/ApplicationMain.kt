package com.mirage

import com.mirage.infrastructure.database.MySQLDatabaseFactory
import io.ktor.server.application.Application

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}
fun Application.module() {
    MySQLDatabaseFactory.init("zion_elders_db")
}