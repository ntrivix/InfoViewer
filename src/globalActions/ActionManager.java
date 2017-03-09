package globalActions;

import javax.swing.JDialog;

import components.FileMetaGenerator.VChooser;
import components.FileMetaGenerator.view.VMetaGenerators.VAbstractGenerator;
import components.ivResource.utilities.abstractLocalFile.model.VFindRow;
import sun.print.resources.serviceui;

public class ActionManager {
	
	private static CSave cSave = new CSave();
	private static COpenFile cOpenFile = new COpenFile();
	private static COpenDBManager codbm = new COpenDBManager();
	private static CCloseApplication cCloseApp = new CCloseApplication();
	private static CDelete cDelete = new CDelete();
	private static CCLoseAll closeAll = new CCLoseAll();	
	private static CDBOpenConnector connector = new CDBOpenConnector();
	
	
	public static COpenFoundFiles getOpenFound(VFindRow parent) {
		return new COpenFoundFiles(parent);
	}
	
	public static COpenFoundInDB getOpenFoundINDB(VFindRow parent) {
		return new COpenFoundInDB(parent);
	}
	public static CSave getcSave() {
		return cSave;
	}
	public static COpenFile getcOpenFile() {
		return cOpenFile;
	}	
	public static COpenDBManager getCodbm() {
		return codbm;
	}
	public static CCloseApplication getcCloseApp() {
		return cCloseApp;
	}
	public static CDelete getcDelete() {
		return cDelete;
	}
	public static CCLoseAll getCloseAll() {
		return closeAll;
	}
	public static CGenerateNewData getGenNewData(VChooser parent) {
		return new CGenerateNewData(parent);
	}
	public static CSaveMetaData getSaveMD(String md, VAbstractGenerator vag){
		return new CSaveMetaData(md,vag);
	}
	public static CDBOpenConnector getConnector() {
		return connector;
	}
	
	
	
}
