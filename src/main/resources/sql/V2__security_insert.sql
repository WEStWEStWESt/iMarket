INSERT INTO authorized_users (id, username, password) VALUES (
    1,
    'admin',
    '$2a$10$sy3n0FTB/AdA.gNirBxsMuUzLE00FarPTbFPKlYeqRotLMY19k/Fu');

INSERT INTO roles (id, rolename) VALUES
    (1, 'CUSTOMER'),
    (2, 'ADMIN');

INSERT INTO users_roles (user_id, role_id) VALUES
    (1, 1),
    (1, 2);
