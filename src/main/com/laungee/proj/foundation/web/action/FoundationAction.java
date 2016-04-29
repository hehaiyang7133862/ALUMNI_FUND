package com.laungee.proj.foundation.web.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbAcceptCompany;
import com.laungee.proj.common.model.TbCommodity;
import com.laungee.proj.common.model.TbFields;
import com.laungee.proj.common.model.TbFoundType;
import com.laungee.proj.common.model.TbFoundation;
import com.laungee.proj.common.model.TbProtocolParam;
import com.laungee.proj.common.model.TbProtocolPic;
import com.laungee.proj.common.model.TbProDetail;
import com.laungee.proj.common.model.TbProPay;
import com.laungee.proj.common.model.TbProtocol;
import com.laungee.proj.common.util.DateUtil;
import com.laungee.proj.common.util.MultiRequestUtil;
import com.laungee.proj.common.util.StringUtils;
import com.laungee.proj.common.web.FileManager;

/**
 * 基金项目管理|项目协议管理|项目支出管理
 * 
 * @author hexiang
 * 
 */
public class FoundationAction extends BaseAction {
	// 项目分类树
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 分类根目录编号
		String id = request.getParameter("id");
		if (null == id || "".equals(id)) {
			id = "1";
		}
		// 分类根目录
		TbFoundType bean = null;
		try {
			bean = (TbFoundType) getCommonBo().get(TbFoundType.class,
					new Long(id));
		} catch (Exception e) {
		}
		request.setAttribute("bean", bean);
		// 项目类别
		TbFields beanType = null;
		String type = request.getParameter("type");
		if (type != null && !"".equals(type)) {
			String hql = "from TbFields a where a.tbField.code='FOUNDATION_TYPE' and a.code='"
					+ type + "'";
			beanType = (TbFields) this.getCommonBo().getHQLUnique(hql);
		}
		request.setAttribute("beanType", beanType);
		// 项目数量
		String hqlCount = "select count(*) from TbFoundation a where 1=1";
		if (beanType != null) {
			// 总数量
			int count = getCommonBo().getHQLNum(
					hqlCount + " and a.foundtypeFid='" + beanType.getFieldId()
							+ "'");
			request.setAttribute("count", count);
		} else {
			// 总数量
			int count = getCommonBo().getHQLNum(hqlCount);
			request.setAttribute("count", count);
			// 详细数量
			String hql = "from TbFields a where a.tbField.code='FOUNDATION_TYPE' order by a.numOrder";
			List beanTypeList = this.getCommonBo().findHQL(hql);
			List beanTypeCountList = this.getList();
			if (beanTypeList != null && !beanTypeList.isEmpty()) {
				for (int i = 0; i < beanTypeList.size(); i++) {
					TbFields obj = (TbFields) beanTypeList.get(i);
					int objcount = getCommonBo().getHQLNum(
							hqlCount + " and a.foundtypeFid='"
									+ obj.getFieldId() + "'");
					beanTypeCountList.add(objcount);
				}
			}
			request.setAttribute("beanTypeList", beanTypeList);
			request.setAttribute("beanTypeCountList", beanTypeCountList);

		}
		// 返回
		return "typeIndex";
	}

	// 项目分类树JSON
	public String typeJson() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 分类编号
		String id = request.getParameter("id");
		// 查询
		try {
			// 分类子目录集合
			String hql = "from TbFoundType a where a.parentId=" + id
					+ " order by a.numOrder";
			List eleList = getCommonBo().findHQL(hql);
			request.setAttribute("list_ele", eleList);
			// 分类子目录项目总数
			List countList = getList();
			List countsList = getList();
			if (eleList != null && !eleList.isEmpty()) {
				// 项目类别
				TbFields beanType = null;
				List beanTypeList = null;
				String type = request.getParameter("type");
				if (type != null && !"".equals(type)) {
					beanType = (TbFields) getCommonBo().get(TbFields.class,
							new Long(type));
				}
				if (beanType == null) {
					hql = "from TbFields a where a.tbField.code='FOUNDATION_TYPE' order by a.numOrder";
					beanTypeList = this.getCommonBo().findHQL(hql);
					request.setAttribute("beanTypeList", beanTypeList);
				}
				for (int i = 0; i < eleList.size(); i++) {
					TbFoundType obj = (TbFoundType) eleList.get(i);
					String objIds = obj.getTypeId() + "";
					List childList = this.getList();
					getAllFoundTypes(childList, obj);
					if (null != childList && !childList.isEmpty()) {
						for (int j = 0; j < childList.size(); j++) {
							TbFoundType temp = (TbFoundType) childList.get(j);
							objIds += "," + temp.getTypeId();
						}
					}
					String hqlCount = "select count(*) from TbFoundation a where a.foundType in ("
							+ objIds + ")";
					if (beanType != null) {
						hqlCount += " and a.foundtypeFid='"
								+ beanType.getFieldId() + "'";
						countList.add(getCommonBo().getHQLNum(hqlCount));
					} else {
						countList.add(getCommonBo().getHQLNum(hqlCount));
						List beanTypeCountList = this.getList();
						if (beanTypeList != null && !beanTypeList.isEmpty()) {
							for (int j = 0; j < beanTypeList.size(); j++) {
								TbFields field = (TbFields) beanTypeList.get(j);
								int fieldcount = getCommonBo().getHQLNum(
										hqlCount + " and a.foundtypeFid='"
												+ field.getFieldId() + "'");
								beanTypeCountList.add(fieldcount);
							}
						}
						countsList.add(beanTypeCountList);
					}
				}
			}
			request.setAttribute("list_count", countList);
			request.setAttribute("list_counts", countsList);
		} catch (Exception e) {
		}
		// 返回
		return "typeJson";
	}

	private void getAllFoundTypes(List beanList, TbFoundType bean)
			throws Exception {
		String hql = "from TbFoundType a where a.parentId=" + bean.getTypeId()
				+ " order by a.numOrder";
		List list = this.getCommonBo().findHQL(hql);
		if (list != null && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				TbFoundType obj = (TbFoundType) list.get(i);
				if (obj != null) {
					TbFoundType temp = new TbFoundType();
					temp.setTypeName(bean.getTypeName() + "  >  "
							+ obj.getTypeName());
					temp.setTypeId(obj.getTypeId());
					beanList.add(temp);
					getAllFoundTypes(beanList, obj);
				}
			}
		}
	}

	// 项目列表
	public String list() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 得到参数
		// 分类
		String typeId = request.getParameter("typeId");
		// 项目类别
		String type = request.getParameter("type");
		// 项目编号
		String foundCode = request.getParameter("foundCode");
		// 项目名称
		String foundName = request.getParameter("foundName");
		// 基金状态
		String foundStatus = request.getParameter("foundStatus");
		// 项目分类
		String foundtypeFid = request.getParameter("foundtypeFid");
		// 项目级别
		String foundLevel = request.getParameter("foundLevel");
		// 是否留本
		String isKeep = request.getParameter("isKeep");
		// 项目创建时间
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");

		String hql = "from TbFoundation a where 1=1 ";
		List pars = this.getList();
		if (typeId != null && !"".equals(typeId) && !"1".equals(typeId)) {
			try {
				TbFoundType foundType = (TbFoundType) getCommonBo().get(
						TbFoundType.class, new Long(typeId));
				String typeIds = foundType.getTypeId() + "";
				List tempList = this.getList();
				getAllFoundTypes(tempList, foundType);
				if (null != tempList && !tempList.isEmpty()) {
					for (int i = 0; i < tempList.size(); i++) {
						TbFoundType temp = (TbFoundType) tempList.get(i);
						typeIds += "," + temp.getTypeId();
					}
				}
				hql += " and a.foundType in (" + typeIds + ")";
			} catch (Exception e) {
			}
		}
		request.setAttribute("typeId", typeId);

		TbFields beanType = null;
		if (type != null && !"".equals(type)) {
			beanType = (TbFields) getCommonBo().get(TbFields.class,
					new Long(type));
		}
		if (beanType != null) {
			hql += " and a.foundtypeFid = ?";
			pars.add(beanType.getFieldId().toString());
			request.setAttribute("type", beanType.getFieldId());
		} else if (foundtypeFid != null && !"".equals(foundtypeFid)) {
			hql += " and a.foundtypeFid = ?";
			pars.add(foundtypeFid);
		}

		if (foundName != null && !"".equals(foundName)) {
			hql += " and a.foundName like ?";
			pars.add("%" + foundName + "%");
		}
		if (foundCode != null && !"".equals(foundCode)) {
			hql += " and a.foundCode like ?";
			pars.add("%" + foundCode + "%");
		}
		if (foundLevel != null && !"".equals(foundLevel)) {
			hql += " and a.foundLevel = ?";
			pars.add(foundLevel);
		}
		if (foundStatus != null && !"".equals(foundStatus)
				&& !"0".equals(foundStatus)) {
			hql += " and a.foundStatus = ?";
			pars.add(foundStatus);
			request.setAttribute("foundStatus", foundStatus);
		}
		if (isKeep != null && !"".equals(isKeep) && !"0".equals(isKeep)) {
			hql += " and a.isKeep = ?";
			pars.add(isKeep);
		}
		if (startDate != null && !"".equals(startDate)) {
			hql += " and a.foundCreateDate>=?";
			pars.add(startDate);
		}
		if (endDate != null && !"".equals(endDate)) {
			hql += " and a.foundCreateDate<=?";
			pars.add(endDate);
		}
		hql += " order by a.creationTime desc";
		// 得到集合
		List foundList = this.getCommonBo().findHQLPage(hql, pars);
		request.setAttribute("foundList", foundList);
		// 返回
		return SUCCESS;
	}

	// 项目新增、编辑页面
	public String toPage() throws NumberFormatException, Exception {
		HttpServletRequest request = this.getRequest();
		// 项目BEAN
		TbFoundation bean = null;
		TbProtocol beanProtocol = null;
		// 项目ID
		String foundId = request.getParameter("foundId");
		if (foundId != null && !"".equals(foundId)) {
			try {
				bean = (TbFoundation) this.getCommonBo().get(
						TbFoundation.class, new Long(foundId));
			} catch (Exception e) {
			}
		}
		if (bean == null) {
			bean = new TbFoundation();
			// 系统时间
			Date dateNow = this.getCommonBo().getSysDate();
			// 项目创建时间
			bean.setFoundCreateDate(DateUtil.format(dateNow, "yyyy-MM-dd"));
			// 项目编号
			bean.setFoundCode(DateUtil.format(dateNow, "yyyyMMddHHmmSS"));
			// 项目所属分类
			String typeId = request.getParameter("typeId");
			Long typeIdL = null;
			if (typeId != null && !"".equals(typeId)) {
				try {
					typeIdL = new Long(typeId);
				} catch (Exception e) {
				}
			}
			bean.setFoundType(typeIdL);
			// 项目分类
			String foundtypeFid = request.getParameter("type");
			if (foundtypeFid != null && !"".equals(foundtypeFid)) {
				bean.setFoundtypeFid(foundtypeFid);
			}
		} else {
			request.setAttribute("foundId", bean.getFoundId());
		}

		beanProtocol = new TbProtocol();
		request.setAttribute("beanProtocol", beanProtocol);
		request.setAttribute("bean", bean);
		// 项目分类集合
		String hql = "from TbFoundType a where a.parentId=1 order by a.numOrder";
		List typeList = this.getCommonBo().findHQL(hql);
		List typeNameList = this.getList();
		if (typeList != null && !typeList.isEmpty()) {
			for (int i = 0; i < typeList.size(); i++) {
				TbFoundType beanFoundType = (TbFoundType) typeList.get(i);
				typeNameList.add(beanFoundType);
				getAllFoundTypes(typeNameList, beanFoundType);
			}
		}
		request.setAttribute("typeList", typeNameList);
		// 操作
		String opt = request.getParameter("opt");
		request.setAttribute("opt", opt);
		// 标签页次序
		String num = request.getParameter("num");
		request.setAttribute("num", num);
		// 返回
		return "addPage";
	}

	// 项目保存
	public String saveOrUpdate() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 项目BEAN
		TbFoundation bean = null;
		// 项目ID
		String foundId = request.getParameter("foundId");
		if (foundId != null && !"".equals(foundId)) {
			try {
				bean = (TbFoundation) this.getCommonBo().get(
						TbFoundation.class, new Long(foundId));
			} catch (Exception e) {
			}
		}
		boolean bIsNewFoundation = false;
		if (bean == null) {
			bIsNewFoundation = true;
			bean = new TbFoundation();
			request.setAttribute("opt", "insert");
		}

		// 协议ID
		String proId = request.getParameter("proId");
		// 协议编号
		String proCode = request.getParameter("proCode");
		// 协议状态
		String proStatus = request.getParameter("proStatus");
		// 协议名称
		String proName = request.getParameter("proName");
		// 捐赠方性质
		String jzfxz = request.getParameter("jzfxz");
		// 捐赠方
		String jzfObject = request.getParameter("jzfObject");
		// 捐赠类型
		String jzType = request.getParameter("jzType");
		// 受赠方
		String szfObject = request.getParameter("szfObject");
		// 捐赠来源
		String jzSource = request.getParameter("jzSource");
		// 使用方
		String syfObject = request.getParameter("syfObject");
		// 签约日期
		String dealDate = request.getParameter("dealDate");
		// 执行日期
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		// 协议金额
		String proAmount = request.getParameter("proAmount");
		// 实际金额
		String toAmount = request.getParameter("toAmount");
		// 协议内容
		String proContent = request.getParameter("proContent");
		// 是否上架
		String isShelves = request.getParameter("isShelves");

		// 项目编号
		String foundCode = request.getParameter("foundCode");
		// 项目名称
		String foundName = request.getParameter("foundName");
		// 基金级别
		String foundLevel = request.getParameter("foundLevel");
		// 项目分类
		String typeId = request.getParameter("typeId");
		// 项目分类
		String foundtypeFid = request.getParameter("foundtypeFid");
		// 是否留本
		String isKeep = request.getParameter("isKeep");
		// 创建时间
		String foundCreateDate = request.getParameter("foundCreateDate");
		// 受惠对象
		String foundObject = request.getParameter("foundObject");
		// 项目摘要
		String foundDis = request.getParameter("foundDis");
		// 项目摘要
		String status = request.getParameter("status");
		if (null == status || "".equals(status)) {
			status = "0";
		}
		// 项目状态
		String foundStatus = request.getParameter("foundStatus");
		// 项目说明
		String foundMemo = request.getParameter("foundMemo");

		// 添加到对象
		try {
			if (foundCode != null && !"".equals(foundCode)) {
				bean.setFoundCode(foundCode);
			}
			if (typeId != null && !"".equals(typeId)) {
				try {
					bean.setFoundType(new Long(typeId));
				} catch (Exception e) {
				}
			}
			bean.setFoundCreateDate(foundCreateDate);
			bean.setFoundDis(foundDis);
			bean.setFoundLevel(foundLevel);
			bean.setFoundObject(foundObject);
			if (foundName != null && !"".equals(foundName)) {
				bean.setFoundName(foundName);
			}
			bean.setFoundtypeFid(foundtypeFid);
			bean.setFoundMemo(foundMemo);
			bean.setIsKeep(isKeep);
			bean.setFoundStatus(status);
			bean.setIsDelete("0");
			bean.setIsShelves("1");
			if (foundStatus != null && !"".equals(foundStatus)) {
				bean.setFoundStatus(foundStatus);
			}
			// 保存
			bean = (TbFoundation) this.getCommonBo().saveOrUpdate(bean);

			if (bIsNewFoundation == true) {
				// 添加到对象
				TbProtocol beanProtocol = null;
				try {
					if (proId != null && !"".equals(proId)) {
						try {
							beanProtocol = (TbProtocol) this.getCommonBo().get(
									TbProtocol.class, new Long(proId));
						} catch (Exception e) {
						}
					}
					if (beanProtocol == null) {
						beanProtocol = new TbProtocol();
					}
					if (proCode != null && !"".equals(proCode)) {
						beanProtocol.setProCode(proCode);
					}
					if (proName != null && !"".equals(proName)) {
						beanProtocol.setProName(proName);
					}
					beanProtocol.setProAmount(proAmount);
					beanProtocol.setProContent(proContent);
					beanProtocol.setProStatus(proStatus);
					beanProtocol.setJzfObject(jzfObject);
					beanProtocol.setJzfxz(jzfxz);
					beanProtocol.setJzSource(jzSource);
					beanProtocol.setJzType(jzType);
					beanProtocol.setSzfObject(szfObject);
					beanProtocol.setDealDate(dealDate);
					beanProtocol.setStartTime(startTime);
					beanProtocol.setEndTime(endTime);
					beanProtocol.setToAmount(toAmount);
					beanProtocol.setSyfObject(syfObject);
					beanProtocol.setIsDelete("0");
					beanProtocol.setProType("1");
					if (isShelves != null && !"".equals(isShelves)) {
						beanProtocol.setIsShelves(isShelves);
					}
					beanProtocol.setFoundId(bean.getFoundId());

					// 保存
					beanProtocol = (TbProtocol) this.getCommonBo()
							.saveOrUpdate(beanProtocol);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// 更新附件编号
			String relIds = this.getRequest().getParameter("relIds");
			FileManager.getInstance().update(bean.getFoundId(), relIds);
			// 返回
			request.setAttribute("foundId", bean.getFoundId());
			return "editPage";
		} catch (Exception e) {
		}
		// 项目分类集合
		String hql = "from TbFoundType a where a.parentId=1 order by a.numOrder";
		List typeList = this.getCommonBo().findHQL(hql);
		List typeNameList = this.getList();
		if (typeList != null && !typeList.isEmpty()) {
			for (int i = 0; i < typeList.size(); i++) {
				TbFoundType beanFoundType = (TbFoundType) typeList.get(i);
				typeNameList.add(beanFoundType);
				getAllFoundTypes(typeNameList, beanFoundType);
			}
		}
		request.setAttribute("typeList", typeNameList);
		// 返回
		request.setAttribute("alert", "保存时，发生异常！");
		request.setAttribute("bean", bean);
		return "addPage";
	}

	// 项目删除
	public String delFound() throws NumberFormatException, Exception {
		// 得到request
		HttpServletRequest request = this.getRequest();
		// 项目ID
		String foundId = request.getParameter("foundId");
		if (foundId != null && !"".equals(foundId)) {
			TbFoundation bean = (TbFoundation) this.getCommonBo().get(
					TbFoundation.class, new Long(foundId));
			if (bean != null) {
				// String hql =
				// "from TbProtocol a where a.foundId="+bean.getFoundId();
				// List proList = this.getCommonBo().findHQL(hql);
				// if(null!=proList&&!proList.isEmpty()&&proList.size()>0){
				// bean.setIsDelete("1");
				// this.getCommonBo().update(bean);
				// }else{
				// //删除项目
				// this.getCommonBo().delete(bean);
				// }
				// 先删除协议捐赠详细
				String hql = "delete from TbProDetail a where proId in (select proId from TbProtocol b where b.foundId="
						+ bean.getFoundId() + ")";
				this.getCommonBo().executeHql(hql);
				// 删除协议
				hql = "delete from TbProtocol a where a.foundId="
						+ bean.getFoundId();
				this.getCommonBo().executeHql(hql);
				// 删除支出
				hql = "delete from TbProPay a where a.foundId="
						+ bean.getFoundId();
				this.getCommonBo().executeHql(hql);
				// 删除项目
				this.getCommonBo().delete(bean);
				request.setAttribute("alert", "删除成功");
			} else {
				request.setAttribute("alert", "对象不存在或已被删除");
			}
		} else {
			request.setAttribute("alert", "对象不存在或已被删除");
		}
		return list();
	}

	// 项目协议列表
	public String procl() throws Exception {
		// 得到request
		HttpServletRequest request = this.getRequest();
		// 标签页次序
		String num = request.getParameter("num");
		request.setAttribute("num", num);
		// 项目ID
		String foundId = request.getParameter("foundId");
		Long foundIdL = null;
		if (foundId != null && !"".equals(foundId)) {
			try {
				foundIdL = new Long(foundId);
			} catch (Exception e) {
			}
		}
		// 协议编号
		String proCode = request.getParameter("proCode");
		// 协议名称
		String proName = request.getParameter("proName");
		// 捐赠来源
		String jzSource = request.getParameter("jzSource");
		// 捐赠类型
		String jzType = request.getParameter("jzType");
		// 协议状态
		String proStatus = request.getParameter("proStatus");
		// 协议金额
		String proSign = request.getParameter("proSign");
		String proAmount = request.getParameter("proAmount");
		// 实际金额
		String toSign = request.getParameter("toSign");
		String toAmount = request.getParameter("toAmount");
		// 捐赠方性质
		String jzfxz = request.getParameter("jzfxz");
		// 捐赠方
		String jzfObject = request.getParameter("jzfObject");
		// 使用方
		String syfObject = request.getParameter("syfObject");
		// 签订日期
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		// TbProtocol
		String hql = "from TbProtocol a where a.proType=1";
		List pars = this.getList();
		if (foundIdL != null) {
			hql += " and a.foundId=?";
			pars.add(foundIdL);
		}
		if (proCode != null && !"".equals(proCode)) {
			hql += " and a.proCode like ?";
			pars.add("%" + proCode + "%");
		}
		if (proName != null && !"".equals(proName)) {
			hql += " and a.proName like ?";
			pars.add("%" + proName + "%");
		}
		if (jzType != null && !"".equals(jzType)) {
			hql += " and a.jzType = ?";
			pars.add(jzSource);
		}
		if (proStatus != null && !"".equals(proStatus)) {
			hql += " and a.proStatus = ?";
			pars.add(proStatus);
		}
		if (jzSource != null && !"".equals(jzSource)) {
			hql += " and a.jzSource = ?";
			pars.add(jzSource);
		}
		if (proAmount != null && !"".equals(proAmount)) {
			hql += " and a.proAmount " + proSign + " ?";
			pars.add(proAmount);
		}
		if (toAmount != null && !"".equals(toAmount)) {
			hql += " and a.toAmount " + toSign + " ?";
			pars.add(toAmount);
		}
		if (jzfxz != null && !"".equals(jzfxz) && !"0".equals(jzfxz)) {
			hql += " and a.jzfxz = ?";
			pars.add(jzfxz);
		}
		if (jzfObject != null && !"".equals(jzfObject)) {
			hql += " and a.jzfObject in (select comId from TbDonationCompany where comName like ?)";
			pars.add("%" + jzfObject + "%");
		}
		if (syfObject != null && !"".equals(syfObject)) {
			hql += " and gettype(a.syfObject,'com_name','com_id','tb_accept_company') like ?";
			pars.add("%" + syfObject + "%");
		}
		if (startDate != null && !"".equals(startDate)) {
			hql += " and a.dealDate>=?";
			pars.add(startDate);
		}
		if (endDate != null && !"".equals(endDate)) {
			hql += " and a.dealDate<=?";
			pars.add(endDate);
		}
		hql += " order by a.creationTime desc";
		List proList = this.getCommonBo().findHQLPage(hql, pars);
		request.setAttribute("proList", proList);
		// 返回
		return "proclPage";
	}

	// 项目协议所有管理列表
	public String proclMgt() throws Exception {
		// 得到request
		HttpServletRequest request = this.getRequest();
		// 项目名称
		String foundName = request.getParameter("foundName");

		// 项目类别
		String foundtypeFid = request.getParameter("foundtypeFid");
		// 项目级别
		String foundLevel = request.getParameter("foundLevel");
		// 项目状态
		String foundStatus = request.getParameter("foundStatus");
		// 项目创建时间
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");

		// 协议编号
		String proCode = request.getParameter("proCode");
		// 协议名称
		String proName = request.getParameter("proName");
		// 捐赠来源
		String jzSource = request.getParameter("jzSource");
		// 捐赠类型
		String jzType = request.getParameter("jzType");
		// 协议状态
		String proStatus = request.getParameter("proStatus");
		// 协议金额
		String proSign = request.getParameter("proSign");
		String proAmount = request.getParameter("proAmount");
		// 实际金额
		String toSign = request.getParameter("toSign");
		String toAmount = request.getParameter("toAmount");
		// 捐赠方性质
		String jzfxz = request.getParameter("jzfxz");
		// 捐赠方
		String jzfObject = request.getParameter("jzfObject");
		// 使用方
		String syfObject = request.getParameter("syfObject");
		// 签订日期
		String foundstartDate = request.getParameter("foundstartDate");
		String foundendDate = request.getParameter("foundendDate");
		// TbProtocol
		String hql = "from TbProtocol a where a.proType=1";
		List pars = this.getList();
		if (foundName != null && !"".equals(foundName)) {
			hql += " and a.tbFoundation.foundName like ?";
			pars.add("%" + foundName + "%");
		}
		if (foundtypeFid != null && !"".equals(foundtypeFid)) {
			hql += " and a.tbFoundation.foundtypeFid = ?";
			pars.add(foundtypeFid);
		}
		if (foundLevel != null && !"".equals(foundLevel)) {
			hql += " and a.tbFoundation.foundLevel = ?";
			pars.add(foundLevel);
		}
		if (foundStatus != null && !"".equals(foundStatus)
				&& !"0".equals(foundStatus)) {
			hql += " and a.tbFoundation.foundStatus = ?";
			pars.add(foundStatus);
		}
		if (foundstartDate != null && !"".equals(foundstartDate)) {
			hql += " and a.tbFoundation.foundCreateDate>=?";
			pars.add(startDate);
		}
		if (foundendDate != null && !"".equals(foundendDate)) {
			hql += " and a.tbFoundation.foundCreateDate<=?";
			pars.add(endDate);
		}
		if (proCode != null && !"".equals(proCode)) {
			hql += " and a.proCode like ?";
			pars.add("%" + proCode + "%");
		}
		if (proName != null && !"".equals(proName)) {
			hql += " and a.proName like ?";
			pars.add("%" + proName + "%");
		}
		if (jzType != null && !"".equals(jzType)) {
			hql += " and a.jzType = ?";
			pars.add(jzSource);
		}
		if (proStatus != null && !"".equals(proStatus)) {
			hql += " and a.proStatus = ?";
			pars.add(proStatus);
		}
		if (jzSource != null && !"".equals(jzSource)) {
			hql += " and a.jzSource = ?";
			pars.add(jzSource);
		}
		if (proAmount != null && !"".equals(proAmount)) {
			hql += " and a.proAmount " + proSign + " ?";
			pars.add(proAmount);
		}
		if (toAmount != null && !"".equals(toAmount)) {
			hql += " and a.toAmount " + toSign + " ?";
			pars.add(toAmount);
		}
		if (jzfxz != null && !"".equals(jzfxz) && !"0".equals(jzfxz)) {
			hql += " and a.jzfxz = ?";
			pars.add(jzfxz);
		}
		if (jzfObject != null && !"".equals(jzfObject)) {
			hql += " and a.jzfObject in (select comId from TbDonationCompany where comName like ?)";
			pars.add("%" + jzfObject + "%");
		}
		if (syfObject != null && !"".equals(syfObject)) {
			hql += " and gettype(a.syfObject,'com_name','com_id','tb_accept_company') like ?";
			pars.add("%" + syfObject + "%");
		}
		if (startDate != null && !"".equals(startDate)) {
			hql += " and a.dealDate>=?";
			pars.add(startDate);
		}
		if (endDate != null && !"".equals(endDate)) {
			hql += " and a.dealDate<=?";
			pars.add(endDate);
		}
		hql += " order by a.creationTime desc";
		List proList = this.getCommonBo().findHQLPage(hql, pars);
		request.setAttribute("proList", proList);
		// 返回
		return "proclMgtPage";
	}

	// 项目协议新增、编辑页面
	public String toProPage() throws NumberFormatException, Exception {
		HttpServletRequest request = this.getRequest();
		// 标签页序号
		String num = request.getParameter("num");
		request.setAttribute("num", num);
		// 项目BEAN
		TbFoundation beanFound = null;
		// 协议BEAN
		TbProtocol bean = null;
		// 协议ID
		String proId = request.getParameter("proId");
		if (proId != null && !"".equals(proId)) {
			try {
				bean = (TbProtocol) this.getCommonBo().get(TbProtocol.class,
						new Long(proId));
			} catch (Exception e) {
			}
		}
		if (bean == null) {
			bean = new TbProtocol();
			// 项目ID
			String foundId = request.getParameter("foundId");
			if (foundId != null && !"".equals(foundId)) {
				try {
					beanFound = (TbFoundation) this.getCommonBo().get(
							TbFoundation.class, new Long(foundId));
				} catch (Exception e) {
				}
			}
			bean.setTbFoundation(beanFound);
		} else {
			// 项目BEAN
			beanFound = bean.getTbFoundation();
			// 得到所有使用方
			String syfObject = bean.getSyfObject();
			if (syfObject != null) {
				String hql = "from TbAcceptCompany a where a.comId in ("
						+ syfObject + ")";
				List syfList = this.getCommonBo().findHQL(hql);
				request.setAttribute("syfList", syfList);
			}
			// //得到捐赠方
			String jzfObject = bean.getJzfObject();
			String jzfxz = bean.getJzfxz() == null ? "1" : bean.getJzfxz();
			Object obj = null;
			if ("1".equals(jzfxz)) {
				obj = this.getCommonBo().getHQLUnique(
						"select comName from TbDonationCompany a where a.comId="
								+ jzfObject);
			} else {
				obj = this.getCommonBo().getHQLUnique(
						"select tbAlumni.nameCn from TbDonationor a where a.memId="
								+ jzfObject);
				if (obj == null || "".equals(obj)) {
					obj = this.getCommonBo().getHQLUnique(
							"select a.nameCn from TbDonationor a where a.memId="
									+ jzfObject);
				}
			}
			request.setAttribute("jzfObject", obj);
		}
		request.setAttribute("bean", bean);
		if (null != bean && null != bean.getProId()) {
			request.setAttribute("proId", bean.getProId());
		}
		request.setAttribute("beanFound", beanFound);
		if (null != beanFound && null != beanFound.getFoundId()) {
			request.setAttribute("foundId", beanFound.getFoundId());
		}
		// 返回
		return "proAddPage";
	}

	// 项目协议保存
	public String saveOrUpdatePro() throws Exception {
		// 得到request
		HttpServletRequest request = this.getRequest();
		// 项目ID
		String foundId = request.getParameter("foundId");
		// 协议ID
		String proId = request.getParameter("proId");
		// 协议编号
		String proCode = request.getParameter("proCode");
		// 协议状态
		String proStatus = request.getParameter("proStatus");
		// 所属项目名称
		String foundName = request.getParameter("foundName");
		// 协议名称
		String proName = request.getParameter("proName");
		// 捐赠方性质
		String jzfxz = request.getParameter("jzfxz");
		// 捐赠方
		String jzfObject = request.getParameter("jzfObject");
		// 捐赠类型
		String jzType = request.getParameter("jzType");
		// 受赠方
		String szfObject = request.getParameter("szfObject");
		// 捐赠来源
		String jzSource = request.getParameter("jzSource");
		// 使用方
		String syfObject = request.getParameter("syfObject");
		// 签约日期
		String dealDate = request.getParameter("dealDate");
		// 执行日期
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		// 协议金额
		String proAmount = request.getParameter("proAmount");
		// 实际金额
		String toAmount = request.getParameter("toAmount");
		// 协议内容
		String proContent = request.getParameter("proContent");
		// 是否上架
		String isShelves = request.getParameter("isShelves");

		// 添加到对象
		TbProtocol bean = null;
		try {
			if (proId != null && !"".equals(proId)) {
				try {
					bean = (TbProtocol) this.getCommonBo().get(
							TbProtocol.class, new Long(proId));
				} catch (Exception e) {
				}
			}
			if (bean == null) {
				bean = new TbProtocol();
				request.setAttribute("opt", "insert");
			}
			if (proCode != null && !"".equals(proCode)) {
				bean.setProCode(proCode);
			}
			if (proName != null && !"".equals(proName)) {
				bean.setProName(proName);
			}
			bean.setProAmount(proAmount);
			bean.setProContent(proContent);
			bean.setProStatus(proStatus);
			bean.setJzfObject(jzfObject);
			bean.setJzfxz(jzfxz);
			bean.setJzSource(jzSource);
			bean.setJzType(jzType);
			bean.setSzfObject(szfObject);
			bean.setDealDate(dealDate);
			bean.setStartTime(startTime);
			bean.setEndTime(endTime);
			bean.setToAmount(toAmount);
			bean.setSyfObject(syfObject);
			bean.setIsDelete("0");
			bean.setProType("1");
			if (isShelves != null && !"".equals(isShelves)) {
				bean.setIsShelves(isShelves);
			}
			TbFoundation foundation = new TbFoundation();
			if (foundId != null && !"".equals(foundId)) {
				foundation = (TbFoundation) this.getCommonBo().get(
						TbFoundation.class, new Long(foundId));
				if (foundName != null) {
					foundation.setFoundName(foundName);
				}
				bean.setFoundId(new Long(foundId));
			}

			// 保存
			this.getCommonBo().saveOrUpdate(foundation);
			// 保存
			bean = (TbProtocol) this.getCommonBo().saveOrUpdate(bean);
			// 更新附件编号
			String relIds = this.getRequest().getParameter("relIds");
			FileManager.getInstance().update(bean.getProId(), relIds);
			request.setAttribute("proId", bean.getProId());
			request.setAttribute("foundId", foundation.getFoundId());
			// 返回
			return "editPro";
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 返回
		return "proAddPage";
	}

	// 项目协议删除
	public String delPro() throws NumberFormatException, Exception {
		// 得到request
		HttpServletRequest request = this.getRequest();
		request.setAttribute("sub", request.getParameter("sub"));
		request.setAttribute("foundId", request.getParameter("foundId"));
		// 指向
		String point = request.getParameter("point");
		if (point == null || "".equals(point)
				|| !",1,2,".contains("," + point + ",")) {
			point = "1";
		}
		request.setAttribute("point", point);
		String proType = request.getParameter("proType");
		if (proType == null || "".equals(proType)) {
			proType = "1";
		}
		// 标签位置
		String num = request.getParameter("num");
		if (num == null || "".equals(num)) {
			num = "1";
		}
		request.setAttribute("num", num);
		// 协议ID
		String proId = request.getParameter("proId");
		if (proId != null && !"".equals(proId)) {
			TbProtocol bean = (TbProtocol) this.getCommonBo().get(
					TbProtocol.class, new Long(proId));
			if (bean != null) {
				// String hql =
				// "from TbProDetail a where a.proId="+bean.getProId();
				// List proDetailList = this.getCommonBo().findHQL(hql);
				// if(null!=proDetailList&&!proDetailList.isEmpty()&&proDetailList.size()>0){
				// bean.setIsDelete("1");
				// this.getCommonBo().update(bean);
				// }else{
				// //删除项目
				// this.getCommonBo().delete(bean);
				// }
				// 先删除协议捐赠详细
				String hql = "delete from TbProDetail a where proId="
						+ bean.getProId();
				this.getCommonBo().executeHql(hql);
				// 再删除协议
				this.getCommonBo().delete(bean);
				request.setAttribute("alert", "删除成功");
			} else {
				request.setAttribute("alert", "对象不存在或已被删除");
			}
		} else {
			request.setAttribute("alert", "对象不存在或已被删除");
		}
		return procl();
	}

	// 项目协议管理删除
	public String delProMgt() throws NumberFormatException, Exception {
		// 得到request
		HttpServletRequest request = this.getRequest();
		request.setAttribute("sub", request.getParameter("sub"));
		request.setAttribute("foundId", request.getParameter("foundId"));
		// 指向
		String point = request.getParameter("point");
		if (point == null || "".equals(point)
				|| !",1,2,".contains("," + point + ",")) {
			point = "1";
		}
		request.setAttribute("point", point);
		String proType = request.getParameter("proType");
		if (proType == null || "".equals(proType)) {
			proType = "1";
		}
		// 标签位置
		String num = request.getParameter("num");
		if (num == null || "".equals(num)) {
			num = "1";
		}
		request.setAttribute("num", num);
		// 协议ID
		String proId = request.getParameter("proId");
		if (proId != null && !"".equals(proId)) {
			TbProtocol bean = (TbProtocol) this.getCommonBo().get(
					TbProtocol.class, new Long(proId));
			if (bean != null) {
				// String hql =
				// "from TbProDetail a where a.proId="+bean.getProId();
				// List proDetailList = this.getCommonBo().findHQL(hql);
				// if(null!=proDetailList&&!proDetailList.isEmpty()&&proDetailList.size()>0){
				// bean.setIsDelete("1");
				// this.getCommonBo().update(bean);
				// }else{
				// //删除项目
				// this.getCommonBo().delete(bean);
				// }
				// 先删除协议捐赠详细
				String hql = "delete from TbProDetail a where proId="
						+ bean.getProId();
				this.getCommonBo().executeHql(hql);
				// 再删除协议
				this.getCommonBo().delete(bean);
				request.setAttribute("alert", "删除成功");
			} else {
				request.setAttribute("alert", "对象不存在或已被删除");
			}
		} else {
			request.setAttribute("alert", "对象不存在或已被删除");
		}
		return proclMgt();
	}

	// 项目协议使用方多选
	public String findManyObject() throws Exception {
		// 得到request
		HttpServletRequest request = this.getRequest();
		// 属性
		String comName = request.getParameter("comName");
		String comIds = request.getParameter("comIds");

		// 查询单位
		String hql = "from TbAcceptCompany a where 1=1";
		List pars = this.getList();
		if (comName != null && !"".equals(comName)) {
			hql += " and comName like ?";
			pars.add("%" + comName + "%");
		}
		// 排除
		if (null != comIds && !comIds.equals("")) {
			String[] arr = comIds.split(",");
			for (int i = 0; i < arr.length && !arr[i].equals(""); i++) {
				hql += " and a.comId!=?";
				pars.add(new Long(arr[i]));
			}
		}
		hql += " order by getpinyin(a.comName) asc";
		List companyList = this.getCommonBo().findHQLPage(hql, pars);
		request.setAttribute("companyList", companyList);

		// 查询已选单位
		if (null != comIds && !comIds.equals("")) {
			hql = "from TbAcceptCompany a where comId in (" + comIds + ")";
			List inComList = this.getCommonBo().findHQL(hql);
			request.setAttribute("inComList", inComList);
		}
		request.setAttribute("comIds", comIds);
		return "findManyObjectPage";
	}

	// 项目协议捐赠方单选
	public String findObject() throws Exception {
		// 得到request
		HttpServletRequest request = this.getRequest();
		// 属性
		String comName = request.getParameter("comName");
		String comIds = request.getParameter("comIds");
		// 原来选择的捐赠方性质
		String oldType = request.getParameter("oldType");
		// 捐赠方性质type==1 单位 type==2 个人
		String type = request.getParameter("type");
		if (type == null || "".equals(type)) {
			type = "1"; // 默认单位
		}

		// 查询单位
		String hql = "";
		List pars = this.getList();
		if ("1".equals(type)) {
			// //捐赠单位列表
			// String hql="from TbDonationCompany a";
			// List companyList=this.getCommonBo().findHQL(hql);
			// //捐赠人列表
			// hql="from TbDonationor a";
			// List dorList=this.getCommonBo().findHQL(hql);
			hql = "from TbDonationCompany a where 1=1";
			if (comName != null && !"".equals(comName)) {
				hql += " and comName like ?";
				pars.add("%" + comName + "%");
			}
			if (type.equals(oldType)) {
				// 排除
				if (null != comIds && !comIds.equals("")) {
					String[] arr = comIds.split(",");
					for (int i = 0; i < arr.length && !arr[i].equals(""); i++) {
						hql += " and a.comId!=?";
						pars.add(new Long(arr[i]));
					}
				}

				// 查询已选单位
				if (null != comIds && !comIds.equals("")) {
					String hql1 = "from TbDonationCompany a where comId in ("
							+ comIds + ")";
					List inComList = this.getCommonBo().findHQL(hql1);
					request.setAttribute("inComList", inComList);
				}
			} else {
				// 查询已选个人
				if (null != comIds && !comIds.equals("")) {
					String hql1 = "from TbDonationor a where memId in ("
							+ comIds + ")";
					List inDorList = this.getCommonBo().findHQL(hql1);
					request.setAttribute("inDorList", inDorList);
				}
			}
			hql += " order by getpinyin(a.comName) asc";
			List companyList = this.getCommonBo().findHQLPage(hql, pars);
			request.setAttribute("companyList", companyList);

		} else if ("2".equals(type)) {
			hql = "from TbDonationor a where 1=1";
			if (comName != null && !"".equals(comName)) {
				hql += " and tbAlumni.nameCn like ?";
				pars.add("%" + comName + "%");
			}
			if (type.equals(oldType)) {
				// 排除
				if (null != comIds && !comIds.equals("")) {
					String[] arr = comIds.split(",");
					for (int i = 0; i < arr.length && !arr[i].equals(""); i++) {
						hql += " and a.memId!=?";
						pars.add(new Long(arr[i]));
					}
				}

				// 查询已选个人
				if (null != comIds && !comIds.equals("")) {
					String hql1 = "from TbDonationor a where memId in ("
							+ comIds + ")";
					List inDorList = this.getCommonBo().findHQL(hql1);
					request.setAttribute("inDorList", inDorList);
				}
			} else {
				// 查询已选单位
				if (null != comIds && !comIds.equals("")) {
					String hql1 = "from TbDonationCompany a where comId in ("
							+ comIds + ")";
					List inComList = this.getCommonBo().findHQL(hql1);
					request.setAttribute("inComList", inComList);
				}
			}
			hql += " order by getpinyin(a.nameCn) asc";
			List dorList = this.getCommonBo().findHQLPage(hql, pars);
			request.setAttribute("dorList", dorList);

		}

		request.setAttribute("comIds", comIds);
		request.setAttribute("comName", comName);
		request.setAttribute("type", type);
		request.setAttribute("oldType", oldType);

		return "findObjectPage";
	}

	// 项目协议捐赠明细列表
	public String proDetailList() throws Exception {
		// 得到request
		HttpServletRequest request = this.getRequest();
		String num = request.getParameter("num");
		if (num == null || "".equals(num)) {
			num = "1";
		}
		request.setAttribute("num", num);
		// 项目
		TbFoundation beanFound = null;
		// 协议
		TbProtocol bean = null;
		// 协议ID
		String proId = request.getParameter("proId");
		if (proId != null && !"".equals(proId)) {
			try {
				bean = (TbProtocol) this.getCommonBo().get(TbProtocol.class,
						new Long(proId));
			} catch (Exception e) {
			}
		}
		if (bean != null) {
			beanFound = bean.getTbFoundation();
			String hql = "from TbProDetail a where a.proId=" + bean.getProId()
					+ " order by a.creationTime desc";
			List proDetailList = this.getCommonBo().findHQL(hql);
			request.setAttribute("proDetailList", proDetailList);
		}
		request.setAttribute("bean", bean);
		if (null != bean && null != bean.getProId()) {
			request.setAttribute("proId", bean.getProId());
		}
		request.setAttribute("beanFound", beanFound);
		if (null != beanFound && null != beanFound.getFoundId()) {
			request.setAttribute("foundId", beanFound.getFoundId());
		}
		// 返回
		return "proDetailPage";
	}

	// 项目协议捐赠明细新增、编辑页面
	public String toProDetailPage() throws NumberFormatException, Exception {
		// 得到request
		HttpServletRequest request = this.getRequest();
		// 协议详细
		TbProDetail bean = null;
		// 协议详细ID
		String detailId = request.getParameter("detailId");
		if (detailId != null && !"".equals(detailId)) {
			try {
				bean = (TbProDetail) this.getCommonBo().get(TbProDetail.class,
						new Long(detailId));
			} catch (Exception e) {
			}
		}
		if (bean == null) {
			bean = new TbProDetail();
			// 协议
			TbProtocol beanPro = null;
			// 协议ID
			String proId = request.getParameter("proId");
			if (proId != null && !"".equals(proId)) {
				try {
					beanPro = (TbProtocol) this.getCommonBo().get(
							TbProtocol.class, new Long(proId));
				} catch (Exception e) {
				}
			}
			if (beanPro != null) {
				bean.setProId(beanPro.getProId());
			}
		}
		request.setAttribute("bean", bean);
		// 查询所有账户
		String hql = "from TbDonationAccout a";
		List accList = this.getCommonBo().findHQL(hql);
		request.setAttribute("accList", accList);
		// 查询所有基金类型
		hql = "from TbFields a where a.parentId in (select fieldId from TbFields b where b.code='TYPE_FUND')";
		List fieldList = this.getCommonBo().findHQL(hql);
		request.setAttribute("fieldList", fieldList);
		
		// 返回
		return "proDetailAddEditPage";
	}

	// 项目协议捐赠明细保存
	public String saveOrUpdateProDetail() throws Exception {
		// 得到request
		HttpServletRequest request = this.getRequest();
		// 协议ID
		String proId = request.getParameter("proId");
		// 协议详细ID
		String detailId = request.getParameter("detailId");
		// 承诺日期
		String dealDate = request.getParameter("dealDate");
		// 承诺金额
		String dealAmount = request.getParameter("dealAmount");
		// 到账日期
		String toDate = request.getParameter("toDate");
		// 到账金额
		String toAmount = request.getParameter("toAmount");
		// 到账账户
		String toAccount = request.getParameter("toAccount");
		// 指定用途
		String proUse = request.getParameter("proUse");
		// 是否配比
		String sfpb = request.getParameter("sfpb");
		// 状态
		String status = request.getParameter("status");
		// 申报金额
		String amount = request.getParameter("amount");
		// 实际金额
		String realAmount = request.getParameter("realAmount");
		// 备注
		String memo = request.getParameter("memo");

		// 添加到对象
		TbProDetail bean = null;
		try {
			bean = new TbProDetail();
			if (detailId != null && !"".equals(detailId)) {
				bean = (TbProDetail) this.getCommonBo().get(TbProDetail.class,
						new Long(detailId));
			}
			bean.setAmount(amount);
			bean.setDealAmount(dealAmount);
			bean.setDealDate(dealDate);
			bean.setMemo(memo);
			if (proId != null && !"".equals(proId)) {
				bean.setProId(new Long(proId));
			}
			bean.setProUse(proUse);
			bean.setRealAmount(realAmount);
			bean.setSfpb(sfpb);
			bean.setStatus(status);
			bean.setToAccount(toAccount);
			bean.setToAmount(toAmount);
			bean.setToDate(toDate);
			// 保存
			this.getCommonBo().saveOrUpdate(bean);
			// 返回
			return "proDetailAddEditSuccess";
		} catch (Exception e) {
		}
		// 返回
		request.setAttribute("bean", bean);
		request.setAttribute("alert", "保存时，发生异常！");
		return toProDetailPage();
	}

	// 项目协议捐赠明细删除
	public String delProDetail() throws NumberFormatException, Exception {
		// 得到request
		HttpServletRequest request = this.getRequest();
		// 协议详细ID
		String detailId = request.getParameter("detailId");
		if (detailId != null && !"".equals(detailId)) {
			TbProDetail bean = (TbProDetail) this.getCommonBo().get(
					TbProDetail.class, new Long(detailId));
			// 删除
			this.getCommonBo().delete(bean);
			//
			request.setAttribute("alert", "删除成功");
		} else {
			request.setAttribute("alert", "对象不存在或已被删除");
		}
		return proDetailList();
	}

	// 项目支出列表
	public String pay() throws Exception {
		// 得到request
		HttpServletRequest request = this.getRequest();
		request.setAttribute("num", request.getParameter("num"));
		// 项目
		TbFoundation beanFound = null;
		// 项目ID
		String foundId = request.getParameter("foundId");
		if (foundId != null && !"".equals(foundId)) {
			try {
				beanFound = (TbFoundation) this.getCommonBo().get(
						TbFoundation.class, new Long(foundId));
			} catch (Exception e) {
			}
		}
		// 得到该项目下所有支出
		String hql = "from TbProPay a where ";
		List pars = this.getList();
		if (beanFound != null) {
			hql += " a.foundId=?";
			pars.add(beanFound.getFoundId());
			request.setAttribute("foundId", beanFound.getFoundId());
		} else {
			hql += " 1<>1";
		}
		// 项目名称
		String foundName = request.getParameter("foundName");
		// 基金类型
		String parentType = request.getParameter("parentType");
		request.setAttribute("parentType", parentType);
		String foundType = request.getParameter("foundType");
		request.setAttribute("foundType", foundType);
		// 支出金额
		String paySign = request.getParameter("paySign");
		String payAmount = request.getParameter("payAmount");
		// 支出日期
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		// 支出账户
		String payAccount = request.getParameter("payAccount");
		// 接受单位
		String jsdwObject = request.getParameter("jsdwObject");

		if (foundName != null && !"".equals(foundName)) {
			hql += " and a.tbFoundation.foundName like ?";
			pars.add("%" + foundName + "%");
		}
		if (parentType != null && !"".equals(parentType)) {
			if (foundType != null && !"".equals(foundType)) {
				hql += " and a.foundType = ?";
				pars.add(foundType);
			} else {
				List tbFieldIdList = this.getList();
				tbFieldIdList.add(parentType);
				Long parentId = null;
				try {
					parentId = new Long(parentType);
				} catch (Exception e) {
				}
				getAllTbFields(tbFieldIdList, parentId);
				String fieldIds = "";
				if (tbFieldIdList != null && !tbFieldIdList.isEmpty()) {
					for (int i = 0; i < tbFieldIdList.size(); i++) {
						String fieldId = "";
						Object obj = tbFieldIdList.get(i);
						if (obj != null) {
							fieldId = obj.toString();
						}
						fieldIds += fieldId;
						if (i != tbFieldIdList.size() - 1) {
							fieldIds += ",";
						}
					}
				}
				if (fieldIds != null && !"".equals(fieldIds)) {
					hql += " and a.foundType in (" + fieldIds + ")";
				}
			}
		}
		if (payAmount != null && !"".equals(payAmount)) {
			hql += " and a.payAmount " + paySign + " ?";
			pars.add(payAmount);
		}
		if (payAccount != null && !"".equals(payAccount)
				&& !"0".equals(payAccount)) {
			hql += " and a.payAccount = ?";
			pars.add(payAccount);
		}
		if (jsdwObject != null && !"".equals(jsdwObject)
				&& !"0".equals(jsdwObject)) {
			hql += " and a.jsdwObject = ?";
			pars.add(jsdwObject);
		}
		if (startDate != null && !"".equals(startDate)) {
			hql += " and a.payDate>=?";
			pars.add(startDate);
		}
		if (endDate != null && !"".equals(endDate)) {
			hql += " and a.payDate<=?";
			pars.add(endDate);
		}
		hql += " order by a.creationTime desc";
		List payList = this.getCommonBo().findHQLPage(hql, pars);
		request.setAttribute("payList", payList);
		// 查询所有账户
		hql = "from TbDonationAccout a";
		List accList = this.getCommonBo().findHQL(hql);
		request.setAttribute("accList", accList);
		// 查询所有基金类型
		hql = "from TbFields a where a.parentId in (select fieldId from TbFields b where b.code='TYPE_FUND')";
		List fieldList = this.getCommonBo().findHQL(hql);
		request.setAttribute("fieldList", fieldList);
		// 所有接受单位
		hql = "from TbAcceptCompany a";
		List acceptList = this.getCommonBo().findHQL(hql);
		request.setAttribute("acceptList", acceptList);
		// 返回
		return "payPage";
	}

	private void getAllTbFields(List tbFieldIdList, Long parentId)
			throws Exception {
		String hql = "select a from TbFields a where a.parentId=" + parentId
				+ " order by a.creationTime";
		List list = this.getCommonBo().findHQL(hql);
		if (list != null && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				TbFields obj = (TbFields) list.get(i);
				if (obj != null) {
					tbFieldIdList.add(obj.getFieldId());
				}
				getAllTbFields(tbFieldIdList, obj.getFieldId());
			}
		}
	}

	// 项目支出管理列表
	public String payMgt() throws Exception {
		// 得到request
		HttpServletRequest request = this.getRequest();
		// 得到该项目下所有支出
		String hql = "from TbProPay a where 1=1 ";
		List pars = this.getList();
		// 项目名称
		String foundName = request.getParameter("foundName");
		// 基金类型
		String parentType = request.getParameter("parentType");
		request.setAttribute("parentType", parentType);
		String foundType = request.getParameter("foundType");
		request.setAttribute("foundType", foundType);
		// 支出金额
		String paySign = request.getParameter("paySign");
		String payAmount = request.getParameter("payAmount");
		// 支出日期
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		// 支出账户
		String payAccount = request.getParameter("payAccount");
		// 接受单位
		String jsdwObject = request.getParameter("jsdwObject");

		if (foundName != null && !"".equals(foundName)) {
			hql += " and a.tbFoundation.foundName like ?";
			pars.add("%" + foundName + "%");
		}
		if (parentType != null && !"".equals(parentType)) {
			if (foundType != null && !"".equals(foundType)) {
				hql += " and a.foundType = ?";
				pars.add(foundType);
			} else {
				List tbFieldIdList = this.getList();
				tbFieldIdList.add(parentType);
				Long parentId = null;
				try {
					parentId = new Long(parentType);
				} catch (Exception e) {
				}
				getAllTbFields(tbFieldIdList, parentId);
				String fieldIds = "";
				if (tbFieldIdList != null && !tbFieldIdList.isEmpty()) {
					for (int i = 0; i < tbFieldIdList.size(); i++) {
						String fieldId = "";
						Object obj = tbFieldIdList.get(i);
						if (obj != null) {
							fieldId = obj.toString();
						}
						fieldIds += fieldId;
						if (i != tbFieldIdList.size() - 1) {
							fieldIds += ",";
						}
					}
				}
				if (fieldIds != null && !"".equals(fieldIds)) {
					hql += " and a.foundType in (" + fieldIds + ")";
				}
			}
		}
		if (payAmount != null && !"".equals(payAmount)) {
			hql += " and a.payAmount " + paySign + " ?";
			pars.add(payAmount);
		}
		if (payAccount != null && !"".equals(payAccount)
				&& !"0".equals(payAccount)) {
			hql += " and a.payAccount = ?";
			pars.add(payAccount);
		}
		if (jsdwObject != null && !"".equals(jsdwObject)
				&& !"0".equals(jsdwObject)) {
			hql += " and a.jsdwObject = ?";
			pars.add(jsdwObject);
		}
		if (startDate != null && !"".equals(startDate)) {
			hql += " and a.payDate>=?";
			pars.add(startDate);
		}
		if (endDate != null && !"".equals(endDate)) {
			hql += " and a.payDate<=?";
			pars.add(endDate);
		}
		hql += " order by a.creationTime desc";
		List payList = this.getCommonBo().findHQLPage(hql, pars);
		request.setAttribute("payList", payList);
		// 查询所有账户
		hql = "from TbDonationAccout a";
		List accList = this.getCommonBo().findHQL(hql);
		request.setAttribute("accList", accList);
		// 查询所有基金类型
		hql = "from TbFields a where a.parentId in (select fieldId from TbFields b where b.code='TYPE_FUND')";
		List fieldList = this.getCommonBo().findHQL(hql);
		request.setAttribute("fieldList", fieldList);
		// 所有接受单位
		hql = "from TbAcceptCompany a";
		List acceptList = this.getCommonBo().findHQL(hql);
		request.setAttribute("acceptList", acceptList);
		// 返回
		return "payMgtPage";
	}

	// 项目支付新增、编辑页面
	public String toProPayPage() throws NumberFormatException, Exception {
		// 得到request
		HttpServletRequest request = this.getRequest();
		// 项目ID
		String foundId = request.getParameter("foundId");
		if (foundId != null && !"".equals(foundId)) {
			TbFoundation bean = (TbFoundation) this.getCommonBo().get(
					TbFoundation.class, new Long(foundId));
			request.setAttribute("foundBean", bean);
		}
		request.setAttribute("foundId", foundId);
		// 支出管理ID
		String payId = request.getParameter("payId");
		if (payId != null && !"".equals(payId)) {
			TbProPay bean = (TbProPay) this.getCommonBo().get(TbProPay.class,
					new Long(payId));
			request.setAttribute("bean", bean);
		}
		request.setAttribute("payId", payId);

		// 所有接受单位
		String hql = "from TbAcceptCompany a order by getpinyin(a.comName)";
		List acceptList = this.getCommonBo().findHQL(hql);
		request.setAttribute("acceptList", acceptList);

		// 查询所有账户
		hql = "from TbDonationAccout a order by getpinyin(a.accountName)";
		List accList = this.getCommonBo().findHQL(hql);

		// 查询所有协议下的基金类型
		hql = "from TbFields a where a.fieldId in (select parentId from TbFields b where b.fieldId in (select proUse from TbProDetail c where c.proId in (select proId from TbProtocol d where d.foundId="
				+ foundId + ")))";
		List fieldList = this.getCommonBo().findHQL(hql);
		if (fieldList == null || fieldList.isEmpty()) {
			// 如果协议下没有基金类型则查询所有的类型
			hql = "from TbFields a where a.parentId in (select fieldId from TbFields b where b.code='TYPE_FUND')";
			fieldList = this.getCommonBo().findHQL(hql);
		}

		// 查询协议下实际到账总额
		hql = "select sum(toAmount) from TbProDetail a where proId in (select proId from TbProtocol where foundId="
				+ foundId + ")";
		Object obj = this.getCommonBo().getHQLUnique(hql);
		obj = obj == null ? 0 : obj;

		// 所有支出总金额，去除当前支出
		hql = "select sum(payAmount) from TbProPay a where a.foundId = "
				+ foundId;
		if (payId != null && !"".equals(payId)) {
			hql += " and payId!=" + payId;
		}
		Object payObj = this.getCommonBo().getHQLUnique(hql);
		payObj = payObj == null ? 0 : payObj;

		request.setAttribute("accList", accList);
		request.setAttribute("fieldList", fieldList);
		request.setAttribute("realAllAmount", obj);
		request.setAttribute("payAllAmount", payObj);

		return "proPayAddEditPage";
	}

	// 项目支付保存
	public String saveOrUpdateProPay() throws Exception {
		// 得到request
		HttpServletRequest request = this.getRequest();
		// 项目ID
		String foundId = request.getParameter("foundId");
		// 支出管理ID
		String payId = request.getParameter("payId");
		// 基金类型
		String foundType = request.getParameter("foundType");
		// 支出金额
		String payAmount = request.getParameter("payAmount");
		// 支出日期
		String payDate = request.getParameter("payDate");
		// 支出账户
		String payAccount = request.getParameter("payAccount");
		// 接受单位
		String jsdwObject = request.getParameter("jsdwObject");
		String jsdwAccount = request.getParameter("jsdwAccount");
		// 支出说明
		String payMemo = request.getParameter("payMemo");

		TbProPay bean = new TbProPay();
		if (payId != null && !"".equals(payId)) {
			bean = (TbProPay) this.getCommonBo().get(TbProPay.class,
					new Long(payId));
		}
		if (foundId != null && !"".equals(foundId)) {
			bean.setFoundId(new Long(foundId));
		}
		bean.setFoundType(foundType);
		bean.setPayAccount(payAccount);
		bean.setPayAmount(payAmount);
		bean.setPayDate(payDate);
		bean.setPayMemo(payMemo);
		bean.setJsdwAccount(jsdwAccount);
		bean.setJsdwObject(jsdwObject);

		// 保存
		bean = (TbProPay) this.getCommonBo().saveOrUpdate(bean);
		// 更新附件编号
		String relIds = this.getRequest().getParameter("relIds");
		FileManager.getInstance().update(bean.getPayId(), relIds);

		return "proPayAddEditSuccess";
	}

	/**
	 * 删除支出
	 * 
	 * @return
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	public String delProPay() throws NumberFormatException, Exception {
		// 得到request
		HttpServletRequest request = this.getRequest();
		// 支出管理ID
		String payId = request.getParameter("payId");
		if (payId != null && !"".equals(payId)) {
			TbProPay bean = (TbProPay) this.getCommonBo().get(TbProPay.class,
					new Long(payId));
			if (bean != null) {
				this.getCommonBo().delete(bean);
			}
			request.setAttribute("alert", "删除成功");
		} else {
			request.setAttribute("alert", "对象不存在或已被删除");
		}
		return pay();
	}

	public String delProPayMgt() throws NumberFormatException, Exception {
		// 得到request
		HttpServletRequest request = this.getRequest();
		// 支出管理ID
		String payId = request.getParameter("payId");
		if (payId != null && !"".equals(payId)) {
			TbProPay bean = (TbProPay) this.getCommonBo().get(TbProPay.class,
					new Long(payId));
			if (bean != null) {
				this.getCommonBo().delete(bean);
			}
			request.setAttribute("alert", "删除成功");
		} else {
			request.setAttribute("alert", "对象不存在或已被删除");
		}
		return payMgt();
	}

	/**
	 * 执行文档
	 * 
	 * @return
	 */
	public String doc() {
		// 得到request
		HttpServletRequest request = this.getRequest();
		String num = request.getParameter("num");
		if (num == null || "".equals(num)) {
			num = "1";
		}
		request.setAttribute("num", num);
		// 项目ID
		request.setAttribute("foundId", request.getParameter("foundId"));
		return "docList";
	}

	/**
	 * 新增时选择基金项目
	 * 
	 * @return
	 * @throws Exception
	 */
	public String chooseFound() throws Exception {
		// 得到request
		HttpServletRequest request = this.getRequest();
		// 得到参数
		// 项目编号
		String foundCode = request.getParameter("foundCode");
		// 项目名称
		String foundName = request.getParameter("foundName");
		// 基金级别
		String foundLevel = request.getParameter("foundLevel");
		// 项目分类
		String foundtypeFid = request.getParameter("foundtypeFid");
		// 是否留本
		String isKeep = request.getParameter("isKeep");

		String hql = "from TbFoundation a where 1=1 ";
		List pars = this.getList();
		if (foundCode != null && !"".equals(foundCode)) {
			hql += " and a.foundCode like ?";
			pars.add("%" + foundCode + "%");
		}
		if (foundName != null && !"".equals(foundName)) {
			hql += " and a.foundName like ?";
			pars.add("%" + foundName + "%");
		}
		if (foundLevel != null && !"".equals(foundLevel)
				&& !"0".equals(foundLevel)) {
			hql += " and a.foundLevel = ?";
			pars.add(foundLevel);
		}
		if (foundtypeFid != null && !"".equals(foundtypeFid)
				&& !"0".equals(foundtypeFid)) {
			hql += " and a.foundtypeFid = ?";
			pars.add(foundtypeFid);
		}
		if (isKeep != null && !"".equals(isKeep) && !"0".equals(isKeep)) {
			hql += " and a.isKeep = ?";
			pars.add(isKeep);
		}

		// 得到集合
		List foundList = this.getCommonBo().findHQLPage(hql, pars);
		request.setAttribute("foundList", foundList);
		request.setAttribute("flag", request.getParameter("flag"));
		return "chooseFoundPage";
	}

	/**
	 * 协议图片
	 * 
	 * @return
	 * @throws Exception
	 */
	public String protocolPic() throws Exception {
		// 得到request
		HttpServletRequest request = this.getRequest();
		String num = request.getParameter("num");
		String foundId = request.getParameter("foundId");
		request.setAttribute("foundId", foundId);
		request.setAttribute("proType", request.getParameter("proType"));
		String proId = request.getParameter("proId");
		if (num == null || "".equals(num)) {
			num = "4";
		}
		request.setAttribute("num", num);
		String hql = "from TbProtocolPic a where 1=1";
		if (proId != null && !"".equals(proId)) {
			try {
				hql += " and a.proId=" + new Long(proId);
				request.setAttribute("proId", proId);
			} catch (Exception e) {
			}
		}
		hql += " order by a.numOrder";
		List beanList = this.getCommonBo().findHQL(hql);
		request.setAttribute("beanList", beanList);
		return "foundPic";
	}

	// 移动图片
	public String movePic() throws Exception {
		HttpServletRequest request = this.getRequest();
		request.setAttribute("proType", request.getParameter("proType"));
		String foundId = request.getParameter("foundId");
		request.setAttribute("foundId", foundId);
		String picId = request.getParameter("picId");
		TbProtocolPic bean = null;
		if (null != picId && !"".equals(picId)) {
			try {
				bean = (TbProtocolPic) this.getCommonBo().get(
						TbProtocolPic.class, new Long(picId));
			} catch (Exception e) {
			}
		}
		String proId = request.getParameter("proId");
		request.setAttribute("proId", proId);
		String num = request.getParameter("num");
		if (null == num || "".equals(num)) {
			num = "4";
		}
		request.setAttribute("num", num);
		// 类型 1：上移 2：下移
		String type = request.getParameter("type");
		if (null == type || "".equals(type)) {
			type = "1";
		}
		String hql = "from TbProtocolPic a where a.proId=" + bean.getProId()
				+ " order by a.numOrder";
		List beanList = this.getCommonBo().findHQL(hql);
		TbProtocolPic temp = new TbProtocolPic();
		for (int i = 0; i < beanList.size(); i++) {
			temp = (TbProtocolPic) beanList.get(i);
			if (temp == bean) {
				if (type.equals("1") && i != 0) {// 上移
					temp = (TbProtocolPic) beanList.get(i - 1);
					Long numOrderQ = temp.getNumOrder();
					Long numOrderH = bean.getNumOrder();
					temp.setNumOrder(numOrderH);
					bean.setNumOrder(numOrderQ);
					break;
				}
				if (type.equals("2") && i != beanList.size() - 1) {
					temp = (TbProtocolPic) beanList.get(i + 1);
					Long numOrderQ = temp.getNumOrder();
					Long numOrderH = bean.getNumOrder();
					temp.setNumOrder(numOrderH);
					bean.setNumOrder(numOrderQ);
					break;
				}
			}
		}
		this.getCommonBo().update(bean);
		this.getCommonBo().update(temp);
		return protocolPic();
	}

	// 操作图片
	public String operationPic() throws Exception {
		HttpServletRequest request = this.getRequest();
		request.setAttribute("proType", request.getParameter("proType"));
		String foundId = request.getParameter("foundId");
		request.setAttribute("foundId", foundId);
		String picId = request.getParameter("picId");
		TbProtocolPic bean = null;
		if (null != picId && !"".equals(picId)) {
			try {
				bean = (TbProtocolPic) this.getCommonBo().get(
						TbProtocolPic.class, new Long(picId));
			} catch (Exception e) {
			}
		}
		request.setAttribute("bean", bean);
		String proId = request.getParameter("proId");
		request.setAttribute("proId", proId);
		String num = request.getParameter("num");
		if (null == num || "".equals(num)) {
			num = "1";
		}
		request.setAttribute("num", num);
		return "operationPic";
	}

	public String saveOrUpdatePic() throws Exception {
		HttpServletRequest request = this.getRequest();
		MultiRequestUtil multiRequest = new MultiRequestUtil(request);
		request.setAttribute("proType", multiRequest.getParameter("proType"));
		TbProtocolPic bean = new TbProtocolPic();
		String foundId = multiRequest.getParameter("foundId");
		request.setAttribute("foundId", foundId);
		String picId = multiRequest.getParameter("picId");
		if (null != picId && !"".equals(picId)) {
			try {
				bean = (TbProtocolPic) this.getCommonBo().get(
						TbProtocolPic.class, new Long(picId));
			} catch (Exception e) {
			}
		}
		String num = multiRequest.getParameter("num");
		if (null == num || "".equals(num)) {
			num = "1";
		}
		request.setAttribute("num", num);
		// 当前时间
		Date dateNow = this.getCommonBo().getSysDate();
		String proId = multiRequest.getParameter("proId");
		if (null != proId && !"".equals(proId)) {
			try {
				bean.setProId(new Long(proId));
			} catch (Exception e) {
			}
		}
		String picAlt = multiRequest.getParameter("picAlt");
		bean.setPicAlt(picAlt);
		FileItem picItem = multiRequest.getFile("pic");
		if (null != picItem) {
			// 文件名称
			String fileName = picItem.getName();
			// 文件后缀
			String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1)
					.toLowerCase();
			if (!(",gif,jpg,jpeg,png,bmp,".contains("," + fileExt + ","))) {
				request.setAttribute("alert", "图标必须为gif,jpg,jpeg,png,bmp格式!");
				return operationPic();
			}
			InputStream saveis = null;
			OutputStream saveos = null;
			try {
				String uploadPath = request.getSession().getServletContext()
						.getRealPath("/upload/")
						+ "/";
				File uploadDir = new File(uploadPath);
				if (!uploadDir.exists()) {
					uploadDir.mkdirs();
				}

				String savePath = uploadPath + "image/";
				File saveDir = new File(savePath);
				if (!saveDir.exists()) {
					saveDir.mkdirs();
				}

				String fileDir = com.laungee.proj.common.util.DateUtil.format(
						dateNow, "yyyyMMdd");
				savePath += fileDir + "/";
				File dirFile = new File(savePath);
				if (!dirFile.exists()) {
					dirFile.mkdirs();
				}

				String newFileName = DateUtil.format(dateNow, "yyyyMMddHHmmss")
						+ "_" + new Random().nextInt(1000) + "." + fileExt;

				File saveFile = new File(savePath + newFileName);

				saveis = picItem.getInputStream();
				saveos = new FileOutputStream(saveFile);
				byte[] buffer = new byte[1024 * 8];
				int readLen = 0;
				while ((readLen = saveis.read(buffer)) != -1) {
					saveos.write(buffer, 0, readLen);
				}
				bean.setPicName(fileName);
				bean.setPicExt(fileExt);
				bean.setPicPath(fileDir + "/" + newFileName);
			} catch (Exception e) {
				request.setAttribute("alert", "图片上传失败!");
				return operationPic();
			} finally {
				if (saveis != null) {
					saveis.close();
				}
				if (saveos != null) {
					saveos.flush();
					saveos.close();
				}
			}
		}
		if (bean.getPicId() == null) {
			String hql = "select Max(numOrder) from TbProtocolPic";
			Object numObj = this.getCommonBo().getHQLUnique(hql);
			Long numOrder = null;
			try {
				if (numObj != null) {
					numOrder = new Long(numObj.toString()) + 1;
				} else {
					numOrder = new Long(1);
				}
			} catch (Exception e) {
			}
			bean.setNumOrder(numOrder);
		}
		String memo = multiRequest.getParameter("memo");
		bean.setMemo(memo);
		this.getCommonBo().saveOrUpdate(bean);
		return "reload";
	}

	// 删除图片
	public String deletePic() throws Exception {
		HttpServletRequest request = this.getRequest();
		request.setAttribute("proType", request.getParameter("proType"));
		String foundId = request.getParameter("foundId");
		request.setAttribute("foundId", foundId);
		TbProtocolPic bean = new TbProtocolPic();
		String proId = request.getParameter("proId");
		request.setAttribute("proId", proId);
		String num = request.getParameter("num");
		if (null == num || "".equals(num)) {
			num = "4";
		}
		request.setAttribute("num", num);
		String picId = request.getParameter("picId");
		if (null != picId && !"".equals(picId)) {
			try {
				bean = (TbProtocolPic) this.getCommonBo().get(
						TbProtocolPic.class, new Long(picId));
			} catch (Exception e) {
			}
		}
		this.getCommonBo().delete(bean);
		return protocolPic();
	}

	/**
	 * 协议参数
	 * 
	 * @return
	 * @throws Exception
	 */
	public String protocolParam() throws Exception {
		// 得到request
		HttpServletRequest request = this.getRequest();
		request.setAttribute("proType", request.getParameter("proType"));
		String num = request.getParameter("num");
		String foundId = request.getParameter("foundId");
		request.setAttribute("foundId", foundId);
		request.setAttribute("proType", request.getParameter("proType"));
		String proId = request.getParameter("proId");
		if (num == null || "".equals(num)) {
			num = "5";
		}
		request.setAttribute("num", num);
		String hql = "from TbProtocolParam a where 1=1";
		if (proId != null && !"".equals(proId)) {
			try {
				hql += " and a.proId=" + new Long(proId);
				request.setAttribute("proId", proId);
			} catch (Exception e) {
			}
		}
		hql += " order by a.numOrder";
		List beanList = this.getCommonBo().findHQL(hql);
		request.setAttribute("beanList", beanList);
		return "foundParam";
	}

	// 移动参数
	public String moveParam() throws Exception {
		HttpServletRequest request = this.getRequest();
		request.setAttribute("proType", request.getParameter("proType"));
		String foundId = request.getParameter("foundId");
		request.setAttribute("foundId", foundId);
		String paramId = request.getParameter("paramId");
		TbProtocolParam bean = null;
		if (null != paramId && !"".equals(paramId)) {
			try {
				bean = (TbProtocolParam) this.getCommonBo().get(
						TbProtocolParam.class, new Long(paramId));
			} catch (Exception e) {
			}
		}
		String proId = request.getParameter("proId");
		request.setAttribute("proId", proId);
		String num = request.getParameter("num");
		if (null == num || "".equals(num)) {
			num = "1";
		}
		request.setAttribute("num", num);
		// 类型 1：上移 2：下移
		String type = request.getParameter("type");
		if (null == type || "".equals(type)) {
			type = "1";
		}
		String hql = "from TbProtocolParam a where a.proId=" + bean.getProId()
				+ " order by a.numOrder";
		List beanList = this.getCommonBo().findHQL(hql);
		TbProtocolParam temp = new TbProtocolParam();
		for (int i = 0; i < beanList.size(); i++) {
			temp = (TbProtocolParam) beanList.get(i);
			if (temp == bean) {
				if (type.equals("1") && i != 0) {// 上移
					temp = (TbProtocolParam) beanList.get(i - 1);
					Long numOrderQ = temp.getNumOrder();
					Long numOrderH = bean.getNumOrder();
					temp.setNumOrder(numOrderH);
					bean.setNumOrder(numOrderQ);
					break;
				}
				if (type.equals("2") && i != beanList.size() - 1) {
					temp = (TbProtocolParam) beanList.get(i + 1);
					Long numOrderQ = temp.getNumOrder();
					Long numOrderH = bean.getNumOrder();
					temp.setNumOrder(numOrderH);
					bean.setNumOrder(numOrderQ);
					break;
				}
			}
		}
		this.getCommonBo().update(bean);
		this.getCommonBo().update(temp);
		return protocolParam();
	}

	// 新增参数
	public String operationParam() throws Exception {
		HttpServletRequest request = this.getRequest();
		request.setAttribute("proType", request.getParameter("proType"));
		String paramId = request.getParameter("paramId");
		TbProtocolParam bean = null;
		if (null != paramId && !"".equals(paramId)) {
			try {
				bean = (TbProtocolParam) this.getCommonBo().get(
						TbProtocolParam.class, new Long(paramId));
			} catch (Exception e) {
			}
		}
		request.setAttribute("bean", bean);
		String proId = request.getParameter("proId");
		request.setAttribute("proId", proId);
		String foundId = request.getParameter("foundId");
		request.setAttribute("foundId", foundId);
		String num = request.getParameter("num");
		if (null == num || "".equals(num)) {
			num = "5";
		}
		request.setAttribute("num", num);
		return "operationParam";
	}

	// 操作参数
	public String saveOrUpdateParam() throws Exception {
		HttpServletRequest request = this.getRequest();
		request.setAttribute("proType", request.getParameter("proType"));
		TbProtocolParam bean = new TbProtocolParam();
		String paramId = request.getParameter("paramId");
		if (null != paramId && !"".equals(paramId)) {
			try {
				bean = (TbProtocolParam) this.getCommonBo().get(
						TbProtocolParam.class, new Long(paramId));
			} catch (Exception e) {
			}
		}
		String num = request.getParameter("num");
		if (null == num || "".equals(num)) {
			num = "5";
		}
		request.setAttribute("num", num);
		String foundId = request.getParameter("foundId");
		request.setAttribute("foundId", foundId);
		String proId = request.getParameter("proId");
		if (null == proId || "".equals(proId)) {
			request.setAttribute("alert", "请选择所属协议!");
			return operationParam();
		}
		try {
			bean.setProId(new Long(proId));
		} catch (Exception e) {
		}
		request.setAttribute("proId", proId);
		String paramName = request.getParameter("paramName");
		if (null == paramName || "".equals(paramName)) {
			request.setAttribute("alert", "请输入参数名称!");
			return operationParam();
		}
		bean.setParamName(paramName);
		String paramValue = request.getParameter("paramValue");
		bean.setParamValue(paramValue);
		String memo = request.getParameter("memo");
		bean.setMemo(memo);
		if (bean.getParamId() == null) {
			String hql = "select Max(numOrder) from TbProtocolParam";
			Object numObj = this.getCommonBo().getHQLUnique(hql);
			Long numOrder = null;
			try {
				if (numObj != null) {
					numOrder = new Long(numObj.toString()) + 1;
				} else {
					numOrder = new Long(1);
				}
			} catch (Exception e) {
			}
			bean.setNumOrder(numOrder);
		}
		this.getCommonBo().saveOrUpdate(bean);
		return "reload";
	}

	// 删除参数
	public String deleteParam() throws Exception {
		HttpServletRequest request = this.getRequest();
		request.setAttribute("proType", request.getParameter("proType"));
		request.setAttribute("foundId", request.getParameter("foundId"));
		TbProtocolParam bean = new TbProtocolParam();
		String proId = request.getParameter("proId");
		request.setAttribute("proId", proId);
		String num = request.getParameter("num");
		if (null == num || "".equals(num)) {
			num = "5";
		}
		request.setAttribute("num", num);
		String paramId = request.getParameter("paramId");
		if (null != paramId && !"".equals(paramId)) {
			try {
				bean = (TbProtocolParam) this.getCommonBo().get(
						TbProtocolParam.class, new Long(paramId));
			} catch (Exception e) {
			}
		}
		this.getCommonBo().delete(bean);
		return protocolParam();
	}

	// 项目上架、下架
	public String shelves() throws Exception {
		HttpServletRequest request = this.getRequest();
		String typeId = request.getParameter("typeId");
		request.setAttribute("typeId", typeId);
		String foundId = request.getParameter("foundId");
		if (null != foundId && !"".equals(foundId)) {
			try {
				TbFoundation bean = (TbFoundation) this.getCommonBo().get(
						TbFoundation.class, new Long(foundId));
				String isShelves = request.getParameter("isShelves");
				if (null != isShelves && !"".equals(isShelves)) {
					bean.setIsShelves(isShelves);
				}
				request.setAttribute("isShelves", isShelves);
				request.setAttribute("foundId", foundId);
				this.getCommonBo().update(bean);
				request.setAttribute("alert", "操作成功");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}

	// 协议上架、下架
	public String proShelves() throws Exception {
		HttpServletRequest request = this.getRequest();
		request.setAttribute("sub", request.getParameter("sub"));
		request.setAttribute("foundId", request.getParameter("foundId"));
		request.setAttribute("num", request.getParameter("num"));
		String proId = request.getParameter("proId");
		if (null != proId && !"".equals(proId)) {
			try {
				TbProtocol bean = (TbProtocol) this.getCommonBo().get(
						TbProtocol.class, new Long(proId));
				String isShelves = request.getParameter("isShelves");
				if (null != isShelves && !"".equals(isShelves)) {
					bean.setIsShelves(isShelves);
				}
				request.setAttribute("isShelves", isShelves);
				this.getCommonBo().update(bean);
				request.setAttribute("alert", "操作成功");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "proclPage";
	}

	/*----------------------------------------------报表----------------------------------------------*/

	/**
	 * 信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String proList() throws Exception {
		// 得到request
		HttpServletRequest request = this.getRequest();
		// 获取系统时间
		Date dateNow = this.getCommonBo().getSysDate();
		// 获取当前时间相关参数
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(dateNow);
		// 当前年份
		int nowYear = c.get(Calendar.YEAR);
		request.setAttribute("nowYear", nowYear);
		String startDate = "";// 开始时间
		String endDate = "";// 结束时间
		String year = request.getParameter("year");
		if (null != year && !"".equals(year)) {
			startDate = year + "-01-01";
			endDate = year + "-12-31";
		}
		String startDateObj = request.getParameter("startDate");
		String endDateObj = request.getParameter("endDate");
		if (null != startDateObj && !"".equals(startDateObj)
				&& null != endDateObj && !"".equals(endDateObj)) {
			startDate = startDateObj;
			endDate = endDateObj;
		}
		// 开始时间不为空，结束时间为空时，结束时间设为当前日期
		if ((null != startDate && !"".equals(startDate))
				&& (null == endDate || "".equals(endDate))) {
			endDate = DateUtil.format(dateNow, "yyyy-MM-dd");
		}
		/*
		 * if(null==startDate || "".equals(startDate) || null==endDate ||
		 * "".equals(endDate)){ startDate = nowYear+"-01-01"; endDate =
		 * nowYear+"-12-31"; }
		 */
		// 类型 1:项目情况 2:项目支出明细 3:到账情况明细 4:未到账情况明细 5:配比情况
		String type = request.getParameter("type");
		if (null == type || type.equals("")) {
			type = "1";
		}
		request.setAttribute("type", type);
		request.setAttribute("year", year);
		request.setAttribute("startDateObj", startDateObj);
		request.setAttribute("endDateObj", endDateObj);
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		return "proList";
	}

	/**
	 * 信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String proListSearch() throws Exception {
		try {
			// 得到request
			HttpServletRequest request = this.getRequest();
			String foundName = request.getParameter("foundName");
			// 获取系统时间
			Date dateNow = this.getCommonBo().getSysDate();
			// 获取当前时间相关参数
			Calendar c = Calendar.getInstance();
			c.setFirstDayOfWeek(Calendar.MONDAY);
			c.setTime(dateNow);
			// 当前年份
			int nowYear = c.get(Calendar.YEAR);
			request.setAttribute("nowYear", nowYear);
			String startDate = "";// 开始时间
			String endDate = "";// 结束时间
			String year = request.getParameter("year");
			if (null != year && !"".equals(year)) {
				startDate = year + "-01-01";
				endDate = year + "-12-31";
				request.setAttribute("year", year);
			}
			// 自定义日期
			String startDateObj = request.getParameter("startDate");
			String endDateObj = request.getParameter("endDate");
			if (null != startDateObj && !"".equals(startDateObj)
					&& null != endDateObj && !"".equals(endDateObj)) {
				startDate = startDateObj;
				endDate = endDateObj;
				request.setAttribute("startDateObj", startDateObj);
				request.setAttribute("endDateObj", endDateObj);
			}
			// 开始时间不为空，结束时间为空时，结束时间设为当前日期
			if ((null != startDateObj && !"".equals(startDateObj))
					&& (null == endDateObj || "".equals(endDateObj))) {
				startDate = startDateObj;
				endDate = DateUtil.format(dateNow, "yyyy-MM-dd");
				request.setAttribute("startDateObj", startDateObj);
				request.setAttribute("endDateObj", endDate);
			}
			// 类型 1:项目情况 2:项目支出明细 3:到账情况明细 4:未到账情况明细 5:配比情况
			String type = request.getParameter("type");
			if (null == type || type.equals("")) {
				type = "1";
			}

			List beanList = this.getList();
			List idList = this.getList();
			if (type.equals("1")) {// 项目情况
				String status = request.getParameter("status");
				String foundtypeFid = request.getParameter("foundtypeFid");
				String isKeep = request.getParameter("isKeep");
				String hql = "from TbFoundation a where 1=1";
				if (null != status && !"".equals(status)) {
					hql += " and a.foundStatus=" + status;
					request.setAttribute("status", status);
				}
				if (null != foundName && !"".equals(foundName)) {
					hql += " and a.foundName like '%" + foundName + "%'";
					request.setAttribute("foundName", foundName);
				}
				if (null != foundtypeFid && !"".equals(foundtypeFid)) {
					hql += " and a.foundtypeFid=" + foundtypeFid;
					request.setAttribute("foundtypeFid", foundtypeFid);
				}
				if (null != isKeep && !"".equals(isKeep)) {
					hql += " and a.isKeep=" + isKeep;
					request.setAttribute("isKeep", isKeep);
				}
				// 协议金额
				String hqlXy = "select t.foundId,sum(t.proAmount) from TbProtocol t where t.foundId in (select a.foundId "
						+ hql
						+ ") GROUP BY t.foundId,t.tbFoundation.creationTime order by t.tbFoundation.creationTime desc";
				List xyList = this.getCommonBo().findHQL(hqlXy);
				// 查询协议下实际到账总额
				String hqlDz = "select tp.tbProtocol.foundId,sum(tp.toAmount) from TbProDetail tp where tp.proId in (select p.proId from TbProtocol p where p.foundId in (select a.foundId "
						+ hql
						+ ")) GROUP BY tp.tbProtocol.foundId,tp.tbProtocol.tbFoundation.creationTime order by tp.tbProtocol.tbFoundation.creationTime desc";
				List dzList = this.getCommonBo().findHQL(hqlDz);
				// 所有支出总金额
				String hqlZc = "select t.foundId,sum(t.payAmount) from TbProPay t where t.foundId in (select a.foundId "
						+ hql
						+ ") GROUP BY t.foundId,t.tbFoundation.creationTime order by t.tbFoundation.creationTime desc";
				List zcList = this.getCommonBo().findHQL(hqlZc);
				List timeDzList = this.getList();
				List timeZcList = this.getList();
				if (null != startDate && !"".equals(startDate)
						&& null != endDate && !"".equals(endDate)) {
					hqlDz = "select tp.tbProtocol.foundId,sum(tp.toAmount) from TbProDetail tp where tp.toDate>='"
							+ startDate
							+ "' and tp.toDate<='"
							+ endDate
							+ "' and tp.proId in (select p.proId from TbProtocol p where p.foundId in (select a.foundId "
							+ hql
							+ ")) GROUP BY tp.tbProtocol.foundId,tp.tbProtocol.tbFoundation.creationTime order by tp.tbProtocol.tbFoundation.creationTime desc";
					timeDzList = this.getCommonBo().findHQL(hqlDz);
					// 所有支出总金额
					hqlZc = "select t.foundId,sum(t.payAmount) from TbProPay t where t.payDate>='"
							+ startDate
							+ "' and t.payDate<='"
							+ endDate
							+ "' and t.foundId in (select a.foundId "
							+ hql
							+ ") GROUP BY t.foundId,t.tbFoundation.creationTime order by t.tbFoundation.creationTime desc";
					timeZcList = this.getCommonBo().findHQL(hqlZc);
				}
				hql += "order by a.creationTime desc";
				List foundList = this.getCommonBo().findHQL(hql);
				BigDecimal xyBig = new BigDecimal(0);
				BigDecimal dzBig = new BigDecimal(0);
				BigDecimal zcBig = new BigDecimal(0);
				BigDecimal timeDzBig = new BigDecimal(0);
				BigDecimal timeZcBig = new BigDecimal(0);
				if (foundList != null && !foundList.isEmpty()) {
					for (int i = 0; i < foundList.size(); i++) {
						List cellList = this.getList();
						TbFoundation bean = (TbFoundation) foundList.get(i);
						if (null != bean) {
							cellList.add(bean.getFoundName());
							cellList.add(bean.getFoundCreateDate());
							if (bean.getFoundtypeFid() != null) {
								if (bean.getFoundtypeFid().equals("1")) {
									cellList.add("单项");
								} else {
									cellList.add("专项");
								}
							} else {
								cellList.add("");
							}
							if (bean.getIsKeep().equals("1")) {
								cellList.add("是");
							} else {
								cellList.add("否");
							}
							BigDecimal xy = new BigDecimal(0);
							boolean xyflag = true;
							for (int j = 0; j < xyList.size(); j++) {
								Object[] obj = (Object[]) xyList.get(j);
								if (obj != null) {
									Long foundId = new Long(obj[0].toString());
									if (foundId != null
											&& foundId.longValue() == bean
													.getFoundId().longValue()) {
										Object object = obj[1];
										object = object == null ? 0 : object;
										xy = new BigDecimal(object.toString());
										cellList.add(xy);
										xyflag = false;
									}
								}
							}
							if (xyflag) {
								cellList.add("0");
							}

							BigDecimal dz = new BigDecimal(0);
							;
							xyflag = true;
							for (int j = 0; j < dzList.size(); j++) {
								Object[] obj = (Object[]) dzList.get(j);
								if (obj != null) {
									Long foundId = new Long(obj[0].toString());
									if (foundId != null
											&& foundId.longValue() == bean
													.getFoundId().longValue()) {
										Object object = obj[1];
										object = object == null ? 0 : object;
										dz = new BigDecimal(object.toString());
										cellList.add(object.toString());
										xyflag = false;
									}
								}
							}
							if (xyflag) {
								cellList.add("0");
							}

							BigDecimal zc = new BigDecimal(0);
							;
							xyflag = true;
							for (int j = 0; j < zcList.size(); j++) {
								Object[] obj = (Object[]) zcList.get(j);
								if (obj != null) {
									Long foundId = new Long(obj[0].toString());
									if (foundId != null
											&& foundId.longValue() == bean
													.getFoundId().longValue()) {
										Object object = obj[1];
										object = object == null ? 0 : object;
										zc = new BigDecimal(object.toString());
										cellList.add(object.toString());
										xyflag = false;
									}
								}
							}
							if (xyflag) {
								cellList.add("0");
							}
							BigDecimal yuObj = dz.subtract(zc);
							if (yuObj == new BigDecimal(0)) {
								cellList.add(0);
							} else {
								cellList.add(yuObj);
							}
							BigDecimal timeDz = new BigDecimal(0);
							BigDecimal timeZc = new BigDecimal(0);
							if (null != startDate && !"".equals(startDate)
									&& null != endDate && !"".equals(endDate)) {
								if ((timeDzList != null && !timeDzList
										.isEmpty())
										|| (timeZcList != null && !timeZcList
												.isEmpty())) {
									xyflag = true;
									if (timeDzList != null
											&& !timeDzList.isEmpty()) {
										for (int j = 0; j < timeDzList.size(); j++) {
											Object[] obj = (Object[]) timeDzList
													.get(j);
											if (obj != null) {
												Long foundId = new Long(
														obj[0].toString());
												if (foundId != null
														&& foundId.longValue() == bean
																.getFoundId()
																.longValue()) {
													Object object = obj[1];
													object = object == null ? 0
															: object;
													timeDz = new BigDecimal(
															object.toString());
													if (timeDz != new BigDecimal(
															0)) {
														xyflag = false;
														cellList.add(timeDz);
													}
												}
											}
										}
									}
									if (xyflag) {
										cellList.add("0");
									}
									boolean flag = true;
									if (timeZcList != null
											&& !timeZcList.isEmpty()) {
										for (int j = 0; j < timeZcList.size(); j++) {
											Object[] obj = (Object[]) timeZcList
													.get(j);
											if (obj != null) {
												Long foundId = new Long(
														obj[0].toString());
												if (foundId != null
														&& foundId.longValue() == bean
																.getFoundId()
																.longValue()) {
													Object object = obj[1];
													object = object == null ? 0
															: object;
													timeZc = new BigDecimal(
															object.toString());
													if (timeZc != new BigDecimal(
															0)) {
														flag = false;
														cellList.add(timeZc);
													}
												}
											}
										}
									}
									if (flag) {
										cellList.add("0");
									}
									if (xyflag && flag) {
										continue;
									}
								} else {
									continue;
								}
							}
							dzBig = dzBig.add(dz);
							zcBig = zcBig.add(zc);
							xyBig = xyBig.add(xy);
							if (null != startDate && !"".equals(startDate)
									&& null != endDate && !"".equals(endDate)
									&& timeDz == new BigDecimal(0)
									&& timeZc == new BigDecimal(0)) {
								continue;
							}
							timeDzBig = timeDzBig.add(timeDz);
							timeZcBig = timeZcBig.add(timeZc);
							beanList.add(cellList);
							idList.add(bean.getFoundId());
						}
					}
				}
				request.setAttribute("amountXy", xyBig);
				request.setAttribute("amountDz", dzBig);
				request.setAttribute("amountZc", zcBig);
				request.setAttribute("amountSur", dzBig.subtract(zcBig));
				if (null != startDate && !"".equals(startDate)
						&& null != endDate && !"".equals(endDate)) {
					request.setAttribute("amountTimeDz", timeDzBig);
					request.setAttribute("amountTimeZc", timeZcBig);
				}
			} else if (type.equals("2")) {// 项目支出明细
				String hql = "from TbProPay a where 1=1";
				if (null != startDate && !"".equals(startDate)
						&& null != endDate && !"".equals(endDate)) {
					hql += " and a.payDate>='" + startDate
							+ "' and a.payDate<='" + endDate + "'";
				}
				if (null != foundName && !"".equals(foundName)) {
					hql += " and a.tbFoundation.foundName like '%" + foundName
							+ "%'";
					request.setAttribute("foundName", foundName);
				}
				hql += " order by a.payDate desc";
				List payList = this.getCommonBo().findHQL(hql);
				if (payList != null && !payList.isEmpty()) {
					double sumPay = 0;
					for (int i = 0; i < payList.size(); i++) {
						TbProPay tbProPay = (TbProPay) payList.get(i);
						List<String> cellList = this.getList();
						if (tbProPay.getTbFoundation() != null) {
							cellList.add(tbProPay.getTbFoundation()
									.getFoundName());
						} else {
							cellList.add("");
						}
						if (tbProPay.getTbFields() != null
								&& tbProPay.getTbFields().getTbField() != null) {
							cellList.add(tbProPay.getTbFields().getTbField()
									.getFieldName()
									+ "--"
									+ tbProPay.getTbFields().getFieldName());
						} else {
							cellList.add("");
						}
						String payAmount = tbProPay.getPayAmount();
						if (null != payAmount && !"".equals(payAmount)) {
							sumPay += Double.parseDouble(payAmount);
							cellList.add(payAmount);
						} else {
							cellList.add("0");
						}
						if (tbProPay.getTbDonationAccout() != null) {
							cellList.add(tbProPay.getTbDonationAccout()
									.getAccountName());
						} else {
							cellList.add("不详");
						}
						cellList.add(tbProPay.getPayDate());
						if (tbProPay.getJsdwObject() != null) {
							String jsdwObject = tbProPay.getJsdwObject();
							TbAcceptCompany tt = (TbAcceptCompany) this
									.getCommonBo().get(TbAcceptCompany.class,
											new Long(jsdwObject));
							if (tt != null) {
								cellList.add(tbProPay.getTbAcceptCompany()
										.getComName());
								cellList.add(tbProPay.getTbAcceptCompany()
										.getRelationName());
							} else {
								cellList.add("不详");
								cellList.add("不详");
							}
						} else {
							cellList.add("无");
							cellList.add("无");
						}
						if (tbProPay.getTbFoundation() != null) {
							idList.add(tbProPay.getTbFoundation().getFoundId()
									+ "");
						} else {
							idList.add("");
						}
						beanList.add(cellList);
					}
					request.setAttribute("sumPay", sumPay);
				}
			} else if (type.equals("3") || type.equals("4") || type.equals("5")) {
				String proStatus = request.getParameter("proStatus");
				boolean flag = false;
				String hql = "from TbProDetail a where 1=1";
				if (null != startDate && !"".equals(startDate)
						&& null != endDate && !"".equals(endDate)) {
					if (type.equals("5")) {
						hql += " and a.dealDate>='" + startDate
								+ "' and a.dealDate<='" + endDate + "'";
					} else if (type.equals("3")) {
						hql += " and a.toDate>='" + startDate
								+ "' and a.toDate<='" + endDate + "'";
					} else if (type.equals("4")) {
						hql += " and a.tbProtocol.dealDate>='" + startDate
								+ "' and a.tbProtocol.dealDate<='" + endDate
								+ "'";
					}
				}
				if (type.equals("5")) {
					// 配比状态选项（0否、1未申报、2申报中、3申报成功、4申报失败）
					String status = request.getParameter("status");
					if (null != status && !"".equals(status)) {
						if (status.equals("0")) {
							hql += " and a.sfpb=2";
						} else {
							hql += " and a.sfpb=1 and a.status=" + status;
						}
						request.setAttribute("status", status);
					}
				} else if (type.equals("3")) {
					hql += " and a.toAccount is not null and a.toAccount>0";
				} else if (type.equals("4")) {
					hql += " and (a.toAccount is null or a.toAccount=0)";
				}
				if (null != proStatus && !"".equals(proStatus)) {
					hql += " and a.tbProtocol.proStatus=" + proStatus;
					request.setAttribute("proStatus", proStatus);
				}
				if (null != foundName && !"".equals(foundName)) {
					hql += " and a.tbProtocol.tbFoundation.foundName like '%"
							+ foundName + "%'";
					request.setAttribute("foundName", foundName);
				}
				// 计算协议总金额
				String sumHql = "select SUM(t.proAmount) from TbProtocol t where t.proId in (select distinct(a.proId) "
						+ hql + ")";
				Object obj = this.getCommonBo().getHQLUnique(sumHql);
				obj = obj == null ? 0 : obj;
				request.setAttribute("sumProAmount", obj);
				hql += " order by a.toDate desc";
				List proDetailList = this.getCommonBo().findHQL(hql);
				String typeHql = hql;
				if (type.equals("3") || type.equals("5")) {
					// 计算到账总金额
					hql = "select SUM(toAmount) " + hql;
				} else {
					hql = "select SUM(dealAmount) " + hql;
				}
				obj = this.getCommonBo().getHQLUnique(hql);
				obj = obj == null ? 0 : obj;
				request.setAttribute("sumToAmount", obj);
				if (type.equals("5")) {
					String sbHql = "select SUM(dealAmount) " + typeHql;
					obj = this.getCommonBo().getHQLUnique(sbHql);
					obj = obj == null ? 0 : obj;
					request.setAttribute("sumSbAmount", obj);
					String sjHql = "select SUM(realAmount) " + typeHql;
					obj = this.getCommonBo().getHQLUnique(sjHql);
					obj = obj == null ? 0 : obj;
					request.setAttribute("sumSjAmount", obj);
				}
				if (null != proDetailList && !proDetailList.isEmpty()) {
					for (int i = 0; i < proDetailList.size(); i++) {
						TbProDetail tbProDetail = (TbProDetail) proDetailList
								.get(i);
						List<String> cellList = this.getList();
						cellList.add(tbProDetail.getTbProtocol()
								.getTbFoundation().getFoundName());
						cellList.add(tbProDetail.getTbProtocol().getProCode());
						cellList.add(tbProDetail.getTbProtocol().getProName());
						cellList.add(tbProDetail.getTbProtocol().getProAmount());
						cellList.add(tbProDetail.getTbProtocol()
								.getStrJzfObject());
						cellList.add(tbProDetail.getTbProtocol()
								.getStrSyfObject());
						String signStr = "";
						String startTimeStr = tbProDetail.getTbProtocol()
								.getStartTime();
						String endTimeStr = tbProDetail.getTbProtocol()
								.getEndTime();
						if (null != startTimeStr && !"".equals(startTimeStr)
								&& null != endTimeStr && !"".equals(endTimeStr)) {
							signStr = startTimeStr + "~" + endTimeStr;
						}
						cellList.add(signStr);
						String proStatusStr = tbProDetail.getTbProtocol()
								.getProStatus();
						if (null != proStatusStr && !"".equals(proStatusStr)) {
							TbFields fieldBean = (TbFields) this
									.getCommonBo()
									.get(TbFields.class, new Long(proStatusStr));
							if (fieldBean != null) {
								cellList.add(fieldBean.getFieldName());
							} else {
								cellList.add("");
							}
						} else {
							cellList.add("");
						}
						if (tbProDetail.getTbDonationAccout() != null) {
							cellList.add(tbProDetail.getTbDonationAccout()
									.getAccountName());
						} else {
							cellList.add("");
						}
						if (type.equals("4")) {
							cellList.add(tbProDetail.getDealDate());
							cellList.add(tbProDetail.getDealAmount());
						} else {
							cellList.add(tbProDetail.getToDate());
							cellList.add(tbProDetail.getToAmount());
						}
						String proUse = tbProDetail.getProUse();
						String useStr = "";
						if (null != proUse && !"".equals(proUse)) {
							TbFields fieldBean = (TbFields) this.getCommonBo()
									.get(TbFields.class, new Long(proUse));
							if (fieldBean != null) {
								if (fieldBean.getTbField() != null) {
									useStr = fieldBean.getTbField()
											.getFieldName()
											+ "--"
											+ fieldBean.getFieldName();
								} else {
									useStr = fieldBean.getFieldName();
								}
							}
						}
						cellList.add(useStr);
						if (type.equals("5")) {
							cellList.add(tbProDetail.getDealAmount());
							cellList.add(tbProDetail.getRealAmount());
						}
						idList.add(tbProDetail.getTbProtocol().getFoundId());
						beanList.add(cellList);
					}
				}
			}
			request.setAttribute("type", type);
			request.setAttribute("proList", beanList);
			request.setAttribute("idList", idList);
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "proList";
	}

	/**
	 * 基金项目列表导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String listDoExport() throws Exception {
		List beanList = this.getList();
		String fileName = "基金项目列表";
		String ajax = "[{\"result\":\"error\"}]";
		// 得到request
		HttpServletRequest request = this.getRequest();
		// 获取系统时间
		Date dateNow = this.getCommonBo().getSysDate();
		// 获取当前时间相关参数
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(dateNow);
		// 当前年份
		int nowYear = c.get(Calendar.YEAR);
		request.setAttribute("nowYear", nowYear);
		String startDate = "";// 开始时间
		String endDate = "";// 结束时间
		String year = request.getParameter("year");
		if (null != year && !"".equals(year)) {
			startDate = year + "-01-01";
			endDate = year + "-12-31";
		}
		// 自定义日期
		String startDateObj = request.getParameter("startDate");
		String endDateObj = request.getParameter("endDate");
		if (null != startDateObj && !"".equals(startDateObj)
				&& null != endDateObj && !"".equals(endDateObj)) {
			startDate = startDateObj;
			endDate = endDateObj;
		}
		// 开始时间不为空，结束时间为空时，结束时间设为当前日期
		if ((null != startDateObj && !"".equals(startDateObj))
				&& (null == endDateObj || "".equals(endDateObj))) {
			startDate = startDateObj;
			endDate = DateUtil.format(dateNow, "yyyy-MM-dd");
			endDateObj = endDate;
		}
		if ((null == startDate || "".equals(startDate))
				&& (null == endDate || "".equals(endDate))) {
			ajax = "[{\"result\":\"warning\",\"value\":\"请选择时间！\"}]";
			this.sendResponse(this.getResponse(), ajax);
			return null;
		}
		String foundName = request.getParameter("foundName");
		String status = request.getParameter("status");
		String foundtypeFid = request.getParameter("foundtypeFid");
		String isKeep = request.getParameter("isKeep");
		String hql = "from TbFoundation a where 1=1";
		if (null != status && !"".equals(status)) {
			hql += " and a.foundStatus=" + status;
			request.setAttribute("status", status);
		}
		if (null != foundName && !"".equals(foundName)) {
			hql += " and a.foundName like '%" + foundName + "%'";
			request.setAttribute("foundName", foundName);
		}
		if (null != foundtypeFid && !"".equals(foundtypeFid)) {
			hql += " and a.foundtypeFid=" + foundtypeFid;
			request.setAttribute("foundtypeFid", foundtypeFid);
		}
		if (null != isKeep && !"".equals(isKeep)) {
			hql += " and a.isKeep=" + isKeep;
			request.setAttribute("isKeep", isKeep);
		}
		// 协议金额
		String hqlXy = "select t.foundId,sum(t.proAmount) from TbProtocol t where t.foundId in (select a.foundId "
				+ hql
				+ ") GROUP BY t.foundId,t.tbFoundation.creationTime order by t.tbFoundation.creationTime desc";
		List xyList = this.getCommonBo().findHQL(hqlXy);
		// 查询协议下实际到账总额
		String hqlDz = "select tp.tbProtocol.foundId,sum(tp.toAmount) from TbProDetail tp where tp.proId in (select p.proId from TbProtocol p where p.foundId in (select a.foundId "
				+ hql
				+ ")) GROUP BY tp.tbProtocol.foundId,tp.tbProtocol.tbFoundation.creationTime order by tp.tbProtocol.tbFoundation.creationTime desc";
		List dzList = this.getCommonBo().findHQL(hqlDz);
		// 所有支出总金额
		String hqlZc = "select t.foundId,sum(t.payAmount) from TbProPay t where t.foundId in (select a.foundId "
				+ hql
				+ ") GROUP BY t.foundId,t.tbFoundation.creationTime order by t.tbFoundation.creationTime desc";
		List zcList = this.getCommonBo().findHQL(hqlZc);
		List timeDzList = this.getList();
		List timeZcList = this.getList();
		if (null != startDate && !"".equals(startDate) && null != endDate
				&& !"".equals(endDate)) {
			hqlDz = "select tp.tbProtocol.foundId,sum(tp.toAmount) from TbProDetail tp where tp.toDate>='"
					+ startDate
					+ "' and tp.toDate<='"
					+ endDate
					+ "' and tp.proId in (select p.proId from TbProtocol p where p.foundId in (select a.foundId "
					+ hql
					+ ")) GROUP BY tp.tbProtocol.foundId,tp.tbProtocol.tbFoundation.creationTime order by tp.tbProtocol.tbFoundation.creationTime desc";
			timeDzList = this.getCommonBo().findHQL(hqlDz);
			// 所有支出总金额
			hqlZc = "select t.foundId,sum(t.payAmount) from TbProPay t where t.payDate>='"
					+ startDate
					+ "' and t.payDate<='"
					+ endDate
					+ "' and t.foundId in (select a.foundId "
					+ hql
					+ ") GROUP BY t.foundId,t.tbFoundation.creationTime order by t.tbFoundation.creationTime desc";
			timeZcList = this.getCommonBo().findHQL(hqlZc);
		}
		hql += "order by a.creationTime desc";
		List foundList = this.getCommonBo().findHQL(hql);
		BigDecimal xyBig = new BigDecimal(0);
		BigDecimal dzBig = new BigDecimal(0);
		BigDecimal zcBig = new BigDecimal(0);
		BigDecimal timeDzBig = new BigDecimal(0);
		BigDecimal timeZcBig = new BigDecimal(0);
		if (foundList != null && !foundList.isEmpty()) {
			for (int i = 0; i < foundList.size(); i++) {
				List cellList = this.getList();
				TbFoundation bean = (TbFoundation) foundList.get(i);
				if (null != bean) {
					cellList.add(bean.getFoundName());
					cellList.add(bean.getFoundCreateDate());
					if (bean.getFoundtypeFid() != null) {
						if (bean.getFoundtypeFid().equals("1")) {
							cellList.add("单项");
						} else {
							cellList.add("专项");
						}
					} else {
						cellList.add("");
					}
					if (bean.getIsKeep().equals("1")) {
						cellList.add("是");
					} else {
						cellList.add("否");
					}
					BigDecimal xy = new BigDecimal(0);
					boolean xyflag = true;
					for (int j = 0; j < xyList.size(); j++) {
						Object[] obj = (Object[]) xyList.get(j);
						if (obj != null) {
							Long foundId = new Long(obj[0].toString());
							if (foundId != null
									&& foundId.longValue() == bean.getFoundId()
											.longValue()) {
								Object object = obj[1];
								object = object == null ? 0 : object;
								xy = new BigDecimal(object.toString());
								cellList.add(xy);
								xyflag = false;
							}
						}
					}
					if (xyflag) {
						cellList.add("0");
					}

					BigDecimal dz = new BigDecimal(0);
					;
					xyflag = true;
					for (int j = 0; j < dzList.size(); j++) {
						Object[] obj = (Object[]) dzList.get(j);
						if (obj != null) {
							Long foundId = new Long(obj[0].toString());
							if (foundId != null
									&& foundId.longValue() == bean.getFoundId()
											.longValue()) {
								Object object = obj[1];
								object = object == null ? 0 : object;
								dz = new BigDecimal(object.toString());
								cellList.add(object.toString());
								xyflag = false;
							}
						}
					}
					if (xyflag) {
						cellList.add("0");
					}

					BigDecimal zc = new BigDecimal(0);
					;
					xyflag = true;
					for (int j = 0; j < zcList.size(); j++) {
						Object[] obj = (Object[]) zcList.get(j);
						if (obj != null) {
							Long foundId = new Long(obj[0].toString());
							if (foundId != null
									&& foundId.longValue() == bean.getFoundId()
											.longValue()) {
								Object object = obj[1];
								object = object == null ? 0 : object;
								zc = new BigDecimal(object.toString());
								cellList.add(object.toString());
								xyflag = false;
							}
						}
					}
					if (xyflag) {
						cellList.add("0");
					}
					BigDecimal yuObj = dz.subtract(zc);
					if (yuObj == new BigDecimal(0)) {
						cellList.add(0);
					} else {
						cellList.add(yuObj);
					}
					BigDecimal timeDz = new BigDecimal(0);
					BigDecimal timeZc = new BigDecimal(0);
					if (null != startDate && !"".equals(startDate)
							&& null != endDate && !"".equals(endDate)) {
						if ((timeDzList != null && !timeDzList.isEmpty())
								|| (timeZcList != null && !timeZcList.isEmpty())) {
							xyflag = true;
							if (timeDzList != null && !timeDzList.isEmpty()) {
								for (int j = 0; j < timeDzList.size(); j++) {
									Object[] obj = (Object[]) timeDzList.get(j);
									if (obj != null) {
										Long foundId = new Long(
												obj[0].toString());
										if (foundId != null
												&& foundId.longValue() == bean
														.getFoundId()
														.longValue()) {
											Object object = obj[1];
											object = object == null ? 0
													: object;
											timeDz = new BigDecimal(
													object.toString());
											if (timeDz != new BigDecimal(0)) {
												xyflag = false;
												cellList.add(timeDz);
											}
										}
									}
								}
							}
							if (xyflag) {
								cellList.add("0");
							}
							boolean flag = true;
							if (timeZcList != null && !timeZcList.isEmpty()) {
								for (int j = 0; j < timeZcList.size(); j++) {
									Object[] obj = (Object[]) timeZcList.get(j);
									if (obj != null) {
										Long foundId = new Long(
												obj[0].toString());
										if (foundId != null
												&& foundId.longValue() == bean
														.getFoundId()
														.longValue()) {
											Object object = obj[1];
											object = object == null ? 0
													: object;
											timeZc = new BigDecimal(
													object.toString());
											if (timeZc != new BigDecimal(0)) {
												flag = false;
												cellList.add(timeZc);
											}
										}
									}
								}
							}
							if (flag) {
								cellList.add("0");
							}
							if (xyflag && flag) {
								continue;
							}
						} else {
							continue;
						}
					}
					dzBig = dzBig.add(dz);
					zcBig = zcBig.add(zc);
					xyBig = xyBig.add(xy);
					if (null != startDate && !"".equals(startDate)
							&& null != endDate && !"".equals(endDate)
							&& timeDz == new BigDecimal(0)
							&& timeZc == new BigDecimal(0)) {
						continue;
					}
					timeDzBig = timeDzBig.add(timeDz);
					timeZcBig = timeZcBig.add(timeZc);
					beanList.add(cellList);
				}
			}
		}
		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = null;
			// 创建单元格样式
			HSSFCellStyle cellStyle = wb.createCellStyle();
			// 指定单元格居中对齐
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// 指定单元格垂直居中对齐
			cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			// 指定当单元格内容显示不下时自动换行
			cellStyle.setWrapText(true);
			// 设置单元格字体
			HSSFFont font = wb.createFont();
			font.setFontName("宋体");
			font.setFontHeight((short) 250);
			cellStyle.setFont(font);
			cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
			cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
			cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
			cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
			HSSFCellStyle cellStyleSmall = wb.createCellStyle();
			cellStyleSmall.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyleSmall.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			cellStyleSmall.setWrapText(true);
			cellStyleSmall.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
			cellStyleSmall.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
			cellStyleSmall.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
			cellStyleSmall.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
			HSSFCellStyle cellStyleName = wb.createCellStyle();
			cellStyleName.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			cellStyleName.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			cellStyleName.setWrapText(true);
			cellStyleName.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
			cellStyleName.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
			cellStyleName.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
			cellStyleName.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
			int rowIndex = 1;
			if (beanList != null && !beanList.isEmpty()) {
				sheet = wb.createSheet("基金项目列表");
				// 计算该报表的列数
				int number = 8;
				if (null != startDate && !"".equals(startDate)
						&& null != endDate && !"".equals(endDate)) {
					number = 10;
				}
				// 给工作表列定义列宽(实际应用自己更改列数)
				for (short i = 0; i < number; i++) {
					int width = 5000;
					if (i == 0) {
						width = 8000;
					} else if (i == 8 || i == 9) {
						width = 16000;
					}
					sheet.setColumnWidth(i, (short) width);
				}
				HSSFRow row1 = null;
				row1 = sheet.createRow(0);// 第1行
				row1.setHeight((short) 450);
				List titileList = this.getList();
				titileList.add("项目名称");
				titileList.add("项目创建时间");
				titileList.add("项目分类");
				titileList.add("是否留本");
				titileList.add("协议总金额");
				titileList.add("到账总额");
				titileList.add("支出总额");
				titileList.add("剩余金额");

				if (null != startDateObj && !"".equals(startDateObj)
						&& null != endDateObj && !"".equals(endDateObj)) {
					titileList.add(startDate + "~" + endDate + "到账金额");
					titileList.add(startDate + "~" + endDate + "支出金额");
				} else if (null != year && !"".equals(year)) {
					titileList.add(year + "到账金额");
					titileList.add(year + "支出金额");
				} else {
					titileList.add("到账金额");
					titileList.add("支出金额");
				}
				for (int i = 0; i < titileList.size(); i++) {
					HSSFCell cell = row1.createCell((short) i);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(new HSSFRichTextString(
							(String) titileList.get(i)));
				}
				for (int i = 0; i < beanList.size(); i++) {
					// 单元格
					HSSFRow row = sheet.createRow((short) rowIndex++);
					row.setHeight((short) 450);
					int count = 0;
					List tempList = (List) beanList.get(i);
					if (null != tempList && !tempList.isEmpty()) {
						for (int j = 0; j < tempList.size(); j++) {
							if (tempList.get(j) != null) {
								String text = tempList.get(j).toString();
								HSSFCell cell = row
										.createCell((short) (count++));
								cell.setCellStyle(cellStyleSmall);
								cell.setCellValue(new HSSFRichTextString(text));
							}
						}
					}
				}
			} else {
				ajax = "[{\"result\":\"warning\",\"value\":\"暂无数据\"}]";
				this.sendResponse(this.getResponse(), ajax);
				return null;
			}
			// 保存文件
			String fielCurName = fileName
					+ DateUtil.format(new Date(), "yyyyMMddHHmmss") + ".xls";
			String fileNewName = fileName + ".xls";
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
			ajax = "[{" + "\"result\":\"success\"," + "\"name\":\""
					+ fileNewName + "\"," + "\"path\":\"download/"
					+ fielCurName + "\"" + "}]";
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.sendResponse(this.getResponse(), ajax);
		return null;
	}

	/**
	 * 支出列表导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String payDoExport() throws Exception {
		String fileName = "支出列表";
		String ajax = "[{\"result\":\"error\"}]";
		// 得到request
		HttpServletRequest request = this.getRequest();
		// 获取系统时间
		Date dateNow = this.getCommonBo().getSysDate();
		// 获取当前时间相关参数
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(dateNow);
		// 当前年份
		int nowYear = c.get(Calendar.YEAR);
		request.setAttribute("nowYear", nowYear);
		String startDate = "";// 开始时间
		String endDate = "";// 结束时间
		String year = request.getParameter("year");
		if (null != year && !"".equals(year)) {
			startDate = year + "-01-01";
			endDate = year + "-12-31";
		}
		// 自定义日期
		String startDateObj = request.getParameter("startDate");
		String endDateObj = request.getParameter("endDate");
		if (null != startDateObj && !"".equals(startDateObj)
				&& null != endDateObj && !"".equals(endDateObj)) {
			startDate = startDateObj;
			endDate = endDateObj;
		}
		// 开始时间不为空，结束时间为空时，结束时间设为当前日期
		if ((null != startDateObj && !"".equals(startDateObj))
				&& (null == endDateObj || "".equals(endDateObj))) {
			startDate = startDateObj;
			endDate = DateUtil.format(dateNow, "yyyy-MM-dd");
		}
		String hql = "from TbProPay a where 1=1";
		if (null != startDate && !"".equals(startDate) && null != endDate
				&& !"".equals(endDate)) {
			hql += " and a.payDate>='" + startDate + "' and a.payDate<='"
					+ endDate + "'";
		}
		String foundName = request.getParameter("foundName");
		if (null != foundName && !"".equals(foundName)) {
			hql += " and a.tbFoundation.foundName like '%" + foundName + "%'";
			request.setAttribute("foundName", foundName);
		}
		hql += " order by a.payDate desc";
		List payList = this.getCommonBo().findHQL(hql);
		if (payList == null || payList.isEmpty() || payList.size() == 0) {
			ajax = "[{\"result\":\"warning\",\"value\":\"暂无数据\"}]";
			this.sendResponse(this.getResponse(), ajax);
			return null;
		}
		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = null;
			// 创建单元格样式
			HSSFCellStyle cellStyle = wb.createCellStyle();
			// 指定单元格居中对齐
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// 指定单元格垂直居中对齐
			cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			// 指定当单元格内容显示不下时自动换行
			cellStyle.setWrapText(true);
			// 设置单元格字体
			HSSFFont font = wb.createFont();
			font.setFontName("宋体");
			font.setFontHeight((short) 250);
			cellStyle.setFont(font);
			cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
			cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
			cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
			cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
			HSSFCellStyle cellStyleSmall = wb.createCellStyle();
			cellStyleSmall.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyleSmall.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			cellStyleSmall.setWrapText(true);
			cellStyleSmall.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
			cellStyleSmall.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
			cellStyleSmall.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
			cellStyleSmall.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
			HSSFCellStyle cellStyleName = wb.createCellStyle();
			cellStyleName.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			cellStyleName.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			cellStyleName.setWrapText(true);
			cellStyleName.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
			cellStyleName.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
			cellStyleName.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
			cellStyleName.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
			int rowIndex = 0;
			sheet = wb.createSheet("支出信息列表");
			// 计算该报表的列数
			int number = 7;
			sheet.setColumnWidth((short) 0, (short) 8000);
			sheet.setColumnWidth((short) 1, (short) 14000);
			// 给工作表列定义列宽(实际应用自己更改列数)
			for (short i = 2; i < number; i++) {
				sheet.setColumnWidth(i, (short) 4500);
			}
			HSSFRow row1 = null;
			row1 = sheet.createRow(rowIndex++);// 第1行
			row1.setHeight((short) 450);
			List titileList = this.getList();
			titileList.add("项目名称");
			titileList.add("指定用途");
			titileList.add("支出金额");
			titileList.add("支出账户");
			titileList.add("支出日期");
			titileList.add("接受单位");
			titileList.add("联系人");
			for (int i = 0; i < titileList.size(); i++) {
				HSSFCell cell = row1.createCell((short) i);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(new HSSFRichTextString((String) titileList
						.get(i)));
			}
			for (int i = 0; i < payList.size(); i++) {
				int count = 0;
				TbProPay tbProPay = (TbProPay) payList.get(i);
				if (tbProPay != null) {
					// 单元格
					HSSFRow row = sheet.createRow((short) rowIndex++);
					row.setHeight((short) 450);
					List<String> cellList = this.getList();
					if (tbProPay.getTbFoundation().getFoundName() != null) {
						cellList.add(tbProPay.getTbFoundation().getFoundName());
					} else {
						cellList.add("");
					}
					if (tbProPay.getTbFields() != null
							&& tbProPay.getTbFields().getTbField() != null) {
						cellList.add(tbProPay.getTbFields().getTbField()
								.getFieldName()
								+ "--" + tbProPay.getTbFields().getFieldName());
					} else {
						cellList.add("");
					}
					cellList.add(tbProPay.getPayAmount());
					if (tbProPay.getTbDonationAccout() != null) {
						cellList.add(tbProPay.getTbDonationAccout()
								.getAccountName());
					} else {
						cellList.add("不详");
					}
					cellList.add(tbProPay.getPayDate());
					if (tbProPay.getJsdwObject() != null) {
						String jsdwObject = tbProPay.getJsdwObject();
						TbAcceptCompany tt = (TbAcceptCompany) this
								.getCommonBo().get(TbAcceptCompany.class,
										new Long(jsdwObject));
						if (tt != null) {
							cellList.add(tbProPay.getTbAcceptCompany()
									.getComName());
							cellList.add(tbProPay.getTbAcceptCompany()
									.getRelationName());
						} else {
							cellList.add("不详");
							cellList.add("不详");
						}
					} else {
						cellList.add("无");
						cellList.add("无");
					}
					for (int m = 0; m < cellList.size(); m++) {
						HSSFCell cell = row.createCell((short) (count++));
						cell.setCellStyle(cellStyleSmall);
						cell.setCellValue(new HSSFRichTextString(cellList
								.get(m)));
					}
				}

			}
			// 保存文件
			String fielCurName = fileName
					+ DateUtil.format(new Date(), "yyyyMMddHHmmss") + ".xls";
			String fileNewName = fileName + ".xls";
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
			ajax = "[{" + "\"result\":\"success\"," + "\"name\":\""
					+ fileNewName + "\"," + "\"path\":\"download/"
					+ fielCurName + "\"" + "}]";
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.sendResponse(this.getResponse(), ajax);
		return null;
	}

	/**
	 * 协议列表导出（基于项目）
	 * 
	 * @return
	 * @throws Exception
	 */
	public String proclDoExport() throws Exception {
		// 得到request
		HttpServletRequest request = this.getRequest();
		String proType = request.getParameter("proType");
		String fileName = "配比信息列表";
		// 类型 1：配比 2：到账 3：未到账
		if (null == proType || "".equals(proType)) {
			proType = "1";
		}
		if (proType.equals("1")) {
			fileName = "配比信息列表";
		} else if (proType.equals("2")) {
			fileName = "到账情况信息列表";
		} else if (proType.equals("3")) {
			fileName = "未到账情况信息列表";
		}
		String ajax = "[{\"result\":\"error\"}]";
		// 获取系统时间
		Date dateNow = this.getCommonBo().getSysDate();
		// 获取当前时间相关参数
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(dateNow);
		// 当前年份
		int nowYear = c.get(Calendar.YEAR);
		request.setAttribute("nowYear", nowYear);
		String startDate = "";// 开始时间
		String endDate = "";// 结束时间
		String year = request.getParameter("year");
		if (null != year && !"".equals(year)) {
			startDate = year + "-01-01";
			endDate = year + "-12-31";
		}
		// 自定义日期
		String startDateObj = request.getParameter("startDate");
		String endDateObj = request.getParameter("endDate");
		if (null != startDateObj && !"".equals(startDateObj)
				&& null != endDateObj && !"".equals(endDateObj)) {
			startDate = startDateObj;
			endDate = endDateObj;
		}
		// 开始时间不为空，结束时间为空时，结束时间设为当前日期
		if ((null != startDateObj && !"".equals(startDateObj))
				&& (null == endDateObj || "".equals(endDateObj))) {
			startDate = startDateObj;
			endDate = DateUtil.format(dateNow, "yyyy-MM-dd");
		}
		String proStatus = request.getParameter("proStatus");
		String hql = "from TbProDetail a where 1=1";
		if (null != startDate && !"".equals(startDate) && null != endDate
				&& !"".equals(endDate)) {
			if (proType.equals("1")) {
				hql += " and a.dealDate>='" + startDate + "' and a.dealDate<='"
						+ endDate + "'";
			}
			if (proType.equals("2")) {
				hql += " and a.toDate>='" + startDate + "' and a.toDate<='"
						+ endDate + "'";
			}
			if (proType.equals("1")) {
				// 配比状态选项（0否、1未申报、2申报中、3申报成功、4申报失败）
				String status = request.getParameter("status");
				if (null != status && !"".equals(status)) {
					if (status.equals("0")) {
						hql += " and a.sfpb=2";
					} else {
						hql += " and a.sfpb=1 and a.status=" + status;
					}
					request.setAttribute("status", status);
				}
			} else if (proType.equals("2")) {
				hql += " and a.toAccount is not null and a.toAccount>0";
			} else if (proType.equals("3")) {
				hql += " and (a.toAccount is null or a.toAccount=0)";
			}
		}
		if (null != proStatus && !"".equals(proStatus)) {
			hql += " and a.tbProtocol.proStatus=" + proStatus;
			request.setAttribute("proStatus", proStatus);
		}
		String foundName = request.getParameter("foundName");
		if (null != foundName && !"".equals(foundName)) {
			hql += " and a.tbProtocol.tbFoundation.foundName like '%"
					+ foundName + "%'";
			request.setAttribute("foundName", foundName);
		}
		if (proType.equals("3")) {
			hql += " and a.tbProtocol.dealDate>='" + startDate
					+ "' and a.tbProtocol.dealDate<='" + endDate + "'";
		}
		hql += " order by a.toDate desc";
		List proDetailList = this.getCommonBo().findHQL(hql);
		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = null;
			// 创建单元格样式
			HSSFCellStyle cellStyle = wb.createCellStyle();
			// 指定单元格居中对齐
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// 指定单元格垂直居中对齐
			cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			// 指定当单元格内容显示不下时自动换行
			cellStyle.setWrapText(true);
			// 设置单元格字体
			HSSFFont font = wb.createFont();
			font.setFontName("宋体");
			font.setFontHeight((short) 250);
			cellStyle.setFont(font);
			cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
			cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
			cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
			cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
			HSSFCellStyle cellStyleSmall = wb.createCellStyle();
			cellStyleSmall.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyleSmall.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			cellStyleSmall.setWrapText(true);
			cellStyleSmall.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
			cellStyleSmall.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
			cellStyleSmall.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
			cellStyleSmall.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
			HSSFCellStyle cellStyleName = wb.createCellStyle();
			cellStyleName.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			cellStyleName.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			cellStyleName.setWrapText(true);
			cellStyleName.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
			cellStyleName.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
			cellStyleName.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
			cellStyleName.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
			int rowIndex = 0;
			if (null != proDetailList && !proDetailList.isEmpty()) {
				String sheetName = "配比信息列表";
				if (proType.equals("1")) {
					sheetName = "配比信息列表";
				} else if (proType.equals("2")) {
					sheetName = "到账情况信息列表";
				} else if (proType.equals("3")) {
					sheetName = "未到账情况信息列表";
				}
				sheet = wb.createSheet(sheetName);
				// 计算该报表的列数
				int number = 14;
				// 给工作表列定义列宽(实际应用自己更改列数)
				for (short i = 0; i < number; i++) {
					int width = 5000;
					if (i == 0 || i == 6) {
						width = 8000;
					} else if (i == 11) {
						width = 10000;
					}
					sheet.setColumnWidth(i, (short) width);
				}
				HSSFRow row1 = null;
				row1 = sheet.createRow(rowIndex++);// 第1行
				row1.setHeight((short) 450);
				List titileList = this.getList();
				titileList.add("项目名称");
				titileList.add("协议编号");
				titileList.add("协议名称");
				titileList.add("协议金额");
				titileList.add("捐赠方");
				titileList.add("使用方");
				titileList.add("签订日期");
				titileList.add("协议状态");
				titileList.add("到账账户");
				if (proType.equals("3")) {
					titileList.add("承诺日期");
					titileList.add("承诺金额");
				} else {
					titileList.add("到账日期");
					titileList.add("到账金额");
				}
				titileList.add("指定用途");
				if (proType.equals("1")) {
					titileList.add("申报金额");
					titileList.add("实际金额");
				}
				for (int i = 0; i < titileList.size(); i++) {
					HSSFCell cell = row1.createCell((short) i);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(new HSSFRichTextString(
							(String) titileList.get(i)));
				}
				for (int i = 0; i < proDetailList.size(); i++) {
					int count = 0;
					// 单元格
					HSSFRow row = sheet.createRow((short) rowIndex++);
					row.setHeight((short) 450);
					TbProDetail tbProDetail = (TbProDetail) proDetailList
							.get(i);
					List<String> cellList = this.getList();
					cellList.add(tbProDetail.getTbProtocol().getTbFoundation()
							.getFoundName());
					cellList.add(tbProDetail.getTbProtocol().getProCode());
					cellList.add(tbProDetail.getTbProtocol().getProName());
					cellList.add(tbProDetail.getTbProtocol().getProAmount());
					cellList.add(tbProDetail.getTbProtocol().getStrJzfObject());
					cellList.add(tbProDetail.getTbProtocol().getStrSyfObject());
					String signStr = "";
					String startTimeStr = tbProDetail.getTbProtocol()
							.getStartTime();
					String endTimeStr = tbProDetail.getTbProtocol()
							.getEndTime();
					if (null != startTimeStr && !"".equals(startTimeStr)
							&& null != endTimeStr && !"".equals(endTimeStr)) {
						signStr = startTimeStr + "~" + endTimeStr;
					}
					cellList.add(signStr);
					String proStatusStr = tbProDetail.getTbProtocol()
							.getProStatus();
					if (null != proStatusStr && !"".equals(proStatusStr)) {
						TbFields fieldBean = (TbFields) this.getCommonBo().get(
								TbFields.class, new Long(proStatusStr));
						if (fieldBean != null) {
							cellList.add(fieldBean.getFieldName());
						} else {
							cellList.add("");
						}
					} else {
						cellList.add("");
					}
					if (tbProDetail.getTbDonationAccout() != null) {
						cellList.add(tbProDetail.getTbDonationAccout()
								.getAccountName());
					} else {
						cellList.add("");
					}
					if (proType.equals("3")) {
						cellList.add(tbProDetail.getDealDate());
						cellList.add(tbProDetail.getDealAmount());
					} else {
						cellList.add(tbProDetail.getToDate());
						cellList.add(tbProDetail.getToAmount());
					}
					String proUse = tbProDetail.getProUse();
					String useStr = "";
					if (null != proUse && !"".equals(proUse)) {
						TbFields fieldBean = (TbFields) this.getCommonBo().get(
								TbFields.class, new Long(proUse));
						if (fieldBean != null) {
							if (fieldBean.getTbField() != null) {
								useStr = fieldBean.getTbField().getFieldName()
										+ "--" + fieldBean.getFieldName();
							} else {
								useStr = fieldBean.getFieldName();
							}
						}
					}
					cellList.add(useStr);
					if (proType.equals("1")) {
						cellList.add(tbProDetail.getDealAmount());
						cellList.add(tbProDetail.getRealAmount());
					}
					for (int m = 0; m < cellList.size(); m++) {
						HSSFCell cell = row.createCell((short) (count++));
						cell.setCellStyle(cellStyleSmall);
						cell.setCellValue(new HSSFRichTextString(cellList
								.get(m)));
					}

				}
			} else {
				ajax = "[{\"result\":\"warning\",\"value\":\"暂无数据\"}]";
				this.sendResponse(this.getResponse(), ajax);
				return null;
			}
			// 保存文件
			String fielCurName = fileName
					+ DateUtil.format(new Date(), "yyyyMMddHHmmss") + ".xls";
			String fileNewName = fileName + ".xls";
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
			ajax = "[{" + "\"result\":\"success\"," + "\"name\":\""
					+ fileNewName + "\"," + "\"path\":\"download/"
					+ fielCurName + "\"" + "}]";
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.sendResponse(this.getResponse(), ajax);
		return null;
	}

	public String ajaxGetFoundationMemo() throws Exception {
		HttpServletRequest request = this.getRequest();
		TbFoundation beanFoundation = null;

		String foundationId = request.getParameter("foundationId");
		if (null != foundationId && !"".equals(foundationId)) {
			try {
				beanFoundation = (TbFoundation) this.getCommonBo().get(
						TbFoundation.class, new Long(foundationId));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		String memo = "";
		if (null != beanFoundation) {
			memo = beanFoundation.getFoundMemo();
		}

		this.sendResponse(this.getResponse(), memo);
		return null;
	}

	public String ajaxGetProtocolContent() throws Exception {
		HttpServletRequest request = this.getRequest();
		TbProtocol beanPro = null;

		String proId = request.getParameter("proId");
		if (null != proId && !"".equals(proId)) {
			try {
				beanPro = (TbProtocol) this.getCommonBo().get(TbProtocol.class,
						new Long(proId));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		String content = "";
		if (null != beanPro) {
			content = beanPro.getProContent();
		}

		this.sendResponse(this.getResponse(), content);
		return null;
	}

}
