-- CREATES ALL TABLES

CREATE TABLE Player
(
    username    char(60) PRIMARY KEY,
    password    char(80) NOT NULL,
    email       char(80) NOT NULL,
    displayName char(80) NOT NULL,
    UNIQUE (email)
);

CREATE TABLE PartyCreation
(
    username  char(80),
    pname     char(80),
    partySize int,
    PRIMARY KEY (username, pname),
    FOREIGN KEY (username) REFERENCES Player
        ON DELETE CASCADE
);

CREATE TABLE Element
(
    name char(80) PRIMARY KEY
);

CREATE TABLE CharacterATK
(
    character_level int,
    baseATK         int,
    currATK         int,
    PRIMARY KEY (character_level, baseATK)
);

CREATE TABLE CharacterHP
(
    character_level int,
    baseHP          int,
    currHP          int,
    CONSTRAINT pk_charHP PRIMARY KEY (character_level, baseHP)
);

CREATE TABLE Character
(
    name            char(80) PRIMARY KEY,
    character_level int,
    baseHP          int,
    baseATK         int,
    ename           char(80) NOT NULL,
    FOREIGN KEY (ename) REFERENCES Element,
    FOREIGN KEY (character_level, baseHP) REFERENCES CharacterHP,
    FOREIGN KEY (character_level, baseATK) REFERENCES CharacterATK
);

CREATE TABLE AbilityDMG
(
    ability_level int PRIMARY KEY,
    dmg           int
);

CREATE TABLE AbilityCast
(
    aname         char(80) PRIMARY KEY,
    cname         char(80) NOT NULL,
    ability_level int,
    cd            float,
    dmg           int,
    FOREIGN KEY (cname) REFERENCES Character
        ON DELETE CASCADE,
    FOREIGN KEY (ability_level) REFERENCES AbilityDMG
);

CREATE TABLE Food
(
    name       char(80) PRIMARY KEY,
    healAmount int DEFAULT 0
);

CREATE TABLE Weapon
(
    name    char(80) PRIMARY KEY,
    baseATK int
);

CREATE TABLE Sword
(
    wname    char(80) PRIMARY KEY,
    hitSpeed int,
    FOREIGN KEY (wname) REFERENCES Weapon
        ON DELETE CASCADE
);

CREATE TABLE Bow
(
    wname      char(80) PRIMARY KEY,
    chargeTime int,
    FOREIGN KEY (wname) REFERENCES Weapon
        ON DELETE CASCADE
);

CREATE TABLE Artifact
(
    name char(80) PRIMARY KEY
);

CREATE TABLE Brooch
(
    aname   char(80) PRIMARY KEY,
    bonusHP int,
    FOREIGN KEY (aname) REFERENCES Artifact
        ON DELETE CASCADE
);

CREATE TABLE Circlet
(
    aname    char(80) PRIMARY KEY,
    bonusATK int,
    FOREIGN KEY (aname) REFERENCES Artifact
        ON DELETE CASCADE
);

CREATE TABLE OwnsWeapon
(
    username char(80),
    wname    char(80),
    amount   int,
    PRIMARY KEY (username, wname),
    FOREIGN KEY (username) REFERENCES Player
        ON DELETE CASCADE,
    FOREIGN KEY (wname) REFERENCES Weapon
        ON DELETE CASCADE
);

CREATE TABLE Plays
(
    username char(80),
    cname    char(80),
    PRIMARY KEY (username, cname),
    FOREIGN KEY (username) REFERENCES Player
        ON DELETE CASCADE,
    FOREIGN KEY (cname) REFERENCES Character
        ON DELETE CASCADE
);

CREATE TABLE Consumes
(
    username char(80),
    fname    char(80),
    amount   int,
    PRIMARY KEY (username, fname),
    FOREIGN KEY (username) REFERENCES Player
        ON DELETE CASCADE,
    FOREIGN KEY (fname) REFERENCES Food
        ON DELETE CASCADE
);

CREATE TABLE ComprisedOf
(
    username char(80),
    pname    char(80),
    cname    char(80),
    PRIMARY KEY (username, pname, cname),
    FOREIGN KEY (username) REFERENCES Player
        ON DELETE CASCADE,
    FOREIGN KEY (username, pname) REFERENCES PartyCreation
        ON DELETE CASCADE,
    FOREIGN KEY (cname) REFERENCES Character
        ON DELETE CASCADE
);

CREATE TABLE FightsWith
(
    cname char(80),
    wname char(80),
    PRIMARY KEY (cname, wname),
    FOREIGN KEY (cname) REFERENCES Character
        ON DELETE CASCADE,
    FOREIGN KEY (wname) REFERENCES Weapon
        ON DELETE CASCADE
);

CREATE TABLE Wears
(
    cname char(80),
    aname char(80),
    PRIMARY KEY (cname, aname),
    FOREIGN KEY (cname) REFERENCES Character
        ON DELETE CASCADE,
    FOREIGN KEY (aname) REFERENCES Artifact
        ON DELETE CASCADE
);

-- /* Tuples in Player table */
-- INSERT
-- INTO Player (username, password, email, displayName)
-- VALUES ('player1', 'password123', 'player1@gmail.com', 'Tiger123');
--
--
--
-- INSERT INTO CharacterHP(character_level, baseHP, currHP)
-- VALUES (0, 525, 525)
--     INSERT
-- INTO Element(name)
-- VALUES ('Cryo')
--
-- INSERT INTO Character (name, character_level, baseHP, baseATK, ename)
-- VALUES ('Qiqi', 0, 525, 40, 'Cryo')
--
-- SELECT *
-- FROM CHARACTER
-- WHERE name = 'Qiqi'
--     INSERT
-- INTO Food(name, healAmount)
-- VALUES ('Mushroom Pizza', 450)
--
-- INSERT
-- INTO Consumes(username, fname, amount)
-- VALUES ('player1', 'Mushroom Pizza', 2)
