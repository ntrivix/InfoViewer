package components.FileMetaGenerator.view.VColumnElement;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.UUID;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import components.ivResourceElement.SupportedType;
import utilities.observer.Observable;
import utilities.observer.Observer;

public abstract class VAbstractColumnElement extends JPanel implements Observer{
	
	private final JComboBox J_COMBO_BOX = new JComboBox(SupportedType.values());
	JComboBox comboBox = J_COMBO_BOX;
	JTextField textField = new JTextField();
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel apanel = new JPanel();
	JPanel mpanel = new JPanel();
	private String key;

	public VAbstractColumnElement() {
		super();
		textField.setPreferredSize( new Dimension( 115, 24 ) );
		this.setLayout(new BorderLayout());
		panel1.add(textField);
		mpanel.add(panel1, BorderLayout.WEST);
		panel2.add(comboBox);
		mpanel.add(panel2, BorderLayout.EAST);
		this.add(mpanel, BorderLayout.NORTH);
		apanel.setLayout(new GridLayout(1, 0));
		this.add(apanel, BorderLayout.CENTER);
		this.key = UUID.randomUUID().toString();

		
	}

	public JComboBox getComboBox() {
		return comboBox;
	}

	public String getText() {
		return textField.getText();
	}
	

	public String getKey() {
		return key;
	}

	
	@Override
	public void update(Observable o, Object obj, String command) {
		// TODO Auto-generated method stub
		
	}
	

}
