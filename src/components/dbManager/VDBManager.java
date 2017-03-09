package components.dbManager;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

public class VDBManager extends JTree{
	
	private MDBManager mymodel;
	
	public VDBManager() {
		this.setModel(new DefaultTreeModel(null));
		this.addMouseListener(new COpenTable(this));
		this.setCellRenderer(new MyTreeCellRenderer());
		this.setCellEditor(new MyTreeCellEditor(this, (DefaultTreeCellRenderer)this.getCellRenderer()));
	}

	@Override
	public void setModel(TreeModel newModel) {
		// TODO Auto-generated method stub
		super.setModel(newModel);
		try {
		this.mymodel = (MDBManager)newModel;
		} catch (Exception nullex){
			
		}
	}

	public MDBManager getMymodel() {
		return mymodel;
	}
	
	
	
	
	

}
