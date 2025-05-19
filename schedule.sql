create database sd;
show databases;
use sd;

create table member (
                        id bigint auto_increment primary key not null,
                        user_name varchar(20) not null,
                        user_id varchar(20) not null,
                        user_pw varchar(20) not null,
                        user_email varchar(50) not null,
                        user_phone_number varchar(30) not null
)character set utf8mb4 collate utf8mb4_unicode_ci;


create table schedule (
                          schedule_id bigint auto_increment primary key not null,
                          member_id bigint not null,
                          user_name varchar(20) not null,
                          title varchar(20) not null,
                          contents varchar(200),
                          created_date DATETIME,
                          updated_date DATETIME,
                          foreign key (member_id) references member (id)
);

show tables;
desc member;
desc schedule;