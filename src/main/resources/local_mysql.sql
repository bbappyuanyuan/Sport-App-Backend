CREATE TABLE account (
  id       INTEGER AUTO_INCREMENT,
  email    VARCHAR(50) UNIQUE,
  password VARCHAR(50),
  username VARCHAR(50),
  gender   VARCHAR(50),
  height   DOUBLE,
  weight   DOUBLE,
  create_d TIMESTAMP,
  PRIMARY KEY (id)
);

CREATE TABLE fan (
  id       INTEGER AUTO_INCREMENT,
  follower INTEGER,
  followee INTEGER,
  PRIMARY KEY (id)
);

CREATE TABLE moment (
  id         INTEGER AUTO_INCREMENT,
  account_id INTEGER,
  message    VARCHAR(200),
  has_photo  TINYINT(1),
  create_d   TIMESTAMP,
  PRIMARY KEY (id)
);

CREATE TABLE record (
  id         INTEGER AUTO_INCREMENT,
  account_id INTEGER,
  duration   INTEGER,
  distance   DOUBLE,
  max_speed  DOUBLE,
  steps      INTEGER,
  create_d   TIMESTAMP,
  PRIMARY KEY (id)
);