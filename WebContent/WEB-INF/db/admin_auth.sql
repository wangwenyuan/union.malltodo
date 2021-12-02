CREATE TABLE `javatodo_admin_auth` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `group_id` int(11) unsigned NOT NULL DEFAULT '0',
  `m` varchar(20) NOT NULL DEFAULT '' ,
  `c` varchar(20) NOT NULL DEFAULT '' ,
  `a` varchar(20) NOT NULL DEFAULT '' ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4