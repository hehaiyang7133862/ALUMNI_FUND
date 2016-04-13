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
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.laungee.proj.common.biz.impl.CommonBizImpl;
import com.laungee.proj.common.editor.ImageUtil;
  
/** 
 * 实锟斤拷KindEditor图片锟较达拷锟斤拷Servlet 
 *  
 * @author SENHUI 
 *  
 * @since 2011/03/21 20:20:23 
 */  
public class UploadImage extends HttpServlet {  
	private static final long serialVersionUID = 5121794650920770832L;  
    
    // 上传图片的最大宽度  
    protected int MAX_WIDTH = -1;   
    // 上传图片的大小  
    protected long MAX_SIZE = 1000000;  
    // 定义允许上传的图片的扩展名  
    protected String[] IMAGETYPES = new String[] { "gif", "jpg", "jpeg", "png", "bmp" };  
    // 定义上传图片保存目录路径  
    protected String UPLOAD_PATH = "";    
    // 上传图片设置信息     
    protected String id = "";    
    // 上传图片的TITLE属性值  
    protected String imgTitle = "";   
      
    protected int imgWidth = -1;     
  
    protected int imgHeight = -1;     
      
    protected String imgBorder = "";     
      
    protected String resizeImg = "";  
      
    protected boolean isFlag = false;  
      
    protected String tempTitle = "";  
  
	private CommonBizImpl commonBiz;
    public void init() throws ServletException {
    	WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
    	commonBiz = (CommonBizImpl) wac.getBean("commonBiz");
	}
    
    protected void doGet(HttpServletRequest request,  
            HttpServletResponse response) throws ServletException, IOException {  
        response.setContentType("text/html; charset=UTF-8");  
        PrintWriter out = response.getWriter();  
        String savePath = this.getInitParameter("UPLOAD_PATH");  
        if (savePath == null || "".equals(savePath)) {  
            out.println(alertMsg("你还没设置上传图片保存的目录路径!"));  
            return;  
        }  
        //判断是否设置了上传图片的大小  
        if(this.getInitParameter("MAX_SIZE") != null){  
            MAX_SIZE = Integer.parseInt(this.getInitParameter("MAX_SIZE"));  
        }  
        //判断是否设置了上传图片的类型  
        if(this.getInitParameter("IMAGETYPES") != null){  
            IMAGETYPES = toArray(this.getInitParameter("IMAGETYPES"));  
        }  
        // 图片保存目录路径  
        String uploadPath = request.getSession().getServletContext().getRealPath("/"+savePath)+"/";  
          
        // 图片保存目录URL  
        String saveUrl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"+savePath;
        //String saveUrl = savePath;
        
        // 检查上传图片是否存在  
        if (!ServletFileUpload.isMultipartContent(request)) {  
            out.println(alertMsg("请选择你要上传的图片!"));  
            return;  
        }
        // 检查目录  
        File uploadDir = new File(uploadPath);  
        if (!uploadDir.isDirectory()) {  
            out.println(alertMsg("上传图片保存的目录不存在!"));
            return;  
        }  
          
        // 检查目录写权限  
        if (!uploadDir.canWrite()) {  
            out.println(alertMsg("上传图片保存的目录没有写权限!"));  
            return;  
        }  
       
        // 准备上传图片  
        DiskFileItemFactory factory = new DiskFileItemFactory();  
        ServletFileUpload upload = new ServletFileUpload(factory);  
        upload.setHeaderEncoding("UTF-8");  
        List items = null;  
        String temp = null; 
        try {  
            items = upload.parseRequest(request);
            Iterator itr = items.iterator();  
            while (itr.hasNext()) {  
                FileItem item = (FileItem) itr.next();  
                 // 上传图片的原文件名     
                String fileName = item.getName();   
                temp = (String) item.getName();  
                if(temp != null && !isFlag){  
                    temp = temp.substring(temp.lastIndexOf("\\")+1);  
                    tempTitle = temp;  
                    isFlag = true;  
                }  
                 // KindEditor编辑器的ID    
                if(((String)item.getFieldName()).equals("id")){     
                    id = item.getString();   
                }    
                 
                // 上传图片的重新提示  
                if(((String)item.getFieldName()).equals("imgTitle")){     
                    imgTitle = item.getString();   
                    if(imgTitle != null){  
                        imgTitle = new String(imgTitle.getBytes("ISO8859-1"),"UTF-8");  
                    }  
                }   
                // 设置图片的宽度  
                if(((String)item.getFieldName()).equals("imgWidth")){     
                   String imgWidth = item.getString();     
                   if(imgWidth != null && !"".equals(imgWidth)){  
                       this.imgWidth = Integer.parseInt(imgWidth);  
                   }  
                }    
                // 设置图片的高度  
                if(((String)item.getFieldName()).equals("imgHeight")){     
                   String imgHeight = item.getString();     
                   if(imgHeight != null && !"".equals(imgHeight)){  
                       this.imgHeight = Integer.parseInt(imgHeight);  
                   }  
                }  
                // 设置图片的边框  
                if(((String)item.getFieldName()).equals("imgBorder")){     
                    imgBorder = item.getString();    
                }   
                long fileSize = item.getSize();  
                if (!item.isFormField()) {                        
                    // 检查文件大小  
                    if (fileSize > MAX_SIZE) {  
                        out.println(alertMsg("上传文件大小超过限制!"));  
                        return;  
                    }  
                      
                    // 检查扩展名  
                    String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();  
                    if (!Arrays.asList(IMAGETYPES).contains(fileExt)) {  
                        out.println(alertMsg("上传图片扩展名是不允许的扩展名!"));  
                        return;  
                    }  
                    // 根据时间创建文件夹  
                    Date dateNow = commonBiz.getSysDate();
                    SimpleDateFormat folderNameFormat = new SimpleDateFormat("yyyyMMdd");  
                    String realPath = uploadPath + folderNameFormat.format(dateNow);  
                    File folder = new File(realPath);  
                    boolean flag = folder.exists();  
                    // 确认文件夹是否已经存在  
                    if(!flag){  
                        flag = folder.mkdir();  
                    }  
                    // 创建文件夹并上传图片  
                    if(flag){  
                        SimpleDateFormat fileNameFormat = new SimpleDateFormat("yyyyMMddHHmmss");                     
                        String newFileName = fileNameFormat.format(dateNow) + "_"+ new Random().nextInt(1000) + "." + fileExt;                         
                        File uploadedFile = new File(realPath, newFileName);                          
                        item.write(uploadedFile);  
                        resizeImg = uploadedFile.getPath();  
                        resizeImg = resizeImg.replaceAll("\\\\", "/");  
                        saveUrl += folderNameFormat.format(dateNow) + "/" + newFileName;                       
                    }else{  
                        System.out.println(" 文件夹创建失败，请确认磁盘没有写保护并且空件足够!");  
                    }     
                }  
            }  
              
            // 判断是否设置图片的最大宽度与高度  
            String max_width = this.getInitParameter("MAX_WIDTH");
            if(max_width != null && !"".equals(max_width)){  
                MAX_WIDTH = Integer.parseInt(max_width);  
            }
              
            if(imgTitle == null || "".equals(imgTitle)){  
                imgTitle = tempTitle;  
            }  
              
            // 判断是否要压缩图片  
            if(MAX_WIDTH != -1) {  
                // 压缩图片  
                ImageUtil.resizeImg(resizeImg, resizeImg, MAX_WIDTH);  
                  
                this.imgWidth = ImageUtil.ImgWidth;  
                this.imgHeight = ImageUtil.ImgHeight;
                  
                // 返回编辑器  
                out.println(insertEditor(id, saveUrl, imgTitle, imgWidth, imgHeight, imgBorder));  
            }else{  
                // 返回编辑器  
                out.println(insertEditor(id, saveUrl, imgTitle, imgWidth, imgHeight, imgBorder));  
            }                 
              
        } catch (FileUploadException e) {  
            e.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }finally{  
            out.flush();  
            out.close();  
            isFlag = false;  
        }  
  
    }  
   
