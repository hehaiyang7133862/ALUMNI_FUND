package com.laungee.proj.account.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbDonationXxgl;
import com.laungee.proj.common.model.TbFields;
import com.laungee.proj.common.web.FileManager;

public class DonationXxglAction extends BaseAction {
	// 列表
	public String list() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 类型编号
		String code = request.getParameter("code");
		// 类型名称
		String name = request.getParameter("name");
		// 受惠对象
		String foundObject = request.getParameter("foundObject");
		// 构建查询
		String hql = "from TbDonationXxgl t where 1=1 ";
		List pars = this.getList();
		if (foundObject != null && !"".equals(foundObject)) {
			hql += " and t.foundObject=?";
			pars.add(foundObject);
			request.setAttribute("foundObject", foundObject);
		}
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
		hql += " order by t.xxId";
		List xxlist = this.getList();
		try {
			xxlist = this.getCommonBo().findHQLPage(hql, pars);
		} catch (Exception e) {
		}
		if (xxlist != null && !xxlist.isEmpty()) {
			request.setAttribute("xxlist", xxlist);
		}

		return "list";
	}

	// 新增
	public String add() throws Exception {
		return "add";
	}

	public String doAdd() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 类型编号
		String code = request.getParameter("code");
		// 类型名称
		String name = request.getParameter("name");
		// 受惠对象
		String foundObject = request.getParameter("foundObject");
		// 类型摘要
		String remark = request.getParameter("remark");
		// 类型说明
		String memo = request.getParameter("memo");
		TbDonationXxgl obj = new TbDonationXxgl();
		String msg = "";
		if (foundObject != null && !"".equals(foundObject)) {
			TbFields tmp = null;
			try {
				tmp = (TbFields) this.getCommonBo().get(TbFields.class,
						new Long(foundObject));
			} catch (Exception e) {
			}
			if (tmp != null) {
				obj.setFoundObject(tmp.getFieldId().toString());
			}
		}
		if (code != null && !"".equals(code)) {
			obj.setCode(code);
		} else {
			msg += "类型编号不能为空；";
		}
		if (name != null && !"".equals(name)) {
			obj.setName(name);
		} else {
			msg += "类型名称不能为空；";
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
		obj = (TbDonationXxgl) this.getCommonBo().save(obj);

		// 更新附件编号
		String relIds = this.getRequest().getParameter("relIds");
		FileManager.getInstance().update(obj.getXxId(), relIds);

		return SUCCESS;
	}

	// 编辑
	public String edit() throws Exception {
		HttpServletRequest request = this.getRequest();
		String id = request.getParameter("id");
		TbDonationXxgl obj = null;
		if (id != null && !"".equals(id)) {
			try {
				obj = (TbDonationXxgl) this.getCommonBo().get(
						TbDonationXxgl.class, new Long(id));
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
		// 类型ID
		String id = request.getParameter("id");
		TbDonationXxgl obj = null;
		if (id != null && !"".equals(id)) {
			try {
				obj = (TbDonationXxgl) this.getCommonBo().get(
						TbDonationXxgl.class, new Long(id));
			} catch (Exception e) {
			}
		}
		if (obj == null) {
			request.setAttribute("msg", "保存过程中出错！");
			request.setAttribute("id", id);
			return "add";
		}
		// 类型编号
		String code = request.getParameter("code");
		// 类型名称
		String name = request.getParameter("name");
		// 受惠对象
		String foundObject = request.getParameter("foundObject");
		// 类型摘要
		String remark = request.getParameter("remark");
		// 类型说明
		String memo = request.getParameter("memo");
		String msg = "";
		if (foundObject != null && !"".equals(foundObject)) {
			TbFields tmp = null;
			try {
				tmp = (TbFields) this.getCommonBo().get(TbFields.class,
						new Long(foundObject));
			} catch (Exception e) {
			}
			if (tmp != null) {
				obj.setFoundObject(tmp.getFieldId().toString());
			}
		}
		if (code != null && !"".equals(code)) {
			obj.setCode(code);
		} else {
			msg += "类型编号不能为空；";
		}
		if (name != null && !"".equals(name)) {
			obj.setName(name);
		} else {
			msg += "类型名称不能为空；";
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

	// 删除
	public String del() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 类型ID
		String id = request.getParameter("id");
		TbDonationXxgl obj = null;
		if (id != null && !"".equals(id)) {
			try {
				obj = (TbDonationXxgl) this.getCommonBo().get(
						TbDonationXxgl.class, new Long(id));
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
