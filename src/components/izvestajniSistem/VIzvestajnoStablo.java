package components.izvestajniSistem;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

import components.dbManager.COpenTable;
import components.dbManager.MDBManager;

public class VIzvestajnoStablo extends JTree{
	
	private MIZvestajnogSistema mis;
	
	public VIzvestajnoStablo(MIZvestajnogSistema mis) {
		this.setModel(new DefaultTreeModel(null));
		this.addMouseListener(new CGenerateReport());
	}

@Override
	public void setModel(TreeModel newModel) {
		// TODO Auto-generated method stub
		super.setModel(newModel);
		try {
		this.mis = (MIZvestajnogSistema)newModel;
		} catch (Exception nullex){
			
		}
	}

	public MIZvestajnogSistema getModelIzvSis(){
		return mis;
	}

}