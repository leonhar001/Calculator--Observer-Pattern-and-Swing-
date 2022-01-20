package calculator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Calculator extends JFrame{
	
	public Calculator() {
		
		setupLayout();
		
		setSize(300,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		setVisible(true);
	}
	
	private void setupLayout() {
		setLayout(new BorderLayout());
		//setUndecorated(true);
		
		/*TitleBar titleBar = new TitleBar();
		titleBar.setPreferredSize(new Dimension(300,30));
		add(titleBar, BorderLayout.NORTH);
		*/
		Display display = new Display();
		display.setPreferredSize(new Dimension(300,150));
		//add(display, BorderLayout.NORTH);
		add(display, BorderLayout.CENTER);
		
		Keyboard keyboard = new Keyboard();
		//add(keyboard, BorderLayout.CENTER);
		keyboard.setPreferredSize(new Dimension(300, 250));
		keyboard.setBackground(new Color(40, 40, 40));
		add(keyboard, BorderLayout.SOUTH);

	}

	public static void main(String[] args) {
		new Calculator();
	}

}
