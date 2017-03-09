package components.dbConnector;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import components.FileMetaGenerator.view.VMetaGenerator;
import components.mainAppFrame.view.VMainAppFrame;

public class VDBConnector extends JDialog{
	
	JPanel panel = new JPanel();
	JLabel label = new JLabel("Meta Data JSon File");	
	JButton button1 = new JButton("Choose File");
	JFileChooser jfc = new JFileChooser();
	private MDBConnector mdbc;
 	PPanel sp,sn,dbn,log,pass;
 	File file;
 	JButton button;
	
	
	public VDBConnector(MDBConnector mdbc) {
		super(VMainAppFrame.getInstance());
		this.mdbc = mdbc;
		this.setModal(true);
		setTitle("DBConnector");
		setModalityType(VMetaGenerator.ModalityType.DOCUMENT_MODAL);
		setSize(300, 300);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(7, 1));
		this.add(sp = new PPanel("Server Port",mdbc.getServerPort()));
		this.add(sn = new PPanel("Server Name",mdbc.getServerName()));
		this.add(dbn = new PPanel("DB Name",mdbc.getDbName()));
		this.add(log = new PPanel("Login",mdbc.getLogin()));
		this.add(pass = new PPanel("Password",mdbc.getPassword()));
		panel.add(label);
		VDBConnector self = this;
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JSon File", "json");
				jfc.setFileFilter(filter);
				int returnVal = jfc.showOpenDialog(self);

		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		        	file = jfc.getSelectedFile();
		        }
								
			}
		});
		panel.add(button1);
		this.add(panel);
		button = new JButton(new CConnectToDB(this));
		button.setPreferredSize(getMinimumSize());
		 
			
		this.add(button);
		this.setVisible(true);
	}
	
	class PPanel extends JPanel{
		
		private String name;
		JTextField textField = new JTextField();
		JPanel panel1 = new JPanel();
		
		public PPanel(String name) {
			this.name = name;
			textField.setPreferredSize( new Dimension( 115, 24 ) );
			this.setLayout(new GridLayout(1, 2));
			this.add(new JLabel(name));
			this.add(textField);
		}
		
		public PPanel(String name, String defVal) {
			this(name);
			textField.setText(defVal);
			
		}
		
		public String getText(){
			return textField.getText();
		}
		
	}

	public PPanel getSp() {
		return sp;
	}

	public PPanel getSn() {
		return sn;
	}

	public PPanel getDbn() {
		return dbn;
	}

	public PPanel getLog() {
		return log;
	}

	public PPanel getPass() {
		return pass;
	}

	public MDBConnector getMdbc() {
		return mdbc;
	}
	
	public File getFile() {
		return file;
	}

	
	
}



