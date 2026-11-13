package com.mirage.infrastructure.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.transactions.experimental.newSuspendedTransaction

object MySQLDatabaseFactory {
    fun init(
        mysqlDatabaseName: String
    ) {
        val config = HikariConfig().apply {
            driverClassName = "com.mysql.cj.jdbc.Driver"
            jdbcUrl = "jdbc:mysql://localhost:3306/$mysqlDatabaseName?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC"
            username = "root"
            password = "Ianmwendwa8435!!"
            maximumPoolSize = 10
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        }

        Database.connect(HikariDataSource(config))
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T = newSuspendedTransaction(
        Dispatchers.IO
    ) {
        block()
    }
}