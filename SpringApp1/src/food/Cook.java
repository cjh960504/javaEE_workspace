/*
	요리사를 정의한다.
*/
package food;

public class Cook {	
	//상위 자료형으로 has a 관계를 명시했을 때 얻는 장점?
	//하위 자료형이 삭제되거나, 변화가 생기더라도 현재클래스와 has a 관계에 있는
	//의존성을 약화시켰기 때문에 유지보수성을 높여줄 수 있다.
	Pan pan; //정확한 자료형으로 has a 관계를 표시하지 잇기 없기?

	//Spring을 이용하지 않고 만들어보기
	//외부로부터 필요한 객체를 주입받기 위한 set메서드
	public void setPan(Pan pan) {
		this.pan = pan;
	}
	
	public void makeFood() {
		pan.boil();
	}
}

