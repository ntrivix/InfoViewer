package components.menus_toolbars.mainMenu;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utilities.ResourceLoader;

public class AboutDialog extends JDialog{
	
	private ImageIcon img1 = ResourceLoader.getImageIcon("icon/nikola.jpg");
	private ImageIcon img2 = ResourceLoader.getImageIcon("icon/jovana.jpg");
	
	public AboutDialog() {
		setTitle("About");
		setSize(700, 377);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(1, 2));
		this.add(new Person("Jovana Mihaljcic", img2));
		this.add(new Person("Nikola Trivic", img1));

		
	}
	
	private class Person extends JPanel{
		
		private ImageIcon image;
		private JLabel label;
		
		public Person(String name, ImageIcon image) {
			label = new JLabel(name);
			this.image = image;
			this.setLayout(new BorderLayout());
			//this.add(image, BorderLayout.CENTER);
			//this.add(label);
		}
		
		 @Override
		    protected void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        g.drawImage(image.getImage(), 0, 0, null); // see javadoc for more info on the parameters            
		    }
		
	}
	

}
