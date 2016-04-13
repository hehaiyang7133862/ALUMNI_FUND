-- ��Ʒ�����
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
-- ��Ʒ��������� 
create sequence s_tb_commodity_type
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

-- ��Ʒ��Ϣ��
create table tb_commodity(
	-- ����
	comm_id		INTEGER primary key,
	-- ��Ʒ����
	comm_name	varchar2(200),
	-- ��Ʒ���
	comm_intro	varchar2(2000),
	-- ��Ʒ����
	comm_detail	CLOB,
	-- ��������
	comm_type	INTEGER,
	-- �Ƿ����˷� 1���˷ѣ�0�����˷�
	is_shipping	char(1), 
	-- �˷�
	shipping_fee	number(*,2),
	-- �˷�˵��
	shipping_memo	varchar2(2000),
	-- �Ƿ���� 1������0������
	is_donation	char(1), 
	-- �������
	donation_fee	number(*,2),
	-- ����˵��
	donation_memo	varchar2(2000),
	-- �Ƿ��ϼ� 1���ϼܣ�0���¼�
	is_shelves	char(1),
	-- �Ƿ�ɾ�� 1��ɾ����0δɾ��
	is_delete	char(1),
	-- ��ע
	memo		varchar2(2000),
	CREATION_USER	INTEGER,
	CREATION_TIME	VARCHAR2(100),
	UPDATE_USER	INTEGER,
	UPDATE_TIME	DATE,
	IS_HOT		CHAR(1),
	HOT_ORDER	VARCHAR2(100)
);
-- ��Ʒ��Ϣ������ 
create sequence s_tb_commodity
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

-- ��ƷͼƬ��Ϣ��
create table tb_commodity_pic(
	pic_id		INTEGER primary key,
	pic_name	varchar2(200),
	pic_ext		varchar2(200),
	pic_path	varchar2(200),
	pic_alt		varchar2(200),
	-- ���������Ʒ��Ϣ��
	comm_id		INTEGER,
	memo		varchar2(2000),
	num_order	VARCHAR2(100),
	CREATION_USER	INTEGER,
	CREATION_TIME	VARCHAR2(100),
	UPDATE_USER	INTEGER,
	UPDATE_TIME	DATE
);
-- ��Ʒ������Ϣ������
create sequence s_tb_commodity_pic
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

-- ��Ʒ������Ϣ��
create table tb_commodity_param(
	param_id	INTEGER primary key,
	param_name	varchar2(200),
	param_value	varchar2(2000),
	-- ���������Ʒ��Ϣ��
	comm_id		INTEGER,
	memo		varchar2(2000),
	num_order	VARCHAR2(100),
	CREATION_USER	INTEGER,
	CREATION_TIME	VARCHAR2(100),
	UPDATE_USER	INTEGER,
	UPDATE_TIME	DATE
);
-- ��Ʒ������Ϣ������
create sequence s_tb_commodity_param
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

-- ��Ʒ�ͺ���Ϣ��
create table tb_commodity_detail(
	-- ����
	detail_id	INTEGER primary key,
	-- �ͺ�����
	detail_name	varchar2(200),
	-- �ͺż��
	detail_intro	varchar2(2000),
	-- �ͺ�ͼƬ
	detail_pic	varchar2(200),
	-- �������
	stock_num	INTEGER,
	-- �ɱ���
	cost_fee	number(*,2),
	-- ���۵���
	sale_fee	number(*,2),
	-- �Ƿ��ϼ� 1���ϼܣ�0���¼�
	is_shelves	char(1),
	-- �Ƿ�ɾ�� 1��ɾ����0δɾ��
	is_delete	char(1),
	-- ���������Ʒ��Ϣ��
	comm_id		INTEGER,
	memo		varchar2(2000),
	num_order	VARCHAR2(100),
	CREATION_USER	INTEGER,
	CREATION_TIME	VARCHAR2(100),
	UPDATE_USER	INTEGER,
	UPDATE_TIME	DATE
);
-- ��Ʒ�ͺ���Ϣ������
create sequence s_tb_commodity_detail
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

