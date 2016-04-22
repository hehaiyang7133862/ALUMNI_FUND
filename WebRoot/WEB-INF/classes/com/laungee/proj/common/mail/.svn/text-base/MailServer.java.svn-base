package com.laungee.proj.common.mail;

import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.laungee.proj.common.web.FieldManager;

public class MailServer {
	// 邮件参数
	private static Map config=null;
	//此段代码用来发送普通电子邮件
	public static boolean sendMail(MailDto bean) {
		try{
			Properties props = new Properties(); // 获取系统环境
			Authenticator auth = new EmailAutherticator(bean.getMailaddress(), bean.getMailpass()); // 进行邮件服务器用户认证
			props.put("mail.smtp.host", bean.getMailhost());
			props.put("mail.smtp.auth", "true");
			Session session = Session.getDefaultInstance(props, auth);
	
			// 设置session,和邮件服务器进行通讯。
			MimeMessage message = new MimeMessage(session);
			
			message.setSubject(bean.getSubject(),"UTF-8"); // 设置邮件主题
			
	        Multipart mp = new MimeMultipart("related");// related意味着可以发送html格式的邮件 
	        BodyPart bodyPart = new MimeBodyPart();// 正文  
	        bodyPart.setDataHandler(new DataHandler(bean.getBody(),"text/html;charset=UTF-8"));// 网页格式  
	        mp.addBodyPart(bodyPart);  
			message.setContent(mp); // 设置邮件正文
			
			Address address = new InternetAddress(bean.getMailaddress(),bean.getMailperson(),"UTF-8");
			message.setFrom(address); // 设置邮件发送者的地址
			
			Address toAddress = new InternetAddress(bean.getSto()); // 设置邮件接收方的地址
			message.addRecipient(Message.RecipientType.TO, toAddress);
	
			Transport.send(message); // 发送邮件
			
			return true;
		}catch(Exception e){
			
		}
		
		return false;
	}

	//用来进行服务器对用户的认证
	private static class EmailAutherticator extends Authenticator {
		private String username;
		private String password;

		public EmailAutherticator() {
			super();
		}

		public EmailAutherticator(String user, String pwd) {
			super();
			username = user;
			password = pwd;
		}

		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
		}
	}
	// 获取配置文件
	public static Map getConfig() {
		try {
			if(null==config){
				FieldManager manager=FieldManager.getInstance();
				config=manager.findValue("EMAIL");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return config;
	}
	public static void setConfig(Map config) {
		MailServer.config = config;
	}
}
