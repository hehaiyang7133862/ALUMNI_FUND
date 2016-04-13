package com.laungee.proj.account.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbDonationAccout;

public class DonationAccountAction extends BaseAction{
	//列表
	public String list() throws Exception {
		HttpServletRequest request = this.getRequest();
		//构建查询
		String hql = "from TbDonationAccout t where 1=1 order by t.accountId";
		List accountlist = this.getList();
		try{
			accountlist = this.getCommonBo().findHQLPage(hql);
		}catch(Exception e){}
		if(accountlist!=null && !accountlist.isEmpty()){
			request.setAttribute("accountlist", accountlist);
		}
		return "list";
	}
	
	//新增
	public String add() throws Exception {
		
		return "add";
	}
	public String doAdd() throws Exception {
		HttpServletRequest request = this.getRequest();
		//账号名称
		String accountName = request.getParameter("accountName");
		//开户行
		String accountBank = request.getParameter("accountBank");
		//账号
		String accountNum = request.getParameter("accountNum");
		//币种
		String currency = request.getParameter("currency");
		//类型说明
		String memo = request.getParameter("memo");
		TbDonationAccout obj = new TbDonationAccout();
		String msg = "";
		if(accountName!=null && !"".equals(accountName)){
			obj.setAccountName(accountName);
		}else{
			msg += "账号名称不能为空；";			
		}
		if(accountNum!=null && !"".equals(accountNum)){
			obj.setAccountNum(accountNum);
		}else{
			msg += "账号不能为空；";
		}
		if(msg!=null && !"".equals(msg)){
			request.setAttribute("msg", msg);
			return "add";
		}
		if(accountBank!=null && !"".equals(accountBank)){
			obj.setAccountBank(accountBank);
		}
		if(currency!=null && !"".equals(currency)){
			obj.setCurrency(currency);
		}
		if(memo!=null && !"".equals(memo)){
			obj.setMemo(memo);
		}
		this.getCommonBo().save(obj);
		
		return SUCCESS;
	}
	
	//编辑
	public String edit() throws Exception {
		HttpServletRequest request = this.getRequest();
		String id = request.getParameter("id");
		TbDonationAccout obj = null;
		if(id != null && !"".equals(id)){
			try{
				obj = (TbDonationAccout)this.getCommonBo().get(TbDonationAccout.class, new Long(id));
			}catch(Exception e){}
		}
		if(obj == null){
			return "add";
		}
		request.setAttribute("obj", obj);
		
		return "edit";
	}
	public String doEdit() throws Exception {
		HttpServletRequest request = this.getRequest();
		//类型ID
		String id = request.getParameter("id");
		TbDonationAccout obj = null;
		if(id != null && !"".equals(id)){
			try{
				obj = (TbDonationAccout)this.getCommonBo().get(TbDonationAccout.class, new Long(id));
			}catch(Exception e){}
		}
		if(obj == null){
			request.setAttribute("msg", "保存过程中出错！");
			request.setAttribute("id", id);
			return "add";
		}
		//账号名称
		String accountName = request.getParameter("accountName");
		//开户行
		String accountBank = request.getParameter("accountBank");
		//账号
		String accountNum = request.getParameter("accountNum");
		//币种
		String currency = request.getParameter("currency");
		//类型说明
		String memo = request.getParameter("memo");
		String msg = "";
		if(accountName!=null && !"".equals(accountName)){
			obj.setAccountName(accountName);
		}else{
			msg += "账号名称不能为空；";			
		}
		if(accountNum!=null && !"".equals(accountNum)){
			obj.setAccountNum(accountNum);
		}else{
			msg += "账号不能为空；";
		}
		if(msg!=null && !"".equals(msg)){
			request.setAttribute("msg", msg);
			request.setAttribute("obj", obj);
			return "edit";
		}
		if(accountBank!=null && !"".equals(accountBank)){
			obj.setAccountBank(accountBank);
		}
		if(currency!=null && !"".equals(currency)){
			obj.setCurrency(currency);
		}
		if(memo!=null && !"".equals(memo)){
			obj.setMemo(memo);
		}
		this.getCommonBo().update(obj);
		
		return SUCCESS;
	}
	
	//删除
	public String del() throws Exception {
		HttpServletRequest request = this.getRequest();
		//账户ID
		String id = request.getParameter("id");
		TbDonationAccout obj = null;
		if(id != null && !"".equals(id)){
			try{
				obj = (TbDonationAccout)this.getCommonBo().get(TbDonationAccout.class, new Long(id));
			}catch(Exception e){}
		}
		if(obj != null){
			try{
				this.getCommonBo().delete(obj);
			}catch(Exception e){}
		}
		
		return list();
	}
}
