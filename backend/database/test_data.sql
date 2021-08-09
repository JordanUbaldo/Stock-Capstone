TRUNCATE users, games, user_status, balances, trades CASCADE;

INSERT INTO users (user_id, username, password_hash, role)
VALUES (1001, 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER'),
       (1002, 'admin', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_ADMIN'),
       (1003, 'testuser1', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER'),
       (1004, 'testuser2', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER'),
       (1005, 'testuser3', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER'),
       (1006, 'testuser4', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');

INSERT INTO games (game_id, game_name, game_active, host, start_date, end_date) 
VALUES (2001, 'testgame1', FALSE, 'testuser1', '2021-08-01', '2021-08-02'),
       (2002, 'testgame2', FALSE, 'testuser2', '2021-08-02', '2021-08-03'),
       (2003, 'testgame3', TRUE, 'testuser1', '2021-08-04', '2021-08-05'),
       (2004, 'testgame4', TRUE, 'testuser1', '2021-08-04', '2021-08-05'),
       (2005, 'testgame5', TRUE, 'testuser3', '2021-08-04', '2021-08-05'),
       (2006, 'testgame6', TRUE, 'testuser4', '2021-08-04', '2021-08-05');

INSERT INTO user_status (game_id, username, user_status)
VALUES (2003, 'testuser1', 'Accepted'),
       (2003, 'testuser2', 'Accepted'),
       (2004, 'testuser1', 'Accepted'),
       (2004, 'testuser3', 'Pending'),
       (2005, 'testuser3', 'Accepted'),
       (2006, 'testuser4', 'Accepted');

INSERT INTO balances (balance_id, game_id, username, amount)
VALUES (4001, 2003, 'testuser1', 100274.91),
       (4002, 2003, 'testuser2', 99732.28),
       (4003, 2004, 'testuser1', 100274.91),
       (4004, 2004, 'testuser3', 100000.00),
       (4005, 2005, 'testuser3', 101238.47),
       (4006, 2006, 'testuser4', 88379.35);
       
INSERT INTO trades (trade_id, game_id, username, type_id, stock_ticker, stock_name, amount, purchase_date, price_per_share, shares)
VALUES (5001, 2003, 'testuser1', 1, 'AAPL', 'Apple', 99858.44, '2021-08-04', 146.42, 682),
       (5002, 2003, 'testuser2', 1, 'AMZN', 'Amazon', 97169.72, '2021-08-04', 3350.68, 29),
       (5003, 2004, 'testuser1', 1, 'AAPL', 'Apple', 99858.44, '2021-08-04', 146.42, 682),
       (5004, 2004, 'testuser3', 1, 'TSLA', 'Tesla', 99984.09, '2021-08-04', 719.31, 139),
       (5005, 2005, 'testuser3', 1, 'MSFT', 'Microsoft', 99851.50, '2021-08-04', 285.29, 350),
       (5006, 2006, 'testuser4', 1, 'GOOG', 'Google', 97808.04, '2021-08-04', 2716.89, 36);
       
INSERT INTO stocks (stock_id, game_id, username, stock_ticker, stock_name, shares)
VALUES (6001, 2003, 'testuser1', 'AAPL', 'Apple', 682),
       (6002, 2003, 'testuser2', 'AMZN', 'Amazon', 29),
       (6003, 2004, 'testuser1', 'AAPL', 'Apple', 682),
       (6004, 2004, 'testuser3', 'TSLA', 'Tesla', 139),
       (6005, 2005, 'testuser3', 'MSFT', 'Microsoft', 350),
       (6006, 2006, 'testuser4', 'GOOG', 'Google', 36);
