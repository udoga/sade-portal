DROP TABLE IF EXISTS expenses CASCADE;
DROP TABLE IF EXISTS people CASCADE;
DROP TABLE IF EXISTS projects CASCADE;
DROP TABLE IF EXISTS receipts CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TYPE IF EXISTS access_type CASCADE;
DROP TYPE IF EXISTS approval_status CASCADE;
DROP TYPE IF EXISTS blood_type CASCADE;
DROP TYPE IF EXISTS currency CASCADE;
DROP TYPE IF EXISTS educational_level CASCADE;
DROP TYPE IF EXISTS education_status CASCADE;
DROP TYPE IF EXISTS gender CASCADE;
DROP TYPE IF EXISTS level_of_disability CASCADE;
DROP TYPE IF EXISTS marital_status CASCADE;
DROP TYPE IF EXISTS military_service CASCADE;
DROP TYPE IF EXISTS tour_status CASCADE;
DROP TYPE IF EXISTS receipt_category CASCADE;

CREATE TYPE access_type AS ENUM (
    'Employee', 'Manager', 'Owner'
);

CREATE TYPE approval_status AS ENUM (
    'Approved', 'Rejected', 'Waiting'
);

CREATE TYPE blood_type AS ENUM (
    'ANegative', 'APositive', 'ZeroPositive', 'ZeroNegative', 'ABNegative', 'ABPositive', 'BNegative', 'BPositive'
);

CREATE TYPE currency AS ENUM (
    'TRY', 'EUR', 'USD'
);

CREATE TYPE educational_level AS ENUM (
    'Primary', 'Secondary', 'High_School', 'Associate', 'Bachelor', 'Master', 'Doctorate', 'None'
);

CREATE TYPE education_status AS ENUM (
    'Graduate', 'Student'
);

CREATE TYPE gender AS ENUM (
    'Female', 'Male', 'Unspecified'
);

CREATE TYPE level_of_disability AS ENUM (
    'NotDisabled', 'ThirdLevel', 'SecondLevel', 'FirstLevel'
);

CREATE TYPE marital_status AS ENUM (
    'Married', 'Single', 'Divorced', 'Unspecified'
);

CREATE TYPE military_service AS ENUM (
    'Completed', 'Uncompleted', 'Exempt', 'Delayed', 'Deserter', 'DraftEvader'
);

CREATE TYPE tour_status AS ENUM (
    'Completed', 'Canceled', 'Active', 'Passive'
);

CREATE TYPE receipt_category AS ENUM (
    'Food', 'Communication', 'Transportation', 'Stationery', 'Technology', 'Vehicle', 'Office', 'Accomodation',
    'Notary', 'Cargo', 'Balancing', 'Other'
);

CREATE TABLE expenses(
    id SERIAL PRIMARY KEY NOT NULL,
    person_id INT NOT NULL,
    form_date DATE NOT NULL,
    amount DECIMAL NOT NULL,
    currency currency NOT NULL,
    paid BOOLEAN NOT NULL,
    description VARCHAR(255) NOT NULL,
    approval_status approval_status NOT NULL
);

CREATE TABLE people(
    id SERIAL PRIMARY KEY NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    work_email VARCHAR(255) NOT NULL,
    mobile_phone VARCHAR(255) NOT NULL,
    work_phone VARCHAR(255) NOT NULL,
    employment_start_date DATE NOT NULL,
    contract_end_date DATE,
    working_day_id VARCHAR(255) NOT NULL,
    access_type access_type NOT NULL,
    birthday DATE NOT NULL,
    blood_type blood_type NOT NULL,
    educational_level educational_level NOT NULL,
    education_status education_status NOT NULL,
    educational_institution VARCHAR(150) NOT NULL,
    gender gender NOT NULL,
    id_number VARCHAR(255) NOT NULL,
    level_of_disability level_of_disability NOT NULL,
    marital_status marital_status NOT NULL,
    partner_work_status BOOLEAN NOT NULL,
    military_service military_service NOT NULL,
    military_service_delay_date DATE NOT NULL,
    nationality VARCHAR(255) NOT NULL,
    number_of_children INT NOT NULL,
    tour_status tour_status NOT NULL
);

CREATE TABLE projects(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE receipts(
    id SERIAL PRIMARY KEY NOT NULL,
    receipt_date DATE NOT NULL,
    category receipt_category NOT NULL,
    amount DECIMAL NOT NULL,
    vat_rate SMALLINT NOT NULL,
    description TEXT NOT NULL
);

CREATE TABLE users(
    id SERIAL PRIMARY KEY NOT NULL,
    username VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) NOT NULL
);

