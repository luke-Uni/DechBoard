CREATE TABLE IF NOT EXISTS `announcement` (
  `announcementID` BIGINT NOT NULL AUTO_INCREMENT,
  `createTime` 	TIMESTAMP NOT NULL,
  `updateAt`TIMESTAMP NULL DEFAULT NULL,
  `title` VARCHAR(50), 
  `content` varchar(140) NOT NULL,
  `contentType`varchar(50), -- If special board, English, Algebra  -- 
  `posterID` bigint NOT NULL,
  `filetype` VARCHAR(16),
  `fileURL` TEXT, 
  PRIMARY KEY (`announcementID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4; -- Constrains fehlen -- 

