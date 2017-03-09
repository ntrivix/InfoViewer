package components.ivResourceElement.eText.model;

import components.ivResource.textFile.model.MTextFile;
import components.ivResourceElement.AbstractResourceElement;

public class METext extends AbstractResourceElement<String> {
	

	public METext(String data) {
		super(data);
		if (data == null)
			this.data = new String("");
		else
			this.data = data;	
	}


	private MTextFile file;

	
	public METext(String text, MTextFile file){
		this(text);
		this.setFile(file);
	}
	
	public String getText() {
		return data;
	}

	public void setText(String text) {
		if (text == null)
			return;
		this.data = text;
	}

	public MTextFile getFile() {
		return file;
	}

	public void setFile(MTextFile file) {
		this.file = file;
	}

	@Override
	public boolean update() {
		try{
			return file.save();
		} catch (Exception e){
			System.out.println("Doslo je do greske pri cuvanju. File unutar METext je verovatno null");
		}
		return false;
	}

	
	public boolean delete() {
		this.setText("");
		return update();
	}

	@Override
	public int compareTo(AbstractResourceElement<String> o) {
		// TODO Auto-generated method stub
		return 0;
	}



}
