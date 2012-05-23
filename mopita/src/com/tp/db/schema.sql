create database mopita DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

use mopita;

create table f_category(
	id int not null auto_increment,
	name varchar(50) not null,
	value varchar(50),
	description varchar(100),
	parent_id int,
	dtype varchar(32) not null,
	primary key(id)
	
)ENGINE=InnoDB;

create table f_file(
	id int not null auto_increment,
	name varchar(50) not null,
	title varchar(50),
	ux_size int,
	apk_size int,
	ux_path varchar(255),
	apk_path varchar(255),
	avail_machine varchar(255),
	unavail_machine varchar(255),
	icon_path varchar(255),
	ad_path varchar(255),
	pre_web_path varchar(255),
	pre_client_path varchar(255),
	market_url varchar(100),
	create_time timestamp,
	primary key(id)

)ENGINE=InnoDB;

create table f_file_info(
	id int not null auto_increment,
	title varchar(50) not null,
	language varchar(10),
	author varchar(50),
	short_description varchar(255),
	long_description varchar(255),
	price float,
	f_id int,
	primary key(id)
	
)ENGINE=InnoDB;

create table f_store_info(
	id int not null auto_increment,
	title varchar(50) not null,
	language varchar(10),
	author varchar(50),
	short_description varchar(255),
	long_description varchar(255),
	price float,
	f_id int,
	fi_id int,
	store_id int,
	primary key(id)
	
)ENGINE=InnoDB;

create table f_file_shelf(
	id int not null auto_increment,
	f_id int,
	s_id int,
	sort int,
	primary key(id)
)ENGINE=InnoDB;

create table f_category_file(
	id int not null auto_increment,
	category_id int,
	file_id int,
	primary key(id)
)ENGINE=InnoDB;

create table f_market(
	id int not null auto_increment,
	name varchar(50),
	value varchar(50),
	pk_name varchar(100),
	market_key varchar(255),
	primary key(id)
)ENGINE=InnoDB;

create table f_market_file(
	id int not null auto_increment,
	m_id int,
	f_id int,
	primary key(id)

)ENGINE=InnoDB;

