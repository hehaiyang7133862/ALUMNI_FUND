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
		// 菜单序号
		request.setAttribute("num", request.getAttribute("num"));
		// 属性编号
		String id=request.getParameter("id");
		if(null==id || "".equals(id)){
			id="1";
		}
		// 查询属性
		TbCommodityType bean = null;
		try {
			bean=(TbCommodityType)getCommonBo().get(TbCommodityType.class, new Long(id));
		} catch (Exception e) {	}
		request.setAttribute("bean", bean);
		// 项目数量
		String hqlCount = "select count(*) from TbCommodity a where 1=1";
		int count = getCommonBo().getHQLNum(hqlCount);
		request.setAttribute("count", count);
		// 返回
		return INPUT;
	}
	public String list() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 查询属性
		String hql="from TbCommodity a where 1=1";
		List pars = this.getList();
		//所属分类
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
		// 项目数量
		String hqlCount = "select count(*) "+hql;
		int count = getCommonBo().getHQLNum(hqlCount);
		request.setAttribute("count", count);
		//名称
		String commName=request.getParameter("commName");
		if(null!=commName && !"".equals(commName)){
			hql += " and a.commName like ?";
			pars.add("%"+commName+"%");
			request.setAttribute("commName", commName);
		}
		//上架
		String isShelves=request.getParameter("isShelves");
		if(null!=isShelves && !"".equals(isShelves)){
			hql += " and a.isShelves=?";	
			pars.add(isShelves);
			request.setAttribute("isShelves", isShelves);
		}
		//热门
		String isHot=request.getParameter("isHot");
		if(null!=isHot && !"".equals(isHot)){
			hql += " and a.isHot=?";	
			pars.add(isHot);
			request.setAttribute("isHot", isHot);
		}
		//库存 1：有货 0：缺货
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
		// 返回
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
	// 上架、下架
	public String shelves() throws Exception {
		HttpServletRequest request = this.getRequest();
		TbCommodity bean = null;
		String commId=request.getParameter("commId");
		//所属分类
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
			request.setAttribute("alert", "删除成功");
		}else{
			request.setAttribute("alert", "对象不存在或已被删除");
		}
		return list();
	}
	// 操作
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
		// 商品分类集合
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
		//名称
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
		//名称
		String commName=multiRequest.getParameter("commName");
		bean.setCommName(commName);
		//所属分类
		String commType=multiRequest.getParameter("commType");
		if(null!=commType && !"".equals(commType)){
			try {
				bean.setCommType(new Long(commType));
			} catch (Exception e) {	}
		}
		//简介
		String commIntro=multiRequest.getParameter("commIntro");
		bean.setCommIntro(commIntro);
		//详情
		String commDetail=multiRequest.getParameter("commDetail");
		bean.setCommDetail(commDetail);

		//是否上架
		String isShelves=multiRequest.getParameter("isShelves");
		if(null==isShelves || !"1".equals(isShelves)){
			isShelves = "0";
		}
		bean.setIsShelves(isShelves);
		//是否热门
		String isHot=multiRequest.getParameter("isHot");
		if(null==isHot || !"1".equals(isHot)){
			isHot = "0";
		}
		bean.setIsHot(isHot);
		//热门排序
		String hotOrder=multiRequest.getParameter("hotOrder");
		bean.setHotOrder(hotOrder);
		//是否免运费 1不免运费，0免运费
		String isShipping=multiRequest.getParameter("isShipping");
		if(null==isShipping || !"1".equals(isShipping)){
			isShipping = "0";
		}
		bean.setIsShipping(isShipping);
		if("1".equals(isShipping)){
			// 运费
			BigDecimal shippingFeeD = null;
			String shippingFee = multiRequest.getParameter("shippingFee");
			if(shippingFee!=null && !"".equals(shippingFee)){
				try{
					shippingFeeD = new BigDecimal(shippingFee).setScale(2, 4);
				}catch(Exception e){}
			}
			bean.setShippingFee(shippingFeeD);
			// 运费说明
			String shippingMemo = multiRequest.getParameter("shippingMemo");
			bean.setShippingMemo(shippingMemo);
		}else{
			bean.setShippingFee(null);
			bean.setShippingMemo(null);
		}
		//是否捐赠 1捐赠，0不捐赠
		String isDonation=multiRequest.getParameter("isDonation");
		if(null==isDonation || !"1".equals(isDonation)){
			isDonation = "0";
		}
		bean.setIsDonation(isDonation);
		if("1".equals(isDonation)){
			// 捐赠
			BigDecimal donationFeeD = null;
			String donationFee = multiRequest.getParameter("donationFee");
			if(donationFee!=null && !"".equals(donationFee)){
				try{
					donationFeeD = new BigDecimal(donationFee).setScale(2, 4);
				}catch(Exception e){}
			}
			bean.setDonationFee(donationFeeD);
			// 捐赠说明
			String donationMemo = multiRequest.getParameter("donationMemo");
			bean.setDonationMemo(donationMemo);
		}else{
			bean.setDonationFee(null);
			bean.setDonationMemo(null);
		}
		// 备注说明
		String memo=multiRequest.getParameter("memo");
		bean.setMemo(memo);
		try{
			// 保存
			if(bean.getCommId()==null){
				bean = (TbCommodity) this.getCommonBo().save(bean);
				request.setAttribute("opt", "insert");
				// 商品图片
				FileItem item = multiRequest.getFile("detailPic");
				if(item!=null){
					InputStream bis = null;
					OutputStream out = null;
					try {
						// 后缀名
						String lastName="";
						int nIndex = item.getName().lastIndexOf(".");
						if (nIndex != -1) {
							lastName = item.getName().substring(nIndex + 1).toLowerCase();
						}
						if("gif".equals(lastName) || "jpg".equals(lastName) || "jpeg".equals(lastName) || "png".equals(lastName) || "bmp".equals(lastName)){
							/* 上传文件 ------------------------------------------- */
							String uploadPath = "upload/image/";
							// 文件保存目录路径
							String savePath = request.getSession().getServletContext()
									.getRealPath("/" + uploadPath)
									+ "/";
							// 检查目录
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
							// 文件路径
							String fileName = DateUtil.format(dateNow, "yyyyMMddHHmmss")+"."+lastName;
							savePath += fileName;
							uploadPath += fileName;
							
							bis = new BufferedInputStream(item.getInputStream());
							// 输出流
							File file = new File(savePath);
							out = new BufferedOutputStream(new FileOutputStream(file));
							byte[] bytes = new byte[1];
							while (bis.read(bytes) != -1) {
								out.write(bytes);
							}
							// 商品图片
							TbCommodityPic beanPic = new TbCommodityPic();
							beanPic.setCommId(bean.getCommId());
							beanPic.setPicPath(uploadPath);
							beanPic.setNumOrder("1");
							// 保存图片
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
				// 商品型号
				TbCommodityDetail beanDetail = new TbCommodityDetail();
				beanDetail.setCommId(bean.getCommId());
				beanDetail.setDetailName("默认");
				beanDetail.setIsDelete("0");
				beanDetail.setIsShelves("1");
				beanDetail.setNumOrder("1");
				// 库存
				Long stockNumL = null;
				String stockNum = multiRequest.getParameter("stockNum");
				if(stockNum!=null && !"".equals(stockNum)){
					try{
						stockNumL = new Long(stockNum);
					}catch(Exception e){}
				}
				beanDetail.setStockNum(stockNumL);
				// 成本价
				BigDecimal costFeeD = null;
				String costFee = multiRequest.getParameter("costFee");
				if(costFee!=null && !"".equals(costFee)){
					try{
						costFeeD = new BigDecimal(costFee).setScale(2, 4);
					}catch(Exception e){}
				}
				beanDetail.setCostFee(costFeeD);
				// 售价
				BigDecimal saleFeeD = null;
				String saleFee = multiRequest.getParameter("saleFee");
				if(saleFee!=null && !"".equals(saleFee)){
					try{
						saleFeeD = new BigDecimal(saleFee).setScale(2, 4);
					}catch(Exception e){}
				}
				beanDetail.setSaleFee(saleFeeD);
				// 保存型号
				this.getCommonBo().save(beanDetail);
			}else{
				bean = (TbCommodity) this.getCommonBo().update(bean);
			}
			// 更新附件编号
			String relIds = multiRequest.getParameter("relIds");
			FileManager.getInstance().update(bean.getCommId(),relIds);
			
			// 返回
			request.setAttribute("commId", bean.getCommId());
			return "choice";
		}catch(Exception e){}
		// 保存失败
		request.setAttribute("bean", bean);
		// 商品分类集合
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
		//名称
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
			if(num.equals("1")){//商品图片
				String hql = "from TbCommodityPic a where a.commId="+bean.getCommId()+" order by a.numOrder";
				List beanList = this.getCommonBo().findHQL(hql);
				request.setAttribute("beanList", beanList);
				return "pic";
			}else if(num.equals("2")){//商品参数
				String hql = "from TbCommodityParam a where a.commId="+bean.getCommId()+" order by a.numOrder";
				List beanList = this.getCommonBo().findHQL(hql);
				request.setAttribute("beanList", beanList);
				return "param";
			}else if(num.equals("3")){//商品型号
				String hql = "from TbCommodityDetail a where a.commId="+bean.getCommId();
				//名称
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
			}else if(num.equals("4")){//商品订单
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
				// 标记
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
//	// 移动图片
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
//		//类型 1：上移 2：下移
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
//				if(type.equals("1")&&i!=0){//上移
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
	// 操作图片
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
			// 商品Id
			Long commIdL = null;
			String commId=request.getParameter("commId");
			if(commId!=null && !"".equals(commId)){
				try{
					commIdL = new Long(commId);
				}catch(Exception e){}
			}
			if(commIdL!=null){
				bean.setCommId(commIdL);
				// 图片排序
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
		// 商品图片
		TbCommodityPic bean = null;
		// 商品图片ID
		String picId = multiRequest.getParameter("picId");
		if(picId!=null && !"".equals(picId)){
			try{
				bean = (TbCommodityPic)this.getCommonBo().get(TbCommodityPic.class, new Long(picId));
			}catch(Exception e){}
		}
		if(bean==null){
			bean = new TbCommodityPic();
			// 商品Id
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
			// 图片上传
			FileItem item = multiRequest.getFile("pic");
			if(item!=null){
				InputStream bis = null;
				OutputStream out = null;
				try {
					// 后缀名
					String lastName="";
					int nIndex = item.getName().lastIndexOf(".");
					if (nIndex != -1) {
						lastName = item.getName().substring(nIndex + 1).toLowerCase();
					}
					if("gif".equals(lastName) || "jpg".equals(lastName) || "jpeg".equals(lastName) || "png".equals(lastName) || "bmp".equals(lastName)){
						/* 上传文件 ------------------------------------------- */
						String uploadPath = "upload/image/";
						// 文件保存目录路径
						String savePath = request.getSession().getServletContext()
								.getRealPath("/" + uploadPath)
								+ "/";
						// 检查目录
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
						// 文件路径
						String fileName = DateUtil.format(dateNow, "yyyyMMddHHmmss")+"."+lastName;
						savePath += fileName;
						uploadPath += fileName;
						
						bis = new BufferedInputStream(item.getInputStream());
						// 输出流
						File file = new File(savePath);
						out = new BufferedOutputStream(new FileOutputStream(file));
						byte[] bytes = new byte[1];
						while (bis.read(bytes) != -1) {
							out.write(bytes);
						}
						// 项目图片
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
			// 图片排序
			String numOrder = multiRequest.getParameter("numOrder");
			bean.setNumOrder(numOrder);
			// 图片说明
			String picAlt = multiRequest.getParameter("picAlt");
			bean.setPicAlt(picAlt);
			// 备注说明
			String memo = multiRequest.getParameter("memo");
			bean.setMemo(memo);
			// 保存图片
			try{
				this.getCommonBo().saveOrUpdate(bean);
			} catch (Exception e) {
				// 返回
				request.setAttribute("alert", "保存时发生异常！");
				request.setAttribute("bean", bean);
				return "operationPic";
			}
		}
		// 返回
		return "winSuccess";
	}
	// 删除图片
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
//	// 移动参数
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
//		//类型 1：上移 2：下移
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
//				if(type.equals("1")&&i!=0){//上移
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
	// 新增参数
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
			// 商品Id
			Long commIdL = null;
			String commId=request.getParameter("commId");
			if(commId!=null && !"".equals(commId)){
				try{
					commIdL = new Long(commId);
				}catch(Exception e){}
			}
			if(commIdL!=null){
				bean.setCommId(commIdL);
				// 图片排序
				String hqlCount = "select count(*) from TbCommodityParam a where a.commId="+commIdL;
				int count = this.getCommonBo().getHQLNum(hqlCount);
				bean.setNumOrder((count+1)+"");
			}
		}
		request.setAttribute("bean", bean);
		return "operationParam";
	}
	// 操作参数
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
			// 商品Id
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
			// 保存
			try{
				this.getCommonBo().saveOrUpdate(bean);
			} catch (Exception e) {
				// 返回
				request.setAttribute("alert", "保存时发生异常！");
				request.setAttribute("bean", bean);
				return "operationParam";
			}
		}
		// 返回
		return "winSuccess";
	}
	// 删除图片
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
	// 新增参数
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
			// 商品Id
			Long commIdL = null;
			String commId=request.getParameter("commId");
			if(commId!=null && !"".equals(commId)){
				try{
					commIdL = new Long(commId);
				}catch(Exception e){}
			}
			if(commIdL!=null){
				bean.setCommId(commIdL);
				// 排序
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
			// 商品Id
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
			//名称
			String detailName=multiRequest.getParameter("detailName");
			bean.setDetailName(detailName);
			//简介
			String detailIntro=multiRequest.getParameter("detailIntro");
			bean.setDetailIntro(detailIntro);
			//库存
			Long stockNumL = null;
			String stockNum=multiRequest.getParameter("stockNum");
			try {
				stockNumL = new Long(stockNum);
			} catch (Exception e1) { }
			bean.setStockNum(stockNumL);
			// 成本价
			BigDecimal costFeeD = null;
			String costFee = multiRequest.getParameter("costFee");
			if(costFee!=null && !"".equals(costFee)){
				try{
					costFeeD = new BigDecimal(costFee).setScale(2, 4);
				}catch(Exception e){}
			}
			bean.setCostFee(costFeeD);
			// 售价
			BigDecimal saleFeeD = null;
			String saleFee = multiRequest.getParameter("saleFee");
			if(saleFee!=null && !"".equals(saleFee)){
				try{
					saleFeeD = new BigDecimal(saleFee).setScale(2, 4);
				}catch(Exception e){}
			}
			bean.setSaleFee(saleFeeD);
			// 图片上传
			FileItem item = multiRequest.getFile("detailPic");
			if(item!=null){
				InputStream bis = null;
				OutputStream out = null;
				try {
					// 后缀名
					String lastName="";
					int nIndex = item.getName().lastIndexOf(".");
					if (nIndex != -1) {
						lastName = item.getName().substring(nIndex + 1).toLowerCase();
					}
					if("gif".equals(lastName) || "jpg".equals(lastName) || "jpeg".equals(lastName) || "png".equals(lastName) || "bmp".equals(lastName)){
						/* 上传文件 ------------------------------------------- */
						String uploadPath = "upload/image/";
						// 文件保存目录路径
						String savePath = request.getSession().getServletContext()
								.getRealPath("/" + uploadPath)
								+ "/";
						// 检查目录
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
						// 文件路径
						String fileName = DateUtil.format(dateNow, "yyyyMMddHHmmss")+"."+lastName;
						savePath += fileName;
						uploadPath += fileName;
						
						bis = new BufferedInputStream(item.getInputStream());
						// 输出流
						File file = new File(savePath);
						out = new BufferedOutputStream(new FileOutputStream(file));
						byte[] bytes = new byte[1];
						while (bis.read(bytes) != -1) {
							out.write(bytes);
						}
						// 图片
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
			// 保存
			try{
				this.getCommonBo().saveOrUpdate(bean);
			} catch (Exception e) {
				// 返回
				request.setAttribute("alert", "保存时发生异常！");
				request.setAttribute("bean", bean);
				return "operationDetail";
			}
		}
		// 返回
		return "winSuccess";
	}
//	// 型号上架、下架
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
	// 删除商品型号
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
//	// 新增、修改订单
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
//	// 操作
//	public String saveOrUpdateOrder() throws Exception {
//		HttpServletRequest request = this.getRequest();
//		TbCommodityOrder bean = new TbCommodityOrder();
//		String orderId=request.getParameter("orderId");
//		if(null!=orderId&&!"".equals(orderId)){
//			try {
//				bean = (TbCommodityOrder)this.getCommonBo().get(TbCommodityOrder.class, new Long(orderId));
//			} catch (Exception e) {}
//		}
//		//商品ID
//		String commId=request.getParameter("commId");
//		if(null!=commId&&!"".equals(commId)){
//			try {
//				TbCommodity comm = (TbCommodity)this.getCommonBo().get(TbCommodity.class, new Long(commId));
//				bean.setCommId(comm.getCommId());
//				//商品名称
//				bean.setCommName(comm.getCommName());
//			} catch (Exception e) {}
//		}
//		//商品型号ID
//		String commDetailId=request.getParameter("commDetailId");
//		if(null!=commDetailId&&!"".equals(commDetailId)){
//			try {
//				TbCommodityDetail detail = (TbCommodityDetail)this.getCommonBo().get(TbCommodityDetail.class, new Long(commDetailId));
//				bean.setCommDetailId(detail.getDetailId());
//				//商品型号名称
//				bean.setCommDetailName(detail.getDetailName());
//			} catch (Exception e) {}
//		}
//		DecimalFormat format = new DecimalFormat("0.00");
//		//商品单价
//		String commFee=request.getParameter("commFee");
//		if(null!=commFee&&!"".equals(commFee)){
//			try {
//				commFee = format.format(new BigDecimal(commFee));
//				bean.setCommFee(new BigDecimal(commFee));
//			} catch (Exception e) {}
//		}
//		//购买数量
//		String commNum=request.getParameter("commNum");
//		if(null!=commNum&&!"".equals(commNum)){
//			try {
//				bean.setCommNum(new Long(commNum));
//			} catch (Exception e) {	}
//		}
//		//收货方式 1买家自提，2物流发货
//		String shippingType=request.getParameter("shippingType");
//		if(null!=shippingType&&!"".equals(shippingType)){
//			bean.setShippingType(shippingType);
//			if(shippingType.equals("2")){
//				//买家姓名
//				String buyerName = request.getParameter("buyerName");
//				bean.setBuyerName(buyerName);
//				//买家电话
//				String buyerPhone = request.getParameter("buyerPhone");
//				bean.setBuyerPhone(buyerPhone);
//				//买家手机
//				String buyerMobile = request.getParameter("buyerMobile");
//				bean.setBuyerMobile(buyerMobile);
//				//买家邮箱
//				String buyerEmail = request.getParameter("buyerEmail");
//				bean.setBuyerEmail(buyerEmail);
//				//买家邮编
//				String buyerZipcode = request.getParameter("buyerZipcode");
//				bean.setBuyerZipcode(buyerZipcode);
//				//买家地址
//				String buyerAddress = request.getParameter("buyerAddress");
//				bean.setBuyerAddress(buyerAddress);
//				//物流运费
//				String shippingFee = request.getParameter("shippingFee");
//				try {
//					shippingFee = format.format(new BigDecimal(shippingFee));
//					bean.setShippingFee(new BigDecimal(shippingFee));
//				} catch (Exception e) {}
//			}
//		}
//		//购买实付金额
//		String orderFee=request.getParameter("orderFee");
//		if(null!=orderFee&&!"".equals(orderFee)){
//			try {
//				orderFee = format.format(new BigDecimal(orderFee));
//				bean.setOrderFee(new BigDecimal(orderFee));
//			} catch (Exception e) {}
//		}
//		//是否捐赠 1捐赠，0不捐赠
//		String donationFee=request.getParameter("donationFee");
//		if(null!=donationFee && !"".equals(donationFee)){
//			donationFee = format.format(new BigDecimal(donationFee));
//			bean.setDonationFee(new BigDecimal(donationFee));
//		}else{
//		}
//		//买家留言
//		String orderMemo=request.getParameter("orderMemo");
//		bean.setOrderMemo(orderMemo);
//		//订单来源  在线购买，线下购买等
//		String orderSource=request.getParameter("orderSource");
//		bean.setOrderSource(orderSource);
//		//订单状态 1等待买家付款，2买家已付款，3已发货，4已收货，5评价
//		String orderStatus=request.getParameter("orderStatus");
//		bean.setOrderStatus(orderStatus);
//		//买家评价星级 1一星，2二星，3三星，4四星，5五星
//		String starNum=request.getParameter("starNum");
//		if(null!=starNum&&!"".equals(starNum)){
//			try {
//				bean.setStarNum(new Long(starNum));
//			} catch (Exception e) {}
//		}
//		//买家评价说明
//		String starMemo=request.getParameter("starMemo");
//		bean.setStarMemo(starMemo);
//		//备注
//		String memo=request.getParameter("memo");
//		bean.setMemo(memo);
//		//买家付款时间
//		//发货时间
//		//收货时间
//		//评价时间
//		this.getCommonBo().saveOrUpdate(bean);
//		if(null==orderId||"".equals(orderId)){
//			// 当前时间
//			Date dateNow = this.getCommonBo().getSysDate();
//			// 生成任务单号
//			String orderNo = DateUtil.format(dateNow,"yyyyMMddHHmm")+bean.getOrderId();
//			bean.setOrderNo(orderNo);
//			this.getCommonBo().update(bean);
//		}
//		return "reload";
//	}
	
	// 商品订单详情页面
	public String orderView() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 商品订单
		TbCommodityOrder bean = null;
		// 商品订单ID
		String orderId = request.getParameter("orderId");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbCommodityOrder)this.getCommonBo().get(TbCommodityOrder.class, new Long(orderId));
			}catch(Exception e){}
		}
		request.setAttribute("bean", bean);
		// 返回
		return "orderView";
	}
	
	// 商品订单标记页面
	public String orderMark() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 商品订单
		TbCommodityOrder bean = null;
		// 商品订单ID
		String orderId = request.getParameter("orderId");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbCommodityOrder)this.getCommonBo().get(TbCommodityOrder.class, new Long(orderId));
			}catch(Exception e){}
		}
		request.setAttribute("bean", bean);
		// 返回
		return "orderMark";
	}
	
	// 商品订单进展标记
	public String orderMarkUpdate() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 商品订单
		TbCommodityOrder bean = null;
		// 商品订单ID
		String orderId = request.getParameter("orderId");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbCommodityOrder)this.getCommonBo().get(TbCommodityOrder.class, new Long(orderId));
			}catch(Exception e){}
		}
		if(bean!=null){
			// 标记信息
			String mark = request.getParameter("mark");
			bean.setMark(mark);
			// 实用运费
			BigDecimal shippingCurfeeD = null;
			String shippingCurfee = request.getParameter("shippingCurfee");
			if(shippingCurfee!=null && !"".equals(shippingCurfee)){
				try{
					shippingCurfeeD = new BigDecimal(shippingCurfee).setScale(2, 4);
				}catch(Exception e){}
			}
			bean.setShippingCurfee(shippingCurfeeD);
			// 回执信息
			String receipt = request.getParameter("receipt");
			bean.setReceipt(receipt);
			// 备注
			String memo = request.getParameter("memo");
			bean.setMemo(memo);
			// 保存选项
			try{
				this.getCommonBo().saveOrUpdate(bean);
			} catch (Exception e) {
				// 返回
				request.setAttribute("alert", "保存时发生异常！");
				return "orderMark";
			}
		}
		// 返回
		return "winSuccess";
	}
	
	// 删除订单
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
		String html = "<option value=''>请选择商品型号</option>";
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
		// 返回
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
		// 返回
		return null;
	}
}