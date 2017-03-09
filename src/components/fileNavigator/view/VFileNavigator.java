package components.fileNavigator.view;

import javax.swing.JList;

import components.fileNavigator.controller.CFileNavigator;
import components.fileNavigator.model.MFileNavigator;
import components.fileNavigator.utilities.NavigatorListCellRender;
import components.ivResource.utilities.interfaces.IivResource;


public class VFileNavigator extends JList<IivResource> {

	private MFileNavigator mFileNavigator;
	
	public VFileNavigator(MFileNavigator mFileNavigator) {
		super(mFileNavigator);
		this.mFileNavigator = mFileNavigator;
		CFileNavigator listener = new CFileNavigator();
		this.addListSelectionListener(listener);
		this.addMouseListener(listener);
		this.setCellRenderer(new NavigatorListCellRender());
		this.addKeyListener(listener);
		this.addFocusListener(listener);
	}

	public MFileNavigator getmFileNavigator() {
		return mFileNavigator;
	}
	
	public void refresh(){
		mFileNavigator.refresh();
	}

}
