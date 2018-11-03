USE stackoveramt;
SET FOREIGN_KEY_CHECKS = 0;
CALL createUser('admin@stackoveramt.ch', 'admin', 'admin', 1, 1);
CALL createUser('user@stackoveramt.ch', 'user', 'user', 0, 1);
CALL createApplication('My super app', 'A super description very very very long', 'api key', 'api secret', 'user@stackoveramt.ch');
SET FOREIGN_KEY_CHECKS = 1;
