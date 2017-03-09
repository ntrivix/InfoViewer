package components.izvestajniSistem;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import components.FileMetaGenerator.view.VMetaGenerator;
import components.metadata.MSingleMetaD;

public class VIzSistGenerator extends JDialog{
		
	MIzvestaj mIzvestaj;
	Panel gpanel = new Panel();
	Panel dPanel = new Panel();
	Panel panel = new Panel();
	ArrayList<PPanel> elements = new ArrayList<>();
	
	public VIzSistGenerator(MIzvestaj mIzvestaj) {
		this.mIzvestaj = mIzvestaj;
		setModalityType(VMetaGenerator.ModalityType.DOCUMENT_MODAL);
		setSize(500, 500);
		setLocationRelativeTo(null);
		gpanel.setLayout(new GridLayout(0, 2));
		panel.setLayout(new BorderLayout());
		
		for (int i = 0; i<mIzvestaj.getParamCount(); i++){
			PPanel panel1 = new PPanel(mIzvestaj.getLabel(i), mIzvestaj.getParameter(i).getKey());
			elements.add(panel1);
			gpanel.add(panel1);
		}
		VIzSistGenerator self = this;
		JButton button = new JButton("Generate report");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				self.setVisible(false);
			}
		});
		
		dPanel.add(button);
		panel.add(gpanel,BorderLayout.NORTH);
		panel.add(dPanel,BorderLayout.SOUTH);
		
		this.add(panel);
		setVisible(true);
	}
	
	public ArrayList getElements(){
		return elements;
	}
	
	public HashMap<String, Object> getHashMap(){
		HashMap<String, Object> hm = new HashMap<>();
		for (PPanel pPanel : elements) {
			if (pPanel.getText() != null)
				hm.put(pPanel.getKey(), pPanel.getText());
		}
		return hm;
	}
	
	private class PPanel extends JPanel{
		
		JTextField textField;
		String key;
		
		public PPanel(String name, String key) {
			this.key = key;
			JLabel label = new JLabel(name);
			textField = new JTextField();
			textField.setPreferredSize( new Dimension( 115, 24 ) );
			JPanel innerPanel = new JPanel();
			this.setLayout(new GridLayout(1, 2));
			this.add(label);
			this.add(textField);
			
		}
		
		public String getText(){
			if (textField.getText().equals("")) return null;
			return textField.getText();
		}
		
		public String getKey(){
			return key;
		}
	
	}
	
}
