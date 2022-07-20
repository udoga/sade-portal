DROP TABLE items IF EXISTS;
DROP TABLE people IF EXISTS;
DROP TABLE projects IF EXISTS;
DROP TABLE users IF EXISTS;

CREATE TABLE items(
    id IDENTITY PRIMARY KEY,
    byte_field TINYINT,
    short_field SMALLINT,
    int_field INT,
    long_field BIGINT,
    double_field DOUBLE,
    float_field REAL,
    decimal_field DECIMAL,
    boolean_field BOOLEAN,
    char_field CHAR,
    string_field VARCHAR(255),
    date_field DATE,
    enum_field VARCHAR(255)
);

CREATE TABLE people(
    id IDENTITY PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255),
    work_email VARCHAR(255),
    mobile_Phone VARCHAR(255),
    work_phone VARCHAR(255),
    employment_start_date DATE,
    contract_type VARCHAR(255),
    contract_end_date DATE,
    working_day_id VARCHAR(255),
    access_type VARCHAR(255),
    birthday DATE,
    blood_type VARCHAR(255),
    educational_level VARCHAR(255),
    education_status VARCHAR(255),
    educational_institution VARCHAR(255),
    gender VARCHAR(255),
    id_number VARCHAR(255),
    level_of_disability VARCHAR(255),
    marital_status VARCHAR(255),
    partner_work_status BOOLEAN,
    military_service VARCHAR(255),
    military_service_delay_date DATE,
    nationality VARCHAR(255),
    number_of_children INT,
    tour_status VARCHAR(255)
);

CREATE TABLE projects(
    id IDENTITY PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE users(
    id IDENTITY PRIMARY KEY,
    username VARCHAR(255),
    full_name VARCHAR(255)
);

INSERT INTO items VALUES
    (default, 11, 12, 13, 14, 15.0, 16.1, 17.2,  true, 'A', 'String One'  , '2018-01-01', 'ValueOne'  ),
    (default, 21, 22, 23, 24, 25.0, 26.1, 27.2, false, 'B', 'String Two'  , '2019-01-01', 'ValueTwo'  ),
    (default, 31, 32, 33, 34, 35.0, 36.1, 37.2, false, 'C', 'String Three', '2019-01-01', 'ValueThree');

INSERT INTO people VALUES
    (default , 'first', 'last', 'email', 'workmail', '5553553535', '5553335533', '2022-01-01', 'Definite', '2022-01-01', '123', 'Employee', '2000-01-01', 'OPositive', 'Bachelor', 'Student', 'YTU', 'Male', '11111111110', 'NotDisabled', 'Single', false, 'Uncompleted', '2024-01-01', 'TR', 0, 'Completed');

INSERT INTO projects VALUES
    (default, 'First Project'),
    (default, 'Second Project');

INSERT INTO users VALUES
    (default, 'first.user', 'First User'),
    (default, 'second.user', 'Second User');
