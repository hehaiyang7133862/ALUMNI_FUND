package com.laungee.proj.manage.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.laungee.proj.common.core.BaseAction;
import com.laungee.proj.common.model.TbMenu;

public class MenuAddAction extends BaseAction {
	// 处理请求
	public String execute() throws Exception {
		// 请求对象
		HttpServletRequest request=ServletActionContext.getRequest();
		String scode=request.getParameter("scode");
		// 父类编号
		String parentId=request.getParameter("parentId");
		// 名称
		String menuName=request.getParameter("menuName");
		
		// 编号
		String code=request.getParameter("code");
		// 备注
		String memo=request.getParameter("memo");
		// 是否为叶子
		String isLeaf=request.getParameter("isLeaf");
		// 菜单链接
		String urlLink=request.getParameter("urlLink");
		// 菜单图标
		String urlIcon=request.getParameter("urlIcon");
		// 类型信息
		TbMenu tbMenu=new TbMenu();
		// 名称
		tbMenu.setMenuName(menuName);
		// 编号
		tbMenu.setCode(code);
		// 备注
		tbMenu.setMemo(memo);
		if(scode!=null&&scode.equals("menu")){
			// 父类编号
			if(null!=parentId&&!"".equals(parentId)){
				tbMenu.setParentId(new Long(parentId));
				TbMenu ptbMenu=(TbMenu) this.getCommonBo().get(TbMenu.class,new Long(parentId));
				if(ptbMenu.getNumLevel()!=null){
					tbMenu.setNumLevel(new Long(ptbMenu.getNumLevel().intValue()+1));
					if(ptbMenu.getNumLevel().equals(new Long(2))){
						// 是否为叶子
						tbMenu.setIsLeaf("1");
					}else{
						// 是否为叶子
						tbMenu.setIsLeaf("0");
					}
					
				}
			}
		}else{
			if(null!=parentId&&!"".equals(parentId)){
					tbMenu.setParentId(new Long(parentId));
					TbMenu ptbMenu=(TbMenu) this.getCommonBo().get(TbMenu.class,new Long(parentId));
					if(ptbMenu.getCode()!=null&&ptbMenu.getCode().equals("button")){
						tbMenu.setIsLeaf("0");
					}else{
						tbMenu.setIsLeaf("1");
					}
				
			}
		}
		
		// 菜单链接
		tbMenu.setUrlLink(urlLink);
		// 菜单图标
		tbMenu.setUrlIcon(urlIcon);
		// 序号
		String numHql="select max(a.numOrder) from TbMenu a where a.parentId="+parentId;
		int numOrder=this.getCommonBo().findHQLCount(numHql);
		tbMenu.setNumOrder(new Long(numOrder+1));
		// 保存信息
		getCommonBo().save(tbMenu);
		// 返回
		return SUCCESS;
	}
}
