package com.laungee.proj.manage.web.action;

import java.io.File;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbFile;
import com.laungee.proj.common.model.TbFileTab;

public class FileListAction extends BaseAction {
	// 处理请求
	public String execute() throws Exception {
		HttpServletRequest request=this.getRequest();
		// 编号
		String id=request.getParameter("id");
		String fd=request.getParameter("fd");
		if(null==id || "".equals(id)){
			if(null==fd || "".equals(fd)){
				fd="0";
			}
			String seq="select S_TB_FILE_TEMP.nextval from dual";
			id=getCommonBo().getSQLUnique(seq).toString();
		}
		else{
			if(null==fd || "".equals(fd)){
				fd="1";
			}
		}
		// 查询文件
		StringBuffer hql=new StringBuffer();
		hql.append("from TbFileTab a where a.tableName=? and a.tableId=? and a.typeCid=?");
		List pars=getList();
		// 表名
		String tab=request.getParameter("tab");
		if(null==tab){
			tab=request.getParameter("name");
		}
		request.setAttribute("tab", tab);
		pars.add(tab);
		// 编号
		request.setAttribute("id", id);
		if(null!=id&&!"".equals(id)){
			pars.add(new Long(id));
		}
		// 类型
		request.setAttribute("fd", fd);
		pars.add(fd);
		// 查询
		List list=getCommonBo().findHQL(hql.toString(),pars);
		request.setAttribute("list_file", list);
		// 返回
		return SUCCESS;
	}
	
	public String ReadOnly() throws Exception {
		HttpServletRequest request=this.getRequest();
		// 编号
		String id=request.getParameter("id");
		String fd=request.getParameter("fd");
		if(null==id || "".equals(id)){
			if(null==fd || "".equals(fd)){
				fd="0";
			}
			String seq="select S_TB_FILE_TEMP.nextval from dual";
			id=getCommonBo().getSQLUnique(seq).toString();
		}
		else{
			if(null==fd || "".equals(fd)){
				fd="1";
			}
		}
		// 查询文件
		StringBuffer hql=new StringBuffer();
		hql.append("from TbFileTab a where a.tableName=? and a.tableId=? and a.typeCid=?");
		List pars=getList();
		// 表名
		String tab=request.getParameter("tab");
		if(null==tab){
			tab=request.getParameter("name");
		}
		request.setAttribute("tab", tab);
		pars.add(tab);
		// 编号
		request.setAttribute("id", id);
		if(null!=id&&!"".equals(id)){
			pars.add(new Long(id));
		}
		// 类型
		request.setAttribute("fd", fd);
		pars.add(fd);
		// 查询
		List list=getCommonBo().findHQL(hql.toString(),pars);
		request.setAttribute("list_file", list);
		// 返回
		return SUCCESS;
	}
	
	// 处理请求
	public String del() throws Exception {
		try {
			HttpServletRequest request=this.getRequest();
			// 关系编号
			String delsId = request.getParameter("delsId");
			// 关系表
			if(null==delsId||"".equals(delsId))
				return null;
			TbFileTab tbFileTab = (TbFileTab) getCommonBo().get(TbFileTab.class, new Long(delsId));
			Long fileId= tbFileTab.getTbFile().getFileId();
			getCommonBo().delete(tbFileTab);
			// 文件
			TbFile tbFile=(TbFile)getCommonBo().get(TbFile.class, fileId);
			// 资源
			String root = ServletActionContext.getServletContext().getRealPath("/");
			String path = tbFile.getUrlFile();
			File file=new File(root+path);
			file.delete();
			getCommonBo().delete(tbFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 返回
		return execute();
	}

}
