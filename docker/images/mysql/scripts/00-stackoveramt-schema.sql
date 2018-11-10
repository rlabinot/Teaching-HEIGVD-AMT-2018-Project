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
  UmustChangePassword INT(1) NOT NULL,
  PRIMARY KEY (Umail)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE OldPasswords(
  OPref VARCHAR(50) NOT NULL,
  OPpassword VARCHAR(50) NOT NULL,
  
  PRIMARY KEY (OPref, OPpassword),
  FOREIGN KEY (OPref) REFERENCES Users(Umail)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE Applications(
  Aid INT(10) AUTO_INCREMENT UNIQUE NOT NULL,
  Aname VARCHAR(50) NOT NULL,
  Adescription VARCHAR(150) NOT NULL,
  AapiKey VARCHAR(50) NOT NULL,
  AapiSecret VARCHAR(50) NOT NULL,
  RefUmail VARCHAR(50) NOT NULL,
  
  PRIMARY KEY (Aid),
  FOREIGN KEY (RefUmail) REFERENCES Users(Umail)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE ActionLogs(
  Lid INT(10) AUTO_INCREMENT UNIQUE NOT NULL,
  Luser VARCHAR(50) NOT NULL,
  Ltimestamp BIGINT NOT NULL,
  Lstatus VARCHAR(50) NOT NULL,
  Laction VARCHAR(50) NOT NULL,
  Ldescription VARCHAR(150) NOT NULL,
  PRIMARY KEY (Lid)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;