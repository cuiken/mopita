create database mopita DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

use mopita;

create table f_category(
	id int not null auto_increment,
	name varchar(20) not null,
	description varchar(100),
	store_id int,
	dtype varchar(32) not null,
	primary key(id)
	
)ENGINE=InnoDB;

create table f_file(
	id int not null auto_increment,
	name varchar(50) not null,
	ux_size int,
	apk_size int,
	ux_path varchar(200),
	apk_path varchar(200),
	avail_machine varchar(255),
	unavail_machine varchar(255),
	icon_path varchar(50),
	market_url varchar(100),
	create_time timestamp,
	category_id int,
	primary key(id)

)ENGINE=InnoDB;

create table f_file_info(
	id int not null auto_increment,
	title varchar(50) not null,
	language varchar(10),
	description varchar(255),
	price float,
	f_id int,
	primary key(id)
	
)ENGINE=InnoDB;

create table f_store_info(
	id int not null auto_increment,
	title varchar(50) not null,
	language varchar(10),
	description varchar(255),
	sort_by varchar(20),
	f_id int,
	store_id int,
	primary key(id)
	
)ENGINE=InnoDB;

create table f_preview(
	id int not null auto_increment,
	pre_path varchar(100),
	f_id int,
	primary key(id)
)ENGINE=InnoDB;

create table f_file_shelf(
	id int not null auto_increment,
	f_id int,
	s_id int,
	primary key(id)
)ENGINE=InnoDB;

