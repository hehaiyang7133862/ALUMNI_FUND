package com.laungee.proj.manage.web.action;

import java.io.File;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbFile;
import com.laungee.proj.common.model.TbFileTab;

public class FileListAction extends BaseAction {
	// ��������
	public String execute() throws Exception {
		HttpServletRequest request=this.getRequest();
		// ���
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
		// ��ѯ�ļ�
		StringBuffer hql=new StringBuffer();
		hql.append("from TbFileTab a where a.tableName=? and a.tableId=? and a.typeCid=?");
		List pars=getList();
		// ����
		String tab=request.getParameter("tab");
		if(null==tab){
			tab=request.getParameter("name");
		}
		request.setAttribute("tab", tab);
		pars.add(tab);
		// ���
		request.setAttribute("id", id);
		if(null!=id&&!"".equals(id)){
			pars.add(new Long(id));
		}
		// ����
		request.setAttribute("fd", fd);
		pars.add(fd);
		// ��ѯ
		List list=getCommonBo().findHQL(hql.toString(),pars);
		request.setAttribute("list_file", list);
		// ����
		return SUCCESS;
	}
	
	public String ReadOnly() throws Exception {
		HttpServletRequest request=this.getRequest();
		// ���
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
		// ��ѯ�ļ�
		StringBuffer hql=new StringBuffer();
		hql.append("from TbFileTab a where a.tableName=? and a.tableId=? and a.typeCid=?");
		List pars=getList();
		// ����
		String tab=request.getParameter("tab");
		if(null==tab){
			tab=request.getParameter("name");
		}
		request.setAttribute("tab", tab);
		pars.add(tab);
		// ���
		request.setAttribute("id", id);
		if(null!=id&&!"".equals(id)){
			pars.add(new Long(id));
		}
		// ����
		request.setAttribute("fd", fd);
		pars.add(fd);
		// ��ѯ
		List list=getCommonBo().findHQL(hql.toString(),pars);
		request.setAttribute("list_file", list);
		// ����
		return SUCCESS;
	}
	
	// ��������
	public String del() throws Exception {
		try {
			HttpServletRequest request=this.getRequest();
			// ��ϵ���
			String delsId = request.getParameter("delsId");
			// ��ϵ��
			if(null==delsId||"".equals(delsId))
				return null;
			TbFileTab tbFileTab = (TbFileTab) getCommonBo().get(TbFileTab.class, new Long(delsId));
			Long fileId= tbFileTab.getTbFile().getFileId();
			getCommonBo().delete(tbFileTab);
			// �ļ�
			TbFile tbFile=(TbFile)getCommonBo().get(TbFile.class, fileId);
			// ��Դ
			String root = ServletActionContext.getServletContext().getRealPath("/");
			String path = tbFile.getUrlFile();
			File file=new File(root+path);
			file.delete();
			getCommonBo().delete(tbFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ����
		return execute();
	}

}
