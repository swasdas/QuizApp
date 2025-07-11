# Spring Project – Issue Fixes

## ✅ Issue 1: Unable to Add New Question (ID Already Exists)

**Problem:**  
New questions couldn't be added due to a database error related to duplicate `id`.  
Spring class `Question.java` already had this setup:
```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
However, in PostgreSQL, the id column was not auto-incrementing correctly.
```

**🔧 Fix:**
Run the following SQL in PostgreSQL:

```sql
ALTER TABLE question ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY;
```
Then, to sync the sequence counter with existing max id in the table, run:

```sql
SELECT setval('question_id_seq', (SELECT MAX(id) FROM question));
```

**📌 Explanation:**
PostgreSQL uses an internal counter (called a sequence) to determine the next id value.

If you insert rows manually with specific ids (e.g., during testing or data migration), the sequence might fall behind.
The setval() command sets the sequence to continue after the current highest id in the table to avoid duplicate key errors.

---

