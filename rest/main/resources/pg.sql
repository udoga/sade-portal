DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS items CASCADE;

CREATE TABLE users(
    id IDENTITY PRIMARY KEY,
    username VARCHAR(255),
    full_name VARCHAR(255)
);

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

INSERT INTO users VALUES
(default, 'first.user', 'First User'),
(default, 'second.user', 'Second User');

INSERT INTO items VALUES
(default, 11, 12, 13, 14, 15.0, 16.1, 17.2,  true, 'A', 'String One'  , '2018-01-01', 'ValueOne'  ),
(default, 21, 22, 23, 24, 25.0, 26.1, 27.2, false, 'B', 'String Two'  , '2019-01-01', 'ValueTwo'  ),
(default, 31, 32, 33, 34, 35.0, 36.1, 37.2, false, 'C', 'String Three', '2019-01-01', 'ValueThree');