package TaoBao;

public class TaoBaoShopInfoValue {
	private String CreditRating = "" // 卖家信用等级
			, BabyAccord = "" // 宝贝与描述相符的评分
			, CreditAttitude = "" // 卖家服务态度
			, LogisticsQuality = "" // 物流服务态度
			, EarnestMoney = "" // 保证金余额
			, PraiseLev = "" // 好评率
			, AuthenticationInformation = "" // 认证信息
			, WeekPraise = "" // 最近一周的好评
			, WeekModerate = "" // 最近一周的中评
			, WeekNegative = "" // 最近一周的差评
			, MonthPraise = "" // 最近一月的好评
			, MonthModerate = "" // 最近一月的中评
			, MonthNegative = "" // 最近一月的差评
			, Half_a_YearPraise = "" // 最近半年的好评
			, Half_a_YearModerate = "" // 最近半年的中评
			, Half_a_YearNegative = "" // 最近半年的差评
			, LongLongAgoPraise = "" // 半年以前的好价
			, LongLongAgoModerate = "" // 半年以前的中价
			, LongLongAgoNegative = "" // 半年以前的差价
	;

	public String getAll() {
		return "\n卖家信用等级/积分 : " + getCreditRating() + "\n卖家服务态度: " + getCreditAttitude() + "\n卖家保证金余额: "
				+ getEarnestMoney() + "\n卖家店铺的好评率: " + getPraiseLev() + "\n卖家认证信息: " + getAuthenticationInformation()
				+ "\n宝贝与描述相符的评分: " + getBabyAccord() + "\n物流服务态度: " + getLogisticsQuality() + "\n最近一周的好评: "
				+ getWeekPraise() + "\n最近一周的中评: " + getWeekModerate() + "\n最近一周的差评: " + getWeekNegative()
				+ "\n最近一月的好评: " + getMonthPraise() + "\n最近一月的中评: " + getMonthModerate() + "\n最近一月的差评: "
				+ getMonthNegative() + "\n最近半年的好评: " + getHalf_a_YearPraise() + "\n最近半年的中评: " + getHalf_a_YearModerate()
				+ "\n最近半年的差评: " + getHalf_a_YearNegative() + "\n半年前的好评: " + getLongLongAgoPraise() + "\n半年前的中评: "
				+ getLongLongAgoModerate() + "\n半年前的差评: " + getLongLongAgoNegative() + "\n";
	}

	public String getCreditRating() {
		return CreditRating;
	}

	public void setCreditRating(String creditRating) {
		CreditRating = creditRating;
	}

	public String getBabyAccord() {
		return BabyAccord;
	}

	public void setBabyAccord(String babyAccord) {
		BabyAccord = babyAccord;
	}

	public String getCreditAttitude() {
		return CreditAttitude;
	}

	public void setCreditAttitude(String creditAttitude) {
		CreditAttitude = creditAttitude;
	}

	public String getLogisticsQuality() {
		return LogisticsQuality;
	}

	public void setLogisticsQuality(String logisticsQuality) {
		LogisticsQuality = logisticsQuality;
	}

	public String getEarnestMoney() {
		return EarnestMoney;
	}

	public void setEarnestMoney(String earnestMoney) {
		EarnestMoney = earnestMoney;
	}

	public String getPraiseLev() {
		return PraiseLev;
	}

	public void setPraiseLev(String praiseLev) {
		PraiseLev = praiseLev;
	}

	public String getAuthenticationInformation() {
		return AuthenticationInformation;
	}

	public void setAuthenticationInformation(String authenticationInformation) {
		AuthenticationInformation = authenticationInformation;
	}

	public String getWeekPraise() {
		return WeekPraise;
	}

	public void setWeekPraise(String weekPraise) {
		WeekPraise = weekPraise;
	}

	public String getWeekModerate() {
		return WeekModerate;
	}

	public void setWeekModerate(String weekModerate) {
		WeekModerate = weekModerate;
	}

	public String getWeekNegative() {
		return WeekNegative;
	}

	public void setWeekNegative(String weekNegative) {
		WeekNegative = weekNegative;
	}

	public String getMonthPraise() {
		return MonthPraise;
	}

	public void setMonthPraise(String monthPraise) {
		MonthPraise = monthPraise;
	}

	public String getMonthModerate() {
		return MonthModerate;
	}

	public void setMonthModerate(String monthModerate) {
		MonthModerate = monthModerate;
	}

	public String getMonthNegative() {
		return MonthNegative;
	}

	public void setMonthNegative(String monthNegative) {
		MonthNegative = monthNegative;
	}

	public String getHalf_a_YearPraise() {
		return Half_a_YearPraise;
	}

	public void setHalf_a_YearPraise(String half_a_YearPraise) {
		Half_a_YearPraise = half_a_YearPraise;
	}

	public String getHalf_a_YearModerate() {
		return Half_a_YearModerate;
	}

	public void setHalf_a_YearModerate(String half_a_YearModerate) {
		Half_a_YearModerate = half_a_YearModerate;
	}

	public String getHalf_a_YearNegative() {
		return Half_a_YearNegative;
	}

	public void setHalf_a_YearNegative(String half_a_YearNegative) {
		Half_a_YearNegative = half_a_YearNegative;
	}

	public String getLongLongAgoPraise() {
		return LongLongAgoPraise;
	}

	public void setLongLongAgoPraise(String longLongAgoPraise) {
		LongLongAgoPraise = longLongAgoPraise;
	}

	public String getLongLongAgoModerate() {
		return LongLongAgoModerate;
	}

	public void setLongLongAgoModerate(String longLongAgoModerate) {
		LongLongAgoModerate = longLongAgoModerate;
	}

	public String getLongLongAgoNegative() {
		return LongLongAgoNegative;
	}

	public void setLongLongAgoNegative(String longLongAgoNegative) {
		LongLongAgoNegative = longLongAgoNegative;
	}
}
