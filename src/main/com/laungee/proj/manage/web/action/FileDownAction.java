package com.laungee.proj.manage.web.action;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbFile;
import com.laungee.proj.common.model.TbResOther;
import com.laungee.proj.common.model.TbResource;

public class FileDownAction extends BaseAction {
	// 处理请求
	public String execute() throws Exception {
		// 请求对象
		HttpServletRequest request = this.getRequest();
		HttpServletResponse response = this.getResponse();
		// 资源编号
		String id=request.getParameter("delsId");
		// 资源信息
		if(null==id||"".equals(id))
			return null;
		TbResource tbResource=(TbResource)getCommonBo().get(TbResource.class, new Long(id));
		// 资源源名称
		String path=tbResource.getResUrl();
		// 文件路径
		String root = ServletActionContext.getServletContext().getRealPath("/"+path);
		// 文件信息
		File file=new File(root);
		// 返回
		if(file.exists()){
			String tmpFileName=tbResource.getResName().replaceAll(" ", "");
			// 资源源名称
			String fileName = URLEncoder.encode(tmpFileName, "UTF-8"); 
			if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox")>0){
	            fileName = new String(tmpFileName.getBytes(), "ISO8859-1");
			}
			response.setContentType("application/x-msdownload;");//1
			response.setHeader("Content-Disposition", "attachment; filename=" +fileName);//2                      

			ServletOutputStream outStream = response.getOutputStream();
			BufferedOutputStream bos=new BufferedOutputStream(outStream);
			FileInputStream stream = new FileInputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while ((bytesRead = stream.read(buffer, 0, 1024)) != -1){
			   bos.write(buffer, 0, bytesRead);
			}
			bos.close();
			outStream.close();
			stream.close();
			return null;
		}
		else{
			request.setAttribute("alert","文件不存在，请您重新选择");
			return ERROR;
		}
	}
	
	public String downImp(){
		// 请求对象
		HttpServletRequest request = this.getRequest();
		HttpServletResponse response = this.getResponse();
		try{
			// 文件路径
			String path=request.getParameter("path");
			if(path!=null && !"".equals(path)){
				// 文件名
				String fileName=request.getParameter("name");
				if(fileName==null || "".equals(fileName)){
					fileName = path.substring(path.lastIndexOf("\\") + 1);
				}
				// 文件实际路径
				String root = ServletActionContext.getServletContext().getRealPath("/"+path);
				// 文件信息
				File file=new File(root);
				// 返回
				if(file.exists()){
					if(request.getHeader("User-Agent").toLowerCase().indexOf("firefox")>0){
						fileName = new String(fileName.getBytes(), "ISO8859-1");
					}else{
						fileName = URLEncoder.encode(fileName, "UTF-8"); 
					}
					response.setContentType("application/x-msdownload;");//1
					response.setHeader("Content-Disposition", "attachment; filename=" +fileName);//2                      
		
					ServletOutputStream outStream = response.getOutputStream();
					BufferedOutputStream bos=new BufferedOutputStream(outStream);
					FileInputStream stream = new FileInputStream(file);
					int bytesRead = 0;
					byte[] buffer = new byte[1024];
					while ((bytesRead = stream.read(buffer, 0, 1024)) != -1){
					   bos.write(buffer, 0, bytesRead);
					}
					bos.close();
					outStream.close();
					stream.close();
					return null;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("Msg", "文件不存在或者已被删除！");
		return ERROR;
	}
	public InputStream getTargetFile() throws Exception{
		HttpServletRequest request = this.getRequest();
		// 资源编号
		String id=request.getParameter("delsId");
		// 资源信息
		if(null==id||"".equals(id))
			return null;
		TbFile tbFile=(TbFile)getCommonBo().get(TbFile.class, new Long(id));
		// 资源原名称
		String fileName=tbFile.getFileName();
		// 资源源名称
		String path=tbFile.getUrlFile();
		// 上传地址
		InputStream bis=null;
		this.getResponse().setHeader("Content-Disposition", "attachment;filename="+ new String(fileName.getBytes(), "ISO-8859-1"));
		bis=ServletActionContext.getServletContext().getResourceAsStream("/"+path);
		// 返回
		return bis;
	}
}
