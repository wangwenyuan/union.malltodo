CREATE TABLE `javatodo_role_auth` (
  `id` char(25) NOT NULL DEFAULT '',
  `role_id` char(25) NOT NULL DEFAULT '',
  `m` varchar(20) NOT NULL DEFAULT '',
  `c` varchar(20) NOT NULL DEFAULT '',
  `a` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4