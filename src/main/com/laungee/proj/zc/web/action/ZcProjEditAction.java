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
	// �ڳ���Ŀ�������༭ҳ��
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ��Ŀ
		TbZcproj bean = null;
		// ��ĿID
		String projId = request.getParameter("projId");
		if(projId!=null && !"".equals(projId)){
			try{
				bean = (TbZcproj)this.getCommonBo().get(TbZcproj.class, new Long(projId));
			}catch(Exception e){}
		}
		if(bean==null){
			bean = new TbZcproj();
			// ��Ŀ����
			Long typeIdL = null;
			String typeId = request.getParameter("typeId");
			if(typeId!=null && !"".equals(typeId)){
				try{
					typeIdL = new Long(typeId);
				}catch(Exception e){}
			}
			bean.setProjType(typeIdL);
			// ϵͳʱ��
			Date dateNow = this.getCommonBo().getSysDate();
			String[] dateStr = DateUtil.format(dateNow).split("-");
			// �Ƿ��ϼ�
			bean.setShelvesFlag("0");
			// �Ƿ�����
			bean.setHotFlag("0");
			// ��ʾ����ʱ��
			bean.setShelvesTime(DateUtil.toDate(dateStr[0]+"-"+dateStr[1]+"-"+dateStr[2]+" 09:00","yyyy-MM-dd HH:mm"));
			// �ڳ￪ʼʱ��
			bean.setBegTime(DateUtil.toDate(dateStr[0]+"-"+dateStr[1]+"-"+dateStr[2]+" 10:00","yyyy-MM-dd HH:mm"));
			// �ڳ��ֹʱ��
			bean.setEndTime(DateUtil.toDate(dateStr[0]+"-"+(Integer.parseInt(dateStr[1])+1)+"-"+dateStr[2]+" 18:00","yyyy-MM-dd HH:mm"));
			// �Ƿ�֧�������ѡ��
			bean.setOptionOther("1");
			// �����ѡ������
			bean.setOptionOtherName("�����");
			// �������ͽ��
			bean.setMinAmount(new BigDecimal(1).setScale(2, 4));
			// Ŀ����ɺ�����ڳ�
			bean.setCompletedJz("1");
			// ��������뾭��ع�
			bean.setClassicStatus("0");
			// ��Ŀ�����ǩ
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
		//��Ŀ���༯��
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
		//����
		String opt = request.getParameter("opt");
		request.setAttribute("opt", opt);
		// ����
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
	
	// �ڳ���Ŀ�������༭
	public String update() throws Exception {
		HttpServletRequest request = this.getRequest();
		MultiRequestUtil multiRequest = new MultiRequestUtil(request);
		// ��Ŀ
		TbZcproj bean = null;
		// ��ĿID
		String projId = multiRequest.getParameter("projId");
		if(projId!=null && !"".equals(projId)){
			try{
				bean = (TbZcproj)this.getCommonBo().get(TbZcproj.class, new Long(projId));
			}catch(Exception e){}
		}
		if(bean==null){
			bean = new TbZcproj();
		}
		// ��Ŀ����
		String projName = multiRequest.getParameter("projName");
		bean.setProjName(projName);
		// ��Ŀ����
		String projCode = multiRequest.getParameter("projCode");
		bean.setProjCode(projCode);
		// ��������
		Long typeIdL = null;
		String typeId = multiRequest.getParameter("typeId");
		if(typeId!=null && !"".equals(typeId)){
			try{
				typeIdL = new Long(typeId);
			}catch(Exception e){}
		}
		bean.setProjType(typeIdL);
		// ��ʾ����ʱ��
		String shelvesTime = multiRequest.getParameter("shelvesTime");
		if(shelvesTime!=null && !"".equals(shelvesTime)){
			bean.setShelvesTime(DateUtil.toDate(shelvesTime,"yyyy-MM-dd HH:mm"));
		}else{
			bean.setShelvesTime(null);
		}
		// Ŀ����
		BigDecimal targetAmoutD = null;
		String targetAmout = multiRequest.getParameter("targetAmout");
		if(targetAmout!=null && !"".equals(targetAmout)){
			try{
				targetAmoutD = new BigDecimal(targetAmout).setScale(2, 4);
			}catch(Exception e){}
		}
		bean.setTargetAmout(targetAmoutD);
		// �Ƿ��ϼ�
		String shelvesFlag = multiRequest.getParameter("shelvesFlag");
		bean.setShelvesFlag(shelvesFlag);
		// �Ƿ�����
		String hotFlag = multiRequest.getParameter("hotFlag");
		bean.setHotFlag(hotFlag);
		// ��������
		String hotOrder = multiRequest.getParameter("hotOrder");
		bean.setHotOrder(hotOrder);
		// �ڳ￪ʼʱ��
		String begTime = multiRequest.getParameter("begTime");
		if(begTime!=null && !"".equals(begTime)){
			bean.setBegTime(DateUtil.toDate(begTime,"yyyy-MM-dd HH:mm"));
		}else{
			bean.setBegTime(null);
		}
		// �ڳ��ֹʱ��
		String endTime = multiRequest.getParameter("endTime");
		if(endTime!=null && !"".equals(endTime)){
			bean.setEndTime(DateUtil.toDate(endTime,"yyyy-MM-dd HH:mm"));
		}else{
			bean.setEndTime(null);
		}
		// Ŀ����ɺ�����ڳ�
		String completedJz = multiRequest.getParameter("completedJz");
		bean.setCompletedJz(completedJz);
		// ��Ŀ�ؼ��ֱ�ǩ
		String searchKey = multiRequest.getParameter("searchKey");
		if(searchKey!=null && !"".equals(searchKey)){
			searchKey = searchKey.replace("��", ",");
		}
		bean.setSearchKey(searchKey);
		// ��������뾭��ع�
		String classicStatus = multiRequest.getParameter("classicStatus");
		bean.setClassicStatus(classicStatus);
		// ��Ŀ���
		String projIntro = multiRequest.getParameter("projIntro");
		bean.setProjIntro(projIntro);
		// �Ƿ�֧�������ѡ��
		String optionOther = multiRequest.getParameter("optionOther");
		bean.setOptionOther(optionOther);
		// �����ѡ������
		String optionOtherName = multiRequest.getParameter("optionOtherName");
		bean.setOptionOtherName(optionOtherName);
		// �����ѡ�������
		BigDecimal minAmountD = null;
		String minAmount = multiRequest.getParameter("minAmount");
		if(minAmount!=null && !"".equals(minAmount)){
			try{
				minAmountD = new BigDecimal(minAmount).setScale(2, 4);
			}catch(Exception e){}
		}
		bean.setMinAmount(minAmountD);
		// �����ѡ������
		String optionOtherMemo = multiRequest.getParameter("optionOtherMemo");
		bean.setOptionOtherMemo(optionOtherMemo);
		// ��Ŀ����
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
		// ��ע˵��
		String memo = multiRequest.getParameter("memo");
		bean.setMemo(memo);
		try{
			// ����
			if(bean.getProjId()==null){
				bean = (TbZcproj) this.getCommonBo().save(bean);
				request.setAttribute("opt", "insert");
			}else{
				bean = (TbZcproj) this.getCommonBo().update(bean);
			}
			// ���¸������
			String relIds = multiRequest.getParameter("relIds");
			FileManager.getInstance().update(bean.getProjId(),relIds);
			// ��ĿͼƬ
			FileItem[] picItems = multiRequest.getFiles("pic");
			String[] picOrders = multiRequest.getParameterValues("picOrder");
			String[] picMemos = multiRequest.getParameterValues("picMemo");
			if(picItems!=null && picOrders!=null && picMemos!=null && picItems.length==picOrders.length && picItems.length==picMemos.length && picItems.length>0){
				for(int i=0;i<picItems.length;i++){
					FileItem item = picItems[i];
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
							String fileName = DateUtil.format(dateNow, "yyyyMMddHHmmss")+"_"+i+new Random().nextInt(1000)+"."+lastName;
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
							TbZcprojPic beanPic = new TbZcprojPic();
							beanPic.setProjId(bean.getProjId());
							beanPic.setPicPath(uploadPath);
							beanPic.setNumOrder(picOrders[i]);
							beanPic.setPicPublish("1");
							beanPic.setPicAlt(picMemos[i]);
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
			}
			// ��Ŀѡ��
			String[] optionNames = multiRequest.getParameterValues("optionName");
			String[] optionAmounts = multiRequest.getParameterValues("optionAmount");
			String[] optionMemos = multiRequest.getParameterValues("optionMemo");
			if(optionNames!=null && optionAmounts!=null && optionMemos!=null && optionNames.length==optionAmounts.length && optionNames.length==optionMemos.length && optionNames.length>0){
				for(int i=0;i<optionNames.length;i++){
					try {
						// ��Ŀѡ��
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
						// ����ѡ��
						this.getCommonBo().save(beanOption);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}
			// ����
			request.setAttribute("projId", bean.getProjId());
			return SUCCESS;
		}catch(Exception e){}
		// ����ʧ��
		request.setAttribute("bean", bean);
		// ��Ŀ���༯��
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
		// ����
		return INPUT;
	}

	// �ڳ���ĿͼƬ�б�ҳ��
	public String picList() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ��ǩҳ
		String num = request.getParameter("num");
		request.setAttribute("num", num);
		// ��Ŀ
		TbZcproj bean = null;
		// ��ĿID
		String projId = request.getParameter("projId");
		if(projId!=null && !"".equals(projId)){
			try{
				bean = (TbZcproj)this.getCommonBo().get(TbZcproj.class, new Long(projId));
			}catch(Exception e){}
		}
		if(bean!=null){
			request.setAttribute("bean", bean);
			// �����ѯ
			String hql = "from TbZcprojPic a where a.projId=?";
			List pars = this.getList();
			pars.add(bean.getProjId());
			// ���ⷢ��
			String publish = request.getParameter("publish");
			if(publish!=null && "1".equals(publish)){
				hql += " and a.picPublish=?";
				pars.add("1");
			}else if(publish!=null && !"".equals(publish)){
				hql += " and (a.picPublish is null or a.picPublish<>?)";
				pars.add("1");
			}
			request.setAttribute("publish", publish);
			// ����
			hql += " order by a.numOrder";
			// ִ�в�ѯ
			List picList = this.getCommonBo().findHQL(hql, pars);
			request.setAttribute("picList", picList);
		}
		// ����
		return "picList";
	}
	
	// �ڳ���ĿͼƬ�������༭ҳ��
	public String picEdit() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ��ĿͼƬ
		TbZcprojPic bean = null;
		// ��ĿͼƬID
		String picId = request.getParameter("picId");
		if(picId!=null && !"".equals(picId)){
			try{
				bean = (TbZcprojPic)this.getCommonBo().get(TbZcprojPic.class, new Long(picId));
			}catch(Exception e){}
		}
		if(bean==null){
			bean = new TbZcprojPic();
			// ��ĿID
			Long projIdL = null;
			String projId = request.getParameter("projId");
			if(projId!=null && !"".equals(projId)){
				try{
					projIdL = new Long(projId);
				}catch(Exception e){}
			}
			if(projIdL!=null){
				bean.setProjId(projIdL);
				// ͼƬ����
				String hqlCount = "select count(*) from TbZcprojPic a where a.projId="+projIdL;
				int count = this.getCommonBo().getHQLNum(hqlCount);
				bean.setNumOrder((count+1)+"");
			}
		}
		request.setAttribute("bean", bean);
		// ����
		return "picEdit";
	}
	
	// �ڳ���ĿͼƬ�������༭
	public String picUpdate() throws Exception {
		HttpServletRequest request = this.getRequest();
		MultiRequestUtil multiRequest = new MultiRequestUtil(request);
		// ��ĿͼƬ
		TbZcprojPic bean = null;
		// ��ĿͼƬID
		String picId = multiRequest.getParameter("picId");
		if(picId!=null && !"".equals(picId)){
			try{
				bean = (TbZcprojPic)this.getCommonBo().get(TbZcprojPic.class, new Long(picId));
			}catch(Exception e){}
		}
		if(bean==null){
			bean = new TbZcprojPic();
			// ��ĿID
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
						String fileName = DateUtil.format(dateNow, "yyyyMMddHHmmss")+"_"+new Random().nextInt(1000)+"."+lastName;
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
			// ͼƬ������ʾ
			String picPublish = multiRequest.getParameter("picPublish");
			if(picPublish!=null && "1".equals(picPublish)){
				bean.setPicPublish("1");
			}else{
				bean.setPicPublish("0");
			}
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
				return "picEdit";
			}
		}
		// ����
		return "winSuccess";
	}

	// �ڳ���ĿͼƬɾ��
	public String picDel() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ��ĿͼƬ
		TbZcprojPic bean = null;
		// ��ĿͼƬID
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
				request.setAttribute("alert", "ɾ��ʧ�ܣ���ĿͼƬ��������1����");
			}else{
				this.getCommonBo().delete(bean);
			}
		}
		// ����
		return picList();
	}

	// �ڳ���Ŀѡ���б�ҳ��
	public String optionList() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ��ǩҳ
		String num = request.getParameter("num");
		request.setAttribute("num", num);
		// ��Ŀ
		TbZcproj bean = null;
		// ��ĿID
		String projId = request.getParameter("projId");
		if(projId!=null && !"".equals(projId)){
			try{
				bean = (TbZcproj)this.getCommonBo().get(TbZcproj.class, new Long(projId));
			}catch(Exception e){}
		}
		if(bean!=null){
			request.setAttribute("bean", bean);
			// �����ѯ
			String hql = "from TbZcprojOption a where a.projId=?";
			List pars = this.getList();
			pars.add(bean.getProjId());
			// ���ⷢ��
			String publish = request.getParameter("publish");
			if(publish!=null && "1".equals(publish)){
				hql += " and a.optionPublish=?";
				pars.add("1");
			}else if(publish!=null && !"".equals(publish)){
				hql += " and (a.optionPublish is null or a.optionPublish<>?)";
				pars.add("1");
			}
			request.setAttribute("publish", publish);
			// ����
			hql += " order by a.optionOrder";
			// ִ�в�ѯ
			List optionList = this.getCommonBo().findHQL(hql, pars);
			request.setAttribute("optionList", optionList);
		}
		// ����
		return "optionList";
	}
	
	// �ڳ���Ŀѡ���������༭ҳ��
	public String optionEdit() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ��Ŀѡ��
		TbZcprojOption bean = null;
		// ��Ŀѡ��ID
		String optionId = request.getParameter("optionId");
		if(optionId!=null && !"".equals(optionId)){
			try{
				bean = (TbZcprojOption)this.getCommonBo().get(TbZcprojOption.class, new Long(optionId));
			}catch(Exception e){}
		}
		if(bean==null){
			bean = new TbZcprojOption();
			// ��ĿID
			Long projIdL = null;
			String projId = request.getParameter("projId");
			if(projId!=null && !"".equals(projId)){
				try{
					projIdL = new Long(projId);
				}catch(Exception e){}
			}
			if(projIdL!=null){
				bean.setProjId(projIdL);
				// ѡ�����
				String hqlCount = "select count(*) from TbZcprojOption a where a.projId="+projIdL;
				int count = this.getCommonBo().getHQLNum(hqlCount);
				bean.setOptionCode((count>9?"0":"00")+(count+1));
			}
			// ѡ����
			bean.setOptionAmount(new BigDecimal(1).setScale(2, 4));
		}
		request.setAttribute("bean", bean);
		// ����
		return "optionEdit";
	}
	
	// �ڳ���Ŀѡ���������༭
	public String optionUpdate() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ��Ŀѡ��
		TbZcprojOption bean = null;
		// ��Ŀѡ��ID
		String optionId = request.getParameter("optionId");
		if(optionId!=null && !"".equals(optionId)){
			try{
				bean = (TbZcprojOption)this.getCommonBo().get(TbZcprojOption.class, new Long(optionId));
			}catch(Exception e){}
		}
		if(bean==null){
			bean = new TbZcprojOption();
			// ��ĿID
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
			// ѡ������
			String optionName = request.getParameter("optionName");
			bean.setOptionName(optionName);
			// ѡ�����
			String optionCode = request.getParameter("optionCode");
			bean.setOptionCode(optionCode);
			// ѡ������
			String optionOrder = request.getParameter("optionOrder");
			bean.setOptionOrder(optionOrder);
			// ѡ����
			BigDecimal optionAmountD = null;
			String optionAmount = request.getParameter("optionAmount");
			if(optionAmount!=null && !"".equals(optionAmount)){
				try{
					optionAmountD = new BigDecimal(optionAmount).setScale(2, 4);
				}catch(Exception e){}
			}
			bean.setOptionAmount(optionAmountD);
			// ѡ������������λ
			String unitName = request.getParameter("unitName");
			bean.setUnitName(unitName);
			// ��Ŀ������ʾ
			String optionPublish = request.getParameter("optionPublish");
			if(optionPublish!=null && "1".equals(optionPublish)){
				bean.setOptionPublish("1");
			}else{
				bean.setOptionPublish("0");
			}
			// ѡ����������
			String limitCount = request.getParameter("limitCount");
			if(limitCount!=null && "1".equals(limitCount)){
				bean.setLimitCount("1");
			}else{
				bean.setLimitCount("0");
			}
			if("1".equals(bean.getLimitCount())){
				// ѡ��ʣ������
				Long optionCountL = null;
				String optionCount = request.getParameter("optionCount");
				if(optionCount!=null && !"".equals(optionCount)){
					try{
						optionCountL = new Long(optionCount);
					}catch(Exception e){}
				}
				bean.setOptionCount(optionCountL);
			}
			// ѡ��˵��
			String optionMemo = request.getParameter("optionMemo");
			bean.setOptionMemo(optionMemo);
			// ��ע
			String memo = request.getParameter("memo");
			bean.setMemo(memo);
			// ����ѡ��
			try{
				this.getCommonBo().saveOrUpdate(bean);
			} catch (Exception e) {
				// ����
				request.setAttribute("alert", "����ʱ�����쳣��");
				return "optionEdit";
			}
		}
		// ����
		return "winSuccess";
	}

	// �ڳ���Ŀѡ��ɾ��
	public String optionDel() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ��Ŀѡ��
		TbZcprojOption bean = null;
		// ��Ŀѡ��ID
		String optionId = request.getParameter("optionId");
		if(optionId!=null && !"".equals(optionId)){
			try{
				bean = (TbZcprojOption)this.getCommonBo().get(TbZcprojOption.class, new Long(optionId));
			}catch(Exception e){}
		}
		if(bean!=null){
			this.getCommonBo().delete(bean);
		}
		// ����
		return optionList();
	}
	
	// �ڳ���Ŀ��չ�б�ҳ��
	public String gressList() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ��ǩҳ
		String num = request.getParameter("num");
		request.setAttribute("num", num);
		// ��Ŀ
		TbZcproj bean = null;
		// ��ĿID
		String projId = request.getParameter("projId");
		if(projId!=null && !"".equals(projId)){
			try{
				bean = (TbZcproj)this.getCommonBo().get(TbZcproj.class, new Long(projId));
			}catch(Exception e){}
		}
		if(bean!=null){
			request.setAttribute("bean", bean);
			// �����ѯ
			String hql = "from TbZcprojProgress a where a.projId=?";
			List pars = this.getList();
			pars.add(bean.getProjId());
			hql += " order by a.gressTime desc";
			// ִ�в�ѯ
			List gressList = this.getCommonBo().findHQLPage(hql, pars);
			request.setAttribute("gressList", gressList);
		}
		// ����
		return "gressList";
	}
	
	// �ڳ���Ŀ��չ�������༭ҳ��
	public String gressEdit() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ��Ŀ��չ
		TbZcprojProgress bean = null;
		// ��Ŀѡ��ID
		String gressId = request.getParameter("gressId");
		if(gressId!=null && !"".equals(gressId)){
			try{
				bean = (TbZcprojProgress)this.getCommonBo().get(TbZcprojProgress.class, new Long(gressId));
			}catch(Exception e){}
		}
		if(bean==null){
			bean = new TbZcprojProgress();
			// ��ĿId
			Long projIdL = null;
			String projId = request.getParameter("projId");
			if(projId!=null && !"".equals(projId)){
				try{
					projIdL = new Long(projId);
				}catch(Exception e){}
			}
			bean.setProjId(projIdL);
			// ��չʱ��
			Date dateNow = this.getCommonBo().getSysDate();
			bean.setGressTime(dateNow);
		}
		request.setAttribute("bean", bean);
		// ����
		return "gressEdit";
	}
	
	// �ڳ���Ŀ��չ�������༭
	public String gressUpdate() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ��Ŀ��չ
		TbZcprojProgress bean = null;
		// ��Ŀѡ��ID
		String gressId = request.getParameter("gressId");
		if(gressId!=null && !"".equals(gressId)){
			try{
				bean = (TbZcprojProgress)this.getCommonBo().get(TbZcprojProgress.class, new Long(gressId));
			}catch(Exception e){}
		}
		if(bean==null){
			bean = new TbZcprojProgress();
			// ��ĿID
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
			// ��չʱ��
			String gressTime = request.getParameter("gressTime");
			if(gressTime!=null && !"".equals(gressTime)){
				bean.setGressTime(DateUtil.toDate(gressTime,"yyyy-MM-dd"));
			}else{
				bean.setGressTime(null);
			}
			// ��չ����
			String gressContent = request.getParameter("gressContent");
			bean.setGressContent(gressContent);
			// ��ע
			String memo = request.getParameter("memo");
			bean.setMemo(memo);
			// ����ѡ��
			try{
				this.getCommonBo().saveOrUpdate(bean);
			} catch (Exception e) {
				// ����
				request.setAttribute("alert", "����ʱ�����쳣��");
				return "gressEdit";
			}
		}
		// ����
		return "winSuccess";
	}

	// �ڳ���Ŀ��չɾ��
	public String gressDel() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ��Ŀ��չ
		TbZcprojProgress bean = null;
		// ��Ŀѡ��ID
		String gressId = request.getParameter("gressId");
		if(gressId!=null && !"".equals(gressId)){
			try{
				bean = (TbZcprojProgress)this.getCommonBo().get(TbZcprojProgress.class, new Long(gressId));
			}catch(Exception e){}
		}
		if(bean!=null){
			this.getCommonBo().delete(bean);
		}
		// ����
		return gressList();
	}

	// �ڳ���Ŀ�����б�ҳ��
	public String orderList() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ��ǩҳ
		String num = request.getParameter("num");
		request.setAttribute("num", num);
		// ��Ŀ
		TbZcproj bean = null;
		// ��ĿID
		String projId = request.getParameter("projId");
		if(projId!=null && !"".equals(projId)){
			try{
				bean = (TbZcproj)this.getCommonBo().get(TbZcproj.class, new Long(projId));
			}catch(Exception e){}
		}
		if(bean!=null){
			request.setAttribute("bean", bean);
			// �����ѯ
			String hql = "from TbZcprojOrder a where a.projId=?";
			List pars = this.getList();
			pars.add(bean.getProjId());
			// ����ѡ��
			String optionName = request.getParameter("optionName");
			if(optionName!=null && !"".equals(optionName)){
				hql += " and a.optionName like ?";
				pars.add("%"+optionName+"%");
			}
			// ��������
			String orderNum = request.getParameter("orderNum");
			if(orderNum!=null && !"".equals(orderNum)){
				hql += " and a.orderNum like ?";
				pars.add("%"+orderNum+"%");
			}
			// ������ʽ
			String orderType = request.getParameter("orderType");
			if(orderType!=null && !"".equals(orderType)){
				hql += " and a.orderType like ?";
				pars.add("%"+orderType+"%");
			}
			// ֤��
			String needZhengshu = request.getParameter("needZhengshu");
			if(needZhengshu!=null && "1".equals(needZhengshu)){
				hql += " and a.needZhengshu = ?";
				pars.add("1");
			}else if(needZhengshu!=null && !"".equals(needZhengshu)){
				hql += " and (a.needZhengshu is null or a.needZhengshu <> ?)";
				pars.add("1");
			}
			// ��Ʊ
			String needFapiao = request.getParameter("needFapiao");
			if(needZhengshu!=null && "1".equals(needZhengshu)){
				hql += " and a.needFapiao = ?";
				pars.add("1");
			}else if(needZhengshu!=null && !"".equals(needZhengshu)){
				hql += " and (a.needFapiao is null or a.needFapiao <> ?)";
				pars.add("1");
			}
			// ��������
			String niMing = request.getParameter("niMing");
			if(niMing!=null && "1".equals(niMing)){
				hql += " and a.niMing = ?";
				pars.add("1");
			}else if(niMing!=null && !"".equals(niMing)){
				hql += " and (a.niMing is null or a.niMing <> ?)";
				pars.add("1");
			}
			// ������
			String personName = request.getParameter("personName");
			if(personName!=null && !"".equals(personName)){
				hql += " and a.personName like ?";
				pars.add("%"+personName+"%");
			}
			// ��������Ϣ
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
			// У��
			String alumni = request.getParameter("alumni");
			if(alumni!=null && "1".equals(alumni)){
				hql += " and a.alumniFlag = ?";
				pars.add("1");
			}else if(alumni!=null && !"".equals(alumni)){
				hql += " and (a.alumniFlag is null or a.alumniFlag <> ?)";
				pars.add("1");
			}
			// У����Ϣ
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
			// ���
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
			// ��������
			String amountType = request.getParameter("amountType");
			if(amountType!=null && !"".equals(amountType)){
				hql += " and a.orderAmountType like ?";
				pars.add("%"+amountType+"%");
			}
			// �ύ��ʼʱ��
			Date orderTimeBegD = null;
			String orderTimeBeg = request.getParameter("orderTimeBeg");
			if(orderTimeBeg!=null && !"".equals(orderTimeBeg)){
				orderTimeBegD = DateUtil.toDate(orderTimeBeg, "yyyy-MM-dd HH:mm");
			}
			if(orderTimeBegD!=null){
				hql += " and a.orderTime >= ?";
				pars.add(orderTimeBegD);
			}
			// �ύ��ֹʱ��
			Date orderTimeEndD = null;
			String orderTimeEnd = request.getParameter("orderTimeEnd");
			if(orderTimeEnd!=null && !"".equals(orderTimeEnd)){
				orderTimeEndD = DateUtil.toDate(orderTimeEnd, "yyyy-MM-dd HH:mm");
			}
			if(orderTimeEndD!=null){
				hql += " and a.orderTime <= ?";
				pars.add(orderTimeEndD);
			}
			// ������ʼʱ��
			Date orderOkTimeBegD = null;
			String orderOkTimeBeg = request.getParameter("orderOkTimeBeg");
			if(orderOkTimeBeg!=null && !"".equals(orderOkTimeBeg)){
				orderOkTimeBegD = DateUtil.toDate(orderOkTimeBeg, "yyyy-MM-dd HH:mm");
			}
			if(orderOkTimeBegD!=null){
				hql += " and a.orderOkTime >= ?";
				pars.add(orderOkTimeBegD);
			}
			// �����ֹʱ��
			Date orderOkTimeEndD = null;
			String orderOkTimeEnd = request.getParameter("orderOkTimeEnd");
			if(orderOkTimeEnd!=null && !"".equals(orderOkTimeEnd)){
				orderOkTimeEndD = DateUtil.toDate(orderOkTimeEnd, "yyyy-MM-dd HH:mm");
			}
			if(orderOkTimeEndD!=null){
				hql += " and a.orderOkTime <= ?";
				pars.add(orderOkTimeEndD);
			}
			// ����״̬
			String orderStatus = request.getParameter("orderStatus");
			if(orderStatus!=null && "1".equals(orderStatus)){
				hql += " and a.orderStatus = ?";
				pars.add("1");
			}else if(orderStatus!=null && !"".equals(orderStatus)){
				hql += " and (a.orderStatus is null or a.orderStatus <> ?)";
				pars.add("1");
			}
			// ����������
			String personType = request.getParameter("personType");
			if(personType!=null && "1".equals(personType)){
				hql += " and a.personType = ?";
				pars.add("1");
			}else if(personType!=null && !"".equals(personType)){
				hql += " and (a.personType is null or a.personType <> ?)";
				pars.add("1");
			}
			// ��ѯ�ϼ�����
			Long orderCount = (Long)this.getCommonBo().getHQLUnique("select count(*) "+hql, pars);
			request.setAttribute("orderCount", orderCount);
			// ��ѯ�ϼ��Ѹ�������
			Long orderOkCount = (Long)this.getCommonBo().getHQLUnique("select count(*) "+hql+" and a.orderStatus = '1'", pars);
			request.setAttribute("orderOkCount", orderOkCount);
			// ��ѯ�ϼ��Ѹ��� ���߾�������
			Long orderOkType0Count = (Long)this.getCommonBo().getHQLUnique("select count(*) "+hql+" and a.orderStatus = '1' and (a.orderType like '%����%' or a.orderType like '%���׺�%')", pars);
			request.setAttribute("orderOkType0Count", orderOkType0Count);
			// ��ѯ�ϼ��Ѹ��� ���˾�������
			Long orderOkType1Count = (Long)this.getCommonBo().getHQLUnique("select count(*) "+hql+" and a.orderStatus = '1' and a.personType = '1'", pars);
			request.setAttribute("orderOkType1Count", orderOkType1Count);
			// ��ѯ�ϼ��Ѹ��� �����������
			Long orderOkType2Count = (Long)this.getCommonBo().getHQLUnique("select count(*) "+hql+" and a.orderStatus = '1' and (a.personType is null or a.personType <> '1')", pars);
			request.setAttribute("orderOkType2Count", orderOkType2Count);
			// ��ѯ�ϼ��Ѹ����ܽ��
			BigDecimal orderOkAmount = (BigDecimal)this.getCommonBo().getHQLUnique("select sum(a.orderAmount) "+hql+" and a.orderStatus = '1'", pars);
			request.setAttribute("orderOkAmount", orderOkAmount);
			// Ĭ�ϲ�ѯ����
			hql += " order by a.orderTime desc,a.orderId desc";
			// ִ�в�ѯ
			List orderList = this.getCommonBo().findHQLPage(hql, pars);
			request.setAttribute("orderList", orderList);
		}
		// ����
		return "orderList";
	}
	
	// �ڳ���Ŀ��������ҳ��
	public String orderView() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ��Ŀ����
		TbZcprojOrder bean = null;
		// ��Ŀ����ID
		String orderId = request.getParameter("orderId");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbZcprojOrder)this.getCommonBo().get(TbZcprojOrder.class, new Long(orderId));
			}catch(Exception e){}
		}
		request.setAttribute("bean", bean);
		// ����
		return "orderView";
	}
	
	// �ڳ���Ŀ�������ҳ��
	public String orderMark() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ��Ŀ����
		TbZcprojOrder bean = null;
		// ��Ŀ����ID
		String orderId = request.getParameter("orderId");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbZcprojOrder)this.getCommonBo().get(TbZcprojOrder.class, new Long(orderId));
			}catch(Exception e){}
		}
		request.setAttribute("bean", bean);
		// ����
		return "orderMark";
	}
	
	// �ڳ���Ŀ��չ���
	public String orderMarkUpdate() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ��Ŀ����
		TbZcprojOrder bean = null;
		// ��Ŀ����ID
		String orderId = request.getParameter("orderId");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbZcprojOrder)this.getCommonBo().get(TbZcprojOrder.class, new Long(orderId));
			}catch(Exception e){}
		}
		if(bean!=null){
			// �����Ϣ
			String mark = request.getParameter("mark");
			bean.setMark(mark);
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

	// �ڳ���Ŀ����ɾ��
	public String orderDel() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ��Ŀ����
		TbZcprojOrder bean = null;
		// ��Ŀ����ID
		String orderId = request.getParameter("orderId");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbZcprojOrder)this.getCommonBo().get(TbZcprojOrder.class, new Long(orderId));
			}catch(Exception e){}
		}
		if(bean!=null){
			this.getCommonBo().delete(bean);
		}
		// ����
		return orderList();
	}
	
	// ����&�༭
	public String toAddOrEdit()  throws Exception {
		HttpServletRequest request = this.getRequest();
		// ϵͳ��ǰʱ��
		Date dateNow = this.getCommonBo().getSysDate();
		// ��ȡ��ǰʱ����ز���
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(dateNow);
		// ��ǰ���
		int nowYear = c.get(Calendar.YEAR);
		request.setAttribute("nowYear", nowYear);
		// ��Ŀ����
		TbZcprojOrder bean = null;
		// ��Ŀ����ID
		String orderId = request.getParameter("orderId");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbZcprojOrder)this.getCommonBo().get(TbZcprojOrder.class, new Long(orderId));
			}catch(Exception e){}
		}
		request.setAttribute("bean", bean);
		// ��Ŀ
		TbZcproj beanZcproj = null;
		// ��ĿID
		String projId = request.getParameter("projId");
		if(projId!=null && !"".equals(projId)){
			try{
				beanZcproj = (TbZcproj)this.getCommonBo().get(TbZcproj.class, new Long(projId));
			}catch(Exception e){}
		}
		request.setAttribute("beanZcproj", beanZcproj);
		// ��ȡ��Ŀѡ��
		if(beanZcproj!=null){
			String hql = "from TbZcprojOption a where a.projId=? order by a.optionOrder";
			List pars = this.getList();
			pars.add(beanZcproj.getProjId());
			List optionList = this.getCommonBo().findHQL(hql, pars);
			request.setAttribute("optionList", optionList);
		}
		// ����
		return "orderEdit";
	}
	public String doAddOrEdit()  throws Exception {
		HttpServletRequest request = this.getRequest();
		// ϵͳ��ǰʱ��
		Date dateNow = this.getCommonBo().getSysDate();
		// ��ȡ��ǰʱ����ز���
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(dateNow);
		// ��ǰ���
		int nowYear = c.get(Calendar.YEAR);
		request.setAttribute("nowYear", nowYear);
		// ��Ŀ
		TbZcproj beanZcproj = null;
		// ��ĿID
		String projId = request.getParameter("projId");
		if(projId!=null && !"".equals(projId)){
			try{
				beanZcproj = (TbZcproj)this.getCommonBo().get(TbZcproj.class, new Long(projId));
			}catch(Exception e){}
		}
		request.setAttribute("beanZcproj", beanZcproj);
		// ��ȡ��Ŀѡ��
		if(beanZcproj!=null){
			String hql = "from TbZcprojOption a where a.projId=? order by a.optionOrder";
			List pars = this.getList();
			pars.add(beanZcproj.getProjId());
			List optionList = this.getCommonBo().findHQL(hql, pars);
			request.setAttribute("optionList", optionList);
		}
		// ��Ŀ����
		TbZcprojOrder bean = null;
		// ��Ŀ����ID
		String orderId = request.getParameter("orderId");
		if(orderId!=null && !"".equals(orderId)){
			try{
				bean = (TbZcprojOrder)this.getCommonBo().get(TbZcprojOrder.class, new Long(orderId));
			}catch(Exception e){}
		}
		if(bean==null){
			bean = new TbZcprojOrder();
		}
		// ������ʽ
		String orderType = request.getParameter("orderType");
		bean.setOrderType(orderType);
		// ����״̬
		String orderStatus = request.getParameter("orderStatus");
		bean.setOrderStatus(orderStatus);
		// �ύʱ��
		String orderTime = request.getParameter("orderTime");
		if(orderTime!=null&&!"".equals(orderTime)){
			bean.setOrderTime(DateUtil.toDate(orderTime, "yyyy-MM-dd HH:mm"));
		}else{
			bean.setOrderTime(null);
		}
		// ����ʱ��
		String orderOkTime = request.getParameter("orderOkTime");
		if(orderOkTime!=null&&!"".equals(orderOkTime)){
			bean.setOrderOkTime(DateUtil.toDate(orderOkTime, "yyyy-MM-dd HH:mm"));
		}else{
			bean.setOrderOkTime(null);
		}
		// ֤��
		String needZhengshu = request.getParameter("needZhengshu");
		bean.setNeedZhengshu(needZhengshu);
		// ��Ʊ
		String needFapiao = request.getParameter("needFapiao");
		bean.setNeedFapiao(needFapiao);
		// ������
		// ����������
		String personType = request.getParameter("personType");
		if(personType==null || !"2".equals(personType)){
			personType = "1";
		}
		bean.setPersonType(personType);
		// ��������
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
		// ����
		String person = request.getParameter("person");
		if(person==null || "".equals(person)){
			person = "����";
		}
		bean.setPersonName(person);
		// �Ա�
		String personSex = request.getParameter("personSex");
		bean.setPersonSex(personSex);
		// ����
		String niMing = request.getParameter("niMing");
		bean.setNiMing(niMing);
		// �ֻ�
		String personPhone = request.getParameter("personPhone");
		bean.setPersonPhone(personPhone);
		// �绰
		String personTel = request.getParameter("personTel");
		bean.setPersonTel(personTel);
		// ����
		String personEmail = request.getParameter("personEmail");
		bean.setPersonEmail(personEmail);
		// �ʱ�
		String zip = request.getParameter("zip");
		bean.setAddressZip(zip);
		// ʡ
		String sheng = request.getParameter("sheng");
		bean.setAddressSheng(sheng);
		// ��
		String shi = request.getParameter("shi");
		bean.setAddressShi(shi);
		// ��/��
		String qu = request.getParameter("qu");
		bean.setAddressQu(qu);
		// ��ϸ��ַ
		String address = request.getParameter("address");
		bean.setAddressDetail(address);
		// У������
		// �Ƿ�У��		
		String alumniFlag = request.getParameter("alumniFlag");
		bean.setAlumniFlag(alumniFlag);
		// ��ѧ��
		String academyBeg = request.getParameter("academyBeg");
		bean.setStudyYearin(academyBeg);
		// ��У��
		String academyEnd = request.getParameter("academyEnd");
		bean.setStudyYearover(academyEnd);
		// Ժϵ
		String academy = request.getParameter("academy");
		bean.setStudyAcademy(academy);
		// רҵ
		String major = request.getParameter("major");
		bean.setStudyMajor(major);
		// �༶
		String clazz = request.getParameter("clazz");
		bean.setStudyClass(clazz);
		// ѧ��
		String studyno = request.getParameter("studyno");
		bean.setStudyNum(studyno);
		// ѧ��
		String academyDegree = request.getParameter("academyDegree");
		bean.setStudyDegree(academyDegree);
		// ��������
		// ��˾
		String company = request.getParameter("company");
		bean.setWorkCompany(company);
		// ����
		String department = request.getParameter("department");
		bean.setWorkDepart(department);
		// ְλ
		String duty = request.getParameter("duty");
		bean.setWorkDuty(duty);
		// ������Ϣ
		String orderMemo = request.getParameter("orderMemo");
		bean.setOrderMemo(orderMemo);
		// �����Ϣ
		String mark = request.getParameter("mark");
		bean.setMark(mark);
		// ��ִ��Ϣ
		String receipt = request.getParameter("receipt");
		bean.setReceipt(receipt);
		// ��ע
		String memo = request.getParameter("memo");
		bean.setMemo(memo);
		// ָ��������Ŀ��Ϣ
		if(bean.getOrderId()==null && beanZcproj==null){
			request.setAttribute("alert", "��Ŀ�����ڣ�");
			request.setAttribute("bean", bean);
			return "orderEdit";
		}
		if(bean.getOrderId()==null){
			bean.setProjId(beanZcproj.getProjId());
			bean.setProjName(beanZcproj.getProjName());
			// �ڳ���ĿͼƬ
	        String projPic = null;
	    	try{
	    		String hql = "select a.picPath from TbZcprojPic a where a.projId="+beanZcproj.getProjId()+" and a.picPublish='1' order by a.numOrder";
	    		projPic = (String)this.getCommonBo().getHQLUnique(hql);
	    	}catch(Exception e){}
	        bean.setProjPic(projPic);
		}
		// �ڳ���Ŀѡ��
		Long optionIdL = null;
        String optionId = request.getParameter("option");
        if(optionId!=null && !"".equals(optionId)){
        	try{
        		optionIdL = new Long(optionId);
        	}catch(Exception e){}
        }
		String optionName = request.getParameter("optionName");
		if(optionIdL==null && (optionName==null || "".equals(optionName))){
			request.setAttribute("alert", "�����þ���ѡ�");
			request.setAttribute("bean", bean);
			return "orderEdit";
		}
    	bean.setOptionId(optionIdL);
    	bean.setOptionName(optionName);
    	// ѡ�����
    	Long optionCountL = null;
        String optionCount = request.getParameter("optionCount");
        if(optionCount!=null && !"".equals(optionCount)){
        	try{
        		optionCountL = new Long(optionCount);
        	}catch(Exception e){}
        }
        if(optionCountL!=null && optionCountL<1){
			request.setAttribute("alert", "����������������1�ݣ�");
			request.setAttribute("bean", bean);
			return "orderEdit";
        }
        bean.setOptionCount(optionCountL);
        // ��������
    	String orderAmoutType = request.getParameter("amountType");
        if(orderAmoutType==null || "".equals(orderAmoutType)){
        	orderAmoutType = "�����";
        }
        bean.setOrderAmountType(orderAmoutType);
        // ���������ܽ��
    	BigDecimal orderAmoutViewD = null;
    	String orderAmoutView = request.getParameter("amountView");
    	if(orderAmoutView!=null && !"".equals(orderAmoutView)){
        	try{
        		orderAmoutViewD = new BigDecimal(orderAmoutView).setScale(2, 4);
        	}catch(Exception e){}
        }
    	if(orderAmoutViewD==null || orderAmoutViewD.doubleValue()<0){
			request.setAttribute("alert", "���������ܽ�������0��");
			request.setAttribute("bean", bean);
			return "orderEdit";
    	}
		bean.setOrderAmountView(orderAmoutViewD);
        // �������ּ�����λ
    	String orderAmoutUnit = request.getParameter("amountUnit");
        if(orderAmoutUnit==null || "".equals(orderAmoutUnit)){
        	orderAmoutUnit = "Ԫ";
        }
        bean.setOrderAmountUnit(orderAmoutUnit);
        // ������ʵ���ܽ��
    	BigDecimal orderAmoutD = null;
    	String orderAmout = request.getParameter("amount");
    	if(orderAmout!=null && !"".equals(orderAmout)){
        	try{
        		orderAmoutD = new BigDecimal(orderAmout).setScale(2, 4);
        	}catch(Exception e){}
        }
    	if(orderAmoutD==null || orderAmoutD.doubleValue()<0){
			request.setAttribute("alert", "ʵ���ܽ�������0Ԫ��");
			request.setAttribute("bean", bean);
			return "orderEdit";
    	}
		bean.setOrderAmount(orderAmoutD);
		//
//		// �ڳ���Ŀѡ��
//        TbZcprojOption beanZcprojOption = null;
//        String optionId = request.getParameter("option");
//        if(optionId!=null && !"".equals(optionId)){
//        	try{
//        		String hql = "from TbZcprojOption a where a.projId="+beanZcproj.getProjId()+" and a.optionId="+new Long(optionId);
//        		beanZcprojOption = (TbZcprojOption)this.getCommonBo().getHQLUnique(hql);
//        	}catch(Exception e){}
//            if(beanZcprojOption==null){
//    			request.setAttribute("alert", "����ѡ����ڣ�");
//    			request.setAttribute("bean", bean);
//    			return "orderEdit";
//            }
//        }
//        if(beanZcprojOption!=null){
//        	bean.setOptionId(beanZcprojOption.getOptionId());
//        	bean.setOptionName(beanZcprojOption.getOptionName());
//        	// ѡ�����
//        	Long optionCountL = null;
//            String optionCount = request.getParameter("optionCount");
//            if(optionCount!=null && !"".equals(optionCount)){
//            	try{
//            		optionCountL = new Long(optionCount);
//            	}catch(Exception e){}
//            }
//            if(optionCountL==null || optionCountL<1){
//    			request.setAttribute("alert", "����������������1�ݣ�");
//    			request.setAttribute("bean", bean);
//    			return "orderEdit";
//            }
//            bean.setOptionCount(optionCountL);
//            // �������ܽ��
//            BigDecimal orderAmoutD = null;
//            try{
//            	orderAmoutD = new BigDecimal(beanZcprojOption.getOptionAmount().doubleValue()*optionCountL).setScale(2, 4);
//        	}catch(Exception e){}
//        	if(orderAmoutD==null || orderAmoutD.doubleValue()<=0){
//    			request.setAttribute("alert", "�����ܽ�������0Ԫ��");
//    			request.setAttribute("bean", bean);
//    			return "orderEdit";
//        	}
//    		bean.setOrderAmount(orderAmoutD);
//        }else if(beanZcproj.getOptionOther()!=null && "1".equals(beanZcproj.getOptionOther())){
//        	if(beanZcproj.getOptionOtherName()!=null && !"".equals(beanZcproj.getOptionOtherName())){
//        		bean.setOptionName(beanZcproj.getOptionOtherName());
//        	}else{
//        		bean.setOptionName("�����");
//        	}
//        	// �������ͽ��
//        	BigDecimal minAmountD = beanZcproj.getMinAmount();
//        	if(minAmountD==null || minAmountD.doubleValue()<=0){
//        		minAmountD = new BigDecimal(0.01).setScale(2, 4);
//        	}
//        	// �������ܽ��
//        	BigDecimal orderAmoutD = null;
//        	String orderAmout = request.getParameter("amount");
//        	if(orderAmout!=null && !"".equals(orderAmout)){
//            	try{
//            		orderAmoutD = new BigDecimal(orderAmout).setScale(2, 4);
//            	}catch(Exception e){}
//            }
//        	if(orderAmoutD==null || orderAmoutD.doubleValue()<minAmountD.doubleValue()){
//    			request.setAttribute("alert", "�����ܽ�������"+minAmountD+"Ԫ��");
//    			request.setAttribute("bean", bean);
//    			return "orderEdit";
//        	}
//    		bean.setOrderAmount(orderAmoutD);
//        }else{
//			request.setAttribute("alert", "����Ŀ��֧������裡");
//			request.setAttribute("bean", bean);
//			return "orderEdit";
//        }
		// ����
    	try{
    		bean = (TbZcprojOrder)this.getCommonBo().saveOrUpdate(bean);
    		if(bean.getOrderNum()==null || "".equals(bean.getOrderNum())){
                // ����
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
    		// ����
        	return "winSuccess";
    	}catch(Exception e){}
		// ����״̬������ʧ��
		request.setAttribute("alert", "����ʧ�ܣ�");
		request.setAttribute("bean", bean);
		// ����
    	return "orderEdit";
	}

	/* ������� */
	// ��ʽ���ı�
	private String formatStr(String str) {
		if (str != null && !"".equals(str)) {
			try {
				// ��ѧ������
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

	// ���ڴ���
	public Date getExcelDate(HSSFCell cell) throws Exception {
		String ret = "";
		try {
			if (cell != null) {
				if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					if (HSSFDateUtil.isCellDateFormatted(cell)) {// �жϵ�Ԫ���Ƿ��������ڸ�ʽ
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
		int maxYear = ca.get(Calendar.YEAR) + 10;// �������ܳ������10��
		try {
			int i = Integer.parseInt(s);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date day = format.parse("1900-01-01");// Excel���ָ�ʽ������ʼ����
			ca.setTime(day);
			ca.add(Calendar.DATE, i);// iΪ������ʼ�������ӵ�����
			day = ca.getTime();
			return day;
		} catch (Exception e) {

		}
		return DateUtil.toDate(s, "yyyy-MM-dd HH:mm");
	}

	public boolean isValidDate(String str) {
		boolean convertSuccess = true;
		// ָ�����ڸ�ʽΪ��λ��/��λ�·�/��λ���ڣ�ע��yyyy/MM/dd���ִ�Сд��
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			// ����lenientΪfalse.
			// ����SimpleDateFormat��ȽϿ��ɵ���֤���ڣ�����2007/02/29�ᱻ���ܣ���ת����2007/03/01
			format.setLenient(false);
			format.parse(str);
		} catch (ParseException e) {
			// e.printStackTrace();
			// ���throw java.text.ParseException����NullPointerException����˵����ʽ����
			convertSuccess = false;
		}
		return convertSuccess;
	}

	// �ϴ�Excel������Ȳ�������
	private String IMPORTCOUNT = "ZCPROJEDITORDEREXCELCOUNT";
	private String IMPORTINDEX = "ZCPROJEDITORDEREXCELINDEX";

	// �ϴ�Excel�������
	public String process() throws Exception {
		HttpServletRequest request = this.getRequest();
		HttpServletResponse response = this.getResponse();
		HttpSession session = request.getSession();
		// �������ʱ���
		String random = request.getParameter("random");
		if(random==null){
			random = "";
		}
		// ������Ȳ���
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
		// ��ֵ
		this.sendResponse(response, "{\"count\":" + count + ",\"index\":" + index + "}");
		// ����
		return null;
	}

	// ����ҳ��
	public String toImport() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ��Ŀ
		TbZcproj beanZcproj = null;
		// ��ĿID
		String projId = request.getParameter("projId");
		if(projId!=null && !"".equals(projId)){
			try{
				beanZcproj = (TbZcproj) this.getCommonBo().get(TbZcproj.class, new Long(projId));
			}catch(Exception e){}
		}
		if(beanZcproj==null){
			request.setAttribute("alert", "�ڳ���Ŀ�����ڣ���رղ����´򿪵���ҳ�棡");
			return "import";
		}
		request.setAttribute("beanZcproj", beanZcproj);
		return "import";
	}

	// ��������
	public String doImport() throws Exception {
		HttpServletRequest request = this.getRequest();
		MultiRequestUtil multiRequest = new MultiRequestUtil(request);
		HttpSession session = request.getSession();
		// ��Ŀ
		TbZcproj beanZcproj = null;
		// ��ĿID
		String projId = multiRequest.getParameter("projId");
		if(projId!=null && !"".equals(projId)){
			try{
				beanZcproj = (TbZcproj) this.getCommonBo().get(TbZcproj.class, new Long(projId));
			}catch(Exception e){ }
		}
		if(beanZcproj==null){
			request.setAttribute("alert", "�ڳ���Ŀ�����ڣ���رղ����´򿪵���ҳ�棡");
			return "import";
		}
		request.setAttribute("beanZcproj", beanZcproj);
		// �ڳ���ĿͼƬ
		String projPic = null;
		try{
			String hql = "select a.picPath from TbZcprojPic a where a.projId=" + beanZcproj.getProjId() + " and a.picPublish='1' order by a.numOrder";
			projPic = (String) this.getCommonBo().getHQLUnique(hql);
		}catch(Exception e){ }
		// ϵͳ��ǰʱ��
		Date dateNow = this.getCommonBo().getSysDate();
		// �������ʱ���
		String random = multiRequest.getParameter("random");
		if(random==null){
			random = "";
		}
		// ��ȡ�����ļ�
		FileItem item = multiRequest.getFile("file");
		if(item==null || item.getSize()==0){
			request.setAttribute("alert", "��ѡ����Ҫ�����Excel�ļ���");
			return "import";
		}
		// ��ȡ�����ļ�
		InputStream fis = null;
		try{
			fis = item.getInputStream();
			POIFSFileSystem fs = new POIFSFileSystem(fis);
			HSSFWorkbook workbook = new HSSFWorkbook(fs);
			HSSFSheet sheet = workbook.getSheetAt(0);
			// �ж��Ƿ�������
			int rowCount = sheet.getLastRowNum();
			if(rowCount<1){
				request.setAttribute("alert", "����ģ����󣬻����ļ���û����ص������ݣ�");
				return "import";
			}
			// ͷ
			HSSFRow headRow = sheet.getRow(0);
			int colCount = headRow.getLastCellNum();
			List headList = this.getList();
			for(int i=0; i<=colCount; i++){
				HSSFCell headCell = headRow.getCell((short) i);
				String headCellValue = "";
				if(headCell!=null){
					headCellValue = headCell.toString().trim();
				}
				if(headCellValue.contains("����")){
					headList.add("��������");
				}else if(headCellValue.contains("ѡ��")){
					headList.add("����ѡ��");
				}else if(headCellValue.contains("����")){
					headList.add("��������");
				}else if(headCellValue.endsWith("����")){
					headList.add("��������");
				}else if(headCellValue.endsWith("���ֽ��")){
					headList.add("�������ֽ��");
				}else if(headCellValue.endsWith("������λ")){
					headList.add("�������ֽ�������λ");
				}else if(headCellValue.contains("ʵ����")){
					headList.add("����ʵ����");
				}else if(headCellValue.contains("��ʽ")){
					headList.add("������ʽ");
				}else if(headCellValue.contains("״̬")){
					headList.add("����״̬");
				}else if(headCellValue.contains("�ύʱ��")){
					headList.add("�ύʱ��");
				}else if(headCellValue.contains("����ʱ��")){
					headList.add("����ʱ��");
				}else if(headCellValue.contains("֤��")){
					headList.add("��Ҫ֤��");
				}else if(headCellValue.contains("��Ʊ")){
					headList.add("��Ҫ��Ʊ");
				}else if(headCellValue.contains("����")){
					headList.add("��������");
				}else if(headCellValue.endsWith("������")){
					headList.add("����������");
				}else if(headCellValue.endsWith("����")){
					headList.add("��������");
				}else if(headCellValue.endsWith("������")){
					headList.add("������");
				}else if(headCellValue.contains("�Ա�")){
					headList.add("�Ա�");
				}else if(headCellValue.contains("�ֻ�")){
					headList.add("�ֻ�");
				}else if(headCellValue.contains("�绰")){
					headList.add("�绰");
				}else if(headCellValue.contains("����")){
					headList.add("����");
				}else if(headCellValue.contains("�ʱ�")){
					headList.add("�ʱ�");
				}else if(headCellValue.contains("ʡ")){
					headList.add("ʡ");
				}else if(headCellValue.contains("��")){
					headList.add("��");
				}else if(headCellValue.contains("��/��")){
					headList.add("��/��");
				}else if(headCellValue.contains("��ϸ��ַ")){
					headList.add("��ϸ��ַ");
				}else if(headCellValue.contains("�Ƿ�У��")){
					headList.add("�Ƿ�У��");
				}else if(headCellValue.contains("��ѧ��")){
					headList.add("��ѧ��");
				}else if(headCellValue.contains("��У��")){
					headList.add("��У��");
				}else if(headCellValue.contains("Ժϵ")){
					headList.add("Ժϵ");
				}else if(headCellValue.contains("רҵ")){
					headList.add("רҵ");
				}else if(headCellValue.contains("�༶")){
					headList.add("�༶");
				}else if(headCellValue.contains("ѧ��")){
					headList.add("ѧ��");
				}else if(headCellValue.contains("ѧ��")){
					headList.add("ѧ��");
				}else if(headCellValue.contains("��˾")){
					headList.add("��˾");
				}else if(headCellValue.contains("����")){
					headList.add("����");
				}else if(headCellValue.contains("ְλ")){
					headList.add("ְλ");
				}else if(headCellValue.contains("����")){
					headList.add("������Ϣ");
				}else if(headCellValue.contains("���")){
					headList.add("�����Ϣ");
				}else if(headCellValue.contains("��ִ")){
					headList.add("��ִ��Ϣ");
				}else if(headCellValue.contains("��ע")){
					headList.add("��ע");
				}else{
					headList.add("");
				}
			}
			if(!headList.contains("����ѡ��") || !headList.contains("��������") || !headList.contains("����ʵ����") || !headList.contains("������")){
				request.setAttribute("alert", "����ģ���ʽ�������������ص���ģ�壡");
				return "import";
			}
			// ���õ�����Ȳ���
			session.setAttribute(IMPORTCOUNT + random, rowCount);
			// ����Excel��������
			int orderNumIndex = headList.indexOf("��������");
			int optionNameIndex = headList.indexOf("����ѡ��");
			int optionCountIndex = headList.indexOf("��������");
			int amountTypeIndex = headList.indexOf("��������");
			int amountViewIndex = headList.indexOf("�������ֽ��");
			int amountUnitIndex = headList.indexOf("�������ֽ�������λ");
			int amountIndex = headList.indexOf("����ʵ����");
			int orderTypeIndex = headList.indexOf("������ʽ");
			int orderStatusIndex = headList.indexOf("����״̬");
			int orderTimeIndex = headList.indexOf("�ύʱ��");
			int orderOkTimeIndex = headList.indexOf("����ʱ��");
			int needZhengshuIndex = headList.indexOf("��Ҫ֤��");
			int needFapiaoIndex = headList.indexOf("��Ҫ��Ʊ");
			int niMingIndex = headList.indexOf("��������");
			int personTypeIndex = headList.indexOf("����������");
			int personCountIndex = headList.indexOf("��������");
			int personIndex = headList.indexOf("������");
			int personSexIndex = headList.indexOf("�Ա�");
			int personPhoneIndex = headList.indexOf("�ֻ�");
			int personTelIndex = headList.indexOf("�绰");
			int personEmailIndex = headList.indexOf("����");
			int zipIndex = headList.indexOf("�ʱ�");
			int shengIndex = headList.indexOf("ʡ");
			int shiIndex = headList.indexOf("��");
			int quIndex = headList.indexOf("��/��");
			int addressIndex = headList.indexOf("��ϸ��ַ");
			int alumniFlagIndex = headList.indexOf("�Ƿ�У��");
			int academyBegIndex = headList.indexOf("��ѧ��");
			int academyEndIndex = headList.indexOf("��У��");
			int academyIndex = headList.indexOf("Ժϵ");
			int majorIndex = headList.indexOf("רҵ");
			int clazzIndex = headList.indexOf("�༶");
			int studynoIndex = headList.indexOf("ѧ��");
			int academyDegreeIndex = headList.indexOf("ѧ��");
			int companyIndex = headList.indexOf("��˾");
			int departmentIndex = headList.indexOf("����");
			int dutyIndex = headList.indexOf("ְλ");
			int orderMemoIndex = headList.indexOf("������Ϣ");
			int markIndex = headList.indexOf("�����Ϣ");
			int receiptIndex = headList.indexOf("��ִ��Ϣ");
			int memoIndex = headList.indexOf("��ע");
			// ���뷴����Ϣ
			int successcount = 0;
			int errorcount = 0;
			List errorList = this.getList();
			// ѭ������
			for(int i=1; i<=rowCount; i++){
				HSSFRow row = sheet.getRow(i);
				// ���õ�����Ȳ���
				session.setAttribute(IMPORTINDEX + random, i);
				try{
					// ��Ŀ����
					TbZcprojOrder bean = null;
					// ��������
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
									errorList.add(new String[] {String.valueOf(i), "����ʧ�ܣ�ԭ�򣺸���Ŀ�в����ڵ���Ϊ��"+orderNum+"���ľ�����¼��δ�����������´���"});
									continue;
								}
							}
						}
					}
					if(bean==null){
						bean = new TbZcprojOrder();
						// ����������Ŀ
						bean.setProjId(beanZcproj.getProjId());
						bean.setProjName(beanZcproj.getProjName());
						bean.setProjPic(projPic);
						// ��������
						bean.setOrderAmountType("�����");
						// �������ֽ�������λ
						bean.setOrderAmountUnit("Ԫ");
						// ����״̬
						bean.setOrderStatus("0");
						// ֤��
						bean.setNeedZhengshu("0");
						// ��Ʊ
						bean.setNeedFapiao("0");
						// ������ ����
						bean.setPersonType("1");
						// ������ ����
						bean.setPersonCount(new Long(1));
						// ������ ����
						bean.setPersonName("����");
						// ����
						bean.setNiMing("0");
						// �Ƿ�У��
						bean.setAlumniFlag("0");
					}
					// ����ѡ��
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
					// ��������
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
						bean.setOptionName("�����");
						bean.setOptionCount(null);
					}
					// ��������
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
			        // �������ֽ��
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
					// �������ּ�����λ
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
					// �ܽ��
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
						errorList.add(new String[] {String.valueOf(i), "����ʧ�ܣ�ԭ�򣺾������������0�������λС������������"});
						continue;
					}
					bean.setOrderAmount(orderAmoutD);
					// �������ܽ�Ĭ�ϴ���
					if("�����".equals(bean.getOrderAmountType()) && bean.getOrderAmountView()==null){
				    	bean.setOrderAmountView(bean.getOrderAmount());
					}
					// ������ʽ
					if(orderTypeIndex!=-1){
						HSSFCell orderTypeCell = row.getCell((short) orderTypeIndex);
						if(orderTypeCell!=null){
							String orderType = formatStr(orderTypeCell.toString().trim());
							bean.setOrderType(orderType);
						}
					}
					// ����״̬
					if(orderStatusIndex!=-1){
						HSSFCell orderStatusCell = row.getCell((short) orderStatusIndex);
						if(orderStatusCell!=null){
							String orderStatus = formatStr(orderStatusCell.toString().trim());
							if(orderStatus!=null && ("�Ѹ���".equals(orderStatus) || "1".equals(orderStatus))){
								bean.setOrderStatus("1");
							}else{
								bean.setOrderStatus("0");
							}
						}
					}
					// �ύʱ��
					if(orderTimeIndex!=-1){
						HSSFCell orderTimeCell = row.getCell((short) orderTimeIndex);
						if(orderTimeCell!=null){
							Date orderTime = getExcelDate(orderTimeCell);
							bean.setOrderTime(orderTime);
						}
					}
					// ����ʱ��
					if(orderOkTimeIndex!=-1){
						HSSFCell orderOkTimeCell = row.getCell((short) orderOkTimeIndex);
						if(orderOkTimeCell!=null){
							Date orderOkTime = getExcelDate(orderOkTimeCell);
							bean.setOrderOkTime(orderOkTime);
						}
					}
					// ֤��
					if(needZhengshuIndex!=-1){
						HSSFCell needZhengshuCell = row.getCell((short) needZhengshuIndex);
						if(needZhengshuCell!=null){
							String needZhengshu = formatStr(needZhengshuCell.toString().trim());
							if(needZhengshu!=null && ("��Ҫ".equals(needZhengshu) || "��".equals(needZhengshu) || "1".equals(needZhengshu))) {
								bean.setNeedZhengshu("1");
							}else{
								bean.setNeedZhengshu("0");
							}
						}
					}
					// ��Ʊ
					if(needFapiaoIndex!=-1){
						HSSFCell needFapiaoCell = row.getCell((short) needFapiaoIndex);
						if(needFapiaoCell!=null){
							String needFapiao = formatStr(needFapiaoCell.toString().trim());
							if(needFapiao!=null && ("��Ҫ".equals(needFapiao) || "��".equals(needFapiao) || "1".equals(needFapiao))){
								bean.setNeedFapiao("1");
							}else{
								bean.setNeedFapiao("0");
							}
						}
					}
					// ������
					// ����������
					if(personTypeIndex!=-1){
						HSSFCell personTypeCell = row.getCell((short) personTypeIndex);
						if(personTypeCell!=null){
							String personType = formatStr(personTypeCell.toString().trim());
							if(personType!=null && "����".equals(personType)){
								personType = "1";
							}else if(personType!=null && "����".equals(personType)){
								personType = "2";
							}
							if(personType==null || (!"1".equals(personType) && !"2".equals(personType))){
								personType = bean.getPersonType();
							}
							bean.setPersonType(personType);
						}
					}
					// ��������
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
					// ����
					if(personIndex!=-1){
						HSSFCell personCell = row.getCell((short) personIndex);
						if(personCell!=null){
							String person = formatStr(personCell.toString().trim());
							if(person==null || "".equals(person)){
								person = "����";
							}
							bean.setPersonName(person);
						}
					}
					// �Ա�
					if(personSexIndex!=-1){
						HSSFCell personSexCell = row.getCell((short) personSexIndex);
						if(personSexCell!=null){
							String personSex = formatStr(personSexCell.toString().trim());
							if(personSex!=null){
								if("��".equals(personSex) || "����".equals(personSex) || "1".equals(personSex)){
									bean.setPersonSex("1");
								}else if("Ů".equals(personSex) || "Ů��".equals(personSex) || "2".equals(personSex)){
									bean.setPersonSex("2");
								}
							}
						}
					}
					// ����
					if(niMingIndex!=-1){
						HSSFCell niMingCell = row.getCell((short) niMingIndex);
						if(niMingCell!=null){
							String niMing = formatStr(niMingCell.toString().trim());
							if(niMing!=null && ("��".equals(niMing) || "����".equals(niMing) || "��������".equals(niMing) || "1".equals(niMing))){
								bean.setNiMing("1");
							}else{
								bean.setNiMing("0");
							}
						}
					}
					// �ֻ�
					if(personPhoneIndex!=-1){
						HSSFCell personPhoneCell = row.getCell((short) personPhoneIndex);
						if(personPhoneCell!=null){
							String personPhone = formatStr(personPhoneCell.toString().trim());
							bean.setPersonPhone(personPhone);
						}
					}
					// �绰
					if(personTelIndex!=-1){
						HSSFCell personTelCell = row.getCell((short) personTelIndex);
						if(personTelCell!=null){
							String personTel = formatStr(personTelCell.toString().trim());
							bean.setPersonTel(personTel);
						}
					}
					// ����
					if(personEmailIndex!=-1){
						HSSFCell personEmailCell = row.getCell((short) personEmailIndex);
						if(personEmailCell!=null){
							String personEmail = formatStr(personEmailCell.toString().trim());
							bean.setPersonEmail(personEmail);
						}
					}
					// �ʱ�
					if(zipIndex!=-1){
						HSSFCell zipCell = row.getCell((short) zipIndex);
						if(zipCell!=null){
							String zip = formatStr(zipCell.toString().trim());
							bean.setAddressZip(zip);
						}
					}
					// ʡ
					if(shengIndex!=-1){
						HSSFCell shengCell = row.getCell((short) shengIndex);
						if(shengCell!=null){
							String sheng = formatStr(shengCell.toString().trim());
							bean.setAddressSheng(sheng);
						}
					}
					// ��
					if(shiIndex!=-1){
						HSSFCell shiCell = row.getCell((short) shiIndex);
						if(shiCell!=null){
							String shi = formatStr(shiCell.toString().trim());
							bean.setAddressShi(shi);
						}
					}
					// ��/��
					if(quIndex!=-1){
						HSSFCell quCell = row.getCell((short) quIndex);
						if (quCell != null) {
							String qu = formatStr(quCell.toString().trim());
							bean.setAddressQu(qu);
						}
					}
					// ��ϸ��ַ
					if(addressIndex!=-1){
						HSSFCell addressCell = row.getCell((short) addressIndex);
						if (addressCell != null) {
							String address = formatStr(addressCell.toString().trim());
							bean.setAddressDetail(address);
						}
					}
					// У������
					// �Ƿ�У��
					if(alumniFlagIndex!=-1){
						HSSFCell alumniFlagCell = row.getCell((short) alumniFlagIndex);
						if (alumniFlagCell != null) {
							String alumniFlag = formatStr(alumniFlagCell.toString().trim());
							if(alumniFlag!=null && ("У��".equals(alumniFlag) || "��".equals(alumniFlag) || "1".equals(alumniFlag))){
								bean.setAlumniFlag("1");
							}else{
								bean.setAlumniFlag("0");
							}
						}
					}
					// ��ѧ��
					if(academyBegIndex!=-1){
						HSSFCell academyBegCell = row.getCell((short) academyBegIndex);
						if(academyBegCell!=null){
							String academyBeg = formatStr(academyBegCell.toString().trim());
							bean.setStudyYearin(academyBeg);
						}
					}
					// ��У��
					if(academyEndIndex!=-1){
						HSSFCell academyEndCell = row.getCell((short) academyEndIndex);
						if(academyEndCell!=null){
							String academyEnd = formatStr(academyEndCell.toString().trim());
							bean.setStudyYearover(academyEnd);
						}
					}
					// Ժϵ
					if(academyIndex!=-1){
						HSSFCell academyCell = row.getCell((short) academyIndex);
						if(academyCell!=null){
							String academy = formatStr(academyCell.toString().trim());
							bean.setStudyAcademy(academy);
						}
					}
					// רҵ
					if(majorIndex!=-1){
						HSSFCell majorCell = row.getCell((short) majorIndex);
						if(majorCell!=null){
							String major = formatStr(majorCell.toString().trim());
							bean.setStudyMajor(major);
						}
					}
					// �༶
					if(clazzIndex!=-1){
						HSSFCell clazzCell = row.getCell((short) clazzIndex);
						if(clazzCell!=null){
							String clazz = formatStr(clazzCell.toString().trim());
							bean.setStudyClass(clazz);
						}
					}
					// ѧ��
					if(studynoIndex!=-1){
						HSSFCell studynoCell = row.getCell((short) studynoIndex);
						if(studynoCell!=null){
							String studyno = formatStr(studynoCell.toString().trim());
							bean.setStudyNum(studyno);
						}
					}
					// ѧ��
					if(academyDegreeIndex!=-1){
						HSSFCell academyDegreeCell = row.getCell((short) academyDegreeIndex);
						if(academyDegreeCell!=null){
							String academyDegree = formatStr(academyDegreeCell.toString().trim());
							bean.setStudyDegree(academyDegree);
						}
					}
					// ��������
					// ��˾
					if(companyIndex!=-1){
						HSSFCell companyCell = row.getCell((short) companyIndex);
						if(companyCell!=null){
							String company = formatStr(companyCell.toString().trim());
							bean.setWorkCompany(company);
						}
					}
					// ����
					if(departmentIndex!=-1){
						HSSFCell departmentCell = row.getCell((short) departmentIndex);
						if(departmentCell!=null){
							String department = formatStr(departmentCell.toString().trim());
							bean.setWorkDepart(department);
						}
					}
					// ְλ
					if(dutyIndex!=-1){
						HSSFCell dutyCell = row.getCell((short) dutyIndex);
						if(dutyCell!=null){
							String duty = formatStr(dutyCell.toString().trim());
							bean.setWorkDuty(duty);
						}
					}
					// ������Ϣ
					if(orderMemoIndex!=-1){
						HSSFCell orderMemoCell = row.getCell((short) orderMemoIndex);
						if(orderMemoCell!=null){
							String orderMemo = formatStr(orderMemoCell.toString().trim());
							bean.setOrderMemo(orderMemo);
						}
					}
					// �����Ϣ
					if(markIndex!=-1){
						HSSFCell markCell = row.getCell((short) markIndex);
						if(markCell!=null){
							String mark = formatStr(markCell.toString().trim());
							bean.setMark(mark);
						}
					}
					// ��ִ��Ϣ
					if(receiptIndex!=-1){
						HSSFCell receiptCell = row.getCell((short) receiptIndex);
						if(receiptCell!=null){
							String receipt = formatStr(receiptCell.toString().trim());
							bean.setReceipt(receipt);
						}
					}
					// ��ע
					if(memoIndex!=-1){
						HSSFCell memoCell = row.getCell((short) memoIndex);
						if(memoCell!=null){
							String memo = formatStr(memoCell.toString().trim());
							bean.setMemo(memo);
						}
					}
					// ����
					bean = (TbZcprojOrder) this.getCommonBo().saveOrUpdate(bean);
					if(bean.getOrderNum()==null || "".equals(bean.getOrderNum())){
						// ����
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
					errorList.add(new String[] { String.valueOf(i), "����ʧ�ܣ�ԭ�򣺸���������Ϣ����ʱ�������쳣���쳣������"+e.getMessage() });
				}
			}
			request.setAttribute("successcount", successcount);
			request.setAttribute("errorcount", errorcount);
			request.setAttribute("errorList", errorList);
			return "import";
		}catch(Exception e){
			request.setAttribute("alert", "���棬�����ļ�ʱ�����쳣���쳣������"+e.getMessage());
			return "import";
		}finally{
			if(fis!=null){
				fis.close();
			}
			// �Ƴ�������Ȳ���
			session.removeAttribute(IMPORTINDEX + random);
			session.removeAttribute(IMPORTCOUNT + random);
		}
	}

	// ��������
	public String doExport() throws Exception {
		HttpServletRequest request = this.getRequest();
		// �������
		String ajax = "{\"result\":\"error\"}";
		// 1�����ص���ģ�壬����Ϊ����
		String type = request.getParameter("type");
		// ��Ҫ��������Ϣϵͳ����
		List beantList = null;
		// ��ȡ��Ҫ�����ľ�����Ϣ����
		if(type==null || !"1".equals(type)){
			// ��Ŀ
			TbZcproj beanZcproj = null;
			// ��ĿID
			String projId = request.getParameter("projId");
			if(projId!=null && !"".equals(projId)){
				try{
					beanZcproj = (TbZcproj)this.getCommonBo().get(TbZcproj.class, new Long(projId));
				}catch (Exception e){ }
			}
			if(beanZcproj!=null){
				// �����ѯ
				String hql = "from TbZcprojOrder a where a.projId=?";
				List pars = this.getList();
				pars.add(beanZcproj.getProjId());
				// ����ѡ��
				String optionName = request.getParameter("optionName");
				if(optionName!=null && !"".equals(optionName)){
					hql += " and a.optionName like ?";
					pars.add("%"+optionName+"%");
				}
				// ��������
				String orderNum = request.getParameter("orderNum");
				if(orderNum != null && !"".equals(orderNum)){
					hql += " and a.orderNum like ?";
					pars.add("%" + orderNum + "%");
				}
				// ������ʽ
				String orderType = request.getParameter("orderType");
				if(orderType != null && !"".equals(orderType)){
					hql += " and a.orderType like ?";
					pars.add("%" + orderType + "%");
				}
				// ֤��
				String needZhengshu = request.getParameter("needZhengshu");
				if(needZhengshu != null && "1".equals(needZhengshu)){
					hql += " and a.needZhengshu = ?";
					pars.add("1");
				}else if(needZhengshu != null && !"".equals(needZhengshu)){
					hql += " and (a.needZhengshu is null or a.needZhengshu <> ?)";
					pars.add("1");
				}
				// ��Ʊ
				String needFapiao = request.getParameter("needFapiao");
				if(needFapiao != null && "1".equals(needFapiao)){
					hql += " and a.needFapiao = ?";
					pars.add("1");
				}else if(needFapiao != null && !"".equals(needFapiao)){
					hql += " and (a.needFapiao is null or a.needFapiao <> ?)";
					pars.add("1");
				}
				// ��������
				String niMing = request.getParameter("niMing");
				if(niMing != null && "1".equals(niMing)){
					hql += " and a.niMing = ?";
					pars.add("1");
				}else if(niMing != null && !"".equals(niMing)){
					hql += " and (a.niMing is null or a.niMing <> ?)";
					pars.add("1");
				}
				// ������
				String personName = request.getParameter("personName");
				if(personName != null && !"".equals(personName)){
					hql += " and a.personName like ?";
					pars.add("%" + personName + "%");
				}
				// ��������Ϣ
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
				// У��
				String alumni = request.getParameter("alumni");
				if(alumni != null && "1".equals(alumni)){
					hql += " and a.alumniFlag = ?";
					pars.add("1");
				}else if(alumni != null && !"".equals(alumni)){
					hql += " and (a.alumniFlag is null or a.alumniFlag <> ?)";
					pars.add("1");
				}
				// У����Ϣ
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
				// ���
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
				// ��������
				String amountType = request.getParameter("amountType");
				if(amountType!=null && !"".equals(amountType)){
					hql += " and a.orderAmountType like ?";
					pars.add("%"+amountType+"%");
				}
				// �ύ��ʼʱ��
				Date orderTimeBegD = null;
				String orderTimeBeg = request.getParameter("orderTimeBeg");
				if(orderTimeBeg!=null && !"".equals(orderTimeBeg)){
					orderTimeBegD = DateUtil.toDate(orderTimeBeg, "yyyy-MM-dd HH:mm");
				}
				if(orderTimeBegD!=null){
					hql += " and a.orderTime >= ?";
					pars.add(orderTimeBegD);
				}
				// �ύ��ֹʱ��
				Date orderTimeEndD = null;
				String orderTimeEnd = request.getParameter("orderTimeEnd");
				if(orderTimeEnd!=null && !"".equals(orderTimeEnd)){
					orderTimeEndD = DateUtil.toDate(orderTimeEnd, "yyyy-MM-dd HH:mm");
				}
				if(orderTimeEndD!=null){
					hql += " and a.orderTime <= ?";
					pars.add(orderTimeEndD);
				}
				// ������ʼʱ��
				Date orderOkTimeBegD = null;
				String orderOkTimeBeg = request.getParameter("orderOkTimeBeg");
				if(orderOkTimeBeg!=null && !"".equals(orderOkTimeBeg)){
					orderOkTimeBegD = DateUtil.toDate(orderOkTimeBeg, "yyyy-MM-dd HH:mm");
				}
				if(orderOkTimeBegD!=null){
					hql += " and a.orderOkTime >= ?";
					pars.add(orderOkTimeBegD);
				}
				// �����ֹʱ��
				Date orderOkTimeEndD = null;
				String orderOkTimeEnd = request.getParameter("orderOkTimeEnd");
				if(orderOkTimeEnd!=null && !"".equals(orderOkTimeEnd)){
					orderOkTimeEndD = DateUtil.toDate(orderOkTimeEnd, "yyyy-MM-dd HH:mm");
				}
				if(orderOkTimeEndD!=null){
					hql += " and a.orderOkTime <= ?";
					pars.add(orderOkTimeEndD);
				}
				// ����״̬
				String orderStatus = request.getParameter("orderStatus");
				if(orderStatus!=null && "1".equals(orderStatus)){
					hql += " and a.orderStatus = ?";
					pars.add("1");
				}else if(orderStatus!=null && !"".equals(orderStatus)){
					hql += " and (a.orderStatus is null or a.orderStatus <> ?)";
					pars.add("1");
				}
				// ����������
				String personType = request.getParameter("personType");
				if(personType!=null && "1".equals(personType)){
					hql += " and a.personType = ?";
					pars.add("1");
				}else if(personType!=null && !"".equals(personType)){
					hql += " and (a.personType is null or a.personType <> ?)";
					pars.add("1");
				}
				// Ĭ�ϲ�ѯ
				hql += " order by a.orderTime desc";
				// ִ�в�ѯ
				try{
					beantList = this.getCommonBo().findHQL(hql, pars);
				}catch(Exception e){ }
			}
		}
		// ����Excel�ļ�
		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet();
			// ���õ�Ԫ����
			sheet.setColumnWidth((short) 0, (short) 8000);
			sheet.setColumnWidth((short) 1, (short) 8000);
			sheet.setDefaultColumnWidth((short) 24);
			// ���õ�Ԫ������
			HSSFFont font = wb.createFont();
			font.setFontName("����");
			font.setFontHeight((short) 200);
			// ������Ԫ����ʽ(���Ͻ�)
			HSSFCellStyle cellLeftTopStyle = wb.createCellStyle();
			// ָ����Ԫ����ж���
			cellLeftTopStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// ָ����Ԫ��ֱ���ж���
			cellLeftTopStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			// ָ������Ԫ��������ʾ����ʱ�Զ�����
			cellLeftTopStyle.setWrapText(true);
			cellLeftTopStyle.setFont(font);
			cellLeftTopStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // �±߿�
			cellLeftTopStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// ��߿�
			cellLeftTopStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// �ϱ߿�
			cellLeftTopStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// �ұ߿�
			// ������Ԫ����ʽ(��)
			HSSFCellStyle cellLeftStyle = wb.createCellStyle();
			// ָ����Ԫ����ж���
			cellLeftStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// ָ����Ԫ��ֱ���ж���
			cellLeftStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			// ָ������Ԫ��������ʾ����ʱ�Զ�����
			cellLeftStyle.setWrapText(true);
			cellLeftStyle.setFont(font);
			cellLeftStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // �±߿�
			cellLeftStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// ��߿�
			cellLeftStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// �ұ߿�
			// ������Ԫ����ʽ(��)
			HSSFCellStyle cellTopStyle = wb.createCellStyle();
			// ָ����Ԫ����ж���
			cellTopStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// ָ����Ԫ��ֱ���ж���
			cellTopStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			// ָ������Ԫ��������ʾ����ʱ�Զ�����
			cellTopStyle.setWrapText(true);
			cellTopStyle.setFont(font);
			cellTopStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // �±߿�
			cellTopStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// �ұ߿�
			cellTopStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// �ϱ߿�
			// ������Ԫ����ʽ(����)
			HSSFCellStyle cellStyle = wb.createCellStyle();
			// ָ����Ԫ����ж���
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// ָ����Ԫ��ֱ���ж���
			cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			// ָ������Ԫ��������ʾ����ʱ�Զ�����
			cellStyle.setWrapText(true);
			cellStyle.setFont(font);
			cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // �±߿�
			cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// �ұ߿�
			// ��һ�У�������
			List headList = this.getList();
			headList.add("��������");
			headList.add("��Ŀ����");
			headList.add("����ѡ��");
			headList.add("��������");
			headList.add("��������");
			headList.add("�������ֽ��");
			headList.add("�������ּ�����λ");
			headList.add("����ʵ����");
			headList.add("������ʽ");
			headList.add("����״̬");
			headList.add("�ύʱ��");
			headList.add("����ʱ��");
			headList.add("��Ҫ֤��");
			headList.add("��Ҫ��Ʊ");
			headList.add("��������");
			headList.add("����������");
			headList.add("��������");
			headList.add("������");
			headList.add("�Ա�");
			headList.add("�ֻ�");
			headList.add("�绰");
			headList.add("����");
			headList.add("�ʱ�");
			headList.add("ʡ");
			headList.add("��");
			headList.add("��/��");
			headList.add("��ϸ��ַ");
			headList.add("�Ƿ�У��");
			headList.add("��ѧ��");
			headList.add("��У��");
			headList.add("Ժϵ");
			headList.add("רҵ");
			headList.add("�༶");
			headList.add("ѧ��");
			headList.add("ѧ��");
			headList.add("��˾");
			headList.add("����");
			headList.add("ְλ");
			headList.add("������Ϣ");
			headList.add("�����Ϣ");
			headList.add("��ִ��Ϣ");
			headList.add("��ע");
			HSSFRow row0 = sheet.createRow(0);
			row0.setHeight((short) 600);
			// ��һ��
			for(int i=0; i<headList.size(); i++){
				HSSFCell row0cell = row0.createCell((short) i);
				row0cell.setCellStyle(cellTopStyle);
				row0cell.setCellValue(new HSSFRichTextString(headList.get(i).toString()));
			}
			// ������
			if(beantList!=null && !beantList.isEmpty()){
				for(int i=1; i<beantList.size()+1; i++){
					HSSFRow row = sheet.createRow(i);
					row.setHeight((short) 600);
					TbZcprojOrder bean = (TbZcprojOrder)beantList.get(i-1);
					// �к�
					int k = 0;
					// �ж���
					HSSFCell cell = null;
					// ��������
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getOrderNum()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getOrderNum()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ��Ŀ����
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getProjName()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getProjName()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ����ѡ��
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getOptionName()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getOptionName()));
					}else{
						TbZcproj tempZcproj = bean.getTbZcproj();
						if(tempZcproj!=null && tempZcproj.getOptionOtherName()!=null && !"".equals(tempZcproj.getOptionOtherName())){
							cell.setCellValue(new HSSFRichTextString(tempZcproj.getOptionOtherName()));
						}else{
							cell.setCellValue(new HSSFRichTextString("�����"));
						}
					}
					// ��������
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getOptionCount()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getOptionCount().toString()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ��������
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getOrderAmountType()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getOrderAmountType().toString()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// �������ֽ��
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getOrderAmountView()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getOrderAmountView().toString()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// �������ּ�����λ
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getOrderAmountUnit()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getOrderAmountUnit().toString()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ����ʵ����
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getOrderAmount()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getOrderAmount().toString()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ������ʽ
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getOrderType()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getOrderType()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ����״̬
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getOrderStatus()!=null && "1".equals(bean.getOrderStatus())){
						cell.setCellValue(new HSSFRichTextString("�Ѹ���"));
					}else{
						cell.setCellValue(new HSSFRichTextString("������"));
					}
					// �ύʱ��
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getOrderTime()!=null){
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						cell.setCellValue(new HSSFRichTextString(format.format(bean.getOrderTime())));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ����ʱ��
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getOrderOkTime()!=null){
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						cell.setCellValue(new HSSFRichTextString(format.format(bean.getOrderOkTime())));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ֤��
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getNeedZhengshu()!=null && "1".equals(bean.getNeedZhengshu())){
						cell.setCellValue(new HSSFRichTextString("��"));
					}else{
						cell.setCellValue(new HSSFRichTextString("��"));
					}
					// ��Ʊ
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getNeedFapiao()!=null && "1".equals(bean.getNeedFapiao())){
						cell.setCellValue(new HSSFRichTextString("��"));
					}else{
						cell.setCellValue(new HSSFRichTextString("��"));
					}
					// �Ƿ�����
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getNiMing()!=null && "1".equals(bean.getNiMing())){
						cell.setCellValue(new HSSFRichTextString("��"));
					}else{
						cell.setCellValue(new HSSFRichTextString("��"));
					}
					// ����������
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getPersonType()!=null && "2".equals(bean.getPersonType())){
						cell.setCellValue(new HSSFRichTextString("����"));
					}else{
						cell.setCellValue(new HSSFRichTextString("����"));
					}
					// ��������
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getPersonCount()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getPersonCount().toString()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ������
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getPersonName()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getPersonName()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// �Ա�
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getPersonSex()!=null && "1".equals(bean.getPersonSex())){
						cell.setCellValue(new HSSFRichTextString("��"));
					}else if(bean.getPersonSex()!=null && "2".equals(bean.getPersonSex())){
						cell.setCellValue(new HSSFRichTextString("Ů"));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// �ֻ�
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getPersonPhone()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getPersonPhone()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// �绰
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getPersonTel()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getPersonTel()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ����
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getPersonEmail()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getPersonEmail()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// �ʱ�
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getAddressZip()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getAddressZip()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ʡ
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getAddressSheng()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getAddressSheng()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ��
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getAddressShi()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getAddressShi()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ��/��
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getAddressQu()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getAddressQu()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ��ϸ��ַ
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getAddressDetail()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getAddressDetail()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// �Ƿ�У��
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getAlumniFlag()!=null && "1".equals(bean.getAlumniFlag())){
						cell.setCellValue(new HSSFRichTextString("��"));
					}else{
						cell.setCellValue(new HSSFRichTextString("��"));
					}
					// ��ѧ��
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getStudyYearin()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getStudyYearin()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ��У��
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getStudyYearover()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getStudyYearover()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// Ժϵ
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getStudyAcademy()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getStudyAcademy()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// רҵ
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getStudyMajor()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getStudyMajor()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// �༶
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getStudyClass()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getStudyClass()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ѧ��
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getStudyNum()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getStudyNum()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ѧ��
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getStudyDegree()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getStudyDegree()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ��˾
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getWorkCompany()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getWorkCompany()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ����
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getWorkDepart()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getWorkDepart()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ְλ
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getWorkDuty()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getWorkDuty()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ������Ϣ
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getOrderMemo()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getOrderMemo()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// �����Ϣ
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getMark()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getMark()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ��ִ��Ϣ
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getReceipt()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getReceipt()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
					// ��ע
					cell = row.createCell((short) k++);
					cell.setCellStyle(cellStyle);
					if(bean.getMemo()!=null){
						cell.setCellValue(new HSSFRichTextString(bean.getMemo()));
					}else{
						cell.setCellValue(new HSSFRichTextString(""));
					}
				}
			}
			// �����ļ�
			String fielCurName = "������Ϣ����" + DateUtil.format(new Date(), "yyyyMMddHHmmss") + ".xls";
			String fileNewName = "������Ϣ����" + ".xls";
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
		// ����
		this.sendResponse(this.getResponse(), ajax);
		return null;
	}

}