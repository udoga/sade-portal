DROP TABLE items IF EXISTS;
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
    (default, 'first', 'last', 'personal@email.com', 'work@email.com', '+905553553535', '+905553335533', '2022-01-01',
    'Definite', '2022-01-01', '123', 'Employee', '2000-01-01', 'OPositive', 'Bachelor', 'Student', 'YTU', 'Male',
    '11111111110', 'NotDisabled', 'Single', false, 'Uncompleted', '2024-01-01', 'TR', 0, 'Completed');

INSERT INTO projects VALUES
    (default, 'First Project'),
    (default, 'Second Project');

INSERT INTO users VALUES
    (default, 'first.user', 'First User'),
    (default, 'second.user', 'Second User');
