CREATE TABLE `javatodo_money_log` (
  `id` char(25) NOT NULL DEFAULT '',
  `uid` char(25) NOT NULL DEFAULT '',
  `admin_id` char(25) NOT NULL DEFAULT '',
  `money` decimal(10,2) NOT NULL,
  `money_type` tinyint(4) NOT NULL DEFAULT '0' ,
  `addtime` bigint(20) NOT NULL DEFAULT '0',
  `table_name` varchar(32) NOT NULL,
  `row_id` char(25) NOT NULL DEFAULT '',
  `type` int(11) NOT NULL ,
  `status` tinyint(4) NOT NULL ,
  `log` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`),
  KEY `money_type` (`money_type`),
  KEY `table_name` (`table_name`),
  KEY `row_id` (`row_id`),
  KEY `status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4