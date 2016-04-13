package com.laungee.proj.account.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbDonationDxgl;

public class DonationDxglAction extends BaseAction {
	// �б�
	public String list() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ���ͱ��
		String code = request.getParameter("code");
		// ��������
		String name = request.getParameter("name");
		// ������ѯ
		String hql = "from TbDonationDxgl t where 1=1 ";
		List pars = this.getList();
		if (code != null && !"".equals(code)) {
			hql += " and t.code like ?";
			pars.add("%" + code + "%");
			request.setAttribute("code", code);
		}
		if (name != null && !"".equals(name)) {
			hql += " and t.name like ?";
			pars.add("%" + name + "%");
			request.setAttribute("name", name);
		}
		hql += " order by t.dxId";
		List dxlist = this.getList();
		try {
			dxlist = this.getCommonBo().findHQLPage(hql, pars);
		} catch (Exception e) {
		}
		if (dxlist != null && !dxlist.isEmpty()) {
			request.setAttribute("dxlist", dxlist);
		}
		return "list";
	}

	// ����
	public String add() throws Exception {

		return "add";
	}

	public String doAdd() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ���ͱ��
		String code = request.getParameter("code");
		// ��������
		String name = request.getParameter("name");
		// ����ժҪ
		String remark = request.getParameter("remark");
		// ����˵��
		String memo = request.getParameter("memo");
		TbDonationDxgl obj = new TbDonationDxgl();
		String msg = "";
		if (code != null && !"".equals(code)) {
			obj.setCode(code);
		} else {
			msg += "���ͱ�Ų���Ϊ�գ�";
		}
		if (name != null && !"".equals(name)) {
			obj.setName(name);
		} else {
			msg += "�������Ʋ���Ϊ�գ�";
		}
		if (msg != null && !"".equals(msg)) {
			request.setAttribute("msg", msg);
			return "add";
		}
		if (remark != null && !"".equals(remark)) {
			obj.setRemark(remark);
		}
		if (memo != null && !"".equals(memo)) {
			obj.setMemo(memo);
		}
		this.getCommonBo().save(obj);

		return SUCCESS;
	}

	// �༭
	public String edit() throws Exception {
		HttpServletRequest request = this.getRequest();
		String id = request.getParameter("id");
		TbDonationDxgl obj = null;
		if (id != null && !"".equals(id)) {
			try {
				obj = (TbDonationDxgl) this.getCommonBo().get(
						TbDonationDxgl.class, new Long(id));
			} catch (Exception e) {
			}
		}
		if (obj == null) {
			return "add";
		}
		request.setAttribute("obj", obj);

		return "edit";
	}

	public String doEdit() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ����ID
		String id = request.getParameter("id");
		TbDonationDxgl obj = null;
		if (id != null && !"".equals(id)) {
			try {
				obj = (TbDonationDxgl) this.getCommonBo().get(
						TbDonationDxgl.class, new Long(id));
			} catch (Exception e) {
			}
		}
		if (obj == null) {
			request.setAttribute("msg", "��������г���");
			request.setAttribute("id", id);
			return "add";
		}
		// ���ͱ��
		String code = request.getParameter("code");
		// ��������
		String name = request.getParameter("name");
		// ����ժҪ
		String remark = request.getParameter("remark");
		// ����˵��
		String memo = request.getParameter("memo");
		String msg = "";
		if (code != null && !"".equals(code)) {
			obj.setCode(code);
		} else {
			msg += "���ͱ�Ų���Ϊ�գ�";
		}
		if (name != null && !"".equals(name)) {
			obj.setName(name);
		} else {
			msg += "�������Ʋ���Ϊ�գ�";
		}
		if (msg != null && !"".equals(msg)) {
			request.setAttribute("msg", msg);
			request.setAttribute("obj", obj);
			return "edit";
		}
		if (remark != null && !"".equals(remark)) {
			obj.setRemark(remark);
		}
		if (memo != null && !"".equals(memo)) {
			obj.setMemo(memo);
		}
		this.getCommonBo().update(obj);

		return SUCCESS;
	}

	// ɾ��
	public String del() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ����ID
		String id = request.getParameter("id");
		TbDonationDxgl obj = null;
		if (id != null && !"".equals(id)) {
			try {
				obj = (TbDonationDxgl) this.getCommonBo().get(
						TbDonationDxgl.class, new Long(id));
			} catch (Exception e) {
			}
		}
		if (obj != null) {
			try {
				this.getCommonBo().delete(obj);
			} catch (Exception e) {
			}
		}
		return list();
	}
}
