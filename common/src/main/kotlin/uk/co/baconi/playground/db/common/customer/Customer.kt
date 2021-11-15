package uk.co.baconi.playground.db.common.customer

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.date
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDate
import java.time.LocalDateTime

object Customers : IdTable<Long>(name = "customer") {

    override val id = long("id").uniqueIndex().entityId()
    override val primaryKey = PrimaryKey(id)

    val registrationDate = date("registration_date")

    // TODO - Remove once we've added these via db migration framework.
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime())
    val modifiedAt = datetime("modified_at").defaultExpression(CurrentDateTime()) // TODO ON UPDATE CurrentDateTime
}

// TODO - Move into server as importer doesn't need this.
class Customer(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<Customer>(Customers)

    val registrationDate: LocalDate by Customers.registrationDate
    val createdAt: LocalDateTime by Customers.createdAt
    val modifiedAt: LocalDateTime by Customers.modifiedAt
}

// TODO - Replace with DSL than rely on DAO for importer
class MutableCustomer(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<MutableCustomer>(Customers)

    var registrationDate: LocalDate by Customers.registrationDate
    val createdAt: LocalDateTime by Customers.createdAt
    val modifiedAt: LocalDateTime by Customers.modifiedAt
}
