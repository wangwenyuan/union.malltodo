CREATE TABLE `javatodo_role` (
  `id` char(25) NOT NULL DEFAULT '',
  `role_name` varchar(255) NOT NULL,
  `is_del` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4