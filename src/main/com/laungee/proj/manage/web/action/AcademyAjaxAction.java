package com.laungee.proj.manage.web.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbAcademy;
import com.laungee.proj.common.model.VwStudentmemberInfo;
import com.laungee.proj.common.util.StringUtil;

public class AcademyAjaxAction extends BaseAction {
	// ��������
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		// ������
		String id = request.getParameter("id");
		// ��ʶ
		String level=request.getParameter("f");
		// ������ѯ
		StringBuffer hql=new StringBuffer();
		hql.append("from TbAcademy a where ");
		// �������
		if("1".equals(level)){
			hql.append("a.tbAcademy.nodeName='"+id+"' and ");
		}
		// ѧԺ
		else if("2".equals(level)){
			hql.append("a.parentId="+id+" and ");
		}
		// ϵ
		else if("3".equals(level) || "4".equals(level)){
			hql.append("a.tbAcademy.tbAcademy.nodeId="+id+" and a.tbAcademy.numLevel="+level+"  and ");
		}
		// ��ѯ
		List list=getCommonBo().findHQL(StringUtil.toQuery(hql.toString()));
		String ajax = this.toXml(list, TbAcademy.class,"nodeId","nodeName");
		this.sendResponse(getResponse(), ajax);
		// ����
		return null;
	}
	
	public String academyOfStudy() throws Exception{
		HttpServletRequest request = this.getRequest();
		//�õ�����
		String namecn=request.getParameter("namecn");
		//��ѧ��
		String rxn=request.getParameter("rxn");
		String json="false";
		if(namecn!=null&&!"".equals(namecn)&&rxn!=null&&!"".equals(rxn)){
			namecn=namecn.trim();
			rxn=rxn.trim();
			//��ѯ��ҵ������
			String hql="from VwStudentmemberInfo a where a.studentName='"+namecn+"' and a.createTime='"+rxn+"'";
//			String sql="select * from vw_studentmember_info a where a.student_name='"+namecn+"' and a.create_time='"+rxn+"'";
			try {
				List list=this.getCommonBo().findHQL(hql);
				if(list!=null&&list.size()!=0){
					json="{'count':'"+list.size()+"','alldata':[";
					for (int i = 0; i < list.size(); i++) {
						VwStudentmemberInfo vsi=(VwStudentmemberInfo) list.get(i);
						if(vsi!=null){
							String name=vsi.getStudentName()==null?"":vsi.getStudentName();
							String year=vsi.getCreateTime()==null?"":vsi.getCreateTime();
							String acadName=vsi.getAcademyName()==null?"":vsi.getAcademyName();
							String departName=vsi.getDepartmentName()==null?"":vsi.getDepartmentName();
							String majorName=vsi.getMajorName()==null?"":vsi.getMajorName();
							String className=vsi.getClassName()==null?"":vsi.getClassName();
							String ssxy=vsi.getSsxy()==null?"":vsi.getSsxy();
							String organName=vsi.getOrganname()==null?"":vsi.getOrganname();
							String studentCode=vsi.getStudentCode()==null?"":vsi.getStudentCode();
							String xueli=vsi.getXueli()==null?"":vsi.getXueli();
							String types=vsi.getStudentType()==null?"":vsi.getStudentType();
							json+="{'name':'"+name+"','createTime':'"+year+"','acadId':'"+vsi.getAcademyId()+"','acadName':'"+acadName
							+"','departId':'"+vsi.getDepartmentId()+"','departName':'"+departName
							+"','majorId':'"+vsi.getMajorId()+"','majorName':'"+majorName
							+"','classId':'"+vsi.getClassId()+"','className':'"+className
							+"','ssxy':'"+ssxy+"','oragnname':'"+organName
							+"','studentCode':'"+studentCode+"','xueli':'"+xueli
							+"','studentType':'"+types+"','sourceInfo':'��ҵ������ƥ��'" +
									",'confid':'7746'}";
							if(list.size()-1!=i){
								json+=",";
							}
						}
					}
					json+="]}";
				}
			} catch (Exception e) {
			}
		}
		this.sendResponse(getResponse(), json);
		return null;
	}
}
