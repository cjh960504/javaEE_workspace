package test;

public class UseDog {
	public static void main(String[] args) {
		//new���� �ʰ� ���� ����
		
		Dog d1 = Dog.getInstance();
		System.out.println(d1);
		Dog d2 = Dog.getInstance();
		System.out.println(d2);
		Dog d3 = Dog.getInstance();
		System.out.println(d3);
		Dog d4 = Dog.getInstance();
		System.out.println(d4);
		
	}
}
