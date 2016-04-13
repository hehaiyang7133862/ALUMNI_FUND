-- 商品分类表
create table tb_commodity_type(
	type_id		INTEGER primary key,
	type_name	varchar2(200),
	type_code	varchar2(200),
	parent_id	INTEGER,
	num_order	INTEGER,
	memo		varchar2(2000),
	CREATION_USER	INTEGER,
	CREATION_TIME	VARCHAR2(100),
	UPDATE_USER	INTEGER,
	UPDATE_TIME	DATE
);
-- 商品分类表序列 
create sequence s_tb_commodity_type
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

-- 商品信息表
create table tb_commodity(
	-- 主键
	comm_id		INTEGER primary key,
	-- 商品名称
	comm_name	varchar2(200),
	-- 商品简介
	comm_intro	varchar2(2000),
	-- 商品详请
	comm_detail	CLOB,
	-- 所属分类
	comm_type	INTEGER,
	-- 是否免运费 1免运费，0不免运费
	is_shipping	char(1), 
	-- 运费
	shipping_fee	number(*,2),
	-- 运费说明
	shipping_memo	varchar2(2000),
	-- 是否捐赠 1捐赠，0不捐赠
	is_donation	char(1), 
	-- 捐赠金额
	donation_fee	number(*,2),
	-- 捐赠说明
	donation_memo	varchar2(2000),
	-- 是否上架 1已上架，0已下架
	is_shelves	char(1),
	-- 是否删除 1已删除，0未删除
	is_delete	char(1),
	-- 备注
	memo		varchar2(2000),
	CREATION_USER	INTEGER,
	CREATION_TIME	VARCHAR2(100),
	UPDATE_USER	INTEGER,
	UPDATE_TIME	DATE,
	IS_HOT		CHAR(1)
);
-- 商品信息表序列 
create sequence s_tb_commodity
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

-- 商品图片信息表
create table tb_commodity_pic(
	pic_id		INTEGER primary key,
	pic_name	varchar2(200),
	pic_ext		varchar2(200),
	pic_path	varchar2(200),
	pic_alt		varchar2(200),
	-- 外键关联商品信息表
	comm_id		INTEGER,
	memo		varchar2(2000),
	num_order	INTEGER,
	CREATION_USER	INTEGER,
	CREATION_TIME	VARCHAR2(100),
	UPDATE_USER	INTEGER,
	UPDATE_TIME	DATE
);
-- 商品参数信息表序列
create sequence s_tb_commodity_pic
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

-- 商品参数信息表
create table tb_commodity_param(
	param_id	INTEGER primary key,
	param_name	varchar2(200),
	param_value	varchar2(2000),
	-- 外键关联商品信息表
	comm_id		INTEGER,
	memo		varchar2(2000),
	num_order	INTEGER,
	CREATION_USER	INTEGER,
	CREATION_TIME	VARCHAR2(100),
	UPDATE_USER	INTEGER,
	UPDATE_TIME	DATE
);
-- 商品参数信息表序列
create sequence s_tb_commodity_param
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

-- 商品型号信息表
create table tb_commodity_detail(
	-- 主键
	detail_id	INTEGER primary key,
	-- 型号名称
	detail_name	varchar2(200),
	-- 型号简介
	detail_intro	varchar2(2000),
	-- 型号图片
	detail_pic	varchar2(200),
	-- 库存数量
	stock_num	INTEGER,
	-- 成本价
	cost_fee	number(*,2),
	-- 出售单价
	sale_fee	number(*,2),
	-- 是否上架 1已上架，0已下架
	is_shelves	char(1),
	-- 是否删除 1已删除，0未删除
	is_delete	char(1),
	-- 外键关联商品信息表
	comm_id		INTEGER,
	memo		varchar2(2000),
	num_order	INTEGER,
	CREATION_USER	INTEGER,
	CREATION_TIME	VARCHAR2(100),
	UPDATE_USER	INTEGER,
	UPDATE_TIME	DATE
);
-- 商品型号信息表序列
create sequence s_tb_commodity_detail
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

