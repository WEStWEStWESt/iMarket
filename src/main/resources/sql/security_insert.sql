INSERT INTO oauth_client_details (
    client_id,
    resource_ids,
    client_secret,
    scope,
    authorized_grant_types,
    web_server_redirect_uri,
    authorities,
    access_token_validity,
    refresh_token_validity,
    additional_information,
    autoapprove)
VALUES (
    'spring-security-oauth2-client',
    'imarket-resource-server-api',
    '$2y$12$0eJHNBeSErmzF9suTWhloe0QyQF3/vI8nrU9tyNkSis8g5AZG4/mG',
    'read,write',
    'password,refresh_token',
    NULL,
    NULL,
    10800,
    2592000,
    NULL,
    NULL
    );

INSERT INTO authorized_users (id, username, password) VALUES (
    1,
    'admin',
    '$2y$12$eV8kcvHw4dyHAwXCizbqHu2Ez7GOFCuX2RGux7oBREalA/AQGc/c2');

INSERT INTO roles (id, rolename) VALUES
    (1, 'CUSTOMER'),
    (2, 'ADMIN');

INSERT INTO users_roles (user_id, role_id) VALUES
    (1, 1),
    (1, 2);
