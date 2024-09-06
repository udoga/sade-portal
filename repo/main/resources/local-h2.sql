CREATE TABLE users(
    id IDENTITY PRIMARY KEY NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE sessions(
    id IDENTITY PRIMARY KEY NOT NULL,
    user_id INT NOT NULL,
    begin_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    token VARCHAR(255) NOT NULL
);

CREATE TABLE roles(
    id IDENTITY PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE permissions(
    id IDENTITY PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE assignments(
    id IDENTITY PRIMARY KEY NOT NULL,
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE grants(
    id IDENTITY PRIMARY KEY NOT NULL,
    role_id INT NOT NULL,
    permission_id INT NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles(id),
    FOREIGN KEY (permission_id) REFERENCES permissions(id)
);

CREATE TABLE sectors(
    id IDENTITY PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE clients(
    id IDENTITY PRIMARY KEY NOT NULL,
    sector_id INT NOT NULL,
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

CREATE TABLE employees(
    id IDENTITY PRIMARY KEY NOT NULL,
    user_id INT NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE RESTRICT
);

CREATE TABLE positions(
    id IDENTITY PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE engagements(
    id IDENTITY PRIMARY KEY NOT NULL,
    project_id INT NOT NULL,
    employee_id INT NOT NULL,
    position_id INT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    description TEXT NOT NULL,
    status VARCHAR(255) NOT NULL,
    FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE RESTRICT,
    FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE RESTRICT,
    FOREIGN KEY (position_id) REFERENCES positions(id) ON DELETE RESTRICT
);

CREATE TABLE worklogs(
    id SERIAL PRIMARY KEY NOT NULL,
    project_id INT NOT NULL,
    employee_id INT NOT NULL,
    activity_id INT NOT NULL,
    work_date DATE NOT NULL,
    duration INT NOT NULL,
    status VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE RESTRICT,
    FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE RESTRICT,
    FOREIGN KEY (activity_id) REFERENCES activities(id) ON DELETE RESTRICT
);

CREATE TABLE vacation_categories(
    id IDENTITY PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE vacations(
    id IDENTITY PRIMARY KEY NOT NULL,
    employee_id INT NOT NULL,
    category_id INT NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    day_count INT NOT NULL,
    description VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE RESTRICT,
    FOREIGN KEY (category_id) REFERENCES vacation_categories(id) ON DELETE RESTRICT
);

CREATE TABLE expenses(
    id IDENTITY PRIMARY KEY NOT NULL,
    employee_id INT NOT NULL,
    form_date DATE NOT NULL,
    description VARCHAR(255) NOT NULL,
    approval_status VARCHAR(255) NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE RESTRICT
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
    currency VARCHAR(255) NOT NULL,
    vat_rate SMALLINT NOT NULL,
    description TEXT NOT NULL,
    FOREIGN KEY (expense_id) REFERENCES expenses(id) ON DELETE RESTRICT,
    FOREIGN KEY (category_id) REFERENCES receipt_categories(id) ON DELETE RESTRICT
);

ALTER TABLE activities ADD CONSTRAINT unique_activity_name UNIQUE (name);
ALTER TABLE assignments ADD CONSTRAINT unique_user_role UNIQUE (role_id, user_id);
ALTER TABLE clients ADD CONSTRAINT unique_client_name UNIQUE (name);
ALTER TABLE employees ADD CONSTRAINT unique_employee_user_id UNIQUE (user_id);
ALTER TABLE grants ADD CONSTRAINT unique_permission_role UNIQUE (permission_id, role_id);
ALTER TABLE receipt_categories ADD CONSTRAINT unique_receipt_category_name UNIQUE (name);
ALTER TABLE roles ADD CONSTRAINT unique_role_name UNIQUE (name);
ALTER TABLE sectors ADD CONSTRAINT unique_sector_name UNIQUE (name);
ALTER TABLE users ADD CONSTRAINT unique_username UNIQUE (username);
ALTER TABLE vacation_categories ADD CONSTRAINT unique_vacation_category_name UNIQUE (name);

INSERT INTO users VALUES
--   id,      username,       password
    (default, 'admin',        'c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec'),
    (default, 'doga.sezgin',  'd404aad0d3110d60debb48163beb3b21ee6dcdf4b25dba7b2065c5e9607deefa87ed938d4aafa9f8b74ad7abe82c2bd5e9fdf6f927919cdc3c825a549d552bb8'),
    (default, 'eylul.erdinc', '5e14b3da28f2ce06212b1e061be836d3074c34fcf029c6271a5521c2223abed099083befbc02783af3dcbdfef27e1e597806f54b7e1435884a2773fe51ac0a26');

INSERT INTO roles VALUES
--   id,      name
    (default, 'SP Admin'),
    (default, 'SP Employee'),
    (default, 'SP Project Manager');

INSERT INTO permissions  VALUES
--   id,      name
    (default, 'sp-list_users'),
    (default, 'sp-list_user_options'),
    (default, 'sp-read_user'),
    (default, 'sp-create_user'),
    (default, 'sp-update_user'),
    (default, 'sp-delete_user');

INSERT INTO assignments VALUES
--   id,      user_id, role_id
    (default,       1,       1),
    (default,       2,       2),
    (default,       3,       2);

INSERT INTO grants VALUES
--   id,      role_id, permission_id
    (default,       1,             1),
    (default,       1,             2),
    (default,       1,             3),
    (default,       1,             4),
    (default,       1,             5),
    (default,       1,             6),
    (default,       2,             1),
    (default,       2,             2),
    (default,       2,             3);

INSERT INTO sectors VALUES
--   id,      name
    (default, 'Sector One'),
    (default, 'Sector Two');

INSERT INTO clients VALUES
--   id,      sector_id            name
    (default,         1,   'Client One'),
    (default,         2,   'Client Two');

INSERT INTO projects VALUES
--   id,      client_id, name,          description
    (default,         1, 'Project One', 'Description one.'),
    (default,         2, 'Project Two', 'Description two.');

INSERT INTO activities VALUES
--   id,      name
    (default, 'Meeting'),
    (default, 'Data Analysis');

INSERT INTO employees VALUES
--   id,      user_id,  first_name, last_name
    (default,       2, 'Uğur Doğa',  'Sezgin'),
    (default,       3, 'Eylül',      'Erdinç');

INSERT INTO positions VALUES
--   id,      name
    (default, 'Manager'),
    (default, 'Assistant' );

INSERT INTO engagements VALUES
--   id,      project_id, employee_id, position_id,   start_date,    end_date,        description,   status
    (default,           1,           1,          1, '2022-01-01', '2022-05-05', 'Description one.', 'Active'),
    (default,           2,           2,          2, '2022-02-02', '2022-06-06', 'Description two.', 'Inactive');

INSERT INTO worklogs VALUES
--   id,       project_id, employee_id, activity_id, work_date,    duration, status, description
    (default,           1,           1,          1, '2022-01-01',        5, 'Done',  'Description one.'),
    (default,           2,           2,          2, '2022-02-02',        6, 'Done',  'Description two.');

INSERT INTO vacation_categories VALUES
--   id,      name
    (default, 'ParentalVacation'),
    (default, 'MarriageVacation');

INSERT INTO vacations VALUES
--   id,      employee_id, category_id, start_time,            end_time,              day_count, description,        status
    (default,           1,           1, '2022-01-01T09:00:00', '2022-05-05T12:00:00',         5, 'Description one.', 'Approved'),
    (default,           2,           1, '2022-01-01T08:00:00', '2022-06-06T11:00:00',         6, 'Description two.', 'Rejected');

INSERT INTO expenses VALUES
--   id,      employee_id, form_date,    description,        status
    (default,           1, '2022-01-01', 'Description one.', 'Approved'),
    (default,           2, '2022-02-02', 'Description two.', 'Rejected');

INSERT INTO receipt_categories VALUES
--   id,      name
    (default, 'Transport'),
    (default, 'Food'     );

INSERT INTO receipts VALUES
--   id,      expense_id, receipt_date, category_id, amount,   currency, vat_rate, description
    (default,          1, '2022-01-01',           1,  '45.20',    'TRY',       18, 'Bus Coast'),
    (default,          1, '2022-01-02',           2, '200.50',    'TRY',       18, 'Office Breakfast'),
    (default,          2, '2022-01-03',           1, '300.00',    'TRY',       18, 'Taxi');
