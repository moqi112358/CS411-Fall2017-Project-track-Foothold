drop table if exists users;
create table if not exists users  (
userID BIGINT unsigned not null PRIMARY key auto_increment,
firstName VARCHAR(50),
lastName VARCHAR(50),
email VARCHAR (50),
address VARCHAR (50),
password VARCHAR (50)
);