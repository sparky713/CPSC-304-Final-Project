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