-- 商品订单信息表
create table tb_commodity_order(
	-- 主键
	order_id	INTEGER primary key,
	-- 订单编号
	order_no	varchar2(200),
	-- 商品ID 外键
	comm_id		INTEGER,
	-- 商品名称
	comm_name	varchar2(200),
	-- 商品型号ID 外键
	comm_detail_id	INTEGER,
	-- 商品型号名称
	comm_detail_name	varchar2(200),
	-- 商品单价
	comm_fee	number(*,2),
	-- 购买数量
	comm_num	INTEGER,
	-- 收货方式 1买家自提，2物流发货
	shipping_type	char(1),
	-- 买家姓名
	buyer_name	varchar2(200),
	-- 买家电话
	buyer_phone	varchar2(200),
	-- 买家手机
	buyer_mobile	varchar2(200),
	-- 买家邮箱
	buyer_email	varchar2(200),
	-- 买家邮编
	buyer_zipcode	varchar2(200),
	-- 买家地址
	buyer_address	varchar2(200),
	-- 物流运费
	shipping_fee	number(*,2),
	-- 捐赠金额
	donation_fee	number(*,2),
	-- 购买实付金额
	order_fee	number(*,2),
	-- 买家留言
	order_memo	varchar2(2000),
	-- 订单来源  在线购买，线下购买等
	order_source	varchar2(2000),
	-- 订单状态 1等待买家付款，2买家已付款，3已发货，4已收货，5评价
	order_status	char(1),
	-- 买家评价星级 1一星，2二星，3三星，4四星，5五星
	star_num	INTEGER,
	-- 买家评价说明
	star_memo	varchar2(2000),
	-- 备注
	memo		varchar2(2000),
	-- 创建、下单时间
	CREATION_USER	INTEGER,
	CREATION_TIME	VARCHAR2(100),
	-- 买家付款时间
	payment_TIME	VARCHAR2(100),
	-- 发货时间
	shipped_TIME	VARCHAR2(100),
	-- 收货时间
	receipt_TIME	VARCHAR2(100),
	-- 评价时间
	complete_TIME	VARCHAR2(100),
	-- 最近更新时间
	UPDATE_USER	INTEGER,
	UPDATE_TIME	DATE
);
-- 商品型号信息表序列
create sequence s_tb_commodity_order
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

-- 项目分类表
create table tb_foundation_type(
  type_id    INTEGER primary key,
  type_name  varchar2(200),
  type_code  varchar2(200),
  parent_id  INTEGER,
  num_order  INTEGER,
  memo    varchar2(2000),
  CREATION_USER  INTEGER,
  CREATION_TIME  VARCHAR2(100),
  UPDATE_USER  INTEGER,
  UPDATE_TIME  DATE
);
-- 项目分类表序列 
create sequence s_tb_foundation_type
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;


-- 协议图片信息表
create table tb_Protocol_pic(
  pic_id    INTEGER primary key,
  pic_name  varchar2(200),
  pic_ext    varchar2(200),
  pic_path  varchar2(200),
  pic_alt    varchar2(200),
  -- 外键关联项目信息表
  pro_id    INTEGER,
  memo    varchar2(2000),
  num_order  INTEGER,
  CREATION_USER  INTEGER,
  CREATION_TIME  VARCHAR2(100),
  UPDATE_USER  INTEGER,
  UPDATE_TIME  DATE
);
-- 协议图片信息表序列
create sequence s_tb_protocol_pic
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

-- 协议参数信息表
create table tb_protocol_param(
  param_id  INTEGER primary key,
  param_name  varchar2(200),
  param_value  varchar2(2000),
  -- 外键关联项目信息表
  pro_id    INTEGER,
  memo    varchar2(2000),
  num_order  INTEGER,
  CREATION_USER  INTEGER,
  CREATION_TIME  VARCHAR2(100),
  UPDATE_USER  INTEGER,
  UPDATE_TIME  DATE
);
-- 协议参数信息表序列
create sequence s_tb_protocol_param
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

TB_FOUNDATION表中新增FOUND_TYPE、IS_SHELVES、IS_DELETE列
TB_PROTOCOL表中新增IS_SHELVES、IS_DELETE列
tb_commodity中新增IS_HOT列
tb_protocol中新增PRO_TYPE、PRO_INTRO、DONATION_MIN、IS_HOT