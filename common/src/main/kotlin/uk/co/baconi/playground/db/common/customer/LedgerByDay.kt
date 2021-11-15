package uk.co.baconi.playground.db.common.customer

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.date
import org.jetbrains.exposed.sql.javatime.datetime

object LedgerByDays : IdTable<Long>(name = "ledger_by_day") {

    override val id = reference("customer_id", Customers, onDelete = ReferenceOption.CASCADE)
    val date = date("date")

    override val primaryKey = PrimaryKey(id, date) // Composite Key

    val expenses = decimal("expenses", precision = 12, scale = 2) // Supports up to 1,000,000,000.00
    val returns = decimal("returns", precision = 12, scale = 2)   // Supports up to 1,000,000,000.00

    // TODO - Remove once we've added these via db migration framework.
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime())
    val modifiedAt = datetime("modified_at").defaultExpression(CurrentDateTime()) // TODO ON UPDATE CurrentDateTime

    init {
        uniqueIndex(id, date) // Unique Composite Key
    }
}

// TODO - Move into server as importer doesn't need this.
class LedgerByDay(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<LedgerByDay>(LedgerByDays)
    val customer by Customer referencedOn LedgerByDays.id
    val date by LedgerByDays.date
    val expenses by LedgerByDays.expenses
    val returns by LedgerByDays.returns
    val createdAt by LedgerByDays.createdAt
    val modifiedAt by LedgerByDays.modifiedAt
}

// TODO - Replace with DSL than rely on DAO for importer
class MutableLedgerByDay(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<LedgerByDay>(LedgerByDays)
    var customer by Customer referencedOn LedgerByDays.id
    var date by LedgerByDays.date
    var expenses by LedgerByDays.expenses
    var returns by LedgerByDays.returns
    val createdAt by LedgerByDays.createdAt
    val modifiedAt by LedgerByDays.modifiedAt
}
