CREATE DATABASE foothold;

use foothold;
 
CREATE TABLE Users
(
UserId BIGINT unsigned not null PRIMARY key, 
Name varchar(50),
Email varchar(200),
Password varchar(200)
);

load data local infile "/home/huaminz2/DatabaseFile/database_user(reviewer).csv" into table Users fields terminated by ',' enclosed by '"'  lines terminated by '\n';
ALter table Users add column lastName varchar(50);

CREATE TABLE Owners
(
HostId  BIGINT unsigned not null PRIMARY key auto_increment, 
Name varchar(50),
Host_location varchar(100),
Host_picture_url varchar(300),
Host_listings_count int,
Host_verifications varchar(200),
Host_has_profile_pic varchar(2),
Host_identity_verified varchar(2),
FOREIGN KEY (HostId) references Users(UserID)
);

load data local infile "/home/huaminz2/DatabaseFile/database_owner.csv"  into table Owners fields terminated by ',' enclosed by '"'  lines terminated by '\n';

drop table Houses;
CREATE table Houses ( 
HouseID BIGINT unsigned not null PRIMARY key auto_increment, 
Name varChar(150), 
Space varchar(1500), 
Description varchar(1600),
Neighborhood_overview varchar(2000),
Notes varchar(2000),
Transit varchar(2000),
Access varchar(2000),
Interaction varchar(2000),
House_rules varchar(1200), 
Thumbnail_url varchar(200), 
UserID BIGINT unsigned , 
Street varchar(90), 
City varchar(30), 
State varchar(20), 
Zipcode varchar(20),
Propery_type varchar(30) ,
Room_type varchar(20),
Accommodates varchar(3),
Bathrooms varchar(3),
Bedrooms varchar(3),
Beds varchar(3),
Amenities varchar(500), 
Price varchar(50), 
Security_deposites varchar(50), 
Cleaning_fee varchar(50), 
Guests_includes varchar(3),
Number_of_reviews varchar(3), 
Review_scores_rating varchar(3),
Review_scores_accuracy varchar(3),
Review_scores_cleanliness varchar(3),
Review_scores_checkin varchar(3),
Review_scores_communication varchar(3),
Review_scores_location varchar(3),
Review_scores_value varchar(3),
FOREIGN KEY (UserID) references Users(UserID),
FOREIGN KEY (UserID) references Owners(HostId)
 );

load data local infile "/home/huaminz2/DatabaseFile/database_house.csv"  into table Houses fields terminated by ',' enclosed by '"'  lines terminated by '\n';

CREATE TABLE Rent
(
RentId  BIGINT unsigned not null PRIMARY key auto_increment, 
HouseID  BIGINT unsigned not null,
Book_Date DATE,
Price varchar(200),
FOREIGN KEY (HouseID) references Houses(HouseID)
);

load data local infile "/home/huaminz2/DatabaseFile/database_rent.csv"  into table Rent fields terminated by ',' enclosed by '"'  lines terminated by '\n';

CREATE TABLE Review
(
HouseID  BIGINT unsigned not null,
Review_id  BIGINT unsigned not null PRIMARY key auto_increment, 
Book_Date DATE,
ReviewerId BIGINT unsigned not null,
Comments varchar(3000),
FOREIGN KEY (HouseID) references Houses(HouseID),
FOREIGN KEY (ReviewerId) references Users(UserId)
);

load data local infile "/home/huaminz2/DatabaseFile/database_review.csv"  into table Review fields terminated by ',' enclosed by '"'  lines terminated by '\n';


CREATE TABLE Forum
(
PostId  BIGINT unsigned not null ,
DiscussionId  BIGINT unsigned not null PRIMARY key auto_increment, 
Name varchar(50),
ReviewerId BIGINT unsigned not null,
Post_time DATE,
Content varchar(2000),
FOREIGN KEY (ReviewerId) references Users(UserId)
);

CREATE TABLE HostSummary
(
HostID  BIGINT unsigned not null PRIMARY key auto_increment, 
Comments varchar(3000),
FOREIGN KEY (HostID) references Owners(HostID)
);

load data local infile "/home/huaminz2/DatabaseFile/database_host-description.csv"  into table HostSummary fields terminated by ',' enclosed by '"'  lines terminated by '\n';

create Table housePictures (HouseID  BIGINT unsigned not null, picture_url varchar(50) not null , picture_origin_name VARCHAR(50),FOREIGN KEY (HouseID) references Houses(HouseID));