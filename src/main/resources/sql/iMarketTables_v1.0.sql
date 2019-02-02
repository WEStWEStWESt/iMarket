
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
  profilename     VARCHAR(50)    NOT NULL    UNIQUE,
  email           VARCHAR(50),
  phone           VARCHAR(30),
  address         VARCHAR(255),
  passport        VARCHAR(20),

  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS coupons (
  id              BIGSERIAL      NOT NULL,
  description     VARCHAR(255)   NOT NULL    UNIQUE,
  sum             BIGINT,

  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS carts (
  id              BIGSERIAL      NOT NULL,
  customer_id     BIGINT         NOT NULL,
  store_status    VARCHAR(6),
  payment         VARCHAR(10),
  delivery        VARCHAR(10),
  opend_date      DATE,
  closed_date     DATE,
  paid_date       DATE,
  sent_date       DATE,
  got_date        DATE,

  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS categories (
  id              BIGSERIAL      NOT NULL,
  categoryname    VARCHAR(255)   NOT NULL    UNIQUE,

  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS groups (
  id              BIGSERIAL      NOT NULL,
  groupname       VARCHAR(255)   NOT NULL    UNIQUE,
  category_id     BIGINT         NOT NULL,

  PRIMARY KEY (id)
);

