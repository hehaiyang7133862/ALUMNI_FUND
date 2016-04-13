package com.laungee.proj.manage.web.action;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbMenu;
import com.laungee.proj.common.model.TbUnAlumni;
import com.laungee.proj.common.util.IFinalMenu;

public class MenuReadAction extends BaseAction implements IFinalMenu {
	// �ϱ�
	public String top() throws Exception{
		HttpServletRequest request=getRequest();
		//����˵�
		String hql="from TbMenu a where numLevel=1 order by a.numOrder";
		List list=getCommonBo().findHQL(hql);
		request.setAttribute("list_menu",list);
		// ����
		return "top";
	}
	// ���
	public String left() throws Exception {
		HttpServletRequest request=getRequest();
		String id=request.getParameter("id");
		String type=request.getSession().getAttribute(USER_TYPE).toString();
		if ("1".equalsIgnoreCase(type)){
			Long userid=(Long) this.getRequest().getSession().getAttribute(USER_ID);
			if(null==id){
				String menuHql="select  a.menu_id from tb_menu_role a left outer join tb_menu c on c.menu_id=a.menu_id " +
				"where exists (select 1 from tb_role_user b where b.role_id=a.role_id"+
		       " and b.user_id="+userid+") and c.num_level=1 order by c.num_Order";
				List list=getCommonBo().findSQL(menuHql);
				if(list!=null&&!list.isEmpty()){
					id=list.get(0).toString();
				}
			}
		}else if("2".equalsIgnoreCase(type)){
			// �û����
			String userId=request.getSession().getAttribute(STU_ID).toString();
			// �û���Ϣ
			TbUnAlumni tbUnAlumni=(TbUnAlumni)getCommonBo().get(TbUnAlumni.class, new Long(userId));
			String code="";
			// [ALUMNI]:1.ͨ��;2.�ٴ����;
			if("1".equals(tbUnAlumni.getStateCid())||"2".equals(tbUnAlumni.getStateCid())){
				code="ALUMNI";
			}
			// [CHECK]:3.�����;4.����
			if("3".equals(tbUnAlumni.getStateCid())||"4".equals(tbUnAlumni.getStateCid())){
				code="CHECK";
			}
			if(null==id){
			String menuHql="select a.tbMenu.menuId from TbMenuRole a where a.tbMenu.numLevel=1 and a.tbRole.code='"+code+"' order by a.tbMenu.numOrder";
		    List list=this.getCommonBo().findHQL(menuHql);
		    if(list!=null&&!list.isEmpty()){
		    	id=list.get(0).toString();
		    }
			}
		}
		
		request.setAttribute("obj_id", id);
		// �ڵ�
		String node="from TbMenu a where numLevel=2 order by a.numOrder";
		List nodeList=getCommonBo().findHQL(node);
		request.setAttribute("list_node", nodeList);
		// �˵�
		String leaf="from TbMenu a where numLevel=3 order by a.numOrder";
		List leafList=getCommonBo().findHQL(leaf);
		request.setAttribute("list_leaf", leafList);
		// ����
		return "left";
	}
}
