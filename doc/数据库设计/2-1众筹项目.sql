-- 众筹项目分类表
create table TB_ZCPROJ_TYPE (
  TYPE_ID       INTEGER PRIMARY KEY,
  TYPE_CODE     VARCHAR2(200),
  TYPE_NAME     VARCHAR2(200),
  PARENT_ID     INTEGER,
  NUM_ORDER     INTEGER,
  MEMO          VARCHAR2(2000),
  CREATION_USER INTEGER,
  CREATION_TIME VARCHAR2(100),
  UPDATE_USER   INTEGER,
  UPDATE_TIME   DATE
);
insert into tb_zcproj_type(type_id,type_name,type_code,num_order) values(1,'全部分类','1000',1);
create sequence S_TB_ZCPROJ_TYPE
minvalue 1
maxvalue 999999999999999999999999999
start with 2
increment by 1
cache 20;
-- 众筹项目表
create table TB_ZCPROJ (
  PROJ_ID       INTEGER PRIMARY KEY,    --序号
  PROJ_TYPE	INTEGER,		--项目分类（外键）
  PROJ_CODE     VARCHAR2(200),          --项目编码
  PROJ_NAME     VARCHAR2(200),          --项目名称
  PROJ_INTRO    VARCHAR2(2000),         --项目简介
  PROJ_CONTENT  CLOB,                   --项目详情
  PROJ_GIFTMEMO CLOB,                   --项目礼品与票据等说明
  SHELVES_FLAG  CHAR(1),                --众筹上架标识（1、上架，0+其他、下架）
  SHELVES_TIME  DATE,                   --众筹上架时间
  TARGET_AMOUT  NUMBER,                 --众筹目标金额
  BEG_TIME      DATE,                   --众筹开始时间
  END_TIME      DATE,                   --众筹结束时间
  HOT_FLAG      CHAR(1),                --众筹热门标识（1、热门，0+其他、非热门）
  HOT_ORDER     VARCHAR2(100),          --众筹热门排序
  MIN_AMOUNT    NUMBER,                 --众筹任意捐最低捐赠金额
  MEMO          VARCHAR2(2000),         --备注说明
  CREATION_USER INTEGER,                --创建人
  CREATION_TIME VARCHAR2(100),          --创建时间
  UPDATE_USER   INTEGER,                --最近更新人
  UPDATE_TIME   DATE                    --最近更新时间
);
create sequence S_TB_ZCPROJ
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;
-- 众筹项目捐赠选项表
create table TB_ZCPROJ_OPTION (
  OPTION_ID     INTEGER PRIMARY KEY,    --序号
  PROJ_ID       INTEGER,                --项目序号（外键）
  OPTION_CODE   VARCHAR2(200),          --选项编码
  OPTION_NAME   VARCHAR2(200),          --选项名称
  OPTION_AMOUNT NUMBER,                 --选项金额
  OPTION_MEMO   CLOB,                   --选项说明
  MEMO          VARCHAR2(2000),         --备注说明
  CREATION_USER INTEGER,                --创建人
  CREATION_TIME VARCHAR2(100),          --创建时间
  UPDATE_USER   INTEGER,                --最近更新人
  UPDATE_TIME   DATE                    --最近更新时间
);
create sequence S_TB_ZCPROJ_OPTION
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;
-- 众筹项目图片表
create table TB_ZCPROJ_PIC (
  PIC_ID        INTEGER PRIMARY KEY,    --序号
  PROJ_ID       INTEGER,                --项目序号（外键）
  PIC_PATH      VARCHAR2(200),          --图片路径
  PIC_ALT       VARCHAR2(200),          --图片说明
  NUM_ORDER     VARCHAR2(100),                --排序
  MEMO          VARCHAR2(2000),         --备注说明
  CREATION_USER INTEGER,                --创建人
  CREATION_TIME VARCHAR2(100),          --创建时间
  UPDATE_USER   INTEGER,                --最近更新人
  UPDATE_TIME   DATE                    --最近更新时间
);
create sequence S_TB_ZCPROJ_PIC
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;
-- 众筹项目进度表
create table TB_ZCPROJ_PROGRESS (
  GRESS_ID      INTEGER PRIMARY KEY,    --序号
  PROJ_ID       INTEGER,                --项目序号（外键）
  GRESS_TIME    DATE,                   --进度时间
  GRESS_CONTENT CLOB,                   --进度详情
  MEMO          VARCHAR2(2000),         --备注说明
  CREATION_USER INTEGER,                --创建人
  CREATION_TIME VARCHAR2(100),          --创建时间
  UPDATE_USER   INTEGER,                --最近更新人
  UPDATE_TIME   DATE                    --最近更新时间
);
create sequence S_TB_ZCPROJ_PROGRESS
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;
-- 众筹项目捐赠明细表
create table TB_ZCPROJ_ORDER (
  ORDER_ID      INTEGER PRIMARY KEY,    --序号
  ORDER_NUM     VARCHAR2(100),          --捐赠单号
  PROJ_ID       INTEGER,                --项目序号（外键）
  PROJ_NAME     VARCHAR2(200),		--项目名称
  OPTION_ID     INTEGER,                --项目选项序号（外键）
  OPTION_NAME   VARCHAR2(200),          --项目选项名称
  OPTION_COUNT  INTEGER,                --项目选项份数
  ORDER_AMOUNT  NUMBER,                 --捐赠总金额
  ORDER_TYPE    VARCHAR2(100),          --捐赠方式（支付宝在线支付，银联在线支付，线下支付等）
  ORDER_STATUS  CHAR(1),                --捐赠状态（1、捐赠成功，0+其他、待付款）
  ORDER_TIME    DATE,                   --捐赠下单时间
  ORDER_OK_TIME DATE,                   --捐赠完成时间
  
  NEED_ZHENGSHU CHAR(1),                --捐赠证书（1、实物，0+其他、电子）
  NEED_FAPIAO   CHAR(1),                --捐赠发票（1、实物，0+其他、电子）
  
  PERSON_NAME   VARCHAR2(100),          --捐赠人姓名
  PERSON_IP     VARCHAR2(100),          --捐赠人IP
  PERSON_SEX    CHAR(1),                --捐赠人性别（1、男，2、女，其他、不详）
  PERSON_EMAIL  VARCHAR2(100),          --捐赠人邮箱
  PERSON_PHONE  VARCHAR2(100),          --捐赠人手机
  PERSON_TEL    VARCHAR2(100),          --捐赠人电话
  
  ADDRESS_ZIP   VARCHAR2(100),          --地址-邮编
  ADDRESS_SHENG VARCHAR2(100),          --地址-省
  ADDRESS_SHI   VARCHAR2(100),          --地址-市
  ADDRESS_QU    VARCHAR2(100),          --地址-区
  ADDRESS_DETAIL VARCHAR2(200),         --地址-详细
  
  ALUMNI_FLAG   CHAR(1),                --校友标识（1、校友，0+其他、非校友）
  STUDY_YEARIN  VARCHAR2(100),          --校友入学年
  STUDY_YEAROVER VARCHAR2(100),         --校友离校年
  STUDY_ACADEMY VARCHAR2(100),          --校友院系
  STUDY_MAJOR   VARCHAR2(100),          --校友专业
  STUDY_CLASS   VARCHAR2(100),          --校友班级
  STUDY_NUM     VARCHAR2(100),          --校友学号
  STUDY_DEGREE  VARCHAR2(100),          --校友学位
  
  WORK_COMPANY  VARCHAR2(100),          --工作单位
  WORK_DEPART   VARCHAR2(100),          --工作部门
  WORK_DUTY     VARCHAR2(100),          --工作职务
  
  ORDER_MEMO    VARCHAR2(2000),         --捐赠说明
  
  RECEIPT       CLOB,                   --回执信息详情
  
  MARK          VARCHAR2(200),          --标记信息
  MEMO          VARCHAR2(2000),         --备注说明
  CREATION_USER INTEGER,                --创建人
  CREATION_TIME VARCHAR2(100),          --创建时间
  UPDATE_USER   INTEGER,                --最近更新人
  UPDATE_TIME   DATE                    --最近更新时间
);
create sequence S_TB_ZCPROJ_ORDER
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

-- 众筹项目视图
create or replace view vw_zcproj as
select a.*,case when a.beg_time is not null and a.beg_time>sysdate then '1' when a.end_time is not null and a.end_time<sysdate then '3'else '2' end as proj_status,
       NVL(b.zced_count,0) as zced_count,NVL(b.zced_amount,0) as zced_amount,NVL(b.zced_amount/a.target_amout*100,0) as zced_percent
from tb_zcproj a
left join (
     select x1.proj_id,count(x2.order_id) as zced_count,sum(x2.order_amount) as zced_amount
     from tb_zcproj x1,tb_zcproj_order x2
     where x1.proj_id=x2.proj_id and x2.order_status='1'
     group by x1.proj_id
) b on a.proj_id=b.proj_id;