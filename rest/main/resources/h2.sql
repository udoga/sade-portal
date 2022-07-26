DROP TABLE people IF EXISTS;
DROP TABLE projects IF EXISTS;
DROP TABLE users IF EXISTS;

CREATE TABLE people(
    id IDENTITY PRIMARY KEY NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    work_email VARCHAR(255) NOT NULL,
    mobile_phone VARCHAR(255) NOT NULL,
    work_phone VARCHAR(255) NOT NULL,
    employment_start_date DATE NOT NULL,
    contract_type VARCHAR(255) NOT NULL,
    contract_end_date DATE NOT NULL,
    working_day_id VARCHAR(255) NOT NULL,
    access_type VARCHAR(255) NOT NULL,
    birthday DATE NOT NULL,
    blood_type VARCHAR(255) NOT NULL,
    educational_level VARCHAR(255) NOT NULL,
    education_status VARCHAR(255) NOT NULL,
    educational_institution VARCHAR(255) NOT NULL,
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

INSERT INTO people VALUES
--   id        first_name,  last_name,                       email,               work_email,    mobile_phone,      work_phone, employment_start_date, contract_type, contract_end_date, working_day_id,   access_type,     birthday,     blood_type, educational_level, education_status, educational_institution,   gender,     id_number, level_of_disability, marital_status, partner_work_status, military_service, military_service_delay_date, nationality, number_of_children, tour_status
    (default,     'Tarik',     'Mert',       'tarikmert@email.com',        'tmert@email.com', '+905553553530', '+905553335530',          '2022-01-01',    'Definite',      '2022-01-01',          '100',    'Employee', '2000-01-01',    'APositive',        'Bachelor',        'Student',                   'YTU',   'Male', '11111111101',       'NotDisabled',       'Single',               false,    'Uncompleted',                '2024-01-01',        'TR',                  0, 'Completed');
    (default,      'John',    'Smith',       'johnsmith@email.com',    'jsmith@workmail.com', '+905553553531', '+905553335531',          '2009-02-03',    'Definite',      '2025-02-03',          '101',       'Owner', '1969-05-12',    'ANegative',          'Master',       'Graduate',                   'ITU',   'Male', '11111111110',       'NotDisabled',      'Married',                true,      'Completed',                '2024-01-01',        'US',                  2,  'Canceled');
    (default,   'Stephen', 'Thompson', 'stephenthompson@email.com', 'sthompson@workmail.com', '+905553553532', '+905553335532',          '2010-03-04',    'Definite',      '2024-03-04',          '102',     'Manager', '1972-06-25',    'BPositive',       'Doctorate',       'Graduate',                  'METU',   'Male', '11111111121',        'FirstLevel',     'Divorced',               false,       'Deserter',                '2024-01-01',        'GB',                  1,   'Passive');
    (default,  'Courtney',  'Stinson', 'courtneystinson@email.com',  'cstinson@workmail.com', '+905553553533', '+905553335533',          '2017-04-04',  'Indefinite',      '2023-04-04',          '103',    'Employee', '2004-12-30',    'BNegative',     'High_School',        'Student',                   'CAL', 'Female', '11111111132',       'SecondLevel',       'Single',               false,         'Exempt',                '2024-01-01',        'MT',                  0,    'Active');
    (default,     'Jared',   'Ackles',     'jaredackles@email.com',   'jackles@workmail.com', '+905553553534', '+905553335534',          '2018-08-01',    'Definite',      '2024-08-01',          '104',    'Employee', '1972-06-25',   'ABPositive',       'Associate',       'Graduate',                    'KU',   'Male', '11111111143',        'ThirdLevel',  'Unspecified',               false,    'DraftEvader',                '2024-01-01',        'PL',                  1,   'Passive');
    (default,     'Ginny',   'Potter',     'ginnypotter@email.com',   'jpotter@workmail.com', '+905553553535', '+905553335535',          '2013-11-11',    'Definite',      '2024-11-11',          '105',    'Employee', '1972-10-09',   'ABNegative',       'Secondary',       'Graduate',                  'HTOO', 'Female', '11111111154',       'NotDisabled',      'Married',                true,         'Exempt',                '2024-01-01',        'GB',                  3,   'Passive');
    (default,     'Demar',     'Rose',       'demarrose@email.com',     'drose@workmail.com', '+905553553536', '+905553335536',          '2012-05-02',    'Definite',      '2023-05-02',          '106',    'Employee', '1985-09-10', 'ZeroNegative',         'Primary',       'Graduate',                   'KIO',   'Male', '11111111165',       'NotDisabled',      'Married',                true,        'Delayed',                '2024-01-01',        'US',                  1,  'Canceled');
    (default,  'Jennifer',    'Curry',   'jennifercurry@email.com',    'jcurry@workmail.com', '+905553553537', '+905553335537',          '2015-09-29',    'Definite',      '2023-09-29',          '107',    'Employee', '1993-08-18', 'ZeroPositive',            'None',       'Graduate',                  'None', 'Female', '11111111176',       'NotDisabled',       'Single',               false,         'Exempt',                '2024-01-01',        'US',                  0, 'Completed');
    (default,      'Emma',    'Stein',       'emmastein@email.com',    'estain@workmail.com', '+905553553538', '+905553335538',          '2015-07-23',    'Definite',      '2023-07-23',          '108',    'Employee', '1966-08-18',    'APositive',        'Bachelor',       'Graduate',                    'IU', 'Female', '11111111187',       'NotDisabled',      'Married',               false,         'Exempt',                '2024-01-01',        'US',                  2, 'Completed');
    (default,      'Jeff',     'Cena',        'jeffcena@email.com',     'jcena@workmail.com', '+905553553539', '+905553335539',          '2011-06-26',    'Definite',      '2023-06-26',          '109',    'Employee', '1995-08-18',    'BPositive',        'Bachelor',       'Graduate',                   'GTU',   'Male', '11111111198',       'NotDisabled',       'Single',               false,      'Completed',                '2024-01-01',        'US',                  1, 'Completed');









INSERT INTO projects VALUES
    (default, 'First Project'),
    (default, 'Second Project');

INSERT INTO users VALUES
    (default, 'first.user', 'First User'),
    (default, 'second.user', 'Second User');
