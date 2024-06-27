DROP TABLE IF EXISTS receipts CASCADE;
DROP TABLE IF EXISTS expenses CASCADE;
DROP TABLE IF EXISTS vacations CASCADE;
DROP TABLE IF EXISTS employees CASCADE;
DROP TABLE IF EXISTS assignments CASCADE;
DROP TABLE IF EXISTS roles CASCADE;
DROP TABLE IF EXISTS permissions CASCADE;
DROP TABLE IF EXISTS sessions CASCADE;
DROP TABLE IF EXISTS users CASCADE;

DROP TYPE IF EXISTS receipt_category CASCADE;
DROP TYPE IF EXISTS vacation_type CASCADE;
DROP TYPE IF EXISTS currency CASCADE;
DROP TYPE IF EXISTS approval_status CASCADE;

CREATE TYPE approval_status AS ENUM (
    'Approved', 'Rejected', 'Waiting'
);

CREATE TYPE currency AS ENUM (
    'TRY', 'EUR', 'USD'
);

CREATE TYPE vacation_type AS ENUM (
    'ParentalVacation', 'BereavementVacation', 'BreastFeedingVacation', 'PersonalVacation', 'MarriageVacation',
    'MilitaryVacation', 'MaternityVacation', 'PaternityVacation', 'UnpaidVacation', 'MedicalVacation', 'AnnualVacation',
    'BirthdayVacation', 'RemoteWorking'
);

CREATE TYPE receipt_category AS ENUM (
    'Food', 'Communication', 'Transportation', 'Stationery', 'Technology', 'Vehicle', 'Office', 'Accommodation',
    'Notary', 'Cargo', 'Balancing', 'Other'
);

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

CREATE TABLE permissions(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE roles(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE clients(
    id IDENTITY PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE projects(
    id IDENTITY PRIMARY KEY NOT NULL,
    client_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    FOREIGN KEY (client_id) REFERENCES clients(id) ON DELETE RESTRICT
);

CREATE TABLE activities(
    id IDENTITY PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE assignments(
    id SERIAL PRIMARY KEY NOT NULL,
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE RESTRICT,
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE RESTRICT
);

CREATE TABLE employees(
    id SERIAL PRIMARY KEY NOT NULL,
    user_id INT NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE RESTRICT
);

CREATE TABLE worklogs(
    id SERIAL PRIMARY KEY NOT NULL,
    activity_id INT NOT NULL,
    employee_id INT NOT NULL,
    project_id INT NOT NULL,
    work_date DATE NOT NULL,
    duration INT NOT NULL,
    status VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    FOREIGN KEY (activity_id) REFERENCES activities(id) ON DELETE RESTRICT,
    FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE RESTRICT,
    FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE RESTRICT
);

CREATE TABLE vacations(
    id SERIAL PRIMARY KEY NOT NULL,
    employee_id INT NOT NULL,
    vacation_type vacation_type NOT NULL,
    replacement_employee_id INT NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    day_count INT NOT NULL,
    description VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE RESTRICT
);

CREATE TABLE expenses(
    id SERIAL PRIMARY KEY NOT NULL,
    employee_id INT NOT NULL REFERENCES employees(id) ON DELETE RESTRICT,
    form_date DATE NOT NULL,
    amount DECIMAL NOT NULL,
    currency currency NOT NULL,
    paid BOOLEAN NOT NULL,
    description VARCHAR(255) NOT NULL,
    approval_status approval_status NOT NULL
);

CREATE TABLE receipt_categories(
    id IDENTITY PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE receipts(
    id IDENTITY PRIMARY KEY NOT NULL,
    expense_id INT NOT NULL,
    receipt_date DATE NOT NULL,
    category_id INT NOT NULL,
    amount DECIMAL NOT NULL,
    vat_rate SMALLINT NOT NULL,
    description TEXT NOT NULL,
    FOREIGN KEY (expense_id) REFERENCES expenses(id) ON DELETE RESTRICT,
    FOREIGN KEY (category_id) REFERENCES receipt_categories(id) ON DELETE RESTRICT
);

INSERT INTO users VALUES
--        id,       username,                                                           password
    (default,        'admin', 'c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec'),
    (default,  'doga.sezgin', 'd404aad0d3110d60debb48163beb3b21ee6dcdf4b25dba7b2065c5e9607deefa87ed938d4aafa9f8b74ad7abe82c2bd5e9fdf6f927919cdc3c825a549d552bb8'),
    (default, 'eylul.erdinc', '5e14b3da28f2ce06212b1e061be836d3074c34fcf029c6271a5521c2223abed099083befbc02783af3dcbdfef27e1e597806f54b7e1435884a2773fe51ac0a26');

INSERT INTO permissions  VALUES
--        id,          name
    (default,   'user_read'),
    (default, 'user_update');

INSERT INTO roles VALUES
--        id,         name
    (default, 'ROLE_ADMIN'),
    (default, 'ROLE_USER');

INSERT INTO clients VALUES
--        id,      name
    (default, 'Client1'),
    (default, 'Client2');

INSERT INTO projects VALUES
--        id, client_id,      name, description
    (default,         1, 'Project1', 'Example1'),
    (default,         2, 'Project2', 'Example2');

INSERT INTO activities VALUES
--        id,      name
    (default, 'Meeting'),
    (default, 'Data Analysis');

INSERT INTO assignments VALUES
--        id, user_id,    role_id
    (default,       1,          1),
    (default,       1,          2),
    (default,       2,          2),
    (default,       3,          2);

INSERT INTO employees VALUES
--        id, user_id,  first_name, last_name
    (default,       2, 'Uğur Doğa',  'Sezgin'),
    (default,       3, 'Eylül',      'Erdinç');

INSERT INTO worklogs VALUES
--        id, activity_id, employee_id, project_id,     work_date, duration, status, description
    (default,       1,           1,          1,  '2022-01-01',        5, 'Done',  'Example1'),
    (default,       2,           2,          2,  '2022-02-02',        6, 'Done',  'Example2');

INSERT INTO vacations VALUES
--        id, employee_id,       vacation_type,    replacement_employee_id,            start_time,                end_time,     day_count,   description,    status
    (default,           1, 'MaternityVacation',                          2,   '2022-01-01T09:00:00',   '2022-05-05T12:00:00',             5,    'Example1', 'Approved'),
    (default,           2, 'PaternityVacation',                          1,   '2022-01-01T08:00:00',   '2022-06-06T11:00:00',             6,    'Example2', 'Rejected');

INSERT INTO expenses VALUES
--        id, employee_id,    form_date,      amount, currency,  paid,   description,     status
    (default,           1, '2022-01-01',   '1000.10',    'EUR',  true,    'Example1', 'Approved'),
    (default,           2, '2022-02-02', '100000.10',    'TRY', false,    'Example2', 'Rejected');

INSERT INTO receipt_categories VALUES
--        id,      name
    (default, 'Transport'),
    (default,      'Food');

INSERT INTO receipts VALUES
--        id, expense_id, receipt_date, category_id,    amount, vat_rate, description
    (default,          1, '2022-01-01',           1,   '45.20',     '18', 'Bus Coast');
