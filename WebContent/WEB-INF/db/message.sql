CREATE TABLE `javatodo_message` (
  `id` char(25) NOT NULL DEFAULT '',
  `website_id` char(25) NOT NULL DEFAULT '',
  `name` varchar(99) NOT NULL DEFAULT '',
  `tel` varchar(25) NOT NULL DEFAULT '',
  `email` varchar(99) NOT NULL DEFAULT '',
  `ip` varchar(25) NOT NULL DEFAULT '',
  `message` text NOT NULL,
  `addtime` bigint(20) NOT NULL DEFAULT '0',
  `is_del` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `website_id` (`website_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4