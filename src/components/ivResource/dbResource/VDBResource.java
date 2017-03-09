package components.ivResource.dbResource;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import components.ivResource.utilities.abstractIvResource.model.AbstractMivResource;
import components.ivResource.utilities.abstractIvResource.viewer.VaddNewRow;
import components.ivResource.utilities.abstractIvResource.viewer.VivResource;
import components.ivResource.utilities.abstractLocalFile.model.ALocalFileModel;
import components.ivResource.utilities.abstractLocalFile.model.CReadNextBlock;
import components.ivResource.utilities.abstractLocalFile.model.VFindRow;
import components.ivResource.utilities.interfaces.IivResource;
import components.izvestajniSistem.MIZvestajnogSistema;
import components.izvestajniSistem.MIzvestaj;
import components.izvestajniSistem.VIzSistGenerator;
import components.mainAppFrame.view.VMainAppFrame;
import components.metadata.MSingleMetaD;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class VDBResource extends VivResource{

	public VDBResource(IivResource model) {
		super(model);
		ArrayList<String> colNames = new ArrayList<>();
		this.setCentralStructure();
	}
	
	public void setCentralStructure(){
		table = new JTable((TableModel) model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		this.add(new JScrollPane(table), BorderLayout.CENTER);
		this.model = (DbTableModel) model;
		model.read();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
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
				new VFindRow(model.getMetaData(), mmodel, "DB");   //prosledio mu je meta datu i sebe
				
			}
		});
		
		JButton sort = new JButton("Sort");
		sort.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VDBSort((DbTableModel) model);
				
			}
		});
		this.addNewControl(sort,2);
		
		this.addNewControl(findRow, 2);
		VDBResource self = this;
		MIZvestajnogSistema mizv = VMainAppFrame.getInstance().getVIzvestajnogSistema().getModelIzvSis();
		for (MIzvestaj izv : mizv.getIzvestaji()) {
			MSingleMetaD md = izv.getParamMeta(((DbTableModel)model).getTableName());
			if (md != null){
				JButton izvestaj = new JButton(izv.toString());
				izvestaj.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						HashMap<String, Object> map = new HashMap<>();
						if (((DbTableModel)model).getValueAt(table.getSelectedRow(), md) != null)
							map.put(md.getKey(), ((DbTableModel)model).getValueAt(table.getSelectedRow(), md));
						JasperPrint print = izv.generateReport(map);
						JasperViewer.viewReport(print, false);
						
					}
				});
				this.addNewControl(izvestaj, 4);
			}
		}
	}


}
