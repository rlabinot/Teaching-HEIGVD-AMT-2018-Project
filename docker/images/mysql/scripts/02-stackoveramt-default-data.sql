USE stackoveramt;
SET FOREIGN_KEY_CHECKS = 0;
CALL createUser('admin@stackoveramt.ch', 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 1, 1, 0);
CALL createUser('user@stackoveramt.ch', 'user', '04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb', 0, 1, 0);
CALL createApplication('My super app', 'A super description very very very long', 'api key', 'api secret', 'user@stackoveramt.ch');
SET FOREIGN_KEY_CHECKS = 1;
