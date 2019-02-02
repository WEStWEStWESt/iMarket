
CREATE TABLE IF NOT EXISTS authorized_users (
  id              BIGSERIAL      NOT NULL,
  username        VARCHAR(50)    NOT NULL    UNIQUE,
  password        VARCHAR(12)    NOT NULL    UNIQUE,

  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS roles (
  id              BIGSERIAL      NOT NULL,
  rolename        VARCHAR(50)    NOT NULL    UNIQUE,

  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS profiles (
  id              BIGSERIAL      NOT NULL,
  name            VARCHAR(50)    NOT NULL    UNIQUE,
  email           VARCHAR(50),
  phone           VARCHAR(30),
  address         VARCHAR(255),
  passport        VARCHAR(20),

  PRIMARY KEY (id)
);



