-- Drops, Creates, and Inserts into the Database
-- needed for hand-in
-- NOT UPDATED FOR HAND IN

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

INSERT INTO CHARACTERATK (CHARACTER_LEVEL, BASEATK, CURRATK)
VALUES (20, 30, 40);

INSERT INTO CHARACTERATK (CHARACTER_LEVEL, BASEATK, CURRATK)
VALUES (20, 50, 100);

/* Tuples in CHARACTER table */
INSERT
INTO CHARACTER (name, CHARACTER_LEVEL, baseHP, baseATK, ename)
VALUES ('Klee', 10, 801, 24, 'Pyro');

INSERT
INTO CHARACTER (NAME, CHARACTER_LEVEL, BASEHP, BASEATK, ENAME)
VALUES ('Qiqi', 12, 963, 22, 'Cryo');

INSERT
INTO CHARACTER (NAME, CHARACTER_LEVEL, BASEHP, BASEATK, ENAME)
VALUES ('Xinyan', 3, 939, 21, 'Pyro');

INSERT
INTO CHARACTER (NAME, CHARACTER_LEVEL, BASEHP, BASEATK, ENAME)
VALUES ('Xiao', 22, 991, 27, 'Anemo');

INSERT
INTO CHARACTER (NAME, CHARACTER_LEVEL, BASEHP, BASEATK, ENAME)
VALUES ('Mona', 17, 810, 22, 'Hydro');

INSERT
INTO CHARACTER (NAME, CHARACTER_LEVEL, BASEHP, BASEATK, ENAME)
VALUES ('Kaeya', 20, 800, 30, 'Cryo');

INSERT INTO CHARACTER(NAME, CHARACTER_LEVEL, BASEHP, BASEATK, ENAME)
VALUES ('Ayato', 20, 800, 50, 'Hydro');

INSERT INTO CHARACTER(NAME, CHARACTER_LEVEL, BASEHP, BASEATK, ENAME)
VALUES ('Yelan', 20, 800, 30, 'Hydro');


/* Tuples in AbilityDMG table */
INSERT
INTO AbilityDMG (ABILITY_LEVEL, dmg)
VALUES (10, 89);

INSERT
INTO AbilityDMG (ABILITY_LEVEL, DMG)
VALUES (12, 105);

INSERT
INTO AbilityDMG (ABILITY_LEVEL, DMG)
VALUES (3, 54);

INSERT
INTO AbilityDMG (ABILITY_LEVEL, DMG)
VALUES (22, 201);

INSERT
INTO AbilityDMG (ABILITY_LEVEL, DMG)
VALUES (17, 133);

INSERT
INTO ABILITYDMG
VALUES (1, 20);

/* Tuples in Ability table */
INSERT
INTO Ability (aname, cname, ABILITY_LEVEL, cd, dmg)
VALUES ('Sparks and Splash', 'Klee', 10, 38.0, 89);

INSERT
INTO Ability (ANAME, CNAME, ABILITY_LEVEL, CD, DMG)
VALUES ('Preserver of Fortune', 'Qiqi', 12, 25.5, 105);

INSERT
INTO Ability (ANAME, CNAME, ABILITY_LEVEL, CD, DMG)
VALUES ('Riff Revolution', 'Xinyan', 3, 47.0, 54);

INSERT
INTO Ability (ANAME, CNAME, ABILITY_LEVEL, CD, DMG)
VALUES ('Bane of All Evil', 'Xiao', 22, 20.0, 201);

INSERT
INTO Ability (ANAME, CNAME, ABILITY_LEVEL, CD, DMG)
VALUES ('Fate', 'Mona', 17, 12.0, 133);

INSERT
INTO ABILITY
VALUES ('Glacial Waltz', 'Kaeya', 1, 15.0, 20);

INSERT INTO ABILITY (ANAME, CNAME, ABILITY_LEVEL, CD, DMG)
VALUES ('Suiyuu', 'Ayato', 1, 15, 20);

INSERT INTO ABILITY (ANAME, CNAME, ABILITY_LEVEL, CD, DMG)
VALUES ('Dice', 'Yelan', 1, 15, 20);


/* Tuples in Artifact table */
INSERT
INTO Artifact (name)
VALUES ('Adventurers Flower');

INSERT
INTO Artifact (NAME)
VALUES ('Pearl Necklace');

INSERT
INTO Artifact (NAME)
VALUES ('Turtle Talisman');

