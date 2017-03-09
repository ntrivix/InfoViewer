package components.dbManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.tree.DefaultMutableTreeNode;

import components.fileNavigator.view.VFileNavigator;
import components.ivResource.dbResource.DbTableModel;
import components.ivResource.utilities.ResourceFactoryView;
import components.ivResource.utilities.abstractIvResource.viewer.VivResource;
import components.ivResource.utilities.abstractLocalFile.model.ALocalFileModel;
import components.mainAppFrame.view.VMainAppFrame;
import components.metadata.MMetaData;
import components.metadata.MSingleMetaD;

public class COpenTable extends MouseAdapter{
	
	VDBManager vdbm;
	DefaultMutableTreeNode obj;
	
	public COpenTable(VDBManager vdbm) {
		this.vdbm = vdbm;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		if(e.getClickCount()==2){
			obj = (DefaultMutableTreeNode) vdbm.getLastSelectedPathComponent();
			if (obj instanceof MMetaData){
				VFileNavigator fileNavigator = VMainAppFrame.getInstance().getvFileNavigator();
				try {
					
					String hash = vdbm.getMymodel().getDbName()+((MMetaData)obj).getName();
					VivResource tmpView;
					if ((tmpView = ResourceFactoryView.getByHash(hash)) == null){
						tmpView = ResourceFactoryView.getViwer(new DbTableModel((MMetaData)obj, vdbm.getMymodel().getDbName(), vdbm.getMymodel().getCon()));
					}


					VMainAppFrame.getInstance().getFileViewer().add(tmpView);
					VMainAppFrame.getInstance().getFileViewer().setSelectedComponent(tmpView);
				} catch (Exception ex){
					ex.printStackTrace();
				}
			}
		}
			//System.out.println("Radi");
		// TODO Auto-generated method stub
		
	}
	
	

}
