package calculator.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import calculator.model.Memory;

@SuppressWarnings("serial")
public class Keyboard extends JPanel implements ActionListener{
	
	private final Color GRAY_STRONG = new Color(69, 69, 69);
	private final Color GRAY_LIGHT= new Color(99, 99, 99);
	private final Color PURPLE = new Color(110, 26, 126);
	
	public Keyboard() {
		
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		setLayout(layout);
		
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		
		// Line 1
		c.gridwidth = 2;
		addButton("AC", GRAY_STRONG, c, 0, 0);
		c.gridwidth = 1;
		addButton("\u00b1", GRAY_STRONG, c, 2, 0);
		addButton("/", PURPLE, c, 3, 0);
		
		// Line 2
		addButton("7", GRAY_LIGHT, c, 0, 1);
		addButton("8", GRAY_LIGHT, c, 1, 1);
		addButton("9", GRAY_LIGHT, c, 2, 1);
		addButton("*", PURPLE, c, 3, 1);		//U+2715
		
		// Line 3
		addButton("4", GRAY_LIGHT, c, 0, 2);
		addButton("5", GRAY_LIGHT, c, 1, 2);
		addButton("6", GRAY_LIGHT, c, 2, 2);
		addButton("-", PURPLE, c, 3, 2);		
		
		// Line 4
		addButton("1", GRAY_LIGHT, c, 0, 3);
		addButton("2", GRAY_LIGHT, c, 1, 3);
		addButton("3", GRAY_LIGHT, c, 2, 3);
		addButton("+", PURPLE, c, 3, 3);		
		
		// Line 5
		c.gridwidth = 2;
		addButton("0", GRAY_LIGHT, c, 0, 4);
		c.gridwidth = 1;
		addButton(".", GRAY_LIGHT, c, 2, 4);
		addButton("=", PURPLE, c, 3, 4);		
	
	}

	private void addButton(String text, Color color, GridBagConstraints c, int x, int y) {
		
		c.gridx = x;
		c.gridy = y;
		Button button =  new Button(text, color);
		button.addActionListener(this);
		add(button, c);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton button = (JButton) e.getSource();
			Memory.getInstance().manageCommands(button.getText());
		}
	}
}
