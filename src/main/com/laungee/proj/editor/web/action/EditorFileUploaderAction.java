package com.laungee.proj.editor.web.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.json.simple.JSONObject;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.util.DateUtil;
import com.laungee.proj.common.util.MultiRequestUtil;

public class EditorFileUploaderAction extends BaseAction {
	//���ò���ֵ
	private String uploadPath = "upload/";//�ϴ��ļ�����Ŀ¼
	private String imageMaxsize = "";//��С��λΪM,ȱʡΪ��������;
	private String imageTypes = "gif,jpg,jpeg,png,bmp";//����ȱʡֵΪgif,jpg,jpeg,png,bmp
	private String imageMaxwidth = "";//ͼƬ���ȱʡΪ���޶�
	private String flashMaxsize = "";//��С��λΪM,ȱʡΪ��������;
	private String flashTypes = "swf,flv";//����ȱʡֵΪswf,flv
	private String mediaMaxsize = "";//��С��λΪM,ȱʡΪ��������;
	private String mediaTypes = "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb";//����ȱʡֵΪswf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb
	private String fileMaxsize = "";//��С��λΪM,ȱʡΪ��������;
	private String fileTypes = "";// ����ȱʡֵΪ�������� doc,xls,ppt,pdf,txt,rar,zip
	// ��������
	public String uploadFile() throws Exception {
		HttpServletRequest request = this.getRequest();
		MultiRequestUtil multiRequest = new MultiRequestUtil(request);
		HttpServletResponse response = this.getResponse();
		
		// �ļ�����Ŀ¼·��
		String savePath = request.getSession().getServletContext().getRealPath("/"+uploadPath)+"/";
		// �ļ�����Ŀ¼URL
		String saveUrl = request.getContextPath() + "/" + uploadPath;

		// ���������ϴ����ļ���չ��
		HashMap<String, String> extMap = new HashMap<String, String>();
		if(imageTypes == null || "".equals(imageTypes)){
			extMap.put("image", "gif,jpg,jpeg,png,bmp");
		}else{
			extMap.put("image", imageTypes);
		}
		if(flashTypes == null || "".equals(flashTypes)){
			extMap.put("flash", "swf,flv");
		}else{
			extMap.put("flash", flashTypes);
		}
		if(mediaTypes == null || "".equals(mediaTypes)){
			extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		}else{
			extMap.put("media", mediaTypes);
		}
		if(fileTypes == null || "".equals(fileTypes)){
			extMap.put("file", null);
		}else{
			extMap.put("file", fileTypes);
		}

		long maxSize = -1;
		
		try {
			// ���Ŀ¼
			File uploadDir = new File(savePath);
			if (!uploadDir.isDirectory()) {
				this.sendResponse(response,getError("�ϴ�Ŀ¼������!"));
				return null;
			}
			// ���Ŀ¼дȨ��
			if (!uploadDir.canWrite()) {
				this.sendResponse(response,getError("�ϴ�Ŀ¼û��дȨ��!"));
				return null;
			}
			String dirName = request.getParameter("dir");
			if (dirName == null) {
				dirName = "image";
			}
			if (!extMap.containsKey(dirName)) {
				this.sendResponse(response,getError("Ŀ¼������ȷ!"));
				return null;
			}else{
				if("image".equals(dirName)){
					try{
						maxSize = Long.parseLong(imageMaxsize);
					}catch(Exception ex){}
					
				}else if("flash".equals(dirName)){
					try{
						maxSize = Long.parseLong(flashMaxsize);
					}catch(Exception ex){}
				}else if("media".equals(dirName)){
					try{
						maxSize = Long.parseLong(mediaMaxsize);
					}catch(Exception ex){}
				}else if("file".equals(dirName)){
					try{
						maxSize = Long.parseLong(fileMaxsize);
					}catch(Exception ex){}
				}else{
					this.sendResponse(response,getError("Ŀ¼������ȷ!"));
					return null;
				}
			}
			// �����ļ���
			savePath += dirName + "/";
			saveUrl += dirName + "/";
			
			File saveDirFile = new File(savePath);

			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
			
			Date dateNow = this.getCommonBo().getSysDate();
			String ymd = DateUtil.format(dateNow,"yyyyMMdd");
			savePath += ymd + "/";
			saveUrl += ymd + "/";
			File dirFile = new File(savePath);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}
			
			FileItem[] items = multiRequest.getFiles();
			if(items!=null){
				if(items.length==1){
					FileItem item = items[0];
					String fileName = item.getName();
					long fileSize = item.getSize();
					
					// ����ļ���С
					if(maxSize>0 && fileSize>maxSize*1024*1024){
						this.sendResponse(response,getError("�ϴ��ļ���С����"+maxSize+"M����!"));
						return null;
					}
					// �����չ��
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					
					String dirTypes = extMap.get(dirName);
					if (!("file".equals(dirName) && dirTypes==null)){
						if (!Arrays.<String> asList(dirTypes.split(","))
								.contains(fileExt)) {
							this.sendResponse(response,getError("�ϴ��ļ���չ���ǲ��������չ��,\nֻ����" + dirTypes + "��ʽ!"));
							return null;
						}
					}
					
					String newFileName = DateUtil.format(dateNow,"yyyyMMddHHmmss") + "_" + new Random().nextInt(1000) + "." + fileExt;
					try {
						File uploadedFile = new File(savePath, newFileName);
						item.write(uploadedFile);	
//						// �ж��Ƿ�Ҫѹ��ͼƬ
//						int imgMaxwidth = -1;
//						if("image".equals(dirName)){
//							if(imageMaxwidth!=null && !"".equals(imageMaxwidth)){
//								try{
//									imgMaxwidth = Integer.parseInt(imageMaxwidth);
//								}catch(Exception ex){}
//							}
//							if(imgMaxwidth>0){
//								ImageUtil.zoomByWidth(uploadedFile,imgMaxwidth);
//							}
//						}
					} catch (Exception e) {
						this.sendResponse(response,getError("�ϴ��ļ�ʧ��!"));
						return null;
					}
					
					JSONObject obj = new JSONObject();
					obj.put("error", 0);
					obj.put("url", saveUrl + newFileName);
					this.sendResponse(response,obj.toJSONString());
					return null;
				}
			}
		} catch (Exception e) {
			this.sendResponse(response,getError("����������!"));
			return null;
		}
		this.sendResponse(response,getError("��ѡ����Ҫ�ϴ����ļ�!"));
		return null;
	}

	public String manageFile() throws Exception {  
		HttpServletRequest request = this.getRequest();
		HttpServletResponse response = this.getResponse();
		// �ļ�����Ŀ¼·��  
		String rootPath = request.getSession().getServletContext().getRealPath("/"+uploadPath)+"/";        
		// �ļ�����Ŀ¼URL  
		String rootUrl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"+uploadPath;  
		//ͼƬ��չ��
		String[] imageTypes = new String[]{"gif", "jpg", "jpeg", "png", "bmp"};
		
		String dirName = request.getParameter("dir");
		if (dirName != null) {
			if(!Arrays.<String>asList(new String[]{"image", "flash", "media", "file"}).contains(dirName)){
				this.sendResponse(response,getError("Ŀ¼������ȷ!"));
				return null;
			}
			rootPath += dirName + "/";
			rootUrl += dirName + "/";
			File saveDirFile = new File(rootPath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
		}
		//����path���������ø�·����URL
		String path = request.getParameter("path") != null ? request.getParameter("path") : "";
		String currentPath = rootPath + path;
		String currentUrl = rootUrl + path;
		String currentDirPath = path;
		String moveupDirPath = "";
		if (!"".equals(path)) {
			String str = currentDirPath.substring(0, currentDirPath.length() - 1);
			moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0, str.lastIndexOf("/") + 1) : "";
		}
		
		//������ʽ��name or size or type
		String order = request.getParameter("order") != null ? request.getParameter("order").toLowerCase() : "name";
		
		//������ʹ��..�ƶ�����һ��Ŀ¼
		if (path.indexOf("..") >= 0) {
			this.sendResponse(response,getError("�����������һ��Ŀ¼!"));
			return null;
		}
		//���һ���ַ�����/
		if (!"".equals(path) && !path.endsWith("/")) {
			this.sendResponse(response,getError("Ŀ¼������Ч!"));
			return null;
		}
		//Ŀ¼�����ڻ���Ŀ¼
		File currentPathFile = new File(currentPath);
		if(!currentPathFile.isDirectory()){
			this.sendResponse(response,getError("Ŀ¼�����ڻ���Ŀ¼!"));
			return null;
		}
		
		//����Ŀ¼ȡ���ļ���Ϣ
		List<Hashtable> fileList = new ArrayList<Hashtable>();
		if(currentPathFile.listFiles() != null) {
			for (File file : currentPathFile.listFiles()) {
				Hashtable<String, Object> hash = new Hashtable<String, Object>();
				String fileName = file.getName();
				if(file.isDirectory()) {
					hash.put("is_dir", true);
					hash.put("has_file", (file.listFiles() != null));
					hash.put("filesize", 0L);
					hash.put("is_photo", false);
					hash.put("filetype", "");
				} else if(file.isFile()){
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					hash.put("is_dir", false);
					hash.put("has_file", false);
					hash.put("filesize", file.length());
					hash.put("is_photo", Arrays.<String>asList(imageTypes).contains(fileExt));
					hash.put("filetype", fileExt);
				}
				hash.put("filename", fileName);
				hash.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
				fileList.add(hash);
			}
		}
		
		if ("size".equals(order)) {
			Collections.sort(fileList, new SizeComparator());
		} else if ("type".equals(order)) {
			Collections.sort(fileList, new TypeComparator());
		} else {
			Collections.sort(fileList, new NameComparator());
		}
		JSONObject result = new JSONObject();
		result.put("moveup_dir_path", moveupDirPath);
		result.put("current_dir_path", currentDirPath);
		result.put("current_url", currentUrl);
		result.put("total_count", fileList.size());
		result.put("file_list", fileList);

		this.sendResponse(response,result.toJSONString());
		return null;
	}  
	
	private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}

	private class NameComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable)a;
			Hashtable hashB = (Hashtable)b;
			if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String)hashA.get("filename")).compareTo((String)hashB.get("filename"));
			}
		}
	}  

	private class SizeComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable)a;
			Hashtable hashB = (Hashtable)b;
			if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
				return 1;
			} else {
				if (((Long)hashA.get("filesize")) > ((Long)hashB.get("filesize"))) {
					return 1;
				} else if (((Long)hashA.get("filesize")) < ((Long)hashB.get("filesize"))) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}

	private class TypeComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable)a;
			Hashtable hashB = (Hashtable)b;
			if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String)hashA.get("filetype")).compareTo((String)hashB.get("filetype"));
			}
		}
	}
}
