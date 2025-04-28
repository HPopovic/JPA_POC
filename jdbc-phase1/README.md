# Phase 1: Pure JDBC Starter Project

This project demonstrates a basic JDBC connection to a PostgreSQL database without using any frameworks like Spring.

## Prerequisites

- Java 17+
- PostgreSQL running locally with a `demo` database
- A `users` table created via:

```sql
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL
);

INSERT INTO users (username) VALUES ('alice'), ('bob');
```

## How to Run

1. Add the PostgreSQL JDBC driver to your classpath.
2. Update `src/main/resources/db.properties` with your local DB config.
3. Compile and run the project:

```
javac -d out src/main/java/com/example/Main.java
java -cp "out:lib/postgresql-<version>.jar" com.example.Main
```

## What You'll Learn

- How JDBC loads drivers via `Class.forName`
- How to manually connect to a database
- How to write parameterized SQL queries
- How to read and close resources properly