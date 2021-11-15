package uk.co.baconi.playground.db.common.customer

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.ReferenceOption.CASCADE
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime
import java.math.BigDecimal
import java.time.LocalDateTime

object NetPositions : IdTable<Long>(name = "net_position") {

    override val id = reference("customer_id", Customers, onDelete = CASCADE).uniqueIndex()
    override val primaryKey = PrimaryKey(id)

    val value = decimal("value", precision = 12, scale = 2) // Supports up to 1,000,000,000.00

    // TODO - Remove once we've added these via db migration framework.
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime())
    val modifiedAt = datetime("modified_at").defaultExpression(CurrentDateTime()) // TODO ON UPDATE CurrentDateTime
}

// TODO - Move into server as importer doesn't need this.
class NetPosition(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<NetPosition>(NetPositions)

    val customer by Customer referencedOn NetPositions.id
    val value: BigDecimal by NetPositions.value
    val createdAt: LocalDateTime by NetPositions.createdAt
    val modifiedAt: LocalDateTime by NetPositions.modifiedAt
}

// TODO - Replace with DSL than rely on DAO for importer
class MutableNetPosition(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<MutableNetPosition>(NetPositions)

    var customer by Customer referencedOn NetPositions.id
    var value: BigDecimal by NetPositions.value
    val createdAt: LocalDateTime by NetPositions.createdAt
    val modifiedAt: LocalDateTime by NetPositions.modifiedAt
}