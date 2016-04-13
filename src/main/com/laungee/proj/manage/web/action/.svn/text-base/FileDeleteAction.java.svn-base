package com.laungee.proj.manage.web.action;

import java.io.File;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbResOther;
import com.laungee.proj.common.model.TbResource;

public class FileDeleteAction extends BaseAction {
	// 处理请求
	public String execute() throws Exception {
		try {
			// 请求对象
			HttpServletRequest request=ServletActionContext.getRequest();
			// 关系编号
			String delsId = request.getParameter("delsId");
			// 删除关系表
			if(null==delsId||"".equals(delsId))
				return null;
			TbResOther tbResOther = (TbResOther) getCommonBo().get(
					TbResOther.class, new Long(delsId));
			Long resId= tbResOther.getTbResource().getResId();
			getCommonBo().delete(tbResOther);
			// 删除资源表
			TbResource tbResource=(TbResource)getCommonBo().get(TbResource.class, resId);
			String realName=tbResource.getResUrl();
			String filePath = "upload//" + realName;
			String path = ServletActionContext.getServletContext().getRealPath(filePath);
			System.out.println(path);
			File file=new File(path);
			file.delete();
			getCommonBo().delete(tbResource);
			// 资源编号集
			String ids=request.getParameter("ids");
			String s1=ids.replaceAll(","+tbResOther.getClass()+",", ",");
			String s2=(" "+s1).replaceAll(" "+tbResOther.getClass()+",", ",");
			request.setAttribute("ids", s2.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 返回
		return SUCCESS;
	}
}