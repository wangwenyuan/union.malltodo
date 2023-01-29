CREATE TABLE `javatodo_commission_set` (
  `id` char(25) NOT NULL DEFAULT '',
  `level_1` decimal(7,4) NOT NULL,
  `level_2` decimal(7,4) NOT NULL,
  `level_3` decimal(7,4) NOT NULL,
  `platform` varchar(25) NOT NULL,
  `is_Internal_purchase` tinyint(4) NOT NULL DEFAULT '0' ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4