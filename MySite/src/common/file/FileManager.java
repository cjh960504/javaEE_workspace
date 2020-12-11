package common.file;

public class FileManager {
	//확장자만 추출하기
	public static String getExtend(String path) {
		// 아무거나치는중.jpg
		int last= path.lastIndexOf(".");
		String ext = path.substring(last+1, path.length());
		return ext;
	}
	
//	//단위 테스트
//	public static void main(String[] args) {
//		String filename = "c:\\photo\\아무거나치는중.jpg";
//		getExtend(filename);
//	}
}
