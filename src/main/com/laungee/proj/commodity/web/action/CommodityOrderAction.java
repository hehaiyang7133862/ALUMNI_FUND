package com.laungee.proj.commodity.web.action;

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
import com.laungee.proj.common.model.TbCommodity;
import com.laungee.proj.common.model.TbCommodityDetail;
import com.laungee.proj.common.model.TbCommodityOrder;
import com.laungee.proj.common.model.TbZcotherOrder;
import com.laungee.proj.common.model.TbZcproj;
import com.laungee.proj.common.model.TbZcprojOption;
import com.laungee.proj.common.model.TbZcprojOrder;
import com.laungee.proj.common.util.DateUtil;
import com.laungee.proj.common.util.MultiRequestUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class CommodityOrderAction extends BaseAction {
	// 商品订单列表
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 构造查询
		String hql = "from TbCommodityOrder a where 1=1";
		List pars = this.getList();
		// 商品名称
		String commName = request.getParameter("commName");
		if (commName != null && !"".equals(commName)) {
			hql += " and a.commName like ?";
			pars.add("%" + commName + "%");
		}
		// 商品型号
		String detialName = request.getParameter("detailName");
		if (detialName != null && !"".equals(detialName)) {
			hql += " and a.commDetailName like ?";
			pars.add("%" + detialName + "%");
		}

		// 提交起始时间
		Date orderTimeBegD = null;
		String orderTimeBeg = request.getParameter("orderTimeBeg");
		if (orderTimeBeg != null && !"".equals(orderTimeBeg)) {
			orderTimeBegD = DateUtil.toDate(orderTimeBeg, "yyyy-MM-dd HH:mm");
		}
		if (orderTimeBegD != null) {
			hql += " and a.orderTime > ?";
			pars.add(orderTimeBegD);
		}

		// 提交截止时间
		Date orderTimeEndD = null;
		String orderTimeEnd = request.getParameter("orderTimeEnd");
		if (orderTimeEnd != null && !"".equals(orderTimeEnd)) {
			orderTimeEndD = DateUtil.toDate(orderTimeEnd, "yyyy-MM-dd HH:mm");
		}
		if (orderTimeEndD != null) {
			hql += " and a.orderTime < ?";
			pars.add(orderTimeEndD);
		}
		// 付款起始时间
		Date orderOkTimeBegD = null;
		String orderOkTimeBeg = request.getParameter("orderOkTimeBeg");
		if (orderOkTimeBeg != null && !"".equals(orderOkTimeBeg)) {
			orderOkTimeBegD = DateUtil.toDate(orderOkTimeBeg,
					"yyyy-MM-dd HH:mm");
		}
		if (orderOkTimeBegD != null) {
			hql += " and a.orderOkTime > ?";
			pars.add(orderOkTimeBegD);
		}
		// 付款截止时间
		Date orderOkTimeEndD = null;
		String orderOkTimeEnd = request.getParameter("orderOkTimeEnd");
		if (orderOkTimeEnd != null && !"".equals(orderOkTimeEnd)) {
			orderOkTimeEndD = DateUtil.toDate(orderOkTimeEnd,
					"yyyy-MM-dd HH:mm");
		}
		if (orderOkTimeEndD != null) {
			hql += " and a.orderOkTime < ?";
			pars.add(orderOkTimeEndD);
		}

		// 买家姓名
		String buyerName = request.getParameter("buyerName");
		if (buyerName != null && !"".equals(buyerName)) {
			hql += " and a.buyerName like ?";
			pars.add("%" + buyerName + "%");
		}
		// 商品订单号
		String orderNo = request.getParameter("orderNo");
		if (orderNo != null && !"".equals(orderNo)) {
			hql += " and a.orderNo like ?";
			pars.add("%" + orderNo + "%");
		}
		// 商品订单支付方式
		String orderType = request.getParameter("orderType");
		if (orderType != null && !"".equals(orderType)) {
			hql += " and a.orderType like ?";
			pars.add("%" + orderType + "%");
		}
		// 商品订单支付状态
		String orderStatus = request.getParameter("orderStatus");
		if (orderStatus != null && "1".equals(orderStatus)) {
			hql += " and a.orderStatus = ?";
			pars.add("1");
		} else if (orderStatus != null && !"".equals(orderStatus)) {
			hql += " and a.orderStatus <> ?";
			pars.add("1");
		}
		// 标记
		String markFlag = request.getParameter("markFlag");
		if (markFlag != null && "1".equals(markFlag)) {
			hql += " and a.mark is not null";
			String mark = request.getParameter("mark");
			if (mark != null && !"".equals(mark)) {
				hql += " and a.mark like ?";
				pars.add("%" + mark + "%");
			}
		} else if (markFlag != null && !"".equals(markFlag)) {
			hql += " and a.mark is null";
		}

		hql += " order by a.orderTime desc";
		// 执行查询
		List orderList = this.getCommonBo().findHQLPage(hql, pars);
		request.setAttribute("orderList", orderList);
		// 返回
		return SUCCESS;
	}

	// 商品订单详情页面
	public String view() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 商品订单
		TbCommodityOrder bean = null;
		// 商品订单ID
		String orderId = request.getParameter("orderId");
		if (orderId != null && !"".equals(orderId)) {
			try {
				bean = (TbCommodityOrder) this.getCommonBo().get(
						TbCommodityOrder.class, new Long(orderId));
			} catch (Exception e) {
			}
		}
		request.setAttribute("bean", bean);
		// 返回
		return "view";
	}

	// 商品订单标记页面
	public String mark() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 商品订单
		TbCommodityOrder bean = null;
		// 商品订单ID
		String orderId = request.getParameter("orderId");
		if (orderId != null && !"".equals(orderId)) {
			try {
				bean = (TbCommodityOrder) this.getCommonBo().get(
						TbCommodityOrder.class, new Long(orderId));
			} catch (Exception e) {
			}
		}
		request.setAttribute("bean", bean);
		// 返回
		return "mark";
	}

	// 众筹项目进展标记
	public String markUpdate() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 商品订单
		TbCommodityOrder bean = null;
		// 商品订单ID
		String orderId = request.getParameter("orderId");
		if (orderId != null && !"".equals(orderId)) {
			try {
				bean = (TbCommodityOrder) this.getCommonBo().get(
						TbCommodityOrder.class, new Long(orderId));
			} catch (Exception e) {
			}
		}
		if (bean != null) {
			// 标记信息
			String mark = request.getParameter("mark");
			bean.setMark(mark);
			// 实用运费
			BigDecimal shippingCurfeeD = null;
			String shippingCurfee = request.getParameter("shippingCurfee");
			if (shippingCurfee != null && !"".equals(shippingCurfee)) {
				try {
					shippingCurfeeD = new BigDecimal(shippingCurfee).setScale(
							2, 4);
				} catch (Exception e) {
				}
			}
			bean.setShippingCurfee(shippingCurfeeD);
			// 回执信息
			String receipt = request.getParameter("receipt");
			bean.setReceipt(receipt);
			// 备注
			String memo = request.getParameter("memo");
			bean.setMemo(memo);
			// 保存选项
			try {
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

	// 商品订单删除
	public String del() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 商品订单
		TbCommodityOrder bean = null;
		// 商品订单ID
		String orderId = request.getParameter("orderId");
		if (orderId != null && !"".equals(orderId)) {
			try {
				bean = (TbCommodityOrder) this.getCommonBo().get(
						TbCommodityOrder.class, new Long(orderId));
			} catch (Exception e) {
			}
		}
		if (bean != null) {
			this.getCommonBo().delete(bean);
		}
		// 返回
		return execute();
	}

	public String GetOrCreatePrdOredr() throws Exception {
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

		TbCommodityOrder bean = null;
		String orderId = request.getParameter("orderId");
		if (orderId != null && !"".equals(orderId)) {
			try {
				bean = (TbCommodityOrder) this.getCommonBo().get(
						TbCommodityOrder.class, new Long(orderId));
			} catch (Exception e) {
			}
		}

		TbCommodity beanPrd = null;
		String commId = request.getParameter("commId");
		if (commId != null && !"".equals(commId)) {
			try {
				beanPrd = (TbCommodity) this.getCommonBo().get(
						TbCommodity.class, new Long(commId));
			} catch (Exception e) {
			}
		}

		if (bean == null) {
			bean = new TbCommodityOrder();
			bean.setCommName(beanPrd.getCommName());
			bean.setCommId(beanPrd.getCommId());
		}
		request.setAttribute("bean", bean);

		String hql = "from TbCommodityDetail a where a.commId=? order by a.numOrder";
		List pars = this.getList();
		pars.add(bean.getCommId());
		List optionList = this.getCommonBo().findHQL(hql, pars);
		request.setAttribute("optionList", optionList);

		// 返回
		return "edit";
	}

	public String SaveOrUpdatePrdOrder() throws Exception {
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

		TbCommodity tbCommodity = null;
		String commId = request.getParameter("commId");
		if (commId != null && !"".equals(commId)) {
			try {
				tbCommodity = (TbCommodity) this.getCommonBo().get(
						TbCommodity.class, new Long(commId));
			} catch (Exception e) {
			}
		}

		if (tbCommodity == null) {
			request.setAttribute("alert", "商品不存在！");
			return "error";
		}

		TbCommodityOrder bean = null;
		String orderId = request.getParameter("orderId");
		if (orderId != null && !"".equals(orderId)) {
			try {
				bean = (TbCommodityOrder) this.getCommonBo().get(
						TbCommodityOrder.class, new Long(orderId));
			} catch (Exception e) {
			}
		}

		if (bean == null) {
			bean = new TbCommodityOrder();
			bean.setCommId(tbCommodity.getCommId());
			bean.setCommName(tbCommodity.getCommName());
		}

		// 订单总金额
		BigDecimal orderFeeD = null;
		String orderFee = request.getParameter("orderFee");
		if (orderFee != null && !"".equals(orderFee)) {
			try {
				orderFeeD = new BigDecimal(orderFee).setScale(2, 4);
			} catch (Exception e) {
			}
		}
		bean.setOrderFee(orderFeeD);

		// 商品型号
		Long detailIdL = null;
		String detailId = request.getParameter("detailId");
		if (detailId != null && !"".equals(detailId)) {
			try {
				detailIdL = new Long(detailId);
			} catch (Exception e) {
			}
		}
		String detailName = request.getParameter("detailName");
		if (detailIdL == null && (detailName == null || "".equals(detailName))) {
			request.setAttribute("alert", "请设置捐赠选项！");
			request.setAttribute("bean", bean);
			return "error";
		}
		bean.setCommDetailName(detailName);
		bean.setCommDetailId(detailIdL);

		// 成本价
		BigDecimal commCostfeeD = null;
		String commCostfee = request.getParameter("commCostfee");
		if (commCostfee != null && !"".equals(commCostfee)) {
			try {
				commCostfeeD = new BigDecimal(commCostfee).setScale(2, 4);
			} catch (Exception e) {
			}
		}
		if (commCostfeeD == null || commCostfeeD.doubleValue() < 0) {
			request.setAttribute("alert", "商品单价不能小于0！");
			request.setAttribute("bean", bean);
			return "error";
		}
		bean.setCommCostfee(commCostfeeD);
		
		//售价
		BigDecimal commSalefeeD = null;
		String commSalefee = request.getParameter("commSalefee");
		if (commSalefee != null && !"".equals(commSalefee)) {
			try {
				commSalefeeD = new BigDecimal(commSalefee).setScale(2, 4);
			} catch (Exception e) {
			}
		}
		if (commSalefeeD == null || commSalefeeD.doubleValue() < 0) {
			request.setAttribute("alert", "商品单价不能小于0！");
			request.setAttribute("bean", bean);
			return "error";
		}
		bean.setCommSalefee(commSalefeeD);

		// 商品数量
		Long commNumL = null;
		String commNum = request.getParameter("commNum");
		if (commNum != null && !"".equals(commNum)) {
			try {
				commNumL = new Long(commNum);
			} catch (Exception e) {
			}
		}
		if (commNumL != null && commNumL < 1) {
			request.setAttribute("alert", "商品数量不能少于1件！");
			request.setAttribute("bean", bean);
			return "error";
		}
		bean.setCommNum(commNumL);

		// 取货方式
		String shippingType = request.getParameter("shippingType");
		if (shippingType != null && !shippingType.equals("")) {
			bean.setShippingType(shippingType);
		}

		// 运费
		BigDecimal shippingFeeD = null;
		String shippingFee = request.getParameter("shippingFee");
		if (shippingFee != null && !"".equals(shippingFee)) {
			try {
				shippingFeeD = new BigDecimal(shippingFee).setScale(2, 4);
			} catch (Exception e) {
			}
		}
		if (shippingFeeD == null || shippingFeeD.doubleValue() < 0) {
			request.setAttribute("alert", "运费不能少于0！");
			request.setAttribute("bean", bean);
			return "error";
		}
		bean.setShippingFee(shippingFeeD);

		// 捐赠金额
		BigDecimal donationFeeD = null;
		String donationFee = request.getParameter("donationFee");
		if (donationFee != null && !"".equals(donationFee)) {
			try {
				donationFeeD = new BigDecimal(donationFee).setScale(2, 4);
			} catch (Exception e) {
			}
		}
		if (donationFeeD == null || donationFeeD.doubleValue() < 0) {
			request.setAttribute("alert", "捐赠金额不能少于0！");
			request.setAttribute("bean", bean);
			return "orderEdit";
		}
		bean.setDonationFee(donationFeeD);

		// 捐赠状态
		String orderStatus = request.getParameter("orderStatus");
		bean.setOrderStatus(orderStatus);

		// 提交时间
		String orderTime = request.getParameter("orderTime");
		if (orderTime != null && !"".equals(orderTime)) {
			bean.setOrderTime(DateUtil.toDate(orderTime, "yyyy-MM-dd HH:mm"));
		} else {
			bean.setOrderTime(null);
		}

		// 支付方式
		String orderType = request.getParameter("orderType");
		if (orderType != null && !"".equals(orderType)) {
			bean.setOrderType(orderType);
		} else {
			bean.setOrderType(null);
		}

		// 付款时间
		String orderOkTime = request.getParameter("orderOkTime");
		if (orderOkTime != null && !"".equals(orderOkTime)) {
			bean.setOrderOkTime(DateUtil
					.toDate(orderOkTime, "yyyy-MM-dd HH:mm"));
		} else {
			bean.setOrderOkTime(null);
		}

		// 姓名
		String person = request.getParameter("person");
		if (person == null || "".equals(person)) {
			person = "匿名";
		}
		bean.setBuyerName(person);
		// 性别
		String personSex = request.getParameter("personSex");
		bean.setBuyerSex(personSex);
		// 匿名
		String niMing = request.getParameter("niMing");
		bean.setNiMing(niMing);
		// 手机
		String personPhone = request.getParameter("personPhone");
		bean.setBuyerPhone(personPhone);
		// 电话
		String personTel = request.getParameter("personTel");
		bean.setBuyerMobile(personTel);
		// 邮箱
		String personEmail = request.getParameter("personEmail");
		bean.setBuyerEmail(personEmail);
		// 邮编
		String zip = request.getParameter("zip");
		bean.setBuyerZipcode(zip);
		// 省
		String sheng = request.getParameter("sheng");
		bean.setBuyerSheng(sheng);
		// 市
		String shi = request.getParameter("shi");
		bean.setBuyerShi(shi);
		// 区/县
		String qu = request.getParameter("qu");
		bean.setBuyerQu(qu);
		// 详细地址
		String address = request.getParameter("address");
		bean.setBuyerAddress(address);

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

		try {
			bean = (TbCommodityOrder) this.getCommonBo().saveOrUpdate(bean);
			if (bean.getOrderNo() == null || "".equals(bean.getOrderNo())) {
				// 单号
				String orderNum = bean.getOrderId().toString();
				int orderLen = orderNum.length();
				for (int i = 0; i < 17 - orderLen; i++) {
					orderNum = "0" + orderNum;
				}
				orderNum = DateUtil.format(dateNow, "yyyyMMdd") + "001"
						+ orderNum;
				bean.setOrderNo(orderNum);
				bean = (TbCommodityOrder) this.getCommonBo().update(bean);
				request.setAttribute("bean", bean);
			}
			// 返回
			return "success";
		} catch (Exception e) {
		}

		// 返回状态：保存失败
		request.setAttribute("alert", "保存失败！");
		request.setAttribute("bean", bean);
		// 返回
		return "error";
	}

	// 上传Excel导入进度参数名称
	private String IMPORTCOUNT = "ZCPROJEDITORDEREXCELCOUNT";
	private String IMPORTINDEX = "ZCPROJEDITORDEREXCELINDEX";

	// 上传Excel导入进度
	public String process() throws Exception {
		HttpServletRequest request = this.getRequest();
		HttpServletResponse response = this.getResponse();
		HttpSession session = request.getSession();
		// 导入进度时间戳
		String random = request.getParameter("random");
		if (random == null) {
			random = "";
		}
		// 导入进度参数
		Object countObj = session.getAttribute(IMPORTCOUNT + random);
		String count = "";
		if (countObj != null) {
			count = countObj.toString();
		}
		Object indexObj = session.getAttribute(IMPORTINDEX + random);
		String index = "";
		if (indexObj != null) {
			index = indexObj.toString();
		}
		// 传值
		this.sendResponse(response, "{\"count\":" + count + ",\"index\":"
				+ index + "}");
		// 返回
		return null;
	}

	// 导入页面
	public String toImport() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 项目
		TbCommodity beanComm = null;
		// 项目ID
		String commId = request.getParameter("commId");
		if (commId != null && !"".equals(commId)) {
			try {
				beanComm = (TbCommodity) this.getCommonBo().get(
						TbCommodity.class, new Long(commId));
			} catch (Exception e) {
			}
		}
		if (beanComm == null) {
			request.setAttribute("alert", "众筹项目不存在，请关闭并重新打开导入页面！");
			return "import";
		}
		request.setAttribute("beanComm", beanComm);
		return "import";
	}

	// 批量导入
	public String doImport() throws Exception {
		HttpServletRequest request = this.getRequest();
		MultiRequestUtil multiRequest = new MultiRequestUtil(request);
		HttpSession session = request.getSession();

		TbCommodity beanComm = null;
		String commId = multiRequest.getParameter("commId");
		if (commId != null && !"".equals(commId)) {
			try {
				beanComm = (TbCommodity) this.getCommonBo().get(
						TbCommodity.class, new Long(commId));
			} catch (Exception e) {
			}
		}
		if (beanComm == null) {
			request.setAttribute("alert", "商品不存在，请关闭并重新打开导入页面！");
			return "import";
		}
		request.setAttribute("beanComm", beanComm);

		// 系统当前时间
		Date dateNow = this.getCommonBo().getSysDate();
		// 导入进度时间戳
		String random = multiRequest.getParameter("random");
		if (random == null) {
			random = "";
		}
		// 获取导入文件
		FileItem item = multiRequest.getFile("file");
		if (item == null || item.getSize() == 0) {
			request.setAttribute("alert", "请选择需要导入的Excel文件！");
			return "import";
		}

		// 读取导入文件
		InputStream fis = null;
		try {
			fis = item.getInputStream();
			POIFSFileSystem fs = new POIFSFileSystem(fis);
			HSSFWorkbook workbook = new HSSFWorkbook(fs);
			HSSFSheet sheet = workbook.getSheetAt(0);
			// 判断是否有数据
			int rowCount = sheet.getLastRowNum();
			if (rowCount < 1) {
				request.setAttribute("alert", "导入模板错误，或导入文件中没有相关导入数据！");
				return "import";
			}
			// 头
			HSSFRow headRow = sheet.getRow(0);
			int colCount = headRow.getLastCellNum();
			List headList = this.getList();
			for (int i = 0; i <= colCount; i++) {
				HSSFCell headCell = headRow.getCell((short) i);
				String headCellValue = "";
				if (headCell != null) {
					headCellValue = headCell.toString().trim();
				}
				if (headCellValue.contains("订单号")) {
					headList.add("订单号");
				} else if (headCellValue.contains("订单总金额")) {
					headList.add("订单总金额");
				} else if (headCellValue.contains("商品名称")) {
					headList.add("商品名称");
				} else if (headCellValue.endsWith("商品型号")) {
					headList.add("商品型号");
				} else if (headCellValue.endsWith("商品单价")) {
					headList.add("商品单价");
				} else if (headCellValue.endsWith("商品数量")) {
					headList.add("商品数量");
				} else if (headCellValue.contains("取货方式")) {
					headList.add("取货方式");
				} else if (headCellValue.contains("运费")) {
					headList.add("运费");
				} else if (headCellValue.contains("捐赠金额")) {
					headList.add("捐赠金额");
				} else if (headCellValue.contains("支付方式")) {
					headList.add("支付方式");
				} else if (headCellValue.contains("支付状态")) {
					headList.add("支付状态");
				} else if (headCellValue.contains("提交时间")) {
					headList.add("提交时间");
				} else if (headCellValue.contains("付款时间")) {
					headList.add("付款时间");
				} else if (headCellValue.endsWith("买家姓名")) {
					headList.add("买家姓名");
				} else if (headCellValue.contains("是否匿名")) {
					headList.add("是否匿名");
				} else if (headCellValue.contains("性别")) {
					headList.add("性别");
				} else if (headCellValue.contains("手机")) {
					headList.add("手机");
				} else if (headCellValue.contains("电话")) {
					headList.add("电话");
				} else if (headCellValue.contains("邮箱")) {
					headList.add("邮箱");
				} else if (headCellValue.contains("邮编")) {
					headList.add("邮编");
				} else if (headCellValue.contains("省")) {
					headList.add("省");
				} else if (headCellValue.contains("市")) {
					headList.add("市");
				} else if (headCellValue.contains("区/县")) {
					headList.add("区/县");
				} else if (headCellValue.contains("详细地址")) {
					headList.add("详细地址");
				} else if (headCellValue.contains("是否校友")) {
					headList.add("是否校友");
				} else if (headCellValue.contains("入学年")) {
					headList.add("入学年");
				} else if (headCellValue.contains("离校年")) {
					headList.add("离校年");
				} else if (headCellValue.contains("院系")) {
					headList.add("院系");
				} else if (headCellValue.contains("专业")) {
					headList.add("专业");
				} else if (headCellValue.contains("班级")) {
					headList.add("班级");
				} else if (headCellValue.contains("学号")) {
					headList.add("学号");
				} else if (headCellValue.contains("学历")) {
					headList.add("学历");
				} else if (headCellValue.contains("公司")) {
					headList.add("公司");
				} else if (headCellValue.contains("部门")) {
					headList.add("部门");
				} else if (headCellValue.contains("职位")) {
					headList.add("职位");
				} else if (headCellValue.contains("其他")) {
					headList.add("其他信息");
				} else if (headCellValue.contains("标记")) {
					headList.add("标记信息");
				} else if (headCellValue.contains("回执")) {
					headList.add("回执信息");
				} else if (headCellValue.contains("备注")) {
					headList.add("备注");
				} else {
					headList.add("");
				}
			}
			if (!headList.contains("商品型号") || !headList.contains("商品数量")
					|| !headList.contains("订单总金额")
					|| !headList.contains("买家姓名")) {
				request.setAttribute("alert", "导入模板格式错误，请重新下载导入模板！");
				return "import";
			}
			// 设置导入进度参数
			session.setAttribute(IMPORTCOUNT + random, rowCount);
			// 遍历Excel导入数据
			int orderNoIndex = headList.indexOf("订单号");
			int orderFeeIndex = headList.indexOf("订单总金额");
			int commNameIndex = headList.indexOf("商品名称");
			int commDetailIndex = headList.indexOf("商品型号");
			int commCostFeeIndex = headList.indexOf("商品单价");
			int commNumIndex = headList.indexOf("商品数量");
			int shippingTypeIndex = headList.indexOf("取货方式");
			int shippingFeeIndex = headList.indexOf("运费");
			int donationFeeIndex = headList.indexOf("捐赠金额");
			int orderTypeIndex = headList.indexOf("支付方式");
			int orderStatusIndex = headList.indexOf("支付状态");
			int orderTimeIndex = headList.indexOf("提交时间");
			int orderOkTimeIndex = headList.indexOf("付款时间");
			int personIndex = headList.indexOf("买家");
			int niMingIndex = headList.indexOf("是否匿名");
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
			for (int i = 1; i <= rowCount; i++) {
				HSSFRow row = sheet.getRow(i);
				// 设置导入进度参数
				session.setAttribute(IMPORTINDEX + random, i);
				try {
					// 项目捐赠
					TbCommodityOrder bean = null;
					// 订单号
					if (orderNoIndex != -1) {
						HSSFCell orderNumCell = row
								.getCell((short) orderNoIndex);
						if (orderNumCell != null) {
							String orderNo = formatStr(orderNumCell.toString()
									.trim());
							if (orderNo != null && !"".equals(orderNo)) {
								try {
									String hql = "from TbCommodityOrder a where a.commId=? and a.orderNo=?";
									List pars = this.getList();
									pars.add(beanComm.getCommId());
									pars.add(orderNo);
									bean = (TbCommodityOrder) this
											.getCommonBo().getHQLUnique(hql,
													pars);
								} catch (Exception e) {
								}
								if (bean == null) {
									errorcount++;
									errorList.add(new String[] {
											String.valueOf(i),
											"导入失败，原因：该项目中不存在订单号为【" + orderNo
													+ "】的捐赠记录，未做新增、更新处理！" });
									continue;
								}
							}
						}
					}

					if (bean == null) {
						bean = new TbCommodityOrder();
						// 所属捐赠项目
						bean.setCommId(beanComm.getCommId());
						bean.setCommName(beanComm.getCommName());
					}

					// 商品型号
					TbCommodityDetail beanCommDetail = null;
					String commDetail = null;
					HSSFCell optionNameCell = row
							.getCell((short) commDetailIndex);
					if (optionNameCell != null) {
						commDetail = formatStr(optionNameCell.toString().trim());
						if (commDetail != null && !"".equals(commDetail)) {
							try {
								String hql = "from TbCommodityDetail a where a.commId=? and a.detailName=?";
								List pars = this.getList();
								pars.add(beanComm.getCommId());
								pars.add(commDetail);
								beanCommDetail = (TbCommodityDetail) this
										.getCommonBo().getHQLUnique(hql, pars);
							} catch (Exception e) {
							}
						}
					}

					// 商品数量
					Long optionCount = null;
					HSSFCell optionCountCell = row
							.getCell((short) commNumIndex);
					if (optionCountCell != null) {
						try {
							String optionCountStr = formatStr(optionCountCell
									.toString().trim());
							if (optionCountStr != null
									&& !"".equals(optionCountStr)) {
								optionCount = Long.parseLong(optionCountStr
										.split("\\.")[0]);
							}
						} catch (Exception e) {
						}
					}

					if (beanCommDetail != null) {
						bean.setCommDetailId(beanCommDetail.getDetailId());
						bean.setCommDetailName(beanCommDetail.getDetailName());
						if (optionCount != null && optionCount.intValue() > 0) {
							bean.setCommNum(optionCount);
						} else {
							errorcount++;
							errorList.add(new String[] { String.valueOf(i),
									"错误：产品数量字段为空，未做新增、更新处理！" });
							continue;
						}
					} else if (commDetail != null && !"".equals(commDetail)) {
						bean.setCommDetailName(commDetail);
						if (optionCount != null && optionCount.intValue() > 0) {
							bean.setCommNum(optionCount);

							errorcount++;
							errorList.add(new String[] { String.valueOf(i),
									"警告：未能成功匹配产品型号，已做新增、更新处理！" });
						} else {
							errorcount++;
							errorList.add(new String[] { String.valueOf(i),
									"错误：产品数量字段为空，未做新增、更新处理！" });
							continue;
						}
					} else {
						errorcount++;
						errorList.add(new String[] { String.valueOf(i),
								"错误：产品型号字段为空，未做新增、更新处理！" });
						continue;
					}

					// 商品单价
					if (commCostFeeIndex != -1) {
						BigDecimal orderAmoutViewD = null;
						HSSFCell amountViewCell = row
								.getCell((short) commCostFeeIndex);
						if (amountViewCell != null) {
							String amountView = formatStr(amountViewCell
									.toString().trim());
							if (amountView != null && !"".equals(amountView)) {
								try {
									orderAmoutViewD = new BigDecimal(amountView)
											.setScale(2, 4);
								} catch (Exception e) {
								}
							}
							bean.setCommCostfee(orderAmoutViewD);
						}
					}

					// 取货方式
					if (shippingTypeIndex != -1) {
						HSSFCell shippingTypeCell = row
								.getCell((short) shippingTypeIndex);
						if (shippingTypeCell != null) {
							String shippingType = formatStr(shippingTypeCell
									.toString().trim());
							if (shippingType != null
									&& ("买家自提".equals(shippingType) || "1"
											.equals(shippingType))) {
								bean.setShippingType("1");
							} else {
								bean.setShippingType("0");
							}
						}
					}

					// 运费
					if (shippingFeeIndex != -1) {
						BigDecimal shippingFeeD = null;
						HSSFCell shippingFeeIndexCell = row
								.getCell((short) shippingFeeIndex);
						if (shippingFeeIndexCell != null) {
							String shippingFee = formatStr(shippingFeeIndexCell
									.toString().trim());
							if (shippingFee != null && !"".equals(shippingFee)) {
								try {
									shippingFeeD = new BigDecimal(shippingFee)
											.setScale(2, 4);
								} catch (Exception e) {
								}
							}
							bean.setShippingFee(shippingFeeD);
						}
					}

					// 捐赠金额
					if (donationFeeIndex != -1) {
						BigDecimal donationFeeD = null;
						HSSFCell donationFeeIndexCell = row
								.getCell((short) donationFeeIndex);
						if (donationFeeIndexCell != null) {
							String donationFee = formatStr(donationFeeIndexCell
									.toString().trim());
							if (donationFee != null && !"".equals(donationFee)) {
								try {
									donationFeeD = new BigDecimal(donationFee)
											.setScale(2, 4);
								} catch (Exception e) {
								}
							}
							bean.setDonationFee(donationFeeD);
						}
					}
					// 支付方式
					if (orderTypeIndex != -1) {
						HSSFCell orderTypeCell = row
								.getCell((short) orderTypeIndex);
						if (orderTypeCell != null) {
							String orderType = formatStr(orderTypeCell
									.toString().trim());
							bean.setOrderType(orderType);
						}
					}
					// 支付状态
					if (orderStatusIndex != -1) {
						HSSFCell orderStatusCell = row
								.getCell((short) orderStatusIndex);
						if (orderStatusCell != null) {
							String orderStatus = formatStr(orderStatusCell
									.toString().trim());
							if (orderStatus != null
									&& ("已付款".equals(orderStatus) || "1"
											.equals(orderStatus))) {
								bean.setOrderStatus("1");
							} else {
								bean.setOrderStatus("0");
							}
						}
					}
					// 提交时间
					if (orderTimeIndex != -1) {
						HSSFCell orderTimeCell = row
								.getCell((short) orderTimeIndex);
						if (orderTimeCell != null) {
							Date orderTime = getExcelDate(orderTimeCell);
							bean.setOrderTime(orderTime);
						}
					}
					// 付款时间
					if (orderOkTimeIndex != -1) {
						HSSFCell orderOkTimeCell = row
								.getCell((short) orderOkTimeIndex);
						if (orderOkTimeCell != null) {
							Date orderOkTime = getExcelDate(orderOkTimeCell);
							bean.setOrderOkTime(orderOkTime);
						}
					}

					// 捐赠人

					// 姓名
					if (personIndex != -1) {
						HSSFCell personCell = row.getCell((short) personIndex);
						if (personCell != null) {
							String person = formatStr(personCell.toString()
									.trim());
							if (person == null || "".equals(person)) {
								person = "匿名";
							}
							bean.setBuyerName(person);
						}
					}
					// 性别
					if (personSexIndex != -1) {
						HSSFCell personSexCell = row
								.getCell((short) personSexIndex);
						if (personSexCell != null) {
							String personSex = formatStr(personSexCell
									.toString().trim());
							if (personSex != null) {
								if ("男".equals(personSex)
										|| "男性".equals(personSex)
										|| "1".equals(personSex)) {
									bean.setBuyerSex("1");
								} else if ("女".equals(personSex)
										|| "女性".equals(personSex)
										|| "2".equals(personSex)) {
									bean.setBuyerSex("2");
								}
							}
						}
					}
					// 匿名
					if (niMingIndex != -1) {
						HSSFCell niMingCell = row.getCell((short) niMingIndex);
						if (niMingCell != null) {
							String niMing = formatStr(niMingCell.toString()
									.trim());
							if (niMing != null
									&& ("是".equals(niMing)
											|| "匿名".equals(niMing)
											|| "匿名捐赠".equals(niMing) || "1"
												.equals(niMing))) {
								bean.setNiMing("1");
							} else {
								bean.setNiMing("0");
							}
						}
					}
					// 手机
					if (personPhoneIndex != -1) {
						HSSFCell personPhoneCell = row
								.getCell((short) personPhoneIndex);
						if (personPhoneCell != null) {
							String personPhone = formatStr(personPhoneCell
									.toString().trim());
							bean.setBuyerPhone(personPhone);
						}
					}
					// 电话
					if (personTelIndex != -1) {
						HSSFCell personTelCell = row
								.getCell((short) personTelIndex);
						if (personTelCell != null) {
							String personTel = formatStr(personTelCell
									.toString().trim());
							bean.setBuyerMobile(personTel);
						}
					}
					// 邮箱
					if (personEmailIndex != -1) {
						HSSFCell personEmailCell = row
								.getCell((short) personEmailIndex);
						if (personEmailCell != null) {
							String personEmail = formatStr(personEmailCell
									.toString().trim());
							bean.setBuyerEmail(personEmail);
						}
					}
					// 邮编
					if (zipIndex != -1) {
						HSSFCell zipCell = row.getCell((short) zipIndex);
						if (zipCell != null) {
							String zip = formatStr(zipCell.toString().trim());
							bean.setBuyerZipcode(zip);
						}
					}
					// 省
					if (shengIndex != -1) {
						HSSFCell shengCell = row.getCell((short) shengIndex);
						if (shengCell != null) {
							String sheng = formatStr(shengCell.toString()
									.trim());
							bean.setBuyerSheng(sheng);
						}
					}
					// 市
					if (shiIndex != -1) {
						HSSFCell shiCell = row.getCell((short) shiIndex);
						if (shiCell != null) {
							String shi = formatStr(shiCell.toString().trim());
							bean.setBuyerShi(shi);
						}
					}
					// 区/县
					if (quIndex != -1) {
						HSSFCell quCell = row.getCell((short) quIndex);
						if (quCell != null) {
							String qu = formatStr(quCell.toString().trim());
							bean.setBuyerQu(qu);
						}
					}
					// 详细地址
					if (addressIndex != -1) {
						HSSFCell addressCell = row
								.getCell((short) addressIndex);
						if (addressCell != null) {
							String address = formatStr(addressCell.toString()
									.trim());
							bean.setBuyerAddress(address);
						}
					}
					// 校友资料
					// 是否校友
					if (alumniFlagIndex != -1) {
						HSSFCell alumniFlagCell = row
								.getCell((short) alumniFlagIndex);
						if (alumniFlagCell != null) {
							String alumniFlag = formatStr(alumniFlagCell
									.toString().trim());
							if (alumniFlag != null
									&& ("校友".equals(alumniFlag)
											|| "是".equals(alumniFlag) || "1"
												.equals(alumniFlag))) {
								bean.setAlumniFlag("1");
							} else {
								bean.setAlumniFlag("0");
							}
						}
					}
					// 入学年
					if (academyBegIndex != -1) {
						HSSFCell academyBegCell = row
								.getCell((short) academyBegIndex);
						if (academyBegCell != null) {
							String academyBeg = formatStr(academyBegCell
									.toString().trim());
							bean.setStudyYearin(academyBeg);
						}
					}
					// 离校年
					if (academyEndIndex != -1) {
						HSSFCell academyEndCell = row
								.getCell((short) academyEndIndex);
						if (academyEndCell != null) {
							String academyEnd = formatStr(academyEndCell
									.toString().trim());
							bean.setStudyYearover(academyEnd);
						}
					}
					// 院系
					if (academyIndex != -1) {
						HSSFCell academyCell = row
								.getCell((short) academyIndex);
						if (academyCell != null) {
							String academy = formatStr(academyCell.toString()
									.trim());
							bean.setStudyAcademy(academy);
						}
					}
					// 专业
					if (majorIndex != -1) {
						HSSFCell majorCell = row.getCell((short) majorIndex);
						if (majorCell != null) {
							String major = formatStr(majorCell.toString()
									.trim());
							bean.setStudyMajor(major);
						}
					}
					// 班级
					if (clazzIndex != -1) {
						HSSFCell clazzCell = row.getCell((short) clazzIndex);
						if (clazzCell != null) {
							String clazz = formatStr(clazzCell.toString()
									.trim());
							bean.setStudyClass(clazz);
						}
					}
					// 学号
					if (studynoIndex != -1) {
						HSSFCell studynoCell = row
								.getCell((short) studynoIndex);
						if (studynoCell != null) {
							String studyno = formatStr(studynoCell.toString()
									.trim());
							bean.setStudyNum(studyno);
						}
					}
					// 学历
					if (academyDegreeIndex != -1) {
						HSSFCell academyDegreeCell = row
								.getCell((short) academyDegreeIndex);
						if (academyDegreeCell != null) {
							String academyDegree = formatStr(academyDegreeCell
									.toString().trim());
							bean.setStudyDegree(academyDegree);
						}
					}
					// 工作资料
					// 公司
					if (companyIndex != -1) {
						HSSFCell companyCell = row
								.getCell((short) companyIndex);
						if (companyCell != null) {
							String company = formatStr(companyCell.toString()
									.trim());
							bean.setWorkCompany(company);
						}
					}
					// 部门
					if (departmentIndex != -1) {
						HSSFCell departmentCell = row
								.getCell((short) departmentIndex);
						if (departmentCell != null) {
							String department = formatStr(departmentCell
									.toString().trim());
							bean.setWorkDepart(department);
						}
					}
					// 职位
					if (dutyIndex != -1) {
						HSSFCell dutyCell = row.getCell((short) dutyIndex);
						if (dutyCell != null) {
							String duty = formatStr(dutyCell.toString().trim());
							bean.setWorkDuty(duty);
						}
					}
					// 其他信息
					if (orderMemoIndex != -1) {
						HSSFCell orderMemoCell = row
								.getCell((short) orderMemoIndex);
						if (orderMemoCell != null) {
							String orderMemo = formatStr(orderMemoCell
									.toString().trim());
							bean.setOrderMemo(orderMemo);
						}
					}
					// 标记信息
					if (markIndex != -1) {
						HSSFCell markCell = row.getCell((short) markIndex);
						if (markCell != null) {
							String mark = formatStr(markCell.toString().trim());
							bean.setMark(mark);
						}
					}
					// 回执信息
					if (receiptIndex != -1) {
						HSSFCell receiptCell = row
								.getCell((short) receiptIndex);
						if (receiptCell != null) {
							String receipt = formatStr(receiptCell.toString()
									.trim());
							bean.setReceipt(receipt);
						}
					}
					// 备注
					if (memoIndex != -1) {
						HSSFCell memoCell = row.getCell((short) memoIndex);
						if (memoCell != null) {
							String memo = formatStr(memoCell.toString().trim());
							bean.setMemo(memo);
						}
					}
					// 保存
					bean = (TbCommodityOrder) this.getCommonBo().saveOrUpdate(
							bean);
					if (bean.getOrderNo() == null
							|| "".equals(bean.getOrderNo())) {
						// 单号
						String orderNum = bean.getOrderId().toString();
						int orderLen = orderNum.length();
						for (int j = 0; j < 17 - orderLen; j++) {
							orderNum = "0" + orderNum;
						}
						orderNum = DateUtil.format(dateNow, "yyyyMMdd") + "001"
								+ orderNum;
						bean.setOrderNo(orderNum);
						bean = (TbCommodityOrder) this.getCommonBo().update(
								bean);
						request.setAttribute("bean", bean);
					}
					successcount++;
				} catch (Exception e) {
					errorcount++;
					errorList.add(new String[] { String.valueOf(i),
							"导入失败，原因：该条捐赠信息导入时，发生异常，异常描述：" + e.getMessage() });
				}
			}
			request.setAttribute("successcount", successcount);
			request.setAttribute("errorcount", errorcount);
			request.setAttribute("errorList", errorList);
			return "import";
		} catch (Exception e) {
			request.setAttribute("alert", "警告，导入文件时出现异常，异常描述：" + e.getMessage());
			return "import";
		} finally {
			if (fis != null) {
				fis.close();
			}
			// 移除导入进度参数
			session.removeAttribute(IMPORTINDEX + random);
			session.removeAttribute(IMPORTCOUNT + random);
		}
	}

	// 批量导出
	public String doExport() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 导出结果
		String ajax = "{\"result\":\"error\"}";
		// 1、下载导入模板，其他为导出
		String type = request.getParameter("type");
		// 需要导出的信息系统集合
		List beantList = null;
		// 获取需要导出的捐赠信息集合
		if (type == null || !"1".equals(type)) {

			// 构造查询
			String hql = "from TbCommodityOrder a where 1=1";
			List pars = this.getList();

			// 商品编号
			String commId = request.getParameter("commId");
			if (commId != null && !"".equals(commId)) {
				hql += " and a.commId = ?";
				pars.add(new Long(commId));
			} else {
				// 商品名称
				String commName = request.getParameter("commName");
				if (commName != null && !"".equals(commName)) {
					hql += " and a.commName like ?";
					pars.add("%" + commName + "%");
				}
			}
			// 商品型号
			String detialName = request.getParameter("detailName");
			if (detialName != null && !"".equals(detialName)) {
				hql += " and a.commDetailName like ?";
				pars.add("%" + detialName + "%");
			}

			// 提交起始时间
			Date orderTimeBegD = null;
			String orderTimeBeg = request.getParameter("orderTimeBeg");
			if (orderTimeBeg != null && !"".equals(orderTimeBeg)) {
				orderTimeBegD = DateUtil.toDate(orderTimeBeg, "yyyy-MM-dd HH:mm");
			}
			if (orderTimeBegD != null) {
				hql += " and a.orderTime >= ?";
				pars.add(orderTimeBegD);
			}

			// 提交截止时间
			Date orderTimeEndD = null;
			String orderTimeEnd = request.getParameter("orderTimeEnd");
			if (orderTimeEnd != null && !"".equals(orderTimeEnd)) {
				orderTimeEndD = DateUtil.toDate(orderTimeEnd, "yyyy-MM-dd HH:mm");
			}
			if (orderTimeEndD != null) {
				hql += " and a.orderTime <= ?";
				pars.add(orderTimeEndD);
			}
			// 付款起始时间
			Date orderOkTimeBegD = null;
			String orderOkTimeBeg = request.getParameter("orderOkTimeBeg");
			if (orderOkTimeBeg != null && !"".equals(orderOkTimeBeg)) {
				orderOkTimeBegD = DateUtil.toDate(orderOkTimeBeg,
						"yyyy-MM-dd HH:mm");
			}
			if (orderOkTimeBegD != null) {
				hql += " and a.orderOkTime >= ?";
				pars.add(orderOkTimeBegD);
			}
			// 付款截止时间
			Date orderOkTimeEndD = null;
			String orderOkTimeEnd = request.getParameter("orderOkTimeEnd");
			if (orderOkTimeEnd != null && !"".equals(orderOkTimeEnd)) {
				orderOkTimeEndD = DateUtil.toDate(orderOkTimeEnd,
						"yyyy-MM-dd HH:mm");
			}
			if (orderOkTimeEndD != null) {
				hql += " and a.orderOkTime <= ?";
				pars.add(orderOkTimeEndD);
			}

			// 买家姓名
			String buyerName = request.getParameter("buyerName");
			if (buyerName != null && !"".equals(buyerName)) {
				hql += " and a.buyerName like ?";
				pars.add("%" + buyerName + "%");
			}
			
			// 商品订单号
			String orderNo = request.getParameter("orderNo");
			if (orderNo != null && !"".equals(orderNo)) {
				hql += " and a.orderNo like ?";
				pars.add("%" + orderNo + "%");
			}
			// 商品订单支付方式
			String orderType = request.getParameter("orderType");
			if (orderType != null && !"".equals(orderType)) {
				hql += " and a.orderType like ?";
				pars.add("%" + orderType + "%");
			}
			// 商品订单支付状态
			String orderStatus = request.getParameter("orderStatus");
			if (orderStatus != null && "1".equals(orderStatus)) {
				hql += " and a.orderStatus = ?";
				pars.add("1");
			} else if (orderStatus != null && !"".equals(orderStatus)) {
				hql += " and a.orderStatus <> ?";
				pars.add("1");
			}
			// 标记
			String markFlag = request.getParameter("markFlag");
			if (markFlag != null && "1".equals(markFlag)) {
				hql += " and a.mark is not null";
				String mark = request.getParameter("mark");
				if (mark != null && !"".equals(mark)) {
					hql += " and a.mark like ?";
					pars.add("%" + mark + "%");
				}
			} else if (markFlag != null && !"".equals(markFlag)) {
				hql += " and a.mark is null";
			}
			hql += " order by a.orderTime desc";

			System.out.println("hql:" + hql);
			// 执行查询
			try {
				beantList = this.getCommonBo().findHQL(hql, pars);
			} catch (Exception e) {
			}

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
			cellLeftTopStyle
					.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
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
			headList.add("订单号");
			headList.add("订单总金额");
			headList.add("商品名称");
			headList.add("商品型号");
			headList.add("商品单价");
			headList.add("商品数量");
			headList.add("取货方式");
			headList.add("运费");
			headList.add("捐赠金额");
			headList.add("支付方式");
			headList.add("支付状态");
			headList.add("提交时间");
			headList.add("付款时间");
			headList.add("买家姓名");
			headList.add("是否匿名");
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
			headList.add("买家评价");
			headList.add("标记信息");
			headList.add("回执信息");
			headList.add("备注");

			HSSFRow row0 = sheet.createRow(0);
			row0.setHeight((short) 600);
			// 第一行
			for (int i = 0; i < headList.size(); i++) {
				HSSFCell row0cell = row0.createCell((short) i);
				row0cell.setCellStyle(cellTopStyle);
				row0cell.setCellValue(new HSSFRichTextString(headList.get(i)
						.toString()));
			}
			// 数据行
			if (beantList != null && !beantList.isEmpty()) {
				for (int i = 1; i < beantList.size() + 1; i++) {
					HSSFRow row = sheet.createRow(i);
					row.setHeight((short) 600);
					TbCommodityOrder bean = (TbCommodityOrder) beantList
							.get(i - 1);
					// 列号
					int k = 0;
					// 列对象
					HSSFCell cell = null;
					// 订单号
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getOrderNo() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getOrderNo()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 订单总金额
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getOrderFee() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getOrderFee().toString()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 商品名称
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getCommName() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getCommName()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 商品型号
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getCommDetailName() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getCommDetailName()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 商品单价
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getCommCostfee() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getCommCostfee().toString()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 商品数量
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getCommNum() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getCommNum().toString()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 取货方式
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getShippingType() != null
							&& "1".equals(bean.getShippingType())) {
						cell.setCellValue(new HSSFRichTextString("买家自提"));
					} else {
						cell.setCellValue(new HSSFRichTextString("物流运输"));
					}
					// 运费
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getShippingFee() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getShippingFee().toString()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 捐赠
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getDonationFee() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getDonationFee().toString()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 支付方式
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getOrderType() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getOrderType().toString()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 支付状态
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getOrderStatus() != null
							&& "1".equals(bean.getOrderStatus())) {
						cell.setCellValue(new HSSFRichTextString("已付款"));
					} else {
						cell.setCellValue(new HSSFRichTextString("待付款"));
					}
					// 提交时间
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getOrderTime() != null) {
						SimpleDateFormat format = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm");
						cell.setCellValue(new HSSFRichTextString(format
								.format(bean.getOrderTime())));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 付款时间
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getOrderOkTime() != null) {
						SimpleDateFormat format = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm");
						cell.setCellValue(new HSSFRichTextString(format
								.format(bean.getOrderOkTime())));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}

					// 买家
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getBuyerName() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getBuyerName()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 是否匿名
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getNiMing() != null
							&& "1".equals(bean.getNiMing())) {
						cell.setCellValue(new HSSFRichTextString("是"));
					} else {
						cell.setCellValue(new HSSFRichTextString("否"));
					}
					// 性别
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getBuyerSex() != null
							&& "1".equals(bean.getBuyerSex())) {
						cell.setCellValue(new HSSFRichTextString("男"));
					} else if (bean.getBuyerSex() != null
							&& "2".equals(bean.getBuyerSex())) {
						cell.setCellValue(new HSSFRichTextString("女"));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 手机
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getBuyerPhone() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getBuyerPhone()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 电话
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getBuyerMobile() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getBuyerMobile()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 邮箱
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getBuyerEmail() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getBuyerEmail()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 邮编
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getBuyerZipcode() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getBuyerZipcode()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 省
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getBuyerSheng() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getBuyerSheng()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 市
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getBuyerShi() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getBuyerShi()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 区/县
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getBuyerQu() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getBuyerQu()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 详细地址
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getBuyerAddress() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getBuyerAddress()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 是否校友
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getAlumniFlag() != null
							&& "1".equals(bean.getAlumniFlag())) {
						cell.setCellValue(new HSSFRichTextString("是"));
					} else {
						cell.setCellValue(new HSSFRichTextString("否"));
					}
					// 入学年
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getStudyYearin() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getStudyYearin()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 离校年
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getStudyYearover() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getStudyYearover()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 院系
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getStudyAcademy() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getStudyAcademy()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 专业
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getStudyMajor() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getStudyMajor()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 班级
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getStudyClass() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getStudyClass()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 学号
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getStudyNum() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getStudyNum()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 学历
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getStudyDegree() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getStudyDegree()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 公司
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getWorkCompany() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getWorkCompany()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 部门
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getWorkDepart() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getWorkDepart()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 职位
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getWorkDuty() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getWorkDuty()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 其他信息
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getOrderMemo() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getOrderMemo()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 买家评价
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getStarNum() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getStarNum().toString()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 标记信息
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getMark() != null) {
						cell.setCellValue(new HSSFRichTextString(bean.getMark()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 回执信息
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getReceipt() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getReceipt()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 备注
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getMemo() != null) {
						cell.setCellValue(new HSSFRichTextString(bean.getMemo()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
				}
			}
			// 保存文件
			String fielCurName = "捐赠信息导出"
					+ DateUtil.format(new Date(), "yyyyMMddHHmmss") + ".xls";
			String fileNewName = "捐赠信息导出" + ".xls";
			String dirPath = ServletActionContext.getServletContext()
					.getRealPath("download/");
			File dirFile = new File(dirPath);
			if (!dirFile.exists()) {
				dirFile.mkdir();
			}
			File file = new File(dirPath, fielCurName);
			FileOutputStream fo = new FileOutputStream(file);
			wb.write(fo);
			fo.close();
			ajax = "{\"result\":\"success\",\"name\":\"" + fileNewName
					+ "\",\"path\":\"download/" + fielCurName + "\"}";
		} catch (Exception ex) {
			System.out.println(execute().toString());
		}
		// 返回
		this.sendResponse(this.getResponse(), ajax);
		return null;
	}

	// 格式化文本
	private String formatStr(String str) {
		if (str != null && !"".equals(str)) {
			try {
				// 科学计数法
				if (str.matches("[\\+\\-]?[\\d]+([\\.][\\d]*)?[Ee][+-]?[\\d]+")) {
					NumberFormat format = NumberFormat.getInstance();
					format.setGroupingUsed(false);
					str = format.format(Double.parseDouble(str));
				}
			} catch (Exception e) {
			}
		}
		return str;
	}

	// 日期处理
	public Date getExcelDate(HSSFCell cell) throws Exception {
		String ret = "";
		try {
			if (cell != null) {
				if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					if (HSSFDateUtil.isCellDateFormatted(cell)) {// 判断单元格是否属于日期格式
						Date theDate = cell.getDateCellValue();
						SimpleDateFormat format = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm");
						ret = format.format(theDate);
						return DateUtil.toDate(ret, "yyyy-MM-dd HH:mm");
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		ret = formatStr(cell.toString().trim());
		if (isValidDate(ret)) {
			return DateUtil.toDate(ret, "yyyy-MM-dd HH:mm");
		} else
			return toDate(ret);
	}

	private Date toDate(String s) throws Exception {
		Calendar ca = Calendar.getInstance();
		int maxYear = ca.get(Calendar.YEAR) + 10;// 年份最大不能超过距今10年
		try {
			int i = Integer.parseInt(s);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date day = format.parse("1900-01-01");// Excel数字格式日期起始日期
			ca.setTime(day);
			ca.add(Calendar.DATE, i);// i为距离起始日期增加的天数
			day = ca.getTime();
			return day;
		} catch (Exception e) {

		}
		return DateUtil.toDate(s, "yyyy-MM-dd HH:mm");
	}

	public boolean isValidDate(String str) {
		boolean convertSuccess = true;
		// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			// 设置lenient为false.
			// 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
			format.setLenient(false);
			format.parse(str);
		} catch (ParseException e) {
			// e.printStackTrace();
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			convertSuccess = false;
		}
		return convertSuccess;
	}
}