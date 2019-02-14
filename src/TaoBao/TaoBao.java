package TaoBao;

/*
 * 一个接口
 * 全都需要实现
 * 
 * */

public interface TaoBao {
	
	public void getAll(String search_name);
	public String[] getAllArray(String search_name);
	public void getAll(String search_name , int maxpage);
	public String[] getAllArray(String search_name , int maxpage);
	public String getAllString(String search_name , int maxpage);
	public String getAllString(String search_name);
	public void setCity(String city_name);
	public String getCity();
	public void setCookie(String cookie);
	public String getCookie();
	public void setUserAgent(String ua);
	public String getUserAgent();
	public int getLength();
	public void SalesVolumeTop(String search_name);
	public String[] SalesVolumeTopArray(String search_name);
	public String SalesVolumeTopString(String search_name);
	public void CreditTop(String search_name);
	public String[] CreditTopArray(String search_name);
	public String CreditTopString(String search_name);
	public void SalesVolumeTop(String search_name,int maxpage);
	public String[] SalesVolumeTopArray(String search_name,int maxpage);
	public String SalesVolumeTopString(String search_name,int maxpage);
	public void CreditTop(String search_name,int maxpage);
	public String[] CreditTopArray(String search_name,int maxpage);
	public String CreditTopString(String search_name,int maxpage);
	public void SaveLocal(String search_name , String path);
	public void SaveLocal(String search_name , String path , String save_name);
	public void SaveLocal(String search_name , String path , int maxpage);
	public void SaveLocal(String search_name , String path ,String save_name , int maxpage);
	public void SaveCreditTopLocal(String search_name , String path);
	public void SaveCreditTopLocal(String search_name , String path, String save_name);
	public void SaveCreditTopLocal(String search_name , String path , int maxpage);
	public void SaveCreditTopLocal(String search_name , String path, String save_name , int maxpage);
	public void SaveSalesVolumeTopLocal(String search_name , String path , int maxpage);
	public void SaveSalesVolumeTopLocal(String search_name , String path, String save_name , int maxpage);
	public void SaveSalesVolumeTopLocal(String search_name , String path);
	public void SaveSalesVolumeTopLocal(String search_name , String path, String save_name);
	
	
	
}
