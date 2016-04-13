package com.laungee.proj.common.editor;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.laungee.proj.common.biz.impl.CommonBizImpl;
  
public class UploadAccessory extends HttpServlet {  
	  
    private static final long serialVersionUID = 1L;  

    // �ϴ��ļ��Ĵ�С  
    protected long MAX_SIZE = 1000000;  
    // ���������ϴ����ļ�����չ��  
    protected String[] FILETYPES = new String[]{"doc", "xls", "ppt", "pdf", "txt", "rar" , "zip"};  
    // �����ϴ��ļ�����Ŀ¼·��  
    protected String UPLOAD_PATH = "";  
      
    protected String id = "";    
  
    protected String attachTitle = "";   
  
    protected boolean isFlag = false;  
  
    protected String tempTitle = "";  
  
	private CommonBizImpl commonBiz;
    public void init() throws ServletException {
    	WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
    	commonBiz = (CommonBizImpl) wac.getBean("commonBiz");
	}
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)  
    throws ServletException, IOException {  
        doPost(request, response);  
    }  
  
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
    throws ServletException, IOException {  
          
        response.setContentType("text/html; charset=UTF-8");  
        PrintWriter out = response.getWriter();  
        String savePath = this.getInitParameter("UPLOAD_PATH");  
        if (savePath == null || "".equals(savePath)) {  
            out.println(alertMsg("�㻹û�����ϴ��ļ������Ŀ¼·��!"));  
            return;  
        }  
        //�ж��Ƿ��������ϴ��ļ��Ĵ�С  
        if(this.getInitParameter("MAX_SIZE") != null){  
            MAX_SIZE = Integer.parseInt(this.getInitParameter("MAX_SIZE"));  
        }  
        //�ж��Ƿ��������ϴ��ļ�������  
        if(this.getInitParameter("FILETYPES") != null){  
            FILETYPES = toArray(this.getInitParameter("FILETYPES"));  
        }  
        // �ļ�����Ŀ¼·��  
        String uploadPath = request.getSession().getServletContext().getRealPath("/"+savePath)+"/";  
  
        // �ļ�����Ŀ¼URL  
        String saveUrl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"+savePath;  
        //String saveUrl = savePath;
        
        if(!ServletFileUpload.isMultipartContent(request)){   
            out.println(alertMsg("��ѡ��Ҫ�ϴ����ļ�!"));  
            return;  
        }  
        //���Ŀ¼  
        File uploadDir = new File(uploadPath);  
        if(!uploadDir.isDirectory()){  
            out.println(alertMsg("�ϴ�Ŀ¼������!"));  
            return;  
        }  
        //���Ŀ¼дȨ��  
        if(!uploadDir.canWrite()){  
            out.println(alertMsg("��ǰ��ɫ���ϴ�Ŀ¼û��дȨ��!"));  
            return;  
        }  
  
        FileItemFactory factory = new DiskFileItemFactory();  
        ServletFileUpload upload = new ServletFileUpload(factory);  
        upload.setHeaderEncoding("UTF-8");  
        String temp = null;  
        String ext = null;  
        try{  
            List items = upload.parseRequest(request);  
            Iterator itr = items.iterator();  
            while (itr.hasNext()) {  
                FileItem item = (FileItem) itr.next();  
                String fileName = item.getName();  
                temp = (String) item.getName();  
                if(temp != null && !isFlag){  
                    temp = temp.substring(temp.lastIndexOf("\\")+1);  
                    tempTitle = temp;  
                    isFlag = true;  
                }  
                // KindEditor�༭����ID    
                if(((String)item.getFieldName()).equals("id")){     
                    id = item.getString();   
                }    
                // �ϴ�ͼƬ��������ʾ  
                if(((String)item.getFieldName()).equals("attachTitle")){     
                    attachTitle = item.getString();   
                    if(attachTitle != null){  
                        attachTitle = new String(attachTitle.getBytes("ISO8859-1"),"UTF-8");  
                    }  
                }   
                if (!item.isFormField()) {  
                    //����ļ���С  
                    if(item.getSize() > MAX_SIZE){                         
                        out.println(alertMsg("�ϴ��ļ���С��������!"));  
                        return;  
                    }  
                    //�����չ��  
                    String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();  
                    if(!Arrays.asList(FILETYPES).contains(fileExt)){  
                        out.println(alertMsg("�ϴ��ļ���չ���ǲ��������չ��!"));  
                        return;  
                    }  
                    // ����ʱ�䴴���ļ���
                    Date dateNow = commonBiz.getSysDate();
                    SimpleDateFormat folderNameFormat = new SimpleDateFormat("yyyyMMdd");  
                    String realPath = uploadPath + folderNameFormat.format(dateNow);  
                    File folder = new File(realPath);  
                    boolean flag = folder.exists();  
                    // ȷ���ļ����Ƿ��Ѿ�����  
                    if(!flag){  
                        flag = folder.mkdir();  
                    }  
                    // �����ļ��в��ϴ�ͼƬ  
                    if(flag){  
                        SimpleDateFormat fileNameFormat = new SimpleDateFormat("yyyyMMddHHmmss");                     
                        String newFileName = fileNameFormat.format(dateNow) + "_"+ new Random().nextInt(1000) + "." + fileExt;                         
                        File uploadedFile = new File(realPath, newFileName);                          
                        item.write(uploadedFile);  
                        saveUrl += folderNameFormat.format(dateNow) + "/" + newFileName;   
                        ext = fileExt;  
                    }else{  
                        System.out.println(alertMsg(" �ļ��д���ʧ�ܣ���ȷ�ϴ���û��д�������ҿռ��㹻!"));  
                    }                 
                }  
            }  
              
  
            if(attachTitle == null || "".equals(attachTitle)){  
                attachTitle = tempTitle;  
            }  
              
            out.println(insertAttach(id, saveUrl, attachTitle, ext));  
              
        }catch(Exception e){  
            e.printStackTrace();  
        }finally{  
            out.flush();  
            out.close();  
            isFlag = false;  
        }  
    }  
  
      
    /** 
     * �����ӡ�ϴ�ʧ�ܵ�JSON��� 
     *  
     * @param message    ʧ����Ϣ 
     *  
     * @return ҳ���ϴ�ʧ�ܵ�JSON��� 
     */  
    public String alertMsg(String message) {  
        StringBuilder sb = new StringBuilder("<html>");  
        sb.append("<head>").append("<title>error</title>");  
        sb.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\">");  
        sb.append("</head>");  
        sb.append("<body>");  
        sb.append("<script type=\"text/javascript\">");  
        sb.append("alert(\"").append(message).append("\");history.back();</script>");  
        sb.append("</body>").append("</html>");  
  
        return sb.toString();   
    }   
      
    /** 
     * ������븽�����༭�����Ľű� 
     *  
     * @param id     �༭��ID 
     *  
     * @param url    �ϴ������ĵ�ַ 
     *  
     * @param title  �ϴ�ʱ���õ�title���� 
     *  
     * @param ext    �ϴ��ļ��ĺ�׺�� 
     *  
     * @return  ���븽�����༭���Ľű���� 
     */  
    public String insertAttach(String id, String url, String title, String ext){  
        StringBuilder sb = new StringBuilder("<html>");  
        sb.append("<head>").append("<title>Insert Accessory</title>");  
        sb.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\">");  
        sb.append("</head>");  
        sb.append("<body>");  
        sb.append("<script type=\"text/javascript\">");  
        sb.append("parent.KE.plugin[\"accessory\"].insert(\"").append(id).append("\",\"");  
        sb.append(url).append("\",\"").append(title).append("\",\"").append(ext).append("\");</script>");  
        sb.append("</body>").append("</html>");  
        return sb.toString();  
    }  
  
    /** 
     * ��������ϴ�ͼƬ���͵����� 
     *  
     * @param filesType �����ϴ���ͼƬ���� 
     *  
     * @return �����ϴ�ͼƬ���� 
     */  
    public String[] toArray(String filesType){  
  
        if(filesType == null){  
            return null;  
        }  
  
        String[] types = filesType.split(",");  
        String[] allowTypes = new String[types.length];  
        int i = 0;  
        for(int j=0;j<types.length;j++){  
            allowTypes[i] = types[j];  
            i++;  
        }  
  
        return allowTypes;  
    }  
}  
