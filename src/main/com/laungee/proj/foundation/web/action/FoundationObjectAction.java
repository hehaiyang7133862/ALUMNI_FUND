package com.laungee.proj.foundation.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbAcceptCompany;
import com.laungee.proj.common.model.TbAlumni;
import com.laungee.proj.common.model.TbDonationCompany;
import com.laungee.proj.common.model.TbDonationCompanyMember;
import com.laungee.proj.common.model.TbDonationor;
import com.laungee.proj.common.model.TbField;
import com.laungee.proj.common.model.TbProDetail;
import com.laungee.proj.common.model.TbProPay;
import com.laungee.proj.common.model.TbWork;

/**
 * 捐赠对象管理
 * @author hexiang
 *
 */
public class FoundationObjectAction extends BaseAction{
	
	/**
	 * 捐赠单位列表
	 * @return
	 * @throws Exception 
	 */
	public String companyList() throws Exception{
		//request
		HttpServletRequest request=this.getRequest();
		//参数
		//单位名称
		String comName=request.getParameter("comName");
		//主要业务活动
		String comBusi=request.getParameter("comBusi");
		//登记注册类型
		String comType=request.getParameter("comType");
		//所属行业
		String industryFid=request.getParameter("industryFid");
		//详细地址
		String comAddress=request.getParameter("comAddress");
		//单位负责人
		String responseName=request.getParameter("responseName");
		
		//hql
		String hql="from TbDonationCompany a where 1=1";
		List pars=this.getList();
		if(comName!=null&&!"".equals(comName)){
			hql+=" and comName like ?";
			pars.add("%"+comName+"%");
		}
		if(comBusi!=null&&!"".equals(comBusi)){
			hql+=" and comBusi like ?";
			pars.add("%"+comBusi+"%");
		}
		if(comType!=null&&!"".equals(comType)){
			hql+=" and comType = ?";
			pars.add(comType);
		}
		if(industryFid!=null&&!"".equals(industryFid)){
			hql+=" and industryFid = ?";
			pars.add("%"+industryFid+"%");
		}
		if(comAddress!=null&&!"".equals(comAddress)){
			hql+=" and comAddress like ?";
			pars.add("%"+comAddress+"%");
		}
		if(responseName!=null&&!"".equals(responseName)){
			hql+=" and responseName like ?";
			pars.add("%"+responseName+"%");
		}
		hql+=" order by getpinyin(a.comName) asc";
		//查询结果
		List companyList=this.getCommonBo().findHQLPage(hql,pars);
		//输出到页面
		request.setAttribute("companyList", companyList);
		return "companyListPage";
	}
	
	
	/**
	 * 跳转到新增或编辑捐赠单位页面
	 * @return
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public String toCompanyModifyPage() throws NumberFormatException, Exception{
		//request
		HttpServletRequest request=this.getRequest();
		//参数
		//单位ID 
		String comId=request.getParameter("comId");
		if(comId!=null&&!"".equals(comId)){
			TbDonationCompany bean=(TbDonationCompany) this.getCommonBo().get(TbDonationCompany.class, new Long(comId));
			request.setAttribute("bean", bean);
		}
		request.setAttribute("comId", comId);
		return "companyModifyPage";
	}
	
	/**
	 * 新增或编辑捐赠单位
	 * @return
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public String saveOrUpdateCom() throws NumberFormatException, Exception{
		String json="{\"code\":\"error\",\"msg\":\"系统错误，请稍候再试\"}";
		//request
		HttpServletRequest request=this.getRequest();
		//参数
		//单位ID 
		String comId=request.getParameter("comId");
		//单位名称
		String comName=request.getParameter("comName");
		//主要业务活动
		String comBusi=request.getParameter("comBusi");
		//登记注册类型
		String comType=request.getParameter("comType");
		//所属行业
		String industryFid=request.getParameter("industryFid");
		//详细地址
		String comAddress=request.getParameter("comAddress");
		//单位负责人
		String responseName=request.getParameter("responseName");
		
		//添加到对象
		try {
			TbDonationCompany bean=null;
			if(comId!=null&&!"".equals(comId)){
				bean=(TbDonationCompany) this.getCommonBo().get(TbDonationCompany.class, new Long(comId));
				request.setAttribute("bean", bean);
			}else{
				bean=new TbDonationCompany();
			}
			
			if(comName!=null&&!"".equals(comName)){
				bean.setComName(comName);
			}
			bean.setComAddress(comAddress);
			bean.setComBusi(comBusi);
			bean.setComType(comType);
			bean.setIndustryFid(industryFid);
			bean.setResponseName(responseName);
			bean=(TbDonationCompany) this.getCommonBo().saveOrUpdate(bean);
			json="{\"code\":\"success\",\"msg\":\"操作成功\",\"id\":\""+bean.getComId()+"\"}";
		}catch (Exception e) {
			// TODO: handle exception
			json="{\"code\":\"error\",\"msg\":\"异常："+e.getCause().getLocalizedMessage()+"\"}";
		}
		this.sendResponse(getResponse(), json);
		return null;
	}
	
	/**
	 * 删除捐赠单位
	 * @return
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	public String delCompany() throws NumberFormatException, Exception{
		//得到request
		HttpServletRequest request=this.getRequest();
		//协议详细ID
		String comId=request.getParameter("comId");
		if(comId!=null&&!"".equals(comId)){
			TbDonationCompany bean=(TbDonationCompany) this.getCommonBo().get(TbDonationCompany.class, new Long(comId));
			if(bean!=null){
				//删除所有单位人员
				String hql="delete from TbDonationCompanyMember a where a.comId="+bean.getComId();
				this.getCommonBo().executeHql(hql);
				//删除
				this.getCommonBo().delete(bean);
			}
			//
			request.setAttribute("alert", "删除成功");
		}else{
			request.setAttribute("alert", "对象不存在或已被删除");
		}
		return "companyListPage";
	}
	
	/**
	 * 单位联系人列表
	 * @return
	 * @throws Exception 
	 */
	public String companyMemberList() throws Exception{
		//request
		HttpServletRequest request=this.getRequest();
		//参数
		//单位ID 
		String comId=request.getParameter("comId");
		String hql="from TbDonationCompanyMember a where comId="+comId;
		List list=this.getCommonBo().findHQL(hql);
		request.setAttribute("list", list);
		return "companyMemberListPage";
	}
	
