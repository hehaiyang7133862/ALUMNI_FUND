package com.laungee.proj.zc.web.action;

import java.util.Date;
import java.util.List;

import javassist.expr.NewArray;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbFields;
import com.laungee.proj.common.model.TbFoundType;
import com.laungee.proj.common.model.TbZcproj;
import com.laungee.proj.common.model.TbZcprojProgress;
import com.laungee.proj.common.model.TbZcprojType;

public class ZcProjAction extends BaseAction {
	// �ڳ���Ŀ������
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// �����Ŀ¼���
		String id = request.getParameter("id");
		if (null == id || "".equals(id)) {
			id = "1";
		}
		// �����Ŀ¼
		TbZcprojType bean = null;
		try {
			bean = (TbZcprojType) getCommonBo().get(TbZcprojType.class,
					new Long(id));
		} catch (Exception e) {
		}
		request.setAttribute("bean", bean);
		// ��Ŀ����
		String hqlCount = "select count(*) from VwZcprojBase a";
		int count = getCommonBo().getHQLNum(hqlCount);
		request.setAttribute("count", count);
		// ��Ŀ�ϼ�����
		String hqlCount1 = "select count(*) from VwZcprojBase a where a.shelvesFlag='1'";
		int count1 = getCommonBo().getHQLNum(hqlCount1);
		request.setAttribute("count1", count1);
		// ��Ŀ�ϼ���������
		String hqlCount2 = "select count(*) from VwZcprojBase a where a.shelvesFlag='1' and a.hotFlag='1'";
		int count2 = getCommonBo().getHQLNum(hqlCount2);
		request.setAttribute("count2", count2);
		// ����
		return "index";
	}

	// ��Ŀ������JSON
	public String typeJson() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ������
		String id = request.getParameter("id");
		// ��ѯ
		try {
			// ������Ŀ¼����
			String hql = "from TbZcprojType a where a.parentId=" + id
					+ " order by a.numOrder";
			List eleList = getCommonBo().findHQL(hql);
			request.setAttribute("list_ele", eleList);
			// ������Ŀ¼��Ŀ����
			List countList = getList();
			// ������Ŀ¼�ϼ���Ŀ����
			List count1List = getList();
			// ������Ŀ¼����Ŀ�ϼ���������
			List count2List = getList();
			if (eleList != null && !eleList.isEmpty()) {
				// ��ǰϵͳʱ��
				Date dateNow = this.getCommonBo().getSysDate();
				for (int i = 0; i < eleList.size(); i++) {
					TbZcprojType obj = (TbZcprojType) eleList.get(i);
					String objIds = obj.getTypeId() + "";
					List childList = this.getList();
					getAllZcProjTypes(childList, obj);
					if (null != childList && !childList.isEmpty()) {
						for (int j = 0; j < childList.size(); j++) {
							TbZcprojType temp = (TbZcprojType) childList.get(j);
							objIds += "," + temp.getTypeId();
						}
					}
					String hqlCount = "select count(*) from VwZcprojBase a where a.projType in ("
							+ objIds + ")";
					int count = getCommonBo().getHQLNum(hqlCount);
					countList.add(count);
					String hqlCount1 = "select count(*) from VwZcprojBase a where a.projType in ("
							+ objIds + ") and a.shelvesFlag='1'";
					int count1 = getCommonBo().getHQLNum(hqlCount1);
					count1List.add(count1);
					String hqlCount2 = "select count(*) from VwZcprojBase a where a.projType in ("
							+ objIds
							+ ") and a.shelvesFlag='1' and a.hotFlag='1'";
					int count2 = getCommonBo().getHQLNum(hqlCount2);
					request.setAttribute("count2", count2);
				}
			}
			request.setAttribute("list_count", countList);
			request.setAttribute("list1_count", count1List);
			request.setAttribute("list2_count", count2List);
		} catch (Exception e) {
		}
		// ����
		return "json";
	}

	private void getAllZcProjTypes(List beanList, TbZcprojType bean)
			throws Exception {
		String hql = "from TbZcprojType a where a.parentId=" + bean.getTypeId()
				+ " order by a.numOrder";
		List list = this.getCommonBo().findHQL(hql);
		if (list != null && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				TbZcprojType obj = (TbZcprojType) list.get(i);
				if (obj != null) {
					TbZcprojType temp = new TbZcprojType();
					temp.setTypeName(bean.getTypeName() + "  >  "
							+ obj.getTypeName());
					temp.setTypeId(obj.getTypeId());
					beanList.add(temp);
					getAllZcProjTypes(beanList, obj);
				}
			}
		}
	}

	// �ڳ���Ŀ�����б�
	public String list() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ������ѯ
		String hql = "from VwZcprojBase a where 1=1 ";
		List pars = this.getList();
		// ��Ŀ����
		TbZcprojType beanType = null;
		String typeId = request.getParameter("typeId");
		if (typeId != null && !"".equals(typeId) && !"1".equals(typeId)) {
			try {
				beanType = (TbZcprojType) this.getCommonBo().get(
						TbZcprojType.class, new Long(typeId));
			} catch (Exception e) {
			}
		}
		if (beanType != null) {
			String typeIds = beanType.getTypeId().toString();
			List childList = this.getList();
			getAllZcProjTypes(childList, beanType);
			if (null != childList && !childList.isEmpty()) {
				for (int j = 0; j < childList.size(); j++) {
					TbZcprojType temp = (TbZcprojType) childList.get(j);
					typeIds += "," + temp.getTypeId();
				}
			}
			hql += " and a.projType in (" + typeIds + ")";
		}
		// ��Ŀ����
		String projName = request.getParameter("projName");
		if (projName != null && !"".equals(projName)) {
			hql += " and a.projName like ?";
			pars.add("%" + projName + "%");
		}
		// ��Ŀ����
		String projCode = request.getParameter("projCode");
		if (projCode != null && !"".equals(projCode)) {
			hql += " and a.projCode like ?";
			pars.add("%" + projCode + "%");
		}
		// ��Ŀ��ǩ
		String searchKey = request.getParameter("searchKey");
		if (searchKey != null && !"".equals(searchKey)) {
			hql += " and a.searchKey like ?";
			pars.add("%" + searchKey + "%");
		}
		// �ϼ�
		String shelvesFlag = request.getParameter("shelvesFlag");
		if (shelvesFlag != null && "1".equals(shelvesFlag)) {
			hql += " and a.shelvesFlag = ?";
			pars.add("1");
		} else if (shelvesFlag != null && !"".equals(shelvesFlag)) {
			hql += " and (a.shelvesFlag is null or a.shelvesFlag <> ?)";
			pars.add("1");
		}
		// ����
		String hotFlag = request.getParameter("hotFlag");
		if (hotFlag != null && "1".equals(hotFlag)) {
			hql += " and a.hotFlag = ?";
			pars.add("1");
		} else if (hotFlag != null && !"".equals(hotFlag)) {
			hql += " and (a.hotFlag is null or a.hotFlag <> ?)";
			pars.add("1");
		}
		// ��������
		String hotOrder = request.getParameter("hotOrder");
		if (hotOrder != null && !"".equals(hotOrder)) {
			hql += " and a.hotOrder like ?";
			pars.add(hotOrder + "%");
		}
		// Ŀ����ɺ��������
		String completedJz = request.getParameter("completedJz");
		if (completedJz != null && "1".equals(completedJz)) {
			hql += " and a.completedJz = ?";
			pars.add("1");
		} else if (completedJz != null && !"".equals(completedJz)) {
			hql += " and (a.completedJz is null or a.completedJz <> ?)";
			pars.add("1");
		}
		// ��Ŀ�������Ϊ����
		String classicStatus = request.getParameter("classicStatus");
		if (classicStatus != null && "1".equals(classicStatus)) {
			hql += " and a.classicStatus = ?";
			pars.add("1");
		} else if (classicStatus != null && !"".equals(classicStatus)) {
			hql += " and (a.classicStatus is null or a.classicStatus <> ?)";
			pars.add("1");
		}
		// �ڳ�״̬
		String zcStatus = request.getParameter("zcStatus");
		if (zcStatus != null && !"".equals(zcStatus)) {
			if ("1".equals(zcStatus)) {
				// ������ʼ
				hql += " and a.projStatus = ?";
				pars.add("1");
			} else if ("2".equals(zcStatus)) {
				// �ڳ���
				hql += " and a.projStatus = ?";
				pars.add("2");
			} else if ("3".equals(zcStatus)) {
				// �ѽ���
				hql += " and a.projStatus = ?";
				pars.add("3");
			}
		}
		// �ڳ�Ŀ�����
		String completed = request.getParameter("completed");
		if (completed != null && "1".equals(completed)) {
			hql += " and zcedPercent >= ?";
			pars.add(100F);
		} else if (completed != null && !"".equals(completed)) {
			hql += " and zcedPercent < ?";
			pars.add(100F);
		}
		// ִ�в�ѯ
		List beanList = this.getCommonBo().findHQLPage(hql, pars);
		request.setAttribute("beanList", beanList);
		// ����
		return "list";
	}

	// �ڳ���Ŀɾ��
	public String del() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ��Ŀ
		TbZcproj bean = null;
		// ��ĿID
		String projId = request.getParameter("projId");
		if (projId != null && !"".equals(projId)) {
			try {
				bean = (TbZcproj) this.getCommonBo().get(TbZcproj.class,
						new Long(projId));
			} catch (Exception e) {
			}
		}
		if (bean != null) {
			// ɾ����ĿͼƬ
			String hql = "delete from TbZcprojPic a where projId="
					+ bean.getProjId();
			this.getCommonBo().executeHql(hql);
			// ɾ����Ŀѡ��
			hql = "delete from TbZcprojOption a where projId="
					+ bean.getProjId();
			this.getCommonBo().executeHql(hql);
			// ɾ����Ŀ����
			hql = "delete from TbZcprojProgress a where projId="
					+ bean.getProjId();
			this.getCommonBo().executeHql(hql);
			// ɾ����Ŀ
			this.getCommonBo().delete(bean);
			request.setAttribute("alert", "ɾ���ɹ�");
		} else {
			request.setAttribute("alert", "���󲻴��ڻ��ѱ�ɾ��");
		}
		// ����
		return list();
	}

	public String toProjListReadOnlyPage() throws Exception {
		HttpServletRequest request = this.getRequest();
		// �����Ŀ¼���
		String id = request.getParameter("id");
		if (null == id || "".equals(id)) {
			id = "1";
		}
		// �����Ŀ¼
		TbZcprojType bean = null;
		try {
			bean = (TbZcprojType) getCommonBo().get(TbZcprojType.class,
					new Long(id));
		} catch (Exception e) {
		}
		request.setAttribute("bean", bean);
		// ��Ŀ����
		String hqlCount = "select count(*) from VwZcprojBase a";
		int count = getCommonBo().getHQLNum(hqlCount);
		request.setAttribute("count", count);
		// ��Ŀ�ϼ�����
		String hqlCount1 = "select count(*) from VwZcprojBase a where a.shelvesFlag='1'";
		int count1 = getCommonBo().getHQLNum(hqlCount1);
		request.setAttribute("count1", count1);
		// ��Ŀ�ϼ���������
		String hqlCount2 = "select count(*) from VwZcprojBase a where a.shelvesFlag='1' and a.hotFlag='1'";
		int count2 = getCommonBo().getHQLNum(hqlCount2);
		request.setAttribute("count2", count2);
		// ����
		return "index";
	}

	public String ajaxGetGressContent() throws Exception {
		HttpServletRequest request = this.getRequest();
		TbZcprojProgress beanZcprojProgress = null;

		String gressId = request.getParameter("gressId");
		if (gressId != null && !gressId.equals("")) {
			try {
				beanZcprojProgress = (TbZcprojProgress) this.getCommonBo().get(
						TbZcprojProgress.class, new Long(gressId));

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		String content = "";
		if (beanZcprojProgress != null) {
			content = beanZcprojProgress.getGressContent();
		}

		this.sendResponse(this.getResponse(), content);
		return null;
	}

	public String ajaxGetDetailPage() throws Exception {
		HttpServletRequest request = this.getRequest();
		TbZcproj beanTbZcproj = null;

		String ProjId = request.getParameter("ProjId");
		String index = request.getParameter("index");
		if (null != ProjId && !"".equals(ProjId)) {
			try {
				beanTbZcproj = (TbZcproj) this.getCommonBo().get(
						TbZcproj.class, new Long(ProjId));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		String detail = "";
		if ((null != beanTbZcproj) && null != index && !"".equals(index)
				&& StringUtils.isNumeric(index)) {
			switch (Integer.parseInt(index)) {
			case 2:
				detail = beanTbZcproj.getDetail2Content();
				break;
			case 3:
				detail = beanTbZcproj.getDetail3Content();
				break;
			case 4:
				detail = beanTbZcproj.getDetail4Content();
				break;
			case 5:
				detail = beanTbZcproj.getDetail5Content();
				break;
			case 6:
				detail = beanTbZcproj.getDetail6Content();
				break;
			case 7:
				detail = beanTbZcproj.getDetail7Content();
				break;
			case 8:
				detail = beanTbZcproj.getDetail8Content();
				break;
			case 9:
				detail = beanTbZcproj.getDetail9Content();
				break;
			case 10:
				detail = beanTbZcproj.getDetail10Content();
				break;
			default:
				detail = beanTbZcproj.getDetail1Content();
				break;
			}
		}

		this.sendResponse(this.getResponse(), detail);
		return null;
	}
}