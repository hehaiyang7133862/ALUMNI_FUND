package com.laungee.proj.commodity.web.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javassist.expr.NewArray;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbCommodity;
import com.laungee.proj.common.model.TbCommodityDetail;
import com.laungee.proj.common.model.TbCommodityOrder;
import com.laungee.proj.common.model.TbCommodityParam;
import com.laungee.proj.common.model.TbCommodityPic;
import com.laungee.proj.common.model.TbCommodityType;
import com.laungee.proj.common.model.TbZcproj;
import com.laungee.proj.common.model.TbZcprojOption;
import com.laungee.proj.common.model.TbZcprojOrder;
import com.laungee.proj.common.model.TbZcprojPic;
import com.laungee.proj.common.util.DateUtil;
import com.laungee.proj.common.util.MultiRequestUtil;
import com.laungee.proj.common.web.FileManager;

public class CommodityAction extends BaseAction {
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// �˵����
		request.setAttribute("num", request.getAttribute("num"));
		// ���Ա��
		String id = request.getParameter("id");
		if (null == id || "".equals(id)) {
			id = "1";
		}
		// ��ѯ����
		TbCommodityType bean = null;
		try {
			bean = (TbCommodityType) getCommonBo().get(TbCommodityType.class,
					new Long(id));
		} catch (Exception e) {
		}
		request.setAttribute("bean", bean);
		// ��Ŀ����
		String hqlCount = "select count(*) from TbCommodity a where 1=1";
		int count = getCommonBo().getHQLNum(hqlCount);
		request.setAttribute("count", count);
		// ����
		return INPUT;
	}

	// ��Ʒ�б�
	public String list() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ��ѯ����
		String hql = "from TbCommodity a where 1=1";
		List pars = this.getList();
		// ��������
		String commType = request.getParameter("commType");
		if (null != commType && !"".equals(commType)) {
			try {
				List beanList = this.getList();
				beanList.add(commType);
				getCommType(beanList, new Long(commType), "1");
				String typeIds = "";
				if (null != beanList && !beanList.isEmpty()
						&& beanList.size() > 0) {
					for (int i = 0; i < beanList.size(); i++) {
						Long typeId = new Long(beanList.get(i).toString());
						typeIds += typeId;
						if (i != beanList.size() - 1) {
							typeIds += ",";
						}
					}
				}
				if (null != typeIds && !"".equals(typeIds)) {
					hql += " and a.commType in (" + typeIds + ")";
				}
			} catch (Exception e) {
			}
			request.setAttribute("commType", commType);
		}
		// ��Ŀ����
		String hqlCount = "select count(*) " + hql;
		int count = getCommonBo().getHQLNum(hqlCount);
		request.setAttribute("count", count);
		// ����
		String commName = request.getParameter("commName");
		if (null != commName && !"".equals(commName)) {
			hql += " and a.commName like ?";
			pars.add("%" + commName + "%");
			request.setAttribute("commName", commName);
		}
		// �ϼ�
		String isShelves = request.getParameter("isShelves");
		if (null != isShelves && !"".equals(isShelves)) {
			hql += " and a.isShelves=?";
			pars.add(isShelves);
			request.setAttribute("isShelves", isShelves);
		}
		// ����
		String isHot = request.getParameter("isHot");
		if (null != isHot && !"".equals(isHot)) {
			hql += " and a.isHot=?";
			pars.add(isHot);
			request.setAttribute("isHot", isHot);
		}
		// ��� 1���л� 0��ȱ��
		String stockNum = request.getParameter("stockNum");
		if (null != stockNum && !"".equals(stockNum)) {
			hql += " and";
			if (stockNum.equals("1")) {
				request.setAttribute("stockNum", 1);
			} else if (stockNum.equals("0")) {
				hql += " not";
				request.setAttribute("stockNum", 0);
			}
			hql += " exists( select 1 from TbCommodityDetail d where d.commId=a.commId and d.isDelete=0 and d.stockNum>0)";
		}
		hql += " order by a.creationTime";
		List beanList = getCommonBo().findHQLPage(hql, pars);
		request.setAttribute("beanList", beanList);
		// ����
		return "list";
	}

	private void getCommType(List list, Long parent, String type)
			throws Exception {
		String hql = "from TbCommodityType a where a.parentId=" + parent
				+ " order by a.numOrder";
		List beanList = this.getCommonBo().findHQL(hql);
		if (null != beanList && !beanList.isEmpty()) {
			for (int i = 0; i < beanList.size(); i++) {
				TbCommodityType bean = (TbCommodityType) beanList.get(i);
				if (type.equals("1")) {
					list.add(bean.getTypeId() + "");
				} else if (type.equals("2")) {
					list.add(bean);
				}
				getCommType(list, bean.getTypeId(), type);
			}
		}
	}

	public String shelves() throws Exception {
		HttpServletRequest request = this.getRequest();
		TbCommodity bean = null;
		String commId = request.getParameter("commId");
		// ��������
		String commType = request.getParameter("commType");
		request.setAttribute("commType", commType);
		if (null != commId && !"".equals(commId)) {
			try {
				bean = (TbCommodity) this.getCommonBo().get(TbCommodity.class,
						new Long(commId));
			} catch (Exception e) {
			}
		}
		String isShelves = request.getParameter("isShelves");
		if (null != isShelves && !"".equals(isShelves)) {
			bean.setIsShelves(isShelves);
		}
		this.getCommonBo().update(bean);
		return execute();
	}

	public String delete() throws Exception {
		HttpServletRequest request = this.getRequest();
		String commId = request.getParameter("commId");
		TbCommodity bean = null;
		if (null != commId && !"".equals(commId)) {
			try {
				bean = (TbCommodity) this.getCommonBo().get(TbCommodity.class,
						new Long(commId));
			} catch (Exception e) {
			}
		}
		if (null != bean) {
			String hql = "delete from TbCommodityPic a where a.commId="
					+ bean.getCommId();
			this.getCommonBo().executeHql(hql);
			hql = "delete from TbCommodityParam a where a.commId="
					+ bean.getCommId();
			this.getCommonBo().executeHql(hql);
			hql = "delete from TbCommodityDetail a where a.commId="
					+ bean.getCommId();
			this.getCommonBo().executeHql(hql);
			hql = "delete from TbCommodityOrder a where a.commId="
					+ bean.getCommId();
			this.getCommonBo().executeHql(hql);
			this.getCommonBo().delete(bean);
			request.setAttribute("alert", "ɾ���ɹ�");
		} else {
			request.setAttribute("alert", "���󲻴��ڻ��ѱ�ɾ��");
		}
		return list();
	}

	// ����
	public String operation() throws Exception {
		HttpServletRequest request = this.getRequest();
		TbCommodity bean = null;
		String commId = request.getParameter("commId");
		if (null != commId && !"".equals(commId)) {
			try {
				bean = (TbCommodity) this.getCommonBo().get(TbCommodity.class,
						new Long(commId));
			} catch (Exception e) {
			}
		}
		if (bean == null) {
			bean = new TbCommodity();
			bean.setIsShelves("0");
			bean.setCommStyle("1");
			bean.setIsHot("0");
			bean.setIsShipping("0");
			bean.setIsDonation("0");
		}
		request.setAttribute("bean", bean);
		// ��Ʒ���༯��
		String hql = "from TbCommodityType a where a.parentId=1 order by a.numOrder";
		List beanList = this.getList();
		List ctList = this.getCommonBo().findHQL(hql);
		if (null != ctList && !ctList.isEmpty()) {
			for (int i = 0; i < ctList.size(); i++) {
				TbCommodityType ct = (TbCommodityType) ctList.get(i);
				beanList.add(ct);
				List tempList = this.getList();
				getCommType(tempList, ct.getTypeId(), "2");
				if (null != tempList && !tempList.isEmpty()
						&& tempList.size() > 0) {
					for (int j = 0; j < tempList.size(); j++) {
						TbCommodityType temp = (TbCommodityType) tempList
								.get(j);
						TbCommodityType ctTemp = new TbCommodityType();
						ctTemp.setTypeId(temp.getTypeId());
						ctTemp.setTypeName(ct.getTypeName() + " > "
								+ temp.getTypeName());
						beanList.add(ctTemp);
					}
				}
			}
		}
		request.setAttribute("ctList", beanList);
		return "operation";
	}

	public String saveOrUpdate() throws Exception {
		HttpServletRequest request = this.getRequest();
		MultiRequestUtil multiRequest = new MultiRequestUtil(request);

		String commId = multiRequest.getParameter("commId");
		TbCommodity bean = null;
		if (null != commId && !"".equals(commId)) {
			try {
				bean = (TbCommodity) this.getCommonBo().get(TbCommodity.class,
						new Long(commId));
			} catch (Exception e) {
			}
		}
		if (bean == null) {
			bean = new TbCommodity();
			bean.setIsDelete("0");

			bean = (TbCommodity) this.getCommonBo().save(bean);
		}

		// ����
		String commName = multiRequest.getParameter("commName");
		if (null != commName && !"".equals(commName)) {
			bean.setCommName(commName);
		}

		// ��������
		String commType = multiRequest.getParameter("commType");
		if (null != commType && !"".equals(commType)) {
			try {
				bean.setCommType(new Long(commType));
			} catch (Exception e) {
			}
		}

		// �Ƿ�����
		String isHot = multiRequest.getParameter("isHot");
		if (null == isHot || !"1".equals(isHot)) {
			isHot = "0";
		}
		bean.setIsHot(isHot);

		// ��������
		String hotOrder = multiRequest.getParameter("hotOrder");
		if (null != hotOrder && !"".equals(hotOrder)) {
			bean.setHotOrder(hotOrder);
		}

		// �Ƿ��ϼ�
		String isShelves = multiRequest.getParameter("isShelves");
		if (null == isShelves || !"1".equals(isShelves)) {
			isShelves = "0";
		}
		bean.setIsShelves(isShelves);

		// ��Ʒ����
		String commStyle = multiRequest.getParameter("commStyle");
		if (null == commStyle || !"".equals(commStyle)) {
			bean.setCommStyle(commStyle);
		}

		// �ʷ�ѡ��
		if (bean.getCommStyle() != "2") {
			// �Ƿ����˷� 1�����˷ѣ�0���˷�
			String isShipping = multiRequest.getParameter("isShipping");
			if (null == isShipping || !"1".equals(isShipping)) {
				isShipping = "0";
			}
			bean.setIsShipping(isShipping);

			if ("1".equals(isShipping)) {
				// �˷�
				BigDecimal shippingFeeD = null;
				String shippingFee = multiRequest.getParameter("shippingFee");
				if (shippingFee != null && !"".equals(shippingFee)) {
					try {
						shippingFeeD = new BigDecimal(shippingFee).setScale(2,
								4);
					} catch (Exception e) {
					}
				}
				bean.setShippingFee(shippingFeeD);
				// �˷�˵��
				String shippingMemo = multiRequest.getParameter("shippingMemo");
				bean.setShippingMemo(shippingMemo);
			} else {
				bean.setShippingFee(null);
				bean.setShippingMemo(null);
			}
		} else {
			bean.setIsShipping(null);
			bean.setShippingFee(null);
			bean.setShippingMemo(null);
		}

		// �Ƿ���� 1������0������
		String isDonation = multiRequest.getParameter("isDonation");
		if (null == isDonation || !"1".equals(isDonation)) {
			isDonation = "0";
		}
		bean.setIsDonation(isDonation);

		if ("1".equals(isDonation)) {
			// ����
			BigDecimal donationFeeD = null;
			String donationFee = multiRequest.getParameter("donationFee");
			if (donationFee != null && !"".equals(donationFee)) {
				try {
					donationFeeD = new BigDecimal(donationFee).setScale(2, 4);
				} catch (Exception e) {
				}
			}
			bean.setDonationFee(donationFeeD);
			// ����˵��
			String donationMemo = multiRequest.getParameter("donationMemo");
			bean.setDonationMemo(donationMemo);
		} else {
			bean.setDonationFee(null);
			bean.setDonationMemo(null);
		}

		// �ؼ���
		String searchKeys = multiRequest.getParameter("searchKey");
		if (null == searchKeys || !"".equals(searchKeys)) {
			bean.setSearchKey(searchKeys);
		}

		// ���
		String commIntro = multiRequest.getParameter("commIntro");
		if (null == commIntro || !"".equals(commIntro)) {
			bean.setCommIntro(commIntro);
		}

		// ��ƷͼƬ
		FileItem[] picItems = multiRequest.getFiles("picPath");
		String[] picOrders = multiRequest.getParameterValues("picOrder");
		String[] picMemos = multiRequest.getParameterValues("picMemo");
		if (picItems != null && picOrders != null && picMemos != null
				&& picItems.length == picOrders.length
				&& picItems.length == picMemos.length && picItems.length > 0) {
			for (int i = 0; i < picItems.length; i++) {
				FileItem item = picItems[i];
				InputStream bis = null;
				OutputStream out = null;
				try {
					// ��׺��
					String lastName = "";
					int nIndex = item.getName().lastIndexOf(".");
					if (nIndex != -1) {
						lastName = item.getName().substring(nIndex + 1)
								.toLowerCase();
					}
					if ("gif".equals(lastName) || "jpg".equals(lastName)
							|| "jpeg".equals(lastName)
							|| "png".equals(lastName) || "bmp".equals(lastName)) {
						/* �ϴ��ļ� ------------------------------------------- */
						String uploadPath = "upload/image/";
						// �ļ�����Ŀ¼·��
						String savePath = request.getSession()
								.getServletContext()
								.getRealPath("/" + uploadPath)
								+ "/";
						// ���Ŀ¼
						File uploadDir = new File(savePath);
						if (!uploadDir.exists()) {
							uploadDir.mkdirs();
						}
						Date dateNow = this.getCommonBo().getSysDate();
						String ymd = DateUtil.format(dateNow, "yyyyMMdd");
						savePath += ymd + "/";
						uploadPath += ymd + "/";
						File dirFile = new File(savePath);
						if (!dirFile.exists()) {
							dirFile.mkdirs();
						}
						// �ļ�·��
						String fileName = DateUtil.format(dateNow,
								"yyyyMMddHHmmss")
								+ "_"
								+ i
								+ new Random().nextInt(1000) + "." + lastName;
						savePath += fileName;
						uploadPath += fileName;

						bis = new BufferedInputStream(item.getInputStream());
						// �����
						File file = new File(savePath);
						out = new BufferedOutputStream(new FileOutputStream(
								file));
						byte[] bytes = new byte[1];
						while (bis.read(bytes) != -1) {
							out.write(bytes);
						}
						// ��ĿͼƬ
						TbCommodityPic beanPic = new TbCommodityPic();
						beanPic.setCommId(bean.getCommId());
						beanPic.setPicPath(uploadPath);
						beanPic.setNumOrder(picOrders[i]);
						beanPic.setPicPublish("1");
						beanPic.setPicAlt(picMemos[i]);
						// ����ͼƬ
						this.getCommonBo().save(beanPic);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (null != bis) {
							bis.close();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						if (null != out) {
							out.close();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		// ��Ʒ����
		String[] paramNames = multiRequest.getParameterValues("paramName");
		String[] paramValues = multiRequest.getParameterValues("paramValue");
		String[] paramMemos = multiRequest.getParameterValues("paramMemo");
		if (paramNames != null && paramValues != null && paramMemos != null
				&& paramNames.length == paramValues.length
				&& paramValues.length == paramMemos.length
				&& paramNames.length > 0) {
			for (int i = 0; i < paramNames.length; i++) {
				try {
					// ��Ŀѡ��
					TbCommodityParam beanParam = new TbCommodityParam();
					beanParam.setCommId(bean.getCommId());
					beanParam.setParamName(paramNames[i]);
					beanParam.setParamValue(paramValues[i]);
					beanParam.setNumOrder("00" + i);
					beanParam.setMemo(paramMemos[i]);
					beanParam.setParamPublish("1");

					this.getCommonBo().save(beanParam);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		// ��Ʒ�ͺ�
		String[] typeNames = multiRequest.getParameterValues("typeName");
		String[] typeCostFees = multiRequest.getParameterValues("typeCostFee");
		String[] typeSaleFees = multiRequest.getParameterValues("typeSaleFee");
		String[] typeUnitNames = multiRequest
				.getParameterValues("typeUnitName");
		String[] typeIsLimitNums = multiRequest
				.getParameterValues("typeIsLimitNum");
		String[] typeStockNums = multiRequest
				.getParameterValues("typeStockNum");

		if (typeNames != null && typeCostFees != null && typeSaleFees != null
				&& typeUnitNames != null && typeIsLimitNums != null
				&& typeStockNums != null
				&& typeNames.length == typeCostFees.length
				&& typeCostFees.length == typeSaleFees.length
				&& typeSaleFees.length == typeUnitNames.length
				&& typeUnitNames.length == typeIsLimitNums.length
				&& typeIsLimitNums.length == typeStockNums.length
				&& typeNames.length > 0) {
			for (int i = 0; i < typeNames.length; i++) {
				try {
					TbCommodityDetail beanDetail = new TbCommodityDetail();
					beanDetail.setCommId(bean.getCommId());
					beanDetail.setIsDelete("0");
					beanDetail.setNumOrder(String.valueOf(i + 1));
					beanDetail.setDetailCode("00" + String.valueOf(i + 1));
					beanDetail.setDetailName(typeNames[i]);
					if (typeCostFees[i] != null && !"".equals(typeCostFees[i])) {
						beanDetail.setCostFee(new BigDecimal(typeCostFees[i])
								.setScale(2, 4));
					}
					if (typeSaleFees[i] != null && !"".equals(typeSaleFees[i])) {
						beanDetail.setSaleFee(new BigDecimal(typeSaleFees[i])
								.setScale(2, 4));
					}
					beanDetail.setUnitName(typeUnitNames[i]);
					beanDetail.setLimitNum(typeIsLimitNums[i]);
					if (typeStockNums[i] != null
							&& !"".equals(typeStockNums[i])) {
						beanDetail.setStockNum(new Long(typeStockNums[i]));
					}
					beanDetail.setIsShelves("1");
					this.getCommonBo().save(beanDetail);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		// ��Ʒ����
		String[] detailTitles = multiRequest.getParameterValues("detailTitle");
		String[] detailContents = multiRequest
				.getParameterValues("detailContent");
		String[] detailPublishs = multiRequest
				.getParameterValues("detailPublish");
		if (detailTitles != null && detailContents != null
				&& detailPublishs != null
				&& detailTitles.length == detailContents.length
				&& detailTitles.length == detailPublishs.length
				&& detailTitles.length > 0) {
			List detailTitleList = this.getList();
			List detailContentList = this.getList();
			List detailPublishList = this.getList();
			for (int i = 0; i < detailTitles.length; i++) {
				if (detailTitles[i] != null && !"".equals(detailTitles[i])) {
					detailTitleList.add(detailTitles[i]);
					detailContentList.add(detailContents[i]);
					if (detailPublishs[i] == null
							|| !"1".equals(detailPublishs[i])) {
						detailPublishList.add("0");
					} else {
						detailPublishList.add("1");
					}
				}
			}
			for (int i = 0; i < detailTitleList.size(); i++) {
				if (i == 0) {
					bean.setDetailTitle1((String) detailTitleList.get(i));
					bean.setDetailContent1((String) detailContentList.get(i));
					bean.setDetailPublish1((String) detailPublishList.get(i));
				} else if (i == 1) {
					bean.setDetailTitle2((String) detailTitleList.get(i));
					bean.setDetailContent2((String) detailContentList.get(i));
					bean.setDetailPublish2((String) detailPublishList.get(i));
				} else if (i == 2) {
					bean.setDetailTitle3((String) detailTitleList.get(i));
					bean.setDetailContent3((String) detailContentList.get(i));
					bean.setDetailPublish3((String) detailPublishList.get(i));
				} else if (i == 3) {
					bean.setDetailTitle4((String) detailTitleList.get(i));
					bean.setDetailContent4((String) detailContentList.get(i));
					bean.setDetailPublish4((String) detailPublishList.get(i));
				} else if (i == 4) {
					bean.setDetailTitle5((String) detailTitleList.get(i));
					bean.setDetailContent5((String) detailContentList.get(i));
					bean.setDetailPublish5((String) detailPublishList.get(i));
				} else if (i == 5) {
					bean.setDetailTitle6((String) detailTitleList.get(i));
					bean.setDetailContent6((String) detailContentList.get(i));
					bean.setDetailPublish6((String) detailPublishList.get(i));
				} else if (i == 6) {
					bean.setDetailTitle7((String) detailTitleList.get(i));
					bean.setDetailContent7((String) detailContentList.get(i));
					bean.setDetailPublish7((String) detailPublishList.get(i));
				} else if (i == 7) {
					bean.setDetailTitle8((String) detailTitleList.get(i));
					bean.setDetailContent8((String) detailContentList.get(i));
					bean.setDetailPublish8((String) detailPublishList.get(i));
				} else if (i == 8) {
					bean.setDetailTitle9((String) detailTitleList.get(i));
					bean.setDetailContent9((String) detailContentList.get(i));
					bean.setDetailPublish9((String) detailPublishList.get(i));
				} else if (i == 9) {
					bean.setDetailTitle10((String) detailTitleList.get(i));
					bean.setDetailContent10((String) detailContentList.get(i));
					bean.setDetailPublish10((String) detailPublishList.get(i));
				}
			}

			for (int i = detailTitleList.size(); i < 10; i++) {
				if (i == 0) {
					bean.setDetailTitle1(null);
					bean.setDetailContent1(null);
					bean.setDetailPublish1(null);
					bean.setDetailTitle2(null);
					bean.setDetailContent2(null);
					bean.setDetailPublish2(null);
					bean.setDetailTitle3(null);
					bean.setDetailContent3(null);
					bean.setDetailPublish3(null);
					bean.setDetailTitle4(null);
					bean.setDetailContent4(null);
					bean.setDetailPublish4(null);
					bean.setDetailTitle5(null);
					bean.setDetailContent5(null);
					bean.setDetailPublish5(null);
					bean.setDetailTitle6(null);
					bean.setDetailContent6(null);
					bean.setDetailPublish6(null);
					bean.setDetailTitle7(null);
					bean.setDetailContent7(null);
					bean.setDetailPublish7(null);
					bean.setDetailTitle8(null);
					bean.setDetailContent8(null);
					bean.setDetailPublish8(null);
					bean.setDetailTitle9(null);
					bean.setDetailContent9(null);
					bean.setDetailPublish9(null);
					bean.setDetailTitle10(null);
					bean.setDetailContent10(null);
					bean.setDetailPublish10(null);
				} else if (i == 1) {
					bean.setDetailTitle2(null);
					bean.setDetailContent2(null);
					bean.setDetailPublish2(null);
					bean.setDetailTitle3(null);
					bean.setDetailContent3(null);
					bean.setDetailPublish3(null);
					bean.setDetailTitle4(null);
					bean.setDetailContent4(null);
					bean.setDetailPublish4(null);
					bean.setDetailTitle5(null);
					bean.setDetailContent5(null);
					bean.setDetailPublish5(null);
					bean.setDetailTitle6(null);
					bean.setDetailContent6(null);
					bean.setDetailPublish6(null);
					bean.setDetailTitle7(null);
					bean.setDetailContent7(null);
					bean.setDetailPublish7(null);
					bean.setDetailTitle8(null);
					bean.setDetailContent8(null);
					bean.setDetailPublish8(null);
					bean.setDetailTitle9(null);
					bean.setDetailContent9(null);
					bean.setDetailPublish9(null);
					bean.setDetailTitle10(null);
					bean.setDetailContent10(null);
					bean.setDetailPublish10(null);
				} else if (i == 2) {
					bean.setDetailTitle3(null);
					bean.setDetailContent3(null);
					bean.setDetailPublish3(null);
					bean.setDetailTitle4(null);
					bean.setDetailContent4(null);
					bean.setDetailPublish4(null);
					bean.setDetailTitle5(null);
					bean.setDetailContent5(null);
					bean.setDetailPublish5(null);
					bean.setDetailTitle6(null);
					bean.setDetailContent6(null);
					bean.setDetailPublish6(null);
					bean.setDetailTitle7(null);
					bean.setDetailContent7(null);
					bean.setDetailPublish7(null);
					bean.setDetailTitle8(null);
					bean.setDetailContent8(null);
					bean.setDetailPublish8(null);
					bean.setDetailTitle9(null);
					bean.setDetailContent9(null);
					bean.setDetailPublish9(null);
					bean.setDetailTitle10(null);
					bean.setDetailContent10(null);
					bean.setDetailPublish10(null);
				} else if (i == 3) {
					bean.setDetailTitle4(null);
					bean.setDetailContent4(null);
					bean.setDetailPublish4(null);
					bean.setDetailTitle5(null);
					bean.setDetailContent5(null);
					bean.setDetailPublish5(null);
					bean.setDetailTitle6(null);
					bean.setDetailContent6(null);
					bean.setDetailPublish6(null);
					bean.setDetailTitle7(null);
					bean.setDetailContent7(null);
					bean.setDetailPublish7(null);
					bean.setDetailTitle8(null);
					bean.setDetailContent8(null);
					bean.setDetailPublish8(null);
					bean.setDetailTitle9(null);
					bean.setDetailContent9(null);
					bean.setDetailPublish9(null);
					bean.setDetailTitle10(null);
					bean.setDetailContent10(null);
					bean.setDetailPublish10(null);
				} else if (i == 4) {
					bean.setDetailTitle5(null);
					bean.setDetailContent5(null);
					bean.setDetailPublish5(null);
					bean.setDetailTitle6(null);
					bean.setDetailContent6(null);
					bean.setDetailPublish6(null);
					bean.setDetailTitle7(null);
					bean.setDetailContent7(null);
					bean.setDetailPublish7(null);
					bean.setDetailTitle8(null);
					bean.setDetailContent8(null);
					bean.setDetailPublish8(null);
					bean.setDetailTitle9(null);
					bean.setDetailContent9(null);
					bean.setDetailPublish9(null);
					bean.setDetailTitle10(null);
					bean.setDetailContent10(null);
					bean.setDetailPublish10(null);
				} else if (i == 5) {
					bean.setDetailTitle6(null);
					bean.setDetailContent6(null);
					bean.setDetailPublish6(null);
					bean.setDetailTitle7(null);
					bean.setDetailContent7(null);
					bean.setDetailPublish7(null);
					bean.setDetailTitle8(null);
					bean.setDetailContent8(null);
					bean.setDetailPublish8(null);
					bean.setDetailTitle9(null);
					bean.setDetailContent9(null);
					bean.setDetailPublish9(null);
					bean.setDetailTitle10(null);
					bean.setDetailContent10(null);
					bean.setDetailPublish10(null);
				} else if (i == 6) {
					bean.setDetailTitle7(null);
					bean.setDetailContent7(null);
					bean.setDetailPublish7(null);
					bean.setDetailTitle8(null);
					bean.setDetailContent8(null);
					bean.setDetailPublish8(null);
					bean.setDetailTitle9(null);
					bean.setDetailContent9(null);
					bean.setDetailPublish9(null);
					bean.setDetailTitle10(null);
					bean.setDetailContent10(null);
					bean.setDetailPublish10(null);
				} else if (i == 7) {
					bean.setDetailTitle8(null);
					bean.setDetailContent8(null);
					bean.setDetailPublish8(null);
					bean.setDetailTitle9(null);
					bean.setDetailContent9(null);
					bean.setDetailPublish9(null);
					bean.setDetailTitle10(null);
					bean.setDetailContent10(null);
					bean.setDetailPublish10(null);
				} else if (i == 8) {
					bean.setDetailTitle9(null);
					bean.setDetailContent9(null);
					bean.setDetailPublish9(null);
					bean.setDetailTitle10(null);
					bean.setDetailContent10(null);
					bean.setDetailPublish10(null);
				} else if (i == 9) {
					bean.setDetailTitle10(null);
					bean.setDetailContent10(null);
					bean.setDetailPublish10(null);
				}
			}
		}
		// ��ע˵��
		String memo = multiRequest.getParameter("memo");
		bean.setMemo(memo);

		try {
			bean = (TbCommodity) this.getCommonBo().update(bean);

			// ���¸������
			String relIds = multiRequest.getParameter("relIds");
			FileManager.getInstance().update(bean.getCommId(), relIds);
		} catch (Exception e) {
			System.out.println("����ʧ��");
		}

		// ��Ʒ���༯��
		String hql = "from TbCommodityType a where a.parentId=1 order by a.numOrder";
		List beanList = this.getList();
		List ctList = this.getCommonBo().findHQL(hql);
		if (null != ctList && !ctList.isEmpty()) {
			for (int i = 0; i < ctList.size(); i++) {
				TbCommodityType ct = (TbCommodityType) ctList.get(i);
				beanList.add(ct);
				List tempList = this.getList();
				getCommType(tempList, ct.getTypeId(), "2");
				if (null != tempList && !tempList.isEmpty()
						&& tempList.size() > 0) {
					for (int j = 0; j < tempList.size(); j++) {
						TbCommodityType temp = (TbCommodityType) tempList
								.get(j);
						TbCommodityType ctTemp = new TbCommodityType();
						ctTemp.setTypeId(temp.getTypeId());
						ctTemp.setTypeName(ct.getTypeName() + " > "
								+ temp.getTypeName());
						beanList.add(ctTemp);
					}
				}
			}
		}

		System.out.println("7");
		request.setAttribute("bean", bean);
		request.setAttribute("ctList", beanList);
		return "success";
	}

	public String info() throws Exception {
		HttpServletRequest request = this.getRequest();
		String num = request.getParameter("num");
		String publish = request.getParameter("publish");
		request.setAttribute("num", num);
		// ����
		String commId = request.getParameter("commId");
		if (null == num || "".equals(num)) {
			num = "1";
		}
		TbCommodity bean = null;
		if (null != commId && !"".equals(commId)) {
			try {
				bean = (TbCommodity) this.getCommonBo().get(TbCommodity.class,
						new Long(commId));
			} catch (Exception e) {
			}
		}
		request.setAttribute("bean", bean);

		if (bean != null) {
			if (num.equals("1")) {// ��ƷͼƬ
				String hql = "from TbCommodityPic a where a.commId="
						+ bean.getCommId();
				if (publish != null && "1".equals(publish)) {
					hql += " and a.picPublish='1'";
				} else if (publish != null && !"".equals(publish)) {
					hql += " and (a.picPublish is null or a.picPublish<>1)";
				}
				request.setAttribute("publish", publish);

				hql += " order by a.numOrder";
				List beanList = this.getCommonBo().findHQL(hql);
				request.setAttribute("beanList", beanList);
				return "pic";
			} else if (num.equals("2")) {// ��Ʒ����
				String hql = "from TbCommodityParam a where a.commId="
						+ bean.getCommId();
				if (publish != null && "1".equals(publish)) {
					hql += " and a.paramPublish='1'";
				} else if (publish != null && !"".equals(publish)) {
					hql += " and (a.paramPublish is null or a.paramPublish<>1)";
				}
				request.setAttribute("publish", publish);

				hql += " order by a.numOrder";
				List beanList = this.getCommonBo().findHQL(hql);
				request.setAttribute("beanList", beanList);
				return "param";
			} else if (num.equals("3")) {// ��Ʒ�ͺ�
				String hql = "from TbCommodityDetail a where a.commId="
						+ bean.getCommId();
				// ����
				String detailName = request.getParameter("detailName");
				if (null != detailName && !"".equals(detailName)) {
					hql += " and a.detailName like '%" + detailName + "%'";
					request.setAttribute("detailName", detailName);
				}
				String stockNum = request.getParameter("stockNum");
				if (null != stockNum && !"".equals(stockNum)) {
					if ("1".equals(stockNum)) {
						hql += " and a.stockNum>0";
						request.setAttribute("stockNum", 1);
					} else if ("0".equals(stockNum)) {
						hql += " and (a.stockNum=0 or a.stockNum is null)";
						request.setAttribute("stockNum", 0);
					}
				}
				String isShelves = request.getParameter("isShelves");
				if (null != isShelves && !"".equals(isShelves)) {
					hql += " and a.isShelves=" + isShelves;
					request.setAttribute("isShelves", isShelves);
				}
				hql += " order by a.numOrder";
				List beanList = this.getCommonBo().findHQLPage(hql);
				request.setAttribute("beanList", beanList);
				return "detail";
			} else if (num.equals("4")) {// ��Ʒ����
				List pars = this.getList();
				String hql = "from TbCommodityOrder a where 1=1";

				hql += " and a.commId = ?";
				pars.add(bean.getCommId());

				String orderNo = request.getParameter("orderNo");
				if (null != orderNo && !"".equals(orderNo)) {
					hql += " and a.orderNo like ?";
					pars.add("%" + orderNo + "%");
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
					orderTimeBegD = DateUtil.toDate(orderTimeBeg,
							"yyyy-MM-dd HH:mm");
				}
				if (orderTimeBegD != null) {
					hql += " and a.orderTime > ?";
					pars.add(orderTimeBegD);
				}

				// �ύ��ֹʱ��
				Date orderTimeEndD = null;
				String orderTimeEnd = request.getParameter("orderTimeEnd");
				if (orderTimeEnd != null && !"".equals(orderTimeEnd)) {
					orderTimeEndD = DateUtil.toDate(orderTimeEnd,
							"yyyy-MM-dd HH:mm");
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
				String orderType = request.getParameter("orderType");
				if (orderType != null && !"".equals(orderType)) {
					hql += " and a.orderType like ?";
					pars.add("%" + orderType + "%");
				}

				// ����״̬
				String orderStatus = request.getParameter("orderStatus");
				if (orderStatus != null && "1".equals(orderStatus)) {
					hql += " and a.orderStatus = ?";
					pars.add("1");
				} else if (orderStatus != null && !"".equals(orderStatus)) {
					hql += " and (a.orderStatus is null or a.orderStatus <> ?)";
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

				// ��ѯ�ϼ�����
				Long orderCount = (Long) this.getCommonBo().getHQLUnique(
						"select count(*) " + hql, pars);
				request.setAttribute("orderCount", orderCount);

				// ��ѯ�ϼ��Ѹ�������
				Long orderOkCount = (Long) this.getCommonBo().getHQLUnique(
						"select count(*) " + hql + " and a.orderStatus = '1'",
						pars);
				request.setAttribute("orderOkCount", orderOkCount);
				// ��ѯ�ϼ��Ѹ����ܽ��
				BigDecimal orderOkAmount = (BigDecimal) this.getCommonBo()
						.getHQLUnique(
								"select sum(a.orderFee) " + hql
										+ " and a.orderStatus = '1'", pars);
				request.setAttribute("orderOkAmount", orderOkAmount);

				hql += " order by a.orderTime desc";
				List orderList = this.getCommonBo().findHQLPage(hql, pars);
				request.setAttribute("orderList", orderList);
				return "orderList";
			}
		}
		return operation();
	}

	// ����ͼƬ
	public String operationPic() throws Exception {
		HttpServletRequest request = this.getRequest();
		String picId = request.getParameter("picId");
		TbCommodityPic bean = null;
		if (null != picId && !"".equals(picId)) {
			try {
				bean = (TbCommodityPic) this.getCommonBo().get(
						TbCommodityPic.class, new Long(picId));
			} catch (Exception e) {
			}
		}
		if (bean == null) {
			bean = new TbCommodityPic();
			// ��ƷId
			Long commIdL = null;
			String commId = request.getParameter("commId");
			if (commId != null && !"".equals(commId)) {
				try {
					commIdL = new Long(commId);
				} catch (Exception e) {
				}
			}
			if (commIdL != null) {
				bean.setCommId(commIdL);
				// ͼƬ����
				String hqlCount = "select count(*) from TbCommodityPic a where a.commId="
						+ commIdL;
				int count = this.getCommonBo().getHQLNum(hqlCount);
				bean.setNumOrder((count + 1) + "");
			}
		}
		request.setAttribute("bean", bean);
		return "operationPic";
	}

	public String saveOrUpdatePic() throws Exception {
		HttpServletRequest request = this.getRequest();
		MultiRequestUtil multiRequest = new MultiRequestUtil(request);
		// ��ƷͼƬ
		TbCommodityPic bean = null;
		// ��ƷͼƬID
		String picId = multiRequest.getParameter("picId");
		if (picId != null && !"".equals(picId)) {
			try {
				bean = (TbCommodityPic) this.getCommonBo().get(
						TbCommodityPic.class, new Long(picId));
			} catch (Exception e) {
			}
		}
		if (bean == null) {
			bean = new TbCommodityPic();
			// ��ƷId
			Long commIdL = null;
			String commId = multiRequest.getParameter("commId");
			if (commId != null && !"".equals(commId)) {
				try {
					commIdL = new Long(commId);
				} catch (Exception e) {
				}
			}
			bean.setCommId(commIdL);
		}
		if (bean.getCommId() != null) {
			// ͼƬ�ϴ�
			FileItem item = multiRequest.getFile("pic");
			if (item != null) {
				InputStream bis = null;
				OutputStream out = null;
				try {
					// ��׺��
					String lastName = "";
					int nIndex = item.getName().lastIndexOf(".");
					if (nIndex != -1) {
						lastName = item.getName().substring(nIndex + 1)
								.toLowerCase();
					}
					if ("gif".equals(lastName) || "jpg".equals(lastName)
							|| "jpeg".equals(lastName)
							|| "png".equals(lastName) || "bmp".equals(lastName)) {
						/* �ϴ��ļ� ------------------------------------------- */
						String uploadPath = "upload/image/";
						// �ļ�����Ŀ¼·��
						String savePath = request.getSession()
								.getServletContext()
								.getRealPath("/" + uploadPath)
								+ "/";
						// ���Ŀ¼
						File uploadDir = new File(savePath);
						if (!uploadDir.exists()) {
							uploadDir.mkdirs();
						}
						Date dateNow = this.getCommonBo().getSysDate();
						String ymd = DateUtil.format(dateNow, "yyyyMMdd");
						savePath += ymd + "/";
						uploadPath += ymd + "/";
						File dirFile = new File(savePath);
						if (!dirFile.exists()) {
							dirFile.mkdirs();
						}
						// �ļ�·��
						String fileName = DateUtil.format(dateNow,
								"yyyyMMddHHmmss") + "." + lastName;
						savePath += fileName;
						uploadPath += fileName;

						bis = new BufferedInputStream(item.getInputStream());
						// �����
						File file = new File(savePath);
						out = new BufferedOutputStream(new FileOutputStream(
								file));
						byte[] bytes = new byte[1];
						while (bis.read(bytes) != -1) {
							out.write(bytes);
						}
						// ��ĿͼƬ
						bean.setPicPath(uploadPath);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (null != bis) {
							bis.close();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						if (null != out) {
							out.close();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			// ͼƬ����
			String numOrder = multiRequest.getParameter("numOrder");
			bean.setNumOrder(numOrder);
			String picPublish = multiRequest.getParameter("picPublish");
			if (picPublish != null && !picPublish.equals("1")) {
				bean.setPicPublish("0");
			} else {
				bean.setPicPublish("1");
			}
			// ͼƬ˵��
			String picAlt = multiRequest.getParameter("picAlt");
			bean.setPicAlt(picAlt);
			// ��ע˵��
			String memo = multiRequest.getParameter("memo");
			bean.setMemo(memo);
			// ����ͼƬ
			try {
				this.getCommonBo().saveOrUpdate(bean);
			} catch (Exception e) {
				// ����
				request.setAttribute("alert", "����ʱ�����쳣��");
				request.setAttribute("bean", bean);
				return "operationPic";
			}
		}
		// ����
		return "winSuccess";
	}

	// ɾ��ͼƬ
	public String deletePic() throws Exception {
		HttpServletRequest request = this.getRequest();
		String num = request.getParameter("num");
		if (null == num || "".equals(num)) {
			num = "1";
		}
		request.setAttribute("num", num);
		String picId = request.getParameter("picId");
		TbCommodityPic bean = null;
		if (null != picId && !"".equals(picId)) {
			try {
				bean = (TbCommodityPic) this.getCommonBo().get(
						TbCommodityPic.class, new Long(picId));
			} catch (Exception e) {
			}
		}
		if (bean != null) {
			this.getCommonBo().delete(bean);
		}
		return info();
	}

	// ��������
	public String operationParam() throws Exception {
		HttpServletRequest request = this.getRequest();
		String paramId = request.getParameter("paramId");
		TbCommodityParam bean = null;
		if (null != paramId && !"".equals(paramId)) {
			try {
				bean = (TbCommodityParam) this.getCommonBo().get(
						TbCommodityParam.class, new Long(paramId));
			} catch (Exception e) {
			}
		}
		if (bean == null) {
			bean = new TbCommodityParam();
			// ��ƷId
			Long commIdL = null;
			String commId = request.getParameter("commId");
			if (commId != null && !"".equals(commId)) {
				try {
					commIdL = new Long(commId);
				} catch (Exception e) {
				}
			}
			if (commIdL != null) {
				bean.setCommId(commIdL);
				// ͼƬ����
				String hqlCount = "select count(*) from TbCommodityParam a where a.commId="
						+ commIdL;
				int count = this.getCommonBo().getHQLNum(hqlCount);
				bean.setNumOrder((count + 1) + "");
			}
		}
		request.setAttribute("bean", bean);
		return "operationParam";
	}

	// ��������
	public String saveOrUpdateParam() throws Exception {
		HttpServletRequest request = this.getRequest();
		String paramId = request.getParameter("paramId");
		TbCommodityParam bean = null;
		if (null != paramId && !"".equals(paramId)) {
			try {
				bean = (TbCommodityParam) this.getCommonBo().get(
						TbCommodityParam.class, new Long(paramId));
			} catch (Exception e) {
			}
		}
		if (bean == null) {
			bean = new TbCommodityParam();
			// ��ƷId
			Long commIdL = null;
			String commId = request.getParameter("commId");
			if (commId != null && !"".equals(commId)) {
				try {
					commIdL = new Long(commId);
				} catch (Exception e) {
				}
			}
			bean.setCommId(commIdL);
		}
		if (bean.getCommId() != null) {
			String paramName = request.getParameter("paramName");
			bean.setParamName(paramName);
			String paramValue = request.getParameter("paramValue");
			bean.setParamValue(paramValue);
			String paramPublish = request.getParameter("paramPublish");
			if (paramPublish != null && !paramPublish.equals("1")) {
				bean.setParamPublish("0");
			} else {
				bean.setParamPublish("1");
			}
			String numOrder = request.getParameter("numOrder");
			bean.setNumOrder(numOrder);
			String memo = request.getParameter("memo");
			bean.setMemo(memo);
			// ����
			try {
				this.getCommonBo().saveOrUpdate(bean);
			} catch (Exception e) {
				// ����
				request.setAttribute("alert", "����ʱ�����쳣��");
				request.setAttribute("bean", bean);
				return "operationParam";
			}
		}
		// ����
		return "winSuccess";
	}

	// ɾ��ͼƬ
	public String deleteParam() throws Exception {
		HttpServletRequest request = this.getRequest();
		String paramId = request.getParameter("paramId");
		TbCommodityParam bean = null;
		if (null != paramId && !"".equals(paramId)) {
			try {
				bean = (TbCommodityParam) this.getCommonBo().get(
						TbCommodityParam.class, new Long(paramId));
			} catch (Exception e) {
			}
		}
		if (bean != null) {
			this.getCommonBo().delete(bean);
		}

		return info();
	}

	// ��������
	public String operationDetail() throws Exception {
		HttpServletRequest request = this.getRequest();
		String detailId = request.getParameter("detailId");
		TbCommodityDetail bean = null;
		if (null != detailId && !"".equals(detailId)) {
			try {
				bean = (TbCommodityDetail) this.getCommonBo().get(
						TbCommodityDetail.class, new Long(detailId));
			} catch (Exception e) {
			}
		}
		if (bean == null) {
			bean = new TbCommodityDetail();
			// ��ƷId
			Long commIdL = null;
			String commId = request.getParameter("commId");
			if (commId != null && !"".equals(commId)) {
				try {
					commIdL = new Long(commId);
				} catch (Exception e) {
				}
			}
			if (commIdL != null) {
				bean.setCommId(commIdL);
				// ����
				String hqlCount = "select count(*) from TbCommodityDetail a where a.commId="
						+ commIdL;
				int count = this.getCommonBo().getHQLNum(hqlCount);
				bean.setDetailCode("00" + (count + 1));
			}
			bean.setIsShelves("0");
			bean.setIsDelete("0");
			bean.setLimitNum("0");
		}
		request.setAttribute("bean", bean);
		return "operationDetail";
	}

	public String saveOrUpdateDetail() throws Exception {
		HttpServletRequest request = this.getRequest();
		MultiRequestUtil multiRequest = new MultiRequestUtil(request);

		String detailId = multiRequest.getParameter("detailId");
		TbCommodityDetail bean = null;
		if (null != detailId && !"".equals(detailId)) {
			try {
				bean = (TbCommodityDetail) this.getCommonBo().get(
						TbCommodityDetail.class, new Long(detailId));
			} catch (Exception e) {
			}
		}
		if (bean == null) {
			bean = new TbCommodityDetail();
			// ��ƷId
			Long commIdL = null;
			String commId = multiRequest.getParameter("commId");
			if (commId != null && !"".equals(commId)) {
				try {
					commIdL = new Long(commId);
				} catch (Exception e) {
				}
			}
			bean.setCommId(commIdL);
			bean.setIsDelete("0");
		}
		if (bean.getCommId() != null) {
			// ����
			String detailName = multiRequest.getParameter("detailName");
			bean.setDetailName(detailName);
			// ���
			String detailIntro = multiRequest.getParameter("detailIntro");
			bean.setDetailIntro(detailIntro);
			// �Ƿ���������
			String limitNum = multiRequest.getParameter("limitNum");
			if (null != limitNum && !"".equals(limitNum)) {
				bean.setLimitNum(limitNum);
			}

			// ���
			Long stockNumL = null;
			String stockNum = multiRequest.getParameter("stockNum");
			if (null != stockNum && !"".equals(stockNum)) {
				try {
					stockNumL = new Long(stockNum);
				} catch (Exception e1) {
				}
				bean.setStockNum(stockNumL);
			}

			// �ɱ���
			BigDecimal costFeeD = null;
			String costFee = multiRequest.getParameter("costFee");
			if (costFee != null && !"".equals(costFee)) {
				try {
					costFeeD = new BigDecimal(costFee).setScale(2, 4);
				} catch (Exception e) {
				}
			}
			bean.setCostFee(costFeeD);
			// �ۼ�
			BigDecimal saleFeeD = null;
			String saleFee = multiRequest.getParameter("saleFee");
			if (saleFee != null && !"".equals(saleFee)) {
				try {
					saleFeeD = new BigDecimal(saleFee).setScale(2, 4);
				} catch (Exception e) {
				}
			}
			bean.setSaleFee(saleFeeD);
			// ͼƬ�ϴ�
			FileItem item = multiRequest.getFile("detailPic");
			if (item != null) {
				InputStream bis = null;
				OutputStream out = null;
				try {
					// ��׺��
					String lastName = "";
					int nIndex = item.getName().lastIndexOf(".");
					if (nIndex != -1) {
						lastName = item.getName().substring(nIndex + 1)
								.toLowerCase();
					}
					if ("gif".equals(lastName) || "jpg".equals(lastName)
							|| "jpeg".equals(lastName)
							|| "png".equals(lastName) || "bmp".equals(lastName)) {
						/* �ϴ��ļ� ------------------------------------------- */
						String uploadPath = "upload/image/";
						// �ļ�����Ŀ¼·��
						String savePath = request.getSession()
								.getServletContext()
								.getRealPath("/" + uploadPath)
								+ "/";
						// ���Ŀ¼
						File uploadDir = new File(savePath);
						if (!uploadDir.exists()) {
							uploadDir.mkdirs();
						}
						Date dateNow = this.getCommonBo().getSysDate();
						String ymd = DateUtil.format(dateNow, "yyyyMMdd");
						savePath += ymd + "/";
						uploadPath += ymd + "/";
						File dirFile = new File(savePath);
						if (!dirFile.exists()) {
							dirFile.mkdirs();
						}
						// �ļ�·��
						String fileName = DateUtil.format(dateNow,
								"yyyyMMddHHmmss") + "." + lastName;
						savePath += fileName;
						uploadPath += fileName;

						bis = new BufferedInputStream(item.getInputStream());
						// �����
						File file = new File(savePath);
						out = new BufferedOutputStream(new FileOutputStream(
								file));
						byte[] bytes = new byte[1];
						while (bis.read(bytes) != -1) {
							out.write(bytes);
						}
						// ͼƬ
						bean.setDetailPic(uploadPath);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (null != bis) {
							bis.close();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						if (null != out) {
							out.close();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			String isShelves = multiRequest.getParameter("isShelves");
			if (isShelves == null || !"1".equals(isShelves)) {
				isShelves = "0";
			}

			// ����
			String detailCode = multiRequest.getParameter("detailCode");
			bean.setDetailCode(detailCode);

			bean.setIsShelves(isShelves);
			String numOrder = multiRequest.getParameter("numOrder");
			bean.setNumOrder(numOrder);
			String memo = multiRequest.getParameter("memo");
			bean.setMemo(memo);
			// ����
			try {
				this.getCommonBo().saveOrUpdate(bean);
			} catch (Exception e) {
				// ����
				request.setAttribute("alert", "����ʱ�����쳣��");
				request.setAttribute("bean", bean);
				return "operationDetail";
			}
		}
		// ����
		return "winSuccess";
	}

	// ɾ����Ʒ�ͺ�
	public String deleteDetail() throws Exception {
		HttpServletRequest request = this.getRequest();
		String detailId = request.getParameter("detailId");
		TbCommodityDetail bean = null;
		if (null != detailId && !"".equals(detailId)) {
			try {
				bean = (TbCommodityDetail) this.getCommonBo().get(
						TbCommodityDetail.class, new Long(detailId));
			} catch (Exception e) {
			}
		}
		if (bean != null) {
			this.getCommonBo().delete(bean);
		}
		return info();
	}

	// ��Ʒ��������ҳ��
	public String orderView() throws Exception {
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
		return "orderView";
	}

	// ��Ʒ�������ҳ��
	public String orderMark() throws Exception {
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
		return "orderMark";
	}

	// ��Ʒ������չ���
	public String orderMarkUpdate() throws Exception {
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
				return "orderMark";
			}
		}
		// ����
		return "winSuccess";
	}

	// ɾ������
	public String deleteOrder() throws Exception {
		HttpServletRequest request = this.getRequest();
		TbCommodityOrder bean = null;
		String orderId = request.getParameter("orderId");
		if (null != orderId && !"".equals(orderId)) {
			try {
				bean = (TbCommodityOrder) this.getCommonBo().get(
						TbCommodityOrder.class, new Long(orderId));
			} catch (Exception e) {
			}
		}
		if (bean != null) {
			this.getCommonBo().delete(bean);
		}
		return info();
	}

	public String ajax() throws Exception {
		HttpServletRequest request = this.getRequest();
		// Ajax
		String ajax = "{\"result\":\"error\"}";
		String html = "<option value=''>��ѡ����Ʒ�ͺ�</option>";
		String commId = request.getParameter("commId");
		String hql = "from TbCommodityDetail a where a.isDelete=0";
		if (null != commId && !"".equals(commId)) {
			try {
				hql += " and a.commId=" + new Long(commId);
			} catch (Exception e) {
			}
		}
		hql += " order by a.detailName";
		List beanList = this.getCommonBo().findHQL(hql);
		if (null != beanList && !beanList.isEmpty() && beanList.size() > 0) {
			for (int i = 0; i < beanList.size(); i++) {
				TbCommodityDetail bean = (TbCommodityDetail) beanList.get(i);
				html += "<option value='" + bean.getDetailId() + "'>"
						+ bean.getDetailName() + "</option>";
			}
		}
		ajax = "{\"result\":\"success\",\"detail\":\"" + html + "\"}";
		this.sendResponse(this.getResponse(), ajax);
		// ����
		return null;
	}

	public String ajaxDetail() throws Exception {
		HttpServletRequest request = this.getRequest();
		// Ajax
		String ajax = "{\"result\":\"error\"}";
		String detailId = request.getParameter("detailId");
		TbCommodityDetail bean = null;
		if (null != detailId && !"".equals(detailId)) {
			try {
				bean = (TbCommodityDetail) this.getCommonBo().get(
						TbCommodityDetail.class, new Long(detailId));
			} catch (Exception e) {
			}
		}
		BigDecimal shippingFee = new BigDecimal(0);
		if (null != bean) {
			if (bean.getTbCommodity() != null) {
				String isShipping = bean.getTbCommodity().getIsShipping();
				if (null != isShipping && isShipping.equals("0")) {
					shippingFee = bean.getTbCommodity().getShippingFee();

				}
			}
		}
		ajax = "{\"result\":\"success\",\"saleFee\":\"" + bean.getSaleFee()
				+ "\",\"shippingFee\":\"" + shippingFee + "\"}";
		System.out.println(ajax);
		this.sendResponse(this.getResponse(), ajax);
		// ����
		return null;
	}
	
	public String ajaxGetDetailPage() throws Exception {
		HttpServletRequest request = this.getRequest();
		TbCommodity beanComm = null;

		String commId = request.getParameter("commId");
		String index = request.getParameter("index");
		if (null != commId && !"".equals(commId)) {
			try {
				beanComm = (TbCommodity) this.getCommonBo().get(
						TbCommodity.class, new Long(commId));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		String detail = "";
		if ((null != beanComm) && null != index && !"".equals(index)
				&& StringUtils.isNumeric(index)) {
			switch (Integer.parseInt(index)) {
			case 2:
				detail = beanComm.getDetailContent2();
				break;
			case 3:
				detail = beanComm.getDetailContent3();
				break;
			case 4:
				detail = beanComm.getDetailContent4();
				break;
			case 5:
				detail = beanComm.getDetailContent5();
				break;
			case 6:
				detail = beanComm.getDetailContent6();
				break;
			case 7:
				detail = beanComm.getDetailContent7();
				break;
			case 8:
				detail = beanComm.getDetailContent8();
				break;
			case 9:
				detail = beanComm.getDetailContent9();
				break;
			case 10:
				detail = beanComm.getDetailContent10();
				break;
			default:
				detail = beanComm.getDetailContent1();
				break;
			}
		}
		this.sendResponse(this.getResponse(), detail);
		return null;
	}
}