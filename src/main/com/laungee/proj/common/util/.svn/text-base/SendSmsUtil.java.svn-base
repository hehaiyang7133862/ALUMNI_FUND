/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laungee.proj.common.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import edu.sufe.sms.api.ReturnCode;
import edu.sufe.sms.api.SmsApi;
import edu.sufe.sms.api.SmsApiImpl;

/**
 *
 * @author hexiang
 */
public class SendSmsUtil {
	
	
	/**
	 * 财大发�?短信接口
	 * @param phone
	 * @param content
	 * @return
	 */
	public String send(String phone,String content){
		String result="";
		SmsApi service = new SmsApiImpl("http://sms.sufe.edu.cn", "oa", "11");
        Set<String> receipt = new HashSet<String>();
        receipt.add(phone);
        ReturnCode rcd=service.sendMessage(receipt, content);
        if(rcd==ReturnCode.OK){
        	result="1,1,1";
        }
        return result;
	}
	/**
	 * 发�?短信
	 * @param uri 短信API
	 * @param user 短信服务用户
	 * @param pass 短信服务密码
	 * @param phone 手机号码
	 * @param content 发�?内容
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	public String send(String uri, String user, String pass, String phone,
			String content) throws HttpException, IOException {
		HttpClient client = new HttpClient();
		String url = uri;
		// 构建URL
		PostMethod post = new UTF8PostMethod(url);
		NameValuePair[] data = { new NameValuePair("uid", user),
				new NameValuePair("pwd", pass),
				new NameValuePair("mobs", phone),
				new NameValuePair("msg", content) };
		post.setRequestBody(data);
		// 执行发�?
		client.executeMethod(post);
		InputStream iStream = post.getResponseBodyAsStream();
		//字符转码
		String contentString = convertStreamToString(iStream);
		post.releaseConnection();
		return contentString;
	}
	
	/**
	 * 发�?短信，参数为手机号与短信内容
	 * 
	 * @param request
	 *            页面对象
	 * @param phone
	 *            手机号码
	 * @param content
	 *            发�?内容
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public String send(HttpServletRequest request,String phone,String content) throws HttpException, IOException {
        //读取配置文件
		String url="";
		String parm="";
		Properties pro = new Properties();
		try {
			String config = request.getRealPath("/sms.properties");
			InputStream input=new FileInputStream(config);
			//加载配置文件
			pro.load(input);
			input.close();
			if(pro!=null){
				url=pro.getProperty("sms.url");
				parm=pro.getProperty("sms.parm");
			}
		}catch(IOException e){
			
		}
		if(url==null||"".equals(url)||parm==null||"".equals(parm)){
			return null;
		}
		HttpClient client = new HttpClient();
		//构建URL
		PostMethod post=new UTF8PostMethod(url);
		//参数
		String[] parm_s=parm.split(",");
		NameValuePair[] data=new NameValuePair[parm_s.length];
		for (int i = 0; i < data.length; i++) {
			String temp=parm_s[i];
			if(i<=data.length-3){
				data[i]=new NameValuePair(temp,pro.getProperty(temp));
			}else if(i==data.length-2){
				//手机号码
				data[i]=new NameValuePair(temp,phone);
			}else if(i==data.length-1){
				//发�?内容
				data[i]=new NameValuePair(temp,content);
			}
		}
		post.setRequestBody(data);
		//执行发�?
		client.executeMethod(post);
		InputStream iStream = post.getResponseBodyAsStream();
		String contentString = convertStreamToString(iStream);
		post.releaseConnection();
//		HttpClient client = new HttpClient();
//        String url="http://qmsg2.mdao.com/sendSms.do?entid=8000947";
//        String user="admin";
//        String pass="lanju0222";
//        //构建URL
//        PostMethod post=new UTF8PostMethod(url);
//        NameValuePair[] data = {
//                new NameValuePair("uid", user),
//                new NameValuePair("pwd", pass),
//                new NameValuePair("mobs", phone),
//                new NameValuePair("msg", content)};
//        post.setRequestBody(data);
//        //执行发�?
//        client.executeMethod(post);
//        InputStream iStream = post.getResponseBodyAsStream();
//        String contentString = convertStreamToString(iStream);
//        post.releaseConnection();
        return contentString;
    }
    
    private String convertStreamToString(InputStream is) throws UnsupportedEncodingException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "gbk"));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                if(line!=null&&!"".equals(line)){
                	sb.append(line+",");
                }
            }
        } catch (IOException e) {
            
        }
        return sb.toString();
    }
    
    /**
     * 获得加密后的密码
     * @return
     */
    public String getEncodePwd(String pwd){
    	String encode="";
    	String url="http://sms.client.com.cn/EN.aspx?DLMM="+pwd;
    	encode = getURLResult(url);
    	return encode;
    }
    
    private static String getURLResult(String url) {
		String result = "";
		URL getUrl = null;
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		String lines = "";
		try {
			getUrl = new URL(url);
			connection = (HttpURLConnection) getUrl.openConnection();
			connection.setRequestProperty("Charset", "UTF-8");
			connection.connect();
			reader = new BufferedReader(new InputStreamReader(connection
					.getInputStream()));
			while ((lines = reader.readLine()) != null)
				result = (new StringBuilder(String.valueOf(result))).append(
						lines).toString();
			reader.close();
			connection.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
    
    public static boolean sendSMS(String phone, String content) {
//		String url = "http://sms.client.com.cn/sm.aspx";
    	String url="http://sms.client.com.cn/EN.aspx?DLMM=123xyh456";
//		String DLZH = "swuxyh";
//		String DLMM = "MZT76ahRQUacw7G58uLEvg==";
		String result = "";
//		Properties pro = new Properties();
//		try {
//		url += "?LX=0&DLZH=" + DLZH + "&DLMM=" + DLMM + "&SJHM=" + phone
//		+ "&XXNR=" + java.net.URLEncoder.encode(content,"UTF-8")+ "&FSFS=1";
		result = getURLResult(url);
			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		System.out.println("短信剩余:" + result);
		return false;
	}
}
