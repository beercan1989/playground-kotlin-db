# Common
The common shared code between the importer and the server.

## Data Schema

Assumptions:
* Reads of `Expenditure & Returns` will be mutually exclusive between `BY_MONTH` and `BY_DAY`.
* Reads of `Expenditure & Returns` will need the ability to select based on date ranges.
* An external process (from the DB itself) will be managing the deletes of Customer data.

Principals:
* Customer table is there to simplify purging a customer, regardless to current or future tables that store specific customer information.
* Created and Modified At fields are meta fields to be managed by the database itself.
  * Maybe enforced by revoking permissions from the application's database user.
  * https://dba.stackexchange.com/a/226278 `REVOKE UPDATE ON OBJECT::Employees.Salary FROM theUser;`
  * https://dev.mysql.com/doc/refman/5.6/en/revoke.html
  * Or just not grant all update/insert by default https://dev.mysql.com/doc/refman/5.6/en/grant.html

### Customer
Primary Key: Customer ID
* Customer ID (unique number)
* Created At (datetime) {default value}
* Modified At (datetime) {default value + upgrade trigger}

### Expenditure & Returns
Composite key of Customer ID, Type, Date
* Customer ID (foreign key field) {ON DELETE CASCADE}
* Type (BY_MONTH|BY_DAY) {enum list}
* Date (year-month-day) {BY_MONTH always has a day of 1, could be protected by a before insert/update trigger}
* Expense (float)
* Return (float)
* Created At (datetime) {default value}
* Modified At (datetime) {default value + upgrade trigger}

### Statement of Net Position:
Primary Key: Customer ID
* Customer ID (foreign key field) {ON DELETE CASCADE}
* Registration Date (year-month-day)
* Net Position (float)
* Created At (datetime) {default value}
* Modified At (datetime) {default value + upgrade trigger}

## Sources
* SQL created | modified fields - https://stackoverflow.com/questions/31634918/how-do-i-add-a-last-modified-and-created-column-in-a-sql-server-table