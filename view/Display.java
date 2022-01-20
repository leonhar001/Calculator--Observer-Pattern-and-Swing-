package calculator.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import calculator.model.Memory;
import calculator.model.ObserverMemory;

@SuppressWarnings("serial")
public class Display extends JPanel implements ObserverMemory{
	
	private final JLabel label;
	
	public Display() {
		
		Memory.getInstance().addObserver(this);
		
		setBackground(new Color(40, 40, 40));
		label = new JLabel(Memory.getInstance().getDisplayTxt());
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Arborea", Font.PLAIN, 30));
		
		setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 60));
		
		add(label);
		
	}

	public void alternateValue(String newValue) {
		label.setText(newValue);		
	}
}
