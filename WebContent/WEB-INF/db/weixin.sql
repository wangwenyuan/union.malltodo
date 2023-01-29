CREATE TABLE `javatodo_weixin` (
  `id` char(25) NOT NULL DEFAULT '',
  `appid` varchar(99) NOT NULL,
  `appsecret` varchar(99) NOT NULL,
  `mchid` varchar(255) NOT NULL DEFAULT '' ,
  `wxpay_key` varchar(255) NOT NULL DEFAULT '' ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4