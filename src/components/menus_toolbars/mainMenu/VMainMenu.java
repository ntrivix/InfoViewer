package components.menus_toolbars.mainMenu;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import com.sun.glass.events.KeyEvent;

import components.newFile.CNewFile;
import components.settings.settingsManager.COpenSettingsManager;
import globalActions.ActionManager;
import globalActions.CAboutDialog;

public class VMainMenu extends JMenuBar{

	public VMainMenu() {
		super();
		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		file.add(new CNewFile());
		//file.add(ActionManager.getcOpenFile());
		file.add(ActionManager.getCodbm());
		file.addSeparator();
		file.add(ActionManager.getcSave());
		file.addSeparator();
		//file.add(ActionManager.getcClose());
		file.add(ActionManager.getCloseAll());
		file.add(ActionManager.getcDelete());
		file.addSeparator();
		file.add(ActionManager.getcCloseApp());
		add(file);
		
		JMenu sett = new JMenu("Settings");
		sett.setMnemonic(KeyEvent.VK_S);
		sett.add(new COpenSettingsManager());
		add(sett);
		JMenu about = new JMenu("About");
		about.setMnemonic(KeyEvent.VK_A);
		about.add(new CAboutDialog());
		add(about);
	
	}
	
	
	

}
