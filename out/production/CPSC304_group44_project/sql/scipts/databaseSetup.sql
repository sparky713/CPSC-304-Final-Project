-- CREATE TABLE Player
-- (
--     username    char(60) PRIMARY KEY,
--     password    char(80) NOT NULL,
--     email       char(80) NOT NULL,
--     displayName char(80) NOT NULL,
--     UNIQUE (email)
-- );

CREATE TABLE Element
(
    name char(80) PRIMARY KEY
);

CREATE TABLE CharacterHP
(
    character_level int,
    baseHP  int,
    currHP  int,
    CONSTRAINT pk_charHP PRIMARY KEY (character_level, baseHP)
);

CREATE TABLE Character
(
    name    char(80) PRIMARY KEY,
    character_level int,
    baseHP  int,
    baseATK int,
    ename   char(80) NOT NULL,
    FOREIGN KEY (ename) REFERENCES Element,
    FOREIGN KEY (character_level, baseHP) REFERENCES CharacterHP
);

-- /* Tuples in Player table */
-- INSERT
-- INTO Player (username, password, email, displayName)
-- VALUES ('player1', 'password123', 'player1@gmail.com', 'Tiger123');

INSERT INTO CharacterHP(character_level, baseHP, currHP) VALUES (0, 525, 525)

INSERT INTO Element(name)
VALUES ('Cryo')

INSERT INTO Character(name, character_level, baseHP, baseATK, ename) VALUES ('Qiqi', 0, 525, 40, 'Cryo')

SELECT * FROM CHARACTER WHERE name = 'Qiqi'