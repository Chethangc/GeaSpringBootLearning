DROP TABLE IF EXISTS `test_table`;

create table IF not exists `test_table`
(
 `id`               INT(20) AUTO_INCREMENT,
 `name`             VARCHAR(20) NOT NULL,
 `created_at`       Datetime DEFAULT NULL,
 `updated_at`       Datetime DEFAULT NULL,
    PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

drop table javajdbc.jdbcconnectiontable;
create table javajdbc.jdbcconnectiontable(
    slno int auto_increment primary key,
    Name varchar(60),
    age int,
    occupation varchar(100)
);