    protected void doPost(HttpServletRequest request,  
            HttpServletResponse response) throws ServletException, IOException {  
        doGet(request, response);  
    }  
  
    /** 
     * 输出打印上传失败的JSON语句 
     *  
     * @param message    失败信息 
     *  
     * @return 页面上传失败的JSON语句 
     */  
    public String alertMsg(String message) {  
        StringBuffer sb = new StringBuffer("{\"error\":\"1\",\"message\":\"");  
        sb.append(message).append("\"}");  
        return sb.toString();  
    }  
      
    /** 
     * 输出插入图片至编辑器语句的脚本 
     *  
     * @param id        编辑器ID 
     *  
     * @param saveUrl   上传图片的浏览地址 
     *  
     * @param imgTitle   图片的提示信息 
     *  
     * @param imgWidth   设置图片的宽度 
     *  
     * @param imgHeight  设置图片的宽度 
     *  
     * @param imgBorder  设置图片的边框 
     *  
     * @return 插入图片至编辑器的脚本语句 
     */  
    public String insertEditor(String id, String saveUrl, String imgTitle,   
            int imgWidth, int imgHeight, String imgBorder){  
        StringBuffer sb = new StringBuffer("<script type=\"text/javascript\">");  
        sb.append("parent.KE.plugin[\"image\"].insert(\"").append(id).append("\",\"");  
        sb.append(saveUrl).append("\",\"").append(imgTitle).append("\",\"");  
        sb.append(imgWidth).append("\",\"").append(imgHeight).append("\",\"");  
        sb.append(imgBorder).append("\");");  
        sb.append("</script>");  
        return sb.toString();  
    }  
      
    /** 
     * 输出允许上传图片类型的数组 
     *  
     * @param filesType 允许上传的图片类型 
     *  
     * @return 允许上传图片类型 
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