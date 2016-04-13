


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
//	static String[] city = { "����", "���", "�Ϻ�", "����", "ʯ��ׯ", "̫ԭ", "����", "����",
//			"������", "�Ͼ�", "����", "�Ϸ�", "����", "�ϲ�", "����", "֣��", "�人", "��ɳ", "����",
//			"����", "�ɶ�", "����", "����", "����", "����", "����", "����", "����", "���ͺ���", "����",
//			"��³ľ��", "���", "̨��", "����" }; // ��������
	// static int[] day={0,1,2,3,4}; //��һ�������
	static String[] city={"�Ϻ�"};
	static int day = 0; // ��һ�������
	static String weather; // �����������
	static String high; // ���浱������¶�
	static String low; // ���浱������¶�

	public void getweather() // ��ȡ��������
	{
		URL ur;

		try {
			for (String str : city) { // ѭ�����ʻ�ȡ����������ͬ�������
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
                                                weather = node.getTextTrim(); // ��ȡ���������
                                        else if (node.getName().equals("temperature1"))
                                                high = node.getTextTrim(); // ��ȡ������¶�
                                        else if (node.getName().equals("temperature2"))
                                                low = node.getTextTrim(); // ��ȡ������¶�
                                    }
                                }
				System.out.println(str + " " + weather + " " + low + "��~"
						+ high + "��"); // ǰ̨���
			}

		} catch (Exception e) {
//			System.out.println("��ȡ����ʧ��:" + e);
                    e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new GetWeather().getweather(); // ���ӿں�������ִ�з���
	}

}