INSERT
INTO Artifact (NAME)
VALUES ('Silver Crown');

INSERT INTO Artifact (NAME)
VALUES ('Gold Crown');

INSERT INTO Artifact (NAME)
VALUES ('Copper Crown');

INSERT INTO Artifact (NAME)
VALUES ('Emerald Crown');

INSERT INTO Artifact (NAME)
VALUES ('Ruby Crown');

INSERT
INTO Artifact (NAME)
VALUES ('Heart Pin');

INSERT INTO Artifact (NAME)
VALUES ('Gold Pin');

INSERT INTO Artifact (NAME)
VALUES ('Ruby Pin');

INSERT INTO Artifact (NAME)
VALUES ('Emerald Pin');

INSERT INTO Artifact (NAME)
VALUES ('Copper Pin');


/* Tuples in Brooch table */
INSERT
INTO Brooch (aname, bonusHP)
VALUES ('Heart Pin', 100);

INSERT INTO Brooch (ANAME, BONUSHP)
VALUES ('Gold Pin', 50);

INSERT INTO Brooch (ANAME, BONUSHP)
VALUES ('Ruby Pin', 120);

INSERT INTO Brooch (ANAME, BONUSHP)
VALUES ('Emerald Pin', 66);

INSERT INTO Brooch (ANAME, BONUSHP)
VALUES ('Copper Pin', 12);


/* Tuples in Circlet table */
INSERT
INTO Circlet (aname, bonusATK)
VALUES ('Silver Crown', 5);

INSERT INTO Circlet (ANAME, BONUSATK)
VALUES ('Gold Crown', 10);

INSERT INTO Circlet (ANAME, BONUSATK)
VALUES ('Copper Crown', 2);

INSERT INTO Circlet (ANAME, BONUSATK)
VALUES ('Emerald Crown', 25);

INSERT INTO Circlet (ANAME, BONUSATK)
VALUES ('Ruby Crown', 50);

/* Tuples in Food table */
INSERT
INTO Food (name, healAmount)
VALUES ('Mushroom Pizza', 450);

INSERT
INTO Food (NAME, HEALAMOUNT)
VALUES ('Jade Parcels', 500);

INSERT
INTO Food (NAME, HEALAMOUNT)
VALUES ('Fullmoon Egg', 300);

INSERT
INTO Food (NAME, HEALAMOUNT)
VALUES ('Teyvat Fried Egg', 750);

INSERT
INTO Food (NAME, HEALAMOUNT)
VALUES ('Butter Crab', 150);

INSERT
INTO Food (NAME, HEALAMOUNT)
VALUES ('Crystal Shrimp', 900);

INSERT
INTO Food (NAME, HEALAMOUNT)
VALUES ('Five Pickled Treasures', 500);

INSERT
INTO Food (NAME, HEALAMOUNT)
VALUES ('Grilled Tiger Fish', 75);

INSERT
INTO Food (NAME, HEALAMOUNT)
VALUES ('Lotus Flower Crisp', 975);

/* Tuples in Weapon table */
INSERT
INTO Weapon (name, baseATK)
VALUES ('Jade Cutter', 110);

INSERT
INTO Weapon (NAME, BASEATK)
VALUES ('Iron Sting', 109);

INSERT
INTO Weapon (NAME, BASEATK)
VALUES ('Dull Blade', 56);

INSERT INTO Weapon (NAME, BASEATK)
VALUES ('Skyward Blade', 122);

INSERT INTO Weapon (NAME, BASEATK)
VALUES ('Mistsplitter', 133);

INSERT INTO Weapon (NAME, BASEATK)
VALUES ('Alley Hunter', 65);

INSERT INTO Weapon (NAME, BASEATK)
VALUES ('Stringless', 62);

INSERT INTO Weapon (NAME, BASEATK)
VALUES ('Raven Bow', 46);

INSERT INTO Weapon (NAME, BASEATK)
VALUES ('Rust', 70);

INSERT INTO Weapon (NAME, BASEATK)
VALUES ('Skyward Harp', 125);


/* Tuples in Sword table */
INSERT
INTO Sword (wname, hitSpeed)
VALUES ('Jade Cutter', 5);

INSERT
INTO Sword (WNAME, HITSPEED)
VALUES ('Dull Blade', 20);

INSERT
INTO Sword (WNAME, HITSPEED)
VALUES ('Iron Sting', 25);

INSERT INTO Sword (WNAME, HITSPEED)
VALUES ('Skyward Blade', 5);

