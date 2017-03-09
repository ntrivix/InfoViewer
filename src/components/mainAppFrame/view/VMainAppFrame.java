package components.mainAppFrame.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.alee.laf.WebLookAndFeel;

import components.dbManager.VDBManager;
import components.fileNavigator.controller.CRefreshFileNavigator;
import components.fileNavigator.model.MFileNavigator;
import components.fileNavigator.view.VCurrentPath;
import components.fileNavigator.view.VFileNavigator;
import components.ivResource.utilities.abstractIvResource.viewer.VivResource;
import components.ivResource.utilities.interfaces.IivResource;
import components.izvestajniSistem.VIzvestajnoStablo;
import components.mainAppFrame.model.MMainAppFrame;
import components.menus_toolbars.mainMenu.VMainMenu;
import components.menus_toolbars.toolbar.VToolbar;
import components.statusBar.VStatusBar;
import utilities.observer.Observable;
import utilities.observer.Observer;

public class VMainAppFrame extends JFrame implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2061658328258434981L;
	private final int SPLITER = 200;

	private MMainAppFrame mMainAppFrame;
	
	private JSplitPane splitpane;
	private VFileNavigator vFileNavigator;
	private JTabbedPane fileViewer;
	private JPanel panel = new JPanel();
	private VStatusBar statusBar = new VStatusBar();
	private VDBManager vdbm;
	private VIzvestajnoStablo vis;
	
	private static VMainAppFrame instance;
	public static VMainAppFrame getInstance(){
		return (instance == null) ? instance = new VMainAppFrame() : instance;
	}
	
	
	
	public VDBManager getVdbm() {
		return vdbm;
	}



	private VMainAppFrame() throws HeadlessException {
		super();
		
		WebLookAndFeel.install();
		
		mMainAppFrame = MMainAppFrame.getInstance();
		mMainAppFrame.addObserver(this);
		
		this.setTitle(mMainAppFrame.getAppName());
		this.setSize(mMainAppFrame.getDimension());
		this.setLocationRelativeTo(null);
		panel.setLayout(new GridLayout(2, 1));
		panel.add(new VToolbar());
		this.setJMenuBar(new VMainMenu());
		this.setIconImage( utilities.ResourceLoader.getImageIcon("icon/mindmap.png", 20, 20).getImage());
		statusBar.setLeftStatus("InfoViewer-2016");
		
		
		
		//this.setIconImage();
		//promeni look and feel
		
		
		//VMetaGenerator vmg = new VMetaGenerator(this);
		//vmg.setVisible(true);
		
		MFileNavigator mFileNavigator = mMainAppFrame.getmFileNavigator();
		vFileNavigator = new VFileNavigator(mFileNavigator);
		
		JPanel pathNav = new JPanel(new BorderLayout());
		VCurrentPath pathViewer = new VCurrentPath(mFileNavigator);
		JButton refresh = new JButton(new CRefreshFileNavigator());
		pathNav.add(pathViewer,BorderLayout.CENTER);
		pathNav.add(refresh, BorderLayout.EAST);
		//add(pathNav,BorderLayout.LINE_START);
		panel.add(pathNav);
		this.add(panel, BorderLayout.PAGE_START);
		this.add(statusBar,BorderLayout.PAGE_END);
		
		JScrollPane listScroll = new JScrollPane(vFileNavigator);
		
		
		JPanel navPanel = new JPanel(new BorderLayout());
		vdbm = new VDBManager();
		vis = new VIzvestajnoStablo(null);
		JScrollPane scrollpane = new JScrollPane(vdbm);
		JScrollPane scrollpane2 = new JScrollPane(vis);
		JSplitPane jsp0 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollpane, scrollpane2);
		JSplitPane jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, listScroll, jsp0);
		navPanel.add(jsp,BorderLayout.CENTER);
		jsp.setResizeWeight(0.33);
		jsp0.setResizeWeight(0.5);
		
		
		
		fileViewer = new JTabbedPane();
		fileViewer.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				System.out.println("changed");
				Object selected = getFileViewer().getSelectedComponent();
				try {
					MMainAppFrame.getInstance().setActive((IivResource) ((VivResource)selected).getModel());
				} catch (Exception ex)
				{
					MMainAppFrame.getInstance().setActive(null);
				}
			}
		});
	
		splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, navPanel, fileViewer);
		splitpane.setDividerLocation(SPLITER);
		
		this.add(splitpane, BorderLayout.CENTER);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
		
	}

	public VFileNavigator getvFileNavigator() {
		return vFileNavigator;
	}

	public JTabbedPane getFileViewer() {
		return fileViewer;
	}

	public VStatusBar getStatusBar() {
		return statusBar;
	}

	@Override
	public void update(Observable o, Object obj, String command) {
		if (command.equals("active_element")){
			if (obj!=null){
				statusBar.setMiddleStatus(""+((IivResource)obj).toString());
				statusBar.setRightStatus(""+((IivResource)obj).getSize()+"kB");
			}
			else{
				statusBar.setMiddleStatus("");
				statusBar.setRightStatus("");
			}
		}
	}



	public VIzvestajnoStablo getVIzvestajnogSistema() {
		return vis;
	}
	
	

}
