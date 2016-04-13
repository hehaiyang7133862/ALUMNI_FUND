package com.laungee.proj.zc.web.action;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbZcproj;
import com.laungee.proj.common.model.TbZcprojOrder;
import com.laungee.proj.common.util.DateUtil;

public class ZcProjOrderAction extends BaseAction {
	// 众筹项目捐赠列表
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 构造查询
		String hql = "from TbZcprojOrder a where 1=1";
		List pars = this.getList();
		// 捐赠项目
		String projName = request.getParameter("projName");
		if(projName!=null && !"".equals(projName)){
			hql += " and a.projName like ?";
			pars.add("%"+projName+"%");
		}
		// 捐赠选项
		String optionName = request.getParameter("optionName");
		if(optionName!=null && !"".equals(optionName)){
			hql += " and a.optionName like ?";
			pars.add("%"+optionName+"%");
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
			hql += " and (a.needZhengshu is null or a.needZhengshu <> ?)";
			pars.add("1");
		}
		// 发票
		String needFapiao = request.getParameter("needFapiao");
		if(needZhengshu!=null && "1".equals(needZhengshu)){
			hql += " and a.needFapiao = ?";
			pars.add("1");
		}else if(needZhengshu!=null && !"".equals(needZhengshu)){
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
		hql += " order by a.orderTime desc,a.orderId desc";
		// 执行查询
		List orderList = this.getCommonBo().findHQLPage(hql, pars);
		request.setAttribute("orderList", orderList);
		// 返回
		return SUCCESS;
	}
	
	// 众筹项目捐赠详情页面
	public String view() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 项目捐赠
		TbZcprojOrder bean = null;
		// 项目捐赠ID
		String orderId = request.getParameter("orderId");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbZcprojOrder)this.getCommonBo().get(TbZcprojOrder.class, new Long(orderId));
			}catch(Exception e){}
		}
		request.setAttribute("bean", bean);
		// 返回
		return "view";
	}
	
	// 众筹项目捐赠标记页面
	public String mark() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 项目捐赠
		TbZcprojOrder bean = null;
		// 项目捐赠ID
		String orderId = request.getParameter("orderId");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbZcprojOrder)this.getCommonBo().get(TbZcprojOrder.class, new Long(orderId));
			}catch(Exception e){}
		}
		request.setAttribute("bean", bean);
		// 返回
		return "mark";
	}
	
	// 众筹项目进展标记
	public String markUpdate() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 项目捐赠
		TbZcprojOrder bean = null;
		// 项目捐赠ID
		String orderId = request.getParameter("orderId");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbZcprojOrder)this.getCommonBo().get(TbZcprojOrder.class, new Long(orderId));
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

	// 众筹项目捐赠删除
	public String del() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 项目捐赠
		TbZcprojOrder bean = null;
		// 项目捐赠ID
		String orderId = request.getParameter("orderId");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbZcprojOrder)this.getCommonBo().get(TbZcprojOrder.class, new Long(orderId));
			}catch(Exception e){}
		}
		if(bean!=null){
			this.getCommonBo().delete(bean);
		}
		// 返回
		return execute();
	}

	// 批量导出
	public String doExport() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 导出结果
		String ajax = "{\"result\":\"error\"}";
		// 需要导出的信息系统集合
		List beantList = null;
		// 获取需要导出的捐赠信息集合
		try {
			// 构造查询
			String hql = "from TbZcprojOrder a where 1=1";
			List pars = this.getList();
			// 捐赠项目
			String projName = request.getParameter("projName");
			if(projName!=null && !"".equals(projName)){
				hql += " and a.projName like ?";
				pars.add("%"+projName+"%");
			}
			// 捐赠选项
			String optionName = request.getParameter("optionName");
			if(optionName!=null && !"".equals(optionName)){
				hql += " and a.optionName like ?";
				pars.add("%"+optionName+"%");
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
				hql += " and (a.needZhengshu is null or a.needZhengshu <> ?)";
				pars.add("1");
			}
			// 发票
			String needFapiao = request.getParameter("needFapiao");
			if(needZhengshu!=null && "1".equals(needZhengshu)){
				hql += " and a.needFapiao = ?";
				pars.add("1");
			}else if(needZhengshu!=null && !"".equals(needZhengshu)){
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
			hql += " order by a.orderTime desc,a.orderId desc";
			// 执行查询
			beantList = this.getCommonBo().findHQL(hql, pars);
		}catch(Exception e){ }
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
			headList.add("捐赠选项");
			headList.add("捐赠份数");
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
					TbZcprojOrder bean = (TbZcprojOrder)beantList.get(i-1);
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
					// 捐赠选项
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getOptionName()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getOptionName()));
					}else{
						TbZcproj tempZcproj = bean.getTbZcproj();
						if(tempZcproj!=null && tempZcproj.getOptionOtherName()!=null && !"".equals(tempZcproj.getOptionOtherName())){
							cell.setCellValue(new HSSFRichTextString(tempZcproj.getOptionOtherName()));
						}else{
							cell.setCellValue(new HSSFRichTextString("任意捐"));
						}
					}
					// 捐赠份数
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getOptionCount()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getOptionCount().toString()));
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
			// 保存文件
			String fielCurName = "捐赠信息导出" + DateUtil.format(new Date(), "yyyyMMddHHmmss") + ".xls";
			String fileNewName = "捐赠信息导出" + ".xls";
			String dirPath = ServletActionContext.getServletContext().getRealPath("download/");
			File dirFile = new File(dirPath);
			if(!dirFile.exists()){
				dirFile.mkdir();
			}
			File file = new File(dirPath, fielCurName);
			FileOutputStream fo = new FileOutputStream(file);
			wb.write(fo);
			fo.close();
			ajax = "{\"result\":\"success\",\"name\":\"" + fileNewName + "\",\"path\":\"download/" + fielCurName + "\"}";
		} catch (Exception ex) { }
		// 返回
		this.sendResponse(this.getResponse(), ajax);
		return null;
	}
}