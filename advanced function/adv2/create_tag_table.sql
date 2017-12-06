use foothold;

CREATE TABLE Tags
(
HouseID bigint(20) unsigned not null PRIMARY key, 
Tag varChar(1500)
);

load data local infile "/home/huaminz2/Project1/adv2/house_tag.csv" into table Tags fields terminated by ',' enclosed by '"'  lines terminated by '\n';