package movie.model;

public class MovieInfo {
	public String getInfo(String movie) {
		String msg = null;
		if(movie.equals("스타워즈")) {
			msg = "스타워즈는 미국의 스페이스 오페라 장르를 대표하는 조지 루카스의 영화 시리즈이다";
		}else if(movie.equals("존윅")) {
			msg = "그를 건드리지 말았어야 했다. " + 
					"상대를 잘못 고른 적들을 향한 통쾌한 복수!";
		}else if(movie.equals("분뇨의질주")) {
			msg = "스트리트 레이싱을 소재로 다룬 자동차 액션 영화 시리즈.[2] 이전까지만 해도 액션 영화의 한 시퀀스에 불과했던 자동차 액션(카 체이스)을 영화의 소재로 옮겨왔던 시리즈. 물론 자동차 관련하여 가장 유명한 시리즈";
		}else if(movie.equals("미션임파서블")) {
			msg = "미션 임파서블: 폴아웃 (Mission: Impossible - Fallout)은 미션 임파서블 시리즈 6번째 영화이다. 이 영화는 미션 임파서블 시리즈 중에서 유일하게 전작과 스토리가 이어지는 영화이다.";
		}
		return msg;
	}
}
