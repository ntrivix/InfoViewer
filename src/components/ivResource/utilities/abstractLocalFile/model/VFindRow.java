package components.ivResource.utilities.abstractLocalFile.model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.accessibility.internal.resources.accessibility;

import components.ivResource.serialDat.model.MSerialDat;
import components.ivResource.utilities.abstractIvResource.model.AbstractMivResource;
import components.ivResourceElement.AbstractResourceElement;
import components.ivResourceElement.ResourceRow;
import components.metadata.MMetaData;
import components.metadata.MSingleMetaD;
import globalActions.ActionManager;
import sun.tools.jar.resources.jar;
import utilities.Convert;

public class VFindRow extends JDialog{
	
	private MMetaData md;
	private AbstractMivResource resource;
	JPanel panel = new JPanel();
	JPanel panel2 = new JPanel();
	Panel1 tlimit = new Panel1("Return Limit");
	JPanel dostaMiJeSvega = new JPanel();
	JPanel kojiVecPoRedu = new JPanel();
	JCheckBox jcb = new JCheckBox("Save in New File");
	String type;
	
	JCheckBox fromBeginOfFile = new JCheckBox("Pretraži od počtka");
	
	public VFindRow(MMetaData md, AbstractMivResource resource, String type) {
		this.md = md;
		this.type = type;
		this.resource = resource;
		panel.setLayout(new GridLayout(0, 2));
		
		setModalityType(ModalityType.APPLICATION_MODAL);
		setSize(500, 500);
		setLocationRelativeTo(null);
		
		dostaMiJeSvega.add(jcb);
		if (resource instanceof MSerialDat)
			dostaMiJeSvega.add(fromBeginOfFile);
		kojiVecPoRedu.add(tlimit, BorderLayout.NORTH);
		kojiVecPoRedu.add(dostaMiJeSvega, BorderLayout.SOUTH);
		this.add(kojiVecPoRedu, BorderLayout.NORTH);
		
		for (MSingleMetaD msmd :md.getAll()) {
			panel.add(new Panel1(msmd.getDescript()));
		}
		
		this.add(panel,BorderLayout.CENTER);
		
		JButton find = null;
		if (type.equals("DB")){
		find = new JButton(ActionManager.getOpenFoundINDB(this));
		}
		else if (type.equals("RES")){
			find = new JButton(ActionManager.getOpenFound(this));
		}
		
		panel2.add(find);
		this.add(panel2,BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	public boolean isFromBeg(){
		return fromBeginOfFile.isSelected();
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

	public JPanel getPanel() {
		return panel;
	}

	public String getTLimit(){
		return ((Panel1)tlimit).getText();
	}

	public MMetaData getMd() {
		return md;
	}

	public AbstractMivResource getResource() {
		return resource;
	}
	
	public boolean saveToFileIsChecked(){
		return jcb.isSelected();
	}

	public String[] getTexts(){
		ArrayList<String> texts = new ArrayList<>();
		for (Component panel1 : panel.getComponents()) {
			String text = ((Panel1) panel1).getText();
			texts.add(text);
		}
		return (String[])texts.toArray(new String[texts.size()]);
	}

}
