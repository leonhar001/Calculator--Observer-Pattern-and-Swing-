package calculator.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import calculator.model.Memory;
/*NÃO UTILIZADO NO PROJETO INICIAL*/
@SuppressWarnings("serial")
public class TitleBar extends JPanel implements ActionListener{
	
	private final Color GRAY_STRONG = new Color(40, 40, 40);

	public TitleBar() {
		
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setLayout(layout);
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;

		c.gridwidth = 2;
		addButton("", GRAY_STRONG, c, 0, 0);
		c.gridwidth = 1;
		addButton("\u005f", GRAY_STRONG, c, 2, 0);
		addButton("\u274c", GRAY_STRONG, c, 3, 0);
		
	}

	private void addButton(String text, Color color, GridBagConstraints c, int x, int y) {
		
		c.gridx = x;
		c.gridy = y;
		Button button =  new Button(text, color);
		button.setBorderPainted(false);
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
