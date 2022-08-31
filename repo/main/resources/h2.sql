CREATE TABLE people(
    id IDENTITY PRIMARY KEY NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    work_email VARCHAR(255) NOT NULL,
    mobile_phone VARCHAR(255) NOT NULL,
    work_phone VARCHAR(255) NOT NULL,
    employment_start_date DATE NOT NULL,
    contract_end_date DATE,
    access_type VARCHAR(255) NOT NULL,
    birthday DATE NOT NULL,
    blood_type VARCHAR(255) NOT NULL,
    education_level VARCHAR(255) NOT NULL,
    graduated BOOLEAN NOT NULL,
    educational_institution VARCHAR(150) NOT NULL,
    gender VARCHAR(255) NOT NULL,
    id_number VARCHAR(255) NOT NULL,
    level_of_disability VARCHAR(255) NOT NULL,
    marital_status VARCHAR(255) NOT NULL,
    partner_work_status BOOLEAN NOT NULL,
    military_service VARCHAR(255) NOT NULL,
    military_service_delay_date DATE NOT NULL,
    nationality VARCHAR(255) NOT NULL,
    number_of_children INT NOT NULL,
    tour_status VARCHAR(255) NOT NULL
);

CREATE TABLE projects(
    id IDENTITY PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE users(
    id IDENTITY PRIMARY KEY NOT NULL,
    username VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) NOT NULL
);

CREATE TABLE expenses(
    id IDENTITY PRIMARY KEY NOT NULL,
    person_id INT NOT NULL,
    form_date DATE NOT NULL,
    amount DECIMAL NOT NULL,
    currency VARCHAR(255) NOT NULL,
    paid BOOLEAN NOT NULL,
    description VARCHAR(255) NOT NULL,
    approval_status VARCHAR(255) NOT NULL,
    FOREIGN KEY (person_id) REFERENCES people(id) ON DELETE RESTRICT
);

CREATE TABLE leaves(
    id IDENTITY PRIMARY KEY NOT NULL,
    person_id INT NOT NULL,
    leave_type VARCHAR(255) NOT NULL,
    replacement_person_id INT NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    day_count INT NOT NULL,
    description VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    FOREIGN KEY (person_id) REFERENCES people(id) ON DELETE RESTRICT
);

CREATE TABLE receipts(
    id SERIAL PRIMARY KEY NOT NULL,
    expense_id INT NOT NULL,
    receipt_date DATE NOT NULL,
    category VARCHAR(255) NOT NULL,
    amount DECIMAL NOT NULL,
    vat_rate SMALLINT NOT NULL,
    description TEXT NOT NULL,
    FOREIGN KEY (expense_id) REFERENCES people(id) ON DELETE RESTRICT
);

