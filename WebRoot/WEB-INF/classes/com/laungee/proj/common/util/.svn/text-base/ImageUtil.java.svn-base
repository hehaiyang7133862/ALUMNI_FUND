package com.laungee.proj.common.util;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.struts2.ServletActionContext;

/**
 * 图片处理类
 * 
 * @author hexiang
 * 
 */
public class ImageUtil {

	/**
	 * 缩放图片
	 * 
	 * @param file
	 *            图片文件
	 * @param zoomWidth
	 *            要缩放的宽
	 * @param zoomHeight
	 *            要缩放的高
	 * @throws Exception
	 */
	public static void zoomImg(File file, int zoomWidth, int zoomHeight)
			throws Exception {
		if (!file.exists()) {
			return;
		}
		BufferedImage sourceImg = javax.imageio.ImageIO.read(file);
		// 根据宽高新建画布
		BufferedImage src = new BufferedImage(zoomWidth, zoomHeight,
				BufferedImage.TYPE_INT_RGB);
		// 根据宽高重绘图像
		src.getGraphics().drawImage(
				sourceImg.getScaledInstance(zoomWidth, zoomHeight,
						Image.SCALE_SMOOTH), 0, 0, null);
		// 输出图像
		ImageIO.write(src, "JPEG", file);
		// 释放资源
		src.getGraphics().dispose();
	}
	
	/**
	 * 缩放图片（输出到新文件）
	 * 
	 * @param file
	 *            图片文件
	 * @param zoomWidth
	 *            要缩放的宽
	 * @param zoomHeight
	 *            要缩放的高
	 * @param newFile
	 * 			  输出到新文件 
	 * @throws Exception
	 */
	public static void zoomImg(File file, int zoomWidth, int zoomHeight,File newFile)
			throws Exception {
		if (!file.exists()) {
			return;
		}
		BufferedImage sourceImg = javax.imageio.ImageIO.read(file);
		// 根据宽高新建画布
		BufferedImage src = new BufferedImage(zoomWidth, zoomHeight,
				BufferedImage.TYPE_INT_RGB);
		// 根据宽高重绘图像
		src.getGraphics().drawImage(
				sourceImg.getScaledInstance(zoomWidth, zoomHeight,
						Image.SCALE_SMOOTH), 0, 0, null);
		// 输出图像
		ImageIO.write(src, "JPEG", newFile);
		// 释放资源
		src.getGraphics().dispose();
	}

	/**
	 * 等比缩放图片
	 * 
	 * @param file
	 *            图片文件
	 * @param zoomWidth
	 *            固定宽度，等比缩放
	 * @throws Exception
	 */
	public static void zoomImgByWidth(File file, int zoomWidth)
			throws Exception {
		if (!file.exists()) {
			return;
		}
		BufferedImage sourceImg = javax.imageio.ImageIO.read(file);
		int width = sourceImg.getWidth(); // 原文件宽度
		int height = sourceImg.getHeight(); // 原文件高度
		DecimalFormat df = new DecimalFormat("0.000");
		if (width > zoomWidth) {
			double proportion = Double.parseDouble(df
					.format((width - zoomWidth) / new Double(width)));// 计算等比
			width = zoomWidth;
			df = new DecimalFormat("0");
			height = Integer.parseInt(df.format(height - height * proportion));
			// 根据计算好的宽高新建画布
			BufferedImage src = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			// 根据宽高重绘图像
			src.getGraphics().drawImage(
					sourceImg.getScaledInstance(width, height,
							Image.SCALE_SMOOTH), 0, 0, null);
			// 输出图像
			ImageIO.write(src, "JPEG", file);
			// 释放资源
			src.getGraphics().dispose();
		}
	}
	
	/**
	 * 等比缩放图片（输出到新文件）
	 * 
	 * @param file
	 *            图片文件
	 * @param zoomWidth
	 *            固定宽度，等比缩放
	 * @param newFile
	 * 			  输出到新文件
	 * @throws Exception
	 */
	public static void zoomImgByWidth(File file, int zoomWidth,File newFile)
			throws Exception {
		if (!file.exists()) {
			return;
		}
		BufferedImage sourceImg = javax.imageio.ImageIO.read(file);
		int width = sourceImg.getWidth(); // 原文件宽度
		int height = sourceImg.getHeight(); // 原文件高度
		DecimalFormat df = new DecimalFormat("0.000");
		if (width > zoomWidth) {
			double proportion = Double.parseDouble(df
					.format((width - zoomWidth) / new Double(width)));// 计算等比
			width = zoomWidth;
			df = new DecimalFormat("0");
			height = Integer.parseInt(df.format(height - height * proportion));
			// 根据计算好的宽高新建画布
			BufferedImage src = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			// 根据宽高重绘图像
			src.getGraphics().drawImage(
					sourceImg.getScaledInstance(width, height,
							Image.SCALE_SMOOTH), 0, 0, null);
			// 输出图像
			ImageIO.write(src, "JPEG", newFile);
			// 释放资源
			src.getGraphics().dispose();
		}
	}

