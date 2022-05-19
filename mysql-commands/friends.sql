CREATE TABLE `friends` (
  `friendshipId` BIGINT NOT NULL AUTO_INCREMENT,
  `sourceId` BIGINT NOT NULL,
  `targetId` BIGINT NOT NULL,
  `type` TINYINT, -- Mother, Neighboor, Student,etc... -- 
  `status` TINYINT NOT NULL, -- requested, refuse, ignored, ... -- 
  `createdAt` TIMESTAMP NOT NULL,
  `notes` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`friendshipId`),

CONSTRAINT `fk_friend_source` -- SourceID as FK for user table -- 
FOREIGN KEY (`sourceId`)
REFERENCES `user` (`userId`)
ON DELETE NO ACTION
ON UPDATE NO ACTION);
    
ALTER TABLE `friends`
ADD UNIQUE `uq_friend`(`sourceId`, `targetId`); -- making friendship between 2 persons unique -- 

ALTER TABLE `friends` 
ADD CONSTRAINT `fk_friend_target`   -- TargetID as FK for user table -- 
FOREIGN KEY (`targetId`)
REFERENCES `user` (`userId`)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

 