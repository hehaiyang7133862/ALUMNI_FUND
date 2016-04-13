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
	// ��������
	public String execute() throws Exception {
		// �������
		HttpServletRequest request = ServletActionContext.getRequest();
		// ��Դ���
		String id=request.getParameter("delsId");
		// ��Դ��Ϣ
		if(null==id||"".equals(id))
			return null;
		TbResource tbResource=(TbResource)getCommonBo().get(TbResource.class, new Long(id));
		// ��ԴԴ����
		String realName=tbResource.getResUrl();
		// �ļ�·��
		String filePath = ServletActionContext.getServletContext().getRealPath("upload//" + realName);
		// �ļ���Ϣ
		File file=new File(filePath);
		// ����
		if(file.exists()){
			return SUCCESS;
		}
		else{
			request.setAttribute("alert","�ļ������ڣ���������ѡ��");
			return ERROR;
		}
	}
	public InputStream getTargetFile() throws Exception{
		// �������
		HttpServletRequest request = ServletActionContext.getRequest();
		// ��Դ���
		String id=request.getParameter("delsId");
		// ��Դ��Ϣ
		TbResource tbResource=(TbResource)getCommonBo().get(TbResource.class, new Long(id));
		// ��Դԭ����
		String fileName=tbResource.getResName();
		// ��ԴԴ����
		String realName=tbResource.getResUrl();
		// �ϴ���ַ
		InputStream bis=null;
		try{
			// �ļ�·��
			String filePath = "upload//" + realName;
			this.getResponse().setHeader("Content-Disposition", "attachment;filename="+ new String(fileName.getBytes(), "ISO-8859-1"));
			bis=ServletActionContext.getServletContext().getResourceAsStream(filePath);
		}
		catch (Exception e) {
			e.printStackTrace();
			bis=new ByteArrayInputStream("����ʧ�ܣ�������������".getBytes());
		}
		finally{
			try {
				bis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// ����
		return bis;
	}
}
