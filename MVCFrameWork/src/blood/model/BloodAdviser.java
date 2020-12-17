package blood.model;

public class BloodAdviser {
	public String getAdvice(String blood) {
		String msg=null;
		
		if(blood.equals("A")){
			msg="꼼꼼하다/세심하다/착하다/솔직하다 | 소심하다";
		}else if(blood.equals("B")){
			msg="엉뚱하다/쿨하다/활발하다 | 고집이 쌔다";
		}else if(blood.equals("O")){
			msg = "사교성있다/둥글둥글하다 | 오지랖이 넓다";
		}else if(blood.equals("AB")){
			msg = "결정장애/4차원적이다";
		}
		return msg;
	}
}
