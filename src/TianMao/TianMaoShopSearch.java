package TianMao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import TaoBao.TaoBao;
import TaoBao.Tools;

public class TianMaoShopSearch extends Tools implements TaoBao {

	private List<TianMaoShopInfo> list = null;
	private String ua = "";
	private String cookie = "";
	private String city_name = "";

	private List<TianMaoShopInfo> PriGetInfo(String data, List<TianMaoShopInfo> l) {
		str = data;
		Matcher ShopName = Pattern.compile("\">(.+?(专营|旗舰|书))(.+?</a)").matcher(str);
		Matcher ShopLink = Pattern.compile("//store.taobao.com(.+?\")").matcher(str);
		Matcher ShopAddress = Pattern.compile("在地(.+?</p)").matcher(str);
		Matcher ShopICO = Pattern.compile("\"(.+?80.jpg)").matcher(str);
		Matcher ShopQuantityOfCommodities = Pattern.compile("em>\\d(.+?\\d</em>)").matcher(str);
		while (ShopName.find() && ShopLink.find() && ShopAddress.find() && ShopICO.find()
				&& ShopQuantityOfCommodities.find()) {
			tianMaoShopInfo = new TianMaoShopInfo();
			tianMaoShopInfo.setShopName(ShopName.group().replaceAll(">|</a|\"", ""));
			tianMaoShopInfo.setShopLink(ShopLink.group().replaceAll("\"", ""));
			tianMaoShopInfo.setShopInfoLink(getShopInfoLink(tianMaoShopInfo.getShopLink()));
			tianMaoShopInfo.setShopAddress(ShopAddress.group().replaceAll("在地：|</p", ""));
			tianMaoShopInfo.setShopICO(ShopICO.group().replaceAll("\"", ""));
			tianMaoShopInfo.setShopQuantityOfCommodities(ShopQuantityOfCommodities.group().replaceAll("em|>|</", ""));
			l.add(tianMaoShopInfo);
		}
		return l;
	}

	private void printList(List<TianMaoShopInfo> l) {
		str = "";
		len = l.size();
		for (int i = 0; i < len; i++) {
			tmp = "商铺名称: " + l.get(i).getShopName() + "\n店铺链接: " + l.get(i).getShopLink() + "\n店铺信息链接: "
					+ l.get(i).getShopInfoLink() + "\n店铺地址: " + l.get(i).getShopAddress() + "\n店铺图标: "
					+ l.get(i).getShopICO() + "\n店铺相关产品: " + l.get(i).getShopQuantityOfCommodities() + "\n";
			System.out.println(tmp);
		}
	}

	private String printListString(List<TianMaoShopInfo> l) {
		str = "";
		len = l.size();
		for (int i = 0; i < len; i++) {
			tmp = "商铺名称: " + l.get(i).getShopName() + "\n店铺链接: " + l.get(i).getShopLink() + "\n店铺信息链接: "
					+ l.get(i).getShopInfoLink() + "\n店铺地址: " + l.get(i).getShopAddress() + "\n店铺图标: "
					+ l.get(i).getShopICO() + "\n店铺相关产品: " + l.get(i).getShopQuantityOfCommodities() + "\n";
			str += tmp + "\n";
		}
		return str;
	}

	private String[] printListArray(List<TianMaoShopInfo> l) {
		str = "";
		len = l.size();
		arry = new String[len];
		for (int i = 0; i < len; i++) {
			tmp = "商铺名称: " + l.get(i).getShopName() + "\n店铺链接: " + l.get(i).getShopLink() + "\n店铺信息链接: "
					+ l.get(i).getShopInfoLink() + "\n店铺地址: " + l.get(i).getShopAddress() + "\n店铺图标: "
					+ l.get(i).getShopICO() + "\n店铺相关产品: " + l.get(i).getShopQuantityOfCommodities() + "\n";
			arry[i] = tmp;
		}
		return arry;
	}

	private TianMaoShopInfo[] printListArrays(List<TianMaoShopInfo> l) {
		str = "";
		len = l.size();
		tianMaoShopInfoArray = new TianMaoShopInfo[len];
		for (int i = 0; i < len; i++) {
			tianMaoShopInfoArray[i] = l.get(i);
		}
		return tianMaoShopInfoArray;
	}

	private String getShopInfoLink(String link) {
		tmp = "";
		link = "https:" + link;
		str = getPageSource(link);
		Matcher infoLink = Pattern.compile("href=\"//rate(.+?\")").matcher(str);
		if (infoLink.find()) {
			tmp = infoLink.group().replaceAll("href=|\"", "");
		}
		return tmp;
	}

	private String checkValue(String search_name, int page, String Top) {
		if (!getCookie().equals("")) {
			if (!getUserAgent().equals("")) {
				str = getPageSourceEncode(TIANMAO_LINK + TIANMAO_NEXT_PAGE + page + TIANMAO_SEARCH_LINK
						+ StringToURLEncode(search_name) + Top + TIANMAO_SEARCH_SHOP_LINK_END, "gbk", getCookie(),
						getUserAgent());
			} else {
				str = getPageSourceEncode(TIANMAO_LINK + TIANMAO_NEXT_PAGE + page + TIANMAO_SEARCH_LINK
						+ StringToURLEncode(search_name) + Top + TIANMAO_SEARCH_SHOP_LINK_END, "gbk", getCookie());
			}
		} else {
			str = getPageSourceEncode(TIANMAO_LINK + TIANMAO_NEXT_PAGE + page + TIANMAO_SEARCH_LINK
					+ StringToURLEncode(search_name) + Top + TIANMAO_SEARCH_SHOP_LINK_END, "gbk");
		}

		return str;
	}

