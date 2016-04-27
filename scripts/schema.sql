DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (                   
          `id` INT(11) NOT NULL AUTO_INCREMENT,  
          `name` VARCHAR(250) DEFAULT NULL,      
          `email` VARCHAR(50) NOT NULL,          
          `phone` VARCHAR(50) DEFAULT NULL,      
          `password` VARCHAR(50) DEFAULT NULL,
          `domain` VARCHAR(60) DEFAULT NULL,
          PRIMARY KEY (`id`,`email`),            
          UNIQUE KEY `id` (`id`)                 
        ) ENGINE=INNODB DEFAULT CHARSET=utf8;