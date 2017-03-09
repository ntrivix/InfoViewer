package components.ivResource.sekDat.view;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import components.ivResource.sekDat.model.MSekDat;
import components.ivResource.utilities.abstractLocalFile.model.ALocalFileModel;
import components.ivResource.utilities.abstractLocalFile.model.VLocalFile;
import utilities.observer.Observable;
import utilities.observer.Observer;

public class VsekDat extends VLocalFile implements Observer {
	JTable bufferTable;
	JScrollPane jsp;
	JSplitPane split;

	public VsekDat(MSekDat model) {
		super(model);
		model.addObserver(this);
	}

	@Override
	public void setCentralStructure() {
		this.model = (ALocalFileModel) model;
		split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		table = new JTable((TableModel) model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

		bufferTable = new JTable();

		jsp = new JScrollPane(bufferTable);
		jsp.setVisible(false);

		split.add(new JScrollPane(table));
		split.add(jsp);

		this.add(split, BorderLayout.CENTER);
		// this.add(new JButton());

		model.read();
	}

	@Override
	public void update(Observable o, Object obj, String command) {
		if (command.equals("showBufferTable")) {
			bufferTable.setModel((TableModel) obj);
			jsp.setVisible(true);
			split.setDividerLocation(0.7);
			System.out.println("ok");
		}
		if (command.equals("hideBufferTable")) {
			jsp.setVisible(false);
		}
		validate();
		repaint();

	}

}
