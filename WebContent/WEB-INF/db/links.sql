CREATE TABLE `javatodo_links` (
  `id` char(25) NOT NULL DEFAULT '',
  `website_id` char(25) NOT NULL DEFAULT '',
  `name` varchar(99) NOT NULL DEFAULT '',
  `pic` varchar(255) NOT NULL DEFAULT '',
  `url` varchar(255) NOT NULL,
  `recommend_level` tinyint(4) NOT NULL DEFAULT '0',
  `sort` int(11) NOT NULL DEFAULT '0',
  `is_del` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `website_id` (`website_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4