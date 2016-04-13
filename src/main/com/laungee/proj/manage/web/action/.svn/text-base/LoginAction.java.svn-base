package com.laungee.proj.manage.web.action;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.kingstar.sso.client.CurrentLoginUser;
import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbLog;
import com.laungee.proj.common.model.TbMenu;
import com.laungee.proj.common.model.TbUnAlumni;
import com.laungee.proj.common.model.TbUser;
import com.laungee.proj.common.util.EncryptUtil;
import com.laungee.proj.common.util.StringUtil;
import com.wiscom.is.IdentityFactory;
import com.wiscom.is.IdentityManager;
import com.wiscom.is.SSOToken;

public class LoginAction extends BaseAction {
	// 处理请求
	public String execute() throws Exception {
		HttpServletRequest request = this.getRequest();
		//得到学校名称
		Object schName=this.getCommonBo().getSQLUnique("select a.field_value from tb_field a where a.code=upper('schoolcode')");
		request.getSession().setAttribute(SCHOOL_NAME, schName);
		try{
			// 登陆帐号
			String Token1 = request.getParameter("Token1");
			// 密码
			String Token2 = request.getParameter("Token2");
			// 登陆账号为空时，默认识别统一身份认证账号
			boolean isCommonLogined = false;
			if(Token1==null || "".equals(Token1)){
				if(schName!=null&&"华东理工大学".equals(schName.toString().trim())){
					//华理统一身份认证
					Token1 = hlCheck(Token1,Token2);
				}else{
					Token1 = check(Token1,Token2);
				}
				isCommonLogined = true;
			}
			// 登陆帐号识别统一身份认证后仍未空时，跳转到登陆页面
			if(Token1==null || "".equals(Token1)){
				return ERROR;
			}
			TbUser bean=null;
			boolean isCommonLogin = false;
			String hql = "";
			List pars=this.getList();
			hql="from TbUser a where a.userCard=? and userOpen=1";
			pars.add(Token1);
			bean = (TbUser)getCommonBo().getHQLUnique(hql, pars);
			if(bean==null){
				request.setAttribute("alert", "用户不存在或没有相关权限！");
				return ERROR;
			}
			if("1".equals(bean.getStarCid())){ 
				// 统一身份认证帐号
				if(!isCommonLogined){//从本系统接口登录统一身份认证
					Token1 = check(Token1,Token2);
					if(Token1==null || "".equals(Token1)){
						request.setAttribute("alert", "身份校验失败，请检查输入是否有误！");
						return ERROR;
					}
				}
			}else{
				// 非统一身份认证帐号
				if(!EncryptUtil.encrypt(Token2).equals(bean.getUserPwd())){
					request.setAttribute("alert", "用户名或密码错误！");
					return ERROR;
				}
			}
			// 用户编号
			Long userId = bean.getUserId();
			request.getSession().setAttribute(USER_ID, userId);
			StringUtil.login_user=""+userId;
			// 用户姓名
			String userName = bean.getUserName();
			request.getSession().setAttribute(USER_NAME, userName);
			// 用户类型
			request.getSession().setAttribute(USER_CID, bean.getStarCid());
			request.getSession().setAttribute(USER_TYPE, bean.getStarCid());
			// 菜单权限
			String menuHql="select d.menuId from TbMenuRole a left join a.tbMenu d where exists " +
					"(select 1 from TbRoleUser b where b.tbRole.roleId=a.tbRole.roleId " +
					"and b.tbUser.userId=" + userId + ")";
			List menuList = this.getCommonBo().findHQL(menuHql);
			initMenu(menuList);
			// 菜单权限
			menuHql="select d from TbMenuRole a left join a.tbMenu d where exists " +
					"(select 1 from TbRoleUser b where b.tbRole.roleId=a.tbRole.roleId " +
					"and b.tbUser.userId=" + userId + ")";
			List menusList = this.getCommonBo().findHQL(menuHql);
			Set menuSet = this.listToSet(menusList, TbMenu.class,"code");
			request.getSession().setAttribute(ROLE_MENU, menuSet);
			// 可见权限
			String openHql = "select Max(a.tbRole.tbOpen.numOrder) from TbRoleUser a where a.tbUser.userId=" + userId;
			Object open = getCommonBo().getHQLUnique(openHql);
			if(null==open){
				open="0";
			}
			request.getSession().setAttribute(ROLE_OPEN,new Long(open.toString()));
			//系统日志
			TbLog tbLog=new TbLog();
			tbLog.setLoginDate(this.getCommonBo().getSysDate());
			tbLog.setLoginIp(request.getRemoteAddr());
			tbLog.setLoginName(userName);
			tbLog.setUserId(userId);
			tbLog.setHandType("登陆");
			tbLog.setHandSign("用户信息");
			this.getCommonBo().save(tbLog);
			this.getRequest().getSession().setAttribute("logid", tbLog.getLoginId());
			//得到首页模板
			openHql="from TbMenuRole a where a.tbMenu.menuId in (select menuId from TbMenu b where b.parentId = (select menuId from TbMenu where code='LOGIN_INDEX'))" +
					" and a.tbRole.roleId =(select c.tbRole.roleId from TbRoleUser c where c.tbUser.userId="+userId+")";
			List loginIndexList=this.getCommonBo().findHQL(openHql);
			//首页模板
			request.setAttribute("loginIndexList", loginIndexList);
			
			//财大统一身份认证
			if(schName!=null&&"上海财经大学".equals(schName.toString().trim())){
				CurrentLoginUser currentLoginUser = new CurrentLoginUser(Token1);
				//loginName为登录用户名
				request.getSession().setAttribute(
				CurrentLoginUser.CURRENT_LOGIN_USER_KEY, currentLoginUser);
			}
			return SUCCESS;
		}catch(Exception e){e.printStackTrace(); }
		
		request.setAttribute("alert", "用户登录异常！");
		return ERROR;
	}
	
