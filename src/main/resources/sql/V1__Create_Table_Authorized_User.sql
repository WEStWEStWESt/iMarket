CREATE TABLE authorized_users
(
   id         BIGSERIAL    NOT NULL
     CONSTRAINT authorized_user_pkey
       PRIMARY KEY,
   username   VARCHAR      NOT NULL
     CONSTRAINT uk_authorized_user_username
       UNIQUE,
   password    VARCHAR      NOT NULL
);

CREATE TABLE roles
(
  id         BIGSERIAL    NOT NULL
    CONSTRAINT role_pkey
      PRIMARY KEY,
  rolename   VARCHAR      NOT NULL
    CONSTRAINT uk_rolename
      UNIQUE,
  description   VARCHAR
);

CREATE TABLE users_roles
(
  role_id        BIGINT    NOT NULL
    CONSTRAINT fk_role_id
      REFERENCES roles (id),
  user_id        BIGINT    NOT NULL
    CONSTRAINT fk_user_id
      REFERENCES authorized_users (id)
);