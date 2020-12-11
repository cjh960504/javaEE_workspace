package board.gui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import board.model.Notice;
import board.model.NoticeDAO;

public class BoardWrite extends Page{
	JTextField t_author;
	JTextField t_title;
	JTextArea area;
	JScrollPane scroll;
	JButton bt_regist;
	NoticeDAO noticeDAO;//CRUD 전담 객체 보유 (Has a)
	
	public BoardWrite(BoardMain boardMain) {
		super(boardMain);
		
		//생성
		t_author = new JTextField();
		t_title = new JTextField();
		area = new JTextArea();
		scroll = new JScrollPane(area);
		bt_regist= new  JButton("등록하기");
		noticeDAO = new  NoticeDAO();
		
		//스타일
		t_author.setPreferredSize(new Dimension((int) this.getPreferredSize().getWidth(), 25));
		t_title.setPreferredSize(new Dimension((int) this.getPreferredSize().getWidth(), 25));
		scroll.setPreferredSize(new Dimension((int) this.getPreferredSize().getWidth(), 500));
		
		//조립
		add(t_author);
		add(t_title);
		add(scroll);
		add(bt_regist);
		
		//등록
		bt_regist.addActionListener((e)->{
			//호출전에 매개변수로 VO완성하여 전달
			Notice notice = new Notice();
			notice.setAuthor(t_author.getText());
			notice.setTitle(t_title.getText());
			notice.setContent(area.getText());
			
			int result = noticeDAO.regist(notice);//DAO에게 등록을 맡기자 
			
			if(result==0) {
				JOptionPane.showMessageDialog(BoardWrite.this, "등록 실패");
			}else {
				JOptionPane.showMessageDialog(BoardWrite.this, "등록 성공");
			}
		});
	}
	
}
