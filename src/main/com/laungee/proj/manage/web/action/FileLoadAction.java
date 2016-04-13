package com.laungee.proj.manage.web.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbFile;
import com.laungee.proj.common.model.TbFileTab;
import com.laungee.proj.common.model.TbResource;
import com.laungee.proj.common.util.DateUtil;
import com.laungee.proj.common.util.MultiRequestUtil;

public class FileLoadAction extends BaseAction {

	// ��������
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		MultiRequestUtil multiRequest = new MultiRequestUtil(request);
		FileItem item = multiRequest.getFile("upload");
		if (null == item) {
			request.setAttribute("alert", "��ѡ���ϴ��ļ�");
			return SUCCESS;
		}
		long length = item.getSize();
		InputStream bis = null;
		OutputStream out = null;
		try {
			/* �ϴ��ļ� ------------------------------------------- */
			String uploadPath = "upload/file/";
			// �ļ�����Ŀ¼·��
			String savePath = request.getSession().getServletContext()
					.getRealPath("/" + uploadPath)
					+ "/";
			// ���Ŀ¼
			File uploadDir = new File(savePath);
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}
			Date dateNow = this.getCommonBo().getSysDate();
			String ymd = DateUtil.format(dateNow, "yyyyMMdd");
			savePath += ymd + "/";
			uploadPath += ymd + "/";
			File dirFile = new File(savePath);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}
			// ������Ϣ
			TbFile tbFile = new TbFile();
			TbResource tbResource = new TbResource();
			// �ļ�����
			int mIndex = item.getName().lastIndexOf("\\");
			String resName = item.getName().substring(mIndex + 1);
			// �ļ�����
			tbFile.setFileName(resName);
			// �ļ�����
			int nIndex = item.getName().lastIndexOf(".");
			if (nIndex != -1) {
				String fileType = item.getName().substring(nIndex + 1);
				tbFile.setFileType(fileType.toLowerCase());
			}
			// �ļ�·��
			String fileName = DateUtil.format(dateNow, "yyyyMMddHHmmss");
			savePath += fileName;
			tbFile.setUrlFile(uploadPath + fileName);
			/* ������ */
			// ������
			bis = new BufferedInputStream(item.getInputStream());
			// �����
			File file = new File(savePath);
			out = new BufferedOutputStream(new FileOutputStream(file));
			byte[] bytes = new byte[1];
			while (bis.read(bytes) != -1) {
				out.write(bytes);
			}
			tbFile.setFileSize(new Long(length));
			// ����
			TbFile obj=(TbFile)getCommonBo().save(tbFile);
			/* ӳ���ϵ ------------------------------------------- */
			// ����ӳ��
			TbFileTab tbFileTab = new TbFileTab();
			// ����
			tbFileTab.setTbFile(obj);
			// ����
			String tab = multiRequest.getParameter("tab");
			tbFileTab.setTableName(tab);
			request.setAttribute("tab", tab);
		
			// ���
			String id =multiRequest.getParameter("id");
			if(null!=id&&!"".equals(id)){
				tbFileTab.setTableId(new Long(id));
				request.setAttribute("id", id);
			}
			// ����
			String fd = multiRequest.getParameter("fd");
			tbFileTab.setTypeCid(fd);
			request.setAttribute("fd", fd);
			// ����
			getCommonBo().save(tbFileTab);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", "�ϴ��ļ�ʧ�ܣ����������ϴ�");
			// ����
			return SUCCESS;
		} finally {
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
		// ����
		return SUCCESS;
	}

}