INSERT INTO Sword (WNAME, HITSPEED)
VALUES ('Mistsplitter', 5);


/* Tuples in Bow table */
INSERT
INTO Bow (wname, chargeTime)
VALUES ('Alley Hunter', 20);

INSERT
INTO Bow (WNAME, CHARGETIME)
VALUES ('Stringless', 15);

INSERT INTO Bow (WNAME, CHARGETIME)
VALUES ('Raven Bow', 10);

INSERT INTO Bow (WNAME, CHARGETIME)
VALUES ('Rust', 5);

INSERT INTO Bow (WNAME, CHARGETIME)
VALUES ('Skyward Harp', 10);

/* Tuples in Consumes table */
INSERT
INTO Consumes (id, USERNAME, FNAME, AMOUNT)
VALUES (2, 'player1', 'Jade Parcels', 5);

INSERT
INTO Consumes (id, USERNAME, FNAME, AMOUNT)
VALUES (3, 'player1', 'Fullmoon Egg', 1);

INSERT
INTO Consumes (id, USERNAME, FNAME, AMOUNT)
VALUES (7, 'player1', 'Mushroom Pizza', 2);

INSERT
INTO Consumes (id, USERNAME, FNAME, AMOUNT)
VALUES (8, 'player1', 'Teyvat Fried Egg', 3);

INSERT
INTO Consumes (id, USERNAME, FNAME, AMOUNT)
VALUES (9, 'player1', 'Butter Crab', 2);

INSERT
INTO Consumes (id, USERNAME, FNAME, AMOUNT)
VALUES (10, 'player1', 'Crystal Shrimp', 5);

INSERT
INTO Consumes (id, USERNAME, FNAME, AMOUNT)
VALUES (11, 'player1', 'Five Pickled Treasures', 1);

INSERT
INTO Consumes (id, USERNAME, FNAME, AMOUNT)
VALUES (12, 'player1', 'Grilled Tiger Fish', 7);

INSERT
INTO Consumes (id, USERNAME, FNAME, AMOUNT)
VALUES (13, 'player1', 'Lotus Flower Crisp', 4);
-- p1
INSERT
INTO Consumes (id, USERNAME, FNAME, AMOUNT)
VALUES (2, 'player2', 'Jade Parcels', 5);

INSERT
INTO Consumes (id, USERNAME, FNAME, AMOUNT)
VALUES (3, 'player2', 'Fullmoon Egg', 1);

INSERT
INTO Consumes (id, USERNAME, FNAME, AMOUNT)
VALUES (7, 'player2', 'Mushroom Pizza', 2);

INSERT
INTO Consumes (id, USERNAME, FNAME, AMOUNT)
VALUES (8, 'player2', 'Teyvat Fried Egg', 3);

INSERT
INTO Consumes (id, USERNAME, FNAME, AMOUNT)
VALUES (9, 'player2', 'Butter Crab', 2);

INSERT
INTO Consumes (id, USERNAME, FNAME, AMOUNT)
VALUES (10, 'player2', 'Crystal Shrimp', 5);

INSERT
INTO Consumes (id, USERNAME, FNAME, AMOUNT)
VALUES (11, 'player2', 'Five Pickled Treasures', 1);

INSERT
INTO Consumes (id, USERNAME, FNAME, AMOUNT)
VALUES (12, 'player2', 'Grilled Tiger Fish', 7);

INSERT
INTO Consumes (id, USERNAME, FNAME, AMOUNT)
VALUES (13, 'player2', 'Lotus Flower Crisp', 4);

-- //player 3
INSERT
INTO Consumes (id, USERNAME, FNAME, AMOUNT)
VALUES (2, 'player3', 'Jade Parcels', 5);

INSERT
INTO Consumes (id, USERNAME, FNAME, AMOUNT)
VALUES (3, 'player3', 'Fullmoon Egg', 1);

INSERT
INTO Consumes (id, USERNAME, FNAME, AMOUNT)
VALUES (7, 'player3', 'Mushroom Pizza', 2);

INSERT
INTO Consumes (id, USERNAME, FNAME, AMOUNT)
VALUES (8, 'player3', 'Teyvat Fried Egg', 3);

INSERT
INTO Consumes (id, USERNAME, FNAME, AMOUNT)
VALUES (9, 'player3', 'Butter Crab', 2);

INSERT
INTO Consumes (id, USERNAME, FNAME, AMOUNT)
VALUES (10, 'player3', 'Crystal Shrimp', 5);

