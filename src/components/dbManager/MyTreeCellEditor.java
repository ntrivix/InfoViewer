package components.dbManager;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;

import components.metadata.MSingleMetaD;

public class MyTreeCellEditor extends DefaultTreeCellEditor{
	
	private MSingleMetaD stavka = null;
	private JTextField edit=null; 

	
	public MyTreeCellEditor(JTree arg0, DefaultTreeCellRenderer arg1) {
		super(arg0, arg1);
	}
	
	/*public void actionPerformed(ActionEvent e){
		stavka.setName(e.getActionCommand());
	}*/
	
	public boolean isCellEditable(EventObject arg0) {
		 if (arg0 instanceof MouseEvent)
			 if (((MouseEvent)arg0).isShiftDown()){
				return true;
			 }	
		 return false;
	 } 
	
	/*public Component getTreeCellEditorComponent(JTree arg0, Object arg1, boolean arg2,
			 boolean arg3, boolean arg4, int arg5) {
			 super.getTreeCellEditorComponent(arg0,arg1,arg2,arg3,arg4,arg5);
			 stavka = (MyMutableTreeNode)arg1;
			 edit=new JTextField(arg1.toString());
			 edit.addActionListener(this);
			 return edit;
			 } */

}
