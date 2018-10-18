USE stackoveramt;

DROP PROCEDURE IF EXISTS addUser;
DROP PROCEDURE IF EXISTS addApplication;
DROP PROCEDURE IF EXISTS AddActionLogs;

/* Create a new user */
DELIMITER //
	CREATE PROCEDURE addUser(IN Umail VARCHAR(50), IN Uname VARCHAR(50), IN Upassword VARCHAR(50), IN UisAdmin INT(1), UisActive INT(1))
	BEGIN
		INSERT INTO Users(Umail, Uname, Upassword, UisAdmin, UisActive) VALUES 
        (Umail, Uname, Upassword, UisAdmin, UisActive);
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

/* Create a new actionLogs */
DELIMITER //
	CREATE PROCEDURE AddActionLogs(IN Lid INT(10), IN Lstatus VARCHAR(50), IN Laction VARCHAR(50), IN Ldescription VARCHAR(50), Ltimestamp DATE)
	BEGIN
		INSERT INTO ActionLogs(Lid, Lstatus, Laction, Ldescription, Ltimestamp) VALUES 
        (Lid, Lstatus, Laction, Ldescription, Ltimestamp);
	END //
DELIMITER ;     
    
   
# "TEST"
call addUser('abc', 'abc', 'abc', 1,1);
select * from Users;
call addApplication(1, 'abc', 'abc', 'abc', 'abc','abc');
select * from Applications;
call AddActionLogs(1, 'abc', 'abc', 'abc', 11/02/2017);
select * from ActionLogs;

select * from users inner join applications where users.umail = applications.refumail;