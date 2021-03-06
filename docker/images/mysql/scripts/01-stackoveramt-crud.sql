USE stackoveramt;

DROP PROCEDURE IF EXISTS countUser;
DROP PROCEDURE IF EXISTS createUser;
DROP PROCEDURE IF EXISTS readUser;
DROP PROCEDURE IF EXISTS readAllUser;
DROP PROCEDURE IF EXISTS readAllUserOffset;
DROP PROCEDURE IF EXISTS userLogin;
DROP PROCEDURE IF EXISTS updateUser;
DROP PROCEDURE IF EXISTS deleteUser;
DROP PROCEDURE IF EXISTS changeUserPassword;
DROP PROCEDURE IF EXISTS changeUserState;
DROP PROCEDURE IF EXISTS createOldPassword;
DROP PROCEDURE IF EXISTS readOldPasswordFromUser;
DROP PROCEDURE IF EXISTS deleteOldPasswordFromUser;
DROP PROCEDURE IF EXISTS countApplicationFromUser;
DROP PROCEDURE IF EXISTS createApplication;
DROP PROCEDURE IF EXISTS readApplication;
DROP PROCEDURE IF EXISTS readApplicationFromUser;
DROP PROCEDURE IF EXISTS readApplicationFromUserOffset;
DROP PROCEDURE IF EXISTS updateApplication;
DROP PROCEDURE IF EXISTS deleteApplication;
DROP PROCEDURE IF EXISTS deleteAllApplicationFromUser;
DROP PROCEDURE IF EXISTS countActionLogs;
DROP PROCEDURE IF EXISTS createActionLogs;
DROP PROCEDURE IF EXISTS readActionLogs;
DROP PROCEDURE IF EXISTS readAllActionLogs;
DROP PROCEDURE IF EXISTS readAllActionLogsOffset;
DROP PROCEDURE IF EXISTS updateActionLogs;
DROP PROCEDURE IF EXISTS deleteActionLogs;

/* CRUD over a user */
DELIMITER //
	CREATE PROCEDURE countUser()
	BEGIN
		SELECT COUNT(*) as "nb" FROM Users;
	END //
DELIMITER ;

DELIMITER //
	CREATE PROCEDURE createUser(IN Umail VARCHAR(50), IN Uname VARCHAR(50), IN Upassword CHAR(64), IN UisAdmin INT(1), IN UisActive INT(1), IN UmustChangePassword INT(1))
	BEGIN
		INSERT INTO Users(Umail, Uname, Upassword, UisAdmin, UisActive, UmustChangePassword) VALUES 
        (Umail, Uname, Upassword, UisAdmin, UisActive, UmustChangePassword);
	END //
DELIMITER ;

DELIMITER //
	CREATE PROCEDURE readUser(IN Umail VARCHAR(50))
	BEGIN
		SELECT * FROM Users WHERE Users.Umail LIKE Umail;
	END //
DELIMITER ;

DELIMITER //
	CREATE PROCEDURE readAllUser()
	BEGIN
		SELECT * FROM Users;
	END //
DELIMITER ;

DELIMITER //
	CREATE PROCEDURE readAllUserOffset(IN offset INT(10), IN size INT(10))
	BEGIN
		SELECT * 
		FROM Users
		LIMIT offset, size;
	END //
DELIMITER ;

DELIMITER //
	CREATE PROCEDURE userLogin(IN Umail VARCHAR(50), IN Upassword CHAR(64))
	BEGIN
		SELECT * FROM Users WHERE Users.Umail LIKE BINARY Umail AND Users.Upassword LIKE BINARY Upassword;
	END //
DELIMITER ;

DELIMITER //
	CREATE PROCEDURE updateUser(IN Umail VARCHAR(50), IN Uname VARCHAR(50), IN Upassword CHAR(64), IN UisAdmin INT(1), IN UisActive INT(1), IN UmustChangePassword INT(1))
	BEGIN
		UPDATE Users
        SET Users.Uname=Uname, Users.Upassword=Upassword, Users.UisAdmin=UisAdmin, Users.UisActive=UisActive, Users.UmustChangePassword=UmustChangePassword
        WHERE Users.Umail LIKE BINARY Umail;
	END //
DELIMITER ;

DELIMITER //
	CREATE PROCEDURE changeUserPassword(IN Umail VARCHAR(50), IN Upassword CHAR(64), IN UmustChangePassword INT(1))
	BEGIN
		UPDATE Users
        SET Users.Upassword=Upassword, Users.UmustChangePassword=UmustChangePassword
        WHERE Users.Umail LIKE BINARY Umail;
	END //
DELIMITER ;

DELIMITER //
	CREATE PROCEDURE deleteUser(IN Umail VARCHAR(50))
	BEGIN
		DELETE FROM Users WHERE Users.Umail LIKE BINARY Umail;
	END //
DELIMITER ;

DELIMITER //
	CREATE PROCEDURE changeUserState(IN Umail VARCHAR(50), IN UisActive INT(1))
	BEGIN
		UPDATE Users
        SET Users.UisActive=UisActive
        WHERE Users.Umail LIKE BINARY Umail;
	END //
