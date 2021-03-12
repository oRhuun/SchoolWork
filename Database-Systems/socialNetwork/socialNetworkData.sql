CREATE TABLE Users
(
	Id char(5),
	`Name` varchar(50) NOT NULL,
	Gender char(1),
	primary key(Id),
	CHECK(Gender in ('M','F'))
);

CREATE TABLE Friends
(
	Id1 char(5),
    Id2 char(5),
    Startdate date,
    primary key(Id1,Id2),
    foreign key(Id1) references Users (Id),
    foreign key(Id2) references Users (Id)
);

CREATE TABLE Comments
(
	CommentId int(10),
    Poster char(5),
    Recipient char(5),
    `Text` varchar(100) NOT NULL,
    PostDate date,
    primary key(CommentId),
    foreign key(Poster) references Users(Id),
    foreign key(Recipient) references Users(Id)
);

INSERT INTO Users
VALUES
('UserA','Adam','M'),
('UserB','Beth','F'),
('UserC','Chris','M'),
('UserD','Danielle','F'),
('UserE','Ellen','F');

INSERT INTO Friends
VALUES
('UserA','UserB','2020-1-1'),
('UserB','UserA','2020-1-1'),
('UserA','UserC','2019-5-20'),
('UserC','UserA','2019-5-20'),
('UserC','UserE','2020-6-15'),
('UserE','UserC','2020-6-15'),
('UserD','UserE','2020-12-31'),
('UserE','UserD','2020-12-31'),
('UserC','UserB','2019-5-20'),
('UserB','UserC','2019-5-20');

INSERT INTO Comments
VALUES
('1', 'UserA', 'UserB', 'lol did you see that', '2020-1-15'),
('2', 'UserA', 'UserD', 'hey i like you lol', '2019-3-20'),
('3', 'UserA', 'UserE', 'woah hey youre a girl', '2018-10-10'),
('4', 'UserC', 'UserD', 'just guy things woah', '2020-2-2'),
('5', 'UserD', 'UserA', 'i wonder things sometimes', '2020-5-8'),
('6', 'UserE', 'UserA', 'now is the time', '2019-1-16'),
('7', 'UserC', 'UserE', 'hi how are ya', '2019-6-1'),
('8', 'UserA', 'UserB', 'oh lol i didnt know', '2020-1-7'),
('9', 'UserB', 'UserC', 'mand thats just insane', '2018-2-26'),
('0', 'UserE', 'UserD', 'oh holy moly great mother of pearl', '2017-7-4');

