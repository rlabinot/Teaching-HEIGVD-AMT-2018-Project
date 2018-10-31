DROP SCHEMA IF EXISTS stackoveramt;

CREATE SCHEMA stackoveramt;

USE stackoveramt;

SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE Users(
  Umail VARCHAR(50) UNIQUE NOT NULL,
  Uname VARCHAR(50) NOT NULL,
  Upassword VARCHAR(50) NOT NULL,
  UisAdmin INT(1) NOT NULL,
  UisActive INT(1) NOT NULL,
  PRIMARY KEY (Umail)
);

CREATE TABLE Applications(
  Aid INT(10) AUTO_INCREMENT UNIQUE NOT NULL,
  Aname VARCHAR(50) NOT NULL,
  Adescription VARCHAR(50) NOT NULL,
  AapiKey VARCHAR(50) NOT NULL,
  AapiSecret VARCHAR(50) NOT NULL,
  RefUmail VARCHAR(50) NOT NULL,
  
  PRIMARY KEY (Aid),
  FOREIGN KEY (RefUmail) REFERENCES Users(Umail)
);

CREATE TABLE ActionLogs(
  Lid INT(10) AUTO_INCREMENT UNIQUE NOT NULL,
  Luser VARCHAR(50) NOT NULL,
  Ltimestamp DATETIME NOT NULL,
  Lstatus VARCHAR(50) NOT NULL,
  Laction VARCHAR(50) NOT NULL,
  Ldescription VARCHAR(50) NOT NULL,
  PRIMARY KEY (Lid)
);

SET FOREIGN_KEY_CHECKS = 1;