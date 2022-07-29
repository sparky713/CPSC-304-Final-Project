CREATE TABLE Player
(
    username    char(60) PRIMARY KEY,
    password    char(80) NOT NULL,
    email       char(80) NOT NULL,
    displayName char(80) NOT NULL,
    UNIQUE (email)
);


/* Tuples in Player table */
INSERT
INTO Player (username, password, email, displayName)
VALUES ('player1', 'password123', 'player1@gmail.com', 'Tiger123')
