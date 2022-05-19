CREATE TABLE `messages` (   -- Chat messages -- 
  `messageId` BIGINT NOT NULL AUTO_INCREMENT,
  `sourceId` BIGINT NOT NULL,  -- Sender Id --
  `targetId` BIGINT NOT NULL,   -- receiver Id--
  `content` TEXT,  -- message text -- 
  `createdAt` TIMESTAMP NOT NULL,  -- for tracking upload date -- 
  PRIMARY KEY (`messageId`),
  CONSTRAINT `fk_umesssage_source`   -- sourceId (sender) as FK for user table --
    FOREIGN KEY (`sourceId`)
    REFERENCES `user` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

ALTER TABLE `messages`   			-- TargetId (receiver) as FK for user table --
ADD CONSTRAINT `fk_umessage_target`
  FOREIGN KEY (`targetId`)
  REFERENCES `user` (`userId`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;