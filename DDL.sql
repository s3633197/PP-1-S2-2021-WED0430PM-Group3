CREATE DATABASE IF NOT EXISTS  `project1`;
use `project1`;


CREATE TABLE IF NOT EXISTS roles 
(
  role_id int primary key AUTO_INCREMENT,
  role_name VARCHAR(15)
  );
  
CREATE TABLE IF NOT EXISTS account 
(
  account_id INT primary key AUTO_INCREMENT,
  password VARCHAR(64),
  email VARCHAR(30) UNIQUE,
  create_time DATETIME,
  last_login_time DATETIME,
  role_id INT,
  constraint fk_account_roles foreign key(role_id) references roles(role_id)
  );
 
  
CREATE TABLE IF NOT EXISTS jobSeeker
(
  seeker_id INT primary key AUTO_INCREMENT,
  first_name VARCHAR(50),
  last_name VARCHAR(50),
  gender VARCHAR(10),
  phone INT,
  date_of_birth DATETIME,
  school_name VARCHAR(50),
  major VARCHAR(50),
  educational_background VARCHAR(50),
  skill VARCHAR(100),
  location VARCHAR(100),
  statement VARCHAR(100),
  wanted_industry VARCHAR(50),
  job_type VARCHAR(50),
  expected_salary bigint(64),
  account_id INT,
  constraint fk_account_JobSeeker foreign key(account_id) references account(account_id)
  );
  
CREATE TABLE IF NOT EXISTS company 
(
  company_id INT primary key AUTO_INCREMENT,
  company_name VARCHAR(50),
  start_up_date DATETIME,
  industry VARCHAR(60),
  owner VARCHAR(50),
  account_id INT,
  constraint fk_account_company foreign key(account_id) references account(account_id)
  );
  
CREATE TABLE IF NOT EXISTS Post
(
  post_id INT primary key AUTO_INCREMENT,
  title VARCHAR(50),
  description VARCHAR(200),
  industry VARCHAR(50),
  position VARCHAR(50),
  address VARCHAR(50),
  employment_type VARCHAR(20),
  educational_background VARCHAR(50),
  min_salary bigint(64),
  max_salary bigint(64),
  company_id INT,
  post_time DATETIME,
  update_time DATETIME,
  constraint fk_post_company foreign key(company_id) references company(company_id)
  );

INSERT INTO roles ( role_name )
                  VALUES
                  ( "Personal" ),
                  ( "Company" ),
                  ("Admin");