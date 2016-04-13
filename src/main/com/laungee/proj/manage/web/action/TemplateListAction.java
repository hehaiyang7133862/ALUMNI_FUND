package com.laungee.proj.manage.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbTemplate;

public class TemplateListAction extends BaseAction {
	//�õ�ȫ��ģ��
	public String templateList() throws Exception{
		HttpServletRequest request=this.getRequest();
		//�õ�����
		String tempName=request.getParameter("tempName");
		String hql="from TbTemplate a ";
		if(null!=tempName&&!"".equals(tempName)){
			hql+="where a.memo like '%"+tempName+"%'";
		}
		hql+=" order by flag desc nulls last";
		List list=this.getCommonBo().findHQLPage(hql);
		request.setAttribute("list", list);
		request.setAttribute("tempName", tempName);
		return SUCCESS;
	}
	
	/**
	 * ��ת�����ģ��ҳ��
	 * @return
	 */
	public String toAdd(){
		return SUCCESS;
	}
	
	/**
	 * ���ģ��
	 * @return
	 * @throws Exception 
	 */
	public String doAdd() throws Exception{
		HttpServletRequest request=this.getRequest();
		//�õ�����
		//ģ������
		String tempName=request.getParameter("tempName");
		//����ף������
		String tempTitle=request.getParameter("tempTitle");
		//�Ƿ���Ϊ����ģ��
		String flag=request.getParameter("isFlag");
		//����
		String tempContent=request.getParameter("tempContent");
		TbTemplate tt=new TbTemplate();
		if(null!=flag&&!"".equals(flag)){
			tt.setFlag(new Long(1));
			//��������ģ��
			String sql="update tb_template a set a.flag=0";
			this.getCommonBo().execute(sql);
		}
		if(null!=tempTitle&&!"".equals(tempTitle)){
			tt.setTempTitle(tempTitle);
		}
		if(null!=tempName&&!"".equals(tempName)){
			tt.setMemo(tempName);
		}
		if(null!=tempContent&&!"".equals(tempContent)){
			tt.setTempContent(tempContent);
		}
		//����
		this.getCommonBo().save(tt);
		
		return SUCCESS;
	}
	
	//��ת���༭ҳ��
	public String toEdit() throws NumberFormatException, Exception{
		HttpServletRequest request=this.getRequest();
		//�õ�id
		String id=request.getParameter("id");
		if(id!=null&&!"".equals(id)){
			//�õ�ģ�����
			TbTemplate tbTemp=(TbTemplate)this.getCommonBo().get(TbTemplate.class, new Long(id));
			if(tbTemp!=null){
				request.setAttribute("tbTemp", tbTemp);
			}
		}
		request.setAttribute("id", id);
		return SUCCESS;
	}
	
	/**
	 * �޸�ģ��
	 * @return
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	public String doEdit() throws NumberFormatException, Exception{
		HttpServletRequest request=this.getRequest();
		//�õ�id
		String id=request.getParameter("id");
		//ģ������
		String tempName=request.getParameter("tempName");
		//����ף������
		String tempTitle=request.getParameter("tempTitle");
		//�Ƿ���Ϊ����ģ��
		String flag=request.getParameter("isFlag");
		//����
		String tempContent=request.getParameter("tempContent");
		if(id!=null&&!"".equals(id)){
			//�õ�ģ�����
			TbTemplate tbTemp=(TbTemplate)this.getCommonBo().get(TbTemplate.class, new Long(id));
			if(tbTemp!=null){
				if(null!=flag&&!"".equals(flag)){
					tbTemp.setFlag(new Long(1));
				}else
					tbTemp.setFlag(new Long(0));
				tbTemp.setMemo(tempName);
				tbTemp.setTempContent(tempContent);
				tbTemp.setTempTitle(tempTitle);
			}
		}
		return SUCCESS;
	}
	
	/**
	 * �޸�ģ��״̬
	 * @return
	 */
	public String modify(){
		HttpServletRequest request=this.getRequest();
		//�õ�id
		String id=request.getParameter("id");
		if(id!=null&&!"".equals(id)){
			//��������ģ��
			String sql="update tb_template a set a.flag=0";
			this.getCommonBo().execute(sql);
			//����ѡ��ģ��
			sql="update tb_template a set a.flag=1 where a.temp_id="+id;
			this.getCommonBo().execute(sql);
		}else{
			request.setAttribute("alert", "ģ����󲻴��ڣ�");
		}
		return SUCCESS;
	}
	
	/**
	 * ɾ��
	 * @return
	 */
	public String del(){
		HttpServletRequest request=this.getRequest();
		//�õ�id
		String id=request.getParameter("id");
		if(id!=null&&!"".equals(id)){
			String sql="delete from tb_template a where a.temp_id="+id;
			this.getCommonBo().execute(sql);
		}else{
			request.setAttribute("alert", "ģ����󲻴��ڣ�");
		}
		return SUCCESS;
	}
}
