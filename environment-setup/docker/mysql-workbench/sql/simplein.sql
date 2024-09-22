CREATE DEFINER=`chethangc`@`%` PROCEDURE `simpleinparam`(IN mAge INT)
BEGIN
SELECT name,age FROM `javajdbc`.`jdbcconnectiontable` WHERE javajdbc.jdbcconnectiontable.age>mAge;
END