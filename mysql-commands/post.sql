CREATE TABLE IF NOT EXISTS `post` (
  `postId` BIGINT NOT NULL AUTO_INCREMENT,
  `publisherId` BIGINT NOT NULL,
  `title` VARCHAR(50), 
  `status` TINYINT NOT NULL, -- private = 1 , public = 2 or important = 3 --
  `content` VARCHAR(140) NOT NULL,
  `boardType`VARCHAR(50), -- If special board, English, Algebra  -- 
  `createTime` TIMESTAMP NOT NULL,
  `updateAt`TIMESTAMP NULL DEFAULT NULL , -- this contains the last updated date-- 
  `commentCounter` BIGINT NOT NULL, -- counter for accumulated number of  comments -- 
  `upvotes` BIGINT,  
  `downvotes` BIGINT, 
  `filetype` VARCHAR(16), 
  `fileURL` TEXT, 
  PRIMARY KEY (`postId`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4; 

ALTER TABLE `post`
ADD CONSTRAINT `fk_upost_user`    -- publisherID as FK for user table -- 
FOREIGN KEY (`publisherId`)				-- Delete the post without deleting the user -- 
REFERENCES `user` (`userId`)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

