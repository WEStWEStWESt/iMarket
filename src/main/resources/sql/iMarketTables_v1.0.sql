/* ---Start--- OAuth2 Tables */

CREATE TABLE oauth_access_token (
  token_id           VARCHAR(255)  DEFAULT NULL,
  token              BYTEA,
  authentication_id  VARCHAR(255)  DEFAULT NULL,
  user_name          VARCHAR(255)  DEFAULT NULL,
  client_id          VARCHAR(255)  DEFAULT NULL,
  authentication     BYTEA,
  refresh_token      VARCHAR(255)  DEFAULT NULL
);


CREATE TABLE oauth_client_details (
  client_id                VARCHAR(255)   NOT NULL,
  resource_ids             VARCHAR(255)   DEFAULT NULL,
  client_secret            VARCHAR(255)   DEFAULT NULL,
  scope                    VARCHAR(255)   DEFAULT NULL,
  authorized_grant_types   VARCHAR(255)   DEFAULT NULL,
  web_server_redirect_uri  VARCHAR(255)   DEFAULT NULL,
  authorities              VARCHAR(255)   DEFAULT NULL,
  access_token_validity    INT            DEFAULT NULL,
  refresh_token_validity   INT            DEFAULT NULL,
  additional_information   VARCHAR(4096)  DEFAULT NULL,
  autoapprove              BIGINT         DEFAULT NULL,

  PRIMARY KEY (client_id)
);


CREATE TABLE oauth_client_token (
  token_id           VARCHAR(255)  DEFAULT NULL,
  token              BYTEA,
  authentication_id  VARCHAR(255)  DEFAULT NULL,
  user_name          VARCHAR(255)  DEFAULT NULL,
  client_id          VARCHAR(255)  DEFAULT NULL
);


CREATE TABLE oauth_code (
  code            VARCHAR(255)  DEFAULT NULL,
  authentication  BYTEA
);


CREATE TABLE oauth_refresh_token (
  token_id        VARCHAR(255)  DEFAULT NULL,
  token           BYTEA,
  authentication  BYTEA
);
