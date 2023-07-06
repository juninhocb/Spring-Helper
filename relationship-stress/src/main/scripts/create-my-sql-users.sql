DROP DATABASE IF EXISTS db_relationship_stress;
DROP USER IF EXISTS `db_admin`@`%`;
DROP USER IF EXISTS `db_user`@`%`;
CREATE DATABASE IF NOT EXISTS db_relationship_stress CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS `db_admin`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `db_relationship_stress`.* TO `db_admin`@`%`;
CREATE USER IF NOT EXISTS `db_user`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, SHOW VIEW ON `db_relationship_stress`.* TO `db_user`@`%`;
FLUSH PRIVILEGES;