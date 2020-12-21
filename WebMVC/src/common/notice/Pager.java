package common.notice;

import java.net.HttpRetryException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class Pager {
	int totalRecord;
	int pageSize=10;
	int totalPage;
	int blockSize=10;
	int firstPage;
	int lastPage;
	int currentPage=1;
	int num;
	int curPos;
	
	public void init(HttpServletRequest request ,List list) {
		totalRecord = list.size();
		totalPage = (int)Math.ceil((float)totalRecord/pageSize);
		if(request.getParameter("currentpage")!=null) {
			currentPage = Integer.parseInt("currentPage");
		}
		firstPage = currentPage - (currentPage-1)%blockSize;
		lastPage = firstPage + (blockSize-1);
		curPos = pageSize*(currentPage-1)+1;
		num = totalRecord - curPos;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public int getBlockSize() {
		return blockSize;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getNum() {
		return num;
	}

	public int getCurPos() {
		return curPos;
	}
	
	
}
