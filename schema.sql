create database acme_bank;

create table accounts(
	account_id varchar(10) not null,
    name varchar(64) not null,
    balance decimal(10,2) not null,
    primary key(account_id)
);

INSERT INTO accounts (account_id,name,balance) VALUES ("V9L3Jd1BBI","fred",100);
INSERT INTO accounts (account_id,name,balance) VALUES ("fhRq46Y6vB","barney",300);
INSERT INTO accounts (account_id,name,balance) VALUES ("uFSFRqUpJy","wilma",1000);
INSERT INTO accounts (account_id,name,balance) VALUES ("ckTV56axff","betty",1000);
INSERT INTO accounts (account_id,name,balance) VALUES ("Qgcnwbshbh","pebbles",50);
INSERT INTO accounts (account_id,name,balance) VALUES ("if9l185l18","bambam",50);

select * from accounts;
drop table accounts;