-- ��Ʒ������Ϣ��
create table tb_commodity_order(
	order_id		INTEGER primary key,	-- ����
	order_no		varchar2(200),		-- �������
	order_source		varchar2(2000),		-- ������Դ  ���߹������¹����
	ORDER_TYPE		VARCHAR2(100),		-- ����֧����ʽ��֧��������֧������������֧��������֧���ȣ�
	order_status		char(1),		-- ����״̬��1�������ɹ���0+�����������
	order_fee		number(*,2),		-- �����ܽ�����*����+�˷�+������
	ORDER_TIME		DATE,			-- �����µ�ʱ��
	ORDER_OK_TIME		DATE,			-- ��������ʱ��
	ORDER_MEMO		VARCHAR2(2000),         -- ����˵����������ԣ�

	comm_id			INTEGER,		-- ��ƷID ���
	comm_name		varchar2(200),		-- ��Ʒ����
	comm_detail_id		INTEGER,		-- ��Ʒ�ͺ�ID ���
	comm_detail_name	varchar2(200),		-- ��Ʒ�ͺ�����

	comm_costfee		number(*,2),		-- ��Ʒ�ɱ�����
	comm_salefee		number(*,2),		-- ��Ʒ����
	comm_num		INTEGER,		-- ��������
	shipping_type		char(1),		-- �ջ���ʽ 1������ᣬ2��������
	shipping_fee		number(*,2),		-- �����˷�
	shipping_curfee		number(*,2),		-- �����˷ѣ�ʵ���˷ѣ�
	donation_fee		number(*,2),		-- �������

	buyer_name		varchar2(200),		-- �������
	buyer_ip		varchar2(200),		-- ���IP
	buyer_sex		CHAR(1),		-- ����Ա�1���У�2��Ů�����������꣩
	buyer_email		varchar2(200),		-- �������
	buyer_mobile		varchar2(200),		-- ����ֻ�
	buyer_phone		varchar2(200),		-- ��ҵ绰
	buyer_zipcode		varchar2(200),		-- ����ʱ�
	buyer_sheng		VARCHAR2(100),          -- ��ҵ�ַ-ʡ
	buyer_shi		VARCHAR2(100),          -- ��ҵ�ַ-��
	buyer_qu		VARCHAR2(100),          -- ��ҵ�ַ-��
	buyer_address		varchar2(200),		-- ��ҵ�ַ��ϸ

	ALUMNI_FLAG		CHAR(1),                --У�ѱ�ʶ��1��У�ѣ�0+��������У�ѣ�
	STUDY_YEARIN		VARCHAR2(100),          --У����ѧ��
	STUDY_YEAROVER		VARCHAR2(100),		--У����У��
	STUDY_ACADEMY		VARCHAR2(100),          --У��Ժϵ
	STUDY_MAJOR		VARCHAR2(100),          --У��רҵ
	STUDY_CLASS		VARCHAR2(100),          --У�Ѱ༶
	STUDY_NUM		VARCHAR2(100),          --У��ѧ��
	STUDY_DEGREE		VARCHAR2(100),          --У��ѧλ
	  
	WORK_COMPANY		VARCHAR2(100),          --������λ
	WORK_DEPART		VARCHAR2(100),          --��������
	WORK_DUTY		VARCHAR2(100),          --����ְ��
	
	star_num		INTEGER,		-- ��������Ǽ� 1һ�ǣ�2���ǣ�3���ǣ�4���ǣ�5����
	star_memo		varchar2(2000),		-- �������˵��
	star_time		DATE,			-- ����ʱ��
	
	RECEIPT			CLOB,                   --��ִ��Ϣ����
	MARK			VARCHAR2(200),          --�����Ϣ
	MEMO			VARCHAR2(2000),         --��ע˵��
	CREATION_USER		INTEGER,                --������
	CREATION_TIME		VARCHAR2(100),          --����ʱ��
	UPDATE_USER		INTEGER,                --���������
	UPDATE_TIME		DATE                    --�������ʱ��
);
-- ��Ʒ�ͺ���Ϣ������
create sequence s_tb_commodity_order
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

-- ��Ʒ��ͼ
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