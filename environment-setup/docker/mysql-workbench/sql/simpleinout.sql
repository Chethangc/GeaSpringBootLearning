CREATE DEFINER=`chethangc`@`%` PROCEDURE `simpleinoutparam`(INOUT mAge INT)
BEGIN
SELECT name,age FROM `javajdbc`.`jdbcconnectiontable` WHERE javajdbc.jdbcconnectiontable.age>mAge;
SELECT COUNT(*)  INTO mAge FROM `javajdbc`.`jdbcconnectiontable` WHERE javajdbc.jdbcconnectiontable.age>mAge;
END