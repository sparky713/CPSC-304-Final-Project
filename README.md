# GenshDB   
  <br/>
A Database Application Project inspired by the game 'Genshin Impact'.  <br/>
  <br/>
<b>Developers: Sally Park, Chenwen Shen, Jasmin Mann.</b>  <br/>
  <br/>
  
#### Project Description
Our project models the data involved in a fictional role-playing game. Specifically, the
domain of playable characters, and how players can customize and use aspects of their
characters, such as weapons, abilities and elements, and which characters they use within their
party. This database application is intended to be used by the game players as well as the
developers to access, modify, and delete information. Players of the game are able to access
their inventory and view their profiles, as well as the specific character profiles. They may also
view what specific abilities each character has and select the character that best suits their
needs.<br/>
<br/>

#### Main Page:
<img width="600" alt="completed_tasks_page" src="demo/main_page.png">

#### Sign Up Page:
<img width="250" alt="completed_tasks_page" src="demo/sign_up.png">

#### Abilities Page:
<img width="580" alt="completed_tasks_page" src="demo/projection/abilities.png">

#### Parties Page:
<img width="280" alt="completed_tasks_page" src="demo/aggregation with having/filtered_parties.png">
The image shows the result after filtering the parties according to the min number of members specified and listed in the order specified by the user
<br/>
<br/>

#### Features:
● Create a new player account and profile with a valid username, password, screen name and email. <br/>
      - username is a primary key, therefore ensuring that a username can only be assigned to one user. <br/>
● An Edit Profile page which allows users to update the player’s account information. <br/>
● Players can view their abilities and filter the attributes they wish to view. <br/>
● Filter and view characters based on their base attack values. <br/>
● View how many parties each character is involved in. <br/>
● View the highest level character in each party and filter to only view parties that have highest level value greater than the given level
value chosen by the user. <br/>
● shows the characters that have a base attack value greater than the average base attack values of characters owned by each player. <br/>
<br/>
<b>Task Assignment:</b>  <br/>
  <br/>
Sally: Insert, Projection, Aggregation with Having, Aggregation with group by, Overall GUI management (consistency, aesthetics). <br/>
Wendy: Update, Join, Nested Aggregation. <br/>
Jasmine: Delete, selection, division. <br/>
<br/>
<b>Language:</b> Java, SQL <br/>
<b>Tools, Database, IDE:</b> IntelliJ, Oracle, JDBC <br/>
<b>Tests:</b> JUnit <br/>
