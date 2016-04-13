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
 * �����������
 * @author hexiang
 *
 */
public class FoundationObjectAction extends BaseAction{
	
	/**
	 * ������λ�б�
	 * @return
	 * @throws Exception 
	 */
	public String companyList() throws Exception{
		//request
		HttpServletRequest request=this.getRequest();
		//����
		//��λ����
		String comName=request.getParameter("comName");
		//��Ҫҵ��
		String comBusi=request.getParameter("comBusi");
		//�Ǽ�ע������
		String comType=request.getParameter("comType");
		//������ҵ
		String industryFid=request.getParameter("industryFid");
		//��ϸ��ַ
		String comAddress=request.getParameter("comAddress");
		//��λ������
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
		//��ѯ���
		List companyList=this.getCommonBo().findHQLPage(hql,pars);
		//�����ҳ��
		request.setAttribute("companyList", companyList);
		return "companyListPage";
	}
	
	
	/**
	 * ��ת��������༭������λҳ��
	 * @return
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public String toCompanyModifyPage() throws NumberFormatException, Exception{
		//request
		HttpServletRequest request=this.getRequest();
		//����
		//��λID 
		String comId=request.getParameter("comId");
		if(comId!=null&&!"".equals(comId)){
			TbDonationCompany bean=(TbDonationCompany) this.getCommonBo().get(TbDonationCompany.class, new Long(comId));
			request.setAttribute("bean", bean);
		}
		request.setAttribute("comId", comId);
		return "companyModifyPage";
	}
	
	/**
	 * ������༭������λ
	 * @return
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public String saveOrUpdateCom() throws NumberFormatException, Exception{
		String json="{\"code\":\"error\",\"msg\":\"ϵͳ�������Ժ�����\"}";
		//request
		HttpServletRequest request=this.getRequest();
		//����
		//��λID 
		String comId=request.getParameter("comId");
		//��λ����
		String comName=request.getParameter("comName");
		//��Ҫҵ��
		String comBusi=request.getParameter("comBusi");
		//�Ǽ�ע������
		String comType=request.getParameter("comType");
		//������ҵ
		String industryFid=request.getParameter("industryFid");
		//��ϸ��ַ
		String comAddress=request.getParameter("comAddress");
		//��λ������
		String responseName=request.getParameter("responseName");
		
		//��ӵ�����
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
			json="{\"code\":\"success\",\"msg\":\"�����ɹ�\",\"id\":\""+bean.getComId()+"\"}";
		}catch (Exception e) {
			// TODO: handle exception
			json="{\"code\":\"error\",\"msg\":\"�쳣��"+e.getCause().getLocalizedMessage()+"\"}";
		}
		this.sendResponse(getResponse(), json);
		return null;
	}
	
	/**
	 * ɾ��������λ
	 * @return
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	public String delCompany() throws NumberFormatException, Exception{
		//�õ�request
		HttpServletRequest request=this.getRequest();
		//Э����ϸID
		String comId=request.getParameter("comId");
		if(comId!=null&&!"".equals(comId)){
			TbDonationCompany bean=(TbDonationCompany) this.getCommonBo().get(TbDonationCompany.class, new Long(comId));
			if(bean!=null){
				//ɾ�����е�λ��Ա
				String hql="delete from TbDonationCompanyMember a where a.comId="+bean.getComId();
				this.getCommonBo().executeHql(hql);
				//ɾ��
				this.getCommonBo().delete(bean);
			}
			//
			request.setAttribute("alert", "ɾ���ɹ�");
		}else{
			request.setAttribute("alert", "���󲻴��ڻ��ѱ�ɾ��");
		}
		return "companyListPage";
	}
	
	/**
	 * ��λ��ϵ���б�
	 * @return
	 * @throws Exception 
	 */
	public String companyMemberList() throws Exception{
		//request
		HttpServletRequest request=this.getRequest();
		//����
		//��λID 
		String comId=request.getParameter("comId");
		String hql="from TbDonationCompanyMember a where comId="+comId;
		List list=this.getCommonBo().findHQL(hql);
		request.setAttribute("list", list);
		return "companyMemberListPage";
	}
	
