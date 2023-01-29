CREATE TABLE `javatodo_withdrawals` (
  `id` char(25) NOT NULL DEFAULT '',
  `uid` char(25) NOT NULL DEFAULT '',
  `money` decimal(10,2) NOT NULL ,
  `examine_admin_id` char(25) NOT NULL DEFAULT '' ,
  `examine_status` varchar(255) NOT NULL DEFAULT '0' ,
  `no_pass_reason` varchar(255) NOT NULL DEFAULT '' ,
  `serial_number` varchar(255) NOT NULL DEFAULT '' ,
  `addtime` bigint(20) NOT NULL DEFAULT '0',
  `examine_time` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4