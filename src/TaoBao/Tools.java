package TaoBao;

/*
 * 一些淘宝的api接口
 * 一些需要使用到的变量跟函数
 * 2019年1月5日18:53:27
 * */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import TianMao.TianMaoBabyInfo;
import TianMao.TianMaoShopInfo;
import TianMao.TianMaoShopInfoValue;

public class Tools {
	protected final static String TAOBAO_SEARCH_LINK = "https://s.taobao.com/search?q=",
			TAOBAO_SORT_SALESVOLUME_TOP = "&sort=sale-desc", 
			TAOBAO_SORT_CREDIT_TOP = "&sort=credit-desc",
			TAOBAO_NEXT_PAGE = "&s=",
			TAOBAO_SHOP_SEARCH_LINK = "https://shopsearch.taobao.com/search?app=shopsearch&q=",
			TAOBAO_SET_CITY = "&loc=",
			TIANMAO_LINK = "https://list.tmall.com/search_product.htm?", 
			TIANMAO_SEARCH_LINK = "&q=",
			TIANMAO_SEARCH_SHOP_LINK_END = "&style=w", 
			TIANMAO_SORT_SALESVOLUME_TOP = "&sort=d",
			TIANMAO_SORT_CREDIT_TOP = "&sort=rq",
			TIANMAO_NEXT_PAGE = TAOBAO_NEXT_PAGE;
	protected String str = "", line = " ", tmp = " ";
	protected int len = -1, num = -1, timeout = 13000, TAOBAO_BABY_NEXT_VALUE = 44, TAOBAO_SHOP_NEXT_VALUE = 20 , TIANMAO_BABY_NEXT_VALUE = 60 , TIANMAO_SHOP_NEXT_VALUE = TAOBAO_SHOP_NEXT_VALUE , start , end , max;

	protected String arry[] = null;

	private String ua_bak = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36",
			cookie_bak = "";
	protected String cookie = "";
	protected String ua = "";
	
	protected TaoBaoBabyInfo babyinfo, babyinfoArray[];
	protected TaoBaoShopInfo shopinfo, shopinfoArray[];
	protected TaoBaoShopInfoValue shopinfovalue;
	protected TianMaoBabyInfo tianMaoBabyInfo , tianMaoBabyInfoArray[];
	protected TianMaoShopInfo tianMaoShopInfo , tianMaoShopInfoArray[];
	protected TianMaoShopInfoValue tianMaoShopInfoValue;
	
	protected void write(String path, String data) {
		write(path, "data.dat", data);
	}

	protected void write(String path, String name, String data) {
		File file = new File(path);
		BufferedWriter bw = null;
		if (!file.exists()) {
			file.mkdirs();
			write(path, name, data);
		} else {
			try {
				bw = new BufferedWriter(new FileWriter(file + "/" + name, true));
				bw.write(data);
				bw.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					bw.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}

		}
	}

	protected String StringToEncode(String src, String encode) {
		str = "";
		try {
			src = new String(src.getBytes(), encode);
			str = src;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;

	}

	protected String read(String path) {
		tmp = " ";
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(path)));
			while ((line = br.readLine()) != null) {
				tmp += line + "\n";
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tmp;
	}

	protected String checkURL(String url_name) // 配置传进来的超链接(url)
	{
		str = url_name;
		if (url_name.indexOf("http://") != -1) {
			tmp = "https://" + str;
		} else if (url_name.indexOf("https://") == -1) {
			tmp = "https://" + str;
		} else {
			tmp = url_name;
		}
		return tmp;
	}

	protected HttpURLConnection checkCon(String url_name, String cookie) throws Exception // 配置请求参数
	{
		return checkCon(url_name, cookie, ua_bak);
	}

	protected HttpURLConnection checkCon(String url_name, String cookie, String ua) throws Exception // 配置请求参数
	{
		tmp = checkURL(url_name);
		HttpURLConnection url_con = (HttpURLConnection) new URL(tmp).openConnection();
		url_con.setRequestMethod("GET");
		url_con.setRequestProperty("content-encoding", "gzip");
		url_con.setRequestProperty("content-language", "zh-CN");
		url_con.setRequestProperty("content-type", "text/html;charset=UTF-8");
//		url_con.setConnectTimeout(4000);
//		url_con.setReadTimeout(4000);
		url_con.setRequestProperty("accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		url_con.setRequestProperty("accept-language", "zh-CN,zh;q=0.9");
		if(!getUserAgent().equals(""))
		{
			url_con.setRequestProperty("user-agent", getUserAgent());
		}else
		{
			url_con.setRequestProperty("user-agent", ua);
		}
		if(!getCookie().equals(""))
		{
			url_con.setRequestProperty("cookie", getCookie());
		}else
		{
			url_con.setRequestProperty("cookie", cookie);
		}
		
		url_con.setRequestProperty("referer", "https://www.taobao.com/");

		return url_con;
	}

	protected HttpURLConnection checkCon(String url_name) throws Exception // 配置请求参数
	{
		return checkCon(url_name, cookie_bak, ua_bak);
	}

	protected String getPageSource(String url_name) // 获取网页源码内容
	{
		return getPageSource(url_name, cookie_bak, ua_bak);
	}

	protected String getPageSourceEncode(String url_name, String encode) // 获取网页源码内容
	{
		return getPageSourceEncode(url_name, encode,cookie_bak,ua_bak);
	}
	
	protected String getPageSourceEncode(String url_name, String encode , String cookie) // 获取网页源码内容
	{
		return getPageSourceEncode(url_name, encode, cookie,ua_bak);
	}
	
	protected String getPageSourceEncode(String url_name, String encode , String cookie , String ua) // 获取网页源码内容
	{
		try {
			InputStream input = checkCon(url_name,cookie,ua).getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(input, encode)); // 强行转成utf-8编码
			while ((line = br.readLine()) != null) {
				tmp += line + "\n";
			}
			br.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return tmp;
	}
	
	
	protected String getPageSource(String url_name, String cookie) // 获取网页源码内容
	{
		return getPageSource(url_name, cookie, ua_bak);
	}

	protected String getPageSource(String url_name, String cookie, String ua) // 获取网页源码内容
	{
		try {
			InputStream input = checkCon(url_name, cookie, ua).getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(input, "utf-8")); // 强行转成utf-8编码
			while ((line = br.readLine()) != null) {
				tmp += line + "\n";
			}
			br.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return tmp;
	}

	protected String StringToURLEncode(String s) // 这三个都是字符串转码用的
	{
		tmp = s;
		try {
			s = URLEncoder.encode(tmp, "utf-8");
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
		return s;
	}

	protected String UnicodeToString(String str) {

		Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
		Matcher matcher = pattern.matcher(str);
		char ch;
		while (matcher.find()) {
			ch = (char) Integer.parseInt(matcher.group(2), 16);
			str = str.replace(matcher.group(1), ch + "");
		}
		return str;
	}

	protected String URLEncodeToString(String en) {
		tmp = en;
		try {
			en = URLDecoder.decode(tmp, "utf-8");
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
		return tmp;
	}
	
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public String getCookie() {
		return cookie;
	}

	public void setUserAgent(String ua) {
		this.ua = ua;
	}

	public String getUserAgent() {
		return ua;
	}
	
	
}
