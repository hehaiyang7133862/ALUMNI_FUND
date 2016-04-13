package com.laungee.proj.api.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbZcprojType;

public class ZcTypeListAction extends BaseAction {
	// 众筹项目分类列表
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 父节点ID
		Long parentIdL = null;
		String parentId = request.getParameter("pid");
		if(parentId!=null && !"".equals(parentId) && !"1".equals(parentId)){
			try{
				parentIdL = new Long(parentId);
			}catch(Exception e){}
			if(parentIdL==null){
				// 参数错误
				request.setAttribute("result", "error");
				// 返回
				return ERROR;
			}
			TbZcprojType beanParent = (TbZcprojType)this.getCommonBo().get(TbZcprojType.class, parentIdL);
			if(beanParent==null){
				// 参数错误
				request.setAttribute("result", "error");
				// 返回
				return ERROR;
			}
		}else{
			parentIdL = new Long(1);
		}
		// 加载标识（all、加载全部树形结构）
		String flag = request.getParameter("flag");
		if(flag==null || !"all".equals(flag)){
			flag = "single";
		}
		request.setAttribute("flag", flag);
		// 构造查询
		String hql = "from TbZcprojType a where a.parentId="+parentIdL+" order by a.numOrder";
		// 执行查询
		List beanList = this.getCommonBo().findHQL(hql);
		request.setAttribute("beanList", beanList);
		// 返回
		return SUCCESS;
	}
}