CREATE TABLE `javatodo_menu_cache` (
  `id` char(25) NOT NULL DEFAULT '',
  `website_id` char(25) NOT NULL DEFAULT '',
  `menu` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `website_id` (`website_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4