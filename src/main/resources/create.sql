CREATE DATABASE IF NOT EXISTS `dicegames`;
use `dicegames`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `registration_date` datetime(6) NOT NULL,
   `winrate` double DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `games` (
  `game_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_dice` int(11) DEFAULT NULL,
  `result` int(11) DEFAULT NULL,
  `second_dice` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`game_id`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);

