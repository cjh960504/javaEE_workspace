package common.file;

import java.io.File;

public class FileManager {
	//Ȯ���ڸ� �����ϱ�
	public static String getExtend(String path) {
		// �ƹ��ų�ġ����.jpg
		int last= path.lastIndexOf(".");
		String ext = path.substring(last+1, path.length());
		return ext;
	}
	//���ϻ���
	public static boolean deleteFile(String path) {
		File file = new File(path);
		boolean result = file.delete();
		return result;
	}
//	//���� �׽�Ʈ
//	public static void main(String[] args) {
//		String filename = "c:\\photo\\�ƹ��ų�ġ����.jpg";
//		getExtend(filename);
//	}
}
