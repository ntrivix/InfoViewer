package components.menus_toolbars.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

import components.FileMetaGenerator.VChooser;
import components.newFile.CNewFile;
import globalActions.ActionManager;

public class VToolbar extends JToolBar{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public VToolbar() {
		//setSize(500,30);
		super("Toolbar");
		this.add(new CNewFile());
		//this.add(ActionManager.getcOpenFile());
		this.add(ActionManager.getCodbm());
		this.addSeparator();
		this.add(ActionManager.getcSave());
		this.addSeparator();
		//this.add(ActionManager.getcClose());
		this.add(ActionManager.getCloseAll());
		this.add(ActionManager.getcDelete());
		this.setRollover(true);
		this.setFloatable(false);
	}

}
