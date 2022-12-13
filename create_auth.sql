create table if not exists account_entity(username varchar(20) PRIMARY KEY,password varchar(20) NOT NULL,role ENUM('admin','user'));
insert into account_entity values('admin','admin','admin');
insert into account_entity values('user','user','user');