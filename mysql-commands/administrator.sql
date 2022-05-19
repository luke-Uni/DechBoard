CREATE TABLE IF NOT EXISTS `administrator` (
  `adminID` bigint NOT NULL,
  `password` varchar(32) NOT NULL,
  `announcementNumber` bigint NOT NULL,
  PRIMARY KEY (`adminID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf32; 

ALTER TABLE `administrator`
ADD CONSTRAINT `administrator_ibfk_1` 
FOREIGN KEY (`adminID`) 
REFERENCES `announcement` (`posterID`) 
ON DELETE RESTRICT 
ON UPDATE RESTRICT;