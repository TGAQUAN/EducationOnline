package cn.wxj.util;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/** 工具类 */
public class Tools {
	//创建日期格式
	public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat df_ymdhms = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
	//创建中国的货币格式
	public static NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.CHINA);	

	/** FCKeditor内置的分页字符串 */
	private static String fck_separator="<div style=\"PAGE-BREAK-AFTER: always\"><span style=\"DISPLAY: none\">&nbsp;</span></div>";
	
	/** 取得指定图片的宽度与高度 */
	public static Map getPicWidthHeight(String filename){
		Map map = new HashMap();
		try {
			BufferedImage sourceImg = javax.imageio.ImageIO.read(new FileInputStream(filename));
			map.put("width", sourceImg.getWidth());
			map.put("height", sourceImg.getHeight());
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	//取得当前时间
	public static String getNow() {  
		return df_ymdhms.format(new Date());
	}	
	
	 /** 计算两个日期相差的天数 */
	public static String getDate(){
		  SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		  Date dd = new Date();
		  return ft.format(dd);
		 }
		 public static long getQuot(String time1, String time2){
		  long quot = 0;
		  SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		  try {
		   Date date1 = ft.parse( time1 );
		   Date date2 = ft.parse( time2 );
		   quot = date1.getTime() - date2.getTime();
		   quot = quot / 1000 / 60 / 60 / 24;
		  } catch (ParseException e) {
		   e.printStackTrace();
		  }
		  return quot;
		 }


	public static String add(String str1,String str2){
		BigDecimal b1 = new BigDecimal(str1);
		BigDecimal b2 = new BigDecimal(str2);
		//System.out.println("两个String数相加得："+b1.add(b2).toString());
		//String str3 = b1.add(b2).toString();
		return b1.add(b2).toString();
	}

	public static String subtract(String str1,String str2){
		BigDecimal b1 = new BigDecimal(str1);
		BigDecimal b2 = new BigDecimal(str2);
		//System.out.println("两个String数相减得："+b1.subtract(b2).toString());
		return b1.subtract(b2).toString();
	}

	
	/** 取得随机主文件名 */
	public synchronized static String getRndFilename(){
		return String.valueOf(System.currentTimeMillis());
	}
	
	public synchronized static String getFileSName(String filename){
		int p = filename.indexOf(".");
		return filename.substring(0,p);
	}
	
	/** 取得指定文件的文件扩展名 */
	public synchronized static String getFileExtName(String filename){
		int p = filename.indexOf(".");
		return filename.substring(p);
	}
	
	 /**创建新的文件
	 * @throws IOException */
	 public static void createFile(String filepath) throws IOException{
	        File file=new File(filepath);
	        if(!file.exists())
	            file.createNewFile();
	    }
	
	/** 验证上传文件的类型是否合法 fileType:1-图片 2-视频*/
	public synchronized static boolean isEnableUploadType(int fileType,String filename){
		String enableExtNames = null;
		int p = filename.indexOf(".");
		String fileExtName = filename.substring(p).toLowerCase();
		if (fileType==1){//图片文件类型
			enableExtNames = ".jpg,.gif,.png";
		}else if (fileType==2){//视频文件类型
			enableExtNames = ".flv";
		}
		if (enableExtNames!=null){
			if (enableExtNames.indexOf(fileExtName)!=-1)return true;
			else return false;			
		}else{
			return true;
		}

	}	
	
	/** HTML代码的Escape处理方法 */
	public static String  escape(String src){
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length()*6);
		for (i=0;i<src.length();i++){
			j = src.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j)) 
				tmp.append(j);
			else if(j<256){
				tmp.append( "%" );
				if (j<16)tmp.append("0");
				tmp.append( Integer.toString(j,16));
			}else{
				tmp.append("%u");
				tmp.append(Integer.toString(j,16));
			}
		}
		return tmp.toString();
	}
	
	/** HTML代码的UnEscape处理方法 */
	public static String  unescape(String src){
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos=0,pos=0;
		char ch;
		while(lastPos<src.length()){
			pos = src.indexOf("%",lastPos);
			if (pos == lastPos){
				if (src.charAt(pos+1)=='u'){
					ch = (char)Integer.parseInt(src.substring(pos+2,pos+6),16);
					tmp.append(ch);
					lastPos = pos+6;
				}else{
					ch = (char)Integer.parseInt(src.substring(pos+1,pos+3),16);
					tmp.append(ch);
					lastPos = pos+3;
				}
			}else{
				if (pos == -1){
					tmp.append(src.substring(lastPos));
					lastPos=src.length();
				}else{
					tmp.append(src.substring(lastPos,pos));
					lastPos=pos;
				}
			}
		}
		return tmp.toString();
	}
	
	/** 为以逗号分隔的字符串的每个单元加入引号,如:aa,bb-->'aa','bb' */
	public static String formatString(String src){
		StringBuffer result = new StringBuffer();
		result.append("");
		if (src!=null){
			String[] tmp = src.split(",");
			result.append("'"+tmp[0]+"'");
			for(int i=1;i<tmp.length;i++){
				result.append(",'"+tmp[i]+"'");
			}
		}		
		return result.toString();
	}	
	
    /** 截取指定字节数的字符串,且确保汉字不被拆分 */
	public static String cutString(String text, int textMaxChar){   
        int size,index;   
        String result = null;  
        if(textMaxChar<=0){   
        	result= text;   
        }else{   
            for(size=0,index=0;index<text.length()&&size<textMaxChar;index++){   
                size += text.substring(index,index+1).getBytes().length;   
            }   
            result = text.substring(0,index);   
        }  
        return result;   
    }
	
    /** 按yyyy-MM-dd格式格式化日期 */
	public static String formatDate(Date date){   
		if (date==null){
			return "";
		}else{
			return df.format(date);
		}
    }
	
	public static String formatAllDate(Date date){   
		if (date==null){
			return "";
		}else{
			return df_ymdhms.format(date);
		}
    }	
	
    /** 对未escape的HTML内容进行FCK分页处理,返回String[] */
	public static String[] splitContent(String unEscapedHtml){ 
		if (unEscapedHtml==null){
			return null;
		}else{
			return unescape(unEscapedHtml).split(fck_separator);
		}
	}
	
	/** 取得格式化后的中国货币字符串 */
	public static String formatCcurrency(double money){
		return currencyFormat.format(money);   		

		
	}

	/** 字符串转换编码 */
	public static String anyToGBK(String fromCode,String toCode,String str){
		if (str==null)return null;
		try {
			if (fromCode==null){
				return new String(str.getBytes(),toCode);
			}else{
				return new String(str.getBytes(fromCode),toCode);
			}
		} catch (Exception e) {
			return str;
		}
	}
	
	/** 获取昨天、明天日期 */
	public static String getToMoYe(int a){
		 Date date=new Date();//取当前时间
		 Calendar calendar = new GregorianCalendar();
		 calendar.setTime(date);
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		//如果传过来的参数是正整数1，则获取明天的日期，负整数-1,则获取的是昨天的日期
		if (a==1) {
			 calendar.add(calendar.DATE,1);
		}else if(a==-1){
			 calendar.add(calendar.DATE,-1);
		}else {
			return null;
		}
		 date=calendar.getTime();
		 String dayString = formatter.format(date);	 
		 return dayString;
	}
	
	 //String类型转int类型
	   public static int stringToInt(String intstr)
	   {
	     Integer integer;
	     integer = Integer.valueOf(intstr);
	     return integer.intValue();
	   }
	   //int类型转String
	   public static String intToString(int value)
	   {
	     Integer integer = new Integer(value);
	     return integer.toString();
	   }
	
	   //string类型转float类型
	   public static float stringToFloat(String floatstr)
	   {
	     Float floatee;
	     floatee = Float.valueOf(floatstr);
	     return floatee.floatValue();
	   }
	   //float类型转string类型
	   public static String floatToString(float value)
	   {
	     Float floatee = new Float(value);
	     return floatee.toString();
	   }
	   
	   
	   //清除html标签 	
	   public static String delHTMLTag(String htmlStr){ 
	         String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式 
	         String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式 
	         String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式 
	         
	         Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
	         Matcher m_script=p_script.matcher(htmlStr); 
	         htmlStr=m_script.replaceAll(""); //过滤script标签 
	         
	         Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
	         Matcher m_style=p_style.matcher(htmlStr); 
	         htmlStr=m_style.replaceAll(""); //过滤style标签 
	         
	         Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
	         Matcher m_html=p_html.matcher(htmlStr); 
	         htmlStr=m_html.replaceAll(""); //过滤html标签 

	        return htmlStr.trim(); //返回文本字符串 
	     } 
	
	   /** 把QQ号码加到文件的最后 */
		@SuppressWarnings("unchecked")
		public static void addQQToFile(String textfile1, Set qq_Set) {
			BufferedWriter bw = null;
			Set qqSet = new LinkedHashSet();	
				try {				
					for(Object m:qq_Set){
    					String[] number =(m.toString()).split("@qq");		    							    			      
    					qqSet.add(number[0].toString());		
    				}
					
					// 读取文件
					BufferedReader br = new BufferedReader(new FileReader(new File(textfile1)));
					qq_Set = new LinkedHashSet();				
					String line = null;
					while ((line = br.readLine()) != null) {
						if (line.trim().length()>0) {
							qq_Set.add(line);
						}
						
					}
					if (br != null)
						br.close();
					qq_Set.addAll(qqSet);
					bw = new BufferedWriter(new FileWriter(new File(textfile1)));
					for (Object m : qq_Set) {
						bw.write(m + "\r\n");
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (bw != null)bw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			
		}
		
		/** HTML代码的Escape处理方法 --编码(配套fckEditor新闻编辑器)*/
		public static String  escape2(String src){
			int i;
			char j;
			StringBuffer tmp = new StringBuffer();
			tmp.ensureCapacity(src.length()*6);
			for (i=0;i<src.length();i++){
				j = src.charAt(i);
				if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j)) 
					tmp.append(j);
				else if(j<256){
					tmp.append( "%" );
					if (j<16)tmp.append("0");
					tmp.append( Integer.toString(j,16));
				}else{
					tmp.append("%u");
					tmp.append(Integer.toString(j,16));
				}
			}
			return tmp.toString();
		}
		
		/** HTML代码的UnEscape处理方法--解码(配套fckEditor新闻编辑器) */
		public static String  unescape2(String src){
			StringBuffer tmp = new StringBuffer();
			tmp.ensureCapacity(src.length());
			int lastPos=0,pos=0;
			char ch;
			while(lastPos<src.length()){
				pos = src.indexOf("%",lastPos);
				if (pos == lastPos){
					if (src.charAt(pos+1)=='u'){
						ch = (char)Integer.parseInt(src.substring(pos+2,pos+6),16);
						tmp.append(ch);
						lastPos = pos+6;
					}else{
						ch = (char)Integer.parseInt(src.substring(pos+1,pos+3),16);
						tmp.append(ch);
						lastPos = pos+3;
					}
				}else{
					if (pos == -1){
						tmp.append(src.substring(lastPos));
						lastPos=src.length();
					}else{
						tmp.append(src.substring(lastPos,pos));
						lastPos=pos;
					}
				}
			}
			return tmp.toString();
		}
	   
	   
	public static void main(String[] args){
		String qqnumber="9999999@qq.com,8888888@qq.com,7777777777777@qq.com,6666666@qq.com,55555555@qq.com";
		String[] qq_number = qqnumber.split(",");
		Set qq_Set = new LinkedHashSet();	
		for(String qq:qq_number){
			qq_Set.add(qq);
			
		}
		addQQToFile("E:/zhijie.txt",qq_Set);
//		System.out.println(escape(""));
//		System.out.println(""+new Date());
//		System.out.println("昨天的日期为"+getToMoYe(-1));
//		System.out.println("明天的日期为"+getToMoYe(1));
//		 Date date=new Date();
//
//		   SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
//
//		  String xingqiString= dateFm.format(date);
//		  System.out.println("今天是："+xingqiString);
		
		//System.out.println("=====================================页号");
        //获取当前时间的上个月
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		String preMonth = dateFormat.format(c.getTime());
		System.out.println("上月时间==================== "+preMonth);
		
	}
}

