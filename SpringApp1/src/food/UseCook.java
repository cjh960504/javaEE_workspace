package food;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UseCook {
	public static void main(String[] args) {

		/*DI (Dependency Injection) 
		 : ���� ��, ������ ����..
		�ǿ� ��, �������ִ� ��ü�� �ܺο��� ���Թ���!!!!
		 */		
			
		//�������� �̿����� �ʰ� ������ ��
		//���� �ø���
		/*
		 * FriPan pan = new FriPan(); Cook cook = new Cook();
		 * 
		 * cook.setPan(pan);//���� �丮�翡�� ���Խ�Ű��!!
		 * 
		 * cook.makeFood();
		 */
		
		//�������� �̿��ؼ� ��ü�� ���Խ��Ѻ���..
		//xml�� ���ϴ� ��ü�� ����ϸ�, �� ��ü�� �ۼ��� xml�� �ľ��Ͽ�
		//��ü���� �ν��Ͻ��� �����������ش�.. �̷��� ������ �����ϴ� ��ü�� ������
		//Spring Context ��ü�� �Ѵ�!!!!! (�ܺ������� ���� �ν��Ͻ� ����)
		ClassPathXmlApplicationContext context = null; //������xml ���������� �о �ۼ���
		//��ü�� �ν��ʹ½��� ���� �� �������ش�(���Ե� ����)
		//�ܺ�������(Assembler)
		context = new ClassPathXmlApplicationContext("spring/config/context.xml");
		
		//xml�� �̹� ������ �����̹Ƿ�, �޸𸮿��� �ν��Ͻ����� ������ ���̰�, �� �� � �ν��Ͻ��� ����������
		//getBean�޼���� �������� �ȴ�..
		Cook cook = (Cook)context.getBean("cook");
		cook.makeFood();
	}
}
