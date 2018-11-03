USE stackoveramt;

DROP PROCEDURE IF EXISTS createUser;
DROP PROCEDURE IF EXISTS readUser;
DROP PROCEDURE IF EXISTS updateUser;
DROP PROCEDURE IF EXISTS deleteUser;
DROP PROCEDURE IF EXISTS createApplication;
DROP PROCEDURE IF EXISTS readApplication;
DROP PROCEDURE IF EXISTS readApplicationFromUser;
DROP PROCEDURE IF EXISTS updateApplication;
DROP PROCEDURE IF EXISTS deleteApplication;
DROP PROCEDURE IF EXISTS createActionLogs;
DROP PROCEDURE IF EXISTS readActionLogs;
DROP PROCEDURE IF EXISTS updateActionLogs;
DROP PROCEDURE IF EXISTS deleteActionLogs;

/* CRUD over a user */
DELIMITER //
	CREATE PROCEDURE createUser(IN Umail VARCHAR(50), IN Uname VARCHAR(50), IN Upassword VARCHAR(50), IN UisAdmin INT(1), IN UisActive INT(1))
	BEGIN
		INSERT INTO Users(Umail, Uname, Upassword, UisAdmin, UisActive) VALUES 
        (Umail, Uname, Upassword, UisAdmin, UisActive);
	END //
DELIMITER ;

DELIMITER //
	CREATE PROCEDURE readUser(IN Umail VARCHAR(50))
	BEGIN
		SELECT * FROM Users WHERE Users.Umail LIKE Umail;
	END //
DELIMITER ;

DELIMITER //
	CREATE PROCEDURE updateUser(IN Umail VARCHAR(50), IN Uname VARCHAR(50), IN Upassword VARCHAR(50), IN UisAdmin INT(1), IN UisActive INT(1))
	BEGIN
		UPDATE Users
        SET Users.Uname=Uname, Users.Upassword=Upassword, Users.UisAdmin=UisAdmin, Users.UisActive=UisActive
        WHERE Users.Umail LIKE Umail;
	END //
DELIMITER ;

DELIMITER //
	CREATE PROCEDURE deleteUser(IN Umail VARCHAR(50))
	BEGIN
		DELETE FROM Users WHERE Users.Umail LIKE Umail;
	END //
DELIMITER ;

/* CRUD over an application */
DELIMITER //
	CREATE PROCEDURE createApplication(IN Aname VARCHAR(50), IN Adescription VARCHAR(150), IN AapiKey VARCHAR(50), IN AapiSecret VARCHAR(50), IN RefUmail VARCHAR(50))
	BEGIN
		INSERT INTO Applications(Aname, Adescription, AapiKey, AapiSecret, RefUmail) VALUES 
        (Aname, Adescription, AapiKey, AapiSecret, RefUmail);
	END //
DELIMITER ; 

DELIMITER //
	CREATE PROCEDURE readApplication(IN id INT(10))
	BEGIN
		SELECT * FROM Applications WHERE Applications.Aid LIKE id;
	END //
DELIMITER ; 

DELIMITER //
	CREATE PROCEDURE readApplicationFromUser(IN RefUmail VARCHAR(50))
	BEGIN
		SELECT * FROM Applications WHERE Applications.RefUmail LIKE RefUmail;
	END //
DELIMITER ; 

DELIMITER //
	CREATE PROCEDURE updateApplication(IN Aid INT(10), IN Aname VARCHAR(50), IN Adescription VARCHAR(150))
	BEGIN
		UPDATE Applications
        SET Applications.Aname=Aname, Applications.Adescription=Adescription
        WHERE Applications.Aid=Aid;
	END //
DELIMITER ; 

DELIMITER //
	CREATE PROCEDURE deleteApplication(IN id INT(10))
	BEGIN
		DELETE FROM Applications WHERE Applications.Aid LIKE id;
	END //
DELIMITER ; 

/* CRUD over a log */
DELIMITER //
	CREATE PROCEDURE createActionLogs(IN Luser VARCHAR(50), IN Ltimestamp DATETIME, IN Lstatus VARCHAR(50), IN Laction VARCHAR(50), IN Ldescription VARCHAR(50))
	BEGIN
		INSERT INTO ActionLogs(Luser, Ltimestamp, Lstatus, Laction, Ldescription) VALUES 
        (Luser, Ltimestamp, Lstatus, Laction, Ldescription);
	END //
DELIMITER ;

DELIMITER //
	CREATE PROCEDURE readActionLogs(IN Lid INT(10))
	BEGIN
		SELECT * FROM ActionLogs WHERE ActionLogs.Lid LIKE Lid;
	END //
DELIMITER ;      

DELIMITER //
	CREATE PROCEDURE updateActionLogs(IN Lid INT(10), IN Luser VARCHAR(50), IN Ltimestamp DATETIME, IN Lstatus VARCHAR(50), IN Laction VARCHAR(50), IN Ldescription VARCHAR(50))
	BEGIN
		UPDATE ActionLogs
        SET ActionLogs.Luser=Luser, ActionLogs.Ltimestamp=Ltimestamp, ActionLogs.Lstatus=Lstatus, ActionLogs.Laction=Laction, ActionLogs.Ldescription=Ldescription
        WHERE ActionLogs.Lid LIKE Lid; 
	END //
DELIMITER ;  

DELIMITER //
	CREATE PROCEDURE deleteActionLogs(IN Lid INT(10))
	BEGIN
		DELETE FROM ActionLogs WHERE ActionLogs.Lid LIKE Lid;
	END //
DELIMITER ;  
   





