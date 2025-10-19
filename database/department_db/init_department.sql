-- Drop the database if it already exists
DROP DATABASE IF EXISTS department_db;
-- Create database
CREATE DATABASE IF NOT EXISTS department_db;
USE department_db;

-- Create table department
DROP TABLE IF EXISTS department;
CREATE TABLE IF NOT EXISTS department (
    id 						INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name 					NVARCHAR(100) NOT NULL UNIQUE KEY,
    total_member			INT	UNSIGNED,
    type					ENUM('DEV','TEST','SCRUM_MASTER','PM') NOT NULL,
    created_at      		DATETIME DEFAULT NOW()
);

 -- =============================================
 -- INSERT DATA
 -- =============================================
 -- Add data department
 INSERT INTO department(	name, 		total_member, 	type, 		    created_at)
 VALUES
 						(N'Marketing'	, 		1,		'DEV', 			'2020-03-05'),
 						(N'Sale'		, 		2,		'TEST', 		'2020-03-05'),
 						(N'Bảo vệ'		, 		3,		'SCRUM_MASTER', '2020-03-07'),
 						(N'Nhân sự'		, 		4,		'PM', 			'2020-03-08'),
 						(N'Kỹ thuật'	, 		5,		'DEV', 			'2020-03-10'),
 						(N'Tài chính'	, 		6,		'SCRUM_MASTER',  NOW()		),
 						(N'Phó giám đốc', 		7,		'PM', 			 NOW()		),
 						(N'Giám đốc'	, 		8,		'TEST', 		'2020-04-07'),
 						(N'Thư kí'		, 		9,		'PM', 			'2020-04-07'),
 						(N'Bán hàng'	, 		1,		'DEV', 			'2020-04-09');