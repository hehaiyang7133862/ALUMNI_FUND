package com.laungee.proj.api.web.action;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbCommodity;
import com.laungee.proj.common.model.TbCommodityDetail;
import com.laungee.proj.common.model.TbCommodityOrder;
import com.laungee.proj.common.model.TbUnAlumni;
import com.laungee.proj.common.model.TbZcotherOrder;
import com.laungee.proj.common.util.DateUtil;

public class SpOrderBeanAction extends BaseAction {
	// 商品订单详情
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 商品订单
		TbCommodityOrder bean = null;
		// 商品订单ID
		String orderId = request.getParameter("id");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbCommodityOrder)this.getCommonBo().get(TbCommodityOrder.class, new Long(orderId));
			}catch(Exception e){}
		}
		if(bean==null){
			// 参数错误
			request.setAttribute("result", "error");
			// 返回
			return ERROR;
		}
		request.setAttribute("bean", bean);
		// 返回
		return SUCCESS;
	}
	
	// 商品订单新增
	public String insert() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 系统当前时间
		Date dateNow = this.getCommonBo().getSysDate();
		// 商品订单
		TbCommodityOrder bean = new TbCommodityOrder();
        // 商品
        TbCommodity beanComm = null;
        String commId = request.getParameter("comm");
        if(commId!=null && !"".equals(commId)){
        	try{
        		String hql = "from TbCommodity a where a.isShelves='1' and a.commId="+new Long(commId);
        		beanComm = (TbCommodity)this.getCommonBo().getHQLUnique(hql);
        	}catch(Exception e){}
        }
        if(beanComm==null){
        	// 返回状态：参数错误
        	request.setAttribute("result", "commError");
        	// 返回
        	return ERROR;
        }
        bean.setCommId(beanComm.getCommId());
        bean.setCommName(beanComm.getCommName());
        // 商品选项
        TbCommodityDetail beanCommOption = null;
        String optionId = request.getParameter("option");
        if(optionId!=null && !"".equals(optionId)){
        	try{
        		String hql = "from TbCommodityDetail a where a.isShelves='1' and a.commId="+beanComm.getCommId()+" and a.detailId="+new Long(optionId);
        		beanCommOption = (TbCommodityDetail)this.getCommonBo().getHQLUnique(hql);
        	}catch(Exception e){}
        }
        if(beanCommOption==null){
        	// 返回状态：参数错误
        	request.setAttribute("result", "optionError");
        	// 返回
        	return ERROR;
        }
    	bean.setCommDetailId(beanCommOption.getDetailId());
    	bean.setCommDetailName(beanCommOption.getDetailName());
    	bean.setCommCostfee(beanCommOption.getCostFee());
    	bean.setCommSalefee(beanCommOption.getSaleFee());
        // 商品图片
        String commPic = beanCommOption.getDetailPic();
        if(commPic==null || "".equals(commPic)){
        	try{
        		String hql = "select a.picPath from TbCommodityPic a where a.picId="+beanComm.getCommId()+" order by a.numOrder";
        		commPic = (String)this.getCommonBo().getHQLUnique(hql);
        	}catch(Exception e){}
        }
        bean.setCommPic(commPic);
    	// 选项个数
    	Long optionCountL = null;
        String optionCount = request.getParameter("optionCount");
        if(optionCount!=null && !"".equals(optionCount)){
        	try{
        		optionCountL = new Long(optionCount);
        	}catch(Exception e){}
        }
        if(optionCountL==null || optionCountL.longValue()<=0){
        	// 返回状态：参数错误
        	request.setAttribute("result", "optionCountError");
        	// 返回
        	return ERROR;
        }
        if(beanCommOption.getStockNum()!=null && optionCountL.longValue()>beanCommOption.getStockNum().longValue()){
        	// 返回状态：参数错误
        	request.setAttribute("result", "optionCountOver");
        	// 返回
        	return ERROR;
        }
        bean.setCommNum(optionCountL);
        // 取货方式
    	String shippingType = request.getParameter("shipType");
    	if(shippingType==null || !"1".equals(shippingType)){
    		shippingType = "2";
    	}
        bean.setShippingType(shippingType);
        // 快递运费
        BigDecimal shipFeeD = null;
        if("2".equals(shippingType) && beanComm.getIsShipping()!=null && "1".equals(beanComm.getIsShipping()) && beanComm.getShippingFee()!=null && beanComm.getShippingFee().doubleValue()>0){
        	shipFeeD = beanComm.getShippingFee();
        }
        if(shipFeeD==null){
        	shipFeeD = new BigDecimal(0).setScale(2, 4);
        }
        bean.setShippingFee(shipFeeD);
        // 捐赠
        BigDecimal donationFeeD = null;
    	String donation = request.getParameter("donation");
    	if(donation!=null && !"".equals(donation)){
    		try{
    			donationFeeD = new BigDecimal(donation).setScale(2, 4);
    	    }catch(Exception e){}
    	}
    	if(donationFeeD==null || donationFeeD.doubleValue()<0){
    		donationFeeD = new BigDecimal(0).setScale(2, 4);
    	}
    	bean.setDonationFee(donationFeeD);
        // 订单总金额
        BigDecimal orderAmoutD = null;
        try{
        	orderAmoutD = new BigDecimal(bean.getCommSalefee().doubleValue()*bean.getCommNum()+bean.getShippingFee().doubleValue()+bean.getDonationFee().doubleValue()).setScale(2, 4);
    	}catch(Exception e){}
    	if(orderAmoutD==null || orderAmoutD.doubleValue()<=0){
        	// 返回状态：参数错误
        	request.setAttribute("result", "orderAmountError");
        	// 返回
        	return ERROR;
    	}
		bean.setOrderFee(orderAmoutD);
        // 订单来源
    	String orderSource = request.getParameter("orderSource");
    	if(orderSource!=null){
    		bean.setOrderSource(orderSource);
    	}
        // 订单支付方式
    	String orderType = request.getParameter("orderType");
		bean.setOrderType(orderType);
        // 订单支付状态
		bean.setOrderStatus("0");
        // 订单提交时间
		bean.setOrderTime(dateNow);
		// 下单人信息
		// 下单人姓名
    	String personName = request.getParameter("personName");
		if(personName==null || "".equals(personName)){
			personName = "匿名";
		}
		bean.setBuyerName(personName);
		// 匿名捐赠、购买
    	String niMing = request.getParameter("niMing");
		if(niMing==null || !"1".equals(niMing)){
			niMing = "0";
		}
		bean.setNiMing(niMing);
		// 下单人IP
    	String personIp = request.getParameter("personIp");
    	if(personIp!=null){
    		bean.setBuyerIp(personIp);
    	}
		// 下单人性别
    	String personSex = request.getParameter("personSex");
    	if(personSex!=null && ("1".equals(personSex) || "2".equals(personSex))){
    		bean.setBuyerSex(personSex);
    	}
		// 下单人邮箱
    	String personEmail = request.getParameter("personEmail");
    	if(personEmail!=null){
    		bean.setBuyerEmail(personEmail);
    	}
		// 下单人手机
    	String personPhone = request.getParameter("personPhone");
    	if(personPhone!=null){
    		bean.setBuyerMobile(personPhone);
    	}
		// 下单人电话
    	String personTel = request.getParameter("personTel");
    	if(personTel!=null){
    		bean.setBuyerPhone(personTel);
    	}
		// 地址信息
    	// 地址邮编
    	String addressZip = request.getParameter("addressZip");
    	if(addressZip!=null){
    		bean.setBuyerZipcode(addressZip);
    	}
    	// 地址省
    	String addressSheng = request.getParameter("addressSheng");
    	if(addressSheng!=null){
    		bean.setBuyerSheng(addressSheng);
    	}
    	// 地址市
    	String addressShi = request.getParameter("addressShi");
    	if(addressShi!=null){
    		bean.setBuyerShi(addressShi);
    	}
    	// 地址区
    	String addressQu = request.getParameter("addressQu");
    	if(addressQu!=null){
    		bean.setBuyerQu(addressQu);
    	}
    	// 地址详情
    	String addressDetail = request.getParameter("addressDetail");
    	if(addressDetail!=null){
    		bean.setBuyerAddress(addressDetail);
    	}
    	// 校友信息
    	// 是否校友
    	String alumniFlag = request.getParameter("alumniFlag");
    	if(alumniFlag==null || !"1".equals(alumniFlag)){
    		alumniFlag = "0";
    	}
    	bean.setAlumniFlag(alumniFlag);
    	// 校友社区用户
    	TbUnAlumni beanUnAlumni = null;
    	String unAlumniId = request.getParameter("alumniUn");
    	if(unAlumniId!=null && !"".equals(unAlumniId)){
        	try{
        		beanUnAlumni = (TbUnAlumni)this.getCommonBo().get(TbUnAlumni.class,new Long(unAlumniId));
        	}catch(Exception e){}
        }
    	if(beanUnAlumni!=null){
    		bean.setUnAlumniId(beanUnAlumni.getUnAlumniId());
    	}
    	// 入学年
    	String studyYearin = request.getParameter("studyYearin");
    	if(studyYearin!=null){
        	bean.setStudyYearin(studyYearin);
    	}
    	// 离校年
    	String studyYearover = request.getParameter("studyYearover");
    	if(studyYearover!=null){
        	bean.setStudyYearover(studyYearover);
    	}
    	// 院系
    	String studyAcademy = request.getParameter("studyAcademy");
    	if(studyAcademy!=null){
        	bean.setStudyAcademy(studyAcademy);
    	}
    	// 专业
    	String studyMajor = request.getParameter("studyMajor");
    	if(studyMajor!=null){
        	bean.setStudyMajor(studyMajor);
    	}
    	// 班级
    	String studyClass = request.getParameter("studyClass");
    	if(studyClass!=null){
        	bean.setStudyClass(studyClass);
    	}
    	// 学号
    	String studyNum = request.getParameter("studyNum");
    	if(studyNum!=null){
        	bean.setStudyNum(studyNum);
    	}
    	// 学历（校友身份）
    	String studyDegree = request.getParameter("studyDegree");
    	if(studyDegree!=null){
        	bean.setStudyDegree(studyDegree);
    	}
    	// 工作信息
    	// 工作单位
    	String workCompany = request.getParameter("workCompany");
    	if(workCompany!=null){
        	bean.setWorkCompany(workCompany);
    	}
    	// 工作部门
    	String workDepart = request.getParameter("workDepart");
    	if(workDepart!=null){
        	bean.setWorkDepart(workDepart);
    	}
    	// 工作职能
    	String workDuty = request.getParameter("workDuty");
    	if(workDuty!=null){
        	bean.setWorkDuty(workDuty);
    	}
    	// 捐赠单备注信息
    	String orderMemo = request.getParameter("orderMemo");
    	if(orderMemo!=null){
        	bean.setOrderMemo(orderMemo);
    	}
        // 保存
    	try{
    		bean = (TbCommodityOrder)this.getCommonBo().save(bean);
            // 单号
    		String orderNum = bean.getOrderId().toString();
    		int orderLen = orderNum.length();
            for(int i=0; i<17-orderLen; i++){  
            	orderNum = "0"+orderNum;  
            }
            orderNum = DateUtil.format(dateNow,"yyyyMMdd")+"003"+orderNum;
            bean.setOrderNo(orderNum);
            bean = (TbCommodityOrder)this.getCommonBo().update(bean);
    		request.setAttribute("bean", bean);
    		// 返回
        	return SUCCESS;
    	}catch(Exception e){}
		// 返回状态：保存失败
		request.setAttribute("result", "failure");
		// 返回
    	return ERROR;
	}
	
	// 商品订单更新编辑
	public String update() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 商品订单
		TbCommodityOrder bean = null;
		// 商品订单ID
		String orderId = request.getParameter("id");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbCommodityOrder)this.getCommonBo().getEvict(TbCommodityOrder.class, new Long(orderId));
			}catch(Exception e){}
		}
		if(bean==null){
    		// 返回状态：参数错误
    		request.setAttribute("result", "error");
    		// 返回
        	return ERROR;
		}
		// 待付款捐赠单，方可修改更新相关信息
		if(bean.getOrderStatus()==null || !"1".equals(bean.getOrderStatus())){
			// 商品
	        TbCommodity beanComm = null;
        	try{
        		String hql = "from TbCommodity a where a.isShelves='1' and a.commId="+bean.getCommId();
        		beanComm = (TbCommodity)this.getCommonBo().getHQLUnique(hql);
        	}catch(Exception e){}
	        if(beanComm==null){
	        	// 返回状态：参数错误
	        	request.setAttribute("result", "commError");
	        	// 返回
	        	return ERROR;
	        }
			// 是否更商品
			String commUpdate = request.getParameter("commUpdate");
			if(commUpdate!=null && ("1".equals(commUpdate) || "true".equals(commUpdate))){
				// 商品名称
		        bean.setCommName(beanComm.getCommName());
			}
	        // 商品选项
	        TbCommodityDetail beanCommOption = null;
        	try{
        		String hql = "from TbCommodityDetail a where a.isShelves='1' and a.commId="+beanComm.getCommId()+" and a.detailId="+bean.getCommDetailId();
        		beanCommOption = (TbCommodityDetail)this.getCommonBo().getHQLUnique(hql);
        	}catch(Exception e){}
	        if(beanCommOption==null){
	        	// 返回状态：参数错误
	        	request.setAttribute("result", "optionError");
	        	// 返回
	        	return ERROR;
	        }
			// 是否更商品选项
			String optionUpdate = request.getParameter("optionUpdate");
			if(optionUpdate!=null && ("1".equals(optionUpdate) || "true".equals(optionUpdate))){
				// 商品选项名称、成本价、售价
		    	bean.setCommDetailName(beanCommOption.getDetailName());
		    	bean.setCommCostfee(beanCommOption.getCostFee());
		    	bean.setCommSalefee(beanCommOption.getSaleFee());
			}
			// 商品图片
			if((commUpdate!=null && ("1".equals(commUpdate) || "true".equals(commUpdate))) || (optionUpdate!=null && ("1".equals(optionUpdate) || "true".equals(optionUpdate)))){
		        String commPic = beanCommOption.getDetailPic();
		        if(commPic==null || "".equals(commPic)){
		        	try{
		        		String hql = "select a.picPath from TbCommodityPic a where a.picId="+beanComm.getCommId()+" order by a.numOrder";
		        		commPic = (String)this.getCommonBo().getHQLUnique(hql);
		        	}catch(Exception e){}
		        }
		        bean.setCommPic(commPic);
			}
        	// 选项个数
            if(bean.getCommNum()==null || bean.getCommNum().longValue()<1){
            	// 返回状态：参数错误
            	request.setAttribute("result", "optionCountError");
            	// 返回
            	return ERROR;
            }
            if(beanCommOption.getStockNum()!=null && bean.getCommNum().longValue()>beanCommOption.getStockNum().longValue()){
            	// 返回状态：参数错误
            	request.setAttribute("result", "optionCountOver");
            	// 返回
            	return ERROR;
            }
            // 取货方式
        	String shippingType = request.getParameter("shipType");
        	if(shippingType!=null && ("1".equals(shippingType) || "true".equals(shippingType))){
                bean.setShippingType(shippingType);
			}
            // 快递运费
            BigDecimal shipFeeD = null;
            if("2".equals(shippingType) && beanComm.getIsShipping()!=null && "1".equals(beanComm.getIsShipping()) && beanComm.getShippingFee()!=null && beanComm.getShippingFee().doubleValue()>0){
            	shipFeeD = beanComm.getShippingFee();
            }
            if(shipFeeD==null){
            	shipFeeD = new BigDecimal(0).setScale(2, 4);
            }
            bean.setShippingFee(shipFeeD);
            // 捐赠
            BigDecimal donationFeeD = null;
        	String donation = request.getParameter("donation");
        	if(donation!=null && !"".equals(donation)){
        		try{
        			donationFeeD = new BigDecimal(donation).setScale(2, 4);
        	    }catch(Exception e){}
        	}
        	if(donationFeeD==null || donationFeeD.doubleValue()<0){
        		donationFeeD = new BigDecimal(0).setScale(2, 4);
        	}
        	bean.setDonationFee(donationFeeD);
        	// 订单总金额
            BigDecimal orderAmoutD = null;
            try{
            	orderAmoutD = new BigDecimal(bean.getCommSalefee().doubleValue()*bean.getCommNum()+bean.getShippingFee().doubleValue()+bean.getDonationFee().doubleValue()).setScale(2, 4);
        	}catch(Exception e){}
        	if(orderAmoutD==null || orderAmoutD.doubleValue()<=0){
            	// 返回状态：参数错误
            	request.setAttribute("result", "orderAmountError");
            	// 返回
            	return ERROR;
        	}
    		bean.setOrderFee(orderAmoutD);
            // 订单来源
        	String orderSource = request.getParameter("orderSource");
        	if(orderSource!=null){
        		bean.setOrderSource(orderSource);
        	}
            // 订单支付方式
        	String orderType = request.getParameter("orderType");
        	if(orderType!=null){
        		bean.setOrderType(orderType);
        	}
	    	// 是否更新订单提交时间
			String orderTimeUpdate = request.getParameter("orderTimeUpdate");
			if(orderTimeUpdate!=null && ("1".equals(orderTimeUpdate) || "true".equals(orderTimeUpdate))){
				// 系统当前时间
				Date dateNow = this.getCommonBo().getSysDate();
		    	bean.setOrderTime(dateNow);
			}
			// 下单人信息
			// 下单人姓名
	    	String personName = request.getParameter("personName");
	    	if(personName!=null){
	    		if("".equals(personName)){
	    			personName = "匿名";
	    		}
	    		bean.setBuyerName(personName);
	    	}
			// 匿名捐赠、购买
	    	String niMing = request.getParameter("niMing");
	    	if(niMing!=null){
	    		if(!"1".equals(niMing)){
	    			niMing = "0";
	        	}
	    		bean.setNiMing(niMing);
	    	}
			// 下单人IP
	    	String personIp = request.getParameter("personIp");
	    	if(personIp!=null){
	    		bean.setBuyerIp(personIp);
	    	}
			// 下单人性别
	    	String personSex = request.getParameter("personSex");
	    	if(personSex!=null && ("1".equals(personSex) || "2".equals(personSex))){
	    		bean.setBuyerSex(personSex);
	    	}
			// 下单人邮箱
	    	String personEmail = request.getParameter("personEmail");
	    	if(personEmail!=null){
	    		bean.setBuyerEmail(personEmail);
	    	}
			// 下单人手机
	    	String personPhone = request.getParameter("personPhone");
	    	if(personPhone!=null){
	    		bean.setBuyerMobile(personPhone);
	    	}
			// 下单人电话
	    	String personTel = request.getParameter("personTel");
	    	if(personTel!=null){
	    		bean.setBuyerPhone(personTel);
	    	}
			// 地址信息
	    	// 地址邮编
	    	String addressZip = request.getParameter("addressZip");
	    	if(addressZip!=null){
	    		bean.setBuyerZipcode(addressZip);
	    	}
	    	// 地址省
	    	String addressSheng = request.getParameter("addressSheng");
	    	if(addressSheng!=null){
	    		bean.setBuyerSheng(addressSheng);
	    	}
	    	// 地址市
	    	String addressShi = request.getParameter("addressShi");
	    	if(addressShi!=null){
	    		bean.setBuyerShi(addressShi);
	    	}
	    	// 地址区
	    	String addressQu = request.getParameter("addressQu");
	    	if(addressQu!=null){
	    		bean.setBuyerQu(addressQu);
	    	}
	    	// 地址详情
	    	String addressDetail = request.getParameter("addressDetail");
	    	if(addressDetail!=null){
	    		bean.setBuyerAddress(addressDetail);
	    	}
	    	// 校友信息
	    	// 是否校友
	    	String alumniFlag = request.getParameter("alumniFlag");
	    	if(alumniFlag!=null){
	    		if(!"1".equals(alumniFlag)){
	    			alumniFlag = "0";
	        	}
	    		bean.setAlumniFlag(alumniFlag);
	    	}
	    	bean.setAlumniFlag(alumniFlag);
	    	// 入学年
	    	String studyYearin = request.getParameter("studyYearin");
	    	if(studyYearin!=null){
	        	bean.setStudyYearin(studyYearin);
	    	}
	    	// 离校年
	    	String studyYearover = request.getParameter("studyYearover");
	    	if(studyYearover!=null){
	        	bean.setStudyYearover(studyYearover);
	    	}
	    	// 院系
	    	String studyAcademy = request.getParameter("studyAcademy");
	    	if(studyAcademy!=null){
	        	bean.setStudyAcademy(studyAcademy);
	    	}
	    	// 专业
	    	String studyMajor = request.getParameter("studyMajor");
	    	if(studyMajor!=null){
	        	bean.setStudyMajor(studyMajor);
	    	}
	    	// 班级
	    	String studyClass = request.getParameter("studyClass");
	    	if(studyClass!=null){
	        	bean.setStudyClass(studyClass);
	    	}
	    	// 学号
	    	String studyNum = request.getParameter("studyNum");
	    	if(studyNum!=null){
	        	bean.setStudyNum(studyNum);
	    	}
	    	// 学历（校友身份）
	    	String studyDegree = request.getParameter("studyDegree");
	    	if(studyDegree!=null){
	        	bean.setStudyDegree(studyDegree);
	    	}
	    	// 工作信息
	    	// 工作单位
	    	String workCompany = request.getParameter("workCompany");
	    	if(workCompany!=null){
	        	bean.setWorkCompany(workCompany);
	    	}
	    	// 工作部门
	    	String workDepart = request.getParameter("workDepart");
	    	if(workDepart!=null){
	        	bean.setWorkDepart(workDepart);
	    	}
	    	// 工作职能
	    	String workDuty = request.getParameter("workDuty");
	    	if(workDuty!=null){
	        	bean.setWorkDuty(workDuty);
	    	}
		}
		// 无视捐赠单状态，满足条件即可更新
    	// 校友社区用户
    	TbUnAlumni beanUnAlumni = null;
    	String unAlumniId = request.getParameter("alumniUn");
    	if(unAlumniId!=null && !"".equals(unAlumniId)){
        	try{
        		beanUnAlumni = (TbUnAlumni)this.getCommonBo().get(TbUnAlumni.class,new Long(unAlumniId));
        	}catch(Exception e){}
        }
    	if(beanUnAlumni!=null){
    		bean.setUnAlumniId(beanUnAlumni.getUnAlumniId());
    	}
    	// 订单备注信息
    	String orderMemo = request.getParameter("orderMemo");
    	if(orderMemo!=null){
        	bean.setOrderMemo(orderMemo);
    	}
    	// 订单回执信息
    	String receipt = request.getParameter("receipt");
    	if(receipt!=null){
        	bean.setReceipt(receipt);
    	}
        // 保存
    	try{
    		bean = (TbCommodityOrder)this.getCommonBo().update(bean);
    		request.setAttribute("bean", bean);
    		// 返回
        	return SUCCESS;
    	}catch(Exception e){}
		// 返回状态：保存失败
		request.setAttribute("result", "failure");
		// 返回
    	return ERROR;
	}
	
	// 商品订单付款状态修改为已付款
	public String setStatusOk() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 商品订单
		TbCommodityOrder bean = null;
		// 商品订单ID
		String orderId = request.getParameter("id");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbCommodityOrder)this.getCommonBo().get(TbCommodityOrder.class, new Long(orderId));
			}catch(Exception e){}
		}
		if(bean==null){
    		// 返回状态：参数错误
    		request.setAttribute("result", "error");
    		// 返回
        	return ERROR;
		}
		if(bean.getOrderStatus()!=null && "1".equals(bean.getOrderStatus())){
    		// 返回状态：已付款
    		request.setAttribute("result", "payed");
    		// 返回
        	return ERROR;
		}
		try{
			// 支付方式
			String orderType = request.getParameter("orderType");
			if(orderType!=null && !"".equals(orderType)){
				bean.setOrderType(orderType);
			}
			// 捐赠单状态修改为已付款
			bean.setOrderStatus("1");
			// 系统时间
			Date dateNow = this.getCommonBo().getSysDate();
			// 捐赠单付款完成时间
			bean.setOrderOkTime(dateNow);
			// 保存
    		bean = (TbCommodityOrder)this.getCommonBo().update(bean);
    		request.setAttribute("bean", bean);
    		// 修改库存
			try{
	    		TbCommodityDetail beanOption = bean.getTbCommodityDetail();
	    		if(beanOption!=null){
	    			Long stockNum = beanOption.getStockNum();
	    			Long saledNum = bean.getCommNum();
	    			if(stockNum!=null && saledNum!=null){
	    				beanOption.setStockNum(stockNum-saledNum);
	    				this.getCommonBo().update(beanOption);
	    			}
	    		}
	    	}catch(Exception e){}
    		// 返回
        	return SUCCESS;
    	}catch(Exception e){}
		// 返回状态：保存失败
		request.setAttribute("result", "failure");
		// 返回
    	return ERROR;
	}
	
	// 商品订单评价
	public String setStar() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 商品订单
		TbCommodityOrder bean = null;
		// 商品订单ID
		String orderId = request.getParameter("id");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbCommodityOrder)this.getCommonBo().get(TbCommodityOrder.class, new Long(orderId));
			}catch(Exception e){}
		}
		if(bean==null){
    		// 返回状态：参数错误
    		request.setAttribute("result", "error");
    		// 返回
        	return ERROR;
		}
		if(bean.getOrderStatus()==null || !"1".equals(bean.getOrderStatus())){
    		// 返回状态：未付款
    		request.setAttribute("result", "nopayed");
    		// 返回
        	return ERROR;
		}
		if(bean.getStarNum()!=null){
    		// 返回状态：已评价
    		request.setAttribute("result", "stared");
    		// 返回
        	return ERROR;
		}
		try{
			// 评级星级
			Long starNumL = null;
			String starNum = request.getParameter("star");
			if(starNum!=null && !"".equals(starNum)){
				try{
					starNumL = new Long(starNum);
				}catch(Exception e){}
			}
			if(starNumL==null || starNumL<1 || starNumL>5){
	    		// 返回状态：参数错误
	    		request.setAttribute("result", "error");
	    		// 返回
	        	return ERROR;
			}
			bean.setStarNum(starNumL);
			// 评价说明
			String memo = request.getParameter("memo");
			if(memo!=null){
				bean.setStarMemo(memo);
			}
			// 系统时间
			Date dateNow = this.getCommonBo().getSysDate();
			// 评价时间
			bean.setStarTime(dateNow);
			// 保存
    		bean = (TbCommodityOrder)this.getCommonBo().update(bean);
    		request.setAttribute("bean", bean);
    		// 返回
        	return SUCCESS;
    	}catch(Exception e){}
		// 返回状态：保存失败
		request.setAttribute("result", "failure");
		// 返回
    	return ERROR;
	}
	
	// 商品订单添加回执信息
	public String addReceipt() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 商品订单
		TbCommodityOrder bean = null;
		// 商品订单ID
		String orderId = request.getParameter("id");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbCommodityOrder)this.getCommonBo().get(TbCommodityOrder.class, new Long(orderId));
			}catch(Exception e){}
		}
		if(bean==null){
    		// 返回状态：参数错误
    		request.setAttribute("result", "error");
    		// 返回
        	return ERROR;
		}
		try{
			// 需要添加的回执信息
			String receipt = request.getParameter("receipt");
			if(receipt!=null && !"".equals(receipt)){
				String receiptOld = bean.getReceipt();
				if(receiptOld==null){
					receiptOld = "";
				}
				// 回执信息追加位置
				String position = request.getParameter("position");
				if(position==null || !"top".equals(position)){
					bean.setReceipt(receiptOld+receipt);
				}else{
					bean.setReceipt(receipt+receiptOld);
				}
				// 保存
	    		bean = (TbCommodityOrder)this.getCommonBo().update(bean);
	    		request.setAttribute("bean", bean);
			}
    		// 返回
        	return SUCCESS;
    	}catch(Exception e){}
		// 返回状态：保存失败
		request.setAttribute("result", "failure");
		// 返回
    	return ERROR;
	}
	
	// 其他众筹项目待付款状态捐赠单删除
	public String delete() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 商品订单
		TbCommodityOrder bean = null;
		// 商品订单ID
		String orderId = request.getParameter("id");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbCommodityOrder)this.getCommonBo().get(TbCommodityOrder.class, new Long(orderId));
			}catch(Exception e){}
		}
		if(bean==null){
    		// 返回状态：参数错误
    		request.setAttribute("result", "error");
    		// 返回
        	return ERROR;
		}
		if(bean.getOrderStatus()!=null && "1".equals(bean.getOrderStatus())){
    		// 返回状态：已付款
    		request.setAttribute("result", "payed");
    		// 返回
        	return ERROR;
		}
		try{
			// 删除
			this.getCommonBo().delete(bean);
    		request.setAttribute("bean", bean);
    		// 返回
        	return SUCCESS;
    	}catch(Exception e){}
		// 返回状态：删除失败
		request.setAttribute("result", "failure");
		// 返回
    	return ERROR;
	}
}