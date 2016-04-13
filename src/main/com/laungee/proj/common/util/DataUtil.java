package com.laungee.proj.common.util;

import java.util.List;

import com.laungee.proj.common.biz.ICommonBiz;
import com.laungee.proj.common.model.TbAlumni;
import com.laungee.proj.common.model.TbStudy;

/**
 * ���ݴ�����
 * @author hexiang
 *
 */
public class DataUtil {
	/**����У�ѻ�����Ϣѧϰ����
	 * @param biz ICommonBiz��
	 * @param studyYear ��ѧ��
	 * @param academy ѧԺ
	 * @param department ϵ��
	 * @param major רҵ
	 * @param clazz �༶
	 * @param alumniId У��ID
	 * @throws Exception
	 */
	public static void updateAlumniWithStudy(ICommonBiz biz,String studyYear,String academy,String department,String major,String clazz,Long alumniId) throws Exception{
		//����У�ѻ�����Ϣѧϰ����
		String syear="";
		if(studyYear!=null&&!studyYear.equals("")){
			 syear=studyYear+";";
		}
		String sacademy="";
		if(academy!=null&&!academy.equals("")){
			sacademy=academy+";";
		}
		String sdepartement="";
		if(department!=null&&!department.equals("")){
			sdepartement=department+";";
		}
		String smajor="";
		if(major!=null&&!major.equals("")){
			smajor=major+";";
		}
		String sclass="";
		if(clazz!=null&&!clazz.equals("")){
			sclass=clazz+";";
		}
		String studyHql="from TbStudy a where a.tbAlumniSt.alumniId="+alumniId+" order by a.studyId";
		List lists=biz.findHQL(studyHql);
		TbAlumni tbAlumni=(TbAlumni)biz.get(TbAlumni.class, alumniId);
		if(null!=lists&&lists.size()>0){
			for (int m =0;m< lists.size();m++) {
				TbStudy temp=(TbStudy)lists.get(m);
				if(temp.getYearJoin()!=null&&!temp.getYearJoin().equals("")){
                    if(syear!=null&&!syear.equals("")){
                    	if(!(syear.contains(temp.getYearJoin()))){
                    		syear+=temp.getYearJoin()+";";
                    	}
                    }else{
                    	syear+=temp.getYearJoin()+";";
                    }
					
				}
				if(temp.getAcademyName()!=null&&!temp.getAcademyName().equals("")){
					if(sacademy!=null&&!sacademy.equals("")){
						if(!(sacademy.contains(temp.getAcademyName()))){
							sacademy+=temp.getAcademyName()+";";
						}
					 }else{
						 sacademy+=temp.getAcademyName()+";";
					 }
				}
				if(temp.getDepartmentName()!=null&&!temp.getDepartmentName().equals("")){
					if(sdepartement!=null&&!sdepartement.equals("")){
						if(!(sdepartement.contains(temp.getDepartmentName()))){
							sdepartement+=temp.getDepartmentName()+";";
						}
					}else{
						sdepartement+=temp.getDepartmentName()+";";
					}
				}
				if(temp.getMajorName()!=null&&!temp.getMajorName().equals("")){
					if(smajor!=null&&!smajor.equals("")){
						if(!(smajor.contains(temp.getMajorName()))){
							smajor+=temp.getMajorName()+";";
						}
					}else{
						smajor+=temp.getMajorName()+";";
					}
				}
				if(temp.getClassName()!=null&&!temp.getClassName().equals("")){
					if(sclass!=null&&!sclass.equals("")){
						if(!(sclass.contains(temp.getClassName()))){
							sclass+=temp.getClassName()+";";
						}
					}else{
						sclass+=temp.getClassName()+";";
					}
				}
			}
		}
		tbAlumni.setStudyacademy1(sacademy);
		tbAlumni.setStudyClass1(sclass);
		tbAlumni.setStudydepartment1(sdepartement);
		tbAlumni.setStudyMajor1(smajor);
		tbAlumni.setStudyYear1(syear);
		biz.update(tbAlumni);
	}
}
