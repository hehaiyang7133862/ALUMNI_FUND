-- 年度捐赠明细表
create table TB_ZCOTHER_ORDER (
  ORDER_ID      INTEGER PRIMARY KEY,    --序号
  ORDER_NUM     VARCHAR2(100),          --捐赠单号
  PROJ_NAME     VARCHAR2(200),		--项目名称
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
create sequence S_TB_ZCOTHER_ORDER
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;