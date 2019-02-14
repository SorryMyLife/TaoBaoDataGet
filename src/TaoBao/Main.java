package TaoBao;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//制作一个完整的淘宝数据采
		TaoBaoBabySearch s = new TaoBaoBabySearch();
//		s.setCookie("");
		s.getAll("Linux");
		
		
		
//		s.ThreadGetInfo("Android", 60, 10);
//		s.getAll("linux");
		
		System.out.println("ok");
	}

}
