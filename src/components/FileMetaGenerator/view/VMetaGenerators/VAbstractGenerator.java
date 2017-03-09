package components.FileMetaGenerator.view.VMetaGenerators;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import components.FileMetaGenerator.view.VMetaGenerator;
import components.FileMetaGenerator.view.VColumnElement.VAbstractColumnElement;
import components.ivResourceElement.SupportedType;
import components.metadata.MMetaData;
import components.metadata.MSingleMetaD;
import globalActions.ActionManager;
import utilities.observer.Observable;
import utilities.observer.Observer;

public abstract class VAbstractGenerator extends JDialog implements Observer{
	
	ArrayList<VAbstractColumnElement> elements = new ArrayList<>(); 
	ArrayList<MSingleMetaD> mSingleMetaDs = new ArrayList<>(); 
	String md;
	JPanel panel = new JPanel();
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	
	public VAbstractGenerator(Frame owner, String md) {
		super(owner);
		//this.add(new Scrollbar(), BorderLayout.EAST);
		//FALI MU SCROLL BAR
		this.md = md;
		setModalityType(VMetaGenerator.ModalityType.DOCUMENT_MODAL);
		setSize(500, 500);
		setLocationRelativeTo(null);
		panel.setLayout(new GridLayout(0, 2));
		JButton generate = new JButton(ActionManager.getSaveMD(md, this));
		
		JButton addNewElement = new JButton("Add");
		addNewElement.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addNewElement();
				revalidate();
			}
		});
		
		panel1.add(addNewElement);
		panel2.add(generate);
		
		this.add(panel1,BorderLayout.NORTH);
		this.add(panel,BorderLayout.CENTER);
		this.add(panel2,BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	abstract void addNewElement();
	

	@Override
	public void update(Observable o, Object obj, String command) {
		// TODO Auto-generated method stub
		
	}

	
	public ArrayList<MSingleMetaD> getMetaData() {
		return mSingleMetaDs;
	}
	
	public MMetaData getMetaDataObject(){
		MMetaData m = new MMetaData();
		for (MSingleMetaD md : mSingleMetaDs) {
			m.addNewMetaD(md);
		}
		return m;
	}

	public ArrayList<VAbstractColumnElement> getElements() {
		return elements;
	}
	
	

}
