package com.laungee.proj.manage.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbTemplate;

public class TemplateListAction extends BaseAction {
	//得到全部模版
	public String templateList() throws Exception{
		HttpServletRequest request=this.getRequest();
		//得到参数
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
	 * 跳转到添加模版页面
	 * @return
	 */
	public String toAdd(){
		return SUCCESS;
	}
	
	/**
	 * 添加模版
	 * @return
	 * @throws Exception 
	 */
	public String doAdd() throws Exception{
		HttpServletRequest request=this.getRequest();
		//得到参数
		//模版名称
		String tempName=request.getParameter("tempName");
		//生日祝福主题
		String tempTitle=request.getParameter("tempTitle");
		//是否作为发送模版
		String flag=request.getParameter("isFlag");
		//内容
		String tempContent=request.getParameter("tempContent");
		TbTemplate tt=new TbTemplate();
		if(null!=flag&&!"".equals(flag)){
			tt.setFlag(new Long(1));
			//更新其它模版
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
		//保存
		this.getCommonBo().save(tt);
		
		return SUCCESS;
	}
	
	//跳转到编辑页面
	public String toEdit() throws NumberFormatException, Exception{
		HttpServletRequest request=this.getRequest();
		//得到id
		String id=request.getParameter("id");
		if(id!=null&&!"".equals(id)){
			//得到模版对象
			TbTemplate tbTemp=(TbTemplate)this.getCommonBo().get(TbTemplate.class, new Long(id));
			if(tbTemp!=null){
				request.setAttribute("tbTemp", tbTemp);
			}
		}
		request.setAttribute("id", id);
		return SUCCESS;
	}
	
	/**
	 * 修改模版
	 * @return
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	public String doEdit() throws NumberFormatException, Exception{
		HttpServletRequest request=this.getRequest();
		//得到id
		String id=request.getParameter("id");
		//模版名称
		String tempName=request.getParameter("tempName");
		//生日祝福主题
		String tempTitle=request.getParameter("tempTitle");
		//是否作为发送模版
		String flag=request.getParameter("isFlag");
		//内容
		String tempContent=request.getParameter("tempContent");
		if(id!=null&&!"".equals(id)){
			//得到模版对象
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
	 * 修改模版状态
	 * @return
	 */
	public String modify(){
		HttpServletRequest request=this.getRequest();
		//得到id
		String id=request.getParameter("id");
		if(id!=null&&!"".equals(id)){
			//更新其它模版
			String sql="update tb_template a set a.flag=0";
			this.getCommonBo().execute(sql);
			//更新选中模版
			sql="update tb_template a set a.flag=1 where a.temp_id="+id;
			this.getCommonBo().execute(sql);
		}else{
			request.setAttribute("alert", "模版对象不存在！");
		}
		return SUCCESS;
	}
	
	/**
	 * 删除
	 * @return
	 */
	public String del(){
		HttpServletRequest request=this.getRequest();
		//得到id
		String id=request.getParameter("id");
		if(id!=null&&!"".equals(id)){
			String sql="delete from tb_template a where a.temp_id="+id;
			this.getCommonBo().execute(sql);
		}else{
			request.setAttribute("alert", "模版对象不存在！");
		}
		return SUCCESS;
	}
}
