package com.laungee.proj.commodity.web.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbCommodity;
import com.laungee.proj.common.model.TbCommodityDetail;
import com.laungee.proj.common.model.TbCommodityOrder;
import com.laungee.proj.common.model.TbCommodityParam;
import com.laungee.proj.common.model.TbCommodityPic;
import com.laungee.proj.common.model.TbCommodityType;
import com.laungee.proj.common.model.TbZcproj;
import com.laungee.proj.common.model.TbZcprojOrder;
import com.laungee.proj.common.model.TbZcprojPic;
import com.laungee.proj.common.util.DateUtil;
import com.laungee.proj.common.util.MultiRequestUtil;
import com.laungee.proj.common.web.FileManager;

public class CommodityAction extends BaseAction {
	public String execute() throws Exception {
		HttpServletRequest request=this.getRequest();
		// �˵����
		request.setAttribute("num", request.getAttribute("num"));
		// ���Ա��
		String id=request.getParameter("id");
		if(null==id || "".equals(id)){
			id="1";
		}
		// ��ѯ����
		TbCommodityType bean = null;
		try {
			bean=(TbCommodityType)getCommonBo().get(TbCommodityType.class, new Long(id));
		} catch (Exception e) {	}
		request.setAttribute("bean", bean);
		// ��Ŀ����
		String hqlCount = "select count(*) from TbCommodity a where 1=1";
		int count = getCommonBo().getHQLNum(hqlCount);
		request.setAttribute("count", count);
		// ����
		return INPUT;
	}
	public String list() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ��ѯ����
		String hql="from TbCommodity a where 1=1";
		List pars = this.getList();
		//��������
		String commType=request.getParameter("commType");
		if(null!=commType && !"".equals(commType)){
			try {
				List beanList = this.getList();
				beanList.add(commType);
				getCommType(beanList,new Long(commType),"1");
				String typeIds = "";
				if(null!=beanList&&!beanList.isEmpty()&&beanList.size()>0){
					for(int i=0;i<beanList.size();i++){
						Long typeId = new Long(beanList.get(i).toString());
						typeIds+=typeId;
						if(i!=beanList.size()-1){
							typeIds+=",";
						}
					}
				}
				if(null!=typeIds&&!"".equals(typeIds)){
					hql += " and a.commType in ("+typeIds+")";
				}
			} catch (Exception e) {	}
			request.setAttribute("commType", commType);
		}
		// ��Ŀ����
		String hqlCount = "select count(*) "+hql;
		int count = getCommonBo().getHQLNum(hqlCount);
		request.setAttribute("count", count);
		//����
		String commName=request.getParameter("commName");
		if(null!=commName && !"".equals(commName)){
			hql += " and a.commName like ?";
			pars.add("%"+commName+"%");
			request.setAttribute("commName", commName);
		}
		//�ϼ�
		String isShelves=request.getParameter("isShelves");
		if(null!=isShelves && !"".equals(isShelves)){
			hql += " and a.isShelves=?";	
			pars.add(isShelves);
			request.setAttribute("isShelves", isShelves);
		}
		//����
		String isHot=request.getParameter("isHot");
		if(null!=isHot && !"".equals(isHot)){
			hql += " and a.isHot=?";	
			pars.add(isHot);
			request.setAttribute("isHot", isHot);
		}
		//��� 1���л� 0��ȱ��
		String stockNum=request.getParameter("stockNum");
		if(null!=stockNum && !"".equals(stockNum)){
			hql += " and";
			if(stockNum.equals("1")){
				request.setAttribute("stockNum", 1);
			}else if(stockNum.equals("0")){
				hql += " not";
				request.setAttribute("stockNum", 0);
			}
			hql += " exists( select 1 from TbCommodityDetail d where d.commId=a.commId and d.isDelete=0 and d.stockNum>0)";
		}
		hql += " order by a.creationTime";
		List beanList=getCommonBo().findHQLPage(hql,pars);
		request.setAttribute("beanList", beanList);
		// ����
		return "list";
	}
	private void getCommType(List list,Long parent,String type) throws Exception {
		String hql = "from TbCommodityType a where a.parentId="+parent+" order by a.numOrder";
		List beanList = this.getCommonBo().findHQL(hql);
		if(null!=beanList&&!beanList.isEmpty()){
			for(int i=0;i<beanList.size();i++){
				TbCommodityType bean = (TbCommodityType)beanList.get(i);
				if(type.equals("1")){
					list.add(bean.getTypeId()+"");
				}else if(type.equals("2")){
					list.add(bean);
				}
				getCommType(list,bean.getTypeId(),type);
			}
		}
	}
	// �ϼܡ��¼�
	public String shelves() throws Exception {
		HttpServletRequest request = this.getRequest();
		TbCommodity bean = null;
		String commId=request.getParameter("commId");
		//��������
		String commType=request.getParameter("commType");
		request.setAttribute("commType", commType);
		if(null!=commId && !"".equals(commId)){
			try {
				bean = (TbCommodity)this.getCommonBo().get(TbCommodity.class, new Long(commId));
			} catch (Exception e) {	}
		}
		String isShelves=request.getParameter("isShelves");
		if(null!=isShelves && !"".equals(isShelves)){
			bean.setIsShelves(isShelves);
		}
		this.getCommonBo().update(bean);
		return execute();
	}
	public String delete() throws Exception {
		HttpServletRequest request = this.getRequest();
		String commId = request.getParameter("commId");
		TbCommodity bean = null;
		if(null!=commId&&!"".equals(commId)){
			try {
				bean = (TbCommodity)this.getCommonBo().get(TbCommodity.class, new Long(commId));
			} catch (Exception e) {	}
		}
		if(null!=bean){
			String hql = "delete from TbCommodityPic a where a.commId="+bean.getCommId();
			this.getCommonBo().executeHql(hql);
			hql = "delete from TbCommodityParam a where a.commId="+bean.getCommId();
			this.getCommonBo().executeHql(hql);
			hql = "delete from TbCommodityDetail a where a.commId="+bean.getCommId();
			this.getCommonBo().executeHql(hql);
			hql = "delete from TbCommodityOrder a where a.commId="+bean.getCommId();
			this.getCommonBo().executeHql(hql);
			this.getCommonBo().delete(bean);
			request.setAttribute("alert", "ɾ���ɹ�");
		}else{
			request.setAttribute("alert", "���󲻴��ڻ��ѱ�ɾ��");
		}
		return list();
	}
	// ����
	public String operation() throws Exception {
		HttpServletRequest request = this.getRequest();
		TbCommodity bean = null;
		String commId=request.getParameter("commId");
		if(null!=commId && !"".equals(commId)){
			try {
				bean = (TbCommodity)this.getCommonBo().get(TbCommodity.class, new Long(commId));
			} catch (Exception e) {	}
		}
		if(bean==null){
			bean = new TbCommodity();
			bean.setIsShelves("0");
			bean.setIsHot("0");
			bean.setIsShipping("0");
			bean.setIsDonation("0");
		}
		request.setAttribute("bean", bean);
		// ��Ʒ���༯��
		String hql="from TbCommodityType a where a.parentId=1 order by a.numOrder";
		List beanList = this.getList();
		List ctList = this.getCommonBo().findHQL(hql);
		if(null!=ctList&&!ctList.isEmpty()){
			for (int i = 0; i < ctList.size(); i++) {
				TbCommodityType ct = (TbCommodityType)ctList.get(i);
				beanList.add(ct);
				List tempList = this.getList();
				getCommType(tempList,ct.getTypeId(),"2");
				if(null!=tempList&&!tempList.isEmpty()&&tempList.size()>0){
					for (int j = 0; j < tempList.size(); j++) {
						TbCommodityType temp = (TbCommodityType)tempList.get(j);
						TbCommodityType ctTemp = new TbCommodityType();
						ctTemp.setTypeId(temp.getTypeId());
						ctTemp.setTypeName(ct.getTypeName()+" > "+temp.getTypeName());
						beanList.add(ctTemp);
					}
				}
			}
		}
		request.setAttribute("ctList", beanList);
		return "operation";
	}
	public String saveOrUpdate() throws Exception {
		HttpServletRequest request = this.getRequest();
		MultiRequestUtil multiRequest = new MultiRequestUtil(request);
		//����
		String commId=multiRequest.getParameter("commId");
		TbCommodity bean = new TbCommodity();
		if(null!=commId && !"".equals(commId)){
			try {
				bean = (TbCommodity)this.getCommonBo().get(TbCommodity.class, new Long(commId));
			} catch (Exception e) {	}
		}
		if(bean==null){
			bean = new TbCommodity();
			bean.setIsDelete("0");
		}
		//����
		String commName=multiRequest.getParameter("commName");
		bean.setCommName(commName);
		//��������
		String commType=multiRequest.getParameter("commType");
		if(null!=commType && !"".equals(commType)){
			try {
				bean.setCommType(new Long(commType));
			} catch (Exception e) {	}
		}
		//���
		String commIntro=multiRequest.getParameter("commIntro");
		bean.setCommIntro(commIntro);
		//����
		String commDetail=multiRequest.getParameter("commDetail");
		bean.setCommDetail(commDetail);

		//�Ƿ��ϼ�
		String isShelves=multiRequest.getParameter("isShelves");
		if(null==isShelves || !"1".equals(isShelves)){
			isShelves = "0";
		}
		bean.setIsShelves(isShelves);
		//�Ƿ�����
		String isHot=multiRequest.getParameter("isHot");
		if(null==isHot || !"1".equals(isHot)){
			isHot = "0";
		}
		bean.setIsHot(isHot);
		//��������
		String hotOrder=multiRequest.getParameter("hotOrder");
		bean.setHotOrder(hotOrder);
		//�Ƿ����˷� 1�����˷ѣ�0���˷�
		String isShipping=multiRequest.getParameter("isShipping");
		if(null==isShipping || !"1".equals(isShipping)){
			isShipping = "0";
		}
		bean.setIsShipping(isShipping);
		if("1".equals(isShipping)){
			// �˷�
			BigDecimal shippingFeeD = null;
			String shippingFee = multiRequest.getParameter("shippingFee");
			if(shippingFee!=null && !"".equals(shippingFee)){
				try{
					shippingFeeD = new BigDecimal(shippingFee).setScale(2, 4);
				}catch(Exception e){}
			}
			bean.setShippingFee(shippingFeeD);
			// �˷�˵��
			String shippingMemo = multiRequest.getParameter("shippingMemo");
			bean.setShippingMemo(shippingMemo);
		}else{
			bean.setShippingFee(null);
			bean.setShippingMemo(null);
		}
		//�Ƿ���� 1������0������
		String isDonation=multiRequest.getParameter("isDonation");
		if(null==isDonation || !"1".equals(isDonation)){
			isDonation = "0";
		}
		bean.setIsDonation(isDonation);
		if("1".equals(isDonation)){
			// ����
			BigDecimal donationFeeD = null;
			String donationFee = multiRequest.getParameter("donationFee");
			if(donationFee!=null && !"".equals(donationFee)){
				try{
					donationFeeD = new BigDecimal(donationFee).setScale(2, 4);
				}catch(Exception e){}
			}
			bean.setDonationFee(donationFeeD);
			// ����˵��
			String donationMemo = multiRequest.getParameter("donationMemo");
			bean.setDonationMemo(donationMemo);
		}else{
			bean.setDonationFee(null);
			bean.setDonationMemo(null);
		}
		// ��ע˵��
		String memo=multiRequest.getParameter("memo");
		bean.setMemo(memo);
		try{
			// ����
			if(bean.getCommId()==null){
				bean = (TbCommodity) this.getCommonBo().save(bean);
				request.setAttribute("opt", "insert");
				// ��ƷͼƬ
				FileItem item = multiRequest.getFile("detailPic");
				if(item!=null){
					InputStream bis = null;
					OutputStream out = null;
					try {
						// ��׺��
						String lastName="";
						int nIndex = item.getName().lastIndexOf(".");
						if (nIndex != -1) {
							lastName = item.getName().substring(nIndex + 1).toLowerCase();
						}
						if("gif".equals(lastName) || "jpg".equals(lastName) || "jpeg".equals(lastName) || "png".equals(lastName) || "bmp".equals(lastName)){
							/* �ϴ��ļ� ------------------------------------------- */
							String uploadPath = "upload/image/";
							// �ļ�����Ŀ¼·��
							String savePath = request.getSession().getServletContext()
									.getRealPath("/" + uploadPath)
									+ "/";
							// ���Ŀ¼
							File uploadDir = new File(savePath);
							if (!uploadDir.exists()) {
								uploadDir.mkdirs();
							}
							Date dateNow = this.getCommonBo().getSysDate();
							String ymd = DateUtil.format(dateNow, "yyyyMMdd");
							savePath += ymd + "/";
							uploadPath += ymd + "/";
							File dirFile = new File(savePath);
							if (!dirFile.exists()) {
								dirFile.mkdirs();
							}
							// �ļ�·��
							String fileName = DateUtil.format(dateNow, "yyyyMMddHHmmss")+"."+lastName;
							savePath += fileName;
							uploadPath += fileName;
							
							bis = new BufferedInputStream(item.getInputStream());
							// �����
							File file = new File(savePath);
							out = new BufferedOutputStream(new FileOutputStream(file));
							byte[] bytes = new byte[1];
							while (bis.read(bytes) != -1) {
								out.write(bytes);
							}
							// ��ƷͼƬ
							TbCommodityPic beanPic = new TbCommodityPic();
							beanPic.setCommId(bean.getCommId());
							beanPic.setPicPath(uploadPath);
							beanPic.setNumOrder("1");
							// ����ͼƬ
							this.getCommonBo().save(beanPic);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						try {
							if(null!=bis){
								bis.close();
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							if(null!=out){
								out.close();
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				// ��Ʒ�ͺ�
				TbCommodityDetail beanDetail = new TbCommodityDetail();
				beanDetail.setCommId(bean.getCommId());
				beanDetail.setDetailName("Ĭ��");
				beanDetail.setIsDelete("0");
				beanDetail.setIsShelves("1");
				beanDetail.setNumOrder("1");
				// ���
				Long stockNumL = null;
				String stockNum = multiRequest.getParameter("stockNum");
				if(stockNum!=null && !"".equals(stockNum)){
					try{
						stockNumL = new Long(stockNum);
					}catch(Exception e){}
				}
				beanDetail.setStockNum(stockNumL);
				// �ɱ���
				BigDecimal costFeeD = null;
				String costFee = multiRequest.getParameter("costFee");
				if(costFee!=null && !"".equals(costFee)){
					try{
						costFeeD = new BigDecimal(costFee).setScale(2, 4);
					}catch(Exception e){}
				}
				beanDetail.setCostFee(costFeeD);
				// �ۼ�
				BigDecimal saleFeeD = null;
				String saleFee = multiRequest.getParameter("saleFee");
				if(saleFee!=null && !"".equals(saleFee)){
					try{
						saleFeeD = new BigDecimal(saleFee).setScale(2, 4);
					}catch(Exception e){}
				}
				beanDetail.setSaleFee(saleFeeD);
				// �����ͺ�
				this.getCommonBo().save(beanDetail);
			}else{
				bean = (TbCommodity) this.getCommonBo().update(bean);
			}
			// ���¸������
			String relIds = multiRequest.getParameter("relIds");
			FileManager.getInstance().update(bean.getCommId(),relIds);
			
			// ����
			request.setAttribute("commId", bean.getCommId());
			return "choice";
		}catch(Exception e){}
		// ����ʧ��
		request.setAttribute("bean", bean);
		// ��Ʒ���༯��
		String hql="from TbCommodityType a where a.parentId=1 order by a.numOrder";
		List beanList = this.getList();
		List ctList = this.getCommonBo().findHQL(hql);
		if(null!=ctList&&!ctList.isEmpty()){
			for (int i = 0; i < ctList.size(); i++) {
				TbCommodityType ct = (TbCommodityType)ctList.get(i);
				beanList.add(ct);
				List tempList = this.getList();
				getCommType(tempList,ct.getTypeId(),"2");
				if(null!=tempList&&!tempList.isEmpty()&&tempList.size()>0){
					for (int j = 0; j < tempList.size(); j++) {
						TbCommodityType temp = (TbCommodityType)tempList.get(j);
						TbCommodityType ctTemp = new TbCommodityType();
						ctTemp.setTypeId(temp.getTypeId());
						ctTemp.setTypeName(ct.getTypeName()+" > "+temp.getTypeName());
						beanList.add(ctTemp);
					}
				}
			}
		}
		request.setAttribute("ctList", beanList);
		return "operation";
	}
	public String info() throws Exception {
		HttpServletRequest request = this.getRequest();
		String num=request.getParameter("num");
		request.setAttribute("num", num);
		//����
		String commId=request.getParameter("commId");
		if(null==num || "".equals(num)){
			num = "1";
		}
		TbCommodity bean = null;
		if(null!=commId && !"".equals(commId)){
			try {
				bean = (TbCommodity)this.getCommonBo().get(TbCommodity.class, new Long(commId));
			} catch (Exception e) {	}
		}
		request.setAttribute("bean", bean);
		if(bean!=null){
			if(num.equals("1")){//��ƷͼƬ
				String hql = "from TbCommodityPic a where a.commId="+bean.getCommId()+" order by a.numOrder";
				List beanList = this.getCommonBo().findHQL(hql);
				request.setAttribute("beanList", beanList);
				return "pic";
			}else if(num.equals("2")){//��Ʒ����
				String hql = "from TbCommodityParam a where a.commId="+bean.getCommId()+" order by a.numOrder";
				List beanList = this.getCommonBo().findHQL(hql);
				request.setAttribute("beanList", beanList);
				return "param";
			}else if(num.equals("3")){//��Ʒ�ͺ�
				String hql = "from TbCommodityDetail a where a.commId="+bean.getCommId();
				//����
				String detailName=request.getParameter("detailName");
				if(null!=detailName&&!"".equals(detailName)){
					hql+=" and a.detailName like '%"+detailName+"%'";
					request.setAttribute("detailName", detailName);
				}
				String stockNum=request.getParameter("stockNum");
				if(null!=stockNum&&!"".equals(stockNum)){
					if("1".equals(stockNum)){
						hql+=" and a.stockNum>0";
						request.setAttribute("stockNum", 1);
					}else if("0".equals(stockNum)){
						hql+=" and (a.stockNum=0 or a.stockNum is null)";
						request.setAttribute("stockNum", 0);
					}
				}
				String isShelves=request.getParameter("isShelves");
				if(null!=isShelves&&!"".equals(isShelves)){
					hql+=" and a.isShelves="+isShelves;
					request.setAttribute("isShelves", isShelves);
				}
				hql+=" order by a.numOrder";
				List beanList = this.getCommonBo().findHQLPage(hql);
				request.setAttribute("beanList", beanList);
				return "detail";
			}else if(num.equals("4")){//��Ʒ����
				String hql = "from TbCommodityOrder a where a.commId="+bean.getCommId();
				String orderNo = request.getParameter("orderNo");
				if(null!=orderNo&&!"".equals(orderNo)){
					hql+=" and a.orderNo like '%"+orderNo+"%'";
				}
				String orderType = request.getParameter("orderType");
				if(orderType!=null && !"".equals(orderType)){
					hql += " and a.orderType like '%"+orderType+"%'";
				}
				String orderStatus = request.getParameter("orderStatus");
				if(orderStatus!=null && "1".equals(orderStatus)){
					hql += " and a.orderStatus = '1'";
				}else if(orderStatus!=null && !"".equals(orderStatus)){
					hql += " and a.orderStatus <> '1'";
				}
				// ���
				String markFlag = request.getParameter("markFlag");
				if(markFlag!=null && "1".equals(markFlag)){
					hql += " and a.mark is not null";
					String mark = request.getParameter("mark");
					if(mark!=null && !"".equals(mark)){
						hql += " and a.mark like '%"+mark+"%'";
					}
				}else if(markFlag!=null && !"".equals(markFlag)){
					hql += " and a.mark is null";
				}
				hql += " order by a.orderTime desc";
				List orderList = this.getCommonBo().findHQLPage(hql);
				request.setAttribute("orderList", orderList);
				return "orderList";
			}
		}
		return operation();
	}
//	// �ƶ�ͼƬ
//	public String movePic() throws Exception {
//		HttpServletRequest request = this.getRequest();
//		String picId=request.getParameter("picId");
//		TbCommodityPic bean = null;
//		if(null!=picId&&!"".equals(picId)){
//			try {
//				bean = (TbCommodityPic)this.getCommonBo().get(TbCommodityPic.class, new Long(picId));
//			} catch (Exception e) {	}
//		}
//		String commId=request.getParameter("commId");
//		request.setAttribute("commId", commId);
//		String num=request.getParameter("num");
//		if(null==num||"".equals(num)){
//			num = "1";
//		}
//		request.setAttribute("num", num);
//		//���� 1������ 2������
//		String type=request.getParameter("type");
//		if(null==type || "".equals(type)){
//			type="1";
//		}
//		String hql = "from TbCommodityPic a where a.commId="+bean.getCommId()+" order by a.numOrder";
//		List beanList = this.getCommonBo().findHQL(hql);
//		TbCommodityPic temp = new TbCommodityPic();
//		for (int i = 0; i < beanList.size(); i++) {
//			temp = (TbCommodityPic)beanList.get(i);
//			if(temp==bean){
//				if(type.equals("1")&&i!=0){//����
//					temp = (TbCommodityPic)beanList.get(i-1);
//					Long numOrderQ = temp.getNumOrder();
//					Long numOrderH = bean.getNumOrder();
//					temp.setNumOrder(numOrderH);
//					bean.setNumOrder(numOrderQ);
//					break;
//				}
//				if(type.equals("2")&&i!=beanList.size()-1){
//					temp = (TbCommodityPic)beanList.get(i+1);
//					Long numOrderQ = temp.getNumOrder();
//					Long numOrderH = bean.getNumOrder();
//					temp.setNumOrder(numOrderH);
//					bean.setNumOrder(numOrderQ);
//					break;
//				}
//			}
//		}
//		this.getCommonBo().update(bean);
//		this.getCommonBo().update(temp);
//		request.setAttribute("bean", bean);
//		return info();
//	}
	// ����ͼƬ
	public String operationPic() throws Exception {
		HttpServletRequest request = this.getRequest();
		String picId=request.getParameter("picId");
		TbCommodityPic bean = null;
		if(null!=picId&&!"".equals(picId)){
			try {
				bean = (TbCommodityPic)this.getCommonBo().get(TbCommodityPic.class, new Long(picId));
			} catch (Exception e) {	}
		}
		if(bean==null){
			bean = new TbCommodityPic();
			// ��ƷId
			Long commIdL = null;
			String commId=request.getParameter("commId");
			if(commId!=null && !"".equals(commId)){
				try{
					commIdL = new Long(commId);
				}catch(Exception e){}
			}
			if(commIdL!=null){
				bean.setCommId(commIdL);
				// ͼƬ����
				String hqlCount = "select count(*) from TbCommodityPic a where a.commId="+commIdL;
				int count = this.getCommonBo().getHQLNum(hqlCount);
				bean.setNumOrder((count+1)+"");
			}
		}
		request.setAttribute("bean", bean);
		return "operationPic";
	}
	public String saveOrUpdatePic() throws Exception {
		HttpServletRequest request = this.getRequest();
		MultiRequestUtil multiRequest = new MultiRequestUtil(request);
		// ��ƷͼƬ
		TbCommodityPic bean = null;
		// ��ƷͼƬID
		String picId = multiRequest.getParameter("picId");
		if(picId!=null && !"".equals(picId)){
			try{
				bean = (TbCommodityPic)this.getCommonBo().get(TbCommodityPic.class, new Long(picId));
			}catch(Exception e){}
		}
		if(bean==null){
			bean = new TbCommodityPic();
			// ��ƷId
			Long commIdL = null;
			String commId=multiRequest.getParameter("commId");
			if(commId!=null && !"".equals(commId)){
				try{
					commIdL = new Long(commId);
				}catch(Exception e){}
			}
			bean.setCommId(commIdL);
		}
		if(bean.getCommId()!=null){
			// ͼƬ�ϴ�
			FileItem item = multiRequest.getFile("pic");
			if(item!=null){
				InputStream bis = null;
				OutputStream out = null;
				try {
					// ��׺��
					String lastName="";
					int nIndex = item.getName().lastIndexOf(".");
					if (nIndex != -1) {
						lastName = item.getName().substring(nIndex + 1).toLowerCase();
					}
					if("gif".equals(lastName) || "jpg".equals(lastName) || "jpeg".equals(lastName) || "png".equals(lastName) || "bmp".equals(lastName)){
						/* �ϴ��ļ� ------------------------------------------- */
						String uploadPath = "upload/image/";
						// �ļ�����Ŀ¼·��
						String savePath = request.getSession().getServletContext()
								.getRealPath("/" + uploadPath)
								+ "/";
						// ���Ŀ¼
						File uploadDir = new File(savePath);
						if (!uploadDir.exists()) {
							uploadDir.mkdirs();
						}
						Date dateNow = this.getCommonBo().getSysDate();
						String ymd = DateUtil.format(dateNow, "yyyyMMdd");
						savePath += ymd + "/";
						uploadPath += ymd + "/";
						File dirFile = new File(savePath);
						if (!dirFile.exists()) {
							dirFile.mkdirs();
						}
						// �ļ�·��
						String fileName = DateUtil.format(dateNow, "yyyyMMddHHmmss")+"."+lastName;
						savePath += fileName;
						uploadPath += fileName;
						
						bis = new BufferedInputStream(item.getInputStream());
						// �����
						File file = new File(savePath);
						out = new BufferedOutputStream(new FileOutputStream(file));
						byte[] bytes = new byte[1];
						while (bis.read(bytes) != -1) {
							out.write(bytes);
						}
						// ��ĿͼƬ
						bean.setPicPath(uploadPath);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					try {
						if(null!=bis){
							bis.close();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						if(null!=out){
							out.close();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			// ͼƬ����
			String numOrder = multiRequest.getParameter("numOrder");
			bean.setNumOrder(numOrder);
			// ͼƬ˵��
			String picAlt = multiRequest.getParameter("picAlt");
			bean.setPicAlt(picAlt);
			// ��ע˵��
			String memo = multiRequest.getParameter("memo");
			bean.setMemo(memo);
			// ����ͼƬ
			try{
				this.getCommonBo().saveOrUpdate(bean);
			} catch (Exception e) {
				// ����
				request.setAttribute("alert", "����ʱ�����쳣��");
				request.setAttribute("bean", bean);
				return "operationPic";
			}
		}
		// ����
		return "winSuccess";
	}
	// ɾ��ͼƬ
	public String deletePic() throws Exception {
		HttpServletRequest request = this.getRequest();
		String num=request.getParameter("num");
		if(null==num||"".equals(num)){
			num = "1";
		}
		request.setAttribute("num", num);
		String picId=request.getParameter("picId");
		TbCommodityPic bean = null;
		if(null!=picId && !"".equals(picId)){
			try {
				bean = (TbCommodityPic)this.getCommonBo().get(TbCommodityPic.class, new Long(picId));
			} catch (Exception e) {	}
		}
		if(bean!=null){
			this.getCommonBo().delete(bean);
		}
		return info();
	}
//	// �ƶ�����
//	public String moveParam() throws Exception {
//		HttpServletRequest request = this.getRequest();
//		String paramId=request.getParameter("paramId");
//		TbCommodityParam bean = null;
//		if(null!=paramId&&!"".equals(paramId)){
//			try {
//				bean = (TbCommodityParam)this.getCommonBo().get(TbCommodityParam.class, new Long(paramId));
//			} catch (Exception e) {	}
//		}
//		String commId=request.getParameter("commId");
//		request.setAttribute("commId", commId);
//		String num=request.getParameter("num");
//		if(null==num||"".equals(num)){
//			num = "1";
//		}
//		request.setAttribute("num", num);
//		//���� 1������ 2������
//		String type=request.getParameter("type");
//		if(null==type || "".equals(type)){
//			type="1";
//		}
//		String hql = "from TbCommodityParam a where a.commId="+bean.getCommId()+" order by a.numOrder";
//		List beanList = this.getCommonBo().findHQL(hql);
//		TbCommodityParam temp = new TbCommodityParam();
//		for (int i = 0; i < beanList.size(); i++) {
//			temp = (TbCommodityParam)beanList.get(i);
//			if(temp==bean){
//				if(type.equals("1")&&i!=0){//����
//					temp = (TbCommodityParam)beanList.get(i-1);
//					Long numOrderQ = temp.getNumOrder();
//					Long numOrderH = bean.getNumOrder();
//					temp.setNumOrder(numOrderH);
//					bean.setNumOrder(numOrderQ);
//					break;
//				}
//				if(type.equals("2")&&i!=beanList.size()-1){
//					temp = (TbCommodityParam)beanList.get(i+1);
//					Long numOrderQ = temp.getNumOrder();
//					Long numOrderH = bean.getNumOrder();
//					temp.setNumOrder(numOrderH);
//					bean.setNumOrder(numOrderQ);
//					break;
//				}
//			}
//		}
//		this.getCommonBo().update(bean);
//		this.getCommonBo().update(temp);
//		request.setAttribute("bean", bean);
//		return info();
//	}
	// ��������
	public String operationParam() throws Exception {
		HttpServletRequest request = this.getRequest();
		String paramId=request.getParameter("paramId");
		TbCommodityParam bean = null;
		if(null!=paramId&&!"".equals(paramId)){
			try {
				bean = (TbCommodityParam)this.getCommonBo().get(TbCommodityParam.class, new Long(paramId));
			} catch (Exception e) {	}
		}
		if(bean==null){
			bean = new TbCommodityParam();
			// ��ƷId
			Long commIdL = null;
			String commId=request.getParameter("commId");
			if(commId!=null && !"".equals(commId)){
				try{
					commIdL = new Long(commId);
				}catch(Exception e){}
			}
			if(commIdL!=null){
				bean.setCommId(commIdL);
				// ͼƬ����
				String hqlCount = "select count(*) from TbCommodityParam a where a.commId="+commIdL;
				int count = this.getCommonBo().getHQLNum(hqlCount);
				bean.setNumOrder((count+1)+"");
			}
		}
		request.setAttribute("bean", bean);
		return "operationParam";
	}
	// ��������
	public String saveOrUpdateParam() throws Exception {
		HttpServletRequest request = this.getRequest();
		String paramId=request.getParameter("paramId");
		TbCommodityParam bean = null;
		if(null!=paramId&&!"".equals(paramId)){
			try {
				bean = (TbCommodityParam)this.getCommonBo().get(TbCommodityParam.class, new Long(paramId));
			} catch (Exception e) {	}
		}
		if(bean==null){
			bean = new TbCommodityParam();
			// ��ƷId
			Long commIdL = null;
			String commId=request.getParameter("commId");
			if(commId!=null && !"".equals(commId)){
				try{
					commIdL = new Long(commId);
				}catch(Exception e){}
			}
			bean.setCommId(commIdL);
		}
		if(bean.getCommId()!=null){
			String paramName=request.getParameter("paramName");
			bean.setParamName(paramName);
			String paramValue=request.getParameter("paramValue");
			bean.setParamValue(paramValue);
			String numOrder=request.getParameter("numOrder");
			bean.setNumOrder(numOrder);
			String memo=request.getParameter("memo");
			bean.setMemo(memo);
			// ����
			try{
				this.getCommonBo().saveOrUpdate(bean);
			} catch (Exception e) {
				// ����
				request.setAttribute("alert", "����ʱ�����쳣��");
				request.setAttribute("bean", bean);
				return "operationParam";
			}
		}
		// ����
		return "winSuccess";
	}
	// ɾ��ͼƬ
	public String deleteParam() throws Exception {
		HttpServletRequest request = this.getRequest();
		String paramId=request.getParameter("paramId");
		TbCommodityParam bean = null;
		if(null!=paramId&&!"".equals(paramId)){
			try {
				bean = (TbCommodityParam)this.getCommonBo().get(TbCommodityParam.class, new Long(paramId));
			} catch (Exception e) {	}
		}
		if(bean!=null){
			this.getCommonBo().delete(bean);
		}
		
		return info();
	}
	// ��������
	public String operationDetail() throws Exception {
		HttpServletRequest request = this.getRequest();
		String detailId=request.getParameter("detailId");
		TbCommodityDetail bean = null;
		if(null!=detailId&&!"".equals(detailId)){
			try {
				bean = (TbCommodityDetail)this.getCommonBo().get(TbCommodityDetail.class, new Long(detailId));
			} catch (Exception e) {	}
		}
		if(bean==null){
			bean = new TbCommodityDetail();
			// ��ƷId
			Long commIdL = null;
			String commId=request.getParameter("commId");
			if(commId!=null && !"".equals(commId)){
				try{
					commIdL = new Long(commId);
				}catch(Exception e){}
			}
			if(commIdL!=null){
				bean.setCommId(commIdL);
				// ����
				String hqlCount = "select count(*) from TbCommodityDetail a where a.commId="+commIdL;
				int count = this.getCommonBo().getHQLNum(hqlCount);
				bean.setNumOrder((count+1)+"");
			}
			bean.setIsShelves("0");
		}
		request.setAttribute("bean", bean);
		return "operationDetail";
	}
	public String saveOrUpdateDetail() throws Exception {
		HttpServletRequest request = this.getRequest();
		MultiRequestUtil multiRequest = new MultiRequestUtil(request);
		
		String detailId=multiRequest.getParameter("detailId");
		TbCommodityDetail bean = null;
		if(null!=detailId&&!"".equals(detailId)){
			try {
				bean = (TbCommodityDetail)this.getCommonBo().get(TbCommodityDetail.class, new Long(detailId));
			} catch (Exception e) {	}
		}
		if(bean==null){
			bean = new TbCommodityDetail();
			// ��ƷId
			Long commIdL = null;
			String commId=multiRequest.getParameter("commId");
			if(commId!=null && !"".equals(commId)){
				try{
					commIdL = new Long(commId);
				}catch(Exception e){}
			}
			bean.setCommId(commIdL);
			bean.setIsDelete("0");
		}
		if(bean.getCommId()!=null){
			//����
			String detailName=multiRequest.getParameter("detailName");
			bean.setDetailName(detailName);
			//���
			String detailIntro=multiRequest.getParameter("detailIntro");
			bean.setDetailIntro(detailIntro);
			//���
			Long stockNumL = null;
			String stockNum=multiRequest.getParameter("stockNum");
			try {
				stockNumL = new Long(stockNum);
			} catch (Exception e1) { }
			bean.setStockNum(stockNumL);
			// �ɱ���
			BigDecimal costFeeD = null;
			String costFee = multiRequest.getParameter("costFee");
			if(costFee!=null && !"".equals(costFee)){
				try{
					costFeeD = new BigDecimal(costFee).setScale(2, 4);
				}catch(Exception e){}
			}
			bean.setCostFee(costFeeD);
			// �ۼ�
			BigDecimal saleFeeD = null;
			String saleFee = multiRequest.getParameter("saleFee");
			if(saleFee!=null && !"".equals(saleFee)){
				try{
					saleFeeD = new BigDecimal(saleFee).setScale(2, 4);
				}catch(Exception e){}
			}
			bean.setSaleFee(saleFeeD);
			// ͼƬ�ϴ�
			FileItem item = multiRequest.getFile("detailPic");
			if(item!=null){
				InputStream bis = null;
				OutputStream out = null;
				try {
					// ��׺��
					String lastName="";
					int nIndex = item.getName().lastIndexOf(".");
					if (nIndex != -1) {
						lastName = item.getName().substring(nIndex + 1).toLowerCase();
					}
					if("gif".equals(lastName) || "jpg".equals(lastName) || "jpeg".equals(lastName) || "png".equals(lastName) || "bmp".equals(lastName)){
						/* �ϴ��ļ� ------------------------------------------- */
						String uploadPath = "upload/image/";
						// �ļ�����Ŀ¼·��
						String savePath = request.getSession().getServletContext()
								.getRealPath("/" + uploadPath)
								+ "/";
						// ���Ŀ¼
						File uploadDir = new File(savePath);
						if (!uploadDir.exists()) {
							uploadDir.mkdirs();
						}
						Date dateNow = this.getCommonBo().getSysDate();
						String ymd = DateUtil.format(dateNow, "yyyyMMdd");
						savePath += ymd + "/";
						uploadPath += ymd + "/";
						File dirFile = new File(savePath);
						if (!dirFile.exists()) {
							dirFile.mkdirs();
						}
						// �ļ�·��
						String fileName = DateUtil.format(dateNow, "yyyyMMddHHmmss")+"."+lastName;
						savePath += fileName;
						uploadPath += fileName;
						
						bis = new BufferedInputStream(item.getInputStream());
						// �����
						File file = new File(savePath);
						out = new BufferedOutputStream(new FileOutputStream(file));
						byte[] bytes = new byte[1];
						while (bis.read(bytes) != -1) {
							out.write(bytes);
						}
						// ͼƬ
						bean.setDetailPic(uploadPath);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					try {
						if(null!=bis){
							bis.close();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						if(null!=out){
							out.close();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			String isShelves=multiRequest.getParameter("isShelves");
			if(isShelves==null || !"1".equals(isShelves)){
				isShelves = "0";
			}
			bean.setIsShelves(isShelves);
			String numOrder=multiRequest.getParameter("numOrder");
			bean.setNumOrder(numOrder);
			String memo=multiRequest.getParameter("memo");
			bean.setMemo(memo);
			// ����
			try{
				this.getCommonBo().saveOrUpdate(bean);
			} catch (Exception e) {
				// ����
				request.setAttribute("alert", "����ʱ�����쳣��");
				request.setAttribute("bean", bean);
				return "operationDetail";
			}
		}
		// ����
		return "winSuccess";
	}
//	// �ͺ��ϼܡ��¼�
//	public String detailShelves() throws Exception {
//		HttpServletRequest request = this.getRequest();
//		String detailId=request.getParameter("detailId");
//		TbCommodityDetail bean = null;
//		if(null!=detailId&&!"".equals(detailId)){
//			try {
//				bean = (TbCommodityDetail)this.getCommonBo().get(TbCommodityDetail.class, new Long(detailId));
//			} catch (Exception e) {	}
//		}
//		if(bean!=null){
//			this.getCommonBo().delete(bean);
//		}
//		return info();
//	}
	// ɾ����Ʒ�ͺ�
	public String deleteDetail() throws Exception {
		HttpServletRequest request = this.getRequest();
		String detailId=request.getParameter("detailId");
		TbCommodityDetail bean = null;
		if(null!=detailId&&!"".equals(detailId)){
			try {
				bean = (TbCommodityDetail)this.getCommonBo().get(TbCommodityDetail.class, new Long(detailId));
			} catch (Exception e) {	}
		}
		if(bean!=null){
			this.getCommonBo().delete(bean);
		}
		return info();
	}
//	// �������޸Ķ���
//	public String operationOrder() throws Exception {
//		HttpServletRequest request = this.getRequest();
//		TbCommodityOrder bean = new TbCommodityOrder();
//		String orderId=request.getParameter("orderId");
//		if(null!=orderId&&!"".equals(orderId)){
//			try {
//				bean = (TbCommodityOrder)this.getCommonBo().get(TbCommodityOrder.class, new Long(orderId));
//				request.setAttribute("bean", bean);
//			} catch (Exception e) {}
//		}
//		String commId=request.getParameter("commId");
//		TbCommodity comm = null;
//		if(null!=commId && !"".equals(commId)){
//			try {
//				comm = (TbCommodity)this.getCommonBo().get(TbCommodity.class, new Long(commId));
//				request.setAttribute("comm",comm);
//			} catch (Exception e) {}
//		}else{
//			String hql="from TbCommodity a where a.isDelete=0 order by a.commName";
//			List commList = this.getCommonBo().findHQL(hql);
//			if(null!=commList&&!commList.isEmpty()&&commList.size()>0){
//				TbCommodity tbCommodity = (TbCommodity)commList.get(0);
//				comm = tbCommodity;
//				request.setAttribute("commList",commList);
//			}
//		}
//		String detailHql = "from TbCommodityDetail a where a.isDelete=0 and a.commId="+comm.getCommId()+" order by a.detailName";
//		List detailList = this.getCommonBo().findHQL(detailHql);
//		if(null!=detailList&&!detailList.isEmpty()&&detailList.size()>0){
//			TbCommodityDetail detail = (TbCommodityDetail)detailList.get(0);
//			request.setAttribute("detailList",detailList);
//			request.setAttribute("detail",detail);
//		}
//		return "operationOrder";
//	}
//	// ����
//	public String saveOrUpdateOrder() throws Exception {
//		HttpServletRequest request = this.getRequest();
//		TbCommodityOrder bean = new TbCommodityOrder();
//		String orderId=request.getParameter("orderId");
//		if(null!=orderId&&!"".equals(orderId)){
//			try {
//				bean = (TbCommodityOrder)this.getCommonBo().get(TbCommodityOrder.class, new Long(orderId));
//			} catch (Exception e) {}
//		}
//		//��ƷID
//		String commId=request.getParameter("commId");
//		if(null!=commId&&!"".equals(commId)){
//			try {
//				TbCommodity comm = (TbCommodity)this.getCommonBo().get(TbCommodity.class, new Long(commId));
//				bean.setCommId(comm.getCommId());
//				//��Ʒ����
//				bean.setCommName(comm.getCommName());
//			} catch (Exception e) {}
//		}
//		//��Ʒ�ͺ�ID
//		String commDetailId=request.getParameter("commDetailId");
//		if(null!=commDetailId&&!"".equals(commDetailId)){
//			try {
//				TbCommodityDetail detail = (TbCommodityDetail)this.getCommonBo().get(TbCommodityDetail.class, new Long(commDetailId));
//				bean.setCommDetailId(detail.getDetailId());
//				//��Ʒ�ͺ�����
//				bean.setCommDetailName(detail.getDetailName());
//			} catch (Exception e) {}
//		}
//		DecimalFormat format = new DecimalFormat("0.00");
//		//��Ʒ����
//		String commFee=request.getParameter("commFee");
//		if(null!=commFee&&!"".equals(commFee)){
//			try {
//				commFee = format.format(new BigDecimal(commFee));
//				bean.setCommFee(new BigDecimal(commFee));
//			} catch (Exception e) {}
//		}
//		//��������
//		String commNum=request.getParameter("commNum");
//		if(null!=commNum&&!"".equals(commNum)){
//			try {
//				bean.setCommNum(new Long(commNum));
//			} catch (Exception e) {	}
//		}
//		//�ջ���ʽ 1������ᣬ2��������
//		String shippingType=request.getParameter("shippingType");
//		if(null!=shippingType&&!"".equals(shippingType)){
//			bean.setShippingType(shippingType);
//			if(shippingType.equals("2")){
//				//�������
//				String buyerName = request.getParameter("buyerName");
//				bean.setBuyerName(buyerName);
//				//��ҵ绰
//				String buyerPhone = request.getParameter("buyerPhone");
//				bean.setBuyerPhone(buyerPhone);
//				//����ֻ�
//				String buyerMobile = request.getParameter("buyerMobile");
//				bean.setBuyerMobile(buyerMobile);
//				//�������
//				String buyerEmail = request.getParameter("buyerEmail");
//				bean.setBuyerEmail(buyerEmail);
//				//����ʱ�
//				String buyerZipcode = request.getParameter("buyerZipcode");
//				bean.setBuyerZipcode(buyerZipcode);
//				//��ҵ�ַ
//				String buyerAddress = request.getParameter("buyerAddress");
//				bean.setBuyerAddress(buyerAddress);
//				//�����˷�
//				String shippingFee = request.getParameter("shippingFee");
//				try {
//					shippingFee = format.format(new BigDecimal(shippingFee));
//					bean.setShippingFee(new BigDecimal(shippingFee));
//				} catch (Exception e) {}
//			}
//		}
//		//����ʵ�����
//		String orderFee=request.getParameter("orderFee");
//		if(null!=orderFee&&!"".equals(orderFee)){
//			try {
//				orderFee = format.format(new BigDecimal(orderFee));
//				bean.setOrderFee(new BigDecimal(orderFee));
//			} catch (Exception e) {}
//		}
//		//�Ƿ���� 1������0������
//		String donationFee=request.getParameter("donationFee");
//		if(null!=donationFee && !"".equals(donationFee)){
//			donationFee = format.format(new BigDecimal(donationFee));
//			bean.setDonationFee(new BigDecimal(donationFee));
//		}else{
//		}
//		//�������
//		String orderMemo=request.getParameter("orderMemo");
//		bean.setOrderMemo(orderMemo);
//		//������Դ  ���߹������¹����
//		String orderSource=request.getParameter("orderSource");
//		bean.setOrderSource(orderSource);
//		//����״̬ 1�ȴ���Ҹ��2����Ѹ��3�ѷ�����4���ջ���5����
//		String orderStatus=request.getParameter("orderStatus");
//		bean.setOrderStatus(orderStatus);
//		//��������Ǽ� 1һ�ǣ�2���ǣ�3���ǣ�4���ǣ�5����
//		String starNum=request.getParameter("starNum");
//		if(null!=starNum&&!"".equals(starNum)){
//			try {
//				bean.setStarNum(new Long(starNum));
//			} catch (Exception e) {}
//		}
//		//�������˵��
//		String starMemo=request.getParameter("starMemo");
//		bean.setStarMemo(starMemo);
//		//��ע
//		String memo=request.getParameter("memo");
//		bean.setMemo(memo);
//		//��Ҹ���ʱ��
//		//����ʱ��
//		//�ջ�ʱ��
//		//����ʱ��
//		this.getCommonBo().saveOrUpdate(bean);
//		if(null==orderId||"".equals(orderId)){
//			// ��ǰʱ��
//			Date dateNow = this.getCommonBo().getSysDate();
//			// �������񵥺�
//			String orderNo = DateUtil.format(dateNow,"yyyyMMddHHmm")+bean.getOrderId();
//			bean.setOrderNo(orderNo);
//			this.getCommonBo().update(bean);
//		}
//		return "reload";
//	}
	
	// ��Ʒ��������ҳ��
	public String orderView() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ��Ʒ����
		TbCommodityOrder bean = null;
		// ��Ʒ����ID
		String orderId = request.getParameter("orderId");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbCommodityOrder)this.getCommonBo().get(TbCommodityOrder.class, new Long(orderId));
			}catch(Exception e){}
		}
		request.setAttribute("bean", bean);
		// ����
		return "orderView";
	}
	
	// ��Ʒ�������ҳ��
	public String orderMark() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ��Ʒ����
		TbCommodityOrder bean = null;
		// ��Ʒ����ID
		String orderId = request.getParameter("orderId");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbCommodityOrder)this.getCommonBo().get(TbCommodityOrder.class, new Long(orderId));
			}catch(Exception e){}
		}
		request.setAttribute("bean", bean);
		// ����
		return "orderMark";
	}
	
	// ��Ʒ������չ���
	public String orderMarkUpdate() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ��Ʒ����
		TbCommodityOrder bean = null;
		// ��Ʒ����ID
		String orderId = request.getParameter("orderId");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbCommodityOrder)this.getCommonBo().get(TbCommodityOrder.class, new Long(orderId));
			}catch(Exception e){}
		}
		if(bean!=null){
			// �����Ϣ
			String mark = request.getParameter("mark");
			bean.setMark(mark);
			// ʵ���˷�
			BigDecimal shippingCurfeeD = null;
			String shippingCurfee = request.getParameter("shippingCurfee");
			if(shippingCurfee!=null && !"".equals(shippingCurfee)){
				try{
					shippingCurfeeD = new BigDecimal(shippingCurfee).setScale(2, 4);
				}catch(Exception e){}
			}
			bean.setShippingCurfee(shippingCurfeeD);
			// ��ִ��Ϣ
			String receipt = request.getParameter("receipt");
			bean.setReceipt(receipt);
			// ��ע
			String memo = request.getParameter("memo");
			bean.setMemo(memo);
			// ����ѡ��
			try{
				this.getCommonBo().saveOrUpdate(bean);
			} catch (Exception e) {
				// ����
				request.setAttribute("alert", "����ʱ�����쳣��");
				return "orderMark";
			}
		}
		// ����
		return "winSuccess";
	}
	
	// ɾ������
	public String deleteOrder() throws Exception {
		HttpServletRequest request = this.getRequest();
		TbCommodityOrder bean = null;
		String orderId=request.getParameter("orderId");
		if(null!=orderId&&!"".equals(orderId)){
			try {
				bean = (TbCommodityOrder)this.getCommonBo().get(TbCommodityOrder.class, new Long(orderId));
			} catch (Exception e) {}
		}
		if(bean!=null){
			this.getCommonBo().delete(bean);
		}
		return info();
	}
	public String ajax() throws Exception{
		HttpServletRequest request=this.getRequest();
		// Ajax
		String ajax = "{\"result\":\"error\"}";
		String html = "<option value=''>��ѡ����Ʒ�ͺ�</option>";
		String commId=request.getParameter("commId");
		String hql = "from TbCommodityDetail a where a.isDelete=0";
		if(null!=commId&&!"".equals(commId)){
			try {
				hql+=" and a.commId="+new Long(commId);
			} catch (Exception e) {}
		}
		hql += " order by a.detailName";
		List beanList = this.getCommonBo().findHQL(hql);
		if(null!=beanList&&!beanList.isEmpty()&&beanList.size()>0){
			for (int i = 0; i < beanList.size(); i++) {
				TbCommodityDetail bean = (TbCommodityDetail)beanList.get(i);
				html += "<option value='"+bean.getDetailId()+"'>"+bean.getDetailName()+"</option>";
			}
		}
		ajax = "{\"result\":\"success\",\"detail\":\""+html+"\"}";
		this.sendResponse(this.getResponse(),ajax);
		// ����
		return null;
	}
	public String ajaxDetail() throws Exception{
		HttpServletRequest request=this.getRequest();
		// Ajax
		String ajax = "{\"result\":\"error\"}";
		String detailId=request.getParameter("detailId");
		TbCommodityDetail bean = null;
		if(null!=detailId&&!"".equals(detailId)){
			try {
				bean = (TbCommodityDetail)this.getCommonBo().get(TbCommodityDetail.class, new Long(detailId));
			} catch (Exception e) {}
		}
		BigDecimal shippingFee = new BigDecimal(0);
		if(null!=bean){
			if(bean.getTbCommodity()!=null){
				String isShipping = bean.getTbCommodity().getIsShipping();
				if(null!=isShipping&&isShipping.equals("0")){
					shippingFee = bean.getTbCommodity().getShippingFee();
					
				}
			}
		}
		ajax = "{\"result\":\"success\",\"saleFee\":\""+bean.getSaleFee()+"\",\"shippingFee\":\""+shippingFee+"\"}";
		System.out.println(ajax);
		this.sendResponse(this.getResponse(),ajax);
		// ����
		return null;
	}
}