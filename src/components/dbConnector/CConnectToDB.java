package components.dbConnector;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.json.JSONObject;

import components.dbManager.MDBManager;
import components.izvestajniSistem.MIZvestajnogSistema;
import components.mainAppFrame.view.VMainAppFrame;

public class CConnectToDB extends AbstractAction{
	
	private VDBConnector vdbc;
	private MDBConnector mdbc;
	
	public CConnectToDB(VDBConnector vdbc) {
		this.vdbc = vdbc;
		//putValue(SMALL_ICON, utilities.ResourceLoader.getImageIcon("icon/new.png", 20, 20));
		putValue(SHORT_DESCRIPTION, "Connect to DB");
		putValue(NAME, "Connect");
		mdbc = vdbc.getMdbc();
		//putValue(MNEMONIC_KEY, new Integer('N'));
		//putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl N"));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		mdbc.setDbName(vdbc.getDbn().getText());
		mdbc.setServerName(vdbc.getSn().getText());
		mdbc.setLogin(vdbc.getLog().getText());
		mdbc.setPassword(vdbc.getPass().getText());
		mdbc.setServerPort(vdbc.getSp().getText());
		mdbc.setFile(vdbc.getFile());
		
		JSONObject json;
		
		try {
			
			String md = "";
			RandomAccessFile raf;
			try {
				if (mdbc.getFile()==null){
					JDialog fileNotFound = new JDialog(vdbc, "File not found", true);
					fileNotFound.add(new JLabel("You must choose JSON file"));
					fileNotFound.setSize(200, 100);
					fileNotFound.setLocationRelativeTo(null);
					fileNotFound.setVisible(true);
					return;
				}
				else {
					raf = new RandomAccessFile(mdbc.getFile(), "r");
					md = raf.readLine();
					raf.close();
				}
				json = new JSONObject(md);
			} catch (Exception e) {
				json = new JSONObject();
			}
			mdbc.getConnection();
			try {
				MDBManager mdbm = MDBManager.getInstance(mdbc.getDbName(), mdbc.getConnection(), json);
				MIZvestajnogSistema mis = new MIZvestajnogSistema();
				VMainAppFrame.getInstance().getVdbm().setModel(mdbm);
				VMainAppFrame.getInstance().getVIzvestajnogSistema().setModel(mis);
				VMainAppFrame.getInstance().getVdbm().updateUI();
				vdbc.setVisible(false);	
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JDialog dialog = new JDialog(vdbc);
			dialog.add(new JLabel("Greska:"+e.getMessage()));
			dialog.setLocationRelativeTo(null);
			dialog.setModalityType(ModalityType.APPLICATION_MODAL);
			dialog.pack();
			dialog.setVisible(true);}
		
	}

}
