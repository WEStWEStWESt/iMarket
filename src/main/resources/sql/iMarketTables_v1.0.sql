
CREATE TABLE IF NOT EXISTS authorized_users (
  id       BIGSERIAL      NOT NULL,
  username VARCHAR(50)    NOT NULL    UNIQUE,
  password VARCHAR(12)    NOT NULL    UNIQUE,

  PRIMARY KEY (id)
)

