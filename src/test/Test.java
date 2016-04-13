import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.laungee.proj.common.biz.ICommonBiz;
import com.laungee.proj.common.model.TbCommodity;
import com.laungee.proj.common.util.DateUtil;
import com.laungee.proj.common.util.SpringUtil;


public class Test {
	public static void main(String[] args) throws Exception {
		String[] optionCountStrs = "1.0".split("\\.");
		System.out.println(Long.parseLong(optionCountStrs[0]));
		/*String jsonString =  "{\"UserName\":\"ZHULI\",\"age\":\"30\",\"workIn\":\"ALI\",\"Array\":[\"ZHULI\",\"30\",\"ALI\"]}";
		String jsonStr = "{'username':'123','age':'12'}";
		//将Json字符串转为java对象          
		JSONArray guestArray = JSON.parseArray(jsonStr);
		if(null==guestArray||guestArray.isEmpty()){
		}
		for (int i = 0; i < guestArray.size(); i++) {
			JSONObject json = (JSONObject)guestArray.get(i);
			System.out.println(json);
			 
		}
		List beanList = new ArrayList();
		TbCommodity bean = new TbCommodity();
		bean.setCommId(new Long(1));
		beanList.add(bean);
		beanList.add(2);
		beanList.add(3);
		beanList.add(4);
		beanList.add(5);

		Object json2 = JSONArray.toJSON(beanList);
		List beanList1 = new ArrayList();
		TbCommodity bean1 = new TbCommodity();
		bean1.setCommId(new Long(1));
		beanList1.add(bean1);
		beanList1.add(2);
		beanList1.add(3);
		beanList1.add(4);
		beanList1.add(5);
		Object json = JSONArray.toJSON(beanList1);
		System.out.println(json2.toString()+json.toString());
		
		Date date1 = DateUtil.toDate("2014-05-26");
		Date date2 = DateUtil.toDate("2014-05-27");
		if(date1.after(date2)){
			System.out.println("1");
		}else{
			System.out.println("2");
			
		}
		*/
		
		String s="D:/MyEclipse 10/ALUMNI_FUND11/WebRoot/upload/image/20150515/120637_541.png";
		s=s.substring(s.indexOf("/upload"));
		System.out.println(s);
		
		
		
		
		
		
	}
}
