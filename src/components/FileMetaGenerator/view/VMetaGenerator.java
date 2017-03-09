package components.FileMetaGenerator.view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import components.FileMetaGenerator.view.VColumnElement.VAbstractColumnElement;
import components.ivResourceElement.SupportedType;
import components.metadata.MMetaData;
import components.metadata.MSingleMetaD;
import utilities.observer.Observable;
import utilities.observer.Observer;

public class VMetaGenerator extends JDialog implements Observer {

	ArrayList<VAbstractColumnElement> elements = new ArrayList<>();
	ArrayList<MSingleMetaD> mSingleMetaDs = new ArrayList<>();
	JPanel panel2 = new JPanel();
	

	public VMetaGenerator(Frame owner) {
		super(owner);
		setModalityType(VMetaGenerator.ModalityType.DOCUMENT_MODAL);
		setSize(500, 500);
		setLocationRelativeTo(null);
		//setLayout(new GridLayout(3, 1));
		//setLayout(new GroupLayout());
		
		JPanel panel1 = new JPanel();
		JPanel panel3 = new JPanel();
		panel2.setLayout(new GridLayout(0, 2));
		
		JButton addNewElement = new JButton("Add");
		addNewElement.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				addNewElement();
				revalidate();

			}
		});
		
		panel1.add(addNewElement);
		this.add(panel1,BorderLayout.NORTH);
		this.add(panel2,BorderLayout.CENTER);
		
		JButton generate = new JButton("Save");
		generate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				mSingleMetaDs.clear();
				
				for (VAbstractColumnElement vColumnElement : elements) {
					
					Class type = ((SupportedType)vColumnElement.getComboBox().getSelectedItem()).getRealClass();
					String key = vColumnElement.getKey();
					String textF = vColumnElement.getText();
					
					if (!textF.equals("") && type!=null){
						mSingleMetaDs.add(new MSingleMetaD<>(type, key, textF));
						
					}
				}
				
				setVisible(false);
			}
		});
		
		
		panel3.add(generate);
		this.add(panel3,BorderLayout.SOUTH);

	}

	void addNewElement() {
		//VAbstractColumnElement vce = new VAbstractColumnElement();
		//panel2.add(vce);
		//elements.add(vce);
		this.revalidate();

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

	@Override
	public void update(Observable o, Object obj, String command) {
		// TODO Auto-generated method stub

	}

}