	/**
	 * 跳转到编辑单位人员页面
	 * @return
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public String toMember() throws NumberFormatException, Exception{
		//request
		HttpServletRequest request=this.getRequest();
		//单位ID 
		String comId=request.getParameter("comId");
		//人员id
		String memId=request.getParameter("memId");
		if(memId!=null&&!"".equals(memId)){
			TbDonationCompanyMember bean=(TbDonationCompanyMember) this.getCommonBo().get(TbDonationCompanyMember.class, new Long(memId));
			request.setAttribute("bean", bean);
			
			//得到member中档案成员的父属性
			TbAlumni alumni=bean.getTbAlumni();
			if(alumni!=null){
				String gradeFids=alumni.getGradeFid();
				if(gradeFids!=null){
					String gradeFid=gradeFids.substring(gradeFids.lastIndexOf(",")+1);
					if(gradeFid!=null){
						TbField parentField = (TbField) this.getCommonBo().get(TbField.class, new Long(gradeFid));
						request.setAttribute("parentField", parentField);
					}
				}
			}else{
				String gradeFid=bean.getGradeFid();
				if(gradeFid!=null){
					TbField parentField = (TbField) this.getCommonBo().get(TbField.class, new Long(gradeFid));
					request.setAttribute("parentField", parentField);
				}
			}
		}
		//查询所有属性类型父类
		String hql="from TbField a where a.parentId in (select fieldId from TbField b where b.code='ALUMNIGRADE')";
		List fieldList=this.getCommonBo().findHQL(hql);
		request.setAttribute("fieldList", fieldList);
		
		request.setAttribute("comId", comId);
		request.setAttribute("memId", memId);
		return "memberPage";
	}
	
	
	/**
	 * 编辑或新增单位人员
	 * @return
	 * @throws Exception
	 */
	public String saveOrUpdateMember() throws Exception{
		String json="{\"code\":\"error\",\"msg\":\"系统错误，请稍候再试\"}";
		//request
		HttpServletRequest request=this.getRequest();
		//参数
		//单位ID 
		String comId=request.getParameter("comId");
		//成员ID 
		String memId=request.getParameter("memId");
		//姓名
		String nameCn=request.getParameter("nameCn");
		//职务
		String dutyName=request.getParameter("dutyName");
		//工作联系内容
		String dutyContent=request.getParameter("dutyContent");
		//所属行业
		String industryFid=request.getParameter("industryFid");
		//办公电话
		String telephone=request.getParameter("telephone");
		//手机
		String handset=request.getParameter("handset");
		//邮箱
		String mail=request.getParameter("mail");
		//性别
		String sexCid=request.getParameter("sexCid");
		//是否校友
		String isStu=request.getParameter("isStu");
		//人物属性
		String gradeFid=request.getParameter("gradeFid");
		//备注
		String memo=request.getParameter("memo");
		
		//添加到对象
		try {
			TbDonationCompanyMember bean=null;
			if(memId!=null&&!"".equals(memId)){
				bean=(TbDonationCompanyMember) this.getCommonBo().get(TbDonationCompanyMember.class, new Long(memId));
				request.setAttribute("bean", bean);
			}else{
				bean=new TbDonationCompanyMember();
			}
			
			if(nameCn!=null&&!"".equals(nameCn)){
				bean.setNameCn(nameCn);
			}
			if(handset!=null&&!"".equals(handset)){
				bean.setHandset(handset);
			}
			if(comId!=null&&!"".equals(comId)){
				bean.setComId(new Long(comId));
			}
			bean.setIndustryFid(industryFid);
			bean.setDutyContent(dutyContent);
			bean.setDutyName(dutyName);
			bean.setTelephone(telephone);
			bean.setMail(mail);
			bean.setSexCid(sexCid);
			bean.setIsStu(isStu);
			bean.setGradeFid(gradeFid);
			bean.setMemo(memo);
			
			//根据姓名和其它字段，对比校友库，将成员加入档案表
			TbAlumni temp=new TbAlumni();
			temp.setAlumniId(bean.getAlumniId());
			temp.setNameCn(nameCn);
			if(industryFid!=null&&!"".equals(industryFid)){
				temp.setIndustryNowFid(new Long(industryFid));
			}
			temp.setTelephoneFirst(telephone);
			temp.setHandsetFirst(handset);
			temp.setMailFirst(mail);
			temp.setSexCid(sexCid);
			if(!"0".equals(isStu)){
				temp.setTypeFid(isStu);
			}
			temp.setGradeFid(gradeFid);
			//生成校友
			temp=makeTbAlumni(temp);
			
			bean.setAlumniId(temp.getAlumniId());
			
			if(temp!=null){
				//生成工作经历
				//得到单位对象
				TbDonationCompany companyBean=(TbDonationCompany) this.getCommonBo().get(TbDonationCompany.class, bean.getComId());
				TbWork work=new TbWork();
				work.setWorkId(bean.getWorkId());
				work.setAddress(companyBean.getComAddress());
				work.setCompanyName(companyBean.getComName());
				if(companyBean.getIndustryFid()!=null&&!"".equals(companyBean.getIndustryFid())){
					work.setIndustryFid(new Long(companyBean.getIndustryFid()));
				}
				work.setDutyName(dutyName);
				work.setDutyContent(dutyContent);
				//生成工作经历
				work=makeTbWork(temp, work);
				
				bean.setWorkId(work.getWorkId());
			}
			//保存对象
			bean=(TbDonationCompanyMember) this.getCommonBo().saveOrUpdate(bean);
			
			json="{\"code\":\"success\",\"msg\":\"操作成功\",\"id\":\""+bean.getComId()+"\"}";
		}catch (Exception e) {
			// TODO: handle exception
			json="{\"code\":\"error\",\"msg\":\"异常："+e.getCause().getLocalizedMessage()+"\"}";
		}
		this.sendResponse(getResponse(), json);
		return null;
	}
	
