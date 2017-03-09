package components.ivResource.textFile.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import components.ivResource.textFile.model.MTextFile;
import components.ivResource.utilities.abstractIvResource.viewer.VivResource;

public class VTextFile extends VivResource {

	private JTextArea textArea;

	public VTextFile(MTextFile model) {
		super(model);
		this.setName(model.toString());
		model.readFile();
		//
		//
		textArea = new JTextArea(model.getText());
		// textArea.setFont(new Font("Serif", Font.ITALIC, 16));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		JScrollPane areaScrollPane = new JScrollPane(textArea);
		areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		areaScrollPane.setPreferredSize(new Dimension(250, 250));
		this.add(areaScrollPane, BorderLayout.CENTER);
		
		
	}

	@Override
	public String toString() {
		return model.toString();
	}

	@Override
	public void close() {
		super.close();
		this.getParent().remove(this);
		if (model instanceof MTextFile){
			((MTextFile) model).resetTextToNull();
		}
		//pozovi funkciju za ciscenje modela
	}

	public JTextArea getTextArea() {
		return textArea;
	}
	
	

}
