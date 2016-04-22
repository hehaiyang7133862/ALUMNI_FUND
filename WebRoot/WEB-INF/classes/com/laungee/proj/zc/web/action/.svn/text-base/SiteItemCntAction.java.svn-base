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
	// 众筹网栏目内容管理
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 栏目对象
		TbSiteItem beanItem = null;
		// 栏目对象itemId
		String itemId = request.getParameter("item");
		if(itemId!=null && !"".equals(itemId)){
			try{
				beanItem = (TbSiteItem)this.getCommonBo().get(TbSiteItem.class, new Long(itemId));
			}catch(Exception e){}
		}
		request.setAttribute("beanItem", beanItem);
		if(beanItem!=null){
			// 构造查询
			String hql = "from TbSiteContent a where a.itemId=?";
			List pars = this.getList();
			pars.add(beanItem.getItemId());
			// 标题
			String title = request.getParameter("title");
			if(title!=null && !"".equals(title)){
				hql += " and a.contentTitle like ?";
				pars.add("%"+title+"%");
				request.setAttribute("title", title);
			}
			// LOGO图标
			String logo = request.getParameter("logo");
			if(logo!=null && "1".equals(logo)){
				hql += " and a.contentLogo is null";
			}else if(logo!=null && "0".equals(logo)){
				hql += " and a.contentLogo is not null";
			}
			request.setAttribute("logo", logo);
			// 对外发布
			String publish = request.getParameter("publish");
			if(publish!=null && "1".equals(publish)){
				hql += " and a.publishFlag='1'";
			}else if(publish!=null && "0".equals(publish)){
				hql += " and (a.publishFlag is null or a.publishFlag<>'1')";
			}
			request.setAttribute("publish", publish);
			// 默认排序
			hql += " order by a.publishTime desc,a.creationTime desc,a.contentTitle";
			// 执行查询
			List beanList = this.getCommonBo().findHQLPage(hql, pars);
			request.setAttribute("beanList", beanList);
		}
		// 返回
		return "list";
	}
	
	// 众筹网栏目内容新建、编辑
	public String edit() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 内容对象
		TbSiteContent bean = null;
		// 栏目对象itemId
		String contentId = request.getParameter("id");
		if(contentId!=null && !"".equals(contentId)){
			try{
				bean = (TbSiteContent)this.getCommonBo().get(TbSiteContent.class, new Long(contentId));
			}catch(Exception e){}
		}
		if(bean==null){
			bean = new TbSiteContent();
			// 栏目对象
			TbSiteItem beanItem = null;
			// 栏目对象itemId
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
		// 返回
		return INPUT;
	}
	
	// 众筹网栏目内容新建、编辑保存
	public String doEdit() throws Exception {
		HttpServletRequest request = this.getRequest();
		MultiRequestUtil multiRequest = new MultiRequestUtil(request);
		// 内容对象
		TbSiteContent bean = null;
		// 栏目对象itemId
		String contentId = multiRequest.getParameter("contentId");
		if(contentId!=null && !"".equals(contentId)){
			try{
				bean = (TbSiteContent)this.getCommonBo().get(TbSiteContent.class, new Long(contentId));
			}catch(Exception e){}
		}
		if(bean==null){
			bean = new TbSiteContent();
			// 栏目对象
			TbSiteItem beanItem = null;
			// 栏目对象itemId
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
		// 图标
		FileItem item = multiRequest.getFile("contentLogo");
		if(item!=null && item.getSize()>0){
			InputStream bis = null;
			OutputStream out = null;
			try {
				// 后缀名
				String lastName="";
				int nIndex = item.getName().lastIndexOf(".");
				if (nIndex != -1) {
					lastName = item.getName().substring(nIndex + 1).toLowerCase();
				}
				if("gif".equals(lastName) || "jpg".equals(lastName) || "jpeg".equals(lastName) || "png".equals(lastName) || "bmp".equals(lastName)){
					/* 上传文件 ------------------------------------------- */
					String uploadPath = "upload/image/";
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
					// 文件路径
					String fileName = DateUtil.format(dateNow, "yyyyMMddHHmmss")+"_"+new Random().nextInt(1000)+"."+lastName;
					savePath += fileName;
					uploadPath += fileName;
					
					bis = new BufferedInputStream(item.getInputStream());
					// 输出流
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
		// 标题
		String contentTitle = multiRequest.getParameter("contentTitle");
		bean.setContentTitle(contentTitle);
		// 简介
		String contentIntro = multiRequest.getParameter("contentIntro");
		bean.setContentIntro(contentIntro);
		// 类型
		String contentType = multiRequest.getParameter("contentType");
		bean.setContentType(contentType);
		// 链接地址
		String linkUrl = multiRequest.getParameter("linkUrl");
		bean.setLinkUrl(linkUrl);
		// 详情内容
		String contentCnt = multiRequest.getParameter("contentCnt");
		bean.setContentCnt(contentCnt);
		// 对外发布
		String publishFlag = multiRequest.getParameter("publishFlag");
		if(publishFlag!=null && "1".equals(publishFlag)){
			bean.setPublishFlag("1");
		}else{
			bean.setPublishFlag("0");
		}
		// 对外发布时间
		String publishTime = multiRequest.getParameter("publishTime");
		Date publishTimeDate = null;
		if(publishTime!=null && !"".equals(publishTime)){
			try{
				publishTimeDate = DateUtil.toDate(publishTime,"yyyy-MM-dd HH:mm");
			}catch(Exception e){}
		}
		bean.setPublishTime(publishTimeDate);
		// 备注
		String memo = multiRequest.getParameter("memo");
		bean.setMemo(memo);
		// 保存
		try{
			bean = (TbSiteContent)this.getCommonBo().saveOrUpdate(bean);
			// 返回
			return SUCCESS;
		}catch(Exception e){
			request.setAttribute("bean", bean);
			// 返回
			return INPUT;
		}
	}
	
	// 众筹网栏目内容删除
	public String del() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 内容对象
		TbSiteContent bean = null;
		// 栏目对象itemId
		String contentId = request.getParameter("id");
		if(contentId!=null && !"".equals(contentId)){
			try{
				bean = (TbSiteContent)this.getCommonBo().get(TbSiteContent.class, new Long(contentId));
			}catch(Exception e){}
		}
		if(bean!=null){
			this.getCommonBo().delete(bean);
			request.setAttribute("alert", "删除成功");
		}else{
			request.setAttribute("alert", "对象不存在或已被删除");
		}
		// 返回
		return execute();
	}
}