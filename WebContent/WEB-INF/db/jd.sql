CREATE TABLE `javatodo_jd` (
  `id` char(25) NOT NULL DEFAULT '',
  `app_key` varchar(255) NOT NULL,
  `app_secret` varchar(255) NOT NULL,
  `site_id` varchar(99) NOT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4