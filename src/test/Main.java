import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;

import com.laungee.proj.common.util.DecryptUtil;

public class Main {

	/**
	 * @param args
	 */
	private  static Date getBirthDay(String arg0){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd");
		Date date=null;
		try {
			date = sdf.parse(arg0);
		} catch (Exception e) {
		} 
		return date;
	}
	private static Date getSchoolDay(String arg0){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
		Date date=null;
		try {
			date = sdf.parse(arg0);
		} catch (Exception e) {
		} 
		return date;
	}
	private static Date getBirthDay(String arg0,String arg1){
		SimpleDateFormat sdf=new SimpleDateFormat(arg1);
		Date date=null;
		try {
			date = sdf.parse(arg0);
		} catch (Exception e) {
		} 
		return date;
	}
	//截取分页参数
	public static String subpagepar(String strs){
		String temp[]=strs.split("&");
		  String str="";
		  String num="-1";
		  for(int i=0;i<temp.length;i++){
			   if(temp[i]!=null&&!temp[i].equals("")){ 
			   String sub=temp[i].substring(0,temp[i].indexOf("=")+1);
			   for(int j=i+1;j<temp.length;j++){
				   String sub1=temp[j].substring(0,temp[j].indexOf("=")+1); 
				   if(sub.equals(sub1)){
				    	 num=i+"";
					}
			   }
			   if(!num.equals(i+"")){
		    		  str+=temp[i]+"&";  
		    	} 
			  }
			  
		  }
		return str;
	}
	//去掉空格
	public static String subspace(String str){
		String str2 = ""; 
		if(str!=null&&!str.equals("")){
        String[] str1 = str.split("\\s{1,}"); 
        for(int i = 0;i<str1.length;i++)
        {
            if(str1[i] != "") 
            {
                str2 += str1[i]; 
            }
        }
		}
		return str2;
	}
	 private static String formatstr(HSSFRow row,int j){
	    	NumberFormat nf = NumberFormat.getInstance();
			nf.setGroupingUsed(false);
	    	String cell="";
	    	try {
				 cell= subspace(row.getCell((short) j).toString());
				//读取公式值
				if(HSSFCell.CELL_TYPE_FORMULA == row.getCell((short)j).getCellType()){
					   double a=row.getCell((short) j).getNumericCellValue();
					   cell=new Double(a).toString();
				}
				if(!cell.startsWith("0")){
					if(cell.endsWith(".0")){
				    if(cell.matches("^\\d*.0$")){
				    //只有是.0格式才去除
				    if(cell.substring(cell.indexOf("."),cell.length()).length()==2){
					 cell=cell.substring(0,cell.lastIndexOf(".0"));
				    }
				  }
				}
				//科学计数法
				if(cell.matches("[\\+\\-]?[\\d]+([\\.][\\d]*)?([Ee][+-]?[\\d]+)?")){
					double d = Double.parseDouble(cell); 
					cell=nf.format(d);
				}
			  }
			}catch(Exception e) {
				cell="";
			}
	    	return cell;
	    }
	public static void main(String[] args) throws Exception {
//		FileInputStream fOut = new FileInputStream("D://北京市_校友信息.xls"); 
//
//		POIFSFileSystem fs= new POIFSFileSystem(fOut);
//		HSSFWorkbook workbook = new HSSFWorkbook(fs);
//		HSSFSheet sheet = workbook.getSheetAt(0);
//		HSSFRow rowtitile = sheet.getRow(0);
//		Scanner scan = null ;
//		
//		for (int j1 = 1; j1 < sheet.getPhysicalNumberOfRows(); j1++) {
//			  // 行
//	        HSSFRow row = sheet.getRow(j1);
//	    	for(int temp=1;temp<=rowtitile.getPhysicalNumberOfCells();temp++){
//				 String breakflag=formatstr(rowtitile,temp);
//				 if(breakflag!=null&&breakflag.equals("通信地址")){
//					 String strs=row.getCell((short) temp).toString();
//					 System.out.println(strs);	
//				 }
//	    	}
//		}
//		String str="11|22|33|44|55|66|77|88|99|10|11|12|13|14|15|16|17|18|19|20|21|22";
//		String[]  aString=  str.split("[|]");
//		System.out.println("strSplit==="+aString.length);
//		String newStr="";
//		for(int i=1; i<=aString.length;i++)
//		{
//			newStr +=aString[i-1];
//			if(i!=0 && i%7==0)
//			{
//				System.out.println("i--"+i);
//				System.out.println("aString[i]===i==="+aString[i]+"===="+i);
//				newStr +="\r\t";
//			}
//		}
//		System.out.println("\t"+newStr);
//		StringBuffer str = new StringBuffer(
//		"select distinct b from TbTelephoneAlumni a left join a.tbTelephone b left join a.tbAlumni c where (a.isRepeat=0 or a.isRepeat is null) and ");
//		System.out.println(str.length());
//		System.out.println(str.lastIndexOf("and "));
//		if(str.length()-4==str.lastIndexOf("and ")){
//			System.out.println(str.substring(0,str.lastIndexOf("and ")));
//		}
//		String str="[南开大学校友总会]帐户激活通知！";
//		System.out.println(str.indexOf('['));
//		System.out.println(str.indexOf(']'));
//		str=str.substring(str.indexOf('[')+1,str.indexOf(']'));
//		System.out.println(str);
//		OutputStream os=new FileOutputStream(file);
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date st=sdf.parse("2013-02-26 10:06:00");
//    	Date now=sdf.parse(sdf.format(new Date()));
//    	System.out.println(st.before(now));
		//System.out.println(getWeek("2013-1-26"));
//		LinkedList ld=new LinkedList();
//		ld.add("123");
//		ld.add("456");
//		ld.add("000");
//		ld.add("000");
//		System.out.println(ld.contains("0000"));
		
//		Long l=new Long(5);
//		l--;
//		System.out.println(l);
//		
//		System.out.println("test@adf.com".substring(0,"test@adf.com".indexOf("@")));
		
//		String str="Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; SE 2.X MetaSr 1.0)";
//		Pattern p = Pattern.compile("MSIE", Pattern.MULTILINE);
//
//		Matcher m = p.matcher(str);
//		System.out.println(m.find());
//		p = Pattern.compile("SE[ ]+(\\d\\.[X])+", Pattern.MULTILINE);
//
//		m = p.matcher(str);
//		System.out.println(m.find());
//
//		p = Pattern.compile("MetaSr[ ]+(\\d\\.)+", Pattern.MULTILINE);
//
//		m = p.matcher(str);
//		System.out.println(m.find());
//		Float tb=null;
//		BigDecimal januaryNowValue=new BigDecimal("0");
//		BigDecimal yesterNowValue=new BigDecimal("10");
//		if(yesterNowValue!=null&&!yesterNowValue.equals(new BigDecimal("0"))){
//			tb=((januaryNowValue.subtract(yesterNowValue)).divide(yesterNowValue,4,BigDecimal.ROUND_HALF_EVEN)).multiply(new BigDecimal(100)).floatValue();
//		}
//		System.out.println(tb);
		
//		String str="456,123,789,123";
//		//System.out.println(str.substring(0,str.lastIndexOf(",")));
//		//System.out.println(str.replaceAll(",123", ""));
//		StringBuffer logContent=new StringBuffer("<h4 style=\"margin-top:5px;\"><b>校友基本信息</b></h4><div style=\"margin:5px 0px 5px 10px;\">");
//		StringBuffer tempstr=new StringBuffer("<h4 style=\"margin-top:5px;\"><b>校友基本信息</b></h4><div style=\"margin:5px 0px 5px 10px;\">");
//		//logContent.append("123");
//		System.out.println(tempstr.toString().equalsIgnoreCase(logContent.toString()));
//    	System.out.println(StringUtils.startsWithIgnoreCase("islive", "Tb"));
//		String str="地主工在,上下斯柯!达晶锐载，地七.苦一工";
//		System.out.println(str.replaceAll("\\p{Punct}", ","));
//		str+="11111111";
//		System.out.println(str.contains("地主1"));
		//[[:punct:]]
		//\\p{Punct}
		//[\\pP‘’“”]--去所有标点，包括中英文
//		String str="select distinct a from tbaadf";
//		System.out.println(str.substring(str.indexOf("select ")+"select ".length(),str.indexOf(" from")));
//		System.out.println(str.indexOf("select "));
		
		//阶乘
//		System.out.print("请输入数字：");
//		Scanner s=new Scanner(System.in);
//		int num=s.nextInt();
//		System.out.println(num+"阶乘后的数字为："+GetResult(new Long(num)));
//		String str1=System.currentTimeMillis()+"";
//		System.out.println("--:"+str1.substring(5));
//		System.out.println("--:"+str1);
//		File file=new File("F:\\work\\code\\new\\PALUMNI_NK\\PALUMNI_NK\\WebRoot\\upload\\temp");
//		File[] files=file.listFiles();
//		if(files!=null&&files.length!=0){
//			for (int i = 0; i < files.length; i++) {
//				File tmpFile=files[i];
//				if(tmpFile!=null){
//					if(tmpFile.getName().toLowerCase().endsWith(".png")
//							||tmpFile.getName().toLowerCase().endsWith(".jpg")
//							||tmpFile.getName().toLowerCase().endsWith(".jpeg")
//							||tmpFile.getName().toLowerCase().endsWith(".gif")
//							||tmpFile.getName().toLowerCase().endsWith(".bmp")){
//						tmpFile.delete();
//					}
//				}
//			}
//		}
//		String strtmp="=C1";
//		for (int i = 2; i <= 1000; i++) {
//			strtmp+="&C"+i;
//		}
//		System.out.println(strtmp);
		
//		SimpleDateFormat format=new SimpleDateFormat("y-M-d");
//		System.out.println(format.format(new Date()));
		

//		SimpleDateFormat sdfMonth=new SimpleDateFormat("MM-dd");
//        Calendar ca=Calendar.getInstance();
//        ca.add(Calendar.DATE, -2);
//        Date times=ca.getTime();
//        String now=sdfMonth.format(times);
//        System.out.println(now);
		
//		Method[] ms=TbAlumni.class.getMethods();
//		for (int i = 0; i < ms.length; i++) {
//			Method tmp=ms[i];
//			Class[] cls=tmp.getParameterTypes();
//			if(cls.length>0){
//				System.out.println(cls[0].getSimpleName());
//			}
//		}

//		String[] fields={"123","456","789","a","b"};
//		System.out.println();
//		Method[] ms=TbAlumni.class.getMethods();
//		String[] filename=new String[ms.length/2];
//		int ti=0;
//		for (int i = 0; i < ms.length; i++) {
//			Method m=ms[i];
//			String name=m.getName();
//			if(name.startsWith("get")){
//				name=name.substring(3);
//				if(!name.equalsIgnoreCase("alumniid")&&!"namecn".equalsIgnoreCase(name)&&!"handsetfirst".equalsIgnoreCase(name)){
//					filename[ti++]=name;
//				}
//			}
//		}
		

//		//循环TbAlumni里面的set方法
//		Method[] meths=TbAlumni.class.getDeclaredMethods();
//		if(meths!=null&&meths.length!=0){
//			for (int i = 0; i < meths.length; i++) {
//				Method md=meths[i];
//				String mName=md.getName();
//				if(mName.indexOf("AlumniId")==-1){
//					System.out.println(mName);
//				}
//			}
//		}
		
//		String repeat=" (isRepeat=0 or isRepeat is null) ";
//		String sql="select * from asdb where 1=1 and a in (select * from tb_adasf a where 1=1) order by 1asdf";
//		if(sql.indexOf("order by")==-1){
//			//如果没有条件where
//			if(sql.indexOf("where")==-1){
//				sql+=" where "+repeat;
//			}else{
//				sql=sql.substring(0,sql.indexOf(" where ")+7)+repeat+" and "+sql.substring(sql.indexOf(" where ")+7);
//			}
//		}else{
//			String sql1=sql.substring(0,sql.indexOf("order by"));
//			String order=sql.substring(sql.indexOf("order by"));
//			//如果没有条件where
//			if(sql.indexOf("where")==-1){
//				sql=sql1+" where "+repeat+" "+order;
//			}else{
//				sql=sql.substring(0,sql.indexOf(" where ")+7)+repeat+" and "+sql.substring(sql.indexOf(" where ")+7);
//			}
//		}
//		System.out.println(sql);
		
//		String s="123123123.1123012";
//		System.out.println(s.length()-s.lastIndexOf(".")-1);
//		
//		NumberFormat df = new DecimalFormat("#,###.00");
//		System.out.println(df.format(123123123.012));
//		System.out.println(DecryptUtil.decrypt("4872A7839810F34FF1EC2F01630FB4B4"));
//		System.out.println(DecryptUtil.encrypt("assoinfomanager"));
		
		String str="select * from tb_foundation order by a.creation";
		System.out.println(str.substring(0,str.lastIndexOf("order")));
	}
	
