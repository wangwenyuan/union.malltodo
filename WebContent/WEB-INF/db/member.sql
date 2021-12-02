CREATE TABLE `javatodo_member` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(99) NOT NULL DEFAULT '',
  `mobile` varchar(25) NOT NULL DEFAULT '',
  `password` char(32) NOT NULL DEFAULT '',
  `pic` varchar(255) NOT NULL DEFAULT '' ,
  `pid` int(11) unsigned NOT NULL DEFAULT '0',
  `ppid` int(11) NOT NULL DEFAULT '0',
  `pppid` int(11) NOT NULL DEFAULT '0',
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