	/**
	 * 等比缩放图片
	 * 
	 * @param file
	 *            图片文件
	 * @param zoomHeight
	 *            固定高度，等比缩放
	 * @throws Exception
	 */
	public static void zoomImgByHeight(File file, int zoomHeight)
			throws Exception {
		if (!file.exists()) {
			return;
		}
		BufferedImage sourceImg = javax.imageio.ImageIO.read(file);
		int width = sourceImg.getWidth(); // 原文件宽度
		int height = sourceImg.getHeight(); // 原文件高度
		DecimalFormat df = new DecimalFormat("0.000");
		if (height > zoomHeight) {
			double proportion = Double.parseDouble(df
					.format((height - zoomHeight) / new Double(height)));
			height = zoomHeight;
			df = new DecimalFormat("0");
			width = Integer.parseInt(df.format(width - width * proportion));
			// 根据计算好的宽高新建画布
			BufferedImage src = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			// 根据宽高重绘图像
			src.getGraphics().drawImage(
					sourceImg.getScaledInstance(width, height,
							Image.SCALE_SMOOTH), 0, 0, null);
			// 输出图像
			ImageIO.write(src, "JPEG", file);
			// 释放资源
			src.getGraphics().dispose();
		}
	}
	
	/**
	 * 等比缩放图片（输出到新文件）
	 * 
	 * @param file
	 *            图片文件
	 * @param zoomHeight
	 *            固定高度，等比缩放
	 * @param newFile
	 * 			  输出到新文件
	 * @throws Exception
	 */
	public static void zoomImgByHeight(File file, int zoomHeight,File newFile)
			throws Exception {
		if (!file.exists()) {
			return;
		}
		BufferedImage sourceImg = javax.imageio.ImageIO.read(file);
		int width = sourceImg.getWidth(); // 原文件宽度
		int height = sourceImg.getHeight(); // 原文件高度
		DecimalFormat df = new DecimalFormat("0.000");
		if (height > zoomHeight) {
			double proportion = Double.parseDouble(df
					.format((height - zoomHeight) / new Double(height)));
			height = zoomHeight;
			df = new DecimalFormat("0");
			width = Integer.parseInt(df.format(width - width * proportion));
			// 根据计算好的宽高新建画布
			BufferedImage src = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			// 根据宽高重绘图像
			src.getGraphics().drawImage(
					sourceImg.getScaledInstance(width, height,
							Image.SCALE_SMOOTH), 0, 0, null);
			// 输出图像
			ImageIO.write(src, "JPEG", newFile);
			// 释放资源
			src.getGraphics().dispose();
		}
	}

	/**
	 * 裁剪图片，将新图片覆盖原文件
	 * 
	 * @param file
	 *            图片文件
	 * @param x
	 *            左上角横坐标
	 * @param y
	 *            左上角竖坐标
	 * @param width
	 *            裁剪的宽度
	 * @param height
	 *            裁剪的高度
	 * @throws IOException
	 */
	public static void cut(File file, int x, int y, int width, int height)
			throws IOException {

		FileInputStream is = null;
		ImageInputStream iis = null;

		try {
			// 读取图片文件
			is = new FileInputStream(file);
			Iterator<ImageReader> it = ImageIO
					.getImageReadersByFormatName("jpg");
			ImageReader reader = it.next();
			// 获取图片流
			iis = ImageIO.createImageInputStream(is);
			reader.setInput(iis, true);
			ImageReadParam param = reader.getDefaultReadParam();

			/*
			 * 通过 Rectangle 对象 的左上顶点的坐标（x，y）、宽度和高度可以定义这个区域。
			 */
			Rectangle rect = new Rectangle(x, y, width, height);
			param.setSourceRegion(rect);

			BufferedImage bi = reader.read(0, param);

			// 覆盖图片
			ImageIO.write(bi, "JPEG", file);
		} finally {
			if (is != null)
				is.close();
			if (iis != null)
				iis.close();
		}
	}

