package components.fileNavigator.controller;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import components.fileNavigator.view.VFileNavigator;
import components.ivResource.utilities.ResourceFactoryView;
import components.ivResource.utilities.abstractIvResource.viewer.VivResource;
import components.ivResource.utilities.abstractLocalFile.model.ALocalFileModel;
import components.mainAppFrame.model.MMainAppFrame;
import components.mainAppFrame.view.VMainAppFrame;

public class CFileNavigator extends MouseAdapter implements ListSelectionListener, KeyListener, FocusListener {

	@Override
	public void valueChanged(ListSelectionEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		if (e.getClickCount() == 2) {
			open();
		} else {
			VFileNavigator fileNavigator = VMainAppFrame.getInstance().getvFileNavigator();
			MMainAppFrame.getInstance().setActive(fileNavigator.getSelectedValue());
		}
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		if (arg0.getKeyChar() == '\n'){ //CHECK FOR ENTER
			open();
		}
	}
	
	private void open(){
		VFileNavigator fileNavigator = VMainAppFrame.getInstance().getvFileNavigator();
		try {
			fileNavigator.getmFileNavigator().setLocation( ((ALocalFileModel)fileNavigator.getSelectedValue()).getLocation());
			VivResource tmpView = ResourceFactoryView.getViwer(fileNavigator.getSelectedValue());
			VMainAppFrame.getInstance().getFileViewer().add(tmpView);
			VMainAppFrame.getInstance().getFileViewer().setSelectedComponent(tmpView);
		} catch (Exception e){
			
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		//valueChanged(null);		
	}

	@Override
	public void focusLost(FocusEvent e) {
		
	}

}
