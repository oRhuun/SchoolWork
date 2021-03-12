/*1  List all attributes of comments containing the ‘word’ lol, 
ordered by decreasing Poster and increasingRecipient*/
SELECT *
FROM Comments
WHERE Text like '%lol%'
ORDER BY Poster desc, Recipient;

/*2 Display, without duplicates, the Posters of one gender who 
posted to Recipients of a different gender
(display Poster and Recipint Ids and Genders).*/
SELECT distinct Poster, pUser.Gender AS PosterGender, Recipient, rUser.Gender AS RecipientGender
FROM Comments
INNER JOIN Users as pUser ON pUser.Id = Comments.Poster
INNER JOIN Users as rUser ON rUser.Id = Comments.Recipient
WHERE NOT pUser.Gender = rUser.Gender;

/*3 List all attributes of comments posted this year either 
by Poster with name Chris or to Recipient with name Ellen.*/
Select Comments.*
FROM Comments
INNER JOIN Users AS PosterUsers ON PosterUsers.Id = Comments.Poster
INNER JOIN Users AS RecipientUsers ON RecipientUsers.Id = Comments.Recipient
WHERE PostDate like '%2020%'
AND (PosterUsers.`Name` = 'Chris'
OR RecipientUsers.`Name` =  'Ellen');


/*4  For each user, find the number of friends of each gender 
(output should have 3 columns: Id, Gender, Count).*/
SELECT person.Id, friendUser.Gender AS fGender, COUNT(*) AS NumFriends
FROM Users AS person
INNER JOIN Friends ON person.id = Friends.Id1
INNER JOIN Users AS friendUser ON friendUser.id = Friends.Id2
GROUP BY Id1, friendUser.Gender;

/*5 List all attributes of Users who do not have any 
friends of the same gender.*/
SELECT *
FROM Users
WHERE Id NOT IN(
	SELECT person.Id
	FROM Users as person
	INNER JOIN Friends ON person.Id = Friends.Id1
	INNER JOIN Users AS friendUser ON friendUser.Id = Friends.Id2
	WHERE person.Gender = friendUser.Gender)
ORDER BY Id;

/*6  List all attributes of Users who have started at least two 
friendships on the same date.*/
SELECT *
FROM Users
WHERE EXISTS(	SELECT *
				FROM Friends AS Friends1, Friends AS Friends2
				WHERE Friends1.Id1 = Users.Id
                AND Friends2.Id1 = Users.Id
				AND Friends1.Startdate = Friends2.Startdate
				AND Friends1.Id2 != Friends2.Id2
);

/*7 List all User attributes and the number of friends of users
who have commented to at least two different users.*/
SELECT *, COUNT(*) AS NumFriends
FROM Users AS person, Friends
WHERE person.id = Friends.Id1
AND EXISTS(	SELECT *
			FROM Comments as Comments1, Comments AS Comments2
			WHERE Comments1.Poster = person.Id
			AND Comments2.Poster = person.Id
			AND Comments1.Recipient != Comments2.Recipient
)
GROUP BY person.Id
;
/*8  List all attributes of Users who have posted comments to all
female users*/
SELECT Id
FROM Users as postingUser
WHERE NOT EXISTS(	SELECT Id
					FROM Users
                    WHERE gender = 'F'
                    AND Id NOT IN(	SELECT Recipient
									FROM comments
                                    WHERE postingUser.Id = Poster));

/*9 List all attributes of User(s) who are friends with
 the most number of Users.*/
WITH UserFriends (Id, `Name`, Gender, NumFriends) AS (
	SELECT Users.*, COUNT(*)
	FROM Users, Friends
    WHERE Users.Id = Friends.Id1
    GROUP BY Users.Id
)
SELECT Id, `Name`, Gender
FROM UserFriends
WHERE NumFriends >= ALL (	SELECT NumFriends
							FROM UserFriends)
;