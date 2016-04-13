package com.laungee.proj.api.web.action;


import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbUnAlumni;
import com.laungee.proj.common.model.TbZcotherOrder;
import com.laungee.proj.common.model.TbZcprojOrder;
import com.laungee.proj.common.util.DateUtil;

public class ZcOtherOrderBeanAction extends BaseAction {
	// �����ڳ���Ŀ����������
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// �����ڳ���Ŀ������
		TbZcotherOrder bean = null;
		// �����ڳ���Ŀ������ID
		String orderId = request.getParameter("id");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbZcotherOrder)this.getCommonBo().get(TbZcotherOrder.class, new Long(orderId));
			}catch(Exception e){}
		}
		if(bean==null){
			// ��������
			request.setAttribute("result", "error");
			// ����
			return ERROR;
		}
		request.setAttribute("bean", bean);
		// ����
		return SUCCESS;
	}
	
	// �����ڳ���Ŀ����������
	public String insert() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ϵͳ��ǰʱ��
		Date dateNow = this.getCommonBo().getSysDate();
		// �����ڳ���Ŀ������
		TbZcotherOrder bean = new TbZcotherOrder();
        // �����ڳ���Ŀ
        String projName = request.getParameter("projName");
        if(projName==null || "".equals(projName)){
        	// ����״̬����������
        	request.setAttribute("result", "projError");
        	// ����
        	return ERROR;
        }
        bean.setProjName(projName);
        // �����ڳ���Ŀ����URL
        String projUrl = request.getParameter("projUrl");
        bean.setProjUrl(projUrl);
		// �������ܽ��
		BigDecimal orderAmoutD = null;
		String orderAmout = request.getParameter("orderAmout");
		if(orderAmout!=null && !"".equals(orderAmout)){
			try{
				orderAmoutD = new BigDecimal(orderAmout).setScale(2, 4);
			}catch(Exception e){}
		}
		if(orderAmoutD==null || orderAmoutD.doubleValue()<=0){
			// ����״̬����������
			request.setAttribute("result", "orderAmoutError");
			// ����
			return ERROR;
		}
    	bean.setOrderAmount(orderAmoutD);
        // ��������
        bean.setOrderAmountType("�����");
        // �������ֽ��
        bean.setOrderAmountView(bean.getOrderAmount());
        // �������ֽ�������λ
        bean.setOrderAmountUnit("Ԫ");
        // ������֧����ʽ
    	String orderType = request.getParameter("orderType");
		bean.setOrderType(orderType);
        // ������֧��״̬
		bean.setOrderStatus("0");
        // �������ύʱ��
		bean.setOrderTime(dateNow);
        // �����Ƿ���Ҫ֤��
    	String needZhengshu = request.getParameter("needZhengshu");
    	if(needZhengshu==null || !"1".equals(needZhengshu)){
    		needZhengshu = "0";
    	}
		bean.setNeedZhengshu(needZhengshu);
        // �����Ƿ���Ҫ��Ʊ
    	String needFapiao = request.getParameter("needFapiao");
    	if(needFapiao==null || !"1".equals(needFapiao)){
    		needFapiao = "0";
    	}
		bean.setNeedFapiao(needFapiao);
		// ��������Ϣ
		// ����������(Ĭ�ϸ���)
		bean.setPersonType("1");
		// ��������(Ĭ��1)
		bean.setPersonCount(new Long(1));
		// ����������
    	String personName = request.getParameter("personName");
		if(personName==null || "".equals(personName)){
			personName = "����";
		}
		bean.setPersonName(personName);
		// ��������
    	String niMing = request.getParameter("niMing");
		if(niMing==null || !"1".equals(niMing)){
			niMing = "0";
		}
		bean.setNiMing(niMing);
		// ������IP
    	String personIp = request.getParameter("personIp");
    	if(personIp!=null){
    		bean.setPersonIp(personIp);
    	}
		// �������Ա�
    	String personSex = request.getParameter("personSex");
    	if(personSex!=null && ("1".equals(personSex) || "2".equals(personSex))){
    		bean.setPersonSex(personSex);
    	}
		// ����������
    	String personEmail = request.getParameter("personEmail");
    	if(personEmail!=null){
    		bean.setPersonEmail(personEmail);
    	}
		// �������ֻ�
    	String personPhone = request.getParameter("personPhone");
    	if(personPhone!=null){
    		bean.setPersonPhone(personPhone);
    	}
		// �����˵绰
    	String personTel = request.getParameter("personTel");
    	if(personTel!=null){
    		bean.setPersonTel(personTel);
    	}
		// ��ַ��Ϣ
    	// ��ַ�ʱ�
    	String addressZip = request.getParameter("addressZip");
    	if(addressZip!=null){
    		bean.setAddressZip(addressZip);
    	}
    	// ��ַʡ
    	String addressSheng = request.getParameter("addressSheng");
    	if(addressSheng!=null){
    		bean.setAddressSheng(addressSheng);
    	}
    	// ��ַ��
    	String addressShi = request.getParameter("addressShi");
    	if(addressShi!=null){
    		bean.setAddressShi(addressShi);
    	}
    	// ��ַ��
    	String addressQu = request.getParameter("addressQu");
    	if(addressQu!=null){
    		bean.setAddressQu(addressQu);
    	}
    	// ��ַ����
    	String addressDetail = request.getParameter("addressDetail");
    	if(addressDetail!=null){
    		bean.setAddressDetail(addressDetail);
    	}
    	// У����Ϣ
    	// �Ƿ�У��
    	String alumniFlag = request.getParameter("alumniFlag");
    	if(alumniFlag==null || !"1".equals(alumniFlag)){
    		alumniFlag = "0";
    	}
    	bean.setAlumniFlag(alumniFlag);
    	// У�������û�
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
    	// ��ѧ��
    	String studyYearin = request.getParameter("studyYearin");
    	if(studyYearin!=null){
        	bean.setStudyYearin(studyYearin);
    	}
    	// ��У��
    	String studyYearover = request.getParameter("studyYearover");
    	if(studyYearover!=null){
        	bean.setStudyYearover(studyYearover);
    	}
    	// Ժϵ
    	String studyAcademy = request.getParameter("studyAcademy");
    	if(studyAcademy!=null){
        	bean.setStudyAcademy(studyAcademy);
    	}
    	// רҵ
    	String studyMajor = request.getParameter("studyMajor");
    	if(studyMajor!=null){
        	bean.setStudyMajor(studyMajor);
    	}
    	// �༶
    	String studyClass = request.getParameter("studyClass");
    	if(studyClass!=null){
        	bean.setStudyClass(studyClass);
    	}
    	// ѧ��
    	String studyNum = request.getParameter("studyNum");
    	if(studyNum!=null){
        	bean.setStudyNum(studyNum);
    	}
    	// ѧ����У����ݣ�
    	String studyDegree = request.getParameter("studyDegree");
    	if(studyDegree!=null){
        	bean.setStudyDegree(studyDegree);
    	}
    	// ������Ϣ
    	// ������λ
    	String workCompany = request.getParameter("workCompany");
    	if(workCompany!=null){
        	bean.setWorkCompany(workCompany);
    	}
    	// ��������
    	String workDepart = request.getParameter("workDepart");
    	if(workDepart!=null){
        	bean.setWorkDepart(workDepart);
    	}
    	// ����ְ��
    	String workDuty = request.getParameter("workDuty");
    	if(workDuty!=null){
        	bean.setWorkDuty(workDuty);
    	}
    	// ��������ע��Ϣ
    	String orderMemo = request.getParameter("orderMemo");
    	if(orderMemo!=null){
        	bean.setOrderMemo(orderMemo);
    	}
        // ����
    	try{
    		bean = (TbZcotherOrder)this.getCommonBo().save(bean);
            // ����
    		String orderNum = bean.getOrderId().toString();
    		int orderLen = orderNum.length();
            for(int i=0; i<17-orderLen; i++){  
            	orderNum = "0"+orderNum;  
            }
            orderNum = DateUtil.format(dateNow,"yyyyMMdd")+"002"+orderNum;
            bean.setOrderNum(orderNum);
            bean = (TbZcotherOrder)this.getCommonBo().update(bean);
    		request.setAttribute("bean", bean);
    		// ����
        	return SUCCESS;
    	}catch(Exception e){}
		// ����״̬������ʧ��
		request.setAttribute("result", "failure");
		// ����
    	return ERROR;
	}
	
	// �����ڳ���Ŀ���������±༭
	public String update() throws Exception {
		HttpServletRequest request = this.getRequest();
		// �����ڳ���Ŀ������
		TbZcotherOrder bean = null;
		// �����ڳ���Ŀ������ID
		String orderId = request.getParameter("id");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbZcotherOrder)this.getCommonBo().getEvict(TbZcotherOrder.class, new Long(orderId));
			}catch(Exception e){}
		}
		if(bean==null){
    		// ����״̬����������
    		request.setAttribute("result", "error");
    		// ����
        	return ERROR;
		}
		// ������������������޸ĸ��������Ϣ
		if(bean.getOrderStatus()==null || !"1".equals(bean.getOrderStatus())){
	        // �����ڳ���Ŀ
	        String projName = request.getParameter("projName");
	        if(projName!=null){
	        	if("".equals(projName)){
	            	// ����״̬����������
	            	request.setAttribute("result", "projError");
	            	// ����
	            	return ERROR;
	            }
		        bean.setProjName(projName);
	        }
	        // �����ڳ���Ŀ����URL
	        String projUrl = request.getParameter("projUrl");
	        if(projUrl!=null){
	        	bean.setProjUrl(projUrl);
	        }
			// �������ܽ��
			String orderAmout = request.getParameter("orderAmout");
			if(orderAmout!=null){
				if("".equals(orderAmout)){
					// ����״̬����������
					request.setAttribute("result", "orderAmoutError");
					// ����
					return ERROR;
				}
				BigDecimal orderAmoutD = null;
				try{
					orderAmoutD = new BigDecimal(orderAmout).setScale(2, 4);
				}catch(Exception e){}
				if(orderAmoutD==null || orderAmoutD.doubleValue()<=0){
					// ����״̬����������
					request.setAttribute("result", "orderAmoutError");
					// ����
					return ERROR;
				}
				bean.setOrderAmount(orderAmoutD);
			}
	        // ��������
	        bean.setOrderAmountType("�����");
	        // �������ֽ��
	        bean.setOrderAmountView(bean.getOrderAmount());
	        // �������ֽ�������λ
	        bean.setOrderAmountUnit("Ԫ");
	        // ������֧����ʽ
	    	String orderType = request.getParameter("orderType");
	    	if(orderType!=null){
	    		bean.setOrderType(orderType);
	    	}
	    	// �Ƿ���¾������ύʱ��
			String orderTimeUpdate = request.getParameter("orderTimeUpdate");
			if(orderTimeUpdate!=null && ("1".equals(orderTimeUpdate) || "true".equals(orderTimeUpdate))){
				// ϵͳ��ǰʱ��
				Date dateNow = this.getCommonBo().getSysDate();
		    	bean.setOrderTime(dateNow);
			}
	        // �����Ƿ���Ҫ֤��
	    	String needZhengshu = request.getParameter("needZhengshu");
	    	if(needZhengshu!=null){
	    		if(!"1".equals(needZhengshu)){
	        		needZhengshu = "0";
	        	}
	    		bean.setNeedZhengshu(needZhengshu);
	    	}
	        // �����Ƿ���Ҫ��Ʊ
	    	String needFapiao = request.getParameter("needFapiao");
	    	if(needFapiao!=null){
	    		if(!"1".equals(needFapiao)){
	    			needFapiao = "0";
	        	}
	    		bean.setNeedFapiao(needFapiao);
	    	}
			// ��������Ϣ
			// ����������
	    	String personName = request.getParameter("personName");
	    	if(personName!=null){
	    		if("".equals(personName)){
	    			personName = "����";
	    		}
	    		bean.setPersonName(personName);
	    	}
			// ��������
	    	String niMing = request.getParameter("niMing");
	    	if(niMing!=null){
	    		if(!"1".equals(niMing)){
	    			niMing = "0";
	        	}
	    		bean.setNiMing(niMing);
	    	}
			// ������IP
	    	String personIp = request.getParameter("personIp");
	    	if(personIp!=null){
	    		bean.setPersonIp(personIp);
	    	}
			// �������Ա�
	    	String personSex = request.getParameter("personSex");
	    	if(personSex!=null && ("1".equals(personSex) || "2".equals(personSex))){
	    		bean.setPersonSex(personSex);
	    	}
			// ����������
	    	String personEmail = request.getParameter("personEmail");
	    	if(personEmail!=null){
	    		bean.setPersonEmail(personEmail);
	    	}
			// �������ֻ�
	    	String personPhone = request.getParameter("personPhone");
	    	if(personPhone!=null){
	    		bean.setPersonPhone(personPhone);
	    	}
			// �����˵绰
	    	String personTel = request.getParameter("personTel");
	    	if(personTel!=null){
	    		bean.setPersonTel(personTel);
	    	}
			// ��ַ��Ϣ
	    	// ��ַ�ʱ�
	    	String addressZip = request.getParameter("addressZip");
	    	if(addressZip!=null){
	    		bean.setAddressZip(addressZip);
	    	}
	    	// ��ַʡ
	    	String addressSheng = request.getParameter("addressSheng");
	    	if(addressSheng!=null){
	    		bean.setAddressSheng(addressSheng);
	    	}
	    	// ��ַ��
	    	String addressShi = request.getParameter("addressShi");
	    	if(addressShi!=null){
	    		bean.setAddressShi(addressShi);
	    	}
	    	// ��ַ��
	    	String addressQu = request.getParameter("addressQu");
	    	if(addressQu!=null){
	    		bean.setAddressQu(addressQu);
	    	}
	    	// ��ַ����
	    	String addressDetail = request.getParameter("addressDetail");
	    	if(addressDetail!=null){
	    		bean.setAddressDetail(addressDetail);
	    	}
	    	// У����Ϣ
	    	// �Ƿ�У��
	    	String alumniFlag = request.getParameter("alumniFlag");
	    	if(alumniFlag!=null){
	    		if(!"1".equals(alumniFlag)){
	    			alumniFlag = "0";
	        	}
	    		bean.setAlumniFlag(alumniFlag);
	    	}
	    	// ��ѧ��
	    	String studyYearin = request.getParameter("studyYearin");
	    	if(studyYearin!=null){
	        	bean.setStudyYearin(studyYearin);
	    	}
	    	// ��У��
	    	String studyYearover = request.getParameter("studyYearover");
	    	if(studyYearover!=null){
	        	bean.setStudyYearover(studyYearover);
	    	}
	    	// Ժϵ
	    	String studyAcademy = request.getParameter("studyAcademy");
	    	if(studyAcademy!=null){
	        	bean.setStudyAcademy(studyAcademy);
	    	}
	    	// רҵ
	    	String studyMajor = request.getParameter("studyMajor");
	    	if(studyMajor!=null){
	        	bean.setStudyMajor(studyMajor);
	    	}
	    	// �༶
	    	String studyClass = request.getParameter("studyClass");
	    	if(studyClass!=null){
	        	bean.setStudyClass(studyClass);
	    	}
	    	// ѧ��
	    	String studyNum = request.getParameter("studyNum");
	    	if(studyNum!=null){
	        	bean.setStudyNum(studyNum);
	    	}
	    	// ѧ����У����ݣ�
	    	String studyDegree = request.getParameter("studyDegree");
	    	if(studyDegree!=null){
	        	bean.setStudyDegree(studyDegree);
	    	}
	    	// ������Ϣ
	    	// ������λ
	    	String workCompany = request.getParameter("workCompany");
	    	if(workCompany!=null){
	        	bean.setWorkCompany(workCompany);
	    	}
	    	// ��������
	    	String workDepart = request.getParameter("workDepart");
	    	if(workDepart!=null){
	        	bean.setWorkDepart(workDepart);
	    	}
	    	// ����ְ��
	    	String workDuty = request.getParameter("workDuty");
	    	if(workDuty!=null){
	        	bean.setWorkDuty(workDuty);
	    	}
		}
		// ���Ӿ�����״̬�������������ɸ���
    	// У�������û�
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
    	// ��������ע��Ϣ
    	String orderMemo = request.getParameter("orderMemo");
    	if(orderMemo!=null){
        	bean.setOrderMemo(orderMemo);
    	}
    	// ��������ִ��Ϣ
    	String receipt = request.getParameter("receipt");
    	if(receipt!=null){
        	bean.setReceipt(receipt);
    	}
        // ����
    	try{
    		bean = (TbZcotherOrder)this.getCommonBo().update(bean);
    		request.setAttribute("bean", bean);
    		// ����
        	return SUCCESS;
    	}catch(Exception e){}
		// ����״̬������ʧ��
		request.setAttribute("result", "failure");
		// ����
    	return ERROR;
	}
	
	// �����ڳ���Ŀ����������״̬�޸�Ϊ�Ѹ���
	public String setStatusOk() throws Exception {
		HttpServletRequest request = this.getRequest();
		// �����ڳ���Ŀ������
		TbZcotherOrder bean = null;
		// �����ڳ���Ŀ������ID
		String orderId = request.getParameter("id");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbZcotherOrder)this.getCommonBo().getEvict(TbZcotherOrder.class, new Long(orderId));
			}catch(Exception e){}
		}
		if(bean==null){
    		// ����״̬����������
    		request.setAttribute("result", "error");
    		// ����
        	return ERROR;
		}
		if(bean.getOrderStatus()!=null && "1".equals(bean.getOrderStatus())){
    		// ����״̬���Ѹ���
    		request.setAttribute("result", "payed");
    		// ����
        	return ERROR;
		}
		try{
			// ֧����ʽ
			String orderType = request.getParameter("orderType");
			if(orderType!=null && !"".equals(orderType)){
				bean.setOrderType(orderType);
			}
			// ������״̬�޸�Ϊ�Ѹ���
			bean.setOrderStatus("1");
			// ϵͳʱ��
			Date dateNow = this.getCommonBo().getSysDate();
			// �������������ʱ��
			bean.setOrderOkTime(dateNow);
			// ����
    		bean = (TbZcotherOrder)this.getCommonBo().update(bean);
    		request.setAttribute("bean", bean);
    		// ����
        	return SUCCESS;
    	}catch(Exception e){}
		// ����״̬������ʧ��
		request.setAttribute("result", "failure");
		// ����
    	return ERROR;
	}
	
	// �����ڳ���Ŀ��������ӻ�ִ��Ϣ
	public String addReceipt() throws Exception {
		HttpServletRequest request = this.getRequest();
		// �����ڳ���Ŀ������
		TbZcotherOrder bean = null;
		// �����ڳ���Ŀ������ID
		String orderId = request.getParameter("id");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbZcotherOrder)this.getCommonBo().getEvict(TbZcotherOrder.class, new Long(orderId));
			}catch(Exception e){}
		}
		if(bean==null){
    		// ����״̬����������
    		request.setAttribute("result", "error");
    		// ����
        	return ERROR;
		}
		try{
			// ��Ҫ��ӵĻ�ִ��Ϣ
			String receipt = request.getParameter("receipt");
			if(receipt!=null && !"".equals(receipt)){
				String receiptOld = bean.getReceipt();
				if(receiptOld==null){
					receiptOld = "";
				}
				// ��ִ��Ϣ׷��λ��
				String position = request.getParameter("position");
				if(position==null || !"top".equals(position)){
					bean.setReceipt(receiptOld+receipt);
				}else{
					bean.setReceipt(receipt+receiptOld);
				}
				// ����
	    		bean = (TbZcotherOrder)this.getCommonBo().update(bean);
	    		request.setAttribute("bean", bean);
			}
    		// ����
        	return SUCCESS;
    	}catch(Exception e){}
		// ����״̬������ʧ��
		request.setAttribute("result", "failure");
		// ����
    	return ERROR;
	}
	
	// �����ڳ���Ŀ������״̬������ɾ��
	public String delete() throws Exception {
		HttpServletRequest request = this.getRequest();
		// �����ڳ���Ŀ������
		TbZcotherOrder bean = null;
		// �����ڳ���Ŀ������ID
		String orderId = request.getParameter("id");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbZcotherOrder)this.getCommonBo().get(TbZcotherOrder.class, new Long(orderId));
			}catch(Exception e){}
		}
		if(bean==null){
    		// ����״̬����������
    		request.setAttribute("result", "error");
    		// ����
        	return ERROR;
		}
		if(bean.getOrderStatus()!=null && "1".equals(bean.getOrderStatus())){
    		// ����״̬���Ѹ���
    		request.setAttribute("result", "payed");
    		// ����
        	return ERROR;
		}
		try{
			// ɾ��
			this.getCommonBo().delete(bean);
    		request.setAttribute("bean", bean);
    		// ����
        	return SUCCESS;
    	}catch(Exception e){}
		// ����״̬��ɾ��ʧ��
		request.setAttribute("result", "failure");
		// ����
    	return ERROR;
	}
}