CREATE TABLE `javatodo_withdrawals` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `money` decimal(10,2) NOT NULL ,
  `examine_admin_id` int(11) NOT NULL DEFAULT '0' ,
  `examine_status` varchar(255) NOT NULL DEFAULT '0' ,
  `no_pass_reason` varchar(255) NOT NULL DEFAULT '' ,
  `serial_number` varchar(255) NOT NULL DEFAULT '' ,
  `addtime` bigint(20) NOT NULL DEFAULT '0',
  `examine_time` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4