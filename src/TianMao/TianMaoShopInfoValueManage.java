package TianMao;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import TaoBao.Tools;

public class TianMaoShopInfoValueManage extends Tools {

	private String ua = "", cookie = "";

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

	private String getAlongData(String data, String reg, String re_str) {
		tmp = "";
		Matcher m = Pattern.compile(reg).matcher(data);
		while (m.find()) {
			tmp = tmp + "-" + m.group().replaceAll(re_str, "");
		}
		return tmp;
	}

	private TianMaoShopInfoValue PriAll(String str) {
		tianMaoShopInfoValue = new TianMaoShopInfoValue();
		Matcher Name = Pattern.compile("nick=(.+?(רӪ|�콢|��|��))(.+?\")").matcher(str);
		Matcher Address = Pattern.compile("����(.+?</)").matcher(str);
		Matcher EarnestMoney = Pattern.compile("��(.+?</)").matcher(str);
		Matcher AuthenticationInformation = Pattern.compile("alt=\"(.+?\")").matcher(str);
		Matcher BabyAccord = Pattern.compile("����(.+?>)\\s+(.+?class)").matcher(str);
		Matcher CreditAttitude = Pattern.compile("����(.+?>)\\s+(.+?class)").matcher(str);
		Matcher LogisticsQuality = Pattern.compile("����(.+?>)\\s+(.+?class)").matcher(str);
		if (Name.find() && Address.find() && EarnestMoney.find() && AuthenticationInformation.find()
				&& BabyAccord.find() && CreditAttitude.find() && LogisticsQuality.find()) {
			tianMaoShopInfoValue.setName(Name.group().replaceAll("data|tnick|nick|encode|=|\"|-", ""));
			tianMaoShopInfoValue.setAddress(Address.group().replaceAll("������|</", ""));
			tianMaoShopInfoValue.setService(getAlongData(str, "promise(.+?title=\")(.+?\")", "promise(.+?title=)|\""));
			tianMaoShopInfoValue.setEarnestMoney(
					EarnestMoney.group().substring(EarnestMoney.group().indexOf(">")).replaceAll(">|</", ""));
			tianMaoShopInfoValue
					.setAuthenticationInformation(AuthenticationInformation.group().replaceAll("alt=|\"", ""));
			tianMaoShopInfoValue.setBabyAccord(BabyAccord.group().substring(BabyAccord.group().indexOf("title"))
					.replaceAll("title=|class|\"", ""));
			tianMaoShopInfoValue.setCreditAttitude(CreditAttitude.group()
					.substring(CreditAttitude.group().indexOf("title")).replaceAll("title=|class|\"", ""));
			tianMaoShopInfoValue.setLogisticsQuality(LogisticsQuality.group()
					.substring(LogisticsQuality.group().indexOf("title")).replaceAll("title=|class|\"", ""));
		}
		return tianMaoShopInfoValue;
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

	public TianMaoShopInfoValue getInfo(String link) { // ���������Ϣ���ӾͿ���ֱ�Ӷ�ȡ����
		if (!getCookie().equals("")) {
			if (!getUa().equals("")) {
				str = getPageSource(link, getCookie(), getUa());
			} else {
				str = getPageSource(link, getCookie());
			}
		} else {
			str = getPageSource(link);
		}
		return PriAll(str);
	}

	public TianMaoShopInfoValue ReadLocalFile(String file_path) // ͨ����ȡ����HTML�ļ�����ȡ���������Ϣ
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