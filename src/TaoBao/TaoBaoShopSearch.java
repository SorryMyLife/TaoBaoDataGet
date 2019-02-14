package TaoBao;

/*
 * 用于搜索店铺相关的
 * 允许你设置地区
 * 允许查看销售量或者信誉的排名
 * 2019年1月5日17:48:44
 * */

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaoBaoShopSearch extends Tools implements TaoBao {

	private String city_name = "", cookie = "", ua = "";
	private List<TaoBaoShopInfo> list = null;

	private void printList(List<TaoBaoShopInfo> li) {
		len = li.size();
		for (int i = 0; i < len; i++) {
			tmp = "店铺名称: " + li.get(i).getShopName() + "\n店铺ID: " + li.get(i).getShopId() + "\n店铺链接: "
					+ li.get(i).getShopLink() + "\n店铺地址: " + li.get(i).getShopAddress() + "\n店铺图标: "
					+ li.get(i).getShopIco() + "\n卖家名称: " + li.get(i).getShopChapmanName() + "\n店铺信息链接: "
					+ li.get(i).getShopInfoLink() + "\n销量: " + li.get(i).getShopSalesVolume() + "\n库存: "
					+ li.get(i).getShopStock() + "\n好评率: " + li.get(i).getShopGoodratePercent() + "\n主营: "
					+ li.get(i).getShopMainAuction() + "\n";
			System.out.println(tmp);
		}
	}

	private String[] printArray(List<TaoBaoShopInfo> li) {
		len = li.size();
		arry = new String[len];
		for (int i = 0; i < len; i++) {
			tmp = "店铺名称: " + li.get(i).getShopName() + "\n店铺ID: " + li.get(i).getShopId() + "\n店铺链接: "
					+ li.get(i).getShopLink() + "\n店铺地址: " + li.get(i).getShopAddress() + "\n店铺图标: "
					+ li.get(i).getShopIco() + "\n卖家名称: " + li.get(i).getShopChapmanName() + "\n店铺信息链接: "
					+ li.get(i).getShopInfoLink() + "\n销量: " + li.get(i).getShopSalesVolume() + "\n库存: "
					+ li.get(i).getShopStock() + "\n好评率: " + li.get(i).getShopGoodratePercent() + "\n主营: "
					+ li.get(i).getShopMainAuction() + "\n";
			arry[i] = tmp;
		}
		return arry;
	}

	private String printString(List<TaoBaoShopInfo> li) {
		len = li.size();
		str = "";
		tmp = " ";
		for (int i = 0; i < len; i++) {
			tmp = "店铺名称: " + li.get(i).getShopName() + "\n店铺ID: " + li.get(i).getShopId() + "\n店铺链接: "
					+ li.get(i).getShopLink() + "\n店铺地址: " + li.get(i).getShopAddress() + "\n店铺图标: "
					+ li.get(i).getShopIco() + "\n卖家名称: " + li.get(i).getShopChapmanName() + "\n店铺信息链接: "
					+ li.get(i).getShopInfoLink() + "\n销量: " + li.get(i).getShopSalesVolume() + "\n库存: "
					+ li.get(i).getShopStock() + "\n好评率: " + li.get(i).getShopGoodratePercent() + "\n主营: "
					+ li.get(i).getShopMainAuction() + "\n";
			str += tmp + "\n";
		}
		return str;
	}

	private TaoBaoShopInfo[] printShop(List<TaoBaoShopInfo> l) {
		len = l.size();
		shopinfoArray = new TaoBaoShopInfo[len];
		for (int i = 0; i < len; i++) {
			shopinfoArray[i] = l.get(i);
		}
		return shopinfoArray;
	}

	private void AllPri(String text, List<TaoBaoShopInfo> l) {
		str = text;
		Matcher shopName = Pattern.compile("\"rawTitle\":\"(.+?\")").matcher(str);
		Matcher shopChapmanName = Pattern.compile("\"nick\":\"(.+?\")").matcher(str);
		Matcher shopIco = Pattern.compile("\"picUrl\":\"(.+?\")").matcher(str);
		Matcher shopLink = Pattern.compile("\"shopUrl\":\"(.+?\")").matcher(str);
		Matcher shopGoodratePercent = Pattern.compile("\"goodratePercent\":\"(.+?\")").matcher(str);
		Matcher shopSalesVolume = Pattern.compile("\"totalsold\":(.+?\")").matcher(str);
		Matcher shopStock = Pattern.compile("\"procnt\":(.+?\")").matcher(str);
		Matcher shopId = Pattern.compile("\"shopUrl\":(.+?\")").matcher(str);
		Matcher shopMainAuction = Pattern.compile("\"mainAuction\":(.+?\")").matcher(str);
		Matcher shopInfoLink = Pattern.compile("\"userRateUrl\":(.+?\")").matcher(str);
		Matcher shopAddress = Pattern.compile("\"provcity\":(.+?\")").matcher(str);
		while (shopName.find() && shopChapmanName.find() && shopIco.find() && shopLink.find()
				&& shopGoodratePercent.find() && shopSalesVolume.find() && shopStock.find() && shopId.find()
				&& shopMainAuction.find() && shopInfoLink.find() && shopAddress.find()) {
			shopinfo = new TaoBaoShopInfo();
			shopinfo.setShopAddress(shopAddress.group().replaceAll("provcity\":|\"", "").replaceAll(" ", "-"));
			shopinfo.setShopChapmanName(shopChapmanName.group().replaceAll("nick\":|\"", ""));
			shopinfo.setShopGoodratePercent(shopGoodratePercent.group().replaceAll("goodratePercent\":|\"", ""));
			shopinfo.setShopIco(shopIco.group().replaceAll("picUrl\":|\"", ""));
			shopinfo.setShopName(shopName.group().replaceAll("rawTitle\":|\"", ""));
			shopinfo.setShopLink(shopLink.group().replaceAll("shopUrl\":|\"", ""));
			shopinfo.setShopSalesVolume(shopSalesVolume.group().replaceAll("totalsold\":|\"|,", ""));
			shopinfo.setShopStock(shopStock.group().replaceAll("procnt\":|\"|,", ""));
			shopinfo.setShopId(shopId.group().replaceAll("shopUrl\":|\"|,|.taobao.com|shop", "").replaceAll("//", ""));
			shopinfo.setShopMainAuction(
					shopMainAuction.group().replaceAll("mainAuction\":|\"|,", "").replaceAll(" ", "-"));
			shopinfo.setShopInfoLink(shopInfoLink.group().replaceAll("userRateUrl\":|\"|,", ""));
			l.add(shopinfo);
		}
	}

	private String checkValue(String search_name, int page, String Top) {
		if (!getCity().equals("")) {
			if (!getCookie().equals("")) {
				str = getPageSource(TAOBAO_SHOP_SEARCH_LINK + StringToURLEncode(search_name) + TAOBAO_SET_CITY
						+ StringToURLEncode(city_name) + Top + TAOBAO_NEXT_PAGE + page, getCookie());
			} else if (!getUserAgent().equals("")) {
				str = getPageSource(TAOBAO_SHOP_SEARCH_LINK + StringToURLEncode(search_name) + TAOBAO_SET_CITY
						+ StringToURLEncode(city_name) + Top + TAOBAO_NEXT_PAGE + page, getUserAgent());
			} else {
				str = getPageSource(TAOBAO_SHOP_SEARCH_LINK + StringToURLEncode(search_name) + TAOBAO_SET_CITY
						+ StringToURLEncode(city_name) + Top + TAOBAO_NEXT_PAGE + page);
			}
		} else if (!getCookie().equals("")) {
			if (!getCity().equals("")) {
				str = getPageSource(TAOBAO_SHOP_SEARCH_LINK + StringToURLEncode(search_name) + TAOBAO_SET_CITY
						+ StringToURLEncode(city_name) + Top + TAOBAO_NEXT_PAGE + page, getCookie());
			} else if (!getUserAgent().equals("")) {
				str = getPageSource(TAOBAO_SHOP_SEARCH_LINK + StringToURLEncode(search_name),
						getCookie() + Top + TAOBAO_NEXT_PAGE + page, getUserAgent());
			} else {
				str = getPageSource(
						TAOBAO_SHOP_SEARCH_LINK + StringToURLEncode(search_name) + Top + TAOBAO_NEXT_PAGE + page,
						getCookie());
			}
		} else if (!getUserAgent().equals("")) {
			if (!getCookie().equals("")) {
				str = getPageSource(
						TAOBAO_SHOP_SEARCH_LINK + StringToURLEncode(search_name) + Top + TAOBAO_NEXT_PAGE + page,
						getCookie(), getUserAgent());
			} else if (!getCity().equals("") || !getCookie().equals("")) {
				str = getPageSource(TAOBAO_SHOP_SEARCH_LINK + StringToURLEncode(search_name) + Top + TAOBAO_SET_CITY
						+ StringToURLEncode(city_name) + TAOBAO_NEXT_PAGE + page, getCookie(), getUserAgent());
			}
		} else {
			str = getPageSource(
					TAOBAO_SHOP_SEARCH_LINK + StringToURLEncode(search_name) + Top + TAOBAO_NEXT_PAGE + page);
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
			str = getPageSource(TAOBAO_SHOP_SEARCH_LINK + StringToURLEncode(search_name));
		}
		return str;
	}

	@Override
	public void getAll(String search_name, int maxpage) {
		list = getAllList(search_name, maxpage);
		printList(list);
	}

	@Override
	public void getAll(String search_name) {

		list = getAllList(search_name);
		printList(list);
	}

	@Override
	public String getAllString(String search_name, int maxpage) {
		// TODO Auto-generated method stub
		return printString(getAllList(search_name, maxpage));
	}

	@Override
	public String getAllString(String search_name) {
		// TODO Auto-generated method stub
		return printString(getAllList(search_name));
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
		return printArray(list);
	}

	@Override
	public String[] getAllArray(String search_name) {

		list = getAllList(search_name);
		return printArray(list);
	}

	@Override
	public String[] SalesVolumeTopArray(String search_name) {

		list = getAllList(search_name, "SalesVolume");
		return printArray(list);
	}

	@Override
	public String[] SalesVolumeTopArray(String search_name, int maxpage) {

		list = getAllList(search_name, maxpage, "SalesVolume");
		return printArray(list);
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
	public String SalesVolumeTopString(String search_name) {
		tmp = "";
		for (String s : SalesVolumeTopArray(search_name)) {
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
		return printArray(list);
	}

	@Override
	public String[] CreditTopArray(String search_name, int maxpage) {

		list = getAllList(search_name, maxpage, "Credit");
		return printArray(list);
	}

	public TaoBaoShopInfo[] SalesVolumeTopArrays(String search_name) {
		list = getAllList(search_name, "SalesVolume");
		return printShop(list);
	}

	public TaoBaoShopInfo[] getAllArrays(String search_name) {

		list = getAllList(search_name);
		return printShop(list);
	}

	public TaoBaoShopInfo[] getAllArrays(String search_name, int maxpage) {

		list = getAllList(search_name, maxpage);
		return printShop(list);
	}

	public TaoBaoShopInfo[] SalesVolumeTopArrays(String search_name, int maxpage) {
		list = getAllList(search_name, maxpage, "SalesVolume");
		return printShop(list);
	}

	public TaoBaoShopInfo[] CreditTopArrays(String search_name) {
		list = getAllList(search_name, "Credit");
		return printShop(list);
	}

	public TaoBaoShopInfo[] CreditTopArrays(String search_name, int maxpage) {
		list = getAllList(search_name, maxpage, "Credit");
		return printShop(list);
	}

	public List<TaoBaoShopInfo> getAllList(String search_name) {

		str = checkdata(search_name, -2, "");
		list = new ArrayList<>();
		AllPri(str, list);
		return list;
	}

	public List<TaoBaoShopInfo> getAllList(String search_name, String Top) {

		str = checkdata(search_name, 0, Top);
		list = new ArrayList<>();
		AllPri(str, list);
		return list;
	}

	public List<TaoBaoShopInfo> getAllList(String search_name, int maxpage) {

		num = TAOBAO_SHOP_NEXT_VALUE;
		TAOBAO_SHOP_NEXT_VALUE = 0;
		list = new ArrayList<>();
		for (int i = 0; i < maxpage; i++) {
			str = checkdata(search_name, TAOBAO_SHOP_NEXT_VALUE, "");
			AllPri(str, list);
			TAOBAO_SHOP_NEXT_VALUE = TAOBAO_SHOP_NEXT_VALUE + num;
		}
		return list;
	}

	public List<TaoBaoShopInfo> getAllList(String search_name, int maxpage, String Top) {

		num = TAOBAO_SHOP_NEXT_VALUE;
		TAOBAO_SHOP_NEXT_VALUE = 0;
		list = new ArrayList<>();
		for (int i = 0; i < maxpage; i++) {
			str = checkdata(search_name, TAOBAO_SHOP_NEXT_VALUE, Top);
			AllPri(str, list);
			TAOBAO_SHOP_NEXT_VALUE = TAOBAO_SHOP_NEXT_VALUE + num;
		}
		return list;
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
		this.cookie = cookie;
	}

	@Override
	public String getCookie() {
		return cookie;
	}

	@Override
	public void setUserAgent(String ua) {
		this.ua = ua;
	}

	@Override
	public String getUserAgent() {
		return ua;
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
		// TODO Auto-generated method stub
		write(path,save_name,getAllString(search_name));
	}

	@Override
	public void SaveLocal(String search_name, String path, String save_name, int maxpage) {
		// TODO Auto-generated method stub
		write(path,save_name,getAllString(search_name,maxpage));
	}

	@Override
	public void SaveCreditTopLocal(String search_name, String path, String save_name) {
		// TODO Auto-generated method stub
		write(path,save_name, CreditTopString(search_name));
	}

	@Override
	public void SaveCreditTopLocal(String search_name, String path, String save_name, int maxpage) {
		// TODO Auto-generated method stub
		write(path,save_name, CreditTopString(search_name,maxpage));
	}

	@Override
	public void SaveSalesVolumeTopLocal(String search_name, String path, String save_name, int maxpage) {
		// TODO Auto-generated method stub
		write(path,save_name, SalesVolumeTopString(search_name,maxpage));
	}

	@Override
	public void SaveSalesVolumeTopLocal(String search_name, String path, String save_name) {
		// TODO Auto-generated method stub
		write(path,save_name, SalesVolumeTopString(search_name));
	}


}
