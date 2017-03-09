package components.settings.settingsManager;

import javax.swing.JDialog;
import javax.swing.JScrollPane;

import components.settings.extensionManager.view.VExtensionManager;

public class VGeneralSettings extends JDialog{
	
	VExtensionManager vem = VExtensionManager.getInstance();
	public static VGeneralSettings instance;
	
	public static VGeneralSettings getInstance(){
		return (instance == null)? instance = new VGeneralSettings():instance;
	}

	private VGeneralSettings() {
		super();
		
		JScrollPane jsp = new JScrollPane(vem);
		this.add(jsp);
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		setModalityType(ModalityType.APPLICATION_MODAL);
		
	}
	
	

}