	/**
	 * 裁剪图片，将新图片保存新路径
	 * 
	 * @param file
	 *            图片文件
	 * @param x
	 *            左上角横坐标
	 * @param y
	 *            左上角竖坐标
	 * @param width
	 *            裁剪的宽度
	 * @param height
	 *            裁剪的高度
	 * @param path
	 *            图片新路径
	 * @throws IOException
	 */
	public static void cut(File file, int x, int y, int width, int height,
			String path) throws IOException {

		FileInputStream is = null;
		ImageInputStream iis = null;

		try {
			// 读取图片文件
			is = new FileInputStream(file);
			Iterator<ImageReader> it = ImageIO
					.getImageReadersByFormatName("jpg");
			ImageReader reader = it.next();
			// 获取图片流
			iis = ImageIO.createImageInputStream(is);
			reader.setInput(iis, true);
			ImageReadParam param = reader.getDefaultReadParam();

			/**//*
				 * 通过 Rectangle 对象 的左上顶点的坐标（x，y）、宽度和高度可以定义这个区域。
				 */
			Rectangle rect = new Rectangle(x, y, width, height);
			param.setSourceRegion(rect);

			BufferedImage bi = reader.read(0, param);

			// 保存新图片
			ImageIO.write(bi, "JPEG", new File(path));
		} finally {
			if (is != null)
				is.close();
			if (iis != null)
				iis.close();
		}
	}

	/**
	 * 修改图片，写入单行文本在图片上
	 * 
	 * @param img
	 *            图片文件
	 * @param content
	 *            要写入内容
	 * @param x
	 *            内容显示在图片位置——横坐标
	 * @param y
	 *            内容显示在图片位置——纵坐标
	 * @param color
	 *            字体颜色
	 * @return 修改后的图片
	 */
	public static BufferedImage modifyImage(BufferedImage img, Object content,
			int x, int y, Color color) {
		UpdateImg ui = new UpdateImg(color);
		return ui.modifyImage(img, content, x, y);
	}

	/**
	 * 修改图片，写入单行文本在图片上(含字体)
	 * 
	 * @param img
	 *            图片文件
	 * @param content
	 *            要写入内容
	 * @param x
	 *            内容显示在图片位置——横坐标
	 * @param y
	 *            内容显示在图片位置——纵坐标
	 * @param color
	 *            字体颜色
	 * @param fontStyle
	 *            文字字体 例：黑体
	 * @param fontSize
	 *            文字大小
	 * @return 修改后的图片
	 */
	public static BufferedImage modifyImage(BufferedImage img, Object content,
			int x, int y, Color color, String fontStyle, int fontSize) {
		UpdateImg ui = new UpdateImg(color);
		ui.setFont(fontStyle, fontSize);
		return ui.modifyImage(img, content, x, y);
	}

	/**
	 * 修改图片，写入多行文本在图片上
	 * 
	 * @param img
	 *            图片文件
	 * @param contentArr
	 *            要写入内容集合
	 * @param x
	 *            内容显示在图片位置——横坐标
	 * @param y
	 *            内容显示在图片位置——纵坐标
	 * @param xory
	 *            是否多行显示，真为多行，假为单行
	 * @param color
	 *            字体颜色
	 * @return 修改后的图片
	 */
	public static BufferedImage modifyImage(BufferedImage img,
			Object[] contentArr, int x, int y, boolean xory, Color color) {
		UpdateImg ui = new UpdateImg(color);
		return ui.modifyImage(img, contentArr, x, y, xory);
	}

	/**
	 * 修改图片，写入多行文本在图片上(含字体)
	 * 
	 * @param img
	 *            图片文件
	 * @param contentArr
	 *            要写入内容集合
	 * @param x
	 *            内容显示在图片位置——横坐标
	 * @param y
	 *            内容显示在图片位置——纵坐标
	 * @param xory
	 *            是否多行显示，真为多行，假为单行
	 * @param color
	 *            字体颜色
	 * @param fontStyle
	 *            文字字体 例：黑体
	 * @param fontSize
	 *            文字大小
	 * @return 修改后的图片
	 */
	public static BufferedImage modifyImage(BufferedImage img,
			Object[] contentArr, int x, int y, boolean xory, Color color,
			String fontStyle, int fontSize) {
		UpdateImg ui = new UpdateImg(color);
		ui.setFont(fontStyle, fontSize);
		return ui.modifyImage(img, contentArr, x, y, xory);
	}

