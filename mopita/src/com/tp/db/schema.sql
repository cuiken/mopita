create database mopita DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

use mopita;


    drop table if exists acct_group;

    drop table if exists acct_group_permission;

    drop table if exists acct_user;

    drop table if exists acct_user_group;

    create table acct_group (
        id bigint not null auto_increment,
        name varchar(255) not null unique,
        primary key (id)
    ) ENGINE=InnoDB;

    create table acct_group_permission (
        group_id bigint not null,
        permission varchar(255) not null
    ) ENGINE=InnoDB;

    create table acct_user (
        id bigint not null auto_increment,
        email varchar(255),
        login_name varchar(255) not null unique,
        name varchar(255),
        password varchar(255),
        primary key (id)
    ) ENGINE=InnoDB;

    create table acct_user_group (
        user_id bigint not null,
        group_id bigint not null
    ) ENGINE=InnoDB;

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

create table log_f_client(
	id int not null auto_increment,
	imei varchar(50),
	imsi varchar(50),
	store_type varchar(20),
	down_type varchar(20),
	language varchar(20),
	client_version varchar(20),
	resolution varchar(50),
	from_market varchar(50),
	create_time timestamp,
	primary key(id)
)ENGINE=InnoDB;

create table log_f_home(
	id int not null auto_increment,
	imei varchar(50),
	imsi varchar(50),
	store_type varchar(20),
	down_type varchar(20),
	language varchar(20),
	client_version varchar(20),
	resolution varchar(50),
	from_market varchar(50),
	request_link varchar(100),
	create_time timestamp,
	primary key(id)
)ENGINE=InnoDB;

