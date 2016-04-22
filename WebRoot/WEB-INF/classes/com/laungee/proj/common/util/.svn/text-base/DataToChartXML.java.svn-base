package com.laungee.proj.common.util;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URLEncoder;

import org.apache.xerces.dom.DocumentImpl;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.List;

import com.laungee.proj.common.util.FusionChart;

public class DataToChartXML {
	public String getChartXML(FusionChart fusionChart) {
		StringWriter sw = new StringWriter();

		Document doc = new DocumentImpl();
		Element root = doc.createElement("chart");
		root.setAttribute("caption", fusionChart.getCaption());
		if (fusionChart.getViewMode().equals("column")) {
			root.setAttribute("xAxisName", fusionChart.getXAxisName());
			root.setAttribute("yAxisName", fusionChart.getYAxisName());
			root.setAttribute("rotateYAxisName", "0");// 纵坐标允许中文
			root.setAttribute("yAxisNameWidth", "14");// 纵坐标名称宽度
		} else if (fusionChart.getViewMode().equals("pie")) {
			root.setAttribute("palette", "4");
			root.setAttribute("decimals", "0");
			root.setAttribute("enableSmartLabels", "1");
			root.setAttribute("enableRotation", "0");
			root.setAttribute("bgColor", "99CCFF,FFFFFF");
			root.setAttribute("bgAlpha", "40,100");
			root.setAttribute("bgRatio", "0,100");
			root.setAttribute("bgAngle", "360");
			root.setAttribute("showBorder", "1");
			root.setAttribute("startingAngle", "70");
		} else if (fusionChart.getViewMode().equals("area")) {
			root.setAttribute("palette", "2");
			root.setAttribute("xAxisName", fusionChart.getXAxisName());
			root.setAttribute("yAxisName", fusionChart.getYAxisName());
			root.setAttribute("yAxisMinValue", "15000");
			root.setAttribute("numberPrefix", "$");
			root.setAttribute("showValues", "0");

		} else if (fusionChart.getViewMode().equals("doughnut")) {
			root.setAttribute("palette", "2");
			root.setAttribute("showBorder", "1");
		}
		root.setAttribute("outCnvbaseFontSize", "12");
		root.setAttribute("baseFontSize", "12");
//		Iterator it = fusionChart.getDataMap().entrySet().iterator();
//		while (it.hasNext()) {
//			Element element = doc.createElement("set");
//			Map.Entry entry = (Map.Entry) it.next();
//			element.setAttribute("label", (String) entry.getKey());
//			long value = ((Long) entry.getValue()).intValue();
//			element.setAttribute("value", Long.toString(value));
//			root.appendChild(element);
//		}
		for (int i = 0; i < fusionChart.getDataList().size(); i++) {
			try {
				List subList = (List) fusionChart.getDataList().get(i);
				Element element = doc.createElement("set");
				element.setAttribute("label", subList.get(0).toString());
				element.setAttribute("value", subList.get(1).toString());
				root.appendChild(element);
			} catch (Exception e) {
			}
		}

		doc.appendChild(root);
		OutputFormat of = new OutputFormat("XML", "UTF-8", false);
		of.setLineSeparator(null);
		of.setDoctype(null, null);
		XMLSerializer serializer = new XMLSerializer(sw, of);
		try {
			serializer.serialize(doc);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sw.toString();
	}
	
	/**XML中含有link属性*/
	public String getChartXMLAsLink(FusionChart fusionChart) {
		StringWriter sw = new StringWriter();
		Document doc = new DocumentImpl();
		Element root = doc.createElement("chart");
		root.setAttribute("caption", fusionChart.getCaption());
		if (fusionChart.getViewMode().equals("column")) {
			root.setAttribute("xAxisName", fusionChart.getXAxisName());
			root.setAttribute("yAxisName", fusionChart.getYAxisName());
			root.setAttribute("rotateYAxisName", "0");// 纵坐标允许中文
			root.setAttribute("yAxisNameWidth", "14");// 纵坐标名称宽度
		} else if (fusionChart.getViewMode().equals("pie")) {
			root.setAttribute("palette", "4");
			root.setAttribute("decimals", "0");
			root.setAttribute("enableSmartLabels", "1");
			root.setAttribute("enableRotation", "0");
			root.setAttribute("bgColor", "99CCFF,FFFFFF");
			root.setAttribute("bgAlpha", "20,100");
			root.setAttribute("bgRatio", "0,100");
			root.setAttribute("bgAngle", "360");
			root.setAttribute("showBorder", "0");
			root.setAttribute("startingAngle", "70");
		} else if (fusionChart.getViewMode().equals("area")) {
			root.setAttribute("palette", "2");
			root.setAttribute("xAxisName", fusionChart.getXAxisName());
			root.setAttribute("yAxisName", fusionChart.getYAxisName());
			root.setAttribute("yAxisMinValue", "15000");
			root.setAttribute("numberPrefix", "$");
			root.setAttribute("showValues", "0");

		} else if (fusionChart.getViewMode().equals("doughnut")) {
			root.setAttribute("palette", "2");
			root.setAttribute("showBorder", "1");
		}
		root.setAttribute("outCnvbaseFontSize", "12");
		root.setAttribute("baseFontSize", "12");
		root.setAttribute("formatNumberScale", "0");

		for (int i = 0; i < fusionChart.getDataList().size(); i++) {
			try {
				List subList = (List) fusionChart.getDataList().get(i);
				Element element = doc.createElement("set");
				element.setAttribute("label", subList.get(0).toString());
				element.setAttribute("value", subList.get(1).toString());
				root.appendChild(element);
			} catch (Exception e) {
			}
		}

		doc.appendChild(root);
		OutputFormat of = new OutputFormat("XML", "UTF-8", false);
		of.setLineSeparator(null);
		of.setDoctype(null, null);
		of.setEncoding("UTF-8");
		XMLSerializer serializer = new XMLSerializer(sw, of);
		try {
			serializer.serialize(doc);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sw.toString();
	}
	
}