INSERT
INTO Consumes (id, USERNAME, FNAME, AMOUNT)
VALUES (11, 'player3', 'Five Pickled Treasures', 1);

INSERT
INTO Consumes (id, USERNAME, FNAME, AMOUNT)
VALUES (12, 'player3', 'Grilled Tiger Fish', 7);

INSERT
INTO Consumes (id, USERNAME, FNAME, AMOUNT)
VALUES (13, 'player3', 'Lotus Flower Crisp', 4);
-- //player 3
INSERT
INTO Consumes (id, USERNAME, FNAME, AMOUNT)
VALUES (5, 'player4', 'Butter Crab', 3);
-- p5
INSERT
INTO Consumes (id, USERNAME, FNAME, AMOUNT)
VALUES (2, 'player5', 'Jade Parcels', 5);

INSERT
INTO Consumes (id, USERNAME, FNAME, AMOUNT)
VALUES (3, 'player5', 'Fullmoon Egg', 1);

INSERT
INTO Consumes (id, USERNAME, FNAME, AMOUNT)
VALUES (7, 'player5', 'Mushroom Pizza', 2);

INSERT
INTO Consumes (id, USERNAME, FNAME, AMOUNT)
VALUES (8, 'player5', 'Teyvat Fried Egg', 3);

INSERT
INTO Consumes (id, USERNAME, FNAME, AMOUNT)
VALUES (9, 'player5', 'Butter Crab', 2);

INSERT
INTO Consumes (id, USERNAME, FNAME, AMOUNT)
VALUES (10, 'player5', 'Crystal Shrimp', 5);

INSERT
INTO Consumes (id, USERNAME, FNAME, AMOUNT)
VALUES (11, 'player5', 'Five Pickled Treasures', 1);

INSERT
INTO Consumes (id, USERNAME, FNAME, AMOUNT)
VALUES (12, 'player5', 'Grilled Tiger Fish', 7);

INSERT
INTO Consumes (id, USERNAME, FNAME, AMOUNT)
VALUES (13, 'player5', 'Lotus Flower Crisp', 4);
-- p5

/* Tuples in ComprisedOf table */
INSERT
INTO ComprisedOf (username, pname, cname)
VALUES ('player1', 'partyepic', 'Klee');

INSERT
INTO ComprisedOf (USERNAME, PNAME, CNAME)
VALUES ('player2', 'animalParty', 'Qiqi');

INSERT
INTO ComprisedOf (USERNAME, PNAME, CNAME)
VALUES ('player2', 'animalParty', 'Klee');

INSERT
INTO ComprisedOf (USERNAME, PNAME, CNAME)
VALUES ('player2', 'theBestTeam', 'Qiqi');

INSERT
INTO ComprisedOf (USERNAME, PNAME, CNAME)
VALUES ('player2', 'theBestTeam', 'Klee');

INSERT
INTO ComprisedOf (USERNAME, PNAME, CNAME)
VALUES ('player2', 'theBestTeam', 'Xiao');

INSERT
INTO ComprisedOf (USERNAME, PNAME, CNAME)
VALUES ('player2', 'mainParty', 'Klee');

INSERT
INTO ComprisedOf (USERNAME, PNAME, CNAME)
VALUES ('player2', 'mainParty', 'Xinyan');

INSERT
INTO ComprisedOf (USERNAME, PNAME, CNAME)
VALUES ('player2', 'mainParty', 'Mona');

INSERT
INTO ComprisedOf (USERNAME, PNAME, CNAME)
VALUES ('player2', 'battleTeam', 'Mona');

INSERT
INTO ComprisedOf (USERNAME, PNAME, CNAME)
VALUES ('player2', 'battleTeam', 'Klee');

INSERT
INTO ComprisedOf (USERNAME, PNAME, CNAME)
VALUES ('player3', 'coolKidsonly', 'Klee');

INSERT
INTO ComprisedOf (USERNAME, PNAME, CNAME)
VALUES ('player4', 'genshinpartyppl', 'Xinyan');

INSERT
INTO ComprisedOf (USERNAME, PNAME, CNAME)
VALUES ('player4', 'genshinpartyppl', 'Xiao');

INSERT
INTO ComprisedOf (USERNAME, PNAME, CNAME)
VALUES ('player4', 'genshinpartyppl', 'Qiqi');

INSERT
INTO ComprisedOf (USERNAME, PNAME, CNAME)
VALUES ('player5', 'lonerclub', 'Xiao');


