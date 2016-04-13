package com.laungee.proj.manage.web.action;

import java.util.List;

import com.laungee.proj.common.core.BaseManager;
import com.laungee.proj.common.model.TbAcademy;

public class AcademyManager extends BaseManager {
	private AcademyManager(){
	}
	public static AcademyManager getInstance(){
		return new AcademyManager();
	}
	public List getList(String id, String level) throws Exception {
		if ("1".equals(level)) {
			// 年份判断
			TbAcademy bean = (TbAcademy) getCommonBo().getHQLUnique(
					"from TbAcademy a where rownum=1 and a.nodeName='" + id
							+ "'");
			if (null != bean) {
				id = bean.getNodeId().toString();
			} else {
				id = "";
			}
		}
		List list = null;
		if (id.matches("[0-9]+")) {
			// 查询子级
			String hql = "from TbAcademy a where a.parentId=" + id
					+ " and numLevel='" + level + "'";
			list = getCommonBo().findHQL(hql);
		}
		return list;
	}
}
