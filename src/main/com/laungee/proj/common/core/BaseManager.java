package com.laungee.proj.common.core;

import com.laungee.proj.common.biz.ICommonBiz;
import com.laungee.proj.common.util.SpringUtil;

public class BaseManager {
	// ȡ��ҵ���
	protected ICommonBiz commonBiz;
	
	/**
	 * ȡ��ҵ���
	 */
	protected ICommonBiz getCommonBiz(){
		if(null==commonBiz){
			commonBiz=(ICommonBiz)SpringUtil.getBean(SpringUtil.COMMONBIZ);
		}
		return commonBiz;
	}
	protected ICommonBiz getCommonBo(){
		if(null==commonBiz){
			commonBiz=(ICommonBiz)SpringUtil.getBean(SpringUtil.COMMONBIZ);
		}
		return commonBiz;
	}
}
