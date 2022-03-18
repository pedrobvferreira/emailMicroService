CREATE SCHEMA `ms_user` ;

CREATE TABLE `ms_user`.`user_model` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `email` VARCHAR(100) NULL,
  `password` VARCHAR(100) NULL,
  PRIMARY KEY (`id`));

/*--------*/
CREATE SCHEMA `ms_email` ;

CREATE TABLE `ms_email`.`user_model` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `ms_email` VARCHAR(100) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `ms_email`.`email_model` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email_from` VARCHAR(100) NULL,
  `email_to` VARCHAR(100) NULL,
  `subject` VARCHAR(100) NULL,
  `body` VARCHAR(100) NULL,
  `send_date` DATETIME NULL,
  `status_email` VARCHAR(100) NULL,
  PRIMARY KEY (`id`));
