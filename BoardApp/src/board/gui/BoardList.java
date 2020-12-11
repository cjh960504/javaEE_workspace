package board.gui;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import board.model.Notice;
import board.model.NoticeDAO;

public class BoardList extends Page{
	JTable table;
	JScrollPane scroll;
	JButton bt;//글쓰기 폼 이동버튼
	BoardModel model;
	NoticeDAO noticeDAO;
	ArrayList<Notice> boardList;//추후 사용할 예정
	
	public BoardList(BoardMain boardMain) {
		super(boardMain);
		//생성
		table = new JTable(model = new BoardModel());
		scroll = new JScrollPane(table);
		bt = new JButton("글쓰기");
		noticeDAO = new NoticeDAO();
		
		//스타일
		scroll.setPreferredSize(new Dimension((int)this.getPreferredSize().getWidth()-10, (int)this.getPreferredSize().getHeight()-100));
		//this.setBackground(Color.yellow);
		
		//조립
		add(scroll);
		add(bt);
		
		getList();
		bt.addActionListener((e)->{
			boardMain.showPage(Pages.BoardWrite.ordinal());
		});
		
		//테이블 중 하나의 레코드를 선택하면 상세보기 보여주기!!
		table.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				//상세보기로 가기 전에 notice_id를 추출!!
				int row = table.getSelectedRow();
				Notice notice = boardList.get(row);
				BoardContent boardContent = (BoardContent)boardMain.pageList[Pages.BoardContent.ordinal()];
				boardContent.setData(notice);
				
				//상세보기로 가기전에 notice_id를 사용!
				boardMain.showPage(Pages.BoardContent.ordinal());
			}
		});
	}
	
	public void getList() {
		this.boardList = noticeDAO.selectAll();
		model.list = boardList;
		table.updateUI();
	}
}
