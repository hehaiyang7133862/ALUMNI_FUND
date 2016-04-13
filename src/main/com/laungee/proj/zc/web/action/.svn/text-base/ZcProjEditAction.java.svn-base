package com.laungee.proj.zc.web.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts2.ServletActionContext;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbZcproj;
import com.laungee.proj.common.model.TbZcprojOption;
import com.laungee.proj.common.model.TbZcprojOrder;
import com.laungee.proj.common.model.TbZcprojPic;
import com.laungee.proj.common.model.TbZcprojProgress;
import com.laungee.proj.common.model.TbZcprojType;
import com.laungee.proj.common.util.DateUtil;
import com.laungee.proj.common.util.MultiRequestUtil;
import com.laungee.proj.common.web.FileManager;

public class ZcProjEditAction extends BaseAction {
	// 众筹项目新增、编辑页面
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 项目
		TbZcproj bean = null;
		// 项目ID
		String projId = request.getParameter("projId");
		if(projId!=null && !"".equals(projId)){
			try{
				bean = (TbZcproj)this.getCommonBo().get(TbZcproj.class, new Long(projId));
			}catch(Exception e){}
		}
		if(bean==null){
			bean = new TbZcproj();
			// 项目分类
			Long typeIdL = null;
			String typeId = request.getParameter("typeId");
			if(typeId!=null && !"".equals(typeId)){
				try{
					typeIdL = new Long(typeId);
				}catch(Exception e){}
			}
			bean.setProjType(typeIdL);
			// 系统时间
			Date dateNow = this.getCommonBo().getSysDate();
			String[] dateStr = DateUtil.format(dateNow).split("-");
			// 是否上架
			bean.setShelvesFlag("0");
			// 是否热门
			bean.setHotFlag("0");
			// 显示发布时间
			bean.setShelvesTime(DateUtil.toDate(dateStr[0]+"-"+dateStr[1]+"-"+dateStr[2]+" 09:00","yyyy-MM-dd HH:mm"));
			// 众筹开始时间
			bean.setBegTime(DateUtil.toDate(dateStr[0]+"-"+dateStr[1]+"-"+dateStr[2]+" 10:00","yyyy-MM-dd HH:mm"));
			// 众筹截止时间
			bean.setEndTime(DateUtil.toDate(dateStr[0]+"-"+(Integer.parseInt(dateStr[1])+1)+"-"+dateStr[2]+" 18:00","yyyy-MM-dd HH:mm"));
			// 是否支持任意捐选项
			bean.setOptionOther("1");
			// 任意捐选项名称
			bean.setOptionOtherName("任意捐");
			// 任意捐最低金额
			bean.setMinAmount(new BigDecimal(1).setScale(2, 4));
			// 目标完成后继续众筹
			bean.setCompletedJz("1");
			// 结束后归入经典回顾
			bean.setClassicStatus("0");
			// 项目详情标签
			String hql = "select a.fieldName from TbFields a,TbFields b where a.parentId=b.fieldId and b.code='ZCPROJ_DETAIL_TITLE' order by a.numOrder";
			List detailTitleList = this.getCommonBo().findHQL(hql);
			if(detailTitleList!=null && !detailTitleList.isEmpty()){
				for(int i=0;i<detailTitleList.size();i++){
					String obj = (String)detailTitleList.get(i);
					if(i==0){
						bean.setDetail1Title(obj);
					}else if(i==1){
						bean.setDetail2Title(obj);
					}else if(i==2){
						bean.setDetail3Title(obj);
					}else if(i==3){
						bean.setDetail4Title(obj);
					}else if(i==4){
						bean.setDetail5Title(obj);
					}else if(i==5){
						bean.setDetail6Title(obj);
					}else if(i==6){
						bean.setDetail7Title(obj);
					}else if(i==7){
						bean.setDetail8Title(obj);
					}else if(i==8){
						bean.setDetail9Title(obj);
					}else if(i==9){
						bean.setDetail10Title(obj);
					}else{
						break;
					}
				}
			}
		}
		request.setAttribute("bean", bean);
		//项目分类集合
		String hql = "from TbZcprojType a where a.parentId=1 order by a.numOrder";
		List typeList = this.getCommonBo().findHQL(hql);
		List typeNameList = this.getList();
		if(typeList!=null && !typeList.isEmpty()){
			for(int i=0;i<typeList.size();i++){
				TbZcprojType obj = (TbZcprojType)typeList.get(i);
				typeNameList.add(obj);
				getAllZcProjTypes(typeNameList,obj);
			}
		}
		request.setAttribute("typeList", typeNameList);
		//操作
		String opt = request.getParameter("opt");
		request.setAttribute("opt", opt);
		// 返回
		return INPUT;
	}
	
	private void getAllZcProjTypes(List beanList,TbZcprojType bean)throws Exception{
		String hql = "from TbZcprojType a where a.parentId="+bean.getTypeId()+" order by a.numOrder";
		List list = this.getCommonBo().findHQL(hql);
		if(list!=null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				TbZcprojType obj = (TbZcprojType)list.get(i);
				if(obj!=null){
					TbZcprojType temp = new TbZcprojType();
					temp.setTypeName(bean.getTypeName()+"  >  "+obj.getTypeName());
					temp.setTypeId(obj.getTypeId());
					beanList.add(temp);
					getAllZcProjTypes(beanList,obj);
				}
			}
		}
	}
	
	// 众筹项目新增、编辑
	public String update() throws Exception {
		HttpServletRequest request = this.getRequest();
		MultiRequestUtil multiRequest = new MultiRequestUtil(request);
		// 项目
		TbZcproj bean = null;
		// 项目ID
		String projId = multiRequest.getParameter("projId");
		if(projId!=null && !"".equals(projId)){
			try{
				bean = (TbZcproj)this.getCommonBo().get(TbZcproj.class, new Long(projId));
			}catch(Exception e){}
		}
		if(bean==null){
			bean = new TbZcproj();
		}
		// 项目名称
		String projName = multiRequest.getParameter("projName");
		bean.setProjName(projName);
		// 项目编码
		String projCode = multiRequest.getParameter("projCode");
		bean.setProjCode(projCode);
		// 所属分类
		Long typeIdL = null;
		String typeId = multiRequest.getParameter("typeId");
		if(typeId!=null && !"".equals(typeId)){
			try{
				typeIdL = new Long(typeId);
			}catch(Exception e){}
		}
		bean.setProjType(typeIdL);
		// 显示发布时间
		String shelvesTime = multiRequest.getParameter("shelvesTime");
		if(shelvesTime!=null && !"".equals(shelvesTime)){
			bean.setShelvesTime(DateUtil.toDate(shelvesTime,"yyyy-MM-dd HH:mm"));
		}else{
			bean.setShelvesTime(null);
		}
		// 目标金额
		BigDecimal targetAmoutD = null;
		String targetAmout = multiRequest.getParameter("targetAmout");
		if(targetAmout!=null && !"".equals(targetAmout)){
			try{
				targetAmoutD = new BigDecimal(targetAmout).setScale(2, 4);
			}catch(Exception e){}
		}
		bean.setTargetAmout(targetAmoutD);
		// 是否上架
		String shelvesFlag = multiRequest.getParameter("shelvesFlag");
		bean.setShelvesFlag(shelvesFlag);
		// 是否热门
		String hotFlag = multiRequest.getParameter("hotFlag");
		bean.setHotFlag(hotFlag);
		// 热门排序
		String hotOrder = multiRequest.getParameter("hotOrder");
		bean.setHotOrder(hotOrder);
		// 众筹开始时间
		String begTime = multiRequest.getParameter("begTime");
		if(begTime!=null && !"".equals(begTime)){
			bean.setBegTime(DateUtil.toDate(begTime,"yyyy-MM-dd HH:mm"));
		}else{
			bean.setBegTime(null);
		}
		// 众筹截止时间
		String endTime = multiRequest.getParameter("endTime");
		if(endTime!=null && !"".equals(endTime)){
			bean.setEndTime(DateUtil.toDate(endTime,"yyyy-MM-dd HH:mm"));
		}else{
			bean.setEndTime(null);
		}
		// 目标完成后继续众筹
		String completedJz = multiRequest.getParameter("completedJz");
		bean.setCompletedJz(completedJz);
		// 项目关键字标签
		String searchKey = multiRequest.getParameter("searchKey");
		if(searchKey!=null && !"".equals(searchKey)){
			searchKey = searchKey.replace("，", ",");
		}
		bean.setSearchKey(searchKey);
		// 结束后归入经典回顾
		String classicStatus = multiRequest.getParameter("classicStatus");
		bean.setClassicStatus(classicStatus);
		// 项目简介
		String projIntro = multiRequest.getParameter("projIntro");
		bean.setProjIntro(projIntro);
		// 是否支持任意捐选项
		String optionOther = multiRequest.getParameter("optionOther");
		bean.setOptionOther(optionOther);
		// 任意捐选项名称
		String optionOtherName = multiRequest.getParameter("optionOtherName");
		bean.setOptionOtherName(optionOtherName);
		// 任意捐选项起捐金额
		BigDecimal minAmountD = null;
		String minAmount = multiRequest.getParameter("minAmount");
		if(minAmount!=null && !"".equals(minAmount)){
			try{
				minAmountD = new BigDecimal(minAmount).setScale(2, 4);
			}catch(Exception e){}
		}
		bean.setMinAmount(minAmountD);
		// 任意捐选项描述
		String optionOtherMemo = multiRequest.getParameter("optionOtherMemo");
		bean.setOptionOtherMemo(optionOtherMemo);
		// 项目内容
		String[] detailTitles = multiRequest.getParameterValues("detailTitle");
		String[] detailContents = multiRequest.getParameterValues("detailContent");
		String[] detailPublishs = multiRequest.getParameterValues("detailPublish");
		if(detailTitles!=null && detailContents!=null && detailPublishs!=null && detailTitles.length==detailContents.length && detailTitles.length==detailPublishs.length && detailTitles.length>0){
			List detailTitleList = this.getList();
			List detailContentList = this.getList();
			List detailPublishList = this.getList();
			for(int i=0;i<detailTitles.length;i++){
				if(detailTitles[i]!=null && !"".equals(detailTitles[i])){
					detailTitleList.add(detailTitles[i]);
					detailContentList.add(detailContents[i]);
					if(detailPublishs[i]==null || !"1".equals(detailPublishs[i])){
						detailPublishList.add("0");
					}else{
						detailPublishList.add("1");
					}
				}
			}
			for(int i=0;i<detailTitleList.size();i++){
				if(i==0){
					bean.setDetail1Title((String)detailTitleList.get(i)); bean.setDetail1Content((String)detailContentList.get(i)); bean.setDetail1Publish((String)detailPublishList.get(i));
				}else if(i==1){
					bean.setDetail2Title((String)detailTitleList.get(i)); bean.setDetail2Content((String)detailContentList.get(i)); bean.setDetail2Publish((String)detailPublishList.get(i));
				}else if(i==2){
					bean.setDetail3Title((String)detailTitleList.get(i)); bean.setDetail3Content((String)detailContentList.get(i)); bean.setDetail3Publish((String)detailPublishList.get(i));
				}else if(i==3){
					bean.setDetail4Title((String)detailTitleList.get(i)); bean.setDetail4Content((String)detailContentList.get(i)); bean.setDetail4Publish((String)detailPublishList.get(i));
				}else if(i==4){
					bean.setDetail5Title((String)detailTitleList.get(i)); bean.setDetail5Content((String)detailContentList.get(i)); bean.setDetail5Publish((String)detailPublishList.get(i));
				}else if(i==5){
					bean.setDetail6Title((String)detailTitleList.get(i)); bean.setDetail6Content((String)detailContentList.get(i)); bean.setDetail6Publish((String)detailPublishList.get(i));
				}else if(i==6){
					bean.setDetail7Title((String)detailTitleList.get(i)); bean.setDetail7Content((String)detailContentList.get(i)); bean.setDetail7Publish((String)detailPublishList.get(i));
				}else if(i==7){
					bean.setDetail8Title((String)detailTitleList.get(i)); bean.setDetail8Content((String)detailContentList.get(i)); bean.setDetail8Publish((String)detailPublishList.get(i));
				}else if(i==8){
					bean.setDetail9Title((String)detailTitleList.get(i)); bean.setDetail9Content((String)detailContentList.get(i)); bean.setDetail9Publish((String)detailPublishList.get(i));
				}else if(i==9){
					bean.setDetail10Title((String)detailTitleList.get(i)); bean.setDetail10Content((String)detailContentList.get(i)); bean.setDetail10Publish((String)detailPublishList.get(i));
				}
			}
			for(int i=detailTitleList.size();i<10;i++){
				if(i==0){
					bean.setDetail1Title(null); bean.setDetail1Content(null); bean.setDetail1Publish(null);
					bean.setDetail2Title(null); bean.setDetail2Content(null); bean.setDetail2Publish(null);
					bean.setDetail3Title(null); bean.setDetail3Content(null); bean.setDetail3Publish(null);
					bean.setDetail4Title(null); bean.setDetail4Content(null); bean.setDetail4Publish(null);
					bean.setDetail5Title(null); bean.setDetail5Content(null); bean.setDetail5Publish(null);
					bean.setDetail6Title(null); bean.setDetail6Content(null); bean.setDetail6Publish(null);
					bean.setDetail7Title(null); bean.setDetail7Content(null); bean.setDetail7Publish(null);
					bean.setDetail8Title(null); bean.setDetail8Content(null); bean.setDetail8Publish(null);
					bean.setDetail9Title(null); bean.setDetail9Content(null); bean.setDetail9Publish(null);
					bean.setDetail10Title(null); bean.setDetail10Content(null); bean.setDetail10Publish(null);
				}else if(i==1){
					bean.setDetail2Title(null); bean.setDetail2Content(null); bean.setDetail2Publish(null);
					bean.setDetail3Title(null); bean.setDetail3Content(null); bean.setDetail3Publish(null);
					bean.setDetail4Title(null); bean.setDetail4Content(null); bean.setDetail4Publish(null);
					bean.setDetail5Title(null); bean.setDetail5Content(null); bean.setDetail5Publish(null);
					bean.setDetail6Title(null); bean.setDetail6Content(null); bean.setDetail6Publish(null);
					bean.setDetail7Title(null); bean.setDetail7Content(null); bean.setDetail7Publish(null);
					bean.setDetail8Title(null); bean.setDetail8Content(null); bean.setDetail8Publish(null);
					bean.setDetail9Title(null); bean.setDetail9Content(null); bean.setDetail9Publish(null);
					bean.setDetail10Title(null); bean.setDetail10Content(null); bean.setDetail10Publish(null);
				}else if(i==2){
					bean.setDetail3Title(null); bean.setDetail3Content(null); bean.setDetail3Publish(null);
					bean.setDetail4Title(null); bean.setDetail4Content(null); bean.setDetail4Publish(null);
					bean.setDetail5Title(null); bean.setDetail5Content(null); bean.setDetail5Publish(null);
					bean.setDetail6Title(null); bean.setDetail6Content(null); bean.setDetail6Publish(null);
					bean.setDetail7Title(null); bean.setDetail7Content(null); bean.setDetail7Publish(null);
					bean.setDetail8Title(null); bean.setDetail8Content(null); bean.setDetail8Publish(null);
					bean.setDetail9Title(null); bean.setDetail9Content(null); bean.setDetail9Publish(null);
					bean.setDetail10Title(null); bean.setDetail10Content(null); bean.setDetail10Publish(null);
				}else if(i==3){
					bean.setDetail4Title(null); bean.setDetail4Content(null); bean.setDetail4Publish(null);
					bean.setDetail5Title(null); bean.setDetail5Content(null); bean.setDetail5Publish(null);
					bean.setDetail6Title(null); bean.setDetail6Content(null); bean.setDetail6Publish(null);
					bean.setDetail7Title(null); bean.setDetail7Content(null); bean.setDetail7Publish(null);
					bean.setDetail8Title(null); bean.setDetail8Content(null); bean.setDetail8Publish(null);
					bean.setDetail9Title(null); bean.setDetail9Content(null); bean.setDetail9Publish(null);
					bean.setDetail10Title(null); bean.setDetail10Content(null); bean.setDetail10Publish(null);
				}else if(i==4){
					bean.setDetail5Title(null); bean.setDetail5Content(null); bean.setDetail5Publish(null);
					bean.setDetail6Title(null); bean.setDetail6Content(null); bean.setDetail6Publish(null);
					bean.setDetail7Title(null); bean.setDetail7Content(null); bean.setDetail7Publish(null);
					bean.setDetail8Title(null); bean.setDetail8Content(null); bean.setDetail8Publish(null);
					bean.setDetail9Title(null); bean.setDetail9Content(null); bean.setDetail9Publish(null);
					bean.setDetail10Title(null); bean.setDetail10Content(null); bean.setDetail10Publish(null);
				}else if(i==5){
					bean.setDetail6Title(null); bean.setDetail6Content(null); bean.setDetail6Publish(null);
					bean.setDetail7Title(null); bean.setDetail7Content(null); bean.setDetail7Publish(null);
					bean.setDetail8Title(null); bean.setDetail8Content(null); bean.setDetail8Publish(null);
					bean.setDetail9Title(null); bean.setDetail9Content(null); bean.setDetail9Publish(null);
					bean.setDetail10Title(null); bean.setDetail10Content(null); bean.setDetail10Publish(null);
				}else if(i==6){
					bean.setDetail7Title(null); bean.setDetail7Content(null); bean.setDetail7Publish(null);
					bean.setDetail8Title(null); bean.setDetail8Content(null); bean.setDetail8Publish(null);
					bean.setDetail9Title(null); bean.setDetail9Content(null); bean.setDetail9Publish(null);
					bean.setDetail10Title(null); bean.setDetail10Content(null); bean.setDetail10Publish(null);
				}else if(i==7){
					bean.setDetail8Title(null); bean.setDetail8Content(null); bean.setDetail8Publish(null);
					bean.setDetail9Title(null); bean.setDetail9Content(null); bean.setDetail9Publish(null);
					bean.setDetail10Title(null); bean.setDetail10Content(null); bean.setDetail10Publish(null);
				}else if(i==8){
					bean.setDetail9Title(null); bean.setDetail9Content(null); bean.setDetail9Publish(null);
					bean.setDetail10Title(null); bean.setDetail10Content(null); bean.setDetail10Publish(null);
				}else if(i==9){
					bean.setDetail10Title(null); bean.setDetail10Content(null); bean.setDetail10Publish(null);
				}
			}
		}
		// 备注说明
		String memo = multiRequest.getParameter("memo");
		bean.setMemo(memo);
		try{
			// 保存
			if(bean.getProjId()==null){
				bean = (TbZcproj) this.getCommonBo().save(bean);
				request.setAttribute("opt", "insert");
			}else{
				bean = (TbZcproj) this.getCommonBo().update(bean);
			}
			// 更新附件编号
			String relIds = multiRequest.getParameter("relIds");
			FileManager.getInstance().update(bean.getProjId(),relIds);
			// 项目图片
			FileItem[] picItems = multiRequest.getFiles("pic");
			String[] picOrders = multiRequest.getParameterValues("picOrder");
			String[] picMemos = multiRequest.getParameterValues("picMemo");
			if(picItems!=null && picOrders!=null && picMemos!=null && picItems.length==picOrders.length && picItems.length==picMemos.length && picItems.length>0){
				for(int i=0;i<picItems.length;i++){
					FileItem item = picItems[i];
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
							String fileName = DateUtil.format(dateNow, "yyyyMMddHHmmss")+"_"+i+new Random().nextInt(1000)+"."+lastName;
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
							TbZcprojPic beanPic = new TbZcprojPic();
							beanPic.setProjId(bean.getProjId());
							beanPic.setPicPath(uploadPath);
							beanPic.setNumOrder(picOrders[i]);
							beanPic.setPicPublish("1");
							beanPic.setPicAlt(picMemos[i]);
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
			}
			// 项目选项
			String[] optionNames = multiRequest.getParameterValues("optionName");
			String[] optionAmounts = multiRequest.getParameterValues("optionAmount");
			String[] optionMemos = multiRequest.getParameterValues("optionMemo");
			if(optionNames!=null && optionAmounts!=null && optionMemos!=null && optionNames.length==optionAmounts.length && optionNames.length==optionMemos.length && optionNames.length>0){
				for(int i=0;i<optionNames.length;i++){
					try {
						// 项目选项
						TbZcprojOption beanOption = new TbZcprojOption();
						beanOption.setProjId(bean.getProjId());
						beanOption.setOptionName(optionNames[i]);
						beanOption.setOptionCode("00"+i);
						beanOption.setOptionOrder("00"+i);
						BigDecimal optionAmountD = null;
						String optionAmount = optionAmounts[i];
						if(optionAmount!=null && !"".equals(optionAmount)){
							try{
								optionAmountD = new BigDecimal(optionAmount).setScale(2, 4);
							}catch(Exception e){}
						}
						beanOption.setOptionAmount(optionAmountD);
						beanOption.setOptionPublish("1");
						beanOption.setOptionMemo(optionMemos[i]);
						// 保存选项
						this.getCommonBo().save(beanOption);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}
			// 返回
			request.setAttribute("projId", bean.getProjId());
			return SUCCESS;
		}catch(Exception e){}
		// 保存失败
		request.setAttribute("bean", bean);
		// 项目分类集合
		String hql = "from TbZcprojType a where a.parentId=1 order by a.numOrder";
		List typeList = this.getCommonBo().findHQL(hql);
		List typeNameList = this.getList();
		if(typeList!=null && !typeList.isEmpty()){
			for(int i=0;i<typeList.size();i++){
				TbZcprojType obj = (TbZcprojType)typeList.get(i);
				typeNameList.add(obj);
				getAllZcProjTypes(typeNameList,obj);
			}
		}
		request.setAttribute("typeList", typeNameList);
		// 返回
		return INPUT;
	}

	// 众筹项目图片列表页面
	public String picList() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 标签页
		String num = request.getParameter("num");
		request.setAttribute("num", num);
		// 项目
		TbZcproj bean = null;
		// 项目ID
		String projId = request.getParameter("projId");
		if(projId!=null && !"".equals(projId)){
			try{
				bean = (TbZcproj)this.getCommonBo().get(TbZcproj.class, new Long(projId));
			}catch(Exception e){}
		}
		if(bean!=null){
			request.setAttribute("bean", bean);
			// 构造查询
			String hql = "from TbZcprojPic a where a.projId=?";
			List pars = this.getList();
			pars.add(bean.getProjId());
			// 对外发布
			String publish = request.getParameter("publish");
			if(publish!=null && "1".equals(publish)){
				hql += " and a.picPublish=?";
				pars.add("1");
			}else if(publish!=null && !"".equals(publish)){
				hql += " and (a.picPublish is null or a.picPublish<>?)";
				pars.add("1");
			}
			request.setAttribute("publish", publish);
			// 排序
			hql += " order by a.numOrder";
			// 执行查询
			List picList = this.getCommonBo().findHQL(hql, pars);
			request.setAttribute("picList", picList);
		}
		// 返回
		return "picList";
	}
	
	// 众筹项目图片新增、编辑页面
	public String picEdit() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 项目图片
		TbZcprojPic bean = null;
		// 项目图片ID
		String picId = request.getParameter("picId");
		if(picId!=null && !"".equals(picId)){
			try{
				bean = (TbZcprojPic)this.getCommonBo().get(TbZcprojPic.class, new Long(picId));
			}catch(Exception e){}
		}
		if(bean==null){
			bean = new TbZcprojPic();
			// 项目ID
			Long projIdL = null;
			String projId = request.getParameter("projId");
			if(projId!=null && !"".equals(projId)){
				try{
					projIdL = new Long(projId);
				}catch(Exception e){}
			}
			if(projIdL!=null){
				bean.setProjId(projIdL);
				// 图片排序
				String hqlCount = "select count(*) from TbZcprojPic a where a.projId="+projIdL;
				int count = this.getCommonBo().getHQLNum(hqlCount);
				bean.setNumOrder((count+1)+"");
			}
		}
		request.setAttribute("bean", bean);
		// 返回
		return "picEdit";
	}
	
	// 众筹项目图片新增、编辑
	public String picUpdate() throws Exception {
		HttpServletRequest request = this.getRequest();
		MultiRequestUtil multiRequest = new MultiRequestUtil(request);
		// 项目图片
		TbZcprojPic bean = null;
		// 项目图片ID
		String picId = multiRequest.getParameter("picId");
		if(picId!=null && !"".equals(picId)){
			try{
				bean = (TbZcprojPic)this.getCommonBo().get(TbZcprojPic.class, new Long(picId));
			}catch(Exception e){}
		}
		if(bean==null){
			bean = new TbZcprojPic();
			// 项目ID
			Long projIdL = null;
			String projId = multiRequest.getParameter("projId");
			if(projId!=null && !"".equals(projId)){
				try{
					projIdL = new Long(projId);
				}catch(Exception e){}
			}
			bean.setProjId(projIdL);
		}
		if(bean.getProjId()!=null){
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
						String fileName = DateUtil.format(dateNow, "yyyyMMddHHmmss")+"_"+new Random().nextInt(1000)+"."+lastName;
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
			// 图片对外显示
			String picPublish = multiRequest.getParameter("picPublish");
			if(picPublish!=null && "1".equals(picPublish)){
				bean.setPicPublish("1");
			}else{
				bean.setPicPublish("0");
			}
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
				return "picEdit";
			}
		}
		// 返回
		return "winSuccess";
	}

	// 众筹项目图片删除
	public String picDel() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 项目图片
		TbZcprojPic bean = null;
		// 项目图片ID
		String picId = request.getParameter("picId");
		if(picId!=null && !"".equals(picId)){
			try{
				bean = (TbZcprojPic)this.getCommonBo().get(TbZcprojPic.class, new Long(picId));
			}catch(Exception e){}
		}
		if(bean!=null){
			String hql = "select count(*) from TbZcprojPic a where a.projId="+bean.getProjId();
			int count = this.getCommonBo().getHQLNum(hql);
			if(count==1){
				request.setAttribute("alert", "删除失败，项目图片不能少于1个！");
			}else{
				this.getCommonBo().delete(bean);
			}
		}
		// 返回
		return picList();
	}

	// 众筹项目选项列表页面
	public String optionList() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 标签页
		String num = request.getParameter("num");
		request.setAttribute("num", num);
		// 项目
		TbZcproj bean = null;
		// 项目ID
		String projId = request.getParameter("projId");
		if(projId!=null && !"".equals(projId)){
			try{
				bean = (TbZcproj)this.getCommonBo().get(TbZcproj.class, new Long(projId));
			}catch(Exception e){}
		}
		if(bean!=null){
			request.setAttribute("bean", bean);
			// 构造查询
			String hql = "from TbZcprojOption a where a.projId=?";
			List pars = this.getList();
			pars.add(bean.getProjId());
			// 对外发布
			String publish = request.getParameter("publish");
			if(publish!=null && "1".equals(publish)){
				hql += " and a.optionPublish=?";
				pars.add("1");
			}else if(publish!=null && !"".equals(publish)){
				hql += " and (a.optionPublish is null or a.optionPublish<>?)";
				pars.add("1");
			}
			request.setAttribute("publish", publish);
			// 排序
			hql += " order by a.optionOrder";
			// 执行查询
			List optionList = this.getCommonBo().findHQL(hql, pars);
			request.setAttribute("optionList", optionList);
		}
		// 返回
		return "optionList";
	}
	
	// 众筹项目选项新增、编辑页面
	public String optionEdit() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 项目选项
		TbZcprojOption bean = null;
		// 项目选项ID
		String optionId = request.getParameter("optionId");
		if(optionId!=null && !"".equals(optionId)){
			try{
				bean = (TbZcprojOption)this.getCommonBo().get(TbZcprojOption.class, new Long(optionId));
			}catch(Exception e){}
		}
		if(bean==null){
			bean = new TbZcprojOption();
			// 项目ID
			Long projIdL = null;
			String projId = request.getParameter("projId");
			if(projId!=null && !"".equals(projId)){
				try{
					projIdL = new Long(projId);
				}catch(Exception e){}
			}
			if(projIdL!=null){
				bean.setProjId(projIdL);
				// 选项编码
				String hqlCount = "select count(*) from TbZcprojOption a where a.projId="+projIdL;
				int count = this.getCommonBo().getHQLNum(hqlCount);
				bean.setOptionCode((count>9?"0":"00")+(count+1));
			}
			// 选项金额
			bean.setOptionAmount(new BigDecimal(1).setScale(2, 4));
		}
		request.setAttribute("bean", bean);
		// 返回
		return "optionEdit";
	}
	
	// 众筹项目选项新增、编辑
	public String optionUpdate() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 项目选项
		TbZcprojOption bean = null;
		// 项目选项ID
		String optionId = request.getParameter("optionId");
		if(optionId!=null && !"".equals(optionId)){
			try{
				bean = (TbZcprojOption)this.getCommonBo().get(TbZcprojOption.class, new Long(optionId));
			}catch(Exception e){}
		}
		if(bean==null){
			bean = new TbZcprojOption();
			// 项目ID
			Long projIdL = null;
			String projId = request.getParameter("projId");
			if(projId!=null && !"".equals(projId)){
				try{
					projIdL = new Long(projId);
				}catch(Exception e){}
			}
			bean.setProjId(projIdL);
		}
		if(bean.getProjId()!=null){
			// 选项名称
			String optionName = request.getParameter("optionName");
			bean.setOptionName(optionName);
			// 选项编码
			String optionCode = request.getParameter("optionCode");
			bean.setOptionCode(optionCode);
			// 选项排序
			String optionOrder = request.getParameter("optionOrder");
			bean.setOptionOrder(optionOrder);
			// 选项金额
			BigDecimal optionAmountD = null;
			String optionAmount = request.getParameter("optionAmount");
			if(optionAmount!=null && !"".equals(optionAmount)){
				try{
					optionAmountD = new BigDecimal(optionAmount).setScale(2, 4);
				}catch(Exception e){}
			}
			bean.setOptionAmount(optionAmountD);
			// 选项数量计量单位
			String unitName = request.getParameter("unitName");
			bean.setUnitName(unitName);
			// 项目对外显示
			String optionPublish = request.getParameter("optionPublish");
			if(optionPublish!=null && "1".equals(optionPublish)){
				bean.setOptionPublish("1");
			}else{
				bean.setOptionPublish("0");
			}
			// 选项数量限制
			String limitCount = request.getParameter("limitCount");
			if(limitCount!=null && "1".equals(limitCount)){
				bean.setLimitCount("1");
			}else{
				bean.setLimitCount("0");
			}
			if("1".equals(bean.getLimitCount())){
				// 选项剩余数量
				Long optionCountL = null;
				String optionCount = request.getParameter("optionCount");
				if(optionCount!=null && !"".equals(optionCount)){
					try{
						optionCountL = new Long(optionCount);
					}catch(Exception e){}
				}
				bean.setOptionCount(optionCountL);
			}
			// 选项说明
			String optionMemo = request.getParameter("optionMemo");
			bean.setOptionMemo(optionMemo);
			// 备注
			String memo = request.getParameter("memo");
			bean.setMemo(memo);
			// 保存选项
			try{
				this.getCommonBo().saveOrUpdate(bean);
			} catch (Exception e) {
				// 返回
				request.setAttribute("alert", "保存时发生异常！");
				return "optionEdit";
			}
		}
		// 返回
		return "winSuccess";
	}

	// 众筹项目选项删除
	public String optionDel() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 项目选项
		TbZcprojOption bean = null;
		// 项目选项ID
		String optionId = request.getParameter("optionId");
		if(optionId!=null && !"".equals(optionId)){
			try{
				bean = (TbZcprojOption)this.getCommonBo().get(TbZcprojOption.class, new Long(optionId));
			}catch(Exception e){}
		}
		if(bean!=null){
			this.getCommonBo().delete(bean);
		}
		// 返回
		return optionList();
	}
	
	// 众筹项目进展列表页面
	public String gressList() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 标签页
		String num = request.getParameter("num");
		request.setAttribute("num", num);
		// 项目
		TbZcproj bean = null;
		// 项目ID
		String projId = request.getParameter("projId");
		if(projId!=null && !"".equals(projId)){
			try{
				bean = (TbZcproj)this.getCommonBo().get(TbZcproj.class, new Long(projId));
			}catch(Exception e){}
		}
		if(bean!=null){
			request.setAttribute("bean", bean);
			// 构造查询
			String hql = "from TbZcprojProgress a where a.projId=?";
			List pars = this.getList();
			pars.add(bean.getProjId());
			hql += " order by a.gressTime desc";
			// 执行查询
			List gressList = this.getCommonBo().findHQLPage(hql, pars);
			request.setAttribute("gressList", gressList);
		}
		// 返回
		return "gressList";
	}
	
	// 众筹项目进展新增、编辑页面
	public String gressEdit() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 项目进展
		TbZcprojProgress bean = null;
		// 项目选项ID
		String gressId = request.getParameter("gressId");
		if(gressId!=null && !"".equals(gressId)){
			try{
				bean = (TbZcprojProgress)this.getCommonBo().get(TbZcprojProgress.class, new Long(gressId));
			}catch(Exception e){}
		}
		if(bean==null){
			bean = new TbZcprojProgress();
			// 项目Id
			Long projIdL = null;
			String projId = request.getParameter("projId");
			if(projId!=null && !"".equals(projId)){
				try{
					projIdL = new Long(projId);
				}catch(Exception e){}
			}
			bean.setProjId(projIdL);
			// 进展时间
			Date dateNow = this.getCommonBo().getSysDate();
			bean.setGressTime(dateNow);
		}
		request.setAttribute("bean", bean);
		// 返回
		return "gressEdit";
	}
	
	// 众筹项目进展新增、编辑
	public String gressUpdate() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 项目进展
		TbZcprojProgress bean = null;
		// 项目选项ID
		String gressId = request.getParameter("gressId");
		if(gressId!=null && !"".equals(gressId)){
			try{
				bean = (TbZcprojProgress)this.getCommonBo().get(TbZcprojProgress.class, new Long(gressId));
			}catch(Exception e){}
		}
		if(bean==null){
			bean = new TbZcprojProgress();
			// 项目ID
			Long projIdL = null;
			String projId = request.getParameter("projId");
			if(projId!=null && !"".equals(projId)){
				try{
					projIdL = new Long(projId);
				}catch(Exception e){}
			}
			bean.setProjId(projIdL);
		}
		if(bean.getProjId()!=null){
			// 进展时间
			String gressTime = request.getParameter("gressTime");
			if(gressTime!=null && !"".equals(gressTime)){
				bean.setGressTime(DateUtil.toDate(gressTime,"yyyy-MM-dd"));
			}else{
				bean.setGressTime(null);
			}
			// 进展详情
			String gressContent = request.getParameter("gressContent");
			bean.setGressContent(gressContent);
			// 备注
			String memo = request.getParameter("memo");
			bean.setMemo(memo);
			// 保存选项
			try{
				this.getCommonBo().saveOrUpdate(bean);
			} catch (Exception e) {
				// 返回
				request.setAttribute("alert", "保存时发生异常！");
				return "gressEdit";
			}
		}
		// 返回
		return "winSuccess";
	}

	// 众筹项目进展删除
	public String gressDel() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 项目进展
		TbZcprojProgress bean = null;
		// 项目选项ID
		String gressId = request.getParameter("gressId");
		if(gressId!=null && !"".equals(gressId)){
			try{
				bean = (TbZcprojProgress)this.getCommonBo().get(TbZcprojProgress.class, new Long(gressId));
			}catch(Exception e){}
		}
		if(bean!=null){
			this.getCommonBo().delete(bean);
		}
		// 返回
		return gressList();
	}

	// 众筹项目捐赠列表页面
	public String orderList() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 标签页
		String num = request.getParameter("num");
		request.setAttribute("num", num);
		// 项目
		TbZcproj bean = null;
		// 项目ID
		String projId = request.getParameter("projId");
		if(projId!=null && !"".equals(projId)){
			try{
				bean = (TbZcproj)this.getCommonBo().get(TbZcproj.class, new Long(projId));
			}catch(Exception e){}
		}
		if(bean!=null){
			request.setAttribute("bean", bean);
			// 构造查询
			String hql = "from TbZcprojOrder a where a.projId=?";
			List pars = this.getList();
			pars.add(bean.getProjId());
			// 捐赠选项
			String optionName = request.getParameter("optionName");
			if(optionName!=null && !"".equals(optionName)){
				hql += " and a.optionName like ?";
				pars.add("%"+optionName+"%");
			}
			// 捐赠单号
			String orderNum = request.getParameter("orderNum");
			if(orderNum!=null && !"".equals(orderNum)){
				hql += " and a.orderNum like ?";
				pars.add("%"+orderNum+"%");
			}
			// 捐赠方式
			String orderType = request.getParameter("orderType");
			if(orderType!=null && !"".equals(orderType)){
				hql += " and a.orderType like ?";
				pars.add("%"+orderType+"%");
			}
			// 证书
			String needZhengshu = request.getParameter("needZhengshu");
			if(needZhengshu!=null && "1".equals(needZhengshu)){
				hql += " and a.needZhengshu = ?";
				pars.add("1");
			}else if(needZhengshu!=null && !"".equals(needZhengshu)){
				hql += " and (a.needZhengshu is null or a.needZhengshu <> ?)";
				pars.add("1");
			}
			// 发票
			String needFapiao = request.getParameter("needFapiao");
			if(needZhengshu!=null && "1".equals(needZhengshu)){
				hql += " and a.needFapiao = ?";
				pars.add("1");
			}else if(needZhengshu!=null && !"".equals(needZhengshu)){
				hql += " and (a.needFapiao is null or a.needFapiao <> ?)";
				pars.add("1");
			}
			// 匿名捐赠
			String niMing = request.getParameter("niMing");
			if(niMing!=null && "1".equals(niMing)){
				hql += " and a.niMing = ?";
				pars.add("1");
			}else if(niMing!=null && !"".equals(niMing)){
				hql += " and (a.niMing is null or a.niMing <> ?)";
				pars.add("1");
			}
			// 捐赠人
			String personName = request.getParameter("personName");
			if(personName!=null && !"".equals(personName)){
				hql += " and a.personName like ?";
				pars.add("%"+personName+"%");
			}
			// 捐赠人信息
			String personInfo = request.getParameter("personInfo");
			if(personInfo!=null && !"".equals(personInfo)){
				hql += " and (a.personPhone like ? or a.personTel like ? or a.personEmail like ? or a.addressSheng like ? or a.addressShi like ? or a.addressQu like ? or a.addressDetail like ? or a.addressZip like ?)";
				pars.add("%"+personInfo+"%");
				pars.add("%"+personInfo+"%");
				pars.add("%"+personInfo+"%");
				pars.add("%"+personInfo+"%");
				pars.add("%"+personInfo+"%");
				pars.add("%"+personInfo+"%");
				pars.add("%"+personInfo+"%");
				pars.add("%"+personInfo+"%");
			}
			// 校友
			String alumni = request.getParameter("alumni");
			if(alumni!=null && "1".equals(alumni)){
				hql += " and a.alumniFlag = ?";
				pars.add("1");
			}else if(alumni!=null && !"".equals(alumni)){
				hql += " and (a.alumniFlag is null or a.alumniFlag <> ?)";
				pars.add("1");
			}
			// 校友信息
			String alumniInfo = request.getParameter("alumniInfo");
			if(alumniInfo!=null && !"".equals(alumniInfo)){
				hql += " and (a.studyYearin like ? or a.studyYearover like ? or a.studyAcademy like ? or a.studyMajor like ? or a.studyClass like ? or a.studyNum like ? or a.studyDegree like ?)";
				pars.add("%"+alumniInfo+"%");
				pars.add("%"+alumniInfo+"%");
				pars.add("%"+alumniInfo+"%");
				pars.add("%"+alumniInfo+"%");
				pars.add("%"+alumniInfo+"%");
				pars.add("%"+alumniInfo+"%");
				pars.add("%"+alumniInfo+"%");
			}
			// 标记
			String markFlag = request.getParameter("markFlag");
			if(markFlag!=null && "1".equals(markFlag)){
				hql += " and a.mark is not null";
				String mark = request.getParameter("mark");
				if(mark!=null && !"".equals(mark)){
					hql += " and a.mark like ?";
					pars.add("%"+mark+"%");
				}
			}else if(markFlag!=null && !"".equals(markFlag)){
				hql += " and a.mark is null";
			}
			// 捐赠币种
			String amountType = request.getParameter("amountType");
			if(amountType!=null && !"".equals(amountType)){
				hql += " and a.orderAmountType like ?";
				pars.add("%"+amountType+"%");
			}
			// 提交起始时间
			Date orderTimeBegD = null;
			String orderTimeBeg = request.getParameter("orderTimeBeg");
			if(orderTimeBeg!=null && !"".equals(orderTimeBeg)){
				orderTimeBegD = DateUtil.toDate(orderTimeBeg, "yyyy-MM-dd HH:mm");
			}
			if(orderTimeBegD!=null){
				hql += " and a.orderTime >= ?";
				pars.add(orderTimeBegD);
			}
			// 提交截止时间
			Date orderTimeEndD = null;
			String orderTimeEnd = request.getParameter("orderTimeEnd");
			if(orderTimeEnd!=null && !"".equals(orderTimeEnd)){
				orderTimeEndD = DateUtil.toDate(orderTimeEnd, "yyyy-MM-dd HH:mm");
			}
			if(orderTimeEndD!=null){
				hql += " and a.orderTime <= ?";
				pars.add(orderTimeEndD);
			}
			// 付款起始时间
			Date orderOkTimeBegD = null;
			String orderOkTimeBeg = request.getParameter("orderOkTimeBeg");
			if(orderOkTimeBeg!=null && !"".equals(orderOkTimeBeg)){
				orderOkTimeBegD = DateUtil.toDate(orderOkTimeBeg, "yyyy-MM-dd HH:mm");
			}
			if(orderOkTimeBegD!=null){
				hql += " and a.orderOkTime >= ?";
				pars.add(orderOkTimeBegD);
			}
			// 付款截止时间
			Date orderOkTimeEndD = null;
			String orderOkTimeEnd = request.getParameter("orderOkTimeEnd");
			if(orderOkTimeEnd!=null && !"".equals(orderOkTimeEnd)){
				orderOkTimeEndD = DateUtil.toDate(orderOkTimeEnd, "yyyy-MM-dd HH:mm");
			}
			if(orderOkTimeEndD!=null){
				hql += " and a.orderOkTime <= ?";
				pars.add(orderOkTimeEndD);
			}
			// 捐赠状态
			String orderStatus = request.getParameter("orderStatus");
			if(orderStatus!=null && "1".equals(orderStatus)){
				hql += " and a.orderStatus = ?";
				pars.add("1");
			}else if(orderStatus!=null && !"".equals(orderStatus)){
				hql += " and (a.orderStatus is null or a.orderStatus <> ?)";
				pars.add("1");
			}
			// 捐赠人类型
			String personType = request.getParameter("personType");
			if(personType!=null && "1".equals(personType)){
				hql += " and a.personType = ?";
				pars.add("1");
			}else if(personType!=null && !"".equals(personType)){
				hql += " and (a.personType is null or a.personType <> ?)";
				pars.add("1");
			}
			// 查询合计条数
			Long orderCount = (Long)this.getCommonBo().getHQLUnique("select count(*) "+hql, pars);
			request.setAttribute("orderCount", orderCount);
			// 查询合计已付款条数
			Long orderOkCount = (Long)this.getCommonBo().getHQLUnique("select count(*) "+hql+" and a.orderStatus = '1'", pars);
			request.setAttribute("orderOkCount", orderOkCount);
			// 查询合计已付款 在线捐赠条数
			Long orderOkType0Count = (Long)this.getCommonBo().getHQLUnique("select count(*) "+hql+" and a.orderStatus = '1' and (a.orderType like '%在线%' or a.orderType like '%交易号%')", pars);
			request.setAttribute("orderOkType0Count", orderOkType0Count);
			// 查询合计已付款 个人捐赠条数
			Long orderOkType1Count = (Long)this.getCommonBo().getHQLUnique("select count(*) "+hql+" and a.orderStatus = '1' and a.personType = '1'", pars);
			request.setAttribute("orderOkType1Count", orderOkType1Count);
			// 查询合计已付款 集体捐赠条数
			Long orderOkType2Count = (Long)this.getCommonBo().getHQLUnique("select count(*) "+hql+" and a.orderStatus = '1' and (a.personType is null or a.personType <> '1')", pars);
			request.setAttribute("orderOkType2Count", orderOkType2Count);
			// 查询合计已付款总金额
			BigDecimal orderOkAmount = (BigDecimal)this.getCommonBo().getHQLUnique("select sum(a.orderAmount) "+hql+" and a.orderStatus = '1'", pars);
			request.setAttribute("orderOkAmount", orderOkAmount);
			// 默认查询条件
			hql += " order by a.orderTime desc,a.orderId desc";
			// 执行查询
			List orderList = this.getCommonBo().findHQLPage(hql, pars);
			request.setAttribute("orderList", orderList);
		}
		// 返回
		return "orderList";
	}
	
	// 众筹项目捐赠详情页面
	public String orderView() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 项目捐赠
		TbZcprojOrder bean = null;
		// 项目捐赠ID
		String orderId = request.getParameter("orderId");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbZcprojOrder)this.getCommonBo().get(TbZcprojOrder.class, new Long(orderId));
			}catch(Exception e){}
		}
		request.setAttribute("bean", bean);
		// 返回
		return "orderView";
	}
	
	// 众筹项目捐赠标记页面
	public String orderMark() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 项目捐赠
		TbZcprojOrder bean = null;
		// 项目捐赠ID
		String orderId = request.getParameter("orderId");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbZcprojOrder)this.getCommonBo().get(TbZcprojOrder.class, new Long(orderId));
			}catch(Exception e){}
		}
		request.setAttribute("bean", bean);
		// 返回
		return "orderMark";
	}
	
	// 众筹项目进展标记
	public String orderMarkUpdate() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 项目捐赠
		TbZcprojOrder bean = null;
		// 项目捐赠ID
		String orderId = request.getParameter("orderId");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbZcprojOrder)this.getCommonBo().get(TbZcprojOrder.class, new Long(orderId));
			}catch(Exception e){}
		}
		if(bean!=null){
			// 标记信息
			String mark = request.getParameter("mark");
			bean.setMark(mark);
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

	// 众筹项目捐赠删除
	public String orderDel() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 项目捐赠
		TbZcprojOrder bean = null;
		// 项目捐赠ID
		String orderId = request.getParameter("orderId");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbZcprojOrder)this.getCommonBo().get(TbZcprojOrder.class, new Long(orderId));
			}catch(Exception e){}
		}
		if(bean!=null){
			this.getCommonBo().delete(bean);
		}
		// 返回
		return orderList();
	}
	
	// 新增&编辑
	public String toAddOrEdit()  throws Exception {
		HttpServletRequest request = this.getRequest();
		// 系统当前时间
		Date dateNow = this.getCommonBo().getSysDate();
		// 获取当前时间相关参数
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(dateNow);
		// 当前年份
		int nowYear = c.get(Calendar.YEAR);
		request.setAttribute("nowYear", nowYear);
		// 项目捐赠
		TbZcprojOrder bean = null;
		// 项目捐赠ID
		String orderId = request.getParameter("orderId");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbZcprojOrder)this.getCommonBo().get(TbZcprojOrder.class, new Long(orderId));
			}catch(Exception e){}
		}
		request.setAttribute("bean", bean);
		// 项目
		TbZcproj beanZcproj = null;
		// 项目ID
		String projId = request.getParameter("projId");
		if(projId!=null && !"".equals(projId)){
			try{
				beanZcproj = (TbZcproj)this.getCommonBo().get(TbZcproj.class, new Long(projId));
			}catch(Exception e){}
		}
		request.setAttribute("beanZcproj", beanZcproj);
		// 获取项目选项
		if(beanZcproj!=null){
			String hql = "from TbZcprojOption a where a.projId=? order by a.optionOrder";
			List pars = this.getList();
			pars.add(beanZcproj.getProjId());
			List optionList = this.getCommonBo().findHQL(hql, pars);
			request.setAttribute("optionList", optionList);
		}
		// 返回
		return "orderEdit";
	}
	public String doAddOrEdit()  throws Exception {
		HttpServletRequest request = this.getRequest();
		// 系统当前时间
		Date dateNow = this.getCommonBo().getSysDate();
		// 获取当前时间相关参数
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(dateNow);
		// 当前年份
		int nowYear = c.get(Calendar.YEAR);
		request.setAttribute("nowYear", nowYear);
		// 项目
		TbZcproj beanZcproj = null;
		// 项目ID
		String projId = request.getParameter("projId");
		if(projId!=null && !"".equals(projId)){
			try{
				beanZcproj = (TbZcproj)this.getCommonBo().get(TbZcproj.class, new Long(projId));
			}catch(Exception e){}
		}
		request.setAttribute("beanZcproj", beanZcproj);
		// 获取项目选项
		if(beanZcproj!=null){
			String hql = "from TbZcprojOption a where a.projId=? order by a.optionOrder";
			List pars = this.getList();
			pars.add(beanZcproj.getProjId());
			List optionList = this.getCommonBo().findHQL(hql, pars);
			request.setAttribute("optionList", optionList);
		}
		// 项目捐赠
		TbZcprojOrder bean = null;
		// 项目捐赠ID
		String orderId = request.getParameter("orderId");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbZcprojOrder)this.getCommonBo().get(TbZcprojOrder.class, new Long(orderId));
			}catch(Exception e){}
		}
		if(bean==null){
			bean = new TbZcprojOrder();
		}
		// 捐赠方式
		String orderType = request.getParameter("orderType");
		bean.setOrderType(orderType);
		// 捐赠状态
		String orderStatus = request.getParameter("orderStatus");
		bean.setOrderStatus(orderStatus);
		// 提交时间
		String orderTime = request.getParameter("orderTime");
		if(orderTime!=null&&!"".equals(orderTime)){
			bean.setOrderTime(DateUtil.toDate(orderTime, "yyyy-MM-dd HH:mm"));
		}else{
			bean.setOrderTime(null);
		}
		// 付款时间
		String orderOkTime = request.getParameter("orderOkTime");
		if(orderOkTime!=null&&!"".equals(orderOkTime)){
			bean.setOrderOkTime(DateUtil.toDate(orderOkTime, "yyyy-MM-dd HH:mm"));
		}else{
			bean.setOrderOkTime(null);
		}
		// 证书
		String needZhengshu = request.getParameter("needZhengshu");
		bean.setNeedZhengshu(needZhengshu);
		// 发票
		String needFapiao = request.getParameter("needFapiao");
		bean.setNeedFapiao(needFapiao);
		// 捐赠人
		// 捐赠人类型
		String personType = request.getParameter("personType");
		if(personType==null || !"2".equals(personType)){
			personType = "1";
		}
		bean.setPersonType(personType);
		// 捐赠人数
    	Long personCountL = null;
		String personCount = request.getParameter("personCount");
        if("2".equals(personType) && personCount!=null && !"".equals(personCount)){
        	try{
        		personCountL = new Long(personCount);
        	}catch(Exception e){}
        }
        if(personCountL==null || personCountL<1){
        	personCountL = new Long(1);
        }
		bean.setPersonCount(personCountL);
		// 姓名
		String person = request.getParameter("person");
		if(person==null || "".equals(person)){
			person = "匿名";
		}
		bean.setPersonName(person);
		// 性别
		String personSex = request.getParameter("personSex");
		bean.setPersonSex(personSex);
		// 匿名
		String niMing = request.getParameter("niMing");
		bean.setNiMing(niMing);
		// 手机
		String personPhone = request.getParameter("personPhone");
		bean.setPersonPhone(personPhone);
		// 电话
		String personTel = request.getParameter("personTel");
		bean.setPersonTel(personTel);
		// 邮箱
		String personEmail = request.getParameter("personEmail");
		bean.setPersonEmail(personEmail);
		// 邮编
		String zip = request.getParameter("zip");
		bean.setAddressZip(zip);
		// 省
		String sheng = request.getParameter("sheng");
		bean.setAddressSheng(sheng);
		// 市
		String shi = request.getParameter("shi");
		bean.setAddressShi(shi);
		// 区/县
		String qu = request.getParameter("qu");
		bean.setAddressQu(qu);
		// 详细地址
		String address = request.getParameter("address");
		bean.setAddressDetail(address);
		// 校友资料
		// 是否校友		
		String alumniFlag = request.getParameter("alumniFlag");
		bean.setAlumniFlag(alumniFlag);
		// 入学年
		String academyBeg = request.getParameter("academyBeg");
		bean.setStudyYearin(academyBeg);
		// 离校年
		String academyEnd = request.getParameter("academyEnd");
		bean.setStudyYearover(academyEnd);
		// 院系
		String academy = request.getParameter("academy");
		bean.setStudyAcademy(academy);
		// 专业
		String major = request.getParameter("major");
		bean.setStudyMajor(major);
		// 班级
		String clazz = request.getParameter("clazz");
		bean.setStudyClass(clazz);
		// 学号
		String studyno = request.getParameter("studyno");
		bean.setStudyNum(studyno);
		// 学历
		String academyDegree = request.getParameter("academyDegree");
		bean.setStudyDegree(academyDegree);
		// 工作资料
		// 公司
		String company = request.getParameter("company");
		bean.setWorkCompany(company);
		// 部门
		String department = request.getParameter("department");
		bean.setWorkDepart(department);
		// 职位
		String duty = request.getParameter("duty");
		bean.setWorkDuty(duty);
		// 其他信息
		String orderMemo = request.getParameter("orderMemo");
		bean.setOrderMemo(orderMemo);
		// 标记信息
		String mark = request.getParameter("mark");
		bean.setMark(mark);
		// 回执信息
		String receipt = request.getParameter("receipt");
		bean.setReceipt(receipt);
		// 备注
		String memo = request.getParameter("memo");
		bean.setMemo(memo);
		// 指定捐赠项目信息
		if(bean.getOrderId()==null && beanZcproj==null){
			request.setAttribute("alert", "项目不存在！");
			request.setAttribute("bean", bean);
			return "orderEdit";
		}
		if(bean.getOrderId()==null){
			bean.setProjId(beanZcproj.getProjId());
			bean.setProjName(beanZcproj.getProjName());
			// 众筹项目图片
	        String projPic = null;
	    	try{
	    		String hql = "select a.picPath from TbZcprojPic a where a.projId="+beanZcproj.getProjId()+" and a.picPublish='1' order by a.numOrder";
	    		projPic = (String)this.getCommonBo().getHQLUnique(hql);
	    	}catch(Exception e){}
	        bean.setProjPic(projPic);
		}
		// 众筹项目选项
		Long optionIdL = null;
        String optionId = request.getParameter("option");
        if(optionId!=null && !"".equals(optionId)){
        	try{
        		optionIdL = new Long(optionId);
        	}catch(Exception e){}
        }
		String optionName = request.getParameter("optionName");
		if(optionIdL==null && (optionName==null || "".equals(optionName))){
			request.setAttribute("alert", "请设置捐赠选项！");
			request.setAttribute("bean", bean);
			return "orderEdit";
		}
    	bean.setOptionId(optionIdL);
    	bean.setOptionName(optionName);
    	// 选项个数
    	Long optionCountL = null;
        String optionCount = request.getParameter("optionCount");
        if(optionCount!=null && !"".equals(optionCount)){
        	try{
        		optionCountL = new Long(optionCount);
        	}catch(Exception e){}
        }
        if(optionCountL!=null && optionCountL<1){
			request.setAttribute("alert", "捐赠份数不能少于1份！");
			request.setAttribute("bean", bean);
			return "orderEdit";
        }
        bean.setOptionCount(optionCountL);
        // 捐赠币种
    	String orderAmoutType = request.getParameter("amountType");
        if(orderAmoutType==null || "".equals(orderAmoutType)){
        	orderAmoutType = "人民币";
        }
        bean.setOrderAmountType(orderAmoutType);
        // 捐赠币种总金额
    	BigDecimal orderAmoutViewD = null;
    	String orderAmoutView = request.getParameter("amountView");
    	if(orderAmoutView!=null && !"".equals(orderAmoutView)){
        	try{
        		orderAmoutViewD = new BigDecimal(orderAmoutView).setScale(2, 4);
        	}catch(Exception e){}
        }
    	if(orderAmoutViewD==null || orderAmoutViewD.doubleValue()<0){
			request.setAttribute("alert", "捐赠币种总金额不能少于0！");
			request.setAttribute("bean", bean);
			return "orderEdit";
    	}
		bean.setOrderAmountView(orderAmoutViewD);
        // 捐赠币种计量单位
    	String orderAmoutUnit = request.getParameter("amountUnit");
        if(orderAmoutUnit==null || "".equals(orderAmoutUnit)){
        	orderAmoutUnit = "元";
        }
        bean.setOrderAmountUnit(orderAmoutUnit);
        // 捐赠单实算总金额
    	BigDecimal orderAmoutD = null;
    	String orderAmout = request.getParameter("amount");
    	if(orderAmout!=null && !"".equals(orderAmout)){
        	try{
        		orderAmoutD = new BigDecimal(orderAmout).setScale(2, 4);
        	}catch(Exception e){}
        }
    	if(orderAmoutD==null || orderAmoutD.doubleValue()<0){
			request.setAttribute("alert", "实算总金额不能少于0元！");
			request.setAttribute("bean", bean);
			return "orderEdit";
    	}
		bean.setOrderAmount(orderAmoutD);
		//
