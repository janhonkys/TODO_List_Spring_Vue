CREATE DATABASE  IF NOT EXISTS `employee_directory`;
USE `employee_directory`;

CREATE Table todo (
`id` int NOT NULL AUTO_INCREMENT,
`title` varchar(100) DEFAULT NULL, 
`description` varchar(2000) DEFAULT NULL,
`priority` varchar(20) DEFAULT NULL, 
`date` DATE DEFAULT NULL,
`completed` boolean DEFAULT FALSE,
 PRIMARY KEY (`id`));

INSERT INTO todo(`id`, `title`, `description`, `priority`, `date`) VALUES(1, 'Cook', 'Today I have to cook dinner.', 'Low', '2024-09-24'),
					(2, 'TV', 'Today I have to watch TV.', 'Medium', '2024-09-23'),
                    (3, 'Bike', 'Today I have to ride a bike.', 'High', '2024-09-25'),
                    (4, 'Clean', 'Today I have to clean bathroom.', 'High', '2024-09-22');
TRUNCATE todo;
SELECT * FROM todo;




