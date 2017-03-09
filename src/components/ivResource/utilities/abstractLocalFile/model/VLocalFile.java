package components.ivResource.utilities.abstractLocalFile.model;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import components.ivResource.utilities.abstractIvResource.model.AbstractMivResource;
import components.ivResource.utilities.abstractIvResource.viewer.VaddNewRow;
import components.ivResource.utilities.abstractIvResource.viewer.VivResource;
import components.ivResource.utilities.interfaces.IivResource;
import components.metadata.MSingleMetaD;

public class VLocalFile extends VivResource {
		
	public VLocalFile(IivResource model) {
		super(model);
		ArrayList<String> colNames = new ArrayList<>();
		//table = new JTable(data, colNames.toArray());
		
		this.setCentralStructure();
	}
	
	public void setCentralStructure(){
		table = new JTable((TableModel) model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

		this.add(new JScrollPane(table), BorderLayout.CENTER);
		//this.add(new JButton());
		this.model = (ALocalFileModel) model;
		model.read();
	}

	@Override
	public String toString() {
		return model.toString(); 
	}

	@Override
	public void close() {
		
		super.close();
		this.getParent().remove(this);
	}

	@Override
	protected void toolbar() {
		super.toolbar();
		this.addNewControl(new JButton(new CReadNextBlock(model)), 0);
		
		AbstractMivResource mmodel = (AbstractMivResource) this.model;
		
		JButton addNewRow = new JButton("New Row");
		addNewRow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VaddNewRow(model.getMetaData(), mmodel);
			}
		});
		
		this.addNewControl(addNewRow, 1);
		
		JButton findRow = new JButton("Find Row");
		findRow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VFindRow(model.getMetaData(), mmodel, "RES");   //prosledio mu je meta datu i sebe
				
			}
		});
		
		this.addNewControl(findRow, 2);
	}

	

		

}