//		// 众筹项目选项
//        TbZcprojOption beanZcprojOption = null;
//        String optionId = request.getParameter("option");
//        if(optionId!=null && !"".equals(optionId)){
//        	try{
//        		String hql = "from TbZcprojOption a where a.projId="+beanZcproj.getProjId()+" and a.optionId="+new Long(optionId);
//        		beanZcprojOption = (TbZcprojOption)this.getCommonBo().getHQLUnique(hql);
//        	}catch(Exception e){}
//            if(beanZcprojOption==null){
//    			request.setAttribute("alert", "捐赠选项不存在！");
//    			request.setAttribute("bean", bean);
//    			return "orderEdit";
//            }
//        }
//        if(beanZcprojOption!=null){
//        	bean.setOptionId(beanZcprojOption.getOptionId());
//        	bean.setOptionName(beanZcprojOption.getOptionName());
//        	// 选项个数
//        	Long optionCountL = null;
//            String optionCount = request.getParameter("optionCount");
//            if(optionCount!=null && !"".equals(optionCount)){
//            	try{
//            		optionCountL = new Long(optionCount);
//            	}catch(Exception e){}
//            }
//            if(optionCountL==null || optionCountL<1){
//    			request.setAttribute("alert", "捐赠份数不能少于1份！");
//    			request.setAttribute("bean", bean);
//    			return "orderEdit";
//            }
//            bean.setOptionCount(optionCountL);
//            // 捐赠单总金额
//            BigDecimal orderAmoutD = null;
//            try{
//            	orderAmoutD = new BigDecimal(beanZcprojOption.getOptionAmount().doubleValue()*optionCountL).setScale(2, 4);
//        	}catch(Exception e){}
//        	if(orderAmoutD==null || orderAmoutD.doubleValue()<=0){
//    			request.setAttribute("alert", "捐赠总金额不能少于0元！");
//    			request.setAttribute("bean", bean);
//    			return "orderEdit";
//        	}
//    		bean.setOrderAmount(orderAmoutD);
//        }else if(beanZcproj.getOptionOther()!=null && "1".equals(beanZcproj.getOptionOther())){
//        	if(beanZcproj.getOptionOtherName()!=null && !"".equals(beanZcproj.getOptionOtherName())){
//        		bean.setOptionName(beanZcproj.getOptionOtherName());
//        	}else{
//        		bean.setOptionName("任意捐");
//        	}
//        	// 任意捐最低金额
//        	BigDecimal minAmountD = beanZcproj.getMinAmount();
//        	if(minAmountD==null || minAmountD.doubleValue()<=0){
//        		minAmountD = new BigDecimal(0.01).setScale(2, 4);
//        	}
//        	// 捐赠单总金额
//        	BigDecimal orderAmoutD = null;
//        	String orderAmout = request.getParameter("amount");
//        	if(orderAmout!=null && !"".equals(orderAmout)){
//            	try{
//            		orderAmoutD = new BigDecimal(orderAmout).setScale(2, 4);
//            	}catch(Exception e){}
//            }
//        	if(orderAmoutD==null || orderAmoutD.doubleValue()<minAmountD.doubleValue()){
//    			request.setAttribute("alert", "捐赠总金额不能少于"+minAmountD+"元！");
//    			request.setAttribute("bean", bean);
//    			return "orderEdit";
//        	}
//    		bean.setOrderAmount(orderAmoutD);
//        }else{
//			request.setAttribute("alert", "该项目不支持任意捐！");
//			request.setAttribute("bean", bean);
//			return "orderEdit";
//        }
		// 保存
    	try{
    		bean = (TbZcprojOrder)this.getCommonBo().saveOrUpdate(bean);
    		if(bean.getOrderNum()==null || "".equals(bean.getOrderNum())){
                // 单号
        		String orderNum = bean.getOrderId().toString();
        		int orderLen = orderNum.length();
                for(int i=0; i<17-orderLen; i++){  
                	orderNum = "0"+orderNum;  
                }
                orderNum = DateUtil.format(dateNow,"yyyyMMdd")+"001"+orderNum;
                bean.setOrderNum(orderNum);
                bean = (TbZcprojOrder)this.getCommonBo().update(bean);
        		request.setAttribute("bean", bean);
    		}
    		// 返回
        	return "winSuccess";
    	}catch(Exception e){}
		// 返回状态：保存失败
		request.setAttribute("alert", "保存失败！");
		request.setAttribute("bean", bean);
		// 返回
    	return "orderEdit";
	}

	/* 导入相关 */
	// 格式化文本
	private String formatStr(String str) {
		if (str != null && !"".equals(str)) {
			try {
				// 科学计数法
				if (str.matches("[\\+\\-]?[\\d]+([\\.][\\d]*)?[Ee][+-]?[\\d]+")) {
					NumberFormat format = NumberFormat.getInstance();
					format.setGroupingUsed(false);
					str = format.format(Double.parseDouble(str));
				}
			} catch (Exception e) {
			}
		}
		return str;
	}

	// 日期处理
	public Date getExcelDate(HSSFCell cell) throws Exception {
		String ret = "";
		try {
			if (cell != null) {
				if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					if (HSSFDateUtil.isCellDateFormatted(cell)) {// 判断单元格是否属于日期格式
						Date theDate = cell.getDateCellValue();
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						ret = format.format(theDate);
						return DateUtil.toDate(ret, "yyyy-MM-dd HH:mm");
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		ret = formatStr(cell.toString().trim());
		if (isValidDate(ret)) {
			return DateUtil.toDate(ret, "yyyy-MM-dd HH:mm");
		} else
			return toDate(ret);
	}

	private Date toDate(String s) throws Exception {
		Calendar ca = Calendar.getInstance();
		int maxYear = ca.get(Calendar.YEAR) + 10;// 年份最大不能超过距今10年
		try {
			int i = Integer.parseInt(s);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date day = format.parse("1900-01-01");// Excel数字格式日期起始日期
			ca.setTime(day);
			ca.add(Calendar.DATE, i);// i为距离起始日期增加的天数
			day = ca.getTime();
			return day;
		} catch (Exception e) {

		}
		return DateUtil.toDate(s, "yyyy-MM-dd HH:mm");
	}

	public boolean isValidDate(String str) {
		boolean convertSuccess = true;
		// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			// 设置lenient为false.
			// 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
			format.setLenient(false);
			format.parse(str);
		} catch (ParseException e) {
			// e.printStackTrace();
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			convertSuccess = false;
		}
		return convertSuccess;
	}

	// 上传Excel导入进度参数名称
	private String IMPORTCOUNT = "ZCPROJEDITORDEREXCELCOUNT";
	private String IMPORTINDEX = "ZCPROJEDITORDEREXCELINDEX";

	// 上传Excel导入进度
	public String process() throws Exception {
		HttpServletRequest request = this.getRequest();
		HttpServletResponse response = this.getResponse();
		HttpSession session = request.getSession();
		// 导入进度时间戳
		String random = request.getParameter("random");
		if(random==null){
			random = "";
		}
		// 导入进度参数
		Object countObj = session.getAttribute(IMPORTCOUNT + random);
		String count = "";
		if(countObj!=null){
			count = countObj.toString();
		}
		Object indexObj = session.getAttribute(IMPORTINDEX + random);
		String index = "";
		if(indexObj!=null){
			index = indexObj.toString();
		}
		// 传值
		this.sendResponse(response, "{\"count\":" + count + ",\"index\":" + index + "}");
		// 返回
		return null;
	}

	// 导入页面
	public String toImport() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 项目
		TbZcproj beanZcproj = null;
		// 项目ID
		String projId = request.getParameter("projId");
		if(projId!=null && !"".equals(projId)){
			try{
				beanZcproj = (TbZcproj) this.getCommonBo().get(TbZcproj.class, new Long(projId));
			}catch(Exception e){}
		}
		if(beanZcproj==null){
			request.setAttribute("alert", "众筹项目不存在，请关闭并重新打开导入页面！");
			return "import";
		}
		request.setAttribute("beanZcproj", beanZcproj);
		return "import";
	}

	// 批量导入
	public String doImport() throws Exception {
		HttpServletRequest request = this.getRequest();
		MultiRequestUtil multiRequest = new MultiRequestUtil(request);
		HttpSession session = request.getSession();
		// 项目
		TbZcproj beanZcproj = null;
		// 项目ID
		String projId = multiRequest.getParameter("projId");
		if(projId!=null && !"".equals(projId)){
			try{
				beanZcproj = (TbZcproj) this.getCommonBo().get(TbZcproj.class, new Long(projId));
			}catch(Exception e){ }
		}
		if(beanZcproj==null){
			request.setAttribute("alert", "众筹项目不存在，请关闭并重新打开导入页面！");
			return "import";
		}
		request.setAttribute("beanZcproj", beanZcproj);
		// 众筹项目图片
		String projPic = null;
		try{
			String hql = "select a.picPath from TbZcprojPic a where a.projId=" + beanZcproj.getProjId() + " and a.picPublish='1' order by a.numOrder";
			projPic = (String) this.getCommonBo().getHQLUnique(hql);
		}catch(Exception e){ }
		// 系统当前时间
		Date dateNow = this.getCommonBo().getSysDate();
		// 导入进度时间戳
		String random = multiRequest.getParameter("random");
		if(random==null){
			random = "";
		}
		// 获取导入文件
		FileItem item = multiRequest.getFile("file");
		if(item==null || item.getSize()==0){
			request.setAttribute("alert", "请选择需要导入的Excel文件！");
			return "import";
		}
		// 读取导入文件
		InputStream fis = null;
		try{
			fis = item.getInputStream();
			POIFSFileSystem fs = new POIFSFileSystem(fis);
			HSSFWorkbook workbook = new HSSFWorkbook(fs);
			HSSFSheet sheet = workbook.getSheetAt(0);
			// 判断是否有数据
			int rowCount = sheet.getLastRowNum();
			if(rowCount<1){
				request.setAttribute("alert", "导入模板错误，或导入文件中没有相关导入数据！");
				return "import";
			}
			// 头
			HSSFRow headRow = sheet.getRow(0);
			int colCount = headRow.getLastCellNum();
			List headList = this.getList();
			for(int i=0; i<=colCount; i++){
				HSSFCell headCell = headRow.getCell((short) i);
				String headCellValue = "";
				if(headCell!=null){
					headCellValue = headCell.toString().trim();
				}
				if(headCellValue.contains("单号")){
					headList.add("捐赠单号");
				}else if(headCellValue.contains("选项")){
					headList.add("捐赠选项");
				}else if(headCellValue.contains("份数")){
					headList.add("捐赠份数");
				}else if(headCellValue.endsWith("币种")){
					headList.add("捐赠币种");
				}else if(headCellValue.endsWith("币种金额")){
					headList.add("捐赠币种金额");
				}else if(headCellValue.endsWith("计量单位")){
					headList.add("捐赠币种金额计量单位");
				}else if(headCellValue.contains("实算金额")){
					headList.add("捐赠实算金额");
				}else if(headCellValue.contains("方式")){
					headList.add("捐赠方式");
				}else if(headCellValue.contains("状态")){
					headList.add("捐赠状态");
				}else if(headCellValue.contains("提交时间")){
					headList.add("提交时间");
				}else if(headCellValue.contains("付款时间")){
					headList.add("付款时间");
				}else if(headCellValue.contains("证书")){
					headList.add("需要证书");
				}else if(headCellValue.contains("发票")){
					headList.add("需要发票");
				}else if(headCellValue.contains("匿名")){
					headList.add("匿名捐赠");
				}else if(headCellValue.endsWith("人类型")){
					headList.add("捐赠人类型");
				}else if(headCellValue.endsWith("人数")){
					headList.add("捐赠人数");
				}else if(headCellValue.endsWith("捐赠人")){
					headList.add("捐赠人");
				}else if(headCellValue.contains("性别")){
					headList.add("性别");
				}else if(headCellValue.contains("手机")){
					headList.add("手机");
				}else if(headCellValue.contains("电话")){
					headList.add("电话");
				}else if(headCellValue.contains("邮箱")){
					headList.add("邮箱");
				}else if(headCellValue.contains("邮编")){
					headList.add("邮编");
				}else if(headCellValue.contains("省")){
					headList.add("省");
				}else if(headCellValue.contains("市")){
					headList.add("市");
				}else if(headCellValue.contains("区/县")){
					headList.add("区/县");
				}else if(headCellValue.contains("详细地址")){
					headList.add("详细地址");
				}else if(headCellValue.contains("是否校友")){
					headList.add("是否校友");
				}else if(headCellValue.contains("入学年")){
					headList.add("入学年");
				}else if(headCellValue.contains("离校年")){
					headList.add("离校年");
				}else if(headCellValue.contains("院系")){
					headList.add("院系");
				}else if(headCellValue.contains("专业")){
					headList.add("专业");
				}else if(headCellValue.contains("班级")){
					headList.add("班级");
				}else if(headCellValue.contains("学号")){
					headList.add("学号");
				}else if(headCellValue.contains("学历")){
					headList.add("学历");
				}else if(headCellValue.contains("公司")){
					headList.add("公司");
				}else if(headCellValue.contains("部门")){
					headList.add("部门");
				}else if(headCellValue.contains("职位")){
					headList.add("职位");
				}else if(headCellValue.contains("其他")){
					headList.add("其他信息");
				}else if(headCellValue.contains("标记")){
					headList.add("标记信息");
				}else if(headCellValue.contains("回执")){
					headList.add("回执信息");
				}else if(headCellValue.contains("备注")){
					headList.add("备注");
				}else{
					headList.add("");
				}
			}
			if(!headList.contains("捐赠选项") || !headList.contains("捐赠份数") || !headList.contains("捐赠实算金额") || !headList.contains("捐赠人")){
				request.setAttribute("alert", "导入模板格式错误，请重新下载导入模板！");
				return "import";
			}
			// 设置导入进度参数
			session.setAttribute(IMPORTCOUNT + random, rowCount);
			// 遍历Excel导入数据
			int orderNumIndex = headList.indexOf("捐赠单号");
			int optionNameIndex = headList.indexOf("捐赠选项");
			int optionCountIndex = headList.indexOf("捐赠份数");
			int amountTypeIndex = headList.indexOf("捐赠币种");
			int amountViewIndex = headList.indexOf("捐赠币种金额");
			int amountUnitIndex = headList.indexOf("捐赠币种金额计量单位");
			int amountIndex = headList.indexOf("捐赠实算金额");
			int orderTypeIndex = headList.indexOf("捐赠方式");
			int orderStatusIndex = headList.indexOf("捐赠状态");
			int orderTimeIndex = headList.indexOf("提交时间");
			int orderOkTimeIndex = headList.indexOf("付款时间");
			int needZhengshuIndex = headList.indexOf("需要证书");
			int needFapiaoIndex = headList.indexOf("需要发票");
			int niMingIndex = headList.indexOf("匿名捐赠");
			int personTypeIndex = headList.indexOf("捐赠人类型");
			int personCountIndex = headList.indexOf("捐赠人数");
			int personIndex = headList.indexOf("捐赠人");
			int personSexIndex = headList.indexOf("性别");
			int personPhoneIndex = headList.indexOf("手机");
			int personTelIndex = headList.indexOf("电话");
			int personEmailIndex = headList.indexOf("邮箱");
			int zipIndex = headList.indexOf("邮编");
			int shengIndex = headList.indexOf("省");
			int shiIndex = headList.indexOf("市");
			int quIndex = headList.indexOf("区/县");
			int addressIndex = headList.indexOf("详细地址");
			int alumniFlagIndex = headList.indexOf("是否校友");
			int academyBegIndex = headList.indexOf("入学年");
			int academyEndIndex = headList.indexOf("离校年");
			int academyIndex = headList.indexOf("院系");
			int majorIndex = headList.indexOf("专业");
			int clazzIndex = headList.indexOf("班级");
			int studynoIndex = headList.indexOf("学号");
			int academyDegreeIndex = headList.indexOf("学历");
			int companyIndex = headList.indexOf("公司");
			int departmentIndex = headList.indexOf("部门");
			int dutyIndex = headList.indexOf("职位");
			int orderMemoIndex = headList.indexOf("其他信息");
			int markIndex = headList.indexOf("标记信息");
			int receiptIndex = headList.indexOf("回执信息");
			int memoIndex = headList.indexOf("备注");
			// 导入反馈信息
			int successcount = 0;
			int errorcount = 0;
			List errorList = this.getList();
			// 循环导入
			for(int i=1; i<=rowCount; i++){
				HSSFRow row = sheet.getRow(i);
				// 设置导入进度参数
				session.setAttribute(IMPORTINDEX + random, i);
				try{
					// 项目捐赠
					TbZcprojOrder bean = null;
					// 捐赠单号
					if(orderNumIndex!=-1){
						HSSFCell orderNumCell = row.getCell((short) orderNumIndex);
						if(orderNumCell!=null){
							String orderNum = formatStr(orderNumCell.toString().trim());
							if(orderNum!=null && !"".equals(orderNum)){
								try{
									String hql = "from TbZcprojOrder a where a.projId=? and a.orderNum=?";
									List pars = this.getList();
									pars.add(beanZcproj.getProjId());
									pars.add(orderNum);
									bean = (TbZcprojOrder) this.getCommonBo().getHQLUnique(hql, pars);
								}catch(Exception e){}
								if(bean==null){
									errorcount++;
									errorList.add(new String[] {String.valueOf(i), "导入失败，原因：该项目中不存在单号为【"+orderNum+"】的捐赠记录，未做新增、更新处理！"});
									continue;
								}
							}
						}
					}
					if(bean==null){
						bean = new TbZcprojOrder();
						// 所属捐赠项目
						bean.setProjId(beanZcproj.getProjId());
						bean.setProjName(beanZcproj.getProjName());
						bean.setProjPic(projPic);
						// 捐赠币种
						bean.setOrderAmountType("人民币");
						// 捐赠币种金额计量单位
						bean.setOrderAmountUnit("元");
						// 捐赠状态
						bean.setOrderStatus("0");
						// 证书
						bean.setNeedZhengshu("0");
						// 发票
						bean.setNeedFapiao("0");
						// 捐赠人 类型
						bean.setPersonType("1");
						// 捐赠人 数量
						bean.setPersonCount(new Long(1));
						// 捐赠人 姓名
						bean.setPersonName("匿名");
						// 匿名
						bean.setNiMing("0");
						// 是否校友
						bean.setAlumniFlag("0");
					}
					// 捐赠选项
					TbZcprojOption beanZcprojOption = null;
					String optionName = null;
					HSSFCell optionNameCell = row.getCell((short) optionNameIndex);
					if(optionNameCell!=null){
						optionName = formatStr(optionNameCell.toString().trim());
						if(optionName!=null && !"".equals(optionName)){
							try{
								String hql = "from TbZcprojOption a where a.projId=? and a.optionName=?";
								List pars = this.getList();
								pars.add(beanZcproj.getProjId());
								pars.add(optionName);
								beanZcprojOption = (TbZcprojOption) this.getCommonBo().getHQLUnique(hql, pars);
							}catch(Exception e){ }
						}
					}
					// 捐赠份数
					Long optionCount = null;
					HSSFCell optionCountCell = row.getCell((short) optionCountIndex);
					if(optionCountCell!=null){
						try{
							String optionCountStr = formatStr(optionCountCell.toString().trim());
							if(optionCountStr!=null && !"".equals(optionCountStr)){
								optionCount = Long.parseLong(optionCountStr.split("\\.")[0]);
							}
						}catch(Exception e){ }
					}
					if(beanZcprojOption!=null){
						bean.setOptionId(beanZcprojOption.getOptionId());
						bean.setOptionName(beanZcprojOption.getOptionName());
						if(optionCount!=null && optionCount.intValue()>0){
							bean.setOptionCount(optionCount);
						}else{
							bean.setOptionCount(new Long(1));
						}
					}else if(optionName!=null && !"".equals(optionName)){
						bean.setOptionId(null);
						bean.setOptionName(optionName);
						if(optionCount!=null && optionCount.intValue()>0){
							bean.setOptionCount(optionCount);
						}else{
							bean.setOptionCount(null);
						}
					}else if(beanZcproj.getOptionOtherName()!=null && !"".equals(beanZcproj.getOptionOtherName())){
						bean.setOptionId(null);
						bean.setOptionName(beanZcproj.getOptionOtherName());
						bean.setOptionCount(null);
					}else{
						bean.setOptionId(null);
						bean.setOptionName("任意捐");
						bean.setOptionCount(null);
					}
					// 捐赠币种
					if(amountTypeIndex!=-1){
						HSSFCell amountTypeCell = row.getCell((short) amountTypeIndex);
						if(amountTypeCell!=null){
							String amountType = formatStr(amountTypeCell.toString().trim());
							if(amountType==null || "".equals(amountType)){
								amountType = bean.getOrderAmountType();
							}
							bean.setOrderAmountType(amountType);
						}
					}
			        // 捐赠币种金额
					if(amountViewIndex!=-1){
						BigDecimal orderAmoutViewD = null;
						HSSFCell amountViewCell = row.getCell((short) amountViewIndex);
						if(amountViewCell!=null){
							String amountView = formatStr(amountViewCell.toString().trim());
							if(amountView!=null && !"".equals(amountView)){
								try{
									orderAmoutViewD = new BigDecimal(amountView).setScale(2, 4);
								}catch(Exception e){ }
							}
							bean.setOrderAmountView(orderAmoutViewD);
						}
					}
					// 捐赠币种计量单位
					if(amountUnitIndex!=-1){
						HSSFCell amountUnitCell = row.getCell((short) amountUnitIndex);
						if(amountUnitCell!=null){
							String amountUnit = formatStr(amountUnitCell.toString().trim());
							if(amountUnit==null || "".equals(amountUnit)){
								amountUnit = bean.getOrderAmountUnit();
							}
							bean.setOrderAmountUnit(amountUnit);
						}
					}
					// 总金额
					BigDecimal orderAmoutD = null;
					HSSFCell amountCell = row.getCell((short) amountIndex);
					if(amountCell!=null){
						String amount = formatStr(amountCell.toString().trim());
						if(amount!=null && !"".equals(amount)){
							try{
								orderAmoutD = new BigDecimal(amount).setScale(2, 4);
							}catch(Exception e){ }
						}
					}
					if(orderAmoutD==null || orderAmoutD.doubleValue()<=0){
						errorcount++;
						errorList.add(new String[] {String.valueOf(i), "导入失败，原因：捐赠金额必须大于0（最多两位小数的正数）！"});
						continue;
					}
					bean.setOrderAmount(orderAmoutD);
					// 捐赠单总金额（默认处理）
					if("人民币".equals(bean.getOrderAmountType()) && bean.getOrderAmountView()==null){
				    	bean.setOrderAmountView(bean.getOrderAmount());
					}
					// 捐赠方式
					if(orderTypeIndex!=-1){
						HSSFCell orderTypeCell = row.getCell((short) orderTypeIndex);
						if(orderTypeCell!=null){
							String orderType = formatStr(orderTypeCell.toString().trim());
							bean.setOrderType(orderType);
						}
					}
					// 捐赠状态
					if(orderStatusIndex!=-1){
						HSSFCell orderStatusCell = row.getCell((short) orderStatusIndex);
						if(orderStatusCell!=null){
							String orderStatus = formatStr(orderStatusCell.toString().trim());
							if(orderStatus!=null && ("已付款".equals(orderStatus) || "1".equals(orderStatus))){
								bean.setOrderStatus("1");
							}else{
								bean.setOrderStatus("0");
							}
						}
					}
					// 提交时间
					if(orderTimeIndex!=-1){
						HSSFCell orderTimeCell = row.getCell((short) orderTimeIndex);
						if(orderTimeCell!=null){
							Date orderTime = getExcelDate(orderTimeCell);
							bean.setOrderTime(orderTime);
						}
					}
					// 付款时间
					if(orderOkTimeIndex!=-1){
						HSSFCell orderOkTimeCell = row.getCell((short) orderOkTimeIndex);
						if(orderOkTimeCell!=null){
							Date orderOkTime = getExcelDate(orderOkTimeCell);
							bean.setOrderOkTime(orderOkTime);
						}
					}
					// 证书
					if(needZhengshuIndex!=-1){
						HSSFCell needZhengshuCell = row.getCell((short) needZhengshuIndex);
						if(needZhengshuCell!=null){
							String needZhengshu = formatStr(needZhengshuCell.toString().trim());
							if(needZhengshu!=null && ("需要".equals(needZhengshu) || "是".equals(needZhengshu) || "1".equals(needZhengshu))) {
								bean.setNeedZhengshu("1");
							}else{
								bean.setNeedZhengshu("0");
							}
						}
					}
					// 发票
					if(needFapiaoIndex!=-1){
						HSSFCell needFapiaoCell = row.getCell((short) needFapiaoIndex);
						if(needFapiaoCell!=null){
							String needFapiao = formatStr(needFapiaoCell.toString().trim());
							if(needFapiao!=null && ("需要".equals(needFapiao) || "是".equals(needFapiao) || "1".equals(needFapiao))){
								bean.setNeedFapiao("1");
							}else{
								bean.setNeedFapiao("0");
							}
						}
					}
					// 捐赠人
					// 捐赠人类型
					if(personTypeIndex!=-1){
						HSSFCell personTypeCell = row.getCell((short) personTypeIndex);
						if(personTypeCell!=null){
							String personType = formatStr(personTypeCell.toString().trim());
							if(personType!=null && "个人".equals(personType)){
								personType = "1";
							}else if(personType!=null && "集体".equals(personType)){
								personType = "2";
							}
							if(personType==null || (!"1".equals(personType) && !"2".equals(personType))){
								personType = bean.getPersonType();
							}
							bean.setPersonType(personType);
						}
					}
					// 捐赠人数
					if(personCountIndex!=-1){
						HSSFCell personCountCell = row.getCell((short) personCountIndex);
						if(personCountCell!=null){
							Long personCountL = null;
							String personCount = formatStr(personCountCell.toString().trim());
							if(personCount!=null && !"".equals(personCount)){
								try{
									personCountL = new Long(personCount);
								}catch(Exception e){}
							}
							if(personCountL==null || personCountL<1){
								personCountL = new Long(1);
							}
							bean.setPersonCount(personCountL);
						}
					}
					// 姓名
					if(personIndex!=-1){
						HSSFCell personCell = row.getCell((short) personIndex);
						if(personCell!=null){
							String person = formatStr(personCell.toString().trim());
							if(person==null || "".equals(person)){
								person = "匿名";
							}
							bean.setPersonName(person);
						}
					}
					// 性别
					if(personSexIndex!=-1){
						HSSFCell personSexCell = row.getCell((short) personSexIndex);
						if(personSexCell!=null){
							String personSex = formatStr(personSexCell.toString().trim());
							if(personSex!=null){
								if("男".equals(personSex) || "男性".equals(personSex) || "1".equals(personSex)){
									bean.setPersonSex("1");
								}else if("女".equals(personSex) || "女性".equals(personSex) || "2".equals(personSex)){
									bean.setPersonSex("2");
								}
							}
						}
					}
					// 匿名
					if(niMingIndex!=-1){
						HSSFCell niMingCell = row.getCell((short) niMingIndex);
						if(niMingCell!=null){
							String niMing = formatStr(niMingCell.toString().trim());
							if(niMing!=null && ("是".equals(niMing) || "匿名".equals(niMing) || "匿名捐赠".equals(niMing) || "1".equals(niMing))){
								bean.setNiMing("1");
							}else{
								bean.setNiMing("0");
							}
						}
					}
					// 手机
					if(personPhoneIndex!=-1){
						HSSFCell personPhoneCell = row.getCell((short) personPhoneIndex);
						if(personPhoneCell!=null){
							String personPhone = formatStr(personPhoneCell.toString().trim());
							bean.setPersonPhone(personPhone);
						}
					}
					// 电话
					if(personTelIndex!=-1){
						HSSFCell personTelCell = row.getCell((short) personTelIndex);
						if(personTelCell!=null){
							String personTel = formatStr(personTelCell.toString().trim());
							bean.setPersonTel(personTel);
						}
					}
					// 邮箱
					if(personEmailIndex!=-1){
						HSSFCell personEmailCell = row.getCell((short) personEmailIndex);
						if(personEmailCell!=null){
							String personEmail = formatStr(personEmailCell.toString().trim());
							bean.setPersonEmail(personEmail);
						}
					}
					// 邮编
					if(zipIndex!=-1){
						HSSFCell zipCell = row.getCell((short) zipIndex);
						if(zipCell!=null){
							String zip = formatStr(zipCell.toString().trim());
							bean.setAddressZip(zip);
						}
					}
					// 省
					if(shengIndex!=-1){
						HSSFCell shengCell = row.getCell((short) shengIndex);
						if(shengCell!=null){
							String sheng = formatStr(shengCell.toString().trim());
							bean.setAddressSheng(sheng);
						}
					}
					// 市
					if(shiIndex!=-1){
						HSSFCell shiCell = row.getCell((short) shiIndex);
						if(shiCell!=null){
							String shi = formatStr(shiCell.toString().trim());
							bean.setAddressShi(shi);
						}
					}
					// 区/县
					if(quIndex!=-1){
						HSSFCell quCell = row.getCell((short) quIndex);
						if (quCell != null) {
							String qu = formatStr(quCell.toString().trim());
							bean.setAddressQu(qu);
						}
					}
					// 详细地址
					if(addressIndex!=-1){
						HSSFCell addressCell = row.getCell((short) addressIndex);
						if (addressCell != null) {
							String address = formatStr(addressCell.toString().trim());
							bean.setAddressDetail(address);
						}
					}
					// 校友资料
					// 是否校友
					if(alumniFlagIndex!=-1){
						HSSFCell alumniFlagCell = row.getCell((short) alumniFlagIndex);
						if (alumniFlagCell != null) {
							String alumniFlag = formatStr(alumniFlagCell.toString().trim());
							if(alumniFlag!=null && ("校友".equals(alumniFlag) || "是".equals(alumniFlag) || "1".equals(alumniFlag))){
								bean.setAlumniFlag("1");
							}else{
								bean.setAlumniFlag("0");
							}
						}
					}
					// 入学年
					if(academyBegIndex!=-1){
						HSSFCell academyBegCell = row.getCell((short) academyBegIndex);
						if(academyBegCell!=null){
							String academyBeg = formatStr(academyBegCell.toString().trim());
							bean.setStudyYearin(academyBeg);
						}
					}
					// 离校年
					if(academyEndIndex!=-1){
						HSSFCell academyEndCell = row.getCell((short) academyEndIndex);
						if(academyEndCell!=null){
							String academyEnd = formatStr(academyEndCell.toString().trim());
							bean.setStudyYearover(academyEnd);
						}
					}
					// 院系
					if(academyIndex!=-1){
						HSSFCell academyCell = row.getCell((short) academyIndex);
						if(academyCell!=null){
							String academy = formatStr(academyCell.toString().trim());
							bean.setStudyAcademy(academy);
						}
					}
					// 专业
					if(majorIndex!=-1){
						HSSFCell majorCell = row.getCell((short) majorIndex);
						if(majorCell!=null){
							String major = formatStr(majorCell.toString().trim());
							bean.setStudyMajor(major);
						}
					}
					// 班级
					if(clazzIndex!=-1){
						HSSFCell clazzCell = row.getCell((short) clazzIndex);
						if(clazzCell!=null){
							String clazz = formatStr(clazzCell.toString().trim());
							bean.setStudyClass(clazz);
						}
					}
					// 学号
					if(studynoIndex!=-1){
						HSSFCell studynoCell = row.getCell((short) studynoIndex);
						if(studynoCell!=null){
							String studyno = formatStr(studynoCell.toString().trim());
							bean.setStudyNum(studyno);
						}
					}
					// 学历
					if(academyDegreeIndex!=-1){
						HSSFCell academyDegreeCell = row.getCell((short) academyDegreeIndex);
						if(academyDegreeCell!=null){
							String academyDegree = formatStr(academyDegreeCell.toString().trim());
							bean.setStudyDegree(academyDegree);
						}
					}
					// 工作资料
					// 公司
					if(companyIndex!=-1){
						HSSFCell companyCell = row.getCell((short) companyIndex);
						if(companyCell!=null){
							String company = formatStr(companyCell.toString().trim());
							bean.setWorkCompany(company);
						}
					}
					// 部门
					if(departmentIndex!=-1){
						HSSFCell departmentCell = row.getCell((short) departmentIndex);
						if(departmentCell!=null){
							String department = formatStr(departmentCell.toString().trim());
							bean.setWorkDepart(department);
						}
					}
					// 职位
					if(dutyIndex!=-1){
						HSSFCell dutyCell = row.getCell((short) dutyIndex);
						if(dutyCell!=null){
							String duty = formatStr(dutyCell.toString().trim());
							bean.setWorkDuty(duty);
						}
					}
					// 其他信息
					if(orderMemoIndex!=-1){
						HSSFCell orderMemoCell = row.getCell((short) orderMemoIndex);
						if(orderMemoCell!=null){
							String orderMemo = formatStr(orderMemoCell.toString().trim());
							bean.setOrderMemo(orderMemo);
						}
					}
					// 标记信息
					if(markIndex!=-1){
						HSSFCell markCell = row.getCell((short) markIndex);
						if(markCell!=null){
							String mark = formatStr(markCell.toString().trim());
							bean.setMark(mark);
						}
					}
					// 回执信息
					if(receiptIndex!=-1){
						HSSFCell receiptCell = row.getCell((short) receiptIndex);
						if(receiptCell!=null){
							String receipt = formatStr(receiptCell.toString().trim());
							bean.setReceipt(receipt);
						}
					}
					// 备注
					if(memoIndex!=-1){
						HSSFCell memoCell = row.getCell((short) memoIndex);
						if(memoCell!=null){
							String memo = formatStr(memoCell.toString().trim());
							bean.setMemo(memo);
						}
					}
					// 保存
					bean = (TbZcprojOrder) this.getCommonBo().saveOrUpdate(bean);
					if(bean.getOrderNum()==null || "".equals(bean.getOrderNum())){
						// 单号
						String orderNum = bean.getOrderId().toString();
						int orderLen = orderNum.length();
						for(int j=0; j<17-orderLen; j++){
							orderNum = "0" + orderNum;
						}
						orderNum = DateUtil.format(dateNow,"yyyyMMdd") + "001" + orderNum;
						bean.setOrderNum(orderNum);
						bean = (TbZcprojOrder) this.getCommonBo().update(bean);
						request.setAttribute("bean", bean);
					}
					successcount++;
				}catch(Exception e){
					errorcount++;
					errorList.add(new String[] { String.valueOf(i), "导入失败，原因：该条捐赠信息导入时，发生异常，异常描述："+e.getMessage() });
				}
			}
			request.setAttribute("successcount", successcount);
			request.setAttribute("errorcount", errorcount);
			request.setAttribute("errorList", errorList);
			return "import";
		}catch(Exception e){
			request.setAttribute("alert", "警告，导入文件时出现异常，异常描述："+e.getMessage());
			return "import";
		}finally{
			if(fis!=null){
				fis.close();
			}
			// 移除导入进度参数
			session.removeAttribute(IMPORTINDEX + random);
			session.removeAttribute(IMPORTCOUNT + random);
		}
	}

	// 批量导出
	public String doExport() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 导出结果
		String ajax = "{\"result\":\"error\"}";
		// 1、下载导入模板，其他为导出
		String type = request.getParameter("type");
		// 需要导出的信息系统集合
		List beantList = null;
		// 获取需要导出的捐赠信息集合
		if(type==null || !"1".equals(type)){
			// 项目
			TbZcproj beanZcproj = null;
			// 项目ID
			String projId = request.getParameter("projId");
			if(projId!=null && !"".equals(projId)){
				try{
					beanZcproj = (TbZcproj)this.getCommonBo().get(TbZcproj.class, new Long(projId));
				}catch (Exception e){ }
			}
			if(beanZcproj!=null){
				// 构造查询
				String hql = "from TbZcprojOrder a where a.projId=?";
				List pars = this.getList();
				pars.add(beanZcproj.getProjId());
				// 捐赠选项
				String optionName = request.getParameter("optionName");
				if(optionName!=null && !"".equals(optionName)){
					hql += " and a.optionName like ?";
					pars.add("%"+optionName+"%");
				}
				// 捐赠单号
				String orderNum = request.getParameter("orderNum");
				if(orderNum != null && !"".equals(orderNum)){
					hql += " and a.orderNum like ?";
					pars.add("%" + orderNum + "%");
				}
				// 捐赠方式
				String orderType = request.getParameter("orderType");
				if(orderType != null && !"".equals(orderType)){
					hql += " and a.orderType like ?";
					pars.add("%" + orderType + "%");
				}
				// 证书
				String needZhengshu = request.getParameter("needZhengshu");
				if(needZhengshu != null && "1".equals(needZhengshu)){
					hql += " and a.needZhengshu = ?";
					pars.add("1");
				}else if(needZhengshu != null && !"".equals(needZhengshu)){
					hql += " and (a.needZhengshu is null or a.needZhengshu <> ?)";
					pars.add("1");
				}
				// 发票
				String needFapiao = request.getParameter("needFapiao");
				if(needFapiao != null && "1".equals(needFapiao)){
					hql += " and a.needFapiao = ?";
					pars.add("1");
				}else if(needFapiao != null && !"".equals(needFapiao)){
					hql += " and (a.needFapiao is null or a.needFapiao <> ?)";
					pars.add("1");
				}
				// 匿名捐赠
				String niMing = request.getParameter("niMing");
				if(niMing != null && "1".equals(niMing)){
					hql += " and a.niMing = ?";
					pars.add("1");
				}else if(niMing != null && !"".equals(niMing)){
					hql += " and (a.niMing is null or a.niMing <> ?)";
					pars.add("1");
				}
				// 捐赠人
				String personName = request.getParameter("personName");
				if(personName != null && !"".equals(personName)){
					hql += " and a.personName like ?";
					pars.add("%" + personName + "%");
				}
				// 捐赠人信息
				String personInfo = request.getParameter("personInfo");
				if(personInfo != null && !"".equals(personInfo)){
					hql += " and (a.personPhone like ? or a.personTel like ? or a.personEmail like ? or a.addressSheng like ? or a.addressShi like ? or a.addressQu like ? or a.addressDetail like ? or a.addressZip like ?)";
					pars.add("%" + personInfo + "%");
					pars.add("%" + personInfo + "%");
					pars.add("%" + personInfo + "%");
					pars.add("%" + personInfo + "%");
					pars.add("%" + personInfo + "%");
					pars.add("%" + personInfo + "%");
					pars.add("%" + personInfo + "%");
					pars.add("%" + personInfo + "%");
				}
				// 校友
				String alumni = request.getParameter("alumni");
				if(alumni != null && "1".equals(alumni)){
					hql += " and a.alumniFlag = ?";
					pars.add("1");
				}else if(alumni != null && !"".equals(alumni)){
					hql += " and (a.alumniFlag is null or a.alumniFlag <> ?)";
					pars.add("1");
				}
				// 校友信息
				String alumniInfo = request.getParameter("alumniInfo");
				if(alumniInfo != null && !"".equals(alumniInfo)){
					hql += " and (a.studyYearin like ? or a.studyYearover like ? or a.studyAcademy like ? or a.studyMajor like ? or a.studyClass like ? or a.studyNum like ? or a.studyDegree like ?)";
					pars.add("%" + alumniInfo + "%");
					pars.add("%" + alumniInfo + "%");
					pars.add("%" + alumniInfo + "%");
					pars.add("%" + alumniInfo + "%");
					pars.add("%" + alumniInfo + "%");
					pars.add("%" + alumniInfo + "%");
					pars.add("%" + alumniInfo + "%");
				}
				// 标记
				String markFlag = request.getParameter("markFlag");
				if(markFlag != null && "1".equals(markFlag)){
					hql += " and a.mark is not null";
					String mark = request.getParameter("mark");
					if (mark != null && !"".equals(mark)) {
						hql += " and a.mark like ?";
						pars.add("%" + mark + "%");
					}
				}else if(markFlag != null && !"".equals(markFlag)){
					hql += " and a.mark is null";
				}
				// 捐赠币种
				String amountType = request.getParameter("amountType");
				if(amountType!=null && !"".equals(amountType)){
					hql += " and a.orderAmountType like ?";
					pars.add("%"+amountType+"%");
				}
				// 提交起始时间
				Date orderTimeBegD = null;
				String orderTimeBeg = request.getParameter("orderTimeBeg");
				if(orderTimeBeg!=null && !"".equals(orderTimeBeg)){
					orderTimeBegD = DateUtil.toDate(orderTimeBeg, "yyyy-MM-dd HH:mm");
				}
				if(orderTimeBegD!=null){
					hql += " and a.orderTime >= ?";
					pars.add(orderTimeBegD);
				}
				// 提交截止时间
				Date orderTimeEndD = null;
				String orderTimeEnd = request.getParameter("orderTimeEnd");
				if(orderTimeEnd!=null && !"".equals(orderTimeEnd)){
					orderTimeEndD = DateUtil.toDate(orderTimeEnd, "yyyy-MM-dd HH:mm");
				}
				if(orderTimeEndD!=null){
					hql += " and a.orderTime <= ?";
					pars.add(orderTimeEndD);
				}
				// 付款起始时间
				Date orderOkTimeBegD = null;
				String orderOkTimeBeg = request.getParameter("orderOkTimeBeg");
				if(orderOkTimeBeg!=null && !"".equals(orderOkTimeBeg)){
					orderOkTimeBegD = DateUtil.toDate(orderOkTimeBeg, "yyyy-MM-dd HH:mm");
				}
				if(orderOkTimeBegD!=null){
					hql += " and a.orderOkTime >= ?";
					pars.add(orderOkTimeBegD);
				}
				// 付款截止时间
				Date orderOkTimeEndD = null;
				String orderOkTimeEnd = request.getParameter("orderOkTimeEnd");
				if(orderOkTimeEnd!=null && !"".equals(orderOkTimeEnd)){
					orderOkTimeEndD = DateUtil.toDate(orderOkTimeEnd, "yyyy-MM-dd HH:mm");
				}
				if(orderOkTimeEndD!=null){
					hql += " and a.orderOkTime <= ?";
					pars.add(orderOkTimeEndD);
				}
				// 捐赠状态
				String orderStatus = request.getParameter("orderStatus");
				if(orderStatus!=null && "1".equals(orderStatus)){
					hql += " and a.orderStatus = ?";
					pars.add("1");
				}else if(orderStatus!=null && !"".equals(orderStatus)){
					hql += " and (a.orderStatus is null or a.orderStatus <> ?)";
					pars.add("1");
				}
				// 捐赠人类型
				String personType = request.getParameter("personType");
				if(personType!=null && "1".equals(personType)){
					hql += " and a.personType = ?";
					pars.add("1");
				}else if(personType!=null && !"".equals(personType)){
					hql += " and (a.personType is null or a.personType <> ?)";
					pars.add("1");
				}
				// 默认查询
				hql += " order by a.orderTime desc";
				// 执行查询
				try{
					beantList = this.getCommonBo().findHQL(hql, pars);
				}catch(Exception e){ }
			}
		}
		// 导出Excel文件
		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet();
			// 设置单元格宽度
			sheet.setColumnWidth((short) 0, (short) 8000);
			sheet.setColumnWidth((short) 1, (short) 8000);
			sheet.setDefaultColumnWidth((short) 24);
			// 设置单元格字体
			HSSFFont font = wb.createFont();
			font.setFontName("宋体");
			font.setFontHeight((short) 200);
			// 创建单元格样式(左上角)
			HSSFCellStyle cellLeftTopStyle = wb.createCellStyle();
			// 指定单元格居中对齐
			cellLeftTopStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// 指定单元格垂直居中对齐
			cellLeftTopStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			// 指定当单元格内容显示不下时自动换行
			cellLeftTopStyle.setWrapText(true);
			cellLeftTopStyle.setFont(font);
			cellLeftTopStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
			cellLeftTopStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
			cellLeftTopStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
			cellLeftTopStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
			// 创建单元格样式(左)
			HSSFCellStyle cellLeftStyle = wb.createCellStyle();
			// 指定单元格居中对齐
			cellLeftStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// 指定单元格垂直居中对齐
			cellLeftStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			// 指定当单元格内容显示不下时自动换行
			cellLeftStyle.setWrapText(true);
			cellLeftStyle.setFont(font);
			cellLeftStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
			cellLeftStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
			cellLeftStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
			// 创建单元格样式(上)
			HSSFCellStyle cellTopStyle = wb.createCellStyle();
			// 指定单元格居中对齐
			cellTopStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// 指定单元格垂直居中对齐
			cellTopStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			// 指定当单元格内容显示不下时自动换行
			cellTopStyle.setWrapText(true);
			cellTopStyle.setFont(font);
			cellTopStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
			cellTopStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
			cellTopStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
			// 创建单元格样式(其他)
			HSSFCellStyle cellStyle = wb.createCellStyle();
			// 指定单元格居中对齐
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// 指定单元格垂直居中对齐
			cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			// 指定当单元格内容显示不下时自动换行
			cellStyle.setWrapText(true);
			cellStyle.setFont(font);
			cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
			cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
			// 第一行，标题行
			List headList = this.getList();
			headList.add("捐赠单号");
			headList.add("项目名称");
			headList.add("捐赠选项");
			headList.add("捐赠份数");
			headList.add("捐赠币种");
			headList.add("捐赠币种金额");
			headList.add("捐赠币种计量单位");
			headList.add("捐赠实算金额");
			headList.add("捐赠方式");
			headList.add("捐赠状态");
			headList.add("提交时间");
			headList.add("付款时间");
			headList.add("需要证书");
			headList.add("需要发票");
			headList.add("匿名捐赠");
			headList.add("捐赠人类型");
			headList.add("捐赠人数");
			headList.add("捐赠人");
			headList.add("性别");
			headList.add("手机");
			headList.add("电话");
			headList.add("邮箱");
			headList.add("邮编");
			headList.add("省");
			headList.add("市");
			headList.add("区/县");
			headList.add("详细地址");
			headList.add("是否校友");
			headList.add("入学年");
			headList.add("离校年");
			headList.add("院系");
			headList.add("专业");
			headList.add("班级");
			headList.add("学号");
			headList.add("学历");
			headList.add("公司");
			headList.add("部门");
			headList.add("职位");
			headList.add("其他信息");
			headList.add("标记信息");
			headList.add("回执信息");
			headList.add("备注");
			HSSFRow row0 = sheet.createRow(0);
			row0.setHeight((short) 600);
			// 第一行
			for(int i=0; i<headList.size(); i++){
				HSSFCell row0cell = row0.createCell((short) i);
				row0cell.setCellStyle(cellTopStyle);
				row0cell.setCellValue(new HSSFRichTextString(headList.get(i).toString()));
			}
			// 数据行
			if(beantList!=null && !beantList.isEmpty()){
				for(int i=1; i<beantList.size()+1; i++){
					HSSFRow row = sheet.createRow(i);
					row.setHeight((short) 600);
					TbZcprojOrder bean = (TbZcprojOrder)beantList.get(i-1);
					// 列号
					int k = 0;
					// 列对象
					HSSFCell cell = null;
					// 捐赠单号
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getOrderNum()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getOrderNum()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 项目名称
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getProjName()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getProjName()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 捐赠选项
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getOptionName()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getOptionName()));
					}else{
						TbZcproj tempZcproj = bean.getTbZcproj();
						if(tempZcproj!=null && tempZcproj.getOptionOtherName()!=null && !"".equals(tempZcproj.getOptionOtherName())){
							cell.setCellValue(new HSSFRichTextString(tempZcproj.getOptionOtherName()));
						}else{
							cell.setCellValue(new HSSFRichTextString("任意捐"));
						}
					}
					// 捐赠份数
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getOptionCount()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getOptionCount().toString()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 捐赠币种
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getOrderAmountType()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getOrderAmountType().toString()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 捐赠币种金额
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getOrderAmountView()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getOrderAmountView().toString()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 捐赠币种计量单位
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getOrderAmountUnit()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getOrderAmountUnit().toString()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 捐赠实算金额
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getOrderAmount()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getOrderAmount().toString()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 捐赠方式
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getOrderType()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getOrderType()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 捐赠状态
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getOrderStatus()!=null && "1".equals(bean.getOrderStatus())){
						cell.setCellValue(new HSSFRichTextString("已付款"));
					}else{
						cell.setCellValue(new HSSFRichTextString("待付款"));
					}
					// 提交时间
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getOrderTime()!=null){
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						cell.setCellValue(new HSSFRichTextString(format.format(bean.getOrderTime())));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 付款时间
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getOrderOkTime()!=null){
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						cell.setCellValue(new HSSFRichTextString(format.format(bean.getOrderOkTime())));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 证书
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getNeedZhengshu()!=null && "1".equals(bean.getNeedZhengshu())){
						cell.setCellValue(new HSSFRichTextString("是"));
					}else{
						cell.setCellValue(new HSSFRichTextString("否"));
					}
					// 发票
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getNeedFapiao()!=null && "1".equals(bean.getNeedFapiao())){
						cell.setCellValue(new HSSFRichTextString("是"));
					}else{
						cell.setCellValue(new HSSFRichTextString("否"));
					}
					// 是否匿名
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getNiMing()!=null && "1".equals(bean.getNiMing())){
						cell.setCellValue(new HSSFRichTextString("是"));
					}else{
						cell.setCellValue(new HSSFRichTextString("否"));
					}
					// 捐赠人类型
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getPersonType()!=null && "2".equals(bean.getPersonType())){
						cell.setCellValue(new HSSFRichTextString("集体"));
					}else{
						cell.setCellValue(new HSSFRichTextString("个人"));
					}
					// 捐赠人数
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getPersonCount()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getPersonCount().toString()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 捐赠人
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getPersonName()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getPersonName()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 性别
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getPersonSex()!=null && "1".equals(bean.getPersonSex())){
						cell.setCellValue(new HSSFRichTextString("男"));
					}else if(bean.getPersonSex()!=null && "2".equals(bean.getPersonSex())){
						cell.setCellValue(new HSSFRichTextString("女"));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 手机
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getPersonPhone()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getPersonPhone()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 电话
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getPersonTel()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getPersonTel()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 邮箱
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getPersonEmail()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getPersonEmail()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 邮编
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getAddressZip()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getAddressZip()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 省
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getAddressSheng()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getAddressSheng()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 市
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getAddressShi()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getAddressShi()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 区/县
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getAddressQu()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getAddressQu()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 详细地址
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getAddressDetail()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getAddressDetail()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 是否校友
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getAlumniFlag()!=null && "1".equals(bean.getAlumniFlag())){
						cell.setCellValue(new HSSFRichTextString("是"));
					}else{
						cell.setCellValue(new HSSFRichTextString("否"));
					}
					// 入学年
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getStudyYearin()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getStudyYearin()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 离校年
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getStudyYearover()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getStudyYearover()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 院系
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getStudyAcademy()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getStudyAcademy()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 专业
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getStudyMajor()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getStudyMajor()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 班级
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getStudyClass()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getStudyClass()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 学号
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getStudyNum()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getStudyNum()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 学历
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getStudyDegree()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getStudyDegree()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 公司
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getWorkCompany()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getWorkCompany()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 部门
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getWorkDepart()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getWorkDepart()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 职位
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getWorkDuty()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getWorkDuty()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 其他信息
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getOrderMemo()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getOrderMemo()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 标记信息
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getMark()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getMark()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 回执信息
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getReceipt()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getReceipt()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// 备注
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getMemo()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getMemo()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
				}
			}
			// 保存文件
			String fielCurName = "捐赠信息导出" + DateUtil.format(new Date(), "yyyyMMddHHmmss") + ".xls";
			String fileNewName = "捐赠信息导出" + ".xls";
			String dirPath = ServletActionContext.getServletContext().getRealPath("download/");
			File dirFile = new File(dirPath);
			if(!dirFile.exists()){
				dirFile.mkdir();
			}
			File file = new File(dirPath, fielCurName);
			FileOutputStream fo = new FileOutputStream(file);
			wb.write(fo);
			fo.close();
			ajax = "{\"result\":\"success\",\"name\":\"" + fileNewName + "\",\"path\":\"download/" + fielCurName + "\"}";
		} catch (Exception ex) { }
		// 返回
		this.sendResponse(this.getResponse(), ajax);
		return null;
	}

}