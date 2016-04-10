package com.cnu.wlx.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import sun.misc.BASE64Encoder;

public class WebUtils {
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
		"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	private final static String CONTEXT_URL="http://10.4.57.247:8080/wlxdoor/";
	/*将表单类中的信息拷贝到对应的类中 */
	public static void copyBean(Object dest,Object src){
		// 为了把RegisterForm中String类型的birthday属性转换到User中的Date类型birthday属性
				// 需要注册转换器 (String - Date)
			/*	ConvertUtils.register(new Converter() {
					
					public Object convert(Class type, Object value) {
						// TODO Auto-generated method stub
						if(null== value)
							return null;
						String str = (String) value;
						if( null== str || str.trim().equals(""))
							return null;
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						try {
							return sdf.parse(str);
						} catch (ParseException e) {
							throw new RuntimeException(e);
						}
					}
				}, Date.class);
*/
				try {
					// 通过BeanUtils类调用方法将表单类中的信息拷贝到对应的类中 ，拷贝过程中只支持8中基本数据类型
					BeanUtils.copyProperties(dest, src);
					
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
	}
	
	/**
	 * 获取日期格式字符串
	 * @param date  日期
	 * @param format 日期格式：为空则默认为"yyyy-MM-dd HH:mm:ss"
	 * @return 
	 */
	public static String getFormatDate(Date date,String format)
	{
		if( format == null)
		 format = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		
		return sdf.format(date);
	}
	
	/**
	 * 获取根目录 http://192.168.253.1:8080/wlxdoor/
	 * @return
	 */
	public static String getContextUrl()
	{
		
		return WebUtils.CONTEXT_URL;
	}
	/**
	 * 得到webContent目录
	 * @return
	 */
	public static File  getWebContentPath()
	{
		String pathString =WebUtils.class.getClassLoader().getResource("").getPath();
		File file = new File(pathString);
		File parentfile = file.getParentFile();
		File pFile = parentfile.getParentFile();
		return pFile;
	}
	public static void closeInputOutStream(InputStream is ,OutputStream os)
	{
		if( is !=null )
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("输入流关闭异常");
			}finally{
				if( os !=null )
					try {
						os.close();
					} catch (IOException e) {
						e.printStackTrace();
						throw new RuntimeException("输出流关闭异常");
					}
			}
	}
	// 获取信息的md5值的base64编码
		public static String getMd5(String message)
		{
			try {
			if( null == message)
			return "";
			MessageDigest md = MessageDigest.getInstance("md5");    // MD5算法
			byte[] md5 = md.digest(message.getBytes());
		
			BASE64Encoder encoder = new BASE64Encoder();   // 利用Base64编码成字符
			return encoder.encode(md5);
			} 
			catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
			}
		}
		/**根据两个账号生成唯一的id,会话id根据两方账号链接起来在经过md5编码生成,
		 * 两个账号链接策略：a,b账号中字符串比较大的在前面小的放在后面，在链接两个字符串中间会添加其他字符来保证唯一性。
		 * @param accounta
		 * @param accountb
		 * @return
		 */
		public static String getUniteId(String accounta, String accountb) {
			
			try {
				if( null == accounta|| null == accountb)
					return "";
				String message = "";
				if(accounta.compareTo(accountb)>=0)
				{
					message+=accounta;
					message+="@#$%^&*ZHUshou"; //加盐
					message+=accountb;
				}else {
					message+=accountb;
					message+="@#$%^&*ZHUshou";
					message+=accounta;
				}
				
				return MD5Encode(message);
				} 
				catch (Exception e) {
				throw new RuntimeException(e);
				}
		}
	/**
	 * 转换字节数组为16进制字串
	 * 
	 * @param b
	 *            字节数组
	 * @return 16进制字串
	 */

	public static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	/**
	 * url路径中的MD5编码，以防出现“=”号
	 * @param origin 源数据
	 * @return MD5数据，不含有"="可以放在连接路径中以防产生错误参数
	 */
	public static String MD5Encode(String origin) {
		String resultString = null;

		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString
					.getBytes()));
		} catch (Exception ex) {

		}
		return resultString;
	}
/**
 * 获取后缀名，不包含小数点
 * @param filename
 * @return
 */
    public static String getExtFromFilename(String filename) {
        int dotPosition = filename.lastIndexOf('.');
        if (dotPosition != -1) {
            return filename.substring(dotPosition + 1, filename.length());
        }
        return "";
    }

    public static String getNameFromFilename(String filename) {
        int dotPosition = filename.lastIndexOf('.');
        if (dotPosition != -1) {
            return filename.substring(0, dotPosition);
        }
        return "";
    }

    public static String getPathFromFilepath(String filepath) {
        int pos = filepath.lastIndexOf('/');
        if (pos != -1) {
            return filepath.substring(0, pos);
        }
        return "";
    }

    public static String getNameFromFilepath(String filepath) {
        int pos = filepath.lastIndexOf('/');
        if (pos != -1) {
            return filepath.substring(pos + 1);
        }
        return "";
    }
    //获取文件每页显示多少
    public static Integer getFilePageSize()
    {
    	return 5;
    }
    // storage, G M K B
    public static String convertStorage(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;

        if (size >= gb) {
            return String.format("%.1f GB", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
        } else
            return String.format("%d B", size);
    }
}
