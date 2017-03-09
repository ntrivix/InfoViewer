package components.ivResource.utilities.abstractIvResource.viewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import components.ivResource.utilities.abstractIvResource.model.AbstractMivResource;
import components.ivResourceElement.AbstractResourceElement;
import components.ivResourceElement.ResourceRow;
import components.ivResourceElement.interfaces.IivResourceRow;
import components.metadata.MMetaData;
import components.metadata.MSingleMetaD;
import utilities.Convert;

public class VaddNewRow extends JDialog{
	
	private MMetaData md;
	JPanel panel = new JPanel();
	JPanel panel1 = new JPanel();
	AbstractMivResource resource;
	
	public VaddNewRow(MMetaData md, AbstractMivResource resource){
		this.md = md;
		this.resource = resource;
		panel.setLayout(new GridLayout(0, 2));
		
		setModalityType(ModalityType.APPLICATION_MODAL);
		setSize(500, 500);
		setLocationRelativeTo(null);
		
		for (MSingleMetaD msmd :md.getAll()) {
			panel.add(new Panel1(msmd.getDescript()));
		}
		
		this.add(panel,BorderLayout.CENTER);
		
		JPanel panel2 = new JPanel();
		
		JButton save = new JButton("Save");
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				ArrayList< AbstractResourceElement> elements = new ArrayList<>();
				int i = 0;
				
				//msinglemetadata.getmodel
				
				for (Component panel1 : panel.getComponents()) {
					
					String text = ((Panel1)panel1).getText();
					
					try
					{
						elements.add(md.getMetaD(i).generateModel(text));
					}catch(Exception notConverted){
						((Panel1)panel1).getText1().setBackground(Color.RED);
						return;
					}
					if (md.getMetaD(i).isMandatory() && text.equals("")){
						((Panel1)panel1).getText1().setBackground(Color.RED);
						return;
					}
					
					i++;
				}
				
				ResourceRow newRow = new ResourceRow(elements);
				
				resource.addNewRow((IivResourceRow)newRow);
				setVisible(false);

			}
		});
		
		panel2.add(save);
		
		this.add(panel2,BorderLayout.SOUTH);
		setVisible(true);
	}
	
	private class Panel1 extends JPanel{
		
		private JLabel label;
		private JTextField text=new JTextField();
		
		public Panel1(String description){
			text.setPreferredSize( new Dimension( 115, 24 ) );
			this.add(text);
			this.add(new JLabel(description));
		}
		
		public String getText(){
			return text.getText();
		}
		
		public JTextField getText1(){
			return text;
		}
		
	}
	

}
