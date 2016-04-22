package com.laungee.proj.common.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.taglib.TagUtils;
import org.apache.struts2.ServletActionContext;

import com.laungee.proj.common.model.TbPersonphotodetil;
import com.laungee.proj.common.web.PhotoCoverManager;

/**
 * 照片封面
 * @author Administrator
 *
 */
public class PhotoCoverTag extends TagSupport {
	// ID
	private String value;
	// 处理标签
	public int doStartTag(){
		try {
			HttpServletRequest request=ServletActionContext.getRequest();
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
			String img="";
			TbPersonphotodetil pd=PhotoCoverManager.getInstance().getCoverPhoto(value);
			if(pd!=null){
				//得到小图片
				String small=pd.getSmallphotoUrl();
				if(small!=null&&!"".equals(small)){
					img="<img src='"+basePath+small+"' class='fit129' width='128' height='128'>";
				}else {
					//得到大图片
					String imgUrl=pd.getPhotodetilUrl();
					if(imgUrl!=null&&!"".equals(imgUrl)){
						img="<img src='"+basePath+imgUrl+"' class='fit129' width='128' height='128'>";
					}else {
						img="<img src='"+basePath+"UI/images/no_photo_s.gif' class='fit129' width='128' height='128'>";
					}
				}
			}else {
				img="<img src='"+basePath+"UI/images/no_photo_s.gif' class='fit129' width='128' height='128'>";
			}
			TagUtils.getInstance().write(pageContext,img);
		} catch (Exception e) {
		}
		
		// 返回
		return SKIP_BODY;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
