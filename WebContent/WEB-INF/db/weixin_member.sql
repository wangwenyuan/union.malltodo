CREATE TABLE `javatodo_weixin_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `openid` varchar(99) NOT NULL,
  `unionid` varchar(99) NOT NULL,
  `sex` varchar(25) NOT NULL,
  `country` varchar(25) NOT NULL,
  `province` varchar(25) NOT NULL,
  `city` varchar(25) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `openid` (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4