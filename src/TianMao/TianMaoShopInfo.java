package TianMao;

public class TianMaoShopInfo {
	private String ShopName = "" // ��������
			, ShopLink = "" //��������
			, ShopInfoLink = "" //������Ϣ����
			, ShopAddress = "" //���̵�ַ
			, ShopICO = "" //����ͼ������
			, ShopQuantityOfCommodities = "" //������Ʒ����
			;

	public String getShopQuantityOfCommodities() {
		return ShopQuantityOfCommodities;
	}

	public void setShopQuantityOfCommodities(String shopQuantityOfCommodities) {
		ShopQuantityOfCommodities = shopQuantityOfCommodities;
	}

	public String getShopName() {
		return ShopName;
	}

	public String getShopLink() {
		return ShopLink;
	}

	public String getShopInfoLink() {
		return ShopInfoLink;
	}

	public String getShopAddress() {
		return ShopAddress;
	}

	public String getShopICO() {
		return ShopICO;
	}

	public void setShopName(String shopName) {
		ShopName = shopName;
	}

	public void setShopLink(String shopLink) {
		ShopLink = shopLink;
	}

	public void setShopInfoLink(String shopInfoLink) {
		ShopInfoLink = shopInfoLink;
	}

	public void setShopAddress(String shopAddress) {
		ShopAddress = shopAddress;
	}

	public void setShopICO(String shopICO) {
		ShopICO = shopICO;
	}

}