INSERT INTO people VALUES
--        id,  first_name,  last_name,                       email,               work_email,     mobile_phone,       work_phone, employment_start_date, contract_end_date,    access_type,     birthday,     blood_type, education_level,    graduated, educational_institution,   gender,     id_number, level_of_disability, marital_status, partner_work_status, military_service, military_service_delay_date, nationality, number_of_children, tour_status
    (default,     'Tarik',     'Mert',       'tarikmert@email.com',     'tmert@workmail.com', '+90 5553553530', '+90 5553335530',          '2022-01-01',      '2022-01-01',     'Employee', '2000-01-01',    'APositive',      'Bachelor',        false,                   'YTU',   'Male', '11111111101',       'NotDisabled',       'Single',               false,    'Uncompleted',                '2024-01-01',        'TR',                  0, 'Completed'),
    (default,      'John',    'Smith',       'johnsmith@email.com',    'jsmith@workmail.com', '+90 5553553531', '+90 5553335531',          '2009-02-03',      '2025-02-03',        'Owner', '1969-05-12',    'ANegative',        'Master',         true,                   'ITU',   'Male', '11111111110',       'NotDisabled',      'Married',                true,      'Completed',                '2024-01-01',        'US',                  2,  'Canceled'),
    (default,   'Stephen', 'Thompson', 'stephenthompson@email.com', 'sthompson@workmail.com', '+90 5553553532', '+90 5553335532',          '2010-03-04',      '2024-03-04',      'Manager', '1972-06-25',    'BPositive',     'Doctorate',         true,                  'METU',   'Male', '11111111121',        'FirstLevel',     'Divorced',               false,       'Deserter',                '2024-01-01',        'GB',                  1,   'Passive'),
    (default,  'Courtney',  'Stinson', 'courtneystinson@email.com',  'cstinson@workmail.com', '+90 5553553533', '+90 5553335533',          '2017-04-04',      '2023-04-04',     'Employee', '2004-12-30',    'BNegative',   'High_School',        false,                   'CAL', 'Female', '11111111132',       'SecondLevel',       'Single',               false,         'Exempt',                '2024-01-01',        'MT',                  0,    'Active'),
    (default,     'Jared',   'Ackles',     'jaredackles@email.com',   'jackles@workmail.com', '+90 5553553534', '+90 5553335534',          '2018-08-01',      '2024-08-01',     'Employee', '1972-06-25',   'ABPositive',     'Associate',         true,                    'KU',   'Male', '11111111143',        'ThirdLevel',  'Unspecified',               false,    'DraftEvader',                '2024-01-01',        'PL',                  1,   'Passive'),
    (default,     'Ginny',   'Potter',     'ginnypotter@email.com',   'jpotter@workmail.com', '+90 5553553535', '+90 5553335535',          '2013-11-11',      '2024-11-11',     'Employee', '1972-10-09',   'ABNegative',     'Secondary',         true,                  'HTOO', 'Female', '11111111154',       'NotDisabled',      'Married',                true,         'Exempt',                '2024-01-01',        'GB',                  3,   'Passive'),
    (default,     'Demar',     'Rose',       'demarrose@email.com',     'drose@workmail.com', '+90 5553553536', '+90 5553335536',          '2012-05-02',      '2023-05-02',     'Employee', '1985-09-10', 'ZeroNegative',       'Primary',         true,                   'KIO',   'Male', '11111111165',       'NotDisabled',      'Married',                true,        'Delayed',                '2024-01-01',        'US',                  1,  'Canceled'),
    (default,  'Jennifer',    'Curry',   'jennifercurry@email.com',    'jcurry@workmail.com', '+90 5553553537', '+90 5553335537',          '2015-09-29',      '2023-09-29',     'Employee', '1993-08-18', 'ZeroPositive',          'None',         true,                  'None', 'Female', '11111111176',       'NotDisabled',       'Single',               false,         'Exempt',                '2024-01-01',        'US',                  0, 'Completed'),
    (default,      'Emma',    'Stein',       'emmastein@email.com',    'estain@workmail.com', '+90 5553553538', '+90 5553335538',          '2015-07-23',      '2023-07-23',     'Employee', '1966-08-18',    'APositive',      'Bachelor',         true,                    'IU', 'Female', '11111111187',       'NotDisabled',      'Married',               false,         'Exempt',                '2024-01-01',        'US',                  2, 'Completed'),
    (default,      'Jeff',     'Cena',        'jeffcena@email.com',     'jcena@workmail.com', '+90 5553553539', '+90 5553335539',          '2011-06-26',      '2023-06-26',     'Employee', '1995-08-18',    'BPositive',      'Bachelor',         true,                   'GTU',   'Male', '11111111198',       'NotDisabled',       'Single',               false,      'Completed',                '2024-01-01',        'US',                  1, 'Completed');

INSERT INTO projects VALUES
--        id,             name
    (default,  'First Project'),
    (default, 'Second Project');

INSERT INTO users VALUES
--        id,      username,     full_name
    (default,  'first.user',  'First User'),
    (default, 'second.user', 'Second User');

INSERT INTO expenses VALUES
--        id, person_id,    form_date,      amount, currency,  paid,   description,     status
    (default,         1, '2022-01-01',   '1000.10',    'EUR',  true,    'Example1', 'Approved'),
    (default,         2, '2022-02-02', '100000.10',    'TRY', false,    'Example2', 'Rejected');

INSERT INTO leaves VALUES
--        id, person_id,          leave_type,    replacement_person_id,            start_time,                end_time,     day_count,   description,    status
    (default,         1,    'MaternityLeave',                      1,   '2022-01-01T09:00:00',   '2022-05-05T12:00:00',          5,    'Example1', 'Approved'),
    (default,         2,    'PaternityLeave',                      2,   '2022-01-01T08:00:00',   '2022-06-06T11:00:00',          6,    'Example2', 'Rejected');

INSERT INTO receipts VALUES
--        id, expense_id, receipt_date, receipt_category,    amount, vat_rate, description
    (default,          1, '2022-01-01', 'Transportation',   '45.20',     '18', 'Bus Coast');
