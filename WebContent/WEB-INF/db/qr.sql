CREATE TABLE `javatodo_qr` (
  `id` char(25) NOT NULL DEFAULT '',
  `bgimg` varchar(255) NOT NULL,
  `bgimg_width` int(11) NOT NULL,
  `bgimg_height` int(11) NOT NULL,
  `qrimg_width` int(11) NOT NULL,
  `qrimg_height` int(11) NOT NULL,
  `qrimg_left` int(11) NOT NULL,
  `qrimg_top` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4