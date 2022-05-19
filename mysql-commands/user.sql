 CREATE TABLE IF NOT EXISTS `dechboard`.`user` (
  `userId` BIGINT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(50) NOT NULL,
  `lastName` VARCHAR(50) NOT NULL,
  `userName` VARCHAR(50) NULL DEFAULT NULL,
  `phoneNumber` VARCHAR(15) NULL,
  `email` VARCHAR(50) NOT NULL,
  `profilePicture` BLOB,  
  `passwordHash` VARCHAR(32) NOT NULL, -- only storing the hash -- 
  `intro` VARCHAR(50) NULL DEFAULT NULL, -- short Text for introduce yourself -- 
  `gender`TINYINT NOT NULL,  -- 1 = man, 2 = women , 3 = divers -- 
  `birthday` DATETIME NOT NULL,
  `status` VARCHAR (50) NOT NULL, -- this specifies Student or Teacher -- 
  
  PRIMARY KEY (`userId`),
  UNIQUE INDEX `uq_userName` (`userName` ASC),
  UNIQUE INDEX `uq_phoneNumber` (`phoneNumber` ASC),
  UNIQUE INDEX `uq_email` (`email` ASC) )
  ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";
-- Constrains fehlen -- 