/* Tuples in Player table */
INSERT
INTO    Player  (username, password, email, displayName)
VALUES  ('player1', 'password123', 'player1@gmail.com', 'Tiger123')

INSERT
INTO    Player  ('player2', 'donut456', 'donut@gmail.com', 'DonutLover')

INSERT
INTO    Player ('player3', 'animalgirl', 'ilikeanimals@gmail.com', 'AnAnimal')

INSERT
INTO    Player ('player4', 'BlueWhale', 'fish4lyfe@gmail.com', 'Bigbluewhale')

INSERT
INTO    Player ('player5', 'plants', 'cactus223@gmail.com', 'Cactusplayer')


/* Tuples in OwnsWeapon table */
INSERT
INTO    OwnsWeapon  (username, wname, amount)
VALUES  ('player2', 'sword', 2)

INSERT
INTO  OwnsWeapon    ('player2', 'sword', 6)

INSERT
INTO  OwnsWeapon    ('player3', 'sword', 3)

INSERT
INTO  OwnsWeapon    ('player4', 'bow', 10)

INSERT
INTO  OwnsWeapon    ('player5', 'bow', 1)


/* Tuples in Plays table */
INSERT
INTO    Plays   (username, cname)
VALUES  ('player1', 'Klee')

INSERT
INTO    Plays   ('player2', 'Qiqi')

INSERT
INTO    Plays   ('player3', 'Klee')

INSERT
INTO    Plays   ('player4', 'Xinyan')

INSERT
INTO    Plays   ('player5', 'Xiao')


/* Tuples in Consumes table */
INSERT
INTO    Consumes    (username, fname, amount)
VALUES  ('player1', 'Mushroom Pizza', 2)

INSERT
INTO    Consumes    ('player2', 'Jade Parcels', 5)

INSERT
INTO    Consumes    ('player2', 'Fullmoon Egg', 1)

INSERT
INTO    Consumes    ('player3', 'Teyvat Fried Egg', 1)

INSERT
INTO    Consumes    ('player4', 'Butter Crab', 3)

INSERT
INTO    Consumes    ('player5', 'Crystal Shrimp', 7)


/* Tuples in Food table */
INSERT
INTO    Food    (name, healAmount)
VALUES  ('Mushroom Pizza', 450)

INSERT
INTO    Food    ('Jade Parcels', 500)

INSERT
INTO    Food    ('Fullmoon Egg', 300)

INSERT
INTO    Food    ('Teyvat Fried Egg', 750)

INSERT
INTO    Food    ('Butter Crab', 150)

INSERT
INTO    Food    ('Crystal Shrimp', 900)

INSERT
INTO    Food    ('Five Pickled Treasures', 500)

INSERT
INTO    Food    ('Grilled Tiger Fish', 75)

INSERT
INTO    Food    ('Lotus Flower Crisp', 975)


/* Tuples in PartyCreation table */
INSERT
INTO    PartyCreation    (username, pname, partySize)
VALUES  ('player1', 'partyepic', 1)

INSERT
INTO    PartyCreation    ('player2', 'animalParty', 2)

INSERT
INTO    PartyCreation    ('player3', 'coolKidsonly', 1)

INSERT
INTO    PartyCreation    ('player4', 'genshinpartyppl', 3)

INSERT
INTO    PartyCreation    ('player5', 'lonerclub', 1)


/* Tuples in ComprisedOf table */
INSERT
INTO    ComprisedOf    (username, pname, cname)
VALUES  ('player1', 'partyepic', 'Klee')

INSERT
INTO    ComprisedOf    ('player2', 'animalParty', 'Qiqi')

INSERT
INTO    ComprisedOf    ('player2', 'animalParty', 'Klee')

INSERT
INTO    ComprisedOf    ('player3', 'coolKidsonly', 'Klee')

INSERT
INTO    ComprisedOf    ('player4', 'genshinpartyppl', 'Xinyan')

INSERT
INTO    ComprisedOf    ('player4', 'genshinpartyppl', 'Xiao')

INSERT
INTO    ComprisedOf    ('player4', 'genshinpartyppl', 'Qiqi')

INSERT
INTO    ComprisedOf    ('player5', 'lonerclub', 'Xiao')


/* Tuples in CharacterHas table */
INSERT
INTO    CharacterHas    (name, level, baseHP, baseATK, ename)
VALUES  ('Klee', 10, 801, 24, 'Pyro')

INSERT
INTO    CharacterHas    ('Qiqi', 12, 963, 22, 'Cryo')

