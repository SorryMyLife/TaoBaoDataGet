package TaoBao;

/*
 * 提供淘宝主页面搜索结果
 * 允许你设置地区
 * 允许你通过信用度或者销量进行排序
 * 2019年1月5日15:29:50
 * */

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaoBaoBabySearch extends Tools implements TaoBao {
	private String city_name = "";
	private List<TaoBaoBabyInfo> list = null;

	private void printList(List<TaoBaoBabyInfo> l) {
		tmp = " ";
		len = l.size();
		for (int i = 0; i < len; i++) {
			tmp = "商品标题: " + l.get(i).getShopTitle() + "\n商品价格: " + l.get(i).getShopMoney() + "\n商品购买人数: "
					+ l.get(i).getGetNum() + "\n商品地址: " + l.get(i).getShopAddress() + "\n商店名称: "
					+ l.get(i).getShopName() + "\n商店id: " + l.get(i).getShopId() + "\n商品链接: " + l.get(i).getShopLink()
					+ "\n商品图片样式链接: " + l.get(i).getShopIco() + "\n";
			System.out.println(tmp);
		}
	}

	private String[] printListArray(List<TaoBaoBabyInfo> l) {
		tmp = " ";
		len = l.size();
		arry = new String[len];
		for (int i = 0; i < len; i++) {
			tmp = "商品标题: " + l.get(i).getShopTitle() + "\n商品价格: " + l.get(i).getShopMoney() + "\n商品购买人数: "
					+ l.get(i).getGetNum() + "\n商品地址: " + l.get(i).getShopAddress() + "\n商店名称: "
					+ l.get(i).getShopName() + "\n商店id: " + l.get(i).getShopId() + "\n商品链接: " + l.get(i).getShopLink()
					+ "\n商品图片样式链接: " + l.get(i).getShopIco() + "\n";
			arry[i] = tmp;
		}
		return arry;
	}

	private String printListString(List<TaoBaoBabyInfo> l) {
		tmp = "";
		str = "";
		len = l.size();
		for (int i = 0; i < len; i++) {
			tmp = "商品标题: " + l.get(i).getShopTitle() + "\n商品价格: " + l.get(i).getShopMoney() + "\n商品购买人数: "
					+ l.get(i).getGetNum() + "\n商品地址: " + l.get(i).getShopAddress() + "\n商店名称: "
					+ l.get(i).getShopName() + "\n商店id: " + l.get(i).getShopId() + "\n商品链接: " + l.get(i).getShopLink()
					+ "\n商品图片样式链接: " + l.get(i).getShopIco() + "\n";
			str += tmp + "\n";
		}
		return str;
	}

	private TaoBaoBabyInfo[] printShop(List<TaoBaoBabyInfo> l) {
		len = l.size();
		babyinfoArray = new TaoBaoBabyInfo[len];
		for (int i = 0; i < len; i++) {
			babyinfoArray[i] = l.get(i);
		}
		return babyinfoArray;
	}

	private String checkValue(String search_name, int page, String Top) {
		if (!getCity().equals("")) {
			if (!getCookie().equals("")) {
				str = getPageSource(TAOBAO_SEARCH_LINK + StringToURLEncode(search_name) + TAOBAO_SET_CITY
						+ StringToURLEncode(city_name) + Top + TAOBAO_NEXT_PAGE + page, getCookie());
			} else if (!getUserAgent().equals("")) {
				str = getPageSource(TAOBAO_SEARCH_LINK + StringToURLEncode(search_name) + TAOBAO_SET_CITY
						+ StringToURLEncode(city_name) + Top + TAOBAO_NEXT_PAGE + page, getUserAgent());
			} else {
				str = getPageSource(TAOBAO_SEARCH_LINK + StringToURLEncode(search_name) + TAOBAO_SET_CITY
						+ StringToURLEncode(city_name) + Top + TAOBAO_NEXT_PAGE + page);
			}
		} else if (!getCookie().equals("")) {
			if (!getCity().equals("")) {
				str = getPageSource(TAOBAO_SEARCH_LINK + StringToURLEncode(search_name) + TAOBAO_SET_CITY
						+ StringToURLEncode(city_name) + Top + TAOBAO_NEXT_PAGE + page, getCookie());
			} else if (!getUserAgent().equals("")) {
				str = getPageSource(TAOBAO_SEARCH_LINK + StringToURLEncode(search_name),
						getCookie() + Top + TAOBAO_NEXT_PAGE + page, getUserAgent());
			} else {
				str = getPageSource(TAOBAO_SEARCH_LINK + StringToURLEncode(search_name) + Top + TAOBAO_NEXT_PAGE + page,
						getCookie());
			}
		} else if (!getUserAgent().equals("")) {
			if (!getCookie().equals("")) {
				str = getPageSource(TAOBAO_SEARCH_LINK + StringToURLEncode(search_name) + Top + TAOBAO_NEXT_PAGE + page,
						getCookie(), getUserAgent());
			} else if (!getCity().equals("") || !getCookie().equals("")) {
				str = getPageSource(TAOBAO_SEARCH_LINK + StringToURLEncode(search_name) + Top + TAOBAO_SET_CITY
						+ StringToURLEncode(city_name) + TAOBAO_NEXT_PAGE + page, getCookie(), getUserAgent());
			}
		} else {
			str = getPageSource(TAOBAO_SEARCH_LINK + StringToURLEncode(search_name) + Top + TAOBAO_NEXT_PAGE + page);
		}
		return str;
	}

	private String checkdata(String search_name, int page, String Top) {
		if (page >= 0) {
			if (Top.equals("SalesVolume")) {
				str = checkValue(search_name, page, TAOBAO_SORT_SALESVOLUME_TOP);
			} else if (Top.equals("Credit")) {
				str = checkValue(search_name, page, TAOBAO_SORT_CREDIT_TOP);
			} else {
				str = checkValue(search_name, page, "");
			}
		} else {
			str = getPageSource(TAOBAO_SEARCH_LINK + StringToURLEncode(search_name));
		}
		return str;
	}

	private void AllPri(String text, List<TaoBaoBabyInfo> l) {
		str = text;
		Matcher shopName = Pattern.compile("\"nick\":(.+?\")").matcher(str);
		Matcher shopLink = Pattern.compile("\"detail_url\":(.+?\")").matcher(str);
		Matcher shopMoney = Pattern.compile("\"view_price\":(.+?\")").matcher(str);
		Matcher shopTitle = Pattern.compile("\"raw_title\":(.+?\")").matcher(str);
		Matcher getNum = Pattern.compile("\"view_sales\":(.+?\")").matcher(str);
		Matcher shopAddress = Pattern.compile("\"item_loc\":(.+?\")").matcher(str);
		Matcher shopIco = Pattern.compile("\"pic_url\":(.+?\")").matcher(str);
		Matcher shopId = Pattern.compile("\"user_id\":(.+?\")").matcher(str);
		while (shopName.find() && shopLink.find() && shopMoney.find() && shopTitle.find() && getNum.find()
				&& shopAddress.find() && shopIco.find() && shopId.find()) {
			babyinfo = new TaoBaoBabyInfo();
			babyinfo.setShopName(shopName.group().replaceAll("nick\":|\"", ""));
			babyinfo.setGetNum(getNum.group().replaceAll("view_sales\":|\"", ""));
			babyinfo.setShopIco(shopIco.group().replaceAll("pic_url\":|\"", ""));
			babyinfo.setShopId(shopId.group().replaceAll("user_id\":|\"", ""));
			babyinfo.setShopLink(UnicodeToString(shopLink.group().replaceAll("detail_url\":|\"", "")));
			babyinfo.setShopMoney(shopMoney.group().replaceAll("view_price\":|\"", ""));
			babyinfo.setShopTitle(shopTitle.group().replaceAll("raw_title\":|\"", ""));
			babyinfo.setShopAddress(shopAddress.group().replaceAll("item_loc\":|\"", ""));
			l.add(babyinfo);
		}
	}

	@Override
	public void getAll(String search_name) {

		list = getAllList(search_name);
		printList(list);
	}

	@Override
	public void getAll(String search_name, int maxpage) {

		list = getAllList(search_name, maxpage);
		printList(list);
	}

	@Override
	public void SalesVolumeTop(String search_name) {

		list = getAllList(search_name, "SalesVolume");
		printList(list);
	}

	@Override
	public void SalesVolumeTop(String search_name, int maxpage) {

		list = getAllList(search_name, maxpage, "SalesVolume");
		printList(list);
	}

	@Override
	public void CreditTop(String search_name) {

		list = getAllList(search_name, "Credit");
		printList(list);
	}

	@Override
	public void CreditTop(String search_name, int maxpage) {

		list = getAllList(search_name, maxpage, "Credit");
		printList(list);
	}

	@Override
	public String[] getAllArray(String search_name, int maxpage) {

		list = getAllList(search_name, maxpage);
		return printListArray(list);
	}

	@Override
	public String[] getAllArray(String search_name) {

		tmp = " ";
		list = getAllList(search_name);
		return printListArray(list);
	}

	@Override
	public String[] SalesVolumeTopArray(String search_name) {

		list = getAllList(search_name, "SalesVolume");
		return printListArray(list);
	}

	@Override
	public String[] SalesVolumeTopArray(String search_name, int maxpage) {

		list = getAllList(search_name, maxpage, "SalesVolume");
		return printListArray(list);
	}

	@Override
	public String SalesVolumeTopString(String search_name) {
		tmp = "";
		for (String s : SalesVolumeTopArray(search_name)) {
			tmp += s + "\n";
		}
		return tmp;
	}

	@Override
	public String SalesVolumeTopString(String search_name, int maxpage) {
		tmp = "";
		for (String s : SalesVolumeTopArray(search_name, maxpage)) {
			tmp += s + "\n";
		}
		return tmp;
	}

	@Override
	public String CreditTopString(String search_name) {
		tmp = "";
		for (String s : CreditTopArray(search_name)) {
			tmp += s + "\n";
		}
		return tmp;
	}

	@Override
	public String CreditTopString(String search_name, int maxpage) {
		tmp = "";
		for (String s : CreditTopArray(search_name, maxpage)) {
			tmp += s + "\n";
		}
		return tmp;
	}

	@Override
	public String[] CreditTopArray(String search_name) {

		list = getAllList(search_name, "Credit");
		return printListArray(list);
	}

	@Override
	public String[] CreditTopArray(String search_name, int maxpage) {
		list = getAllList(search_name, maxpage, "Credit");
		return printListArray(list);
	}

	public TaoBaoBabyInfo[] getAllArrays(String search_name, int maxpage) {
		list = getAllList(search_name, maxpage);
		return printShop(list);
	}

	public TaoBaoBabyInfo[] getAllArrays(String search_name) {
		list = getAllList(search_name);
		return printShop(list);
	}

	public TaoBaoBabyInfo[] SalesVolumeTopArrays(String search_name) {
		list = getAllList(search_name, "SalesVolume");
		return printShop(list);
	}

	public TaoBaoBabyInfo[] SalesVolumeTopArrays(String search_name, int maxpage) {
		list = getAllList(search_name, maxpage, "SalesVolume");
		return printShop(list);
	}

	public TaoBaoBabyInfo[] CreditTopArrays(String search_name) {
		list = getAllList(search_name, "Credit");
		return printShop(list);
	}

	public TaoBaoBabyInfo[] CreditTopArrays(String search_name, int maxpage) {
		list = getAllList(search_name, maxpage, "Credit");
		return printShop(list);
	}

	public List<TaoBaoBabyInfo> getAllList(String search_name) {
		str = checkdata(search_name, -2, "");
		list = new ArrayList<>();
		AllPri(str, list);
		return list;
	}

	public List<TaoBaoBabyInfo> getAllList(String search_name, String Top) {
		str = checkdata(search_name, -2, Top);
		list = new ArrayList<>();
		AllPri(str, list);
		return list;
	}

	public List<TaoBaoBabyInfo> getAllList(String search_name, int maxpage) {
		return getAllList(search_name, maxpage, "");
	}
	
	public int getPage(int n)
	{
		int tmp = 0;
		len = TAOBAO_BABY_NEXT_VALUE;
		while(tmp <= n)
		{
			len = len + TAOBAO_BABY_NEXT_VALUE;
			tmp++;
		}
		return len;
	}
	
	
	
	public void ThreadGetInfo(String search_name ,int maxpage ,  int thread_id)
	{
		if(maxpage > 1)
		{
			int t=-1;
			t = maxpage/thread_id;
			for(int i = 0;i<=thread_id;i++)
			{
				start = i*t;
				if(i == thread_id) {
					end = start + (maxpage - start);
				}else
				{
					end = (i +1) * t -1 ;
				}
				new PriThread(getPage(start), getPage(end),(t-1), i ,search_name).start();
			}
		}else
		{
			System.err.println("maxpage : "+maxpage);
		}
	}
	
	private class PriThread extends Thread
	{
		private int  start , end, id , maxpage;
		private String search_name ;
		public PriThread(int start, int end,int maxpage ,int id , String search_name) {
			this.start = start;
			this.end = end;
			this.maxpage = maxpage;
			this.id = id;
			this.search_name = search_name;
		}
		@Override
		public void run() {
			System.err.println("thread : "+id + " start !");
			for(int i = 0;i<maxpage;i++)
			{
				List<TaoBaoBabyInfo> ll = new ArrayList<>();
				str = checkdata(search_name, start, "");
				AllPri(str, ll);
				start = start + TAOBAO_BABY_NEXT_VALUE;
				if(start == end)
				{
					write("E:\\test\\files\\taobao_data\\", "taobao-"+id+".dat", printListString(ll));
					System.err.println("thread : "+id + " run ok !");
					return;
				}
			}
		}
	}
	
	public List<TaoBaoBabyInfo> getAllList(String search_name, int maxpage, String Top) {
		num = TAOBAO_BABY_NEXT_VALUE;
		TAOBAO_BABY_NEXT_VALUE = 0;
		list = new ArrayList<>();
		for (int n = 0; n < maxpage; n++) {
			str = checkdata(search_name, TAOBAO_BABY_NEXT_VALUE, Top);
			AllPri(str, list);
			TAOBAO_BABY_NEXT_VALUE = TAOBAO_BABY_NEXT_VALUE + num;
		}
		return list;
	}

	@Override
	public String getAllString(String search_name, int maxpage) {
		return printListString(getAllList(search_name, maxpage));
	}

	@Override
	public String getAllString(String search_name) {
		return printListString(getAllList(search_name));
	}

	@Override
	public void setCity(String city_name) {
		this.city_name = city_name;
	}

	@Override
	public String getCity() {
		return city_name;
	}

	@Override
	public void setCookie(String cookie) {
		super.setCookie(cookie);
	}

	@Override
	public String getCookie() {
		return super.cookie;
	}

	@Override
	public void setUserAgent(String ua) {
		super.setUserAgent(ua);
	}

	@Override
	public String getUserAgent() {
		return super.ua;
	}

	@Override
	public int getLength() {
		return list.size();
	}

	@Override
	public void SaveLocal(String search_name, String path) {
		write(path, getAllString(search_name));
	}

	@Override
	public void SaveLocal(String search_name, String path, int maxpage) {
		write(path, getAllString(search_name, maxpage));
	}

	@Override
	public void SaveCreditTopLocal(String search_name, String path) {
		write(path, CreditTopString(search_name));
	}

	@Override
	public void SaveCreditTopLocal(String search_name, String path, int maxpage) {
		write(path, CreditTopString(search_name, maxpage));
	}

	@Override
	public void SaveSalesVolumeTopLocal(String search_name, String path, int maxpage) {
		write(path, SalesVolumeTopString(search_name, maxpage));
	}

	@Override
	public void SaveSalesVolumeTopLocal(String search_name, String path) {
		write(path, SalesVolumeTopString(search_name));
	}

	@Override
	public void SaveLocal(String search_name, String path, String save_name) {
		write(path,save_name,getAllString(search_name));
	}

	@Override
	public void SaveLocal(String search_name, String path, String save_name, int maxpage) {
		write(path,save_name,getAllString(search_name,maxpage));
	}

	@Override
	public void SaveCreditTopLocal(String search_name, String path, String save_name) {
		write(path,save_name, CreditTopString(search_name));
	}

	@Override
	public void SaveCreditTopLocal(String search_name, String path, String save_name, int maxpage) {
		write(path,save_name, CreditTopString(search_name,maxpage));
	}

	@Override
	public void SaveSalesVolumeTopLocal(String search_name, String path, String save_name, int maxpage) {
		write(path,save_name, SalesVolumeTopString(search_name,maxpage));
	}

	@Override
	public void SaveSalesVolumeTopLocal(String search_name, String path, String save_name) {
		write(path,save_name, SalesVolumeTopString(search_name));
	}
	
	
	
}
