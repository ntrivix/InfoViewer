package appCore;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import components.mainAppFrame.model.MMainAppFrame;
import components.mainAppFrame.view.VMainAppFrame;

public class InfoViewer {

	public static MMainAppFrame mInfoViewer;
	public static VMainAppFrame vInfoViewer;
	
	public static void main(String[] args) {
		mInfoViewer = MMainAppFrame.getInstance();
		vInfoViewer = VMainAppFrame.getInstance();
	}
}
