package components.settings.extensionManager.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import components.mainAppFrame.model.MMainAppFrame;
import components.settings.extensionManager.controller.CCheckBoxStateChange;
import components.settings.extensionManager.model.MExtension;
import components.settings.extensionManager.model.MExtensionManager;
import components.settings.settingsManager.SettingsManager;

public class VExtension extends JPanel{
	
	private MExtension m;
	private JCheckBox jcb = new JCheckBox();

	public VExtension(MExtension m) {
		super();
		this.m = m;
		jcb.setText(m.getExtension());
		jcb.setSelected(m.isSelected());
		this.add(jcb);
		JButton jb = new JButton("X");
		VExtension self = this;
		jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MExtensionManager.getInstance().removeExtension(m);
				VExtensionManager.getInstance().getVExtensionContainer().remove(self);
				SettingsManager.getInstance().save();
				VExtensionManager.getInstance().revalidate();
				VExtensionManager.getInstance().repaint();
				MMainAppFrame.getInstance().getmFileNavigator().refresh();
			}
		});
		this.add(jb);
		
		jcb.addActionListener(CCheckBoxStateChange.getInstance());
		
		
	}
	
	
	
	public boolean isSelected(){
		return jcb.isSelected();
	}
	
	public MExtension getModel(){
		return m;
	}

}
