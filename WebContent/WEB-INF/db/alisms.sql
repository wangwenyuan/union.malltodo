CREATE TABLE `javatodo_alisms` (
  `id` char(25) NOT NULL DEFAULT '',
  `alisms_signname` varchar(99) NOT NULL ,
  `alisms_access_key_id` varchar(99) NOT NULL,
  `alisms_access_key_secret` varchar(99) NOT NULL,
  `alisms_template_code` varchar(99) NOT NULL,
  `sms_period_of_validity` int(11) NOT NULL DEFAULT '0' ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4