--JDBC Game DAO Queries

--game-name changed to game_name
INSERT INTO games (game_name, host, end_date)
VALUES ('querytest1', 1003, '2021-08-06') RETURNING game_id;

INSERT INTO user_status (game_id, username, user_status)
VALUES (1, 'testuser1', 'Accepted');

--amount default is 100000.00
INSERT INTO balances (game_id, username)
VALUES (1, 'testuser1');

SELECT game_name, end_date
FROM games g
JOIN user_status s ON g.game_id = s.game_id
JOIN users u ON s.username = u.username
WHERE u.username = 'testuser1' AND s.user_status ILIKE 'Accepted';

SELECT game_id, username, user_status
FROM user_status
WHERE game_id = 1;

--user_status default is 'Pending'
INSERT INTO user_status (game_id, username, user_status)
VALUES (1, 'testuser2', 'Pending');

SELECT game_name
FROM games
WHERE game_name = 'querytest1';

--JDBC User DAO Queries

SELECT user_id
FROM users
WHERE username = 'testuser1';

SELECT *
FROM users
WHERE user_id = 1003;

SELECT *
FROM users;

INSERT INTO users (username,password_hash,role)
VALUES ('testuser5', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');

--JDBC Trade DAO Queries

SELECT stock_ticker, stock_name, shares, price_per_share * shares AS total_cost
FROM trades
WHERE game_id = 1003;

SELECT game_id, shares, price_per_share, stock_name, stock_ticker, purchase_date, typ.type
FROM trades AS tra
JOIN trade_type AS typ ON tra.type_id = typ.type_id
WHERE game_id = 1003;