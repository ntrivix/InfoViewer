package components.settings.extensionManager.view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import components.settings.extensionManager.controller.CAddNewExtension;

public class VExtensionManager extends JPanel{
	
	private JTextField textField;
	private JButton addButton;
	private static VExtensionManager instance;
	private VExtensionContainer vec = new VExtensionContainer();

	private VExtensionManager() {
		super(new BorderLayout());	
		textField = new JTextField();
		addButton = new JButton(new CAddNewExtension());
		JLabel label = new JLabel();
		label.setLayout(new BorderLayout());
		label.add(textField,BorderLayout.CENTER);
		label.add(addButton, BorderLayout.EAST);
		this.add(label,BorderLayout.PAGE_START);
		this.add(vec, BorderLayout.CENTER);
		
	}

	public static VExtensionManager getInstance() {
		if (instance == null) {
			instance = new VExtensionManager();
		}
		return instance;
	}

	public JTextField getTextField() {
		return textField;
	}
	

	public VExtensionContainer getVExtensionContainer() {
		return vec;
	}
	

}
