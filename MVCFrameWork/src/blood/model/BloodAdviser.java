package blood.model;

public class BloodAdviser {
	public String getAdvice(String blood) {
		String msg=null;
		
		if(blood.equals("A")){
			msg="�Ĳ��ϴ�/�����ϴ�/���ϴ�/�����ϴ� | �ҽ��ϴ�";
		}else if(blood.equals("B")){
			msg="�����ϴ�/���ϴ�/Ȱ���ϴ� | ������ �ش�";
		}else if(blood.equals("O")){
			msg = "�米���ִ�/�ձ۵ձ��ϴ� | �������� �д�";
		}else if(blood.equals("AB")){
			msg = "�������/4�������̴�";
		}
		return msg;
	}
}
