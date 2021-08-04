BEGIN TRANSACTION;

DROP TABLE IF EXISTS trades;
DROP TABLE IF EXISTS trade_type;
DROP TABLE IF EXISTS balances;
DROP TABLE IF EXISTS user_status;
DROP TABLE IF EXISTS games;
DROP TABLE IF EXISTS users;

DROP SEQUENCE IF EXISTS seq_trade_id;
DROP SEQUENCE IF EXISTS seq_type_id;
DROP SEQUENCE IF EXISTS seq_balance_id;
DROP SEQUENCE IF EXISTS seq_game_id;
DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
CREATE SEQUENCE seq_game_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_balance_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
<<<<<<< HEAD
<<<<<<< HEAD

=======
  
>>>>>>> ee93fe8bbbea0eaacfd3c8cfb214a16721c24f5a
=======
>>>>>>> 3dc58722ccecbfc47d44d74aa2ce15c3c3e32406
CREATE SEQUENCE seq_type_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
<<<<<<< HEAD
<<<<<<< HEAD

=======
  
>>>>>>> ee93fe8bbbea0eaacfd3c8cfb214a16721c24f5a
=======
>>>>>>> 3dc58722ccecbfc47d44d74aa2ce15c3c3e32406
CREATE SEQUENCE seq_trade_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
<<<<<<< HEAD
<<<<<<< HEAD

=======
  
>>>>>>> ee93fe8bbbea0eaacfd3c8cfb214a16721c24f5a
=======
>>>>>>> 3dc58722ccecbfc47d44d74aa2ce15c3c3e32406
CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_users PRIMARY KEY (user_id)
);

CREATE TABLE games (
	game_id int DEFAULT nextval('seq_game_id'::regclass) NOT NULL,
	game_name varchar(50) NOT NULL,
	game_status varchar(50) DEFAULT 'Active' NOT NULL,
	host varchar(50) NOT NULL,
	start_date date DEFAULT CURRENT_DATE NOT NULL,
	end_date date NOT NULL,
	CONSTRAINT PK_games PRIMARY KEY (game_id)
);

CREATE TABLE user_status (
	game_id int NOT NULL,
	username varchar(50) NOT NULL,
	user_status varchar(50) DEFAULT 'Pending' NOT NULL,
	CONSTRAINT FK_game_id FOREIGN KEY (game_id) REFERENCES games (game_id),
	CONSTRAINT FK_username FOREIGN KEY (username) REFERENCES users (username)
);

CREATE TABLE balances (
	balance_id int DEFAULT nextval('seq_balance_id'::regclass) NOT NULL,
	game_id int NOT NULL,
	username varchar(50) NOT NULL,
	amount decimal(13, 2) NOT NULL,
	CONSTRAINT PK_balances PRIMARY KEY (balance_id),
	CONSTRAINT FK_game_id FOREIGN KEY (game_id) REFERENCES games (game_id),
	CONSTRAINT FK_username FOREIGN KEY (username) REFERENCES users (username)
);

CREATE TABLE trade_type (
	type_id int DEFAULT nextval('seq_type_id'::regclass) NOT NULL,
	type varchar(50) NOT NULL,
	CONSTRAINT PK_type PRIMARY KEY (type_id)
);

CREATE TABLE trades (
	trade_id int DEFAULT nextval('seq_trade_id'::regclass) NOT NULL,
	game_id int NOT NULL,
	username varchar(50) NOT NULL,
	type_id int NOT NULL,
	stock_ticker int NOT NULL,
	stock_name varchar(50) NOT NULL,
	amount decimal(13, 2) NOT NULL,
	purchase_date date DEFAULT CURRENT_DATE NOT NULL,
	price_per_share decimal(13, 2) NOT NULL,
	shares int NOT NULL,
	CONSTRAINT PK_trades PRIMARY KEY (trade_id),
	CONSTRAINT FK_game_id FOREIGN KEY (game_id) REFERENCES games (game_id),
	CONSTRAINT FK_username FOREIGN KEY (username) REFERENCES users (username),
	CONSTRAINT FK_type_id FOREIGN KEY (type_id) REFERENCES trade_type (type_id),
	CONSTRAINT CK_trade_amount_gt_0 CHECK ((amount>0))
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO trade_type (type) VALUES ('Buy');
INSERT INTO trade_type (type) VALUES ('Sell');

COMMIT TRANSACTION;
