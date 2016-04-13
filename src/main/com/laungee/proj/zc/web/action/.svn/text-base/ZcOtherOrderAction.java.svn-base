package com.laungee.proj.zc.web.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts2.ServletActionContext;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbZcotherOrder;
import com.laungee.proj.common.util.DateUtil;
import com.laungee.proj.common.util.MultiRequestUtil;

public class ZcOtherOrderAction extends BaseAction {
	// 其他众筹项目捐赠列表
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 构造查询
		String hql = "from TbZcotherOrder a where 1=1";
		List pars = this.getList();
		// 捐赠项目
		String projName = request.getParameter("projName");
		if(projName!=null && !"".equals(projName)){
			hql += " and a.projName like ?";
			pars.add("%"+projName+"%");
		}
		// 捐赠单号
		String orderNum = request.getParameter("orderNum");
		if(orderNum!=null && !"".equals(orderNum)){
			hql += " and a.orderNum like ?";
			pars.add("%"+orderNum+"%");
		}
		// 捐赠方式
		String orderType = request.getParameter("orderType");
		if(orderType!=null && !"".equals(orderType)){
			hql += " and a.orderType like ?";
			pars.add("%"+orderType+"%");
		}
		// 证书
		String needZhengshu = request.getParameter("needZhengshu");
		if(needZhengshu!=null && "1".equals(needZhengshu)){
			hql += " and a.needZhengshu = ?";
			pars.add("1");
		}else if(needZhengshu!=null && !"".equals(needZhengshu)){
			hql += " and a.needZhengshu <> ?";
			pars.add("1");
		}
		// 发票
		String needFapiao = request.getParameter("needFapiao");
		if(needFapiao!=null && "1".equals(needFapiao)){
			hql += " and a.needFapiao = ?";
			pars.add("1");
		}else if(needFapiao!=null && !"".equals(needFapiao)){
			hql += " and (a.needFapiao is null or a.needFapiao <> ?)";
			pars.add("1");
		}
		// 匿名捐赠
		String niMing = request.getParameter("niMing");
		if(niMing!=null && "1".equals(niMing)){
			hql += " and a.niMing = ?";
			pars.add("1");
		}else if(niMing!=null && !"".equals(niMing)){
			hql += " and (a.niMing is null or a.niMing <> ?)";
			pars.add("1");
		}
		// 捐赠人
		String personName = request.getParameter("personName");
		if(personName!=null && !"".equals(personName)){
			hql += " and a.personName like ?";
			pars.add("%"+personName+"%");
		}
		// 捐赠人信息
		String personInfo = request.getParameter("personInfo");
		if(personInfo!=null && !"".equals(personInfo)){
			hql += " and (a.personPhone like ? or a.personTel like ? or a.personEmail like ? or a.addressSheng like ? or a.addressShi like ? or a.addressQu like ? or a.addressDetail like ? or a.addressZip like ?)";
			pars.add("%"+personInfo+"%");
			pars.add("%"+personInfo+"%");
			pars.add("%"+personInfo+"%");
			pars.add("%"+personInfo+"%");
			pars.add("%"+personInfo+"%");
			pars.add("%"+personInfo+"%");
			pars.add("%"+personInfo+"%");
			pars.add("%"+personInfo+"%");
		}
		// 校友
		String alumni = request.getParameter("alumni");
		if(alumni!=null && "1".equals(alumni)){
			hql += " and a.alumniFlag = ?";
			pars.add("1");
		}else if(alumni!=null && !"".equals(alumni)){
			hql += " and (a.alumniFlag is null or a.alumniFlag <> ?)";
			pars.add("1");
		}
		// 校友信息
		String alumniInfo = request.getParameter("alumniInfo");
		if(alumniInfo!=null && !"".equals(alumniInfo)){
			hql += " and (a.studyYearin like ? or a.studyYearover like ? or a.studyAcademy like ? or a.studyMajor like ? or a.studyClass like ? or a.studyNum like ? or a.studyDegree like ?)";
			pars.add("%"+alumniInfo+"%");
			pars.add("%"+alumniInfo+"%");
			pars.add("%"+alumniInfo+"%");
			pars.add("%"+alumniInfo+"%");
			pars.add("%"+alumniInfo+"%");
			pars.add("%"+alumniInfo+"%");
			pars.add("%"+alumniInfo+"%");
		}
		// 标记
		String markFlag = request.getParameter("markFlag");
		if(markFlag!=null && "1".equals(markFlag)){
			hql += " and a.mark is not null";
			String mark = request.getParameter("mark");
			if(mark!=null && !"".equals(mark)){
				hql += " and a.mark like ?";
				pars.add("%"+mark+"%");
			}
		}else if(markFlag!=null && !"".equals(markFlag)){
			hql += " and a.mark is null";
		}
		// 捐赠币种
		String amountType = request.getParameter("amountType");
		if(amountType!=null && !"".equals(amountType)){
			hql += " and a.orderAmountType like ?";
			pars.add("%"+amountType+"%");
		}
		// 提交起始时间
		Date orderTimeBegD = null;
		String orderTimeBeg = request.getParameter("orderTimeBeg");
		if(orderTimeBeg!=null && !"".equals(orderTimeBeg)){
			orderTimeBegD = DateUtil.toDate(orderTimeBeg, "yyyy-MM-dd HH:mm");
		}
		if(orderTimeBegD!=null){
			hql += " and a.orderTime >= ?";
			pars.add(orderTimeBegD);
		}
		// 提交截止时间
		Date orderTimeEndD = null;
		String orderTimeEnd = request.getParameter("orderTimeEnd");
		if(orderTimeEnd!=null && !"".equals(orderTimeEnd)){
			orderTimeEndD = DateUtil.toDate(orderTimeEnd, "yyyy-MM-dd HH:mm");
		}
		if(orderTimeEndD!=null){
			hql += " and a.orderTime <= ?";
			pars.add(orderTimeEndD);
		}
		// 付款起始时间
		Date orderOkTimeBegD = null;
		String orderOkTimeBeg = request.getParameter("orderOkTimeBeg");
		if(orderOkTimeBeg!=null && !"".equals(orderOkTimeBeg)){
			orderOkTimeBegD = DateUtil.toDate(orderOkTimeBeg, "yyyy-MM-dd HH:mm");
		}
		if(orderOkTimeBegD!=null){
			hql += " and a.orderOkTime >= ?";
			pars.add(orderOkTimeBegD);
		}
		// 付款截止时间
		Date orderOkTimeEndD = null;
		String orderOkTimeEnd = request.getParameter("orderOkTimeEnd");
		if(orderOkTimeEnd!=null && !"".equals(orderOkTimeEnd)){
			orderOkTimeEndD = DateUtil.toDate(orderOkTimeEnd, "yyyy-MM-dd HH:mm");
		}
		if(orderOkTimeEndD!=null){
			hql += " and a.orderOkTime <= ?";
			pars.add(orderOkTimeEndD);
		}
		// 捐赠状态
		String orderStatus = request.getParameter("orderStatus");
		if(orderStatus!=null && "1".equals(orderStatus)){
			hql += " and a.orderStatus = ?";
			pars.add("1");
		}else if(orderStatus!=null && !"".equals(orderStatus)){
			hql += " and (a.orderStatus is null or a.orderStatus <> ?)";
			pars.add("1");
		}
		// 捐赠人类型
		String personType = request.getParameter("personType");
		if(personType!=null && "1".equals(personType)){
			hql += " and a.personType = ?";
			pars.add("1");
		}else if(personType!=null && !"".equals(personType)){
			hql += " and (a.personType is null or a.personType <> ?)";
			pars.add("1");
		}
		// 查询合计条数
		Long orderCount = (Long)this.getCommonBo().getHQLUnique("select count(*) "+hql, pars);
		request.setAttribute("orderCount", orderCount);
		// 查询合计已付款条数
		Long orderOkCount = (Long)this.getCommonBo().getHQLUnique("select count(*) "+hql+" and a.orderStatus = '1'", pars);
		request.setAttribute("orderOkCount", orderOkCount);
		// 查询合计已付款 在线捐赠条数
		Long orderOkType0Count = (Long)this.getCommonBo().getHQLUnique("select count(*) "+hql+" and a.orderStatus = '1' and (a.orderType like '%在线%' or a.orderType like '%交易号%')", pars);
		request.setAttribute("orderOkType0Count", orderOkType0Count);
		// 查询合计已付款 个人捐赠条数
		Long orderOkType1Count = (Long)this.getCommonBo().getHQLUnique("select count(*) "+hql+" and a.orderStatus = '1' and a.personType = '1'", pars);
		request.setAttribute("orderOkType1Count", orderOkType1Count);
		// 查询合计已付款 集体捐赠条数
		Long orderOkType2Count = (Long)this.getCommonBo().getHQLUnique("select count(*) "+hql+" and a.orderStatus = '1' and (a.personType is null or a.personType <> '1')", pars);
		request.setAttribute("orderOkType2Count", orderOkType2Count);
		// 查询合计已付款总金额
		BigDecimal orderOkAmount = (BigDecimal)this.getCommonBo().getHQLUnique("select sum(a.orderAmount) "+hql+" and a.orderStatus = '1'", pars);
		request.setAttribute("orderOkAmount", orderOkAmount);
		// 默认查询条件
		hql += " order by a.orderTime desc";
		// 执行查询
		List orderList = this.getCommonBo().findHQLPage(hql, pars);
		request.setAttribute("orderList", orderList);
		// 返回
		return SUCCESS;
	}
	
	// 其他众筹项目捐赠详情页面
	public String view() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 项目捐赠
		TbZcotherOrder bean = null;
		// 项目捐赠ID
		String orderId = request.getParameter("orderId");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbZcotherOrder)this.getCommonBo().get(TbZcotherOrder.class, new Long(orderId));
			}catch(Exception e){}
		}
		request.setAttribute("bean", bean);
		// 返回
		return "view";
	}
	
	// 其他众筹项目捐赠标记页面
	public String mark() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 项目捐赠
		TbZcotherOrder bean = null;
		// 项目捐赠ID
		String orderId = request.getParameter("orderId");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbZcotherOrder)this.getCommonBo().get(TbZcotherOrder.class, new Long(orderId));
			}catch(Exception e){}
		}
		request.setAttribute("bean", bean);
		// 返回
		return "mark";
	}
	
	// 其他众筹项目进展标记
	public String markUpdate() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 项目捐赠
		TbZcotherOrder bean = null;
		// 项目捐赠ID
		String orderId = request.getParameter("orderId");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbZcotherOrder)this.getCommonBo().get(TbZcotherOrder.class, new Long(orderId));
			}catch(Exception e){}
		}
		if(bean!=null){
			// 标记信息
			String mark = request.getParameter("mark");
			bean.setMark(mark);
			// 回执信息
			String receipt = request.getParameter("receipt");
			bean.setReceipt(receipt);
			// 备注
			String memo = request.getParameter("memo");
			bean.setMemo(memo);
			// 保存选项
			try{
				this.getCommonBo().saveOrUpdate(bean);
			} catch (Exception e) {
				// 返回
				request.setAttribute("alert", "保存时发生异常！");
				return "mark";
			}
		}
		// 返回
		return "winSuccess";
	}

	// 其他众筹项目捐赠删除
	public String del() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 项目捐赠
		TbZcotherOrder bean = null;
		// 项目捐赠ID
		String orderId = request.getParameter("orderId");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbZcotherOrder)this.getCommonBo().get(TbZcotherOrder.class, new Long(orderId));
			}catch(Exception e){}
		}
		if(bean!=null){
			this.getCommonBo().delete(bean);
		}
		// 返回
		return execute();
	}
	
	// 新增&编辑
	public String toAddOrEdit()  throws Exception {
		HttpServletRequest request = this.getRequest();
		// 系统当前时间
		Date dateNow = this.getCommonBo().getSysDate();
		// 获取当前时间相关参数
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(dateNow);
		// 当前年份
		int nowYear = c.get(Calendar.YEAR);
		request.setAttribute("nowYear", nowYear);
		// 项目捐赠
		TbZcotherOrder bean = null;
		// 项目捐赠ID
		String orderId = request.getParameter("orderId");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbZcotherOrder)this.getCommonBo().get(TbZcotherOrder.class, new Long(orderId));
			}catch(Exception e){}
		}
		if(bean!=null){
			request.setAttribute("bean", bean);
		}
		// 返回
		return "edit";
	}
	public String doAddOrEdit()  throws Exception {
		HttpServletRequest request = this.getRequest();
		// 系统当前时间
		Date dateNow = this.getCommonBo().getSysDate();
		// 获取当前时间相关参数
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(dateNow);
		// 当前年份
		int nowYear = c.get(Calendar.YEAR);
		request.setAttribute("nowYear", nowYear);
		// 项目捐赠
		TbZcotherOrder bean = null;
		// 项目捐赠ID
		String orderId = request.getParameter("orderId");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbZcotherOrder)this.getCommonBo().get(TbZcotherOrder.class, new Long(orderId));
				request.setAttribute("bean", bean);
			}catch(Exception e){}
		}
		if(bean==null){
			bean = new TbZcotherOrder();
		}
		// 捐赠方式
		String orderType = request.getParameter("orderType");
		bean.setOrderType(orderType);
		// 捐赠状态
		String orderStatus = request.getParameter("orderStatus");
		bean.setOrderStatus(orderStatus);
		// 提交时间
		String orderTime = request.getParameter("orderTime");
		if(orderTime!=null&&!"".equals(orderTime)){
			bean.setOrderTime(DateUtil.toDate(orderTime, "yyyy-MM-dd HH:mm"));
		}else{
			bean.setOrderTime(null);
		}
		// 付款时间
		String orderOkTime = request.getParameter("orderOkTime");
		if(orderOkTime!=null&&!"".equals(orderOkTime)){
			bean.setOrderOkTime(DateUtil.toDate(orderOkTime, "yyyy-MM-dd HH:mm"));
		}else{
			bean.setOrderOkTime(null);
		}
		// 证书
		String needZhengshu = request.getParameter("needZhengshu");
		bean.setNeedZhengshu(needZhengshu);
		// 发票
		String needFapiao = request.getParameter("needFapiao");
		bean.setNeedFapiao(needFapiao);
		// 捐赠人
		// 捐赠人类型
		String personType = request.getParameter("personType");
		if(personType==null || !"2".equals(personType)){
			personType = "1";
		}
		bean.setPersonType(personType);
		// 捐赠人数
    	Long personCountL = null;
		String personCount = request.getParameter("personCount");
        if("2".equals(personType) && personCount!=null && !"".equals(personCount)){
        	try{
        		personCountL = new Long(personCount);
        	}catch(Exception e){}
        }
        if(personCountL==null || personCountL<1){
        	personCountL = new Long(1);
        }
		bean.setPersonCount(personCountL);
		// 姓名
		String person = request.getParameter("person");
		if(person==null || "".equals(person)){
			person = "匿名";
		}
		bean.setPersonName(person);
		// 性别
		String personSex = request.getParameter("personSex");
		bean.setPersonSex(personSex);
		// 匿名
		String niMing = request.getParameter("niMing");
		bean.setNiMing(niMing);
		// 手机
		String personPhone = request.getParameter("personPhone");
		bean.setPersonPhone(personPhone);
		// 电话
		String personTel = request.getParameter("personTel");
		bean.setPersonTel(personTel);
		// 邮箱
		String personEmail = request.getParameter("personEmail");
		bean.setPersonEmail(personEmail);
		// 邮编
		String zip = request.getParameter("zip");
		bean.setAddressZip(zip);
		// 省
		String sheng = request.getParameter("sheng");
		bean.setAddressSheng(sheng);
		// 市
		String shi = request.getParameter("shi");
		bean.setAddressShi(shi);
		// 区/县
		String qu = request.getParameter("qu");
		bean.setAddressQu(qu);
		// 详细地址
		String address = request.getParameter("address");
		bean.setAddressDetail(address);
		// 校友资料
		// 是否校友		
		String alumniFlag = request.getParameter("alumniFlag");
		bean.setAlumniFlag(alumniFlag);
		// 入学年
		String academyBeg = request.getParameter("academyBeg");
		bean.setStudyYearin(academyBeg);
		// 离校年
		String academyEnd = request.getParameter("academyEnd");
		bean.setStudyYearover(academyEnd);
		// 院系
		String academy = request.getParameter("academy");
		bean.setStudyAcademy(academy);
		// 专业
		String major = request.getParameter("major");
		bean.setStudyMajor(major);
		// 班级
		String clazz = request.getParameter("clazz");
		bean.setStudyClass(clazz);
		// 学号
		String studyno = request.getParameter("studyno");
		bean.setStudyNum(studyno);
		// 学历
		String academyDegree = request.getParameter("academyDegree");
		bean.setStudyDegree(academyDegree);
		// 工作资料
		// 公司
		String company = request.getParameter("company");
		bean.setWorkCompany(company);
		// 部门
		String department = request.getParameter("department");
		bean.setWorkDepart(department);
		// 职位
		String duty = request.getParameter("duty");
		bean.setWorkDuty(duty);
		// 其他信息
		String orderMemo = request.getParameter("orderMemo");
		bean.setOrderMemo(orderMemo);
		// 标记信息
		String mark = request.getParameter("mark");
		bean.setMark(mark);
		// 回执信息
		String receipt = request.getParameter("receipt");
		bean.setReceipt(receipt);
		// 备注
		String memo = request.getParameter("memo");
		bean.setMemo(memo);
		// 捐赠项目
		String projName = request.getParameter("projName");
		if(projName!=null && !"".equals(projName)){
			bean.setProjName(projName);
		}else{
			request.setAttribute("alert", "捐赠项目不能为空！");
			request.setAttribute("bean", bean);
			return "edit";
		}
		// 捐赠项目链接
		String projUrl = request.getParameter("projUrl");
		bean.setProjUrl(projUrl);
		// 捐赠币种
    	String orderAmoutType = request.getParameter("amountType");
        if(orderAmoutType==null || "".equals(orderAmoutType)){
        	orderAmoutType = "人民币";
        }
        bean.setOrderAmountType(orderAmoutType);
        // 捐赠币种总金额
    	BigDecimal orderAmoutViewD = null;
    	String orderAmoutView = request.getParameter("amountView");
    	if(orderAmoutView!=null && !"".equals(orderAmoutView)){
        	try{
        		orderAmoutViewD = new BigDecimal(orderAmoutView).setScale(2, 4);
        	}catch(Exception e){}
        }
    	if(orderAmoutViewD==null || orderAmoutViewD.doubleValue()<0){
			request.setAttribute("alert", "捐赠币种总金额不能少于0！");
			request.setAttribute("bean", bean);
			return "orderEdit";
    	}
		bean.setOrderAmountView(orderAmoutViewD);
        // 捐赠币种计量单位
    	String orderAmoutUnit = request.getParameter("amountUnit");
        if(orderAmoutUnit==null || "".equals(orderAmoutUnit)){
        	orderAmoutUnit = "元";
        }
        bean.setOrderAmountUnit(orderAmoutUnit);
        // 捐赠单实算总金额
		String amount = request.getParameter("amount");
		BigDecimal orderAmoutD = null;
		if(amount!=null && !"".equals(amount)){
			try{
				orderAmoutD = new BigDecimal(amount).setScale(2, 4);
			}catch(Exception e){}
		}
		if(orderAmoutD==null || orderAmoutD.doubleValue()<=0){
			// 返回状态：参数错误
			request.setAttribute("alert", "捐赠实算总金额不能为空！");
			request.setAttribute("bean", bean);
			// 返回
			return "edit";
		}
    	bean.setOrderAmount(orderAmoutD);
		// 保存
    	try{
    		bean = (TbZcotherOrder)this.getCommonBo().saveOrUpdate(bean);
    		if(bean.getOrderNum()==null || "".equals(bean.getOrderNum())){
                // 单号
        		String orderNum = bean.getOrderId().toString();
        		int orderLen = orderNum.length();
                for(int i=0; i<17-orderLen; i++){  
                	orderNum = "0"+orderNum;  
                }
                orderNum = DateUtil.format(dateNow,"yyyyMMdd")+"002"+orderNum;
                bean.setOrderNum(orderNum);
                bean = (TbZcotherOrder)this.getCommonBo().update(bean);
        		request.setAttribute("bean", bean);
    		}
    		// 返回
        	return "winSuccess";
    	}catch(Exception e){}
		// 返回状态：保存失败
		request.setAttribute("alert", "保存失败！");
		// 返回
    	return "edit";
	}
	/*导入相关*/
	//格式化文本
	private String formatStr(String str){
		if(str!=null && !"".equals(str)){
			try {
				//科学计数法
				if(str.matches("[\\+\\-]?[\\d]+([\\.][\\d]*)?[Ee][+-]?[\\d]+")){
			    	NumberFormat format = NumberFormat.getInstance();
			    	format.setGroupingUsed(false);
					str = format.format(Double.parseDouble(str));
				}
			}catch(Exception e) { }
		}
		return str;
	}
	// 是或否
	private String isOrNot(String str){
		return "是".equals(str)?"1":"0";
	}
	// 日期处理
	public Date getExcelDate(HSSFCell cell) throws Exception {
		String ret = "";
		try{
			if(cell!=null){
				if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {  
				    if(HSSFDateUtil.isCellDateFormatted(cell)){// 判断单元格是否属于日期格式  
				    	Date theDate = cell.getDateCellValue();
		            	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		            	ret = format.format(theDate);
		            	return DateUtil.toDate(ret, "yyyy-MM-dd HH:mm");
				    }
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		ret = formatStr(cell.toString().trim());
		if(isValidDate(ret)){
			return DateUtil.toDate(ret, "yyyy-MM-dd HH:mm");
		}else
			return toDate(ret);
	}
	
	private Date toDate(String s) throws Exception {
		Calendar ca = Calendar.getInstance();
		int maxYear = ca.get(Calendar.YEAR)+10;//年份最大不能超过距今10年
		try{
			int i = Integer.parseInt(s);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date day = format.parse("1900-01-01");//Excel数字格式日期起始日期
			ca.setTime(day);
			ca.add(Calendar.DATE, i);// i为距离起始日期增加的天数
			day = ca.getTime();
			return day;
		}catch(Exception e){
			
		}
		return DateUtil.toDate(s, "yyyy-MM-dd");
	}
	
	public boolean isValidDate(String str) {
		boolean convertSuccess=true;
		// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
			format.setLenient(false);
			format.parse(str);
		} catch (ParseException e) {
			// e.printStackTrace();
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			convertSuccess=false;
		} 
		return convertSuccess;
	}
	//上传Excel导入进度参数名称
	private String IMPORTCOUNT = "ZCOTHERORDEREXCELCOUNT";
	private String IMPORTINDEX = "ZCOTHERORDEREXCELINDEX";
	//上传Excel导入进度
	public String process() throws Exception{
		HttpServletRequest request = this.getRequest();
		HttpServletResponse response = this.getResponse();
		HttpSession session = request.getSession();
		// 导入进度时间戳
		String random = request.getParameter("random");
		if(random==null){
			random = "";
		}
		// 导入进度参数
		Object countObj = session.getAttribute(IMPORTCOUNT+random);
		String count = "";
		if(countObj!=null){
			count = countObj.toString();
		}
		Object indexObj = session.getAttribute(IMPORTINDEX+random);
		String index = "";
		if(indexObj!=null){
			index = indexObj.toString();
		}
		// 传值
		this.sendResponse(response, "{\"count\":"+count+",\"index\":"+index+"}");
		// 返回
		return null;
	}
	// 导入页面
	public String toImport() throws Exception {
		return "import";
	}
	// 批量导入
	public String doImport()  throws Exception {
		HttpServletRequest request = this.getRequest();
		MultiRequestUtil multiRequest = new MultiRequestUtil(request);
		HttpSession session = request.getSession();
		// 系统当前时间
		Date dateNow = this.getCommonBo().getSysDate();
		// 导入进度时间戳
		String random = multiRequest.getParameter("random");
		if(random==null){
			random = "";
		}
		// 获取导入文件
		FileItem item = multiRequest.getFile("file");
		if(item==null || item.getSize()==0){
			request.setAttribute("alert", "请选择需要导入的Excel文件！");
			return "import";
		}
		// 读取导入文件
		InputStream fis = null;
		try{
			fis = item.getInputStream();
			POIFSFileSystem fs = new POIFSFileSystem(fis);
			HSSFWorkbook workbook = new HSSFWorkbook(fs);
			HSSFSheet sheet = workbook.getSheetAt(0);
			// 判断是否有数据
			int rowCount = sheet.getLastRowNum();
			if(rowCount<1){
				request.setAttribute("alert", "导入模板错误，或导入文件中没有相关导入数据！");
				return "import";
			}
			// 头
			HSSFRow headRow = sheet.getRow(0);
			int colCount = headRow.getLastCellNum();
			List headList = this.getList();
			for(int i=0; i<=colCount; i++){
				HSSFCell headCell = headRow.getCell((short) i);
				String headCellValue = "";
				if(headCell!=null){
					headCellValue = headCell.toString().trim();
				}
				if(headCellValue.contains("单号")){
					headList.add("捐赠单号");
				}else if(headCellValue.contains("名称")){
					headList.add("项目名称");
				}else if(headCellValue.contains("链接")){
					headList.add("项目链接");
				}else if(headCellValue.endsWith("币种")){
					headList.add("捐赠币种");
				}else if(headCellValue.endsWith("币种金额")){
					headList.add("捐赠币种金额");
				}else if(headCellValue.endsWith("计量单位")){
					headList.add("捐赠币种金额计量单位");
				}else if(headCellValue.contains("实算金额")){
					headList.add("捐赠实算金额");
				}else if(headCellValue.contains("方式")){
					headList.add("捐赠方式");
				}else if(headCellValue.contains("状态")){
					headList.add("捐赠状态");
				}else if(headCellValue.contains("提交时间")){
					headList.add("提交时间");
				}else if(headCellValue.contains("付款时间")){
					headList.add("付款时间");
				}else if(headCellValue.contains("证书")){
					headList.add("需要证书");
				}else if(headCellValue.contains("发票")){
					headList.add("需要发票");
				}else if(headCellValue.contains("匿名")){
					headList.add("匿名捐赠");
				}else if(headCellValue.endsWith("人类型")){
					headList.add("捐赠人类型");
				}else if(headCellValue.endsWith("人数")){
					headList.add("捐赠人数");
				}else if(headCellValue.endsWith("捐赠人")){
					headList.add("捐赠人");
				}else if(headCellValue.contains("性别")){
					headList.add("性别");
				}else if(headCellValue.contains("手机")){
					headList.add("手机");
				}else if(headCellValue.contains("电话")){
					headList.add("电话");
				}else if(headCellValue.contains("邮箱")){
					headList.add("邮箱");
				}else if(headCellValue.contains("邮编")){
					headList.add("邮编");
				}else if(headCellValue.contains("省")){
					headList.add("省");
				}else if(headCellValue.contains("市")){
					headList.add("市");
				}else if(headCellValue.contains("区/县")){
					headList.add("区/县");
				}else if(headCellValue.contains("详细地址")){
					headList.add("详细地址");
				}else if(headCellValue.contains("是否校友")){
					headList.add("是否校友");
				}else if(headCellValue.contains("入学年")){
					headList.add("入学年");
				}else if(headCellValue.contains("离校年")){
					headList.add("离校年");
				}else if(headCellValue.contains("院系")){
					headList.add("院系");
				}else if(headCellValue.contains("专业")){
					headList.add("专业");
				}else if(headCellValue.contains("班级")){
					headList.add("班级");
				}else if(headCellValue.contains("学号")){
					headList.add("学号");
				}else if(headCellValue.contains("学历")){
					headList.add("学历");
				}else if(headCellValue.contains("公司")){
					headList.add("公司");
				}else if(headCellValue.contains("部门")){
					headList.add("部门");
				}else if(headCellValue.contains("职位")){
					headList.add("职位");
				}else if(headCellValue.contains("其他")){
					headList.add("其他信息");
				}else if(headCellValue.contains("标记")){
					headList.add("标记信息");
				}else if(headCellValue.contains("回执")){
					headList.add("回执信息");
				}else if(headCellValue.contains("备注")){
					headList.add("备注");
				}else{
					headList.add("");
				}
			}
			if(!headList.contains("项目名称") || !headList.contains("捐赠实算金额") || !headList.contains("捐赠人")){
				request.setAttribute("alert", "导入模板格式错误，请重新下载导入模板！");
				return "import";
			}
			// 设置导入进度参数
			session.setAttribute(IMPORTCOUNT + random, rowCount);
			// 遍历Excel导入数据
			int orderNumIndex = headList.indexOf("捐赠单号");
			int projNameIndex = headList.indexOf("项目名称");
			int projUrlIndex = headList.indexOf("项目链接");
			int amountTypeIndex = headList.indexOf("捐赠币种");
			int amountViewIndex = headList.indexOf("捐赠币种金额");
			int amountUnitIndex = headList.indexOf("捐赠币种金额计量单位");
			int amountIndex = headList.indexOf("捐赠实算金额");
			int orderTypeIndex = headList.indexOf("捐赠方式");
			int orderStatusIndex = headList.indexOf("捐赠状态");
			int orderTimeIndex = headList.indexOf("提交时间");
			int orderOkTimeIndex = headList.indexOf("付款时间");
			int needZhengshuIndex = headList.indexOf("需要证书");
			int needFapiaoIndex = headList.indexOf("需要发票");
			int niMingIndex = headList.indexOf("匿名捐赠");
			int personTypeIndex = headList.indexOf("捐赠人类型");
			int personCountIndex = headList.indexOf("捐赠人数");
			int personIndex = headList.indexOf("捐赠人");
			int personSexIndex = headList.indexOf("性别");
			int personPhoneIndex = headList.indexOf("手机");
			int personTelIndex = headList.indexOf("电话");
			int personEmailIndex = headList.indexOf("邮箱");
			int zipIndex = headList.indexOf("邮编");
			int shengIndex = headList.indexOf("省");
			int shiIndex = headList.indexOf("市");
			int quIndex = headList.indexOf("区/县");
			int addressIndex = headList.indexOf("详细地址");
			int alumniFlagIndex = headList.indexOf("是否校友");
			int academyBegIndex = headList.indexOf("入学年");
			int academyEndIndex = headList.indexOf("离校年");
			int academyIndex = headList.indexOf("院系");
			int majorIndex = headList.indexOf("专业");
			int clazzIndex = headList.indexOf("班级");
			int studynoIndex = headList.indexOf("学号");
			int academyDegreeIndex = headList.indexOf("学历");
			int companyIndex = headList.indexOf("公司");
			int departmentIndex = headList.indexOf("部门");
			int dutyIndex = headList.indexOf("职位");
			int orderMemoIndex = headList.indexOf("其他信息");
			int markIndex = headList.indexOf("标记信息");
			int receiptIndex = headList.indexOf("回执信息");
			int memoIndex = headList.indexOf("备注");
			// 导入反馈信息
			int successcount = 0;
			int errorcount = 0;
			List errorList = this.getList();
			// 循环导入
			for(int i=1; i<=rowCount; i++){
				HSSFRow row = sheet.getRow(i);
				// 设置导入进度参数
				session.setAttribute(IMPORTINDEX + random, i);
				try{
					// 项目捐赠
					TbZcotherOrder bean = null;
					// 捐赠单号
					if(orderNumIndex!=-1){
						HSSFCell orderNumCell = row.getCell((short) orderNumIndex);
						if(orderNumCell!=null){
							String orderNum = formatStr(orderNumCell.toString().trim());
							if(orderNum!=null && !"".equals(orderNum)){
								try{
									String hql = "from TbZcotherOrder a where a.orderNum=?";
									List pars = this.getList();
									pars.add(orderNum);
									bean = (TbZcotherOrder) this.getCommonBo().getHQLUnique(hql, pars);
								}catch(Exception e){}
								if(bean==null){
									errorcount++;
									errorList.add(new String[] {String.valueOf(i), "导入失败，原因：不存在单号为【"+orderNum+"】的捐赠记录，未做新增、更新处理！"});
									continue;
								}
							}
						}
					}
					if(bean==null){
						bean = new TbZcotherOrder();
						// 捐赠币种
						bean.setOrderAmountType("人民币");
						// 捐赠币种金额计量单位
						bean.setOrderAmountUnit("元");
						// 捐赠状态
						bean.setOrderStatus("0");
						// 证书
						bean.setNeedZhengshu("0");
						// 发票
						bean.setNeedFapiao("0");
						// 捐赠人 类型
						bean.setPersonType("1");
						// 捐赠人 数量
						bean.setPersonCount(new Long(1));
						// 捐赠人 姓名
						bean.setPersonName("匿名");
						// 匿名
						bean.setNiMing("0");
						// 是否校友
						bean.setAlumniFlag("0");
					}
					// 项目名称
					String projName = null;
					HSSFCell projNameCell = row.getCell((short) projNameIndex);
					if(projNameCell!=null){
						projName = formatStr(projNameCell.toString().trim());
					}
					if(projName==null || "".equals(projName)){
						errorcount++;
						errorList.add(new String[] {String.valueOf(i), "导入失败，原因：项目名称不能为空！"});
						continue;
					}
					bean.setProjName(projName);
					// 项目链接
					if(projUrlIndex!=-1){
						HSSFCell projUrlCell = row.getCell((short) projUrlIndex);
						if(projUrlCell!=null){
							String projUrl = formatStr(projUrlCell.toString().trim());
							bean.setProjUrl(projUrl);
						}
					}
					// 捐赠币种
					if(amountTypeIndex!=-1){
						HSSFCell amountTypeCell = row.getCell((short) amountTypeIndex);
						if(amountTypeCell!=null){
							String amountType = formatStr(amountTypeCell.toString().trim());
							if(amountType==null || "".equals(amountType)){
								amountType = bean.getOrderAmountType();
							}
							bean.setOrderAmountType(amountType);
						}
					}
			        // 捐赠币种金额
					if(amountViewIndex!=-1){
						BigDecimal orderAmoutViewD = null;
						HSSFCell amountViewCell = row.getCell((short) amountViewIndex);
						if(amountViewCell!=null){
							String amountView = formatStr(amountViewCell.toString().trim());
							if(amountView!=null && !"".equals(amountView)){
								try{
									orderAmoutViewD = new BigDecimal(amountView).setScale(2, 4);
								}catch(Exception e){ }
							}
							bean.setOrderAmountView(orderAmoutViewD);
						}
					}
					// 捐赠币种计量单位
					if(amountUnitIndex!=-1){
						HSSFCell amountUnitCell = row.getCell((short) amountUnitIndex);
						if(amountUnitCell!=null){
							String amountUnit = formatStr(amountUnitCell.toString().trim());
							if(amountUnit==null || "".equals(amountUnit)){
								amountUnit = bean.getOrderAmountUnit();
							}
							bean.setOrderAmountUnit(amountUnit);
						}
					}
					// 总金额
					BigDecimal orderAmoutD = null;
					HSSFCell amountCell = row.getCell((short) amountIndex);
					if(amountCell!=null){
						String amount = formatStr(amountCell.toString().trim());
						if(amount!=null && !"".equals(amount)){
							try{
								orderAmoutD = new BigDecimal(amount).setScale(2, 4);
							}catch(Exception e){ }
						}
					}
					if(orderAmoutD==null || orderAmoutD.doubleValue()<=0){
						errorcount++;
						errorList.add(new String[] {String.valueOf(i), "导入失败，原因：捐赠金额必须大于0（最多两位小数的正数）！"});
						continue;
					}
					bean.setOrderAmount(orderAmoutD);
					// 捐赠单总金额（默认处理）
					if("人民币".equals(bean.getOrderAmountType()) && bean.getOrderAmountView()==null){
				    	bean.setOrderAmountView(bean.getOrderAmount());
					}
					// 捐赠方式
					if(orderTypeIndex!=-1){
						HSSFCell orderTypeCell = row.getCell((short) orderTypeIndex);
						if(orderTypeCell!=null){
							String orderType = formatStr(orderTypeCell.toString().trim());
							bean.setOrderType(orderType);
						}
					}
					// 捐赠状态
					if(orderStatusIndex!=-1){
						HSSFCell orderStatusCell = row.getCell((short) orderStatusIndex);
						if(orderStatusCell!=null){
							String orderStatus = formatStr(orderStatusCell.toString().trim());
							if(orderStatus!=null && ("已付款".equals(orderStatus) || "1".equals(orderStatus))){
								bean.setOrderStatus("1");
							}else{
								bean.setOrderStatus("0");
							}
						}
					}
					// 提交时间
					if(orderTimeIndex!=-1){
						HSSFCell orderTimeCell = row.getCell((short) orderTimeIndex);
						if(orderTimeCell!=null){
							Date orderTime = getExcelDate(orderTimeCell);
							bean.setOrderTime(orderTime);
						}
					}
					// 付款时间
					if(orderOkTimeIndex!=-1){
						HSSFCell orderOkTimeCell = row.getCell((short) orderOkTimeIndex);
						if(orderOkTimeCell!=null){
							Date orderOkTime = getExcelDate(orderOkTimeCell);
							bean.setOrderOkTime(orderOkTime);
						}
					}
					// 证书
					if(needZhengshuIndex!=-1){
						HSSFCell needZhengshuCell = row.getCell((short) needZhengshuIndex);
						if(needZhengshuCell!=null){
							String needZhengshu = formatStr(needZhengshuCell.toString().trim());
							if(needZhengshu!=null && ("需要".equals(needZhengshu) || "是".equals(needZhengshu) || "1".equals(needZhengshu))) {
								bean.setNeedZhengshu("1");
							}else{
								bean.setNeedZhengshu("0");
							}
						}
					}
					// 发票
					if(needFapiaoIndex!=-1){
						HSSFCell needFapiaoCell = row.getCell((short) needFapiaoIndex);
						if(needFapiaoCell!=null){
							String needFapiao = formatStr(needFapiaoCell.toString().trim());
							if(needFapiao!=null && ("需要".equals(needFapiao) || "是".equals(needFapiao) || "1".equals(needFapiao))){
								bean.setNeedFapiao("1");
							}else{
								bean.setNeedFapiao("0");
							}
						}
					}
					// 捐赠人
					// 捐赠人类型
					if(personTypeIndex!=-1){
						HSSFCell personTypeCell = row.getCell((short) personTypeIndex);
						if(personTypeCell!=null){
							String personType = formatStr(personTypeCell.toString().trim());
							if(personType!=null && "个人".equals(personType)){
								personType = "1";
							}else if(personType!=null && "集体".equals(personType)){
								personType = "2";
							}
							if(personType==null || (!"1".equals(personType) && !"2".equals(personType))){
								personType = bean.getPersonType();
							}
							bean.setPersonType(personType);
						}
					}
					// 捐赠人数
					if(personCountIndex!=-1){
						HSSFCell personCountCell = row.getCell((short) personCountIndex);
						if(personCountCell!=null){
							Long personCountL = null;
							String personCount = formatStr(personCountCell.toString().trim());
							if(personCount!=null && !"".equals(personCount)){
								try{
									personCountL = new Long(personCount);
								}catch(Exception e){}
							}
							if(personCountL==null || personCountL<1){
								personCountL = new Long(1);
							}
							bean.setPersonCount(personCountL);
						}
					}
					// 姓名
					if(personIndex!=-1){
						HSSFCell personCell = row.getCell((short) personIndex);
						if(personCell!=null){
							String person = formatStr(personCell.toString().trim());
							if(person==null || "".equals(person)){
								person = "匿名";
							}
							bean.setPersonName(person);
						}
					}
					// 性别
					if(personSexIndex!=-1){
						HSSFCell personSexCell = row.getCell((short) personSexIndex);
						if(personSexCell!=null){
							String personSex = formatStr(personSexCell.toString().trim());
							if(personSex!=null){
								if("男".equals(personSex) || "男性".equals(personSex) || "1".equals(personSex)){
									bean.setPersonSex("1");
								}else if("女".equals(personSex) || "女性".equals(personSex) || "2".equals(personSex)){
									bean.setPersonSex("2");
								}
							}
						}
					}
					// 匿名
					if(niMingIndex!=-1){
						HSSFCell niMingCell = row.getCell((short) niMingIndex);
						if(niMingCell!=null){
							String niMing = formatStr(niMingCell.toString().trim());
							if(niMing!=null && ("是".equals(niMing) || "匿名".equals(niMing) || "匿名捐赠".equals(niMing) || "1".equals(niMing))){
								bean.setNiMing("1");
							}else{
								bean.setNiMing("0");
							}
						}
					}
					// 手机
					if(personPhoneIndex!=-1){
						HSSFCell personPhoneCell = row.getCell((short) personPhoneIndex);
						if(personPhoneCell!=null){
							String personPhone = formatStr(personPhoneCell.toString().trim());
							bean.setPersonPhone(personPhone);
						}
					}
					// 电话
					if(personTelIndex!=-1){
						HSSFCell personTelCell = row.getCell((short) personTelIndex);
						if(personTelCell!=null){
							String personTel = formatStr(personTelCell.toString().trim());
							bean.setPersonTel(personTel);
						}
					}
					// 邮箱
					if(personEmailIndex!=-1){
						HSSFCell personEmailCell = row.getCell((short) personEmailIndex);
						if(personEmailCell!=null){
							String personEmail = formatStr(personEmailCell.toString().trim());
							bean.setPersonEmail(personEmail);
						}
					}
					// 邮编
					if(zipIndex!=-1){
						HSSFCell zipCell = row.getCell((short) zipIndex);
						if(zipCell!=null){
							String zip = formatStr(zipCell.toString().trim());
							bean.setAddressZip(zip);
						}
					}
					// 省
					if(shengIndex!=-1){
						HSSFCell shengCell = row.getCell((short) shengIndex);
						if(shengCell!=null){
							String sheng = formatStr(shengCell.toString().trim());
							bean.setAddressSheng(sheng);
						}
					}
					// 市
					if(shiIndex!=-1){
						HSSFCell shiCell = row.getCell((short) shiIndex);
						if(shiCell!=null){
							String shi = formatStr(shiCell.toString().trim());
							bean.setAddressShi(shi);
						}
					}
					// 区/县
					if(quIndex!=-1){
						HSSFCell quCell = row.getCell((short) quIndex);
						if (quCell != null) {
							String qu = formatStr(quCell.toString().trim());
							bean.setAddressQu(qu);
						}
					}
					// 详细地址
					if(addressIndex!=-1){
						HSSFCell addressCell = row.getCell((short) addressIndex);
						if (addressCell != null) {
							String address = formatStr(addressCell.toString().trim());
							bean.setAddressDetail(address);
						}
					}
					// 校友资料
					// 是否校友
					if(alumniFlagIndex!=-1){
						HSSFCell alumniFlagCell = row.getCell((short) alumniFlagIndex);
						if (alumniFlagCell != null) {
							String alumniFlag = formatStr(alumniFlagCell.toString().trim());
							if(alumniFlag!=null && ("校友".equals(alumniFlag) || "是".equals(alumniFlag) || "1".equals(alumniFlag))){
								bean.setAlumniFlag("1");
							}else{
								bean.setAlumniFlag("0");
							}
						}
					}
					// 入学年
					if(academyBegIndex!=-1){
						HSSFCell academyBegCell = row.getCell((short) academyBegIndex);
						if(academyBegCell!=null){
							String academyBeg = formatStr(academyBegCell.toString().trim());
							bean.setStudyYearin(academyBeg);
						}
					}
					// 离校年
					if(academyEndIndex!=-1){
						HSSFCell academyEndCell = row.getCell((short) academyEndIndex);
						if(academyEndCell!=null){
							String academyEnd = formatStr(academyEndCell.toString().trim());
							bean.setStudyYearover(academyEnd);
						}
					}
					// 院系
					if(academyIndex!=-1){
						HSSFCell academyCell = row.getCell((short) academyIndex);
						if(academyCell!=null){
							String academy = formatStr(academyCell.toString().trim());
							bean.setStudyAcademy(academy);
						}
					}
					// 专业
					if(majorIndex!=-1){
						HSSFCell majorCell = row.getCell((short) majorIndex);
						if(majorCell!=null){
							String major = formatStr(majorCell.toString().trim());
							bean.setStudyMajor(major);
						}
					}
					// 班级
					if(clazzIndex!=-1){
						HSSFCell clazzCell = row.getCell((short) clazzIndex);
						if(clazzCell!=null){
							String clazz = formatStr(clazzCell.toString().trim());
							bean.setStudyClass(clazz);
						}
					}
					// 学号
					if(studynoIndex!=-1){
						HSSFCell studynoCell = row.getCell((short) studynoIndex);
						if(studynoCell!=null){
							String studyno = formatStr(studynoCell.toString().trim());
							bean.setStudyNum(studyno);
						}
					}
					// 学历
					if(academyDegreeIndex!=-1){
						HSSFCell academyDegreeCell = row.getCell((short) academyDegreeIndex);
						if(academyDegreeCell!=null){
							String academyDegree = formatStr(academyDegreeCell.toString().trim());
							bean.setStudyDegree(academyDegree);
						}
					}
					// 工作资料
					// 公司
					if(companyIndex!=-1){
						HSSFCell companyCell = row.getCell((short) companyIndex);
						if(companyCell!=null){
							String company = formatStr(companyCell.toString().trim());
							bean.setWorkCompany(company);
						}
					}
					// 部门
					if(departmentIndex!=-1){
						HSSFCell departmentCell = row.getCell((short) departmentIndex);
						if(departmentCell!=null){
							String department = formatStr(departmentCell.toString().trim());
							bean.setWorkDepart(department);
						}
					}
					// 职位
					if(dutyIndex!=-1){
						HSSFCell dutyCell = row.getCell((short) dutyIndex);
						if(dutyCell!=null){
							String duty = formatStr(dutyCell.toString().trim());
							bean.setWorkDuty(duty);
						}
					}
					// 其他信息
					if(orderMemoIndex!=-1){
						HSSFCell orderMemoCell = row.getCell((short) orderMemoIndex);
						if(orderMemoCell!=null){
							String orderMemo = formatStr(orderMemoCell.toString().trim());
							bean.setOrderMemo(orderMemo);
						}
					}
					// 标记信息
					if(markIndex!=-1){
						HSSFCell markCell = row.getCell((short) markIndex);
						if(markCell!=null){
							String mark = formatStr(markCell.toString().trim());
							bean.setMark(mark);
						}
					}
					// 回执信息
					if(receiptIndex!=-1){
						HSSFCell receiptCell = row.getCell((short) receiptIndex);
						if(receiptCell!=null){
							String receipt = formatStr(receiptCell.toString().trim());
							bean.setReceipt(receipt);
						}
					}
					// 备注
					if(memoIndex!=-1){
						HSSFCell memoCell = row.getCell((short) memoIndex);
						if(memoCell!=null){
							String memo = formatStr(memoCell.toString().trim());
							bean.setMemo(memo);
						}
					}
					// 保存
					bean = (TbZcotherOrder) this.getCommonBo().saveOrUpdate(bean);
					if(bean.getOrderNum()==null || "".equals(bean.getOrderNum())){
						// 单号
						String orderNum = bean.getOrderId().toString();
						int orderLen = orderNum.length();
						for(int j=0; j<17-orderLen; j++){
							orderNum = "0" + orderNum;
						}
						orderNum = DateUtil.format(dateNow,"yyyyMMdd") + "002" + orderNum;
						bean.setOrderNum(orderNum);
						bean = (TbZcotherOrder) this.getCommonBo().update(bean);
						request.setAttribute("bean", bean);
					}
					successcount++;
				}catch(Exception e){
					errorcount++;
					errorList.add(new String[] { String.valueOf(i), "导入失败，原因：该条捐赠信息导入时，发生异常，异常描述："+e.getMessage() });
				}
			}
			request.setAttribute("successcount", successcount);
			request.setAttribute("errorcount", errorcount);
			request.setAttribute("errorList", errorList);
			return "import";
		}catch(Exception e){
			request.setAttribute("alert", "警告，导入文件时出现异常，异常描述："+e.getMessage());
			return "import";
		}finally{
			if(fis!=null){
				fis.close();
			}
			// 移除导入进度参数
			session.removeAttribute(IMPORTINDEX + random);
			session.removeAttribute(IMPORTCOUNT + random);
		}
	}
	// 批量导出
	public String doExport()  throws Exception {
		HttpServletRequest request = this.getRequest();
		// 导出结果
		String ajax = "{\"result\":\"error\"}";
		// 1、下载导入模板，其他为导出
		String type = request.getParameter("type");
		// 需要导出的信息系统集合
		List beantList = null;
		// 获取需要导出的捐赠信息集合
		if(type==null || !"1".equals(type)){
			try{
				// 构造查询
				String hql = "from TbZcotherOrder a where 1=1";
				List pars = this.getList();
				// 捐赠项目
				String projName = request.getParameter("projName");
				if(projName!=null && !"".equals(projName)){
					hql += " and a.projName like ?";
					pars.add("%"+projName+"%");
				}
				// 捐赠单号
				String orderNum = request.getParameter("orderNum");
				if(orderNum!=null && !"".equals(orderNum)){
					hql += " and a.orderNum like ?";
					pars.add("%"+orderNum+"%");
				}
				// 捐赠方式
				String orderType = request.getParameter("orderType");
				if(orderType!=null && !"".equals(orderType)){
					hql += " and a.orderType like ?";
					pars.add("%"+orderType+"%");
				}
				// 证书
				String needZhengshu = request.getParameter("needZhengshu");
				if(needZhengshu!=null && "1".equals(needZhengshu)){
					hql += " and a.needZhengshu = ?";
					pars.add("1");
				}else if(needZhengshu!=null && !"".equals(needZhengshu)){
					hql += " and a.needZhengshu <> ?";
					pars.add("1");
				}
				// 发票
				String needFapiao = request.getParameter("needFapiao");
				if(needFapiao!=null && "1".equals(needFapiao)){
					hql += " and a.needFapiao = ?";
					pars.add("1");
				}else if(needFapiao!=null && !"".equals(needFapiao)){
					hql += " and (a.needFapiao is null or a.needFapiao <> ?)";
					pars.add("1");
				}
				// 匿名捐赠
				String niMing = request.getParameter("niMing");
				if(niMing!=null && "1".equals(niMing)){
					hql += " and a.niMing = ?";
					pars.add("1");
				}else if(niMing!=null && !"".equals(niMing)){
					hql += " and (a.niMing is null or a.niMing <> ?)";
					pars.add("1");
				}
				// 捐赠人
				String personName = request.getParameter("personName");
				if(personName!=null && !"".equals(personName)){
					hql += " and a.personName like ?";
					pars.add("%"+personName+"%");
				}
				// 捐赠人信息
				String personInfo = request.getParameter("personInfo");
				if(personInfo!=null && !"".equals(personInfo)){
					hql += " and (a.personPhone like ? or a.personTel like ? or a.personEmail like ? or a.addressSheng like ? or a.addressShi like ? or a.addressQu like ? or a.addressDetail like ? or a.addressZip like ?)";
					pars.add("%"+personInfo+"%");
					pars.add("%"+personInfo+"%");
					pars.add("%"+personInfo+"%");
					pars.add("%"+personInfo+"%");
					pars.add("%"+personInfo+"%");
					pars.add("%"+personInfo+"%");
					pars.add("%"+personInfo+"%");
					pars.add("%"+personInfo+"%");
				}
				// 校友
				String alumni = request.getParameter("alumni");
				if(alumni!=null && "1".equals(alumni)){
					hql += " and a.alumniFlag = ?";
					pars.add("1");
				}else if(alumni!=null && !"".equals(alumni)){
					hql += " and (a.alumniFlag is null or a.alumniFlag <> ?)";
					pars.add("1");
				}
				// 校友信息
				String alumniInfo = request.getParameter("alumniInfo");
				if(alumniInfo!=null && !"".equals(alumniInfo)){
					hql += " and (a.studyYearin like ? or a.studyYearover like ? or a.studyAcademy like ? or a.studyMajor like ? or a.studyClass like ? or a.studyNum like ? or a.studyDegree like ?)";
					pars.add("%"+alumniInfo+"%");
					pars.add("%"+alumniInfo+"%");
					pars.add("%"+alumniInfo+"%");
					pars.add("%"+alumniInfo+"%");
					pars.add("%"+alumniInfo+"%");
					pars.add("%"+alumniInfo+"%");
					pars.add("%"+alumniInfo+"%");
				}
				// 标记
				String markFlag = request.getParameter("markFlag");
				if(markFlag!=null && "1".equals(markFlag)){
					hql += " and a.mark is not null";
					String mark = request.getParameter("mark");
					if(mark!=null && !"".equals(mark)){
						hql += " and a.mark like ?";
						pars.add("%"+mark+"%");
					}
				}else if(markFlag!=null && !"".equals(markFlag)){
					hql += " and a.mark is null";
				}
				// 捐赠币种
				String amountType = request.getParameter("amountType");
				if(amountType!=null && !"".equals(amountType)){
					hql += " and a.orderAmountType like ?";
					pars.add("%"+amountType+"%");
				}
				// 提交起始时间
				Date orderTimeBegD = null;
				String orderTimeBeg = request.getParameter("orderTimeBeg");
				if(orderTimeBeg!=null && !"".equals(orderTimeBeg)){
					orderTimeBegD = DateUtil.toDate(orderTimeBeg, "yyyy-MM-dd HH:mm");
				}
				if(orderTimeBegD!=null){
					hql += " and a.orderTime >= ?";
					pars.add(orderTimeBegD);
				}
				// 提交截止时间
				Date orderTimeEndD = null;
				String orderTimeEnd = request.getParameter("orderTimeEnd");
				if(orderTimeEnd!=null && !"".equals(orderTimeEnd)){
					orderTimeEndD = DateUtil.toDate(orderTimeEnd, "yyyy-MM-dd HH:mm");
				}
				if(orderTimeEndD!=null){
					hql += " and a.orderTime <= ?";
					pars.add(orderTimeEndD);
				}
				// 付款起始时间
				Date orderOkTimeBegD = null;
				String orderOkTimeBeg = request.getParameter("orderOkTimeBeg");
				if(orderOkTimeBeg!=null && !"".equals(orderOkTimeBeg)){
					orderOkTimeBegD = DateUtil.toDate(orderOkTimeBeg, "yyyy-MM-dd HH:mm");
				}
				if(orderOkTimeBegD!=null){
					hql += " and a.orderOkTime >= ?";
					pars.add(orderOkTimeBegD);
				}
				// 付款截止时间
				Date orderOkTimeEndD = null;
				String orderOkTimeEnd = request.getParameter("orderOkTimeEnd");
				if(orderOkTimeEnd!=null && !"".equals(orderOkTimeEnd)){
					orderOkTimeEndD = DateUtil.toDate(orderOkTimeEnd, "yyyy-MM-dd HH:mm");
				}
				if(orderOkTimeEndD!=null){
					hql += " and a.orderOkTime <= ?";
					pars.add(orderOkTimeEndD);
				}
				// 捐赠状态
				String orderStatus = request.getParameter("orderStatus");
				if(orderStatus!=null && "1".equals(orderStatus)){
					hql += " and a.orderStatus = ?";
					pars.add("1");
				}else if(orderStatus!=null && !"".equals(orderStatus)){
					hql += " and (a.orderStatus is null or a.orderStatus <> ?)";
					pars.add("1");
				}
				// 捐赠人类型
				String personType = request.getParameter("personType");
				if(personType!=null && "1".equals(personType)){
					hql += " and a.personType = ?";
					pars.add("1");
				}else if(personType!=null && !"".equals(personType)){
					hql += " and (a.personType is null or a.personType <> ?)";
					pars.add("1");
				}
				hql += " order by a.orderTime desc";
				// 执行查询
				beantList = this.getCommonBo().findHQL(hql,pars);
			}catch (Exception e) {}
		}
		// 导出Excel文件
		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet();
			// 设置单元格宽度
			sheet.setColumnWidth((short) 0, (short) 8000);
			sheet.setColumnWidth((short) 1, (short) 8000);
			sheet.setDefaultColumnWidth((short) 24);
			// 设置单元格字体
			HSSFFont font = wb.createFont();
			font.setFontName("宋体");
			font.setFontHeight((short) 200);
			// 创建单元格样式(左上角)
			HSSFCellStyle cellLeftTopStyle = wb.createCellStyle();
			// 指定单元格居中对齐
			cellLeftTopStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// 指定单元格垂直居中对齐
			cellLeftTopStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			// 指定当单元格内容显示不下时自动换行
			cellLeftTopStyle.setWrapText(true);
			cellLeftTopStyle.setFont(font);
			cellLeftTopStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
			cellLeftTopStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
			cellLeftTopStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
			cellLeftTopStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
			// 创建单元格样式(左)
			HSSFCellStyle cellLeftStyle = wb.createCellStyle();
			// 指定单元格居中对齐
			cellLeftStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// 指定单元格垂直居中对齐
			cellLeftStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			// 指定当单元格内容显示不下时自动换行
			cellLeftStyle.setWrapText(true);
			cellLeftStyle.setFont(font);
			cellLeftStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
			cellLeftStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
			cellLeftStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
			// 创建单元格样式(上)
			HSSFCellStyle cellTopStyle = wb.createCellStyle();
			// 指定单元格居中对齐
			cellTopStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// 指定单元格垂直居中对齐
			cellTopStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			// 指定当单元格内容显示不下时自动换行
			cellTopStyle.setWrapText(true);
			cellTopStyle.setFont(font);
			cellTopStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
			cellTopStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
			cellTopStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
			// 创建单元格样式(其他)
			HSSFCellStyle cellStyle = wb.createCellStyle();
			// 指定单元格居中对齐
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// 指定单元格垂直居中对齐
			cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			// 指定当单元格内容显示不下时自动换行
			cellStyle.setWrapText(true);
			cellStyle.setFont(font);
			cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
			cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
			// 第一行，标题行
			List headList = this.getList();
			headList.add("捐赠单号");
			headList.add("项目名称");
			headList.add("项目链接");
			headList.add("捐赠币种");
			headList.add("捐赠币种金额");
			headList.add("捐赠币种计量单位");
			headList.add("捐赠实算金额");
			headList.add("捐赠方式");
			headList.add("捐赠状态");
			headList.add("提交时间");
			headList.add("付款时间");
			headList.add("需要证书");
			headList.add("需要发票");
			headList.add("匿名捐赠");
			headList.add("捐赠人类型");
			headList.add("捐赠人数");
			headList.add("捐赠人");
			headList.add("性别");
			headList.add("手机");
			headList.add("电话");
			headList.add("邮箱");
			headList.add("邮编");
			headList.add("省");
			headList.add("市");
			headList.add("区/县");
			headList.add("详细地址");
			headList.add("是否校友");
			headList.add("入学年");
			headList.add("离校年");
			headList.add("院系");
			headList.add("专业");
			headList.add("班级");
			headList.add("学号");
			headList.add("学历");
			headList.add("公司");
			headList.add("部门");
			headList.add("职位");
			headList.add("其他信息");
			headList.add("标记信息");
			headList.add("回执信息");
			headList.add("备注");
			HSSFRow row0 = sheet.createRow(0);
			row0.setHeight((short) 600);
			// 第一行
			for(int i=0; i<headList.size(); i++){
				HSSFCell row0cell = row0.createCell((short) i);
				row0cell.setCellStyle(cellTopStyle);
				row0cell.setCellValue(new HSSFRichTextString(headList.get(i).toString()));
			}
			// 数据行
			if(beantList!=null && !beantList.isEmpty()){
				for(int i=1; i<beantList.size()+1; i++){
					HSSFRow row = sheet.createRow(i);
					row.setHeight((short) 600);
					TbZcotherOrder bean = (TbZcotherOrder)beantList.get(i-1);
					// 列号
					int k = 0;
					// 列对象
					HSSFCell cell = null;
					// 捐赠单号
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getOrderNum()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getOrderNum()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 项目名称
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getProjName()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getProjName()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 项目连接
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getProjUrl()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getProjUrl()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 捐赠币种
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getOrderAmountType()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getOrderAmountType().toString()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 捐赠币种金额
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getOrderAmountView()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getOrderAmountView().toString()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 捐赠币种计量单位
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getOrderAmountUnit()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getOrderAmountUnit().toString()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 捐赠实算金额
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getOrderAmount()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getOrderAmount().toString()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 捐赠方式
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getOrderType()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getOrderType()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 捐赠状态
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getOrderStatus()!=null && "1".equals(bean.getOrderStatus())){
						cell.setCellValue(new HSSFRichTextString("已付款"));
					}else{
						cell.setCellValue(new HSSFRichTextString("待付款"));
					}
					// 提交时间
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getOrderTime()!=null){
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						cell.setCellValue(new HSSFRichTextString(format.format(bean.getOrderTime())));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 付款时间
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getOrderOkTime()!=null){
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						cell.setCellValue(new HSSFRichTextString(format.format(bean.getOrderOkTime())));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 证书
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getNeedZhengshu()!=null && "1".equals(bean.getNeedZhengshu())){
						cell.setCellValue(new HSSFRichTextString("是"));
					}else{
						cell.setCellValue(new HSSFRichTextString("否"));
					}
					// 发票
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getNeedFapiao()!=null && "1".equals(bean.getNeedFapiao())){
						cell.setCellValue(new HSSFRichTextString("是"));
					}else{
						cell.setCellValue(new HSSFRichTextString("否"));
					}
					// 是否匿名
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getNiMing()!=null && "1".equals(bean.getNiMing())){
						cell.setCellValue(new HSSFRichTextString("是"));
					}else{
						cell.setCellValue(new HSSFRichTextString("否"));
					}
					// 捐赠人类型
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getPersonType()!=null && "2".equals(bean.getPersonType())){
						cell.setCellValue(new HSSFRichTextString("集体"));
					}else{
						cell.setCellValue(new HSSFRichTextString("个人"));
					}
					// 捐赠人数
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getPersonCount()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getPersonCount().toString()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 捐赠人
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getPersonName()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getPersonName()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 性别
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getPersonSex()!=null && "1".equals(bean.getPersonSex())){
						cell.setCellValue(new HSSFRichTextString("男"));
					}else if(bean.getPersonSex()!=null && "2".equals(bean.getPersonSex())){
						cell.setCellValue(new HSSFRichTextString("女"));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 手机
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getPersonPhone()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getPersonPhone()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 电话
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getPersonTel()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getPersonTel()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 邮箱
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getPersonEmail()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getPersonEmail()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 邮编
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getAddressZip()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getAddressZip()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 省
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getAddressSheng()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getAddressSheng()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 市
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getAddressShi()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getAddressShi()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 区/县
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getAddressQu()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getAddressQu()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 详细地址
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getAddressDetail()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getAddressDetail()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 是否校友
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getAlumniFlag()!=null && "1".equals(bean.getAlumniFlag())){
						cell.setCellValue(new HSSFRichTextString("是"));
					}else{
						cell.setCellValue(new HSSFRichTextString("否"));
					}
					// 入学年
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getStudyYearin()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getStudyYearin()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 离校年
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getStudyYearover()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getStudyYearover()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 院系
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getStudyAcademy()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getStudyAcademy()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 专业
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getStudyMajor()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getStudyMajor()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 班级
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getStudyClass()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getStudyClass()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 学号
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getStudyNum()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getStudyNum()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 学历
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getStudyDegree()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getStudyDegree()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 公司
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getWorkCompany()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getWorkCompany()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 部门
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getWorkDepart()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getWorkDepart()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 职位
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getWorkDuty()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getWorkDuty()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 其他信息
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getOrderMemo()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getOrderMemo()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 标记信息
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getMark()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getMark()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 回执信息
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getReceipt()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getReceipt()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 备注
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getMemo()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getMemo()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
				}
			}
			//保存文件
			String fielCurName = "其他捐赠信息导出"+DateUtil.format(new Date(),"yyyyMMddHHmmss")+".xls";
			String fileNewName = "其他捐赠信息导出"+".xls";
			String dirPath = ServletActionContext.getServletContext().getRealPath("download/");
			File dirFile=new File(dirPath);
			if(!dirFile.exists()){
				dirFile.mkdir();
			}
			File file=new File(dirPath,fielCurName);
			FileOutputStream fo = new FileOutputStream(file);
			wb.write(fo);
			fo.close();
			ajax = "{\"result\":\"success\",\"name\":\""+fileNewName+"\",\"path\":\"download/"+fielCurName+"\"}";
		}catch(Exception ex){}
		// 返回
		this.sendResponse(this.getResponse(), ajax);
		return null;
	}
}
