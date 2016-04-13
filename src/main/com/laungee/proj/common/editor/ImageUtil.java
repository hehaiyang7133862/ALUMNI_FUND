package com.laungee.proj.common.editor;
import java.awt.Image;  
import java.awt.image.BufferedImage;  
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.IOException;  
  
import javax.imageio.ImageIO;  
  
import com.sun.image.codec.jpeg.JPEGCodec;  
import com.sun.image.codec.jpeg.JPEGImageEncoder;  
  
/** 
 * ��ͼƬ���д���ķ��� 
 *  
 * @author SENHUI 
 */  
public class ImageUtil {  
    
    public static int ImgWidth = -1;  
      
    public static int ImgHeight = -1;  
  
  
      
    /** 
     * ѹ��ͼƬ 
     *  
     * @param imgsrc 
     *            Դ�ļ� 
     * @param imgdist 
     *            Ŀ���ļ� 
     * @param widthdist 
     *            �� 
     * @param heightdist 
     *            �� 
     */  
    public static void resizeImg(String imgsrc, String imgdist,  
            int widthdist) {  
        try {  
            File srcfile = new File(imgsrc);  
            if (!srcfile.exists()) {  
                return;  
            }  
            Image src = ImageIO.read(srcfile);        
            ImgWidth = src.getWidth(null);  
            ImgHeight = src.getHeight(null);  
            if(ImgWidth > widthdist){
            	float imgH = ImgHeight+0f;
            	float imgW = ImgWidth+0f;
            	ImgHeight = (int)(widthdist*(imgH/imgW));
            	ImgWidth = widthdist;
            }
            BufferedImage tag = new BufferedImage(ImgWidth, ImgHeight,BufferedImage.TYPE_INT_RGB);      
              
            tag.getGraphics().drawImage(src.getScaledInstance(ImgWidth, ImgHeight,Image.SCALE_SMOOTH), 0, 0, null);  
            FileOutputStream out = new FileOutputStream(imgdist);  
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
            encoder.encode(tag);  
            out.close();  
        } catch (IOException ex) {  
            ex.printStackTrace();  
        }  
    }  
}  