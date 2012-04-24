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
	create_time timestamp,
	primary key(id)

)ENGINE=InnoDB;

create table f_file_info(
	id int not null auto_increment,
	title varchar(50) not null,
	description varchar(100),
	icon_path varchar(50),
	market_url varchar(100),
	create_time timestamp,
	available varchar(5),
	sort_by int,
	price float,
	fs_id int,
	shelf_id int,
	primary key(id)
	
)ENGINE=InnoDB;

create table f_preview(
	id int not null auto_increment,
	pre_path varchar(100),
	f_id int,
	primary key(id)
)ENGINE=InnoDB;

create table f_category_file(
	id int not null auto_increment,
	category_id int,
	file_id int,
	primary key(id)
)ENGINE=InnoDB;

