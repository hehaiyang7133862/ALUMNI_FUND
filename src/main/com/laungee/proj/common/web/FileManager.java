package com.laungee.proj.common.web;

import java.util.List;

import com.laungee.proj.common.core.BaseManager;
import com.laungee.proj.common.model.TbFileTab;
public class FileManager extends BaseManager{
	private FileManager(){
	}
	public static FileManager getInstance(){
		return new FileManager();
	}
	// 更新上传文件：编号,资源列表
	public void update(Long id,String ids) throws Exception{
		String tid=ids;
		if(null==tid || "".equals(tid)){
			return;
		}
		// 查询
		String hql="from TbFileTab a where a.typeCid=0 and a.tableId="+tid;
		List list=getCommonBo().findHQL(hql);
		// 更新
		TbFileTab bean=null;
		for (int i = 0; i < list.size(); i++) {
			bean=(TbFileTab)list.get(i);
			bean.setTableId(id);
			bean.setTypeCid("1");
			getCommonBo().update(bean);
		}
	}
	// 删除上传文件：表名,编号
	public void delete(Long id,String name) throws Exception{
		
	}
}