	//华理统一身份认证
	private String hlCheck(String Token1,String Token2){
		String userId = "";
		try{
			if((Token1!=null) && (Token2!=null)){
				String is_config = getRequest().getRealPath("/client.properties");
				IdentityFactory factory = null;
				factory = IdentityFactory.createFactory(is_config);
				IdentityManager im = factory.getIdentityManager();
				if(im.checkPassword(Token1, Token2)){
					userId = Token1;
					im.createStoken(Token1, Token2);
					Cookie myCookie = new Cookie("iPlanetDirectoryPro",URLEncoder.encode(userId));
					getResponse().addCookie(myCookie);
				}
			}
		} catch(Exception e){ }
		return userId;
	}
	
	//南大的退出
//	public String exit() throws Exception{
//		//先注销系统中的用户
//		HttpServletRequest request = ServletActionContext.getRequest();
//		Long loginid=(Long) this.getRequest().getSession().getAttribute("logid");
//		if(loginid!=null){
//			TbLog tbLog=(TbLog) this.getCommonBo().get(TbLog.class, loginid);
//			tbLog.setOutDate(this.getCommonBo().getSysDate());
//			this.getCommonBo().update(tbLog);
//		}
//		this.getRequest().getSession().invalidate();
//		
//		//注销统一身份认证用户
//		String is_config = request.getRealPath("/client.properties");
//		IdentityFactory factory = IdentityFactory.createFactory(is_config);
//		IdentityManager im = factory.getIdentityManager();
//		//域名
//		StringBuffer url = request.getRequestURL();  
//		String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();
//		String loginOutUrl=im.getLogoutURL()+"?goto="+URLEncoder.encode(tempContextUrl+"/common/unifyLogin.jsp");
//		this.getResponse().sendRedirect(loginOutUrl);
//		return null;
//	}
	// 退出
	public String exit() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		Long loginid=(Long) this.getRequest().getSession().getAttribute("logid");
		if(loginid!=null){
			TbLog tbLog=(TbLog) this.getCommonBo().get(TbLog.class, loginid);
			if(tbLog!=null){
				tbLog.setOutDate(this.getCommonBo().getSysDate());
				this.getCommonBo().update(tbLog);
			}
		}
		this.getRequest().getSession().invalidate();
		//判断学校是否财大
		Object schName=this.getCommonBo().getSQLUnique("select a.field_value from tb_field a where a.code=upper('schoolcode')");
		if(schName!=null&&"上海财经大学".equals(schName.toString().trim())){
			request.setAttribute("schName", schName);
		}
		return LOGIN;
	}
	//获取菜单
	private void initMenu(List idsList) throws Exception{
		HttpServletRequest request = this.getRequest();
		if(idsList==null) return;
		if(idsList.size()==0) return;
		String hql = "";
		String menuIds = "";
		for(int i=0;i<idsList.size();i++){
			if(i>0){
				menuIds += ",";
			}
			menuIds += idsList.get(i);
		}
		//一级菜单
		hql="from TbMenu a where a.numLevel=1 and a.menuId in ("+menuIds+") order by a.numOrder";
		List firstMenuList=getCommonBo().findHQL(hql);
		
		if(firstMenuList==null) return;
		if(firstMenuList.size()==0) return;
		//二级菜单
		List secondMenuList=this.getList();
		//三级菜单
		List thirdMenuList=this.getList();
		
		for(int i=0;i<firstMenuList.size();i++){
			TbMenu tbMenu1 = (TbMenu)firstMenuList.get(i);
			hql="from TbMenu a where a.numLevel=2 and a.parentId="+tbMenu1.getMenuId()+"  and a.menuId in ("+menuIds+") order by a.numOrder";
			List secondMenus=getCommonBo().findHQL(hql);
			secondMenuList.add(secondMenus);
			List thirdMenus=this.getList();
			if(secondMenus!=null){
				for(int j=0;j<secondMenus.size();j++){
					TbMenu tbMenu2 = (TbMenu)secondMenus.get(j);
					hql="from TbMenu a where a.numLevel=3 and a.parentId="+tbMenu2.getMenuId()+"  and a.menuId in ("+menuIds+") order by a.numOrder";
					List thirdMenuDetail=getCommonBo().findHQL(hql);
					thirdMenus.add(thirdMenuDetail);
				}
				
			}
			thirdMenuList.add(thirdMenus);
		}
		
		request.setAttribute("list_menu1", firstMenuList);
		request.setAttribute("list_menu2", secondMenuList);
		request.setAttribute("list_menu3", thirdMenuList);
	}
	// 统一身份认证
	private String check(String Token1,String Token2) throws Exception{
		String userCard="";
		try{
			HttpServletRequest request=this.getRequest();
			String is_config = request.getRealPath("/client.properties");
			IdentityFactory factory = IdentityFactory.createFactory(is_config);
			IdentityManager im = factory.getIdentityManager();
		
			if(null==Token1 && null==Token2){
				// Cookie中取值
				Cookie all_cookies[] = request.getCookies();
				Cookie myCookie;
				String decodedCookieValue = null;
				if(all_cookies!=null){
					for(int i=0; i<all_cookies.length; i++){
						myCookie = all_cookies[i];
						if(myCookie.getName().equals("iPlanetDirectoryPro")){
							decodedCookieValue = URLDecoder.decode(myCookie.getValue(), "GB2312");
						}
					}
				}
				if(decodedCookieValue!=null){
					userCard = im.getCurrentUser(decodedCookieValue);
				}
			}else{
				if(im.checkPassword(Token1, Token2)){
					userCard=Token1;
					SSOToken token=im.createStoken(Token1, Token2);
					String cookievalue=URLEncoder.encode(token.getTokenValue());
					Cookie cookie=new Cookie("iPlanetDirectoryPro",cookievalue);
					cookie.setPath("/");
					cookie.setMaxAge(-1);
					this.getResponse().addCookie(cookie);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return userCard;
	}
	// 处理请求
	public String xylogin() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		// 登陆标识
		String fd = request.getParameter("fd");
		// 帐号
		String Token1 = request.getParameter("Token1");
		request.setAttribute("Token1", Token1);
		// 密码
		String Token2 = request.getParameter("Token2");
		// 类型
		String type = request.getParameter("type");
		if(null==type){
			type="0";
		}
		request.setAttribute("type", type);
		// 合法性
		if(!(null!=Token1 && !"".equals(Token1) && null!=Token2 && !"".equals(Token2))){
			if("0".equals(fd)){
				return LOGIN;
			}else{
				return ERROR;
			}
		}
		// 用户
		TbUser bean=null;
		// 非统一身份认证帐号
		if("0".equals(type)){
			
				String hql="select a from TbUser a where a.userCard=? and a.userPwd=? and starCid='0'";
				List pars=getList();
				pars.add(Token1);
				pars.add(EncryptUtil.encrypt(Token2));
				bean=(TbUser) this.getCommonBo().getHQLUnique(hql, pars);
			
		}
		// 统一身份认证帐号
		if("1".equals(type)){
			String usrCard=check(Token1, Token2);
			if(null!=usrCard && !"".equals(usrCard)){
				String hql="select a from TbUser a where a.userCard=? and starCid='1'";
				List pars=getList();
				pars.add(Token1);
				bean=(TbUser) this.getCommonBo().getHQLUnique(hql, pars);
			}
		}
		// 用户
		if(null!=bean){
			// 用户编号
			Long userId = bean.getUserId();
			request.getSession().setAttribute(USER_ID, userId);
			// 用户姓名
			// 用户姓名
			String userName = bean.getUserName();
			request.getSession().setAttribute(USER_NAME, userName);
			// 用户类型
			request.getSession().setAttribute(USER_CID, bean.getStarCid());
			request.getSession().setAttribute(USER_TYPE, bean.getStarCid());
			// 菜单权限
			String menuHql="select d.menuId from TbMenuRole a left join a.tbMenu d where exists " +
					"(select 1 from TbRoleUser b where b.tbRole.roleId=a.tbRole.roleId " +
					"and b.tbUser.userId=" + userId + ")";
			List menuList = this.getCommonBo().findHQL(menuHql);
			initMenu(menuList);
			// 可见权限
			String openHql = "select Max(a.tbRole.tbOpen.numOrder) from TbRoleUser a where a.tbUser.userId=" + userId;
			Object open = getCommonBo().getHQLUnique(openHql);
			if(null==open){
				open="0";
			}
			request.getSession().setAttribute(ROLE_OPEN,new Long(open.toString()));
			//系统日志
			TbLog tbLog=new TbLog();
			tbLog.setLoginDate(new Date());
			tbLog.setLoginIp(request.getRemoteAddr());
			tbLog.setLoginName(userName);
			tbLog.setUserId(userId);
			this.getCommonBo().save(tbLog);
			this.getRequest().getSession().setAttribute("logid", tbLog.getLoginId());
		}
		else{
			request.setAttribute("Token1", Token1);
			request.setAttribute("type", type);
			if(null!=Token1 && null!=Token2){
				request.setAttribute("alert", "该用户不存在或密码错误，请检查输入是否有误，或注册新用户！");
			}
			if("0".equals(fd)){
				return LOGIN;
			}else{
				return ERROR;
			}
		}
		if("0".equals(fd)){
			return SUCCESS;
		}else{
			return ERROR;
		}
	}
	// 退出
	public String xyexit() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		Long loginid=(Long) this.getRequest().getSession().getAttribute("logid");
		if(loginid!=null){
			TbLog tbLog=(TbLog) this.getCommonBo().get(TbLog.class, loginid);
			tbLog.setOutDate(new Date());
			this.getCommonBo().update(tbLog);
		}
		this.getRequest().getSession().invalidate();
		return LOGIN;
	}
	
	//加载管理文件
	public String ldManageFile() throws Exception{
		//得全全部管理文件
		String hql="from TbResOther a where a.tableName='TbManage'";
		List fileList=this.getCommonBo().findHQL(hql);
		this.getRequest().setAttribute("fileList", fileList);
		return "manageFilePage";
	}
	
	/**
	 * 监控系统状态
	 * @return
	 * @throws Exception 
	 */
	public String monitor() throws Exception{
		String ajax="";
		try {
			ajax="+ok";
			this.getCommonBo().execute("select 1 from dual");
			this.getResponse().setStatus(200);
		} catch (Exception e) {
			// TODO: handle exception
			this.getResponse().setStatus(503);
		}
		//this.sendResponse(this.getResponse(), ajax);
		HttpServletResponse response=this.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Pragma", "No-Cache");
		response.setHeader("Cache-Control", "No-Cache");
		response.setDateHeader("Expires", 0L);
		response.getWriter().println(ajax);
		return null;
	}
}