/* Tuples in Plays table */
INSERT
INTO Plays (USERNAME, CNAME)
VALUES ('player1', 'Klee');

INSERT
INTO Plays (USERNAME, CNAME)
VALUES ('player1', 'Qiqi');

INSERT
INTO Plays (USERNAME, CNAME)
VALUES ('player1', 'Xinyan');

INSERT
INTO Plays (USERNAME, CNAME)
VALUES ('player1', 'Xiao');

INSERT
INTO Plays (USERNAME, CNAME)
VALUES ('player2', 'Klee');

INSERT
INTO Plays (USERNAME, CNAME)
VALUES ('player2', 'Qiqi');

INSERT
INTO Plays (USERNAME, CNAME)
VALUES ('player2', 'Xinyan');

INSERT
INTO Plays (USERNAME, CNAME)
VALUES ('player2', 'Xiao');

INSERT
INTO Plays (USERNAME, CNAME)
VALUES ('player2', 'Mona');

INSERT
INTO Plays(USERNAME, CNAME)
VALUES ('player3', 'Klee');

INSERT
INTO Plays(USERNAME, CNAME)
VALUES ('player4', 'Xinyan');

INSERT
INTO Plays(USERNAME, CNAME)
VALUES ('player5', 'Xiao');

INSERT
INTO Plays (USERNAME, CNAME)
VALUES ('player4', 'Yelan');

/* Tuples in FightsWith table */
INSERT
INTO FightsWith (cname, wname)
VALUES ('Klee', 'Jade Cutter');

INSERT
INTO FightsWith(cname, wname)
VALUES ('Qiqi', 'Dull Blade');

INSERT
INTO FightsWith(cname, wname)
VALUES ('Xinyan', 'Dull Blade');

INSERT
INTO FightsWith(cname, wname)
VALUES ('Xiao', 'Stringless');

INSERT
INTO FightsWith(cname, wname)
VALUES ('Mona', 'Alley Hunter');

INSERT INTO FIGHTSWITH (cname, wname)
VALUES ('Kaeya', 'Dull Blade');

INSERT INTO FIGHTSWITH(cname, wname)
VALUES ('Ayato', 'Jade Cutter');

INSERT INTO FIGHTSWITH(cname, wname)
VALUES ('Yelan', 'Skyward Harp');

/* Tuples in OwnsWeapon table */
INSERT
INTO OwnsWeapon (USERNAME, WNAME, AMOUNT)
VALUES ('player1', 'Dull Blade', 2);

INSERT
INTO OwnsWeapon (USERNAME, WNAME, AMOUNT)
VALUES ('player2', 'Dull Blade', 2);

INSERT
INTO OwnsWeapon(USERNAME, WNAME, AMOUNT)
VALUES ('player2', 'Alley Hunter', 3);

INSERT
INTO OwnsWeapon(USERNAME, WNAME, AMOUNT)
VALUES ('player2', 'Stringless', 2);

INSERT
INTO OwnsWeapon(USERNAME, WNAME, AMOUNT)
VALUES ('player3', 'Alley Hunter', 3);

INSERT
INTO OwnsWeapon(USERNAME, WNAME, AMOUNT)
VALUES ('player4', 'Jade Cutter', 1);

INSERT
INTO OwnsWeapon(USERNAME, WNAME, AMOUNT)
VALUES ('player5', 'Stringless', 2);

/* Tuples in Wears table */
INSERT
INTO Wears (cname, aname)
VALUES ('Klee', 'Silver Crown');

INSERT
INTO Wears(cname, aname)
VALUES ('Qiqi', 'Heart Pin');

INSERT
INTO Wears(cname, aname)
VALUES ('Xinyan', 'Turtle Talisman');

INSERT
INTO Wears(cname, aname)
VALUES ('Xiao', 'Pearl Necklace');

INSERT
INTO Wears(cname, aname)
VALUES ('Mona', 'Heart Pin');

INSERT
INTO Wears(cname, aname)
VALUES ('Xinyan', 'Adventurers Flower');

INSERT
INTO Wears(cname, aname)
VALUES ('Mona', 'Adventurers Flower');

INSERT
INTO Wears(cname, aname)
VALUES ('Qiqi', 'Gold Crown');

INSERT
INTO Wears(cname, aname)
VALUES ('Xiao', 'Ruby Pin');

INSERT
INTO Wears(cname, aname)
VALUES ('Mona', 'Emerald Crown');
