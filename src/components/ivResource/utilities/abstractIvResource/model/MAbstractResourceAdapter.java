package components.ivResource.utilities.abstractIvResource.model;

import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import components.ivResource.sekDat.model.MSekDat;
import components.ivResource.utilities.abstractLocalFile.model.ALocalFileModel;
import components.ivResourceElement.ResourceRow;
import components.ivResourceElement.interfaces.IivResourceElement;
import components.ivResourceElement.interfaces.IivResourceRow;
import components.metadata.MMetaData;

public class MAbstractResourceAdapter extends ALocalFileModel {

	public MAbstractResourceAdapter(File file) {
		super(file);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean connect() {
		return false;
	}

	@Override
	public void disconnect() {
	}

	@Override
	public boolean save() {
		return false;
	}

	@Override
	public boolean rename(String newName) {
		return false;
	}

	@Override
	public boolean readMetaData() {
		return false;
	}

	@Override
	public boolean read() {
		return false;
	}

	@Override
	public boolean addNewRow(IivResourceRow r) {
		return false;
	}

	@Override
	public ResourceRow[] find(ResourceRow r, int returnLimit) {
		return null;
	}

	public File getLocation() {
		return null;
	}

	@Override
	public ImageIcon getIcon() {
		return null;
	}

	@Override
	public boolean delete() {
		return false;
	}

	@Override
	public int getSize() {
		return 0;
	}

	@Override
	public IivResourceRow[] getRows(int start, int count) {
		return null;
	}

	@Override
	public int getLoadedRowCount() {
		return 0;
	}

	@Override
	public AbstractMivResource sortAll(int[][] crit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHashCode() {
		// TODO Auto-generated method stub
		return null;
	}


}
