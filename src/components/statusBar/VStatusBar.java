package components.statusBar;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class VStatusBar extends JPanel{
	
	private JPanel left = new JPanel();
	private JPanel middle = new JPanel();
	private JPanel right = new JPanel();
	private JLabel llabel = new JLabel();
	private JLabel mlabel = new JLabel();
	private JLabel rlabel = new JLabel();
	
	public VStatusBar() {
		setLayout(new GridLayout(1, 3));
		left.add(llabel);
		this.add(left);
		middle.add(mlabel);
		this.add(middle);
		right.add(rlabel);
		this.add(right);
	}
	
	public void setLeftStatus(String str){
		llabel.setText(str);
		revalidate();
	}
	
	public void setRightStatus(String str){
		rlabel.setText(str);		
		revalidate();
	}
	
	public void setMiddleStatus(String str){
		mlabel.setText(str);
		revalidate();
	}
	
	

}
