package components.FileMetaGenerator;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import components.FileMetaGenerator.view.VMetaGenerator;
import components.metadata.MSingleMetaD;
import globalActions.ActionManager;

public class VChooser extends JDialog{
	
	JPanel panel = new JPanel();
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	
	String[] dataList = {"Serial", "Sequential", "Index-sequential"};
	JComboBox comboB = new JComboBox(dataList);
	ArrayList<MSingleMetaD> mtd;
	
	public VChooser(){
		setModalityType(VMetaGenerator.ModalityType.DOCUMENT_MODAL);
		setSize(300, 300);
		setLocationRelativeTo(null);
		JLabel text = new JLabel("Select type of meta data:");
		JButton butt =  new JButton(ActionManager.getGenNewData(this));
		panel3.add(butt);
		panel.setLayout(new GridLayout(3, 1));
		panel1.add(text);
		panel2.add(comboB);
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);
		this.add(panel);
		//setVisible(true);
		
	}

	public JComboBox getComboB() {
		return comboB;
	}

	public ArrayList<MSingleMetaD> getMetaD() {
		return mtd;
	}

	public void setMetaD(ArrayList<MSingleMetaD> mtd) {
		this.mtd = mtd;
	}
	
	
}
