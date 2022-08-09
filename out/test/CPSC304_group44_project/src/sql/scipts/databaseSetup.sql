-- DROPS ALL TABLES

DROP TABLE PLAYS;
DROP TABLE COMPRISEDOF;
DROP TABLE PARTYCREATION;
DROP TABLE OWNSWEAPON;
DROP TABLE CONSUMES;

DROP TABLE PLAYER;

DROP TABLE FIGHTSWITH;
DROP TABLE WEARS;

DROP TABLE ABILITY;
DROP TABLE ABILITYDMG;

DROP TABLE CHARACTER;
DROP TABLE CHARACTERHP;
DROP TABLE CHARACTERATK;

DROP TABLE ELEMENT;

DROP TABLE BOW;
DROP TABLE SWORD;
DROP TABLE WEAPON;

DROP TABLE FOOD;

DROP TABLE BROOCH;
DROP TABLE CIRCLET;
DROP TABLE ARTIFACT;

-- CREATES ALL TABLES

CREATE TABLE Player
(
    username    varchar2(80) PRIMARY KEY,
    password    varchar2(80) NOT NULL,
    email       varchar2(80) NOT NULL,
    displayName varchar2(80) NOT NULL,
    UNIQUE (email)
);

CREATE TABLE PartyCreation
(
    username  varchar2(80),
    pname     varchar2(80),
    partySize int,
    PRIMARY KEY (username, pname),
    FOREIGN KEY (username) REFERENCES Player
        ON DELETE CASCADE
);

CREATE TABLE Element
(
    name varchar2(80) PRIMARY KEY
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
    name            varchar2(80) PRIMARY KEY,
    character_level int,
    baseHP          int,
    baseATK         int,
    ename           varchar2(80) NOT NULL,
    FOREIGN KEY (ename) REFERENCES Element,
    FOREIGN KEY (character_level, baseHP) REFERENCES CharacterHP,
    FOREIGN KEY (character_level, baseATK) REFERENCES CharacterATK
);

CREATE TABLE AbilityDMG
(
    ability_level int PRIMARY KEY,
    dmg           int
);

CREATE TABLE Ability
(
    aname         varchar2(80) PRIMARY KEY,
    cname         varchar2(80) NOT NULL,
    ability_level int,
    cd            float,
    dmg           int,
    FOREIGN KEY (cname) REFERENCES Character
        ON DELETE CASCADE,
    FOREIGN KEY (ability_level) REFERENCES AbilityDMG
);

CREATE TABLE Food
(
    name       varchar2(80) PRIMARY KEY,
    healAmount int DEFAULT 0
);

CREATE TABLE Weapon
(
    name    varchar2(80) PRIMARY KEY,
    baseATK int
);

CREATE TABLE Sword
(
    wname    varchar2(80) PRIMARY KEY,
    hitSpeed int,
    FOREIGN KEY (wname) REFERENCES Weapon
        ON DELETE CASCADE
);

CREATE TABLE Bow
(
    wname      varchar2(80) PRIMARY KEY,
    chargeTime int,
    FOREIGN KEY (wname) REFERENCES Weapon
        ON DELETE CASCADE
);

CREATE TABLE Artifact
(
    name varchar2(80) PRIMARY KEY
);

CREATE TABLE Brooch
(
    aname   varchar2(80) PRIMARY KEY,
    bonusHP int,
    FOREIGN KEY (aname) REFERENCES Artifact
        ON DELETE CASCADE
);

CREATE TABLE Circlet
(
    aname    varchar2(80) PRIMARY KEY,
    bonusATK int,
    FOREIGN KEY (aname) REFERENCES Artifact
        ON DELETE CASCADE
);

CREATE TABLE OwnsWeapon
(
    username varchar2(80),
    wname    varchar2(80),
    amount   int,
    PRIMARY KEY (username, wname),
    FOREIGN KEY (username) REFERENCES Player
        ON DELETE CASCADE,
    FOREIGN KEY (wname) REFERENCES Weapon
        ON DELETE CASCADE
);

CREATE TABLE Plays
(
    username varchar2(80),
    cname    varchar2(80),
    PRIMARY KEY (username, cname),
    FOREIGN KEY (username) REFERENCES Player
        ON DELETE CASCADE,
    FOREIGN KEY (cname) REFERENCES Character
        ON DELETE CASCADE
);

CREATE TABLE Consumes
(
    id       int,
    username varchar2(80),
    fname    varchar2(80),
    amount   int,
    PRIMARY KEY (id, username, fname),
    FOREIGN KEY (username) REFERENCES Player
        ON DELETE CASCADE,
    FOREIGN KEY (fname) REFERENCES Food
        ON DELETE CASCADE
);

CREATE TABLE ComprisedOf
(
    username varchar2(80),
    pname    varchar2(80),
    cname    varchar2(80),
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
    cname varchar2(80),
    wname varchar2(80),
    PRIMARY KEY (cname, wname),
    FOREIGN KEY (cname) REFERENCES Character
        ON DELETE CASCADE,
    FOREIGN KEY (wname) REFERENCES Weapon
        ON DELETE CASCADE
);

