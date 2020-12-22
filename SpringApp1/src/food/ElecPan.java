package food;

public class ElecPan implements Pan{
	@Override
	public void boil() {
		System.out.println("전기를 이용하여 음식을 데워요.");
	}
}
