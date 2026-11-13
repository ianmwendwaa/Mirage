package com.mirage.zion.transactions

import com.mirage.infrastructure.database.MySQLDatabaseFactory
import com.mirage.zion.ReceivedMemberData
import com.mirage.zion.db_tables.Members
import org.jetbrains.exposed.v1.jdbc.insert

class MemberService {
    private val databaseName = "zion_elders_db"

    suspend fun addMember(receivedMemberData: ReceivedMemberData): Int = MySQLDatabaseFactory.dbQuery{
        Members.insert {
            it[Members.memberName] = receivedMemberData.receivedMemberName
            it[Members.memberPhoneNumber] = receivedMemberData.receivedPhoneNumber
            it[Members.memberNumber] = receivedMemberData.receivedMemberNumber
        } [Members.memberId]
    }

//    suspend fun viewMembers(): List<>
}