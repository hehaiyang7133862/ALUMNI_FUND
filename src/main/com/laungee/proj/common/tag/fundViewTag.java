package com.laungee.proj.common.tag;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.taglib.TagUtils;

import com.laungee.proj.common.biz.ICommonBiz;
import com.laungee.proj.common.model.TbFoundation;
import com.laungee.proj.common.util.SpringUtil;
import com.laungee.proj.common.util.StringUtils;

public class fundViewTag  extends TagSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long value;

	@Override
	public int doStartTag() throws JspException {
		String text="";
		//数字格式化
//		NumberFormat df = new DecimalFormat("#,##0.00");
		ICommonBiz biz=(ICommonBiz)SpringUtil.getBean("commonBiz");
		try {
			TbFoundation bean=(TbFoundation) biz.get(TbFoundation.class, new Long(value));
			String fundName=bean.getFoundName();
			String temp="";
			if(fundName.getBytes().length>30){
				temp=fundName.substring(0,15)+"...";
			}else{
				temp=fundName;
			}
			text="<span style='font-size: 13px; font-weight: 700;' title='"+fundName+"'>"+temp+"</span>";
			//协议金额
			String hql="select sum(proAmount) from TbProtocol a where foundId="+bean.getFoundId();
//			String hql="select sum(dealAmount) from TbProDetail a where proId in (select proId from TbProtocol where foundId="+bean.getFoundId()+")";
			Object obj=biz.getHQLUnique(hql);
			obj=obj==null?0:obj;
			String objFormat=StringUtils.formatMoney(obj+"");
			//查询协议下实际到账总额
			hql="select sum(toAmount) from TbProDetail a where proId in (select proId from TbProtocol where foundId="+bean.getFoundId()+")";
			Object realObj=biz.getHQLUnique(hql);
			realObj=realObj==null?0:realObj;
			String realObjFormat=StringUtils.formatMoney(realObj+"");
			//所有支出总金额，去除当前支出
			hql="select sum(payAmount) from TbProPay a where a.foundId = "+bean.getFoundId();
			Object payObj=biz.getHQLUnique(hql);
			payObj=payObj==null?0:payObj;
			String payObjFormat=StringUtils.formatMoney(payObj+"");
			//剩余：
			BigDecimal realObjBig=new BigDecimal(realObj+"");
			BigDecimal payObjBig=new BigDecimal(payObj+"");
			BigDecimal yuObj=realObjBig.subtract(payObjBig);
			
			text+="（<span style=\"margin:0px 4px;\">协议金额："+objFormat+" 元</span>，<span style=\"margin:0px 4px;\">到账总额："+realObjFormat+" 元</span>，<span style=\"margin:0px 4px;\">已支出总额："+payObjFormat+" 元</span>，<span style=\"margin:0px 4px;\">剩余金额："+StringUtils.formatMoney(yuObj.doubleValue()+"")+" 元</span>）";
		} catch (Exception e) {
			e.printStackTrace();
		}
		TagUtils.getInstance().write(pageContext,text);
		return super.doStartTag();
	}
	

	public void setValue(Long value) {
		this.value = value;
	}
	
}
