


import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.dom4j.io.SAXReader;
import org.dom4j.Document;
import org.dom4j.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GetWeather {
//	static String[] city = { "北京", "天津", "上海", "重庆", "石家庄", "太原", "沈阳", "长春",
//			"哈尔滨", "南京", "杭州", "合肥", "福州", "南昌", "济南", "郑州", "武汉", "长沙", "广州",
//			"海口", "成都", "贵阳", "昆明", "西安", "兰州", "西宁", "拉萨", "南宁", "呼和浩特", "银川",
//			"乌鲁木齐", "香港", "台北", "澳门" }; // 各个城市
	// static int[] day={0,1,2,3,4}; //哪一天的天气
	static String[] city={"上海"};
	static int day = 0; // 哪一天的天气
	static String weather; // 保存天气情况
	static String high; // 保存当天最高温度
	static String low; // 保存当天最低温度

	public void getweather() // 获取天气函数
	{
		URL ur;

		try {
			for (String str : city) { // 循环访问获取各个地区不同天气情况
				ur = new URL("http://php.weather.sina.com.cn/xml.php?city="
						+ str + "&password=DJOYnieT8234jlsK&day=" + day);
                                SAXReader saxReader = new SAXReader();
                                Document doc=saxReader.read(ur.toString());
                                Element root = doc.getRootElement();
                                Iterator i= root.elementIterator();
                                if(i.hasNext()){
                                    root=(Element) i.next();
                                }
                                if (root != null) {
                                    for (Iterator it = root.elementIterator(); it.hasNext();) {
                                        Element node = (Element) it.next();
                                        if (node.getName().equals("status1"))
                                                weather = node.getTextTrim(); // 获取到天气情况
                                        else if (node.getName().equals("temperature1"))
                                                high = node.getTextTrim(); // 获取到最高温度
                                        else if (node.getName().equals("temperature2"))
                                                low = node.getTextTrim(); // 获取到最低温度
                                    }
                                }
				System.out.println(str + " " + weather + " " + low + "℃~"
						+ high + "℃"); // 前台输出
			}

		} catch (Exception e) {
//			System.out.println("获取天气失败:" + e);
                    e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new GetWeather().getweather(); // 主接口函数调用执行方法
	}

}