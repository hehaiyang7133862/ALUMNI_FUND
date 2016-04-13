package com.laungee.proj.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class MultiRequestUtil {
	
	private Map params;
	private Map files;
	
	public MultiRequestUtil(HttpServletRequest request){
		try {
			params = new HashMap();
			files = new HashMap();
			if(ServletFileUpload.isMultipartContent(request)) {
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setHeaderEncoding("UTF-8");
				List items = upload.parseRequest(request);
				if(items!=null){
					for(int i=0;i<items.size();i++){
						FileItem item = (FileItem) items.get(i);
						String key = item.getFieldName();
						if(item.isFormField()){
							List values = (List)params.get(key);
							if(values == null){
								values = new ArrayList();
							}
							values.add(item.getString("UTF-8"));
							params.put(key, values);
						}else if(item.getSize()>0){
							List values = (List)files.get(key);
							if(values == null){
								values = new ArrayList();
							}
							values.add(item);
							files.put(key, values);
						}
					}
				}
			}
		} catch (Exception e) {
		}
	}
	
	public MultiRequestUtil(HttpServletRequest request, String charset){
		try {
			params = new HashMap();
			files = new HashMap();
			if(ServletFileUpload.isMultipartContent(request)) {
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setHeaderEncoding(charset);
				List items = upload.parseRequest(request);
				if(items!=null){
					for(int i=0;i<items.size();i++){
						FileItem item = (FileItem) items.get(i);
						String key = item.getFieldName();
						if(item.isFormField()){
							List values = (List)params.get(key);
							if(values == null){
								values = new ArrayList();
							}
							values.add(item.getString(charset));
							params.put(key, values);
						}else if(item.getSize()>0){
							List values = (List)files.get(key);
							if(values == null){
								values = new ArrayList();
							}
							values.add(item);
							files.put(key, values);
						}
					}
				}
			}
		} catch (Exception e) {
		}
	}
	
	public String getParameter(String name){
		List values = (List)params.get(name);
		if (values!=null){
			if(values.size()>0){
				return (String)values.get(0);
			}
		}
		return null;
	}
	
	public String[] getParameterValues(String name){
		List values = (List)params.get(name);
		if (values!=null){
			if(values.size()>0){
				return (String[])values.toArray(new String[values.size()]);
			}
		}
		return null;
	}
	
	public String[] getParameterValues(){
		Collection values = params.values();
		if (values.size()>0){
			List strs = new ArrayList();
			Iterator iterator = values.iterator();
			while(iterator.hasNext()){
				List list = (List)iterator.next();
				if(list.size()>0){
					for(int i=0;i<list.size();i++){
						strs.add(list.get(i));
					}
				}
			}
			if(strs.size()>0){
				return (String[])strs.toArray(new String[strs.size()]);
			}
		}
		return null;
	}
	
	public FileItem getFile(String name){
		List items = (List)files.get(name);
		if (items!=null){
			if(items.size()>0){
				return (FileItem)items.get(0);
			}
		}
		return null;
	}
	
	public FileItem[] getFiles(String name){
		List items = (List)files.get(name);
		if (items!=null){
			if(items.size()>0){
				return (FileItem[])items.toArray(new FileItem[items.size()]);
			}
		}
		return null;
	}
	
	public FileItem[] getFiles(){
		Collection values = files.values();
		if (values.size()>0){
			List items = new ArrayList();
			Iterator iterator = values.iterator();
			while(iterator.hasNext()){
				List list = (List)iterator.next();
				if(list.size()>0){
					for(int i=0;i<list.size();i++){
						items.add(list.get(i));
					}
				}
			}
			if(items.size()>0){
				return (FileItem[])items.toArray(new FileItem[items.size()]);
			}
		}
		return null;
	}
}
