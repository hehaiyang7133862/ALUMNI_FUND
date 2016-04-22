package com.laungee.proj.common.util;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.util.Date;
import java.util.Properties;

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

import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;
/** */
/**
 * 简单邮件（不带附件的邮件）发送器
 */
public class SimpleMailSender {
	/** */
	/**
	 * 以文本格式发送邮件
	 * 
	 * @param mailInfo
	 *            待发送的邮件的信息
	 * @throws UnsupportedEncodingException
	 */
	public boolean sendTextMail(MailSenderInfo mailInfo)
			throws UnsupportedEncodingException {
		// // 判断是否需要身份认证
		// MyAuthenticator authenticator = null;
		// Properties pro = mailInfo.getProperties();
		// if (mailInfo.isValidate()) {
		// // 如果需要身份认证，则创建一个密码验证器
		// authenticator = new
		// MyAuthenticator(mailInfo.getUserName(),mailInfo.getPassword());
		// }
		// // 根据邮件会话属性和密码验证器构造一个发送邮件的session
		// Session sendMailSession = Session.getDefaultInstance(pro,
		// authenticator);
		// try {
		// // 根据session创建一个邮件消息
		// MimeMessage mailMessage=new MimeMessage(sendMailSession);
		// // 创建邮件发送者地址
		// Address from = new InternetAddress(mailInfo.getFromAddress());
		// // 设置邮件消息的发送者
		// mailMessage.setFrom(from);
		// // 创建邮件的接收者地址，并设置到邮件消息中
		// Address to = new InternetAddress(mailInfo.getToAddress());
		// mailMessage.setRecipient(Message.RecipientType.TO, to);
		// // 设置邮件消息的主题
		// mailMessage.setSubject(mailInfo.getSubject());
		// // 设置邮件消息发送的时间
		// mailMessage.setSentDate(new Date());
		// // 设置邮件消息的主要内容
		// String mailContent = mailInfo.getContent();
		// mailMessage.setText(mailContent);
		// mailMessage.setReplyTo(InternetAddress.parse("hexiang@laungee.com"));
		// //设置参数
		// MailcapCommandMap mc = (MailcapCommandMap)
		// CommandMap.getDefaultCommandMap();
		// mc.addMailcap("text/html;;
		// x-java-content-handler=com.sun.mail.handlers.text_html");
		// mc.addMailcap("text/xml;;
		// x-java-content-handler=com.sun.mail.handlers.text_xml");
		// mc.addMailcap("text/plain;;
		// x-java-content-handler=com.sun.mail.handlers.text_plain");
		// mc.addMailcap("multipart/*;;
		// x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
		// mc.addMailcap("message/rfc822;;
		// x-java-content-handler=com.sun.mail.handlers.message_rfc822");
		// CommandMap.setDefaultCommandMap(mc);
		// // 发送邮件
		// // Transport.send(mailMessage);
		// Transport tran=sendMailSession.getTransport("smtp");
		// tran.connect();
		// mailMessage.saveChanges();
		// tran.sendMessage(mailMessage, mailMessage.getAllRecipients());
		//	        
		// return true;
		// } catch (MessagingException ex) {
		// ex.printStackTrace();
		// }
		// return false;
		// return sendHtmlMail(mailInfo);
		try {
			SimpleEmail email = new SimpleEmail();
			email.setHostName(mailInfo.getMailServerHost());
			email.setAuthentication(mailInfo.getUserName(), mailInfo
					.getPassword());
			email.setCharset("UTF-8");
			email.addTo(mailInfo.getToAddress());
			

			//认证
			Session mi=email.getMailSession();
			InetAddress ia=InetAddress.getByName(mailInfo.getMailServerHost());
			PasswordAuthentication pa= mi.requestPasswordAuthentication(ia, 25, "", "", "");

			
			email.setFrom(mailInfo.getFromAddress(), mailInfo.getFromName());
			email.setSubject(mailInfo.getSubject());
			email.setMsg(mailInfo.getContent());
			email.send();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	/** */
	/**
	 * 以HTML格式发送邮件
	 * 
	 * @param mailInfo
	 *            待发送的邮件信息
	 * @throws UnsupportedEncodingException
	 */
	public static boolean sendHtmlMail(MailSenderInfo mailInfo)
			throws UnsupportedEncodingException {
		// // 判断是否需要身份认证
		// MyAuthenticator authenticator = null;
		// Properties pro = mailInfo.getProperties();
		// // 如果需要身份认证，则创建一个密码验证器
		// if (mailInfo.isValidate()) {
		// authenticator = new
		// MyAuthenticator(mailInfo.getUserName(),mailInfo.getPassword());
		// }
		// // 根据邮件会话属性和密码验证器构造一个发送邮件的session
		// Session sendMailSession = Session.getDefaultInstance(pro,
		// authenticator);
		// try {
		// MimeMessage mailMessage=new MimeMessage(sendMailSession);
		// // 根据session创建一个邮件消息
		// // Message mailMessage = new MimeMessage(sendMailSession);
		// // 创建邮件发送者地址
		// Address from = new InternetAddress(mailInfo.getFromAddress());
		// // 设置邮件消息的发送者
		// mailMessage.setFrom(from);
		// // 创建邮件的接收者地址，并设置到邮件消息中
		// Address to = new InternetAddress(mailInfo.getToAddress());
		// // Message.RecipientType.TO属性表示接收者的类型为TO
		// mailMessage.setRecipient(Message.RecipientType.TO, to);
		//			
		// // 设置邮件消息的主题
		// mailMessage.setSubject(mailInfo.getSubject());
		// // 设置邮件消息发送的时间
		// mailMessage.setSentDate(new Date());
		// // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
		// Multipart mainPart = new MimeMultipart();
		// // 创建一个包含HTML内容的MimeBodyPart
		// BodyPart html = new MimeBodyPart();
		// // 设置HTML内容
		// html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
		// mainPart.addBodyPart(html);
		// // 将MiniMultipart对象设置为邮件内容
		// mailMessage.setContent(mainPart);
		//			
		// MailcapCommandMap mc = (MailcapCommandMap)
		// CommandMap.getDefaultCommandMap();
		// mc.addMailcap("text/html;;
		// x-java-content-handler=com.sun.mail.handlers.text_html");
		// mc.addMailcap("text/xml;;
		// x-java-content-handler=com.sun.mail.handlers.text_xml");
		// mc.addMailcap("text/plain;;
		// x-java-content-handler=com.sun.mail.handlers.text_plain");
		// mc.addMailcap("multipart/*;;
		// x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
		// mc.addMailcap("message/rfc822;;
		// x-java-content-handler=com.sun.mail.handlers.message_rfc822");
		// CommandMap.setDefaultCommandMap(mc);
		//
		// // 发送邮件
		// // Transport.send(mailMessage);
		// Transport tran=sendMailSession.getTransport("smtp");
		// tran.connect();
		// mailMessage.saveChanges();
		// tran.sendMessage(mailMessage, mailMessage.getAllRecipients());
		//	        
		// return true;
		// } catch (MessagingException ex) {
		// ex.printStackTrace();
		// }
		// return false;
		try {
			HtmlEmail email = new HtmlEmail();
			email.setHostName(mailInfo.getMailServerHost());
			email.setAuthentication(mailInfo.getUserName(), mailInfo
					.getPassword());
			email.setCharset("UTF-8");
			email.addTo(mailInfo.getToAddress());
			
			//认证
			Session mi=email.getMailSession();
			InetAddress ia=InetAddress.getByName(mailInfo.getMailServerHost());
			PasswordAuthentication pa= mi.requestPasswordAuthentication(ia, 25, "", "", "");

			
			email.setFrom(mailInfo.getFromAddress(), mailInfo.getFromName());
			email.setSubject(mailInfo.getSubject());
			email.setHtmlMsg(mailInfo.getContent());
			email.send();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
//	public static boolean sendHtmlMail(MailSenderInfo mailInfo) throws Exception {
//		boolean flag=false;
//		try {
//
//			Properties p = System.getProperties();
//			p.put("mail.smtp.host", mailInfo.getMailServerHost());
//			p.put("mail.smtp.port", "25");
//			p.put("mail.smtp.auth", "f");
//			Session session = Session.getInstance(p, null);
//			session.setDebug(true);
//
//			Message mimeMessage = new MimeMessage(session);
//			mimeMessage.setFrom(new InternetAddress(mailInfo.getFromAddress(),
//					mailInfo.getFromName()));//可发匿名信
//			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(
//					mailInfo.getToAddress()));
//			mimeMessage.setSubject(mailInfo.getSubject());
//			mimeMessage.setHeader("X-Mailer", "JavaMail");
//			mimeMessage.setSentDate(new Date());
//
//			Multipart mimeMultipart = new MimeMultipart();
//
//			//内容部分.
//			BodyPart contentBodyPart = new MimeBodyPart();
//			contentBodyPart.setContent(mailInfo.getContent(),
//					"text/html; charset=utf-8");
//			mimeMultipart.addBodyPart(contentBodyPart);
//
//			//附件部分
//			//        if (affixFileList != null && affixFileList.size() > 0)
//			//        {
//			//            BodyPart fileBodyPart = null;
//			//            String filePath = null;
//			//            FileDataSource fileDataSource = null;
//			//            for (int i = 0; i < affixFileList.size(); ++i)
//			//            {
//			//                filePath = (String) affixFileList.get(i);
//			//                fileBodyPart = new MimeBodyPart();
//			//                fileDataSource = new FileDataSource(filePath);
//			//                fileBodyPart.setDataHandler(new DataHandler(fileDataSource));
//			//                fileBodyPart.setFileName(MimeUtility.encodeWord(fileDataSource.
//			//                    getName(), "GB2312", null));
//			//                mimeMultipart.addBodyPart(fileBodyPart);
//			//            }
//			//        }
//
//			mimeMessage.setContent(mimeMultipart);
//			mimeMessage.setSentDate(new Date());
//			mimeMessage.saveChanges();
//
//			//发送
//			Transport.send(mimeMessage);
//			//发送成功
//			flag=true;
//		} catch (Exception e) {
//			// TODO: handle exception
//			flag=false;
//		}
//		return flag;
//	}
}