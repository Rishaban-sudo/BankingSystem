/* table creation  */

create table customer
(
    cust_id    int unsigned not null auto_increment,
    fname      varchar(50)  not null,
    lname      varchar(50)  not null,
    city       varchar(30),
    zipcode    varchar(10),
    state      varchar(30),
    contact_no varchar(15),
    constraint pk_cust_id primary key (cust_id)
);

ALTER TABLE customer
    ADD COLUMN email VARCHAR(50) NOT NULL;

ALTER TABLE customer
    MODIFY COLUMN email VARCHAR(50) NOT NULL UNIQUE;

ALTER TABLE customer
    ADD COLUMN password VARCHAR(50) NOT NULL;


create table account_type
(
    acc_type_cd  varchar(20) not null,
    name         varchar(50) not null,
    date_offered date        not null,
    date_retired date,
    constraint pk_acc_type_cd primary key (acc_type_cd)
);




create table branch
(
    branch_id smallint unsigned not null auto_increment,
    name      varchar(20)       not null,
    address   varchar(30),
    city      varchar(20),
    state     varchar(2),
    zip       varchar(12),
    constraint pk_branch primary key (branch_id)
);

create table account
(
    account_id         int unsigned not null auto_increment,
    acc_type_cd        varchar(20)  not null,
    cust_id            int unsigned not null,
    open_date          date         not null,
    close_date         date,
    last_activity_date date,
    status             enum ('ACTIVE','CLOSED','FROZEN'),
    open_branch_id     smallint unsigned,
    avail_balance      float(10, 2),
    constraint pk_account_id primary key (account_id),
    constraint fk_acc_type_cd foreign key (acc_type_cd) references account_type (acc_type_cd),
    constraint fk_cust_id foreign key (cust_id) references customer (cust_id),
    constraint fk_open_branch_id foreign key (open_branch_id) references branch (branch_id)
);

ALTER TABLE account
DROP FOREIGN KEY fk_acc_type_cd;

ALTER TABLE account
ADD CONSTRAINT fk_acc_type_cd foreign key (acc_type_cd) references account_type (acc_type_cd)
ON UPDATE CASCADE;



create table bank_admin
(
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    constraint pk_username primary key (username)
);

create table transaction
(
    txn_id      integer unsigned not null auto_increment,
    txn_date    datetime         not null,
    src_account_id  integer unsigned not null,
    dest_account_id integer unsigned not null,
    amount      float(10, 2)     not null,
    constraint pk_txn_id primary key (txn_id),
    constraint fk_src_account_id foreign key (src_account_id) references account (account_id),
    constraint fk_dest_account_id foreign key (dest_account_id) references account (account_id)
);

ALTER TABLE transaction
DROP FOREIGN KEY fk_src_account_id;

ALTER TABLE transaction
ADD CONSTRAINT fk_src_account_id foreign key (src_account_id) references account (account_id)
ON DELETE CASCADE;


ALTER TABLE transaction
DROP FOREIGN KEY fk_dest_account_id;

ALTER TABLE transaction
ADD CONSTRAINT fk_dest_account_id foreign key (dest_account_id) references account (account_id)
ON DELETE CASCADE;
