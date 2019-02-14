package TianMao;

/*
 * 用于天猫操作的
 * 
 * */

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import TaoBao.*;

public class TianMaoBabySearch extends Tools implements TaoBao {

	private List<TianMaoBabyInfo> list = null;
	private String ua = "";
	private String cookie = "";
	private String city_name = "";

	private void printList(List<TianMaoBabyInfo> l) {
		str = " ";
		len = l.size();
		for (int i = 0; i < len; i++) {
			str = "店铺名称: " + l.get(i).getShopName() + "\n店铺链接: " + l.get(i).getShopLink() + "\n店铺ID: "
					+ l.get(i).getShopId() + "\n商品价格: " + l.get(i).getShopMoney() + "\n商品标签: " + l.get(i).getShopTitle()
					+ "\n商品预览图: " + l.get(i).getShopIco() + "\n商品购买人数: " + list.get(i).getGetNum() + "\n";
			System.out.println(str);
		}
	}

	private String printListString(List<TianMaoBabyInfo> l) {
		str = " ";
		tmp = "";
		len = l.size();
		for (int i = 0; i < len; i++) {
			str = "店铺名称: " + l.get(i).getShopName() + "\n店铺链接: " + l.get(i).getShopLink() + "\n店铺ID: "
					+ l.get(i).getShopId() + "\n商品价格: " + l.get(i).getShopMoney() + "\n商品标签: " + l.get(i).getShopTitle()
					+ "\n商品预览图: " + l.get(i).getShopIco() + "\n商品购买人数: " + list.get(i).getGetNum() + "\n";
			tmp += str + "\n";
		}
		return tmp;
	}

	private String[] printListArray(List<TianMaoBabyInfo> l) {
		str = " ";
		len = l.size();
		arry = new String[len];
		for (int i = 0; i < len; i++) {
			str = "店铺名称: " + l.get(i).getShopName() + "\n店铺链接: " + l.get(i).getShopLink() + "\n店铺ID: "
					+ l.get(i).getShopId() + "\n商品价格: " + l.get(i).getShopMoney() + "\n商品标签: " + l.get(i).getShopTitle()
					+ "\n商品预览图: " + l.get(i).getShopIco() + "\n商品购买人数: " + list.get(i).getGetNum() + "\n";
			arry[i] = str;
		}
		return arry;
	}

	private TianMaoBabyInfo[] printListArrays(List<TianMaoBabyInfo> l) {
		str = " ";
		len = l.size();
		tianMaoBabyInfoArray = new TianMaoBabyInfo[len];
		for (int i = 0; i < len; i++) {
			babyinfoArray[i] = l.get(i);
		}
		return tianMaoBabyInfoArray;
	}

	private void PriGetInfo(String data, List<TianMaoBabyInfo> l) {
		str = data;
		Matcher shopName = Pattern.compile("data-nick=\"(.+?\")").matcher(str);
		Matcher shopLink = Pattern.compile("//detail(.+?\" class=\"productImg\")").matcher(str);
		Matcher shopMoney = Pattern.compile("em title=(.+?\")").matcher(str);
		Matcher shopTitle = Pattern.compile("blank\" title=\"(.+?\")").matcher(str);
		Matcher getNum = Pattern.compile("<span>(.+?</em)").matcher(str);
		Matcher shopIco = Pattern.compile("//img.alicdn.com/bao/uploaded(.+?\")").matcher(str);
		Matcher shopId = Pattern.compile("data-id=(.+?\")").matcher(str);
		while (shopName.find() && shopLink.find() && shopMoney.find() && shopTitle.find() && getNum.find()
				&& shopIco.find() && shopId.find()) {
			tianMaoBabyInfo = new TianMaoBabyInfo();
			tianMaoBabyInfo.setGetNum(getNum.group().replaceAll("<span>|<|/|em|>", "").replaceAll(" ", ":"));
			tianMaoBabyInfo.setShopName(shopName.group().replaceAll("data-nick=|\"", ""));
			tianMaoBabyInfo.setShopLink(shopLink.group().replaceAll("class=\"productImg\"|\"", ""));
			tianMaoBabyInfo.setShopMoney(shopMoney.group().replaceAll("em|title=|\"", ""));
			tianMaoBabyInfo.setShopTitle(shopTitle.group().replaceAll("blank\" title=\"|\"", ""));
			tianMaoBabyInfo.setShopIco(shopIco.group().replaceAll("\"", ""));
			tianMaoBabyInfo.setShopId(shopId.group().replaceAll("data-id=|\"", ""));
			l.add(tianMaoBabyInfo);
		}
	}

	private String checkValue(String search_name, int page, String Top) {
		if (!getCookie().equals("")) {
			if (!getUserAgent().equals("")) {
				str = getPageSourceEncode(TIANMAO_LINK + TIANMAO_NEXT_PAGE + page + TIANMAO_SEARCH_LINK
						+ StringToURLEncode(search_name) + Top, "gbk", getCookie(), getUserAgent());
			} else {
				str = getPageSourceEncode(TIANMAO_LINK + TIANMAO_NEXT_PAGE + page + TIANMAO_SEARCH_LINK
						+ StringToURLEncode(search_name) + Top, "gbk", getCookie());
			}
		} else {
			str = getPageSourceEncode(TIANMAO_LINK + TIANMAO_NEXT_PAGE + page + TIANMAO_SEARCH_LINK
					+ StringToURLEncode(search_name) + Top, "gbk");
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
			str = getPageSourceEncode(TIANMAO_LINK+TIANMAO_SEARCH_LINK + StringToURLEncode(search_name),"gbk");
		}
		return str;
	}

	public List<TianMaoBabyInfo> getAllList(String search_name) {
		return getAllList(search_name, "");
	}

	public List<TianMaoBabyInfo> getAllList(String search_name, String Top) {
		str = checkdata(search_name, -2, Top);
		list = new ArrayList<>();
		PriGetInfo(str, list);
		return list;
	}

	public List<TianMaoBabyInfo> getAllList(String search_name, int maxpage) {
		return getAllList(search_name, maxpage, "");
	}

	public List<TianMaoBabyInfo> getAllList(String search_name, int maxpage, String Top) {
		num = TIANMAO_BABY_NEXT_VALUE;
		TIANMAO_BABY_NEXT_VALUE = 0;
		list = new ArrayList<>();
		for (int n = 0; n < maxpage; n++) {
			str = checkdata(search_name, TIANMAO_BABY_NEXT_VALUE, Top);
			PriGetInfo(str, list);
			TIANMAO_BABY_NEXT_VALUE = TIANMAO_BABY_NEXT_VALUE + num;
		}
		return list;
	}
	
	public TianMaoBabyInfo[] getAllArrays(String search_name, int maxpage) {
		list = getAllList(search_name, maxpage);
		return printListArrays(list);
	}

	public TianMaoBabyInfo[] getAllArrays(String search_name) {
		list = getAllList(search_name);
		return printListArrays(list);
	}

	public TianMaoBabyInfo[] SalesVolumeTopArrays(String search_name) {
		list = getAllList(search_name, "SalesVolume");
		return printListArrays(list);
	}

	public TianMaoBabyInfo[] SalesVolumeTopArrays(String search_name, int maxpage) {
		list = getAllList(search_name, maxpage, "SalesVolume");
		return printListArrays(list);
	}

	public TianMaoBabyInfo[] CreditTopArrays(String search_name) {
		list = getAllList(search_name, "Credit");
		return printListArrays(list);
	}

	public TianMaoBabyInfo[] CreditTopArrays(String search_name, int maxpage) {
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
