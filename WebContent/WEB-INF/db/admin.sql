CREATE TABLE `javatodo_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT ,
  `mobile` varchar(25) NOT NULL DEFAULT '' ,
  `username` varchar(99) NOT NULL DEFAULT '' ,
  `password` char(32) NOT NULL DEFAULT '' ,
  `hader_img` varchar(255) NOT NULL DEFAULT '',
  `group_id` int(11) NOT NULL DEFAULT '0' ,
  `apptoken` varchar(99) DEFAULT '',
  `is_del` tinyint(4) NOT NULL DEFAULT '0' ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4