import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Calculator {
	private JTextArea field;
	private JButton[] numButtons;
	private JButton addition;
	private JButton subtraction;
	private JButton multiplication;
	private JButton division;
	private JButton point;
	private JButton delete;
	private JButton clear;
	private JButton equals;
	private JFrame calcFrame;
	private JPanel textPanel;
	private JPanel operatorsPanel;
	private JPanel numPanel;
	private JPanel delPanel;
	private ArrayList<Float> args;
	private String operator;
	private int argnum;

	Calculator(){
		args = new ArrayList<Float>();
		argnum = 1;
		operator = "";
		numButtons = new JButton[10];
		field = new JTextArea(3,30);
		initNumButtons();
		addition = new JButton("+");
		subtraction = new JButton("-");
		division = new JButton("/");
		multiplication = new JButton("*");
		addition.setBackground(Color.WHITE);
		addition.addActionListener(new operatorButtonListener());
		subtraction.setBackground(Color.WHITE);
		subtraction.addActionListener(new operatorButtonListener());
		multiplication.setBackground(Color.WHITE);
		multiplication.addActionListener(new operatorButtonListener());
		division.setBackground(Color.WHITE);
		division.addActionListener(new operatorButtonListener());
		point = new JButton(".");
		point.addActionListener(new operatorButtonListener());
		equals = new JButton("=");
		equals.addActionListener(new operatorButtonListener());
		delete = new JButton("Delete");
		clear = new JButton("Clear");
		delete.addActionListener(new delClearButtonListener());
		clear.addActionListener(new delClearButtonListener());
		calcFrame = new JFrame("Calculator");
		calcFrame.setSize(500, 600);
		calcFrame.setVisible(true);
		calcFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		calcFrame.setLayout(new BorderLayout());
		textPanel = new JPanel();
		textPanel.add(field);
		numPanel = new JPanel();
		numPanel.setLayout(new GridLayout(4,3));
		for(int i =0;i<9;i++)
			numPanel.add(numButtons[i]);
		numPanel.add(point);
		numPanel.add(numButtons[9]);
		numPanel.add(equals);
		operatorsPanel = new JPanel();
		operatorsPanel.setLayout(new GridLayout(4,1));
		operatorsPanel.add(addition);
		operatorsPanel.add(subtraction);
		operatorsPanel.add(multiplication);
		operatorsPanel.add(division);
		delPanel = new JPanel();
		delPanel.add(delete);
		delPanel.add(clear);
		calcFrame.add(textPanel, BorderLayout.NORTH);
		calcFrame.add(numPanel, BorderLayout.CENTER);
		calcFrame.add(operatorsPanel, BorderLayout.EAST);	
		calcFrame.add(delPanel, BorderLayout.SOUTH);		
        calcFrame.validate(); 
        field.setText(null);
	
	}
	private void initNumButtons() {
		for(int i = 1; i < 10; i++) {
	         numButtons[i-1] = new JButton(String.valueOf(i));
	         numButtons[i-1].addActionListener(new NumButtonListener());
	       }
		numButtons[9] = new JButton("0");
		numButtons[9].addActionListener(new NumButtonListener());
	}
	public static void main(String agrs[]) {
		Calculator calc = new Calculator();
	}
	
	class NumButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			field.append(e.getActionCommand());
		}
		
	}
	class delClearButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Delete")) {
				field.setText(field.getText().substring(0,field.getText().length()-1));
			}
			else if(e.getActionCommand().equals("Clear")) {
				field.setText(null);
				operator = "";
				args.clear();
			}
			
		}
		
	}
	class operatorButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("+")) {
				if(addition.getBackground() == Color.WHITE)
					addition.setBackground(Color.DARK_GRAY);
				else {
					addition.setBackground(Color.WHITE);
					operator = "";
				}
				if(field.getText().length() != 0) {
					operator = "+";
					args.add(Float.parseFloat(field.getText()));
				}
				field.setText(null);
			}
			else if(e.getActionCommand().equals("-")) {
				if(subtraction.getBackground() == Color.WHITE)
					subtraction.setBackground(Color.DARK_GRAY);
				else {
					operator = "";
					subtraction.setBackground(Color.WHITE);
				}
				if(field.getText().length() != 0) {
					operator = "-";
					args.add(Float.parseFloat(field.getText()));
				}
				field.setText(null);
			}
			else if(e.getActionCommand().equals("*")) {
				if(multiplication.getBackground() == Color.WHITE)
					multiplication.setBackground(Color.DARK_GRAY);
				else {
					multiplication.setBackground(Color.WHITE);
					operator = "";
				}
				if(field.getText().length() != 0) {
					operator = "*";
					args.add(Float.parseFloat(field.getText()));
				}
				field.setText(null);
			}
			else if(e.getActionCommand().equals("/")) {
				if(division.getBackground() == Color.WHITE)
					division.setBackground(Color.DARK_GRAY);
				else {
					operator = "";
					division.setBackground(Color.WHITE);
				}
				if(field.getText().length() != 0) {
					operator = "/";
					args.add(Float.parseFloat(field.getText()));
				}
				field.setText(null);
			}
			else if(e.getActionCommand().equals(".")) {
				field.append(".");
			}
			else if(e.getActionCommand().equals("=")){
				float ans = 0;
				args.add(Float.parseFloat(field.getText()));
				if(operator == "+") {
					ans = args.get(0) + args.get(1);
					addition.setBackground(Color.WHITE);
				}
				else if(operator == "-") {
					ans = args.get(0) - args.get(1);
					subtraction.setBackground(Color.WHITE);		
				}
				else if(operator == "*") {
					ans = args.get(0) * args.get(1);
					multiplication.setBackground(Color.WHITE);	
				}
				else if(operator == "/") {
					ans = args.get(0) / args.get(1);
					division.setBackground(Color.WHITE);	
				}
				else if(operator == "") {
					ans = args.get(0);
				}
				operator = "";
				field.setText(null);
				field.append(String.valueOf(ans));
				args.clear();
			}
			
		}
		
	}

	
}
