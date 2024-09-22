CREATE DEFINER=`chethangc`@`%` PROCEDURE `simpleoutparam`(IN mAge INT, OUT mCount INT)
BEGIN
SELECT name,age FROM `javajdbc`.`jdbcconnectiontable` WHERE javajdbc.jdbcconnectiontable.age>mAge;
SELECT COUNT(*)  INTO mCount FROM `javajdbc`.`jdbcconnectiontable` WHERE javajdbc.jdbcconnectiontable.age>mAge;
END