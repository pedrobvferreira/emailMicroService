CREATE SCHEMA `ms_user` ;

CREATE TABLE `ms_user`.`user_model` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `email` VARCHAR(100) NULL,
  `password` VARCHAR(100) NULL,
  PRIMARY KEY (`id`));
