package com.laungee.proj.common.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class UpdateImg {
	private Font font = null;// 添加字体的属性设置
	private Graphics2D g = null;
	private int fontsize = 0;
	private int x = 0;
	private int y = 0;
	private int width = 0;
	private Color color;	//字体颜色

	public UpdateImg(Color color) {
		this.color=color;
	}
	public static void main(String[] args) {
		UpdateImg img = new UpdateImg(new Color(001234));
		BufferedImage bufferImg = img.loadImageLocal("C:\\test\\test.jpg");
		int fontSize = 30;
		img.setFont("方正舒体", fontSize);
		String str = "北京农业科技大学大学大厦";
		int width = bufferImg.getWidth();
		int height = bufferImg.getHeight();
		// 计算绘图的位置
		int x = ((width - 100 - str.length() * fontSize)) / 2 + 90;
		if (str.length() * fontSize > x)
			x = 100;
		int y = (height + fontSize) / 2;
		Object[] objs = null;
		if (str.length() % 6 == 0) {
			objs = new Object[str.length() / 6];
			for (int i = 0; i < str.length() / 6; i++) {
				objs[i] = str.substring(i * 6, (i + 1) * 6);
			}
		} else {
			objs = new Object[str.length() / 6 + 1];
			for (int i = 0; i < str.length() / 6 + 1; i++) {
				if ((i + 1) * 6 > str.length()) {
					objs[i] = str.substring(i * 6);
				} else {
					objs[i] = str.substring(i * 6, (i + 1) * 6);
				}
			}
		}
		y -= ((objs.length - 1) * fontSize / 2 + 5);
		bufferImg = img.modifyImage(bufferImg, objs, x, y, false);
		img.writeImageLocal("C:\\test\\test1.jpg", bufferImg);
		// try {
		// img.createYinying();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	/**
	 * 导入本地图片到缓冲区
	 */
	public BufferedImage loadImageLocal(String imgName) {
		try {
			return ImageIO.read(new File(imgName));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * 导入网络图片到缓冲区
	 */
	public BufferedImage loadImageUrl(String imgName) {
		try {
			URL url = new URL(imgName);
			return ImageIO.read(url);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * 生成新图片到本地
	 */
	public void writeImageLocal(String newImage, BufferedImage img) {
		if (newImage != null && img != null) {
			try {
				File outputfile = new File(newImage);
				ImageIO.write(img, "JPG", outputfile);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * 设定文字的字体等
	 */
	public void setFont(String fontStyle, int fontSize) {
		this.fontsize = fontSize;
		this.font = new Font(fontStyle, Font.BOLD, fontSize);
	}

	/**
	 * 修改图片,返回修改后的图片缓冲区（只输出一行文本）
	 */
	public BufferedImage modifyImage(BufferedImage img, Object content, int x,
			int y) {
		try {
			int w = img.getWidth();
			int h = img.getHeight();
			g = img.createGraphics();
			if (this.font != null)
				g.setFont(this.font);
			// 验证输出位置的纵坐标和横坐标
			if (x >= w || y >= h) {
				this.x = w - this.fontsize + 2;
				this.y = h;
			} else {
				this.x = x;
				this.y = y;
			}

			/**
			 * 设置干扰符点
			 */
			java.util.Random ran = new java.util.Random();
			// for (int i = 0; i < 2120; i++) {
			// g.setColor(this.color);
			// int x1 = ran.nextInt(w);
			// int y1 = ran.nextInt(h);
			// g.drawLine(x1, y1, x1, y1);
			// }//end for
			int red = 0, green = 0, blue = 0;
			if (content != null) {
				g.setColor(this.color);
				g.drawString(content.toString(), this.x, this.y);
			}
			g.dispose();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return img;
	}

	/**
	 * 修改图片,返回修改后的图片缓冲区（输出多个文本段） xory：true表示将内容在一行中输出；false表示将内容多行输出
	 */
	public BufferedImage modifyImage(BufferedImage img, Object[] contentArr,
			int x, int y, boolean xory) {
		try {
			int w = img.getWidth();
			int h = img.getHeight();
			g = img.createGraphics();
			g.setBackground(Color.WHITE);
			g.setColor(this.color);
			if (this.font != null)
				g.setFont(this.font);
			// 验证输出位置的纵坐标和横坐标
			if (y >= h) {
				this.y = h;
			} else {
				this.y = y;
			}
			this.width=x;

			if (contentArr != null) {
				int arrlen = contentArr.length;
				if (xory) {
					for (int i = 0; i < arrlen; i++) {
						g.drawString(contentArr[i].toString(), this.x, this.y);
						this.x += contentArr[i].toString().length()
								* this.fontsize / 2 + 5;// 重新计算文本输出位置
					}
				} else {
					for (int i = 0; i < arrlen; i++) {
						g.setColor(this.color);
						//计算x的输出位置
						int k = 0;
						for (int n = 0; n < contentArr[i].toString().length(); n++) {
							byte[] b = (contentArr[i].toString().charAt(n) + "").getBytes();
							k = k + b.length;
						}
						int length=k/2;
						x = (this.width-length*this.fontsize)/2;
						g.drawString(contentArr[i].toString(), x, this.y);
						this.y += this.fontsize;// 重新计算文本输出位置
					}
				}
			}

			g.dispose();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return img;
	}

	private int ShiftNorth(int p, int distance) {
		return (p - distance);
	}

	private int ShiftSouth(int p, int distance) {
		return (p + distance);
	}

	private int ShiftEast(int p, int distance) {
		return (p + distance);
	}

	private int ShiftWest(int p, int distance) {
		return (p - distance);
	}
}
