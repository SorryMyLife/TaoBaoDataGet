package TianMao;

public class TianMaoShopInfoValue {
	private String Name = "" // ��������
			, Address = "" // ���̵�ַ
			, Service = "" // ���̳�ŵ�ķ���
			, EarnestMoney = "" // ���̱�֤�����
			, AuthenticationInformation = "" // ��֤��Ϣ
			, BabyAccord = "" // �������������������
			, CreditAttitude = "" // ���ҷ���̬��
			, LogisticsQuality = "" // ��������̬��
	;

	public String getAll() {
		return "��������: " + getName() + "\n���̵�ַ: " + getAddress() + "\n���̳�ŵ�ķ���: " + getService() + "\n���̱�֤���: "
				+ getEarnestMoney() + "\n��֤��Ϣ: " + getAuthenticationInformation() + "\n�������������������: " + getBabyAccord()
				+ "\n���ҷ���̬������: " + getCreditAttitude() + "\n��������̬������: " + getLogisticsQuality() + "\n";
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
