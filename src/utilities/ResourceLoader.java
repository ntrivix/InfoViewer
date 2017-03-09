package utilities;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.rmi.CORBA.Util;
import javax.swing.ImageIcon;

public class ResourceLoader {
	public static BufferedImage loadImage(String name) {
		return loadImage(name, 0, 0);
	}

	private static BufferedImage loadImage(String name, int width, int height) {
		try {
			if (!name.startsWith("/resources/")) {
				name = "/resources/" + name;
			}
			BufferedImage original = ImageIO.read(Util.class.getResource(name));
			if (width != 0 && height != 0) {
				BufferedImage resized = new BufferedImage(width, height, original.getType());
				Graphics2D g = resized.createGraphics();
				g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
				g.drawImage(original, 0, 0, width, height, 0, 0, original.getWidth(), original.getHeight(), null);
				g.dispose();
				return resized;
			}
			return original;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ImageIcon getImageIcon(String name) {
		return new ImageIcon(loadImage(name));
	}

	public static ImageIcon getImageIcon(String name, int width, int height){	
		return new ImageIcon(loadImage(name,width,height));
	 }
}
