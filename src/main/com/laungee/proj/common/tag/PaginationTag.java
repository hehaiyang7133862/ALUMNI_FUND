package com.laungee.proj.common.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.struts.taglib.TagUtils;

import com.laungee.proj.common.util.PaginationDto;

public class PaginationTag extends TagSupport {
	// 请求地址
	private String action;
	// 参数列表
	private PaginationDto dto;

	public int doStartTag() throws JspException {
		// 产生随机数
		String rand=""+(int)(Math.random()*100)+(int)(Math.random()*100)+(int)(Math.random()*100);
		// 请求对象
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		// 分页对象
		if(null==dto){
			dto=(PaginationDto)request.getAttribute("p");
		}
		String url="";
		if(null!=action){
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://" + request.getServerName()+ ":" + request.getServerPort() + path + "/";
		    url=basePath+action+"?"+dto.getPart();
		}
		// 排序
		if(null!=dto.getSt() && !dto.equals("")){
			url+="&"+dto.getSst()+"="+dto.getSt()+"&"+dto.getSor()+"="+dto.getOr()+"&";
		}
		// 打印DIV
		TagUtils.getInstance().write(pageContext,"\n<br/>\n"+"<div style=\"text-align:center\">"+"\n");
		// 首页
		TagUtils.getInstance().write(pageContext,"<a href=\"" + url 
				+ dto.getSsize()+"="+dto.getSize()+"&"+dto.getScurr()+"=1 \">首页</a>&nbsp;"+"\n");
		// 上一页_有
		if (dto.getCurr() >=2) {
			TagUtils.getInstance().write(pageContext,"<a href=\"" + url 
					+ dto.getSsize()+"="+dto.getSize()+"&"+dto.getScurr()+"="+(dto.getCurr()-1)+"\">上一页</a>&nbsp;"+"\n");
			
		}
		// 上一页_无
		else {
			TagUtils.getInstance().write(pageContext, "<span style=\"color:#CCC;\">上一页</span>"+"\n");
		}
		// 第几页
		TagUtils.getInstance().write(pageContext,"到");
		//TagUtils.getInstance().write(pageContext,"<input id=\"jump"+rand+"Id\" type=\"text\" style=\"width:22px;height:18;background:#FFF;font-size:12px;margin:0 3px 0 4px;\" onKeyUp=\"limit"+rand+"(this)\" />");
		TagUtils.getInstance().write(pageContext,"<input id=\"jump"+rand+"Id\" type=\"text\" class=\"page_input\" onKeyUp=\"limit"+rand+"(this)\" />");
	    TagUtils.getInstance().write(pageContext,"页"+"\n");
	    //TagUtils.getInstance().write(pageContext,"<input type=\"button\" value=\"跳\" onClick=\"jump"+rand+"()\" style=\"border:#728eb8 1px double;height:18px;width:20px;background-color:#f4f7fd;line-height:18px;\" />"+"\n");
	    TagUtils.getInstance().write(pageContext,"<input type=\"button\" value=\"跳\" onClick=\"jump"+rand+"()\" class=\"page_jump\" />"+"\n");
		// 页数信息
		TagUtils.getInstance().write(pageContext,"〖第" + dto.getCurr() + "页〗【共" + (0==dto.getCount()?1:dto.getCount()) + "页】"+"\n");
		// 下一页_有
		if (dto.getCurr() < dto.getCount()) {
			TagUtils.getInstance().write(pageContext,"<a href=\"" + url 
					+ dto.getSsize()+"="+dto.getSize()+"&"+dto.getScurr()+"="+(dto.getCurr()+1)+"\">下一页</a>&nbsp;"+"\n");
		}
		// 下一页_无
		else {
			TagUtils.getInstance().write(pageContext, "<span style=\"color:#CCC;\">下一页</span>"+"\n");
		}
		// 末页
		TagUtils.getInstance().write(pageContext,"<a href=\"" + url 
				+ dto.getSsize()+"="+dto.getSize()+"&"+dto.getScurr()+"="+dto.getCount()+"\">末页</a>&nbsp;"+"\n");
		// 条目数
		TagUtils.getInstance().write(pageContext,"每页显示"+"\n");
		TagUtils.getInstance().write(pageContext,"<a href=\"" + url 
				+ dto.getSsize()+"="+dto.getItem1()+"&"+dto.getScurr()+"=1\">10</a>&nbsp;"+"\n");
		TagUtils.getInstance().write(pageContext,"<a href=\"" + url 
				+ dto.getSsize()+"="+dto.getItem2()+"&"+dto.getScurr()+"=1\">25</a>&nbsp;"+"\n");
		TagUtils.getInstance().write(pageContext,"<a href=\"" + url 
				+ dto.getSsize()+"="+dto.getItem3()+"&"+dto.getScurr()+"=1\">50</a>&nbsp;"+"\n");
		TagUtils.getInstance().write(pageContext,"条共"+dto.getTotal()+"条记录"+"\n");
		TagUtils.getInstance().write(pageContext,"</div>"+"\n");
		// 跳转JS代码
		String jump=
		     "<script language=\"javascript\">"+"\n"
			+"function limit"+rand+"(obj){"+"\n"
			+"var val=obj.value;"+"\n"
			+"if(!(/^\\d{1,6}$/g.test(val))){"+"\n"
			+"obj.value=\"\";"+"\n"
			+"return;"+"\n"
			+"}"+"\n"
			+"if(!(obj.value>=1&&obj.value<="+dto.getCount()+")){"+"\n"
			+"obj.value=val.substr(0,val.length-1);"+"\n"
			+"return;"+"\n"
			+"}"+"\n"
			+"}"+"\n"
			+"function jump"+rand+"(){"+"\n"
			+"var val=document.getElementById(\"jump"+rand+"Id\").value;"+"\n"
			+"if(val==\"\"){"+"\n"
			+"return;"+"\n"
			+"}"+"\n"
			+"location.href=\"" + url + dto.getSsize()+"="+dto.getSize()+"&"+dto.getScurr()+"=\"+val;"+"\n"
			+"}"+"\n"
			+"function myRefresh(){"+"\n"
			+"location.href=\"" + url + dto.getSsize()+"="+dto.getSize()+"&"+dto.getScurr()+"="+dto.getCurr()+"\";"+"\n"
			+"}"+"\n"
			+"</script>"+"\n";
		TagUtils.getInstance().write(pageContext,jump);
		// 初始化
		dto=null;
		return 0;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setDto(PaginationDto dto) {
		this.dto = dto;
	}
}