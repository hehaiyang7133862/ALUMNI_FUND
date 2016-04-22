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
	// ��Ʒ�����б�
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// �����ѯ
		String hql = "from TbCommodityOrder a where 1=1";
		List pars = this.getList();
		// ��Ʒ����
		String commName = request.getParameter("commName");
		if (commName != null && !"".equals(commName)) {
			hql += " and a.commName like ?";
			pars.add("%" + commName + "%");
		}
		// ��Ʒ�ͺ�
		String detialName = request.getParameter("detailName");
		if (detialName != null && !"".equals(detialName)) {
			hql += " and a.commDetailName like ?";
			pars.add("%" + detialName + "%");
		}

		// �ύ��ʼʱ��
		Date orderTimeBegD = null;
		String orderTimeBeg = request.getParameter("orderTimeBeg");
		if (orderTimeBeg != null && !"".equals(orderTimeBeg)) {
			orderTimeBegD = DateUtil.toDate(orderTimeBeg, "yyyy-MM-dd HH:mm");
		}
		if (orderTimeBegD != null) {
			hql += " and a.orderTime > ?";
			pars.add(orderTimeBegD);
		}

		// �ύ��ֹʱ��
		Date orderTimeEndD = null;
		String orderTimeEnd = request.getParameter("orderTimeEnd");
		if (orderTimeEnd != null && !"".equals(orderTimeEnd)) {
			orderTimeEndD = DateUtil.toDate(orderTimeEnd, "yyyy-MM-dd HH:mm");
		}
		if (orderTimeEndD != null) {
			hql += " and a.orderTime < ?";
			pars.add(orderTimeEndD);
		}
		// ������ʼʱ��
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
		// �����ֹʱ��
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

		// �������
		String buyerName = request.getParameter("buyerName");
		if (buyerName != null && !"".equals(buyerName)) {
			hql += " and a.buyerName like ?";
			pars.add("%" + buyerName + "%");
		}
		// ��Ʒ������
		String orderNo = request.getParameter("orderNo");
		if (orderNo != null && !"".equals(orderNo)) {
			hql += " and a.orderNo like ?";
			pars.add("%" + orderNo + "%");
		}
		// ��Ʒ����֧����ʽ
		String orderType = request.getParameter("orderType");
		if (orderType != null && !"".equals(orderType)) {
			hql += " and a.orderType like ?";
			pars.add("%" + orderType + "%");
		}
		// ��Ʒ����֧��״̬
		String orderStatus = request.getParameter("orderStatus");
		if (orderStatus != null && "1".equals(orderStatus)) {
			hql += " and a.orderStatus = ?";
			pars.add("1");
		} else if (orderStatus != null && !"".equals(orderStatus)) {
			hql += " and a.orderStatus <> ?";
			pars.add("1");
		}
		// ���
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
		// ִ�в�ѯ
		List orderList = this.getCommonBo().findHQLPage(hql, pars);
		request.setAttribute("orderList", orderList);
		// ����
		return SUCCESS;
	}

	// ��Ʒ��������ҳ��
	public String view() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ��Ʒ����
		TbCommodityOrder bean = null;
		// ��Ʒ����ID
		String orderId = request.getParameter("orderId");
		if (orderId != null && !"".equals(orderId)) {
			try {
				bean = (TbCommodityOrder) this.getCommonBo().get(
						TbCommodityOrder.class, new Long(orderId));
			} catch (Exception e) {
			}
		}
		request.setAttribute("bean", bean);
		// ����
		return "view";
	}

	// ��Ʒ�������ҳ��
	public String mark() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ��Ʒ����
		TbCommodityOrder bean = null;
		// ��Ʒ����ID
		String orderId = request.getParameter("orderId");
		if (orderId != null && !"".equals(orderId)) {
			try {
				bean = (TbCommodityOrder) this.getCommonBo().get(
						TbCommodityOrder.class, new Long(orderId));
			} catch (Exception e) {
			}
		}
		request.setAttribute("bean", bean);
		// ����
		return "mark";
	}

	// �ڳ���Ŀ��չ���
	public String markUpdate() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ��Ʒ����
		TbCommodityOrder bean = null;
		// ��Ʒ����ID
		String orderId = request.getParameter("orderId");
		if (orderId != null && !"".equals(orderId)) {
			try {
				bean = (TbCommodityOrder) this.getCommonBo().get(
						TbCommodityOrder.class, new Long(orderId));
			} catch (Exception e) {
			}
		}
		if (bean != null) {
			// �����Ϣ
			String mark = request.getParameter("mark");
			bean.setMark(mark);
			// ʵ���˷�
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
			// ��ִ��Ϣ
			String receipt = request.getParameter("receipt");
			bean.setReceipt(receipt);
			// ��ע
			String memo = request.getParameter("memo");
			bean.setMemo(memo);
			// ����ѡ��
			try {
				this.getCommonBo().saveOrUpdate(bean);
			} catch (Exception e) {
				// ����
				request.setAttribute("alert", "����ʱ�����쳣��");
				return "mark";
			}
		}
		// ����
		return "winSuccess";
	}

	// ��Ʒ����ɾ��
	public String del() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ��Ʒ����
		TbCommodityOrder bean = null;
		// ��Ʒ����ID
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
		// ����
		return execute();
	}

	public String GetOrCreatePrdOredr() throws Exception {
		HttpServletRequest request = this.getRequest();

		// ϵͳ��ǰʱ��
		Date dateNow = this.getCommonBo().getSysDate();
		// ��ȡ��ǰʱ����ز���
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(dateNow);
		// ��ǰ���
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

		// ����
		return "edit";
	}

	public String SaveOrUpdatePrdOrder() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ϵͳ��ǰʱ��
		Date dateNow = this.getCommonBo().getSysDate();
		// ��ȡ��ǰʱ����ز���
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(dateNow);
		// ��ǰ���
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
			request.setAttribute("alert", "��Ʒ�����ڣ�");
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

		// �����ܽ��
		BigDecimal orderFeeD = null;
		String orderFee = request.getParameter("orderFee");
		if (orderFee != null && !"".equals(orderFee)) {
			try {
				orderFeeD = new BigDecimal(orderFee).setScale(2, 4);
			} catch (Exception e) {
			}
		}
		bean.setOrderFee(orderFeeD);

		// ��Ʒ�ͺ�
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
			request.setAttribute("alert", "�����þ���ѡ�");
			request.setAttribute("bean", bean);
			return "error";
		}
		bean.setCommDetailName(detailName);
		bean.setCommDetailId(detailIdL);

		// �ɱ���
		BigDecimal commCostfeeD = null;
		String commCostfee = request.getParameter("commCostfee");
		if (commCostfee != null && !"".equals(commCostfee)) {
			try {
				commCostfeeD = new BigDecimal(commCostfee).setScale(2, 4);
			} catch (Exception e) {
			}
		}
		if (commCostfeeD == null || commCostfeeD.doubleValue() < 0) {
			request.setAttribute("alert", "��Ʒ���۲���С��0��");
			request.setAttribute("bean", bean);
			return "error";
		}
		bean.setCommCostfee(commCostfeeD);
		
		//�ۼ�
		BigDecimal commSalefeeD = null;
		String commSalefee = request.getParameter("commSalefee");
		if (commSalefee != null && !"".equals(commSalefee)) {
			try {
				commSalefeeD = new BigDecimal(commSalefee).setScale(2, 4);
			} catch (Exception e) {
			}
		}
		if (commSalefeeD == null || commSalefeeD.doubleValue() < 0) {
			request.setAttribute("alert", "��Ʒ���۲���С��0��");
			request.setAttribute("bean", bean);
			return "error";
		}
		bean.setCommSalefee(commSalefeeD);

		// ��Ʒ����
		Long commNumL = null;
		String commNum = request.getParameter("commNum");
		if (commNum != null && !"".equals(commNum)) {
			try {
				commNumL = new Long(commNum);
			} catch (Exception e) {
			}
		}
		if (commNumL != null && commNumL < 1) {
			request.setAttribute("alert", "��Ʒ������������1����");
			request.setAttribute("bean", bean);
			return "error";
		}
		bean.setCommNum(commNumL);

		// ȡ����ʽ
		String shippingType = request.getParameter("shippingType");
		if (shippingType != null && !shippingType.equals("")) {
			bean.setShippingType(shippingType);
		}

		// �˷�
		BigDecimal shippingFeeD = null;
		String shippingFee = request.getParameter("shippingFee");
		if (shippingFee != null && !"".equals(shippingFee)) {
			try {
				shippingFeeD = new BigDecimal(shippingFee).setScale(2, 4);
			} catch (Exception e) {
			}
		}
		if (shippingFeeD == null || shippingFeeD.doubleValue() < 0) {
			request.setAttribute("alert", "�˷Ѳ�������0��");
			request.setAttribute("bean", bean);
			return "error";
		}
		bean.setShippingFee(shippingFeeD);

		// �������
		BigDecimal donationFeeD = null;
		String donationFee = request.getParameter("donationFee");
		if (donationFee != null && !"".equals(donationFee)) {
			try {
				donationFeeD = new BigDecimal(donationFee).setScale(2, 4);
			} catch (Exception e) {
			}
		}
		if (donationFeeD == null || donationFeeD.doubleValue() < 0) {
			request.setAttribute("alert", "������������0��");
			request.setAttribute("bean", bean);
			return "orderEdit";
		}
		bean.setDonationFee(donationFeeD);

		// ����״̬
		String orderStatus = request.getParameter("orderStatus");
		bean.setOrderStatus(orderStatus);

		// �ύʱ��
		String orderTime = request.getParameter("orderTime");
		if (orderTime != null && !"".equals(orderTime)) {
			bean.setOrderTime(DateUtil.toDate(orderTime, "yyyy-MM-dd HH:mm"));
		} else {
			bean.setOrderTime(null);
		}

		// ֧����ʽ
		String orderType = request.getParameter("orderType");
		if (orderType != null && !"".equals(orderType)) {
			bean.setOrderType(orderType);
		} else {
			bean.setOrderType(null);
		}

		// ����ʱ��
		String orderOkTime = request.getParameter("orderOkTime");
		if (orderOkTime != null && !"".equals(orderOkTime)) {
			bean.setOrderOkTime(DateUtil
					.toDate(orderOkTime, "yyyy-MM-dd HH:mm"));
		} else {
			bean.setOrderOkTime(null);
		}

		// ����
		String person = request.getParameter("person");
		if (person == null || "".equals(person)) {
			person = "����";
		}
		bean.setBuyerName(person);
		// �Ա�
		String personSex = request.getParameter("personSex");
		bean.setBuyerSex(personSex);
		// ����
		String niMing = request.getParameter("niMing");
		bean.setNiMing(niMing);
		// �ֻ�
		String personPhone = request.getParameter("personPhone");
		bean.setBuyerPhone(personPhone);
		// �绰
		String personTel = request.getParameter("personTel");
		bean.setBuyerMobile(personTel);
		// ����
		String personEmail = request.getParameter("personEmail");
		bean.setBuyerEmail(personEmail);
		// �ʱ�
		String zip = request.getParameter("zip");
		bean.setBuyerZipcode(zip);
		// ʡ
		String sheng = request.getParameter("sheng");
		bean.setBuyerSheng(sheng);
		// ��
		String shi = request.getParameter("shi");
		bean.setBuyerShi(shi);
		// ��/��
		String qu = request.getParameter("qu");
		bean.setBuyerQu(qu);
		// ��ϸ��ַ
		String address = request.getParameter("address");
		bean.setBuyerAddress(address);

		// У������
		// �Ƿ�У��
		String alumniFlag = request.getParameter("alumniFlag");
		bean.setAlumniFlag(alumniFlag);
		// ��ѧ��
		String academyBeg = request.getParameter("academyBeg");
		bean.setStudyYearin(academyBeg);
		// ��У��
		String academyEnd = request.getParameter("academyEnd");
		bean.setStudyYearover(academyEnd);
		// Ժϵ
		String academy = request.getParameter("academy");
		bean.setStudyAcademy(academy);
		// רҵ
		String major = request.getParameter("major");
		bean.setStudyMajor(major);
		// �༶
		String clazz = request.getParameter("clazz");
		bean.setStudyClass(clazz);
		// ѧ��
		String studyno = request.getParameter("studyno");
		bean.setStudyNum(studyno);
		// ѧ��
		String academyDegree = request.getParameter("academyDegree");
		bean.setStudyDegree(academyDegree);
		// ��������
		// ��˾
		String company = request.getParameter("company");
		bean.setWorkCompany(company);
		// ����
		String department = request.getParameter("department");
		bean.setWorkDepart(department);
		// ְλ
		String duty = request.getParameter("duty");
		bean.setWorkDuty(duty);
		// ������Ϣ
		String orderMemo = request.getParameter("orderMemo");
		bean.setOrderMemo(orderMemo);
		// �����Ϣ
		String mark = request.getParameter("mark");
		bean.setMark(mark);
		// ��ִ��Ϣ
		String receipt = request.getParameter("receipt");
		bean.setReceipt(receipt);
		// ��ע
		String memo = request.getParameter("memo");
		bean.setMemo(memo);

		try {
			bean = (TbCommodityOrder) this.getCommonBo().saveOrUpdate(bean);
			if (bean.getOrderNo() == null || "".equals(bean.getOrderNo())) {
				// ����
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
			// ����
			return "success";
		} catch (Exception e) {
		}

		// ����״̬������ʧ��
		request.setAttribute("alert", "����ʧ�ܣ�");
		request.setAttribute("bean", bean);
		// ����
		return "error";
	}

	// �ϴ�Excel������Ȳ�������
	private String IMPORTCOUNT = "ZCPROJEDITORDEREXCELCOUNT";
	private String IMPORTINDEX = "ZCPROJEDITORDEREXCELINDEX";

	// �ϴ�Excel�������
	public String process() throws Exception {
		HttpServletRequest request = this.getRequest();
		HttpServletResponse response = this.getResponse();
		HttpSession session = request.getSession();
		// �������ʱ���
		String random = request.getParameter("random");
		if (random == null) {
			random = "";
		}
		// ������Ȳ���
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
		// ��ֵ
		this.sendResponse(response, "{\"count\":" + count + ",\"index\":"
				+ index + "}");
		// ����
		return null;
	}

	// ����ҳ��
	public String toImport() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ��Ŀ
		TbCommodity beanComm = null;
		// ��ĿID
		String commId = request.getParameter("commId");
		if (commId != null && !"".equals(commId)) {
			try {
				beanComm = (TbCommodity) this.getCommonBo().get(
						TbCommodity.class, new Long(commId));
			} catch (Exception e) {
			}
		}
		if (beanComm == null) {
			request.setAttribute("alert", "�ڳ���Ŀ�����ڣ���رղ����´򿪵���ҳ�棡");
			return "import";
		}
		request.setAttribute("beanComm", beanComm);
		return "import";
	}

	// ��������
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
			request.setAttribute("alert", "��Ʒ�����ڣ���رղ����´򿪵���ҳ�棡");
			return "import";
		}
		request.setAttribute("beanComm", beanComm);

		// ϵͳ��ǰʱ��
		Date dateNow = this.getCommonBo().getSysDate();
		// �������ʱ���
		String random = multiRequest.getParameter("random");
		if (random == null) {
			random = "";
		}
		// ��ȡ�����ļ�
		FileItem item = multiRequest.getFile("file");
		if (item == null || item.getSize() == 0) {
			request.setAttribute("alert", "��ѡ����Ҫ�����Excel�ļ���");
			return "import";
		}

		// ��ȡ�����ļ�
		InputStream fis = null;
		try {
			fis = item.getInputStream();
			POIFSFileSystem fs = new POIFSFileSystem(fis);
			HSSFWorkbook workbook = new HSSFWorkbook(fs);
			HSSFSheet sheet = workbook.getSheetAt(0);
			// �ж��Ƿ�������
			int rowCount = sheet.getLastRowNum();
			if (rowCount < 1) {
				request.setAttribute("alert", "����ģ����󣬻����ļ���û����ص������ݣ�");
				return "import";
			}
			// ͷ
			HSSFRow headRow = sheet.getRow(0);
			int colCount = headRow.getLastCellNum();
			List headList = this.getList();
			for (int i = 0; i <= colCount; i++) {
				HSSFCell headCell = headRow.getCell((short) i);
				String headCellValue = "";
				if (headCell != null) {
					headCellValue = headCell.toString().trim();
				}
				if (headCellValue.contains("������")) {
					headList.add("������");
				} else if (headCellValue.contains("�����ܽ��")) {
					headList.add("�����ܽ��");
				} else if (headCellValue.contains("��Ʒ����")) {
					headList.add("��Ʒ����");
				} else if (headCellValue.endsWith("��Ʒ�ͺ�")) {
					headList.add("��Ʒ�ͺ�");
				} else if (headCellValue.endsWith("��Ʒ����")) {
					headList.add("��Ʒ����");
				} else if (headCellValue.endsWith("��Ʒ����")) {
					headList.add("��Ʒ����");
				} else if (headCellValue.contains("ȡ����ʽ")) {
					headList.add("ȡ����ʽ");
				} else if (headCellValue.contains("�˷�")) {
					headList.add("�˷�");
				} else if (headCellValue.contains("�������")) {
					headList.add("�������");
				} else if (headCellValue.contains("֧����ʽ")) {
					headList.add("֧����ʽ");
				} else if (headCellValue.contains("֧��״̬")) {
					headList.add("֧��״̬");
				} else if (headCellValue.contains("�ύʱ��")) {
					headList.add("�ύʱ��");
				} else if (headCellValue.contains("����ʱ��")) {
					headList.add("����ʱ��");
				} else if (headCellValue.endsWith("�������")) {
					headList.add("�������");
				} else if (headCellValue.contains("�Ƿ�����")) {
					headList.add("�Ƿ�����");
				} else if (headCellValue.contains("�Ա�")) {
					headList.add("�Ա�");
				} else if (headCellValue.contains("�ֻ�")) {
					headList.add("�ֻ�");
				} else if (headCellValue.contains("�绰")) {
					headList.add("�绰");
				} else if (headCellValue.contains("����")) {
					headList.add("����");
				} else if (headCellValue.contains("�ʱ�")) {
					headList.add("�ʱ�");
				} else if (headCellValue.contains("ʡ")) {
					headList.add("ʡ");
				} else if (headCellValue.contains("��")) {
					headList.add("��");
				} else if (headCellValue.contains("��/��")) {
					headList.add("��/��");
				} else if (headCellValue.contains("��ϸ��ַ")) {
					headList.add("��ϸ��ַ");
				} else if (headCellValue.contains("�Ƿ�У��")) {
					headList.add("�Ƿ�У��");
				} else if (headCellValue.contains("��ѧ��")) {
					headList.add("��ѧ��");
				} else if (headCellValue.contains("��У��")) {
					headList.add("��У��");
				} else if (headCellValue.contains("Ժϵ")) {
					headList.add("Ժϵ");
				} else if (headCellValue.contains("רҵ")) {
					headList.add("רҵ");
				} else if (headCellValue.contains("�༶")) {
					headList.add("�༶");
				} else if (headCellValue.contains("ѧ��")) {
					headList.add("ѧ��");
				} else if (headCellValue.contains("ѧ��")) {
					headList.add("ѧ��");
				} else if (headCellValue.contains("��˾")) {
					headList.add("��˾");
				} else if (headCellValue.contains("����")) {
					headList.add("����");
				} else if (headCellValue.contains("ְλ")) {
					headList.add("ְλ");
				} else if (headCellValue.contains("����")) {
					headList.add("������Ϣ");
				} else if (headCellValue.contains("���")) {
					headList.add("�����Ϣ");
				} else if (headCellValue.contains("��ִ")) {
					headList.add("��ִ��Ϣ");
				} else if (headCellValue.contains("��ע")) {
					headList.add("��ע");
				} else {
					headList.add("");
				}
			}
			if (!headList.contains("��Ʒ�ͺ�") || !headList.contains("��Ʒ����")
					|| !headList.contains("�����ܽ��")
					|| !headList.contains("�������")) {
				request.setAttribute("alert", "����ģ���ʽ�������������ص���ģ�壡");
				return "import";
			}
			// ���õ�����Ȳ���
			session.setAttribute(IMPORTCOUNT + random, rowCount);
			// ����Excel��������
			int orderNoIndex = headList.indexOf("������");
			int orderFeeIndex = headList.indexOf("�����ܽ��");
			int commNameIndex = headList.indexOf("��Ʒ����");
			int commDetailIndex = headList.indexOf("��Ʒ�ͺ�");
			int commCostFeeIndex = headList.indexOf("��Ʒ����");
			int commNumIndex = headList.indexOf("��Ʒ����");
			int shippingTypeIndex = headList.indexOf("ȡ����ʽ");
			int shippingFeeIndex = headList.indexOf("�˷�");
			int donationFeeIndex = headList.indexOf("�������");
			int orderTypeIndex = headList.indexOf("֧����ʽ");
			int orderStatusIndex = headList.indexOf("֧��״̬");
			int orderTimeIndex = headList.indexOf("�ύʱ��");
			int orderOkTimeIndex = headList.indexOf("����ʱ��");
			int personIndex = headList.indexOf("���");
			int niMingIndex = headList.indexOf("�Ƿ�����");
			int personSexIndex = headList.indexOf("�Ա�");
			int personPhoneIndex = headList.indexOf("�ֻ�");
			int personTelIndex = headList.indexOf("�绰");
			int personEmailIndex = headList.indexOf("����");
			int zipIndex = headList.indexOf("�ʱ�");
			int shengIndex = headList.indexOf("ʡ");
			int shiIndex = headList.indexOf("��");
			int quIndex = headList.indexOf("��/��");
			int addressIndex = headList.indexOf("��ϸ��ַ");
			int alumniFlagIndex = headList.indexOf("�Ƿ�У��");
			int academyBegIndex = headList.indexOf("��ѧ��");
			int academyEndIndex = headList.indexOf("��У��");
			int academyIndex = headList.indexOf("Ժϵ");
			int majorIndex = headList.indexOf("רҵ");
			int clazzIndex = headList.indexOf("�༶");
			int studynoIndex = headList.indexOf("ѧ��");
			int academyDegreeIndex = headList.indexOf("ѧ��");
			int companyIndex = headList.indexOf("��˾");
			int departmentIndex = headList.indexOf("����");
			int dutyIndex = headList.indexOf("ְλ");
			int orderMemoIndex = headList.indexOf("������Ϣ");
			int markIndex = headList.indexOf("�����Ϣ");
			int receiptIndex = headList.indexOf("��ִ��Ϣ");
			int memoIndex = headList.indexOf("��ע");
			// ���뷴����Ϣ
			int successcount = 0;
			int errorcount = 0;
			List errorList = this.getList();
			// ѭ������
			for (int i = 1; i <= rowCount; i++) {
				HSSFRow row = sheet.getRow(i);
				// ���õ�����Ȳ���
				session.setAttribute(IMPORTINDEX + random, i);
				try {
					// ��Ŀ����
					TbCommodityOrder bean = null;
					// ������
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
											"����ʧ�ܣ�ԭ�򣺸���Ŀ�в����ڶ�����Ϊ��" + orderNo
													+ "���ľ�����¼��δ�����������´���" });
									continue;
								}
							}
						}
					}

					if (bean == null) {
						bean = new TbCommodityOrder();
						// ����������Ŀ
						bean.setCommId(beanComm.getCommId());
						bean.setCommName(beanComm.getCommName());
					}

					// ��Ʒ�ͺ�
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

					// ��Ʒ����
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
									"���󣺲�Ʒ�����ֶ�Ϊ�գ�δ�����������´���" });
							continue;
						}
					} else if (commDetail != null && !"".equals(commDetail)) {
						bean.setCommDetailName(commDetail);
						if (optionCount != null && optionCount.intValue() > 0) {
							bean.setCommNum(optionCount);

							errorcount++;
							errorList.add(new String[] { String.valueOf(i),
									"���棺δ�ܳɹ�ƥ���Ʒ�ͺţ��������������´���" });
						} else {
							errorcount++;
							errorList.add(new String[] { String.valueOf(i),
									"���󣺲�Ʒ�����ֶ�Ϊ�գ�δ�����������´���" });
							continue;
						}
					} else {
						errorcount++;
						errorList.add(new String[] { String.valueOf(i),
								"���󣺲�Ʒ�ͺ��ֶ�Ϊ�գ�δ�����������´���" });
						continue;
					}

					// ��Ʒ����
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

					// ȡ����ʽ
					if (shippingTypeIndex != -1) {
						HSSFCell shippingTypeCell = row
								.getCell((short) shippingTypeIndex);
						if (shippingTypeCell != null) {
							String shippingType = formatStr(shippingTypeCell
									.toString().trim());
							if (shippingType != null
									&& ("�������".equals(shippingType) || "1"
											.equals(shippingType))) {
								bean.setShippingType("1");
							} else {
								bean.setShippingType("0");
							}
						}
					}

					// �˷�
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

					// �������
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
					// ֧����ʽ
					if (orderTypeIndex != -1) {
						HSSFCell orderTypeCell = row
								.getCell((short) orderTypeIndex);
						if (orderTypeCell != null) {
							String orderType = formatStr(orderTypeCell
									.toString().trim());
							bean.setOrderType(orderType);
						}
					}
					// ֧��״̬
					if (orderStatusIndex != -1) {
						HSSFCell orderStatusCell = row
								.getCell((short) orderStatusIndex);
						if (orderStatusCell != null) {
							String orderStatus = formatStr(orderStatusCell
									.toString().trim());
							if (orderStatus != null
									&& ("�Ѹ���".equals(orderStatus) || "1"
											.equals(orderStatus))) {
								bean.setOrderStatus("1");
							} else {
								bean.setOrderStatus("0");
							}
						}
					}
					// �ύʱ��
					if (orderTimeIndex != -1) {
						HSSFCell orderTimeCell = row
								.getCell((short) orderTimeIndex);
						if (orderTimeCell != null) {
							Date orderTime = getExcelDate(orderTimeCell);
							bean.setOrderTime(orderTime);
						}
					}
					// ����ʱ��
					if (orderOkTimeIndex != -1) {
						HSSFCell orderOkTimeCell = row
								.getCell((short) orderOkTimeIndex);
						if (orderOkTimeCell != null) {
							Date orderOkTime = getExcelDate(orderOkTimeCell);
							bean.setOrderOkTime(orderOkTime);
						}
					}

					// ������

					// ����
					if (personIndex != -1) {
						HSSFCell personCell = row.getCell((short) personIndex);
						if (personCell != null) {
							String person = formatStr(personCell.toString()
									.trim());
							if (person == null || "".equals(person)) {
								person = "����";
							}
							bean.setBuyerName(person);
						}
					}
					// �Ա�
					if (personSexIndex != -1) {
						HSSFCell personSexCell = row
								.getCell((short) personSexIndex);
						if (personSexCell != null) {
							String personSex = formatStr(personSexCell
									.toString().trim());
							if (personSex != null) {
								if ("��".equals(personSex)
										|| "����".equals(personSex)
										|| "1".equals(personSex)) {
									bean.setBuyerSex("1");
								} else if ("Ů".equals(personSex)
										|| "Ů��".equals(personSex)
										|| "2".equals(personSex)) {
									bean.setBuyerSex("2");
								}
							}
						}
					}
					// ����
					if (niMingIndex != -1) {
						HSSFCell niMingCell = row.getCell((short) niMingIndex);
						if (niMingCell != null) {
							String niMing = formatStr(niMingCell.toString()
									.trim());
							if (niMing != null
									&& ("��".equals(niMing)
											|| "����".equals(niMing)
											|| "��������".equals(niMing) || "1"
												.equals(niMing))) {
								bean.setNiMing("1");
							} else {
								bean.setNiMing("0");
							}
						}
					}
					// �ֻ�
					if (personPhoneIndex != -1) {
						HSSFCell personPhoneCell = row
								.getCell((short) personPhoneIndex);
						if (personPhoneCell != null) {
							String personPhone = formatStr(personPhoneCell
									.toString().trim());
							bean.setBuyerPhone(personPhone);
						}
					}
					// �绰
					if (personTelIndex != -1) {
						HSSFCell personTelCell = row
								.getCell((short) personTelIndex);
						if (personTelCell != null) {
							String personTel = formatStr(personTelCell
									.toString().trim());
							bean.setBuyerMobile(personTel);
						}
					}
					// ����
					if (personEmailIndex != -1) {
						HSSFCell personEmailCell = row
								.getCell((short) personEmailIndex);
						if (personEmailCell != null) {
							String personEmail = formatStr(personEmailCell
									.toString().trim());
							bean.setBuyerEmail(personEmail);
						}
					}
					// �ʱ�
					if (zipIndex != -1) {
						HSSFCell zipCell = row.getCell((short) zipIndex);
						if (zipCell != null) {
							String zip = formatStr(zipCell.toString().trim());
							bean.setBuyerZipcode(zip);
						}
					}
					// ʡ
					if (shengIndex != -1) {
						HSSFCell shengCell = row.getCell((short) shengIndex);
						if (shengCell != null) {
							String sheng = formatStr(shengCell.toString()
									.trim());
							bean.setBuyerSheng(sheng);
						}
					}
					// ��
					if (shiIndex != -1) {
						HSSFCell shiCell = row.getCell((short) shiIndex);
						if (shiCell != null) {
							String shi = formatStr(shiCell.toString().trim());
							bean.setBuyerShi(shi);
						}
					}
					// ��/��
					if (quIndex != -1) {
						HSSFCell quCell = row.getCell((short) quIndex);
						if (quCell != null) {
							String qu = formatStr(quCell.toString().trim());
							bean.setBuyerQu(qu);
						}
					}
					// ��ϸ��ַ
					if (addressIndex != -1) {
						HSSFCell addressCell = row
								.getCell((short) addressIndex);
						if (addressCell != null) {
							String address = formatStr(addressCell.toString()
									.trim());
							bean.setBuyerAddress(address);
						}
					}
					// У������
					// �Ƿ�У��
					if (alumniFlagIndex != -1) {
						HSSFCell alumniFlagCell = row
								.getCell((short) alumniFlagIndex);
						if (alumniFlagCell != null) {
							String alumniFlag = formatStr(alumniFlagCell
									.toString().trim());
							if (alumniFlag != null
									&& ("У��".equals(alumniFlag)
											|| "��".equals(alumniFlag) || "1"
												.equals(alumniFlag))) {
								bean.setAlumniFlag("1");
							} else {
								bean.setAlumniFlag("0");
							}
						}
					}
					// ��ѧ��
					if (academyBegIndex != -1) {
						HSSFCell academyBegCell = row
								.getCell((short) academyBegIndex);
						if (academyBegCell != null) {
							String academyBeg = formatStr(academyBegCell
									.toString().trim());
							bean.setStudyYearin(academyBeg);
						}
					}
					// ��У��
					if (academyEndIndex != -1) {
						HSSFCell academyEndCell = row
								.getCell((short) academyEndIndex);
						if (academyEndCell != null) {
							String academyEnd = formatStr(academyEndCell
									.toString().trim());
							bean.setStudyYearover(academyEnd);
						}
					}
					// Ժϵ
					if (academyIndex != -1) {
						HSSFCell academyCell = row
								.getCell((short) academyIndex);
						if (academyCell != null) {
							String academy = formatStr(academyCell.toString()
									.trim());
							bean.setStudyAcademy(academy);
						}
					}
					// רҵ
					if (majorIndex != -1) {
						HSSFCell majorCell = row.getCell((short) majorIndex);
						if (majorCell != null) {
							String major = formatStr(majorCell.toString()
									.trim());
							bean.setStudyMajor(major);
						}
					}
					// �༶
					if (clazzIndex != -1) {
						HSSFCell clazzCell = row.getCell((short) clazzIndex);
						if (clazzCell != null) {
							String clazz = formatStr(clazzCell.toString()
									.trim());
							bean.setStudyClass(clazz);
						}
					}
					// ѧ��
					if (studynoIndex != -1) {
						HSSFCell studynoCell = row
								.getCell((short) studynoIndex);
						if (studynoCell != null) {
							String studyno = formatStr(studynoCell.toString()
									.trim());
							bean.setStudyNum(studyno);
						}
					}
					// ѧ��
					if (academyDegreeIndex != -1) {
						HSSFCell academyDegreeCell = row
								.getCell((short) academyDegreeIndex);
						if (academyDegreeCell != null) {
							String academyDegree = formatStr(academyDegreeCell
									.toString().trim());
							bean.setStudyDegree(academyDegree);
						}
					}
					// ��������
					// ��˾
					if (companyIndex != -1) {
						HSSFCell companyCell = row
								.getCell((short) companyIndex);
						if (companyCell != null) {
							String company = formatStr(companyCell.toString()
									.trim());
							bean.setWorkCompany(company);
						}
					}
					// ����
					if (departmentIndex != -1) {
						HSSFCell departmentCell = row
								.getCell((short) departmentIndex);
						if (departmentCell != null) {
							String department = formatStr(departmentCell
									.toString().trim());
							bean.setWorkDepart(department);
						}
					}
					// ְλ
					if (dutyIndex != -1) {
						HSSFCell dutyCell = row.getCell((short) dutyIndex);
						if (dutyCell != null) {
							String duty = formatStr(dutyCell.toString().trim());
							bean.setWorkDuty(duty);
						}
					}
					// ������Ϣ
					if (orderMemoIndex != -1) {
						HSSFCell orderMemoCell = row
								.getCell((short) orderMemoIndex);
						if (orderMemoCell != null) {
							String orderMemo = formatStr(orderMemoCell
									.toString().trim());
							bean.setOrderMemo(orderMemo);
						}
					}
					// �����Ϣ
					if (markIndex != -1) {
						HSSFCell markCell = row.getCell((short) markIndex);
						if (markCell != null) {
							String mark = formatStr(markCell.toString().trim());
							bean.setMark(mark);
						}
					}
					// ��ִ��Ϣ
					if (receiptIndex != -1) {
						HSSFCell receiptCell = row
								.getCell((short) receiptIndex);
						if (receiptCell != null) {
							String receipt = formatStr(receiptCell.toString()
									.trim());
							bean.setReceipt(receipt);
						}
					}
					// ��ע
					if (memoIndex != -1) {
						HSSFCell memoCell = row.getCell((short) memoIndex);
						if (memoCell != null) {
							String memo = formatStr(memoCell.toString().trim());
							bean.setMemo(memo);
						}
					}
					// ����
					bean = (TbCommodityOrder) this.getCommonBo().saveOrUpdate(
							bean);
					if (bean.getOrderNo() == null
							|| "".equals(bean.getOrderNo())) {
						// ����
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
							"����ʧ�ܣ�ԭ�򣺸���������Ϣ����ʱ�������쳣���쳣������" + e.getMessage() });
				}
			}
			request.setAttribute("successcount", successcount);
			request.setAttribute("errorcount", errorcount);
			request.setAttribute("errorList", errorList);
			return "import";
		} catch (Exception e) {
			request.setAttribute("alert", "���棬�����ļ�ʱ�����쳣���쳣������" + e.getMessage());
			return "import";
		} finally {
			if (fis != null) {
				fis.close();
			}
			// �Ƴ�������Ȳ���
			session.removeAttribute(IMPORTINDEX + random);
			session.removeAttribute(IMPORTCOUNT + random);
		}
	}

	// ��������
	public String doExport() throws Exception {
		HttpServletRequest request = this.getRequest();
		// �������
		String ajax = "{\"result\":\"error\"}";
		// 1�����ص���ģ�壬����Ϊ����
		String type = request.getParameter("type");
		// ��Ҫ��������Ϣϵͳ����
		List beantList = null;
		// ��ȡ��Ҫ�����ľ�����Ϣ����
		if (type == null || !"1".equals(type)) {

			// �����ѯ
			String hql = "from TbCommodityOrder a where 1=1";
			List pars = this.getList();

			// ��Ʒ���
			String commId = request.getParameter("commId");
			if (commId != null && !"".equals(commId)) {
				hql += " and a.commId = ?";
				pars.add(new Long(commId));
			} else {
				// ��Ʒ����
				String commName = request.getParameter("commName");
				if (commName != null && !"".equals(commName)) {
					hql += " and a.commName like ?";
					pars.add("%" + commName + "%");
				}
			}
			// ��Ʒ�ͺ�
			String detialName = request.getParameter("detailName");
			if (detialName != null && !"".equals(detialName)) {
				hql += " and a.commDetailName like ?";
				pars.add("%" + detialName + "%");
			}

			// �ύ��ʼʱ��
			Date orderTimeBegD = null;
			String orderTimeBeg = request.getParameter("orderTimeBeg");
			if (orderTimeBeg != null && !"".equals(orderTimeBeg)) {
				orderTimeBegD = DateUtil.toDate(orderTimeBeg, "yyyy-MM-dd HH:mm");
			}
			if (orderTimeBegD != null) {
				hql += " and a.orderTime >= ?";
				pars.add(orderTimeBegD);
			}

			// �ύ��ֹʱ��
			Date orderTimeEndD = null;
			String orderTimeEnd = request.getParameter("orderTimeEnd");
			if (orderTimeEnd != null && !"".equals(orderTimeEnd)) {
				orderTimeEndD = DateUtil.toDate(orderTimeEnd, "yyyy-MM-dd HH:mm");
			}
			if (orderTimeEndD != null) {
				hql += " and a.orderTime <= ?";
				pars.add(orderTimeEndD);
			}
			// ������ʼʱ��
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
			// �����ֹʱ��
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

			// �������
			String buyerName = request.getParameter("buyerName");
			if (buyerName != null && !"".equals(buyerName)) {
				hql += " and a.buyerName like ?";
				pars.add("%" + buyerName + "%");
			}
			
			// ��Ʒ������
			String orderNo = request.getParameter("orderNo");
			if (orderNo != null && !"".equals(orderNo)) {
				hql += " and a.orderNo like ?";
				pars.add("%" + orderNo + "%");
			}
			// ��Ʒ����֧����ʽ
			String orderType = request.getParameter("orderType");
			if (orderType != null && !"".equals(orderType)) {
				hql += " and a.orderType like ?";
				pars.add("%" + orderType + "%");
			}
			// ��Ʒ����֧��״̬
			String orderStatus = request.getParameter("orderStatus");
			if (orderStatus != null && "1".equals(orderStatus)) {
				hql += " and a.orderStatus = ?";
				pars.add("1");
			} else if (orderStatus != null && !"".equals(orderStatus)) {
				hql += " and a.orderStatus <> ?";
				pars.add("1");
			}
			// ���
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
			// ִ�в�ѯ
			try {
				beantList = this.getCommonBo().findHQL(hql, pars);
			} catch (Exception e) {
			}

		}
		// ����Excel�ļ�
		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet();
			// ���õ�Ԫ����
			sheet.setColumnWidth((short) 0, (short) 8000);
			sheet.setColumnWidth((short) 1, (short) 8000);
			sheet.setDefaultColumnWidth((short) 24);
			// ���õ�Ԫ������
			HSSFFont font = wb.createFont();
			font.setFontName("����");
			font.setFontHeight((short) 200);
			// ������Ԫ����ʽ(���Ͻ�)
			HSSFCellStyle cellLeftTopStyle = wb.createCellStyle();
			// ָ����Ԫ����ж���
			cellLeftTopStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// ָ����Ԫ��ֱ���ж���
			cellLeftTopStyle
					.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			// ָ������Ԫ��������ʾ����ʱ�Զ�����
			cellLeftTopStyle.setWrapText(true);
			cellLeftTopStyle.setFont(font);
			cellLeftTopStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // �±߿�
			cellLeftTopStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// ��߿�
			cellLeftTopStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// �ϱ߿�
			cellLeftTopStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// �ұ߿�
			// ������Ԫ����ʽ(��)
			HSSFCellStyle cellLeftStyle = wb.createCellStyle();
			// ָ����Ԫ����ж���
			cellLeftStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// ָ����Ԫ��ֱ���ж���
			cellLeftStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			// ָ������Ԫ��������ʾ����ʱ�Զ�����
			cellLeftStyle.setWrapText(true);
			cellLeftStyle.setFont(font);
			cellLeftStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // �±߿�
			cellLeftStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// ��߿�
			cellLeftStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// �ұ߿�
			// ������Ԫ����ʽ(��)
			HSSFCellStyle cellTopStyle = wb.createCellStyle();
			// ָ����Ԫ����ж���
			cellTopStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// ָ����Ԫ��ֱ���ж���
			cellTopStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			// ָ������Ԫ��������ʾ����ʱ�Զ�����
			cellTopStyle.setWrapText(true);
			cellTopStyle.setFont(font);
			cellTopStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // �±߿�
			cellTopStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// �ұ߿�
			cellTopStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// �ϱ߿�
			// ������Ԫ����ʽ(����)
			HSSFCellStyle cellStyle = wb.createCellStyle();
			// ָ����Ԫ����ж���
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// ָ����Ԫ��ֱ���ж���
			cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			// ָ������Ԫ��������ʾ����ʱ�Զ�����
			cellStyle.setWrapText(true);
			cellStyle.setFont(font);
			cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // �±߿�
			cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// �ұ߿�
			// ��һ�У�������
			List headList = this.getList();
			headList.add("������");
			headList.add("�����ܽ��");
			headList.add("��Ʒ����");
			headList.add("��Ʒ�ͺ�");
			headList.add("��Ʒ����");
			headList.add("��Ʒ����");
			headList.add("ȡ����ʽ");
			headList.add("�˷�");
			headList.add("�������");
			headList.add("֧����ʽ");
			headList.add("֧��״̬");
			headList.add("�ύʱ��");
			headList.add("����ʱ��");
			headList.add("�������");
			headList.add("�Ƿ�����");
			headList.add("�Ա�");
			headList.add("�ֻ�");
			headList.add("�绰");
			headList.add("����");
			headList.add("�ʱ�");
			headList.add("ʡ");
			headList.add("��");
			headList.add("��/��");
			headList.add("��ϸ��ַ");
			headList.add("�Ƿ�У��");
			headList.add("��ѧ��");
			headList.add("��У��");
			headList.add("Ժϵ");
			headList.add("רҵ");
			headList.add("�༶");
			headList.add("ѧ��");
			headList.add("ѧ��");
			headList.add("��˾");
			headList.add("����");
			headList.add("ְλ");
			headList.add("������Ϣ");
			headList.add("�������");
			headList.add("�����Ϣ");
			headList.add("��ִ��Ϣ");
			headList.add("��ע");

			HSSFRow row0 = sheet.createRow(0);
			row0.setHeight((short) 600);
			// ��һ��
			for (int i = 0; i < headList.size(); i++) {
				HSSFCell row0cell = row0.createCell((short) i);
				row0cell.setCellStyle(cellTopStyle);
				row0cell.setCellValue(new HSSFRichTextString(headList.get(i)
						.toString()));
			}
			// ������
			if (beantList != null && !beantList.isEmpty()) {
				for (int i = 1; i < beantList.size() + 1; i++) {
					HSSFRow row = sheet.createRow(i);
					row.setHeight((short) 600);
					TbCommodityOrder bean = (TbCommodityOrder) beantList
							.get(i - 1);
					// �к�
					int k = 0;
					// �ж���
					HSSFCell cell = null;
					// ������
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getOrderNo() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getOrderNo()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// �����ܽ��
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getOrderFee() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getOrderFee().toString()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ��Ʒ����
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getCommName() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getCommName()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ��Ʒ�ͺ�
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getCommDetailName() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getCommDetailName()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ��Ʒ����
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getCommCostfee() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getCommCostfee().toString()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ��Ʒ����
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getCommNum() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getCommNum().toString()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ȡ����ʽ
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getShippingType() != null
							&& "1".equals(bean.getShippingType())) {
						cell.setCellValue(new HSSFRichTextString("�������"));
					} else {
						cell.setCellValue(new HSSFRichTextString("��������"));
					}
					// �˷�
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getShippingFee() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getShippingFee().toString()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ����
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getDonationFee() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getDonationFee().toString()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ֧����ʽ
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getOrderType() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getOrderType().toString()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ֧��״̬
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getOrderStatus() != null
							&& "1".equals(bean.getOrderStatus())) {
						cell.setCellValue(new HSSFRichTextString("�Ѹ���"));
					} else {
						cell.setCellValue(new HSSFRichTextString("������"));
					}
					// �ύʱ��
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
					// ����ʱ��
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

					// ���
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getBuyerName() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getBuyerName()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// �Ƿ�����
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getNiMing() != null
							&& "1".equals(bean.getNiMing())) {
						cell.setCellValue(new HSSFRichTextString("��"));
					} else {
						cell.setCellValue(new HSSFRichTextString("��"));
					}
					// �Ա�
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getBuyerSex() != null
							&& "1".equals(bean.getBuyerSex())) {
						cell.setCellValue(new HSSFRichTextString("��"));
					} else if (bean.getBuyerSex() != null
							&& "2".equals(bean.getBuyerSex())) {
						cell.setCellValue(new HSSFRichTextString("Ů"));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// �ֻ�
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getBuyerPhone() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getBuyerPhone()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// �绰
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getBuyerMobile() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getBuyerMobile()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ����
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getBuyerEmail() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getBuyerEmail()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// �ʱ�
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getBuyerZipcode() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getBuyerZipcode()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ʡ
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getBuyerSheng() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getBuyerSheng()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ��
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getBuyerShi() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getBuyerShi()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ��/��
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getBuyerQu() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getBuyerQu()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ��ϸ��ַ
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getBuyerAddress() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getBuyerAddress()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// �Ƿ�У��
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getAlumniFlag() != null
							&& "1".equals(bean.getAlumniFlag())) {
						cell.setCellValue(new HSSFRichTextString("��"));
					} else {
						cell.setCellValue(new HSSFRichTextString("��"));
					}
					// ��ѧ��
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getStudyYearin() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getStudyYearin()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ��У��
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getStudyYearover() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getStudyYearover()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// Ժϵ
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getStudyAcademy() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getStudyAcademy()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// רҵ
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getStudyMajor() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getStudyMajor()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// �༶
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getStudyClass() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getStudyClass()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ѧ��
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getStudyNum() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getStudyNum()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ѧ��
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getStudyDegree() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getStudyDegree()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ��˾
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getWorkCompany() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getWorkCompany()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ����
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getWorkDepart() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getWorkDepart()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ְλ
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getWorkDuty() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getWorkDuty()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ������Ϣ
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getOrderMemo() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getOrderMemo()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// �������
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getStarNum() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getStarNum().toString()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// �����Ϣ
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getMark() != null) {
						cell.setCellValue(new HSSFRichTextString(bean.getMark()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ��ִ��Ϣ
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getReceipt() != null) {
						cell.setCellValue(new HSSFRichTextString(bean
								.getReceipt()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ��ע
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if (bean.getMemo() != null) {
						cell.setCellValue(new HSSFRichTextString(bean.getMemo()));
					} else {
						cell.setCellValue(new HSSFRichTextString(""));
					}
				}
			}
			// �����ļ�
			String fielCurName = "������Ϣ����"
					+ DateUtil.format(new Date(), "yyyyMMddHHmmss") + ".xls";
			String fileNewName = "������Ϣ����" + ".xls";
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
		// ����
		this.sendResponse(this.getResponse(), ajax);
		return null;
	}

	// ��ʽ���ı�
	private String formatStr(String str) {
		if (str != null && !"".equals(str)) {
			try {
				// ��ѧ������
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

	// ���ڴ���
	public Date getExcelDate(HSSFCell cell) throws Exception {
		String ret = "";
		try {
			if (cell != null) {
				if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					if (HSSFDateUtil.isCellDateFormatted(cell)) {// �жϵ�Ԫ���Ƿ��������ڸ�ʽ
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
		int maxYear = ca.get(Calendar.YEAR) + 10;// �������ܳ������10��
		try {
			int i = Integer.parseInt(s);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date day = format.parse("1900-01-01");// Excel���ָ�ʽ������ʼ����
			ca.setTime(day);
			ca.add(Calendar.DATE, i);// iΪ������ʼ�������ӵ�����
			day = ca.getTime();
			return day;
		} catch (Exception e) {

		}
		return DateUtil.toDate(s, "yyyy-MM-dd HH:mm");
	}

	public boolean isValidDate(String str) {
		boolean convertSuccess = true;
		// ָ�����ڸ�ʽΪ��λ��/��λ�·�/��λ���ڣ�ע��yyyy/MM/dd���ִ�Сд��
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			// ����lenientΪfalse.
			// ����SimpleDateFormat��ȽϿ��ɵ���֤���ڣ�����2007/02/29�ᱻ���ܣ���ת����2007/03/01
			format.setLenient(false);
			format.parse(str);
		} catch (ParseException e) {
			// e.printStackTrace();
			// ���throw java.text.ParseException����NullPointerException����˵����ʽ����
			convertSuccess = false;
		}
		return convertSuccess;
	}
}