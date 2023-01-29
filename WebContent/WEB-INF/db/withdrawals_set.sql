CREATE TABLE `javatodo_withdrawals_set` (
  `id` char(25) NOT NULL DEFAULT '',
  `min_money` decimal(10,2) NOT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4