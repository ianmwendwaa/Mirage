package com.mirage.zion.db_tables

import org.jetbrains.exposed.v1.core.Table

object Members: Table("zion_elders_members") {
    val memberId = integer("member_id").autoIncrement()
    val memberName = varchar("member_name", 30)
    val memberPhoneNumber = varchar("member_phone_number", 10)
    val memberNumber = varchar("member_number", 20)
}