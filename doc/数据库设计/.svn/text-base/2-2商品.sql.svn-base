-- 商品分类表
create table tb_commodity_type(
	type_id		INTEGER primary key,
	type_name	varchar2(200),
	type_code	varchar2(200),
	parent_id	INTEGER,
	num_order	VARCHAR2(100),
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
	IS_HOT		CHAR(1),
	HOT_ORDER	VARCHAR2(100)
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
	num_order	VARCHAR2(100),
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
	num_order	VARCHAR2(100),
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
	num_order	VARCHAR2(100),
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
	order_id		INTEGER primary key,	-- 主键
	order_no		varchar2(200),		-- 订单编号
	order_source		varchar2(2000),		-- 订单来源  在线购买，线下购买等
	ORDER_TYPE		VARCHAR2(100),		-- 订单支付方式（支付宝在线支付，银联在线支付，线下支付等）
	order_status		char(1),		-- 订单状态（1、捐赠成功，0+其他、待付款）
	order_fee		number(*,2),		-- 订单总金额（单价*数量+运费+捐赠）
	ORDER_TIME		DATE,			-- 订单下单时间
	ORDER_OK_TIME		DATE,			-- 订单付款时间
	ORDER_MEMO		VARCHAR2(2000),         -- 订单说明（买家留言）

	comm_id			INTEGER,		-- 商品ID 外键
	comm_name		varchar2(200),		-- 商品名称
	comm_detail_id		INTEGER,		-- 商品型号ID 外键
	comm_detail_name	varchar2(200),		-- 商品型号名称

	comm_costfee		number(*,2),		-- 商品成本单价
	comm_salefee		number(*,2),		-- 商品单价
	comm_num		INTEGER,		-- 购买数量
	shipping_type		char(1),		-- 收货方式 1买家自提，2物流发货
	shipping_fee		number(*,2),		-- 物流运费
	shipping_curfee		number(*,2),		-- 物流运费（实际运费）
	donation_fee		number(*,2),		-- 捐赠金额

	buyer_name		varchar2(200),		-- 买家姓名
	buyer_ip		varchar2(200),		-- 买家IP
	buyer_sex		CHAR(1),		-- 买家性别（1、男，2、女，其他、不详）
	buyer_email		varchar2(200),		-- 买家邮箱
	buyer_mobile		varchar2(200),		-- 买家手机
	buyer_phone		varchar2(200),		-- 买家电话
	buyer_zipcode		varchar2(200),		-- 买家邮编
	buyer_sheng		VARCHAR2(100),          -- 买家地址-省
	buyer_shi		VARCHAR2(100),          -- 买家地址-市
	buyer_qu		VARCHAR2(100),          -- 买家地址-区
	buyer_address		varchar2(200),		-- 买家地址详细

	ALUMNI_FLAG		CHAR(1),                --校友标识（1、校友，0+其他、非校友）
	STUDY_YEARIN		VARCHAR2(100),          --校友入学年
	STUDY_YEAROVER		VARCHAR2(100),		--校友离校年
	STUDY_ACADEMY		VARCHAR2(100),          --校友院系
	STUDY_MAJOR		VARCHAR2(100),          --校友专业
	STUDY_CLASS		VARCHAR2(100),          --校友班级
	STUDY_NUM		VARCHAR2(100),          --校友学号
	STUDY_DEGREE		VARCHAR2(100),          --校友学位
	  
	WORK_COMPANY		VARCHAR2(100),          --工作单位
	WORK_DEPART		VARCHAR2(100),          --工作部门
	WORK_DUTY		VARCHAR2(100),          --工作职务
	
	star_num		INTEGER,		-- 买家评价星级 1一星，2二星，3三星，4四星，5五星
	star_memo		varchar2(2000),		-- 买家评价说明
	star_time		DATE,			-- 评价时间
	
	RECEIPT			CLOB,                   --回执信息详情
	MARK			VARCHAR2(200),          --标记信息
	MEMO			VARCHAR2(2000),         --备注说明
	CREATION_USER		INTEGER,                --创建人
	CREATION_TIME		VARCHAR2(100),          --创建时间
	UPDATE_USER		INTEGER,                --最近更新人
	UPDATE_TIME		DATE                    --最近更新时间
);
-- 商品型号信息表序列
create sequence s_tb_commodity_order
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

-- 商品视图
create or replace view vw_commodity as
select a.*,NVL(b.sale_fee_min,0) as sale_fee_min,NVL(b.sale_fee_max,0) as sale_fee_max,NVL(b.stock_count,0) as stock_count,NVL(c.saled_count,0) as saled_count
from tb_commodity a
left join (
     select y1.comm_id,sum(y2.stock_num) as stock_count,min(y2.sale_fee) as sale_fee_min,max(y2.sale_fee) as sale_fee_max
     from tb_commodity y1,tb_commodity_detail y2
     where y1.comm_id=y2.comm_id
     group by y1.comm_id
) b on a.comm_id=b.comm_id
left join (
     select x1.comm_id,count(x2.order_id) as saled_count
     from tb_commodity x1,tb_commodity_order x2
     where x1.comm_id=x2.comm_id and x2.order_status='1'
     group by x1.comm_id
) c on a.comm_id=c.comm_id