
-- Entities

CREATE TABLE IF NOT EXISTS authorized_users (
  id                      BIGSERIAL      NOT NULL,
  username                VARCHAR(50)    NOT NULL    UNIQUE,
  password                VARCHAR(12)    NOT NULL    UNIQUE,

  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS roles (
  id                      BIGSERIAL      NOT NULL,
  rolename                VARCHAR(50)    NOT NULL    UNIQUE,
  description             TEXT,

  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS profiles (
  id                      BIGSERIAL      NOT NULL,
  user_id                 BIGINT         NOT NULL,
  coupon_id               BIGINT,
  profilename             VARCHAR(50)    NOT NULL    UNIQUE,
  email                   VARCHAR(50),
  phone                   VARCHAR(30),
  address                 VARCHAR(255),
  passport                VARCHAR(20),

  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS coupons (
  id                      BIGSERIAL      NOT NULL,
  description             VARCHAR(255)   NOT NULL    UNIQUE,
  sum                     BIGINT,

  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS carts (
  id                      BIGSERIAL      NOT NULL,
  profile_id              BIGINT         NOT NULL,
  product_id              BIGINT         NOT NULL,
  payment                 VARCHAR(10),
  delivery                VARCHAR(10),
  opend_date              DATE,
  closed_date             DATE,
  paid_date               DATE,
  sent_date               DATE,
  got_date                DATE,

  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS categories (
  id                      BIGSERIAL      NOT NULL,
  categoryname            VARCHAR(255)   NOT NULL    UNIQUE,

  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS groups (
  id                      BIGSERIAL      NOT NULL,
  category_id             BIGINT         NOT NULL,
  groupname               VARCHAR(255)   NOT NULL    UNIQUE,

  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS products (
  id                      BIGSERIAL      NOT NULL,
  group_id                BIGINT         NOT NULL,
  productname             VARCHAR(255),
  description_short       VARCHAR(255),
  description_full        TEXT,
  price                   MONEY,
  picture                 TEXT,
  store_status            VARCHAR(6),
  discount                FLOAT,

  PRIMARY KEY (id)
);


-- Foreign keys

ALTER TABLE profiles
  ADD FOREIGN KEY (user_id) REFERENCES authorized_users(id) ON DELETE CASCADE,
  ADD FOREIGN KEY (coupon_id) REFERENCES coupons(id);

ALTER TABLE carts
  ADD FOREIGN KEY (profile_id) REFERENCES profiles(id),
  ADD FOREIGN KEY (product_id) REFERENCES products(id);

ALTER TABLE groups
  ADD FOREIGN KEY (category_id) REFERENCES categories(id);

ALTER TABLE products
  ADD FOREIGN KEY (group_id) REFERENCES groups(id);
