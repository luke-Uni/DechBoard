CREATE TABLE IF NOT EXISTS `comment` (   -- Comments on generated Posts --
  `commentID` BIGINT NOT NULL AUTO_INCREMENT, -- PK --
  `publisherID` BIGINT NOT NULL,  -- ID from the commetator --
  `postId` BIGINT NOT NULL,  -- ID from the commented post --
  `content` varchar(120) NOT NULL,  -- some text --
  `createTime` timestamp NOT NULL,
  `updatedAt`TIMESTAMP NULL DEFAULT NULL,
  primary key (`commentId`),
  KEY `publisherID` (`publisherID`),
  KEY `commentReceiveID` (`commentReceiveID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4; -- Constrains fehlen -- 

ALTER TABLE `comment` 				-- publisher Id as FK for post -- 
ADD CONSTRAINT `fk_user_source`
FOREIGN KEY (`publisherId`)
REFERENCES `user` (`userId`)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE `comment` 				-- commentReceiveId as FK for post -- 
ADD CONSTRAINT `fk_post_source`
FOREIGN KEY (`commentReceiveId`)
REFERENCES `post` (`postId`)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

