package TaoBao;

/*
 * ���������������ѯ�Ա�������Ϣ��
 * ���ܲ�ѯ��è���̣����ܺ��ڻ���ϼ�����è����
 * 2019��1��5��18:52:49
 * */

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaoBaoShopInfoValueManage extends Tools {

	private final static String rege_str = "title|span|em|class|<|>|/|\"|=|\\s+";
	private String ua = "" , cookie = "";
	
	
	public String getUa() {
		return ua;
	}

	public String getCookie() {
		return cookie;
	}

	public void setUa(String ua) {
		this.ua = ua;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	private String found_match(Matcher m, int num) {
		str = "";
		if (m.find()) {
			str = m.group();
			Matcher m2 = Pattern.compile("Line=-" + num + "(.+?</a)").matcher(str);
			if (m2.find()) {
				str = m2.group();
				str = str.substring(str.indexOf(">"));
				str = str.replaceAll(">|</a", "");
			} else {
				str = "0";
			}
		} else {
			str = 0 + "";
		}
		return str;
	}

	private TaoBaoShopInfoValue PriAll(String src) {
		shopinfovalue = new TaoBaoShopInfoValue();
		Matcher CreditRating = Pattern.compile("<li>��������\\W+\\s\\d*").matcher(src);
		Matcher BabyAccord = Pattern.compile("title\">�������������\\W+</span>(\\s+)(.+?class)").matcher(src);
		Matcher CreditAttitude = Pattern.compile("title\">���ҵķ���\\W+</span>(\\s+)(.+?class)").matcher(src);
		Matcher LogisticsQuality = Pattern.compile("title\">��������\\W+</span>(\\s+)(.+?class)").matcher(src);
		Matcher EarnestMoney = Pattern.compile("��ǰ��֤��(.+?</span>)").matcher(src);
		Matcher PraiseLev = Pattern.compile("������(.+?<)").matcher(src);
		Matcher AuthenticationInformation = Pattern.compile("alt=\"\\W+��\\W+").matcher(src);
		Matcher WeekPraise = Pattern.compile("rateok\\W+\\s+(.+?timeLine=-7)(.+?</a)").matcher(src);
		Matcher WeekModerate = Pattern.compile("ratenormal\\W+\\s+(.+?timeLine=-7)(.+?</a)").matcher(src);
		Matcher WeekNegative = Pattern.compile("ratebad\\W+\\s+(.+?timeLine=-7)(.+?</a)").matcher(src);
		Matcher MonthPraise = Pattern.compile("rateok\\W+\\s+(.+?timeLine=-30)(.+?</a)").matcher(src);
		Matcher MonthModerate = Pattern.compile("ratenormal\\W+\\s+(.+?timeLine=-30)(.+?</a)").matcher(src);
		Matcher MonthNegative = Pattern.compile("ratebad\\W+\\s+(.+?timeLine=-30)(.+?</a)").matcher(src);
		Matcher Half_a_YearPraise = Pattern.compile("rateok\\W+\\s+(.+?timeLine=-210)(.+?</a)").matcher(src);
		Matcher Half_a_YearModerate = Pattern.compile("ratenormal\\W+\\s+(.+?timeLine=-210)(.+?</a)").matcher(src);
		Matcher Half_a_YearNegative = Pattern.compile("ratebad\\W+\\s+(.+?timeLine=-210)(.+?</a)").matcher(src);
		Matcher LongLongAgoPraise = Pattern.compile("rateok\\W+\\s+(.+?211)(.+?</a)").matcher(src);
		Matcher LongLongAgoModerate = Pattern.compile("ratenormal\\W+\\s+(.+?211)(.+?</a)").matcher(src);
		Matcher LongLongAgoNegative = Pattern.compile("ratebad\\W+\\s+(.+?211)(.+?</a)").matcher(src);
		if (CreditRating.find() && BabyAccord.find() && CreditAttitude.find() && LogisticsQuality.find()
				&& EarnestMoney.find() && PraiseLev.find() && AuthenticationInformation.find()) {
			shopinfovalue.setCreditRating(CreditRating.group().replaceAll("<li>|�������ã�|\\s+", ""));
			shopinfovalue.setAuthenticationInformation(AuthenticationInformation.group().replaceAll("alt|\"|=", ""));
			shopinfovalue.setPraiseLev(PraiseLev.group().replaceAll("<|�����ʣ�", ""));
			shopinfovalue.setEarnestMoney(EarnestMoney.group().replaceAll("��ǰ��֤�����|span|/|<|>", ""));
			shopinfovalue.setBabyAccord(BabyAccord.group().replaceAll(rege_str + "|���������������", ""));
			shopinfovalue.setCreditAttitude(CreditAttitude.group().replaceAll(rege_str + "|���ҵķ���̬�ȣ�", ""));
			shopinfovalue.setLogisticsQuality(LogisticsQuality.group().replaceAll(rege_str + "|���������������", ""));
		}
		shopinfovalue.setWeekPraise(found_match(WeekPraise, 7));
		shopinfovalue.setWeekModerate(found_match(WeekModerate, 7));
		shopinfovalue.setWeekNegative(found_match(WeekNegative, 7));
		shopinfovalue.setMonthPraise(found_match(MonthPraise, 30));
		shopinfovalue.setMonthModerate(found_match(MonthModerate, 30));
		shopinfovalue.setMonthNegative(found_match(MonthNegative, 30));
		shopinfovalue.setHalf_a_YearPraise(found_match(Half_a_YearPraise, 210));
		shopinfovalue.setHalf_a_YearModerate(found_match(Half_a_YearModerate, 210));
		shopinfovalue.setHalf_a_YearNegative(found_match(Half_a_YearNegative, 210));
		shopinfovalue.setLongLongAgoPraise(found_match(LongLongAgoPraise, 211));
		shopinfovalue.setLongLongAgoModerate(found_match(LongLongAgoModerate, 211));
		shopinfovalue.setLongLongAgoNegative(found_match(LongLongAgoNegative, 211));
		return shopinfovalue;
	}

	private String checkData(String src) {
		str = "";
		if (src.indexOf("https") != -1 && src.endsWith(".htm")) {
			str = getInfo(src).getAll();
		} else if (new File(src).isFile()) {
			str = ReadLocalFile(src).getAll();
		} else {
			System.err.println("��������ַ����html�ļ�·��");
			str = "error";
		}
		return str;
	}

	public TaoBaoShopInfoValue getInfo(String link) { // ���������Ϣ���ӾͿ���ֱ�Ӷ�ȡ����
		if(!getCookie().equals(""))
		{
			if(!getUa().equals(""))
			{
				str = getPageSource(link, getCookie(), getUa());
			}else
			{
				str =  getPageSource(link, getCookie());
			}
		}else
		{
			str = getPageSource(link);
		}
		return PriAll(str);
	}

	public TaoBaoShopInfoValue ReadLocalFile(String file_path) // ͨ����ȡ����HTML�ļ�����ȡ���������Ϣ
	{
		str = read(file_path);
		return PriAll(str);
	}

	public void SaveLocal(String save_path, String save_name, String src) {
		write(save_path, save_name, checkData(src));
	}

	public void SaveLocal(String save_path, String src) {
		write(save_path, checkData(src));
	}

	public void getAll(String src) {
		System.out.println(checkData(src));
	}

}
