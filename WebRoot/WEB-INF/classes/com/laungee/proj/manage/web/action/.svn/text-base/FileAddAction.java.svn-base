package com.laungee.proj.manage.web.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
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
import com.laungee.proj.common.model.TbField;
import com.laungee.proj.common.model.TbResOther;
import com.laungee.proj.common.model.TbResource;

public class FileAddAction extends BaseAction {
	// 文件域
	private File upload;
	// 文件名
	private String uploadFileName;
	// 文件类型
	private String uploadContentType;

	// 处理请求
	public String execute() throws Exception {
		// 请求对象
		HttpServletRequest request = ServletActionContext.getRequest();
		if(null==upload || !upload.exists()){
			request.setAttribute("alert", "请选择上传文件");
			return SUCCESS;
		}
		InputStream bis=null;
		OutputStream out=null;
		try {
			// 对象名称
			String name = request.getParameter("name");
			// 对象编号
			String id = request.getParameter("id");
			// 附件信息
			TbResource tbResource = new TbResource();
			int nIndex=uploadFileName.lastIndexOf(".");
			if(nIndex==-1){
				nIndex=uploadFileName.length()-1;
			}
			String resType=uploadFileName.substring(nIndex+1);
			tbResource.setResName(uploadFileName);
			tbResource.setResType(resType);
			// 上传地址
			long length=0;
			// 资源集合yyMMddHHmmss
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
			String realName=sdf.format(this.getCommonBo().getSysDate());
			// 上传
			String loadPath = ServletActionContext.getServletContext().getRealPath("upload//" + realName);
			File loadFile = new File(loadPath);
			bis= new BufferedInputStream(new FileInputStream(upload));
			out  = new BufferedOutputStream(new FileOutputStream(loadFile));
			byte[] bytes = new byte[1];
			//将文件内容写入位数组流
			while(bis.read(bytes)!=-1)
			{
				length++;
				out.write(bytes);
			}
			tbResource.setResSize(new Long(length));
			tbResource.setResUrl(realName);
			// 保存附件信息
			TbResource obj=(TbResource)getCommonBo().save(tbResource);
			// 附件映射
			TbResOther tbResOther = new TbResOther();
			System.out.println("obj=========="+obj.getResId());
			tbResOther.setTbResource(obj);
			
			tbResOther.setTableName(name);
			if (null != id && !id.equals("")) {
				tbResOther.setTableId(new Long(id));
			}
			// 保存附件映射
			TbResOther objOther=(TbResOther) getCommonBo().save(tbResOther);
			System.out.println("-=-=-=-="+objOther.getTbResource().getResId());
			// 资源集合
			String ids = request.getParameter("ids");
			if (null == ids) {
				ids = "";
			}
			ids += tbResOther.getRelId() + ",";
			request.setAttribute("ids", ids);
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", "上传文件失败，请您重新上传");
			// 返回
			return SUCCESS;
		}
		finally{
			try {
				bis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 返回
		return SUCCESS;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
}