	public static String GetResult(long n) {
		List<Long> listResult = new ArrayList<Long>();
		listResult.add(new Long(1));
		

		int posCount = 1;// 记录总位数
		for (long i = 1; i <= n; i++) {
			long carry = 0;
			for (int j = 0; j < posCount; j++) {
				// 先相乘
				listResult.add((listResult.get(j) * i));
				// 加上进位
				listResult.add((listResult.get(j) + carry));
				Long temp = listResult.get(j);
				// 存储个位数
				listResult.add((listResult.get(j) % 10));
				carry = temp / 10;
				if (carry > 0 && j == listResult.size() - 1) {
					posCount++;
					listResult.add(new Long(0));
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = listResult.size() - 1; i >= 0; i--) {
			sb.append(listResult.get(i).toString());
		}
		return sb.toString();
	}
	public static String getWeek(String date) throws ParseException{
		String[] weeks={"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
		// w=y+[y/4]+[c/4]-2c+[26(m+1）/10]+d-1
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String time=sdf.format(sdf.parse(date));
		// C
		int c=Integer.valueOf(time.substring(0,2));
		int y=Integer.valueOf(time.substring(2,4));
		int m=Integer.valueOf(time.substring(5,7));
		if(m<3){
			m+=12;
		}
		int d=Integer.valueOf(time.substring(8,10));
		//蔡勒公式
		int w=y+(y/4)+(c/4)-2*c+(26*(m+1)/10)+d-1;
		System.out.println(w);
		int i=((int)(w%7));
		return weeks[i];
	}

}
