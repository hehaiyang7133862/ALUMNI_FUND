package com.laungee.proj.manage.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbAlumniSt;
import com.laungee.proj.common.model.TbEmailAlumni;
import com.laungee.proj.common.model.TbEmailGroup;
import com.laungee.proj.common.model.TbMailAlumni;
import com.laungee.proj.common.model.TbMailGroup;
import com.laungee.proj.common.model.TbUnAlumniSt;
import com.laungee.proj.common.report.ExcelExport;
import com.laungee.proj.common.util.DecryptUtil;
import com.laungee.proj.common.util.EncryptUtil;

public class EditUserAction extends BaseAction {

	// 用户信息页面列表
	public String toModifyUserdetil() throws Exception {
		// 属性
		String usertype = (String) this.getRequest().getSession().getAttribute(
				USER_TYPE);
		String flag = "";
		if (usertype.equals("1")) {
			Long userid = (Long) this.getRequest().getSession().getAttribute(
					USER_ID);
			TbAlumniSt tbAlumniSt = (TbAlumniSt) this.getCommonBo().get(
					TbAlumniSt.class, userid);
			this.getRequest().setAttribute("tbAlumniSt", tbAlumniSt);
			flag = "1";
		}
		if (usertype.equals("2")) {
			Long stuid = (Long) this.getRequest().getSession().getAttribute(
					STU_ID);
			TbUnAlumniSt tbUnAlumniSt = (TbUnAlumniSt) this.getCommonBo().get(
					TbUnAlumniSt.class, stuid);
			this.getRequest().setAttribute("tbUnAlumniSt", tbUnAlumniSt);
			if(tbUnAlumniSt.getUserPwd()!=null){
			this.getRequest().setAttribute("userPwd", DecryptUtil.decrypt(tbUnAlumniSt.getUserPwd()));
			}
			flag = "2";
		}
		this.getRequest().setAttribute("flag", flag);
		return SUCCESS;
	}
	// 更新用户基本信息
	public String doModifyuserDetil() throws Exception {
		// 属性
		String username = this.getRequest().getParameter("username");
		String passwd = this.getRequest().getParameter("passwd");
		String sexCid = this.getRequest().getParameter("sexCid");
		String telephone = this.getRequest().getParameter("telephone");
		String mobilephone = this.getRequest().getParameter("mobilephone");
		String firstmail = this.getRequest().getParameter("firstmail");
		String memo = this.getRequest().getParameter("memo");
		String usertype = (String) this.getRequest().getSession().getAttribute(
				USER_TYPE);
		
		if (usertype.equals("1")) {
			Long userid = (Long) this.getRequest().getSession().getAttribute(
					USER_ID);
			TbAlumniSt tbAlumniSt = (TbAlumniSt) this.getCommonBo().get(
					TbAlumniSt.class, userid);
			tbAlumniSt.setHandsetFirst(mobilephone);
			tbAlumniSt.setMailFirst(firstmail);
			tbAlumniSt.setNameCn(username);
			tbAlumniSt.setSelfBase(memo);
			tbAlumniSt.setSexCid(sexCid);
			tbAlumniSt.setTelephoneFirst(telephone);
			this.getCommonBo().update(tbAlumniSt);
		}
		if (usertype.equals("2")) {
			Long stuid = (Long) this.getRequest().getSession().getAttribute(
					STU_ID);
			TbUnAlumniSt tbUnAlumniSt = (TbUnAlumniSt) this.getCommonBo().get(
					TbUnAlumniSt.class, stuid);
			tbUnAlumniSt.setHandsetFirst(mobilephone);
			tbUnAlumniSt.setMailFirst(firstmail);
			tbUnAlumniSt.setNameCn(username);
			tbUnAlumniSt.setSelfBase(memo);
			tbUnAlumniSt.setSexCid(sexCid);
			tbUnAlumniSt.setTelephoneFirst(telephone);
			if(passwd!=null){
			tbUnAlumniSt.setUserPwd(EncryptUtil.encrypt(passwd));
			}
			this.getCommonBo().update(tbUnAlumniSt);
		}
		return this.toModifyUserdetil();
	}
	

}