	/**
	 * 删除单位人员
	 * @return
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	public String delCompanyMember() throws NumberFormatException, Exception{
		//得到request
		HttpServletRequest request=this.getRequest();
		//协议详细ID
		String memId=request.getParameter("memId");
		if(memId!=null&&!"".equals(memId)){
			TbDonationCompanyMember bean=(TbDonationCompanyMember) this.getCommonBo().get(TbDonationCompanyMember.class, new Long(memId));
			//删除
			this.getCommonBo().delete(bean);
			//
			request.setAttribute("alert", "删除成功");
		}else{
			request.setAttribute("alert", "对象不存在或已被删除");
		}
		return "companyMemberListPage";
	}
	
	
	/**
	 * 捐赠人列表
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public String dorlist() throws Exception{
		//得到request
		HttpServletRequest request=this.getRequest();
		//参数
		//姓名
		String nameCn=request.getParameter("nameCn");
		//办公电话
		String telephone=request.getParameter("telephone");
		//邮箱
		String mail=request.getParameter("mail");
		//手机
		String handset=request.getParameter("handset");
		//工作单位
		String company=request.getParameter("company");
		//行业
		String industryFid=request.getParameter("industryFid");
		//是否校友
		String isStu=request.getParameter("isStu");
		
		//构建查询
		String hql="from TbDonationor a where 1=1";
		List pars=this.getList();
		if(nameCn!=null&&!"".equals(nameCn)){
			hql+=" and nameCn like ?";
			pars.add("%"+nameCn+"%");
		}
		if(telephone!=null&&!"".equals(telephone)){
			hql+=" and (tbAlumni.telephoneFirst like ? or telephone like ?)";
			pars.add("%"+telephone+"%");
			pars.add("%"+telephone+"%");
		}
		if(mail!=null&&!"".equals(mail)){
			hql+=" and (tbAlumni.mailFirst like ? or mail like ?)";
			pars.add("%"+mail+"%");
			pars.add("%"+mail+"%");
		}
		if(handset!=null&&!"".equals(handset)){
			hql+=" and (tbAlumni.handsetFirst like ? or handset like ?)";
			pars.add("%"+handset+"%");
			pars.add("%"+handset+"%");
		}
		if(company!=null&&!"".equals(company)){
			hql+=" and (tbWork.company like ? or company like ?)";
			pars.add("%"+company+"%");
			pars.add("%"+company+"%");
		}
		if(industryFid!=null&&!"".equals(industryFid)){
			hql+=" and (tbWork.industryFid = ? or industryFid = ?)";
			pars.add(new Long(industryFid));
			pars.add(industryFid);
		}
		if(isStu!=null&&!"".equals(isStu)){
			if("0".equals(isStu)){
				hql+=" and isStu = ?";
				pars.add(isStu);
			}else{
				hql+=" and (instr(','||tbAlumni.typeFid||',',?)!=0 or isStu=?)";
				pars.add(","+isStu+",");
				pars.add(isStu);
			}
		}
		hql+=" order by getpinyin(a.nameCn) asc";
		List dorList=this.getCommonBo().findHQLPage(hql,pars);
		request.setAttribute("dorList", dorList);
		
		return "donationorListPage";
	}
	
	/**
	 * 跳转到编辑捐赠人页面
	 * @return
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public String toDorAdd() throws NumberFormatException, Exception{
		//request
		HttpServletRequest request=this.getRequest();
		//人员id
		String memId=request.getParameter("memId");
		if(memId!=null&&!"".equals(memId)){
			TbDonationor bean=(TbDonationor) this.getCommonBo().get(TbDonationor.class, new Long(memId));
			request.setAttribute("bean", bean);
			
			//得到member中档案成员的父属性
			TbAlumni alumni=bean.getTbAlumni();
			if(alumni!=null){
				String gradeFids=alumni.getGradeFid();
				if(gradeFids!=null){
					String gradeFid=gradeFids.substring(gradeFids.lastIndexOf(",")+1);
					if(gradeFid!=null){
						TbField parentField = (TbField) this.getCommonBo().get(TbField.class, new Long(gradeFid));
						request.setAttribute("parentField", parentField);
					}
				}
			}else{
				String gradeFid=bean.getGradeFid();
				if(gradeFid!=null){
					TbField parentField = (TbField) this.getCommonBo().get(TbField.class, new Long(gradeFid));
					request.setAttribute("parentField", parentField);
				}
			}
		}
		//查询所有属性类型父类
		String hql="from TbField a where a.parentId in (select fieldId from TbField b where b.code='ALUMNIGRADE')";
		List fieldList=this.getCommonBo().findHQL(hql);
		request.setAttribute("fieldList", fieldList);
		
		request.setAttribute("memId", memId);
		return "donationorAddPage";
	}
	
	/**
	 * 编辑或新增单位人员
	 * @return
	 * @throws Exception
	 */
	public String saveOrUpdateDor() throws Exception{
		String json="{\"code\":\"error\",\"msg\":\"系统错误，请稍候再试\"}";
		//request
		HttpServletRequest request=this.getRequest();
		//参数
		//成员ID 
		String memId=request.getParameter("memId");
		//姓名
		String nameCn=request.getParameter("nameCn");
		//职务
		String dutyName=request.getParameter("dutyName");
		//工作单位
		String company=request.getParameter("company");
		//所属行业
		String industryFid=request.getParameter("industryFid");
		//办公电话
		String telephone=request.getParameter("telephone");
		//手机
		String handset=request.getParameter("handset");
		//邮箱
		String mail=request.getParameter("mail");
		//性别
		String sexCid=request.getParameter("sexCid");
		//是否校友
		String isStu=request.getParameter("isStu");
		//人物属性
		String gradeFid=request.getParameter("gradeFid");
		//备注
		String memo=request.getParameter("memo");
		
		//添加到对象
		try {
			TbDonationor bean=null;
			if(memId!=null&&!"".equals(memId)){
				bean=(TbDonationor) this.getCommonBo().get(TbDonationor.class, new Long(memId));
				request.setAttribute("bean", bean);
			}else{
				bean=new TbDonationor();
			}
			
			if(nameCn!=null&&!"".equals(nameCn)){
				bean.setNameCn(nameCn);
			}
			if(handset!=null&&!"".equals(handset)){
				bean.setHandset(handset);
			}
			bean.setIndustryFid(industryFid);
			bean.setCompany(company);
			bean.setDutyName(dutyName);
			bean.setTelephone(telephone);
			bean.setMail(mail);
			bean.setSexCid(sexCid);
			bean.setIsStu(isStu);
			bean.setGradeFid(gradeFid);
			bean.setMemo(memo);
			
			//根据姓名和其它字段，对比校友库，将成员加入档案表
			TbAlumni temp=new TbAlumni();
			temp.setAlumniId(bean.getAlumniId());
			temp.setNameCn(nameCn);
			if(industryFid!=null&&!"".equals(industryFid)){
				temp.setIndustryNowFid(new Long(industryFid));
			}
			temp.setTelephoneFirst(telephone);
			temp.setHandsetFirst(handset);
			temp.setMailFirst(mail);
			temp.setSexCid(sexCid);
			if(!"0".equals(isStu)){
				temp.setTypeFid(isStu);
			}
			temp.setGradeFid(gradeFid);
			//生成校友
			temp=makeTbAlumni(temp);
			
			bean.setAlumniId(temp.getAlumniId());
			
			if(temp!=null){
				//生成工作经历
				//得到单位对象
				TbWork work=new TbWork();
				work.setCompanyName(company);
				if(industryFid!=null&&!"".equals(industryFid)){
					work.setIndustryFid(new Long(industryFid));
				}
				work.setDutyName(dutyName);
				//生成工作经历
				work=makeTbWork(temp, work);
				
				bean.setWorkId(work.getWorkId());
			}
			//保存对象
			bean=(TbDonationor) this.getCommonBo().saveOrUpdate(bean);
			
			json="{\"code\":\"success\",\"msg\":\"操作成功\",\"id\":\""+bean.getMemId()+"\"}";
		}catch (Exception e) {
			// TODO: handle exception
			json="{\"code\":\"error\",\"msg\":\"异常："+e.getCause().getLocalizedMessage()+"\"}";
		}
		this.sendResponse(getResponse(), json);
		return null;
	}
	
