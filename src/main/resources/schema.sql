DROP TABLE account;
DROP TABLE customer;
DROP TABLE bankstatement;
DROP SEQUENCE sq_account;
DROP SEQUENCE sq_customer;
DROP SEQUENCE sq_bankstatement;

CREATE TABLE account (
    id int NOT NULL PRIMARY KEY,
    agency VARCHAR(10) NOT NULL,
    account_number VARCHAR(10) NOT NULL,
    balance DECIMAL(15, 2) NOT NULL,
    account_limit DECIMAL(7, 2) NOT NULL DEFAULT 0,
    account_type VARCHAR(10) NOT NULL
);


CREATE TABLE customer(
    id int PRIMARY KEY,
    name VARCHAR(25) not null,
    surname varchar(100) not null,
    sex char(1) null default 'X',
    account_id int NOT NULL,
    foreign key(account_id) references account(id)
);


CREATE TABLE bankstatement(
    id int NOT NULL primary key,
    transaction_type CHAR(1) NOT NULL,
    VALUE DECIMAL(15, 2) NOT NULL,
    balance DECIMAL(15, 2) NOT NULL,
    history VARCHAR(50),
    movement_date DATE,
    customer_id int NOT NULL,
    foreign key(customer_id) references customer(id)
);

CREATE SEQUENCE sq_account
increment by 1
start with 1;

CREATE SEQUENCE sq_customer
increment by 1
start with 1;

CREATE SEQUENCE sq_bankstatement
increment by 1
start with 1;
