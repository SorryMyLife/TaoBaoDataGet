package TianMao;

public class TianMaoShopInfoValue {
	private String Name = "" // 店铺名称
			, Address = "" // 店铺地址
			, Service = "" // 店铺承诺的服务
			, EarnestMoney = "" // 店铺保证金余额
			, AuthenticationInformation = "" // 认证信息
			, BabyAccord = "" // 宝贝与描述相符的评分
			, CreditAttitude = "" // 卖家服务态度
			, LogisticsQuality = "" // 物流服务态度
	;

	public String getAll() {
		return "店铺名称: " + getName() + "\n店铺地址: " + getAddress() + "\n店铺承诺的服务: " + getService() + "\n店铺保证金额: "
				+ getEarnestMoney() + "\n认证信息: " + getAuthenticationInformation() + "\n宝贝与描述相符的评分: " + getBabyAccord()
				+ "\n卖家服务态度评分: " + getCreditAttitude() + "\n物流服务态度评分: " + getLogisticsQuality() + "\n";
	}

	public String getName() {
		return Name;
	}

	public String getAddress() {
		return Address;
	}

	public String getService() {
		return Service;
	}

	public String getEarnestMoney() {
		return EarnestMoney;
	}

	public String getAuthenticationInformation() {
		return AuthenticationInformation;
	}

	public String getBabyAccord() {
		return BabyAccord;
	}

	public String getCreditAttitude() {
		return CreditAttitude;
	}

	public String getLogisticsQuality() {
		return LogisticsQuality;
	}

	public void setName(String name) {
		Name = name;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public void setService(String service) {
		Service = service;
	}

	public void setEarnestMoney(String earnestMoney) {
		EarnestMoney = earnestMoney;
	}

	public void setAuthenticationInformation(String authenticationInformation) {
		AuthenticationInformation = authenticationInformation;
	}

	public void setBabyAccord(String babyAccord) {
		BabyAccord = babyAccord;
	}

	public void setCreditAttitude(String creditAttitude) {
		CreditAttitude = creditAttitude;
	}

	public void setLogisticsQuality(String logisticsQuality) {
		LogisticsQuality = logisticsQuality;
	}
}
