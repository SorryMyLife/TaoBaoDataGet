package TianMao;

public class TianMaoShopInfo {
	private String ShopName = "" // 店铺名称
			, ShopLink = "" //店铺链接
			, ShopInfoLink = "" //店铺信息链接
			, ShopAddress = "" //店铺地址
			, ShopICO = "" //店铺图标链接
			, ShopQuantityOfCommodities = "" //店铺商品数量
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
