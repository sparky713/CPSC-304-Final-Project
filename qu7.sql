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
VALUES  ('player2', 'Dull Blade', 2)

INSERT
INTO  OwnsWeapon    ('player2', 'Alley Hunter', 6)

INSERT
INTO  OwnsWeapon    ('player3', 'Alley Hunter', 3)

INSERT
INTO  OwnsWeapon    ('player4', 'Jade Cutter' , 1)

INSERT
INTO  OwnsWeapon    ('player5', 'Stringless', 2)


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
VALUES  ('Klee', 'Jade Cutter')

INSERT
INTO    FightsWith    ('Qiqi', 'Dull Blade')

INSERT
INTO    FightsWith    ('Xinyan', 'Dull Blade')

INSERT
INTO    FightsWith    ('Xiao', 'Stringless')

INSERT
INTO    FightsWith    ('Mona', 'Alley Hunter')



/* Tuples in Wears table */
INSERT
INTO    Wears    (cname, aname)
VALUES  ('Klee', 'Silver Crown')

INSERT
INTO    Wears    ('Qiqi', 'Heart Pin')

INSERT
INTO    Wears    ('Xinyan', 'Turtle Talisman')

INSERT
INTO    Wears    ('Xiao', 'Pearl Necklace')

INSERT
INTO    Wears    ('Mona', 'Heart Pin')



/* Tuples in AbilityCast table */
INSERT
INTO    AbilityCast    (aname, cname, level, cd, dmg)
VALUES  ('Circlet', 'Klee', 10, 38.0, 89)

INSERT
INTO    AbilityCast    ('Preserver of Fortune', 'Qiqi', 12, 25.5, 105)

INSERT
INTO    AbilityCast    ('Riff Revolution', 'Xinyan', 3, 47.0, 54)

INSERT
INTO    AbilityCast    ('Bane of All Evil', 'Xiao', 22, 20.0, 201)

INSERT
INTO    AbilityCast    ('Fate', 'Mona' 17, 12.0, 133)



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


/* Tuples in Weapon table */
INSERT
INTO    Weapon    (name, baseATK)
VALUES  ('Jade Cutter', 48)

INSERT
INTO    Weapon    ('Iron Sting', 35)

INSERT  
INTO    Weapon    ('Dull Blade',  20)

INSERT
INTO    Weapon    ('Alley Hunter', 46)

INSERT 
INTO    Weapon    ('Stringless', 30)


/* Tuples in Sword table */
INSERT
INTO    Sword    (wname, hitSpeed)
VALUES  ('Jade Cutter', 5)

INSERT 
INTO    Sword    ('Dull Blade', 20)

INSERT
INTO    Sword    ('Iron Sting', 25)


/* Tuples in Bow table */
INSERT
INTO    Bow    (wname, chargeTime)
VALUES         ('Alley Hunter', 20)

INSERT 
INTO    Bow    ('Stringless', 15)


/* Tuples in Artifact table */
INSERT
INTO    Artifact    (name)
VALUES  ("Adventurer's Flower")

INSERT
INTO    Artifact    ('Pearl Necklace')

INSERT
INTO    Artifact    ('Turtle Talisman')

INSERT
INTO    Artifact    ('Silver Crown')

INSERT 
INTO    Artifact    ('Heart Pin')



/* Tuples in Brooch table */
INSERT
INTO    Brooch    (aname, bonusHP)
VALUES  ('Heart Pin', 100)



/* Tuples in Circlet table */
INSERT
INTO    Circlet    (aname, bonusATK)
VALUES  ('Silver Crown', 5)