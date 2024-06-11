CREATE TABLE users(
    id IDENTITY PRIMARY KEY NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE permissions(
    id IDENTITY PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE sessions(
    id IDENTITY PRIMARY KEY NOT NULL,
    user_id INT NOT NULL,
    begin_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    token VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE RESTRICT
);

CREATE TABLE employees(
    id IDENTITY PRIMARY KEY NOT NULL,
    user_id INT NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE RESTRICT
);

CREATE TABLE leaves(
    id IDENTITY PRIMARY KEY NOT NULL,
    employee_id INT NOT NULL,
    leave_type VARCHAR(255) NOT NULL,
    replacement_employee_id INT NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    day_count INT NOT NULL,
    description VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE RESTRICT,
    FOREIGN KEY (replacement_employee_id) REFERENCES employees(id) ON DELETE RESTRICT
);

CREATE TABLE expenses(
    id IDENTITY PRIMARY KEY NOT NULL,
    employee_id INT NOT NULL,
    form_date DATE NOT NULL,
    amount DECIMAL NOT NULL,
    currency VARCHAR(255) NOT NULL,
    paid BOOLEAN NOT NULL,
    description VARCHAR(255) NOT NULL,
    approval_status VARCHAR(255) NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE RESTRICT
);

CREATE TABLE receipts(
    id IDENTITY PRIMARY KEY NOT NULL,
    expense_id INT NOT NULL,
    receipt_date DATE NOT NULL,
    category VARCHAR(255) NOT NULL,
    amount DECIMAL NOT NULL,
    vat_rate SMALLINT NOT NULL,
    description TEXT NOT NULL,
    FOREIGN KEY (expense_id) REFERENCES expenses(id) ON DELETE RESTRICT
);

INSERT INTO users VALUES
--        id,      username,                                                            password
    (default,        'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918'),
    (default,  'doga.sezgin', '6181e874f2fcb2ef71aa4598fd8fbc2dd0e2483eac363b664c51dd0476815840'),
    (default, 'eylul.erdinc', '65e527189be2f94987de4e2c623297a1337d1752fde15aa12e6546dd19a1de18');

INSERT INTO permissions  VALUES
--        id,          name
    (default,   'user_read'),
    (default, 'user_update');

INSERT INTO employees VALUES
--        id,  user_id,  first_name, last_name
    (default,        2, 'Uğur Doğa',  'Sezgin'),
    (default,        3, 'Eylül',      'Erdinç');

INSERT INTO leaves VALUES
--        id, employee_id,          leave_type, replacement_employee_id,              start_time,                end_time,  day_count,   description,     status
    (default,           1,    'MaternityLeave',                       2,   '2022-01-01T09:00:00',   '2022-05-05T12:00:00',          5,    'Example1', 'Approved'),
    (default,           2,    'PaternityLeave',                       1,   '2022-01-01T08:00:00',   '2022-06-06T11:00:00',          6,    'Example2', 'Rejected');

INSERT INTO expenses VALUES
--        id, employee_id,    form_date,      amount, currency,  paid,   description,     status
    (default,           1, '2022-01-01',   '1000.10',    'EUR',  true,    'Example1', 'Approved'),
    (default,           2, '2022-02-02', '100000.10',    'TRY', false,    'Example2', 'Rejected');

INSERT INTO receipts VALUES
--        id, expense_id, receipt_date, receipt_category,    amount, vat_rate, description
    (default,          1, '2022-01-01', 'Transportation',   '45.20',     '18', 'Bus Coast');
