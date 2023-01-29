CREATE TABLE `javatodo_member` (
  `id` char(25) NOT NULL DEFAULT '',
  `username` varchar(99) NOT NULL DEFAULT '',
  `mobile` varchar(25) NOT NULL DEFAULT '',
  `password` char(32) NOT NULL DEFAULT '',
  `pic` varchar(255) NOT NULL DEFAULT '' ,
  `pid` char(25) NOT NULL DEFAULT '',
  `ppid` char(25) NOT NULL DEFAULT '',
  `pppid` char(25) NOT NULL DEFAULT '',
  `recommend_code` varchar(25) NOT NULL DEFAULT '' ,
  `web_poster` varchar(255) NOT NULL DEFAULT '' ,
  `createtime` bigint(20) NOT NULL DEFAULT '0',
  `money` decimal(10,2) NOT NULL DEFAULT '0.00' ,
  `money_0` decimal(10,2) NOT NULL DEFAULT '0.00' ,
  `money_1` decimal(10,2) NOT NULL DEFAULT '0.00' ,
  `is_del` tinyint(3) unsigned NOT NULL DEFAULT '0' ,
  PRIMARY KEY (`id`),
  KEY `idx_shareid` (`pid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4