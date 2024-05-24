-- DDL

DROP TABLE IF EXISTS "user";


-- Create the users table
CREATE TABLE "user" (
                        id SERIAL PRIMARY KEY,
                        first_name VARCHAR(50) Unique NOT NULL,
                        last_name VARCHAR(70) NOT NULL,
                        email VARCHAR(255) Unique NOT NULL,
                        role VARCHAR(20) NOT NULL,
                        password VARCHAR(100) NOT NULL
);