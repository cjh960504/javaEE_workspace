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
	JButton bt;//�۾��� �� �̵���ư
	BoardModel model;
	NoticeDAO noticeDAO;
	ArrayList<Notice> boardList;//���� ����� ����
	
	public BoardList(BoardMain boardMain) {
		super(boardMain);
		//����
		table = new JTable(model = new BoardModel());
		scroll = new JScrollPane(table);
		bt = new JButton("�۾���");
		noticeDAO = new NoticeDAO();
		
		//��Ÿ��
		scroll.setPreferredSize(new Dimension((int)this.getPreferredSize().getWidth()-10, (int)this.getPreferredSize().getHeight()-100));
		//this.setBackground(Color.yellow);
		
		//����
		add(scroll);
		add(bt);
		
		getList();
		bt.addActionListener((e)->{
			boardMain.showPage(Pages.BoardWrite.ordinal());
		});
		
		//���̺� �� �ϳ��� ���ڵ带 �����ϸ� �󼼺��� �����ֱ�!!
		table.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				//�󼼺���� ���� ���� notice_id�� ����!!
				int row = table.getSelectedRow();
				Notice notice = boardList.get(row);
				BoardContent boardContent = (BoardContent)boardMain.pageList[Pages.BoardContent.ordinal()];
				boardContent.setData(notice);
				
				//�󼼺���� �������� notice_id�� ���!
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
