CREATE TABLE `javatodo_file` (
  `id` char(25) NOT NULL DEFAULT '',
  `admin_id` char(25) NOT NULL DEFAULT '',
  `store_id` char(25) NOT NULL DEFAULT '',
  `uid` char(25) NOT NULL DEFAULT '',
  `agent_id` char(25) NOT NULL DEFAULT '',
  `merchant_id` char(25) NOT NULL DEFAULT '',
  `url` varchar(255) NOT NULL DEFAULT '',
  `name` varchar(99) NOT NULL DEFAULT '',
  `ext` varchar(25) NOT NULL DEFAULT '',
  `show_pic` varchar(255) NOT NULL DEFAULT '',
  `addtime` bigint(20) NOT NULL DEFAULT '0',
  `filesize` bigint(20) NOT NULL DEFAULT '0',
  `is_del` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4