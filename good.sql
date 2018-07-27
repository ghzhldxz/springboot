#商品表信息
Create table `goods`(
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `goods_name` varchar(16) NOT NULL COMMENT '商品名称',
  `goods_title` varchar(64) DEFAULT NULL COMMENT '商品标题',
  `goods_img` varchar(64) DEFAULT NULL comment '商品的图片',
  `goods_detail` longtext COMMENT '商品的详细介绍',
  `goods_price` decimal(10,2) DEFAULT 0.00 COMMENT '商品单价',
  `goods_stock` int(11) DEFAULT 0 COMMENT '商品库存，-1表示没有限制',
PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#秒杀的商品信息表
Create table miaosha_goods(
	id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '秒杀ID',
	goods_id bigint(20) DEFAULT NULL COMMENT '商品ID',
	miaosha_price decimal(10,2) DEFAULT 0.00 COMMENT '秒杀价',
	stock_stock int(11) DEFAULT 0 COMMENT '库存数量',
	start_date datetime default null comment '秒杀开始时间',
	end_date datetime default null comment '秒杀结束时间',
	PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#订单表
Create table order_info(
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  user_id bigint(20) DEFAULT NULL COMMENT '用户ID',
  goods_id bigint(20) DEFAULT NULL COMMENT '商品ID',
  delivery_addr_id bigint(20) DEFAULT NULL COMMENT '收贷地址ID',
  goods_name varchar(16) NOT NULL COMMENT '商品名称',
  goods_count int(11) DEFAULT 0 COMMENT '商品数量',
  goods_price decimal(10,2) DEFAULT 0.00 COMMENT '商品单价',
  order_channel tinyint(4) default 0 comment '1-pc,2-android,3-ios',
  status tinyint(4) default 0 comment '订单状态，0-新建，1-已支付，2-已发贷，3-已收货，4-已退款，5-已完成',
  create_date datetime default null comment '订单创建时间',
  pay_date datetime default null comment '支付时间',
PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#秒杀订单表
Create table miaosha_order(
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  user_id bigint(20) DEFAULT NULL COMMENT '用户ID',
  goods_id bigint(20) DEFAULT NULL COMMENT '商品ID',
  order_id bigint(20) DEFAULT NULL COMMENT '订单ID',
PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;