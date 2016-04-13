package com.laungee.proj.manage.web.action;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbLog;
import com.laungee.proj.common.model.TbUnAlumni;
import com.laungee.proj.common.model.TbUser;

public class LogListAction extends BaseAction {
	// ��תҳ
	public String dolistlog() throws Exception{
		HttpServletRequest request=this.getRequest();
		String logindate1 = request.getParameter("logindate1");
		String logindate2 = request.getParameter("logindate2");
		String name = request.getParameter("name1");
		String landip = request.getParameter("landip1");
		String handType=request.getParameter("handType");
		//�����ת��ɾ����־ҳ��
		String tag=request.getParameter("tag");
		//
		String delTime=request.getParameter("deleteLog");
		if(null==delTime||"".equals(delTime)){
			if(request.getAttribute("delTime")!=null){
				delTime=(String) request.getAttribute("delTime");
			}
		}
		request.setAttribute("delTime", delTime);
		List pars = getList();
		StringBuffer hql = new StringBuffer("select a from TbLog a where a.loginName!='У��ϵͳ����Ա' and 1=1 ");
		//�õ�xyadmin�ʺ�id
		String xyhql="from TbUser a where a.userCard='xyadmin'";
		TbUser user=(TbUser)this.getCommonBo().getHQLUnique(xyhql);
		if(user!=null){
			hql.append("and a.userId!="+user.getUserId());
		}
		
		//��ѯ����
		if(logindate1 != null && !logindate1.equals("") &&  logindate2 != null && !logindate2.equals("")){
			hql.append("and a.loginDate >= TO_DATE(?,'YYYY-MM-DD') ");
			pars.add(logindate1);
			hql.append("and a.loginDate <= TO_DATE(?,'YYYY-MM-DD')+1 ");
			pars.add(logindate2);
		}
		//��ѯ����
		if(name != null && !name.equals("")){
			hql.append("and a.loginName like ? ");
			pars.add("%" + name + "%");
		}
		
		//��ѯip
		if(landip != null  && !landip.equals("")){
			hql.append("and a.loginIp like ? ");
			pars.add("%" + landip + "%");
		}
		
		//��ѯ����
		if(null!=handType&&!handType.equals("")){
			String handTypeStr="";
			if(handType.equals("1")){
				handTypeStr="��½";
			}else if(handType.equals("2")){
				handTypeStr="����";
			}else if(handType.equals("3")){
				handTypeStr="����";
			}else if(handType.equals("4")){
				handTypeStr="ɾ��";
			}
			hql.append("and a.handType like ?");
			pars.add("%"+handTypeStr+"%");
		}
		//��ѯɾ����־ʱ��
		if(null!=delTime&&!"".equals(delTime)){
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			int date=Integer.valueOf(delTime);
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, -date);
			String saveDate = format.format(calendar.getTime());
			hql.append("and a.updateTime <= to_date(?,'yyyy-MM-dd')");
			pars.add(saveDate);
		}
		hql.append("order by a.updateTime desc ");
		List list=getCommonBo().findHQLPage(hql.toString(),pars);
		//��ѯ��־��������
		String logHql="select a.fieldValue from TbField a where a.fieldId=10042";
		Object obj=this.getCommonBo().getHQLUnique(logHql);
		request.setAttribute("list_log", list);
		request.setAttribute("logindate1", logindate1);
		request.setAttribute("logindate2", logindate2);
		request.setAttribute("name", name);
		request.setAttribute("landip", landip);
		request.setAttribute("handType", handType);
		request.setAttribute("logSaveDate", obj);
		if(tag!=null&&!tag.equals("")){
			return "delete";
		}
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String deleteLog(){
		HttpServletRequest request=this.getRequest();
		//�õ���־ID
		String[] loginIds=request.getParameterValues("loginId");
		String delTime=request.getParameter("deleteLog");
		request.setAttribute("delTime", delTime);
		if(null!=loginIds&&loginIds.length!=0){
			try{
				List<TbLog> list=this.getList();
				for (int i = 0; i < loginIds.length; i++) {
					String loginId=loginIds[i];
					if(loginId==null||loginId=="")
						continue;
					//�õ���־����
					TbLog log=(TbLog) this.getCommonBo().get(TbLog.class, new Long(loginId));
					list.add(log);
				}
				this.getCommonBo().deleteAll(list);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}

}
