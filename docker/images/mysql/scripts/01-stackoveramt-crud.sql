USE stackoveramt;

DROP PROCEDURE IF EXISTS createUser;
DROP PROCEDURE IF EXISTS deleteUser;
DROP PROCEDURE IF EXISTS addApplication;
DROP PROCEDURE IF EXISTS deleteApplication;
DROP PROCEDURE IF EXISTS AddActionLogs;
DROP PROCEDURE IF EXISTS deleteActionLogs;

/* Create a new user */
DELIMITER //
	CREATE PROCEDURE createUser(IN Umail VARCHAR(50), IN Uname VARCHAR(50), IN Upassword VARCHAR(50), IN UisAdmin INT(1), UisActive INT(1))
	BEGIN
		INSERT INTO Users(Umail, Uname, Upassword, UisAdmin, UisActive) VALUES 
        (Umail, Uname, Upassword, UisAdmin, UisActive);
	END //
DELIMITER ;

DELIMITER //
	CREATE PROCEDURE deleteUser(IN Umail VARCHAR(50))
	BEGIN
		DELETE FROM Users WHERE Users.Umail LIKE Umail;
	END //
DELIMITER ;

/* Create a new application */
DELIMITER //
	CREATE PROCEDURE addApplication(IN Aid INT(10), Aname VARCHAR(50), Adescription VARCHAR(50), AapiKey VARCHAR(50), AapiSecret VARCHAR(50), RefUmail VARCHAR(50))
	BEGIN
		INSERT INTO Applications(Aid, Aname, Adescription, AapiKey, AapiSecret, RefUmail) VALUES 
        (Aid, Aname, Adescription, AapiKey, AapiSecret, RefUmail);
	END //
DELIMITER ; 

DELIMITER //
	CREATE PROCEDURE deleteApplication(IN id INT(10))
	BEGIN
		DELETE FROM Applications WHERE Applications.Aid LIKE id;
	END //
DELIMITER ; 

/* Create a new actionLogs */
DELIMITER //
	CREATE PROCEDURE AddActionLogs(IN Lid INT(10), IN Lstatus VARCHAR(50), IN Laction VARCHAR(50), IN Ldescription VARCHAR(50), Ltimestamp DATETIME)
	BEGIN
		INSERT INTO ActionLogs(Lid, Lstatus, Laction, Ldescription, Ltimestamp) VALUES 
        (Lid, Lstatus, Laction, Ldescription, Ltimestamp);
	END //
DELIMITER ;     

DELIMITER //
	CREATE PROCEDURE deleteActionLogs(IN Lid INT(10))
	BEGIN
		DELETE FROM ActionLogs WHERE ActionLogs.Lid LIKE Lid;
	END //
DELIMITER ;  
   





