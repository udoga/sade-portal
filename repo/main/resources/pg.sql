DROP TABLE IF EXISTS receipts CASCADE;
DROP TABLE IF EXISTS expenses CASCADE;
DROP TABLE IF EXISTS leaves CASCADE;
DROP TABLE IF EXISTS employees CASCADE;
DROP TABLE IF EXISTS sessions CASCADE;
DROP TABLE IF EXISTS permissions CASCADE;
DROP TABLE IF EXISTS users CASCADE;

DROP TYPE IF EXISTS receipt_category CASCADE;
DROP TYPE IF EXISTS leave_type CASCADE;
DROP TYPE IF EXISTS currency CASCADE;
DROP TYPE IF EXISTS approval_status CASCADE;

CREATE TYPE approval_status AS ENUM (
    'Approved', 'Rejected', 'Waiting'
);

CREATE TYPE currency AS ENUM (
    'TRY', 'EUR', 'USD'
);

CREATE TYPE leave_type AS ENUM (
    'ParentalLeave', 'BereavementLeave', 'BreastFeedingLeave', 'PersonalLeave', 'MarriageLeave', 'MilitaryLeave',
    'MaternityLeave', 'PaternityLeave', 'UnpaidLeave', 'MedicalLeave', 'AnnualLeave', 'BirthdayLeave', 'RemoteWorking'
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

CREATE TABLE permissions(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE sessions(
    id SERIAL PRIMARY KEY NOT NULL,
    user_id INT NOT NULL,
    begin_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    token VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE RESTRICT
);

CREATE TABLE employees(
    id SERIAL PRIMARY KEY NOT NULL,
    user_id INT NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE RESTRICT
);

CREATE TABLE leaves(
    id SERIAL PRIMARY KEY NOT NULL,
    user_id INT NOT NULL,
    leave_type leave_type NOT NULL,
    replacement_user_id INT NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    day_count INT NOT NULL,
    description VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE RESTRICT
);

CREATE TABLE expenses(
    id SERIAL PRIMARY KEY NOT NULL,
    user_id INT NOT NULL REFERENCES users(id) ON DELETE RESTRICT,
    form_date DATE NOT NULL,
    amount DECIMAL NOT NULL,
    currency currency NOT NULL,
    paid BOOLEAN NOT NULL,
    description VARCHAR(255) NOT NULL,
    approval_status approval_status NOT NULL
);

CREATE TABLE receipts(
    id SERIAL PRIMARY KEY NOT NULL,
    expense_id INT NOT NULL REFERENCES expenses(id) ON DELETE RESTRICT,
    receipt_date DATE NOT NULL,
    category receipt_category NOT NULL,
    amount DECIMAL NOT NULL,
    vat_rate SMALLINT NOT NULL,
    description TEXT NOT NULL
);

INSERT INTO users VALUES
--        id,      username,          password
    (default,  'first.user',  'first-password'),
    (default, 'second.user', 'second-password');

INSERT INTO permissions  VALUES
--        id,          name
    (default,   'user_read'),
    (default, 'user_update');

INSERT INTO employees VALUES
--        id,  user_id,  First Name, Last Name
    (default,        1, 'Uğur Doğa',  'Sezgin'),
    (default,        2, 'Eylül',      'Erdinç');

INSERT INTO leaves VALUES
--        id,   user_id,          leave_type,    replacement_user_id,            start_time,                end_time,     day_count,   description,    status
    (default,         1,    'MaternityLeave',                      1,   '2022-01-01T09:00:00',   '2022-05-05T12:00:00',             5,    'Example1', 'Approved'),
    (default,         2,    'PaternityLeave',                      2,   '2022-01-01T08:00:00',   '2022-06-06T11:00:00',             6,    'Example2', 'Rejected');

INSERT INTO expenses VALUES
--        id,   user_id,    form_date,      amount, currency,  paid,   description,     status
    (default,         1, '2022-01-01',   '1000.10',    'EUR',  true,    'Example1', 'Approved'),
    (default,         2, '2022-02-02', '100000.10',    'TRY', false,    'Example2', 'Rejected');

INSERT INTO receipts VALUES
--        id, expense_id, receipt_date, receipt_category,    amount, vat_rate, description
    (default,          1, '2022-01-01', 'Transportation',   '45.20',     '18', 'Bus Coast');
