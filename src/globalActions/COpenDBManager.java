package globalActions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import components.dbConnector.MDBConnector;
import components.dbConnector.VDBConnector;

public class COpenDBManager extends AbstractAction{
	
	public COpenDBManager() {
		putValue(SMALL_ICON, utilities.ResourceLoader.getImageIcon("icon/Database.png", 17, 17));		
		putValue(SHORT_DESCRIPTION, "Open File");
		putValue(SHORT_DESCRIPTION, "Connect to DB");
		putValue(NAME, "Connect to DataBase");
		putValue(MNEMONIC_KEY, new Integer('D'));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl D"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MDBConnector mdbc = new MDBConnector();
		mdbc.setDbName("ui-2015-tim202.4");
		mdbc.setLogin("ui-2015-tim202.4");
		mdbc.setServerPort("1433");
		mdbc.setPassword("ui-2015-tim202.4.7joni6");
		mdbc.setServerName("147.91.175.155");
		new VDBConnector(mdbc);
		// TODO Auto-generated method stub
		 
	}

}
