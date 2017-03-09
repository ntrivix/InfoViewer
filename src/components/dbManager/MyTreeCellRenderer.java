package components.dbManager;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import components.metadata.MSingleMetaD;

public class MyTreeCellRenderer extends DefaultTreeCellRenderer{
	
	public MyTreeCellRenderer() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Component getTreeCellRendererComponent(JTree arg0, Object arg1, boolean arg2, boolean arg3, boolean arg4,
			int arg5, boolean arg6) {
		
		Object o = ((DefaultMutableTreeNode) arg1);
		
		super.getTreeCellRendererComponent(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	
		if (o instanceof MSingleMetaD){
			if (((MSingleMetaD)o).isPrimary())
				setIcon(utilities.ResourceLoader.getImageIcon("icon/key.png", 15, 15));
			
		}
		
		//super.getTreeCellRendererComponent(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
		
		return this;
	}

}
