CREATE DATABASE `tedproject` /*!40100 DEFAULT CHARACTER SET utf8 */;
/*-----------------------------------Create Users Table------------------------------------------*/
CREATE TABLE `tedproject`.`users` (
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL COMMENT '		',
  `name` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `telephone` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `role` varchar(50) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `accepted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`username`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `tedproject`.`users`(`username`,`password`,`role`,`accepted`)VALUES ("admin","admin","Administrator",1);
/*-----------------------------------Create Messages Table---------------------------------------*/
CREATE TABLE `tedproject`.`messages` (
  `recipient` VARCHAR(45) NULL,
  `sender_mail` VARCHAR(45) NULL,
  `subject` TINYTEXT NULL,
  `message` MEDIUMTEXT NULL,
  `is_read` TINYINT(1) NULL DEFAULT 0,
  `unique_id` VARCHAR(50) NOT NULL,
  INDEX `username_foreign_idx` (`recipient` ASC),
  PRIMARY KEY (`unique_id`),
  UNIQUE INDEX `unique_id_UNIQUE` (`unique_id` ASC),
  CONSTRAINT `username_foreign`
    FOREIGN KEY (`recipient`)
    REFERENCES `tedproject`.`users` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
/*-----------------------------------Create Houses Table-----------------------------------------*/
CREATE TABLE `tedproject`.`houses` (
  `uniqueCode` VARCHAR(50) NOT NULL,
  `x` VARCHAR(50) NULL,
  `y` VARCHAR(50) NULL,
  `priceBuy` FLOAT NULL DEFAULT 0,
  `priceLent` FLOAT NULL DEFAULT 0,
  `region` VARCHAR(50) NULL,
  `squares` INT(11) NULL DEFAULT 0,
  `heat` VARCHAR(20) NULL,
  `expenses` FLOAT NULL DEFAULT 0,
  `timeAvailable` VARCHAR(50) NULL,
  `username` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  `address` VARCHAR(45) NULL,
  `description` MEDIUMTEXT NULL,
  `type` VARCHAR(45) NULL,
  PRIMARY KEY (`uniqueCode`),
  UNIQUE INDEX `uniqueCode_UNIQUE` (`uniqueCode` ASC),
  INDEX `username_foreign_idx` (`username` ASC),
  CONSTRAINT `username_foreignkey`
    FOREIGN KEY (`username`)
    REFERENCES `tedproject`.`users` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
/*-----------------------------------Create Houses_pics Table------------------------------------*/
CREATE TABLE `tedproject`.`houses_pics` (
  `house_code` VARCHAR(50) NOT NULL,
  `pic` MEDIUMBLOB NULL,
  `description` VARCHAR(45) NULL,
  `unique_key` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`unique_key`),
  UNIQUE INDEX `unique_key_UNIQUE` (`unique_key` ASC),
  INDEX `fk_houses_pics_1_idx` (`house_code` ASC),
  CONSTRAINT `fk_houses_pics_1`
    FOREIGN KEY (`house_code`)
    REFERENCES `tedproject`.`houses` (`uniqueCode`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
/*-----------------------------------Create Algorithm Table--------------------------------------*/
CREATE TABLE `tedproject`.`algorithm` (
  `algorithm_choice` varchar(45) NOT NULL,
  PRIMARY KEY (`algorithm_choice`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*----------------------------------------------------------------------------------------------*/
/*----------------------------------------------------------------------------------------------*/
/*----------------------------------------------------------------------------------------------*/
/*----------------------------------------------------------------------------------------------*/
/*----------------------------------------------------------------------------------------------*/
/*----------------------------------------Add_Sample_Data---------------------------------------*/
INSERT INTO `tedproject`.`users`(username,password,name,lastName,telephone,email,role,country,accepted)
VALUES ('user','user','George','Georgopoulos','98991999','george@gmail.com','Seller,Administrator,Seller,Buyer','GR',0);
INSERT INTO `tedproject`.`users`(username,password,name,lastName,telephone,email,role,country,accepted)
VALUES ('user1','user1','Apostolos','Apostolopoulos','9865298','apo@hotmail.com','Seller,Buyer','GB',1);
INSERT INTO `tedproject`.`users`(username,password,name,lastName,telephone,email,role,country,accepted)
VALUES ('user2','user2','Bill','Billovich','685632388','thekid@yahoo.gr','Seller,Buyer,Lessor,Tenant','RU',1);
INSERT INTO `tedproject`.`users`(username,password,name,lastName,telephone,email,role,country,accepted)
VALUES ('gioulisapo','12345','Apostolos','Gioulis','2106529456','gioulisapo@yahoo.gr','Seller,Buyer,Lessor,Tenant,admin','GR',0);
INSERT INTO `tedproject`.`houses`(uniqueCode, x, y, priceBuy, priceLent, region, squares, heat, expenses, timeAvailable, username, name, address, description, type)
('0c3e5923-5be7-4759-a57b-39abb264372d', '23.971452999999997', '41.1791878', '500000', '300', 'Greece', '65', 'independent', '32', '21-09-2014', 'gioulisapo', 'My house', 'Dramas 23', 'It\'s a great hous', 'house');





