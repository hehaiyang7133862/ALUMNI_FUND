package com.laungee.proj.manage.web.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbResource;

public class FileByIdAction extends BaseAction {
	// 处理请求
	public String execute() throws Exception {
		// 请求对象
		HttpServletRequest request = ServletActionContext.getRequest();
		// 资源编号
		String id=request.getParameter("delsId");
		// 资源信息
		if(null==id||"".equals(id))
			return null;
		TbResource tbResource=(TbResource)getCommonBo().get(TbResource.class, new Long(id));
		// 资源源名称
		String realName=tbResource.getResUrl();
		// 文件路径
		String filePath = ServletActionContext.getServletContext().getRealPath("upload//" + realName);
		// 文件信息
		File file=new File(filePath);
		// 返回
		if(file.exists()){
			return SUCCESS;
		}
		else{
			request.setAttribute("alert","文件不存在，请您重新选择");
			return ERROR;
		}
	}
	public InputStream getTargetFile() throws Exception{
		// 请求对象
		HttpServletRequest request = ServletActionContext.getRequest();
		// 资源编号
		String id=request.getParameter("delsId");
		// 资源信息
		TbResource tbResource=(TbResource)getCommonBo().get(TbResource.class, new Long(id));
		// 资源原名称
		String fileName=tbResource.getResName();
		// 资源源名称
		String realName=tbResource.getResUrl();
		// 上传地址
		InputStream bis=null;
		try{
			// 文件路径
			String filePath = "upload//" + realName;
			this.getResponse().setHeader("Content-Disposition", "attachment;filename="+ new String(fileName.getBytes(), "ISO-8859-1"));
			bis=ServletActionContext.getServletContext().getResourceAsStream(filePath);
		}
		catch (Exception e) {
			e.printStackTrace();
			bis=new ByteArrayInputStream("下载失败，请您重新下载".getBytes());
		}
		finally{
			try {
				bis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 返回
		return bis;
	}
}