INSERT
INTO    CharacterHas    ('Xinyan', 3, 939, 21, 'Pyro')

INSERT
INTO    CharacterHas    ('Xiao', 22, 991, 27, 'Anemo')

INSERT
INTO    CharacterHas    ('Mona', 17, 810, 22, 'Hydro')



/* Tuples in CharacterHP table */
INSERT
INTO    CharacterHP    (level, baseHP, currHP)
VALUES  (10, 801, 1021)

INSERT
INTO    CharacterHP    (12, 963, 1567)

INSERT
INTO    CharacterHP    (3, 939, 2001)

INSERT
INTO    CharacterHP    (22, 991, 1347)

INSERT
INTO    CharacterHP    (17, 810, 998)


/* Tuples in CharacterATK table */
INSERT
INTO    CharacterATK    (level, baseATK, currATK)
VALUES  (10, 24, 27)

INSERT
INTO    CharacterATK    (12, 22, 30)

INSERT
INTO    CharacterATK    (3, 21, 21)

INSERT
INTO    CharacterATK    (22, 27, 33)

INSERT
INTO    CharacterATK    (17, 22, 31)


/* Tuples in FightsWith table */
INSERT
INTO    FightsWith    (cname, wname)
VALUES  ('Klee', 'sword')

INSERT
INTO    FightsWith    ('Qiqi', 'sword')

INSERT
INTO    FightsWith    ('Xinyan', 'bow')

INSERT
INTO    FightsWith    ('Xiao', 'bow')

INSERT
INTO    FightsWith    ('Mona', 'bow')



/* Tuples in Wears table */
INSERT
INTO    Wears    (cname, aname)
VALUES  ('Klee', 'sword')

INSERT
INTO    Wears    ('Qiqi', 'sword')

INSERT
INTO    Wears    ('Xinyan', 'bow')

INSERT
INTO    Wears    ('Xiao', 'bow')

INSERT
INTO    Wears    ('Mona', 'bow')



/* Tuples in AbilityCast table */
INSERT
INTO    AbilityCast    (aname, cname, level, cd, dmg)
VALUES  ('Circlet', 'Klee', 10, 38.0, 89)

INSERT
INTO    AbilityCast    ('Brooch', 'Qiqi', 12, 25.5, 105)

INSERT
INTO    AbilityCast    ('Circlet', 'Xinyan', 3, 47.0, 54)

INSERT
INTO    AbilityCast    ('Circlet', 'Xiao', 22, 20.0, 201)

INSERT
INTO    AbilityCast    ('Brooch', 'Mona' 17, 12.0, 133)



/* Tuples in AbilityDMG table */
INSERT
INTO    AbilityDMG    (level, dmg)
VALUES  (10, 89)

INSERT
INTO    AbilityDMG    (12, 105)

INSERT
INTO    AbilityDMG    (3, 54)

INSERT
INTO    AbilityDMG    (22, 201)

INSERT
INTO    AbilityDMG    (17, 133)



/* Tuples in Element table */
INSERT
INTO    Element    (name)
VALUES  ('Cryo')

INSERT
INTO    Element    ('Pyro')

INSERT
INTO    Element    ('Anemo')

INSERT
INTO    Element    ('Hydro')

INSERT
INTO    Element    ('Geo')

INSERT
INTO    Element    ('Electro')

INSERT
INTO    Element    ('Dendro')



///////////////FIX///////////////
is there more than one type of weapon??
/* Tuples in Weapon table */
INSERT
INTO    Weapon    (name, baseATK)
VALUES  ('sword', 48)

INSERT
INTO    Weapon    ('bow', 46)


///////////////FIX///////////////
??? are there diff types of swords?????
/* Tuples in Sword table */
INSERT
INTO    Sword    (wname, hitSpeed)
VALUES  ('sword', 90)


///////////////FIX///////////////
are there diff types of bows?????
/* Tuples in Bow table */
INSERT
INTO    Bow    (wname, chargeTime)
VALUES  ('bow', 20)


///////////////FIX///////////////
 are there other types of artifacts?
/* Tuples in Artifact table */
INSERT
INTO    Artifact    (name)
VALUES  ('Brooch')

INSERT
INTO    Artifact    ('Circlet')



/* Tuples in Brooch table */
INSERT
INTO    Brooch    (aname, bonusHP)
VALUES  ('Brooch', 100)



/* Tuples in Circlet table */
INSERT
INTO    Circlet    (aname, bonusATK)
VALUES  ('Brooch', 5)



