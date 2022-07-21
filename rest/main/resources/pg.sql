DROP TABLE IF EXISTS people CASCADE;
DROP TABLE IF EXISTS projects CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TYPE IF EXISTS access_type CASCADE;
DROP TYPE IF EXISTS blood_type CASCADE;
DROP TYPE IF EXISTS contract_type CASCADE;
DROP TYPE IF EXISTS educational_level CASCADE;
DROP TYPE IF EXISTS education_status CASCADE;
DROP TYPE IF EXISTS gender CASCADE;
DROP TYPE IF EXISTS level_of_disability CASCADE;
DROP TYPE IF EXISTS marital_status CASCADE;
DROP TYPE IF EXISTS military_service CASCADE;
DROP TYPE IF EXISTS tour_status CASCADE;

CREATE TYPE access_type AS ENUM (
    'Employee', 'Manager', 'Owner'
);

CREATE TYPE blood_type AS ENUM (
    'ANegative', 'APositive', 'OPositive', 'ONegative', 'ABNegative', 'ABPositive', 'BNegative', 'BPositive'
);

CREATE TYPE contract_type AS ENUM (
    'Definite', 'Indefinite'
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
    'NotDisabled', 'ThirdLeve', 'SecondLevel', 'FirstLevel'
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

CREATE TABLE people(
    id SERIAL PRIMARY KEY NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    work_email VARCHAR(255) NOT NULL,
    mobile_phone VARCHAR(255) NOT NULL,
    work_phone VARCHAR(255) NOT NULL,
    employment_start_date DATE NOT NULL,
    contract_type contract_type NOT NULL,
    contract_end_date DATE NOT NULL,
    working_day_id VARCHAR(255) NOT NULL,
    access_type access_type NOT NULL,
    birthday DATE NOT NULL,
    blood_type blood_type NOT NULL,
    educational_level educational_level NOT NULL,
    education_status education_status NOT NULL,
    educational_institution VARCHAR(255) NOT NULL,
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

CREATE TABLE users(
    id SERIAL PRIMARY KEY NOT NULL,
    username VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) NOT NULL
);

INSERT INTO people VALUES
    (default, 'first', 'last', 'personal@email.com', 'work@email.com', '5553553535', '5553335533', '2022-06-27',
    'Definite', '2022-08-11', '123', 'Employee', '2000-02-05', 'APositive', 'Bachelor', 'Student', 'YTU', 'Male',
    '11111111110', 'NotDisabled', 'Single', false, 'Uncompleted', '2020-01-01', 'TR', 0, 'Completed');

INSERT INTO projects VALUES
    (default, 'First Project'),
    (default, 'Second Project');

INSERT INTO users VALUES
    (default, 'first.user', 'First User'),
    (default, 'second.user', 'Second User');