	private String checkdata(String search_name, int page, String Top) {
		if (page >= 0) {
			if (Top.equals("SalesVolume")) {
				str = checkValue(search_name, page, TIANMAO_SORT_SALESVOLUME_TOP);
			} else if (Top.equals("Credit")) {
				str = checkValue(search_name, page, TIANMAO_SORT_CREDIT_TOP);
			} else {
				str = checkValue(search_name, page, "");
			}
		} else {
			str = getPageSourceEncode(
					TIANMAO_LINK + TIANMAO_SEARCH_LINK + StringToURLEncode(search_name) + TIANMAO_SEARCH_SHOP_LINK_END,
					"gbk");
		}
		return str;
	}

	public List<TianMaoShopInfo> getAllList(String search_name) {
		return getAllList(search_name, "");
	}

	public List<TianMaoShopInfo> getAllList(String search_name, String Top) {
		str = checkdata(search_name, -2, Top);
		list = new ArrayList<>();
		PriGetInfo(str, list);
		return list;
	}

	public List<TianMaoShopInfo> getAllList(String search_name, int maxpage) {
		return getAllList(search_name, maxpage, "");
	}

	public List<TianMaoShopInfo> getAllList(String search_name, int maxpage, String Top) {
		num = TIANMAO_SHOP_NEXT_VALUE;
		TIANMAO_SHOP_NEXT_VALUE = 0;
		list = new ArrayList<>();
		for (int n = 0; n < maxpage; n++) {
			str = checkdata(search_name, TIANMAO_SHOP_NEXT_VALUE, Top);
			PriGetInfo(str, list);
			TIANMAO_SHOP_NEXT_VALUE = TIANMAO_SHOP_NEXT_VALUE + num;
		}
		return list;
	}

	public TianMaoShopInfo[] getAllArrays(String search_name, int maxpage) {
		list = getAllList(search_name, maxpage);
		return printListArrays(list);
	}

	public TianMaoShopInfo[] getAllArrays(String search_name) {
		list = getAllList(search_name);
		return printListArrays(list);
	}

	public TianMaoShopInfo[] SalesVolumeTopArrays(String search_name) {
		list = getAllList(search_name, "SalesVolume");
		return printListArrays(list);
	}

	public TianMaoShopInfo[] SalesVolumeTopArrays(String search_name, int maxpage) {
		list = getAllList(search_name, maxpage, "SalesVolume");
		return printListArrays(list);
	}

	public TianMaoShopInfo[] CreditTopArrays(String search_name) {
		list = getAllList(search_name, "Credit");
		return printListArrays(list);
	}

	public TianMaoShopInfo[] CreditTopArrays(String search_name, int maxpage) {
		list = getAllList(search_name, maxpage, "Credit");
		return printListArrays(list);
	}

	@Override
	public void getAll(String search_name) {
		printList(getAllList(search_name));
	}

	@Override
	public String[] getAllArray(String search_name) {
		return printListArray(getAllList(search_name));
	}

	@Override
	public void getAll(String search_name, int maxpage) {
		printList(getAllList(search_name, maxpage));
	}

	@Override
	public String[] getAllArray(String search_name, int maxpage) {
		return printListArray(getAllList(search_name, maxpage));
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
	public void SalesVolumeTop(String search_name) {
		printList(getAllList(search_name, "SalesVolume"));
	}

	@Override
	public String[] SalesVolumeTopArray(String search_name) {
		return printListArray(getAllList(search_name, "SalesVolume"));
	}

	@Override
	public String SalesVolumeTopString(String search_name) {
		return printListString(getAllList(search_name, "SalesVolume"));
	}

	@Override
	public void CreditTop(String search_name) {
		printList(getAllList(search_name, "Credit"));
	}

	@Override
	public String[] CreditTopArray(String search_name) {
		return printListArray(getAllList(search_name, "Credit"));
	}

	@Override
	public String CreditTopString(String search_name) {
		return printListString(getAllList(search_name, "Credit"));
	}

	@Override
	public void SalesVolumeTop(String search_name, int maxpage) {
		printList(getAllList(search_name, maxpage, "SalesVolume"));
	}

	@Override
	public String[] SalesVolumeTopArray(String search_name, int maxpage) {
		return printListArray(getAllList(search_name, maxpage, "SalesVolume"));
	}

	@Override
	public String SalesVolumeTopString(String search_name, int maxpage) {
		return printListString(getAllList(search_name, maxpage, "SalesVolume"));
	}

	@Override
	public void CreditTop(String search_name, int maxpage) {
		printList(getAllList(search_name, maxpage, "Credit"));
	}

	@Override
	public String[] CreditTopArray(String search_name, int maxpage) {
		return printListArray(getAllList(search_name, maxpage, "Credit"));
	}

	@Override
	public String CreditTopString(String search_name, int maxpage) {
		return printListString(getAllList(search_name, maxpage, "Credit"));
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
		write(path, save_name, getAllString(search_name));
	}

	@Override
	public void SaveLocal(String search_name, String path, String save_name, int maxpage) {
		write(path, save_name, getAllString(search_name, maxpage));
	}

	@Override
	public void SaveCreditTopLocal(String search_name, String path, String save_name) {
		write(path, save_name, CreditTopString(search_name));
	}

	@Override
	public void SaveCreditTopLocal(String search_name, String path, String save_name, int maxpage) {
		write(path, save_name, CreditTopString(search_name, maxpage));
	}

	@Override
	public void SaveSalesVolumeTopLocal(String search_name, String path, String save_name, int maxpage) {
		write(path, save_name, SalesVolumeTopString(search_name, maxpage));
	}

	@Override
	public void SaveSalesVolumeTopLocal(String search_name, String path, String save_name) {
		write(path, save_name, SalesVolumeTopString(search_name));
	}

}
