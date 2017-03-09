package globalActions;

import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import components.dbConnector.MDBConnector;
import components.dbConnector.VDBConnector;
import components.dbManager.MDBManager;

public class CDBOpenConnector extends AbstractAction{
	
	public CDBOpenConnector() {
		putValue(SMALL_ICON, utilities.ResourceLoader.getImageIcon("icon/delete.png", 20, 20));		
		putValue(SHORT_DESCRIPTION, "Connect to DB");
		putValue(NAME, "Connect to DataBase");
		//putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl E"));
		//putValue(MNEMONIC_KEY, new Integer('E'));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		new VDBConnector(new MDBConnector());
		
	}

}
