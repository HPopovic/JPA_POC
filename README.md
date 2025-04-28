## Create Database and Tables

```
CREATE DATABASE demo;

\c demo  -- connect to demo

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL
);

INSERT INTO users (username) VALUES ('alice'), ('bob');
```

```
CREATE TABLE error_logs (
    id SERIAL PRIMARY KEY,
    message TEXT NOT NULL,
    timestamp VARCHAR(50) NOT NULL
);
```

```
CREATE TABLE errors (
    id SERIAL PRIMARY KEY,
    message TEXT NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

```
CREATE TABLE IF NOT EXISTS user_activity_log (
    id SERIAL PRIMARY KEY,
    username TEXT NOT NULL,
    activity_time TIMESTAMP NOT NULL
);
```
## Create Postgres Function and Stored Procedure

Postgres function for read
```
CREATE OR REPLACE FUNCTION get_usernames()
RETURNS TABLE(id INT, username VARCHAR)
LANGUAGE SQL
AS $$
    SELECT id, username FROM users;
$$;
```

Postgres procedure for insert
```
CREATE OR REPLACE PROCEDURE log_username_activity(IN username_input TEXT)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO user_activity_log(username, activity_time)
    VALUES (username_input, NOW());
END;
$$;
```
