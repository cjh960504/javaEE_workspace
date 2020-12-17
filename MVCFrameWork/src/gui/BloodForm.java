package gui;

import java.awt.Choice;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import blood.model.BloodAdviser;

public class BloodForm extends JFrame{
	Choice ch;
	JButton bt;
	BloodAdviser bloodAdviser;
	public BloodForm() {
		ch = new Choice();
		bt = new JButton("분석보기");
		bloodAdviser = new BloodAdviser();
		ch.addItem("혈액형을 선택하세요.");
		ch.addItem("A");
		ch.addItem("B");
		ch.addItem("O");
		ch.addItem("AB");
		
		setLayout(new FlowLayout());
		add(ch);
		add(bt);
		
		setSize(400, 200);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		bt.addActionListener((e)->{
			showResult();
		});
	}
	
	public void showResult() {
		String msg = bloodAdviser.getAdvice(ch.getSelectedItem());
		JOptionPane.showMessageDialog(this, msg);
	}
	
	public static void main(String[] args) {
		new BloodForm();
	}
}
