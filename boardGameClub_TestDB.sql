USE master;
DROP DATABASE IF EXISTS[boardGamesDb];
CREATE DATABASE [boardGamesDb];
GO
USE boardGamesDb;
GO

CREATE TABLE Game
(
    gameId INT IDENTITY (1,1) PRIMARY KEY,
    gameName NVARCHAR(100) NOT NULL,
    purchaseDate DATE NOT NULL,
    totalPlayers INT,
    category NVARCHAR(50),
    recommendedAge INT,
    averagePlayTime INT,
    --minutes
    description NVARCHAR(MAX),
    memberComment NVARCHAR(MAX)
);
GO

CREATE TABLE GameSession
(
    sessionId INT IDENTITY (1,1) PRIMARY KEY,
    gameId INT FOREIGN KEY REFERENCES Game (gameId),
    date DATE NOT NULL
);
GO


CREATE TABLE Member
(
    memberId INT IDENTITY (1,1) PRIMARY KEY,
    name NVARCHAR(100) NOT NULL,
    email NVARCHAR(100) UNIQUE,
    CHECK (email LIKE '%_@__%.__%'),
    membershipFeePaid BIT DEFAULT 0,
    totalWins INT DEFAULT 0,
    age INT NOT NULL
);
GO

CREATE TABLE SessionParticipant
(
    id INT IDENTITY (1,1) PRIMARY KEY,
    memberId INT FOREIGN KEY REFERENCES Member (memberId),
    sessionId INT FOREIGN KEY REFERENCES GameSession (sessionId),
    score INT,
    isWinner BIT DEFAULT 0
);

GO


INSERT INTO Game (gameName, purchaseDate, totalPlayers, category, recommendedAge, averagePlayTime, description,
                  memberComment)
VALUES (N'Catan', '2020-05-10', 4, N'Strategy', 10, 90, N'Trade and build game', N'Club favorite'),
       (N'Carcassonne', '2019-03-15', 5, N'Tile Placement', 8, 45, N'Build cities and roads', N'Easy to learn'),
       (N'Ticket to Ride', '2021-07-20', 5, N'Family', 8, 60, N'Build train routes', N'Popular game'),
       (N'7 Wonders', '2022-01-10', 7, N'Card Drafting', 10, 30, N'Ancient civilization game', N'Fast gameplay'),
       (N'Risk', '2018-11-05', 6, N'War Strategy', 12, 120, N'World domination game',
        N'Long but fun'); -- bought it a long time ago but haven't played it yet.
GO

INSERT INTO Member (name, email, membershipFeePaid, totalWins, age)
VALUES (N'Anna Svensson', 'anna@mail.com', 1, 2, 25),
       (N'Erik Johansson', 'erik@mail.com', 1, 1, 30),
       (N'Lina Karlsson', 'lina@mail.com', 0, 0, 22), -- Not paid yet + not won yet
       (N'Oskar Nilsson', 'oskar@mail.com', 1, 3, 28),
       (N'Maja Eriksson', 'maja@mail.com', 0, 0, 19); -- Not participated much + haven't won yet
GO

INSERT INTO GameSession (gameId, date)
VALUES (1, '2024-01-10'), -- Catan
       (1, '2024-01-15'), -- Catan (play multiple times)
       (2, '2024-02-01'), -- Carcassonne
       (3, '2024-02-10'), -- Ticket to Ride
       (4, '2024-03-05');
-- 7 Wonders
-- Risk (game_id = 5) has no session yet → no one is playing.
GO

INSERT INTO SessionParticipant (memberId, sessionId, score, isWinner)
VALUES
-- Session 1 (many player)
(1, 1, 10, 1),
(2, 1, 8, 0),
(3, 1, 6, 0),
(4, 1, 9, 0),

-- Session 2 (fewer player)
(1, 2, 7, 0),
(4, 2, 11, 1),

-- Session 3
(2, 3, 12, 1),
(3, 3, 9, 0),

-- Session 4
(1, 4, 15, 1),
(5, 4, 5, 0), -- few player participated

-- Session 5 (only two player)
(4, 5, 13, 1),
(2, 5, 10, 0);
GO
