package components.ivResource.utilities.abstractLocalFile.model;

import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import components.ivResource.utilities.abstractIvResource.model.AbstractMivResource;
import components.ivResourceElement.ResourceRow;
import components.ivResourceElement.interfaces.IivResourceElement;
import components.ivResourceElement.interfaces.IivResourceRow;

public class MLocalFileAdapter extends ALocalFileModel {

	public MLocalFileAdapter(File file) {
		super(file);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean save() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean read() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addNewRow(IivResourceRow r) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ResourceRow[] find(ResourceRow r, int returnLimit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImageIcon getIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLoadedRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IivResourceRow[] getRows(int start, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractMivResource sortAll(int[][] crit) {
		// TODO Auto-generated method stub
		return null;
	}
	
	


}