	/**
	 * 删除捐赠人
	 * @return
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	public String delDonationor() throws NumberFormatException, Exception{
		//得到request
		HttpServletRequest request=this.getRequest();
		//协议详细ID
		String memId=request.getParameter("memId");
		if(memId!=null&&!"".equals(memId)){
			TbDonationor bean=(TbDonationor) this.getCommonBo().get(TbDonationor.class, new Long(memId));
			//删除
			this.getCommonBo().delete(bean);
			//
			request.setAttribute("alert", "删除成功");
		}else{
			request.setAttribute("alert", "对象不存在或已被删除");
		}
		return "donationorListPage";
	}
	
	/**
	 * 接受单位列表 
	 * @return
	 * @throws Exception
	 */
	public String acceptList() throws Exception{
		//得到request
		HttpServletRequest request=this.getRequest();
		//参数
		//接受单位
		String comName=request.getParameter("comName");
		//hql
		String hql="from TbAcceptCompany a where 1=1";
		List pars=this.getList();
		if(comName!=null&&!"".equals(comName)){
			hql+=" and comName like ?";
			pars.add("%"+comName+"%");
		}
		hql+=" order by getpinyin(a.comName) asc";
		List acceptList=this.getCommonBo().findHQLPage(hql,pars);
		request.setAttribute("acceptList", acceptList);
		
		return "acceptCompanyListPage";
	}
	
