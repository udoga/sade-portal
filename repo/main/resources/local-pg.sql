DROP SCHEMA IF EXISTS public CASCADE;
CREATE SCHEMA public;
SET SCHEMA 'public';

CREATE TABLE users(
    id SERIAL PRIMARY KEY NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE sessions(
    id SERIAL PRIMARY KEY NOT NULL,
    user_id INT NOT NULL,
    begin_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    token VARCHAR(255) NOT NULL
);

CREATE TABLE roles(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE permissions(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE assignments(
    id SERIAL PRIMARY KEY NOT NULL,
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE RESTRICT,
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE RESTRICT
);

CREATE TABLE grants(
    id SERIAL PRIMARY KEY NOT NULL,
    role_id INT NOT NULL,
    permission_id INT NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE RESTRICT,
    FOREIGN KEY (permission_id) REFERENCES permissions(id) ON DELETE RESTRICT
);

CREATE TABLE sectors(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE clients(
    id SERIAL PRIMARY KEY NOT NULL,
    sector_id INT NOT NULL,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE projects(
    id SERIAL PRIMARY KEY NOT NULL,
    client_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    FOREIGN KEY (client_id) REFERENCES clients(id) ON DELETE RESTRICT
);

CREATE TABLE activities(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE employees(
    id SERIAL PRIMARY KEY NOT NULL,
    user_id INT NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE RESTRICT
);

CREATE TABLE positions(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE engagements(
    id SERIAL PRIMARY KEY NOT NULL,
    project_id INT NOT NULL,
    employee_id INT NOT NULL,
    position_id INT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    description TEXT NOT NULL,
    status VARCHAR(255) NOT NULL,
    FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE RESTRICT,
    FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE RESTRICT,
    FOREIGN KEY (position_id) REFERENCES positions(id) ON DELETE RESTRICT
);

CREATE TABLE worklogs(
    id SERIAL PRIMARY KEY NOT NULL,
    project_id INT NOT NULL,
    employee_id INT NOT NULL,
    activity_id INT NOT NULL,
    work_date DATE NOT NULL,
    duration INT NOT NULL,
    description TEXT NOT NULL,
    approval_status VARCHAR(1),
    FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE RESTRICT,
    FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE RESTRICT,
    FOREIGN KEY (activity_id) REFERENCES activities(id) ON DELETE RESTRICT
);

CREATE TABLE vacation_categories(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE vacations(
    id SERIAL PRIMARY KEY NOT NULL,
    employee_id INT NOT NULL,
    category_id INT NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    day_count INT NOT NULL,
    description VARCHAR(255) NOT NULL,
    approval_status VARCHAR(255) NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE RESTRICT,
    FOREIGN KEY (category_id) REFERENCES vacation_categories(id) ON DELETE RESTRICT
);

CREATE TABLE expenses(
    id SERIAL PRIMARY KEY NOT NULL,
    employee_id INT NOT NULL REFERENCES employees(id) ON DELETE RESTRICT,
    form_date DATE NOT NULL,
    description VARCHAR(255) NOT NULL,
    approval_status VARCHAR(255) NOT NULL
);

CREATE TABLE receipt_categories(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE receipts(
    id SERIAL PRIMARY KEY NOT NULL,
    expense_id INT NOT NULL,
    receipt_date DATE NOT NULL,
    category_id INT NOT NULL,
    amount DECIMAL NOT NULL,
    currency VARCHAR(255) NOT NULL,
    vat_rate SMALLINT NOT NULL,
    description TEXT NOT NULL,
    FOREIGN KEY (expense_id) REFERENCES expenses(id) ON DELETE RESTRICT,
    FOREIGN KEY (category_id) REFERENCES receipt_categories(id) ON DELETE RESTRICT
);

ALTER TABLE activities ADD CONSTRAINT unique_activity_name UNIQUE (name);
ALTER TABLE assignments ADD CONSTRAINT unique_user_role UNIQUE (role_id, user_id);
ALTER TABLE clients ADD CONSTRAINT unique_client_name UNIQUE (name);
ALTER TABLE employees ADD CONSTRAINT unique_employee_user_id UNIQUE (user_id);
ALTER TABLE grants ADD CONSTRAINT unique_permission_role UNIQUE (permission_id, role_id);
ALTER TABLE receipt_categories ADD CONSTRAINT unique_receipt_category_name UNIQUE (name);
ALTER TABLE roles ADD CONSTRAINT unique_role_name UNIQUE (name);
ALTER TABLE sectors ADD CONSTRAINT unique_sector_name UNIQUE (name);
ALTER TABLE users ADD CONSTRAINT unique_username UNIQUE (username);
ALTER TABLE vacation_categories ADD CONSTRAINT unique_vacation_category_name UNIQUE (name);

INSERT INTO users VALUES
--   id,      username,       password
    (default, 'admin',        '63524dd54916a96b6fbf052757e39bae1af467e82d3a392931edfa9580888ae55f49f3f68d014396e8a9f13851c89d0f0c1865661da5be18dc097ab9e13c8e9d'),
    (default, 'doga.sezgin',  'd404aad0d3110d60debb48163beb3b21ee6dcdf4b25dba7b2065c5e9607deefa87ed938d4aafa9f8b74ad7abe82c2bd5e9fdf6f927919cdc3c825a549d552bb8'),
    (default, 'eylul.erdinc', '5e14b3da28f2ce06212b1e061be836d3074c34fcf029c6271a5521c2223abed099083befbc02783af3dcbdfef27e1e597806f54b7e1435884a2773fe51ac0a26');

INSERT INTO roles VALUES
--   id,      name
    (default, 'SP Admin'),
    (default, 'SP Employee'),
    (default, 'SP Project Manager');

INSERT INTO permissions  VALUES
--   id,      name
    (default, 'sp-user_list'),
    (default, 'sp-user_list_options'),
    (default, 'sp-user_read'),
    (default, 'sp-user_read:own'),
    (default, 'sp-user_create'),
    (default, 'sp-user_update'),
    (default, 'sp-user_delete'),
    (default, 'sp-worklog_list'),
    (default, 'sp-worklog_list:own'),
    (default, 'sp-worklog_list_detailed'),
    (default, 'sp-worklog_list_detailed:own'),
    (default, 'sp-worklog_read'),
    (default, 'sp-worklog_read:own'),
    (default, 'sp-worklog_read_detailed'),
    (default, 'sp-worklog_read_detailed:own'),
    (default, 'sp-worklog_create'),
    (default, 'sp-worklog_create:own'),
    (default, 'sp-worklog_update'),
    (default, 'sp-worklog_update:own'),
    (default, 'sp-worklog_delete'),
    (default, 'sp-worklog_delete:own');

INSERT INTO assignments VALUES
--   id,      user_id, role_id
    (default,       1,       1),
    (default,       2,       2),
    (default,       3,       2);

INSERT INTO grants VALUES
--   id,      role_id, permission_id
    (default,       1,             1),
    (default,       1,             2),
    (default,       1,             3),
    (default,       1,             5),
    (default,       1,             6),
    (default,       1,             7),
    (default,       1,             8),
    (default,       1,            10),
    (default,       1,            12),
    (default,       1,            14),
    (default,       1,            16),
    (default,       1,            18),
    (default,       1,            20),
    (default,       2,             1),
    (default,       2,             2),
    (default,       2,             4),
    (default,       2,             9),
    (default,       2,            11),
    (default,       2,            13),
    (default,       2,            15),
    (default,       2,            17),
    (default,       2,            19),
    (default,       2,            21);

INSERT INTO sectors VALUES
--   id,      name
    (default, 'Sector One'),
    (default, 'Sector Two');

INSERT INTO clients VALUES
--   id,      sector_id            name
    (default,         1,   'Client One'),
    (default,         2,   'Client Two');

INSERT INTO projects VALUES
--   id,      client_id, name,          description
    (default,         1, 'Project One', 'Description one.'),
    (default,         2, 'Project Two', 'Description two.');

INSERT INTO activities VALUES
--   id,      name
    (default, 'Meeting'),
    (default, 'Data Analysis');

INSERT INTO employees VALUES
--   id,      user_id,  first_name, last_name
    (default,       2, 'Uğur Doğa',  'Sezgin'),
    (default,       3, 'Eylül',      'Erdinç');

INSERT INTO positions VALUES
--   id,      name
    (default, 'Manager'),
    (default, 'Assistant' );

INSERT INTO engagements VALUES
--   id,      project_id, employee_id, position_id,   start_date,    end_date,        description,   status
    (default,           1,           1,          1, '2022-01-01', '2022-05-05', 'Description one.',     'A'),
    (default,           2,           2,          2, '2022-02-02', '2022-06-06', 'Description two.',     'I');

INSERT INTO worklogs VALUES
--   id,      project_id, employee_id, activity_id, work_date,    duration,  description,        approval_status
    (default,          1,           1,           1, '2022-01-01',        5,  'Description one.',             'D'),
    (default,          2,           2,           2, '2022-02-02',        6,  'Description two.',            null);

INSERT INTO vacation_categories VALUES
--   id,      name
    (default, 'ParentalVacation'),
    (default, 'MarriageVacation');

INSERT INTO vacations VALUES
--   id,      employee_id, category_id, start_time,            end_time,              day_count, description,        approval_status
    (default,           1,           1, '2022-01-01T09:00:00', '2022-05-05T12:00:00',         5, 'Description one.',             'A'),
    (default,           2,           1, '2022-01-01T08:00:00', '2022-06-06T11:00:00',         6, 'Description two.',             'D');

INSERT INTO expenses VALUES
--   id,      employee_id, form_date,    description,        approval_status
    (default,           1, '2022-01-01', 'Description one.',             'A'),
    (default,           2, '2022-02-02', 'Description two.',             'D');

INSERT INTO receipt_categories VALUES
--   id,      name
    (default, 'Transport'),
    (default, 'Food'     );

INSERT INTO receipts VALUES
--   id,      expense_id, receipt_date, category_id, amount,   currency, vat_rate, description
    (default,          1, '2022-01-01',           1,  '45.20',    'TRY',       18, 'Bus Coast'),
    (default,          1, '2022-01-02',           2, '200.50',    'TRY',       18, 'Office Breakfast'),
    (default,          2, '2022-01-03',           1, '300.00',    'TRY',       18, 'Taxi');
