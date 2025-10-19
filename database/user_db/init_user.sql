-- Drop the database if it already exists
DROP DATABASE IF EXISTS user_db;
-- Create database
CREATE DATABASE IF NOT EXISTS user_db;
USE user_db;

-- Create table users
DROP TABLE IF EXISTS users;
CREATE TABLE users (
    id bigint NOT NULL AUTO_INCREMENT,
    email varchar(50) NOT NULL,
    password varchar(120) NOT NULL,
    username varchar(20) NOT NULL,
    firstname varchar(20),
    lastname varchar(20),
    access_token varchar(255),
    refresh_token varchar(255),
    role ENUM('USER','ADMIN','MANAGER') NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY (username),
    UNIQUE KEY (email)
 );

  -- Create table refreshtoken
 DROP TABLE IF EXISTS refreshtoken;
 CREATE TABLE refreshtoken (
   id bigint NOT NULL,
   expiry_date datetime(6) NOT NULL,
   token varchar(255) NOT NULL,
   user_id bigint DEFAULT NULL,
   PRIMARY KEY (id),
   UNIQUE KEY (token),
   UNIQUE KEY (user_id),
   CONSTRAINT FOREIGN KEY (user_id) REFERENCES users (id)
 );

 -- Create table refreshtoken_seq
 DROP TABLE IF EXISTS refreshtoken_seq;
 CREATE TABLE refreshtoken_seq (
   next_val bigint DEFAULT NULL
 );

 -- Create table roles
 DROP TABLE IF EXISTS roles;
 CREATE TABLE roles (
   id int NOT NULL AUTO_INCREMENT,
   name enum('ROLE_ADMIN','ROLE_MODERATOR','ROLE_USER') DEFAULT NULL,
   PRIMARY KEY (id)
 );


 -- Create table user_roles
 DROP TABLE IF EXISTS user_roles;
 CREATE TABLE user_roles (
   user_id bigint NOT NULL,
   role_id int NOT NULL,
   PRIMARY KEY (user_id, role_id),
   KEY (role_id),
   CONSTRAINT FOREIGN KEY (role_id) REFERENCES roles (id),
   CONSTRAINT FOREIGN KEY (user_id) REFERENCES users (id)
 );


 -- Add data refreshtoken_seq
 INSERT INTO refreshtoken_seq VALUES (1);

 -- Add data roles
 INSERT INTO roles VALUES (1,'ROLE_USER'),(2,'ROLE_MODERATOR'),(3,'ROLE_ADMIN');