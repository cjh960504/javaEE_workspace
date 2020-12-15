package common.db;

public class text {
	public static void main(String[] args) {
		for(int i=1;i<=10;i++) {
			System.out.println(i+" : "+PoolManager2.getInstance());
		}
	}
}
