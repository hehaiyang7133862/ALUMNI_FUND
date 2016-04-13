package com.laungee.proj.zc.web.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbSiteContent;
import com.laungee.proj.common.model.TbSiteItem;
import com.laungee.proj.common.util.DateUtil;
import com.laungee.proj.common.util.MultiRequestUtil;

public class SiteItemCntAction extends BaseAction {
	// �ڳ�����Ŀ���ݹ���
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ��Ŀ����
		TbSiteItem beanItem = null;
		// ��Ŀ����itemId
		String itemId = request.getParameter("item");
		if(itemId!=null && !"".equals(itemId)){
			try{
				beanItem = (TbSiteItem)this.getCommonBo().get(TbSiteItem.class, new Long(itemId));
			}catch(Exception e){}
		}
		request.setAttribute("beanItem", beanItem);
		if(beanItem!=null){
			// �����ѯ
			String hql = "from TbSiteContent a where a.itemId=?";
			List pars = this.getList();
			pars.add(beanItem.getItemId());
			// ����
			String title = request.getParameter("title");
			if(title!=null && !"".equals(title)){
				hql += " and a.contentTitle like ?";
				pars.add("%"+title+"%");
				request.setAttribute("title", title);
			}
			// LOGOͼ��
			String logo = request.getParameter("logo");
			if(logo!=null && "1".equals(logo)){
				hql += " and a.contentLogo is null";
			}else if(logo!=null && "0".equals(logo)){
				hql += " and a.contentLogo is not null";
			}
			request.setAttribute("logo", logo);
			// ���ⷢ��
			String publish = request.getParameter("publish");
			if(publish!=null && "1".equals(publish)){
				hql += " and a.publishFlag='1'";
			}else if(publish!=null && "0".equals(publish)){
				hql += " and (a.publishFlag is null or a.publishFlag<>'1')";
			}
			request.setAttribute("publish", publish);
			// Ĭ������
			hql += " order by a.publishTime desc,a.creationTime desc,a.contentTitle";
			// ִ�в�ѯ
			List beanList = this.getCommonBo().findHQLPage(hql, pars);
			request.setAttribute("beanList", beanList);
		}
		// ����
		return "list";
	}
	
	// �ڳ�����Ŀ�����½����༭
	public String edit() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ���ݶ���
		TbSiteContent bean = null;
		// ��Ŀ����itemId
		String contentId = request.getParameter("id");
		if(contentId!=null && !"".equals(contentId)){
			try{
				bean = (TbSiteContent)this.getCommonBo().get(TbSiteContent.class, new Long(contentId));
			}catch(Exception e){}
		}
		if(bean==null){
			bean = new TbSiteContent();
			// ��Ŀ����
			TbSiteItem beanItem = null;
			// ��Ŀ����itemId
			String itemId = request.getParameter("item");
			if(itemId!=null && !"".equals(itemId)){
				try{
					beanItem = (TbSiteItem)this.getCommonBo().get(TbSiteItem.class, new Long(itemId));
				}catch(Exception e){}
			}
			if(beanItem!=null){
				bean.setItemId(beanItem.getItemId());
			}
		}
		request.setAttribute("bean", bean);
		// ����
		return INPUT;
	}
	
	// �ڳ�����Ŀ�����½����༭����
	public String doEdit() throws Exception {
		HttpServletRequest request = this.getRequest();
		MultiRequestUtil multiRequest = new MultiRequestUtil(request);
		// ���ݶ���
		TbSiteContent bean = null;
		// ��Ŀ����itemId
		String contentId = multiRequest.getParameter("contentId");
		if(contentId!=null && !"".equals(contentId)){
			try{
				bean = (TbSiteContent)this.getCommonBo().get(TbSiteContent.class, new Long(contentId));
			}catch(Exception e){}
		}
		if(bean==null){
			bean = new TbSiteContent();
			// ��Ŀ����
			TbSiteItem beanItem = null;
			// ��Ŀ����itemId
			String itemId = multiRequest.getParameter("itemId");
			if(itemId!=null && !"".equals(itemId)){
				try{
					beanItem = (TbSiteItem)this.getCommonBo().get(TbSiteItem.class, new Long(itemId));
				}catch(Exception e){}
			}
			if(beanItem!=null){
				bean.setItemId(beanItem.getItemId());
			}
		}
		// ͼ��
		FileItem item = multiRequest.getFile("contentLogo");
		if(item!=null && item.getSize()>0){
			InputStream bis = null;
			OutputStream out = null;
			try {
				// ��׺��
				String lastName="";
				int nIndex = item.getName().lastIndexOf(".");
				if (nIndex != -1) {
					lastName = item.getName().substring(nIndex + 1).toLowerCase();
				}
				if("gif".equals(lastName) || "jpg".equals(lastName) || "jpeg".equals(lastName) || "png".equals(lastName) || "bmp".equals(lastName)){
					/* �ϴ��ļ� ------------------------------------------- */
					String uploadPath = "upload/image/";
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
					// �ļ�·��
					String fileName = DateUtil.format(dateNow, "yyyyMMddHHmmss")+"_"+new Random().nextInt(1000)+"."+lastName;
					savePath += fileName;
					uploadPath += fileName;
					
					bis = new BufferedInputStream(item.getInputStream());
					// �����
					File file = new File(savePath);
					out = new BufferedOutputStream(new FileOutputStream(file));
					byte[] bytes = new byte[1];
					while (bis.read(bytes) != -1) {
						out.write(bytes);
					}
					bean.setContentLogo(uploadPath);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					if(null!=bis){
						bis.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if(null!=out){
						out.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		// ����
		String contentTitle = multiRequest.getParameter("contentTitle");
		bean.setContentTitle(contentTitle);
		// ���
		String contentIntro = multiRequest.getParameter("contentIntro");
		bean.setContentIntro(contentIntro);
		// ����
		String contentType = multiRequest.getParameter("contentType");
		bean.setContentType(contentType);
		// ���ӵ�ַ
		String linkUrl = multiRequest.getParameter("linkUrl");
		bean.setLinkUrl(linkUrl);
		// ��������
		String contentCnt = multiRequest.getParameter("contentCnt");
		bean.setContentCnt(contentCnt);
		// ���ⷢ��
		String publishFlag = multiRequest.getParameter("publishFlag");
		if(publishFlag!=null && "1".equals(publishFlag)){
			bean.setPublishFlag("1");
		}else{
			bean.setPublishFlag("0");
		}
		// ���ⷢ��ʱ��
		String publishTime = multiRequest.getParameter("publishTime");
		Date publishTimeDate = null;
		if(publishTime!=null && !"".equals(publishTime)){
			try{
				publishTimeDate = DateUtil.toDate(publishTime,"yyyy-MM-dd HH:mm");
			}catch(Exception e){}
		}
		bean.setPublishTime(publishTimeDate);
		// ��ע
		String memo = multiRequest.getParameter("memo");
		bean.setMemo(memo);
		// ����
		try{
			bean = (TbSiteContent)this.getCommonBo().saveOrUpdate(bean);
			// ����
			return SUCCESS;
		}catch(Exception e){
			request.setAttribute("bean", bean);
			// ����
			return INPUT;
		}
	}
	
	// �ڳ�����Ŀ����ɾ��
	public String del() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ���ݶ���
		TbSiteContent bean = null;
		// ��Ŀ����itemId
		String contentId = request.getParameter("id");
		if(contentId!=null && !"".equals(contentId)){
			try{
				bean = (TbSiteContent)this.getCommonBo().get(TbSiteContent.class, new Long(contentId));
			}catch(Exception e){}
		}
		if(bean!=null){
			this.getCommonBo().delete(bean);
			request.setAttribute("alert", "ɾ���ɹ�");
		}else{
			request.setAttribute("alert", "���󲻴��ڻ��ѱ�ɾ��");
		}
		// ����
		return execute();
	}
}