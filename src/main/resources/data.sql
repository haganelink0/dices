INSERT INTO `dicegames`.`user` (`id`, `name`, `registration_date`, `winrate`) VALUES (1, 'bob', now(), 50);

INSERT INTO `dicegames`.`user` (`id`, `name`, `registration_date`, `winrate`) VALUES (2, 'Susan', now(), 100);

INSERT INTO `dicegames`.`games` (`game_id`, `first_dice`, `result`, `second_dice`, `user_id`) VALUES
(1, 3, 0, 4, 1);

INSERT INTO `dicegames`.`games` (`game_id`, `first_dice`, `result`, `second_dice`, `user_id`) VALUES
(2, 4, 1, 6, 1);

INSERT INTO `dicegames`.`games` (`game_id`, `first_dice`, `result`, `second_dice`, `user_id`) VALUES
(3, 5, 0, 2, 2);

INSERT INTO `dicegames`.`games` (`game_id`, `first_dice`, `result`, `second_dice`, `user_id`) VALUES
(4, 6, 0, 1, 2);