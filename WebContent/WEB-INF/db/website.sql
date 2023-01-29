CREATE TABLE `javatodo_website` (
  `id` char(25) NOT NULL DEFAULT '',
  `website_name` varchar(255) NOT NULL ,
  `website_host` varchar(99) NOT NULL ,
  `addtime` bigint(20) NOT NULL,
  `admin_id` char(25) NOT NULL,
  `statistics_code` text NOT NULL,
  `menu_list` text NOT NULL,
  `admin_menu_list` text NOT NULL,
  `is_del` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4