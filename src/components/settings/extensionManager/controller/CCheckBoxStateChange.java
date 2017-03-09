package components.settings.extensionManager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import components.mainAppFrame.model.MMainAppFrame;
import components.settings.extensionManager.view.VExtension;
import components.settings.settingsManager.SettingsManager;

public class CCheckBoxStateChange implements ActionListener{
	
	private static CCheckBoxStateChange instance;
	
	private CCheckBoxStateChange() {
		
	}
	
	public static CCheckBoxStateChange getInstance(){
		return (instance == null)? instance = new CCheckBoxStateChange(): instance;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//OVO MORA DA SE PROVERI
		VExtension j =(VExtension)((JCheckBox)e.getSource()).getParent();
		j.getModel().setSelected(j.isSelected());
		SettingsManager.getInstance().save();
		MMainAppFrame.getInstance().getmFileNavigator().refresh();
	}
}
