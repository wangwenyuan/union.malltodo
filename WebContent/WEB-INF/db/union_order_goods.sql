CREATE TABLE `javatodo_union_order_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_sn` varchar(99) NOT NULL,
  `type` varchar(25) NOT NULL DEFAULT '',
  `goods_id` varchar(32) NOT NULL,
  `goods_name` varchar(255) NOT NULL,
  `goods_number` int(11) NOT NULL,
  `goods_pic` varchar(255) NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '1' ,
  PRIMARY KEY (`id`),
  KEY `order_sn` (`order_sn`),
  KEY `type` (`type`),
  KEY `status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4