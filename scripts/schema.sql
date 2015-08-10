CREATE TABLE `users` (                   
          `id` int(11) NOT NULL AUTO_INCREMENT,  
          `name` varchar(250) DEFAULT NULL,      
          `email` varchar(50) NOT NULL,          
          `phone` varchar(50) DEFAULT NULL,      
          `password` varchar(50) DEFAULT NULL,   
          PRIMARY KEY (`id`,`email`),            
          UNIQUE KEY `id` (`id`)                 
        ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

