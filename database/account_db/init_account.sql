-- Drop the database if it already exists
DROP DATABASE IF EXISTS account_db;
-- Create database
CREATE DATABASE IF NOT EXISTS account_db;
USE account_db;

-- create table: account
DROP TABLE IF EXISTS account;
CREATE TABLE account(
    id						INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    username				VARCHAR(50) NOT NULL UNIQUE KEY,
    firstname				VARCHAR(50),
    lastname				VARCHAR(50),
    department_id 			INT UNSIGNED NOT NULL
);

 -- =============================================
 -- INSERT DATA
 -- =============================================

 -- Add data account
 INSERT INTO account(	username		,	department_id	)
 VALUES 			(	'dangblack'		,   	'5'			),
 					(	'quanganh'		,		'1'			),
                    (	'vanchien'		,		'1'			),
                    (	'cocoduongqua'	,		'1'			),
                    (	'doccocaubai'	,   	'2'			),
                    (	'khabanh'		,   	'2'			),
                    (	'huanhoahong'	,   	'2'			),
                    (	'tungnui'		,   	'8'			),
                    (	'duongghuu'		,   	'9'			);