	/**
	 * 指定长宽裁剪图片中间图片
	 * 
	 * @param file
	 *            文件
	 * @param cutPath
	 *            文件裁剪后存放路径
	 * @param cutWidth
	 *            指定裁剪的宽
	 * @param cutHeight
	 *            指定裁剪的高
	 */
	public static void modifyImageSize(File file, String cutPath, int cutWidth,
			int cutHeight) {
		if (!file.exists()) {
			return;
		}
		BufferedImage img = null;
		try {
			img = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int w = img.getWidth(); // 宽
		int h = img.getHeight(); // 高
		// 如果指定裁剪的宽或高大于本身图片的宽或高，则不裁剪
		if (cutWidth > w || cutHeight > h) {
			return;
		}
		int x = (w - cutWidth) / 2;
		int y = (h - cutHeight) / 2;
		try {
			// 裁剪图片
			ImageUtil.cut(file, x, y, cutWidth, cutHeight, cutPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 先根据指定的长度缩小图片，然后根据指定长宽裁剪图片中间图片
	 * 
	 * @param file
	 *            文件
	 * @param cutPath
	 *            文件裁剪后存放路径
	 * @param width
	 *            指定缩小的宽
	 * @param height
	 *            指定缩小的宽
	 * @param cutWidth
	 *            指定裁剪的宽
	 * @param cutHeight
	 *            指定裁剪的高
	 */
	public static void modifyImageSize(File file, String cutPath, int width,
			int height, int cutWidth, int cutHeight) {
		// 缩小图片
		try {
			ImageUtil.zoomImg(file, width, height);
			// 如果指定裁剪的宽或高大于本身图片的宽或高，则不裁剪
			if (cutWidth > width || cutHeight > height) {
				BufferedImage im = ImageIO.read(file);
				ImageIO.write(im, "JPG", new File(cutPath));
			} else {
				int x = (width - cutWidth) / 2;
				int y = (height - cutHeight) / 2;
				ImageUtil.cut(file, x, y, cutWidth, cutHeight, cutPath);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 根据指定宽按比例缩小，然后根据指定长宽裁剪图片中间图片
	 * 
	 * @param file
	 *            文件
	 * @param cutPath
	 *            文件裁剪后存放路径
	 * @param width
	 *            指定缩小的宽
	 * @param cutWidth
	 *            指定裁剪的宽
	 * @param cutHeight
	 *            指定裁剪的高
	 */
	public static void modifyImageSizeByWidth(File file, String cutPath,
			int width, int cutWidth, int cutHeight) {
		// 缩小图片
		try {
			ImageUtil.zoomImgByWidth(file, width);
			// 如果指定裁剪的宽或高大于本身图片的宽或高，则不裁剪
			BufferedImage im = ImageIO.read(file);
			int w = im.getWidth();
			int h = im.getHeight();
			if (cutWidth > w || cutHeight > h) {
				ImageIO.write(im, "JPG", new File(cutPath));
			} else {
				int x = (w - cutWidth) / 2;
				int y = (h - cutHeight) / 2;
				ImageUtil.cut(file, x, y, cutWidth, cutHeight, cutPath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据指定宽按比例缩小，然后根据指定长宽裁剪图片中间图片
	 * 
	 * @param file
	 *            文件
	 * @param cutPath
	 *            文件裁剪后存放路径
	 * @param width
	 *            指定缩小的宽
	 * @param cutWidth
	 *            指定裁剪的宽
	 * @param cutHeight
	 *            指定裁剪的高
	 */
	public static void modifyImageSizeByHeight(File file, String cutPath,
			int height, int cutWidth, int cutHeight) {
		// 缩小图片
		try {
			ImageUtil.zoomImgByHeight(file, height);
			// 如果指定裁剪的宽或高大于本身图片的宽或高，则不裁剪
			BufferedImage im = ImageIO.read(file);
			int w = im.getWidth();
			int h = im.getHeight();
			if (cutWidth > w || cutHeight > h) {
				ImageIO.write(im, "JPG", new File(cutPath));
			} else {
				int x = (w - cutWidth) / 2;
				int y = (h - cutHeight) / 2;
				ImageUtil.cut(file, x, y, cutWidth, cutHeight, cutPath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
