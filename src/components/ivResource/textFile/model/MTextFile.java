package components.ivResource.textFile.model;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.swing.ImageIcon;

import components.ivResource.textFile.view.VTextFile;
import components.ivResource.utilities.abstractIvResource.viewer.VivResource;
import components.ivResource.utilities.abstractLocalFile.model.MLocalFileAdapter;
import components.ivResourceElement.eText.model.METext;
import components.mainAppFrame.view.VMainAppFrame;
import configs.AppConfigs;
import utilities.ResourceLoader;

public class MTextFile extends MLocalFileAdapter {
	
	public MTextFile(File file) {
		super(file);
	}

	private static ImageIcon icon = ResourceLoader.getImageIcon("icon/file.png",AppConfigs.NAV_ICON_w, AppConfigs.NAV_ICON_H);
	private METext text;
	

	public METext getTextObject() {
		return text;
	}
	
	public String getText(){
		return text.getText();
	}

	public String readFile() {
		if (text != null)
			return text.getText();
		
		if (this.connect()) {
			try {
				byte[] b = new byte[(int) fileRW.length()];
				fileRW.read(b);
				text = new METext(new String(b, StandardCharsets.UTF_8));
			} catch (IOException e) {
				//e.printStackTrace();
				text = new METext("");
			}
		}
		this.disconnect();
		return text.getText();
	}

	@Override
	public boolean save() {
		
		this.connect();
		VivResource tv = (VivResource)VMainAppFrame.getInstance().getFileViewer().getSelectedComponent();
		if (tv instanceof VTextFile){
			text.setText(((VTextFile) tv).getTextArea().getText());
			
		}
		try {
			fileRW.write(text.getText().getBytes(StandardCharsets.UTF_8), 0,
					text.getText().getBytes(StandardCharsets.UTF_8).length);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.disconnect();
		return false;
	}

	@Override
	public ImageIcon getIcon() {
		return icon;
	}

	@Override
	public boolean read() {
		this.readFile();
		return true;
	}

	public void resetTextToNull(){
		text = null;
	}
	
	
}