	/**
	 * ��ת���༭��λ��Աҳ��
	 * @return
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public String toMember() throws NumberFormatException, Exception{
		//request
		HttpServletRequest request=this.getRequest();
		//��λID 
		String comId=request.getParameter("comId");
		//��Աid
		String memId=request.getParameter("memId");
		if(memId!=null&&!"".equals(memId)){
			TbDonationCompanyMember bean=(TbDonationCompanyMember) this.getCommonBo().get(TbDonationCompanyMember.class, new Long(memId));
			request.setAttribute("bean", bean);
			
			//�õ�member�е�����Ա�ĸ�����
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
		//��ѯ�����������͸���
		String hql="from TbField a where a.parentId in (select fieldId from TbField b where b.code='ALUMNIGRADE')";
		List fieldList=this.getCommonBo().findHQL(hql);
		request.setAttribute("fieldList", fieldList);
		
		request.setAttribute("comId", comId);
		request.setAttribute("memId", memId);
		return "memberPage";
	}
	
	
	/**
	 * �༭��������λ��Ա
	 * @return
	 * @throws Exception
	 */
	public String saveOrUpdateMember() throws Exception{
		String json="{\"code\":\"error\",\"msg\":\"ϵͳ�������Ժ�����\"}";
		//request
		HttpServletRequest request=this.getRequest();
		//����
		//��λID 
		String comId=request.getParameter("comId");
		//��ԱID 
		String memId=request.getParameter("memId");
		//����
		String nameCn=request.getParameter("nameCn");
		//ְ��
		String dutyName=request.getParameter("dutyName");
		//������ϵ����
		String dutyContent=request.getParameter("dutyContent");
		//������ҵ
		String industryFid=request.getParameter("industryFid");
		//�칫�绰
		String telephone=request.getParameter("telephone");
		//�ֻ�
		String handset=request.getParameter("handset");
		//����
		String mail=request.getParameter("mail");
		//�Ա�
		String sexCid=request.getParameter("sexCid");
		//�Ƿ�У��
		String isStu=request.getParameter("isStu");
		//��������
		String gradeFid=request.getParameter("gradeFid");
		//��ע
		String memo=request.getParameter("memo");
		
		//��ӵ�����
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
			
			//���������������ֶΣ��Ա�У�ѿ⣬����Ա���뵵����
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
			//����У��
			temp=makeTbAlumni(temp);
			
			bean.setAlumniId(temp.getAlumniId());
			
			if(temp!=null){
				//���ɹ�������
				//�õ���λ����
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
				//���ɹ�������
				work=makeTbWork(temp, work);
				
				bean.setWorkId(work.getWorkId());
			}
			//�������
			bean=(TbDonationCompanyMember) this.getCommonBo().saveOrUpdate(bean);
			
			json="{\"code\":\"success\",\"msg\":\"�����ɹ�\",\"id\":\""+bean.getComId()+"\"}";
		}catch (Exception e) {
			// TODO: handle exception
			json="{\"code\":\"error\",\"msg\":\"�쳣��"+e.getCause().getLocalizedMessage()+"\"}";
		}
		this.sendResponse(getResponse(), json);
		return null;
	}
	
	/**
	 * ɾ����λ��Ա
	 * @return
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	public String delCompanyMember() throws NumberFormatException, Exception{
		//�õ�request
		HttpServletRequest request=this.getRequest();
		//Э����ϸID
		String memId=request.getParameter("memId");
		if(memId!=null&&!"".equals(memId)){
			TbDonationCompanyMember bean=(TbDonationCompanyMember) this.getCommonBo().get(TbDonationCompanyMember.class, new Long(memId));
			//ɾ��
			this.getCommonBo().delete(bean);
			//
			request.setAttribute("alert", "ɾ���ɹ�");
		}else{
			request.setAttribute("alert", "���󲻴��ڻ��ѱ�ɾ��");
		}
		return "companyMemberListPage";
	}
	
	
	/**
	 * �������б�
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public String dorlist() throws Exception{
		//�õ�request
		HttpServletRequest request=this.getRequest();
		//����
		//����
		String nameCn=request.getParameter("nameCn");
		//�칫�绰
		String telephone=request.getParameter("telephone");
		//����
		String mail=request.getParameter("mail");
		//�ֻ�
		String handset=request.getParameter("handset");
		//������λ
		String company=request.getParameter("company");
		//��ҵ
		String industryFid=request.getParameter("industryFid");
		//�Ƿ�У��
		String isStu=request.getParameter("isStu");
		
		//������ѯ
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
	 * ��ת���༭������ҳ��
	 * @return
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public String toDorAdd() throws NumberFormatException, Exception{
		//request
		HttpServletRequest request=this.getRequest();
		//��Աid
		String memId=request.getParameter("memId");
		if(memId!=null&&!"".equals(memId)){
			TbDonationor bean=(TbDonationor) this.getCommonBo().get(TbDonationor.class, new Long(memId));
			request.setAttribute("bean", bean);
			
			//�õ�member�е�����Ա�ĸ�����
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
		//��ѯ�����������͸���
		String hql="from TbField a where a.parentId in (select fieldId from TbField b where b.code='ALUMNIGRADE')";
		List fieldList=this.getCommonBo().findHQL(hql);
		request.setAttribute("fieldList", fieldList);
		
		request.setAttribute("memId", memId);
		return "donationorAddPage";
	}
	
	/**
	 * �༭��������λ��Ա
	 * @return
	 * @throws Exception
	 */
	public String saveOrUpdateDor() throws Exception{
		String json="{\"code\":\"error\",\"msg\":\"ϵͳ�������Ժ�����\"}";
		//request
		HttpServletRequest request=this.getRequest();
		//����
		//��ԱID 
		String memId=request.getParameter("memId");
		//����
		String nameCn=request.getParameter("nameCn");
		//ְ��
		String dutyName=request.getParameter("dutyName");
		//������λ
		String company=request.getParameter("company");
		//������ҵ
		String industryFid=request.getParameter("industryFid");
		//�칫�绰
		String telephone=request.getParameter("telephone");
		//�ֻ�
		String handset=request.getParameter("handset");
		//����
		String mail=request.getParameter("mail");
		//�Ա�
		String sexCid=request.getParameter("sexCid");
		//�Ƿ�У��
		String isStu=request.getParameter("isStu");
		//��������
		String gradeFid=request.getParameter("gradeFid");
		//��ע
		String memo=request.getParameter("memo");
		
		//��ӵ�����
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
			
			//���������������ֶΣ��Ա�У�ѿ⣬����Ա���뵵����
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
			//����У��
			temp=makeTbAlumni(temp);
			
			bean.setAlumniId(temp.getAlumniId());
			
			if(temp!=null){
				//���ɹ�������
				//�õ���λ����
				TbWork work=new TbWork();
				work.setCompanyName(company);
				if(industryFid!=null&&!"".equals(industryFid)){
					work.setIndustryFid(new Long(industryFid));
				}
				work.setDutyName(dutyName);
				//���ɹ�������
				work=makeTbWork(temp, work);
				
				bean.setWorkId(work.getWorkId());
			}
			//�������
			bean=(TbDonationor) this.getCommonBo().saveOrUpdate(bean);
			
			json="{\"code\":\"success\",\"msg\":\"�����ɹ�\",\"id\":\""+bean.getMemId()+"\"}";
		}catch (Exception e) {
			// TODO: handle exception
			json="{\"code\":\"error\",\"msg\":\"�쳣��"+e.getCause().getLocalizedMessage()+"\"}";
		}
		this.sendResponse(getResponse(), json);
		return null;
	}
	
	/**
	 * ɾ��������
	 * @return
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	public String delDonationor() throws NumberFormatException, Exception{
		//�õ�request
		HttpServletRequest request=this.getRequest();
		//Э����ϸID
		String memId=request.getParameter("memId");
		if(memId!=null&&!"".equals(memId)){
			TbDonationor bean=(TbDonationor) this.getCommonBo().get(TbDonationor.class, new Long(memId));
			//ɾ��
			this.getCommonBo().delete(bean);
			//
			request.setAttribute("alert", "ɾ���ɹ�");
		}else{
			request.setAttribute("alert", "���󲻴��ڻ��ѱ�ɾ��");
		}
		return "donationorListPage";
	}
	
	/**
	 * ���ܵ�λ�б� 
	 * @return
	 * @throws Exception
	 */
	public String acceptList() throws Exception{
		//�õ�request
		HttpServletRequest request=this.getRequest();
		//����
		//���ܵ�λ
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
	 * ��ת�����ܵ�λ������༭ҳ��
	 * @return
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public String toAcceptAdd() throws NumberFormatException, Exception{
		//�õ�request
		HttpServletRequest request=this.getRequest();
		//��λID
		String comId=request.getParameter("comId");
		if(comId!=null&&!"".equals(comId)){
			TbAcceptCompany bean=(TbAcceptCompany) this.getCommonBo().get(TbAcceptCompany.class, new Long(comId));
			request.setAttribute("bean", bean);
		}
		request.setAttribute("comId", comId);
		
		return "acceptCompanyAddOrUpdatePage";
	}
	
	
	/**
	 * �༭���������ܵ�λ
	 * @return
	 */
	public String saveOrUpdateAccept(){
		//�õ�request
		HttpServletRequest request=this.getRequest();
		//��λID
		String comId=request.getParameter("comId");
		//��λ����
		String comName=request.getParameter("comName");
		//�˻�����
		String accountName=request.getParameter("accountName");
		//�˻�
		String accountNum=request.getParameter("accountNum");
		//��λ������
		String responseName=request.getParameter("responseName");
		//��ϵ�绰
		String responseTel=request.getParameter("responseTel");
		//��ϵ�ֻ�
		String responseHandset=request.getParameter("responseHandset");
		//��ϵ����
		String responseMail=request.getParameter("responseMail");
		//��λ��ϵ��
		String relationName=request.getParameter("relationName");
		//��ϵ�绰
		String relationTel=request.getParameter("relationTel");
		//��ϵ�ֻ�
		String relationHandset=request.getParameter("relationHandset");
		//��ϵ����
		String relationMail=request.getParameter("relationMail");
		//��ע
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
			
			//���»�����
			this.getCommonBo().saveOrUpdate(bean);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "successPage";
	}
	
	/**
	 * ɾ�����ܵ�λ
	 * @return
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	public String delAccept() throws NumberFormatException, Exception{
		//�õ�request
		HttpServletRequest request=this.getRequest();
		//���ܵ�λID
		String comId=request.getParameter("comId");
		if(comId!=null&&!"".equals(comId)){
			TbAcceptCompany bean=(TbAcceptCompany) this.getCommonBo().get(TbAcceptCompany.class, new Long(comId));
			String hql = "from TbProPay a where a.tbAcceptCompany.comId="+bean.getComId();
			List list = this.getCommonBo().findHQL(hql);
			if(null!=list&&!list.isEmpty()){
				for (int i = 0; i < list.size(); i++) {
					TbProPay temp = (TbProPay)list.get(i);
					//ɾ��
					this.getCommonBo().delete(temp);
				}
			}
			//ɾ��
			this.getCommonBo().delete(bean);
			//
			request.setAttribute("alert", "ɾ���ɹ�");
		}else{
			request.setAttribute("alert", "���󲻴��ڻ��ѱ�ɾ��");
		}
		return "acceptCompanyListPage";
	}
	
	/**
	 * ���ɵ�����Ϣ
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
			String selfMemo="���Ի�����Ŀ����ϵͳ";
			
			//�������������䣬�����������ֻ����������͵绰����
			String hql="from TbAlumni a where a.nameCn=? and (mailFirst=? or telephoneFirst=? or handsetFirst=?)";
			List pars=this.getList();
			pars.add(nameCn);
			pars.add(mailFirst);
			pars.add(telephoneFirst);
			pars.add(handsetFirst);
			List list = this.getCommonBo().findHQL(hql,pars);
			if((list!=null&&!list.isEmpty())||temp.getAlumniId()!=null){
				boolean isUpdate=false;
				//���������
				TbAlumni bean=(TbAlumni) list.get(0);
				if(temp.getAlumniId()!=null){
					bean=(TbAlumni) this.getCommonBo().get(TbAlumni.class, temp.getAlumniId());
				}
				//����������
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
//				//������������
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
	 * ���ɹ�������
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
			//��ҵ
			Long industryNowFid=temp.getIndustryNowFid();
			String address=work.getAddress();
			String selfMemo="���Ի�����Ŀ����ϵͳ";
			//����ID
			Long alumniId=temp.getAlumniId();
			if(alumniId==null){
				return work;
			}
			
			//���ݵ���id�͹�����λ��ѯ�Ƿ���ڹ�������
			String hql="from TbWork a where a.tbAlumni.alumniId=? and a.companyName=?";
			List pars=this.getList();
			pars.add(alumniId);
			pars.add(companyName);
			List list=this.getCommonBo().findHQL(hql,pars);
			
			if((list!=null&&!list.isEmpty())||work.getWorkId()!=null){
				boolean isUpdate=false;
				//���ڹ�������������
				TbWork bean=(TbWork) list.get(0);
				if(work.getWorkId()!=null){
					bean=(TbWork) this.getCommonBo().get(TbWork.class, work.getWorkId());
				}
				//������λ������
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
//				//����
//				//�ж�isMain,isNew
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