DELIMITER ;

/* CRUD over an old password */
DELIMITER //
	CREATE PROCEDURE createOldPassword(IN OPref VARCHAR(50), IN OPpassword CHAR(64))
	BEGIN
		INSERT INTO OldPasswords(OPref, OPpassword) VALUES 
        (OPref, OPpassword);
	END //
DELIMITER ;

DELIMITER //
	CREATE PROCEDURE readOldPasswordFromUser(IN OPref VARCHAR(50))
	BEGIN
		SELECT * FROM OldPasswords WHERE OPref LIKE BINARY OPref;
	END //
DELIMITER ;

DELIMITER //
	CREATE PROCEDURE deleteOldPasswordFromUser(IN OPref VARCHAR(50))
	BEGIN
		DELETE FROM OldPasswords WHERE OldPasswords.OPref LIKE BINARY OPref;
	END //
DELIMITER ;

/* CRUD over an application */
DELIMITER //
	CREATE PROCEDURE countApplicationFromUser(IN RefUmail VARCHAR(50))
	BEGIN
		SELECT COUNT(*) as "nb" FROM Applications WHERE Applications.RefUmail LIKE BINARY RefUmail;
	END //
DELIMITER ; 

DELIMITER //
	CREATE PROCEDURE createApplication(IN Aname VARCHAR(50), IN Adescription VARCHAR(150), IN AapiKey VARCHAR(50), IN AapiSecret VARCHAR(50), IN RefUmail VARCHAR(50))
	BEGIN
		INSERT INTO Applications(Aname, Adescription, AapiKey, AapiSecret, RefUmail) VALUES 
        (Aname, Adescription, AapiKey, AapiSecret, RefUmail);
	END //
DELIMITER ; 

DELIMITER //
	CREATE PROCEDURE readApplication(IN id INT(10), IN RefUmail VARCHAR(50))
	BEGIN
		SELECT * FROM Applications WHERE Applications.Aid LIKE BINARY id AND Applications.RefUmail LIKE BINARY RefUmail;
	END //
DELIMITER ; 

DELIMITER //
	CREATE PROCEDURE readApplicationFromUser(IN RefUmail VARCHAR(50))
	BEGIN
		SELECT * 
		FROM Applications 
		WHERE Applications.RefUmail 
		LIKE RefUmail;
	END //
DELIMITER ; 

DELIMITER //
	CREATE PROCEDURE readApplicationFromUserOffset(IN RefUmail VARCHAR(50), IN offset INT(10), IN size INT(10))
	BEGIN
		SELECT * 
		FROM Applications 
		WHERE Applications.RefUmail LIKE BINARY RefUmail 
		LIMIT offset, size;
	END //
DELIMITER ; 

DELIMITER //
	CREATE PROCEDURE updateApplication(IN Aid INT(10), IN Aname VARCHAR(50), IN Adescription VARCHAR(150), IN RefUmail VARCHAR(50))
	BEGIN
		UPDATE Applications
        SET Applications.Aname=Aname, Applications.Adescription=Adescription
        WHERE Applications.RefUmail LIKE BINARY RefUmail AND Applications.Aid LIKE Aid;
	END //
DELIMITER ; 

DELIMITER //
	CREATE PROCEDURE deleteApplication(IN id INT(10), IN RefUmail VARCHAR(50))
	BEGIN
		DELETE FROM Applications WHERE Applications.Aid LIKE id AND Applications.RefUmail LIKE BINARY RefUmail;
	END //
DELIMITER ; 

DELIMITER //
	CREATE PROCEDURE deleteAllApplicationFromUser(IN RefUmail VARCHAR(50))
	BEGIN
		DELETE FROM Applications WHERE Applications.RefUmail LIKE BINARY RefUmail;
	END //
DELIMITER ; 

/* CRUD over a log */

DELIMITER //
	CREATE PROCEDURE countActionLogs()
	BEGIN
		SELECT COUNT(*) as "nb" FROM ActionLogs;
	END //
DELIMITER ;


DELIMITER //
	CREATE PROCEDURE createActionLogs(IN Luser VARCHAR(50), IN Ltimestamp BIGINT, IN Lstatus VARCHAR(50), IN Laction VARCHAR(50), IN Ldescription VARCHAR(150))
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
	CREATE PROCEDURE readAllActionLogs()
	BEGIN
		SELECT * FROM ActionLogs ORDER BY Ltimestamp DESC;
	END //
DELIMITER ;

DELIMITER //
	CREATE PROCEDURE readAllActionLogsOffset(IN offset INT(10), IN size INT(10))
	BEGIN
		SELECT * FROM ActionLogs ORDER BY Ltimestamp DESC LIMIT offset, size;
	END //
DELIMITER ;

DELIMITER //
	CREATE PROCEDURE updateActionLogs(IN Lid INT(10), IN Luser VARCHAR(50), IN Ltimestamp BIGINT, IN Lstatus VARCHAR(50), IN Laction VARCHAR(50), IN Ldescription VARCHAR(150))
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