	/**
	 * 跳转到接受单位新增或编辑页面
	 * @return
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public String toAcceptAdd() throws NumberFormatException, Exception{
		//得到request
		HttpServletRequest request=this.getRequest();
		//单位ID
		String comId=request.getParameter("comId");
		if(comId!=null&&!"".equals(comId)){
			TbAcceptCompany bean=(TbAcceptCompany) this.getCommonBo().get(TbAcceptCompany.class, new Long(comId));
			request.setAttribute("bean", bean);
		}
		request.setAttribute("comId", comId);
		
		return "acceptCompanyAddOrUpdatePage";
	}
	
	
	/**
	 * 编辑或新增接受单位
	 * @return
	 */
	public String saveOrUpdateAccept(){
		//得到request
		HttpServletRequest request=this.getRequest();
		//单位ID
		String comId=request.getParameter("comId");
		//单位名称
		String comName=request.getParameter("comName");
		//账户名称
		String accountName=request.getParameter("accountName");
		//账户
		String accountNum=request.getParameter("accountNum");
		//单位负责人
		String responseName=request.getParameter("responseName");
		//联系电话
		String responseTel=request.getParameter("responseTel");
		//联系手机
		String responseHandset=request.getParameter("responseHandset");
		//联系邮箱
		String responseMail=request.getParameter("responseMail");
		//单位联系人
		String relationName=request.getParameter("relationName");
		//联系电话
		String relationTel=request.getParameter("relationTel");
		//联系手机
		String relationHandset=request.getParameter("relationHandset");
		//联系邮箱
		String relationMail=request.getParameter("relationMail");
		//备注
		String memo=request.getParameter("memo");
		
		try {
			TbAcceptCompany bean=null;
			if(comId!=null&&!"".equals(comId)){
				bean=(TbAcceptCompany) this.getCommonBo().get(TbAcceptCompany.class, new Long(comId));
			}else{
				bean=new TbAcceptCompany();
			}
			if(comName!=null&&!"".equals(comName)){
				bean.setComName(comName);
			}
			bean.setAccountName(accountName);
			bean.setAccountNum(accountNum);
			bean.setMemo(memo);
			bean.setRelationHandset(relationHandset);
			bean.setRelationMail(relationMail);
			bean.setRelationName(relationName);
			bean.setRelationTel(relationTel);
			bean.setResponseHandset(responseHandset);
			bean.setResponseMail(responseMail);
			bean.setResponseName(responseName);
			bean.setResponseTel(responseTel);
			
			//更新或新增
			this.getCommonBo().saveOrUpdate(bean);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "successPage";
	}
	
	/**
	 * 删除接受单位
	 * @return
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	public String delAccept() throws NumberFormatException, Exception{
		//得到request
		HttpServletRequest request=this.getRequest();
		//接受单位ID
		String comId=request.getParameter("comId");
		if(comId!=null&&!"".equals(comId)){
			TbAcceptCompany bean=(TbAcceptCompany) this.getCommonBo().get(TbAcceptCompany.class, new Long(comId));
			String hql = "from TbProPay a where a.tbAcceptCompany.comId="+bean.getComId();
			List list = this.getCommonBo().findHQL(hql);
			if(null!=list&&!list.isEmpty()){
				for (int i = 0; i < list.size(); i++) {
					TbProPay temp = (TbProPay)list.get(i);
					//删除
					this.getCommonBo().delete(temp);
				}
			}
			//删除
			this.getCommonBo().delete(bean);
			//
			request.setAttribute("alert", "删除成功");
		}else{
			request.setAttribute("alert", "对象不存在或已被删除");
		}
		return "acceptCompanyListPage";
	}
	
	/**
	 * 生成档案信息
	 * @param temp
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public TbAlumni makeTbAlumni(TbAlumni temp) throws Exception{
		if(temp!=null){
			String nameCn=temp.getNameCn();
			Long industryNowFid=temp.getIndustryNowFid();
			String telephoneFirst=temp.getTelephoneFirst();
			String handsetFirst=temp.getHandsetFirst();
			String mailFirst=temp.getMailFirst();
			String sexCid=temp.getSexCid();
			String gradeFid=temp.getGradeFid();
			String typeFid=temp.getTypeFid();
			String selfMemo="来自基金项目管理系统";
			
			//根据姓名和邮箱，或者姓名和手机或者姓名和电话查重
			String hql="from TbAlumni a where a.nameCn=? and (mailFirst=? or telephoneFirst=? or handsetFirst=?)";
			List pars=this.getList();
			pars.add(nameCn);
			pars.add(mailFirst);
			pars.add(telephoneFirst);
			pars.add(handsetFirst);
			List list = this.getCommonBo().findHQL(hql,pars);
			if((list!=null&&!list.isEmpty())||temp.getAlumniId()!=null){
				boolean isUpdate=false;
				//存在则更新
				TbAlumni bean=(TbAlumni) list.get(0);
				if(temp.getAlumniId()!=null){
					bean=(TbAlumni) this.getCommonBo().get(TbAlumni.class, temp.getAlumniId());
				}
				//姓名不更新
//				if(nameCn!=null&&!"".equals(nameCn)){
//					bean.setNameCn(nameCn);
//				}
				if(industryNowFid!=null&&!"".equals(industryNowFid)){
					bean.setIndustryNowFid(industryNowFid);
					isUpdate=true;
				}
				if(telephoneFirst!=null&&!"".equals(telephoneFirst)){
					bean.setTelephoneFirst(telephoneFirst);
					isUpdate=true;
				}
				if(handsetFirst!=null&&!"".equals(handsetFirst)){
					bean.setHandsetFirst(handsetFirst);
					isUpdate=true;
				}
				if(telephoneFirst!=null&&!"".equals(telephoneFirst)){
					bean.setTelephoneFirst(telephoneFirst);
					isUpdate=true;
				}
				if(mailFirst!=null&&!"".equals(mailFirst)){
					bean.setMailFirst(mailFirst);
					isUpdate=true;
				}
				if(sexCid!=null&&!"".equals(sexCid)){
					bean.setSexCid(sexCid);
					isUpdate=true;
				}
				if(gradeFid!=null&&!"".equals(gradeFid)){
					if(bean.getGradeFid()!=null&&!"".equals(bean.getGradeFid())){
						if((","+bean.getGradeFid()+",").indexOf((","+gradeFid+","))==-1){
							bean.setGradeFid(","+gradeFid);
						}
					}else{
						bean.setGradeFid(gradeFid);
					}
					isUpdate=true;
				}
				if(typeFid!=null&&!"".equals(typeFid)){
					if(bean.getTypeFid()!=null&&!"".equals(bean.getTypeFid())){
						if((","+bean.getTypeFid()+",").indexOf((","+typeFid+","))==-1){
							bean.setTypeFid(","+typeFid);
						}
					}else{
						bean.setTypeFid(typeFid);
					}
					isUpdate=true;
				}
				if(isUpdate){
					bean.setSelfMemo(selfMemo);
					temp=(TbAlumni) this.getCommonBo().update(bean);
				}
			}
//			else{
//				//不存在则新增
//				TbAlumni bean=new TbAlumni();
//				bean.setIndustryNowFid(industryNowFid);
//				bean.setNameCn(nameCn);
//				bean.setHandsetFirst(handsetFirst);
//				bean.setTelephoneFirst(telephoneFirst);
//				bean.setMailFirst(mailFirst);
//				bean.setSexCid(sexCid);
//				bean.setGradeFid(gradeFid);
//				bean.setTypeFid(typeFid);
//				bean.setIsLive("1");
//				bean.setSelfMemo(selfMemo);
//				temp=(TbAlumni) this.getCommonBo().save(bean);
//			}
		}
		return temp;
	}

	/**
	 * 生成工作经历
	 * @param temp
	 * @param work
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public TbWork makeTbWork(TbAlumni temp,TbWork work) throws Exception{
		if(temp!=null){
			String companyName=work.getCompanyName();
			String dutyName=work.getDutyName();
			String dutyContent=work.getDutyContent();
			//行业
			Long industryNowFid=temp.getIndustryNowFid();
			String address=work.getAddress();
			String selfMemo="来自基金项目管理系统";
			//档案ID
			Long alumniId=temp.getAlumniId();
			if(alumniId==null){
				return work;
			}
			
			//根据档案id和工作单位查询是否存在工作经历
			String hql="from TbWork a where a.tbAlumni.alumniId=? and a.companyName=?";
			List pars=this.getList();
			pars.add(alumniId);
			pars.add(companyName);
			List list=this.getCommonBo().findHQL(hql,pars);
			
			if((list!=null&&!list.isEmpty())||work.getWorkId()!=null){
				boolean isUpdate=false;
				//存在工作经历，更新
				TbWork bean=(TbWork) list.get(0);
				if(work.getWorkId()!=null){
					bean=(TbWork) this.getCommonBo().get(TbWork.class, work.getWorkId());
				}
				//工作单位不更新
//				if(companyName!=null&&!"".equals(companyName)){
//					bean.setCompanyName(companyName);
//				}
				if(dutyName!=null&&!"".equals(dutyName)){
					bean.setDutyName(dutyName);
					isUpdate=true;
				}
				if(dutyContent!=null&&!"".equals(dutyContent)){
					bean.setDutyContent(dutyContent);
					isUpdate=true;
				}
				if(industryNowFid!=null&&!"".equals(industryNowFid)){
					bean.setIndustryFid(industryNowFid);
					isUpdate=true;
				}
				if(address!=null&&!"".equals(address)){
					bean.setAddress(address);
					isUpdate=true;
				}
				if(isUpdate){
					bean.setSourceInfo(selfMemo);
					work=(TbWork) this.getCommonBo().update(bean);
				}
			}
//			else{
//				//新增
//				//判断isMain,isNew
//				Object obj=this.getCommonBo().getSQLUnique("select max(a.is_new) from tb_work a where a.alumni_id="+alumniId);
//				String isMain="1",isNew="1";
//				if(obj!=null){
//					if("1".equals(obj.toString())){
//						isMain="2";
//						isNew="2";
//					}else{
//						isMain="2";
//						isNew=(Integer.parseInt(obj.toString())+1)+"";
//					}
//				}else{
//					isMain="1";
//					isNew="1";
//				}
//				TbWork bean=new TbWork();
//				bean.setAddress(address);
//				bean.setCompanyName(companyName);
//				bean.setTbAlumni(temp);
//				bean.setIsMain(isMain);
//				bean.setIsNew(isNew);
//				bean.setDutyName(dutyName);
//				bean.setDutyContent(dutyContent);
//				bean.setSourceInfo(selfMemo);
//				if(industryNowFid!=null&&!"".equals(industryNowFid)){
//					bean.setIndustryFid(industryNowFid);
//				}
//				work=(TbWork) this.getCommonBo().save(bean);
//			}
		}
		return work;
	}
	
	
}
