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
        salt varchar(64),
        status varchar(32),
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
	ux_w_path varchar(255),
	ux_h_path varchar(255),
	apk_path varchar(255),
	avail_machine varchar(255),
	unavail_machine varchar(255),
	icon_path varchar(255),
	ad_path varchar(255),
	pre_web_path varchar(255),
	pre_client_path varchar(255),
	market_url varchar(100),
	version varchar(10),
	create_time varchar(30),
	modify_time varchar(30),
	primary key(id)

)ENGINE=InnoDB;

create table f_preview(
	id int not null auto_increment,
	name varchar(100) not null,
	path varchar(255) not null,
	theme_id int ,
	primary key(id)

)ENGINE=InnoDB;

create table f_file_info(
	id int not null auto_increment,
	title varchar(50) not null,
	language varchar(10),
	author varchar(50),
	short_description varchar(255),
	long_description varchar(500),
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
	long_description varchar(500),
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

create table f_market_info(
	id int not null auto_increment,
	key_name varchar(30),
	key_value varchar(30),
	theme_id int,
	market_id int,
	primary key(id)
)ENGINE=InnoDB;

create table f_client(
	id int not null auto_increment,
	name varchar(50),
	size int,
	pk_name varchar(255),
	path varchar(255),
	version varchar(35),
	create_time varchar(35),
	modify_time varchar(35),
	dtype varchar(32) not null,
	primary key(id)
)ENGINE=InnoDB;

create table f_client_market(
	client_id int not null,
	market_id int not null
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
	create_time varchar(32),
	primary key(id)
)ENGINE=InnoDB;

create table log_f_store(
	id int not null auto_increment,
	imei varchar(50),
	imsi varchar(50),
	store_type varchar(20),
	down_type varchar(20),
	language varchar(20),
	client_version varchar(20),
	resolution varchar(100),
	from_market varchar(255),
	request_method varchar(50),
	request_params varchar(255),
	create_time varchar(32),
	primary key(id)
)ENGINE=InnoDB;

create table log_count_client(
	id int not null auto_increment,
	create_time varchar(32),
	open_count int,
	total_user int,
	open_user int,
	increment_user int,
	total_download int,
	down_by_content int,
	down_by_share int,
	down_by_other int,
	visit_store_count int,
	visit_store_user int,
	take_times int,
	primary key(id)
	
)ENGINE=InnoDB;

create table log_count_content(
	id int not null auto_increment,
	log_date varchar(32),
	theme_name varchar(32),
	total_visit int,
	visit_by_ad int,
	visit_by_store int,
	total_down int,
	down_by_store int,
	create_time timestamp,
	primary key(id)
)ENGINE=InnoDB;

create table log_cc_market(
	id int not null auto_increment,
	market_name varchar(32),
	total_down int,
	log_c_id int,
	primary key(id)
)ENGINE=InnoDB;

--分类表
create table nav_category(
	id int not null auto_increment,
	name varchar(35),
	value varchar(35),
	parent_id int,
	pic_addr varchar(255),
	dtype varchar(35),
	primary key(id)
)ENGINE=InnoDB;

--具体元素表
create table nav_item(
	id int not null auto_increment,
	name varchar(35),
	value varchar(35),
	nav_addr varchar(255),
	pic_addr varchar(255),
	primary key(id)

)ENGINE=InnoDB;

--分类元素关系表
create table nav_board_navigator(
	id int not null auto_increment,
	b_id int,
	n_id int,
	primary key(id)
)ENGINE=InnoDB;

create table nav_tag_navigator(
	id int not null auto_increment,
	t_id int,
	n_id int,
	primary key(id)

)ENGINE=InnoDB;

