TRUNCATE users, games, user_status, balances, trades CASCADE;

INSERT INTO users (user_id, username, password_hash, role)
VALUES (1001, 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER'),
       (1002, 'admin', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_ADMIN'),
       (1003, 'testuser1', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER'),
       (1004, 'testuser2', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER'),
       (1005, 'testuser3', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER'),
       (1006, 'testuser4', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');

INSERT INTO games (game_id, game_name, game_active, host, start_date, end_date) 
VALUES (1001, 'testgame1', FALSE, 'testuser1', '2021-08-01', '2021-08-02'),
       (1002, 'testgame2', FALSE, 'testuser2', '2021-08-02', '2021-08-03'),
       (1003, 'testgame3', TRUE, 'testuser1', '2021-08-04', '2021-08-05'),
       (1004, 'testgame4', TRUE, 'testuser1', '2021-08-04', '2021-08-05'),
       (1005, 'testgame5', TRUE, 'testuser3', '2021-08-04', '2021-08-05'),
       (1006, 'testgame6', TRUE, 'testuser4', '2021-08-04', '2021-08-05');

INSERT INTO user_status (game_id, username, user_status)
VALUES (1003, 'testuser1', 'Accepted'),
       (1003, 'testuser2', 'Accepted'),
       (1004, 'testuser1', 'Accepted'),
       (1004, 'testuser3', 'Pending'),
       (1005, 'testuser3', 'Accepted'),
       (1006, 'testuser4', 'Accepted');

INSERT INTO balances (balance_id, game_id, username, amount)
VALUES (1001, 1003, 'testuser1', 100274.91),
       (1002, 1003, 'testuser2', 99732.28),
       (1003, 1004, 'testuser1', 100274.91),
       (1004, 1004, 'testuser3', 100000.00),
       (1005, 1005, 'testuser3', 101238.47),
       (1006, 1006, 'testuser4', 88379.35);
       
INSERT INTO trades (trade_id, game_id, username, type_id, stock_ticker, stock_name, amount, purchase_date, price_per_share, shares)
VALUES (1001, 1003, 'testuser1', 1, 'APPL', 'Apple', 99858.44, '2021-08-04', 146.42, 682),
       (1002, 1003, 'testuser2', 1, 'AMZN', 'Amazon', 97169.72, '2021-08-04', 3350.68, 29),
       (1003, 1004, 'testuser1', 1, 'APPL', 'Apple', 99858.44, '2021-08-04', 146.42, 682),
       (1004, 1004, 'testuser3', 1, 'TSLA', 'Tesla', 99984.09, '2021-08-04', 719.31, 139),
       (1005, 1005, 'testuser3', 1, 'MSFT', 'Microsoft', 99851.50, '2021-08-04', 285.29, 350),
       (1006, 1006, 'testuser4', 1, 'GOOG', 'Google', 97808.04, '2021-08-04', 2716.89, 36);