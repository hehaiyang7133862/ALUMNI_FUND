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

	// 处理请求
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		MultiRequestUtil multiRequest = new MultiRequestUtil(request);
		FileItem item = multiRequest.getFile("upload");
		if (null == item) {
			request.setAttribute("alert", "请选择上传文件");
			return SUCCESS;
		}
		long length = item.getSize();
		InputStream bis = null;
		OutputStream out = null;
		try {
			/* 上传文件 ------------------------------------------- */
			String uploadPath = "upload/file/";
			// 文件保存目录路径
			String savePath = request.getSession().getServletContext()
					.getRealPath("/" + uploadPath)
					+ "/";
			// 检查目录
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
			// 附件信息
			TbFile tbFile = new TbFile();
			TbResource tbResource = new TbResource();
			// 文件名称
			int mIndex = item.getName().lastIndexOf("\\");
			String resName = item.getName().substring(mIndex + 1);
			// 文件名称
			tbFile.setFileName(resName);
			// 文件类型
			int nIndex = item.getName().lastIndexOf(".");
			if (nIndex != -1) {
				String fileType = item.getName().substring(nIndex + 1);
				tbFile.setFileType(fileType.toLowerCase());
			}
			// 文件路径
			String fileName = DateUtil.format(dateNow, "yyyyMMddHHmmss");
			savePath += fileName;
			tbFile.setUrlFile(uploadPath + fileName);
			/* 操作流 */
			// 输入流
			bis = new BufferedInputStream(item.getInputStream());
			// 输出流
			File file = new File(savePath);
			out = new BufferedOutputStream(new FileOutputStream(file));
			byte[] bytes = new byte[1];
			while (bis.read(bytes) != -1) {
				out.write(bytes);
			}
			tbFile.setFileSize(new Long(length));
			// 保存
			TbFile obj=(TbFile)getCommonBo().save(tbFile);
			/* 映射关系 ------------------------------------------- */
			// 附件映射
			TbFileTab tbFileTab = new TbFileTab();
			// 附件
			tbFileTab.setTbFile(obj);
			// 表名
			String tab = multiRequest.getParameter("tab");
			tbFileTab.setTableName(tab);
			request.setAttribute("tab", tab);
		
			// 编号
			String id =multiRequest.getParameter("id");
			if(null!=id&&!"".equals(id)){
				tbFileTab.setTableId(new Long(id));
				request.setAttribute("id", id);
			}
			// 类型
			String fd = multiRequest.getParameter("fd");
			tbFileTab.setTypeCid(fd);
			request.setAttribute("fd", fd);
			// 保存
			getCommonBo().save(tbFileTab);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", "上传文件失败，请您重新上传");
			// 返回
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
		// 返回
		return SUCCESS;
	}

}