CREATE TABLE Wears
(
    cname varchar2(80),
    aname varchar2(80),
    PRIMARY KEY (cname, aname),
    FOREIGN KEY (cname) REFERENCES Character
        ON DELETE CASCADE,
    FOREIGN KEY (aname) REFERENCES Artifact
        ON DELETE CASCADE
);

-- INSERTS TUPLES INTO THE TABLES

/* Tuples in Player table */
INSERT INTO Player (USERNAME, PASSWORD, EMAIL, DISPLAYNAME)
VALUES ('player1', 'password123', 'player1@gmail.com', 'Tiger123');

INSERT INTO PLAYER (USERNAME, PASSWORD, EMAIL, DISPLAYNAME)
VALUES ('player2', 'donut456', 'donut@gmail.com', 'DonutLover');

INSERT INTO PLAYER (USERNAME, PASSWORD, EMAIL, DISPLAYNAME)
VALUES ('player3', 'animalgirl', 'ilikeanimals@gmail.com', 'AnAnimal');

INSERT INTO PLAYER (USERNAME, PASSWORD, EMAIL, DISPLAYNAME)
VALUES ('player4', 'BlueWhale', 'fish4lyfe@gmail.com', 'Bigbluewhale');

INSERT INTO PLAYER (USERNAME, PASSWORD, EMAIL, DISPLAYNAME)
VALUES ('player5', 'plants', 'cactus223@gmail.com', 'Cactusplayer');

/* Tuples in PartyCreation table */
INSERT
INTO PartyCreation (username, pname, partySize)
VALUES ('player1', 'partyepic', 1);

INSERT
INTO PartyCreation (USERNAME, PNAME, PARTYSIZE)
VALUES ('player2', 'animalParty', 2);

INSERT
INTO PartyCreation (USERNAME, PNAME, PARTYSIZE)
VALUES ('player2', 'theBestTeam', 3);

INSERT
INTO PartyCreation (USERNAME, PNAME, PARTYSIZE)
VALUES ('player2', 'mainParty', 3);

INSERT
INTO PartyCreation (USERNAME, PNAME, PARTYSIZE)
VALUES ('player2', 'battleTeam', 2);

INSERT
INTO PartyCreation (USERNAME, PNAME, PARTYSIZE)
VALUES ('player3', 'coolKidsonly', 1);

INSERT
INTO PartyCreation (USERNAME, PNAME, PARTYSIZE)
VALUES ('player4', 'genshinpartyppl', 3);

INSERT
INTO PartyCreation (USERNAME, PNAME, PARTYSIZE)
VALUES ('player5', 'lonerclub', 1);

/* Tuples in Element table */
INSERT
INTO Element (name)
VALUES ('Cryo');

INSERT
INTO Element (NAME)
VALUES ('Pyro');

INSERT
INTO Element (NAME)
VALUES ('Anemo');

INSERT
INTO Element (NAME)
VALUES ('Hydro');

INSERT
INTO Element (NAME)
VALUES ('Geo');

INSERT
INTO Element (NAME)
VALUES ('Electro');

INSERT
INTO Element (NAME)
VALUES ('Dendro');


/* Tuples in CharacterHP table */
INSERT
INTO CharacterHP (CHARACTER_LEVEL, baseHP, currHP)
VALUES (10, 801, 1021);

INSERT
INTO CharacterHP (CHARACTER_LEVEL, BASEHP, CURRHP)
VALUES (12, 963, 1567);

INSERT
INTO CharacterHP (CHARACTER_LEVEL, BASEHP, CURRHP)
VALUES (3, 939, 2001);

INSERT
INTO CharacterHP (CHARACTER_LEVEL, BASEHP, CURRHP)
VALUES (22, 991, 1347);

INSERT
INTO CharacterHP (CHARACTER_LEVEL, BASEHP, CURRHP)
VALUES (17, 810, 998);

INSERT
INTO CHARACTERHP (CHARACTER_LEVEL, BASEHP, CURRHP)
VALUES (20, 800, 900);


/* Tuples in CharacterATK table */
INSERT
INTO CharacterATK (CHARACTER_LEVEL, baseATK, currATK)
VALUES (10, 24, 27);

INSERT
INTO CharacterATK (CHARACTER_LEVEL, BASEATK, CURRATK)
VALUES (12, 22, 30);

INSERT
INTO CharacterATK (CHARACTER_LEVEL, BASEATK, CURRATK)
VALUES (3, 21, 21);

INSERT
INTO CharacterATK (CHARACTER_LEVEL, BASEATK, CURRATK)
VALUES (22, 27, 33);

INSERT
INTO CharacterATK (CHARACTER_LEVEL, BASEATK, CURRATK)
VALUES (17, 22, 31);
