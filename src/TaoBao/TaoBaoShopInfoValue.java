package TaoBao;

public class TaoBaoShopInfoValue {
	private String CreditRating = "" // �������õȼ�
			, BabyAccord = "" // �������������������
			, CreditAttitude = "" // ���ҷ���̬��
			, LogisticsQuality = "" // ��������̬��
			, EarnestMoney = "" // ��֤�����
			, PraiseLev = "" // ������
			, AuthenticationInformation = "" // ��֤��Ϣ
			, WeekPraise = "" // ���һ�ܵĺ���
			, WeekModerate = "" // ���һ�ܵ�����
			, WeekNegative = "" // ���һ�ܵĲ���
			, MonthPraise = "" // ���һ�µĺ���
			, MonthModerate = "" // ���һ�µ�����
			, MonthNegative = "" // ���һ�µĲ���
			, Half_a_YearPraise = "" // �������ĺ���
			, Half_a_YearModerate = "" // ������������
			, Half_a_YearNegative = "" // �������Ĳ���
			, LongLongAgoPraise = "" // ������ǰ�ĺü�
			, LongLongAgoModerate = "" // ������ǰ���м�
			, LongLongAgoNegative = "" // ������ǰ�Ĳ��
	;

	public String getAll() {
		return "\n�������õȼ�/���� : " + getCreditRating() + "\n���ҷ���̬��: " + getCreditAttitude() + "\n���ұ�֤�����: "
				+ getEarnestMoney() + "\n���ҵ��̵ĺ�����: " + getPraiseLev() + "\n������֤��Ϣ: " + getAuthenticationInformation()
				+ "\n�������������������: " + getBabyAccord() + "\n��������̬��: " + getLogisticsQuality() + "\n���һ�ܵĺ���: "
				+ getWeekPraise() + "\n���һ�ܵ�����: " + getWeekModerate() + "\n���һ�ܵĲ���: " + getWeekNegative()
				+ "\n���һ�µĺ���: " + getMonthPraise() + "\n���һ�µ�����: " + getMonthModerate() + "\n���һ�µĲ���: "
				+ getMonthNegative() + "\n�������ĺ���: " + getHalf_a_YearPraise() + "\n������������: " + getHalf_a_YearModerate()
				+ "\n�������Ĳ���: " + getHalf_a_YearNegative() + "\n����ǰ�ĺ���: " + getLongLongAgoPraise() + "\n����ǰ������: "
				+ getLongLongAgoModerate() + "\n����ǰ�Ĳ���: " + getLongLongAgoNegative() + "\n";
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
