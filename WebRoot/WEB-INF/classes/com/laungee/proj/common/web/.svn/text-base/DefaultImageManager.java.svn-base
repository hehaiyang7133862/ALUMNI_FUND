package com.laungee.proj.common.web;

import java.awt.Color;
import java.awt.image.BufferedImage;

import org.apache.struts2.ServletActionContext;

import com.laungee.proj.common.core.BaseManager;
import com.laungee.proj.common.util.StringUtils;
import com.laungee.proj.common.util.UpdateImg;

public class DefaultImageManager extends BaseManager {
	public DefaultImageManager(){}
	public static DefaultImageManager getInstance() {
		return new DefaultImageManager();
	}
	public String doDefaultImage(String imgName,String str) {
		//新图片地址
		String newFileName="UI/images/default/"+imgName+".jpg";
		//默认图片地址
		String defaultFileName="UI/images/doom.jpg";
		String realPath=ServletActionContext.getServletContext().getRealPath(defaultFileName);
		UpdateImg img = new UpdateImg(new Color(0,153,255));
		BufferedImage bufferImg = img
				.loadImageLocal(realPath);
		int fontSize = 40;
		img.setFont("方正舒体", fontSize);
		int k = 0;
		for (int i = 0; i < str.length(); i++) {
			byte[] b = (str.charAt(i) + "").getBytes(); // 每循环一次，将str里的值放入byte数组
			k = k + b.length;
		}
		int width = bufferImg.getWidth();
		int height = bufferImg.getHeight() - 90;
		// 计算绘图的位置
		int x=width;
		int y = (height + fontSize) / 2;
		Object[] objs = null;
		if (k % 12 == 0) {
			objs = new Object[k / 12];
			for (int i = 0; i < k / 12; i++) {
				objs[i] = StringUtils.substrByBytes(str,i * 12, (i + 1) * 12);
			}
		} else {
			objs = new Object[k / 12 + 1];
			for (int i = 0; i < k / 12 + 1; i++) {
				if ((i + 1) * 12 > k) {
					objs[i] = StringUtils.substrByBytes(str,i * 12,k);
				} else {
					objs[i] = StringUtils.substrByBytes(str,i * 12, (i + 1) * 12);
				}
			}
		}
		y -= ((objs.length - 1) * fontSize / 2 + 5);
		bufferImg = img.modifyImage(bufferImg, objs, x, y, false);
		img.writeImageLocal(ServletActionContext.getServletContext().getRealPath(newFileName), bufferImg);
		return newFileName;
	}
}