INSERT INTO people VALUES
--        id,  first_name,  last_name,                       email,               work_email,     mobile_phone,       work_phone, employment_start_date, contract_end_date, working_day_id,   access_type,     birthday,     blood_type, educational_level, education_status, educational_institution,   gender,     id_number, level_of_disability, marital_status, partner_work_status, military_service, military_service_delay_date, nationality, number_of_children, tour_status
    (default,     'Tarik',     'Mert',       'tarikmert@email.com',     'tmert@workmail.com', '+90 5553553530', '+90 5553335530',          '2022-01-01',      '2022-01-01',          '100',    'Employee', '2000-01-01',    'APositive',        'Bachelor',        'Student',                   'YTU',   'Male', '11111111101',       'NotDisabled',       'Single',               false,    'Uncompleted',                '2024-01-01',        'TR',                  0, 'Completed'),
    (default,      'John',    'Smith',       'johnsmith@email.com',    'jsmith@workmail.com', '+90 5553553531', '+90 5553335531',          '2009-02-03',      '2025-02-03',          '101',       'Owner', '1969-05-12',    'ANegative',          'Master',       'Graduate',                   'ITU',   'Male', '11111111110',       'NotDisabled',      'Married',                true,      'Completed',                '2024-01-01',        'US',                  2,  'Canceled'),
    (default,   'Stephen', 'Thompson', 'stephenthompson@email.com', 'sthompson@workmail.com', '+90 5553553532', '+90 5553335532',          '2010-03-04',      '2024-03-04',          '102',     'Manager', '1972-06-25',    'BPositive',       'Doctorate',       'Graduate',                  'METU',   'Male', '11111111121',        'FirstLevel',     'Divorced',               false,       'Deserter',                '2024-01-01',        'GB',                  1,   'Passive'),
    (default,  'Courtney',  'Stinson', 'courtneystinson@email.com',  'cstinson@workmail.com', '+90 5553553533', '+90 5553335533',          '2017-04-04',      '2023-04-04',          '103',    'Employee', '2004-12-30',    'BNegative',     'High_School',        'Student',                   'CAL', 'Female', '11111111132',       'SecondLevel',       'Single',               false,         'Exempt',                '2024-01-01',        'MT',                  0,    'Active'),
    (default,     'Jared',   'Ackles',     'jaredackles@email.com',   'jackles@workmail.com', '+90 5553553534', '+90 5553335534',          '2018-08-01',      '2024-08-01',          '104',    'Employee', '1972-06-25',   'ABPositive',       'Associate',       'Graduate',                    'KU',   'Male', '11111111143',        'ThirdLevel',  'Unspecified',               false,    'DraftEvader',                '2024-01-01',        'PL',                  1,   'Passive'),
    (default,     'Ginny',   'Potter',     'ginnypotter@email.com',   'jpotter@workmail.com', '+90 5553553535', '+90 5553335535',          '2013-11-11',      '2024-11-11',          '105',    'Employee', '1972-10-09',   'ABNegative',       'Secondary',       'Graduate',                  'HTOO', 'Female', '11111111154',       'NotDisabled',      'Married',                true,         'Exempt',                '2024-01-01',        'GB',                  3,   'Passive'),
    (default,     'Demar',     'Rose',       'demarrose@email.com',     'drose@workmail.com', '+90 5553553536', '+90 5553335536',          '2012-05-02',      '2023-05-02',          '106',    'Employee', '1985-09-10', 'ZeroNegative',         'Primary',       'Graduate',                   'KIO',   'Male', '11111111165',       'NotDisabled',      'Married',                true,        'Delayed',                '2024-01-01',        'US',                  1,  'Canceled'),
    (default,  'Jennifer',    'Curry',   'jennifercurry@email.com',    'jcurry@workmail.com', '+90 5553553537', '+90 5553335537',          '2015-09-29',      '2023-09-29',          '107',    'Employee', '1993-08-18', 'ZeroPositive',            'None',       'Graduate',                  'None', 'Female', '11111111176',       'NotDisabled',       'Single',               false,         'Exempt',                '2024-01-01',        'US',                  0, 'Completed'),
    (default,      'Emma',    'Stein',       'emmastein@email.com',    'estain@workmail.com', '+90 5553553538', '+90 5553335538',          '2015-07-23',      '2023-07-23',          '108',    'Employee', '1966-08-18',    'APositive',        'Bachelor',       'Graduate',                    'IU', 'Female', '11111111187',       'NotDisabled',      'Married',               false,         'Exempt',                '2024-01-01',        'US',                  2, 'Completed'),
    (default,      'Jeff',     'Cena',        'jeffcena@email.com',     'jcena@workmail.com', '+90 5553553539', '+90 5553335539',          '2011-06-26',      '2023-06-26',          '109',    'Employee', '1995-08-18',    'BPositive',        'Bachelor',       'Graduate',                   'GTU',   'Male', '11111111198',       'NotDisabled',       'Single',               false,      'Completed',                '2024-01-01',        'US',                  1, 'Completed');

INSERT INTO expenses VALUES
--        id, person_id,    form_date,      amount, currency,  paid,   description,     status
    (default,         1, '2022-01-01',   '1000.10',    'EUR',  true,    'Example1', 'Approved'),
    (default,         2, '2022-02-02', '100000.10',    'TRY', false,    'Example2', 'Rejected');

INSERT INTO projects VALUES
--        id,             name
    (default,  'First Project'),
    (default, 'Second Project');

INSERT INTO receipts VALUES
--        id, receipt_date, receipt_category,    amount, vat_rate, description
    (default, '2022-01-01', 'Transportation',   '45.20',     '18', 'Bus Coast');

INSERT INTO users VALUES
--        id,      username,     full_name
    (default,  'first.user',  'First User'),
    (default, 'second.user', 'Second User');
